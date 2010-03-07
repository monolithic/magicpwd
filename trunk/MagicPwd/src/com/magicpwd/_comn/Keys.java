/**
 * 
 */
package com.magicpwd._comn;

import java.io.Serializable;
import java.sql.Timestamp;

import com.magicpwd._cons.ConsDat;
import javax.crypto.Cipher;

/**
 * 口令信息
 * @author Amon
 */
public final class Keys implements Serializable
{

    private boolean histBack;
    /**
     * 显示排序
     */
    private int P30F0101;
    /**
     * 使用状态
     */
    private int P30F0102;
    /**
     * 紧要程度
     */
    private int P30F0103;
    /**
     * 口令索引
     */
    private String P30F0104;
    /**
     * 用户索引
     */
    private String P30F0105;
    /**
     * 所属类别
     */
    private String P30F0106;
    /**
     * 注册日期
     */
    private Timestamp P30F0107;
    /**
     * 模板索引
     */
    private String P30F0108;
    /**
     * 口令标题
     */
    private String P30F0109;
    /**
     * 关键搜索
     */
    private String P30F010A;
    /**
     * 口令图标
     */
    private String P30F010B;
    /**
     * 图标说明
     */
    private String P30F010C;
    /**
     * 到期日期
     */
    private Timestamp P30F010D;
    /**
     * 到期提示
     */
    private String P30F010E;
    /**
     * 相关说明
     */
    private String P30F010F;
    /**
     * 加密口令
     */
    private static PwdsItem password;

    public Keys()
    {
        password = new PwdsItem();
        setDefault();
    }

    /**
     * 恢复默认值
     */
    public void setDefault()
    {
        histBack = true;

        P30F0101 = 0;
        P30F0102 = ConsDat.PWDS_MODE_0;
        setP30F0104(null);
        setP30F0105(null);
        setP30F0106(null);
        setP30F0107(null);
        setP30F0109(null);
        setP30F010A(null);
        setP30F010B(null);
        setP30F010D(null);
        setP30F010E(null);
        password.setDefault();
    }

    /**
     * 加密数据读取
     * @return
     */
    public boolean loadPwds()
    {
        return true;
    }

    /**
     * 数据加密存储
     * @return
     */
    public boolean savePwds()
    {
        return true;
    }

    /**
     * @return the histBack
     */
    public boolean isHistBack()
    {
        return histBack;
    }

    /**
     * @param histBack
     *            the histBack to set
     */
    public void setHistBack(boolean histBack)
    {
        this.histBack = histBack;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return getP30F0109();
    }

    /**
     * 口令数据
     * @return the Password
     */
    public PwdsItem getPassword()
    {
        return password;
    }

    /**
     * 口令数据
     * @param Password the Password to set
     */
    public void setPassword(PwdsItem Password)
    {
        Keys.password = Password;
    }

    /**
     * 显示排序
     * @return the P30F0101
     */
    public int getP30F0101()
    {
        return P30F0101;
    }

    /**
     * 显示排序
     * @param P30F0101
     */
    public void setP30F0101(int P30F0101)
    {
        this.P30F0101 = P30F0101;
    }

    /**
     * 使用状态
     * @return the P30F0102
     */
    public int getP30F0102()
    {
        return P30F0102;
    }

    /**
     * 使用状态
     * @param P30F0102 the P30F0102 to set
     */
    public void setP30F0102(int P30F0102)
    {
        this.P30F0102 = P30F0102;
    }

    /**
     * 紧要程度
     * @return the P30F0103
     */
    public int getP30F0103()
    {
        return P30F0103;
    }

    /**
     * 紧要程度
     * @param P30F0103 the P30F0103 to set
     */
    public void setP30F0103(int P30F0103)
    {
        this.P30F0103 = P30F0103;
    }

    /**
     * 口令索引
     * @return the P30F0104
     */
    public String getP30F0104()
    {
        return P30F0104;
    }

    /**
     * 口令索引
     * @param P30F0104 the P30F0104 to set
     */
    public void setP30F0104(String P30F0104)
    {
        this.P30F0104 = P30F0104;
    }

    /**
     * 用户索引
     * @return the P30F0105
     */
    public String getP30F0105()
    {
        return P30F0105;
    }

