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
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._enum.AuthLog;
import com.magicpwd._enum.RunMode;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.Jpng;
import com.magicpwd._util.Logs;
import com.magicpwd.m.MpwdMdl;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.tray.TrayPtn;

public class UserPtn extends javax.swing.JPanel
{

    /**
     * 登录模式
     */
    private AuthLog signType;
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

        switch (signType)
        {
            case signIn:
                userView = new SignIn(this);
                break;
            case signLs:
                userView = new SignLs(this);
                break;
            case signRs:
                userView = new SignRs(this);
                break;
            case signUp:
                userView = new SignUp(this);
                break;
            case signPk:
                userView = new SignPk(this);
                break;
            case signFp:
                userView = new SignFp(this);
                break;
            case signSk:
                userView = new SignSk(this);
                break;
            case signSu:
                userView = new SignSu(this);
                break;
            case signCs:
                userView = new SignCs(this);
                break;
            default:
                return false;
        }
        userView.initView();

        initLogoView();
        initInputView();
        initActionView();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg.addComponent(plLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg.addComponent(plInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg.addComponent(plAction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setHorizontalGroup(hpg);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addComponent(plLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
//        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(plInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
//        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(plAction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setVerticalGroup(vsg);

        if (frame != null)
        {
            frame.getContentPane().add(this);
        }
        if (dialog != null)
        {
            dialog.getContentPane().add(this);
        }
        pack();

        return true;
    }

    private void initLogoView()
    {
        plLogo = new javax.swing.JPanel();
        plLogo.setLayout(new java.awt.BorderLayout());
        lbLogo = new javax.swing.JLabel();
        plLogo.add(lbLogo);
        try
        {
            java.io.InputStream stream = this.getClass().getResourceAsStream("/res/icon/guid.png");
            lbLogo.setIcon(new javax.swing.ImageIcon(javax.imageio.ImageIO.read(stream)));
            stream.close();
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
        }
    }

    private void initInputView()
    {
        plInput = new javax.swing.JPanel();
        plInput.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        plInput.add(userView.getPanel());
    }

    private void initActionView()
    {
        plAction = new javax.swing.JPanel();
        btAbort = new javax.swing.JButton();
        btAbort.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                userView.btAbortActionPerformed(e);
            }
        });
        btApply = new javax.swing.JButton();
        btApply.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                userView.btApplyActionPerformed(e);
            }
        });
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(plAction);
        plAction.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addComponent(jLabel1);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(jLabel2);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE);
        hsg.addComponent(btApply);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(btAbort);
        hsg.addContainerGap();
        layout.setHorizontalGroup(hsg);
        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg.addComponent(btAbort);
        vpg.addComponent(btApply);
        vpg.addComponent(jLabel1);
        vpg.addComponent(jLabel2);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
//        vsg.addContainerGap();
        vsg.addGroup(vpg);
        vsg.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setVerticalGroup(vsg);
    }

    public boolean initLang()
    {
        userView.initLang();
        pack();
        return true;
    }

    public boolean initData()
    {
        userView.initData();
        if (jpng == null)
        {
            new Thread()
            {

                @Override
                public void run()
                {
                    loadRes();
                }
            }.start();
        }

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

    public void setTitle(String title)
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

    private void loadRes()
    {
        jpng = new Jpng();
        try
        {
            java.io.InputStream stream = Jpng.class.getResourceAsStream(ConsEnv.ICON_PATH + "wait.png");
            jpng.readIcons(stream, 16, 16);
            stream.close();
            jpng.setIt(0);
            jpng.setButton(btApply);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }

        if (MpwdMdl.getRunMode() == RunMode.dev)
        {
            return;
        }

        javax.swing.Icon icon = null;
        try
        {
            StringBuilder buf = new StringBuilder();
            buf.append(ConsEnv.HOMEPAGE);
            buf.append("mpwd/mpwd0001.ashx?sid=").append(ConsEnv.VERSIONS);
            buf.append("&uri=").append(java.net.InetAddress.getLocalHost().getHostAddress());
            buf.append("&opt=").append(Char.escape(System.getProperty("os.name")));
            buf.append("_").append(Char.escape(System.getProperty("os.arch")));
            buf.append("_").append(Char.escape(System.getProperty("os.version")));
            java.net.URL url = new java.net.URL(buf.toString());
            java.io.InputStream stream = url.openStream();
            icon = new javax.swing.ImageIcon(javax.imageio.ImageIO.read(stream));
            stream.close();
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
            icon = null;
        }
        if (icon != null)
        {
            if (lbLogo != null)
            {
                final String tgi = "";
                synchronized (tgi)
                {
                    lbLogo.setIcon(icon);
                }
            }
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

    /**
     * @return the backCall
     */
    IBackCall<UserDto> getBackCall()
    {
        return backCall;
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

    /**
     * @return the btAbort
     */
    public javax.swing.JButton getAbortButton()
    {
        return btAbort;
    }

    /**
     * @return the btApply
     */
    public javax.swing.JButton getApplyButton()
    {
        return btApply;
    }

    /**
     * @return the userMdl
     */
    UserMdl getUserMdl()
    {
        return userMdl;
    }
    private javax.swing.JPanel plLogo;
    private javax.swing.JPanel plInput;
    private javax.swing.JPanel plAction;
    private javax.swing.JButton btAbort;
    private javax.swing.JButton btApply;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
}
