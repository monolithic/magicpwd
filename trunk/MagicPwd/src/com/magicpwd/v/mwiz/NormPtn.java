/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.v.mwiz;

import com.magicpwd.__a.AFrame;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.m.UserMdl;
import com.magicpwd.m.mwiz.KeysMdl;
import com.magicpwd.m.mwiz.MwizMdl;
import com.magicpwd.v.MenuPtn;
import com.magicpwd.v.TrayPtn;
import com.magicpwd.v.HintBar;

/**
 * 向导模式
 * @author Amon
 */
public class NormPtn extends AFrame
{

    private MenuPtn menuPtn;
    private MwizMdl mwizMdl;
    private EditPtn editPtn;
    private String lastMeta = "";

    public NormPtn(TrayPtn trayPtn, UserMdl userMdl)
    {
        super(trayPtn, userMdl);
    }

    public void initView()
    {
        tb_ToolBar = new javax.swing.JToolBar();
        tb_ToolBar.setFloatable(false);
        tb_ToolBar.setRollover(true);

        tb_FindBar = new javax.swing.JToolBar();
        tb_FindBar.setFloatable(false);
        tb_FindBar.setRollover(true);
        tb_FindBar.setVisible(false);

        tf_FindBox = new javax.swing.JTextField();
        tf_FindBox.setColumns(20);
        tb_FindBar.add(tf_FindBox);

        tb_KeysList = new javax.swing.JTable();
        tb_KeysList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        hb_HintBar = new HintBar(userMdl);
        hb_HintBar.initView();

        pm_MenuPop = new javax.swing.JPopupMenu();
        javax.swing.JScrollPane jsp = new javax.swing.JScrollPane(tb_KeysList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addContainerGap();
        hsg1.addComponent(tb_ToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hsg1.addComponent(tb_FindBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg1.addContainerGap();
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addContainerGap();
        hsg2.addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE);
        hsg2.addContainerGap();
        javax.swing.GroupLayout.SequentialGroup hsg3 = layout.createSequentialGroup();
        hsg3.addContainerGap();
        hsg3.addComponent(hb_HintBar, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE);
        hsg3.addContainerGap();
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(hsg1).addGroup(hsg2).addGroup(hsg3));

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg.addComponent(tb_ToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg.addComponent(tb_FindBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
//        vsg.addComponent(tb_ToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addGroup(vpg);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE);
//        vsg.addContainerGap();
        vsg.addComponent(hb_HintBar);
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(vsg));

        setIconImage(Bean.getLogo(16));
        pack();
        Bean.centerForm(this, null);
    }

    public void initLang()
    {
        setTitle(Lang.getLang(LangRes.P30F6201, "向导模式"));

        hb_HintBar.initLang();
    }

    public void initData()
    {
        super.setVisible(true);

        hb_HintBar.initData();

        mwizMdl = new MwizMdl(userMdl);
        mwizMdl.init();
        tb_KeysList.setModel(mwizMdl.getGridMdl());
        int w = tb_KeysList.getFontMetrics(tb_KeysList.getFont()).stringWidth("99999");
        tb_KeysList.getColumnModel().getColumn(0).setMaxWidth(w);
        tb_KeysList.getColumnModel().getColumn(1).setMaxWidth(40);

        try
        {
            menuPtn = new MenuPtn(trayPtn, this);
            menuPtn.loadData(new java.io.File(userMdl.getDataDir(), "mwiz.xml"));
            menuPtn.getToolBar("mwiz", tb_ToolBar, rootPane, "mwiz");
            menuPtn.getPopMenu("mwiz", pm_MenuPop);
            menuPtn.getStrokes("mwiz", rootPane);
        }
        catch (Exception e)
        {
            Logs.exception(e);
        }

        tf_FindBox.addKeyListener(new java.awt.event.KeyAdapter()
        {

            @Override
            public void keyReleased(java.awt.event.KeyEvent e)
            {
                tf_FindBox_KeyReleased(e);
            }
        });

        tb_KeysList.addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                // 右键事件处理
                if (evt.isPopupTrigger())
                {
                    int row = tb_KeysList.rowAtPoint(evt.getPoint());
                    tb_KeysList.setRowSelectionInterval(row, row);
                    pm_MenuPop.show(tb_KeysList, evt.getX(), evt.getY());
                }
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                // 右键事件处理
                if (evt.isPopupTrigger())
                {
                    int row = tb_KeysList.rowAtPoint(evt.getPoint());
                    tb_KeysList.setRowSelectionInterval(row, row);
                    pm_MenuPop.show(tb_KeysList, evt.getX(), evt.getY());
                }
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tbKeysListMouseClicked(evt);
            }
        });

