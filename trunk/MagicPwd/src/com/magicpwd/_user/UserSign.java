package com.magicpwd._user;

import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.m.UserMdl;
import com.magicpwd.u.DBU3000;

public class UserSign extends javax.swing.JPanel
{

    /**
     * 登录模式
     */
    private int signType;
    /**
     * 登录错误次数
     */
    private int errCount;
    /**
     * 
     */
    private javax.swing.JFrame frame;
    private javax.swing.JDialog dialog;
    /**
     * 成功回调函数
     */
    private com.magicpwd._face.IBackCall confrm;
    /**
     * 取消回调函数
     */
    private com.magicpwd._face.IBackCall cancel;

    /**
     * 独立窗口
     * @param type
     */
    public UserSign()
    {
        frame = new javax.swing.JFrame();
        frame.setResizable(false);
        frame.setIconImage(Util.getLogo());
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
    }

    /**
     * 模式窗口
     * @param type
     * @param frame
     */
    public UserSign(javax.swing.JFrame frame)
    {
        dialog = new javax.swing.JDialog(frame, true);
        dialog.setResizable(false);
        dialog.setIconImage(Util.getLogo());
        dialog.setDefaultCloseOperation(javax.swing.JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().add(this);
    }

    public boolean initView(int type)
    {
        this.signType = type;
        this.errCount = 0;

        initGuidView();
        initInfoView();
        initSafeView();

        if (lb_UsrLabel == null)
        {
            lb_UsrLabel = new javax.swing.JLabel();
            lb_UsrLabel.addMouseListener(new java.awt.event.MouseAdapter()
            {

                @Override
                public void mouseReleased(java.awt.event.MouseEvent evt)
                {
                    lb_UsrLabelMouseReleased(evt);
                }
            });
        }
        if (lb_KeyLabel == null)
        {
            lb_KeyLabel = new javax.swing.JLabel();
            lb_KeyLabel.addMouseListener(new java.awt.event.MouseAdapter()
            {

                @Override
                public void mouseReleased(java.awt.event.MouseEvent evt)
                {
                    lb_KeyLabelMouseReleased(evt);
                }
            });
        }

        if (bt_Cancel == null)
        {
            bt_Cancel = new javax.swing.JButton();
            bt_Cancel.addActionListener(new java.awt.event.ActionListener()
            {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    bt_CancelActionPerformed(evt);
                }
            });
        }
        if (bt_Confrm == null)
        {
            bt_Confrm = new javax.swing.JButton();
            bt_Confrm.addActionListener(new java.awt.event.ActionListener()
            {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    bt_ConfrmActionPerformed(evt);
                }
            });
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addComponent(lb_UsrLabel);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(lb_KeyLabel);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE);
        hsg1.addComponent(bt_Confrm);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(bt_Cancel);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(pl_GuidPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg1.addComponent(pl_InfoPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg1.addComponent(pl_SafePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg1.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hsg1);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addGroup(hpg1);
        hsg.addContainerGap();
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(bt_Cancel);
        vpg1.addComponent(bt_Confrm);
        vpg1.addComponent(lb_KeyLabel);
        vpg1.addComponent(lb_UsrLabel);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        vsg.addComponent(pl_GuidPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(pl_InfoPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(pl_SafePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        vsg.addGroup(vpg1);
        vsg.addContainerGap();
        layout.setVerticalGroup(vsg);

        return true;
    }

    private void initGuidView()
    {
        if (pl_GuidPane != null)
        {
            return;
        }
        pl_GuidPane = new javax.swing.JPanel();
    }

    private void initInfoView()
    {
        if (pl_InfoPane == null)
        {
            pl_InfoPane = new javax.swing.JPanel();
            pl_InfoPane.setLayout(new java.awt.FlowLayout());
        }

        tf_UserName = new javax.swing.JTextField(20);
        tf_UserName.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                tf_UserNameActionPerformed(evt);
            }
        });
        tf_UserName.addFocusListener(new java.awt.event.FocusAdapter()
        {

            @Override
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                tf_UserName.selectAll();
            }
        });

        lb_UserName = new javax.swing.JLabel();
        lb_UserName.setLabelFor(tf_UserName);

        pf_UserKey0 = new javax.swing.JPasswordField(20);
        pf_UserKey0.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pf_UserKey0ActionPerformed(evt);
            }
        });
        pf_UserKey0.addFocusListener(new java.awt.event.FocusAdapter()
        {

            @Override
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                pf_UserKey0.selectAll();
            }
        });

        lb_UserKey0 = new javax.swing.JLabel();
        lb_UserKey0.setLabelFor(pf_UserKey0);

        pf_UserKey1 = new javax.swing.JPasswordField(20);
        pf_UserKey1.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pf_UserKey1ActionPerformed(evt);
            }
        });
        pf_UserKey1.addFocusListener(new java.awt.event.FocusAdapter()
        {

            @Override
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                pf_UserKey1.selectAll();
            }
        });

        lb_UserKey1 = new javax.swing.JLabel();
        lb_UserKey1.setLabelFor(pf_UserKey1);

        pf_UserKey2 = new javax.swing.JPasswordField(20);
        pf_UserKey2.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pf_UserKey2ActionPerformed(evt);
            }
        });
        pf_UserKey2.addFocusListener(new java.awt.event.FocusAdapter()
        {

            @Override
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                pf_UserKey2.selectAll();
            }
        });

        lb_UserKey2 = new javax.swing.JLabel();
        lb_UserKey2.setLabelFor(pf_UserKey2);

        javax.swing.JPanel panel = new javax.swing.JPanel();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        switch (signType)
        {
            case ConsEnv.SIGN_IN:// 用户登录
            case ConsEnv.SIGN_RS:// 权限认证
            case ConsEnv.SIGN_FP:// 口令找回
            case ConsEnv.SIGN_CS:// 在线存储
                hpg1.addComponent(lb_UserName, javax.swing.GroupLayout.Alignment.TRAILING);
                hpg2.addComponent(tf_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);

                hpg1.addComponent(lb_UserKey0, javax.swing.GroupLayout.Alignment.TRAILING);
                hpg2.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                break;
            case ConsEnv.SIGN_UP:// 用户注册
            case ConsEnv.SIGN_SU:// 从属用户
                hpg1.addComponent(lb_UserName, javax.swing.GroupLayout.Alignment.TRAILING);
                hpg2.addComponent(tf_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);

                hpg1.addComponent(lb_UserKey0, javax.swing.GroupLayout.Alignment.TRAILING);
                hpg2.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);

                hpg1.addComponent(lb_UserKey1, javax.swing.GroupLayout.Alignment.TRAILING);
                hpg2.addComponent(pf_UserKey1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                break;
            case ConsEnv.SIGN_PK:// 修改登录口令
            case ConsEnv.SIGN_SK:// 修改安全口令
                hpg1.addComponent(lb_UserKey0, javax.swing.GroupLayout.Alignment.TRAILING);
                hpg2.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);

                hpg1.addComponent(lb_UserKey1, javax.swing.GroupLayout.Alignment.TRAILING);
                hpg2.addComponent(pf_UserKey1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);

                hpg1.addComponent(lb_UserKey2, javax.swing.GroupLayout.Alignment.TRAILING);
                hpg2.addComponent(pf_UserKey2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                break;
            case ConsEnv.SIGN_NW:
                break;
            default:
                break;
        }
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addGroup(hpg1);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addGroup(hpg2);
        hsg.addContainerGap();
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        switch (signType)
        {
            case ConsEnv.SIGN_IN:// 用户登录
            case ConsEnv.SIGN_RS:// 权限认证
            case ConsEnv.SIGN_FP:// 口令找回
            case ConsEnv.SIGN_CS:// 在线存储
                javax.swing.GroupLayout.ParallelGroup vpg11 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg11.addComponent(lb_UserName);
                vpg11.addComponent(tf_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg11);
                vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

                javax.swing.GroupLayout.ParallelGroup vpg21 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg21.addComponent(lb_UserKey0);
                vpg21.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg21);
                break;
            case ConsEnv.SIGN_UP:// 用户注册
            case ConsEnv.SIGN_SU:// 从属用户
                javax.swing.GroupLayout.ParallelGroup vpg12 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg12.addComponent(lb_UserName);
                vpg12.addComponent(tf_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg12);
                vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

                javax.swing.GroupLayout.ParallelGroup vpg22 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg22.addComponent(lb_UserKey0);
                vpg22.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg22);
                vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

                javax.swing.GroupLayout.ParallelGroup vpg32 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg32.addComponent(lb_UserKey1);
                vpg32.addComponent(pf_UserKey1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg32);
                break;
            case ConsEnv.SIGN_PK:// 修改登录口令
            case ConsEnv.SIGN_SK:// 修改安全口令
                javax.swing.GroupLayout.ParallelGroup vpg23 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg23.addComponent(lb_UserKey0);
                vpg23.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg23);
                vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

                javax.swing.GroupLayout.ParallelGroup vpg33 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg33.addComponent(lb_UserKey1);
                vpg33.addComponent(pf_UserKey1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg33);
                vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

                javax.swing.GroupLayout.ParallelGroup vpg43 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg43.addComponent(lb_UserKey2);
                vpg43.addComponent(pf_UserKey2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg43);
                break;
            case ConsEnv.SIGN_NW:
                break;
            default:
                break;
        }
        vsg.addContainerGap();
        layout.setVerticalGroup(vsg);

        pl_InfoPane.removeAll();
        pl_InfoPane.add(panel);
    }

    private void initSafeView()
    {
        if (pl_SafePane != null)
        {
            return;
        }
        pl_SafePane = new javax.swing.JPanel();
    }

    public boolean initLang()
    {
        pl_GuidPane.setVisible(false);

        switch (signType)
        {
            case ConsEnv.SIGN_IN:
                Lang.setWText(lb_UserName, LangRes.P30FA301, "用户(&U)");
                Lang.setWText(lb_UserKey0, LangRes.P30FA302, "口令(&P)");

                Lang.setWText(lb_UsrLabel, LangRes.P30FA30E, "口令找回");
                Lang.setWTips(lb_UsrLabel, LangRes.P30FA30F, "找回您的登录口令");

                Lang.setWText(lb_KeyLabel, LangRes.P30FA310, "屏幕键盘");
                Lang.setWTips(lb_KeyLabel, LangRes.P30FA311, "使用屏幕键盘输入");

                Lang.setWText(bt_Confrm, LangRes.P30FA501, "登录(&S)");

                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(&C)");

                setTitle(Lang.getLang(LangRes.P30FA201, "用户登录"));

                lb_UsrLabel.setVisible(true);
                break;
            case ConsEnv.SIGN_RS:
                Lang.setWText(lb_UserName, LangRes.P30FA301, "用户(&U)");

                Lang.setWText(lb_UserKey0, LangRes.P30FA302, "口令(&P)");

                Lang.setWText(bt_Confrm, LangRes.P30FA501, "登录(&S)");

                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(&C)");

                setTitle(Lang.getLang(LangRes.P30FA202, "身份验证"));

                lb_UsrLabel.setVisible(false);
                break;
            case ConsEnv.SIGN_UP:
                Lang.setWText(lb_UserName, LangRes.P30FA301, "用户(&U)");

                Lang.setWText(lb_UserKey0, LangRes.P30FA302, "口令(&P)");

                Lang.setWText(lb_UserKey1, LangRes.P30FA303, "确认(&R)");

                Lang.setWText(lb_KeyLabel, LangRes.P30FA307, "数据升级");
                Lang.setWTips(lb_KeyLabel, LangRes.P30FA308, "从旧版本软件升级数据");

                Lang.setWText(bt_Confrm, LangRes.P30FA502, "注册(&S)");

                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(&C)");

                setTitle(Lang.getLang(LangRes.P30FA203, "用户注册"));

                lb_UsrLabel.setVisible(false);
                break;
            case ConsEnv.SIGN_PK:
                Lang.setWText(lb_UserKey0, LangRes.P30FA304, "现有口令(&O)");

                Lang.setWText(lb_UserKey1, LangRes.P30FA305, "修改口令(&N)");

                Lang.setWText(lb_UserKey2, LangRes.P30FA306, "口令确认(&R)");

                Lang.setWText(bt_Confrm, LangRes.P30FA503, "修改(&S)");

                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(&C)");

                setTitle(Lang.getLang(LangRes.P30FA205, "登录口令修改"));

                lb_UsrLabel.setVisible(false);
                break;
            case ConsEnv.SIGN_FP:
                Lang.setWText(lb_UserName, LangRes.P30FA30C, "登录用户(&U)");

                Lang.setWText(lb_UserKey0, LangRes.P30FA30D, "安全口令(&P)");

                Lang.setWText(bt_Confrm, LangRes.P30FA509, "找回(&F)");

                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(&C)");

                setTitle(Lang.getLang(LangRes.P30FA204, "口令找回"));

                lb_UsrLabel.setVisible(false);
                break;
            case ConsEnv.SIGN_SK:
                Lang.setWText(lb_UserKey0, LangRes.P30FA304, "现有口令(&O)");

                Lang.setWText(lb_UserKey1, LangRes.P30FA30D, "安全口令(&P)");

                Lang.setWText(lb_UserKey2, LangRes.P30FA306, "口令确认(&R)");

                Lang.setWText(bt_Confrm, LangRes.P30FA508, "设定(&S)");

                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(&C)");

                setTitle(Lang.getLang(LangRes.P30FA206, "安全口令修改"));

                lb_UsrLabel.setVisible(false);
                break;
            case ConsEnv.SIGN_SU:
                setTitle(Lang.getLang(LangRes.P30FA207, "添加从属用户"));

                lb_UsrLabel.setVisible(false);
                break;
            case ConsEnv.SIGN_CS:
                Lang.setWText(lb_UserName, LangRes.P30FA301, "用户(&U)");

                Lang.setWText(lb_UserKey0, LangRes.P30FA302, "口令(&P)");

                Lang.setWText(bt_Confrm, LangRes.P30FA508, "设定(&S)");

                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(&C)");

                setTitle(Lang.getLang(LangRes.P30FA207, "配置Google Docs用户"));

                lb_UsrLabel.setVisible(false);
                break;
            default:
                break;
        }

        lb_KeyLabel.setVisible(false);
        pl_SafePane.setVisible(false);
        return true;
    }

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

    private void dispoze()
    {
        if (frame != null)
        {
            frame.dispose();
        }
        if (dialog != null)
        {
            dialog.dispose();
        }
    }

    public boolean initData()
    {
        switch (signType)
        {
            case ConsEnv.SIGN_IN:
                // 显示上次登录用户
                String name = UserMdl.getUserCfg().getCfg(ConsCfg.CFG_USER_LAST);
                if (Util.isValidate(name))
                {
                    tf_UserName.setText(name);
                }
                break;
            case ConsEnv.SIGN_RS:
                break;
            case ConsEnv.SIGN_UP:
                break;
            case ConsEnv.SIGN_PK:
                break;
            case ConsEnv.SIGN_FP:
                break;
            case ConsEnv.SIGN_SK:
                break;
            case ConsEnv.SIGN_SU:
                break;
            case ConsEnv.SIGN_CS:
                break;
            default:
                break;
        }

        java.awt.Window window = frame != null ? frame : (dialog != null ? dialog : null);
        if (window == null)
        {
            return false;
        }

        window.pack();
        java.awt.Dimension windowsize = window.getSize();
        java.awt.Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((screensize.width - windowsize.width) >> 1, (screensize.height - windowsize.height) >> 1);
        window.setVisible(true);
        return true;
    }

    public void setConfrmBackCall(com.magicpwd._face.IBackCall backCall)
    {
        this.confrm = backCall;
    }

    public void setCancelBackCall(com.magicpwd._face.IBackCall backCall)
    {
        this.cancel = backCall;
    }

    /**
     * @param evt
     */
    private void bt_CancelActionPerformed(java.awt.event.ActionEvent evt)
    {
        switch (signType)
        {
            case ConsEnv.SIGN_IN:
            case ConsEnv.SIGN_UP:
                System.exit(0);
                break;
            case ConsEnv.SIGN_RS:
            case ConsEnv.SIGN_PK:
            case ConsEnv.SIGN_SK:
            case ConsEnv.SIGN_SU:
            case ConsEnv.SIGN_NW:
            case ConsEnv.SIGN_CS:
                dispoze();
                break;
            case ConsEnv.SIGN_FP:
                initView(ConsEnv.SIGN_IN);
                initLang();
                initData();
                break;
            default:
                break;
        }
        if (cancel != null)
        {
            cancel.callBack(null, null, "");
        }
    }

    /**
     * @param evt
     */
    private void bt_ConfrmActionPerformed(java.awt.event.ActionEvent evt)
    {
        switch (signType)
        {
            case ConsEnv.SIGN_IN:
                signIn();
                break;
            case ConsEnv.SIGN_RS:
                signRs();
                break;
            case ConsEnv.SIGN_UP:
                signUp();
                break;
            case ConsEnv.SIGN_PK:
                signPk();
                break;
            case ConsEnv.SIGN_FP:
                signFp();
                break;
            case ConsEnv.SIGN_SK:
                signSk();
                break;
            case ConsEnv.SIGN_SU:
                signSu();
                break;
            case ConsEnv.SIGN_CS:
                signCs();
                break;
            default:
                break;
        }
    }

    private void tf_UserNameActionPerformed(java.awt.event.ActionEvent evt)
    {
        bt_ConfrmActionPerformed(null);
    }

    private void pf_UserKey0ActionPerformed(java.awt.event.ActionEvent evt)
    {
        bt_ConfrmActionPerformed(null);
    }

    private void pf_UserKey1ActionPerformed(java.awt.event.ActionEvent evt)
    {
        bt_ConfrmActionPerformed(null);
    }

    private void pf_UserKey2ActionPerformed(java.awt.event.ActionEvent evt)
    {
        bt_ConfrmActionPerformed(null);
    }

    private void lb_UsrLabelMouseReleased(java.awt.event.MouseEvent evt)
    {
        initView(ConsEnv.SIGN_FP);
        initLang();
        initData();
    }

    private void lb_KeyLabelMouseReleased(java.awt.event.MouseEvent evt)
    {
        if (signType == ConsEnv.SIGN_IN)
        {
            signType = ConsEnv.SIGN_FP;
            return;
        }

        DBU3000 du = new DBU3000();
        du.initView();
        du.initLang();
        du.initData();

        dispoze();
    }

    private void signIn()
    {
        String name = tf_UserName.getText();
        if (!Util.isValidate(name))
        {
            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
            tf_UserName.requestFocus();
            return;
        }
        String pwds = new String(pf_UserKey0.getPassword());
        if (!Util.isValidate(pwds))
        {
            Lang.showMesg(this, LangRes.P30FAA02, "请输入登录口令！");
            pf_UserKey0.requestFocus();
            return;
        }

        try
        {
            boolean b = UserMdl.signIn(name, pwds);
            if (!b)
            {
                errCount += 1;
                if (errCount > 2)
                {
                    Lang.showMesg(this, LangRes.P30FAA1C, "您操作的错误次太多，请确认您是否为合法用户！\n为了保障用户数据安全，软件将自动关闭。");
                    System.exit(0);
                }
                Lang.showMesg(this, LangRes.P30FAA03, "身份验证错误，请确认您的用户名及口令是否正确！");
                pf_UserKey0.setText("");
                pf_UserKey0.requestFocus();
                return;
            }
        }
        catch (java.security.NoSuchAlgorithmException exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA04, "系统错误：无法加载密码算法，请重新启动应用程序后重试！");
            System.exit(0);
            return;
        }
        catch (javax.crypto.NoSuchPaddingException exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA04, "系统错误：无法加载密码算法，请重新启动应用程序后重试！");
            System.exit(0);
            return;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA03, "身份验证错误，请确认您的用户名及口令是否正确！");
            System.exit(0);
            return;
        }

        if (confrm != null)
        {
            confrm.callBack(null, null, "");
        }
        dispoze();
    }

    private void signRs()
    {
        String name = tf_UserName.getText();
        if (!Util.isValidate(name))
        {
            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
            tf_UserName.requestFocus();
            return;
        }
        String pwds = new String(pf_UserKey0.getPassword());
        if (!Util.isValidate(pwds))
        {
            Lang.showMesg(this, LangRes.P30FAA02, "请输入登录口令！");
            pf_UserKey0.requestFocus();
            return;
        }

        try
        {
            boolean b = UserMdl.signIn(name, pwds);
            if (!b)
            {
                errCount += 1;
                if (errCount > 2)
                {
                    Lang.showMesg(this, LangRes.P30FAA1C, "您操作的错误次太多，请确认您是否为合法用户！\n为了保障用户数据安全，软件将自动关闭。");
                    System.exit(0);
                }
                Lang.showMesg(this, LangRes.P30FAA03, "身份验证错误，请确认您的用户名及口令是否正确！");
                pf_UserKey0.setText("");
                pf_UserKey0.requestFocus();
                return;
            }
        }
        catch (java.security.NoSuchAlgorithmException exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA04, "系统错误：无法加载密码算法，请重新启动应用程序后重试！");
            System.exit(0);
            return;
        }
        catch (javax.crypto.NoSuchPaddingException exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA04, "系统错误：无法加载密码算法，请重新启动应用程序后重试！");
            System.exit(0);
            return;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA03, "身份验证错误，请确认您的用户名及口令是否正确！");
            System.exit(0);
            return;
        }

        dispoze();
        if (confrm != null)
        {
            confrm.callBack(null, null, "");
        }
    }

    private void signUp()
    {
        // 登录名称检测
        String un = tf_UserName.getText();
        if (un == null)
        {
            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
            tf_UserName.requestFocus();
            return;
        }
        un = un.trim();
        if (un.length() < 1)
        {
            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
            tf_UserName.requestFocus();
            return;
        }

        // 口令为空检测
        String p1 = new String(pf_UserKey0.getPassword());
        if (p1.length() < 1)
        {
            Lang.showMesg(this, LangRes.P30FAA02, "请输入登录口令！");
            pf_UserKey0.requestFocus();
            return;
        }

        // 口令不一致检测
        String p2 = new String(pf_UserKey1.getPassword());
        if (!p1.equals(p2))
        {
            Lang.showMesg(this, LangRes.P30FAA05, "您输入的口令不一致，请重新输入！");
            pf_UserKey0.setText("");
            pf_UserKey1.setText("");
            pf_UserKey0.requestFocus();
            return;
        }
        try
        {
            boolean b = UserMdl.signUp(un, p1);
            if (!b)
            {
                Lang.showMesg(this, LangRes.P30FAA06, "注册用户失败，请更换用户名及口令后重试！");
                tf_UserName.setText("");
                pf_UserKey0.setText("");
                pf_UserKey1.setText("");
                tf_UserName.requestFocus();
                return;
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA07, "创建用户失败，请重新启动本程序尝试！");
            System.exit(0);
            return;
        }

        dispoze();
        if (confrm != null)
        {
            confrm.callBack(null, null, "");
        }
    }

    private void signPk()
    {
        String p1 = new String(pf_UserKey1.getPassword());
        String p2 = new String(pf_UserKey2.getPassword());
        if (!p1.equals(p2))
        {
            Lang.showMesg(this, LangRes.P30FAA08, "新输入的两次口令不匹配，请重新输入！");
            pf_UserKey1.setText("");
            pf_UserKey2.setText("");
            pf_UserKey1.requestFocus();
            return;
        }

        String p0 = new String(pf_UserKey0.getPassword());
        if (!Util.isValidate(p0))
        {
            Lang.showMesg(this, LangRes.P30FAA02, "请输入登录口令！");
            pf_UserKey0.requestFocus();
            return;
        }
        try
        {
            boolean b = UserMdl.signPk(p0, p1);
            if (!b)
            {
                Lang.showMesg(this, LangRes.P30FAA09, "口令更改失败，请检查您输入的口令是否与原口令相同！");
                pf_UserKey0.setText("");
                pf_UserKey1.setText("");
                pf_UserKey2.setText("");
                pf_UserKey0.requestFocus();
                return;
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA09, "口令更改失败，请检查您输入的口令是否与原口令相同！");
            return;
        }

        dispoze();
        if (confrm != null)
        {
            confrm.callBack(null, null, "");
        }
        Lang.showMesg(this, LangRes.P30FAA0A, "登录口令修改成功，您可以使用新口令登录了！");
    }

    private void signFp()
    {
        String name = tf_UserName.getText();
        if (!Util.isValidate(name))
        {
            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
            tf_UserName.requestFocus();
            return;
        }
        String pwds = new String(pf_UserKey0.getPassword());
        if (!Util.isValidate(pwds))
        {
            Lang.showMesg(this, LangRes.P30FAA14, "请输入安全口令！");
            pf_UserKey0.requestFocus();
            return;
        }

        StringBuffer sb = new StringBuffer(pwds);
        try
        {
            boolean b = UserMdl.signFp(name, sb);
            if (!b)
            {
                Lang.showMesg(this, LangRes.P30FAA03, "身份验证错误，请确认您的用户名及口令是否正确！");
                tf_UserName.setText("");
                pf_UserKey0.setText("");
                tf_UserName.requestFocus();
                errCount += 1;
                if (errCount > 2)
                {
                    System.exit(0);
                }
                return;
            }
        }
        catch (java.security.NoSuchAlgorithmException exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA04, "系统错误：无法加载密码算法，请重新启动应用程序后重试！");
            System.exit(0);
            return;
        }
        catch (javax.crypto.NoSuchPaddingException exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA04, "系统错误：无法加载密码算法，请重新启动应用程序后重试！");
            System.exit(0);
            return;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA03, "身份验证错误，请确认您的用户名及口令是否正确！");
            tf_UserName.setText("");
            pf_UserKey0.setText("");
            errCount += 1;
            if (errCount > 2)
            {
                System.exit(0);
            }
            return;
        }

        if (confrm != null)
        {
            confrm.callBack(null, null, sb.toString());
        }
        dispoze();
        Lang.showMesg(null, LangRes.P30FAA18, "", sb.toString());
    }

    /**
     * 
     */
    private void signSk()
    {
        String p1 = new String(pf_UserKey1.getPassword());
        if (!Util.isValidate(p1))
        {
            Lang.showMesg(this, LangRes.P30FAA14, "请输入安全口令！");
            pf_UserKey1.setText("");
            pf_UserKey1.requestFocus();
            return;
        }
        String p2 = new String(pf_UserKey2.getPassword());

        if (!p1.equals(p2))
        {
            Lang.showMesg(this, LangRes.P30FAA15, "您输入的两次口令不匹配，请重新输入！");
            pf_UserKey1.setText("");
            pf_UserKey2.setText("");
            pf_UserKey1.requestFocus();
            return;
        }

        String p0 = new String(pf_UserKey0.getPassword());
        if (!Util.isValidate(p0))
        {
            Lang.showMesg(this, LangRes.P30FAA02, "请输入登录口令！");
            pf_UserKey0.requestFocus();
            return;
        }
        try
        {
            boolean b = UserMdl.signSk(p0, p1);
            if (!b)
            {
                Lang.showMesg(this, LangRes.P30FAA16, "安全口令设定失败，请稍后重新尝试！");
                pf_UserKey0.setText("");
                pf_UserKey1.setText("");
                pf_UserKey2.setText("");
                pf_UserKey0.requestFocus();
                return;
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA16, "安全口令设定失败，请稍后重新尝试！");
            return;
        }

        dispoze();
        if (confrm != null)
        {
            confrm.callBack(null, null, "");
        }
        Lang.showMesg(this, LangRes.P30FAA17, "安全口令设定成功！");
    }

    private void signSu()
    {
        // 登录名称检测
        String un = tf_UserName.getText();
        if (un == null)
        {
            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
            tf_UserName.requestFocus();
            return;
        }
        un = un.trim();
        if (un.length() < 1)
        {
            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
            tf_UserName.requestFocus();
            return;
        }
        String ul = UserMdl.getUserCfg().getCfg(ConsCfg.CFG_USER, "");
        if (ul.indexOf(un + ',') >= 0)
        {
            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
        }
    }

    private void signCs()
    {
        // 登录名称检测
        String un = tf_UserName.getText();
        if (un == null)
        {
            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
            tf_UserName.requestFocus();
            return;
        }
        un = un.trim();
        if (un.length() < 1)
        {
            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
            tf_UserName.requestFocus();
            return;
        }

        char[] uc = pf_UserKey0.getPassword();
        if (uc == null || uc.length < 1)
        {
            Lang.showMesg(this, LangRes.P30FAA02, "请输入登录口令！");
            pf_UserKey0.requestFocus();
            return;
        }
        dispoze();
        if (confrm != null)
        {
            confrm.callBack(null, null, un, new String(uc));
        }
    }
    private javax.swing.JButton bt_Cancel;
    private javax.swing.JButton bt_Confrm;
    private javax.swing.JLabel lb_UsrLabel;
    private javax.swing.JLabel lb_KeyLabel;
    private javax.swing.JPanel pl_GuidPane;
    private javax.swing.JPanel pl_InfoPane;
    private javax.swing.JPanel pl_SafePane;
    private javax.swing.JLabel lb_UserKey0;
    private javax.swing.JLabel lb_UserKey1;
    private javax.swing.JLabel lb_UserKey2;
    private javax.swing.JLabel lb_UserName;
    private javax.swing.JPasswordField pf_UserKey0;
    private javax.swing.JPasswordField pf_UserKey1;
    private javax.swing.JPasswordField pf_UserKey2;
    private javax.swing.JTextField tf_UserName;
}
