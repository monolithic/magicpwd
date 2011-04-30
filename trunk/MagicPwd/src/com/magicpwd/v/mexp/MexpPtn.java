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
package com.magicpwd.v.mexp;

import com.magicpwd.__a.AMpwdPtn;
import com.magicpwd.__i.IBackCall;
import com.magicpwd.__i.IEditBean;
import com.magicpwd.__i.IEditItem;
import com.magicpwd.__i.mexp.IMexpBean;
import com.magicpwd._bean.mail.Connect;
import com.magicpwd._bean.mexp.AreaBean;
import com.magicpwd._bean.mexp.DataBean;
import com.magicpwd._bean.mexp.DateBean;
import com.magicpwd._bean.mexp.FileBean;
import com.magicpwd._bean.mexp.GuidBean;
import com.magicpwd._bean.mexp.LogoBean;
import com.magicpwd._bean.mexp.InfoBean;
import com.magicpwd._bean.mexp.LinkBean;
import com.magicpwd._bean.mexp.MailBean;
import com.magicpwd._bean.mexp.MetaBean;
import com.magicpwd._bean.mexp.HintBean;
import com.magicpwd._bean.mexp.SignBean;
import com.magicpwd._bean.mexp.ListBean;
import com.magicpwd._bean.mexp.PwdsBean;
import com.magicpwd._bean.mexp.TextBean;
import com.magicpwd._comn.I1S2;
import com.magicpwd._comn.mpwd.Mkey;
import com.magicpwd._comp.WButtonGroup;
import com.magicpwd._comn.item.GuidItem;
import com.magicpwd._comn.item.LogoItem;
import com.magicpwd._comn.item.MetaItem;
import com.magicpwd._comn.mpwd.Hint;
import com.magicpwd._comn.prop.Kind;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._enum.AppView;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Card;
import com.magicpwd._util.Char;
import com.magicpwd._util.Desk;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.d.db.DBA4000;
import com.magicpwd.m.UserMdl;
import com.magicpwd.m.mexp.MexpMdl;
import com.magicpwd.m.mexp.KindMdl;
import com.magicpwd.r.KeysCR;
import com.magicpwd.r.KindTN;
import com.magicpwd.r.TreeCR;
import com.magicpwd.v.HintBar;
import com.magicpwd.v.MenuPtn;
import com.magicpwd.v.mail.MailPtn;
import com.magicpwd.v.tray.TrayPtn;
import com.magicpwd.x.MdiDialog;
import com.magicpwd.x.mail.MailOpt;

public class MexpPtn extends AMpwdPtn
{

    private EditDlg ed_KeysEdit;
    private IMexpBean[] mexpBean;
    private FindBar findBar;
    private HintBar hintBar;
    private MailPtn mailPtn;
    private HistDlg histDlg;
    private MdiDialog cfgForm;
    private MenuPtn menuPtn;
    private MexpMdl mexpMdl;
    /**口令列表上次选择索引*/
    private Mkey lastKeys;
    /**属性列表上次选择索引*/
    private int tb_LastIndx = -1;
    /**用户最后一次查找内容*/
    private String queryKey;
    /**用户上一次的操作方式*/
    private boolean isSearch;

    public MexpPtn(TrayPtn trayPtn, UserMdl userMdl)
    {
        super(trayPtn, userMdl);
    }

    @Override
    public boolean initView()
    {
        initGuidView();
        initPropView();
        initUserView();
        initBaseView();

        menuBar = new javax.swing.JMenuBar();
        setJMenuBar(menuBar);

        toolBar = new javax.swing.JToolBar();
        getContentPane().add(toolBar, userMdl.getToolLoc(AppView.mexp));

        getContentPane().add(plKeysBase);

        setIconImage(Bean.getLogo(16));

        this.pack();
        Bean.centerForm(this, null);
        super.setVisible(true);
        return true;
    }

    @Override
    public boolean initLang()
    {
        setTitle(Lang.getLang(LangRes.P30F7201, "魔方密码"));

        initGuidLang();
        initPropLang();
        initUserLang();
        initBaseLang();

        this.pack();
        Bean.centerForm(this, null);
        return true;
    }

    @Override
    public boolean initData()
    {
        try
        {
            menuPtn = new MenuPtn(trayPtn, this);
            menuPtn.loadData(new java.io.File(userMdl.getDataDir(), "mexp.xml"));

            menuPtn.getMenuBar("mexp", menuBar, rootPane);

            menuPtn.getToolBar("mexp", toolBar, rootPane, AppView.mexp);

            menuPtn.getPopMenu("kind", kindPop);
            trGuidTree.setComponentPopupMenu(kindPop);

            menuPtn.getPopMenu("list", listPop);

            menuPtn.getPopMenu("grid", gridPop);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }

        mexpMdl = new MexpMdl(userMdl);
        mexpMdl.init();

        safeMdl = mexpMdl.getGridMdl();

        trGuidTree.setModel(mexpMdl.getKindMdl());
        lsGuidList.addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e)
            {
                lsGuidListMouseClick(e);
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e)
            {
                lsGuidListMouseEvent(e);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e)
            {
                lsGuidListMouseEvent(e);
            }
        });

        lsGuidList.setModel(mexpMdl.getListMdl());

        tbKeysView.setModel(mexpMdl.getGridMdl());
        javax.swing.table.TableColumnModel colModel = tbKeysView.getColumnModel();
        colModel.getColumn(0).setMaxWidth(tbKeysView.getFontMetrics(tbKeysView.getFont()).stringWidth("999999"));
