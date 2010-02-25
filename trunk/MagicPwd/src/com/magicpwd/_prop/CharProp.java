/**
 * 
 */
package com.magicpwd._prop;

import com.magicpwd._comn.Char;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

import com.magicpwd._comp.IcoLabel;
import com.magicpwd.r.ListCR;
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
public class CharProp extends JPanel implements IPropBean
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
        initInfoView();
        initSortView();
        initBaseView();
    }

    @Override
    public void initLang()
    {
        initInfoLang();
        initSortLang();
        initBaseLang();
    }

    @Override
    public void initData()
    {
        DefaultComboBoxModel cm_CharTplt = new DefaultComboBoxModel();
        Char c = new Char();
        c.setP30F2103("0");
        c.setP30F2104("请选择");
        c.setP30F2105("请选择");
        cm_CharTplt.addElement(new Char());
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
    public JPanel getPanel()
    {
        return this;
    }

    private void initInfoView()
    {
        pl_ItemInfo = new javax.swing.JPanel();

        lb_CharTplt = new javax.swing.JLabel();
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
        lb_CharName = new javax.swing.JLabel();
        tf_CharName = new javax.swing.JTextField();
        lb_CharTips = new javax.swing.JLabel();
        tf_CharTips = new javax.swing.JTextField();
        lb_CharSets = new javax.swing.JLabel();
        javax.swing.JScrollPane sp_ItemDesp = new javax.swing.JScrollPane();
        ta_CharSets = new javax.swing.JTextArea();
        bt_Delete = new javax.swing.JButton();
        bt_Update = new javax.swing.JButton();
        bt_Create = new javax.swing.JButton();

        tf_CharName.setColumns(16);

        tf_CharTips.setColumns(16);

        ta_CharSets.setLineWrap(true);
        sp_ItemDesp.setViewportView(ta_CharSets);

        bt_Delete.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_DeleteActionPerformed(evt);
            }
        });

        bt_Update.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_UpdateActionPerformed(evt);
            }
        });

        bt_Create.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_CreateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_ItemInfo);
        pl_ItemInfo.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                lb_CharTplt).addComponent(lb_CharName).addComponent(lb_CharTips)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(cb_CharTplt,
                                                                                                   javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                   javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                   javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(
                tf_CharName, javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(
                tf_CharTips, javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(102,
                                                                         Short.MAX_VALUE)).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap(67, Short.MAX_VALUE).addComponent(bt_Create).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
                bt_Update).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(bt_Delete)).addComponent(
                sp_ItemDesp,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                250,
                Short.MAX_VALUE).addGroup(
                layout.createSequentialGroup().addComponent(lb_CharSets).addContainerGap(208, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_CharTplt).addComponent(
                cb_CharTplt,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_CharName).addComponent(
                tf_CharName,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lb_CharTips).addComponent(
                tf_CharTips,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lb_CharSets).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(sp_ItemDesp,
                                                                                 javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                 129, Short.MAX_VALUE).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(bt_Delete).addComponent(
                bt_Update).addComponent(bt_Create))));
    }

    private void initSortView()
    {
        pl_SortItem = new javax.swing.JPanel();
        pl_SortItem.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        bt_SortUp = new IcoLabel();
        bt_SortUp.setIcon(Util.getIcon(ConsEnv.ICON_ITEM_PREV));
        bt_SortUp.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_SortUpActionPerformed(evt);
            }
        });
        pl_SortItem.add(bt_SortUp);

        bt_SortDown = new IcoLabel();
        bt_SortDown.setIcon(Util.getIcon(ConsEnv.ICON_ITEM_NEXT));
        bt_SortDown.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_SortDownActionPerformed(evt);
            }
        });
        pl_SortItem.add(bt_SortDown);
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
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(
                pl_SortItem, javax.swing.GroupLayout.Alignment.LEADING,
                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE).addComponent(sp_ItemList, javax.swing.GroupLayout.Alignment.LEADING,
                javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(pl_ItemInfo,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addComponent(sp_ItemList, javax.swing.GroupLayout.DEFAULT_SIZE, 174,
                Short.MAX_VALUE).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(pl_SortItem,
                javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(
                pl_ItemInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE));
    }

    private void initInfoLang()
    {
        Lang.setWText(lb_CharTplt, LangRes.P30F8321, "基本字符");
        Lang.setWText(lb_CharName, LangRes.P30F8322, "显示名称");
        Lang.setWText(lb_CharTips, LangRes.P30F8323, "提示信息");
        Lang.setWText(lb_CharSets, LangRes.P30F8324, "字符空间");
        Lang.setWText(bt_Update, LangRes.P30F8504, "保存(&S)");
        Lang.setWText(bt_Create, LangRes.P30F8505, "新增(&N)");
        Lang.setWText(bt_Delete, LangRes.P30F8506, "删除(&D)");
    }

    private void initSortLang()
    {
    }

    private void initBaseLang()
    {
    }

    private void bt_SortUpActionPerformed(java.awt.event.ActionEvent evt)
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

    private void bt_SortDownActionPerformed(java.awt.event.ActionEvent evt)
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

    private void bt_CreateActionPerformed(java.awt.event.ActionEvent evt)
    {
        charItem = new Char();
        isUpdate = false;

        cb_CharTplt.setSelectedIndex(0);
        showInfo(charItem);
    }

    private void bt_UpdateActionPerformed(java.awt.event.ActionEvent evt)
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

    private void bt_DeleteActionPerformed(java.awt.event.ActionEvent evt)
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
    private javax.swing.JPanel pl_ItemInfo;
    private javax.swing.JButton bt_Create;
    private javax.swing.JButton bt_Delete;
    private javax.swing.JButton bt_Update;
    private javax.swing.JLabel lb_CharTplt;
    private javax.swing.JComboBox cb_CharTplt;
    private javax.swing.JLabel lb_CharName;
    private javax.swing.JTextField tf_CharName;
    private javax.swing.JLabel lb_CharTips;
    private javax.swing.JTextField tf_CharTips;
    private javax.swing.JLabel lb_CharSets;
    private javax.swing.JTextArea ta_CharSets;
    private IcoLabel bt_SortUp;
    private IcoLabel bt_SortDown;
    private javax.swing.JList ls_CharList;
    private javax.swing.JPanel pl_SortItem;
}
