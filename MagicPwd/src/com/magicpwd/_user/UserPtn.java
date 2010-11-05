package com.magicpwd._user;

import com.magicpwd.__i.IBackCall;
import com.magicpwd._comn.S1S1;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._mail.Connect;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.Jpng;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.m.UserMdl;
import com.magicpwd.u.DBU3000;
import com.magicpwd.v.TrayPtn;
import javax.imageio.ImageIO;

public class UserPtn extends javax.swing.JPanel
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
     * 动态图像
     */
    private Jpng jpng;
    /**
     * 窗体对象引用
     */
    private javax.swing.JFrame frame;
    private javax.swing.JDialog dialog;
    /**
     * 成功回调函数
     */
    private IBackCall backCall;
    /**
     * 导航图标
     */
    private static javax.swing.Icon guidIcon;
    private TrayPtn trayPtn;
    private UserMdl userMdl;

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
        dialog = new javax.swing.JDialog(frame, true);
        dialog.setResizable(false);
        dialog.setIconImage(Bean.getLogo(16));
        dialog.setDefaultCloseOperation(javax.swing.JDialog.DISPOSE_ON_CLOSE);
    }

    public boolean initView(int type)
    {
        if (type == signType)
        {
            return true;
        }

        this.signType = type;

        initGuidView();
        initInfoView();
        initSafeView();

        if (lb_UsrLabel == null)
        {
            lb_UsrLabel = new javax.swing.JLabel();
            lb_UsrLabel.setForeground(java.awt.Color.blue);
            lb_UsrLabel.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
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
            lb_KeyLabel.setForeground(java.awt.Color.blue);
            lb_KeyLabel.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
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
        hsg1.addContainerGap();
        hsg1.addComponent(pl_InfoPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hsg1.addContainerGap();
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addContainerGap();
        hsg2.addComponent(pl_SafePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hsg2.addContainerGap();
        javax.swing.GroupLayout.SequentialGroup hsg3 = layout.createSequentialGroup();
        hsg3.addContainerGap();
        hsg3.addComponent(lb_UsrLabel);
        hsg3.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg3.addComponent(lb_KeyLabel);
        hsg3.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE);
        hsg3.addComponent(bt_Confrm);
        hsg3.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg3.addComponent(bt_Cancel);
        hsg3.addContainerGap();
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(pl_GuidPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg1.addGroup(hsg1);
        hpg1.addGroup(hsg2);
        hpg1.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hsg3);
//        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
//        hsg.addContainerGap();
//        hsg.addGroup(hpg1);
//        hsg.addContainerGap();
        layout.setHorizontalGroup(hpg1);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(bt_Cancel);
        vpg1.addComponent(bt_Confrm);
        vpg1.addComponent(lb_KeyLabel);
        vpg1.addComponent(lb_UsrLabel);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        //vsg.addContainerGap();
        vsg.addComponent(pl_GuidPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(pl_InfoPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(pl_SafePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        vsg.addGroup(vpg1);
        vsg.addContainerGap();
        layout.setVerticalGroup(vsg);

        if (frame != null)
        {
            frame.getContentPane().add(this);
        }
        if (dialog != null)
        {
            dialog.getContentPane().add(this);
        }
        return true;
    }

    private void initGuidView()
    {
        if (pl_GuidPane != null)
        {
            return;
        }
        pl_GuidPane = new javax.swing.JPanel();
        pl_GuidPane.setOpaque(true);
        pl_GuidPane.setBackground(java.awt.Color.white);
        pl_GuidPane.setLayout(new java.awt.BorderLayout(0, 0));

        lb_GuidIcon = new javax.swing.JLabel();
        lb_GuidIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pl_GuidPane.add(java.awt.BorderLayout.CENTER, lb_GuidIcon);

        try
        {
            java.io.InputStream stream = this.getClass().getResourceAsStream("/res/icon/guid.png");
            guidIcon = new javax.swing.ImageIcon(ImageIO.read(stream));
            stream.close();
            lb_GuidIcon.setIcon(guidIcon);
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
        }
    }

    private void initInfoView()
    {
        if (pl_InfoPane == null)
        {
            pl_InfoPane = new javax.swing.JPanel();
            pl_InfoPane.setLayout(new java.awt.FlowLayout());
        }

        cb_UserType = new javax.swing.JComboBox();

        lb_UserType = new javax.swing.JLabel();
        lb_UserType.setLabelFor(cb_UserType);

        tf_UserName = new javax.swing.JTextField(22);
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

        pf_UserKey0 = new javax.swing.JPasswordField(22);
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

        pf_UserKey1 = new javax.swing.JPasswordField(22);
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

        pf_UserKey2 = new javax.swing.JPasswordField(22);
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
            case ConsEnv.INT_SIGN_IN:// 用户登录
                hpg1.addComponent(lb_UserType, javax.swing.GroupLayout.Alignment.TRAILING);
                //hpg2.addComponent(tf_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                hpg2.addComponent(cb_UserType, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);

                hpg1.addComponent(lb_UserName, javax.swing.GroupLayout.Alignment.TRAILING);
                //hpg2.addComponent(tf_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                hpg2.addComponent(tf_UserName, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);

                hpg1.addComponent(lb_UserKey0, javax.swing.GroupLayout.Alignment.TRAILING);
                //hpg2.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                hpg2.addComponent(pf_UserKey0, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);
                break;
            case ConsEnv.INT_SIGN_LS:// 锁定屏幕
                hpg1.addComponent(lb_UserKey0, javax.swing.GroupLayout.Alignment.TRAILING);
                //hpg2.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                hpg2.addComponent(pf_UserKey0, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);
                break;
            case ConsEnv.INT_SIGN_RS:// 权限认证
            case ConsEnv.INT_SIGN_FP:// 口令找回
                hpg1.addComponent(lb_UserName, javax.swing.GroupLayout.Alignment.TRAILING);
                //hpg2.addComponent(tf_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                hpg2.addComponent(tf_UserName, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);

                hpg1.addComponent(lb_UserKey0, javax.swing.GroupLayout.Alignment.TRAILING);
                //hpg2.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                hpg2.addComponent(pf_UserKey0, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);
                break;
            case ConsEnv.INT_SIGN_UP:// 用户注册
            case ConsEnv.INT_SIGN_SU:// 从属用户
                hpg1.addComponent(lb_UserName, javax.swing.GroupLayout.Alignment.TRAILING);
                //hpg2.addComponent(tf_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                hpg2.addComponent(tf_UserName, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);

                hpg1.addComponent(lb_UserKey0, javax.swing.GroupLayout.Alignment.TRAILING);
                //hpg2.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                hpg2.addComponent(pf_UserKey0, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);

                hpg1.addComponent(lb_UserKey1, javax.swing.GroupLayout.Alignment.TRAILING);
                //hpg2.addComponent(pf_UserKey1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                hpg2.addComponent(pf_UserKey1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);
                break;
            case ConsEnv.INT_SIGN_PK:// 修改登录口令
            case ConsEnv.INT_SIGN_SK:// 修改安全口令
                hpg1.addComponent(lb_UserKey0, javax.swing.GroupLayout.Alignment.TRAILING);
                //hpg2.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                hpg2.addComponent(pf_UserKey0, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);

                hpg1.addComponent(lb_UserKey1, javax.swing.GroupLayout.Alignment.TRAILING);
                //hpg2.addComponent(pf_UserKey1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                hpg2.addComponent(pf_UserKey1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);

                hpg1.addComponent(lb_UserKey2, javax.swing.GroupLayout.Alignment.TRAILING);
                //hpg2.addComponent(pf_UserKey2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                hpg2.addComponent(pf_UserKey2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);
                break;
            case ConsEnv.INT_SIGN_CS:// 在线存储
                hpg1.addComponent(lb_UserType, javax.swing.GroupLayout.Alignment.TRAILING);
                //hpg2.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                hpg2.addComponent(cb_UserType, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);

                hpg1.addComponent(lb_UserName, javax.swing.GroupLayout.Alignment.TRAILING);
                //hpg2.addComponent(pf_UserKey1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                hpg2.addComponent(tf_UserName, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);

                hpg1.addComponent(lb_UserKey0, javax.swing.GroupLayout.Alignment.TRAILING);
                //hpg2.addComponent(pf_UserKey2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                hpg2.addComponent(pf_UserKey0, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);
                break;
            case ConsEnv.INT_SIGN_NW:
                break;
            default:
                break;
        }
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        //hsg.addContainerGap();
        hsg.addGroup(hpg1);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addGroup(hpg2);
        //hsg.addContainerGap();
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        //vsg.addContainerGap();
        switch (signType)
        {
            case ConsEnv.INT_SIGN_IN:// 用户登录
                javax.swing.GroupLayout.ParallelGroup vpg11 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg11.addComponent(lb_UserType);
                vpg11.addComponent(cb_UserType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg11);
                vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

                javax.swing.GroupLayout.ParallelGroup vpg12 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg12.addComponent(lb_UserName);
                vpg12.addComponent(tf_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg12);
                vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

                javax.swing.GroupLayout.ParallelGroup vpg13 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg13.addComponent(lb_UserKey0);
                vpg13.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg13);
                break;
            case ConsEnv.INT_SIGN_LS:// 锁定屏幕
                javax.swing.GroupLayout.ParallelGroup vpg21 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg21.addComponent(lb_UserKey0);
                vpg21.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg21);
                break;
            case ConsEnv.INT_SIGN_RS:// 权限认证
            case ConsEnv.INT_SIGN_FP:// 口令找回
                javax.swing.GroupLayout.ParallelGroup vpg31 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg31.addComponent(lb_UserName);
                vpg31.addComponent(tf_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg31);
                vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

                javax.swing.GroupLayout.ParallelGroup vpg32 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg32.addComponent(lb_UserKey0);
                vpg32.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg32);
                break;
            case ConsEnv.INT_SIGN_UP:// 用户注册
            case ConsEnv.INT_SIGN_SU:// 从属用户
                javax.swing.GroupLayout.ParallelGroup vpg41 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg41.addComponent(lb_UserName);
                vpg41.addComponent(tf_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg41);
                vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

                javax.swing.GroupLayout.ParallelGroup vpg42 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg42.addComponent(lb_UserKey0);
                vpg42.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg42);
                vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

                javax.swing.GroupLayout.ParallelGroup vpg43 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg43.addComponent(lb_UserKey1);
                vpg43.addComponent(pf_UserKey1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg43);
                break;
            case ConsEnv.INT_SIGN_PK:// 修改登录口令
            case ConsEnv.INT_SIGN_SK:// 修改安全口令
                javax.swing.GroupLayout.ParallelGroup vpg51 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg51.addComponent(lb_UserKey0);
                vpg51.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg51);
                vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

                javax.swing.GroupLayout.ParallelGroup vpg52 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg52.addComponent(lb_UserKey1);
                vpg52.addComponent(pf_UserKey1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg52);
                vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

                javax.swing.GroupLayout.ParallelGroup vpg53 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg53.addComponent(lb_UserKey2);
                vpg53.addComponent(pf_UserKey2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg53);
                break;
            case ConsEnv.INT_SIGN_CS:// 在线存储
                javax.swing.GroupLayout.ParallelGroup vpg61 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg61.addComponent(lb_UserType);
                vpg61.addComponent(cb_UserType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg61);
                vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

                javax.swing.GroupLayout.ParallelGroup vpg62 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg62.addComponent(lb_UserName);
                vpg62.addComponent(tf_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg62);
                vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

                javax.swing.GroupLayout.ParallelGroup vpg63 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
                vpg63.addComponent(lb_UserKey0);
                vpg63.addComponent(pf_UserKey0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                vsg.addGroup(vpg63);
                break;
            case ConsEnv.INT_SIGN_NW:
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
        switch (signType)
        {
            case ConsEnv.INT_SIGN_IN:
                Lang.setWText(lb_UserName, LangRes.P30FA301, "用户(@U)");
                Lang.setWText(lb_UserKey0, LangRes.P30FA302, "口令(@P)");

                Lang.setWText(lb_UsrLabel, LangRes.P30FA30E, "口令找回");
                Lang.setWTips(lb_UsrLabel, LangRes.P30FA30F, "找回您的登录口令");

                Lang.setWText(lb_KeyLabel, LangRes.P30FA310, "屏幕键盘");
                Lang.setWTips(lb_KeyLabel, LangRes.P30FA311, "使用屏幕键盘输入");

                Lang.setWText(bt_Confrm, LangRes.P30FA501, "登录(@S)");

                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(@C)");

                setTitle(Lang.getLang(LangRes.P30FA201, "用户登录"));

                lb_UsrLabel.setVisible(true);
                bt_Cancel.setVisible(true);
                break;
            case ConsEnv.INT_SIGN_LS:
                Lang.setWText(lb_UserKey0, LangRes.P30FA302, "口令(@P)");

                Lang.setWText(bt_Confrm, LangRes.P30FA501, "登录(@S)");

                lb_UsrLabel.setVisible(false);
                bt_Cancel.setVisible(false);
                break;
            case ConsEnv.INT_SIGN_RS:
                Lang.setWText(lb_UserName, LangRes.P30FA301, "用户(@U)");

                Lang.setWText(lb_UserKey0, LangRes.P30FA302, "口令(@P)");

                Lang.setWText(bt_Confrm, LangRes.P30FA501, "登录(@S)");

                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(@C)");

                setTitle(Lang.getLang(LangRes.P30FA202, "身份验证"));

                lb_UsrLabel.setVisible(false);
                bt_Cancel.setVisible(true);
                break;
            case ConsEnv.INT_SIGN_UP:
                Lang.setWText(lb_UserName, LangRes.P30FA301, "用户(@U)");

                Lang.setWText(lb_UserKey0, LangRes.P30FA302, "口令(@P)");

                Lang.setWText(lb_UserKey1, LangRes.P30FA303, "确认(@R)");

                Lang.setWText(lb_KeyLabel, LangRes.P30FA307, "数据升级");
                Lang.setWTips(lb_KeyLabel, LangRes.P30FA308, "从旧版本软件升级数据");

                Lang.setWText(bt_Confrm, LangRes.P30FA502, "注册(@S)");

                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(@C)");

                setTitle(Lang.getLang(LangRes.P30FA203, "用户注册"));

                lb_UsrLabel.setVisible(false);
                bt_Cancel.setVisible(true);
                break;
            case ConsEnv.INT_SIGN_PK:
                Lang.setWText(lb_UserKey0, LangRes.P30FA304, "现有口令(@O)");

                Lang.setWText(lb_UserKey1, LangRes.P30FA305, "修改口令(@N)");

                Lang.setWText(lb_UserKey2, LangRes.P30FA306, "口令确认(@R)");

                Lang.setWText(bt_Confrm, LangRes.P30FA503, "修改(@S)");

                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(@C)");

                setTitle(Lang.getLang(LangRes.P30FA205, "登录口令修改"));

                lb_UsrLabel.setVisible(false);
                bt_Cancel.setVisible(true);
                break;
            case ConsEnv.INT_SIGN_FP:
                Lang.setWText(lb_UserName, LangRes.P30FA30C, "登录用户(@U)");

                Lang.setWText(lb_UserKey0, LangRes.P30FA30D, "安全口令(@P)");

                Lang.setWText(bt_Confrm, LangRes.P30FA509, "找回(@F)");

                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(@C)");

                setTitle(Lang.getLang(LangRes.P30FA204, "口令找回"));

                lb_UsrLabel.setVisible(false);
                bt_Cancel.setVisible(true);
                break;
            case ConsEnv.INT_SIGN_SK:
                Lang.setWText(lb_UserKey0, LangRes.P30FA304, "现有口令(@O)");

                Lang.setWText(lb_UserKey1, LangRes.P30FA30D, "安全口令(@P)");

                Lang.setWText(lb_UserKey2, LangRes.P30FA306, "口令确认(@R)");

                Lang.setWText(bt_Confrm, LangRes.P30FA508, "设定(@S)");

                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(@C)");

                setTitle(Lang.getLang(LangRes.P30FA206, "安全口令修改"));

                lb_UsrLabel.setVisible(false);
                bt_Cancel.setVisible(true);
                break;
            case ConsEnv.INT_SIGN_SU:
                setTitle(Lang.getLang(LangRes.P30FA207, "添加从属用户"));

                lb_UsrLabel.setVisible(false);
                bt_Cancel.setVisible(true);
                break;
            case ConsEnv.INT_SIGN_CS:
                Lang.setWText(lb_UserType, LangRes.P30FA301, "服务器(@V)");

                Lang.setWText(lb_UserName, LangRes.P30FA301, "用户(@U)");

                Lang.setWText(lb_UserKey0, LangRes.P30FA302, "口令(@P)");

                Lang.setWText(bt_Confrm, LangRes.P30FA508, "设定(@S)");

                Lang.setWText(bt_Cancel, LangRes.P30FA504, "取消(@C)");

                setTitle(Lang.getLang(LangRes.P30FA20B, "配置邮件账户"));

                lb_UsrLabel.setVisible(false);
                bt_Cancel.setVisible(true);
                break;
            default:
                break;
        }

        lb_KeyLabel.setVisible(false);
        pl_SafePane.setVisible(false);
        return true;
    }

    public boolean initData()
    {
        this.errCount = 0;

        switch (signType)
        {
            case ConsEnv.INT_SIGN_IN:
                // 显示上次登录用户
                cb_UserType.addItem(new S1S1("mpwd", "高级模式"));
                cb_UserType.addItem(new S1S1("mwiz", "向导模式"));
                cb_UserType.addItem(new S1S1("mpad", "记事模式"));
                cb_UserType.setSelectedIndex(UserMdl.getAppMode());
                String name = userMdl.getCfg(ConsCfg.CFG_USER_LAST);
                if (com.magicpwd._util.Char.isValidate(name))
                {
                    tf_UserName.setText(name);
                }
                break;
            case ConsEnv.INT_SIGN_RS:
                tf_UserName.setText("");
                pf_UserKey0.setText("");
                tf_UserName.requestFocus();
                break;
            case ConsEnv.INT_SIGN_UP:
                break;
            case ConsEnv.INT_SIGN_PK:
                break;
            case ConsEnv.INT_SIGN_FP:
                break;
            case ConsEnv.INT_SIGN_SK:
                break;
            case ConsEnv.INT_SIGN_SU:
                break;
            case ConsEnv.INT_SIGN_CS:
                java.util.Properties prop = Connect.getMailCfg();
                cb_UserType.removeAllItems();
                java.util.Enumeration<?> names = prop.propertyNames();
                java.util.LinkedList<String> text = new java.util.LinkedList<String>();
                while (names.hasMoreElements())
                {
                    String t = names.nextElement() + "";
                    int i = t.lastIndexOf(".type");
                    if (i <= 0 || i + 5 != t.length())
                    {
                        continue;
                    }
                    t = t.substring(0, i);

                    // 排序
                    i = 0;
                    int j = text.size();
                    while (i < j)
                    {
                        if (t.compareTo(text.get(i)) < 0)
                        {
                            break;
                        }
                        i += 1;
                    }
                    text.add(i, t);
                    cb_UserType.insertItemAt(t, i);
                }

                break;
            default:
                break;
        }

        java.awt.Window window = frame != null ? frame : dialog;
        if (window == null)
        {
            return false;
        }

        if (jpng == null)
        {
            new Thread()
            {

                @Override
                public void run()
                {
                    jpng = new Jpng();
                    try
                    {
                        java.io.InputStream stream = Jpng.class.getResourceAsStream(ConsEnv.ICON_PATH + "wait.png");
                        jpng.readIcons(stream, 16, 16);
                        stream.close();
                        jpng.setIt(0);
                        jpng.setButton(bt_Confrm);
                    }
                    catch (Exception exp)
                    {
                        Logs.exception(exp);
                    }

                    if (UserMdl.getRunMode() != ConsEnv.RUN_MODE_DEV)
                    {
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
                            icon = new javax.swing.ImageIcon(ImageIO.read(stream));
                            stream.close();
                        }
                        catch (Exception ex)
                        {
                            Logs.exception(ex);
                            icon = null;
                        }
                        if (icon != null)
                        {
                            guidIcon = icon;
                            if (lb_GuidIcon != null)
                            {
                                final String tgi = "";
                                synchronized (tgi)
                                {
                                    lb_GuidIcon.setIcon(guidIcon);
                                }
                            }
                        }
                    }
                }
            }.start();
        }

        window.pack();
        if (!window.isVisible())
        {
            java.awt.Dimension windowsize = window.getSize();
            java.awt.Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            window.setLocation((screensize.width - windowsize.width) >> 1, (screensize.height - windowsize.height) >> 1);
            window.setVisible(true);
        }

        return true;
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

    public void setBackCall(IBackCall backCall)
    {
        this.backCall = backCall;
    }

    /**
     * @param evt
     */
    private void bt_CancelActionPerformed(java.awt.event.ActionEvent evt)
    {
        switch (signType)
        {
            case ConsEnv.INT_SIGN_IN:
            case ConsEnv.INT_SIGN_UP:
                System.exit(0);
                return;
            case ConsEnv.INT_SIGN_RS:
            case ConsEnv.INT_SIGN_PK:
            case ConsEnv.INT_SIGN_SK:
            case ConsEnv.INT_SIGN_SU:
            case ConsEnv.INT_SIGN_NW:
            case ConsEnv.INT_SIGN_CS:
                dispoze();
                break;
            case ConsEnv.INT_SIGN_FP:
                initView(ConsEnv.INT_SIGN_IN);
                initLang();
                initData();
                tf_UserName.requestFocus();
                break;
            default:
                break;
        }
        if (backCall != null)
        {
            backCall.callBack(null, null, "cancel");
        }
    }

    /**
     * @param evt
     */
    private void bt_ConfrmActionPerformed(java.awt.event.ActionEvent evt)
    {
        bt_Confrm.setEnabled(false);
        jpng.start();
        lb_UsrLabel.setEnabled(false);
        new Thread()
        {

            @Override
            public void run()
            {
                switch (signType)
                {
                    case ConsEnv.INT_SIGN_IN:
                        signIn();
                        break;
                    case ConsEnv.INT_SIGN_RS:
                        signRs();
                        break;
                    case ConsEnv.INT_SIGN_UP:
                        signUp();
                        break;
                    case ConsEnv.INT_SIGN_PK:
                        signPk();
                        break;
                    case ConsEnv.INT_SIGN_FP:
                        signFp();
                        break;
                    case ConsEnv.INT_SIGN_SK:
                        signSk();
                        break;
                    case ConsEnv.INT_SIGN_SU:
                        signSu();
                        break;
                    case ConsEnv.INT_SIGN_CS:
                        signCs();
                        break;
                    default:
                        break;
                }
                jpng.stop();
                bt_Confrm.setEnabled(true);
                lb_UsrLabel.setEnabled(true);
            }
        }.start();
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
        if (!lb_UsrLabel.isEnabled())
        {
            return;
        }

        initView(ConsEnv.INT_SIGN_FP);
        initLang();
        initData();

        tf_UserName.requestFocus();
    }

    private void lb_KeyLabelMouseReleased(java.awt.event.MouseEvent evt)
    {
        if (!lb_KeyLabel.isEnabled())
        {
            return;
        }

        if (signType == ConsEnv.INT_SIGN_IN)
        {
            signType = ConsEnv.INT_SIGN_FP;
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
        if (!com.magicpwd._util.Char.isValidate(name))
        {
            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
            tf_UserName.requestFocus();
            return;
        }
        String pwds = new String(pf_UserKey0.getPassword());
        if (!com.magicpwd._util.Char.isValidate(pwds))
        {
            Lang.showMesg(this, LangRes.P30FAA02, "请输入登录口令！");
            pf_UserKey0.requestFocus();
            return;
        }

        try
        {
            boolean b = userMdl.signIn(name, pwds);
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

        Object object = cb_UserType.getSelectedItem();
        if (object instanceof S1S1)
        {
            UserMdl.setAppMode(((S1S1) object).getK());
        }
        if (backCall != null)
        {
            backCall.callBack(null, null, ConsEnv.STR_SIGN_IN);
        }
        tf_UserName.setText("");
        pf_UserKey0.setText("");
        dispoze();
    }

    private void signRs()
    {
        String name = tf_UserName.getText();
        if (!com.magicpwd._util.Char.isValidate(name))
        {
            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
            tf_UserName.requestFocus();
            return;
        }
        String pwds = new String(pf_UserKey0.getPassword());
        if (!com.magicpwd._util.Char.isValidate(pwds))
        {
            Lang.showMesg(this, LangRes.P30FAA02, "请输入登录口令！");
            pf_UserKey0.requestFocus();
            return;
        }

        try
        {
            boolean b = userMdl.signIn(name, pwds);
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
        if (backCall != null)
        {
            backCall.callBack(null, null, ConsEnv.STR_SIGN_RS);
        }
        tf_UserName.setText("");
        pf_UserKey0.setText("");
    }

    private void signUp()
    {
        // 登录名称检测
        String un = tf_UserName.getText();
        if (!com.magicpwd._util.Char.isValidate(un))
        {
            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
            tf_UserName.requestFocus();
            return;
        }

        // 口令为空检测
        String p1 = new String(pf_UserKey0.getPassword());
        if (!com.magicpwd._util.Char.isValidate(p1))
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
            boolean b = userMdl.signUp(un, p1);
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

        if (backCall != null)
        {
            if (!backCall.callBack(null, null, ConsEnv.STR_SIGN_UP))
            {
                System.exit(0);
                return;
            }
        }
        dispoze();
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
        if (!com.magicpwd._util.Char.isValidate(p0))
        {
            Lang.showMesg(this, LangRes.P30FAA02, "请输入登录口令！");
            pf_UserKey0.requestFocus();
            return;
        }
        try
        {
            boolean b = userMdl.signPk(p0, p1);
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
        if (backCall != null)
        {
            backCall.callBack(null, null, ConsEnv.STR_SIGN_PK);
        }
        Lang.showMesg(this, LangRes.P30FAA0A, "登录口令修改成功，您可以使用新口令登录了！");
    }

    private void signFp()
    {
        String name = tf_UserName.getText();
        if (!com.magicpwd._util.Char.isValidate(name))
        {
            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
            tf_UserName.requestFocus();
            return;
        }
        String pwds = new String(pf_UserKey0.getPassword());
        if (!com.magicpwd._util.Char.isValidate(pwds))
        {
            Lang.showMesg(this, LangRes.P30FAA14, "请输入安全口令！");
            pf_UserKey0.requestFocus();
            return;
        }

        StringBuffer sb = new StringBuffer(pwds);
        try
        {
            boolean b = userMdl.signFp(name, sb);
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

        if (backCall != null)
        {
            backCall.callBack(null, null, ConsEnv.STR_SIGN_FP, sb.toString());
        }
        Lang.showMesg(null, LangRes.P30FAA18, "您的新口令是：{0}\n为了您的安全，请登录软件后尽快修改您的口令。", sb.toString());
        trayPtn.showViewPtn(ConsEnv.APP_MODE_MPWD);
    }

    /**
     * 
     */
    private void signSk()
    {
        String p0 = new String(pf_UserKey0.getPassword());
        if (!com.magicpwd._util.Char.isValidate(p0))
        {
            Lang.showMesg(this, LangRes.P30FAA02, "请输入登录口令！");
            pf_UserKey0.requestFocus();
            return;
        }

        String p1 = new String(pf_UserKey1.getPassword());
        if (!com.magicpwd._util.Char.isValidate(p1))
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

        try
        {
            boolean b = userMdl.signSk(p0, p1);
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
        if (backCall != null)
        {
            backCall.callBack(null, null, ConsEnv.STR_SIGN_SK);
        }
        pf_UserKey0.setText("");
        pf_UserKey1.setText("");
        pf_UserKey2.setText("");
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
        String ul = userMdl.getCfg(ConsCfg.CFG_USER, "");
        if (ul.indexOf(un + ',') >= 0)
        {
            Lang.showMesg(this, LangRes.P30FAA01, "请输入用户名称！");
        }

        if (backCall != null)
        {
            backCall.callBack(null, null, ConsEnv.STR_SIGN_SU);
        }
    }

    private void signCs()
    {
        // 登录名称检测
        String ut = (cb_UserType.getSelectedItem() + "").trim();
        if (ut.length() < 1)
        {
            Lang.showMesg(this, LangRes.P30FAA1D, "请选择邮件服务器！");
            cb_UserType.requestFocus();
            return;
        }

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
        if (backCall != null)
        {
            backCall.callBack(null, null, ConsEnv.STR_SIGN_CS, ut, un, new String(uc));
        }
    }
    private javax.swing.JLabel lb_GuidIcon;
    private javax.swing.JLabel lb_UsrLabel;
    private javax.swing.JLabel lb_KeyLabel;
    private javax.swing.JPanel pl_GuidPane;
    private javax.swing.JPanel pl_InfoPane;
    private javax.swing.JPanel pl_SafePane;
    private javax.swing.JLabel lb_UserType;
    private javax.swing.JComboBox cb_UserType;
    private javax.swing.JLabel lb_UserName;
    private javax.swing.JTextField tf_UserName;
    private javax.swing.JLabel lb_UserKey0;
    private javax.swing.JLabel lb_UserKey1;
    private javax.swing.JLabel lb_UserKey2;
    private javax.swing.JPasswordField pf_UserKey0;
    private javax.swing.JPasswordField pf_UserKey1;
    private javax.swing.JPasswordField pf_UserKey2;
    private javax.swing.JButton bt_Confrm;
    private javax.swing.JButton bt_Cancel;
}
