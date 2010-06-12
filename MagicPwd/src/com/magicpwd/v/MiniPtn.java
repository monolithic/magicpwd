/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.v;

import com.magicpwd.MagicPwd;
import com.magicpwd._comn.S1S2;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IBackCall;
import com.magicpwd._face.IEditItem;
import com.magicpwd._face.IFormView;
import com.magicpwd._util.Lang;
import com.magicpwd._user.UserSign;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.c.FindEvt;
import com.magicpwd.c.MenuEvt;
import com.magicpwd.d.DBA3000;
import com.magicpwd.m.UserMdl;
import com.magicpwd.r.ListCR;

/**
 * 迷你模式：记事便签
 *
 * @author Amon
 */
public class MiniPtn extends javax.swing.JFrame implements IFormView, MenuEvt, FindEvt
{

    private String lastHash;
    private java.awt.CardLayout infoLayout;
    private java.util.List<S1S2> noteList;

    public MiniPtn()
    {
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
        ta_NoteData = new javax.swing.JTextArea();
        bt_ExitNote = new BtnLabel();
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
                tf_SrchNoteActionPerformed(evt);
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

        sp_NoteData.setViewportView(ta_NoteData);

        bt_ExitNote.setIcon(Util.getIcon(ConsEnv.ICON_SOFT_EXIT));
        bt_ExitNote.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ExitNoteActionPerformed(evt);
            }
        });

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
        hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg2.addComponent(bt_ExitNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
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
        vpg2.addComponent(bt_ExitNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
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
        this.setIconImage(Util.getLogo());
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

        Lang.setWText(bt_ExitNote, LangRes.P30F5509, "&X");
        Lang.setWTips(bt_ExitNote, LangRes.P30F550A, "退出(Alt + X)");

    }

    public void initData()
    {
        noteList = new java.util.ArrayList<S1S2>();
        Util.addEditAction(pl_NoteBase.getActionMap(), pl_NoteBase.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW), this);
        Util.addFileAction(pl_NoteBase.getActionMap(), pl_NoteBase.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW), this);
    }

    @Override
    public void setVisible(boolean visible)
    {
        super.setVisible(visible);
    }

    @Override
    public javax.swing.JFrame getForm()
    {
        return this;
    }

    @Override
    public void dataImptActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void dataExptActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void dataSyncActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void dataBackActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void dataDocsActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void helpSKeyActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void editAreaActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void editDateActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void editFileActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void editFindActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void editLinkActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void editMailActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void editNextActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void editPrevActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void editPwdsActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void editTextActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void fileApndActionPerformed(java.awt.event.ActionEvent evt)
    {
        tf_NoteHead.setText("");
        ta_NoteData.setText("");
        infoLayout.show(pl_NoteInfo, "info");
        lb_NoteInfo.setText("");
        tf_NoteHead.requestFocus();
        UserMdl.getNoteMdl().clear();
    }

    @Override
    public void fileCopyActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void fileDeltActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void fileExitActionPerformed(java.awt.event.ActionEvent evt)
    {
        MagicPwd.exit(0);
    }

    @Override
    public void fileHideActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void fileOpenActionPerformed(java.awt.event.ActionEvent evt)
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
            String path = file.getPath();
            if (path.length() > 20)
            {
                path = "..." + path.substring(path.length() - 12);
            }
            infoLayout.show(pl_NoteInfo, "info");
            lb_NoteInfo.setText(path);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
    }

    @Override
    public void fileSaveActionPerformed(java.awt.event.ActionEvent evt)
    {
        String head = tf_NoteHead.getText();
        if (!Util.isValidate(head))
        {
            Lang.showMesg(this, LangRes.P30F5A01, "请输入记事标题！");
            tf_NoteHead.requestFocus();
            return;
        }

        String data = ta_NoteData.getText();
        if (!Util.isValidate(data))
        {
            if (Lang.showFirm(this, LangRes.P30F5A02, "记事内容并没有实际意义的文字，确认要保存么？") != javax.swing.JOptionPane.NO_OPTION)
            {
                return;
            }
        }

        if (UserMdl.getNoteMdl().getSize() < 1)
        {
            // Guid
            UserMdl.getNoteMdl().initGuid();
            // Meta
            UserMdl.getNoteMdl().initMeta();
            // Logo
            UserMdl.getNoteMdl().initLogo();
            // Hint
            UserMdl.getNoteMdl().initHint();
            // Note
            UserMdl.getNoteMdl().initNote();
        }

        UserMdl.getNoteMdl().setNote(head, data);

        try
        {
            UserMdl.getNoteMdl().saveData(true);
            infoLayout.show(pl_NoteInfo, "info");
            Lang.setWText(lb_NoteInfo, LangRes.P30F5A03, "");
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30F5A04, "");
        }
    }

    @Override
    public void helpHelpActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void helpJavaActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void helpInfoActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void helpMailActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void helpSiteActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void helpUpdtActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void keysModeActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void keysNoteActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void listSkeyActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void listSascActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void kindApndActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void kindDeltActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void kindUpdtActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void skinChangeActionPerformed(java.awt.event.ActionEvent evt)
    {
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
    }

    @Override
    public void userSecretActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void viewEditActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void viewFindActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void viewInfoActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void viewMenuActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void viewSideActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void viewToolActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void viewTop1ActionPerformed(java.awt.event.ActionEvent evt)
    {
        boolean b = !UserMdl.getUserCfg().isViewTop();
        TrayPtn.getCurrForm().setAlwaysOnTop(b);

        UserMdl.getUserCfg().setViewTop(b);
    }

    @Override
    public void findActionPerformed(java.awt.event.ActionEvent evt)
    {
        String noteName = tf_NoteHead.getText();
        if (!Util.isValidate(noteName))
        {
            return;
        }

        noteList.clear();
        boolean b = DBA3000.findUserNote(noteName, noteList);
        b &= noteList.size() > 0;
        if (!b || noteList.size() < 1)
        {
            Lang.showMesg(this, LangRes.P30F7A1F, "搜索不到与标题相匹配的记事内容，请修改查询条件后重试！");
            tf_NoteHead.requestFocus();
            return;
        }

        lastHash = "";
        infoLayout.show(pl_NoteInfo, "list");
        cb_NoteInfo.removeAllItems();
        for (S1S2 item : noteList)
        {
            cb_NoteInfo.addItem(item);
        }
    }

    private void bt_ExitNoteActionPerformed(java.awt.event.ActionEvent evt)
    {
        UserSign us = new UserSign(TrayPtn.getCurrForm());
        us.setConfrmBackCall(new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                return showFrm();
            }
        });
        us.initView(ConsEnv.SIGN_RS);
        us.initLang();
        us.initData();
    }

    private void bt_SaveNoteActionPerformed(java.awt.event.ActionEvent evt)
    {
        fileSaveActionPerformed(evt);
    }

    private void bt_SrchNoteActionPerformed(java.awt.event.ActionEvent evt)
    {
        findActionPerformed(evt);
    }

    private void bt_OpenNoteActionPerformed(java.awt.event.ActionEvent evt)
    {
        fileOpenActionPerformed(evt);
    }

    private void bt_CrteNoteActionPerformed(java.awt.event.ActionEvent evt)
    {
        fileApndActionPerformed(evt);
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
            UserMdl.getNoteMdl().clear();
            UserMdl.getNoteMdl().loadData(lastHash);
            IEditItem note = UserMdl.getNoteMdl().getNote();
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
    }

    private void ck_NoteWrapStateChanged(javax.swing.event.ChangeEvent evt)
    {
        ta_NoteData.setLineWrap(ck_NoteWrap.isSelected());
    }

    private void tf_SrchNoteActionPerformed(java.awt.event.ActionEvent evt)
    {
        findActionPerformed(evt);
    }

    @Override
    protected void processWindowEvent(java.awt.event.WindowEvent e)
    {
        if (e.getID() == java.awt.event.WindowEvent.WINDOW_CLOSING)
        {
            fileExitActionPerformed(null);
        }
        super.processWindowEvent(e);
    }

    private boolean showFrm()
    {
        TrayPtn.showMainPtn();
        TrayPtn.getCurrForm().setVisible(true);
        this.setVisible(false);
        return true;
    }
    private javax.swing.JPanel pl_NoteBase;
    private javax.swing.JPanel pl_NoteInfo;
    private javax.swing.JCheckBox ck_NoteWrap;
    private javax.swing.JComboBox cb_NoteInfo;
    private javax.swing.JLabel lb_NoteHead;
    private javax.swing.JLabel lb_NoteInfo;
    private javax.swing.JTextArea ta_NoteData;
    private javax.swing.JTextField tf_NoteHead;
    private BtnLabel bt_ExitNote;
    private BtnLabel bt_CrteNote;
    private BtnLabel bt_OpenNote;
    private BtnLabel bt_SaveNote;
    private BtnLabel bt_SrchNote;
}
