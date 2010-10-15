/*
 * 
 */
package com.magicpwd._bean.pwd;

import com.magicpwd._comp.WEditBox;
import com.magicpwd._comn.item.LogoItem;
import com.magicpwd._comp.IcoLabel;
import com.magicpwd._cons.LangRes;
import com.magicpwd.$i.IBackCall;
import com.magicpwd.$i.IEditBean;
import com.magicpwd.$i.IEditItem;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Lang;
import com.magicpwd.v.pwd.MainPtn;
import com.magicpwd.x.IcoDialog;
import java.util.EventListener;

/**
 * 属性：图标
 * 键值：ConsEnv.INDX_ICON
 * @author Amon
 */
public class LogoBean extends javax.swing.JPanel implements IEditBean, IBackCall
{

    private IEditItem itemData;
    private MainPtn mainPtn;
    private WEditBox dataEdit;

    public LogoBean(MainPtn mainPtn)
    {
        this.mainPtn = mainPtn;
    }

    @Override
    public void initView()
    {
        dataEdit = new WEditBox(mainPtn.getCoreMdl().getUserCfg(), this, false);
        dataEdit.initView();
        dataEdit.setCopyButtonVisible(false);
        dataEdit.setDropButtonVisible(false);

        lb_PropName = new javax.swing.JLabel();
        ib_PropName = new IcoLabel();
        ib_PropName.setIcon(Bean.getNone());
        ib_PropName.setOpaque(true);
        ib_PropName.setPreferredSize(new java.awt.Dimension(21, 21));
        ib_PropName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ib_PropName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ib_PropName.setBackground(javax.swing.UIManager.getDefaults().getColor("TextField.background"));
        ib_PropName.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        ib_PropName.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ib_PropDataActionPerformed(evt);
            }
        });
        lb_PropName.setLabelFor(ib_PropName);

        lb_PropData = new javax.swing.JLabel();
        ta_PropData = new javax.swing.JTextArea();
        lb_PropData.setLabelFor(ta_PropData);
        ta_PropData.setLineWrap(true);
        ta_PropData.setRows(3);
        javax.swing.JScrollPane sp_PropData = new javax.swing.JScrollPane(ta_PropData);

        lb_PropEdit = new javax.swing.JLabel();
        pl_PropEdit = new javax.swing.JPanel();
        pl_PropEdit.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_PropName);
        hpg1.addComponent(lb_PropData);
        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(ib_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
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
        vpg1.addComponent(ib_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
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

    @Override
    public void initLang()
    {
        dataEdit.initLang();

        Lang.setWText(lb_PropName, LangRes.P30F131D, "徽标(@P)");

        Lang.setWText(lb_PropData, LangRes.P30F131E, "名称(@N)");

        Lang.setWText(ib_PropName, LangRes.P30F131F, "&O");
        Lang.setWTips(ib_PropName, LangRes.P30F1320, "点击选择徽标(Alt + O)");
    }

    @Override
    public void initData()
    {
    }

    @Override
    public void showData(IEditItem item)
    {
        itemData = (LogoItem) item;
        ib_PropName.setIcon(Bean.getDataIcon(item.getName()));
        ta_PropData.setText(item.getData());
    }

    @Override
    public void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
//        String name = tf_PropName.getText();
//        if (com.magicpwd._util.Char.isValidateHash(itemData.getData()) && !com.magicpwd._util.Char.isValidate(name))
//        {
//            Lang.showMesg(MagicPwd.getCurrForm(), LangRes.P30F7A39, "请输入徽标名称！");
//            return;
//        }
        itemData.setData(ta_PropData.getText());
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

    @Override
    public boolean callBack(Object sender, EventListener event, String... params)
    {
        if (params.length < 1)
        {
            return false;
        }

        String key = params[0];
        if ("0".equals(key))
        {
            ib_PropName.setIcon(Bean.getNone());
            itemData.setName(key);
            return true;
        }

        if (!com.magicpwd._util.Char.isValidateHash(key))
        {
            return false;
        }
        ib_PropName.setIcon(Bean.getDataIcon(key));
        itemData.setName(key);
        return true;
    }

    @Override
    public void requestFocus()
    {
        ta_PropData.requestFocus();
    }

    private void ib_PropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        IcoDialog ico = new IcoDialog(this);
        ico.initView();
        ico.initLang();
        ico.initData(itemData.getName());
        ico.setVisible(true);
    }
    private javax.swing.JLabel lb_PropEdit;
    private javax.swing.JLabel lb_PropName;
    private IcoLabel ib_PropName;
    private javax.swing.JLabel lb_PropData;
    private javax.swing.JTextArea ta_PropData;
    private javax.swing.JPanel pl_PropEdit;
}
