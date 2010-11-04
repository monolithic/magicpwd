package com.magicpwd.v.mpad;

import com.magicpwd.__a.AFrame;
import com.magicpwd.__i.IEditItem;
import com.magicpwd._comn.S1S2;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comp.WTextBox;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.d.DBA3000;
import com.magicpwd.e.mpad.FindAction;
import com.magicpwd.e.mpad.NewAction;
import com.magicpwd.e.mpad.OpenAction;
import com.magicpwd.e.mpad.SaveAction;
import com.magicpwd.m.mpad.NoteMdl;
import com.magicpwd.m.UserMdl;
import com.magicpwd.m.mpad.MpadMdl;
import com.magicpwd.r.ListCR;
import com.magicpwd.v.MenuPtn;
import com.magicpwd.v.TrayPtn;

/**
 * 迷你模式：记事便签
 *
 * @author Amon
 */
public class MiniPtn extends AFrame
{

    private String lastHash;
    private java.awt.CardLayout infoLayout;
    private java.util.List<S1S2> noteList;
    private MpadMdl mpadMdl;
    private WTextBox nameBox;
    private WTextBox dataBox;

    public MiniPtn(TrayPtn trayPtn, UserMdl userMdl)
    {
        super(trayPtn, userMdl);
    }

    public void initView()
    {
        pl_NoteBase = new javax.swing.JPanel();
        lb_NoteHead = new javax.swing.JLabel();
        tf_NoteName = new javax.swing.JTextField();
        bt_CreateNote = new BtnLabel();
        bt_OpenNote = new BtnLabel();
        bt_SaveNote = new BtnLabel();
        bt_SearchNote = new BtnLabel();
        javax.swing.JScrollPane sp_NoteData = new javax.swing.JScrollPane();
        ta_NoteData = new javax.swing.JTextArea();
        ck_NoteWrap = new javax.swing.JCheckBox();
        pl_NoteInfo = new javax.swing.JPanel();
        lb_NoteInfo = new javax.swing.JLabel();
        cb_NoteInfo = new javax.swing.JComboBox();

        lb_NoteHead.setLabelFor(tf_NoteName);

        FindAction findAction = new FindAction();
        findAction.setMiniPtn(this);
        tf_NoteName.addActionListener(findAction);
        nameBox = new WTextBox(tf_NoteName, true);
        nameBox.initView();

        bt_CreateNote.setIcon(userMdl.readIcon(ConsEnv.FEEL_PATH + "file-new.png"));
        NewAction newAction = new NewAction();
        newAction.setMiniPtn(this);
        bt_CreateNote.addActionListener(newAction);
        Bean.registerKeyStrokeAction(rootPane, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK), newAction, "file-new", javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);

