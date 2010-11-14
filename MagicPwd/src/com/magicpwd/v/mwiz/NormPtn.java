/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.v.mwiz;

import com.magicpwd.__a.AFrame;
import com.magicpwd.__i.IBackCall;
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
import java.util.EventListener;

/**
 * 向导模式
 * @author Amon
 */
public class NormPtn extends AFrame
{

    private MenuPtn menuPtn;
    private MwizMdl mwizMdl;
    private EditPtn editPtn;

    public NormPtn(TrayPtn trayPtn, UserMdl userMdl)
    {
        super(trayPtn, userMdl);
    }

    public void initView()
    {
        tb_ToolBar = new javax.swing.JToolBar();
        tb_ToolBar.setFloatable(false);
        tb_ToolBar.setRollover(true);

        fb_FindBar = new FindBar(this);
        fb_FindBar.initView();
        fb_FindBar.setVisible(false);
        getLayeredPane().add(fb_FindBar, javax.swing.JLayeredPane.POPUP_LAYER);

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
        hsg1.addContainerGap();
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addContainerGap();
        hsg2.addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE);
        hsg2.addContainerGap();
        javax.swing.GroupLayout.SequentialGroup hsg3 = layout.createSequentialGroup();
//        hsg3.addContainerGap();
        hsg3.addComponent(hb_HintBar, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE);
//        hsg3.addContainerGap();
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(hsg1).addGroup(hsg2).addGroup(hsg3));

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addComponent(tb_ToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
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
        hb_HintBar.setBackCall(new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, EventListener event, String... params)
            {
                return true;
            }
        });

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
        tb_KeysList.addKeyListener(new java.awt.event.KeyAdapter()
        {

            @Override
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
                {
                    viewKeys();
                }
            }
        });

        safeMdl = mwizMdl.getKeysMdl();

        mwizMdl.getGridMdl().listKeysByKind("0");

        this.addComponentListener(new java.awt.event.ComponentAdapter()
        {

            @Override
            public void componentResized(java.awt.event.ComponentEvent e)
            {
                if (fb_FindBar.isVisible())
                {
                    moveFindBar();
                }
            }
        });

        fb_FindBar.initData();
    }

    @Override
    public boolean endSave()
    {
        return true;
    }

    public void findKeys(String meta)
    {
        if (Char.isValidate(meta))
        {
            mwizMdl.getGridMdl().listKeysByMeta(meta);
        }
        else
        {
            mwizMdl.getGridMdl().listKeysByKind("0");
        }
    }

    public boolean findHint(java.util.Date s, java.util.Date t)
    {
        return mwizMdl.getGridMdl().listTask(s, t);
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

    private void moveFindBar()
    {
        java.awt.Dimension size = fb_FindBar.getPreferredSize();
        fb_FindBar.setBounds(this.getContentPane().getSize().width - size.width - 10, 0, size.width, size.height);
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
        if (visible)
        {
            fb_FindBar.setVisible(true);
            moveFindBar();
            fb_FindBar.requestFocus();
        }
        else
        {
            fb_FindBar.setVisible(false);
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
        mwizMdl.getGridMdl().deCrypt(src, dst);
    }

    public void enCrypt(java.io.File src, java.io.File dst) throws Exception
    {
        mwizMdl.getGridMdl().enCrypt(src, dst);
    }

    private void tbKeysListMouseClicked(java.awt.event.MouseEvent e)
    {
        if (e.getClickCount() > 1)
        {
            viewKeys();
        }
    }
    private HintBar hb_HintBar;
    private FindBar fb_FindBar;
    private javax.swing.JTable tb_KeysList;
    private javax.swing.JToolBar tb_ToolBar;
    private javax.swing.JPopupMenu pm_MenuPop;
}
