/**
 * 
 */
package com.magicpwd._bean;

import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comn.Item;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IEditBean;
import com.magicpwd._face.IEditItem;
import com.magicpwd._face.IGridView;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.EditBox;

/**
 * 属性：过期提示
 * 键值：ConsEnv.INDX_TIME
 * @author Amon
 */
public class NoteBean extends javax.swing.JPanel implements IEditBean
{

    private IEditItem tpltData;
    private IGridView gridView;
    private EditBox dataEdit;

    public NoteBean(IGridView view)
    {
        gridView = view;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.magicpwd._face.IEditBean#initView()
     */
    @Override
    public void initView()
    {
        dataEdit = new EditBox(this, true);
        dataEdit.initView();
        dataEdit.setDropButtonVisible(false);

        lb_PropName = new javax.swing.JLabel();

        tf_PropName = new javax.swing.JTextField(14);
        tf_PropName.addFocusListener(new java.awt.event.FocusAdapter()
        {

            @Override
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                tf_PropName.selectAll();
            }
        });
        lb_PropName.setLabelFor(tf_PropName);

        lb_PropData = new javax.swing.JLabel();

        tf_PropData = new javax.swing.JTextField();
        lb_PropData.setLabelFor(tf_PropData);

        lb_PropEdit = new javax.swing.JLabel();

        pl_PropEdit = new javax.swing.JPanel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_PropEdit);
        hpg1.addComponent(lb_PropData);
        hpg1.addComponent(lb_PropName);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(pl_PropEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg2.addComponent(tf_PropData, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addGroup(hpg1);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addGroup(hpg2);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(dataEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lb_PropName);
        vpg1.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(lb_PropData);
        vpg2.addComponent(tf_PropData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg3.addComponent(lb_PropEdit);
        vpg3.addComponent(pl_PropEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addGroup(vpg1);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg2);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg3);
        javax.swing.GroupLayout.ParallelGroup vpg4 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        vpg4.addComponent(dataEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        vpg4.addGroup(vsg);
        layout.setVerticalGroup(vpg4);

        bt_DateView = new BtnLabel();
        bt_DateView.setMnemonic('O');
        bt_DateView.setIcon(new javax.swing.ImageIcon(Util.getImage(ConsEnv.ICON_DATE_TIME)));
        bt_DateView.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_DateViewActionPerformed(evt);
            }
        });

