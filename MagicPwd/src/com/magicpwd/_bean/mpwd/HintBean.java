/**
 * 
 */
package com.magicpwd._bean.mpwd;

import com.magicpwd.__i.IEditItem;
import com.magicpwd.__i.mpwd.IMpwdBean;
import com.magicpwd._comp.WEditBox;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comp.WTextBox;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Date;
import com.magicpwd._util.Lang;
import com.magicpwd.v.mpwd.MainPtn;

/**
 * 属性：过期提示
 * 键值：ConsEnv.INDX_TIME
 * @author Amon
 */
public class HintBean extends javax.swing.JPanel implements IMpwdBean
{

    private WEditBox dataEdit;
    private IEditItem itemData;
    private MainPtn mainPtn;
    private WTextBox nameBox;
    //private WTextBox dataBox;
    private java.text.DateFormat format;

    public HintBean(MainPtn mainPtn)
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
        dataEdit = new WEditBox(mainPtn, this, true);
        dataEdit.initView();
        dataEdit.setCopyButtonVisible(false);
        dataEdit.setDropButtonVisible(false);

        lb_PropName = new javax.swing.JLabel();
        tf_PropName = new javax.swing.JTextField(14);
        nameBox = new WTextBox(tf_PropName, true);
        nameBox.initView();
        lb_PropName.setLabelFor(tf_PropName);

        lb_PropData = new javax.swing.JLabel();
        tf_PropData = new javax.swing.JTextField();
        lb_PropData.setLabelFor(tf_PropData);

        lb_PropConf = new javax.swing.JLabel();
        pl_PropConf = new javax.swing.JPanel();
        pl_PropConf.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        bt_DateView = new BtnLabel();
        bt_DateView.setIcon(mainPtn.readFavIcon("hint-time", false));
        bt_DateView.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_DateViewActionPerformed(evt);
            }
        });
        pl_PropConf.add(bt_DateView);

        pm_DateView = new javax.swing.JPopupMenu();
        mi_HalfHour = new javax.swing.JMenuItem();
        pm_DateView.add(mi_HalfHour);

        mi_FullHour = new javax.swing.JMenuItem();
        pm_DateView.add(mi_FullHour);

        pm_DateView.addSeparator();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_PropConf);
        hpg1.addComponent(lb_PropData);
        hpg1.addComponent(lb_PropName);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(tf_PropData, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE);
        hpg2.addComponent(pl_PropConf, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE);
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
        vpg3.addComponent(lb_PropConf);
        vpg3.addComponent(pl_PropConf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
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

    /*
     * (non-Javadoc)
     * 
     * @see com.magicpwd._face.IEditBean#initLang()
     */
    @Override
    public void initLang()
    {
        Lang.setWText(lb_PropName, LangRes.P30F1305, "提示");
        Lang.setWText(lb_PropData, LangRes.P30F1306, "时间");

        Lang.setWText(bt_DateView, LangRes.P30F151B, "&O");
        Lang.setWTips(bt_DateView, LangRes.P30F151C, "提醒时间(Alt + O)");

        nameBox.initLang();
        dataEdit.initLang();
    }

    @Override
    public void initData()
    {
        format = new java.text.SimpleDateFormat(ConsEnv.HINT_DATE);
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

        nameBox.initData();
    }

    @Override
    public void showData(IEditItem item)
    {
        itemData = item;

        tf_PropName.setText(item.getName());
        tf_PropData.setText(item.getData());
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
    public void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        String name = tf_PropName.getText();
        String data = tf_PropData.getText();
        if (com.magicpwd._util.Char.isValidate(data))
        {
            if (!itemData.setData(data))
            {
                Lang.showMesg(mainPtn, LangRes.P30F7A37, "您输入的日期格式无效，请重新输入！");
                tf_PropData.requestFocus();
                return;
            }
            tf_PropData.setText(itemData.getData());

            if (!com.magicpwd._util.Char.isValidate(name))
            {
                Lang.showMesg(mainPtn, LangRes.P30F7A36, "请输入过期提示！");
                tf_PropName.requestFocus();
                return;
            }
        }

        itemData.setName(name);
        itemData.setData(data);

        mainPtn.updateSelected();
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
        pm_DateView.show(bt_DateView, 0, bt_DateView.getHeight());
    }

    private void mi_DateTimeActionPerformed(java.awt.event.ActionEvent e)
    {
        java.util.Calendar cal = Date.toDate(e.getActionCommand());
        if (cal != null)
        {
            tf_PropData.setText(format.format(cal.getTime()));
        }
    }
    private javax.swing.JLabel lb_PropName;
    private javax.swing.JLabel lb_PropData;
    private javax.swing.JTextField tf_PropData;
    private javax.swing.JTextField tf_PropName;
    // 配置信息
    private javax.swing.JLabel lb_PropConf;
    private javax.swing.JPanel pl_PropConf;
    private BtnLabel bt_DateView;
    private javax.swing.JPopupMenu pm_DateView;
    private javax.swing.JMenuItem mi_HalfHour;
    private javax.swing.JMenuItem mi_FullHour;
}
