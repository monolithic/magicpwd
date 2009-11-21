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
import com.magicpwd._comn.Item;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IPropBean;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.d.DBA3000;
import com.magicpwd.m.UserMdl;

/**
 * @author shangwen.yao
 * 
 */
public class HistProp extends JPanel implements IPropBean
{
    private int indx;
    private Keys pwds;
    private S1S2 item;
    private List<S1S2> hist;
    private List<Item> tplt;
    private DefaultListModel lm_HistList;

    public HistProp()
    {
        pwds = new Keys();
    }

    public void initView()
    {
        javax.swing.JScrollPane sp1 = new javax.swing.JScrollPane();
        ls_HistList = new javax.swing.JList();
        bt_PickupCur = new javax.swing.JButton();
        bt_DeleteAll = new javax.swing.JButton();
        javax.swing.JScrollPane sp2 = new javax.swing.JScrollPane();
        ta_HistInfo = new javax.swing.JTextArea();
        bt_DeleteCur = new javax.swing.JButton();

        ls_HistList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ls_HistList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                ls_HistListValueChanged(evt);
            }
        });
        sp1.setViewportView(ls_HistList);

        bt_PickupCur.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_PickupCurActionPerformed(evt);
            }
        });

        bt_DeleteAll.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_DeleteAllActionPerformed(evt);
            }
        });

        ta_HistInfo.setLineWrap(true);
        sp2.setViewportView(ta_HistInfo);

        bt_DeleteCur.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_DeleteCurActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, 127,
                        javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                                javax.swing.GroupLayout.Alignment.TRAILING,
                                layout.createSequentialGroup().addComponent(bt_DeleteCur).addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(bt_DeleteAll)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bt_PickupCur)).addComponent(sp2))));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(sp1,
                javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addComponent(sp2, javax.swing.GroupLayout.DEFAULT_SIZE, 211,
                        Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(
                                bt_PickupCur).addComponent(bt_DeleteAll).addComponent(bt_DeleteCur))));
    }

    public void initLang()
    {
        Lang.setWText(bt_PickupCur, LangRes.P30F8507, "恢复");
        Lang.setWText(bt_DeleteCur, LangRes.P30F8508, "删除当前");
        Lang.setWText(bt_DeleteAll, LangRes.P30F8509, "删除所有");
    }

    public void initData()
    {
    }

    public void initData(S1S2 item)
    {
        this.item = item;
        if (lm_HistList != null)
        {
            lm_HistList.clear();
            hist.clear();
        }
        else
        {
            pwds = new Keys();
            hist = new ArrayList<S1S2>();
            tplt = new ArrayList<Item>();

            lm_HistList = new DefaultListModel();
            ls_HistList.setModel(lm_HistList);
        }

        DBA3000.selectHistData(item.getK(), hist);
        for (S1S2 temp : hist)
        {
            lm_HistList.addElement(temp);
        }
        ta_HistInfo.setText("");
        indx = -1;
    }

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
            DBA3000.selectHistData(item.getK(), pwds);
            UserMdl.getGridMdl().deCrypt(pwds, tplt);

            StringBuffer sb = new StringBuffer();
            idx = 0;
            String t = Lang.getLang(LangRes.P30FA101, "：");
            Item temp = tplt.get(idx++);
            sb.append(Lang.getLang(LangRes.P30FA102, "创建时间")).append(t).append(temp.getName()).append('\n');
            temp = tplt.get(idx++);
            sb.append(Lang.getLang(LangRes.P30FA102, "口令名称")).append(t).append(temp.getName()).append('\n');
            sb.append(Lang.getLang(LangRes.P30FA102, "关键搜索")).append(t).append(temp.getData()).append('\n');
            temp = tplt.get(idx++);
            sb.append(Lang.getLang(LangRes.P30FA102, "过期日期")).append(t).append(temp.getData()).append('\n');
            sb.append(Lang.getLang(LangRes.P30FA102, "过期提示")).append(t).append(temp.getName()).append('\n');
            for (int j = tplt.size(); idx < j; idx += 1)
            {
                temp = tplt.get(idx);
                sb.append(temp.getName()).append(t).append(temp.getData()).append('\n');
            }
            ta_HistInfo.setText(sb.toString());
            tplt.clear();
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FAA0F, "");
        }
    }

    private void bt_DeleteCurActionPerformed(java.awt.event.ActionEvent evt)
    {
        S1S2 temp = (S1S2) ls_HistList.getSelectedValue();
        if (temp == null)
        {
            return;
        }

        if (Lang.showFirm(this, LangRes.P30FAA11, "") != JOptionPane.YES_OPTION)
        {
            return;
        }
        DBA3000.deleteHistData(item.getK(), temp.getK());
        lm_HistList.removeElement(temp);
        ta_HistInfo.setText("");
    }

    private void bt_DeleteAllActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (lm_HistList.size() < 1)
        {
            return;
        }
        if (Lang.showFirm(this, LangRes.P30FAA12, "") != JOptionPane.YES_OPTION)
        {
            return;
        }
        DBA3000.deleteHistData(item.getK());
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

        if (Lang.showFirm(this, LangRes.P30FAA13, "") != JOptionPane.YES_OPTION)
        {
            return;
        }
        DBA3000.pickupHistData(item.getK(), temp.getK());
        lm_HistList.clear();
        hist.clear();

        DBA3000.selectHistData(item.getK(), hist);
        for (int i = 0, j = hist.size(); i < j; i += 1)
        {
            lm_HistList.addElement(hist.get(i));
        }
        indx = -1;
        ta_HistInfo.setText("");
    }

    private javax.swing.JList ls_HistList;
    private javax.swing.JTextArea ta_HistInfo;
    private javax.swing.JButton bt_DeleteCur;
    private javax.swing.JButton bt_DeleteAll;
    private javax.swing.JButton bt_PickupCur;
}