        bt_OpenNote.setIcon(userMdl.readIcon(ConsEnv.FEEL_PATH + "file-open.png"));
        OpenAction openAction = new OpenAction();
        openAction.setMiniPtn(this);
        bt_OpenNote.addActionListener(openAction);
        Bean.registerKeyStrokeAction(rootPane, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK), openAction, "file-open", javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);

        bt_SaveNote.setIcon(userMdl.readIcon(ConsEnv.FEEL_PATH + "file-save.png"));
        SaveAction saveAction = new SaveAction();
        saveAction.setMiniPtn(this);
        bt_SaveNote.addActionListener(saveAction);
        Bean.registerKeyStrokeAction(rootPane, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK), saveAction, "file-save", javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);

        bt_SearchNote.setIcon(userMdl.readIcon(ConsEnv.FEEL_PATH + "find.png"));
        bt_SearchNote.addActionListener(findAction);

        ta_NoteData.setDragEnabled(true);
        dataBox = new WTextBox(ta_NoteData);
        dataBox.initView();
        sp_NoteData.setViewportView(ta_NoteData);

        ck_NoteWrap.addChangeListener(new javax.swing.event.ChangeListener()
        {

            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                ta_NoteData.setLineWrap(ck_NoteWrap.isSelected());
            }
        });

        infoLayout = new java.awt.CardLayout();
        pl_NoteInfo.setLayout(infoLayout);
        pl_NoteInfo.add("info", lb_NoteInfo);

        cb_NoteInfo.setRenderer(new ListCR());
        cb_NoteInfo.addItemListener(new java.awt.event.ItemListener()
        {

            @Override
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cb_NoteInfoItemStateChanged(evt);
            }
        });
        pl_NoteInfo.add("list", cb_NoteInfo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_NoteBase);
        pl_NoteBase.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addComponent(lb_NoteHead);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(tf_NoteName, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(bt_SearchNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg1.addGap(2);
        hsg1.addComponent(bt_SaveNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg1.addGap(2);
        hsg1.addComponent(bt_OpenNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg1.addGap(2);
        hsg1.addComponent(bt_CreateNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addComponent(pl_NoteInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE);
        hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg2.addComponent(ck_NoteWrap);
        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        hpg.addGroup(hsg1);
        hpg.addComponent(sp_NoteData, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE);
        hpg.addGroup(hsg2);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addGroup(hpg);
        hsg.addContainerGap();
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER);
        vpg1.addComponent(lb_NoteHead);
        vpg1.addComponent(bt_CreateNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg1.addComponent(bt_OpenNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg1.addComponent(bt_SaveNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg1.addComponent(bt_SearchNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg1.addComponent(tf_NoteName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER);
        vpg2.addComponent(ck_NoteWrap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg2.addComponent(pl_NoteInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();
        vsg.addGroup(vpg1);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(sp_NoteData, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addGroup(vpg2);
        vsg.addContainerGap();
        layout.setVerticalGroup(vsg);

        getContentPane().add(pl_NoteBase);

        setIconImage(Bean.getLogo(16));
        pack();
        Bean.centerForm(this, null);
    }

    public void initLang()
    {
        setTitle(Lang.getLang(LangRes.P30F5201, "记事便签"));

        Lang.setWText(lb_NoteHead, LangRes.P30F5301, "标题");

        Lang.setWText(ck_NoteWrap, LangRes.P30F5302, "自动换行");

        Lang.setWText(bt_SearchNote, LangRes.P30F5501, "&F");
        Lang.setWTips(bt_SearchNote, LangRes.P30F5502, "搜索(Alt + F)");

        Lang.setWText(bt_SaveNote, LangRes.P30F5503, "&S");
        Lang.setWTips(bt_SaveNote, LangRes.P30F5504, "保存(Alt + S)");

        Lang.setWText(bt_OpenNote, LangRes.P30F5505, "&O");
        Lang.setWTips(bt_OpenNote, LangRes.P30F5506, "打开(Alt + O)");

        Lang.setWText(bt_CreateNote, LangRes.P30F5507, "&N");
        Lang.setWTips(bt_CreateNote, LangRes.P30F5508, "新建(Alt + N)");

        nameBox.initLang();
        dataBox.initLang();
    }

    public void initData()
    {
        super.setVisible(true);

        noteList = new java.util.ArrayList<S1S2>();
        nameBox.initData();
        dataBox.initData();

        javax.swing.Action action = new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                setVisible(false);
                trayPtn.showViewPtn(ConsEnv.APP_MODE_MPWD);
            }
        };
        Bean.registerKeyStrokeAction(rootPane, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK | java.awt.event.InputEvent.ALT_DOWN_MASK), action, "showMainPtn", javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);

        action = new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
            }
        };
        Bean.registerKeyStrokeAction(rootPane, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK | java.awt.event.InputEvent.ALT_DOWN_MASK), action, "showNormPtn", javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private void cb_NoteInfoItemStateChanged(java.awt.event.ItemEvent evt)
    {
        S1S2 item = (S1S2) cb_NoteInfo.getSelectedItem();
        if (item == null || item.getK().equals(lastHash))
        {
            return;
        }
        lastHash = item.getK();

        try
        {
            NoteMdl noteMdl = mpadMdl.getNoteMdl();
            noteMdl.clear();
            noteMdl.loadData(lastHash);
            IEditItem note = noteMdl.getNote();
            if (note != null)
            {
                tf_NoteName.setText(note.getName());
                ta_NoteData.setText(note.getData());
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, null, exp.getMessage());
            return;
        }

        dataBox.reset();
    }

    @Override
    public boolean endSave()
    {
        return true;
    }

    public void requestFocus()
    {
    }

    @Override
    public MenuPtn getMenuPtn()
    {
        return null;
    }

    public void findNote()
    {
        String name = tf_NoteName.getText();
        if (!com.magicpwd._util.Char.isValidate(name))
        {
            return;
        }

        noteList.clear();
        boolean b = DBA3000.findUserNote(userMdl, name, noteList);
        b &= noteList.size() > 0;
        if (!b || noteList.size() < 1)
        {
            Lang.showMesg(this, LangRes.P30F7A1F, "搜索不到与标题相匹配的记事内容，请修改查询条件后重试！");
            tf_NoteName.requestFocus();
            return;
        }

        lastHash = "";
        infoLayout.show(pl_NoteInfo, "list");
        cb_NoteInfo.removeAllItems();
        for (S1S2 item : noteList)
        {
            cb_NoteInfo.addItem(item);
        }

        nameBox.reset();
        dataBox.reset();
    }

    public void newNote()
    {
        tf_NoteName.setText("");
        ta_NoteData.setText("");
        infoLayout.show(pl_NoteInfo, "info");
        lb_NoteInfo.setText("");
        tf_NoteName.requestFocus();
        mpadMdl.getNoteMdl().clear();

        nameBox.reset();
        dataBox.reset();
    }

    public void openNote()
    {
        javax.swing.JFileChooser jfc = new javax.swing.JFileChooser();
        jfc.setMultiSelectionEnabled(false);
        jfc.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
        int status = jfc.showOpenDialog(this);
        if (status != javax.swing.JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        java.io.File file = jfc.getSelectedFile();
        if (!file.exists())
        {
            Lang.showMesg(this, LangRes.P30F7A03, "", "");
            return;
        }
        if (!file.isFile())
        {
            Lang.showMesg(this, LangRes.P30F7A04, "", "");
            return;
        }
        if (!file.canRead())
        {
            Lang.showMesg(this, LangRes.P30F7A05, "");
            return;
        }
        if (file.length() > 1048576)
        {
            Lang.showMesg(this, LangRes.P30F7A06, "");
            return;
        }
        try
        {
            byte[] buf = new byte[(int) file.length()];
            java.io.FileInputStream fis = new java.io.FileInputStream(file);
            int len = fis.read(buf);
            fis.close();
            ta_NoteData.setText(new String(buf, 0, len));
            String path = file.getName();
            if (path.length() > 20)
            {
                path = "..." + path.substring(path.length() - 20);
            }
            infoLayout.show(pl_NoteInfo, "info");
            lb_NoteInfo.setText(path);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }

        nameBox.reset();
        dataBox.reset();
    }

    public void saveNote()
    {
        String name = tf_NoteName.getText();
        if (!com.magicpwd._util.Char.isValidate(name))
        {
            Lang.showMesg(this, LangRes.P30F5A01, "请输入记事标题！");
            tf_NoteName.requestFocus();
            return;
        }

        String data = ta_NoteData.getText();
        if (!com.magicpwd._util.Char.isValidate(data))
        {
            if (Lang.showFirm(this, LangRes.P30F5A02, "记事内容并没有实际意义的文字，确认要保存么？") != javax.swing.JOptionPane.NO_OPTION)
            {
                return;
            }
        }

        NoteMdl noteMdl = mpadMdl.getNoteMdl();
        if (noteMdl.getSize() < 1)
        {
            noteMdl.initHead();
            noteMdl.initBody();
        }

        noteMdl.setNote(name, data);

        try
        {
            noteMdl.saveData(true);
            infoLayout.show(pl_NoteInfo, "info");
            Lang.setWText(lb_NoteInfo, LangRes.P30F5A03, "");
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30F5A04, "");
        }
    }

    public void deleteNote()
    {
    }
    private javax.swing.JPanel pl_NoteBase;
    private javax.swing.JPanel pl_NoteInfo;
    private javax.swing.JCheckBox ck_NoteWrap;
    private javax.swing.JComboBox cb_NoteInfo;
    private javax.swing.JLabel lb_NoteHead;
    private javax.swing.JLabel lb_NoteInfo;
    private javax.swing.JTextArea ta_NoteData;
    private javax.swing.JTextField tf_NoteName;
    private BtnLabel bt_OpenNote;
    private BtnLabel bt_SaveNote;
    private BtnLabel bt_CreateNote;
    private BtnLabel bt_SearchNote;
}
