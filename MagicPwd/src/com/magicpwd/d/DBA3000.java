/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.d;

import com.magicpwd._comn.Char;
import com.magicpwd._comn.Keys;
import com.magicpwd._comn.Kind;
import com.magicpwd._comn.S1S2;
import com.magicpwd._comn.S1S3;
import com.magicpwd._comn.Item;
import com.magicpwd._comn.Tplt;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.DBC3000;
import com.magicpwd._face.IEditItem;
import com.magicpwd._util.Hash;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.m.UserMdl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Amon
 */
public class DBA3000
{

    public static boolean readDBVersion(DBAccess dba)
    {
        dba.addTable(DBC3000.P30F0000);
        dba.addWhere(DBC3000.P30F0001, "versions");
        dba.addWhere(DBC3000.P30F0002, ConsDat.VERSIONS);
        boolean b = false;
        try
        {
            ResultSet rest = dba.executeSelect();
            b = rest.next();
            rest.close();
        }
        catch (SQLException ex)
        {
            Logs.exception(ex);
        }
        return b;
    }

    /**
     * 添加用户过滤条件
     * @param dba
     */
    private static void addUserSort(DBAccess dba)
    {
//        dba.addWhere(DBC3000.P30F0102, "");
        dba.addWhere(DBC3000.P30F0104, UserMdl.getUserId());
    }

    /**
     * 添加显示排序
     * @param dba
     */
    private static void addDataSort(DBAccess dba)
    {
        boolean asc = ConsCfg.DEF_TRUE.equals(UserMdl.getCfg().getCfg(ConsCfg.CFG_VIEW_LIST_ASC));
        String key = UserMdl.getCfg().getCfg(ConsCfg.CFG_VIEW_LIST_ASC, "");

        // 按访问频率排序
        if (ConsEnv.LIST_SORT_FEQ.equals(key))
        {
            dba.addSort(DBC3000.P30F0101, asc);
            return;
        }

        // 按注册时间排序
        if (ConsEnv.LIST_SORT_REG.equals(key))
        {
            dba.addSort(DBC3000.P30F0106, asc);
            return;
        }

        // 按到期时间排序
        if (ConsEnv.LIST_SORT_DUE.equals(key))
        {
            dba.addSort(DBC3000.P30F010A, asc);
            return;
        }

        // 按显示名称排序
        dba.addSort(DBC3000.P30F0107, asc);
    }

    /**
     * 读取口令信息
     * @param rest
     * @param list
     * @throws SQLException
     */
    private static void getNameData(ResultSet rest, List<Keys> list) throws SQLException
    {
        Keys item;
        while (rest.next())
        {
            item = new Keys();
            item.setP30F0101(rest.getInt(DBC3000.P30F0101));
            item.setP30F0102(rest.getInt(DBC3000.P30F0102));
            item.setP30F0103(rest.getString(DBC3000.P30F0103));
            item.setP30F0104(rest.getString(DBC3000.P30F0104));
            item.setP30F0105(rest.getString(DBC3000.P30F0105));
            item.setP30F0106(rest.getTimestamp(DBC3000.P30F0106));
            item.setP30F0107(rest.getString(DBC3000.P30F0107));
            item.setP30F0108(rest.getString(DBC3000.P30F0108));
            item.setP30F0109(rest.getString(DBC3000.P30F0109));
            item.setP30F010A(rest.getTimestamp(DBC3000.P30F010A));
            item.setP30F010B(rest.getString(DBC3000.P30F010B));
            item.setP30F010C(rest.getString(DBC3000.P30F010C));
            list.add(item);
        }
        rest.close();
    }

