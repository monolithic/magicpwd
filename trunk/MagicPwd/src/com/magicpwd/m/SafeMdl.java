/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.m;

import com.magicpwd.__i.IEditItem;
import com.magicpwd._comn.Keys;
import com.magicpwd._comn.Pwds;
import com.magicpwd._comn.item.EditItem;
import com.magicpwd._comn.item.GuidItem;
import com.magicpwd._comn.item.HintItem;
import com.magicpwd._comn.item.LogoItem;
import com.magicpwd._comn.item.MetaItem;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.d.DBA3000;

/**
 *
 * @author Amon
 */
public abstract class SafeMdl
{

    protected Keys keys;
    protected UserMdl userMdl;
    protected java.util.ArrayList<IEditItem> ls_ItemList;
    /**
     * 临时数据保存
     */
    private boolean interim;
    private boolean modified;
    private javax.crypto.Cipher dCipher;
    private javax.crypto.Cipher eCipher;

    public SafeMdl(UserMdl userMdl)
    {
        this.userMdl = userMdl;
        keys = new Keys();
        ls_ItemList = new java.util.ArrayList<IEditItem>();
    }

    public abstract void initHead();

    public abstract void initBody();

    public abstract void clear();

    /**
     * @return the dCipher
     */
    public javax.crypto.Cipher getDCipher()
    {
        if (dCipher == null)
        {
            try
            {
                dCipher = javax.crypto.Cipher.getInstance(ConsEnv.NAME_CIPHER);
                dCipher.init(javax.crypto.Cipher.DECRYPT_MODE, userMdl.safeKey);
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
                Lang.showMesg(null, LangRes.P30FAA04, "系统错误：系统无法加载密码算法！");
                System.exit(0);
            }
        }
        return dCipher;
    }

    /**
     * @return the eCipher
     */
    public javax.crypto.Cipher getECipher()
    {
        if (eCipher == null)
        {
            try
            {
                eCipher = javax.crypto.Cipher.getInstance(ConsEnv.NAME_CIPHER);
                eCipher.init(javax.crypto.Cipher.ENCRYPT_MODE, userMdl.safeKey);
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
                Lang.showMesg(null, LangRes.P30FAA04, "系统错误：系统无法加载密码算法！");
                System.exit(0);
            }
        }
        return eCipher;
    }

    /**
     * 解密处理
     * @param text
     * @throws Exception
     */
    public String deCrypt(String text) throws Exception
    {
        return new String(getDCipher().doFinal(Util.stringToBytes(text, userMdl.safeKey.getMask())), ConsEnv.FILE_ENCODING);
    }

    /**
     * 对文件进行加密或解密处理
     * @param c 密码算法
     * @param s 来源文件
     * @param d 写入文件
     * @throws Exception
     */
    public final void deCrypt(java.io.File s, java.io.File d) throws Exception
    {
        byte[] buf = new byte[1024];
        java.io.FileInputStream fis = new java.io.FileInputStream(s);
        java.io.FileOutputStream fos = new java.io.FileOutputStream(d);
        int len = fis.read(buf);
        while (len >= 0)
        {
            fos.write(getDCipher().update(buf, 0, len));
            len = fis.read(buf);
        }
        fos.write(getDCipher().doFinal());
        fos.flush();
        fos.close();
        fis.close();
    }

    /**
     * 加密处理
     * @param text
     * @throws Exception
     */
    public String enCrypt(String text) throws Exception
    {
        return Util.bytesToString(getECipher().doFinal(text.getBytes(ConsEnv.FILE_ENCODING)), userMdl.safeKey.getMask());
    }

