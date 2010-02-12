package com.magicpwd.v;

import java.util.EventListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.magicpwd.MagicPwd;
import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comn.Item;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IBackCall;
import com.magicpwd._face.IEditItem;
import com.magicpwd._util.Lang;
import com.magicpwd._user.UserSign;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.c.FindEvt;
import com.magicpwd.c.MenuEvt;
import com.magicpwd.d.DBA3000;
import com.magicpwd.m.UserMdl;

/**
 * 迷你模式：记事本功能
 * 
 * @author Amonsoft
 * 
 */
public class MiniPtn extends JPanel implements MenuEvt, FindEvt
{
    public MiniPtn()
    {
    }

    public void initView()
    {
        lb_NoteHead = new javax.swing.JLabel();
        tf_NoteHead = new javax.swing.JTextField();
        bt_CrteNote = new BtnLabel();
        bt_OpenNote = new BtnLabel();
        bt_SaveNote = new BtnLabel();
        bt_SrchNote = new BtnLabel();
        javax.swing.JScrollPane sp1 = new javax.swing.JScrollPane();
        ta_NoteData = new javax.swing.JTextArea();
        bt_ExitNote = new BtnLabel();
        ck_NoteWrap = new javax.swing.JCheckBox();
        lb_NoteInfo = new javax.swing.JLabel();

        lb_NoteHead.setLabelFor(tf_NoteHead);

        tf_NoteHead.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                tf_SrchNoteActionPerformed(evt);
            }
        });

        bt_CrteNote.setIcon(new ImageIcon(Util.getImage(ConsEnv.ICON_DATA_APND)));
        bt_CrteNote.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_CrteNoteActionPerformed(evt);
            }
        });

        bt_OpenNote.setIcon(new ImageIcon(Util.getImage(ConsEnv.ICON_FILE_OPEN)));
        bt_OpenNote.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_OpenNoteActionPerformed(evt);
            }
        });

        bt_SaveNote.setIcon(new ImageIcon(Util.getImage(ConsEnv.ICON_DATA_SAVE)));
        bt_SaveNote.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_SaveNoteActionPerformed(evt);
            }
        });

        bt_SrchNote.setMnemonic('F');
        bt_SrchNote.setIcon(new ImageIcon(Util.getImage(ConsEnv.ICON_DATA_SRCH)));
        bt_SrchNote.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_SrchNoteActionPerformed(evt);
            }
        });

        sp1.setViewportView(ta_NoteData);

        bt_ExitNote.setMnemonic('X');
        bt_ExitNote.setIcon(new ImageIcon(Util.getImage(ConsEnv.ICON_DATA_EXIT)));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap().addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(sp1,
                                javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300,
                                Short.MAX_VALUE).addGroup(
                                javax.swing.GroupLayout.Alignment.LEADING,
                                layout.createSequentialGroup().addComponent(lb_NoteHead).addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(tf_NoteHead,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE).addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(bt_SrchNote,
                                        ConsEnv.BUTN_MINI_WIDH, ConsEnv.BUTN_MINI_WIDH, ConsEnv.BUTN_MINI_WIDH).addGap(
                                        2).addComponent(bt_SaveNote, ConsEnv.BUTN_MINI_WIDH, ConsEnv.BUTN_MINI_WIDH,
                                        ConsEnv.BUTN_MINI_WIDH).addGap(2).addComponent(bt_OpenNote,
                                        ConsEnv.BUTN_MINI_WIDH, ConsEnv.BUTN_MINI_WIDH, ConsEnv.BUTN_MINI_WIDH).addGap(
                                        2).addComponent(bt_CrteNote, ConsEnv.BUTN_MINI_WIDH, ConsEnv.BUTN_MINI_WIDH,
                                        ConsEnv.BUTN_MINI_WIDH)).addGroup(
                                layout.createSequentialGroup().addComponent(lb_NoteInfo,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE).addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(ck_NoteWrap)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bt_ExitNote, ConsEnv.BUTN_NORM_WIDH, ConsEnv.BUTN_NORM_WIDH,
                                                ConsEnv.BUTN_NORM_WIDH))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER).addComponent(lb_NoteHead)
                                .addComponent(bt_CrteNote, ConsEnv.BUTN_MINI_HIGH, ConsEnv.BUTN_MINI_HIGH,
                                        ConsEnv.BUTN_MINI_HIGH).addComponent(bt_OpenNote, ConsEnv.BUTN_MINI_HIGH,
                                        ConsEnv.BUTN_MINI_HIGH, ConsEnv.BUTN_MINI_HIGH).addComponent(bt_SaveNote,
                                        ConsEnv.BUTN_MINI_HIGH, ConsEnv.BUTN_MINI_HIGH, ConsEnv.BUTN_MINI_HIGH)
                                .addComponent(bt_SrchNote, ConsEnv.BUTN_MINI_HIGH, ConsEnv.BUTN_MINI_HIGH,
                                        ConsEnv.BUTN_MINI_HIGH).addComponent(tf_NoteHead,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(sp1,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE).addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER).addComponent(bt_ExitNote,
                                ConsEnv.BUTN_NORM_HIGH, ConsEnv.BUTN_NORM_HIGH, ConsEnv.BUTN_NORM_HIGH).addComponent(
                                ck_NoteWrap).addComponent(lb_NoteInfo)).addContainerGap()));
    }

    public void initLang()
    {
        Lang.setWText(lb_NoteHead, LangRes.P30F5301, "标题");

        Lang.setWText(ck_NoteWrap, LangRes.P30F5302, "自动换行");

        Lang.setWTips(bt_SaveNote, LangRes.P30F5502, "保存");

        Lang.setWTips(bt_OpenNote, LangRes.P30F5503, "打开");

        Lang.setWTips(bt_CrteNote, LangRes.P30F5504, "新建");

        Lang.setWTips(bt_ExitNote, LangRes.P30F5505, "退出");

        Lang.setWTips(bt_SrchNote, LangRes.P30F5501, "搜索");
    }

    public void initData()
    {
        Util.addEditAction(getActionMap(), getInputMap(WHEN_IN_FOCUSED_WINDOW), this);
        Util.addFileAction(getActionMap(), getInputMap(WHEN_IN_FOCUSED_WINDOW), this);
    }

    @Override
    public void dataExptActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void dataSKeyActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    @Override
    public void dataImptActionPerformed(java.awt.event.ActionEvent evt)
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
            Lang.showMesg(this, LangRes.P30F5A01, "", "");
            tf_NoteHead.requestFocus();
            return;
        }
        String data = ta_NoteData.getText();
        if (!Util.isValidate(data))
        {
            if (Lang.showFirm(this, LangRes.P30F5A02, "") != JOptionPane.NO_OPTION)
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
            // Past;
            UserMdl.getNoteMdl().initPast();
            // Note
            UserMdl.getNoteMdl().initNote();
        }

        UserMdl.getNoteMdl().setNote(head, data);

        try
        {
            UserMdl.getNoteMdl().saveData(true);
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
        boolean b = !UserMdl.getCfg().isViewTop();
        MagicPwd.getFrame().setAlwaysOnTop(b);

        UserMdl.getCfg().setViewTop(b);
    }

    @Override
    public void findActionPerformed(java.awt.event.ActionEvent evt)
    {
        String noteName = tf_NoteHead.getText();
        if (!Util.isValidate(noteName))
        {
            return;
        }
        String noteHash = DBA3000.findUserNote(noteName);
        if (!Util.isValidate(noteHash))
        {
            Lang.showMesg(this, LangRes.P30F7A1F, "搜索不到与标题相匹配的记事内容，请修改查询条件后重试！");
            tf_NoteHead.requestFocus();
            return;
        }

        try
        {
            UserMdl.getNoteMdl().clear();
            UserMdl.getNoteMdl().loadData(noteHash);
            IEditItem tplt = UserMdl.getNoteMdl().getTplt(3);
            if (tplt != null)
            {
                tf_NoteHead.setText(tplt.getName());
                ta_NoteData.setText(tplt.getData());
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, exp.getMessage(), "");
            return;
        }
    }

    private void bt_ExitNoteActionPerformed(java.awt.event.ActionEvent evt)
    {
        UserSign us = new UserSign(ConsEnv.SIGN_RS, MagicPwd.getFrame());
        us.setConfrmBackCall(new IBackCall()
        {
            @Override
            public boolean callBack(Object sender, EventListener event, String... params)
            {
                return showFrm();
            }
        });
        us.init();
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

    private void ck_NoteWrapStateChanged(javax.swing.event.ChangeEvent evt)
    {
        ta_NoteData.setLineWrap(ck_NoteWrap.isSelected());
    }

    private void tf_SrchNoteActionPerformed(java.awt.event.ActionEvent evt)
    {
        findActionPerformed(evt);
    }

    private boolean showFrm()
    {
        MagicPwd.showMainPtn();
        if (!MagicPwd.getFrame().isVisible())
        {
            MagicPwd.getFrame().setVisible(true);
        }
        MagicPwd.initMainPtn();
        return true;
    }

    private javax.swing.JCheckBox ck_NoteWrap;
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