        safeMdl = mwizMdl.getKeysMdl();

        mwizMdl.getGridMdl().listKeysByKind("0");
    }

    @Override
    public boolean endSave()
    {
        return true;
    }

    public void viewKeys()
    {
        int row = tb_KeysList.getSelectedRow();
        if (row < 0)
        {
            Lang.showMesg(this, LangRes.P30F6A01, "请选择您要查看的口令！");
            return;
        }

        try
        {
            EditPtn editDlg = getEditPtn();
            editDlg.setTitle(Lang.getLang(LangRes.P30F6202, "口令查看"));
            KeysMdl keysMdl = mwizMdl.getKeysMdl();
            keysMdl.loadData(mwizMdl.getGridMdl().getKeysAt(row));
            editDlg.showData(keysMdl, false);
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
        }
    }

    public void appendKeys()
    {
        EditPtn editDlg = getEditPtn();
        editDlg.setTitle(Lang.getLang(LangRes.P30F6203, "口令编辑"));
        KeysMdl keysMdl = mwizMdl.getKeysMdl();
        keysMdl.clear();
        editDlg.showData(keysMdl, true);
    }

    public void updateKeys()
    {
        int row = tb_KeysList.getSelectedRow();
        if (row < 0)
        {
            Lang.showMesg(this, LangRes.P30F6A02, "请选择您要修改的口令！");
            return;
        }

        try
        {
            EditPtn editDlg = getEditPtn();
            editDlg.setTitle(Lang.getLang(LangRes.P30F6203, "口令编辑"));
            KeysMdl keysMdl = mwizMdl.getKeysMdl();
            keysMdl.loadData(mwizMdl.getGridMdl().getKeysAt(row));
            editDlg.showData(keysMdl, true);
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
        }
    }

    public void deleteKeys()
    {
        int row = tb_KeysList.getSelectedRow();
        if (row < 0)
        {
            Lang.showMesg(this, LangRes.P30F6A03, "请选择您要删除的口令！");
            return;
        }

        try
        {
            mwizMdl.getGridMdl().wDelete(row);
            mwizMdl.getKeysMdl().clear();
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
        }
    }

    @Override
    public void requestFocus()
    {
        tb_KeysList.requestFocus();
    }

    public void setFindVisible(boolean visible)
    {
        tb_FindBar.setVisible(visible);
        if (visible)
        {
            tf_FindBox.requestFocus();
        }
        else
        {
            tb_KeysList.requestFocus();
        }
    }

    private EditPtn getEditPtn()
    {
        if (editPtn == null)
        {
            editPtn = new EditPtn(this);
            editPtn.initView();
            editPtn.initLang();
            editPtn.initData();
        }
        editPtn.setVisible(true);
        return editPtn;
    }

    public void endKeys()
    {
        mwizMdl.getGridMdl().listKeysByKind("0");
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

    private void tf_FindBox_KeyReleased(java.awt.event.KeyEvent e)
    {
        String meta = tf_FindBox.getText();
        if (lastMeta.equals(meta))
        {
            return;
        }
        lastMeta = meta;

        if (Char.isValidate(meta))
        {
            mwizMdl.getGridMdl().listKeysByMeta(meta);
        }
        else
        {
            mwizMdl.getGridMdl().listKeysByKind("0");
        }
    }
    private HintBar hb_HintBar;
    private javax.swing.JTable tb_KeysList;
    private javax.swing.JTextField tf_FindBox;
    private javax.swing.JToolBar tb_ToolBar;
    private javax.swing.JToolBar tb_FindBar;
    private javax.swing.JPopupMenu pm_MenuPop;
}