    /**
     * 对文件进行加密或解密处理
     * @param c 密码算法
     * @param s 来源文件
     * @param d 写入文件
     * @throws Exception
     */
    public final void enCrypt(java.io.File s, java.io.File d) throws Exception
    {
        byte[] buf = new byte[1024];
        java.io.FileInputStream fis = new java.io.FileInputStream(s);
        java.io.FileOutputStream fos = new java.io.FileOutputStream(d);
        int len = fis.read(buf);
        while (len >= 0)
        {
            fos.write(getECipher().update(buf, 0, len));
            len = fis.read(buf);
        }
        fos.write(getECipher().doFinal());
        fos.flush();
        fos.close();
        fis.close();
    }

    /**
     * 是否使用临时口令名称
     *
     * @return
     */
    public boolean isInterim()
    {
        return interim;
    }

    /**
     * @param interim
     */
    public void setInterim(boolean interim)
    {
        this.interim = interim;
    }

    /**
     * 数据是否被修改过
     *
     * @return
     */
    public boolean isModified()
    {
        return modified;
    }

    /**
     * @param modified
     */
    public void setModified(boolean modified)
    {
        this.modified = modified;
    }

    /**
     * 读取指定索引的密码数据
     *
     * @param keysHash
     */
    public void loadData(String keysHash) throws Exception
    {
        clear();
        keys.setP30F0104(keysHash);
        keys.setP30F0105(userMdl.getCode());
        if (DBA3000.readPwdsData(keys))
        {
            deCrypt(keys, ls_ItemList);
        }
    }

    /**
     * 是否要更新原有数据
     *
     * @param histBack
     * @throws Exception
     */
    public void saveData(boolean histBack) throws Exception
    {
        keys.setP30F0105(userMdl.getCode());
        keys.setHistBack(histBack);
        enCrypt(keys, ls_ItemList);
        DBA3000.savePwdsData(keys);
        clear();
    }

    public int getItemSize()
    {
        return ls_ItemList.size();
    }

    public String getKeysHash()
    {
        return keys.getP30F0104();
    }

    /**
     * 向导初始化
     * @return
     */
    public IEditItem initGuid()
    {
        GuidItem guid = new GuidItem(userMdl);
        guid.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
        ls_ItemList.add(guid);
        return guid;
    }

    /**
     * 关键搜索
     * @return
     */
    public IEditItem initMeta()
    {
        MetaItem meta = new MetaItem(userMdl);
        ls_ItemList.add(meta);
        return meta;
    }

    /**
     * 徽标
     * @return
     */
    public IEditItem initLogo()
    {
        LogoItem logo = new LogoItem(userMdl);
        ls_ItemList.add(logo);
        return logo;
    }

    /**
     * 过时提醒
     * @return
     */
    public IEditItem initHint()
    {
        HintItem hint = new HintItem(userMdl);
        ls_ItemList.add(hint);
        return hint;
    }

    public IEditItem getItemAt(int index)
    {
        return ls_ItemList.get(index);
    }

    public void setKeysLabel(int label)
    {
        keys.setP30F0102(label);
        DBA3000.saveKeysData(keys);
    }

    public void setKeysMajor(int major)
    {
        keys.setP30F0103(major);
        DBA3000.saveKeysData(keys);
    }

    private StringBuffer deCrypt(Pwds pwds) throws Exception
    {
//        pwds.deCript(userMdl.getDCipher(), userMdl.getSec().getMask());
//        return pwds.getP30F0203();
        StringBuffer buf = pwds.getP30F0203();
        String tmp = buf.toString();
        return buf.delete(0, buf.length()).append(deCrypt(tmp));
    }

