/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.__a;

import com.magicpwd.__i.IBackCall;
import com.magicpwd._comn.S1S1;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._mail.Connect;
import com.magicpwd._mail.Reader;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.m.SafeMdl;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MenuPtn;
import com.magicpwd.v.TrayPtn;
import com.magicpwd.x.LckDialog;
import java.io.IOException;
import java.util.EventListener;

/**
 *
 * @author Amon
 */
public abstract class AFrame extends javax.swing.JFrame
{

    protected TrayPtn trayPtn;
    protected UserMdl userMdl;
    protected SafeMdl safeMdl;
    protected static java.util.HashMap<String, javax.swing.Icon> defIcon;
    private static java.util.Properties defProp;
    private java.util.Properties favProp;
    private LckDialog lckDialog;

    public AFrame(TrayPtn trayPtn, UserMdl userMdl)
    {
        this.trayPtn = trayPtn;
        this.userMdl = userMdl;
    }

    public static void loadPre()
    {
        defProp = new java.util.Properties();
        java.io.InputStream stream = null;
        try
        {
            stream = AFrame.class.getResourceAsStream("/res/feel.amf");
            defProp.load(stream);
        }
        catch (IOException ex)
        {
            Logs.exception(ex);
        }
        finally
        {
            Bean.closeStream(stream);
        }

        defIcon = new java.util.HashMap<String, javax.swing.Icon>();
        try
        {
            stream = AFrame.class.getResourceAsStream(ConsEnv.ICON_PATH + "icon.png");
            java.awt.image.BufferedImage bufImg = javax.imageio.ImageIO.read(stream);

            int w = bufImg.getWidth();
            int h = bufImg.getHeight();
            for (int i = 0, j = 0; j < w; i += 1)
            {
                defIcon.put("def:" + i, new javax.swing.ImageIcon(bufImg.getSubimage(j, 0, h, h)));
                j += h;
            }
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        finally
        {
            Bean.closeStream(stream);
        }
    }

    public abstract MenuPtn getMenuPtn();

    /**
     * 用户数据保存
     * @return
     */
    public abstract boolean endSave();

    @Override
    public abstract void requestFocus();

    @Override
    protected void processWindowEvent(java.awt.event.WindowEvent e)
    {
        if (e.getID() == java.awt.event.WindowEvent.WINDOW_CLOSING)
        {
            hideFrame();
            return;
        }
        super.processWindowEvent(e);
    }

    private void loadFav()
    {
        favProp = new java.util.Properties();

        java.io.File file = new java.io.File(ConsEnv.DIR_SKIN, ConsEnv.DIR_FEEL);
        if (!file.exists() || !file.isDirectory() || !file.canRead())
        {
            return;
        }
        file = new java.io.File(file, userMdl.getFeel() + '/' + ConsEnv.SKIN_FEEL_FILE);
        if (!file.exists() || !file.isFile() || !file.canRead())
        {
            return;
        }
        java.io.FileInputStream stream = null;
        try
        {
            stream = new java.io.FileInputStream(file);
            favProp.load(stream);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        finally
        {
            Bean.closeStream(stream);
        }
    }

    /**
     * 获取用户偏好图片
     * @param favHash
     * @return
     */
    public javax.swing.Icon getFavIcon(String favHash)
    {
        if (!Char.isValidate(favHash))
        {
            return Bean.getNone();
        }

        return defIcon.get("fav:" + favHash);
    }

    public javax.swing.Icon getDefIcon(String favHash)
    {
        if (!Char.isValidate(favHash))
        {
            return Bean.getNone();
        }

        if (defProp.containsKey(favHash))
        {
            favHash = defProp.getProperty(favHash);
        }
        return defIcon.get("def:" + favHash);
    }

    /**
     * 缓存用户偏好图片
     * @param favHash
     * @param favIcon
     */
    public void setFavIcon(String favHash, javax.swing.Icon favIcon)
    {
        if (Char.isValidate(favHash))
        {
//            if (defProp.containsKey(favHash))
//            {
//                favHash = defProp.getProperty(favHash);
//            }
            defIcon.put("fav:" + favHash, favIcon);
        }
    }

    public javax.swing.Icon readFavIcon(String favHash, boolean chache)
    {
        if (!Char.isValidate(favHash))
        {
            return Bean.getNone();
        }

        if (favProp == null)
        {
            loadFav();
        }

        javax.swing.Icon icon;
        if (!chache)
        {
            icon = favProp.containsKey(favHash) ? userMdl.readIcon(ConsEnv.FEEL_PATH + favProp.getProperty(favHash)) : getDefIcon(favHash);
            return icon != null ? icon : Bean.getNone();
        }

        icon = getFavIcon(favHash);
        if (icon == null)
        {
            icon = favProp.containsKey(favHash) ? userMdl.readIcon(ConsEnv.FEEL_PATH + favProp.getProperty(favHash)) : getDefIcon(favHash);
            //favProp.remove(favHash);
            setFavIcon(favHash, icon);
        }
        return icon;
    }

    /**
     * 隐藏窗口
     */
    public void hideFrame()
    {
//        setExtendedState(ICONIFIED);
        setVisible(false);
        trayPtn.showTips(Lang.getLang(LangRes.P30F9A01, "友情提示"), Lang.getLang(LangRes.P30F7A43, "魔方密码仍在运行中，您可以通过双击此处显示主窗口！"));
        endSave();
    }

    /**
     * 锁屏窗口
     */
    public void lockFrame()
    {
        trayPtn.getUserPtn(ConsEnv.INT_SIGN_LS, new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, EventListener event, String... params)
            {
                if (params == null || params.length != 1)
                {
                    return false;
                }
                return ConsEnv.STR_SIGN_LS.equalsIgnoreCase(params[0]);
            }
        });
    }

