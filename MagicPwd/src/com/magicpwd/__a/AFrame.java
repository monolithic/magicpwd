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
import com.magicpwd._mail.Sender;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.File;
import com.magicpwd._util.Jpng;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.d.DBA3000;
import com.magicpwd.m.SafeMdl;
import com.magicpwd.m.UserMdl;
import com.magicpwd.v.MenuPtn;
import com.magicpwd.v.TrayPtn;
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

    public String getCfgText(String key)
    {
        String text = DBA3000.readConfig(key);
        if (com.magicpwd._util.Char.isValidate(text))
        {
            try
            {
                text = safeMdl.deCrypt(text);
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
                text = null;
            }
        }
        return text;
    }

    public void setCfgText(String key, String text)
    {
        try
        {
            DBA3000.saveConfig(key, safeMdl.enCrypt(text));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            text = null;
        }
    }

    /**
     * 隐藏窗口
     */
    public void hideFrame()
    {
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
        if (pl_LckPanel != null && pl_LckPanel.isVisible())
        {
            return;
        }

        if (pl_LckPanel == null)
        {
            pl_LckPanel = new javax.swing.JPanel();
            pl_LckPanel.setLayout(new java.awt.BorderLayout());
            pl_LckPanel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray));

            lb_LckLabel = new javax.swing.JLabel();
            lb_LckLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lb_LckLabel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.gray));
            pl_LckPanel.add(lb_LckLabel, java.awt.BorderLayout.CENTER);

            getLayeredPane().add(pl_LckPanel, javax.swing.JLayeredPane.MODAL_LAYER);
        }

        setEnabled(false);
        java.awt.Dimension size = this.getContentPane().getSize();
        int w = 200;
        int h = 80;
        if (size.width < w)
        {
            w = size.width;
        }
        if (size.height < h)
        {
            h = size.height;
        }
        pl_LckPanel.setBounds((size.width - w) >> 1, (size.height - h) >> 1, w, h);
        pl_LckPanel.setVisible(true);

        if (jpng == null)
        {
            jpng = new Jpng();
            try
            {
                java.io.InputStream stream = AFrame.class.getResourceAsStream(ConsEnv.ICON_PATH + "wait.png");
                jpng.readIcons(stream, 16, 16);
                stream.close();
                jpng.setLabel(lb_LckLabel);
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }
        jpng.start();
    }

    public void hideProcessDialog()
    {
        if (pl_LckPanel == null || !pl_LckPanel.isVisible())
        {
            return;
        }
        setEnabled(true);
        pl_LckPanel.setVisible(false);
        if (jpng != null)
        {
            jpng.stop();
        }
    }

    public boolean checkResume(java.util.List<S1S1> list)
    {
        try
        {
            String docs = getCfgText("pop_mail");
            if (!com.magicpwd._util.Char.isValidate(docs))
            {
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

            // 读取备份文件
            javax.mail.Store store = connect.getStore();
            javax.mail.Folder folder = store.getDefaultFolder().getFolder("inbox");
            if (folder.isOpen())
            {
                folder.close(false);
            }
            folder.open(javax.mail.Folder.READ_ONLY);

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
                        list.add(new S1S1(headers[0], format.format(new java.util.Date(Long.parseLong(headers[0], 16)))));
                    }
                }
            }
            folder.close(false);
            store.close();

            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
    }

    public boolean localBackup(String filePath, IBackCall backCall)
    {
        if (!Char.isValidate(filePath))
        {
            return false;
        }

        java.io.File srcFile = trayPtn.endSave();

        java.io.File dstFile = new java.io.File(filePath);
        if (!dstFile.exists())
        {
            if (!dstFile.mkdirs())
            {
                return false;
            }
        }
        if (!dstFile.isDirectory() || !dstFile.canWrite())
        {
            return false;
        }
        dstFile = new java.io.File(dstFile, srcFile.getName().replace("amon", "magicpwd").replace("-", "").replace(".backup", ".amb"));
        File.copy(srcFile, dstFile, true);

        if (backCall != null && !backCall.callBack(null, null, dstFile.toString()))
        {
            return false;
        }

        return true;
    }

    public boolean localResume(String filePath, IBackCall backCall)
    {
        if (!Char.isValidate(filePath))
        {
            return false;
        }

        trayPtn.endSave();

        java.io.File srcFile = new java.io.File(filePath);
        if (!srcFile.exists() || !srcFile.isFile() || !srcFile.canRead())
        {
            return false;
        }

        try
        {
            Jzip.unZip(new java.io.FileInputStream(srcFile), new java.io.File("."), true);
            Lang.showMesg(this, LangRes.P30F7A3F, "数据恢复成功，您需要重新启动本程序！");
            Logs.end();
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, null, exp.getLocalizedMessage());
            return false;
        }
        finally
        {
            System.exit(0);
        }
    }

    public boolean cloudBackup(IBackCall backCall) throws Exception
    {
        String user = userMdl.getCode();
        if (!Char.isValidateCode(user))
        {
            return false;
        }

        String docs = getCfgText("pop_mail");
        if (!com.magicpwd._util.Char.isValidate(docs))
        {
            throw new Exception(Lang.getLang(LangRes.P30F7A3A, "您还没有配置您的POP邮箱信息！"));
        }

        java.io.File bakFile = trayPtn.endSave();
        if (bakFile == null || !bakFile.exists() || !bakFile.isFile() || !bakFile.canRead())
        {
            throw new Exception(Lang.getLang(LangRes.P30F7A3B, "压缩用户数据文件出错，请重启软件后重试！"));
        }

        String[] data = docs.split("\n");
        Connect connect = new Connect(data[0], data[2], "smtp");
        connect.setUsername(data[1]);
        if (!connect.useDefault())
        {
            throw new Exception(Lang.getLang(null, "查找不到对应的服务信息，如有疑问请与作者联系！"));
        }

        String sign = Char.lPad(Long.toHexString(new java.util.Date().getTime()), 16, '0');

        Sender sender = new Sender(connect);
        sender.setFrom(data[0]);
        sender.setTo(data[0]);
        sender.setSubject(Lang.getLang(LangRes.P30F7A48, "魔方密码备份文件！"));
        sender.setContent(Lang.getLang(LangRes.P30F7A49, "此邮件为魔方密码数据备份文件，请勿手动删除！"));
        sender.setHeader("magicpwd-user", user);
        sender.setHeader("magicpwd-sign", sign);
        sender.addAttachment(ConsEnv.FILE_SYNC, bakFile.getAbsolutePath());
        if (!sender.send())
        {
            throw new Exception(Lang.getLang(LangRes.P30F7A3C, "系统无法备份您的数据到云端！"));
        }

        if (backCall != null && !backCall.callBack(null, null, sign))
        {
            return false;
        }

        userMdl.setCfg("mail.date", sign);

        return true;
    }

    public boolean cloudResume(String sign, IBackCall backCall) throws Exception
    {
        if (!Char.isValidate(sign, 16))
        {
            return false;
        }

        String user = userMdl.getCode();
        if (!Char.isValidateCode(user))
        {
            return false;
        }

        String docs = getCfgText("pop_mail");
        if (!com.magicpwd._util.Char.isValidate(docs))
        {
            throw new Exception(Lang.getLang(LangRes.P30F7A3A, "您还没有配置您的POP邮箱信息！"));
        }

        trayPtn.endSave();

        String[] data = docs.split("\n");
        Connect connect = new Connect(data[0], data[2]);
        connect.setUsername(data[1]);
        if (!connect.useDefault())
        {
            throw new Exception(Lang.getLang(null, "查找不到对应的服务信息，如有疑问请与作者联系！"));
        }

        Reader reader = new Reader(connect);
        javax.mail.Store store = connect.getStore();
        javax.mail.Folder folder = store.getDefaultFolder().getFolder("inbox");
        if (folder.isOpen())
        {
            folder.close(false);
        }
        folder.open(javax.mail.Folder.READ_ONLY);

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

        if (message != null && reader.read(message))
        {
            for (S1S1 item : reader.getAttachmentList())
            {
                if (ConsEnv.FILE_SYNC.equals(item.getK()))
                {
                    localResume(item.getV(), null);
                }
            }
        }
        folder.close(false);
        store.close();

        if (backCall != null && !backCall.callBack(null, null))
        {
            return false;
        }

        return true;
    }
    private Jpng jpng;
    private javax.swing.JPanel pl_LckPanel;
    private javax.swing.JLabel lb_LckLabel;
}
