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
package com.magicpwd._bean.mexp;

import com.magicpwd.__i.IEditItem;
import com.magicpwd.__i.mexp.IMexpBean;
import com.magicpwd._comn.mpwd.Mgtd;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comp.WEditBox;
import com.magicpwd._comp.WTextBox;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd.d.db.DBA4000;
import com.magicpwd.v.MenuPtn;
import com.magicpwd.v.mexp.MexpPtn;

/**
 * 属性：过期提示
 * 键值：ConsEnv.INDX_TIME
 * @author Amon
 */
public class HintBean extends javax.swing.JPanel implements IMexpBean
{

    private WEditBox dataEdit;
    private IEditItem itemData;
    private MexpPtn mainPtn;
    private WTextBox dataBox;
    private String mgtdHash;
    private java.text.DateFormat format;

    public HintBean(MexpPtn mainPtn)
    {
        this.mainPtn = mainPtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.magicpwd._face.IEditBean#initView()
     */
    @Override
    public void initView()
    {
        dataEdit = new WEditBox(mainPtn, this, false);
        dataEdit.initView();
        dataEdit.setCopyButtonVisible(false);
        dataEdit.setDropButtonVisible(false);

        bl_PropName = new BtnLabel();
//        ib_PropName.setIcon(Bean.getNone());
        tf_PropName = new javax.swing.JTextField(14);
        tf_PropName.setEditable(false);
        lb_PropName = new javax.swing.JLabel();
        lb_PropName.setLabelFor(bl_PropName);

        pm_DateView = new javax.swing.JPopupMenu();
        mi_HalfHour = new javax.swing.JMenuItem();
        pm_DateView.add(mi_HalfHour);

        mi_FullHour = new javax.swing.JMenuItem();
        pm_DateView.add(mi_FullHour);

        pm_DateView.addSeparator();

        ta_PropData = new javax.swing.JTextArea();
        ta_PropData.setLineWrap(true);
        ta_PropData.setRows(3);
        dataBox = new WTextBox(ta_PropData);
        dataBox.initView();
        lb_PropData = new javax.swing.JLabel();
        lb_PropData.setLabelFor(ta_PropData);

        javax.swing.JScrollPane sp_PropData = new javax.swing.JScrollPane(ta_PropData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(bl_PropName, 21, 21, 21);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_PropName);
        hpg1.addComponent(lb_PropData);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addGroup(hsg1);
        hpg2.addComponent(sp_PropData, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addGroup(hpg1);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addGroup(hpg2);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(dataEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lb_PropName);
        vpg1.addComponent(tf_PropName);
        vpg1.addComponent(bl_PropName, 21, 21, 21);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addComponent(lb_PropData);
        vsg1.addContainerGap(49, Short.MAX_VALUE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg2.addGroup(vsg1);
        vpg2.addComponent(sp_PropData, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup vsg2 = layout.createSequentialGroup();
        vsg2.addGroup(vpg1);
        vsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg2.addGroup(vpg2);
        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg.addGroup(vsg2);
        vpg.addComponent(dataEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setVerticalGroup(vpg);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.magicpwd._face.IEditBean#initLang()
     */
    @Override
    public void initLang()
    {
        Lang.setWText(lb_PropName, LangRes.P30F1305, "时间");
        Lang.setWText(lb_PropData, LangRes.P30F1306, "提醒");

        Lang.setWText(bl_PropName, LangRes.P30F151B, "@O");
        Lang.setWTips(bl_PropName, LangRes.P30F151C, "提醒时间(Alt + O)");

        dataBox.initLang();
        dataEdit.initLang();
    }

    @Override
    public void initData()
    {
        format = new java.text.SimpleDateFormat(ConsEnv.HINT_DATE);
        bl_PropName.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bl_PropNameActionPerformed(evt);
            }
        });
        java.awt.event.ActionListener action = new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                mi_DateTimeActionPerformed(e);
            }
        };
        mi_HalfHour.addActionListener(action);
        mi_FullHour.addActionListener(action);
        mainPtn.getMenuPtn().getSubMenu("date-interval", pm_DateView, action);
        initMenu();

        dataBox.initData();
    }

    @Override
    public void showData(IEditItem item)
    {
        itemData = item;

        ta_PropData.setText(item.getData());
    }

    @Override
    public void requestFocus()
    {
        ta_PropData.requestFocus();
    }

    @Override
    public void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        String data = ta_PropData.getText();
        if (com.magicpwd._util.Char.isValidate(mgtdHash))
        {
            if (!com.magicpwd._util.Char.isValidate(data))
            {
                Lang.showMesg(mainPtn, LangRes.P30F7A36, "请输入过期提示！");
                ta_PropData.requestFocus();
                return;
            }
        }

        itemData.setName(mgtdHash);
        itemData.setData(data);

        mainPtn.updateSelectedItem();
    }

