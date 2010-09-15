/**
 *
 */
package com.magicpwd.v;

import com.magicpwd.MagicPwd;
import com.magicpwd._bean.AreaBean;
import com.magicpwd._bean.DateBean;
import com.magicpwd._bean.FileBean;
import com.magicpwd._bean.GuidBean;
import com.magicpwd._bean.LogoBean;
import com.magicpwd._bean.InfoBean;
import com.magicpwd._bean.LinkBean;
import com.magicpwd._bean.MailBean;
import com.magicpwd._bean.MetaBean;
import com.magicpwd._bean.HintBean;
import com.magicpwd._bean.PwdsBean;
import com.magicpwd._bean.TextBean;
import com.magicpwd._comn.Kind;
import com.magicpwd._comn.Keys;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IBackCall;
import com.magicpwd._face.IEditBean;
import com.magicpwd._face.IEditItem;
import com.magicpwd._face.IFormView;
import com.magicpwd._face.IGridView;
import com.magicpwd._mail.MailDlg;
import com.magicpwd._util.Lang;
import com.magicpwd._user.UserSign;
import com.magicpwd._util.Desk;
import com.magicpwd._util.Jcsv;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.c.FindEvt;
import com.magicpwd.c.InfoEvt;
import com.magicpwd.c.MPwdEvt;
import com.magicpwd.c.ToolEvt;
import com.magicpwd.d.DBA3000;
import com.magicpwd.m.GridMdl;
import com.magicpwd.m.UserCfg;
import com.magicpwd.m.UserMdl;
import com.magicpwd.r.KeysCR;
import com.magicpwd.r.KindTN;
import com.magicpwd.r.TreeCR;
import com.magicpwd.x.DatDialog;
import com.magicpwd.x.MdiDialog;
import com.magicpwd.x.MpsDialog;

public class MainPtn extends javax.swing.JFrame implements IFormView, MPwdEvt, ToolEvt, InfoEvt, FindEvt, IGridView
{

    private java.awt.CardLayout cl_CardProp;
    private MailDlg mailForm;
    private MpsDialog md_MpsDialog;
    private IEditBean[] editBean;
    private FindBar mainFind;
    private HintBar mainInfo;
    private MenuBar mainMenu;
    private ToolBar mainTool;
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
    /**
     * 用户上一次的操作方式
     */
    private boolean isSearch;
    /**
     * 用户查找字符串
     */
    private String queryKey;
    private UserMdl coreMdl;
    private javax.swing.border.TitledBorder border;

    public MainPtn(UserMdl coreMdl)
    {
        this.coreMdl = coreMdl;
    }

    public UserMdl getCoreMdl()
    {
        return coreMdl;
    }

    public void initView()
    {
        UserCfg cfg = coreMdl.getUserCfg();

        initGuidView();
        initPropView();
        initUserView();
        initBaseView();

        this.getContentPane().add(pl_KeysBase);

        mainMenu = new MenuBar();
        mainMenu.initView();
        mainMenu.setMenuEvent(this);
        mainMenu.setVisible(cfg.isMenuViw());
        this.setJMenuBar(mainMenu);

        mainTool = new ToolBar(cfg);
        mainTool.initView();
        mainTool.setToolEvent(this);
        mainTool.setVisible(cfg.isToolViw());
        this.getContentPane().add(mainTool, cfg.getToolLoc());

        mainFind.setVisible(cfg.isFindViw());

        mainInfo.setVisible(cfg.isInfoViw());

        this.pack();
        this.setIconImage(Util.getLogo(16));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Util.centerForm(this, null);
    }

    public void initLang()
    {
        setTitle(Lang.getLang(LangRes.P30F7201, "魔方密码"));

        initGuidLang();
        initPropLang();
        initUserLang();
        initBaseLang();
    }

