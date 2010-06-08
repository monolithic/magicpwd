package com.magicpwd.x;

import com.magicpwd.MagicPwd;
import com.magicpwd._comn.S1S2;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IPropBean;
import com.magicpwd._prop.CharProp;
import com.magicpwd._prop.HistProp;
import com.magicpwd._prop.InfoProp;
import com.magicpwd._prop.JavaProp;
import com.magicpwd._prop.KindProp;
import com.magicpwd._prop.SKeyProp;
import com.magicpwd._prop.TpltProp;
import com.magicpwd._prop.USetProp;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.c.MenuEvt;
import com.magicpwd.m.UserMdl;
import com.magicpwd.r.ListCR;
import java.awt.Color;

/**
 * 软件设置对话框
 * @author Amon
 */
public class MdiDialog extends javax.swing.JDialog
{

    private static MdiDialog md_Dialog;
    private String lastPanel;
    private java.awt.CardLayout cl_CardLayout;
    private javax.swing.DefaultListModel lm_PropList;
    private java.util.HashMap<String, IPropBean> hm_PropList;

    private MdiDialog()
    {
        super(MagicPwd.getCurrForm());
        lm_PropList = new javax.swing.DefaultListModel();
        cl_CardLayout = new java.awt.CardLayout();
        setDefaultCloseOperation(MdiDialog.DISPOSE_ON_CLOSE);
    }

    public static MdiDialog getInstance(MenuEvt menuEvt)
    {
        if (md_Dialog == null)
        {
            md_Dialog = new MdiDialog();
            md_Dialog.initView();
            md_Dialog.initLang();
            md_Dialog.initData();
            if (menuEvt != null)
            {
                md_Dialog.addHideAction(menuEvt);
            }
        }
        return md_Dialog;
    }

