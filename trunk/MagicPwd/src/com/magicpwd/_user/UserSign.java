package com.magicpwd._user;

import com.magicpwd._cons.ConsCfg;
import java.awt.event.FocusEvent;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;
import javax.swing.JPanel;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.m.UserMdl;
import com.magicpwd.u.DBU3000;

public class UserSign extends JPanel
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
     * 模式父窗口
     */
    private java.awt.Window window;
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
    public UserSign(int type)
    {
        this.signType = type;

        javax.swing.JFrame frame = new javax.swing.JFrame();
        frame.setResizable(false);
        frame.setIconImage(Util.getLogo());
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        switch (signType)
        {
            case ConsEnv.SIGN_IN:
                frame.setTitle(Lang.getLang(LangRes.P30FA201, "用户登录"));
                break;
            case ConsEnv.SIGN_RS:
                frame.setTitle(Lang.getLang(LangRes.P30FA202, "身份验证"));
                break;
            case ConsEnv.SIGN_UP:
                frame.setTitle(Lang.getLang(LangRes.P30FA203, "用户注册"));
                break;
            case ConsEnv.SIGN_FP:
                frame.setTitle(Lang.getLang(LangRes.P30FA204, "口令找回"));
                break;
            case ConsEnv.SIGN_PK:
                frame.setTitle(Lang.getLang(LangRes.P30FA205, "登录口令修改"));
                break;
            case ConsEnv.SIGN_SK:
                frame.setTitle(Lang.getLang(LangRes.P30FA206, "安全口令修改"));
                break;
            case ConsEnv.SIGN_SU:
                frame.setTitle(Lang.getLang(LangRes.P30FA207, "添加从属用户"));
                break;
            default:
                break;
        }
        window = frame;
    }

    /**
     * 模式窗口
     * @param type
     * @param frame
     */
    public UserSign(int type, javax.swing.JFrame frame)
    {
        this.signType = type;

        javax.swing.JDialog dialog = new javax.swing.JDialog(frame, true);
        dialog.setResizable(false);
        dialog.setIconImage(Util.getLogo());
        dialog.setDefaultCloseOperation(javax.swing.JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().add(this);
        switch (signType)
        {
            case ConsEnv.SIGN_IN:
                dialog.setTitle(Lang.getLang(LangRes.P30FA201, "用户登录"));
                break;
            case ConsEnv.SIGN_RS:
                dialog.setTitle(Lang.getLang(LangRes.P30FA202, "身份验证"));
                break;
            case ConsEnv.SIGN_UP:
                dialog.setTitle(Lang.getLang(LangRes.P30FA203, "用户注册"));
                break;
            case ConsEnv.SIGN_FP:
                dialog.setTitle(Lang.getLang(LangRes.P30FA204, "口令找回"));
                break;
            case ConsEnv.SIGN_PK:
                dialog.setTitle(Lang.getLang(LangRes.P30FA205, "登录口令修改"));
                break;
            case ConsEnv.SIGN_SK:
                dialog.setTitle(Lang.getLang(LangRes.P30FA206, "安全口令修改"));
                break;
            case ConsEnv.SIGN_SU:
                dialog.setTitle(Lang.getLang(LangRes.P30FA207, "添加从属用户"));
                break;
            default:
                break;
        }
        window = dialog;
    }

    public void init()
    {
        switch (signType)
        {
            case ConsEnv.SIGN_IN:
                initSignInView();
                initSignInLang();
                // 显示上次登录用户
                String name = UserMdl.getUserCfg().getCfg(ConsCfg.CFG_USER_LAST);
                if (Util.isValidate(name))
                {
                    tf_UserName.setText(name);
                }
                break;
            case ConsEnv.SIGN_RS:
                initSignRsView();
                initSignRsLang();
                break;
            case ConsEnv.SIGN_UP:
                initSignUpView();
                initSignUpLang();
                break;
            case ConsEnv.SIGN_PK:
                initSignPkView();
                initSignPkLang();
                break;
            case ConsEnv.SIGN_FP:
                initSignFpView();
                initSignFpLang();
                break;
            case ConsEnv.SIGN_SK:
                initSignSkView();
                initSignSkLang();
                break;
            case ConsEnv.SIGN_SU:
                initSignSuView();
                initSignSuLang();
                break;
            default:
                break;
        }

        window.pack();
        java.awt.Dimension windowsize = window.getSize();
        java.awt.Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((screensize.width - windowsize.width) >> 1, (screensize.height - windowsize.height) >> 1);
        window.setVisible(true);
    }

    public void setConfrmBackCall(com.magicpwd._face.IBackCall backCall)
    {
        this.confrm = backCall;
    }

    public void setCancelBackCall(com.magicpwd._face.IBackCall backCall)
    {
        this.cancel = backCall;
    }

    private void initSignInView()
    {
        if (lb_UserName == null)
        {
            lb_UserName = new javax.swing.JLabel();
        }
        if (tf_UserName == null)
        {
            tf_UserName = new javax.swing.JTextField();
            tf_UserName.addFocusListener(new java.awt.event.FocusAdapter()
            {

                @Override
                public void focusGained(FocusEvent e)
                {
                    tf_UserName.selectAll();
                }
            });
        }
        if (lb_UserKeys == null)
        {
            lb_UserKeys = new javax.swing.JLabel();
        }
        if (pf_UserKeys == null)
        {
            pf_UserKeys = new javax.swing.JPasswordField();
            pf_UserKeys.addFocusListener(new java.awt.event.FocusAdapter()
            {

                @Override
                public void focusGained(FocusEvent e)
                {
                    pf_UserKeys.selectAll();
                }
            });
        }
        if (bt_Cancel == null)
        {
            bt_Cancel = new javax.swing.JButton();
        }
        if (bt_Confrm == null)
        {
            bt_Confrm = new javax.swing.JButton();
        }
        lb_KeyLabel = new javax.swing.JLabel();

        lb_UserName.setDisplayedMnemonic('U');
        lb_UserName.setLabelFor(tf_UserName);
        lb_UserName.setText("用户(U)");

        lb_UserKeys.setDisplayedMnemonic('P');
        lb_UserKeys.setLabelFor(pf_UserKeys);
        lb_UserKeys.setText("口令(P)");

        pf_UserKeys.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ConfrmActionPerformed(evt);
            }
        });

        bt_Cancel.setMnemonic('C');
        bt_Cancel.setText("取消(C)");
        bt_Cancel.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_CancelActionPerformed(evt);
            }
        });

        bt_Confrm.setMnemonic('S');
        bt_Confrm.setText("登录(S)");
        bt_Confrm.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ConfrmActionPerformed(evt);
            }
        });

        lb_KeyLabel.setText("口令找回");
        lb_KeyLabel.addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                lb_KeyLabelMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addComponent(lb_UserName).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(tf_UserName,
                javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)).addGroup(
                layout.createSequentialGroup().addComponent(lb_UserKeys).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(pf_UserKeys,
                javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addComponent(lb_KeyLabel).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(bt_Confrm).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(bt_Cancel))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_UserName).addComponent(tf_UserName,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_UserKeys).addComponent(pf_UserKeys,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(bt_Cancel).addComponent(bt_Confrm).addComponent(lb_KeyLabel)).addContainerGap()));
    }

    private void initSignRsView()
    {
        if (lb_UserName == null)
        {
            lb_UserName = new javax.swing.JLabel();
        }
        if (tf_UserName == null)
        {
            tf_UserName = new javax.swing.JTextField();
            tf_UserName.addFocusListener(new java.awt.event.FocusAdapter()
            {

                @Override
                public void focusGained(FocusEvent e)
                {
                    tf_UserName.selectAll();
                }
            });
        }
        if (lb_UserKeys == null)
        {
            lb_UserKeys = new javax.swing.JLabel();
        }
        if (pf_UserKeys == null)
        {
            pf_UserKeys = new javax.swing.JPasswordField();
            pf_UserKeys.addFocusListener(new java.awt.event.FocusAdapter()
            {

                @Override
                public void focusGained(FocusEvent e)
                {
                    pf_UserKeys.selectAll();
                }
            });
        }
        if (bt_Cancel == null)
        {
            bt_Cancel = new javax.swing.JButton();
        }
        if (bt_Confrm == null)
        {
            bt_Confrm = new javax.swing.JButton();
        }

        lb_UserName.setLabelFor(tf_UserName);

        lb_UserKeys.setLabelFor(pf_UserKeys);

        bt_Cancel.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_CancelActionPerformed(evt);
            }
        });

        bt_Confrm.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ConfrmActionPerformed(evt);
            }
        });

        pf_UserKeys.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ConfrmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(lb_UserKeys).addComponent(lb_UserName)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(tf_UserName,
                javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE).addComponent(pf_UserKeys,
                javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)).addContainerGap()).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE).addComponent(
                bt_Confrm).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(bt_Cancel).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_UserName).addComponent(tf_UserName,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_UserKeys).addComponent(pf_UserKeys,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(bt_Cancel).addComponent(bt_Confrm)).addContainerGap()));
    }

    private void initSignUpView()
    {
        if (lb_UserName == null)
        {
            lb_UserName = new javax.swing.JLabel();
        }
        if (tf_UserName == null)
        {
            tf_UserName = new javax.swing.JTextField();
            tf_UserName.addFocusListener(new java.awt.event.FocusAdapter()
            {

                @Override
                public void focusGained(FocusEvent e)
                {
                    tf_UserName.selectAll();
                }
            });
        }
        if (lb_UserKeys == null)
        {
            lb_UserKeys = new javax.swing.JLabel();
        }
        if (pf_UserKeys == null)
        {
            pf_UserKeys = new javax.swing.JPasswordField();
            pf_UserKeys.addFocusListener(new java.awt.event.FocusAdapter()
            {

                @Override
                public void focusGained(FocusEvent e)
                {
                    pf_UserKeys.selectAll();
                }
            });
        }
        if (lb_FirmPwds == null)
        {
            lb_FirmPwds = new javax.swing.JLabel();
        }
        if (pf_FirmPwds == null)
        {
            pf_FirmPwds = new javax.swing.JPasswordField();
            pf_FirmPwds.addFocusListener(new java.awt.event.FocusAdapter()
            {

                @Override
                public void focusGained(FocusEvent e)
                {
                    pf_FirmPwds.selectAll();
                }
            });
        }
        if (bt_Cancel == null)
        {
            bt_Cancel = new javax.swing.JButton();
        }
        if (bt_Confrm == null)
        {
            bt_Confrm = new javax.swing.JButton();
        }
        if (lb_KeyLabel == null)
        {
            lb_KeyLabel = new javax.swing.JLabel();
        }

        lb_UserName.setDisplayedMnemonic('U');
        lb_UserName.setLabelFor(tf_UserName);
        lb_UserName.setText("用户(U)");

        lb_UserKeys.setDisplayedMnemonic('P');
        lb_UserKeys.setLabelFor(pf_UserKeys);
        lb_UserKeys.setText("口令(P)");

        lb_FirmPwds.setDisplayedMnemonic('R');
        lb_FirmPwds.setLabelFor(pf_FirmPwds);
        lb_FirmPwds.setText("确认(R)");

        bt_Cancel.setMnemonic('C');
        bt_Cancel.setText("取消(C)");
        bt_Cancel.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_CancelActionPerformed(evt);
            }
        });

        bt_Confrm.setMnemonic('S');
        bt_Confrm.setText("注册(S)");
        bt_Confrm.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ConfrmActionPerformed(evt);
            }
        });

        lb_KeyLabel.setText("数据升级");
        lb_KeyLabel.addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                lb_KeyLabelMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addComponent(lb_UserName).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(tf_UserName,
                javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)).addGroup(
                layout.createSequentialGroup().addComponent(lb_UserKeys).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(pf_UserKeys,
                javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)).addGroup(
                layout.createSequentialGroup().addComponent(lb_FirmPwds).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(pf_FirmPwds,
                javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addComponent(lb_KeyLabel).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(bt_Confrm).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(bt_Cancel))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_UserName).addComponent(tf_UserName,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_UserKeys).addComponent(pf_UserKeys,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_FirmPwds).addComponent(pf_FirmPwds,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(bt_Cancel).addComponent(bt_Confrm).addComponent(lb_KeyLabel)).addContainerGap()));
    }

    private void initSignPkView()
    {
        if (lb_CurrPwds == null)
        {
            lb_CurrPwds = new javax.swing.JLabel();
        }
        if (pf_CurrPwds == null)
        {
            pf_CurrPwds = new javax.swing.JPasswordField();
            pf_CurrPwds.addFocusListener(new java.awt.event.FocusAdapter()
            {

                @Override
                public void focusGained(FocusEvent e)
                {
                    pf_CurrPwds.selectAll();
                }
            });
        }
        if (lb_UserKeys == null)
        {
            lb_UserKeys = new javax.swing.JLabel();
        }
        if (pf_UserKeys == null)
        {
            pf_UserKeys = new javax.swing.JPasswordField();
            pf_UserKeys.addFocusListener(new java.awt.event.FocusAdapter()
            {

                @Override
                public void focusGained(FocusEvent e)
                {
                    pf_UserKeys.selectAll();
                }
            });
        }
        if (lb_FirmPwds == null)
        {
            lb_FirmPwds = new javax.swing.JLabel();
        }
        if (pf_FirmPwds == null)
        {
            pf_FirmPwds = new javax.swing.JPasswordField();
        }
        if (bt_Cancel == null)
        {
            bt_Cancel = new javax.swing.JButton();
        }
        if (bt_Confrm == null)
        {
            bt_Confrm = new javax.swing.JButton();
        }

        lb_CurrPwds.setLabelFor(pf_CurrPwds);

        lb_UserKeys.setLabelFor(pf_UserKeys);

        lb_FirmPwds.setLabelFor(pf_FirmPwds);

        bt_Cancel.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_CancelActionPerformed(evt);
            }
        });

        bt_Confrm.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ConfrmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(lb_CurrPwds).addComponent(lb_UserKeys).addComponent(lb_FirmPwds)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pf_FirmPwds,
                javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE).addComponent(pf_UserKeys,
                javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE).addComponent(pf_CurrPwds,
                javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)).addContainerGap()).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap(70, Short.MAX_VALUE).addComponent(bt_Confrm).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
                bt_Cancel).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_CurrPwds).addComponent(pf_CurrPwds,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_UserKeys).addComponent(pf_UserKeys,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_FirmPwds).addComponent(pf_FirmPwds,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(bt_Cancel).addComponent(bt_Confrm)).addContainerGap()));
    }

    private void initSignFpView()
    {
        lb_KeyLabel.setVisible(false);
    }

    private void initSignSkView()
    {
        if (lb_UserKeys == null)
        {
            lb_UserKeys = new javax.swing.JLabel();
        }
        if (pf_UserKeys == null)
        {
            pf_UserKeys = new javax.swing.JPasswordField();
            pf_UserKeys.addFocusListener(new java.awt.event.FocusAdapter()
            {

                @Override
                public void focusGained(FocusEvent e)
                {
                    pf_UserKeys.selectAll();
                }
            });
        }
        if (lb_FirmPwds == null)
        {
            lb_FirmPwds = new javax.swing.JLabel();
        }
        if (pf_FirmPwds == null)
        {
            pf_FirmPwds = new javax.swing.JPasswordField();
            pf_FirmPwds.addFocusListener(new java.awt.event.FocusAdapter()
            {

                @Override
                public void focusGained(FocusEvent e)
                {
                    pf_FirmPwds.selectAll();
                }
            });
        }
        if (bt_Cancel == null)
        {
            bt_Cancel = new javax.swing.JButton();
        }
        if (bt_Confrm == null)
        {
            bt_Confrm = new javax.swing.JButton();
        }

        lb_UserKeys.setLabelFor(pf_UserKeys);

        lb_FirmPwds.setLabelFor(pf_FirmPwds);

        bt_Cancel.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_CancelActionPerformed(evt);
            }
        });

        bt_Confrm.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ConfrmActionPerformed(evt);
            }
        });

        pf_FirmPwds.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ConfrmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(lb_FirmPwds).addComponent(lb_UserKeys)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pf_UserKeys,
                javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE).addComponent(pf_FirmPwds,
                javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)).addContainerGap()).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap(70, Short.MAX_VALUE).addComponent(bt_Confrm).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
                bt_Cancel).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_UserKeys).addComponent(pf_UserKeys,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_FirmPwds).addComponent(pf_FirmPwds,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(bt_Cancel).addComponent(bt_Confrm)).addContainerGap()));
    }

    /**
     * 
     */
    private void initSignSuView()
    {
    }

    private void initSignInLang()
    {
        Lang.setWText(lb_UserName, LangRes.P30FA301, "用户(&U)");
        Lang.setWText(lb_UserKeys, LangRes.P30FA302, "口令(&P)");

        Lang.setWText(bt_Confrm, LangRes.P30FA501, "登录(&S)");

        Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(&C)");

//        Lang.setWText(lb_UsrLabel, LangRes.P30FA30E, "用户注册");
//        Lang.setWTips(lb_UsrLabel, LangRes.P30FA30F, "注册一个新账户");

        Lang.setWText(lb_KeyLabel, LangRes.P30FA30E, "口令找回");
        Lang.setWTips(lb_KeyLabel, LangRes.P30FA30F, "找回您的登录口令");
    }

    private void initSignRsLang()
    {
        Lang.setWText(lb_UserName, LangRes.P30FA301, "用户(&U)");

        Lang.setWText(lb_UserKeys, LangRes.P30FA302, "口令(&P)");

        Lang.setWText(bt_Confrm, LangRes.P30FA501, "登录(&S)");

        Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(&C)");
    }

    private void initSignUpLang()
    {
        Lang.setWText(lb_UserName, LangRes.P30FA301, "用户(&U)");

        Lang.setWText(lb_UserKeys, LangRes.P30FA302, "口令(&P)");

        Lang.setWText(lb_FirmPwds, LangRes.P30FA303, "确认(&R)");

        Lang.setWText(lb_KeyLabel, LangRes.P30FA307, "数据升级");
        Lang.setWTips(lb_KeyLabel, LangRes.P30FA308, "从旧版本软件升级数据");
        lb_KeyLabel.setVisible(false);

        Lang.setWText(bt_Confrm, LangRes.P30FA502, "注册(&S)");

        Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(&C)");
    }

    private void initSignPkLang()
    {
        Lang.setWText(lb_CurrPwds, LangRes.P30FA304, "现有口令(&O)");

        Lang.setWText(lb_UserKeys, LangRes.P30FA305, "修改口令(&N)");

        Lang.setWText(lb_FirmPwds, LangRes.P30FA306, "口令确认(&R)");

        Lang.setWText(bt_Confrm, LangRes.P30FA503, "修改(&S)");

        Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(&C)");
    }

    private void initSignFpLang()
    {
        ((javax.swing.JFrame) window).setTitle(Lang.getLang(LangRes.P30FA204, "口令找回"));

        Lang.setWText(lb_UserName, LangRes.P30FA30C, "登录用户(&U)");

        Lang.setWText(lb_UserKeys, LangRes.P30FA30D, "安全口令(&P)");

        Lang.setWText(bt_Confrm, LangRes.P30FA509, "找回(&F)");

        Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(&C)");
    }

    private void initSignSkLang()
    {
        Lang.setWText(lb_UserKeys, LangRes.P30FA30D, "安全口令(&P)");

        Lang.setWText(lb_FirmPwds, LangRes.P30FA306, "口令确认(&R)");

        Lang.setWText(bt_Confrm, LangRes.P30FA508, "设定(&S)");

        Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(&C)");
    }

    private void initSignSuLang()
    {
    }

    /**
     * @param evt
     */
    private void bt_CancelActionPerformed(java.awt.event.ActionEvent evt)
    {
        switch (signType)
        {
            case ConsEnv.SIGN_IN:
                System.exit(0);
                break;
            case ConsEnv.SIGN_RS:
                window.dispose();
                break;
            case ConsEnv.SIGN_UP:
                System.exit(0);
                break;
            case ConsEnv.SIGN_PK:
                window.dispose();
                break;
            case ConsEnv.SIGN_FP:
                lb_KeyLabel.setVisible(true);
                ((javax.swing.JFrame) window).setTitle(Lang.getLang(LangRes.P30FA201, "用户登录"));
                initSignInLang();
                signType = ConsEnv.SIGN_IN;
                break;
            case ConsEnv.SIGN_SK:
                window.dispose();
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
                signIn();
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
            default:
                break;
        }
    }

    private void pf_UserKeysActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    private void lb_UsrLabelMouseClicked(java.awt.event.MouseEvent evt)
    {
    }

    private void lb_KeyLabelMouseReleased(java.awt.event.MouseEvent evt)
    {
        if (signType == ConsEnv.SIGN_IN)
        {
            signType = ConsEnv.SIGN_FP;
            initSignFpView();
            initSignFpLang();
            return;
        }

        DBU3000 du = new DBU3000();
        du.initView();
        du.initLang();
        du.initData();

        window.dispose();
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
        String pwds = new String(pf_UserKeys.getPassword());
        if (!Util.isValidate(pwds))
        {
            Lang.showMesg(this, LangRes.P30FAA02, "请输入登录口令！");
            pf_UserKeys.requestFocus();
            return;
        }

        try
        {
            boolean b = UserMdl.signIn(name, pwds);
            if (!b)
            {
                Lang.showMesg(this, LangRes.P30FAA03, "身份验证错误，请确认您的用户名及口令是否正确！");
                pf_UserKeys.setText("");
                pf_UserKeys.requestFocus();
                errCount += 1;
                if (errCount > 2)
                {
                    System.exit(0);
                }
                return;
            }
        }
        catch (NoSuchAlgorithmException exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA04, "系统错误：无法加载密码算法，请重新启动应用程序后重试！");
            System.exit(0);
            return;
        }
        catch (NoSuchPaddingException exp)
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

        window.dispose();
        if (confrm != null)
        {
            confrm.callBack(null, null, "");
        }
    }

    private void signRs()
    {
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
        String p1 = new String(pf_UserKeys.getPassword());
        if (p1.length() < 1)
        {
            Lang.showMesg(this, LangRes.P30FAA02, "请输入登录口令！");
            pf_UserKeys.requestFocus();
            return;
        }

        // 口令不一致检测
        String p2 = new String(pf_FirmPwds.getPassword());
        if (!p1.equals(p2))
        {
            Lang.showMesg(this, LangRes.P30FAA05, "您输入的口令不一致，请重新输入！");
            pf_UserKeys.setText("");
            pf_FirmPwds.setText("");
            pf_UserKeys.requestFocus();
            return;
        }
        try
        {
            boolean b = UserMdl.signUp(un, p1);
            if (!b)
            {
                Lang.showMesg(this, LangRes.P30FAA06, "注册用户失败，请更换用户名及口令后重试！");
                tf_UserName.setText("");
                pf_UserKeys.setText("");
                pf_FirmPwds.setText("");
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

        window.dispose();
        if (confrm != null)
        {
            confrm.callBack(null, null, "");
        }
    }

    private void signPk()
    {
        String p1 = new String(pf_UserKeys.getPassword());
        String p2 = new String(pf_FirmPwds.getPassword());
        if (!p1.equals(p2))
        {
            Lang.showMesg(this, LangRes.P30FAA08, "新输入的两次口令不匹配，请重新输入！");
            pf_UserKeys.setText("");
            pf_FirmPwds.setText("");
            pf_UserKeys.requestFocus();
            return;
        }

        String p0 = new String(pf_CurrPwds.getPassword());
        try
        {
            boolean b = UserMdl.signPk(p0, p1);
            if (!b)
            {
                Lang.showMesg(this, LangRes.P30FAA09, "口令更改失败，请检查您输入的口令是否与原口令相同！");
                pf_CurrPwds.setText("");
                pf_UserKeys.setText("");
                pf_FirmPwds.setText("");
                pf_CurrPwds.requestFocus();
                return;
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA09, "口令更改失败，请检查您输入的口令是否与原口令相同！");
            return;
        }

        window.dispose();
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
        String pwds = new String(pf_UserKeys.getPassword());
        if (!Util.isValidate(pwds))
        {
            Lang.showMesg(this, LangRes.P30FAA02, "请输入登录口令！");
            pf_UserKeys.requestFocus();
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
                pf_UserKeys.setText("");
                tf_UserName.requestFocus();
                errCount += 1;
                if (errCount > 2)
                {
                    System.exit(0);
                }
                return;
            }
        }
        catch (NoSuchAlgorithmException exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA04, "系统错误：无法加载密码算法，请重新启动应用程序后重试！");
            System.exit(0);
            return;
        }
        catch (NoSuchPaddingException exp)
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
            pf_UserKeys.setText("");
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
        window.dispose();
        Lang.showMesg(null, LangRes.P30FAA18, "", sb.toString());
    }

    /**
     * 
     */
    private void signSk()
    {
        String p1 = new String(pf_UserKeys.getPassword());
        if (!Util.isValidate(p1))
        {
            Lang.showMesg(this, LangRes.P30FAA14, "请输入安全口令！");
            pf_FirmPwds.setText("");
            pf_UserKeys.requestFocus();
            return;
        }
        String p2 = new String(pf_FirmPwds.getPassword());

        if (!p1.equals(p2))
        {
            Lang.showMesg(this, LangRes.P30FAA15, "您输入的两次口令不匹配，请重新输入！");
            pf_UserKeys.setText("");
            pf_FirmPwds.setText("");
            pf_UserKeys.requestFocus();
            return;
        }

        try
        {
            boolean b = UserMdl.signSk(p1);
            if (!b)
            {
                Lang.showMesg(this, LangRes.P30FAA16, "安全口令设定失败，请稍后重新尝试！");
                pf_CurrPwds.setText("");
                pf_UserKeys.setText("");
                pf_FirmPwds.setText("");
                pf_CurrPwds.requestFocus();
                return;
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA16, "安全口令设定失败，请稍后重新尝试！");
            return;
        }

        window.dispose();
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
    /**
     * 登录用户
     */
    private javax.swing.JLabel lb_UserName;
    private javax.swing.JTextField tf_UserName;
    /**
     * 登录口令
     */
    private javax.swing.JLabel lb_UserKeys;
    private javax.swing.JPasswordField pf_UserKeys;
    /**
     * 交互按钮
     */
    private javax.swing.JButton bt_Cancel;
    private javax.swing.JButton bt_Confrm;
    /**
     * 口令确认（用于口令修改及用户注册）
     */
    private javax.swing.JLabel lb_FirmPwds;
    private javax.swing.JPasswordField pf_FirmPwds;
    /**
     * 当前口令（用于口令修改）
     */
    private javax.swing.JLabel lb_CurrPwds;
    private javax.swing.JPasswordField pf_CurrPwds;
    /**
     * 用户注册及口令找回
     */
    private javax.swing.JLabel lb_UsrLabel;
    private javax.swing.JLabel lb_KeyLabel;
    private javax.swing.JCheckBox ck_WebCount;
}
