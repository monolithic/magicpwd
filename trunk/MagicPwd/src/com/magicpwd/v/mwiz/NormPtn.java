/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.v.mwiz;

import com.magicpwd.__a.AFrame;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Logs;
import com.magicpwd.m.UserMdl;
import com.magicpwd.m.mwiz.KeysMdl;
import com.magicpwd.m.mwiz.MwizMdl;
import com.magicpwd.v.MenuPtn;
import com.magicpwd.v.TrayPtn;

/**
 * 向导模式
 * @author Amon
 */
public class NormPtn extends AFrame
{

    private MenuPtn menuPtn;
    private MwizMdl mwizMdl;

    public NormPtn(TrayPtn trayPtn, UserMdl userMdl)
    {
        super(trayPtn, userMdl);
    }

    public void initView()
    {
        menuPtn = new MenuPtn(trayPtn, userMdl);
        try
        {
            menuPtn.loadData(new java.io.File("dat/mwiz.xml"));
            tb_ToolBar = menuPtn.getToolBar("mwiz", rootPane);
        }
        catch (Exception e)
        {
            tb_ToolBar = new javax.swing.JToolBar();
        }

        tb_ToolBar.setFloatable(false);
        tb_ToolBar.setRollover(true);

        tb_KeysList = new javax.swing.JTable();
        tb_KeysList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        mwizMdl = new MwizMdl(userMdl);
        mwizMdl.init();
        tb_KeysList.setModel(mwizMdl);
        int w = tb_KeysList.getFontMetrics(tb_KeysList.getFont()).stringWidth("99999");
        tb_KeysList.getColumnModel().getColumn(0).setMaxWidth(w);
        tb_KeysList.getColumnModel().getColumn(1).setMaxWidth(40);

        tb_KeysList.addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tbKeysListMouseClicked(evt);
            }
        });
        javax.swing.JScrollPane jsp = new javax.swing.JScrollPane(tb_KeysList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addContainerGap();
        hsg1.addComponent(tb_ToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE);
        hsg1.addContainerGap();
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addContainerGap();
        hsg2.addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE);
        hsg2.addContainerGap();
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(hsg1).addGroup(hsg2));

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addComponent(tb_ToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE);
        vsg.addContainerGap();
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(vsg));

        setIconImage(Bean.getLogo(16));
        pack();
        Bean.centerForm(this, null);
    }

    public void initLang()
    {
    }

    public void initData()
    {
        safeMdl = mwizMdl.getKeysMdl();

        mwizMdl.listKeysByKind("0");
    }

    @Override
    public boolean endSave()
    {
        return true;
    }

    public void appendKeys()
    {
        EditPtn editPtn = new EditPtn(this);
        editPtn.initView();
        editPtn.initLang();
        editPtn.initData();
        KeysMdl keysMdl = mwizMdl.getKeysMdl();
        keysMdl.clear();
        editPtn.showData(keysMdl);
    }

    public void updateKeys()
    {
        EditPtn editPtn = new EditPtn(this);
        editPtn.initView();
        editPtn.initLang();
        editPtn.initData();
        KeysMdl keysMdl = mwizMdl.getKeysMdl();
        try
        {
            keysMdl.loadData(mwizMdl.getKeysAt(tb_KeysList.getSelectedRow()));
            editPtn.showData(keysMdl);
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
        }
    }

    public void deleteKeys()
    {
    }

    public void endKeys()
    {
        mwizMdl.listKeysByKind("0");
    }

    @Override
    public MenuPtn getMenuPtn()
    {
        return menuPtn;
    }

    public void deCrypt(java.io.File src, java.io.File dst) throws Exception
    {
    }

    public void enCrypt(java.io.File src, java.io.File dst) throws Exception
    {
    }

    public void editSelected()
    {
    }

    private void tbKeysListMouseClicked(java.awt.event.MouseEvent e)
    {
    }
    private javax.swing.JTable tb_KeysList;
    private javax.swing.JToolBar tb_ToolBar;
}
