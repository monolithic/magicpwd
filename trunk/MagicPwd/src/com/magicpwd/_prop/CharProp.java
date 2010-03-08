/**
 * 
 */
package com.magicpwd._prop;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import com.magicpwd.r.ListCR;
import com.magicpwd._comn.Char;
import com.magicpwd._comp.IcoLabel;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IPropBean;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.d.DBA3000;
import com.magicpwd.m.UserMdl;

/**
 * @author Amon
 * 
 */
public class CharProp extends javax.swing.JPanel implements IPropBean
{

    private DefaultListModel lm_CharList;
    /**
     * 当前编辑的字符空间
     */
    private Char charItem;
    /**
     * 当前操作是否为更新
     */
    private boolean isUpdate;
    /**
     * 判断是否为默认字符空间，以确认在用户切换默认字符空间里，是否需要清空已输入文本
     */
    private String defText = "";

    public CharProp()
    {
    }

    @Override
    public void initView()
    {
        initSortView();
        initInfoView();
        initBaseView();
    }

    @Override
    public void initLang()
    {
        initSortLang();
        initInfoLang();
        initBaseLang();
    }

    @Override
    public void initData()
    {
        DefaultComboBoxModel cm_CharTplt = new DefaultComboBoxModel();
        Char c = new Char();
        c.setP30F2103("0");
        c.setP30F2104(Lang.getLang(LangRes.P30F1114, "请选择"));
        c.setP30F2105(Lang.getLang(LangRes.P30F1114, "请选择"));

        cm_CharTplt.addElement(c);
        for (Char item : UserMdl.getCharDef())
        {
            cm_CharTplt.addElement(item);
        }
        cb_CharTplt.setModel(cm_CharTplt);

        lm_CharList = new DefaultListModel();
        for (Char item : UserMdl.getCharMdl())
        {
            lm_CharList.addElement(item);
        }
        ls_CharList.setModel(lm_CharList);
    }

    @Override
    public javax.swing.JPanel getPanel()
    {
        return this;
    }

    private void initInfoView()
    {
        pl_ItemInfo = new javax.swing.JPanel();

        cb_CharTplt = new javax.swing.JComboBox();
        cb_CharTplt.setRenderer(new ListCR());
        cb_CharTplt.addItemListener(new java.awt.event.ItemListener()
        {

            @Override
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cb_CharTpltItemStateChanged(evt);
            }
        });
        lb_CharTplt = new javax.swing.JLabel();
        lb_CharTplt.setLabelFor(cb_CharTplt);

        tf_CharName = new javax.swing.JTextField(16);
        lb_CharName = new javax.swing.JLabel();
        lb_CharName.setLabelFor(tf_CharName);

        tf_CharTips = new javax.swing.JTextField(16);
        lb_CharTips = new javax.swing.JLabel();
        lb_CharTips.setLabelFor(tf_CharTips);

