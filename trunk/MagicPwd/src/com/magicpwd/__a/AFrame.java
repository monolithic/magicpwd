/*
 *  Copyright (C) 2010 Amon
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd.__a;

import com.magicpwd.__i.IBackCall;
import com.magicpwd._comn.S1S1;
import com.magicpwd._cons.ConsCfg;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._bean.mail.Connect;
import com.magicpwd._comp.WDialog;
import com.magicpwd._user.UserDto;
import com.magicpwd.m.mail.Reader;
import com.magicpwd.m.mail.Sender;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.File;
import com.magicpwd._util.Jcsv;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.d.db.DBA3000;
import com.magicpwd.d.db.DBAccess;
import com.magicpwd.d.dx.DXA;
import com.magicpwd.d.dx.DXA1000;
import com.magicpwd.d.dx.DXA2000;
import com.magicpwd.m.SafeMdl;
import com.magicpwd.m.UserMdl;
import com.magicpwd.r.AmonFF;
import com.magicpwd.v.MenuPtn;
import com.magicpwd.v.tray.TrayPtn;
import java.io.IOException;
import javax.swing.JLayeredPane;

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
    private static int hintCnt;
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

    public void initDemo()
    {
        java.io.File dir = new java.io.File(ConsEnv.DIR_AMX);
        if (!dir.exists() || !dir.isDirectory() || !dir.canRead())
        {
            return;
        }

        createDialog(true);
        showProgress(true, Lang.getLang(LangRes.P30F1A05, "正在初始化资源数据，请稍候……"));

        String tmp;
        for (java.io.File file : dir.listFiles(new AmonFF("^(0|[A-Za-z]{16})\\.amx$", true)))
        {
            try
            {
                Jcsv csv = new Jcsv(file);
                java.util.ArrayList<java.util.ArrayList<String>> data = csv.readFile();
                tmp = file.getName();
                DXA.getInstance(csv.getHead()).importByKind(userMdl, safeMdl, data, tmp.substring(0, tmp.length() - ConsEnv.DIR_AMX.length()));
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }

        showProgress();
        createDialog(true);
    }

    @Override
    protected void processWindowEvent(java.awt.event.WindowEvent e)
    {
        if (e.getID() == java.awt.event.WindowEvent.WINDOW_CLOSING)
        {
            if (wDialog == null || !wDialog.isShowing())
            {
                hideFrame();
            }
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
        int cnt = userMdl.getTrayHintCnt();
        if (cnt < 0 || hintCnt < cnt)
        {
            trayPtn.showTips(Lang.getLang(LangRes.P30F9A01, "友情提示"), Lang.getLang(LangRes.P30F7A43, "魔方密码仍在运行中，您可以通过双击此处显示主窗口！"));
            hintCnt += 1;
        }
        endSave();
    }

    /**
     * 锁屏窗口
     */
    public void lockFrame()
    {
        trayPtn.getUserPtn(ConsEnv.INT_SIGN_LS, new IBackCall<UserDto>()
        {

            @Override
            public boolean callBack(String options, UserDto object)
            {
                return ConsEnv.STR_SIGN_LS.equalsIgnoreCase(options);
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

    public WDialog createDialog()
    {
        return createDialog(false);
    }

    public WDialog createDialog(boolean resizable)
    {
        return createDialog(resizable, false);
    }

    public WDialog createDialog(boolean resizable, boolean opaque)
    {
        if (wDialog == null)
        {
            wDialog = new WDialog(this);
            wDialog.setOpaque(false);
            wDialog.init();
            getLayeredPane().add(wDialog, new Integer(JLayeredPane.POPUP_LAYER - 1));
        }
        wDialog.setResizable(resizable);
        wDialog.setOpaque(opaque);
        return wDialog;
    }

    /**
     * 显示进度窗口（无提示信息）
     */
    public void showProgress()
    {
        showProgress(true);
    }

    public void showProgress(boolean modal)
    {
        showProgress(modal, null);
    }

    /**
     * 显示进度窗口（有提示信息）
     * @param notice
     */
    public void showProgress(boolean modal, String notice)
    {
        if (pl_BarPanel == null)
        {
            pl_BarPanel = new javax.swing.JPanel();
            pl_BarPanel.setLayout(new java.awt.BorderLayout());
            pl_BarPanel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray));
            pl_BarPanel.setVisible(false);

            lb_IcoLabel = new javax.swing.JLabel();
            lb_IcoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            pl_BarPanel.add(lb_IcoLabel, java.awt.BorderLayout.CENTER);

            lb_TipLabel = new javax.swing.JLabel();
            lb_TipLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            pl_BarPanel.add(lb_TipLabel, java.awt.BorderLayout.SOUTH);

            getLayeredPane().add(pl_BarPanel, javax.swing.JLayeredPane.MODAL_LAYER);
            lb_IcoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(ConsEnv.ICON_PATH + "wait.gif")));
        }

        if (Char.isValidate(notice))
        {
            lb_TipLabel.setText(notice);
        }

        if (pl_BarPanel.isVisible())
        {
            return;
        }

        if (modal)
        {
            createDialog(false, false).setVisible(true);
        }

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
        pl_BarPanel.setBounds((size.width - w) >> 1, (size.height - h) >> 1, w, h);
        pl_BarPanel.setVisible(true);
    }

    /**
     * 隐藏进度窗口
     */
    public void hideProgress()
    {
        if (wDialog.isVisible())
        {
            wDialog.setVisible(false);
        }

        if (pl_BarPanel == null || !pl_BarPanel.isVisible())
        {
            return;
        }
        pl_BarPanel.setVisible(false);
    }

    public boolean nativeDetect(java.util.List<S1S1> list) throws Exception
    {
        String user = userMdl.getCode();
        if (!Char.isValidateCode(user))
        {
            return false;
        }

        String path = userMdl.getCfg(ConsCfg.CFG_SAFE_BACK_LOC);
        if (!Char.isValidate(path))
        {
            return false;
        }

        java.io.File dir = new java.io.File(path);
        if (!dir.exists() || !dir.isDirectory() || !dir.canRead())
        {
            return false;
        }

        java.io.File[] files = dir.listFiles(new AmonFF("magicpwd_[\\d]{14}[.]amb", true));
        StringBuilder buffer = new StringBuilder();
        for (java.io.File file : files)
        {
            if (file.canRead())
            {
                buffer.append(file.getName()).delete(23, 27).delete(0, 9).insert(12, ':').insert(10, ':').insert(8, ' ').insert(6, '-').insert(4, '-');
                list.add(0, new S1S1(file.getAbsolutePath(), buffer.toString()));
                buffer.delete(0, buffer.length());
            }
        }

        return true;
    }

    public boolean nativeBackup(String filePath, IBackCall<java.io.File> backCall) throws Exception
    {
        if (!Char.isValidate(filePath))
        {
            return false;
        }

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

        java.io.File srcFile = trayPtn.endSave();
        dstFile = new java.io.File(dstFile, srcFile.getName().replace("amon", "magicpwd").replace("-", "").replace(".backup", ".amb"));
        File.copy(srcFile, dstFile, true);

        if (backCall != null && !backCall.callBack(null, dstFile))
        {
            return false;
        }

        return true;
    }

    public boolean nativeResume(String filePath, IBackCall backCall) throws Exception
    {
        if (!Char.isValidate(filePath))
        {
            return false;
        }

        java.io.File srcFile = new java.io.File(filePath);
        if (!srcFile.exists() || !srcFile.isFile() || !srcFile.canRead())
        {
            return false;
        }

        trayPtn.endSave();

        DBAccess.locked = true;
        Jzip.unZip(new java.io.FileInputStream(srcFile), new java.io.File("."), true);
        DBAccess.locked = false;
        Logs.end();
        return true;
    }

    public boolean remoteDetect(java.util.List<S1S1> list) throws Exception
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

        String[] data = docs.split("\n");
        Connect connect = new Connect(data[0], data[2]);
        connect.setUsername(data[1]);
        if (!connect.useDefault())
        {
            throw new Exception(Lang.getLang(null, "查找不到对应的服务信息，如有疑问请与作者联系！"));
        }

        javax.mail.Store store = connect.getStore();
        javax.mail.Folder folder = store.getDefaultFolder().getFolder("inbox");
        try
        {
            if (folder.isOpen())
            {
                folder.close(false);
            }
            folder.open(javax.mail.Folder.READ_ONLY);

            String sign = userMdl.getCfg("mail.date");
            if (!Char.isValidate(sign, 16))
            {
                sign = null;
            }
            java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(ConsEnv.HINT_DATE);
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
                        list.add(0, new S1S1(headers[0], formater.format(new java.util.Date(Long.parseLong(headers[0], 16)))));
                    }
                }
            }
        }
        finally
        {
            folder.close(false);
            store.close();
        }

        return true;
    }

    public boolean remoteBackup(IBackCall<String> backCall) throws Exception
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

        if (backCall != null && !backCall.callBack(null, sign))
        {
            return false;
        }

        userMdl.setCfg("mail.date", sign);

        return true;
    }

    public boolean remoteResume(String sign, IBackCall<String> backCall) throws Exception
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
        try
        {
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
                        nativeResume(item.getV(), null);
                    }
                }
            }

            if (backCall != null && !backCall.callBack(null, null))
            {
                return false;
            }
        }
        finally
        {
            folder.close(false);
            store.close();
        }

        return true;
    }

    public boolean exportByKind(String kindHash)
    {
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

        createDialog(true);
        showProgress();
        try
        {
            Jcsv csv = new Jcsv(file);
            csv.setHead("V2");
            java.util.ArrayList<java.util.ArrayList<String>> data = new java.util.ArrayList<java.util.ArrayList<String>>();
            int size = new DXA2000().exportByKind(userMdl, safeMdl, data, kindHash);
            csv.saveFile(data);
            Lang.showMesg(this, LangRes.P30F7A25, "成功导出数据个数：{0}", size + "");
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30F7A26, "数据导出失败，请确认您数据的正确性，然后重新尝试！");
        }
        hideProgress();
        createDialog(true);
        return true;
    }

    public boolean exportByKeys(String keysHash)
    {
        return true;
    }

    public boolean importByKind(String kindHash)
    {
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
            Lang.showMesg(this, LangRes.P30F7A03, "您选取的文件不存在！");
            return false;
        }
        if (!file.isFile())
        {
            Lang.showMesg(this, LangRes.P30F7A04, "请选取一个合适的文档！");
            return false;
        }
        if (!file.canRead())
        {
            Lang.showMesg(this, LangRes.P30F7A05, "无法读取您选择的文件，请确认您是否有足够的权限！");
            return false;
        }

        createDialog(true);
        showProgress();
        try
        {
            Jcsv csv = new Jcsv(file);
            java.util.ArrayList<java.util.ArrayList<String>> data = csv.readFile();
            DXA dxa = "V2".equalsIgnoreCase(csv.getHead()) ? new DXA2000() : new DXA1000();
            int size = dxa.importByKind(userMdl, safeMdl, data, kindHash);
            Lang.showMesg(this, LangRes.P30F7A07, "成功导入数据个数：{0}", "" + size);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30F7A08, "TXT文档格式解析出错，数据导入失败！");
        }
        hideProgress();
        createDialog(false);
        return true;
    }

    public boolean importByKeys(String keysHash)
    {
        return true;
    }
    private WDialog wDialog;
    private javax.swing.JPanel pl_BarPanel;
    private javax.swing.JLabel lb_IcoLabel;
    private javax.swing.JLabel lb_TipLabel;
}
