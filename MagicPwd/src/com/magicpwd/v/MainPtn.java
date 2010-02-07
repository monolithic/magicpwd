package com.magicpwd.v;

import com.magicpwd.MagicPwd;
import com.magicpwd._bean.AreaBean;
import com.magicpwd._bean.DateBean;
import com.magicpwd._bean.FileBean;
import com.magicpwd._bean.GmtpBean;
import com.magicpwd._bean.GuidBean;
import com.magicpwd._bean.LogoBean;
import com.magicpwd._bean.InfoBean;
import com.magicpwd._bean.LinkBean;
import com.magicpwd._bean.MailBean;
import com.magicpwd._bean.MarkBean;
import com.magicpwd._bean.MetaBean;
import com.magicpwd._bean.NoteBean;
import com.magicpwd._bean.PwdsBean;
import com.magicpwd._bean.TextBean;
import com.magicpwd._comn.Guid;
import com.magicpwd._comn.Kind;
import com.magicpwd._comn.S1S2;
import com.magicpwd._comn.S1S3;
import com.magicpwd._comn.Item;
import com.magicpwd._comn.Keys;
import com.magicpwd._comn.Meta;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IBackCall;
import com.magicpwd._face.IEditBean;
import com.magicpwd._face.IGridView;
import com.magicpwd._util.Lang;
import com.magicpwd._prop.HistProp;
import com.magicpwd._user.UserSign;
import com.magicpwd._util.Desk;
import com.magicpwd._util.Jcsv;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.c.FindEvt;
import com.magicpwd.c.InfoEvt;
import com.magicpwd.c.MenuEvt;
import com.magicpwd.c.ToolEvt;
import com.magicpwd.d.DBA3000;
import com.magicpwd.m.GridMdl;
import com.magicpwd.m.TimeOut;
import com.magicpwd.m.UserMdl;
import com.magicpwd.r.KindTN;
import com.magicpwd.r.ListCR;
import com.magicpwd.r.TreeCR;
import com.magicpwd.x.DatDialog;
import com.magicpwd.x.MdiDialog;
import com.magicpwd.x.MpsDialog;

public class MainPtn extends javax.swing.JPanel implements MenuEvt, ToolEvt, InfoEvt, FindEvt, IGridView
{

    private java.awt.CardLayout cl_CardProp;
    private MpsDialog mp_MpsDialog;
    private IEditBean[] editBean;
    private MenuPop gridMenu;
    private MenuPop treeMenu;
    private MenuPop listMenu;
    /**
     * 口令列表上次选择索引
     */
    private int ls_LastIndx = -1;
    /**
     * 属性列表上次选择索引
     */
    private int tb_LastIndx = -1;
    private javax.swing.border.TitledBorder border;

    public MainPtn()
    {
    }

    public void initView()
    {
        initGuidView();
        initPropView();
        initUserView();
        initBaseView();
    }

    public void initLang()
    {
        initGuidLang();
        initPropLang();
        initUserLang();
        initBaseLang();
    }

    public void initData()
    {
        if (UserMdl.getCfg().isEditViw())
        {
            showPropEdit(UserMdl.getCfg().isEditWnd());
        }

        Util.addHideAction(getActionMap(), getInputMap(WHEN_IN_FOCUSED_WINDOW), this);
        Util.addEditAction(getActionMap(), getInputMap(WHEN_IN_FOCUSED_WINDOW), this);
        Util.addFileAction(getActionMap(), getInputMap(WHEN_IN_FOCUSED_WINDOW), this);
        Util.addViewAction(getActionMap(), getInputMap(WHEN_IN_FOCUSED_WINDOW), this);
        Util.addDataAction(getActionMap(), getInputMap(WHEN_IN_FOCUSED_WINDOW), this);
        MagicPwd.getWInfoBar().initData();
    }

    @Override
    public void dataExptActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.tree.TreePath path = tr_GuidTree.getSelectionPath();
        if (path == null)
        {
            Lang.showMesg(this, LangRes.P30F7A20, "请选择您要导出数据的类别信息！");
            tr_GuidTree.requestFocus();
            return;
        }

