/*
 *  Copyright (C) 2010 Amon
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd.d.db;

import com.magicpwd.__i.IEditItem;
import com.magicpwd._comn.prop.Char;
import com.magicpwd._comn.mpwd.Keys;
import com.magicpwd._comn.prop.Kind;
import com.magicpwd._comn.S1S2;
import com.magicpwd._comn.S1S3;
import com.magicpwd._comn.item.EditItem;
import com.magicpwd._comn.prop.Tplt;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.DBC3000;
import com.magicpwd._enum.AppView;
import com.magicpwd._util.Hash;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.m.UserMdl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Amon
 */
public class DBA3000
{

    public static String readConfig(String key)
    {
        DBAccess dba = new DBAccess();

        String result = null;
        try
        {
            dba.init();
            dba.addTable(DBC3000.P30F0000);
            dba.addColumn(DBC3000.P30F0002);
            dba.addWhere(DBC3000.P30F0001, Util.text2DB(key));

            ResultSet resultSet = dba.executeSelect();
            if (resultSet.next())
            {
                result = resultSet.getString(DBC3000.P30F0002);
            }
            resultSet.close();
        }
        catch (SQLException ex)
        {
            Logs.exception(ex);
            result = null;
        }

        dba.dispose();
        return result;
    }

    public static boolean saveConfig(String key, String value)
    {
        key = Util.text2DB(key);
        if (!com.magicpwd._util.Char.isValidate(key, 1, DBC3000.P30F0001_SIZE))
        {
            return false;
        }

        DBAccess dba = new DBAccess();

        boolean isOK = false;
        try
        {
            dba.init();
            dba.addTable(DBC3000.P30F0000);
            dba.addWhere(DBC3000.P30F0001, key);

            ResultSet resultSet = dba.executeSelect();
            boolean isUpdate = resultSet.next();
            resultSet.close();

            dba.reInit();
            dba.addTable(DBC3000.P30F0000);
            dba.addParam(DBC3000.P30F0002, Util.text2DB(value));
            dba.addParam(DBC3000.P30F0003, DBC3000.SQL_NOW, false);
            if (isUpdate)
            {
                dba.addWhere(DBC3000.P30F0001, key);
                dba.executeUpdate();
            }
            else
            {
                dba.addParam(DBC3000.P30F0001, key);
                dba.executeInsert();
            }
            isOK = true;
        }
        catch (SQLException ex)
        {
            Logs.exception(ex);
            isOK = false;
        }

        dba.dispose();
        return isOK;
    }

    /**
     * 添加用户过滤条件
     * @param dba
     */
    private static void addUserSort(DBAccess dba, UserMdl cfg)
    {
//        dba.addWhere(DBC3000.P30F0102, "");
        dba.addWhere(DBC3000.P30F0105, cfg.getCode());
    }

    /**
     * 添加显示排序
     * @param dba
     */
    private static void addDataSort(DBAccess dba, UserMdl cfg)
    {
        boolean asc = ConsCfg.DEF_TRUE.equals(cfg.getCfg(AppView.mexp, ConsCfg.CFG_VIEW_LIST_ASC, ConsCfg.DEF_FALSE));
        String key = cfg.getCfg(AppView.mexp, ConsCfg.CFG_VIEW_LIST_KEY, "09");

        if (!Pattern.matches("^[0-9A-Z]{2}$", key))
        {
            key = "09";
        }
        dba.addSort("P30F01" + key, asc);
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
            item.setP30F0103(rest.getInt(DBC3000.P30F0103));
            item.setP30F0104(rest.getString(DBC3000.P30F0104));
            item.setP30F0105(rest.getString(DBC3000.P30F0105));
            item.setP30F0106(rest.getString(DBC3000.P30F0106));
            item.setP30F0107(rest.getString(DBC3000.P30F0107));
            item.setP30F0108(rest.getString(DBC3000.P30F0108));
            item.setP30F0109(rest.getString(DBC3000.P30F0109));
            item.setP30F010A(rest.getString(DBC3000.P30F010A));
            item.setP30F010B(rest.getString(DBC3000.P30F010B));
            item.setP30F010C(rest.getString(DBC3000.P30F010C));
            item.setP30F010D(rest.getString(DBC3000.P30F010D));
            item.setP30F010E(rest.getString(DBC3000.P30F010E));
            item.setP30F010F(rest.getString(DBC3000.P30F010F));
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
    public static boolean readKeysList(UserMdl cfg, String kindHash, List<Keys> list)
    {
        // 数据库连接初始化
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            // 查询语句拼接
            dba.addTable(DBC3000.P30F0100);
            dba.addWhere(DBC3000.P30F0106, kindHash);
            addUserSort(dba, cfg);
            addDataSort(dba, cfg);

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
            dba.dispose();
        }
    }

