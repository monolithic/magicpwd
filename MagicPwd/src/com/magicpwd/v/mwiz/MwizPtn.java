/*
 *  Copyright (C) 2010 Amon
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd.v.mwiz;

import com.magicpwd.__a.AFrame;
import com.magicpwd.__i.IBackCall;
import com.magicpwd._bean.mail.Connect;
import com.magicpwd._comn.I1S2;
import com.magicpwd._comn.Keys;
import com.magicpwd._comn.S1S1;
import com.magicpwd._comp.WButtonGroup;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.m.UserMdl;
import com.magicpwd.m.mail.Reader;
import com.magicpwd.m.mwiz.KeysMdl;
import com.magicpwd.m.mwiz.MwizMdl;
import com.magicpwd.v.MenuPtn;
import com.magicpwd.v.tray.TrayPtn;
import com.magicpwd.v.HintBar;
import com.magicpwd.v.mpwd.MailDlg;

/**
 * 向导模式
 * @author Amon
 */
public class MwizPtn extends AFrame
{

    private MenuPtn menuPtn;
    private MwizMdl mwizMdl;
    private EditPtn editPtn;
    private String keyMeta;

    public MwizPtn(TrayPtn trayPtn, UserMdl userMdl)
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

        this.setIconImage(Bean.getLogo(16));