    public void initData()
    {
        UserCfg cfg = coreMdl.getUserCfg();
        if (cfg.isEditViw())
        {
            showPropEdit(cfg.isEditWnd());
        }

        Util.addDataAction(pl_KeysBase.getActionMap(), pl_KeysBase.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW), this);
        Util.addFormAction(pl_KeysBase.getActionMap(), pl_KeysBase.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW), this);
        Util.addFileAction(pl_KeysBase.getActionMap(), pl_KeysBase.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW), this);
        Util.addHideAction(pl_KeysBase.getActionMap(), pl_KeysBase.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW), this);
        Util.addViewAction(pl_KeysBase.getActionMap(), pl_KeysBase.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW), this);

        mainMenu.initData();
        mainTool.initData();
        mainInfo.initData();
        mainFind.initData();

        showPropEdit();
    }

    @Override
    public void setVisible(boolean visible)
    {
        if (!visible)
        {
            if (mailForm != null && mailForm.isVisible())
            {
                mailForm.setVisible(false);
            }
            if (md_MpsDialog != null && md_MpsDialog.isVisible())
            {
                md_MpsDialog.setVisible(false);
            }
            MdiDialog mdiDialog = MdiDialog.getInstance();
            if (mdiDialog != null && mdiDialog.isVisible())
            {
                mdiDialog.setVisible(false);
            }
        }
        super.setVisible(visible);
    }

    @Override
    public javax.swing.JFrame getForm()
    {
        return this;
    }

    @Override
    public void helpSKeyActionPerformed(java.awt.event.ActionEvent evt)
    {
        MdiDialog mdiDialog = MdiDialog.getInstance();
        if (mdiDialog == null)
        {
            MdiDialog.newInstance(this);
            mdiDialog = MdiDialog.getInstance();
        }
        mdiDialog.showProp(ConsEnv.PROP_SKEY, false);
    }

    @Override
    public void editFindActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (!coreMdl.getUserCfg().isFindViw())
        {
            mainFind.setVisible(true);
            mainMenu.setViewFindSelected(true);
            coreMdl.getUserCfg().setFindViw(true);
        }
        mainFind.requestFocus();
    }

    @Override
    public void keysModeActionPerformed(java.awt.event.ActionEvent evt)
    {
        String command = evt.getActionCommand();
        int val = java.util.regex.Pattern.matches("^[+-]?\\d+$", command) ? Integer.parseInt(command) : 0;

        Object obj = ls_GuidList.getSelectedValue();
        if (obj instanceof Keys)
        {
            ((Keys) obj).setP30F0102(val);
        }
        coreMdl.getGridMdl().setKeysMode(val);
    }

    @Override
    public void keysNoteActionPerformed(java.awt.event.ActionEvent evt)
    {
        String command = evt.getActionCommand();
        int val = java.util.regex.Pattern.matches("^[+-]?\\d+$", command) ? Integer.parseInt(command) : 0;

        Object obj = ls_GuidList.getSelectedValue();
        if (obj instanceof Keys)
        {
            ((Keys) obj).setP30F0103(val);
        }
        coreMdl.getGridMdl().setKeysNote(val);
    }

    @Override
    public void listSascActionPerformed(java.awt.event.ActionEvent evt)
    {
        coreMdl.getUserCfg().setCfg(ConsCfg.CFG_VIEW_LIST_ASC, evt.getActionCommand());
        if (isSearch)
        {
            coreMdl.getListMdl().findName(queryKey);
        }
        else if (com.magicpwd._util.Char.isValidateHash(queryKey))
        {
            coreMdl.getListMdl().listName(queryKey);
        }
    }

    @Override
    public void listSkeyActionPerformed(java.awt.event.ActionEvent evt)
    {
        coreMdl.getUserCfg().setCfg(ConsCfg.CFG_VIEW_LIST_KEY, evt.getActionCommand());
        if (isSearch)
        {
            coreMdl.getListMdl().findName(queryKey);
        }
        else if (com.magicpwd._util.Char.isValidateHash(queryKey))
        {
            coreMdl.getListMdl().listName(queryKey);
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

        Object obj = path.getLastPathComponent();
        if (obj == null || !(obj instanceof KindTN))
        {
            return;
        }

        String kindName = javax.swing.JOptionPane.showInputDialog(Lang.getLang(LangRes.P30F7A15, "请输入类别名称："));
        if (kindName == null)
        {
            return;
        }
        if (!com.magicpwd._util.Char.isValidate(kindName))
        {
            Lang.showMesg(this, LangRes.P30F7A16, "");
            return;
        }

        KindTN p = (KindTN) obj;
        Kind c = new Kind();
        c.setC2010101(p.getChildCount());
        c.setC2010105(kindName);
        c.setC2010106(kindName);
        coreMdl.getTreeMdl().wAppend(path, c);
    }

    @Override
    public void kindDeltActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.tree.TreePath path = tr_GuidTree.getSelectionPath();
        if (path == null)
        {
            return;
        }

        Object obj = path.getLastPathComponent();
        if (obj == null || !(obj instanceof KindTN))
        {
            return;
        }

        KindTN node = (KindTN) obj;
        if (node.isRoot())
        {
            return;
        }

        if (Lang.showFirm(this, LangRes.P30F7A1A, "执行此操作后，此类别下的其它类别将会移动到根类别下，\n确认要删除此类别么？") == javax.swing.JOptionPane.YES_OPTION)
        {
            coreMdl.getTreeMdl().wRemove(path);
        }
    }

    @Override
    public void kindMoveActionPerformed(java.awt.event.ActionEvent evt)
    {
        DatDialog dat = new DatDialog(coreMdl.getTreeMdl(), new IBackCall()
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
        dat.setVisible(true);
    }

    @Override
    public void kindUpdtActionPerformed(java.awt.event.ActionEvent evt)
    {
        javax.swing.tree.TreePath path = tr_GuidTree.getSelectionPath();
        if (path == null)
        {
            return;
        }

        Object obj = path.getLastPathComponent();
        if (obj == null || !(obj instanceof KindTN))
        {
            return;
        }

        KindTN node = (KindTN) obj;
        if (node.isRoot())
        {
            return;
        }

        Kind c = (Kind) node.getUserObject();

        String name = javax.swing.JOptionPane.showInputDialog(Lang.getLang(LangRes.P30F7A15, "请输入类别名称："), c.getC2010105());
        if (name == null)
        {
            return;
        }
        if (!com.magicpwd._util.Char.isValidate(name))
        {
            Lang.showMesg(this, LangRes.P30F7A17, "更新失败：您输入的类别名称无任何意义！");
            return;
        }
        c.setC2010105(name);
        c.setC2010106(name);
        DBA3000.updateKindData(c);
    }

    @Override
    public void histBackActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void editNextActionPerformed(java.awt.event.ActionEvent evt)
    {
        int t = tb_LastIndx + 1;
        if (t <= ConsEnv.PWDS_HEAD_SIZE || t >= tb_KeysView.getRowCount())
        {
            return;
        }
        coreMdl.getGridMdl().wMoveto(tb_LastIndx, t);
        tb_LastIndx = t;
        Util.scrollToVisible(tb_KeysView, tb_LastIndx, 0, true);
        tb_KeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
    }

    @Override
    public void editPrevActionPerformed(java.awt.event.ActionEvent evt)
    {
        int t = tb_LastIndx - 1;
        if (t < ConsEnv.PWDS_HEAD_SIZE)
        {
            return;
        }
        coreMdl.getGridMdl().wMoveto(tb_LastIndx, t);
        tb_LastIndx = t;
        Util.scrollToVisible(tb_KeysView, tb_LastIndx, 0, true);
        tb_KeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
    }

    @Override
    public void hintDataActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void findActionPerformed(java.awt.event.ActionEvent evt)
    {
        String text = mainFind.getSearchText();
        if (!com.magicpwd._util.Char.isValidate(text))
        {
            Lang.showMesg(this, LangRes.P30F7A18, "请输入您要查询的关键字，多个关键字可以使用空格或加号进行分隔！");
            mainFind.requestFocus();
            return;
        }

        boolean b = coreMdl.getListMdl().findName(text);
        if (!b)
        {
            Lang.showMesg(this, LangRes.P30F7A19, "查询不到符合您条件的数据，请用空格或加号分隔您的搜索关键字后重试！");
            mainFind.requestFocus();
        }

        queryKey = text;
        isSearch = true;
    }

    @Override
    public void copyDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        int row = tb_KeysView.getSelectedRow();
        if (row < 0 || row > tb_KeysView.getRowCount() - 1)
        {
            return;
        }
        IEditItem tplt = coreMdl.getGridMdl().getItemAt(row);
        Util.setClipboardContents(tplt.getData(), coreMdl.getUserCfg().getStayTime());
    }

    @Override
    public void deltDataActionPerformed(java.awt.event.ActionEvent evt)
    {
        int row = tb_KeysView.getSelectedRow();
        if (row < ConsEnv.PWDS_HEAD_SIZE || row > tb_KeysView.getRowCount() - 1)
        {
            return;
        }
        if (Lang.showFirm(this, LangRes.P30F1A01, "确认要删除此属性数据么？") == javax.swing.JOptionPane.YES_OPTION)
        {
            coreMdl.getGridMdl().wRemove(row);
            selectNext(0, true);
        }
    }

    @Override
    public void histViewActionPerformed(java.awt.event.ActionEvent evt)
    {
        Keys item = (Keys) ls_GuidList.getSelectedValue();
        if (item == null)
        {
            return;
        }

        MdiDialog mdiDialog = MdiDialog.getInstance();
        if (mdiDialog == null)
        {
            MdiDialog.newInstance(this);
            mdiDialog = MdiDialog.getInstance();
        }
        mdiDialog.showProp(ConsEnv.PROP_HIST, false);
    }

    @Override
    public void selectNext(int step, boolean updt)
    {
        if (updt)
        {
            coreMdl.getGridMdl().fireTableDataChanged();
        }

        int c = tb_KeysView.getRowCount() - 1;
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
        tb_KeysView.setRowSelectionInterval(tb_LastIndx, tb_LastIndx);
        Util.scrollToVisible(tb_KeysView, tb_LastIndx, 0, true);
        showPropEdit(coreMdl.getGridMdl().getItemAt(tb_LastIndx), true);

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

        HintBean beanNote = new HintBean(this);
        beanNote.initView();
        pl_CardProp.add(ConsEnv.BEAN_NOTE, beanNote);
        editBean[idx++] = beanNote;
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
        treeMenu.setToolEvent(this);
        treeMenu.setMenuEvent(this);

        tr_GuidTree = new javax.swing.JTree();
        tr_GuidTree.setComponentPopupMenu(treeMenu);
        tr_GuidTree.setCellRenderer(new TreeCR());
        tr_GuidTree.setModel(coreMdl.getTreeMdl());
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
        listMenu.setToolEvent(this);
        listMenu.setMenuEvent(this);

        ls_GuidList = new javax.swing.JList();
        ls_GuidList.setComponentPopupMenu(listMenu);
        ls_GuidList.setCellRenderer(new KeysCR());
        ls_GuidList.setModel(coreMdl.getListMdl());
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

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE);
        layout.setVerticalGroup(vsg);
    }

    private void initUserView()
    {
        pl_KeysInfo = new javax.swing.JPanel();

        mainFind = new FindBar();
        mainFind.initView();
        mainFind.setFindEvent(this);

        gridMenu = new MenuPop(MenuPop.MENU_GRID);
        gridMenu.initView();
        gridMenu.setToolEvent(this);
        gridMenu.setMenuEvent(this);

        tb_KeysView = new javax.swing.JTable();
        tb_KeysView.setModel(coreMdl.getGridMdl());
        tb_KeysView.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_KeysView.getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        javax.swing.ActionMap actionMap = tb_KeysView.getActionMap();
        javax.swing.InputMap inputMap = tb_KeysView.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);
        Util.addEditAction(actionMap, inputMap, this);
        // 添加快捷键
        actionMap.put(ConsEnv.EVENT_EDIT_GUID, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                tr_GuidTree.requestFocus();
            }
        });
        inputMap.put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK), ConsEnv.EVENT_EDIT_GUID);
        actionMap.put(ConsEnv.EVENT_EDIT_KEYS, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ls_GuidList.requestFocus();
            }
        });
        inputMap.put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.ALT_MASK), ConsEnv.EVENT_EDIT_KEYS);
        actionMap.put(ConsEnv.EVENT_EDIT_ITEM, new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                tb_KeysView.requestFocus();
            }
        });
        inputMap.put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK), ConsEnv.EVENT_EDIT_ITEM);

        tb_KeysView.addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                // 右键事件处理
                if (evt.isPopupTrigger())
                {
                    int row = tb_KeysView.rowAtPoint(evt.getPoint());
                    tb_KeysView.setRowSelectionInterval(row, row);
                    gridMenu.show(tb_KeysView, evt.getX(), evt.getY());
                }
                else
                {
                    tb_ItemListMouseReleased(evt);
                }
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                // 右键事件处理
                if (evt.isPopupTrigger())
                {
                    int row = tb_KeysView.rowAtPoint(evt.getPoint());
                    tb_KeysView.setRowSelectionInterval(row, row);
                    gridMenu.show(tb_KeysView, evt.getX(), evt.getY());
                }
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                // 右键事件处理
                if (evt.isPopupTrigger())
                {
                    int row = tb_KeysView.rowAtPoint(evt.getPoint());
                    tb_KeysView.setRowSelectionInterval(row, row);
                    gridMenu.show(tb_KeysView, evt.getX(), evt.getY());
                }
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
        sp_KeysView = new javax.swing.JScrollPane(tb_KeysView);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_KeysInfo);
        pl_KeysInfo.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg.addComponent(sp_KeysView, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE);
        hpg.addComponent(pl_KeysEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hpg.addComponent(mainFind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);

        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addGap(5);
        hsg.addGroup(hpg);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addComponent(mainFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(sp_KeysView, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(pl_KeysEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setVerticalGroup(vsg);
    }

    private void initBaseView()
    {
        pl_KeysBase = new javax.swing.JPanel();

        mainInfo = new HintBar(coreMdl.getHintMdl());
        mainInfo.initView();

        javax.swing.JSplitPane sp = new javax.swing.JSplitPane();
        sp.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        sp.setDividerLocation(160);
        sp.setOneTouchExpandable(true);
        sp.setLeftComponent(pl_KeysGuid);
        sp.setRightComponent(pl_KeysInfo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_KeysBase);
        pl_KeysBase.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE);
        hsg.addContainerGap();
        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg.addGroup(hsg);
        hpg.addComponent(mainInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 560, Short.MAX_VALUE);
        layout.setHorizontalGroup(hpg);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        vsg.addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 10, 20);
        vsg.addComponent(mainInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setVerticalGroup(vsg);
    }

    private void initGuidLang()
    {
        gridMenu.initLang();

        Lang.setWTips(tr_GuidTree, LangRes.P30F7B08, "类别列表(Alt + G)");
        Lang.setWTips(ls_GuidList, LangRes.P30F7B09, "口令列表(Alt + K)");
        Lang.setWTips(sp_KeysView, LangRes.P30F7B0A, "属性列表(Alt + T)");
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
        mainMenu.initLang();
        mainTool.initLang();
        mainInfo.initLang();
        mainFind.initLang();
        treeMenu.initLang();
        listMenu.initLang();
    }

    @Override
    protected void processWindowEvent(java.awt.event.WindowEvent e)
    {
        if (e.getID() == java.awt.event.WindowEvent.WINDOW_CLOSING)
        {
            if (coreMdl.getGridMdl().isModified() && javax.swing.JOptionPane.YES_OPTION != Lang.showFirm(this, LangRes.P30F7A42, "您的数据尚未保存，确认要退出吗？"))
            {
                return;
            }
            setVisible(false);
            MagicPwd.endSave();
        }
        else if (e.getID() == java.awt.event.WindowEvent.WINDOW_ICONIFIED)
        {
            this.setVisible(false);

            TrayPtn.getInstance().displayMessage(Lang.getLang(LangRes.P30F9A01, "友情提示"), Lang.getLang(LangRes.P30F7A43, "魔方密码仍在运行中，您可以通过双击此处显示主窗口！"), java.awt.TrayIcon.MessageType.INFO);

            hideWindow();
        }
        super.processWindowEvent(e);
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
            queryKey = kv.getC2010103();
            coreMdl.getListMdl().listName(queryKey);
        }
        isSearch = false;
    }

    private void ls_DataListValueChanged(javax.swing.event.ListSelectionEvent evt)
    {
        int i = ls_GuidList.getSelectedIndex();
        if (i == ls_LastIndx)
        {
            return;
        }
        if (i < 0)
        {
            ls_LastIndx = i;
            return;
        }

        GridMdl gm = coreMdl.getGridMdl();
        if (gm.isModified())
        {
            if (Lang.showFirm(this, LangRes.P30F7A09, "记录数据 {0} 已修改，要放弃修改吗？", gm.getItemAt(ConsEnv.PWDS_HEAD_META).getName()) != javax.swing.JOptionPane.YES_OPTION)
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
            gm.clear();
            gm.loadData(keys.getP30F0104());
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }

        showPropEdit();
    }

    private void tb_ItemListMouseReleased(java.awt.event.MouseEvent evt)
    {
        // 左键事件处理
        int row = tb_KeysView.getSelectedRow();
        if (row < 0 || row > tb_KeysView.getRowCount() || row == tb_LastIndx)
        {
            return;
        }
        tb_LastIndx = row;
        showPropEdit(coreMdl.getGridMdl().getItemAt(row), true);
    }

    private void tb_ItemListKeyReleased(java.awt.event.KeyEvent evt)
    {
        int row = tb_KeysView.getSelectedRow();
        if (row < 0 || row > tb_KeysView.getRowCount() || row == tb_LastIndx)
        {
            return;
        }
        tb_LastIndx = row;
        showPropEdit(coreMdl.getGridMdl().getItemAt(row), false);
    }

    public void showPropEdit()
    {
        if (coreMdl.getUserCfg().isEditViw())
        {
            editBean[ConsDat.INDX_INFO].initData(null);
            cl_CardProp.show(pl_CardProp, ConsEnv.BEAN_INFO);
            editBean[ConsDat.INDX_INFO].requestFocus();
        }
    }

    public void showPropEdit(IEditItem tplt, boolean focus)
    {
        if (coreMdl.getUserCfg().isEditViw())
        {
            editBean[tplt.getType()].initData(tplt);
            cl_CardProp.show(pl_CardProp, ConsEnv.BEAN_PROP + tplt.getType());
            if (focus)
            {
                editBean[tplt.getType()].requestFocus();
            }
            if (coreMdl.getUserCfg().isEditWnd())
            {
                md_MpsDialog.setTitle(getPropName(tplt.getType()));
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

    public void showPropEdit(boolean editWnd)
    {
        pl_KeysEdit.setVisible(!editWnd);

        if (editWnd)
        {
            if (md_MpsDialog != null)
            {
                md_MpsDialog.setPropView(pl_CardProp);
            }
            else
            {
                md_MpsDialog = new MpsDialog(TrayPtn.getCurrForm(), this);
                md_MpsDialog.initView();
                md_MpsDialog.initLang();
                md_MpsDialog.setPropView(pl_CardProp);
                md_MpsDialog.pack();
                md_MpsDialog.setResizable(false);
                java.awt.Dimension a = md_MpsDialog.getSize();
                java.awt.Dimension b = TrayPtn.getCurrForm().getSize();
                java.awt.Point p = TrayPtn.getCurrForm().getLocation();
                md_MpsDialog.setLocation(p.x + b.width, p.y + b.height - a.height);
            }
            if (!md_MpsDialog.isVisible())
            {
                md_MpsDialog.setVisible(true);
            }
        }
        else
        {
            pl_KeysEdit.add(pl_CardProp);
            if (md_MpsDialog != null && md_MpsDialog.isVisible())
            {
                md_MpsDialog.setVisible(false);
            }
        }
    }

    private boolean changeKind(String hash)
    {
        GridMdl gm = coreMdl.getGridMdl();
        if (hash == null || hash.equals(gm.getItemAt(ConsEnv.PWDS_HEAD_GUID).getData()))
        {
            return true;
        }

        gm.getItemAt(ConsEnv.PWDS_HEAD_GUID).setData(hash);
        try
        {
            gm.saveData(true, true);
            coreMdl.getListMdl().wRemove(ls_LastIndx);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
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

        if (!coreMdl.getUserCfg().isEditViw())
        {
            mainMenu.setViewPropSelected(true);
            mainMenu.setViewSideSelected(true);
            coreMdl.getUserCfg().setEditViw(true);
            coreMdl.getUserCfg().setEditWnd(true);
            showPropEdit(true);
        }

        showPropEdit(coreMdl.getGridMdl().initGuid(), true);
        return false;
    }

    private boolean exportData()
    {
        javax.swing.tree.TreePath path = tr_GuidTree.getSelectionPath();
        KindTN node = (KindTN) path.getLastPathComponent();
        Kind kind = (Kind) node.getUserObject();

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
                Logs.exception(exp);
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
            int size = coreMdl.getGridMdl().wExport(data, kind.getC2010103());
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
        Kind kind = (Kind) node.getUserObject();

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
            int size = coreMdl.getGridMdl().wImport(data, kind.getC2010103());
            coreMdl.getListMdl().listName(kind.getC2010103());
            Lang.showMesg(this, LangRes.P30F7A07, "成功导入数据个数：{0}", "" + size);

        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30F7A08, "TXT文档格式解析出错，数据导入失败！");
        }
        return true;
    }
    /**
     * 
     */
    private javax.swing.JPanel pl_KeysBase;
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
    private javax.swing.JScrollPane sp_KeysView;
}
