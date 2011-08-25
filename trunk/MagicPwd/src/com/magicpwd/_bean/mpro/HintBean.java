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
package com.magicpwd._bean.mpro;

import com.magicpwd.__i.IEditItem;
import com.magicpwd.__i.mpro.IMexpBean;
import com.magicpwd._comn.item.HintItem;
import com.magicpwd._comn.mpwd.Hint;
import com.magicpwd._comn.mpwd.Mgtd;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comp.WEditBox;
import com.magicpwd._comp.WTextBox;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.Date;
import com.magicpwd._util.Lang;
import com.magicpwd.d.db.DBA4000;
import com.magicpwd.v.app.MenuPtn;
import com.magicpwd.v.app.mpro.MproPtn;
import com.magicpwd.x.app.mgtd.MgtdDlg;

/**
 * 属性：过期提示
 * 键值：ConsEnv.INDX_TIME
 * @author Amon
 */
public class HintBean extends javax.swing.JPanel implements IMexpBean
{

    private WEditBox dataEdit;
    private HintItem itemData;
    private MproPtn mexpPtn;
    private WTextBox dataBox;
    private int mgtdType;
    private int mgtdData;
    private int mgtdUnit;
    private String mgtdHash;
    private java.util.Calendar mgtdCal;
    private java.text.DateFormat format;

    public HintBean(MproPtn mexpPtn)
    {
        this.mexpPtn = mexpPtn;
    }