    public void initView()
    {
        ls_PropList = new javax.swing.JList();
        ls_PropList.setModel(lm_PropList);
        ls_PropList.setCellRenderer(new ListCR(javax.swing.SwingConstants.CENTER));
        ls_PropList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {

            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                ls_PropListValueChanged(evt);
            }
        });
        javax.swing.JScrollPane sp_PropList = new javax.swing.JScrollPane();
        sp_PropList.setViewportView(ls_PropList);

        lb_HeadPanel = new javax.swing.JLabel()
        {

            @Override
            protected void paintComponent(java.awt.Graphics g)
            {
                java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
                java.awt.Dimension size = getSize();
                g2d.setPaint(new java.awt.GradientPaint(0f, 0f, Color.lightGray, size.width, size.height, Color.white));
                g2d.fillRect(0, 0, size.width, size.height);

                super.paintComponent(g);
            }
        };
        lb_HeadPanel.setIcon(Util.getNone());
        lb_HeadPanel.setFont(lb_HeadPanel.getFont().deriveFont(java.awt.Font.BOLD));

        pl_CardPanel = new javax.swing.JPanel();
        cl_CardLayout = new java.awt.CardLayout();
        pl_CardPanel.setLayout(cl_CardLayout);

        bt_Discard = new javax.swing.JButton();
        bt_Discard.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_DiscardActionPerformed(evt);
            }
        });
        bt_Confirm = new javax.swing.JButton();
        bt_Confirm.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ConfirmActionPerformed(evt);
            }
        });
        bt_Applied = new javax.swing.JButton();
        bt_Applied.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_AppliedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(pl_CardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg1.addComponent(lb_HeadPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addComponent(sp_PropList, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addGroup(hpg1);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addComponent(bt_Applied);
        hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg2.addComponent(bt_Confirm);
        hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg2.addComponent(bt_Discard);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addGroup(hsg1);
        hpg2.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hsg2);
        layout.setHorizontalGroup(layout.createSequentialGroup().addContainerGap().addGroup(hpg2).addContainerGap());

        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addComponent(lb_HeadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addComponent(pl_CardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        vpg1.addComponent(sp_PropList, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE);
        vpg1.addGroup(javax.swing.GroupLayout.Alignment.LEADING, vsg1);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(bt_Discard);
        vpg2.addComponent(bt_Confirm);
        vpg2.addComponent(bt_Applied);
        javax.swing.GroupLayout.SequentialGroup vsg2 = layout.createSequentialGroup();
        vsg2.addContainerGap();
        vsg2.addGroup(vpg1);
        vsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg2.addGroup(vpg2);
        vsg2.addContainerGap();
        layout.setVerticalGroup(vsg2);

        this.setIconImage(Util.getLogo());
        this.pack();
    }

    public void initLang()
    {
        Lang.setWText(bt_Applied, LangRes.P30FA50E, "应用(&A)");
        Lang.setWText(bt_Confirm, LangRes.P30FA50A, "确定(&O)");
        Lang.setWText(bt_Discard, LangRes.P30FA50B, "取消(&C)");
    }

    public void initData()
    {
        hm_PropList = new java.util.HashMap<String, IPropBean>();

        String t;

        t = Lang.getLang(LangRes.P30F1202, "常规设置");
        lm_PropList.addElement(new S1S2(ConsEnv.PROP_USET, t, t));
        USetProp up = new USetProp();
        up.initView();
        up.initLang();
        pl_CardPanel.add(ConsEnv.PROP_USET, up);
        hm_PropList.put(ConsEnv.PROP_USET, up);

        t = Lang.getLang(LangRes.P30F1203, "口令管理");
        lm_PropList.addElement(new S1S2(ConsEnv.PROP_CHAR, t, t));
        CharProp cp = new CharProp();
        cp.initView();
        cp.initLang();
        pl_CardPanel.add(ConsEnv.PROP_CHAR, cp);
        hm_PropList.put(ConsEnv.PROP_CHAR, cp);

        t = Lang.getLang(LangRes.P30F1204, "模板管理");
        lm_PropList.addElement(new S1S2(ConsEnv.PROP_TPLT, t, t));
        TpltProp tp = new TpltProp();
        tp.initView();
        tp.initLang();
        pl_CardPanel.add(ConsEnv.PROP_TPLT, tp);
        hm_PropList.put(ConsEnv.PROP_TPLT, tp);

        t = Lang.getLang(LangRes.P30F1205, "类别管理");
        lm_PropList.addElement(new S1S2(ConsEnv.PROP_KIND, t, t));
        KindProp kp = new KindProp();
        kp.initView();
        kp.initLang();
        pl_CardPanel.add(ConsEnv.PROP_KIND, kp);
        hm_PropList.put(ConsEnv.PROP_KIND, kp);

        t = Lang.getLang(LangRes.P30F1207, "历史查看");
        lm_PropList.addElement(new S1S2(ConsEnv.PROP_HIST, t, t));
        HistProp hp = new HistProp();
        hp.initView();
        hp.initLang();
        pl_CardPanel.add(ConsEnv.PROP_HIST, hp);
        hm_PropList.put(ConsEnv.PROP_HIST, hp);

        t = Lang.getLang(LangRes.P30F1206, "键盘快捷");
        lm_PropList.addElement(new S1S2(ConsEnv.PROP_SKEY, t, t));
        SKeyProp sp = new SKeyProp();
        sp.initView();
        sp.initLang();
        pl_CardPanel.add(ConsEnv.PROP_SKEY, sp);
        hm_PropList.put(ConsEnv.PROP_SKEY, sp);

        t = Lang.getLang(LangRes.P30F1209, "Java环境");
        lm_PropList.addElement(new S1S2(ConsEnv.PROP_JAVA, t, t));
        JavaProp jp = new JavaProp();
        jp.initView();
        jp.initLang();
        pl_CardPanel.add(ConsEnv.PROP_JAVA, jp);
        hm_PropList.put(ConsEnv.PROP_JAVA, jp);

        t = Lang.getLang(LangRes.P30F1208, "关于软件");
        lm_PropList.addElement(new S1S2(ConsEnv.PROP_INFO, t, t));
        InfoProp ip = new InfoProp();
        ip.initView();
        ip.initLang();
        pl_CardPanel.add(ConsEnv.PROP_INFO, ip);
        hm_PropList.put(ConsEnv.PROP_INFO, ip);
    }

    /**
     * 显示对应的面板
     * @param panelKey 要显示的面板键值
     * @param showList 是否显示导航列表
     */
    public void showProp(String panelKey, boolean showList)
    {
        if (!Util.isValidate(panelKey))
        {
            return;
        }

        int idx = 0;
        for (int i = 0, j = lm_PropList.getSize(); i < j; i += 1)
        {
            if (lm_PropList.get(i).equals(panelKey))
            {
                idx = i;
                break;
            }
        }

        cl_CardLayout.show(pl_CardPanel, panelKey);
        ls_PropList.setSelectedIndex(idx);
        hm_PropList.get(panelKey).initData();

        if (lastPanel != null)
        {
            hm_PropList.get(lastPanel).saveData();
        }
        lastPanel = panelKey;

        if (!md_Dialog.isVisible())
        {
            Util.centerForm(this, MagicPwd.getCurrForm());
            md_Dialog.setVisible(true);
        }
    }

    private void addHideAction(MenuEvt menuEvt)
    {
        Util.addHideAction(pl_CardPanel.getActionMap(), pl_CardPanel.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW), menuEvt);
    }

    private void ls_PropListValueChanged(javax.swing.event.ListSelectionEvent evt)
    {
        Object obj = ls_PropList.getSelectedValue();
        if (!(obj instanceof S1S2))
        {
            return;
        }

        S1S2 kvItem = (S1S2) obj;
        cl_CardLayout.show(pl_CardPanel, kvItem.getK());
        lb_HeadPanel.setText(kvItem.getV());
        setTitle(kvItem.getV());
        hm_PropList.get(kvItem.getK()).initData();
        if (lastPanel != null)
        {
            hm_PropList.get(lastPanel).saveData();
        }
        lastPanel = kvItem.getK();
    }

    private void bt_AppliedActionPerformed(java.awt.event.ActionEvent evt)
    {
        hm_PropList.get(lastPanel).saveData();
    }

    private void bt_ConfirmActionPerformed(java.awt.event.ActionEvent evt)
    {
        setVisible(false);
        dispose();
        UserMdl.getUserCfg().saveCfg();
    }

    private void bt_DiscardActionPerformed(java.awt.event.ActionEvent evt)
    {
        setVisible(false);
        dispose();
    }
    private javax.swing.JList ls_PropList;
    private javax.swing.JPanel pl_CardPanel;
    private javax.swing.JButton bt_Applied;
    private javax.swing.JButton bt_Confirm;
    private javax.swing.JButton bt_Discard;
    private javax.swing.JLabel lb_HeadPanel;
}