    @Override
    public void copyDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    private void initMenu()
    {
        java.util.List<Mgtd> mgtdList = DBA4000.readMgtdList();
        MenuPtn menuPtn = mainPtn.getMenuPtn();
        int idx = 0;
        int max = mgtdList.size();
        Mgtd mgtd;
        javax.swing.AbstractButton button;

        button = menuPtn.getButton("hint-fixtime");
        if (button != null)
        {
            button.removeAll();
            while (idx < max)
            {
                mgtd = mgtdList.get(idx);
                if (mgtd.getP30F0305() != ConsDat.MGTD_INTVAL_FIXTIME)
                {
                    break;
                }
                javax.swing.JCheckBoxMenuItem item = new javax.swing.JCheckBoxMenuItem();
                item.setActionCommand("key:" + mgtd.getP30F0309() + mgtd.getP30F030C());
                item.setText(mgtd.getP30F030C());
                button.add(item);
                idx += 1;
            }
        }
        button = menuPtn.getButton("hint-period");
        if (button != null)
        {
            button.removeAll();
            while (idx < max)
            {
                mgtd = mgtdList.get(idx);
                if (mgtd.getP30F0305() != ConsDat.MGTD_INTVAL_PERIOD)
                {
                    break;
                }
                javax.swing.JCheckBoxMenuItem item = new javax.swing.JCheckBoxMenuItem();
                item.setActionCommand("key:" + mgtd.getP30F0309() + mgtd.getP30F030C());
                item.setText(mgtd.getP30F030C());
                button.add(item);
                idx += 1;
            }
        }
        button = menuPtn.getButton("hint-intval");
        if (button != null)
        {
            button.removeAll();
            while (idx < max)
            {
                mgtd = mgtdList.get(idx);
                if (mgtd.getP30F0305() != ConsDat.MGTD_INTVAL_INTVAL)
                {
                    break;
                }
                javax.swing.JCheckBoxMenuItem item = new javax.swing.JCheckBoxMenuItem();
                item.setActionCommand("key:" + mgtd.getP30F0309() + mgtd.getP30F030C());
                item.setText(mgtd.getP30F030C());
                button.add(item);
                idx += 1;
            }
        }
        button = menuPtn.getButton("hint-special");
        if (button != null)
        {
            button.removeAll();
            while (idx < max)
            {
                mgtd = mgtdList.get(idx);
                if (mgtd.getP30F0305() != ConsDat.MGTD_INTVAL_SPECIAL)
                {
                    break;
                }
                javax.swing.JCheckBoxMenuItem item = new javax.swing.JCheckBoxMenuItem();
                item.setActionCommand("key:" + mgtd.getP30F0309() + mgtd.getP30F030C());
                item.setText(mgtd.getP30F030C());
                button.add(item);
                idx += 1;
            }
        }
        button = menuPtn.getButton("hint-formula");
        if (button != null)
        {
            button.removeAll();
            while (idx < max)
            {
                mgtd = mgtdList.get(idx);
                if (mgtd.getP30F0305() != ConsDat.MGTD_INTVAL_FORMULA)
                {
                    break;
                }
                javax.swing.JCheckBoxMenuItem item = new javax.swing.JCheckBoxMenuItem();
                item.setActionCommand("key:" + mgtd.getP30F0309() + mgtd.getP30F030C());
                item.setText(mgtd.getP30F030C());
                button.add(item);
                idx += 1;
            }
        }
    }

    private void bl_PropNameActionPerformed(java.awt.event.ActionEvent evt)
    {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.set(java.util.Calendar.SECOND, 0);
        c.set(java.util.Calendar.MILLISECOND, 0);

        java.util.Date d1;
        java.util.Date d2;
        String t1;
        String t2;
        if (c.get(java.util.Calendar.MINUTE) < 30)
        {
            c.set(java.util.Calendar.MINUTE, 30);
            d1 = c.getTime();
            t1 = c.get(java.util.Calendar.HOUR_OF_DAY) + ":30";
            c.add(java.util.Calendar.MINUTE, 30);
            d2 = c.getTime();
            t2 = c.get(java.util.Calendar.HOUR_OF_DAY) + ":00";
        }
        else
        {
            c.set(java.util.Calendar.MINUTE, 0);
            c.add(java.util.Calendar.HOUR_OF_DAY, 1);
            d1 = c.getTime();
            t1 = c.get(java.util.Calendar.HOUR_OF_DAY) + ":00";

            c.add(java.util.Calendar.MINUTE, 30);
            d2 = c.getTime();
            t2 = c.get(java.util.Calendar.HOUR_OF_DAY) + ":30";
        }

        Bean.setText(mi_HalfHour, t1);
        mi_HalfHour.setActionCommand("fix:" + format.format(d1));
        Bean.setText(mi_FullHour, t2);
        mi_FullHour.setActionCommand("fix:" + format.format(d2));
        pm_DateView.show(bl_PropName, 0, bl_PropName.getHeight());
    }

    private void mi_DateTimeActionPerformed(java.awt.event.ActionEvent e)
    {
        String cmd = e.getActionCommand();
        if (Char.isValidate(cmd))
        {
            return;
        }
        if (cmd.startsWith("fix:"))
        {
            if (!Char.isValidateDateTime(cmd))
            {
                return;
            }
            return;
        }
        if (cmd.startsWith("var:"))
        {
            java.util.Calendar c = java.util.Calendar.getInstance();
            c.set(java.util.Calendar.SECOND, 0);
            c.set(java.util.Calendar.MILLISECOND, 0);
            return;
        }
        if (cmd.startsWith("key:"))
        {
            if (cmd.length() > 15)
            {
                itemData.setData(cmd.substring(0, 15));
                tf_PropName.setText(cmd.substring(16));
            }
            return;
        }
    }
    private javax.swing.JLabel lb_PropName;
    private javax.swing.JTextField tf_PropName;
    private BtnLabel bl_PropName;
    private javax.swing.JLabel lb_PropData;
    private javax.swing.JTextArea ta_PropData;
    private javax.swing.JPopupMenu pm_DateView;
    private javax.swing.JMenuItem mi_HalfHour;
    private javax.swing.JMenuItem mi_FullHour;
}
