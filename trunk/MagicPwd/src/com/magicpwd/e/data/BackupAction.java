/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.data;

import com.magicpwd.MagicPwd;
import com.magicpwd._comn.PwdsItem;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._mail.Connect;
import com.magicpwd._mail.Sender;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.d.DBA3000;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.TrayPtn;
import com.magicpwd.x.LckDialog;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Store;

/**
 *
 * @author Administrator
 */
public class BackupAction extends javax.swing.AbstractAction
{

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (javax.swing.JOptionPane.YES_OPTION != Lang.showFirm(TrayPtn.getCurrForm(), LangRes.P30F7A40, "确认要执行备份数据到云端的操作吗，此操作将需要一定的时间？"))
        {
            return;
        }

        TrayPtn.setDbLocked(true);
        final LckDialog dialog = new LckDialog(TrayPtn.getCurrForm());
        dialog.initView();
        dialog.initLang();
        dialog.initData();

        new Thread()
        {

            @Override
            public void run()
            {
                backupData(dialog);
            }
        }.start();

        Util.centerForm(dialog, TrayPtn.getCurrForm());
        dialog.setVisible(true);
    }

    private void backupData(LckDialog dialog)
    {
        try
        {
            String docs = DBA3000.readConfig("pop_mail");
            if (!com.magicpwd._util.Char.isValidate(docs))
            {
                dialog.setVisible(false);
                dialog.dispose();
                Lang.showMesg(TrayPtn.getCurrForm(), LangRes.P30F7A3A, "您还没有配置您的POP邮箱信息！");
                return;
            }

            PwdsItem pwds = new PwdsItem();
            pwds.getP30F0203().append(docs);
            docs = UserMdl.getGridMdl().deCrypt(pwds).toString();
            String[] data = docs.split("\n");

            java.io.File bakFile = MagicPwd.endSave();
            if (bakFile == null || !bakFile.exists() || !bakFile.canRead())
            {
                dialog.setVisible(false);
                dialog.dispose();
                Lang.showMesg(TrayPtn.getCurrForm(), LangRes.P30F7A3B, "压缩用户数据文件出错，请重启软件后重试！");
                return;
            }

            Connect connect = new Connect(data[0], data[1], "smtp");
            connect.useDefault();
            connect.setUsername(data[0]);
            Sender mail = new Sender(connect);

            // 删除已有文件
            Store store = connect.getStore();
            Folder folder = store.getDefaultFolder();
            folder.open(Folder.READ_WRITE);
            Message message = mail.find(folder, null, Lang.getLang(LangRes.P30F7A48, "魔方密码备份文件！"), null, null);
            if (message != null)
            {
                message.setFlag(Flag.DELETED, true);
            }
            folder.close(true);

            mail.setFrom(data[0]);
            mail.setTo(data[0]);
            mail.setSubject(Lang.getLang(LangRes.P30F7A48, "魔方密码备份文件！"));
            mail.setContent(Lang.getLang(LangRes.P30F7A49, "此邮件为魔方密码数据备份文件，请勿手动删除！"));
            mail.addAttachment(ConsEnv.FILE_SYNC, MagicPwd.endSave().getAbsolutePath());
            //if (!new Google().backup(data[0], data[1], ConsEnv.FILE_SYNC, MagicPwd.endSave()))
            if (!mail.send())
            {
                dialog.setVisible(false);
                dialog.dispose();
                Lang.showMesg(TrayPtn.getCurrForm(), LangRes.P30F7A3C, "系统无法备份您的数据到云端！");
                return;
            }

            dialog.setVisible(false);
            dialog.dispose();
            Lang.showMesg(TrayPtn.getCurrForm(), LangRes.P30F7A3D, "数据成功备份到云端！");
            return;
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
            Lang.showMesg(TrayPtn.getCurrForm(), null, ex.getLocalizedMessage());
        }
        finally
        {
            if (dialog.isVisible())
            {
                dialog.setVisible(false);
                dialog.dispose();
            }
            TrayPtn.setDbLocked(false);
        }
    }
}