        bt_SaveData = new BtnLabel();
        bt_SaveData.setMnemonic('U');
        bt_SaveData.setIcon(new javax.swing.ImageIcon(Util.getImage(ConsEnv.ICON_PROP_UPDT)));
        bt_SaveData.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveDataActionPerformed(evt);
            }
        });

        pm_DateView = new javax.swing.JPopupMenu();
        String[] commands =
        {
            "", "half", "hour", "day", "week", "month", "year"
        };
        mi_MenuItem = new javax.swing.JCheckBoxMenuItem[commands.length];

        java.awt.event.ActionListener al = new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mi_MenuItemActionPerformed(evt);
            }
        };
        javax.swing.ButtonGroup bg = new javax.swing.ButtonGroup();
        int idx = 0;

        javax.swing.JCheckBoxMenuItem menuItem = new javax.swing.JCheckBoxMenuItem();
        menuItem.addActionListener(al);
        menuItem.setActionCommand("fixed");
        pm_DateView.add(menuItem);
        bg.add(menuItem);
        mi_MenuItem[idx++] = menuItem;

        pm_DateView.addSeparator();

        while (idx < commands.length)
        {
            menuItem = new javax.swing.JCheckBoxMenuItem();
            menuItem.addActionListener(al);
            menuItem.setActionCommand(commands[idx]);
            pm_DateView.add(menuItem);
            bg.add(menuItem);
            mi_MenuItem[idx++] = menuItem;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.magicpwd._face.IEditBean#initLang()
     */
    @Override
    public void initLang()
    {
        dataEdit.initLang();

        Lang.setWText(lb_PropName, LangRes.P30F1305, "提示");
        Lang.setWText(lb_PropData, LangRes.P30F1306, "时间");
        Lang.setWTips(bt_SaveData, LangRes.P30F1502, "保存");
        Lang.setWTips(bt_DateView, LangRes.P30F150C, "当前时间");

        javax.swing.JCheckBoxMenuItem item;
        int k = 1;
        for (int i = 1, j = mi_MenuItem.length; i < j; i += 1)
        {
            item = mi_MenuItem[i];
            Lang.setWText(item, "P30FA60" + Integer.toHexString(k++).toUpperCase(), "");
            Lang.setWTips(item, "P30FA60" + Integer.toHexString(k++).toUpperCase(), "");
        }
    }

    @Override
    public void initData(Item tplt)
    {
        tpltData = tplt;
        tf_PropName.setText(tplt.getName());
        tf_PropData.setText(tplt.getData());
    }

    @Override
    public void requestFocus()
    {
        if (!Util.isValidate(tf_PropName.getText()))
        {
            tf_PropName.requestFocus();
            return;
        }
        tf_PropData.requestFocus();
    }

    @Override
    public void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        String name = tf_PropName.getText();
        String data = tf_PropData.getText();
        if (Util.isValidate(data))
        {
            if (!tpltData.setData(data))
            {
                Lang.showMesg(this, "", "您输入的日期格式无效，请重新输入！");
                return;
            }
            tf_PropData.setText(data);

            if (!Util.isValidate(name))
            {
                Lang.showMesg(this, "", "请输入过期提示！");
            }
        }

        tpltData.setName(name);
        tpltData.setData(data);
        UserMdl.getGridMdl().setModified(true);
        gridView.selectNext(!UserMdl.getGridMdl().isUpdate());
    }

    @Override
    public void copyDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    private void bt_DateViewActionPerformed(java.awt.event.ActionEvent evt)
    {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.HOUR, 1);
        cal.set(java.util.Calendar.MINUTE, 0);
        cal.set(java.util.Calendar.SECOND, 0);

        String t = cal.get(java.util.Calendar.HOUR_OF_DAY) + "点";
        Lang.setWText(mi_MenuItem[0], "", t);
        Lang.setWTips(mi_MenuItem[0], "", t + "提醒");
        mi_MenuItem[0].setActionCommand(new java.text.SimpleDateFormat(ConsEnv.VIEW_DATE).format(cal.getTime()));
        pm_DateView.show(bt_DateView, 0, bt_DateView.getHeight());
    }

    private void mi_MenuItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        String cmd = evt.getActionCommand();
        if (!Util.isValidate(cmd))
        {
            return;
        }

        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(ConsEnv.VIEW_DATE);
        if ("half".equalsIgnoreCase(cmd))
        {
            cal.add(java.util.Calendar.MINUTE, 30);
            tf_PropData.setText(sdf.format(cal.getTime()));
            return;
        }
        if ("hour".equalsIgnoreCase(cmd))
        {
            cal.add(java.util.Calendar.HOUR_OF_DAY, 1);
            tf_PropData.setText(sdf.format(cal.getTime()));
            return;
        }
        if ("day".equalsIgnoreCase(cmd))
        {
            cal.add(java.util.Calendar.DAY_OF_MONTH, 1);
            tf_PropData.setText(sdf.format(cal.getTime()));
            return;
        }
        if ("week".equalsIgnoreCase(cmd))
        {
            cal.add(java.util.Calendar.WEEK_OF_MONTH, 1);
            tf_PropData.setText(sdf.format(cal.getTime()));
            return;
        }
        if ("month".equalsIgnoreCase(cmd))
        {
            cal.add(java.util.Calendar.MONTH, 1);
            tf_PropData.setText(sdf.format(cal.getTime()));
            return;
        }
        if ("year".equalsIgnoreCase(cmd))
        {
            cal.add(java.util.Calendar.YEAR, 1);
            tf_PropData.setText(sdf.format(cal.getTime()));
            return;
        }
        tf_PropData.setText(cmd);
    }
    private javax.swing.JPopupMenu pm_DateView;
    private javax.swing.JCheckBoxMenuItem[] mi_MenuItem;
    private javax.swing.JLabel lb_PropData;
    private javax.swing.JLabel lb_PropEdit;
    private javax.swing.JLabel lb_PropName;
    private javax.swing.JPanel pl_PropEdit;
    private javax.swing.JTextField tf_PropData;
    private javax.swing.JTextField tf_PropName;
    private BtnLabel bt_SaveData;
    private BtnLabel bt_DateView;
}