        ta_CharSets = new javax.swing.JTextArea();
        ta_CharSets.setLineWrap(true);
        javax.swing.JScrollPane sp_ItemDesp = new javax.swing.JScrollPane(ta_CharSets);
        lb_CharSets = new javax.swing.JLabel();
        lb_CharSets.setLabelFor(ta_CharSets);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_ItemInfo);
        pl_ItemInfo.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_CharSets);
        hpg1.addComponent(lb_CharTips);
        hpg1.addComponent(lb_CharName);
        hpg1.addComponent(lb_CharTplt);
        hpg1.addComponent(pl_ItemSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(cb_CharTplt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(tf_CharName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(tf_CharTips, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(sp_ItemDesp, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addGroup(hpg1);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addGroup(hpg2);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lb_CharTplt);
        vpg1.addComponent(cb_CharTplt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(lb_CharName);
        vpg2.addComponent(tf_CharName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg3.addComponent(lb_CharTips);
        vpg3.addComponent(tf_CharTips, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addComponent(lb_CharSets);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addComponent(pl_ItemSort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        javax.swing.GroupLayout.ParallelGroup vpg4 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg4.addGroup(vsg1);
        vpg4.addComponent(sp_ItemDesp, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup vsg2 = layout.createSequentialGroup();
        vsg2.addGroup(vpg1);
        vsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg2.addGroup(vpg2);
        vsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg2.addGroup(vpg3);
        vsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg2.addGroup(vpg4);
        layout.setVerticalGroup(vsg2);
    }

    private void initSortView()
    {
        pl_ItemSort = new javax.swing.JPanel();

        bt_DropData = new IcoLabel();
        bt_DropData.setIcon(Util.getIcon(ConsEnv.ICON_KEYS_DELT));
        bt_DropData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                dropDataActionPerformed(evt);
            }
        });

        bt_SaveData = new IcoLabel();
        bt_SaveData.setIcon(Util.getIcon(ConsEnv.ICON_KEYS_SAVE));
        bt_SaveData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveDataActionPerformed(evt);
            }
        });

        bt_ApndData = new IcoLabel();
        bt_ApndData.setIcon(Util.getIcon(ConsEnv.ICON_KEYS_APND));
        bt_ApndData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                apndDataActionPerformed(evt);
            }
        });
        bt_SortU = new IcoLabel();
        bt_SortU.setIcon(Util.getIcon(ConsEnv.ICON_ITEM_PREV));
        bt_SortU.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                sortUActionPerformed(evt);
            }
        });
        add(bt_SortU);

        bt_SortD = new IcoLabel();
        bt_SortD.setIcon(Util.getIcon(ConsEnv.ICON_ITEM_NEXT));
        bt_SortD.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                sortDActionPerformed(evt);
            }
        });
        add(bt_SortD);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_ItemSort);
        pl_ItemSort.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(bt_SortU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg1.addComponent(bt_SortD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(bt_ApndData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(bt_SaveData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(bt_DropData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addGroup(hpg1);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addGroup(hpg2);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addContainerGap(1, Short.MAX_VALUE);
        vsg1.addComponent(bt_ApndData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addComponent(bt_SaveData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addComponent(bt_DropData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg2 = layout.createSequentialGroup();
        vsg2.addContainerGap(1, Short.MAX_VALUE);
        vsg2.addComponent(bt_SortU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg2.addComponent(bt_SortD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg.addGroup(vsg1);
        vpg.addGroup(vsg2);
        layout.setVerticalGroup(vpg);
    }

    private void initBaseView()
    {
        javax.swing.JScrollPane sp_ItemList = new javax.swing.JScrollPane();
        ls_CharList = new javax.swing.JList();
        ls_CharList.setCellRenderer(new ListCR());
        ls_CharList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        sp_ItemList.setViewportView(ls_CharList);
        ls_CharList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {

            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                ls_CharListValueChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addComponent(sp_ItemList, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(pl_ItemInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setHorizontalGroup(hsg);
        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg.addComponent(sp_ItemList, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE);
        vpg.addComponent(pl_ItemInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE);
        layout.setVerticalGroup(vpg);
    }

    private void initInfoLang()
    {
        Lang.setWText(lb_CharTplt, LangRes.P30F8321, "基本字符");
        Lang.setWText(lb_CharName, LangRes.P30F8322, "显示名称");
        Lang.setWText(lb_CharTips, LangRes.P30F8323, "提示信息");
        Lang.setWText(lb_CharSets, LangRes.P30F8324, "字符空间");
    }

    public void initSortLang()
    {
//        Lang.setWText(bt_ApndData, LangRes.P30F8505, "新增(&N)");
//        Lang.setWText(bt_SaveData, LangRes.P30F8504, "保存(&S)");
//        Lang.setWText(bt_DropData, LangRes.P30F8506, "删除(&D)");
    }

    private void initBaseLang()
    {
    }

    private void sortUActionPerformed(java.awt.event.ActionEvent evt)
    {
        int indx = ls_CharList.getSelectedIndex();
        if (indx < 1 || indx >= lm_CharList.getSize())
        {
            return;
        }

        Char s = (Char) lm_CharList.remove(indx);
        lm_CharList.insertElementAt(s, indx - 1);

        DBA3000.updateCharData(s);
        DBA3000.updateCharData((Char) lm_CharList.get(indx));
        ls_CharList.setSelectedIndex(indx - 1);
        UserMdl.getCharMdl().add(indx - 1, UserMdl.getCharMdl().remove(indx));
        UserMdl.setCharUpd(true);
    }

    private void sortDActionPerformed(java.awt.event.ActionEvent evt)
    {
        int indx = ls_CharList.getSelectedIndex();
        if (indx < 0 || indx >= lm_CharList.getSize() - 1)
        {
            return;
        }

        Char s = (Char) lm_CharList.remove(indx);
        lm_CharList.insertElementAt(s, indx + 1);

        DBA3000.updateCharData(s);
        DBA3000.updateCharData((Char) lm_CharList.get(indx));
        ls_CharList.setSelectedIndex(indx + 1);
        UserMdl.getCharMdl().add(indx + 1, UserMdl.getCharMdl().remove(indx));
        UserMdl.setCharUpd(true);
    }

    private void apndDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        charItem = new Char();
        isUpdate = false;

        cb_CharTplt.setSelectedIndex(0);
        showInfo(charItem);
    }

    private void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        String name = tf_CharName.getText();
        if (!Util.isValidate(name))
        {
            Lang.showMesg(this, "", "空间名称不能为空！");
            tf_CharName.requestFocus();
            return;
        }

        String sets = ta_CharSets.getText();
        if (!Util.isValidate(sets))
        {
            Lang.showMesg(this, "", "空间内容不能为空！");
            ta_CharSets.requestFocus();
            return;
        }

        charItem.setP30F2104(name);
        charItem.setP30F2105(tf_CharTips.getText());
        charItem.setP30F2106(sets);
        charItem.setP30F2107(ta_CharSets.getText());
        DBA3000.updateCharData(charItem);

        if (isUpdate)
        {
            lm_CharList.set(ls_CharList.getSelectedIndex(), charItem);
            UserMdl.getCharMdl().set(ls_CharList.getSelectedIndex(), charItem);
        }
        else
        {
            lm_CharList.addElement(charItem);
            UserMdl.getCharMdl().add(charItem);
        }

        charItem = null;
        cb_CharTplt.setSelectedIndex(0);
        tf_CharName.setText("");
        tf_CharTips.setText("");
        ta_CharSets.setText("");
        UserMdl.setCharUpd(true);
    }

    private void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (charItem == null)
        {
            Lang.showMesg(this, "", "请选择您要删除的类别数据！");
            ls_CharList.requestFocus();
            return;
        }

        DBA3000.deleteCharData(charItem);
        int idx = ls_CharList.getSelectedIndex();
        lm_CharList.remove(idx);
        UserMdl.getCharMdl().remove(idx);
        UserMdl.setCharUpd(true);
    }

    private void ls_CharListValueChanged(javax.swing.event.ListSelectionEvent evt)
    {
        charItem = (Char) ls_CharList.getSelectedValue();
        if (charItem == null)
        {
            return;
        }

        cb_CharTplt.setSelectedIndex(0);
        defText = "";
    }

    private void cb_CharTpltItemStateChanged(java.awt.event.ItemEvent evt)
    {
        if (defText.equals(ta_CharSets.getText()) || !Util.isValidate(ta_CharSets.getText()))
        {
            Char item = (Char) cb_CharTplt.getSelectedItem();
            defText = item.getP30F2106();
            ta_CharSets.setText(defText);
        }
    }

    /**
     * 显示字符空间信息
     * @param item
     */
    private void showInfo(Char item)
    {
        tf_CharName.setText(item.getP30F2104());
        tf_CharTips.setText(item.getP30F2105());
        ta_CharSets.setText(item.getP30F2106());
        //ta_CharDesp.setText(item.getP30F2107());
    }
    private javax.swing.JList ls_CharList;
    private javax.swing.JPanel pl_ItemInfo;
    private javax.swing.JLabel lb_CharTplt;
    private javax.swing.JComboBox cb_CharTplt;
    private javax.swing.JLabel lb_CharName;
    private javax.swing.JTextField tf_CharName;
    private javax.swing.JLabel lb_CharTips;
    private javax.swing.JTextField tf_CharTips;
    private javax.swing.JLabel lb_CharSets;
    private javax.swing.JTextArea ta_CharSets;
    private javax.swing.JPanel pl_ItemSort;
    private IcoLabel bt_ApndData;
    private IcoLabel bt_SaveData;
    private IcoLabel bt_DropData;
    private IcoLabel bt_SortU;
    private IcoLabel bt_SortD;
}
