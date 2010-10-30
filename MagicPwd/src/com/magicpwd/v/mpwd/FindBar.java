/**
 * 
 */
package com.magicpwd.v.mpwd;

import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd.m.mpwd.ListMdl;
import java.awt.event.FocusEvent;

/**
 * @author Amon
 * 
 */
public class FindBar extends javax.swing.JPanel
{

    private MainPtn mainPtn;
    private ListMdl listMdl;

    public FindBar(MainPtn mainPtn, ListMdl listMdl)
    {
        this.mainPtn = mainPtn;
        this.listMdl = listMdl;
    }

    public void initView()
    {
        lb_ItemFind = new javax.swing.JLabel();
        tf_ItemFind = new javax.swing.JTextField();
        bt_ItemFind = new javax.swing.JButton();

        lb_ItemFind.setLabelFor(tf_ItemFind);

        tf_ItemFind.addFocusListener(new java.awt.event.FocusListener()
        {

            @Override
            public void focusGained(FocusEvent e)
            {
                tf_ItemFind.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e)
            {
            }
        });

        java.awt.event.ActionListener listener = new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                findActionPerformed(evt);
            }
        };
        tf_ItemFind.addActionListener(listener);
        bt_ItemFind.addActionListener(listener);

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
        Lang.setWText(lb_ItemFind, LangRes.P30F7301, "查找(@X)");

        Lang.setWText(bt_ItemFind, LangRes.P30F7302, "查询(@Q)");
    }

    public void initData()
    {
    }

    @Override
    public void requestFocus()
    {
        tf_ItemFind.requestFocus();
    }

    public String getSearchText()
    {
        return tf_ItemFind.getText();
    }

    public void setSearchText(String text)
    {
        tf_ItemFind.setText(text);
    }

    private void findActionPerformed(java.awt.event.ActionEvent evt)
    {

        String text = getSearchText();
        if (!com.magicpwd._util.Char.isValidate(text))
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A18, "请输入您要查询的关键字，多个关键字可以使用空格或加号进行分隔！");
            requestFocus();
            return;
        }

        boolean b = listMdl.listKeysByMeta(text);
        if (!b)
        {
            Lang.showMesg(mainPtn, LangRes.P30F7A19, "查询不到符合您条件的数据，请用空格或加号分隔您的搜索关键字后重试！");
            requestFocus();
        }
    }
    private javax.swing.JButton bt_ItemFind;
    private javax.swing.JLabel lb_ItemFind;
    private javax.swing.JTextField tf_ItemFind;
}
