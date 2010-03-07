/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.x;

import com.magicpwd.MagicPwd;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IBackCall;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.r.AmonFF;

/**
 *
 * @author amon
 */
public class IcoDialog extends javax.swing.JDialog
{

    private java.awt.Color bdColor;
    private java.awt.Color bsColor;
    private java.awt.Color fdColor;
    private java.awt.Color fsColor;
    private java.io.File icoPath;
    private IBackCall backCall;
    private javax.swing.JLabel lb_LastIcon;

    public IcoDialog(IBackCall backCall)
    {
        super(MagicPwd.getCurrForm(), true);
        this.backCall = backCall;
        javax.swing.UIDefaults uiDef = javax.swing.UIManager.getDefaults();
        bdColor = uiDef.getColor("Table.background");
        bsColor = uiDef.getColor("Table.selectionBackground");
        fdColor = uiDef.getColor("Table.foreground");
        fsColor = uiDef.getColor("Table.selectionForeground");
    }

    public void initView()
    {
        javax.swing.JScrollPane sp_IconList = new javax.swing.JScrollPane();
        pl_IconList = new javax.swing.JPanel();
        pl_IconGrid = new javax.swing.JPanel();
        bt_Append = new javax.swing.JButton();
        bt_Select = new javax.swing.JButton();

        pl_IconList.setBackground(bdColor);
        pl_IconList.setLayout(new javax.swing.BoxLayout(pl_IconList, javax.swing.BoxLayout.Y_AXIS));
        sp_IconList.setViewportView(pl_IconList);

        pl_IconGrid.setLayout(new java.awt.GridLayout(0, 10, 4, 4));
        pl_IconGrid.setOpaque(false);
        javax.swing.GroupLayout pl_IconListLayout = new javax.swing.GroupLayout(pl_IconList);
        pl_IconList.setLayout(pl_IconListLayout);
        pl_IconListLayout.setHorizontalGroup(
                pl_IconListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pl_IconGrid, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE));
        pl_IconListLayout.setVerticalGroup(
                pl_IconListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(pl_IconListLayout.createSequentialGroup().addComponent(pl_IconGrid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(251, Short.MAX_VALUE)));

        bt_Append.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_AppendActionPerformed(evt);
            }
        });

        bt_Select.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_SelectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addComponent(bt_Select);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(bt_Append);
        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        hpg.addComponent(sp_IconList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE);
        hpg.addGroup(hsg1);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addGroup(hpg);
        hsg.addContainerGap();
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg.addComponent(bt_Append);
        vpg.addComponent(bt_Select);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        vsg.addComponent(sp_IconList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg);
        vsg.addContainerGap();
        layout.setVerticalGroup(vsg);

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setIconImage(Util.getLogo());
        this.setResizable(false);
        this.pack();
        Util.centerForm(this, MagicPwd.getCurrForm());
    }

    public void initLang()
    {
        Lang.setWText(bt_Select, LangRes.P30FA50A, "确定(&O)");

        Lang.setWText(bt_Append, LangRes.P30FA50B, "取消(&C)");
    }

    public void initData(final String lastIcon)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {
                pl_IconGrid.removeAll();
                initIcon(lastIcon);
            }
        });
    }

    private synchronized void initIcon(String lastIcon)
    {
        if (icoPath == null)
        {
            icoPath = Util.icoPath;
            if (!icoPath.exists())
            {
                icoPath.mkdirs();
            }
        }

        java.awt.event.MouseAdapter listener = new java.awt.event.MouseAdapter()
        {

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                lb_LastIcon.setBackground(bdColor);
                lb_LastIcon.setForeground(fdColor);
                lb_LastIcon = (javax.swing.JLabel) evt.getSource();
                lb_LastIcon.setBackground(bsColor);
                lb_LastIcon.setForeground(fsColor);
            }
        };

        boolean checked = !Util.isValidate(lastIcon) || "0".equals(lastIcon);
        javax.swing.JLabel label = new javax.swing.JLabel(Util.getNone());
        label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        label.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        label.setBackground(bdColor);
        label.setOpaque(true);
        label.setText("0");
        label.putClientProperty("key", "0");
        label.addMouseListener(listener);
        pl_IconGrid.add(label);
        if (checked)
        {
            lb_LastIcon = label;
            lb_LastIcon.setBackground(bsColor);
            lb_LastIcon.setForeground(fsColor);
        }

        java.io.File[] fileList = icoPath.listFiles(new AmonFF("[0-9a-z]{16}\\.png", false));
        if (fileList == null)
        {
            return;
        }

        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[0-9A-Za-z]{16}");
        int i = 1;
        for (java.io.File file : fileList)
        {
            if (!file.isFile())
            {
                continue;
            }
            java.util.regex.Matcher matcher = pattern.matcher(file.getName());
            if (!matcher.find())
            {
                continue;
            }

            String key = matcher.group();
            label = new javax.swing.JLabel(Util.getIcon(file));
            label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            label.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            label.setText(Integer.toString(i++));
            label.setBackground(bdColor);
            label.setForeground(fdColor);
            label.setOpaque(true);
            label.putClientProperty("key", key);
            label.addMouseListener(listener);
            pl_IconGrid.add(label);
            if (!checked)
            {
                checked = key.equalsIgnoreCase(lastIcon);
                if (checked)
                {
                    lb_LastIcon = label;
                    label.setBackground(bsColor);
                    label.setForeground(fsColor);
                }
            }
        }
    }

    private void bt_SelectActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (lb_LastIcon == null)
        {
            Lang.showMesg(this, null, "");
            return;
        }
        backCall.callBack(null, null, (String) lb_LastIcon.getClientProperty("key"));
    }

    private void bt_AppendActionPerformed(java.awt.event.ActionEvent evt)
    {
    }
    private javax.swing.JButton bt_Append;
    private javax.swing.JButton bt_Select;
    private javax.swing.JPanel pl_IconList;
    private javax.swing.JPanel pl_IconGrid;
}