    /**
     * 用户索引
     * @param P30F0105 the P30F0105 to set
     */
    public void setP30F0105(String P30F0105)
    {
        this.P30F0105 = P30F0105;
    }

    /**
     * 所属类别
     * @return the P30F0106
     */
    public String getP30F0106()
    {
        return P30F0106;
    }

    /**
     * 所属类别
     * @param P30F0106 the P30F0106 to set
     */
    public void setP30F0106(String P30F0106)
    {
        this.P30F0106 = P30F0106;
    }

    /**
     * 注册日期
     * @return the P30F0107
     */
    public Timestamp getP30F0107()
    {
        return P30F0107;
    }

    /**
     * 注册日期
     * @param P30F0107 the P30F0107 to set
     */
    public void setP30F0107(Timestamp P30F0107)
    {
        this.P30F0107 = P30F0107;
    }

    /**
     * 模板索引
     * @return the P30F0108
     */
    public String getP30F0108()
    {
        return P30F0108;
    }

    /**
     * 模板索引
     * @param P30F0108 the P30F0108 to set
     */
    public void setP30F0108(String P30F0108)
    {
        this.P30F0108 = P30F0108;
    }

    /**
     * 口令标题
     * @return the P30F0109
     */
    public String getP30F0109()
    {
        return P30F0109;
    }

    /**
     * 口令标题
     * @param P30F0109 the P30F0109 to set
     */
    public void setP30F0109(String P30F0109)
    {
        this.P30F0109 = P30F0109;
    }

    /**
     * 关键搜索
     * @return the P30F010A
     */
    public String getP30F010A()
    {
        return P30F010A;
    }

    /**
     * 关键搜索
     * @param P30F010A the P30F010A to set
     */
    public void setP30F010A(String P30F010A)
    {
        this.P30F010A = P30F010A;
    }

    /**
     * 口令图标
     * @return the P30F010B
     */
    public String getP30F010B()
    {
        return P30F010B;
    }

    /**
     * 口令图标
     * @param P30F010B the P30F010B to set
     */
    public void setP30F010B(String P30F010B)
    {
        this.P30F010B = P30F010B;
    }

    /**
     * 图标说明
     * @return the P30F010C
     */
    public String getP30F010C()
    {
        return P30F010C;
    }

    /**
     * 图标说明
     * @param P30F010C the P30F010C to set
     */
    public void setP30F010C(String P30F010C)
    {
        this.P30F010C = P30F010C;
    }

    /**
     * 到期日期
     * @return the P30F010D
     */
    public Timestamp getP30F010D()
    {
        return P30F010D;
    }

    /**
     * 到期日期
     * @param P30F010D the P30F010D to set
     */
    public void setP30F010D(Timestamp P30F010D)
    {
        this.P30F010D = P30F010D;
    }

    /**
     * 到期提示
     * @return the P30F010E
     */
    public String getP30F010E()
    {
        return P30F010E;
    }

    /**
     * 到期提示
     * @param P30F010E the P30F010E to set
     */
    public void setP30F010E(String P30F010E)
    {
        this.P30F010E = P30F010E;
    }

    /**
     * 相关说明
     * @return the P30F010F
     */
    public String getP30F010F()
    {
        return P30F010F;
    }

    /**
     * 相关说明
     * @param P30F010F the P30F010F to set
     */
    public void setP30F010F(String P30F010F)
    {
        this.P30F010F = P30F010F;
    }

    /**
     * 对文件进行加密或解密处理
     * @param c 密码算法
     * @param s 来源文件
     * @param d 写入文件
     * @throws Exception
     */
    public static final void doCrypt(Cipher c, java.io.File s, java.io.File d) throws Exception
    {
        byte[] buf = new byte[1024];
        java.io.FileInputStream fis = new java.io.FileInputStream(s);
        java.io.FileOutputStream fos = new java.io.FileOutputStream(d);
        int len = fis.read(buf);
        while (len >= 0)
        {
            fos.write(c.update(buf, 0, len));
            len = fis.read(buf);
        }
        fos.write(c.doFinal());
        fos.flush();
        fos.close();
        fis.close();
    }
}