    /**
     * 读取指定类别下的所有口令数据
     * @param kindHash
     * @param list
     * @return
     */
    public static boolean readKeysList(String kindHash, List<Keys> list)
    {
        // 数据库连接初始化
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            // 查询语句拼接
            dba.addTable(DBC3000.P30F0100);
            addUserSort(dba);
            dba.addWhere(DBC3000.P30F0105, kindHash);
            addDataSort(dba);

            getNameData(dba.executeSelect(), list);
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    /**
     * 查询标题或关键搜索中含有指定字符的口令数据
     * @param text
     * @param list
     * @return
     */
    public static boolean findUserData(String text, List<Keys> list)
    {
        if (!Util.isValidate(text))
        {
            return false;
        }

        // 数据库连接初始化
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            text = '%' + Util.text2DB(text.trim()).replace(' ', '%').replace('+', '%') + '%';
            // 查询语句拼接
            dba.addTable(DBC3000.P30F0100);
            addUserSort(dba);
            dba.addWhere(Util.format("LOWER({0}) LIKE '{2}' OR LOWER({1}) LIKE '{2}'", DBC3000.P30F0107, DBC3000.P30F0108, text.toLowerCase()));
            addDataSort(dba);

            getNameData(dba.executeSelect(), list);
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    /**
     * 查询在当前日期到指定日期之间的口令数据
     * @param time
     * @param list
     * @return
     */
    public static boolean findTimeNote(Timestamp s, Timestamp e, List<Keys> list)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F0100);
            addUserSort(dba);
            dba.addWhere(DBC3000.P30F0109, "BETWEEN " + s.toString() + " AND " + e.toString(), true);
            addDataSort(dba);

            getNameData(dba.executeSelect(), list);
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
    }