        UserSign us = new UserSign(ConsEnv.SIGN_RS, MagicPwd.getFrame());
        us.setConfrmBackCall(new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                return exportData();
            }
        });
        us.init();
    }

    @Override
    public void dataSKeyActionPerformed(java.awt.event.ActionEvent evt)
    {
        MdiDialog.getInstance(this).showProp(ConsEnv.PROP_SKEY, false);
    }

    @Override
    public void dataImptActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.tree.TreePath path = tr_GuidTree.getSelectionPath();
        if (path == null)
        {
            Lang.showMesg(this, LangRes.P30F7A02, "");
            tr_GuidTree.requestFocus();
            return;
        }

        UserSign us = new UserSign(ConsEnv.SIGN_RS, MagicPwd.getFrame());
        us.setConfrmBackCall(new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                return importData();
            }
        });
        us.init();
    }

    @Override
    public void editAreaActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (checkData())
        {
            tb_LastIndx = tb_KeysView.getSelectedRow();
            if (tb_LastIndx < ConsEnv.PWDS_HEAD_SIZE)
            {
                tb_LastIndx = tb_KeysView.getRowCount();
            }
            showPropEdit(UserMdl.getGridMdl().wAppend(tb_LastIndx, ConsDat.INDX_AREA), true);
            tb_KeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
        }
    }

    @Override
    public void editDateActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (checkData())
        {
            tb_LastIndx = tb_KeysView.getSelectedRow();
            if (tb_LastIndx < ConsEnv.PWDS_HEAD_SIZE)
            {
                tb_LastIndx = tb_KeysView.getRowCount();
            }
            showPropEdit(UserMdl.getGridMdl().wAppend(tb_LastIndx, ConsDat.INDX_DATE), true);
            tb_KeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
        }
    }

    @Override
    public void editFileActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (checkData())
        {
            tb_LastIndx = tb_KeysView.getSelectedRow();
            if (tb_LastIndx < ConsEnv.PWDS_HEAD_SIZE)
            {
                tb_LastIndx = tb_KeysView.getRowCount();
            }
            showPropEdit(UserMdl.getGridMdl().wAppend(tb_LastIndx, ConsDat.INDX_FILE), true);
            tb_KeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
        }
    }

    @Override
    public void editLinkActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (checkData())
        {
            tb_LastIndx = tb_KeysView.getSelectedRow();
            if (tb_LastIndx < ConsEnv.PWDS_HEAD_SIZE)
            {
                tb_LastIndx = tb_KeysView.getRowCount();
            }
            showPropEdit(UserMdl.getGridMdl().wAppend(tb_LastIndx, ConsDat.INDX_LINK), true);
            tb_KeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
        }
    }

    @Override
    public void editMailActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (checkData())
        {
            tb_LastIndx = tb_KeysView.getSelectedRow();
            if (tb_LastIndx < ConsEnv.PWDS_HEAD_SIZE)
            {
                tb_LastIndx = tb_KeysView.getRowCount();
            }
            showPropEdit(UserMdl.getGridMdl().wAppend(tb_LastIndx, ConsDat.INDX_MAIL), true);
            tb_KeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
        }
    }

    @Override
    public void editPwdsActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (checkData())
        {
            tb_LastIndx = tb_KeysView.getSelectedRow();
            if (tb_LastIndx < ConsEnv.PWDS_HEAD_SIZE)
            {
                tb_LastIndx = tb_KeysView.getRowCount();
            }
            showPropEdit(UserMdl.getGridMdl().wAppend(tb_LastIndx, ConsDat.INDX_PWDS), true);
            tb_KeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
        }
    }

    @Override
    public void editTextActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (checkData())
        {
            tb_LastIndx = tb_KeysView.getSelectedRow();
            if (tb_LastIndx < ConsEnv.PWDS_HEAD_SIZE)
            {
                tb_LastIndx = tb_KeysView.getRowCount();
            }
            showPropEdit(UserMdl.getGridMdl().wAppend(tb_LastIndx, ConsDat.INDX_TEXT), true);
            tb_KeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
        }
    }

    @Override
    public void editFindActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (!UserMdl.getCfg().isFindViw())
        {
            MagicPwd.getWFindBar().setVisible(true);
            MagicPwd.getWMenuBar().setViewFindSelected(true);
            UserMdl.getCfg().setFindViw(true);
        }
        MagicPwd.getWFindBar().requestFocus();
    }

    @Override
    public void updtAreaActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (checkData())
        {
            int idx = tb_KeysView.getSelectedRow();
            if (idx > ConsEnv.PWDS_HEAD_SIZE && idx < tb_KeysView.getRowCount())
            {
                Item tplt = UserMdl.getGridMdl().getItemAt(idx);
                if (tplt.getType() != ConsDat.INDX_AREA)
                {
                    tplt.setType(ConsDat.INDX_AREA);
                    showPropEdit(tplt, true);
                    UserMdl.getGridMdl().setModified(true);
                }
            }
        }
    }

    @Override
    public void updtDateActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (checkData())
        {
            int idx = tb_KeysView.getSelectedRow();
            if (idx > ConsEnv.PWDS_HEAD_SIZE && idx < tb_KeysView.getRowCount())
            {
                Item tplt = UserMdl.getGridMdl().getItemAt(idx);
                if (tplt.getType() != ConsDat.INDX_DATE)
                {
                    tplt.setType(ConsDat.INDX_DATE);
                    showPropEdit(tplt, true);
                    UserMdl.getGridMdl().setModified(true);
                }
            }
        }
    }

    @Override
    public void updtFileActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (checkData())
        {
            int idx = tb_KeysView.getSelectedRow();
            if (idx > ConsEnv.PWDS_HEAD_SIZE && idx < tb_KeysView.getRowCount())
            {
                Item tplt = UserMdl.getGridMdl().getItemAt(idx);
                if (tplt.getType() != ConsDat.INDX_FILE)
                {
                    tplt.setType(ConsDat.INDX_FILE);
                    showPropEdit(tplt, true);
                    UserMdl.getGridMdl().setModified(true);
                }
            }
        }
    }

    @Override
    public void updtLinkActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (checkData())
        {
            int idx = tb_KeysView.getSelectedRow();
            if (idx > ConsEnv.PWDS_HEAD_SIZE && idx < tb_KeysView.getRowCount())
            {
                Item tplt = UserMdl.getGridMdl().getItemAt(idx);
                if (tplt.getType() != ConsDat.INDX_LINK)
                {
                    tplt.setType(ConsDat.INDX_LINK);
                    showPropEdit(tplt, true);
                    UserMdl.getGridMdl().setModified(true);
                }
            }
        }
    }

    @Override
    public void updtMailActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (checkData())
        {
            int idx = tb_KeysView.getSelectedRow();
            if (idx > ConsEnv.PWDS_HEAD_SIZE && idx < tb_KeysView.getRowCount())
            {
                Item tplt = UserMdl.getGridMdl().getItemAt(idx);
                if (tplt.getType() != ConsDat.INDX_MAIL)
                {
                    tplt.setType(ConsDat.INDX_MAIL);
                    showPropEdit(tplt, true);
                    UserMdl.getGridMdl().setModified(true);
                }
            }
        }
    }

    @Override
    public void updtPwdsActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (checkData())
        {
            int idx = tb_KeysView.getSelectedRow();
            if (idx > ConsEnv.PWDS_HEAD_SIZE && idx < tb_KeysView.getRowCount())
            {
                Item tplt = UserMdl.getGridMdl().getItemAt(idx);
                if (tplt.getType() != ConsDat.INDX_PWDS)
                {
                    tplt.setType(ConsDat.INDX_PWDS);
                    showPropEdit(tplt, true);
                    UserMdl.getGridMdl().setModified(true);
                }
            }
        }
    }

    @Override
    public void updtTextActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (checkData())
        {
            int idx = tb_KeysView.getSelectedRow();
            if (idx > ConsEnv.PWDS_HEAD_SIZE && idx < tb_KeysView.getRowCount())
            {
                Item tplt = UserMdl.getGridMdl().getItemAt(idx);
                if (tplt.getType() != ConsDat.INDX_TEXT)
                {
                    tplt.setType(ConsDat.INDX_TEXT);
                    showPropEdit(tplt, true);
                    UserMdl.getGridMdl().setModified(true);
                }
            }
        }
    }

    @Override
    public void fileApndActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (UserMdl.getGridMdl().isModified())
        {
            if (Lang.showFirm(this, LangRes.P30F7A09, "", "") != javax.swing.JOptionPane.YES_OPTION)
            {
                return;
            }
        }

        tb_LastIndx = 0;
        UserMdl.getGridMdl().clear();
        if (!UserMdl.getCfg().isEditViw())
        {
            MagicPwd.getWMenuBar().setViewPropSelected(true);
            MagicPwd.getWMenuBar().setViewSideSelected(true);
            UserMdl.getCfg().setEditViw(true);
            UserMdl.getCfg().setEditWnd(true);
            showPropEdit(true);
        }
        showPropEdit(UserMdl.getGridMdl().initGuid(), true);
    }

    @Override
    public void fileCopyActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void fileDeltActionPerformed(java.awt.event.ActionEvent evt)
    {
        int index = ls_GuidList.getSelectedIndex();
        if (index < 0 || index >= UserMdl.getListMdl().getSize())
        {
            return;
        }

        if (Lang.showFirm(this, LangRes.P30F7A0A, "") != javax.swing.JOptionPane.YES_OPTION)
        {
            return;
        }
        if (Lang.showFirm(this, LangRes.P30F7A0B, "") != javax.swing.JOptionPane.NO_OPTION)
        {
            return;
        }
        UserMdl.getListMdl().wDelete(index);
    }

    @Override
    public void fileOpenActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void fileSaveActionPerformed(java.awt.event.ActionEvent evt)
    {
        // 是否需要保存
        if (tb_KeysView.getRowCount() < ConsEnv.PWDS_HEAD_SIZE)
        {
            return;
        }

        GridMdl gm = UserMdl.getGridMdl();

        // 数据未被修改
        if (!gm.isModified())
        {
            //Lang.showMesg(this, LangRes.P30F7A27, "您未曾修改过数据，不需要保存！");
            return;
        }

        // 口令类别检测
        Guid guid = (Guid) gm.getItemAt(ConsEnv.PWDS_HEAD_GUID);
        if (!Util.isValidate(guid.getData()))
        {
            javax.swing.tree.TreePath path = tr_GuidTree.getSelectionPath();
            if (path == null)
            {
                Lang.showMesg(this, LangRes.P30F7A0D, "请选择口令类别信息！");
                tr_GuidTree.requestFocus();
                return;
            }

            KindTN node = (KindTN) path.getLastPathComponent();
            Kind kind = (Kind) node.getUserObject();
            gm.getItemAt(ConsEnv.PWDS_HEAD_GUID).setData(kind.getC2010103());
        }

        // 标题为空检测
        Meta meta = (Meta) gm.getItemAt(ConsEnv.PWDS_HEAD_META);
        if (!Util.isValidate(meta.getName()))
        {
            Lang.showMesg(this, LangRes.P30F7A0C, "请输入口令标题！");
            tb_KeysView.setRowSelectionInterval(1, 1);
            showPropEdit(meta, true);
            return;
        }

        try
        {
            ls_LastIndx = -1;
            tb_LastIndx = -1;
            gm.saveData(MagicPwd.getWToolBar().isHistBackSelected(), true);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30F7A0E, "口令数据保存失败，请重新启动本程序后再次尝试！");
        }

        // 数据新增的情况下，需要重新显示列表信息
        if (gm.isUpdate())
        {
            UserMdl.getListMdl().updtName(ls_GuidList.getSelectedIndex(), gm.getItemAt(ConsEnv.PWDS_HEAD_META).getName());
        }
        else
        {
            UserMdl.getListMdl().listName(guid.getData());
        }

        showPropEdit();
        TimeOut.readNote();
    }

    @Override
    public void fileExitActionPerformed(java.awt.event.ActionEvent evt)
    {
        TimeOut.setStopWork(true);
        MagicPwd.exit(0);
    }

    @Override
    public void fileHideActionPerformed(java.awt.event.ActionEvent evt)
    {
        MagicPwd.showMiniPtn();
        MdiDialog md = MdiDialog.getInstance(this);
        if (md != null && md.isVisible())
        {
            md.setVisible(false);
        }
        if (mp_MpsDialog != null && mp_MpsDialog.isVisible())
        {
            mp_MpsDialog.setVisible(false);
        }

        // Save Temperary Data
        if (UserMdl.getGridMdl().isModified())
        {
            UserMdl.getGridMdl().getItemAt(ConsEnv.PWDS_HEAD_GUID).setData(ConsDat.HASH_ROOT);
            try
            {
                UserMdl.getGridMdl().saveData(true, false);
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }
        showPropEdit();
    }

    @Override
    public void helpHelpActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (!java.awt.Desktop.isDesktopSupported())
        {
            Lang.showMesg(this, LangRes.P30F7A0F, "");
        }

        java.io.File help = new java.io.File("help", "index.html");
        if (!help.exists())
        {
            Lang.showMesg(this, LangRes.P30F7A10, "");
            return;
        }
        try
        {
            java.awt.Desktop.getDesktop().browse(help.toURI());
        }
        catch (java.io.IOException exp)
        {
            Logs.exception(exp);
        }
    }

    @Override
    public void helpInfoActionPerformed(java.awt.event.ActionEvent evt)
    {
        StringBuffer text = new StringBuffer();
        text.append("<html><body>");
        text.append(ConsEnv.SOFTNAME).append(" ").append(ConsEnv.VERSIONS).append("<br />");
        text.append("Build ").append(ConsEnv.BUILDER).append("<br />");
        text.append("<a href=\"").append(ConsEnv.HOMEPAGE).append("\">").append(ConsEnv.HOMEPAGE).append("</a>");
        text.append("</body></html>");
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(Util.getImage(ConsEnv.ICON_LOGO_0032));
        javax.swing.JOptionPane.showMessageDialog(this, text.toString(), "关于", javax.swing.JOptionPane.OK_OPTION, icon);
    }

    @Override
    public void helpSiteActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (!java.awt.Desktop.isDesktopSupported())
        {
            Lang.showMesg(this, LangRes.P30F7A0F, "");
        }

        try
        {
            java.awt.Desktop.getDesktop().browse(new java.net.URI(ConsEnv.HOMEPAGE));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
    }

    @Override
    public void helpMailActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (!java.awt.Desktop.isDesktopSupported())
        {
            Lang.showMesg(this, LangRes.P30F7A11, "");
        }

        try
        {
            java.awt.Desktop.getDesktop().mail(new java.net.URI("mailto:Amonsoft@163.com"));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
    }

    @Override
    public void helpUpdtActionPerformed(java.awt.event.ActionEvent evt)
    {
        try
        {
            boolean b = Util.checkUpdate(ConsEnv.SOFTHASH, ConsEnv.VERSIONS);
            if (b)
            {
                if (Lang.showFirm(this, LangRes.P30F7A12, "") == javax.swing.JOptionPane.YES_OPTION)
                {
                    Desk.browse(ConsEnv.HOMEPAGE);
                }
            }
            else
            {
                Lang.showMesg(this, LangRes.P30F7A13, "");
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30F7A14, "");
        }
    }

    @Override
    public void kindApndActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.tree.TreePath path = tr_GuidTree.getSelectionPath();
        if (path == null)
        {
            return;
        }

        String kindName = javax.swing.JOptionPane.showInputDialog(Lang.getLang(LangRes.P30F7A15, ""));
        if (kindName == null)
        {
            return;
        }
        if (!Util.isValidate(kindName))
        {
            Lang.showMesg(this, LangRes.P30F7A16, "");
            return;
        }

        KindTN p = (KindTN) path.getLastPathComponent();
        Kind c = new Kind();
        c.setC2010101(p.getChildCount());
        c.setC2010105(kindName);
        c.setC2010106(kindName);
        UserMdl.getTreeMdl().wAppend(path, c);
    }

    @Override
    public void kindDeltActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.tree.TreePath path = tr_GuidTree.getSelectionPath();
        if (path == null)
        {
            return;
        }

        if (Lang.showFirm(this, LangRes.P30F7A1A, "") == javax.swing.JOptionPane.YES_OPTION)
        {
            UserMdl.getTreeMdl().wRemove(path);
        }
    }

    @Override
    public void kindMoveActionPerformed(java.awt.event.ActionEvent evt)
    {
        DatDialog dat = new DatDialog(new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                return changeKind(params[0]);
            }
        });
        dat.initView();
        dat.initLang();
        dat.setTitle(Lang.getLang(LangRes.P30F4206, "把记录迁移到..."));
    }

    @Override
    public void kindUpdtActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.tree.TreePath tp = tr_GuidTree.getSelectionPath();
        if (tp == null)
        {
            return;
        }

        Object obj = tp.getLastPathComponent();
        if (obj == null || !(obj instanceof KindTN))
        {
            return;
        }

        KindTN node = (KindTN) obj;
        Kind c = (Kind) node.getUserObject();

        String name = javax.swing.JOptionPane.showInputDialog(Lang.getLang(LangRes.P30F7A15, ""), c.getC2010105());
        if (name == null)
        {
            return;
        }
        if (!Util.isValidate(name))
        {
            Lang.showMesg(this, LangRes.P30F7A17, "");
            return;
        }
        c.setC2010105(name);
        c.setC2010106(name);
        DBA3000.updateKindData(c);
    }

    @Override
    public void userCreateActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void userSwitchActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void userUpdateActionPerformed(java.awt.event.ActionEvent evt)
    {
        UserSign us = new UserSign(ConsEnv.SIGN_PK, MagicPwd.getFrame());
        us.init();
    }

    @Override
    public void userSecretActionPerformed(java.awt.event.ActionEvent evt)
    {
        String skey = UserMdl.getCfg().getCfg(ConsCfg.CFG_USER_SKEY);
        if (skey != null && skey.length() == 224)
        {
            Lang.showMesg(this, LangRes.P30F7A28, "您已经设置过安全口令！");
            return;
        }

        UserSign us = new UserSign(ConsEnv.SIGN_SK, MagicPwd.getFrame());
        us.setConfrmBackCall(new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                MagicPwd.getWMenuBar().setUserSecretEnabled();
                return true;
            }
        });
        us.init();
    }

    @Override
    public void viewFindActionPerformed(java.awt.event.ActionEvent evt)
    {
        boolean b = !UserMdl.getCfg().isFindViw();
        MagicPwd.getWFindBar().setVisible(b);
        MagicPwd.getFrame().pack();

        MagicPwd.getWMenuBar().setViewFindSelected(b);
        UserMdl.getCfg().setFindViw(b);
    }

    @Override
    public void viewInfoActionPerformed(java.awt.event.ActionEvent evt)
    {
        boolean b = !UserMdl.getCfg().isInfoViw();
        MagicPwd.getWInfoBar().setVisible(b);
        MagicPwd.getFrame().pack();

        MagicPwd.getWMenuBar().setViewInfoSelected(b);
        UserMdl.getCfg().setInfoViw(b);
    }

    @Override
    public void viewMenuActionPerformed(java.awt.event.ActionEvent evt)
    {
        boolean b = !UserMdl.getCfg().isMenuViw();
        MagicPwd.getWMenuBar().setVisible(b);
        MagicPwd.getFrame().pack();

        MagicPwd.getWMenuBar().setViewMenuSelected(b);
        UserMdl.getCfg().setMenuViw(b);
    }

    @Override
    public void viewEditActionPerformed(java.awt.event.ActionEvent evt)
    {
        boolean b = !UserMdl.getCfg().isEditViw();
        if (b)
        {
            showPropEdit(UserMdl.getCfg().isEditWnd());
        }
        else
        {
            pl_KeysEdit.setVisible(b);
            if (mp_MpsDialog != null)
            {
                mp_MpsDialog.setVisible(b);
            }
        }
        UserMdl.getCfg().setEditViw(b);
    }

    @Override
    public void viewSideActionPerformed(java.awt.event.ActionEvent evt)
    {
        boolean b = !UserMdl.getCfg().isEditWnd();

        // if (!UserMdl.getCfg().isEditViw())
        // {
        // MagicPwd.getWMenuBar().setViewSideSelected(false);
        // MagicPwd.getWToolBar().setPropSideSelected(false);
        // return;
        // }

        if (UserMdl.getCfg().isEditViw())
        {
            showPropEdit(b);
        }
        // MagicPwd.getFrame().pack();

        MagicPwd.getWMenuBar().setViewSideSelected(b);
        MagicPwd.getWToolBar().setPropSideSelected(b);

        UserMdl.getCfg().setEditWnd(b);
    }

    @Override
    public void viewToolActionPerformed(java.awt.event.ActionEvent evt)
    {
        boolean b = !UserMdl.getCfg().isToolViw();
        MagicPwd.getWToolBar().setVisible(b);
        MagicPwd.getFrame().pack();

        MagicPwd.getWMenuBar().setViewToolSelected(b);
        UserMdl.getCfg().setToolViw(b);
    }

    @Override
    public void viewTop1ActionPerformed(java.awt.event.ActionEvent evt)
    {
        boolean b = !UserMdl.getCfg().isViewTop();
        MagicPwd.getFrame().setAlwaysOnTop(b);

        UserMdl.getCfg().setViewTop(b);
    }

    @Override
    public void histBackActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void editNextActionPerformed(java.awt.event.ActionEvent evt)
    {
        int t = tb_LastIndx + 1;
        if (t <= 3 || t >= tb_KeysView.getRowCount())
        {
            return;
        }
        UserMdl.getGridMdl().wMoveto(tb_LastIndx, t);
        tb_LastIndx = t;
        Util.scrollToVisible(tb_KeysView, tb_LastIndx, 0, true);
        tb_KeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
    }

    @Override
    public void editPrevActionPerformed(java.awt.event.ActionEvent evt)
    {
        int t = tb_LastIndx - 1;
        if (t < 3)
        {
            return;
        }
        UserMdl.getGridMdl().wMoveto(tb_LastIndx, t);
        tb_LastIndx = t;
        Util.scrollToVisible(tb_KeysView, tb_LastIndx, 0, true);
        tb_KeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
    }

    @Override
    public void toDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void findActionPerformed(java.awt.event.ActionEvent evt)
    {
        String text = MagicPwd.getWFindBar().getSearchText();
        if (!Util.isValidate(text))
        {
            Lang.showMesg(this, LangRes.P30F7A18, "");
            MagicPwd.getWFindBar().requestFocus();
            return;
        }

        boolean b = UserMdl.getListMdl().findName(text);
        if (!b)
        {
            Lang.showMesg(this, LangRes.P30F7A19, "");
            MagicPwd.getWFindBar().requestFocus();
        }

        tr_GuidTree.setSelectionPath(null);
    }

    @Override
    public void copyDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        int row = tb_KeysView.getSelectedRow();
        if (row < 0 || row > tb_KeysView.getRowCount() - 1)
        {
            return;
        }
        Item tplt = UserMdl.getGridMdl().getItemAt(row);
        Util.setClipboardContents(tplt.getData(), UserMdl.getCfg().getClnClp());
    }

    @Override
    public void deltDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        int row = tb_KeysView.getSelectedRow();
        if (row < ConsEnv.PWDS_HEAD_SIZE || row > tb_KeysView.getRowCount() - 1)
        {
            return;
        }
        if (Lang.showFirm(this, LangRes.P30F1A01, "") == javax.swing.JOptionPane.YES_OPTION)
        {
            UserMdl.getGridMdl().wRemove(row);
            selectNext(false);
        }
    }

    @Override
    public void histViewActionPerformed(java.awt.event.ActionEvent evt)
    {
        S1S2 item = (S1S2) ls_GuidList.getSelectedValue();
        if (item == null)
        {
            return;
        }

        MdiDialog.getInstance(this).showProp(ConsEnv.PROP_HIST, false);
    }

    @Override
    public void selectNext(boolean next)
    {
        int t = tb_KeysView.getRowCount() - 1;
        if (next)
        {
            tb_LastIndx += 1;
            if (tb_LastIndx > t)
            {
                tb_LastIndx = t;
                UserMdl.getGridMdl().fireTableDataChanged();
            }
            tb_KeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);

            Item tplt = UserMdl.getGridMdl().getItemAt(tb_LastIndx);
            showPropEdit(tplt, true);
        }
        else
        {
            if (tb_LastIndx < 0 || tb_LastIndx > t)
            {
                tb_LastIndx = t;
            }
            UserMdl.getGridMdl().fireTableDataChanged();
            tb_KeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
            tb_KeysView.requestFocus();
        }

        Util.scrollToVisible(tb_KeysView, tb_LastIndx, 0, true);
        showPropEdit(UserMdl.getGridMdl().getItemAt(tb_LastIndx), true);
    }

    private void initPropView()
    {
        pl_KeysEdit = new javax.swing.JPanel();
        pl_KeysEdit.setLayout(new java.awt.BorderLayout());
        border = javax.swing.BorderFactory.createTitledBorder(Lang.getLang(LangRes.P30F7305, ""));
        pl_KeysEdit.setBorder(border);
        pl_KeysEdit.setVisible(false);

        cl_CardProp = new java.awt.CardLayout();
        pl_CardProp = new javax.swing.JPanel();
        pl_CardProp.setLayout(cl_CardProp);
        editBean = new IEditBean[ConsDat.INDX_SIZE];
        int idx = 0;

        InfoBean beanInfo = new InfoBean(this);
        beanInfo.initView();
        pl_CardProp.add(ConsEnv.BEAN_INFO, beanInfo);
        editBean[idx++] = beanInfo;

        TextBean beanText = new TextBean(this);
        beanText.initView();
        pl_CardProp.add(ConsEnv.BEAN_TEXT, beanText);
        editBean[idx++] = beanText;

        PwdsBean beanPwds = new PwdsBean(this);
        beanPwds.initView();
        pl_CardProp.add(ConsEnv.BEAN_PWDS, beanPwds);
        editBean[idx++] = beanPwds;

        LinkBean beanLink = new LinkBean(this);
        beanLink.initView();
        pl_CardProp.add(ConsEnv.BEAN_LINK, beanLink);
        editBean[idx++] = beanLink;

        MailBean beanMail = new MailBean(this);
        beanMail.initView();
        pl_CardProp.add(ConsEnv.BEAN_MAIL, beanMail);
        editBean[idx++] = beanMail;

        DateBean beanDate = new DateBean(this);
        beanDate.initView();
        pl_CardProp.add(ConsEnv.BEAN_DATE, beanDate);
        editBean[idx++] = beanDate;

        AreaBean beanArea = new AreaBean(this);
        beanArea.initView();
        pl_CardProp.add(ConsEnv.BEAN_AREA, beanArea);
        editBean[idx++] = beanArea;

        FileBean beanFile = new FileBean(this);
        beanFile.initView();
        pl_CardProp.add(ConsEnv.BEAN_FILE, beanFile);
        editBean[idx++] = beanFile;

        GmtpBean beanGmtp = new GmtpBean(this);
        beanGmtp.initView();
        pl_CardProp.add(ConsEnv.BEAN_GMTP, beanGmtp);
        editBean[idx++] = beanGmtp;

        GuidBean beanGuid = new GuidBean(this);
        beanGuid.initView();
        pl_CardProp.add(ConsEnv.BEAN_GUID, beanGuid);
        editBean[idx++] = beanGuid;

        MetaBean beanMeta = new MetaBean(this);
        beanMeta.initView();
        pl_CardProp.add(ConsEnv.BEAN_META, beanMeta);
        editBean[idx++] = beanMeta;

        LogoBean beanIcon = new LogoBean(this);
        beanIcon.initView();
        pl_CardProp.add(ConsEnv.BEAN_ICON, beanIcon);
        editBean[idx++] = beanIcon;

        NoteBean beanNote = new NoteBean(this);
        beanNote.initView();
        pl_CardProp.add(ConsEnv.BEAN_NOTE, beanNote);
        editBean[idx++] = beanNote;

        MarkBean beanMark = new MarkBean(this);
        beanMark.initView();
        pl_CardProp.add(ConsEnv.BEAN_MARK, beanMark);
        editBean[idx++] = beanMark;
    }

    private void initGuidView()
    {
        pl_KeysGuid = new javax.swing.JPanel();

        javax.swing.JSplitPane sp = new javax.swing.JSplitPane();
        sp.setDividerLocation(180);
        sp.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        sp.setOneTouchExpandable(true);

        treeMenu = new MenuPop(MenuPop.MENU_TREE);
        treeMenu.initView();
        treeMenu.setActionEvent(this);

        tr_GuidTree = new javax.swing.JTree();
        tr_GuidTree.setComponentPopupMenu(treeMenu);
        tr_GuidTree.setCellRenderer(new TreeCR());
        tr_GuidTree.setModel(UserMdl.getTreeMdl());
        tr_GuidTree.getSelectionModel().setSelectionMode(javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION);
        javax.swing.ToolTipManager.sharedInstance().registerComponent(tr_GuidTree);
        tr_GuidTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener()
        {

            @Override
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt)
            {
                tr_GuidTreeValueChanged(evt);
            }
        });

        javax.swing.JScrollPane sp1 = new javax.swing.JScrollPane();
        sp1.setViewportView(tr_GuidTree);
        sp.setTopComponent(sp1);

        listMenu = new MenuPop(MenuPop.MENU_LIST);
        listMenu.initView();
        listMenu.setActionEvent(this);

        ls_GuidList = new javax.swing.JList();
        ls_GuidList.setComponentPopupMenu(listMenu);
        ls_GuidList.setCellRenderer(new ListCR());
        ls_GuidList.setModel(UserMdl.getListMdl());
        ls_GuidList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ls_GuidList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {

            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                ls_DataListValueChanged(evt);
            }
        });

        javax.swing.JScrollPane sp2 = new javax.swing.JScrollPane();
        sp2.setViewportView(ls_GuidList);
        sp.setRightComponent(sp2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_KeysGuid);
        pl_KeysGuid.setLayout(layout);

        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE);
        hsg.addGap(3);
        layout.setHorizontalGroup(hsg);
