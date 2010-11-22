/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.e.mpwd.safe;

import com.magicpwd.__a.mpwd.AMpwdAction;
import com.magicpwd._comn.S1S1;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._mail.Connect;
import com.magicpwd._mail.Reader;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.v.TrayPtn;
import com.magicpwd.x.LckDialog;

/**
 *
 * @author Amon
 */
public class CloudResumeAction extends AMpwdAction
{

    public CloudResumeAction()
    {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        if (javax.swing.JOptionPane.YES_OPTION != Lang.showFirm(mainPtn, LangRes.P30F7A41, "确认要执行从云端数据恢复的操作吗，此操作将需要一定的时间？"))
        {
            return;
        }

        TrayPtn.setDbLocked(true);
        final LckDialog dialog = new LckDialog(mainPtn);
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

        Bean.centerForm(dialog, mainPtn);
        dialog.setVisible(true);
    }

    @Override
    public void doInit(Object object)
    {
    }

    @Override
    public void reInit(javax.swing.AbstractButton button)
    {
    }

    private void resumeData(LckDialog dialog)
    {
        try
        {
            String docs = mainPtn.readCfg("pop_mail");
            if (!com.magicpwd._util.Char.isValidate(docs))
            {
                dialog.setVisible(false);
                dialog.dispose();
                Lang.showMesg(mainPtn, LangRes.P30F7A3A, "您还没有配置您的POP邮箱信息！");
                return;
            }

            String[] data = docs.split("\n");

            trayPtn.endSave();
            Connect connect = new Connect(data[0], data[2]);
            connect.setUsername(data[1]);
            if (!connect.useDefault())
            {
                Lang.showMesg(mainPtn, null, "查找不到对应的服务信息，如有疑问请与作者联系！");
                return;
            }
            Reader mail = new Reader(connect);

            // 读取备份文件
            javax.mail.Store store = connect.getStore();
            javax.mail.Folder folder = store.getDefaultFolder().getFolder("inbox");
            if (folder.isOpen())
            {
                folder.close(false);
            }
            folder.open(javax.mail.Folder.READ_ONLY);
            javax.mail.Message message = mail.find(folder, null, Lang.getLang(LangRes.P30F7A48, "魔方密码备份文件！"), null, "http://magicpwd.com/?*" + mainPtn.getUserMdl().getCfg("mail.date"));
            if (message == null)
            {
                dialog.setVisible(false);
                dialog.dispose();
                Lang.showMesg(mainPtn, LangRes.P30F7A3E, "无法从POP邮箱读取备份数据！");
            }
            else if (mail.read(message))
            {
                for (S1S1 item : mail.getAttachmentList())
                {
                    if (ConsEnv.FILE_SYNC.equals(item.getK()))
                    {
                        Jzip.unZip(item.getV(), ".");
                        dialog.setVisible(false);
                        dialog.dispose();
                        Lang.showMesg(mainPtn, LangRes.P30F7A3F, "数据恢复成功，您需要重新启动本程序！");
                        Logs.end();
                        System.exit(0);
                    }
                }
            }
            folder.close(false);
            store.close();
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
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
