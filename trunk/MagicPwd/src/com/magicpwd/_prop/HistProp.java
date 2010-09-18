/**
 * 
 */
package com.magicpwd._prop;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.magicpwd._comn.Keys;
import com.magicpwd._comn.S1S2;
import com.magicpwd._comp.IcoLabel;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IEditItem;
import com.magicpwd._face.IPropBean;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.d.DBA3000;
import com.magicpwd.m.GridMdl;
import com.magicpwd.v.TrayPtn;

/**
 * @author shangwen.yao
 * 
 */
public class HistProp extends JPanel implements IPropBean
{

    private int indx;
    private Keys keys;
    private List<S1S2> hist;
    private List<IEditItem> ls_ItemList;
    private DefaultListModel lm_HistList;
    private GridMdl gridMdl;

    public HistProp(GridMdl gridMdl)
    {
        this.gridMdl = gridMdl;
        keys = new Keys();
    }

    @Override
    public void initView()
    {
        javax.swing.JScrollPane sp_HistList = new javax.swing.JScrollPane();
        ls_HistList = new javax.swing.JList();
        pl_ItemEdit = new javax.swing.JPanel();
        bt_PickupCur = new IcoLabel();
        bt_DeleteCur = new IcoLabel();
        bt_DeleteAll = new IcoLabel();
        javax.swing.JScrollPane sp_HistInfo = new javax.swing.JScrollPane();
        ta_HistInfo = new javax.swing.JTextArea();

        ls_HistList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ls_HistList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {

            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                ls_HistListValueChanged(evt);
            }
        });
        sp_HistList.setViewportView(ls_HistList);

        bt_PickupCur.setIcon(Util.getIcon(ConsEnv.ICON_HIST_PICK));
        bt_PickupCur.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_PickupCurActionPerformed(evt);
            }
        });

        bt_DeleteCur.setIcon(Util.getIcon(ConsEnv.ICON_PROP_DELT));
        bt_DeleteCur.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_DeleteCurActionPerformed(evt);
            }
        });

        bt_DeleteAll.setIcon(Util.getIcon(ConsEnv.ICON_HIST_DROP));
        bt_DeleteAll.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_DeleteAllActionPerformed(evt);
            }
        });

        ta_HistInfo.setLineWrap(true);
        sp_HistInfo.setViewportView(ta_HistInfo);

        javax.swing.GroupLayout editLayout = new javax.swing.GroupLayout(pl_ItemEdit);
        pl_ItemEdit.setLayout(editLayout);
        javax.swing.GroupLayout.ParallelGroup hpg = editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg.addComponent(bt_PickupCur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg.addComponent(bt_DeleteCur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg.addComponent(bt_DeleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        editLayout.setHorizontalGroup(hpg);

        javax.swing.GroupLayout.SequentialGroup vsg = editLayout.createSequentialGroup();
        vsg.addContainerGap(1, Short.MAX_VALUE);
        vsg.addComponent(bt_PickupCur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(bt_DeleteCur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(bt_DeleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        editLayout.setVerticalGroup(vsg);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(sp_HistList, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(pl_ItemEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(sp_HistInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(sp_HistList, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(pl_ItemEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(sp_HistInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE));
    }

    @Override
    public void initLang()
    {
        Lang.setWText(bt_PickupCur, LangRes.P30F850D, "&R");
        Lang.setWTips(bt_PickupCur, LangRes.P30F850E, "恢复(Alt + R)");

        Lang.setWText(bt_DeleteCur, LangRes.P30F850F, "&C");
        Lang.setWTips(bt_DeleteCur, LangRes.P30F8510, "删除当前(Alt + C)");

        Lang.setWText(bt_DeleteAll, LangRes.P30F8511, "&D");
        Lang.setWTips(bt_DeleteAll, LangRes.P30F8512, "删除所有(Alt + D)");
    }

    @Override
    public void initData()
    {
        if (lm_HistList != null)
        {
            lm_HistList.clear();
            hist.clear();
        }
        else
        {
            keys = new Keys();
            hist = new ArrayList<S1S2>();
            ls_ItemList = new ArrayList<IEditItem>();

            lm_HistList = new DefaultListModel();
            ls_HistList.setModel(lm_HistList);
        }

        DBA3000.selectHistData(gridMdl.getKeysHash(), hist);
        for (S1S2 temp : hist)
        {
            lm_HistList.addElement(temp);
        }
        ta_HistInfo.setText("");
        indx = -1;
    }

    @Override
    public void saveData()
    {
    }

    @Override
    public JPanel getPanel()
    {
        return this;
    }

    private void ls_HistListValueChanged(javax.swing.event.ListSelectionEvent evt)
    {
        int idx = ls_HistList.getSelectedIndex();
        if (idx < 0 || idx == indx)
        {
            return;
        }
        indx = idx;

        S1S2 item = (S1S2) ls_HistList.getSelectedValue();
        if (item == null)
        {
            return;
        }

        try
        {
            keys.setDefault();
            DBA3000.selectHistData(item.getK(), keys);
            gridMdl.deCrypt(keys, ls_ItemList);

            StringBuilder sb = new StringBuilder();
            idx = 0;
            String t = Lang.getLang(LangRes.P30FA101, "：");
            IEditItem temp = ls_ItemList.get(idx++);
            sb.append(Lang.getLang(LangRes.P30FA102, "创建时间")).append(t).append(temp.getName()).append('\n');
            temp = ls_ItemList.get(idx++);
            sb.append(Lang.getLang(LangRes.P30FA103, "口令名称")).append(t).append(temp.getName()).append('\n');
            sb.append(Lang.getLang(LangRes.P30FA104, "关键搜索")).append(t).append(temp.getData()).append('\n');
            temp = ls_ItemList.get(idx++);
            sb.append(Lang.getLang(LangRes.P30FA105, "过期日期")).append(t).append(temp.getData()).append('\n');
            sb.append(Lang.getLang(LangRes.P30FA106, "过期提示")).append(t).append(temp.getName()).append('\n');
            for (int j = ls_ItemList.size(); idx < j; idx += 1)
            {
                temp = ls_ItemList.get(idx);
                sb.append(temp.getName()).append(t).append(temp.getData()).append('\n');
            }
            ta_HistInfo.setText(sb.toString());
            ls_ItemList.clear();
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA0F, "");
        }
    }

    public void bt_DeleteCurActionPerformed(java.awt.event.ActionEvent evt)
    {
        S1S2 temp = (S1S2) ls_HistList.getSelectedValue();
        if (temp == null)
        {
            return;
        }

        if (Lang.showFirm(TrayPtn.getCurrForm(), LangRes.P30FAA11, "确认要删除选中的历史信息么，此操作不可恢复？") != JOptionPane.YES_OPTION)
        {
            return;
        }
        DBA3000.deleteHistData(gridMdl.getKeysHash(), temp.getK());
        lm_HistList.removeElement(temp);
        ta_HistInfo.setText("");
    }

    private void bt_DeleteAllActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (lm_HistList.size() < 1)
        {
            return;
        }
        if (Lang.showFirm(TrayPtn.getCurrForm(), LangRes.P30FAA12, "确认要删除此记录的所有历史信息吗，此操作将不可恢复？") != JOptionPane.YES_OPTION)
        {
            return;
        }
        DBA3000.deleteHistData(gridMdl.getKeysHash(), null);
        lm_HistList.clear();
        ta_HistInfo.setText("");
    }

    private void bt_PickupCurActionPerformed(java.awt.event.ActionEvent evt)
    {
        S1S2 temp = (S1S2) ls_HistList.getSelectedValue();
        if (temp == null)
        {
            return;
        }

        if (Lang.showFirm(TrayPtn.getCurrForm(), LangRes.P30FAA13, "为了确保您的数据安全，此操作仅复制一份选中的数据为最新数据，\n确认要执行此操作么？") != JOptionPane.YES_OPTION)
        {
            return;
        }
        DBA3000.pickupHistData(gridMdl.getKeysHash(), temp.getK(), 0);//TODO:数据恢复序列
        lm_HistList.clear();
        hist.clear();

        DBA3000.selectHistData(gridMdl.getKeysHash(), hist);
        for (int i = 0, j = hist.size(); i < j; i += 1)
        {
            lm_HistList.addElement(hist.get(i));
        }
        indx = -1;
        ta_HistInfo.setText("");
    }
    private javax.swing.JList ls_HistList;
    private javax.swing.JTextArea ta_HistInfo;
    private javax.swing.JPanel pl_ItemEdit;
    private IcoLabel bt_DeleteCur;
    private IcoLabel bt_DeleteAll;
    private IcoLabel bt_PickupCur;
}
