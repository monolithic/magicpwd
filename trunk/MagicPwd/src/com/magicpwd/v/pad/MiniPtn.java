package com.magicpwd.v.pad;

import com.magicpwd._comn.S1S2;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comp.WTextArea;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IEditItem;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.c.FindEvt;
import com.magicpwd.c.MPadEvt;
import com.magicpwd.m.NoteMdl;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.r.ListCR;
import com.magicpwd.v.TrayPtn;

/**
 * 迷你模式：记事便签
 *
 * @author Amon
 */
public class MiniPtn extends javax.swing.JFrame implements MPadEvt, FindEvt
{

    private String lastHash;
    private java.awt.CardLayout infoLayout;
    private java.util.List<S1S2> noteList;
    private CoreMdl coreMdl;

    public MiniPtn(CoreMdl coreMdl)
    {
        this.coreMdl = coreMdl;
    }

    public void initView()
    {
        pl_NoteBase = new javax.swing.JPanel();
        lb_NoteHead = new javax.swing.JLabel();
        tf_NoteHead = new javax.swing.JTextField();
        bt_CrteNote = new BtnLabel();
        bt_OpenNote = new BtnLabel();
        bt_SaveNote = new BtnLabel();
        bt_SrchNote = new BtnLabel();
        javax.swing.JScrollPane sp_NoteData = new javax.swing.JScrollPane();
        ta_NoteData = new WTextArea();
        ck_NoteWrap = new javax.swing.JCheckBox();
        pl_NoteInfo = new javax.swing.JPanel();
        lb_NoteInfo = new javax.swing.JLabel();
        cb_NoteInfo = new javax.swing.JComboBox();

        lb_NoteHead.setLabelFor(tf_NoteHead);

        tf_NoteHead.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                tf_NoteHeadActionPerformed(evt);
            }
        });
        tf_NoteHead.addFocusListener(new java.awt.event.FocusAdapter()
        {

            @Override
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                tf_NoteHead.selectAll();
            }
        });

        bt_CrteNote.setIcon(Util.getIcon(ConsEnv.ICON_KEYS_APND));
        bt_CrteNote.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_CrteNoteActionPerformed(evt);
            }
        });

        bt_OpenNote.setIcon(Util.getIcon(ConsEnv.ICON_FILE_PICK));
        bt_OpenNote.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_OpenNoteActionPerformed(evt);
            }
        });

        bt_SaveNote.setIcon(Util.getIcon(ConsEnv.ICON_KEYS_SAVE));
        bt_SaveNote.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_SaveNoteActionPerformed(evt);
            }
        });

        bt_SrchNote.setIcon(Util.getIcon(ConsEnv.ICON_NOTE_SRCH));
        bt_SrchNote.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_SrchNoteActionPerformed(evt);
            }
        });

        ta_NoteData.setDragEnabled(true);
        sp_NoteData.setViewportView(ta_NoteData);

        ck_NoteWrap.addChangeListener(new javax.swing.event.ChangeListener()
        {

            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                ck_NoteWrapStateChanged(evt);
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
        hsg1.addComponent(tf_NoteHead, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE);
        hsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg1.addComponent(bt_SrchNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg1.addGap(2);
        hsg1.addComponent(bt_SaveNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg1.addGap(2);
        hsg1.addComponent(bt_OpenNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg1.addGap(2);
        hsg1.addComponent(bt_CrteNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
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
        vpg1.addComponent(bt_CrteNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg1.addComponent(bt_OpenNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg1.addComponent(bt_SaveNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg1.addComponent(bt_SrchNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vpg1.addComponent(tf_NoteHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
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

        this.getContentPane().add(pl_NoteBase);
        this.pack();
        this.setIconImage(Util.getLogo(16));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Util.centerForm(this, null);
    }

    public void initLang()
    {
        setTitle(Lang.getLang(LangRes.P30F5201, "记事便签"));


        Lang.setWText(lb_NoteHead, LangRes.P30F5301, "标题");

        Lang.setWText(ck_NoteWrap, LangRes.P30F5302, "自动换行");

        Lang.setWText(bt_SrchNote, LangRes.P30F5501, "&F");
        Lang.setWTips(bt_SrchNote, LangRes.P30F5502, "搜索(Alt + F)");

        Lang.setWText(bt_SaveNote, LangRes.P30F5503, "&S");
        Lang.setWTips(bt_SaveNote, LangRes.P30F5504, "保存(Alt + S)");

        Lang.setWText(bt_OpenNote, LangRes.P30F5505, "&O");
        Lang.setWTips(bt_OpenNote, LangRes.P30F5506, "打开(Alt + O)");

        Lang.setWText(bt_CrteNote, LangRes.P30F5507, "&N");
        Lang.setWTips(bt_CrteNote, LangRes.P30F5508, "新建(Alt + N)");

//        noteMenu.initLang();
    }

    public void initData()
    {
        noteList = new java.util.ArrayList<S1S2>();
//        Util.addFormAction(pl_NoteBase.getActionMap(), pl_NoteBase.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW), this);
//        Util.addFileAction(pl_NoteBase.getActionMap(), pl_NoteBase.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW), this);
//        Util.addHideAction(pl_NoteBase.getActionMap(), pl_NoteBase.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW), this);

        pl_NoteBase.getActionMap().put("showMainPtn", new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                TrayPtn.getCurrForm().setVisible(false);
                TrayPtn.showMainPtn();
                TrayPtn.getCurrForm().setVisible(true);
            }
        });
        pl_NoteBase.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK | java.awt.event.InputEvent.ALT_DOWN_MASK), "showMainPtn");

        pl_NoteBase.getActionMap().put("showNormPtn", new javax.swing.AbstractAction()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
            }
        });
        pl_NoteBase.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK | java.awt.event.InputEvent.ALT_DOWN_MASK), "showNormPtn");
    }

    @Override
    public void setVisible(boolean visible)
    {
        super.setVisible(visible);
    }

    private void bt_SaveNoteActionPerformed(java.awt.event.ActionEvent evt)
    {
//        fileSaveActionPerformed(evt);
    }

    private void bt_SrchNoteActionPerformed(java.awt.event.ActionEvent evt)
    {
//        findActionPerformed(evt);
    }

    private void bt_OpenNoteActionPerformed(java.awt.event.ActionEvent evt)
    {
//        fileOpenActionPerformed(evt);
    }

    private void bt_CrteNoteActionPerformed(java.awt.event.ActionEvent evt)
    {
//        fileApndActionPerformed(evt);
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
            NoteMdl noteMdl = coreMdl.getNoteMdl();
            noteMdl.clear();
            noteMdl.loadData(lastHash);
            IEditItem note = noteMdl.getNote();
            if (note != null)
            {
                tf_NoteHead.setText(note.getName());
                ta_NoteData.setText(note.getData());
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, null, exp.getMessage());
            return;
        }

        ta_NoteData.reset();
    }

    private void ck_NoteWrapStateChanged(javax.swing.event.ChangeEvent evt)
    {
        ta_NoteData.setLineWrap(ck_NoteWrap.isSelected());
    }

    private void tf_NoteHeadActionPerformed(java.awt.event.ActionEvent evt)
    {
//        findActionPerformed(evt);
    }

    @Override
    protected void processWindowEvent(java.awt.event.WindowEvent e)
    {
        if (e.getID() == java.awt.event.WindowEvent.WINDOW_CLOSING)
        {
            setVisible(false);
            TrayPtn.endSave();
        }
        else if (e.getID() == java.awt.event.WindowEvent.WINDOW_ICONIFIED)
        {
            hideWindow();
        }
        super.processWindowEvent(e);
    }

    public void hideWindow()
    {
        this.setVisible(false);
    }

    private boolean showMain()
    {
        TrayPtn.showMainPtn();
        this.setVisible(false);
        TrayPtn.getCurrForm().setVisible(true);
        return true;
    }
    private javax.swing.JPanel pl_NoteBase;
    private javax.swing.JPanel pl_NoteInfo;
    private javax.swing.JCheckBox ck_NoteWrap;
    private javax.swing.JComboBox cb_NoteInfo;
    private javax.swing.JLabel lb_NoteHead;
    private javax.swing.JLabel lb_NoteInfo;
    private WTextArea ta_NoteData;
    private javax.swing.JTextField tf_NoteHead;
    private BtnLabel bt_CrteNote;
    private BtnLabel bt_OpenNote;
    private BtnLabel bt_SaveNote;
    private BtnLabel bt_SrchNote;
}