    @Override
    public void initView()
    {
        dataEdit = new WEditBox(mexpPtn, this, false);
        dataEdit.initView();
        dataEdit.setCopyButtonVisible(false);
        dataEdit.setDropButtonVisible(false);

        blPropName = new BtnLabel();
//        blPropName.setIcon(Bean.getNone());
        lbPropName = new javax.swing.JLabel();

        pmDateView = new javax.swing.JPopupMenu();
        miHalfHour = new javax.swing.JMenuItem();
        pmDateView.add(miHalfHour);

        miFullHour = new javax.swing.JMenuItem();
        pmDateView.add(miFullHour);

        miEditMgtd = new javax.swing.JMenuItem();

        pmDateView.addSeparator();

        taPropData = new javax.swing.JTextArea();
        taPropData.setLineWrap(true);
        taPropData.setRows(3);
        dataBox = new WTextBox(taPropData);
        dataBox.initView();
        lbPropData = new javax.swing.JLabel();
        lbPropData.setLabelFor(taPropData);

        javax.swing.JScrollPane sp_PropData = new javax.swing.JScrollPane(taPropData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lbPropName);
        hpg1.addComponent(lbPropData);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(blPropName, 21, 21, 21);
        hpg2.addComponent(sp_PropData, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addGroup(hpg1);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addGroup(hpg2);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(dataEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER);
        vpg1.addComponent(lbPropName);
        vpg1.addComponent(blPropName, 21, 21, 21);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addComponent(lbPropData);
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
        Lang.setWText(lbPropName, LangRes.P30F1305, "时间");
        Lang.setWText(lbPropData, LangRes.P30F1306, "提醒");

        Lang.setWText(blPropName, LangRes.P30F151B, "@O");
        Lang.setWTips(blPropName, LangRes.P30F151C, "提醒时间(Alt + O)");

        Lang.setWText(miEditMgtd, null, "高级管理(@M)");
        Lang.setWTips(miEditMgtd, null, "高级管理");

        dataBox.initLang();
        dataEdit.initLang();
    }

    @Override
    public void initData()
    {
        format = new java.text.SimpleDateFormat(ConsEnv.HINT_DATE);
        blPropName.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                blPropNameActionPerformed(evt);
            }
        });
        java.awt.event.ActionListener action = new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                miDateTimeActionPerformed(e);
            }
        };
        miHalfHour.addActionListener(action);
        miFullHour.addActionListener(action);
        mexpPtn.getMenuPtn().getSubMenu("date-remind", pmDateView, action);
        if (initMgtdMenu())
        {
            pmDateView.addSeparator();
        }

        miEditMgtd.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                miEditMgtdActionPerformed(e);
            }
        });
        pmDateView.add(miEditMgtd);

        dataBox.initData();
    }

    @Override
    public void showData(IEditItem item)
    {
        itemData = (HintItem) item;

        taPropData.setText(item.getName());
    }

    @Override
    public void requestFocus()
    {
        taPropData.requestFocus();
    }

    @Override
    public void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (mgtdType > 0)
        {
            Mgtd mgtd = itemData.getMgtd();
            if (mgtd == null)
            {
                mgtd = new Mgtd();
                mgtd.setP30F0301(0);
                mgtd.setP30F0302(ConsDat.MGTD_TYPE_DATETIME);
                mgtd.setP30F0303(ConsDat.MGTD_STATUS_INIT);
                mgtd.setP30F0304(0);
                mgtd.setP30F0305(mgtdType);
                mgtd.setP30F0306(ConsDat.MGTD_METHOD_NOTE);
                mgtd.setP30F0307(0);
                mgtd.setP30F0308(0);
                switch (mgtdType)
                {
                    case ConsDat.MGTD_INTVAL_FIXTIME:
                        mgtd.setP30F030C("定时提醒");
                        break;
                    case ConsDat.MGTD_INTVAL_PERIOD:
                        mgtd.setP30F030C("周期提醒");
                        break;
                    case ConsDat.MGTD_INTVAL_INTVAL:
                        mgtd.setP30F030C("间隔提醒");
                        break;
                    case ConsDat.MGTD_INTVAL_SPECIAL:
                        mgtd.setP30F030C("特殊提醒");
                        break;
                    case ConsDat.MGTD_INTVAL_FORMULA:
                        mgtd.setP30F030C("公式提醒");
                        break;
                    default:
                        mgtd.setP30F030C("未知");
                }
                mgtd.setP30F030D(System.currentTimeMillis());
                mgtd.setP30F030E(0L);
                mgtd.setP30F030F(0L);
                mgtd.setP30F0312(ConsDat.MGTD_UNIT_MINUTE);
                mgtd.setP30F0313(5);
                mgtd.setP30F0314(itemData.getName());
                itemData.setMgtd(mgtd);
            }

            java.util.ArrayList<Hint> list = new java.util.ArrayList<Hint>(1);
            Hint hint = new Hint();
            hint.setP30F0403(mgtdCal.getTimeInMillis());
            hint.setP30F0404(mgtdUnit);
            hint.setP30F0405(mgtdData);
            hint.setP30F0406("");
            list.add(hint);
            mgtd.setHintList(list);

            itemData.setMgtd(mgtd);
        }

        itemData.setName(taPropData.getText());

        mexpPtn.updateSelectedItem();
    }

    @Override
    public void copyDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    private boolean initMgtdMenu()
    {
        java.util.List<Mgtd> mgtdList = DBA4000.readMgtdList(mexpPtn.getUserMdl());
        java.awt.event.ActionListener al = new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                miDateTimeActionPerformed(e);
            }
        };
        MenuPtn menuPtn = mexpPtn.getMenuPtn();
        boolean tmp = false;
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
                item.setText(mgtd.getP30F030C());
                item.setActionCommand("key:" + mgtd.getP30F0309() + mgtd.getP30F030C());
                item.addActionListener(al);
                button.add(item);
                idx += 1;
            }
            tmp = true;
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
                item.setText(mgtd.getP30F030C());
                item.setActionCommand("key:" + mgtd.getP30F0309() + mgtd.getP30F030C());
                item.addActionListener(al);
                button.add(item);
                idx += 1;
            }
            tmp = true;
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
                item.setText(mgtd.getP30F030C());
                item.setActionCommand("key:" + mgtd.getP30F0309() + mgtd.getP30F030C());
                item.addActionListener(al);
                button.add(item);
                idx += 1;
            }
            tmp = true;
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
                item.setText(mgtd.getP30F030C());
                item.setActionCommand("key:" + mgtd.getP30F0309() + mgtd.getP30F030C());
                item.addActionListener(al);
                button.add(item);
                idx += 1;
            }
            tmp = true;
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
                item.setText(mgtd.getP30F030C());
                item.setActionCommand("key:" + mgtd.getP30F0309() + mgtd.getP30F030C());
                item.addActionListener(al);
                button.add(item);
                idx += 1;
            }
            tmp = true;
        }

        return tmp;
    }

    private void blPropNameActionPerformed(java.awt.event.ActionEvent evt)
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

        Bean.setText(miHalfHour, t1);
        miHalfHour.setActionCommand("fix:" + format.format(d1));
        Bean.setText(miFullHour, t2);
        miFullHour.setActionCommand("fix:" + format.format(d2));
        if (mexpPtn.getUserMdl().isGtdTemplateUpdated())
        {
            initMgtdMenu();
            mexpPtn.getUserMdl().setGtdTemplateUpdated(false);
        }
        pmDateView.show(blPropName, 0, blPropName.getHeight());
    }

    private void miDateTimeActionPerformed(java.awt.event.ActionEvent e)
    {
        String cmd = e.getActionCommand();
        if (!Char.isValidate(cmd))
        {
            return;
        }
        // 定时提醒
        if (cmd.startsWith("fix:"))
        {
            cmd = cmd.substring(4);
            mgtdCal = Date.processFix(cmd);
            if (mgtdCal == null)
            {
                return;
            }
            taPropData.setText("定时提醒：" + cmd);
            mgtdHash = null;
            mgtdType = ConsDat.MGTD_INTVAL_FIXTIME;
            mgtdData = 0;
            mgtdUnit = 0;
            return;
        }
        // 延后提醒
        if (cmd.startsWith("var:"))
        {
            cmd = cmd.substring(4);
            mgtdCal = Date.processVar(cmd);
            if (mgtdCal == null)
            {
                return;
            }
            taPropData.setText("定时提醒：" + format.format(mgtdCal.getTime()));
            mgtdHash = null;
            mgtdType = ConsDat.MGTD_INTVAL_FIXTIME;
            mgtdData = 0;
            mgtdUnit = 0;
            return;
        }
        // 周期提醒
        if (cmd.startsWith("per:"))
        {
            cmd = cmd.substring(4);
            java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d+").matcher(cmd);
            if (!matcher.find())
            {
                return;
            }
            taPropData.setText("定制的周期提醒！");
            mgtdHash = null;
            mgtdType = ConsDat.MGTD_INTVAL_PERIOD;
            mgtdData = Integer.parseInt(matcher.group());

            if (cmd.endsWith("second"))
            {
                mgtdUnit = ConsDat.MGTD_UNIT_SECOND;
            }
            if (cmd.endsWith("minute"))
            {
                mgtdUnit = ConsDat.MGTD_UNIT_MINUTE;
            }
            if (cmd.endsWith("hour"))
            {
                mgtdUnit = ConsDat.MGTD_UNIT_HOUR;
            }
            if (cmd.endsWith("day"))
            {
                mgtdUnit = ConsDat.MGTD_UNIT_DAY;
            }
            if (cmd.endsWith("week"))
            {
                mgtdUnit = ConsDat.MGTD_UNIT_WEEK;
            }
            if (cmd.endsWith("month"))
            {
                mgtdUnit = ConsDat.MGTD_UNIT_MONTH;
            }
            if (cmd.endsWith("year"))
            {
                mgtdUnit = ConsDat.MGTD_UNIT_YEAR;
            }
            return;
        }
        // 间隔提醒
        if (cmd.startsWith("int:"))
        {
            cmd = cmd.substring(4);
            java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d+").matcher(cmd);
            if (!matcher.find())
            {
                return;
            }
            taPropData.setText("定制的间隔提醒！");
            mgtdHash = null;
            mgtdType = ConsDat.MGTD_INTVAL_INTVAL;
            mgtdData = Integer.parseInt(matcher.group());

            if (cmd.endsWith("second"))
            {
                mgtdUnit = ConsDat.MGTD_UNIT_SECOND;
            }
            if (cmd.endsWith("minute"))
            {
                mgtdUnit = ConsDat.MGTD_UNIT_MINUTE;
            }
            if (cmd.endsWith("hour"))
            {
                mgtdUnit = ConsDat.MGTD_UNIT_HOUR;
            }
            if (cmd.endsWith("day"))
            {
                mgtdUnit = ConsDat.MGTD_UNIT_DAY;
            }
            if (cmd.endsWith("week"))
            {
                mgtdUnit = ConsDat.MGTD_UNIT_WEEK;
            }
            if (cmd.endsWith("month"))
            {
                mgtdUnit = ConsDat.MGTD_UNIT_MONTH;
            }
            if (cmd.endsWith("year"))
            {
                mgtdUnit = ConsDat.MGTD_UNIT_YEAR;
            }
            return;
        }
        // 任务提醒
        if (cmd.startsWith("key:"))
        {
            cmd = cmd.substring(4);
            if (cmd.length() < 16)
            {
                return;
            }
            taPropData.setText(cmd.substring(16));
            mgtdHash = cmd.substring(0, 16);
            mgtdType = 0;
            return;
        }
    }

    private void miEditMgtdActionPerformed(java.awt.event.ActionEvent e)
    {
        Mgtd mgtd = DBA4000.readMgtd(mexpPtn.getUserMdl(), itemData.getData());
        if (mgtd == null)
        {
            return;
        }

        MgtdDlg mgtdDlg = new MgtdDlg(mexpPtn, true);
        mgtdDlg.setBackCall(null);
        mgtdDlg.initView();
        mgtdDlg.initLang();
        mgtdDlg.initData(mgtd);
    }
    private javax.swing.JLabel lbPropName;
    private BtnLabel blPropName;
    private javax.swing.JLabel lbPropData;
    private javax.swing.JTextArea taPropData;
    private javax.swing.JPopupMenu pmDateView;
    private javax.swing.JMenuItem miHalfHour;
    private javax.swing.JMenuItem miFullHour;
    private javax.swing.JMenuItem miEditMgtd;
}
