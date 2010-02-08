/**
 * 
 */
package com.magicpwd.v;

import javax.swing.JPanel;

import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd.c.FindEvt;

/**
 * @author Amon
 * 
 */
public class FindBar extends JPanel
{

    private FindEvt fe_FindEvent;

    public FindBar()
    {
    }

    public void initView()
    {
        lb_ItemFind = new javax.swing.JLabel();
        tf_ItemFind = new javax.swing.JTextField();
        bt_ItemFind = new javax.swing.JButton();

        lb_ItemFind.setLabelFor(tf_ItemFind);

        tf_ItemFind.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                fe_FindEvent.findActionPerformed(evt);
            }
        });

        bt_ItemFind.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                fe_FindEvent.findActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addComponent(lb_ItemFind);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(tf_ItemFind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(bt_ItemFind);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg.addComponent(lb_ItemFind);
        vpg.addComponent(bt_ItemFind);
        vpg.addComponent(tf_ItemFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setVerticalGroup(vpg);
    }

    public void initLang()
    {
        Lang.setWText(lb_ItemFind, LangRes.P30F7301, "查找(&X)");

        Lang.setWText(bt_ItemFind, LangRes.P30F7302, "查询(&Q)");
    }

    public void initData()
    {
        tf_ItemFind.requestFocus();
    }

    public void setActionEvent(FindEvt event)
    {
        fe_FindEvent = event;
    }

    public String getSearchText()
    {
        return tf_ItemFind.getText();
    }

    public void setSearchText(String text)
    {
        tf_ItemFind.setText(text);
    }

    @Override
    public void requestFocus()
    {
        tf_ItemFind.requestFocus();
    }
    private javax.swing.JButton bt_ItemFind;
    private javax.swing.JLabel lb_ItemFind;
    private javax.swing.JTextField tf_ItemFind;
}
