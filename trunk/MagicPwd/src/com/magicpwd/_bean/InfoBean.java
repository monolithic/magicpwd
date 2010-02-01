/**
 * 
 */
package com.magicpwd._bean;

import com.magicpwd._comn.Item;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IEditBean;
import com.magicpwd._face.IGridView;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;

/**
 * 属性：信息
 * 键值：ConsEnv.INDX_INFO
 * @author Amon
 */
public class InfoBean extends javax.swing.JPanel implements IEditBean
{

    private int tipsSize;

    public InfoBean(IGridView view)
    {
    }

    @Override
    public void initView()
    {
        ta_PropData = new javax.swing.JTextArea();
        lb_PropName = new javax.swing.JLabel();
        lb_NextTips = new javax.swing.JLabel();

        ta_PropData.setEditable(false);
        ta_PropData.setLineWrap(true);
        ta_PropData.setEnabled(false);
        ta_PropData.setOpaque(false);

        lb_PropName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lb_NextTips.addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                lb_NextTipsMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg.addComponent(lb_PropName, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE);
        hpg.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(lb_NextTips));
        hpg.addComponent(ta_PropData, 280, 280, Short.MAX_VALUE);
        layout.setHorizontalGroup(hpg);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addComponent(lb_PropName);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(ta_PropData, 0, 48, Short.MAX_VALUE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(lb_NextTips);
        layout.setVerticalGroup(vsg);
    }

    @Override
    public void initLang()
    {
        Lang.setWText(lb_PropName, LangRes.P30F1317, "每日提示");
        Lang.setWText(lb_NextTips, LangRes.P30F1318, "下一提示");
    }

    @Override
    public void initData(Item tplt)
    {
        if (tplt != null)
        {
            lb_PropName.setText(tplt.getName());
            ta_PropData.setText(tplt.getData());
            return;
        }
        try
        {
            tipsSize = Integer.parseInt(Lang.getLang(LangRes.P30F1B00, "0"), 16);
        }
        catch (Exception exp)
        {
            tipsSize = 0;
        }
        tipsSize -= 1;

        Lang.setWText(ta_PropData, LangRes.P30F1B01, "");

        nextTips();
    }

    @Override
    public void requestFocus()
    {
    }

    private void lb_NextTipsMouseReleased(java.awt.event.MouseEvent evt)
    {
        nextTips();
    }

    private void nextTips()
    {
        if (tipsSize <= 0)
        {
            return;
        }

        // 显示提示信息
        String t = Integer.toHexString(new java.util.Random().nextInt(tipsSize) + 1);
        t = "P30F1B" + Util.lPad(t, 2, '0').toUpperCase();
        Lang.setWText(ta_PropData, t, "");
    }

    @Override
    public void saveDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void copyDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void dropDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }
    private javax.swing.JLabel lb_NextTips;
    private javax.swing.JTextArea ta_PropData;
    private javax.swing.JLabel lb_PropName;
}
