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
package com.magicpwd._user;

import com.magicpwd.__i.IBackCall;
import com.magicpwd.__i.IUserView;
import com.magicpwd._comp.ImgPanel;
import com.magicpwd._enum.AuthLog;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Jpng;
import com.magicpwd._util.Logs;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.tray.TrayPtn;

public class UserPtn extends javax.swing.JPanel
{

    /**
     * 登录模式
     */
    private AuthLog signType;
    /**
     * 登录错误次数
     */
    private int errCount;
    /**
     * 动态图像
     */
    private Jpng jpng;
    /**
     * 窗体对象引用
     */
    private javax.swing.JFrame frame;
    private javax.swing.JFrame parent;
    private javax.swing.JDialog dialog;
    /**
     * 成功回调函数
     */
    private IBackCall<UserDto> backCall;
    private TrayPtn trayPtn;
    private UserMdl userMdl;
    private IUserView userView;

    /**
     * 独立窗口
     * @param type
     */
    public UserPtn(UserMdl userMdl, TrayPtn trayPtn)
    {
        this.userMdl = userMdl;
        this.trayPtn = trayPtn;
        frame = new javax.swing.JFrame();
        frame.setResizable(false);
        frame.setIconImage(Bean.getLogo(16));
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 模式窗口
     * @param type
     * @param frame
     */
    public UserPtn(UserMdl userMdl, javax.swing.JFrame frame)
    {
        this.userMdl = userMdl;
        this.parent = frame;
        dialog = new javax.swing.JDialog(frame, true)
        {

            @Override
            protected void processWindowEvent(java.awt.event.WindowEvent e)
            {
                if (e.getID() == java.awt.event.WindowEvent.WINDOW_CLOSING && signType == AuthLog.signLs)
                {
                    return;
                }
                super.processWindowEvent(e);
            }
        };
        dialog.setResizable(false);
        dialog.setIconImage(Bean.getLogo(16));
        dialog.setDefaultCloseOperation(javax.swing.JDialog.DISPOSE_ON_CLOSE);
    }

    public boolean initView(AuthLog type)
    {
        if (type == signType)
        {
            return true;
        }

        this.signType = type;

        userView = new SignUp(this);
        userView.initView();

        plLogo = new ImgPanel();
        plLogo.setPreferredSize(new java.awt.Dimension(250, 50));
        plUser = new javax.swing.JPanel();
        plUser.add(userView.getPanel());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg.addComponent(plUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg.addComponent(plLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setHorizontalGroup(hpg);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addComponent(plLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(plUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setVerticalGroup(vsg);

        try
        {
            java.io.InputStream stream = this.getClass().getResourceAsStream("/res/icon/guid.png");
            plLogo.setImage(javax.imageio.ImageIO.read(stream));
            stream.close();
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
        }

        if (frame != null)
        {
            frame.getContentPane().add(this);
            frame.pack();
        }
        if (dialog != null)
        {
            dialog.getContentPane().add(this);
            dialog.pack();
        }

        return true;
    }

    public boolean initLang()
    {
        userView.initLang();
//        switch (signType)
//        {
//            case signIn:
//                Lang.setWText(lb_UserType, LangRes.P30FA312, "模式(@M)");
//
//                Lang.setWText(lb_UserName, LangRes.P30FA301, "用户(@U)");
//                Lang.setWText(lb_UserKey0, LangRes.P30FA302, "口令(@P)");
//
//                Lang.setWText(lb_UsrLabel, LangRes.P30FA30E, "口令找回");
//                Lang.setWTips(lb_UsrLabel, LangRes.P30FA30F, "找回您的登录口令");
//
//                Lang.setWText(lb_KeyLabel, LangRes.P30FA310, "屏幕键盘");
//                Lang.setWTips(lb_KeyLabel, LangRes.P30FA311, "使用屏幕键盘输入");
//
//                Lang.setWText(bt_Confrm, LangRes.P30FA501, "登录(@S)");
//
//                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(@C)");
//
//                setTitle(Lang.getLang(LangRes.P30FA201, "用户登录"));
//
//                lb_UsrLabel.setVisible(true);
//                break;
//            case signLs:
//                Lang.setWText(lb_UserKey0, LangRes.P30FA302, "口令(@P)");
//
//                Lang.setWText(bt_Confrm, LangRes.P30FA501, "登录(@S)");
//
//                setTitle(Lang.getLang(LangRes.P30FA202, "身份验证"));
//
//                lb_UsrLabel.setVisible(false);
//                break;
//            case signRs:
//                Lang.setWText(lb_UserName, LangRes.P30FA301, "用户(@U)");
//
//                Lang.setWText(lb_UserKey0, LangRes.P30FA302, "口令(@P)");
//
//                Lang.setWText(bt_Confrm, LangRes.P30FA501, "登录(@S)");
//
//                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(@C)");
//
//                setTitle(Lang.getLang(LangRes.P30FA202, "身份验证"));
//
//                lb_UsrLabel.setVisible(false);
//                break;
//            case signUp:
//                Lang.setWText(lb_UserName, LangRes.P30FA301, "用户(@U)");
//
//                Lang.setWText(lb_UserKey0, LangRes.P30FA302, "口令(@P)");
//
//                Lang.setWText(lb_UserKey1, LangRes.P30FA303, "确认(@R)");
//
//                Lang.setWText(lb_KeyLabel, LangRes.P30FA307, "数据升级");
//                Lang.setWTips(lb_KeyLabel, LangRes.P30FA308, "从旧版本软件升级数据");
//
//                Lang.setWText(bt_Confrm, LangRes.P30FA502, "注册(@S)");
//
//                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(@C)");
//
//                setTitle(Lang.getLang(LangRes.P30FA203, "用户注册"));
//
//                lb_UsrLabel.setVisible(false);
//                break;
//            case signPk:
//                Lang.setWText(lb_UserKey0, LangRes.P30FA304, "现有口令(@O)");
//
//                Lang.setWText(lb_UserKey1, LangRes.P30FA305, "修改口令(@N)");
//
//                Lang.setWText(lb_UserKey2, LangRes.P30FA306, "口令确认(@R)");
//
//                Lang.setWText(bt_Confrm, LangRes.P30FA503, "修改(@S)");
//
//                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(@C)");
//
//                setTitle(Lang.getLang(LangRes.P30FA205, "登录口令修改"));
//
//                lb_UsrLabel.setVisible(false);
//                break;
//            case signFp:
//                Lang.setWText(lb_UserName, LangRes.P30FA30C, "登录用户(@U)");
//
//                Lang.setWText(lb_UserKey0, LangRes.P30FA30D, "安全口令(@P)");
//
//                Lang.setWText(bt_Confrm, LangRes.P30FA509, "找回(@F)");
//
//                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(@C)");
//
//                setTitle(Lang.getLang(LangRes.P30FA204, "口令找回"));
//
//                lb_UsrLabel.setVisible(false);
//                break;
//            case signSk:
//                Lang.setWText(lb_UserKey0, LangRes.P30FA304, "现有口令(@O)");
//
//                Lang.setWText(lb_UserKey1, LangRes.P30FA30D, "安全口令(@P)");
//
//                Lang.setWText(lb_UserKey2, LangRes.P30FA306, "口令确认(@R)");
//
//                Lang.setWText(bt_Confrm, LangRes.P30FA508, "设定(@S)");
//
//                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(@C)");
//
//                setTitle(Lang.getLang(LangRes.P30FA206, "安全口令修改"));
//
//                lb_UsrLabel.setVisible(false);
//                break;
//            case signSu:
//                setTitle(Lang.getLang(LangRes.P30FA207, "添加从属用户"));
//
//                lb_UsrLabel.setVisible(false);
//                break;
//            case signCs:
//                Lang.setWText(lb_UserType, LangRes.P30FA313, "服务器(@M)");
//
//                Lang.setWText(lb_UserName, LangRes.P30FA301, "用户(@U)");
//
//                Lang.setWText(lb_UserKey0, LangRes.P30FA302, "口令(@P)");
//
//                Lang.setWText(bt_Confrm, LangRes.P30FA508, "设定(@S)");
//
//                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(@C)");
//
//                setTitle(Lang.getLang(LangRes.P30FA20B, "配置邮件账户"));
//
//                lb_UsrLabel.setVisible(false);
//                break;
//            default:
//                break;
//        }

//        lb_KeyLabel.setVisible(false);
//        pl_SafePane.setVisible(false);
        return true;
    }

    public boolean initData()
    {
        userView.initData();
//        this.errCount = 0;
//
//        switch (signType)
//        {
//            case signIn:
//                // 显示上次登录用户
//                String[] arr = userMdl.getMpwdMdl().getViewList().split(",");
//                String tmp;
//                for (int i = 0, j = arr.length; i < j; i += 1)
//                {
//                    tmp = arr[i].toLowerCase();
//                    if (AppView.mexp.name().equals(tmp))
//                    {
//                        cb_UserType.addItem(new S1S1(tmp, Lang.getLang(LangRes.P30FA107, "专业模式")));
//                        continue;
//                    }
//                    if (AppView.mwiz.name().equals(tmp))
//                    {
//                        cb_UserType.addItem(new S1S1(tmp, Lang.getLang(LangRes.P30FA108, "向导模式")));
//                        continue;
//                    }
//                    if (AppView.mpad.name().equals(tmp))
//                    {
//                        cb_UserType.addItem(new S1S1(tmp, Lang.getLang(LangRes.P30FA109, "记事模式")));
//                        continue;
//                    }
//                    if (AppView.maoc.name().equals(tmp))
//                    {
//                        cb_UserType.addItem(new S1S1(tmp, Lang.getLang(LangRes.P30FA10A, "数值运算")));
//                        continue;
//                    }
//                    if (AppView.mruc.name().equals(tmp))
//                    {
//                        cb_UserType.addItem(new S1S1(tmp, Lang.getLang(LangRes.P30FA10B, "单位换算")));
//                        continue;
//                    }
//                    if (AppView.mgtd.name().equals(tmp))
//                    {
//                        cb_UserType.addItem(new S1S1(tmp, Lang.getLang(LangRes.P30FA10C, "计划任务")));
//                        continue;
//                    }
//                }
//                cb_UserType.setSelectedItem(new S1S1(userMdl.getMpwdMdl().getViewLast(), ""));
//                String name = userMdl.getCfg(ConsCfg.CFG_USER_LAST, "");
//                if (com.magicpwd._util.Char.isValidate(name))
//                {
//                    tf_UserName.setText(name);
//                    pf_UserKey0.requestFocus();
//                }
//                else
//                {
//                    tf_UserName.requestFocus();
//                }
//                break;
//            case signRs:
//                tf_UserName.setText("");
//                pf_UserKey0.setText("");
//                tf_UserName.requestFocus();
//                break;
//            case signUp:
//                break;
//            case signPk:
//                break;
//            case signFp:
//                break;
//            case signSk:
//                break;
//            case signSu:
//                break;
//            case signCs:
//                java.util.Properties prop = Connect.getMailCfg();
//                cb_UserType.removeAllItems();
//                java.util.Enumeration<?> names = prop.propertyNames();
//                java.util.LinkedList<String> text = new java.util.LinkedList<String>();
//                while (names.hasMoreElements())
//                {
//                    String t = names.nextElement() + "";
//                    int i = t.lastIndexOf(".type");
//                    if (i <= 0 || i + 5 != t.length())
//                    {
//                        continue;
//                    }
//                    t = t.substring(0, i);
//
//                    // 排序
//                    i = 0;
//                    int j = text.size();
//                    while (i < j)
//                    {
//                        if (t.compareTo(text.get(i)) < 0)
//                        {
//                            break;
//                        }
//                        i += 1;
//                    }
//                    text.add(i, t);
//                    cb_UserType.insertItemAt(t, i);
//                }
//
//                break;
//            default:
//                break;
//        }
//
//        if (jpng == null)
//        {
//            new Thread()
//            {
//
//                @Override
//                public void run()
//                {
//                    loadRes();
//                }
//            }.start();
//        }

        return true;
    }

    public void pack()
    {
        if (frame != null)
        {
            frame.pack();
            Bean.centerForm(frame, null);
        }
        if (dialog != null)
        {
            dialog.pack();
            Bean.centerForm(dialog, parent);
        }
    }

    public void toFront()
    {
        if (frame != null)
        {
            frame.toFront();
        }
        if (dialog != null)
        {
            dialog.toFront();
        }
    }

//    private void loadRes()
//    {
//        jpng = new Jpng();
//        try
//        {
//            java.io.InputStream stream = Jpng.class.getResourceAsStream(ConsEnv.ICON_PATH + "wait.png");
//            jpng.readIcons(stream, 16, 16);
//            stream.close();
//            jpng.setIt(0);
//            jpng.setButton(bt_Confrm);
//        }
//        catch (Exception exp)
//        {
//            Logs.exception(exp);
//        }
//
//        if (MpwdMdl.getRunMode() == RunMode.dev)
//        {
//            return;
//        }
//
//        javax.swing.Icon icon = null;
//        try
//        {
//            StringBuilder buf = new StringBuilder();
//            buf.append(ConsEnv.HOMEPAGE);
//            buf.append("mpwd/mpwd0001.ashx?sid=").append(ConsEnv.VERSIONS);
//            buf.append("&uri=").append(java.net.InetAddress.getLocalHost().getHostAddress());
//            buf.append("&opt=").append(Char.escape(System.getProperty("os.name")));
//            buf.append("_").append(Char.escape(System.getProperty("os.arch")));
//            buf.append("_").append(Char.escape(System.getProperty("os.version")));
//            java.net.URL url = new java.net.URL(buf.toString());
//            java.io.InputStream stream = url.openStream();
//            icon = new javax.swing.ImageIcon(javax.imageio.ImageIO.read(stream));
//            stream.close();
//        }
//        catch (Exception ex)
//        {
//            Logs.exception(ex);
//            icon = null;
//        }
//        if (icon != null)
//        {
//            guidIcon = icon;
//            if (lb_GuidIcon != null)
//            {
//                final String tgi = "";
//                synchronized (tgi)
//                {
//                    lb_GuidIcon.setIcon(guidIcon);
//                }
//            }
//        }
//    }
    private void setTitle(String title)
    {
        if (frame != null)
        {
            frame.setTitle(title);
        }
        if (dialog != null)
        {
            dialog.setTitle(title);
        }
    }

    @Override
    public void setVisible(boolean visible)
    {
        if (frame != null)
        {
            frame.setVisible(visible);
        }
        if (dialog != null)
        {
            dialog.setVisible(visible);
        }
    }

    private synchronized void dispoze()
    {
        if (frame != null)
        {
            frame.setVisible(false);
//            frame.dispose();
        }
        if (dialog != null)
        {
            dialog.setVisible(false);
        }
    }

    public void setBackCall(IBackCall<UserDto> backCall)
    {
        this.backCall = backCall;
    }
//    /**
//     * @param evt
//     */
//    private void bt_CancelActionPerformed(java.awt.event.ActionEvent evt)
//    {
//        switch (signType)
//        {
//            case signIn:
//            case signUp:
//                System.exit(0);
//                return;
//            case signRs:
//            case signPk:
//            case signSk:
//            case signSu:
//            case signNw:
//            case signCs:
//                dispoze();
//                break;
//            case signFp:
//                initView(AuthLog.signIn);
//                initLang();
//                initData();
//                tf_UserName.requestFocus();
//                break;
//            default:
//                break;
//        }
//        if (backCall != null)
//        {
//            backCall.callBack(IBackCall.OPTIONS_ABORT, null);
//        }
//    }
//    private void doSign()
//    {
//        bt_Confrm.setEnabled(false);
//        jpng.start();
//        lb_UsrLabel.setEnabled(false);
//        new Thread()
//        {
//
//            @Override
//            public void run()
//            {
//                switch (signType)
//                {
//                    case signIn:
//                        signIn();
//                        break;
//                    case signLs:
//                        signLs();
//                        break;
//                    case signRs:
//                        signRs();
//                        break;
//                    case signUp:
//                        signUp();
//                        break;
//                    case signPk:
//                        signPk();
//                        break;
//                    case signFp:
//                        signFp();
//                        break;
//                    case signSk:
//                        signSk();
//                        break;
//                    case signSu:
//                        signSu();
//                        break;
//                    case signCs:
//                        signCs();
//                        break;
//                    default:
//                        break;
//                }
//                jpng.stop();
//                bt_Confrm.setEnabled(true);
//                lb_UsrLabel.setEnabled(true);
//            }
//        }.start();
//    }
//    /**
//     * @param evt
//     */
//    private void bt_ConfrmActionPerformed(java.awt.event.ActionEvent evt)
//    {
//        doSign();
//    }
//
//    private void cb_UserTypeActionPerformed(java.awt.event.ActionEvent evt)
//    {
//        doSign();
//    }
//
//    private void tf_UserNameActionPerformed(java.awt.event.ActionEvent evt)
//    {
//        doSign();
//    }
//
//    private void pf_UserKey0ActionPerformed(java.awt.event.ActionEvent evt)
//    {
//        doSign();
//    }
//
//    private void pf_UserKey1ActionPerformed(java.awt.event.ActionEvent evt)
//    {
//        doSign();
//    }
//
//    private void pf_UserKey2ActionPerformed(java.awt.event.ActionEvent evt)
//    {
//        doSign();
//    }
//
//    private void lb_UsrLabelMouseReleased(java.awt.event.MouseEvent evt)
//    {
//        if (!lb_UsrLabel.isEnabled())
//        {
//            return;
//        }
//
//        initView(AuthLog.signFp);
//        initLang();
//        initData();
//
//        tf_UserName.requestFocus();
//    }
//
//    private void lb_KeyLabelMouseReleased(java.awt.event.MouseEvent evt)
//    {
//        if (!lb_KeyLabel.isEnabled())
//        {
//            return;
//        }
//
//        if (signType == AuthLog.signIn)
//        {
//            signType = AuthLog.signFp;
//            return;
//        }
//
//        DBU3000 du = new DBU3000();
//        du.initView();
//        du.initLang();
//        du.initData();
//
//        dispoze();
//    }
    private ImgPanel plLogo;
    private javax.swing.JPanel plUser;
}