    private static String text2Query(String text)
    {
        text = Util.text2DB(text.toLowerCase().replace('　', ' ').replace('＋', '+'));
        return text.replaceFirst("^[+\\s]*", "%").replaceFirst("[+\\s]*$", "%").replaceAll("[+%\\s]+", "%");
    }

    /**
     * 查询标题或关键搜索中含有指定字符的口令数据
     * @param text
     * @param list
     * @return
     */
    public static boolean findUserData(UserMdl cfg, String text, List<Keys> list)
    {
        if (!com.magicpwd._util.Char.isValidate(text))
        {
            return false;
        }

        // 数据库连接初始化
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            // 查询语句拼接
            dba.addTable(DBC3000.P30F0100);
            dba.addWhere(com.magicpwd._util.Char.format("LOWER({0}) LIKE '{2}' OR LOWER({1}) LIKE '{2}'", DBC3000.P30F0109, DBC3000.P30F010A, text2Query(text)));
            addUserSort(dba, cfg);
            addDataSort(dba, cfg);

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
            dba.dispose();
        }
    }

    public static boolean findUnitList(UserMdl cfg, List<S1S2> list)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            StringBuilder buf = new StringBuilder();
            buf.append("SELECT ");
            buf.append(DBC3000.C2010103);
            buf.append(" FROM ");
            buf.append(DBC3000.C2010100);
            buf.append(" WHERE ").append(DBC3000.C2010107).append("='unit'");

            dba.addTable(DBC3000.P30F0100);
            dba.addColumn(DBC3000.P30F0104);
            dba.addColumn(DBC3000.P30F0109);
            dba.addColumn(DBC3000.P30F010A);
            dba.addWhere(DBC3000.P30F0106 + " IN (" + buf.toString() + ')');
            addUserSort(dba, cfg);
            addDataSort(dba, cfg);

            ResultSet rest = dba.executeSelect();
            S1S2 item;
            while (rest.next())
            {
                item = new S1S2();
                item.setK(rest.getString(DBC3000.P30F0104));
                item.setV(rest.getString(DBC3000.P30F0109));
                item.setV2(rest.getString(DBC3000.P30F010A));
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
    }

    /**
     * 查询在当前日期到指定日期之间的口令数据
     * @param time
     * @param list
     * @return
     */
    public static boolean findHintList(UserMdl cfg, java.sql.Timestamp s, java.sql.Timestamp t, List<Keys> list)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F0100);
            dba.addWhere(DBC3000.P30F010D + " BETWEEN '" + s + "' AND '" + t + '\'');
            addUserSort(dba, cfg);
            addDataSort(dba, cfg);

            getNameData(dba.executeSelect(), list);
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            dba.dispose();
            return false;
        }
    }

    public static boolean findUserNote(UserMdl cfg, String text, java.util.List<S1S2> list)
    {
        if (!com.magicpwd._util.Char.isValidate(text))
        {
            return false;
        }

        // 数据库连接初始化
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            // 查询语句拼接
            dba.addTable(DBC3000.P30F0100);
            dba.addColumn(DBC3000.P30F0104);
            dba.addColumn(DBC3000.P30F0109);
            dba.addColumn(DBC3000.P30F010A);
            dba.addWhere(DBC3000.P30F0105, cfg.getCode());
            dba.addWhere(com.magicpwd._util.Char.format("LOWER({0}) LIKE '{2}' OR LOWER({1}) LIKE '{2}'", DBC3000.P30F0109, DBC3000.P30F010A, text2Query(text)));
            //dba.addWhere(DBC3000.P30F0102, ConsDat.PWDS_MODE_1);
            dba.addWhere(DBC3000.P30F0106, ConsDat.HASH_NOTE);

            ResultSet rest = dba.executeSelect();
            while (rest.next())
            {
                list.add(new S1S2(rest.getString(DBC3000.P30F0104), rest.getString(DBC3000.P30F0109), rest.getString(DBC3000.P30F010A)));
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
            dba.dispose();
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
            dba.addColumn(DBC3000.P30F010D);
            dba.addColumn(DBC3000.P30F010E);
            dba.addColumn(DBC3000.P30F010F);
            //dba.addWhere(DBC3000.P30F0102, keys.getP30F0102());
            dba.addWhere(DBC3000.P30F0104, keys.getP30F0104());
            dba.addWhere(DBC3000.P30F0105, keys.getP30F0105());

            ResultSet rest = dba.executeSelect();
            if (!rest.next())
            {
                return false;
            }

            keys.setP30F0101(rest.getInt(DBC3000.P30F0101));
            keys.setP30F0102(rest.getInt(DBC3000.P30F0102));
            keys.setP30F0103(rest.getInt(DBC3000.P30F0103));
            keys.setP30F0104(rest.getString(DBC3000.P30F0104));
            keys.setP30F0106(rest.getString(DBC3000.P30F0106));
            keys.setP30F0107(rest.getString(DBC3000.P30F0107));
            keys.setP30F0108(rest.getString(DBC3000.P30F0108));
            keys.setP30F0109(rest.getString(DBC3000.P30F0109));
            keys.setP30F010A(rest.getString(DBC3000.P30F010A));
            keys.setP30F010B(rest.getString(DBC3000.P30F010B));
            keys.setP30F010C(rest.getString(DBC3000.P30F010C));
            keys.setP30F010D(rest.getString(DBC3000.P30F010D));
            keys.setP30F010E(rest.getString(DBC3000.P30F010E));
            keys.setP30F010F(rest.getString(DBC3000.P30F010F));

            // 口令内容读取
            dba.reInit();
            dba.addTable(DBC3000.P30F0200);
            dba.addColumn(DBC3000.P30F0203);
            dba.addWhere(DBC3000.P30F0202, keys.getP30F0104());
            dba.addSort(DBC3000.P30F0201);
            rest = dba.executeSelect();
            StringBuffer sb = keys.getPassword().getP30F0203();
            while (rest.next())
            {
                sb.append(rest.getString(DBC3000.P30F0203));
            }
            keys.getPassword().setP30F0202(keys.getP30F0104());
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.dispose();
        }
    }

    public static boolean saveKeysData(Keys keys)
    {
        // 数据库连接初始化
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();
            updateKeys(dba, keys);
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
            dba.dispose();
        }
    }

    public static boolean savePwdsData(Keys keys)
    {
        // 数据库连接初始化
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();
            savePwdsData(dba, keys);
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.dispose();
        }
    }

    public static void savePwdsData(DBAccess dba, Keys keys) throws Exception
    {
        // 数据更新时，首先删除已有数据，再添加数据
        if (com.magicpwd._util.Char.isValidateHash(keys.getP30F0104()))
        {
            if (keys.isHistBack())
            {
                backup(dba, keys.getP30F0104());
            }
            remove(dba, keys);
        }

        updateKeys(dba, keys);
        updatePwds(dba, keys);
        dba.executeBatch();
    }

    public static boolean deletePwdsData(String hash)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            String DELETE = "DELETE FROM {0} WHERE {1}='{2}'";
            // 删除信息数据
            dba.addBatch(com.magicpwd._util.Char.format(DELETE, DBC3000.P30F0100, DBC3000.P30F0104, hash));
            // 删除内容数据
            dba.addBatch(com.magicpwd._util.Char.format(DELETE, DBC3000.P30F0200, DBC3000.P30F0202, hash));
            // 删除信息备份
            dba.addBatch(com.magicpwd._util.Char.format(DELETE, DBC3000.P30F0A00, DBC3000.P30F0A04, hash));
            // 删除内容备份
            dba.addBatch(com.magicpwd._util.Char.format(DELETE, DBC3000.P30F0B00, DBC3000.P30F0B03, hash));
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
            dba.dispose();
        }
    }

    /**
     * 数据备份
     * @param dba
     * @param keys
     * @throws SQLException
     */
    private static void backup(DBAccess dba, String keysHash) throws SQLException
    {
        String hash = com.magicpwd._util.Date.nowTime();

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
        dba.addParam(DBC3000.P30F0A0D, DBC3000.P30F010D, false);
        dba.addParam(DBC3000.P30F0A0E, DBC3000.P30F010E, false);
        dba.addParam(DBC3000.P30F0A0F, DBC3000.P30F010F, false);
        dba.addWhere(DBC3000.P30F0104, keysHash);
        dba.addCopyBatch(DBC3000.P30F0A00, DBC3000.P30F0100);
        dba.reInit();

        dba.addParam(DBC3000.P30F0B01, hash);
        dba.addParam(DBC3000.P30F0B02, DBC3000.P30F0201, false);
        dba.addParam(DBC3000.P30F0B03, DBC3000.P30F0202, false);
        dba.addParam(DBC3000.P30F0B04, DBC3000.P30F0203, false);
        dba.addWhere(DBC3000.P30F0202, keysHash);
        dba.addCopyBatch(DBC3000.P30F0B00, DBC3000.P30F0200);
        dba.reInit();
    }

    /**
     * 数据清除
     * @param dba
     * @param pwds
     * @throws SQLException
     */
    private static void remove(DBAccess dba, Keys pwds) throws SQLException
    {
        if (!com.magicpwd._util.Char.isValidateHash(pwds.getP30F0104()))
        {
            return;
        }

        dba.addTable(DBC3000.P30F0200);
        dba.addWhere(DBC3000.P30F0202, pwds.getP30F0104());
        dba.addDeleteBatch();
        dba.reInit();
    }

    /**
     * 数据只在
     * @param dba
     * @param keys
     * @throws SQLException
     */
    private static void updateKeys(DBAccess dba, Keys keys) throws SQLException
    {
        dba.addTable(DBC3000.P30F0100);
        dba.addParam(DBC3000.P30F0101, keys.getP30F0101());
        dba.addParam(DBC3000.P30F0102, keys.getP30F0102());
        dba.addParam(DBC3000.P30F0103, keys.getP30F0103());
        dba.addParam(DBC3000.P30F0105, keys.getP30F0105());
        dba.addParam(DBC3000.P30F0106, keys.getP30F0106());
        dba.addParam(DBC3000.P30F0107, keys.getP30F0107().toString());
        dba.addParam(DBC3000.P30F0108, keys.getP30F0108());
        dba.addParam(DBC3000.P30F0109, Util.text2DB(keys.getP30F0109()));
        dba.addParam(DBC3000.P30F010A, Util.text2DB(keys.getP30F010A()));
        dba.addParam(DBC3000.P30F010B, Util.text2DB(keys.getP30F010B()));
        dba.addParam(DBC3000.P30F010C, Util.text2DB(keys.getP30F010C()));
        dba.addParam(DBC3000.P30F010D, keys.getP30F010D() != null ? keys.getP30F010D().toString() : null);
        dba.addParam(DBC3000.P30F010E, Util.text2DB(keys.getP30F010E()));
        dba.addParam(DBC3000.P30F010F, Util.text2DB(keys.getP30F010F()));

        if (com.magicpwd._util.Char.isValidateHash(keys.getP30F0104()))
        {
            // 数据更新
            dba.addWhere(DBC3000.P30F0104, keys.getP30F0104());
            dba.addUpdateBatch();
        }
        else
        {
            // 新增数据
            keys.setP30F0104(Hash.hash(false));
            dba.addParam(DBC3000.P30F0104, keys.getP30F0104());
            dba.addInsertBatch();
        }

        dba.reInit();
    }

    public static void updateKeys(String c, String v, String k) throws SQLException
    {
        // 数据库连接初始化
        DBAccess dba = new DBAccess();

        if (!com.magicpwd._util.Char.isValidateHash(k))
        {
            return;
        }
        dba.addTable(DBC3000.P30F0100);
        dba.addParam(c, v);
        dba.addWhere(DBC3000.P30F0104, k);
        dba.executeUpdate();

        dba.dispose();
    }

    private static void updatePwds(DBAccess dba, Keys keys) throws SQLException
    {
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
            dba.addParam(DBC3000.P30F0202, keys.getP30F0104());
            dba.addParam(DBC3000.P30F0203, pwd.substring(t1, t2));

            dba.addInsertBatch();
            dba.reInit();

            t1 = t2;
            t2 += ConsEnv.PWDS_DATA_SIZE;
        }

        // 处理剩余节段数据
        dba.addTable(DBC3000.P30F0200);
        dba.addParam(DBC3000.P30F0201, idx);
        dba.addParam(DBC3000.P30F0202, keys.getP30F0104());
        dba.addParam(DBC3000.P30F0203, pwd.substring(t1));

        dba.addInsertBatch();
        dba.reInit();
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

            dba.reInit();

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
            dba.dispose();
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
            if (com.magicpwd._util.Char.isValidateHash(item.getC2010103()))
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
            dba.dispose();
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
                item.setV(rest.getString(DBC3000.C2010105));
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
            dba.dispose();
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
            dba.dispose();
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
            dba.addParam(DBC3000.P30F1105, Util.text2DB(kindItem.getV()));
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
            dba.dispose();
        }
    }

    public static boolean updateTpltKind(S1S2 kindItem, String kindDesp)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F1100);
            dba.addParam(DBC3000.P30F1105, Util.text2DB(kindItem.getV()));
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
            dba.dispose();
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
            dba.addColumn(DBC3000.P30F1102);
            dba.addColumn(DBC3000.P30F1103);
            dba.addColumn(DBC3000.P30F1104);
            dba.addColumn(DBC3000.P30F1105);
            dba.addColumn(DBC3000.P30F1106);
            dba.addColumn(DBC3000.P30F1107);
            if (com.magicpwd._util.Char.isValidate(hash))
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
                item.setP30F1102(rest.getInt(DBC3000.P30F1102));
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
            dba.dispose();
        }
        return kindList;
    }

    public static boolean deleteTpltData(Tplt tpltItem)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F1100);
            dba.addWhere(com.magicpwd._util.Char.format("{1}='{0}' OR {2}='{0}'", tpltItem.getP30F1103(), DBC3000.P30F1103, DBC3000.P30F1104));
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
            dba.dispose();
        }
    }

    public static boolean saveTpltData(Tplt tpltItem)
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

            if (com.magicpwd._util.Char.isValidateHash(tpltItem.getP30F1103()))
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
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
        finally
        {
            dba.dispose();
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
            dba.dispose();
        }
        return true;
    }

    public static boolean selectTpltData(UserMdl cfg, String kindHash, List<IEditItem> tpltList)
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
            EditItem kv;
            while (rest.next())
            {
                kv = new EditItem(cfg);
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
            dba.dispose();
        }
    }

    public static boolean deleteCharData(Char charItem)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F2100);
            dba.addParam(DBC3000.P30F2102, ConsDat.PWDS_MODE_3);
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
            dba.dispose();
        }
    }

    public static boolean saveCharData(Char charItem)
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

            if (com.magicpwd._util.Char.isValidateHash(charItem.getP30F2103()))
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
            dba.dispose();
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
            dba.dispose();
        }
        return charList;
    }

    public static boolean pickupHistData(String keysHash, String logsHash, int sequence)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            backup(dba, keysHash);

            String DELETE = "DELETE FROM {0} WHERE {1}='{2}'";
            // 删除信息数据
            dba.addBatch(com.magicpwd._util.Char.format(DELETE, DBC3000.P30F0100, DBC3000.P30F0104, keysHash));
            // 删除内容数据
            dba.addBatch(com.magicpwd._util.Char.format(DELETE, DBC3000.P30F0200, DBC3000.P30F0202, keysHash));

            dba.addParam(DBC3000.P30F0101, sequence);
            dba.addParam(DBC3000.P30F0102, DBC3000.P30F0A02, false);
            dba.addParam(DBC3000.P30F0103, DBC3000.P30F0A03, false);
            dba.addParam(DBC3000.P30F0104, DBC3000.P30F0A04, false);
            dba.addParam(DBC3000.P30F0105, DBC3000.P30F0A05, false);
            dba.addParam(DBC3000.P30F0106, DBC3000.P30F0A06, false);
            dba.addParam(DBC3000.P30F0107, DBC3000.P30F0A07, false);
            dba.addParam(DBC3000.P30F0108, DBC3000.P30F0A08, false);
            dba.addParam(DBC3000.P30F0109, DBC3000.P30F0A09, false);
            dba.addParam(DBC3000.P30F010A, DBC3000.P30F0A0A, false);
            dba.addParam(DBC3000.P30F010B, DBC3000.P30F0A0B, false);
            dba.addParam(DBC3000.P30F010C, DBC3000.P30F0A0C, false);
            dba.addParam(DBC3000.P30F010D, DBC3000.P30F0A0D, false);
            dba.addParam(DBC3000.P30F010E, DBC3000.P30F0A0E, false);
            dba.addParam(DBC3000.P30F010F, DBC3000.P30F0A0F, false);
            dba.addWhere(DBC3000.P30F0A01, logsHash);
            dba.addCopyBatch(DBC3000.P30F0100, DBC3000.P30F0A00);
            dba.reInit();

            dba.addParam(DBC3000.P30F0201, DBC3000.P30F0B02, false);
            dba.addParam(DBC3000.P30F0202, DBC3000.P30F0B03, false);
            dba.addParam(DBC3000.P30F0203, DBC3000.P30F0B04, false);
            dba.addWhere(DBC3000.P30F0B01, logsHash);
            dba.addCopyBatch(DBC3000.P30F0200, DBC3000.P30F0B00);
            dba.reInit();

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
            dba.dispose();
        }
    }

    public static boolean deleteHistData(String keysHash, String logsHash)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            boolean b = com.magicpwd._util.Char.isValidateHash(logsHash);

            dba.addTable(DBC3000.P30F0A00);
            dba.addWhere(DBC3000.P30F0A04, keysHash);
            if (b)
            {
                dba.addWhere(DBC3000.P30F0A01, logsHash);
            }
            dba.addDeleteBatch();

            dba.reInit();
            dba.addTable(DBC3000.P30F0B00);
            dba.addWhere(DBC3000.P30F0B03, keysHash);
            if (b)
            {
                dba.addWhere(DBC3000.P30F0B01, logsHash);
            }
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
            dba.dispose();
        }
    }

    public static boolean selectHistData(String logsHash, Keys keys)
    {
        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F0A00);
            dba.addWhere(DBC3000.P30F0A01, logsHash);
            ResultSet rest = dba.executeSelect();
            if (!rest.next())
            {
                return false;
            }

            keys.setP30F0102(rest.getInt(DBC3000.P30F0A02));
            keys.setP30F0103(rest.getInt(DBC3000.P30F0A03));
            keys.setP30F0104(rest.getString(DBC3000.P30F0A04));
            keys.setP30F0106(rest.getString(DBC3000.P30F0A06));
            keys.setP30F0107(rest.getString(DBC3000.P30F0A07));
            keys.setP30F0108(rest.getString(DBC3000.P30F0A08));
            keys.setP30F0109(rest.getString(DBC3000.P30F0A09));
            keys.setP30F010A(rest.getString(DBC3000.P30F0A0A));
            keys.setP30F010B(rest.getString(DBC3000.P30F0A0B));
            keys.setP30F010C(rest.getString(DBC3000.P30F0A0C));
            keys.setP30F010D(rest.getString(DBC3000.P30F0A0D));
            keys.setP30F010E(rest.getString(DBC3000.P30F0A0E));
            keys.setP30F010F(rest.getString(DBC3000.P30F0A0F));

            dba.reInit();
            dba.addTable(DBC3000.P30F0B00);
            dba.addColumn(DBC3000.P30F0B04);
            dba.addWhere(DBC3000.P30F0B01, logsHash);
            dba.addSort(DBC3000.P30F0B02);
            rest = dba.executeSelect();
            StringBuffer sb = keys.getPassword().getP30F0203();
            while (rest.next())
            {
                sb.append(rest.getString(DBC3000.P30F0B04));
            }
            keys.getPassword().setP30F0202(keys.getP30F0104());

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
            dba.dispose();
        }
    }

    public static boolean selectHistData(String hash, List<S1S2> list)
    {
        if (!com.magicpwd._util.Char.isValidateHash(hash))
        {
            return false;
        }

        DBAccess dba = new DBAccess();

        try
        {
            dba.init();

            dba.addTable(DBC3000.P30F0A00);
            dba.addColumn(DBC3000.P30F0A01);
            dba.addWhere(DBC3000.P30F0A04, hash);
            dba.addSort(DBC3000.P30F0A01, false);

            S1S2 item;
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(ConsEnv.HINT_DATE);
            ResultSet rest = dba.executeSelect();
            while (rest.next())
            {
                item = new S1S2();
                item.setK(rest.getString(DBC3000.P30F0A01));
                item.setV(sdf.format(new Date(Long.parseLong(item.getK(), 16))));
                item.setV2(item.getV());

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
            dba.dispose();
        }
    }
}