        this.pack();
        Bean.centerForm(this, null);
        this.setVisible(true);
    }

    public void initLang()
    {
        setTitle(Lang.getLang(LangRes.P30F6201, "魔方密码"));

        hb_HintBar.initLang();

        this.pack();
        Bean.centerForm(this, null);
    }

    public void initData()
    {
        hb_HintBar.initData();
        hb_HintBar.setBackCall(new IBackCall<String>()
        {

            @Override
            public boolean callBack(String options, String object)
            {
                hintCallBack();
                return true;
            }
        });

        mwizMdl = new MwizMdl(userMdl);
        mwizMdl.init();

        tb_KeysList.setModel(mwizMdl.getGridMdl());
        int w = tb_KeysList.getFontMetrics(tb_KeysList.getFont()).stringWidth("99999");
        tb_KeysList.getColumnModel().getColumn(0).setMaxWidth(w);
        tb_KeysList.getColumnModel().getColumn(1).setMaxWidth(40);
        tb_KeysList.getColumnModel().getColumn(1).setCellRenderer(new ImageCR(this));
        tb_KeysList.getColumnModel().getColumn(2).setCellRenderer(new LabelCR(this));

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

        fb_FindBar.initData();

        WButtonGroup group = menuPtn.getGroup("order-dir");
        if (group != null)
        {
            group.setSelected(userMdl.getCfg(ConsCfg.CFG_VIEW_LIST_ASC, ConsCfg.DEF_FALSE), true);
        }
        group = menuPtn.getGroup("order-key");
        if (group != null)
        {
            group.setSelected(userMdl.getCfg(ConsCfg.CFG_VIEW_LIST_KEY, "01"), true);
        }

        tb_KeysList.addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                // 右键事件处理
                if (evt.isPopupTrigger())
                {
                    showPopupMenu(evt);
                }
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                // 右键事件处理
                if (evt.isPopupTrigger())
                {
                    showPopupMenu(evt);
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
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER && !evt.isControlDown())
                {
                    showViewPtn();
                }
            }
        });

        safeMdl = mwizMdl.getKeysMdl();

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
    }

    public void showData()
    {
        mwizMdl.getGridMdl().listKeysByKind("0");
        hb_HintBar.showHint("共 " + mwizMdl.getGridMdl().getRowCount() + " 条数据");
    }

    @Override
    public boolean endSave()
    {
        return true;
    }

    public void findKeys(String meta)
    {
        keyMeta = meta;
        if (Char.isValidate(meta))
        {
            mwizMdl.getGridMdl().listKeysByMeta(meta);
        }
        else
        {
            mwizMdl.getGridMdl().listKeysByKind("0");
        }
        hb_HintBar.showHint("共 " + mwizMdl.getGridMdl().getRowCount() + " 条数据");
    }

    public boolean findHint(java.util.Date s, java.util.Date t)
    {
        return mwizMdl.getGridMdl().listTask(s, t);
    }

    public HintBar getHintPtn()
    {
        return hb_HintBar;
    }

    public void changeLabel(int label)
    {
        mwizMdl.getGridMdl().setKeysLabel(tb_KeysList.getSelectedRow(), label);
    }

    public void changeMajor(int major)
    {
        mwizMdl.getGridMdl().setKeysMajor(tb_KeysList.getSelectedRow(), major);
    }

    public void appendKeys()
    {
        EditPtn editDlg = getEditPtn();
        editDlg.setTitle(Lang.getLang(LangRes.P30F6203, "口令编辑"));
        KeysMdl keysMdl = mwizMdl.getKeysMdl();
        keysMdl.clear();
        editDlg.showData(keysMdl, true);
    }

    public void showEditPtn()
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

    public void showViewPtn()
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

    public void showMailPtn() throws Exception
    {
        int row = tb_KeysList.getSelectedRow();
        if (row < 0)
        {
            return;
        }

        KeysMdl keysMdl = mwizMdl.getKeysMdl();
        keysMdl.loadData(mwizMdl.getGridMdl().getKeysAt(row));

        MailDlg mailDlg = new MailDlg();
        mailDlg.initView();
        mailDlg.initLang();
        java.util.List<I1S2> mailList = mwizMdl.getGridMdl().wSelect(ConsDat.INDX_MAIL);
        mailDlg.initMail(mailList);
        if (mailList.size() < 1)
        {
            Lang.showMesg(this, null, "没有可用的邮件类型数据！");
            return;
        }
        java.util.List<I1S2> userList = mwizMdl.getGridMdl().wSelect(ConsDat.INDX_TEXT);
        mailDlg.initUser(userList);
        if (userList.size() < 1)
        {
            Lang.showMesg(this, null, "没有可用的文本类型数据！");
            return;
        }
        java.util.List<I1S2> pwdsList = mwizMdl.getGridMdl().wSelect(ConsDat.INDX_PWDS);
        mailDlg.initPwds(pwdsList);
        if (pwdsList.size() < 1)
        {
            Lang.showMesg(this, null, "没有可用的口令类型数据！");
            return;
        }
        if (javax.swing.JOptionPane.OK_OPTION != javax.swing.JOptionPane.showConfirmDialog(this, mailDlg, "登录确认", javax.swing.JOptionPane.OK_CANCEL_OPTION))
        {
            return;
        }

        String mail = mailList.get(mailDlg.getMail()).getK();
        String user = userList.get(mailDlg.getUser()).getK();
        String pwds = pwdsList.get(mailDlg.getPwds()).getK();

        String host = mail.substring(mail.indexOf('@') + 1);
        if (!com.magicpwd._util.Char.isValidate(host))
        {
            return;
        }

        final Connect connect = new Connect(mail, pwds);
        connect.setUsername(user);
        if (!connect.useDefault())
        {
            Lang.showMesg(this, null, "查找不到对应的服务信息，如有疑问请与作者联系！");
            return;
        }

        Reader reader = new Reader(connect);
        try
        {
            java.util.List<S1S1> list = reader.getUnReadMail();
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, null, exp.getLocalizedMessage());
        }
    }

    private void hintCallBack()
    {
        javax.swing.AbstractButton button = getMenuPtn().getButton("hint");
        if (button != null)
        {
            button.setSelected(true);
        }
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.sql.Timestamp s = new java.sql.Timestamp(cal.getTimeInMillis());
        cal.add(java.util.Calendar.DAY_OF_MONTH, 1);
        java.sql.Timestamp e = new java.sql.Timestamp(cal.getTimeInMillis());
        findHint(s, e);
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
        return editPtn;
    }

    public void findLast()
    {
        findKeys(keyMeta);
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
            showViewPtn();
        }
    }

    private void showPopupMenu(java.awt.event.MouseEvent evt)
    {
        int row = tb_KeysList.rowAtPoint(evt.getPoint());
        if (row < 0)
        {
            return;
        }

        tb_KeysList.setRowSelectionInterval(row, row);
        pm_MenuPop.show(tb_KeysList, evt.getX(), evt.getY());
        showKeysInfo(mwizMdl.getGridMdl().getKeysAt(row));
    }

    private void showKeysInfo(Keys keys)
    {
        WButtonGroup group = menuPtn.getGroup("label");
        if (group != null)
        {
            group.setSelected(Integer.toString(keys.getP30F0102()), true);
        }

        group = menuPtn.getGroup("major");
        if (group != null)
        {
            group.setSelected(Integer.toString(keys.getP30F0103()), true);
        }
    }
    private HintBar hb_HintBar;
    private FindBar fb_FindBar;
    private javax.swing.JTable tb_KeysList;
    private javax.swing.JToolBar tb_ToolBar;
    private javax.swing.JPopupMenu pm_MenuPop;
}