    public static String findUserNote(String text)
    {
        // 数据库连接初始化
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            text = '%' + Util.text2DB(text.replace(' ', '%').replace('+', '%')) + '%';

            // 查询语句拼接
            dba.addTable(DBC3000.P30F0100);
            dba.addColumn(DBC3000.P30F0103);
            dba.addColumn(DBC3000.P30F0107);
            dba.addColumn(DBC3000.P30F0108);
            dba.addWhere(DBC3000.P30F0104, UserMdl.getUserId());
            dba.addWhere(Util.format("LOWER({0}) LIKE '{2}' OR LOWER({1}) LIKE '{2}'", DBC3000.P30F0107, DBC3000.P30F0108, text.toLowerCase()));
            dba.addWhere(DBC3000.P30F0102, ConsDat.PWDS_STAT_1);
            dba.addWhere(DBC3000.P30F0106, ConsDat.HASH_NOTE);

            ResultSet rest = dba.executeSelect();
            if (rest.next())
            {
                return rest.getString(DBC3000.P30F0103);
            }
            return "";
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return "";
        }
        finally
        {
            dba.close();
        }
    }

    public static boolean readPwdsData(Keys keys)
    {
        // 数据库连接初始化
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            // 查询语句拼接
            dba.addTable(DBC3000.P30F0100);
            dba.addColumn(DBC3000.P30F0101);
            dba.addColumn(DBC3000.P30F0102);
            dba.addColumn(DBC3000.P30F0103);
            dba.addColumn(DBC3000.P30F0104);
            dba.addColumn(DBC3000.P30F0105);
            dba.addColumn(DBC3000.P30F0106);
            dba.addColumn(DBC3000.P30F0107);
            dba.addColumn(DBC3000.P30F0108);
            dba.addColumn(DBC3000.P30F0109);
            dba.addColumn(DBC3000.P30F010A);
            dba.addColumn(DBC3000.P30F010B);
            dba.addColumn(DBC3000.P30F010C);
            //dba.addWhere(DBC3000.P30F0102, keys.getP30F0102());
            dba.addWhere(DBC3000.P30F0103, keys.getP30F0103());
            dba.addWhere(DBC3000.P30F0104, keys.getP30F0104());

            ResultSet rest = dba.executeSelect();
            if (!rest.next())
            {
                return false;
            }

            keys.setP30F0102(rest.getInt(DBC3000.P30F0102));
            keys.setP30F0103(rest.getString(DBC3000.P30F0103));
            keys.setP30F0105(rest.getString(DBC3000.P30F0105));
            keys.setP30F0106(rest.getTimestamp(DBC3000.P30F0106));
            keys.setP30F0107(rest.getString(DBC3000.P30F0107));
            keys.setP30F0108(rest.getString(DBC3000.P30F0108));
            keys.setP30F0109(rest.getString(DBC3000.P30F0109));
            keys.setP30F010A(rest.getTimestamp(DBC3000.P30F010A));
            keys.setP30F010B(rest.getString(DBC3000.P30F010B));
            keys.setP30F010C(rest.getString(DBC3000.P30F010C));

            // 口令内容读取
            dba.reset();
            dba.addTable(DBC3000.P30F0200);
            dba.addColumn(DBC3000.P30F0203);
            dba.addWhere(DBC3000.P30F0202, keys.getP30F0103());
            dba.addSort(DBC3000.P30F0201);
            rest = dba.executeSelect();
            StringBuffer sb = keys.getPassword().getP30F0203();
            while (rest.next())
            {
                sb.append(rest.getString(DBC3000.P30F0203));
            }
            keys.getPassword().setP30F0202(keys.getP30F0103());
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    public static boolean savePwdsData(Keys keys)
    {
        // 数据库连接初始化
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            // 数据更新时，首先删除已有数据，再添加数据
            if (Util.isValidateHash(keys.getP30F0103()))
            {
                if (keys.isHistBack())
                {
                    backup(dba, keys);
                }
                remove(dba, keys);
            }

            update(dba, keys);
            dba.executeBatch();
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    public static boolean deletePwdsData(String hash)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            String DELETE = "DELETE FROM {0} WHERE {1}='{2}'";
            // 删除信息数据
            dba.addBatch(Util.format(DELETE, DBC3000.P30F0100, DBC3000.P30F0103, hash));
            // 删除内容数据
            dba.addBatch(Util.format(DELETE, DBC3000.P30F0200, DBC3000.P30F0202, hash));
            // 删除信息备份
            dba.addBatch(Util.format(DELETE, DBC3000.P30F0A00, DBC3000.P30F0A03, hash));
            // 删除内容备份
            dba.addBatch(Util.format(DELETE, DBC3000.P30F0B00, DBC3000.P30F0B03, hash));
            dba.executeBatch();
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    /**
     * 数据备份
     * @param dba
     * @param keys
     * @throws SQLException
     */
    private static void backup(DBAccess dba, Keys keys) throws SQLException
    {
        String hash = Hash.hash(false);

        dba.addParam(DBC3000.P30F0A01, hash);
        dba.addParam(DBC3000.P30F0A02, DBC3000.P30F0102, false);
        dba.addParam(DBC3000.P30F0A03, DBC3000.P30F0103, false);
        dba.addParam(DBC3000.P30F0A04, DBC3000.P30F0104, false);
        dba.addParam(DBC3000.P30F0A05, DBC3000.P30F0105, false);
        dba.addParam(DBC3000.P30F0A06, DBC3000.P30F0106, false);
        dba.addParam(DBC3000.P30F0A07, DBC3000.P30F0107, false);
        dba.addParam(DBC3000.P30F0A08, DBC3000.P30F0108, false);
        dba.addParam(DBC3000.P30F0A09, DBC3000.P30F0109, false);
        dba.addParam(DBC3000.P30F0A0A, DBC3000.P30F010A, false);
        dba.addParam(DBC3000.P30F0A0B, DBC3000.P30F010B, false);
        dba.addParam(DBC3000.P30F0A0C, DBC3000.P30F010C, false);
        dba.addWhere(DBC3000.P30F0103, keys.getP30F0103());
        dba.addCopyBatch(DBC3000.P30F0A00, DBC3000.P30F0100);
        dba.reset();

        dba.addParam(DBC3000.P30F0B01, hash);
        dba.addParam(DBC3000.P30F0B02, DBC3000.P30F0201, false);
        dba.addParam(DBC3000.P30F0B03, DBC3000.P30F0202, false);
        dba.addParam(DBC3000.P30F0B04, DBC3000.P30F0203, false);
        dba.addWhere(DBC3000.P30F0202, keys.getP30F0103());
        dba.addCopyBatch(DBC3000.P30F0B00, DBC3000.P30F0200);
        dba.reset();
    }

    /**
     * 数据清除
     * @param dba
     * @param pwds
     * @throws SQLException
     */
    private static void remove(DBAccess dba, Keys pwds) throws SQLException
    {
        if (!Util.isValidateHash(pwds.getP30F0103()))
        {
            return;
        }

        dba.addTable(DBC3000.P30F0200);
        dba.addWhere(DBC3000.P30F0202, pwds.getP30F0103());
        dba.addDeleteBatch();
        dba.reset();
    }

    /**
     * 数据只在
     * @param dba
     * @param keys
     * @throws SQLException
     */
    private static void update(DBAccess dba, Keys keys) throws SQLException
    {
        dba.addTable(DBC3000.P30F0100);
        dba.addParam(DBC3000.P30F0101, keys.getP30F0101());
        dba.addParam(DBC3000.P30F0102, keys.getP30F0102());
        dba.addParam(DBC3000.P30F0104, keys.getP30F0104());
        dba.addParam(DBC3000.P30F0105, keys.getP30F0105());
        dba.addParam(DBC3000.P30F0106, keys.getP30F0106().toString());
        dba.addParam(DBC3000.P30F0107, Util.text2DB(keys.getP30F0107()));
        dba.addParam(DBC3000.P30F0108, Util.text2DB(keys.getP30F0108()));
        dba.addParam(DBC3000.P30F0109, Util.text2DB(keys.getP30F0109()));
        dba.addParam(DBC3000.P30F010A, keys.getP30F010A() != null ? keys.getP30F010A().toString() : null);
        dba.addParam(DBC3000.P30F010B, Util.text2DB(keys.getP30F010B()));
        dba.addParam(DBC3000.P30F010C, Util.text2DB(keys.getP30F010C()));

        if (Util.isValidateHash(keys.getP30F0103()))
        {
            // 数据更新
            dba.addWhere(DBC3000.P30F0103, keys.getP30F0103());
            dba.addUpdateBatch();
        }
        else
        {
            // 新增数据
            keys.setP30F0103(Hash.hash(false));
            dba.addParam(DBC3000.P30F0103, keys.getP30F0103());
            dba.addInsertBatch();
        }

        dba.reset();

        StringBuffer pwd = keys.getPassword().getP30F0203();
        int len = pwd.length();
        int idx = 0;
        int t1 = 0;
        int t2 = t1 + ConsEnv.PWDS_DATA_SIZE;
        // 循环处理每一节段数据
        while (t2 < len)
        {
            dba.addTable(DBC3000.P30F0200);
            dba.addParam(DBC3000.P30F0201, idx++);
            dba.addParam(DBC3000.P30F0202, keys.getP30F0103());
            dba.addParam(DBC3000.P30F0203, pwd.substring(t1, t2));

            dba.addInsertBatch();
            dba.reset();

            t1 = t2;
            t2 += ConsEnv.PWDS_DATA_SIZE;
        }

        // 处理剩余节段数据
        dba.addTable(DBC3000.P30F0200);
        dba.addParam(DBC3000.P30F0201, idx);
        dba.addParam(DBC3000.P30F0202, keys.getP30F0103());
        dba.addParam(DBC3000.P30F0203, pwd.substring(t1));

        dba.addInsertBatch();
        dba.reset();
    }

    public static boolean deleteKindData(String root, Kind item, int step)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.C2010100);
            dba.addParam(DBC3000.C2010104, root);
            dba.addParam(DBC3000.C2010101, DBC3000.C2010101 + "+" + step, false);
            dba.addWhere(DBC3000.C2010104, item.getC2010103());
            dba.addUpdateBatch();

            dba.reset();

            dba.addTable(DBC3000.C2010100);
            dba.addWhere(DBC3000.C2010103, item.getC2010103());
            dba.addDeleteBatch();

            dba.executeBatch();
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    public static boolean updateKindData(Kind item)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.C2010100);
            dba.addParam(DBC3000.C2010101, item.getC2010101());
            dba.addParam(DBC3000.C2010102, item.getC2010102());
            dba.addParam(DBC3000.C2010104, item.getC2010104());
            dba.addParam(DBC3000.C2010105, Util.text2DB(item.getC2010105()));// 类别名称
            dba.addParam(DBC3000.C2010106, Util.text2DB(item.getC2010106()));// 类别提示
            dba.addParam(DBC3000.C2010107, Util.text2DB(item.getC2010107()));//
            dba.addParam(DBC3000.C2010108, Util.text2DB(item.getC2010108()));// 类别描述
            dba.addParam(DBC3000.C2010109, DBC3000.SQL_NOW, false);// 更新时间
            if (Util.isValidateHash(item.getC2010103()))
            {
                dba.addWhere(DBC3000.C2010103, item.getC2010103());// 类别索引
                dba.executeUpdate();
            }
            else
            {
                item.setC2010103(Hash.hash(false));
                dba.addParam(DBC3000.C2010103, item.getC2010103());// 类别索引
                dba.addParam(DBC3000.C201010A, DBC3000.SQL_NOW, false);// 更新时间
                dba.executeInsert();
            }
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    public static List<S1S3> selectKindData()
    {
        List<S1S3> list = new ArrayList<S1S3>();

        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.C2010100);
            dba.addColumn(DBC3000.C2010103);// 类别索引
            dba.addColumn(DBC3000.C2010105);// 类别名称
            dba.addColumn(DBC3000.C2010106);// 类别提示
            dba.addColumn(DBC3000.C2010108);// 类别提示
            dba.addSort(DBC3000.C2010101, true);

            ResultSet rest = dba.executeSelect();
            S1S3 item;
            while (rest.next())
            {
                item = new S1S3();
                item.setK(rest.getString(DBC3000.C2010103));
                item.setV1(rest.getString(DBC3000.C2010105));
                item.setV2(rest.getString(DBC3000.C2010106));
                item.setV3(rest.getString(DBC3000.C2010108));
                list.add(item);
            }
            rest.close();
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        finally
        {
            dba.close();
        }
        return list;
    }

    public static List<Kind> selectKindData(String typeHash)
    {
        List<Kind> list = new ArrayList<Kind>();

        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.C2010100);
            dba.addColumn(DBC3000.C2010101);
            dba.addColumn(DBC3000.C2010102);
            dba.addColumn(DBC3000.C2010103);// 类别索引
            dba.addColumn(DBC3000.C2010104);
            dba.addColumn(DBC3000.C2010105);// 类别名称
            dba.addColumn(DBC3000.C2010106);// 类别提示
            dba.addColumn(DBC3000.C2010107);
            dba.addColumn(DBC3000.C2010108);// 类别提示
            dba.addWhere(DBC3000.C2010104, typeHash);
            dba.addSort(DBC3000.C2010101, true);

            ResultSet rest = dba.executeSelect();
            Kind item;
            while (rest.next())
            {
                item = new Kind();
                item.setC2010101(rest.getInt(DBC3000.C2010101));
                item.setC2010102(rest.getInt(DBC3000.C2010102));
                item.setC2010103(rest.getString(DBC3000.C2010103));
                item.setC2010104(rest.getString(DBC3000.C2010104));
                item.setC2010105(rest.getString(DBC3000.C2010105));
                item.setC2010106(rest.getString(DBC3000.C2010106));
                item.setC2010107(rest.getString(DBC3000.C2010107));
                item.setC2010108(rest.getString(DBC3000.C2010108));
                list.add(item);
            }
            rest.close();
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        finally
        {
            dba.close();
        }
        return list;
    }

    public static boolean insertTpltKind(S1S2 kindItem, String kindDesp)
    {
        DBAccess dba = new DBAccess();

        kindItem.setK(Hash.hash(false));
        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F1100);
            dba.addParam(DBC3000.P30F1101, "0", false);
            dba.addParam(DBC3000.P30F1102, "-1", false);
            dba.addParam(DBC3000.P30F1103, kindItem.getK());
            dba.addParam(DBC3000.P30F1104, "");
            dba.addParam(DBC3000.P30F1105, Util.text2DB(kindItem.getV1()));
            dba.addParam(DBC3000.P30F1106, Util.text2DB(kindItem.getV2()));
            dba.addParam(DBC3000.P30F1107, Util.text2DB(kindDesp));
            dba.addParam(DBC3000.P30F1108, DBC3000.SQL_NOW, false);
            dba.addParam(DBC3000.P30F1109, DBC3000.SQL_NOW, false);

            dba.executeInsert();
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    public static boolean updateTpltKind(S1S2 kindItem, String kindDesp)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F1100);
            dba.addParam(DBC3000.P30F1105, Util.text2DB(kindItem.getV1()));
            dba.addParam(DBC3000.P30F1106, Util.text2DB(kindItem.getV2()));
            dba.addParam(DBC3000.P30F1107, Util.text2DB(kindDesp));
            dba.addParam(DBC3000.P30F1108, DBC3000.SQL_NOW, false);
            dba.addWhere(DBC3000.P30F1103, kindItem.getK());

            dba.executeUpdate();
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    public static List<Tplt> selectTpltData(String hash)
    {
        List<Tplt> kindList = new ArrayList<Tplt>();

        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F1100);
            dba.addColumn(DBC3000.P30F1101);
            dba.addColumn(DBC3000.P30F1103);
            dba.addColumn(DBC3000.P30F1104);
            dba.addColumn(DBC3000.P30F1105);
            dba.addColumn(DBC3000.P30F1106);
            dba.addColumn(DBC3000.P30F1107);
            if (Util.isValidate(hash))
            {
                dba.addWhere(DBC3000.P30F1104, hash);
            }
            dba.addSort(DBC3000.P30F1101, true);

            ResultSet rest = dba.executeSelect();
            Tplt item;
            while (rest.next())
            {
                item = new Tplt();
                item.setP30F1101(rest.getInt(DBC3000.P30F1101));
                item.setP30F1103(rest.getString(DBC3000.P30F1103));
                item.setP30F1104(rest.getString(DBC3000.P30F1104));
                item.setP30F1105(rest.getString(DBC3000.P30F1105));
                item.setP30F1106(rest.getString(DBC3000.P30F1106));
                item.setP30F1107(rest.getString(DBC3000.P30F1107));

                kindList.add(item);
            }

            rest.close();
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        finally
        {
            dba.close();
        }
        return kindList;
    }

    public static boolean deleteTpltData(S1S2 tpltItem)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F1100);
            dba.addWhere(DBC3000.P30F1104, tpltItem.getK());
            dba.addDeleteBatch();

            dba.reset();

            dba.addTable(DBC3000.P30F1100);
            dba.addWhere(DBC3000.P30F1103, tpltItem.getK());
            dba.addDeleteBatch();

            dba.executeBatch();
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    public static boolean updateTpltData(Tplt tpltItem)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F1100);
            dba.addParam(DBC3000.P30F1101, tpltItem.getP30F1101());
            dba.addParam(DBC3000.P30F1102, tpltItem.getP30F1102());
            dba.addParam(DBC3000.P30F1104, tpltItem.getP30F1104());
            dba.addParam(DBC3000.P30F1105, Util.text2DB(tpltItem.getP30F1105()));
            dba.addParam(DBC3000.P30F1106, Util.text2DB(tpltItem.getP30F1106()));
            dba.addParam(DBC3000.P30F1107, Util.text2DB(tpltItem.getP30F1107()));
            dba.addParam(DBC3000.P30F1108, DBC3000.SQL_NOW, false);

            if (Util.isValidateHash(tpltItem.getP30F1103()))
            {
                dba.addWhere(DBC3000.P30F1103, tpltItem.getP30F1103());
                dba.executeUpdate();
            }
            else
            {
                tpltItem.setP30F1103(Hash.hash(false));
                dba.addParam(DBC3000.P30F1103, tpltItem.getP30F1103());
                dba.addParam(DBC3000.P30F1109, DBC3000.SQL_NOW, false);
                dba.executeInsert();
            }

            dba.executeUpdate();
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    public static boolean updateTpltData(List<S1S2> list)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            int index = list.size();
            while (index > 0)
            {
                dba.addTable(DBC3000.P30F1100);
                dba.addParam(DBC3000.P30F1101, --index);
                dba.addWhere(DBC3000.P30F1103, list.get(index).getK());
                dba.addUpdateBatch();
                dba.init();
            }

            dba.executeBatch();
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
        return true;
    }

    public static boolean selectTpltData(String kindHash, List<IEditItem> tpltList)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F1100);
            dba.addColumn(DBC3000.P30F1102);
            dba.addColumn(DBC3000.P30F1105);
            dba.addColumn(DBC3000.P30F1106);
            dba.addWhere(DBC3000.P30F1104, kindHash);
            dba.addSort(DBC3000.P30F1101, true);

            ResultSet rest = dba.executeSelect();
            Item kv;
            while (rest.next())
            {
                kv = new Item();
                kv.setType(rest.getInt(DBC3000.P30F1102));
                kv.setName(rest.getString(DBC3000.P30F1105));
                kv.setData(rest.getString(DBC3000.P30F1106));
                tpltList.add(kv);
            }
            rest.close();
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    public static boolean deleteCharData(Char charItem)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F2100);
            dba.addParam(DBC3000.P30F2102, ConsDat.PWDS_STAT_3);
            dba.addWhere(DBC3000.P30F2103, charItem.getP30F2103());// 类别索引

            dba.executeDelete();
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    public static boolean updateCharData(Char charItem)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F2100);
            dba.addParam(DBC3000.P30F2101, charItem.getP30F2101());// 显示排序
            dba.addParam(DBC3000.P30F2102, charItem.getP30F2102());// 使用状态
            dba.addParam(DBC3000.P30F2104, Util.text2DB(charItem.getP30F2104()));// 空间名称
            dba.addParam(DBC3000.P30F2105, Util.text2DB(charItem.getP30F2105()));// 空间提示
            dba.addParam(DBC3000.P30F2106, Util.text2DB(charItem.getP30F2106()));// 空间字符
            dba.addParam(DBC3000.P30F2108, DBC3000.SQL_NOW, false);// 更新日期

            if (Util.isValidateHash(charItem.getP30F2103()))
            {
                dba.addWhere(DBC3000.P30F2103, charItem.getP30F2103());// 空间索引
                dba.executeUpdate();
            }
            else
            {
                charItem.setP30F2103(Hash.hash(false));
                dba.addParam(DBC3000.P30F2103, charItem.getP30F2103());// 空间索引
                dba.addParam(DBC3000.P30F2109, DBC3000.SQL_NOW, false);// 创建日期
                dba.executeInsert();
            }
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    /**
     * 读取字符空间列表
     * @return
     */
    public static List<Char> selectCharData()
    {
        List<Char> charList = new LinkedList<Char>();

        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F2100);
            dba.addColumn(DBC3000.P30F2103);
            dba.addColumn(DBC3000.P30F2104);
            dba.addColumn(DBC3000.P30F2105);
            dba.addColumn(DBC3000.P30F2106);
            dba.addSort(DBC3000.P30F2101, true);

            ResultSet rest = dba.executeSelect();
            Char charItem;
            while (rest.next())
            {
                charItem = new Char();
                charItem.setP30F2103(rest.getString(DBC3000.P30F2103));
                charItem.setP30F2104(rest.getString(DBC3000.P30F2104));
                charItem.setP30F2105(rest.getString(DBC3000.P30F2105));
                charItem.setP30F2106(rest.getString(DBC3000.P30F2106));

                charList.add(charItem);
            }

            rest.close();
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        finally
        {
            dba.close();
        }
        return charList;
    }

    public static boolean pickupHistData(String curr, String hist)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F0100);
            dba.addParam(DBC3000.P30F0102, ConsDat.PWDS_STAT_2);
            dba.addWhere(DBC3000.P30F0103, curr);
            dba.addUpdateBatch();

            StringBuffer sql = new StringBuffer();
            sql.append("insert into P30F0100 (P30F0101,");
            sql.append("    P30F0102,");
            sql.append("    P30F0103,");
            sql.append("    P30F0104,");
            sql.append("    P30F0105,");
            sql.append("    P30F0106,");
            sql.append("    P30F0107,");
            sql.append("    P30F0108,");
            sql.append("    P30F0109,");
            sql.append("    P30F010A,");
            sql.append("    P30F010B,");
            sql.append("    P30F010C,");
            sql.append("    P30F010D)");
            sql.append(" (select " + ConsDat.PWDS_STAT_1 + ",");
            sql.append("           P30F0102,");
            sql.append(Util.format("   '{0}',", com.magicpwd._util.Date.curTime()));
            sql.append("           P30F0104,");
            sql.append("           P30F0105,");
            sql.append("           P30F0106,");
            sql.append("           P30F0107,");
            sql.append("           P30F0108,");
            sql.append("           P30F0109,");
            sql.append("           P30F010A,");
            sql.append("           P30F010B,");
            sql.append("           P30F010C,");
            sql.append("           P30F010D");
            sql.append("      from P30F0100");
            sql.append(Util.format(" where P30F0103='{0}')", hist));
            dba.addBatch(sql.toString());

            dba.executeBatch();
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    public static boolean deleteHistData(String keysHash)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F0100);
            dba.addWhere(DBC3000.P30F0103, keysHash);
            dba.addWhere(DBC3000.P30F0102, ConsDat.PWDS_STAT_2);

            dba.executeDelete();
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    public static boolean deleteHistData(String keysHash, String updtHash)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F0100);
            dba.addParam(DBC3000.P30F0103, keysHash);
            dba.addWhere(DBC3000.P30F0103, updtHash);// 类别索引

            dba.executeDelete();
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    public static boolean selectHistData(String hash, Keys pwds)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F0100);
            dba.addColumn(DBC3000.P30F0107);
            dba.addColumn(DBC3000.P30F0108);
            dba.addColumn(DBC3000.P30F0109);
            dba.addColumn(DBC3000.P30F010A);
            dba.addColumn(DBC3000.P30F010B);
            dba.addColumn(DBC3000.P30F0109);
            dba.addColumn(DBC3000.P30F010A);
            dba.addWhere(DBC3000.P30F0103, hash);
            dba.addWhere(DBC3000.P30F0102, ConsDat.PWDS_STAT_2);

            ResultSet rest = dba.executeSelect();
            StringBuffer sb = new StringBuffer();
            while (rest.next())
            {
                sb.append(rest.getString(DBC3000.P30F0109));
            }
            pwds.setP30F0109(sb.toString());

            rest.close();
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }

    public static boolean selectHistData(String hash, List<S1S2> list)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F0100);
            dba.addColumn(DBC3000.P30F0103);
            dba.addWhere(DBC3000.P30F0103, hash);
            dba.addWhere(DBC3000.P30F0102, ConsDat.PWDS_STAT_2);
            dba.addSort(DBC3000.P30F0103, false);

            S1S2 item;
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(ConsEnv.VIEW_DATE);
            ResultSet rest = dba.executeSelect();
            while (rest.next())
            {
                item = new S1S2();
                item.setK(rest.getString(DBC3000.P30F0103));
                item.setV1(sdf.format(new Date(Long.parseLong(item.getK(), 16))));
                item.setV2(item.getV1());

                list.add(item);
            }

            rest.close();
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.close();
        }
    }
}
