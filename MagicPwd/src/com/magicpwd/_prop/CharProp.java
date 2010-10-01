/**
 * 
 */
package com.magicpwd._prop;

import javax.swing.DefaultComboBoxModel;

import com.magicpwd.r.ListCR;
import com.magicpwd._comn.Char;
import com.magicpwd._comp.IcoLabel;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IPropBean;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Lang;
import com.magicpwd.m.CharMdl;
import com.magicpwd.m.CoreMdl;

/**
 * @author Amon
 * 
 */
public class CharProp extends javax.swing.JPanel implements IPropBean
{

    /**
     * 当前编辑的字符空间
     */
    private Char charItem;
    private boolean isUpdate;
    private CoreMdl coreMdl;

    public CharProp(CoreMdl coreMdl)
    {
        this.coreMdl = coreMdl;
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
        if (cb_CharTplt.getItemCount() < 1)
        {
            CharMdl cm = coreMdl.getCharMdl();
            ls_CharList.setModel(cm);

            DefaultComboBoxModel cm_CharTplt = new DefaultComboBoxModel();
            Char c = new Char();
            c.setP30F2103("0");
            c.setP30F2104(Lang.getLang(LangRes.P30F1114, "请选择"));
            c.setP30F2105(Lang.getLang(LangRes.P30F1114, "请选择"));
            c.setP30F2106("");
            cm_CharTplt.addElement(c);
            for (Char item : cm.getCharSys())
            {
                cm_CharTplt.addElement(item);
            }
            cb_CharTplt.setModel(cm_CharTplt);
        }
    }

    @Override
    public void saveData()
    {
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
        bt_DropData.setIcon(Bean.readIcon(coreMdl.getUserCfg(), ConsEnv.DIR_FEEL + "file-delete.png"));
        bt_DropData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                dropDataActionPerformed(evt);
            }
        });

        bt_SaveData = new IcoLabel();
        bt_SaveData.setIcon(Bean.readIcon(coreMdl.getUserCfg(), ConsEnv.DIR_FEEL + "file-save.png"));
        bt_SaveData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveDataActionPerformed(evt);
            }
        });

        bt_ApndData = new IcoLabel();
        bt_ApndData.setIcon(Bean.readIcon(coreMdl.getUserCfg(), ConsEnv.DIR_FEEL + "file-new.png"));
        bt_ApndData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                apndDataActionPerformed(evt);
            }
        });
        bt_SortU = new IcoLabel();
        bt_SortU.setIcon(Bean.readIcon(coreMdl.getUserCfg(), ConsEnv.DIR_FEEL + "edit-move-up.png"));
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
        bt_SortD.setIcon(Bean.readIcon(coreMdl.getUserCfg(), ConsEnv.DIR_FEEL + "edit-move-down.png"));
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
        Lang.setWText(bt_ApndData, LangRes.P30F8509, "@N");
        Lang.setWTips(bt_ApndData, LangRes.P30F850A, "新增(Alt + N)");

        Lang.setWText(bt_SaveData, LangRes.P30F8507, "@S");
        Lang.setWTips(bt_SaveData, LangRes.P30F8508, "保存(Alt + S)");

        Lang.setWText(bt_DropData, LangRes.P30F850B, "@D");
        Lang.setWTips(bt_DropData, LangRes.P30F850C, "删除(Alt + D)");
    }

    private void initBaseLang()
    {
    }

    private void sortUActionPerformed(java.awt.event.ActionEvent evt)
    {
        int indx = ls_CharList.getSelectedIndex();
        if (indx < 1 || indx >= coreMdl.getCharMdl().getCharUsr().size())
        {
            return;
        }

        coreMdl.getCharMdl().changeItemAt(indx, -1);
        ls_CharList.setSelectedIndex(indx - 1);
    }

    private void sortDActionPerformed(java.awt.event.ActionEvent evt)
    {
        int indx = ls_CharList.getSelectedIndex();
        if (indx < 0 || indx >= coreMdl.getCharMdl().getCharUsr().size() - 1)
        {
            return;
        }

        coreMdl.getCharMdl().changeItemAt(indx, 1);
        ls_CharList.setSelectedIndex(indx + 1);
    }

    private void apndDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        charItem = new Char();
        cb_CharTplt.setSelectedIndex(0);
        ls_CharList.setSelectedIndex(-1);
        showInfo(charItem);
        isUpdate = false;
    }

    private void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        String name = tf_CharName.getText();
        if (!com.magicpwd._util.Char.isValidate(name))
        {
            Lang.showMesg(this, LangRes.P30F8A02, "空间名称不能为空！");
            tf_CharName.requestFocus();
            return;
        }

        String sets = ta_CharSets.getText();
        if (!com.magicpwd._util.Char.isValidate(sets))
        {
            Lang.showMesg(this, LangRes.P30F8A03, "空间内容不能为空！");
            ta_CharSets.requestFocus();
            return;
        }

        if (charItem == null)
        {
            charItem = new Char();
        }
        charItem.setP30F2104(name);
        charItem.setP30F2105(tf_CharTips.getText());
        charItem.setP30F2106(sets);
        if (isUpdate)
        {
            coreMdl.getCharMdl().updateItemAt(ls_CharList.getSelectedIndex(), charItem);
        }
        else
        {
            coreMdl.getCharMdl().appendItem(charItem);
        }

        charItem = new Char();
        cb_CharTplt.setSelectedIndex(0);
        showInfo(charItem);
        isUpdate = false;
    }

    private void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (charItem == null)
        {
            Lang.showMesg(this, LangRes.P30F8A04, "请选择您要删除的类别数据！");
            ls_CharList.requestFocus();
            return;
        }
        if (Lang.showFirm(this, LangRes.P30F8A09, "确认要删除此数据吗，此操作将不可恢复？") != javax.swing.JOptionPane.YES_OPTION)
        {
            return;
        }

        coreMdl.getCharMdl().removeItemAt(ls_CharList.getSelectedIndex());
        charItem = new Char();
        showInfo(charItem);
        isUpdate = false;
    }

    private void ls_CharListValueChanged(javax.swing.event.ListSelectionEvent evt)
    {
        charItem = (Char) ls_CharList.getSelectedValue();
        if (charItem == null)
        {
            return;
        }

        cb_CharTplt.setSelectedIndex(0);
        showInfo(charItem);
        isUpdate = true;
    }

    private void cb_CharTpltItemStateChanged(java.awt.event.ItemEvent evt)
    {
        Char item = (Char) cb_CharTplt.getSelectedItem();
        ta_CharSets.setText(item.getP30F2106());
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