//        for (int i = 0, j = colModel.getColumnCount(); i < j; i += 1)
//        {
//            colModel.getColumn(i).setCellRenderer(new MpwdCR());
//        }

        // 菜单栏
        setMenuVisible(userMdl.isMenuVisible(AppView.mexp));

        // 工具栏
        setToolVisible(userMdl.isToolVisible(AppView.mexp));

        // 搜索栏
        findBar.initData();
        setFindVisible(userMdl.isFindVisible(AppView.mexp));

        // 信息栏
        hintBar.initData();
        hintBar.setBackCall(new IBackCall<String, java.util.List<Hint>>()
        {

            @Override
            public boolean callBack(String options, java.util.List<Hint> object)
            {
                mexpMdl.getListMdl().listHint(object);
                return true;
            }
        });
        setInfoVisible(userMdl.isInfoVisible(AppView.mexp));

        // 属性编辑组件
        ebKeysEdit.initData();
        ed_KeysEdit.initData();
        for (IEditBean bean : mexpBean)
        {
            bean.initData();
        }

        if (userMdl.isEditVisible(AppView.mexp))
        {
            showPropInfo();
        }
        setEditIsolate(userMdl.isEditIsolate(AppView.mexp));
        setEditVisible(userMdl.isEditVisible(AppView.mexp));

        // 列表菜单
        WButtonGroup group = menuPtn.getGroup("order-dir");
        if (group != null)
        {
            group.setSelected(userMdl.getCfg(AppView.mexp, ConsCfg.CFG_VIEW_LIST_ASC, ConsCfg.DEF_FALSE), true);
        }
        group = menuPtn.getGroup("order-key");
        if (group != null)
        {
            group.setSelected(userMdl.getCfg(AppView.mexp, ConsCfg.CFG_VIEW_LIST_KEY, "01"), true);
        }

        this.pack();
        Bean.centerForm(this, null);
        return true;
    }

    @Override
    public boolean showData()
    {
        return true;
    }

    public boolean newKeys()
    {
        if (!clearGrid())
        {
            return false;
        }

        setEditVisible(true);
        showPropEdit(mexpMdl.getGridMdl().initGuid(), true);
        lastKeys = null;
        return true;
    }

    public boolean isKindValidate(Kind kind)
    {
        if (kind == null)
        {
            return false;
        }
        if (kind.getC2010208() != null)
        {
            if (kind.getC2010208().indexOf("task") >= 0)
            {
                return false;
            }
        }
        return true;
    }

    public boolean findKeys(String meta)
    {
        lastKeys = null;

        if (Char.isValidate(meta))
        {
            isSearch = true;
            queryKey = meta;
            trGuidTree.setSelectionPath(null);
            mexpMdl.getListMdl().listKeysByMeta(meta);
            hintBar.showInfo("共 " + mexpMdl.getListMdl().getSize() + " 条数据");
        }
        else
        {
            mexpMdl.getListMdl().listKeysByKind(queryKey);
            hintBar.showInfo("共 " + mexpMdl.getListMdl().getSize() + " 条数据");
        }
        return true;
    }

    public void findLast()
    {
        if (isSearch)
        {
            mexpMdl.getListMdl().listKeysByMeta(queryKey);
        }
        else if (com.magicpwd._util.Char.isValidateHash(queryKey))
        {
            mexpMdl.getListMdl().listKeysByKind(queryKey);
        }
        hintBar.showInfo("共 " + mexpMdl.getListMdl().getSize() + " 条数据");

        lastKeys = null;
    }

    public boolean saveKeys()
    {
        // 是否需要保存
        if (mexpMdl.getGridMdl().getRowCount() < ConsEnv.PWDS_HEAD_SIZE)
        {
            return false;
        }

        // 数据未被修改
        if (!mexpMdl.getGridMdl().isModified())
        {
            //Lang.showMesg(this, LangRes.P30F7A27, "您未曾修改过数据，不需要保存！");
            return false;
        }

        // 口令类别检测
        GuidItem guid = (GuidItem) mexpMdl.getGridMdl().getItemAt(ConsEnv.PWDS_HEAD_GUID);
        if (!com.magicpwd._util.Char.isValidate(guid.getData()))
        {
            javax.swing.tree.TreePath path = trGuidTree.getSelectionPath();
            if (path == null)
            {
                Lang.showMesg(this, LangRes.P30F7A0D, "请选择口令类别信息！");
                trGuidTree.requestFocus();
                return false;
            }

            KindTN node = (KindTN) path.getLastPathComponent();
            Kind kind = (Kind) node.getUserObject();
            if (!isKindValidate(kind))
            {
                Lang.showMesg(this, LangRes.P30F7A4A, "不能保存到任务列表中去！");
                trGuidTree.requestFocus();
                return false;
            }
            mexpMdl.getGridMdl().getItemAt(ConsEnv.PWDS_HEAD_GUID).setData(kind.getC2010203());
        }

        // 标题为空检测
        MetaItem metaItem = (MetaItem) mexpMdl.getGridMdl().getItemAt(ConsEnv.PWDS_HEAD_META);
        if (!com.magicpwd._util.Char.isValidate(metaItem.getName()))
        {
            Lang.showMesg(this, LangRes.P30F7A0C, "请输入口令标题！");
            tbKeysView.setRowSelectionInterval(1, 1);
            showPropEdit(metaItem, true);
            return false;
        }

        // 徽标
        LogoItem logoItem = (LogoItem) mexpMdl.getGridMdl().getItemAt(ConsEnv.PWDS_HEAD_LOGO);

        String keysHash = mexpMdl.getGridMdl().getKeysHash();

        try
        {
            mexpMdl.getGridMdl().saveData(userMdl.isIncBack(), true);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30F7A0E, "口令数据保存失败，请重新启动本程序后再次尝试！");
        }

        // 数据新增的情况下，需要重新显示列表信息
        if (com.magicpwd._util.Char.isValidateHash(keysHash))
        {
            mexpMdl.getListMdl().updtName(keysHash, metaItem.getName(), logoItem.getName());
        }
        else
        {
            if (isSearch)
            {
                mexpMdl.getListMdl().listKeysByMeta(queryKey);
            }
            else if ("0".equals(queryKey) || com.magicpwd._util.Char.isValidateHash(queryKey))
            {
                mexpMdl.getListMdl().listKeysByKind(queryKey);
            }
        }

        showPropInfo();
        hintBar.showNote(true);

        lastKeys = null;
        tb_LastIndx = -1;
        return true;
    }

    @Override
    public void setVisible(boolean visible)
    {
        if (!visible)
        {
            if (mailPtn != null && mailPtn.isVisible())
            {
                mailPtn.setVisible(false);
            }
            if (ed_KeysEdit != null && ed_KeysEdit.isVisible())
            {
                ed_KeysEdit.setVisible(false);
            }
            if (cfgForm != null && cfgForm.isVisible())
            {
                cfgForm.setVisible(false);
            }
        }
        super.setVisible(visible);
    }

    @Override
    public MenuPtn getMenuPtn()
    {
        return menuPtn;
    }

    public void setEditVisible(boolean visible)
    {
        if (userMdl.isEditIsolate(AppView.mexp))
        {
            ed_KeysEdit.setVisible(visible);
        }
        else
        {
            ebKeysEdit.setVisible(visible);
        }
        userMdl.setEditVisible(AppView.mexp, visible);
    }

    public void setEditIsolate(boolean isolate)
    {
        if (userMdl.isEditVisible(AppView.mexp))
        {
            if (isolate)
            {
                ebKeysEdit.setVisible(false);
                ed_KeysEdit.setPropView(plCardProp);
                ed_KeysEdit.setVisible(true);
            }
            else
            {
                ebKeysEdit.setPropView(plCardProp);
                ebKeysEdit.setVisible(true);
                ed_KeysEdit.setVisible(false);
            }
            userMdl.setEditIsolate(AppView.mexp, isolate);
        }
    }

    public void setFindFocused()
    {
        findBar.requestFocus();
    }

    public void setFindVisible(boolean visible)
    {
        findBar.setVisible(visible);
        userMdl.setFindVisible(AppView.mexp, visible);
    }

    public void setInfoVisible(boolean visible)
    {
        hintBar.setVisible(visible);
        userMdl.setInfoVisible(AppView.mexp, visible);
    }

    public void setMenuVisible(boolean visible)
    {
        menuBar.setVisible(visible);
        userMdl.setMenuVisible(AppView.mexp, visible);
    }

    public void setToolVisible(boolean visible)
    {
        toolBar.setVisible(visible);
        userMdl.setToolVisible(AppView.mexp, visible);
    }

    public javax.swing.tree.TreePath getSelectedKindValue()
    {
        return trGuidTree.getSelectionPath();
    }

    public void appendKindBySelected(Kind kind)
    {
        if (kind == null)
        {
            return;
        }
        javax.swing.tree.TreePath path = trGuidTree.getSelectionPath();
        if (path == null)
        {
            return;
        }
        mexpMdl.getKindMdl().wAppend(path, kind);
    }

    public void updateKindBySelected(Kind kind)
    {
        if (kind == null)
        {
            return;
        }
        javax.swing.tree.TreePath path = trGuidTree.getSelectionPath();
        if (path == null)
        {
            return;
        }
        mexpMdl.getKindMdl().wUpdate(path, kind);
    }

    public int getSelectedKindIndex()
    {
        return 0;
    }

    public Object getSelectedListValue()
    {
        return lsGuidList.getSelectedValue();
    }

    public int getSelectedListIndex()
    {
        return lsGuidList.getSelectedIndex();
    }

    public void selectNext(int step, boolean updt)
    {
        if (mexpMdl.getGridMdl().getRowCount() < 1)
        {
            return;
        }

        if (updt)
        {
            mexpMdl.getGridMdl().fireTableDataChanged();
        }
        else if (step == 0)
        {
            return;
        }

        int c = tbKeysView.getRowCount() - 1;
        int n = tb_LastIndx + step;
        if (n < 0)
        {
            n = 0;
        }
        if (n > c)
        {
            n = c;
        }
        tb_LastIndx = n;
        tbKeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
        Util.scrollToVisible(tbKeysView, tb_LastIndx, 0, true);
        showPropEdit(mexpMdl.getGridMdl().getItemAt(tb_LastIndx), true);

//        if (updt)
//        {
//            tb_LastIndx += 1;
//            if (tb_LastIndx > c)
//            {
//                tb_LastIndx = c;
//                UserMdl.getGridMdl().fireTableDataChanged();
//            }
//            tb_KeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
//
//            IEditItem tplt = UserMdl.getGridMdl().getItemAt(tb_LastIndx);
//            showPropEdit(tplt, true);
//        }
//        else
//        {
//            if (tb_LastIndx < 0 || tb_LastIndx > c)
//            {
//                tb_LastIndx = c;
//            }
//            UserMdl.getGridMdl().fireTableDataChanged();
//            tb_KeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
//            tb_KeysView.requestFocus();
//        }
//
//        Util.scrollToVisible(tb_KeysView, tb_LastIndx, 0, true);
//        showPropEdit(UserMdl.getGridMdl().getItemAt(tb_LastIndx), true);
    }

    public void appendBean(int type)
    {
        if (checkData())
        {
            tb_LastIndx = tbKeysView.getSelectedRow();
            if (tb_LastIndx < ConsEnv.PWDS_HEAD_SIZE)
            {
                tb_LastIndx = tbKeysView.getRowCount();
            }
            showPropEdit(mexpMdl.getGridMdl().wAppend(tb_LastIndx, type), true);
            tbKeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
        }
    }

    public void changeBean(int type)
    {
        if (checkData())
        {
            int idx = tbKeysView.getSelectedRow();
            if (idx >= ConsEnv.PWDS_HEAD_SIZE && idx < tbKeysView.getRowCount())
            {
                IEditItem tplt = mexpMdl.getGridMdl().getItemAt(idx);
                if (tplt.getType() != type)
                {
                    tplt.setType(type);
                    showPropEdit(tplt, true);
                    mexpMdl.getGridMdl().setModified(true);
                }
            }
        }
    }

    public void movetoPrev()
    {
        int t = tb_LastIndx - 1;
        if (t < ConsEnv.PWDS_HEAD_SIZE)
        {
            return;
        }
        mexpMdl.getGridMdl().wMoveto(tb_LastIndx, t);
        tb_LastIndx = t;
        Util.scrollToVisible(tbKeysView, tb_LastIndx, 0, true);
        tbKeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
    }

    public void movetoNext()
    {
        int t = tb_LastIndx + 1;
        if (t <= ConsEnv.PWDS_HEAD_SIZE || t >= tbKeysView.getRowCount())
        {
            return;
        }
        mexpMdl.getGridMdl().wMoveto(tb_LastIndx, t);
        tb_LastIndx = t;
        Util.scrollToVisible(tbKeysView, tb_LastIndx, 0, true);
        tbKeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
    }

    private void initPropView()
    {
        clCardProp = new java.awt.CardLayout();
        plCardProp = new javax.swing.JPanel();
        plCardProp.setLayout(clCardProp);
        mexpBean = new IMexpBean[ConsDat.INDX_SIZE];
        int idx = 0;

        InfoBean beanInfo = new InfoBean(this);
        beanInfo.initView();
        plCardProp.add(ConsEnv.BEAN_INFO, beanInfo);
        mexpBean[idx++] = beanInfo;

        TextBean beanText = new TextBean(this);
        beanText.initView();
        plCardProp.add(ConsEnv.BEAN_TEXT, beanText);
        mexpBean[idx++] = beanText;

        PwdsBean beanPwds = new PwdsBean(this);
        beanPwds.initView();
        plCardProp.add(ConsEnv.BEAN_PWDS, beanPwds);
        mexpBean[idx++] = beanPwds;

        LinkBean beanLink = new LinkBean(this);
        beanLink.initView();
        plCardProp.add(ConsEnv.BEAN_LINK, beanLink);
        mexpBean[idx++] = beanLink;

        MailBean beanMail = new MailBean(this);
        beanMail.initView();
        plCardProp.add(ConsEnv.BEAN_MAIL, beanMail);
        mexpBean[idx++] = beanMail;

        DateBean beanDate = new DateBean(this);
        beanDate.initView();
        plCardProp.add(ConsEnv.BEAN_DATE, beanDate);
        mexpBean[idx++] = beanDate;

        AreaBean beanArea = new AreaBean(this);
        beanArea.initView();
        plCardProp.add(ConsEnv.BEAN_AREA, beanArea);
        mexpBean[idx++] = beanArea;

        FileBean beanFile = new FileBean(this);
        beanFile.initView();
        plCardProp.add(ConsEnv.BEAN_FILE, beanFile);
        mexpBean[idx++] = beanFile;

        DataBean beanData = new DataBean(this);
        beanData.initView();
        plCardProp.add(ConsEnv.BEAN_DATA, beanData);
        mexpBean[idx++] = beanData;

        ListBean beanList = new ListBean(this);
        beanList.initView();
        plCardProp.add(ConsEnv.BEAN_LIST, beanList);
        mexpBean[idx++] = beanList;

        SignBean beanSign = new SignBean(this);
        beanSign.initView();
        plCardProp.add(ConsEnv.BEAN_SIGN, beanSign);
        mexpBean[idx++] = beanSign;

        GuidBean beanGuid = new GuidBean(this);
        beanGuid.initView();
        plCardProp.add(ConsEnv.BEAN_GUID, beanGuid);
        mexpBean[idx++] = beanGuid;

        MetaBean beanMeta = new MetaBean(this);
        beanMeta.initView();
        plCardProp.add(ConsEnv.BEAN_META, beanMeta);
        mexpBean[idx++] = beanMeta;

        LogoBean beanIcon = new LogoBean(this);
        beanIcon.initView();
        plCardProp.add(ConsEnv.BEAN_ICON, beanIcon);
        mexpBean[idx++] = beanIcon;

        HintBean beanNote = new HintBean(this);
        beanNote.initView();
        plCardProp.add(ConsEnv.BEAN_NOTE, beanNote);
        mexpBean[idx++] = beanNote;

        ebKeysEdit = new EditBar();
        ebKeysEdit.initView();

        ed_KeysEdit = new EditDlg(this);
        ed_KeysEdit.initView();
    }

    private void initGuidView()
    {
        plKeysGuid = new javax.swing.JPanel();

        javax.swing.JSplitPane sp = new javax.swing.JSplitPane();
        sp.setDividerLocation(180);
        sp.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        sp.setOneTouchExpandable(true);

        trGuidTree = new javax.swing.JTree();
        trGuidTree.setCellRenderer(new TreeCR());
        trGuidTree.getSelectionModel().setSelectionMode(javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION);
        javax.swing.ToolTipManager.sharedInstance().registerComponent(trGuidTree);
        trGuidTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener()
        {

            @Override
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt)
            {
                trGuidTreeValueChanged(evt);
            }
        });

        kindPop = new javax.swing.JPopupMenu();

        javax.swing.JScrollPane sp1 = new javax.swing.JScrollPane();
        sp1.setViewportView(trGuidTree);
        sp.setTopComponent(sp1);

        lsGuidList = new javax.swing.JList();
        lsGuidList.setCellRenderer(new KeysCR(this));
        lsGuidList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        listPop = new javax.swing.JPopupMenu();

        javax.swing.JScrollPane sp2 = new javax.swing.JScrollPane();
        sp2.setViewportView(lsGuidList);
        sp.setRightComponent(sp2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(plKeysGuid);
        plKeysGuid.setLayout(layout);

        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE);
        hsg.addGap(3);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE);
        layout.setVerticalGroup(vsg);
    }

    private void initUserView()
    {
        plKeysInfo = new javax.swing.JPanel();

        findBar = new FindBar(this);
        findBar.initView();

        tbKeysView = new javax.swing.JTable();
        tbKeysView.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbKeysView.getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        javax.swing.AbstractAction action = new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                trGuidTree.requestFocus();
            }
        };
        Bean.registerKeyStrokeAction(rootPane, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK), action, "guid-kind", javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);
        action = new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                lsGuidList.requestFocus();
            }
        };
        Bean.registerKeyStrokeAction(rootPane, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.ALT_MASK), action, "guid-list", javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);
        action = new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                tbKeysView.requestFocus();
            }
        };
        Bean.registerKeyStrokeAction(rootPane, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK), action, "guid-grid", javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);

        tbKeysView.addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                // 右键事件处理
                if (evt.isPopupTrigger())
                {
                    int row = tbKeysView.rowAtPoint(evt.getPoint());
                    tbKeysView.setRowSelectionInterval(row, row);
                    gridPop.show(tbKeysView, evt.getX(), evt.getY());
                }
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                // 右键事件处理
                if (evt.isPopupTrigger())
                {
                    int row = tbKeysView.rowAtPoint(evt.getPoint());
                    tbKeysView.setRowSelectionInterval(row, row);
                    gridPop.show(tbKeysView, evt.getX(), evt.getY());
                }
                else
                {
                    tbItemListMouseReleased(evt);
                }
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                // 右键事件处理
                if (evt.isPopupTrigger())
                {
                    int row = tbKeysView.rowAtPoint(evt.getPoint());
                    tbKeysView.setRowSelectionInterval(row, row);
                    gridPop.show(tbKeysView, evt.getX(), evt.getY());
                }
            }
        });
        tbKeysView.addKeyListener(new java.awt.event.KeyAdapter()
        {

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                tbItemListKeyReleased(evt);
            }
        });

        spKeysView = new javax.swing.JScrollPane(tbKeysView);

        gridPop = new javax.swing.JPopupMenu();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(plKeysInfo);
        plKeysInfo.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg.addComponent(spKeysView, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE);
        hpg.addComponent(ebKeysEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg.addComponent(findBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);

        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addGap(5);
        hsg.addGroup(hpg);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addComponent(findBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(spKeysView, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(ebKeysEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setVerticalGroup(vsg);
    }

    private void initBaseView()
    {
        plKeysBase = new javax.swing.JPanel();

        hintBar = new HintBar(userMdl);
        hintBar.initView();

        javax.swing.JSplitPane sp = new javax.swing.JSplitPane();
        sp.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        sp.setDividerLocation(160);
        sp.setOneTouchExpandable(true);
        sp.setLeftComponent(plKeysGuid);
        sp.setRightComponent(plKeysInfo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(plKeysBase);
        plKeysBase.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE);
        hsg.addContainerGap();
        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg.addGroup(hsg);
        hpg.addComponent(hintBar, javax.swing.GroupLayout.PREFERRED_SIZE, 560, Short.MAX_VALUE);
        layout.setHorizontalGroup(hpg);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        vsg.addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 10, 20);
        vsg.addComponent(hintBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setVerticalGroup(vsg);
    }

    private void initGuidLang()
    {
        Lang.setWTips(trGuidTree, LangRes.P30F7B08, "类别列表(Alt + G)");
        Lang.setWTips(lsGuidList, LangRes.P30F7B09, "口令列表(Alt + K)");
        Lang.setWTips(spKeysView, LangRes.P30F7B0A, "属性列表(Alt + T)");
    }

    private void initPropLang()
    {
        ebKeysEdit.initLang();
        ed_KeysEdit.initLang();

        for (IEditBean bean : mexpBean)
        {
            bean.initLang();
        }
    }

    private void initUserLang()
    {
    }

    private void initBaseLang()
    {
        hintBar.initLang();
        findBar.initLang();
    }

    @Override
    public void requestFocus()
    {
        findBar.requestFocus();
    }

    private void trGuidTreeValueChanged(javax.swing.event.TreeSelectionEvent evt)
    {
        javax.swing.tree.TreePath path = trGuidTree.getSelectionPath();
        if (path == null)
        {
            return;
        }

        Object obj = path.getLastPathComponent();
        if (obj instanceof KindTN)
        {
            KindTN item = (KindTN) obj;
            Kind kind = (Kind) item.getUserObject();
            if (!isKindValidate(kind))
            {
                listTask(kind);
            }
            else
            {
                queryKey = kind.getC2010203();
                mexpMdl.getListMdl().listKeysByKind(queryKey);
            }
        }

        hintBar.showInfo("共 " + mexpMdl.getListMdl().getSize() + " 条数据");

        isSearch = false;
        lastKeys = null;
    }

    private void listTask(Kind kind)
    {
        String task = kind.getC2010208();
        java.util.Calendar c = java.util.Calendar.getInstance();
        java.util.Date s = c.getTime();
        if ("task".equals(task))
        {
            c.set(java.util.Calendar.HOUR_OF_DAY, 0);
            c.set(java.util.Calendar.MINUTE, 0);
            c.set(java.util.Calendar.SECOND, 0);
            c.set(java.util.Calendar.MILLISECOND, 0);
            c.add(java.util.Calendar.DAY_OF_MONTH, 1);
            mexpMdl.getListMdl().listHint(s, c.getTime());
            return;
        }
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d+").matcher(task);
        if (!matcher.find())
        {
            return;
        }
        int time = Integer.parseInt(matcher.group());
        if (task.endsWith("second"))
        {
            c.add(java.util.Calendar.SECOND, time);
            mexpMdl.getListMdl().listHint(s, c.getTime());
            return;
        }
        if (task.endsWith("minute"))
        {
            c.add(java.util.Calendar.MINUTE, time);
            mexpMdl.getListMdl().listHint(s, c.getTime());
            return;
        }
        if (task.endsWith("hour"))
        {
            c.add(java.util.Calendar.HOUR_OF_DAY, time);
            mexpMdl.getListMdl().listHint(s, c.getTime());
            return;
        }
        if (task.endsWith("day"))
        {
            c.add(java.util.Calendar.DAY_OF_MONTH, time);
            mexpMdl.getListMdl().listHint(s, c.getTime());
            return;
        }
        if (task.endsWith("week"))
        {
            c.add(java.util.Calendar.WEEK_OF_YEAR, time);
            mexpMdl.getListMdl().listHint(s, c.getTime());
            return;
        }
        if (task.endsWith("month"))
        {
            c.add(java.util.Calendar.MONTH, time);
            mexpMdl.getListMdl().listHint(s, c.getTime());
            return;
        }
        if (task.endsWith("year"))
        {
            c.add(java.util.Calendar.YEAR, time);
            mexpMdl.getListMdl().listHint(s, c.getTime());
            return;
        }
    }

    private void lsGuidListMouseClick(java.awt.event.MouseEvent e)
    {
        Object obj = lsGuidList.getSelectedValue();
        if (obj == null || !(obj instanceof Mkey))
        {
            return;
        }
        // 重复事件判断
        if (lastKeys != null && lastKeys.equals(obj))
        {
            return;
        }

        // 记录上次索引
        lastKeys = (Mkey) obj;

        if (mexpMdl.getGridMdl().isModified())
        {
            if (Lang.showFirm(this, LangRes.P30F7A09, "记录数据 {0} 已修改，要放弃修改吗？", mexpMdl.getGridMdl().getItemAt(ConsEnv.PWDS_HEAD_META).getName()) != javax.swing.JOptionPane.YES_OPTION)
            {
                lsGuidList.setSelectedValue(lastKeys, true);
                return;
            }
        }

        Mkey keys = (Mkey) obj;

        try
        {
            tb_LastIndx = -1;
            mexpMdl.getGridMdl().clear();
            mexpMdl.getGridMdl().loadData(keys.getP30F0104());

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
        catch (Exception exp)
        {
            Logs.exception(exp);
        }

        showPropInfo();
    }

    private void lsGuidListMouseEvent(java.awt.event.MouseEvent e)
    {
        if (e.isPopupTrigger())
        {
            int i = lsGuidList.locationToIndex(e.getPoint());
            if (i > -1)
            {
                lsGuidList.setSelectedIndex(i);
                listPop.show(lsGuidList, e.getX(), e.getY());
            }
            return;
        }
    }

    private void tbItemListMouseReleased(java.awt.event.MouseEvent evt)
    {
        // 左键事件处理
        int row = tbKeysView.getSelectedRow();
        if (row < 0 || row > tbKeysView.getRowCount() || row == tb_LastIndx)
        {
            return;
        }
        tb_LastIndx = row;
        showPropEdit(mexpMdl.getGridMdl().getItemAt(row), true);
    }

    private void tbItemListKeyReleased(java.awt.event.KeyEvent evt)
    {
        int row = tbKeysView.getSelectedRow();
        if (row < 0 || row > tbKeysView.getRowCount() || row == tb_LastIndx)
        {
            return;
        }
        tb_LastIndx = row;
        showPropEdit(mexpMdl.getGridMdl().getItemAt(row), false);
    }

    public void showPropInfo()
    {
        if (userMdl.isEditVisible(AppView.mexp))
        {
            mexpBean[ConsDat.INDX_INFO].showData(null);
            clCardProp.show(plCardProp, ConsEnv.BEAN_INFO);
            mexpBean[ConsDat.INDX_INFO].requestFocus();
        }
    }

    public void showPropEdit(IEditItem item, boolean focus)
    {
        if (userMdl.isEditVisible(AppView.mexp))
        {
            clCardProp.show(plCardProp, ConsEnv.BEAN_PROP + item.getType());
            mexpBean[item.getType()].showData(item);

            if (focus)
            {
                mexpBean[item.getType()].requestFocus();
            }

            String title = getPropName(item.getType());
            ebKeysEdit.setTitle(title);
            ed_KeysEdit.setTitle(title);
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
            case ConsDat.INDX_HINT:
            {
                return Lang.getLang(LangRes.P30F110B, "提醒");
            }
            default:
            {
                return Lang.getLang(LangRes.P30F110C, "属性");
            }
        }
    }

    private boolean checkData()
    {
        if (tbKeysView.getRowCount() == 1)
        {
            Lang.showMesg(this, LangRes.P30F7A01, "");
            mexpBean[ConsDat.INDX_GUID].requestFocus();
            return false;
        }
        if (tbKeysView.getRowCount() > 1)
        {
            return true;
        }

        if (!userMdl.isEditVisible(AppView.mexp))
        {
            userMdl.setEditVisible(AppView.mexp, true);
            userMdl.setEditIsolate(AppView.mexp, true);
            setEditVisible(true);
        }

        showPropEdit(mexpMdl.getGridMdl().initGuid(), true);
        return false;
    }

    @Override
    public boolean endSave()
    {
        // Save Temperary Data
        if (mexpMdl.getGridMdl().isModified())
        {
            mexpMdl.getGridMdl().setInterim(true);
            mexpMdl.getGridMdl().getItemAt(ConsEnv.PWDS_HEAD_GUID).setData(ConsDat.HASH_ROOT);
            try
            {
                mexpMdl.getGridMdl().saveData(true, false);
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }
        return true;
    }

    public IEditItem getMeta()
    {
        return mexpMdl.getGridMdl().getItemAt(ConsEnv.PWDS_HEAD_META);
    }

    public void changeLabel(int mode)
    {
        mexpMdl.getGridMdl().setKeysLabel(mode);
    }

    public void changeMajor(int note)
    {
        mexpMdl.getGridMdl().setKeysMajor(note);
    }

    public void changeKind(String hash)
    {
        if (mexpMdl.getGridMdl().setKeysKind(hash))
        {
            findLast();
        }
    }

    public boolean clearGrid()
    {
        if (gridModified())
        {
            if (Lang.showFirm(this, LangRes.P30F7A09, "记录数据 {0} 已修改，要放弃修改吗？", getMeta().getName()) != javax.swing.JOptionPane.YES_OPTION)
            {
                return false;
            }
        }

        mexpMdl.getGridMdl().clear();
        tb_LastIndx = 0;

        return true;
    }

    public boolean gridModified()
    {
        return mexpMdl.getGridMdl().isModified();
    }

    public void removeSelectedKeys()
    {
        mexpMdl.getListMdl().wDelete(lsGuidList.getSelectedIndex());
    }

    public void removeSelectedItem()
    {
        mexpMdl.getGridMdl().wRemove(tbKeysView.getSelectedRow());
        selectNext(0, true);
    }

    public void updateSelectedItem()
    {
        mexpMdl.getGridMdl().setModified(true);
        if (mexpMdl.getGridMdl().getRowCount() < ConsEnv.PWDS_HEAD_SIZE)
        {
            mexpMdl.getGridMdl().initBody();
        }
        selectNext(com.magicpwd._util.Char.isValidateHash(mexpMdl.getGridMdl().getKeysHash()) ? 0 : 1, true);
    }

    public void enCrypt(java.io.File src, java.io.File dst) throws Exception
    {
        mexpMdl.getGridMdl().enCrypt(src, dst);
    }

    public void deCrypt(java.io.File src, java.io.File dst) throws Exception
    {
        mexpMdl.getGridMdl().deCrypt(src, dst);
    }

    public void exportCard(java.io.File srcFile, java.io.File dstFile, String fileExt)
    {
        try
        {
            Card card = new Card(mexpMdl.getGridMdl());
            if (ConsEnv.CARD_HTM.equals(fileExt))
            {
                dstFile = card.exportHtm(srcFile, dstFile);
            }
            else if (ConsEnv.CARD_TXT.equals(fileExt))
            {
                dstFile = card.exportTxt(srcFile, dstFile);
            }
            else if (ConsEnv.CARD_PNG.equals(fileExt))
            {
                dstFile = card.exportPng(srcFile, dstFile);
            }
            else if (ConsEnv.CARD_SVG.equals(fileExt))
            {
                dstFile = card.exportSvg(srcFile, dstFile);
            }
            else
            {
                dstFile = card.exportAll(srcFile, dstFile);
            }

            if (dstFile == null || !dstFile.exists())
            {
                Lang.showMesg(this, LangRes.P30F7A46, "生成卡片文件失败，请稍后重试！");
            }
            else if (javax.swing.JOptionPane.YES_OPTION == Lang.showFirm(this, LangRes.P30F7A47, "生成卡片文件成功，要打开卡片文件吗？"))
            {
                if (!Desk.open(dstFile))
                {
                    Lang.showMesg(this, LangRes.P30F1A03, "打开文件错误，请尝试手动方式查看！");
                }
            }
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
            Lang.showMesg(this, null, ex.getLocalizedMessage());
        }
    }

    public void showMailPtn()
    {
        if (mailPtn == null)
        {
            mailPtn = new MailPtn(userMdl);
            mailPtn.initView();
            mailPtn.initLang();
            mailPtn.initData();
            Bean.centerForm(mailPtn, this);
        }

        MailOpt mailOpt = new MailOpt();
        mailOpt.initView();
        mailOpt.initLang();
        java.util.List<I1S2> mailList = mexpMdl.getGridMdl().wSelect(ConsDat.INDX_MAIL);
        mailOpt.initMail(mailList);
        if (mailList.size() < 1)
        {
            Lang.showMesg(this, null, "没有可用的邮件类型数据！");
            return;
        }
        java.util.List<I1S2> userList = mexpMdl.getGridMdl().wSelect(ConsDat.INDX_TEXT);
        mailOpt.initUser(userList);
        if (userList.size() < 1)
        {
            Lang.showMesg(this, null, "没有可用的文本类型数据！");
            return;
        }
        java.util.List<I1S2> pwdsList = mexpMdl.getGridMdl().wSelect(ConsDat.INDX_PWDS);
        mailOpt.initPwds(pwdsList);
        if (pwdsList.size() < 1)
        {
            Lang.showMesg(this, null, "没有可用的口令类型数据！");
            return;
        }
        if (javax.swing.JOptionPane.OK_OPTION != javax.swing.JOptionPane.showConfirmDialog(this, mailOpt, "登录确认", javax.swing.JOptionPane.OK_CANCEL_OPTION))
        {
            return;
        }

        String mail = mailList.get(mailOpt.getMail()).getV();
        String user = userList.get(mailOpt.getUser()).getV();
        String pwds = pwdsList.get(mailOpt.getPwds()).getV();

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

        mailPtn.setVisible(true);
        new Thread()
        {

            @Override
            public void run()
            {
                mailPtn.append(connect, "");
            }
        }.start();
    }

    public void showHistory()
    {
        if (histDlg == null)
        {
            histDlg = new HistDlg(mexpMdl.getGridMdl(), this);
            histDlg.initView();
            histDlg.initLang();
            Bean.centerForm(histDlg, this);
        }
        histDlg.initData();
        histDlg.setVisible(true);
    }

    public KindMdl getTreeMdl()
    {
        return mexpMdl.getKindMdl();
    }

    public void showOptions(String propName)
    {
        if (cfgForm == null)
        {
            cfgForm = new MdiDialog(this);
            cfgForm.initView();
            cfgForm.initLang();
            cfgForm.initData();
        }
        cfgForm.showProp(propName, true);
    }

    public void forwardSelectedKind()
    {
        javax.swing.tree.TreePath path = trGuidTree.getSelectionPath();
        if (path == null)
        {
            return;
        }

        javax.swing.tree.DefaultMutableTreeNode p = (javax.swing.tree.DefaultMutableTreeNode) path.getLastPathComponent();
        javax.swing.tree.DefaultMutableTreeNode n = p.getPreviousSibling();
        if (n == null)
        {
            return;
        }
        javax.swing.tree.DefaultMutableTreeNode o = (javax.swing.tree.DefaultMutableTreeNode) p.getParent();
        if (o == null)
        {
            return;
        }

        int i = o.getIndex(p);
        o.remove(i--);
        o.insert(p, i);
        getTreeMdl().nodeStructureChanged(o);

        trGuidTree.setSelectionPath(path);

        Kind c = (Kind) p.getUserObject();
        c.addC2010201(-1);
        DBA4000.updateKindData(c);
        c = (Kind) n.getUserObject();
        c.addC2010201(1);
        DBA4000.updateKindData(c);
    }

    public void backwardSelectedKind()
    {
        javax.swing.tree.TreePath path = trGuidTree.getSelectionPath();
        if (path == null)
        {
            return;
        }

        javax.swing.tree.DefaultMutableTreeNode p = (javax.swing.tree.DefaultMutableTreeNode) path.getLastPathComponent();
        javax.swing.tree.DefaultMutableTreeNode n = p.getNextSibling();
        if (n == null)
        {
            return;
        }
        javax.swing.tree.DefaultMutableTreeNode o = (javax.swing.tree.DefaultMutableTreeNode) p.getParent();
        if (o == null)
        {
            return;
        }

        int i = o.getIndex(p);
        o.remove(i++);
        o.insert(p, i);
        getTreeMdl().nodeStructureChanged(o);

        trGuidTree.setSelectionPath(path);

        Kind c = (Kind) p.getUserObject();
        c.addC2010201(1);
        DBA4000.updateKindData(c);
        c = (Kind) n.getUserObject();
        c.addC2010201(-1);
        DBA4000.updateKindData(c);
    }

    public void promotionSelectedKind()
    {
        javax.swing.tree.TreePath path = trGuidTree.getSelectionPath();
        if (path == null)
        {
            return;
        }

        javax.swing.tree.DefaultMutableTreeNode s = (javax.swing.tree.DefaultMutableTreeNode) path.getLastPathComponent();
        javax.swing.tree.DefaultMutableTreeNode p1 = (javax.swing.tree.DefaultMutableTreeNode) s.getParent();
        if (p1 == null)
        {
            return;
        }

        javax.swing.tree.DefaultMutableTreeNode p2 = (javax.swing.tree.DefaultMutableTreeNode) p1.getParent();
        if (p2 == null)
        {
            return;
        }

        p1.remove(s);
        p2.add(s);
        getTreeMdl().nodeStructureChanged(p2);
        trGuidTree.setSelectionPath(new javax.swing.tree.TreePath(s.getPath()));

        Kind u = (Kind) p2.getUserObject();
        Kind c = (Kind) s.getUserObject();
        c.setC2010201(p2.getChildCount());
        c.setC2010204(u.getC2010203());
        DBA4000.updateKindData(c);
    }

    public void demotionSelectedKind()
    {
        javax.swing.tree.TreePath path = trGuidTree.getSelectionPath();
        if (path == null)
        {
            return;
        }

        javax.swing.tree.DefaultMutableTreeNode s = (javax.swing.tree.DefaultMutableTreeNode) path.getLastPathComponent();
        javax.swing.tree.DefaultMutableTreeNode p = s.getPreviousSibling();
        if (p == null)
        {
            return;
        }

        javax.swing.tree.DefaultMutableTreeNode o = (javax.swing.tree.DefaultMutableTreeNode) p.getParent();
        o.remove(s);
        p.add(s);
        getTreeMdl().nodeStructureChanged(o);
        trGuidTree.setSelectionPath(new javax.swing.tree.TreePath(s.getPath()));

        Kind u = (Kind) p.getUserObject();
        Kind c = (Kind) s.getUserObject();
        c.setC2010201(p.getChildCount());
        c.setC2010204(u.getC2010203());
        DBA4000.updateKindData(c);
    }
    /**
     * 
     */
    private javax.swing.JPanel plKeysBase;
    /**
     * 数据导航面板，用于显示类别、口令列表等信息
     */
    private javax.swing.JPanel plKeysGuid;
    /**
     * 用户交互面板，用于显示查找、列表、编辑等信息
     */
    private javax.swing.JPanel plKeysInfo;
    /**
     * 属性编辑面板，用于显示边框及相关信息
     */
    private EditBar ebKeysEdit;
    /**
     * 属性切换面板，用于显示不同属性的面板
     */
    private javax.swing.JPanel plCardProp;
    /**
     * 导航列表
     */
    private javax.swing.JList lsGuidList;
    /**
     * 类别导航
     */
    private javax.swing.JTree trGuidTree;
    /**
     * 口令列表
     */
    private javax.swing.JTable tbKeysView;
    private javax.swing.JScrollPane spKeysView;
    private java.awt.CardLayout clCardProp;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JPopupMenu kindPop;
    private javax.swing.JPopupMenu listPop;
    private javax.swing.JPopupMenu gridPop;
}