    /**
     * 数据解密处理
     *
     * @param dba
     */
    public final void deCrypt(Keys keys, java.util.List<IEditItem> list) throws Exception
    {
        // 查询数据是否为空
        StringBuffer text = deCrypt(keys.getPassword());
        if (text.length() < 1)
        {
            return;
        }

        // Guid
        GuidItem guid = new GuidItem(userMdl);
        guid.setData(keys.getP30F0106());
        guid.setTime(keys.getP30F0107());
        guid.setSpec(IEditItem.SPEC_GUID_TPLT, keys.getP30F0108());
        list.add(guid);

        // MetaItem
        MetaItem meta = new MetaItem(userMdl);
        meta.setName(keys.getP30F0109());
        meta.setData(keys.getP30F010A());
        list.add(meta);

        // LogoItem
        LogoItem logo = new LogoItem(userMdl);
        logo.setName(keys.getP30F010B());
        logo.setData(keys.getP30F010C());
        list.add(logo);

        // HintItem
        HintItem hint = new HintItem(userMdl);
        hint.setTime(keys.getP30F010D());
        hint.setName(keys.getP30F010E());
        list.add(hint);

        // 处理每一个数据
        java.util.StringTokenizer st = new java.util.StringTokenizer(text.toString(), ConsDat.SP_SQL_EE);
        String name;
        String data;
        String spec;
        int dn;
        int dd;
        int ds;
        int type;
        String t;
        EditItem item;
        while (st.hasMoreTokens())
        {
            t = st.nextToken() + ConsDat.SP_SQL_KV;
            dn = t.indexOf(ConsDat.SP_SQL_KV);
            dd = t.indexOf(ConsDat.SP_SQL_KV, dn + 1);
            ds = t.indexOf(ConsDat.SP_SQL_KV, dd + 1);

            type = Integer.parseInt(t.substring(0, dn));
            name = t.substring(dn + 1, dd);
            data = t.substring(dd + 1, ds);
            spec = t.substring(ds + 1, t.length());
            item = new EditItem(userMdl, type, name, data);
            if (spec.length() > 0)
            {
                item.deCodeSpec(spec, ConsDat.SP_SQL_KV);
            }
            list.add(item);
        }
    }

    private StringBuffer enCrypt(Pwds pwds) throws Exception
    {
//        pwds.enCrypt(userMdl.getECipher(), userMdl.getSec().getMask());
//        return pwds.getP30F0203();
        StringBuffer buf = pwds.getP30F0203();
        String tmp = buf.toString();
        return buf.delete(0, buf.length()).append(enCrypt(tmp));
    }

    /**
     * 数据加密处理
     *
     * @param dba
     */
    public final void enCrypt(Keys keys, java.util.List<IEditItem> list) throws Exception
    {
        Pwds pwds = keys.getPassword();
        StringBuffer text = pwds.getP30F0203();
        text.delete(0, text.length());

        // Guid
        GuidItem guid = (GuidItem) list.get(ConsEnv.PWDS_HEAD_GUID);
        keys.setP30F0106(guid.getData());
        keys.setP30F0107(guid.getTime());
        keys.setP30F0108(guid.getSpec(IEditItem.SPEC_GUID_TPLT));

        // MetaItem
        MetaItem meta = (MetaItem) list.get(ConsEnv.PWDS_HEAD_META);
        keys.setP30F0109(interim ? '<' + meta.getName() + '_' + keys.getP30F0107() + '>' : meta.getName());
        keys.setP30F010A(meta.getData());
        interim = false;

        // LogoItem
        LogoItem logo = (LogoItem) list.get(ConsEnv.PWDS_HEAD_LOGO);
        keys.setP30F010B(logo.getName());
        keys.setP30F010C(logo.getData());

        // HintItem
        HintItem note = (HintItem) list.get(ConsEnv.PWDS_HEAD_HINT);
        keys.setP30F010D(note.getTime());
        keys.setP30F010E(note.getName());

        // 字符串拼接
        IEditItem item;
        for (int i = ConsEnv.PWDS_HEAD_SIZE, j = list.size(); i < j; i += 1)
        {
            item = list.get(i);
            text.append(item.getType());
            text.append(ConsDat.SP_SQL_KV);
            text.append(item.getName());
            text.append(ConsDat.SP_SQL_KV);
            text.append(item.getData());
            text.append(item.enCodeSpec(ConsDat.SP_SQL_KV));
            text.append(ConsDat.SP_SQL_EE);
        }

        enCrypt(pwds);
    }
}
