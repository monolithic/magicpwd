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
package com.magicpwd._bean.mwiz;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd.__i.IBackCall;
import com.magicpwd.__i.IEditItem;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comp.IcoLabel;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Lang;
import com.magicpwd.m.mwiz.KeysMdl;
import com.magicpwd.v.mwiz.NormPtn;
import com.magicpwd.x.IcoDialog;
import java.util.EventListener;
import javax.swing.AbstractButton;

/**
 * Application: MagicPwd
 * Author     : Amon
 * Encoding   : UTF-8
 * Created    : 2010-10-24 23:52:16
 * Website    : http://magicpwd.com/
 * Project    : http://magicpwd.googlecode.com/
 * Contact    : Amon@magicpwd.com
 * CopyRight  : Winshine.biz
 * Description:
 */
public class HeadBean extends javax.swing.JPanel implements IBackCall
{

    private NormPtn normPtn;
    private KeysMdl keysMdl;

    public HeadBean(NormPtn normPtn)
    {
        this.normPtn = normPtn;
    }

    public void initView()
    {
        lb_MetaName = new javax.swing.JLabel();
        tf_MetaName = new javax.swing.JTextField(14);
        lb_MetaData = new javax.swing.JLabel();
        ta_MetaData = new javax.swing.JTextArea();
        lb_HintName = new javax.swing.JLabel();
        tf_HintName = new javax.swing.JTextField(14);
        lb_HintDate = new javax.swing.JLabel();
        tf_HintDate = new javax.swing.JTextField(14);

        lb_MetaName.setLabelFor(tf_MetaName);

        ib_KeysIcon = new IcoLabel();
        ib_KeysIcon.setIcon(Bean.getNone());
        ib_KeysIcon.setOpaque(true);
        ib_KeysIcon.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ib_KeysIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ib_KeysIcon.setBackground(javax.swing.UIManager.getDefaults().getColor("TextField.background"));
        ib_KeysIcon.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        ib_KeysIcon.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ib_KeysIconActionPerformed(evt);
            }
        });

        lb_MetaData.setLabelFor(ta_MetaData);

        lb_HintName.setLabelFor(tf_HintName);

        lb_HintDate.setLabelFor(tf_HintDate);

        ib_HintDate = new BtnLabel();
        ib_HintDate.setIcon(normPtn.getUserMdl().readIcon(ConsEnv.FEEL_PATH + "hint.png"));
        ib_HintDate.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ib_HintDateActionPerformed(evt);
            }
        });

        pm_HintDate = new javax.swing.JPopupMenu();
        AMpwdAction action = new AMpwdAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mi_MenuItemActionPerformed(evt);
            }

            @Override
            public void doInit(Object object)
            {
            }

            @Override
            public void reInit(AbstractButton button)
            {
            }
        };
        mi_HalfHour = new javax.swing.JMenuItem();
        mi_HalfHour.addActionListener(action);
        pm_HintDate.add(mi_HalfHour);

        mi_FullHour = new javax.swing.JMenuItem();
        mi_FullHour.addActionListener(action);
        pm_HintDate.add(mi_FullHour);

        pm_HintDate.addSeparator();

        normPtn.getMenuPtn().getSubMenu("date-interval", pm_HintDate, action);

        javax.swing.JScrollPane jsp = new javax.swing.JScrollPane(ta_MetaData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addComponent(tf_MetaName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(ib_KeysIcon, 22, 22, 22);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addComponent(tf_HintDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg2.addComponent(ib_HintDate, 20, 20, 20);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_MetaName, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lb_MetaData, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lb_HintName, javax.swing.GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(lb_HintDate, javax.swing.GroupLayout.Alignment.TRAILING);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addGroup(hsg1);
        hpg2.addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE);
        hpg2.addGroup(hsg2);
        hpg2.addComponent(tf_HintName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup hsg3 = layout.createSequentialGroup();
        hsg3.addContainerGap();
        hsg3.addGroup(hpg1);
        hsg3.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg3.addGroup(hpg2);
        hsg3.addContainerGap(14, Short.MAX_VALUE);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(hsg3));

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lb_MetaName);
        vpg1.addComponent(tf_MetaName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg1.addComponent(ib_KeysIcon, 22, 22, 22);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg2.addComponent(lb_MetaData);
        vpg2.addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE);
        javax.swing.GroupLayout.ParallelGroup vpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg3.addComponent(lb_HintName);
        vpg3.addComponent(tf_HintName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg4 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg4.addComponent(lb_HintDate);
        vpg4.addComponent(tf_HintDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg4.addComponent(ib_HintDate, 20, 20, 20);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addContainerGap();
        vsg1.addGroup(vpg1);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg2);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg3);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg4);
        vsg1.addContainerGap(14, Short.MAX_VALUE);
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(vsg1));
    }

    public void initLang()
    {
        Bean.setText(lb_MetaName, Lang.getLang(LangRes.P30F1303, "标题"));

        Lang.setWText(ib_KeysIcon, LangRes.P30F131F, "&O");
        Lang.setWTips(ib_KeysIcon, LangRes.P30F1320, "点击选择徽标(Alt + L)");

        Bean.setText(lb_MetaData, Lang.getLang(LangRes.P30F1304, "搜索"));

        Lang.setWText(lb_HintName, LangRes.P30F1305, "提示");
        Lang.setWText(lb_HintDate, LangRes.P30F1306, "时间");

        Lang.setWText(ib_HintDate, LangRes.P30F151B, "&O");
        Lang.setWTips(ib_HintDate, LangRes.P30F151C, "提醒时间(Alt + T)");
    }

    public void initData()
    {
    }

    public final void showData(KeysMdl keysMdl)
    {
        this.keysMdl = keysMdl;

        IEditItem meta = keysMdl.getItemAt(ConsEnv.PWDS_HEAD_META);
        tf_MetaName.setText(meta.getName());
        ta_MetaData.setText(meta.getData());

        IEditItem logo = keysMdl.getItemAt(ConsEnv.PWDS_HEAD_LOGO);
        ib_KeysIcon.setIcon(Bean.getDataIcon(logo.getSpec(0)));

        IEditItem hint = keysMdl.getItemAt(ConsEnv.PWDS_HEAD_HINT);
        tf_HintName.setText(hint.getName());
        tf_HintDate.setText(hint.getData());
    }

    public boolean saveData()
    {
        String metaName = tf_MetaName.getText();
        if (!com.magicpwd._util.Char.isValidate(metaName))
        {
            Lang.showMesg(normPtn, LangRes.P30FAA1A, "记录标题不能为空!");
            tf_MetaName.requestFocus();
            return false;
        }
        if (keysMdl == null)
        {
            return false;
        }

        IEditItem meta = keysMdl.getItemAt(ConsEnv.PWDS_HEAD_META);
        meta.setName(metaName);
        meta.setData(ta_MetaData.getText());

        IEditItem hint = keysMdl.getItemAt(ConsEnv.PWDS_HEAD_HINT);
        String hintName = tf_HintName.getText();
        String hintDate = tf_HintDate.getText();
        if (com.magicpwd._util.Char.isValidate(hintDate))
        {
            if (!hint.setData(hintDate))
            {
                Lang.showMesg(normPtn, LangRes.P30F7A37, "您输入的日期格式无效，请重新输入！");
                tf_HintDate.requestFocus();
                return false;
            }
            tf_HintDate.setText(hintDate);

            if (!com.magicpwd._util.Char.isValidate(hintName))
            {
                Lang.showMesg(normPtn, LangRes.P30F7A36, "请输入过期提示！");
                tf_HintName.requestFocus();
                return false;
            }
        }
        hint.setName(hintName);
        hint.setData(hintDate);

        return true;
    }

    @Override
    public boolean callBack(Object sender, EventListener event, String... params)
    {
        if (params.length < 1)
        {
            return false;
        }

        IEditItem logo = keysMdl.getItemAt(ConsEnv.PWDS_HEAD_LOGO);

        String key = params[0];
        if ("0".equals(key))
        {
            ib_KeysIcon.setIcon(Bean.getNone());
            logo.setName(key);
            return true;
        }

        if (!com.magicpwd._util.Char.isValidateHash(key))
        {
            return false;
        }
        ib_KeysIcon.setIcon(Bean.getDataIcon(key));
        logo.setName(key);
        return true;
    }

    private void ib_KeysIconActionPerformed(java.awt.event.ActionEvent evt)
    {
        IcoDialog ico = new IcoDialog(normPtn, this);
        ico.initView();
        ico.initLang();
        ico.initData();
        ico.showData(keysMdl.getItemAt(ConsEnv.PWDS_HEAD_LOGO).getName());
        ico.setVisible(true);
    }

    private void ib_HintDateActionPerformed(java.awt.event.ActionEvent evt)
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

        java.text.DateFormat format = new java.text.SimpleDateFormat(ConsEnv.HINT_DATE);
        Bean.setText(mi_HalfHour, t1);
        mi_HalfHour.setActionCommand(format.format(d1));
        Bean.setText(mi_FullHour, t2);
        mi_FullHour.setActionCommand(format.format(d2));
        pm_HintDate.show(ib_HintDate, 0, ib_HintDate.getHeight());
    }

    private void mi_MenuItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        String cmd = evt.getActionCommand();
        if (!com.magicpwd._util.Char.isValidate(cmd))
        {
            return;
        }

        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(ConsEnv.HINT_DATE);
        if ("half".equalsIgnoreCase(cmd))
        {
            cal.add(java.util.Calendar.MINUTE, 30);
            tf_HintDate.setText(sdf.format(cal.getTime()));
            return;
        }
        if ("hour".equalsIgnoreCase(cmd))
        {
            cal.add(java.util.Calendar.HOUR_OF_DAY, 1);
            tf_HintDate.setText(sdf.format(cal.getTime()));
            return;
        }
        if ("day".equalsIgnoreCase(cmd))
        {
            cal.add(java.util.Calendar.DAY_OF_MONTH, 1);
            tf_HintDate.setText(sdf.format(cal.getTime()));
            return;
        }
        if ("week".equalsIgnoreCase(cmd))
        {
            cal.add(java.util.Calendar.WEEK_OF_MONTH, 1);
            tf_HintDate.setText(sdf.format(cal.getTime()));
            return;
        }
        if ("month".equalsIgnoreCase(cmd))
        {
            cal.add(java.util.Calendar.MONTH, 1);
            tf_HintDate.setText(sdf.format(cal.getTime()));
            return;
        }
        if ("year".equalsIgnoreCase(cmd))
        {
            cal.add(java.util.Calendar.YEAR, 1);
            tf_HintDate.setText(sdf.format(cal.getTime()));
            return;
        }
        tf_HintDate.setText(cmd);
    }
    private BtnLabel ib_HintDate;
    private IcoLabel ib_KeysIcon;
    private javax.swing.JLabel lb_HintDate;
    private javax.swing.JLabel lb_HintName;
    private javax.swing.JLabel lb_MetaData;
    private javax.swing.JLabel lb_MetaName;
    private javax.swing.JTextArea ta_MetaData;
    private javax.swing.JTextField tf_HintDate;
    private javax.swing.JTextField tf_HintName;
    private javax.swing.JTextField tf_MetaName;
    private javax.swing.JPopupMenu pm_HintDate;
    private javax.swing.JMenuItem mi_HalfHour;
    private javax.swing.JMenuItem mi_FullHour;
}
