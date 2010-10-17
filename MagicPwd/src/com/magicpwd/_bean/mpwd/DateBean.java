/**
 * 
 */
package com.magicpwd._bean.mpwd;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd.__i.IEditBean;
import com.magicpwd.__i.IEditItem;
import com.magicpwd._comp.WEditBox;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comp.WTextBox;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.Date;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.v.mpwd.MainPtn;

/**
 * 属性：日期
 * 键值：ConsEnv.INDX_DATE
 * @author Amon
 */
public class DateBean extends javax.swing.JPanel implements IEditBean
{

    private IEditItem itemData;
    private WEditBox dataEdit;
    private MainPtn mainPtn;
    private WTextBox nameBox;
//    private WTextBox dataBox;
    private java.text.DateFormat format;

    public DateBean(MainPtn mainPtn)
    {
        this.mainPtn = mainPtn;
    }

    @Override
    public void initView()
    {
        dataEdit = new WEditBox(mainPtn.getCoreMdl().getUserCfg(), this, false);
        dataEdit.initView();

        lb_PropName = new javax.swing.JLabel();
        tf_PropName = new javax.swing.JTextField(14);
        nameBox = new WTextBox(tf_PropName, true);
        nameBox.initView();
        lb_PropName.setLabelFor(tf_PropName);

        lb_PropData = new javax.swing.JLabel();
        tf_PropData = new javax.swing.JTextField();
        lb_PropData.setLabelFor(tf_PropData);

        lb_PropEdit = new javax.swing.JLabel();
        pl_PropEdit = new javax.swing.JPanel();
        pl_PropEdit.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        bt_DateTime = new BtnLabel();
        bt_DateTime.setIcon(Bean.readIcon(ConsEnv.FEEL_PATH + "date.png", mainPtn.getCoreMdl().getUserCfg()));
        bt_DateTime.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_DateViewActionPerformed(evt);
            }
        });
        pl_PropEdit.add(bt_DateTime);

        bt_DateConf = new BtnLabel();
        bt_DateConf.setIcon(Bean.readIcon(ConsEnv.FEEL_PATH + "date.png", mainPtn.getCoreMdl().getUserCfg()));
        bt_DateConf.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_DateConfActionPerformed(evt);
            }
        });
        pl_PropEdit.add(bt_DateConf);

        pm_MenuTime = new javax.swing.JPopupMenu();
        mi_TimeDef = new javax.swing.JMenuItem();
        AMpwdAction action = new AMpwdAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                mi_DateTimeActionPerformed(e);
            }

            @Override
            public void doInit(Object object)
            {
            }

            @Override
            public void reInit(Object object)
            {
            }
        };
        mi_TimeDef.addActionListener(action);
        pm_MenuTime.add(mi_TimeDef);
        pm_MenuTime.addSeparator();
        mainPtn.getMenuPtn().getSubMenu("date-interval", pm_MenuTime, action);

        pm_MenuConf = new javax.swing.JPopupMenu();
        mi_ConfDef = new javax.swing.JCheckBoxMenuItem();
        pm_MenuConf.add(mi_ConfDef);
        pm_MenuTime.addSeparator();
        mainPtn.getMenuPtn().getSubMenu("date-template", pm_MenuConf, new AMpwdAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                mi_DateTpltActionPerformed(e);
            }

            @Override
            public void doInit(Object object)
            {
            }

            @Override
            public void reInit(Object object)
            {
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_PropEdit);
        hpg1.addComponent(lb_PropData);
        hpg1.addComponent(lb_PropName);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(tf_PropData, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE);
        hpg2.addComponent(pl_PropEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE);
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
        vpg3.addComponent(pl_PropEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addGroup(vpg1);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg2);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg3);
        vsg1.addContainerGap(14, Short.MAX_VALUE);
        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        vpg.addGroup(vsg1);
        vpg.addComponent(dataEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setVerticalGroup(vpg);
    }

    @Override
    public void initLang()
    {
        Lang.setWText(lb_PropName, LangRes.P30F130F, "名称");
        Lang.setWText(lb_PropData, LangRes.P30F1310, "时间");

        Lang.setWText(bt_DateTime, LangRes.P30F1513, "&O");
        Lang.setWTips(bt_DateTime, LangRes.P30F1514, "当前时间(Alt + O)");

        Bean.setText(mi_TimeDef, "当前时间");
        Bean.setText(mi_ConfDef, "默认格式");

        nameBox.initLang();
        dataEdit.initLang();
    }

    @Override
    public void initData()
    {
        nameBox.initData();
    }

    @Override
    public void showData(IEditItem item)
    {
        itemData = item;
        String name = itemData.getName();
        if (com.magicpwd._util.Char.isValidate(name) && name.startsWith(ConsDat.SP_TPL_LS) && name.endsWith(ConsDat.SP_TPL_RS))
        {
            name = name.substring(1, name.length() - 1);
        }
        tf_PropName.setText(name);
        tf_PropData.setText(itemData.getData());
        boolean b = mainPtn.getMenuPtn().getGroup("date-template").setSelected(itemData.getSpec(IEditItem.SPEC_DATE_FORM), true);
        mi_ConfDef.setSelected(!b);
    }

    @Override
    public void requestFocus()
    {
        if (!com.magicpwd._util.Char.isValidate(tf_PropName.getText()))
        {
            tf_PropName.requestFocus();
            return;
        }
        tf_PropData.requestFocus();
    }

    @Override
    public void copyDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        tf_PropData.selectAll();
        Util.setClipboardContents(tf_PropData.getText());
    }

    @Override
    public void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (Lang.showFirm(mainPtn, LangRes.P30F1A01, "确认要删除此属性数据么？") == javax.swing.JOptionPane.YES_OPTION)
        {
            mainPtn.removeSelected();
        }
    }

    @Override
    public void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        String name = tf_PropName.getText();
        if (!com.magicpwd._util.Char.isValidate(name))
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A30, "请输入日期名称！");
            tf_PropName.requestFocus();
            return;
        }

        itemData.setName(name);
        itemData.setData(tf_PropData.getText());

        mainPtn.updateSelected();
    }

    private void bt_DateViewActionPerformed(java.awt.event.ActionEvent evt)
    {
        pm_MenuTime.show(bt_DateTime, 0, bt_DateTime.getSize().height);
    }

    private void bt_DateConfActionPerformed(java.awt.event.ActionEvent evt)
    {
        pm_MenuConf.show(bt_DateConf, 0, bt_DateConf.getSize().height);
    }

    private void mi_DateTimeActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (format == null)
        {
            getFormat();
        }
        tf_PropData.setText(format.format(Date.parseDate(evt.getActionCommand()).getTime()));
    }

    private void mi_DateTpltActionPerformed(java.awt.event.ActionEvent evt)
    {
        String cmd = evt.getActionCommand();
        if (!Char.isValidate(cmd))
        {
            return;
        }
        itemData.setSpec(IEditItem.SPEC_DATE_FORM, cmd);
        getFormat();
    }

    private java.text.DateFormat getFormat()
    {
        String t = itemData.getSpec(IEditItem.SPEC_DATE_FORM);
        if (!Char.isValidate(t))
        {
            format = java.text.DateFormat.getDateTimeInstance();
        }
        if (t.startsWith("java:"))
        {
            java.util.regex.Matcher dm = java.util.regex.Pattern.compile("\\d+d").matcher(t);
            String date = dm.find() ? dm.group().replace("d", "") : null;
            java.util.regex.Matcher tm = java.util.regex.Pattern.compile("\\d+t").matcher(t);
            String time = tm.find() ? tm.group().replace("t", "") : null;
            if (date != null && time != null)
            {
                format = java.text.DateFormat.getDateTimeInstance(Integer.parseInt(date), Integer.parseInt(time));
                return format;
            }
            if (date != null)
            {
                format = java.text.DateFormat.getDateInstance(Integer.parseInt(date));
                return format;
            }
            if (time != null)
            {
                format = java.text.DateFormat.getDateInstance(Integer.parseInt(time));
                return format;
            }

            format = java.text.DateFormat.getDateTimeInstance();
            return format;
        }

        if (t.startsWith("user:"))
        {
            format = new java.text.SimpleDateFormat(t.substring(5));
            return format;
        }

        format = java.text.DateFormat.getDateTimeInstance();
        return format;
    }
    private javax.swing.JLabel lb_PropData;
    private javax.swing.JLabel lb_PropEdit;
    private javax.swing.JLabel lb_PropName;
    private javax.swing.JPanel pl_PropEdit;
    private javax.swing.JTextField tf_PropData;
    private javax.swing.JTextField tf_PropName;
    private BtnLabel bt_DateTime;
    private BtnLabel bt_DateConf;
    private javax.swing.JPopupMenu pm_MenuTime;
    private javax.swing.JPopupMenu pm_MenuConf;
    private javax.swing.JMenuItem mi_TimeDef;
    private javax.swing.JCheckBoxMenuItem mi_ConfDef;
}