//        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
//        hpg.addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE);
//        layout.setHorizontalGroup(hpg);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE);
        layout.setVerticalGroup(vsg);
//        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
//        vpg.addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE);
//        layout.setVerticalGroup(vpg);
    }

    private void initUserView()
    {
        pl_KeysInfo = new javax.swing.JPanel();
        FindBar fb = MagicPwd.getWFindBar();

        gridMenu = new MenuPop(MenuPop.MENU_GRID);
        gridMenu.initView();
        gridMenu.setActionEvent(this);

        tb_KeysView = new javax.swing.JTable();
        tb_KeysView.setModel(UserMdl.getGridMdl());
        tb_KeysView.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        javax.swing.ActionMap actionMap = tb_KeysView.getActionMap();
        javax.swing.InputMap inputMap = tb_KeysView.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        Util.addSortAction(actionMap, inputMap, this);
        // 添加快捷键
        actionMap.put(ConsEnv.EVENT_EDIT_FCUS, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                tb_KeysView.requestFocus();
            }
        });
        inputMap.put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK), ConsEnv.EVENT_EDIT_FCUS);

        tb_KeysView.addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                tb_ItemListMouseReleased(evt);
            }
        });
        tb_KeysView.addKeyListener(new java.awt.event.KeyAdapter()
        {

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                tb_ItemListKeyReleased(evt);
            }
        });

        int w = tb_KeysView.getFontMetrics(tb_KeysView.getFont()).stringWidth("999999");
        tb_KeysView.getColumnModel().getColumn(0).setPreferredWidth(w);
        tb_KeysView.getColumnModel().getColumn(1).setPreferredWidth(365 - w);

        javax.swing.JScrollPane sp1 = new javax.swing.JScrollPane();
        sp1.setViewportView(tb_KeysView);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_KeysInfo);
        pl_KeysInfo.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg.addComponent(sp1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE);
        hpg.addComponent(pl_KeysEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg.addComponent(fb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);

        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addGap(5);
        hsg.addGroup(hpg);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addComponent(fb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(sp1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(pl_KeysEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setVerticalGroup(vsg);
    }

    private void initBaseView()
    {
        javax.swing.JSplitPane sp = new javax.swing.JSplitPane();
        InfoBar ib = MagicPwd.getWInfoBar();

        sp.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        sp.setDividerLocation(160);
        sp.setOneTouchExpandable(true);

        sp.setLeftComponent(pl_KeysGuid);

        sp.setRightComponent(pl_KeysInfo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE);
        hsg.addContainerGap();

        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg.addGroup(hsg);
        hpg.addComponent(ib, javax.swing.GroupLayout.PREFERRED_SIZE, 560, Short.MAX_VALUE);

        layout.setHorizontalGroup(hpg);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE);
        vsg.addComponent(ib, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setVerticalGroup(vsg);
    }

    private void initGuidLang()
    {
        gridMenu.initLang();
    }

    private void initPropLang()
    {
        for (IEditBean bean : editBean)
        {
            bean.initLang();
        }
    }

    private void initUserLang()
    {
    }

    private void initBaseLang()
    {
        treeMenu.initLang();
        listMenu.initLang();
    }

    private void tr_GuidTreeValueChanged(javax.swing.event.TreeSelectionEvent evt)
    {
        javax.swing.tree.TreePath tp = tr_GuidTree.getSelectionPath();
        if (tp == null)
        {
            return;
        }

        Object obj = tp.getLastPathComponent();
        if (obj instanceof KindTN)
        {
            KindTN item = (KindTN) obj;
            Kind kv = (Kind) item.getUserObject();
            UserMdl.getListMdl().listName(kv.getC2010103());
        }
    }

    private void ls_DataListValueChanged(javax.swing.event.ListSelectionEvent evt)
    {
        int i = ls_GuidList.getSelectedIndex();
        if (i < 0)
        {
            ls_LastIndx = i;
            return;
        }
        if (i == ls_LastIndx)
        {
            return;
        }

        if (UserMdl.getGridMdl().isModified())
        {
            if (Lang.showFirm(this, LangRes.P30F7A09, "") != javax.swing.JOptionPane.YES_OPTION)
            {
                ls_GuidList.setSelectedIndex(ls_LastIndx);
                return;
            }

            // S1S2 item = UserMdl.getListMdl().getElement(ls_LastIndx);
            // UserMdl.getGridMdl().setKindHash(item.getK());
            // try
            // {
            // UserMdl.getGridMdl().saveData(true, true);
            // }
            // catch (Exception exp)
            // {
            // LogUtil.exception(exp);
            // return;
            // }
        }

        ls_LastIndx = i;
        Object obj = ls_GuidList.getSelectedValue();
        if (!(obj instanceof Keys))
        {
            return;
        }
        try
        {
            tb_LastIndx = -1;
            Keys keys = (Keys) obj;
            UserMdl.getGridMdl().clear();
            UserMdl.getGridMdl().loadData(keys.getP30F0103());
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }

        showPropEdit();
    }

    private void tb_ItemListMouseReleased(java.awt.event.MouseEvent evt)
    {
        int row = tb_KeysView.getSelectedRow();
        if (row < 0 || row > tb_KeysView.getRowCount() || row == tb_LastIndx)
        {
            return;
        }
        if (evt.isPopupTrigger())
        {
            gridMenu.show(tb_KeysView, evt.getX(), evt.getY());
        }
        else
        {
            tb_LastIndx = row;
            showPropEdit(UserMdl.getGridMdl().getItemAt(row), true);
        }
    }

    private void tb_ItemListKeyReleased(java.awt.event.KeyEvent evt)
    {
        int row = tb_KeysView.getSelectedRow();
        if (row < 0 || row > tb_KeysView.getRowCount() || row == tb_LastIndx)
        {
            return;
        }
        tb_LastIndx = row;
        showPropEdit(UserMdl.getGridMdl().getItemAt(row), false);
    }

    private void showPropEdit()
    {
        if (UserMdl.getCfg().isEditViw())
        {
            editBean[ConsDat.INDX_INFO].initData(null);
            cl_CardProp.show(pl_CardProp, ConsEnv.BEAN_INFO);
            editBean[ConsDat.INDX_INFO].requestFocus();
        }
    }

    private void showPropEdit(Item tplt, boolean focus)
    {
        if (UserMdl.getCfg().isEditViw())
        {
            editBean[tplt.getType()].initData(tplt);
            cl_CardProp.show(pl_CardProp, ConsEnv.BEAN_PROP + tplt.getType());
            if (focus)
            {
                editBean[tplt.getType()].requestFocus();
            }
            if (UserMdl.getCfg().isEditWnd())
            {
                mp_MpsDialog.setTitle(getPropName(tplt.getType()));
            }
            else
            {
                border.setTitle(getPropName(tplt.getType()));
                pl_KeysEdit.repaint();
            }
        }
    }

    private String getPropName(int type)
    {
        switch (type)
        {
            case ConsDat.INDX_INFO:
            {
                return Lang.getLang(LangRes.P30F1101, "提示");
            }
            case ConsDat.INDX_TEXT:
            {
                return Lang.getLang(LangRes.P30F1102, "文本");
            }
            case ConsDat.INDX_PWDS:
            {
                return Lang.getLang(LangRes.P30F1103, "口令");
            }
            case ConsDat.INDX_LINK:
            {
                return Lang.getLang(LangRes.P30F1104, "链接");
            }
            case ConsDat.INDX_MAIL:
            {
                return Lang.getLang(LangRes.P30F1105, "邮件");
            }
            case ConsDat.INDX_DATE:
            {
                return Lang.getLang(LangRes.P30F1106, "日期");
            }
            case ConsDat.INDX_AREA:
            {
                return Lang.getLang(LangRes.P30F1107, "附注");
            }
            case ConsDat.INDX_FILE:
            {
                return Lang.getLang(LangRes.P30F1108, "文件");
            }
            case ConsDat.INDX_GUID:
            {
                return Lang.getLang(LangRes.P30F1109, "向导");
            }
            case ConsDat.INDX_META:
            {
                return Lang.getLang(LangRes.P30F110A, "标题");
            }
            case ConsDat.INDX_LOGO:
            {
                return Lang.getLang(LangRes.P30F1112, "徽标");
            }
            case ConsDat.INDX_NOTE:
            {
                return Lang.getLang(LangRes.P30F110B, "提醒");
            }
            default:
                return Lang.getLang(LangRes.P30F110C, "属性");
        }
    }

    private void showPropEdit(boolean editWnd)
    {
        pl_KeysEdit.setVisible(!editWnd);

        if (editWnd)
        {
            if (mp_MpsDialog != null)
            {
                mp_MpsDialog.setPropView(pl_CardProp);
            }
            else
            {
                mp_MpsDialog = new MpsDialog(MagicPwd.getFrame(), this);
                mp_MpsDialog.initView();
                mp_MpsDialog.initLang();
                mp_MpsDialog.setPropView(pl_CardProp);
                mp_MpsDialog.pack();
                mp_MpsDialog.setResizable(false);
                java.awt.Dimension a = mp_MpsDialog.getSize();
                java.awt.Dimension b = MagicPwd.getFrame().getSize();
                java.awt.Point p = MagicPwd.getFrame().getLocation();
                mp_MpsDialog.setLocation(p.x + b.width, p.y + b.height - a.height);
            }
            if (!mp_MpsDialog.isVisible())
            {
                mp_MpsDialog.setVisible(true);
            }
        }
        else
        {
            pl_KeysEdit.add(pl_CardProp);
            if (mp_MpsDialog != null && mp_MpsDialog.isVisible())
            {
                mp_MpsDialog.setVisible(false);
            }
        }
    }

    private boolean changeKind(String hash)
    {
        GridMdl gm = UserMdl.getGridMdl();
        if (hash == null || hash.equals(gm.getItemAt(ConsEnv.PWDS_HEAD_GUID).getData()))
        {
            return true;
        }

        gm.getItemAt(ConsEnv.PWDS_HEAD_GUID).setData(hash);
        try
        {
            gm.saveData(true, true);
            UserMdl.getListMdl().wRemove(ls_LastIndx);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean checkData()
    {
        if (tb_KeysView.getRowCount() == 1)
        {
            Lang.showMesg(this, LangRes.P30F7A01, "");
            editBean[ConsDat.INDX_GUID].requestFocus();
            return false;
        }
        if (tb_KeysView.getRowCount() > 1)
        {
            return true;
        }

        if (!UserMdl.getCfg().isEditViw())
        {
            MagicPwd.getWMenuBar().setViewPropSelected(true);
            MagicPwd.getWMenuBar().setViewSideSelected(true);
            UserMdl.getCfg().setEditViw(true);
            UserMdl.getCfg().setEditWnd(true);
            showPropEdit(true);
        }

        showPropEdit(UserMdl.getGridMdl().initGuid(), true);
        return false;
    }

    private boolean exportData()
    {
        javax.swing.tree.TreePath path = tr_GuidTree.getSelectionPath();
        KindTN node = (KindTN) path.getLastPathComponent();
        S1S3 item = (S1S3) node.getUserObject();

        javax.swing.JFileChooser jfc = new javax.swing.JFileChooser();
        jfc.setMultiSelectionEnabled(false);
        jfc.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
        int status = jfc.showSaveDialog(this);
        if (status != javax.swing.JFileChooser.APPROVE_OPTION)
        {
            return false;
        }
        java.io.File file = jfc.getSelectedFile();
        if (file.exists())
        {
            if (Lang.showFirm(this, LangRes.P30F7A21, "目标文件已存在，确认要覆盖此文件么？") != javax.swing.JOptionPane.YES_OPTION)
            {
                return false;
            }
        }
        else
        {
            try
            {
                file.createNewFile();
            }
            catch (Exception exp)
            {
                Lang.showMesg(this, LangRes.P30F7A22, "数据导出失败，无法在指定文件创建文档！");
                return false;
            }
        }
        if (!file.isFile())
        {
            Lang.showMesg(this, LangRes.P30F7A23, "数据导出失败，您选择的对象不是一个合适的文档！");
            return false;
        }
        if (!file.canWrite())
        {
            Lang.showMesg(this, LangRes.P30F7A24, "数据导出失败，请确认您是否拥有合适的读写权限！");
            return false;
        }

        try
        {
            Jcsv csv = new Jcsv(file);
            java.util.ArrayList<java.util.ArrayList<String>> data = new java.util.ArrayList<java.util.ArrayList<String>>();
            int size = UserMdl.getGridMdl().wExport(data, item.getK());
            csv.saveFile(data);
            Lang.showMesg(this, LangRes.P30F7A25, "成功导出数据个数：{0}", size + "");
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30F7A26, "数据导出失败，请确认您数据的正确性，然后重新尝试！");
        }
        return true;
    }

    private boolean importData()
    {
        javax.swing.tree.TreePath path = tr_GuidTree.getSelectionPath();
        KindTN node = (KindTN) path.getLastPathComponent();
        S1S3 item = (S1S3) node.getUserObject();

        javax.swing.JFileChooser jfc = new javax.swing.JFileChooser();
        jfc.setMultiSelectionEnabled(false);
        jfc.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
        int status = jfc.showOpenDialog(this);
        if (status != javax.swing.JFileChooser.APPROVE_OPTION)
        {
            return false;
        }
        java.io.File file = jfc.getSelectedFile();
        if (!file.exists())
        {
            Lang.showMesg(this, LangRes.P30F7A03, "");
            return false;
        }
        if (!file.isFile())
        {
            Lang.showMesg(this, LangRes.P30F7A04, "");
            return false;
        }
        if (!file.canRead())
        {
            Lang.showMesg(this, LangRes.P30F7A05, "");
            return false;
        }

        try
        {
            Jcsv csv = new Jcsv(file);
            csv.setEe("");
            java.util.ArrayList<java.util.ArrayList<String>> data = csv.readFile();
            int size = UserMdl.getGridMdl().wImport(data, item.getK());
            UserMdl.getListMdl().listName(item.getK());
            Lang.showMesg(this, LangRes.P30F7A07, "", "" + size);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30F7A08, "");
        }
        return true;
    }
    /**
     * 数据导航面板，用于显示类别、口令列表等信息
     */
    private javax.swing.JPanel pl_KeysGuid;
    /**
     * 用户交互面板，用于显示查找、列表、编辑等信息
     */
    private javax.swing.JPanel pl_KeysInfo;
    /**
     * 属性编辑面板，用于显示边框及相关信息
     */
    private javax.swing.JPanel pl_KeysEdit;
    /**
     * 属性切换面板，用于显示不同属性的面板
     */
    private javax.swing.JPanel pl_CardProp;
    /**
     * 导航列表
     */
    private javax.swing.JList ls_GuidList;
    /**
     * 类别导航
     */
    private javax.swing.JTree tr_GuidTree;
    /**
     * 口令列表
     */
    private javax.swing.JTable tb_KeysView;
}