    /**
     * @return the userMdl
     */
    public UserMdl getUserMdl()
    {
        return userMdl;
    }

    /**
     * @param userMdl the userMdl to set
     */
    public void setUserMdl(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }

    public void showProcessDialog()
    {
        lckDialog = new LckDialog(this);
        lckDialog.initView();
        lckDialog.initLang();
        lckDialog.initData();
        Bean.centerForm(lckDialog, this);
        lckDialog.setVisible(true);
    }

    public void hideProcessDialog()
    {
        if (lckDialog.isVisible())
        {
            lckDialog.setVisible(false);
            lckDialog.dispose();
        }
    }

    public synchronized java.util.List<S1S1> checkResume()
    {
        try
        {
            String docs = "";//userMdl.readCfg("pop_mail");
            if (!com.magicpwd._util.Char.isValidate(docs))
            {
                lckDialog.setVisible(false);
                lckDialog.dispose();
                Lang.showMesg(this, LangRes.P30F7A3A, "您还没有配置您的POP邮箱信息！");
                return null;
            }

            String[] data = docs.split("\n");

            trayPtn.endSave();
            Connect connect = new Connect(data[0], data[2]);
            connect.setUsername(data[1]);
            if (!connect.useDefault())
            {
                Lang.showMesg(this, null, "查找不到对应的服务信息，如有疑问请与作者联系！");
                return null;
            }

            // 读取备份文件
            javax.mail.Store store = connect.getStore();
            javax.mail.Folder folder = store.getDefaultFolder().getFolder("inbox");
            if (folder.isOpen())
            {
                folder.close(false);
            }
            folder.open(javax.mail.Folder.READ_ONLY);
            java.util.List<S1S1> mesgList = new java.util.ArrayList<S1S1>();

            String user = userMdl.getCode();
            String sign = userMdl.getCfg("mail.date");
            if (!Char.isValidate(sign, 16))
            {
                sign = null;
            }
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(ConsEnv.HINT_DATE);
            javax.mail.Message[] messages = folder.getMessages();
            if (messages != null)
            {
                String[] headers;
                for (javax.mail.Message mesg : messages)
                {
                    headers = mesg.getHeader("magicpwd-user");
                    if (headers == null || headers.length != 1 || !user.equalsIgnoreCase(headers[0]))
                    {
                        continue;
                    }
                    headers = mesg.getHeader("magicpwd-sign");
                    if (headers == null || headers.length != 1)
                    {
                        continue;
                    }
                    if (sign == null || sign.compareToIgnoreCase(headers[0]) < 0)
                    {
                        mesgList.add(new S1S1(headers[0], format.format(new java.util.Date(Long.parseLong(headers[0], 16)))));
                    }
                }
            }
            folder.close(false);
            store.close();

            lckDialog.setVisible(false);
            lckDialog.dispose();

            return mesgList;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return null;
        }
    }

    public synchronized boolean localBackup(String filePath)
    {
        return true;
    }

    public synchronized boolean localResume(String filePath)
    {
        TrayPtn.setDbLocked(true);

        trayPtn.endSave();

        try
        {
            Jzip.unZip(filePath, ".");
            hideProcessDialog();
            Lang.showMesg(this, LangRes.P30F7A3F, "数据恢复成功，您需要重新启动本程序！");
            Logs.end();
            System.exit(0);
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
    }

    public synchronized boolean cloudBackup()
    {
        return true;
    }

    public synchronized boolean cloudResume(String sign)
    {
        if (!Char.isValidate(sign, 16))
        {
            return false;
        }

        try
        {
            String docs = "";//mainPtn.readCfg("pop_mail");
            if (!com.magicpwd._util.Char.isValidate(docs))
            {
                hideProcessDialog();
                Lang.showMesg(this, LangRes.P30F7A3A, "您还没有配置您的POP邮箱信息！");
                return false;
            }

            String[] data = docs.split("\n");

            trayPtn.endSave();
            Connect connect = new Connect(data[0], data[2]);
            connect.setUsername(data[1]);
            if (!connect.useDefault())
            {
                Lang.showMesg(this, null, "查找不到对应的服务信息，如有疑问请与作者联系！");
                return false;
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
            String user = userMdl.getCode();
            javax.mail.Message message = null;
            javax.mail.Message[] messages = folder.getMessages();
            if (messages != null)
            {
                String[] headers;
                for (javax.mail.Message mesg : messages)
                {
                    headers = mesg.getHeader("magicpwd-user");
                    if (headers == null || headers.length != 1 || !user.equalsIgnoreCase(headers[0]))
                    {
                        continue;
                    }
                    headers = mesg.getHeader("magicpwd-sign");
                    if (headers == null || headers.length != 1)
                    {
                        continue;
                    }
                    if (sign.equalsIgnoreCase(headers[0]))
                    {
                        message = mesg;
                        break;
                    }
                }
            }
            if (message == null)
            {
                hideProcessDialog();
                Lang.showMesg(this, LangRes.P30F7A3E, "无法从POP邮箱读取备份数据！");
            }
            else if (mail.read(message))
            {
                for (S1S1 item : mail.getAttachmentList())
                {
                    if (ConsEnv.FILE_SYNC.equals(item.getK()))
                    {
                        localResume(item.getV());
                    }
                }
            }
            folder.close(false);
            store.close();
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
            Lang.showMesg(this, null, ex.getLocalizedMessage());
        }
        finally
        {
            hideProcessDialog();
            TrayPtn.setDbLocked(false);
        }

        return true;
    }
}
