/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.x;

import com.magicpwd.MagicPwd;
import com.magicpwd._comp.IcoLabel;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IBackCall;
import com.magicpwd._util.Hash;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
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
    private java.io.File filePath;
    private java.io.File icoPath;
    private IBackCall backCall;
    private IcoLabel lb_LastIcon;
    private java.awt.event.ActionListener listener;

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
        bt_Append = new javax.swing.JButton();
        bt_Select = new javax.swing.JButton();
        javax.swing.JScrollPane sp_IconGrid = new javax.swing.JScrollPane();
        tb_IconGrid = new javax.swing.JTable();

        tb_IconGrid.setModel(null);
        tb_IconGrid.getTableHeader().setVisible(false);
        tb_IconGrid.setCellSelectionEnabled(true);
        tb_IconGrid.setShowHorizontalLines(false);
        tb_IconGrid.setShowVerticalLines(false);
        sp_IconGrid.setViewportView(tb_IconGrid);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sp_IconGrid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_Select)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_Append)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp_IconGrid, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_Append)
                    .addComponent(bt_Select))
                .addContainerGap())
        );

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setIconImage(Util.getLogo());
        this.setResizable(false);
        this.pack();
        Util.centerForm(this, MagicPwd.getCurrForm());
    }

    public void initLang()
    {
        Lang.setWText(bt_Select, LangRes.P30FA50C, "选择(&C)");

        Lang.setWText(bt_Append, LangRes.P30FA50D, "追加(&A)");
    }

    public void initData(final String lastIcon)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {
                tb_IconGrid.removeAll();
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

        if (listener == null)
        {
            listener = new java.awt.event.ActionListener()
            {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    selectIcoLabel((IcoLabel) evt.getSource());
                }
            };
        }

        boolean checked = !Util.isValidate(lastIcon) || "0".equals(lastIcon);
        IcoLabel label = createIcoLabel("0", "0", Util.getNone(), listener);
        tb_IconGrid.add(label);
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
            label = createIcoLabel(key, Integer.toString(i++), Util.getIcon(key), listener);
            tb_IconGrid.add(label);
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
        if (backCall.callBack(null, null, (String) lb_LastIcon.getClientProperty("key")))
        {
            this.setVisible(false);
            this.dispose();
        }
    }

    private void bt_AppendActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.JFileChooser jfc = new javax.swing.JFileChooser();
        AmonFF ff = new AmonFF("[^\\.]+\\." + ConsEnv.IMAGE_FORMAT + "$", false);
        ff.setHasFolder(true);
        ff.setDescription('.' + ConsEnv.IMAGE_FORMAT);
        jfc.setFileFilter(ff);
        jfc.setMultiSelectionEnabled(false);
        jfc.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
        if (filePath != null)
        {
            jfc.setSelectedFile(filePath);
        }
        if (jfc.showOpenDialog(this) != javax.swing.JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        filePath = jfc.getSelectedFile();
        if (!filePath.exists())
        {
            Lang.showMesg(this, LangRes.P30F7A03, "您选取的文件不存在！");
            return;
        }
        if (!filePath.isFile() || !filePath.canRead())
        {
            Lang.showMesg(this, LangRes.P30F7A05, "无法读取您选择的文件，请确认您是否有足够的权限！");
            return;
        }
        try
        {
            java.io.FileInputStream fis = new java.io.FileInputStream(filePath);
            java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(fis);
            fis.close();

            final int S = 16;
            int w = img.getWidth();
            int h = img.getHeight();
            if (w != S || h != S)
            {
                double dw = 16.0 / w;
                double dh = 16.0 / h;
                double d = dw <= dh ? dw : dh;
                w *= d;
                h *= d;
                java.awt.Image tmp = img.getScaledInstance(w, h, java.awt.Image.SCALE_DEFAULT);
                img = new java.awt.image.BufferedImage(S, S, java.awt.image.BufferedImage.TYPE_INT_ARGB);
                img.createGraphics().drawImage(tmp, (S - w) >> 1, (S - h) >> 1, w, h, null);
            }

            String hash = Hash.hash(false);
            java.io.FileOutputStream fos = new java.io.FileOutputStream(new java.io.File(icoPath, hash + '.' + ConsEnv.IMAGE_FORMAT));
            javax.imageio.ImageIO.write(img, ConsEnv.IMAGE_FORMAT, fos);
            fos.flush();
            fos.close();

            javax.swing.ImageIcon icon = new javax.swing.ImageIcon(img);
            IcoLabel label = createIcoLabel(hash, Integer.toString(tb_IconGrid.getComponents().length), icon, listener);
            tb_IconGrid.add(label);
            tb_IconGrid.revalidate();
            selectIcoLabel(label);
            Util.setIcon(hash, icon);
        }
        catch (Exception exp)
        {
            Lang.showMesg(this, null, exp.getLocalizedMessage());
            Logs.exception(exp);
            return;
        }
    }

    private IcoLabel createIcoLabel(String key, String txt, javax.swing.ImageIcon ico, java.awt.event.ActionListener listener)
    {
        IcoLabel label = new IcoLabel();
        label.setIcon(ico);
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        label.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        label.setText(txt);
        label.setBackground(bdColor);
        label.setForeground(fdColor);
        label.setOpaque(true);
        label.putClientProperty("key", key);
        label.addActionListener(listener);
        return label;
    }

    private void selectIcoLabel(IcoLabel label)
    {
        if (lb_LastIcon != null)
        {
            lb_LastIcon.setBackground(bdColor);
            lb_LastIcon.setForeground(fdColor);
        }
        lb_LastIcon = label;
        lb_LastIcon.setBackground(bsColor);
        lb_LastIcon.setForeground(fsColor);
    }
    private javax.swing.JButton bt_Append;
    private javax.swing.JButton bt_Select;
    private javax.swing.JTable tb_IconGrid;
}
