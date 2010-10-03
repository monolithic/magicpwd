/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.pwd.safe;

import com.magicpwd._comn.S1S1;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._mail.Connect;
import com.magicpwd._mail.Reader;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import com.magicpwd.d.DBA3000;
import com.magicpwd.e.pwd.IPwdAction;
import com.magicpwd.m.CoreMdl;
import com.magicpwd.v.pwd.MainPtn;
import com.magicpwd.v.TrayPtn;
import com.magicpwd.x.LckDialog;
import java.awt.event.ActionEvent;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Store;

/**
 *
 * @author Amon
 */
public class CloudResumeAction extends javax.swing.AbstractAction implements IPwdAction
{

    private MainPtn mainPtn;
    private CoreMdl coreMdl;

    public CloudResumeAction()
    {
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (javax.swing.JOptionPane.YES_OPTION != Lang.showFirm(TrayPtn.getCurrForm(), LangRes.P30F7A41, "确认要执行从云端数据恢复的操作吗，此操作将需要一定的时间？"))
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
                resumeData(dialog);
            }
        }.start();

        Util.centerForm(dialog, TrayPtn.getCurrForm());
        dialog.setVisible(true);
    }

    @Override
    public void setMainPtn(MainPtn mainPtn)
    {
        this.mainPtn = mainPtn;
    }

    @Override
    public void setCoreMdl(CoreMdl coreMdl)
    {
        this.coreMdl = coreMdl;
    }

    @Override
    public void doUpdate(Object object)
    {
    }

    private void resumeData(LckDialog dialog)
    {
        try
        {
            String docs = DBA3000.readConfig("pop_mail");
            if (!com.magicpwd._util.Char.isValidate(docs))
            {
                dialog.setVisible(false);
                dialog.dispose();
                Lang.showMesg(mainPtn, LangRes.P30F7A3A, "您还没有配置您的POP邮箱信息！");
                return;
            }

            docs = mainPtn.deCrypt(docs);
            String[] data = docs.split("\n");

            TrayPtn.endSave();
            Connect connect = new Connect(data[0], data[1]);
            connect.useDefault();
            connect.setUsername(data[0]);
            Reader mail = new Reader(connect);

            // 读取备份文件
            Store store = connect.getStore();
            Folder folder = store.getDefaultFolder();
            folder.open(Folder.READ_ONLY);
            Message message = mail.find(folder, null, Lang.getLang(LangRes.P30F7A48, "魔方密码备份文件！"), null, null);
            if (message == null)
            {
                dialog.setVisible(false);
                dialog.dispose();
                Lang.showMesg(mainPtn, LangRes.P30F7A3E, "无法从POP邮箱读取备份数据！");
            }

            if (mail.read(message))
            {
                for (S1S1 item : mail.getAttachmentList())
                {
                    if (ConsEnv.FILE_SYNC.equals(item.getK()))
                    {
                        Jzip.unZip(item.getV(), ".");
                        dialog.setVisible(false);
                        dialog.dispose();
                        Lang.showMesg(mainPtn, LangRes.P30F7A3F, "数据恢复成功，您需要重新启动本程序！");
                        System.exit(0);
                    }
                }
            }
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
            dialog.setVisible(false);
            dialog.dispose();
            Lang.showMesg(mainPtn, null, ex.getLocalizedMessage());
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
