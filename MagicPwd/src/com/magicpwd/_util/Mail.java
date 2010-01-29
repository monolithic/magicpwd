/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Amon
 */
public class Mail
{
    /**
     * 是否采用调试方式
     */
    private boolean isDebug;
    /**
     * 邮件传输协议
     */
    private String protocol;
    /**
     * 邮件服务器地址
     */
    private String mailHost;
    /**
     * 邮件服务器端口
     */
    private int mailPort;
    /**
     * 登录用户
     */
    private String userName;
    /**
     * 登录口令
     */
    private String password;
    /**
     * Mail发送的起始地址
     */
    private String mailFrom;
    /**
     * 抄送地址
     */
    private List<String> mailCC;
    /**
     * 密抄地址
     */
    private List<String> mailBCC;
    /**
     * Mail主题
     */
    private String subject;
    /**
     * Mail内容
     */
    private String content;
    /**
     * 接收地址
     */
    private List<String> mailTO = new ArrayList<String>();
    /**
     * 邮件附件
     */
    private List<String> attachment = new ArrayList<String>();
    /**
     * 邮件内容格式
     */
    private String contentType = "text/html; charset=utf-8";
    /**
     * 链接配置信息
     */
    private Properties propKey = System.getProperties();
    private Session session;
    private Store store;

    private Mail()
    {
    }

    public static Mail getInstance()
    {
        return new Mail();
    }

    /**
     * 取得邮件服务器连接
     */
    public boolean connect()
    {
        // 登录用户身份认证
        Authenticator auth = new Authenticator()
        {
            @Override
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(userName, password);
            }
        };

        // 获得服务器连接
        session = Session.getDefaultInstance(propKey, auth);
        session.setDebug(isDebug);

        return true;
    }

    public boolean openStore()
    {
        try
        {
            store = session.getStore(protocol);
            if (mailPort > 0)
            {
                store.connect(mailHost, userName, password);
            }
            else
            {
                store.connect(mailHost, userName, password);
            }
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
    }

    public boolean exitStore()
    {
        try
        {
            // 断开用户登录
            store.close();
            return true;
        }
        catch (MessagingException exp)
        {
            Logs.exception(exp);
            return false;
        }
    }

    /**
     * 断开邮件服务器连接
     */
    public boolean dispose()
    {
        return true;
    }

    /**
     * 发送电子邮件
     * @return 是否发送成功
     * @throws java.io.IOException
     * @throws javax.mail.MessagingException
     */
    public boolean sendMail() throws IOException, MessagingException
    {
        // 连接服务器
        if (session == null)
        {
            connect();
        }

        // 邮件封装
        MimeMessage msg = new MimeMessage(session);

        // 邮件发送人
        if (!Util.isValidate(mailFrom))
        {
            return false;
        }
        msg.setFrom(new InternetAddress(mailFrom));

        // 接收
        if (mailTO.size() < 1)
        {
            return false;
        }
        for (String to : mailTO)
        {
            msg.addRecipients(Message.RecipientType.TO, to);
        }

        // 抄送
        if (mailCC != null)
        {
            for (String cc : mailCC)
            {
                msg.addRecipients(Message.RecipientType.CC, cc);
            }
        }

        // 密抄
        if (mailBCC != null)
        {
            for (String bcc : mailBCC)
            {
                msg.addRecipients(Message.RecipientType.BCC, bcc);
            }
        }

        // 设置邮件标题
        msg.setSubject(subject);

        // 设置回复地址
//        InternetAddress[] replyAddress =
//        {
//            new InternetAddress(mailFrom)
//        };
//        msg.setReplyTo(replyAddress);

        // 邮件体
        Multipart body = new MimeMultipart();

        // 邮件内容
        BodyPart part = new MimeBodyPart();
        if (this.content != null)
        {
            part.setText(this.content);
        }
        body.addBodyPart(part);

        // 邮件附件
        if (attachment != null)
        {
            for (String fileName : attachment)
            {
                part = new MimeBodyPart();
                FileDataSource fds = new FileDataSource(fileName);
                part.setDataHandler(new DataHandler(fds));
                part.setFileName(fileName);
                //part.setDisposition("");
                //注意：下面定义的enc对象用来处理中文附件名，否则名称是中文的附件在邮箱里面显示的会是乱码，
                //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
                //content.setFileName("=?GBK?B?" + enc.encode(fileName.getBytes()) + "?=");
                body.addBodyPart(part);
            }
        }
        msg.setContent(body);
        msg.setSentDate(new Date());

        // 邮件发送
        try
        {
            Transport.send(msg);
        }
        catch (MessagingException e)
        {
            Logs.exception(e);
            return false;
        }

        return true;
    }

    /**
     * 读取指定发件人的指定标题的邮件，并将其附件存储到指定的目录
     * @param  mailFrom 邮件发件人
     * @param subject 要获取的邮件的标题
     * @param fileDir 指定附件的保存路径
     * @return
     */
    public Message[] loadMail(String mailFrom, String subject, String fileDir)
    {
        Message[] msgs = null;
        try
        {
            // 定位收件箱
            Folder inbox = store.getDefaultFolder().getFolder("INBOX");
            if (inbox == null)
            {
                return msgs;
            }
            inbox.open(Folder.READ_WRITE);//设置inbox对象属性为可读写，这样可以控制在读完邮件后直接删除该附件

            // 不清楚干什么的？
            FetchProfile profile = new FetchProfile();
            profile.add(FetchProfile.Item.ENVELOPE);

            // 取得所有的邮件列表
            msgs = inbox.getMessages();
            inbox.fetch(msgs, profile);

            // 读取指定标题的邮件
            if (Util.isValidate(mailFrom) && Util.isValidate(subject))
            {
                for (int i = 0; i < msgs.length; i++)
                {
                    //标记此邮件的flag标志对象的DELETED位为true,可以在读完邮件后直接删除该附件，具体执行时间是在调用
                    //inbox.close()方法的时候
                    //msg[i].setFlag(Flags.Flag.DELETED, true);
                    if (subject.equals(msgs[i].getSubject()))
                    {
                        saveAttachment(msgs[i], fileDir);
                    }
                }
            }
            // 关闭收件箱连接
            inbox.close(false);//参数为true表明阅读完此邮件后将其删除，更多的属性请参考mail.jar的API
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        return msgs;
    }

    /**
     * 保存附件数据
     * @param part
     * @throws java.lang.Exception
     */
    private void saveAttach(BodyPart part, String path) throws Exception
    {
        // 以下是中文邮件名称的处理
        //得到未经处理的附件名字
        //String temp = part.getFileName();
        //除去发送邮件时，对中文附件名编码的头和尾，得到正确的附件名
        //（请参考发送邮件程序SendMail的附件名编码部分）
        //String s = temp.substring(8, temp.indexOf("?="));
        //文件名经过了base64编码,下面是解码
        //String fileName = base64Decoder(s);

        String fileName = part.getFileName();
        if (!Util.isValidate(fileName))
        {
            return;
        }
        if (fileName.indexOf(File.separator) >= 0)
        {
            int i = fileName.lastIndexOf(File.separator) + 1;
            if (i == fileName.length())
            {
                return;
            }
            fileName = fileName.substring(i + 1);
        }
        InputStream in = part.getInputStream();
        FileOutputStream out = new FileOutputStream(new File(path + fileName));

        // 数据流保存
        byte[] b = new byte[1024];
        int i = in.read(b);
        while (i >= 0)
        {
            out.write(b);
        }
        out.close();
        in.close();
    }

    /**
     * 处理Multipart邮件，包括了保存附件的功能
     * @param msg
     * @throws java.lang.Exception
     */
    public void saveAttachment(Message msg, String dir) throws Exception
    {
        Object obj = msg.getContent();
        if (!(obj instanceof MimeMultipart))
        {
            return;
        }

        MimeMultipart mp = (MimeMultipart) obj;

        //循环处理每一个Part（一般即为多个附件）
        BodyPart part;
        for (int i = 0, j = mp.getCount(); i < j; i++)
        {
            part = mp.getBodyPart(i);
            //判断是否有附件
            if (Part.ATTACHMENT.equals(part.getDisposition()))
            {
                //这个方法负责保存附件
                saveAttach(part, dir);
            }
            else
            {
                //不是附件，就只显示文本内容
                System.out.println(part.getContent());
            }
        }
    }

    /**
     * 添加附件
     * @param filePath
     */
    public void addAttachmentFile(String filePath)
    {
        attachment.add(filePath);
    }

    public void setDebug(boolean debugFlag)
    {
        isDebug = debugFlag;
    }

    /**
     * 添加抄送地址
     * @param ccto
     */
    public void setMailccTo(String cc)
    {
        mailCC.add(cc);
    }

    /**
     * 设置邮件发送人
     * @param from
     */
    public void setMailFrom(String from)
    {
        if (Util.isValidateEmail(from))
        {
            mailFrom = from;
        }

    }

    /**
     * 添加接收人员
     * @param to
     */
    public void addMailTO(String to)
    {
        if (Util.isValidateEmail(to))
        {
            mailTO.add(to);
        }
    }

    /**
     * 添加抄送接收人员
     * @param cc
     */
    public void addMailCC(String cc)
    {
        if (Util.isValidateEmail(cc))
        {
            mailCC.add(cc);
        }

    }

    /**
     * 添加密抄接收人员
     * @param bcc
     */
    public void addMailBCC(String bcc)
    {
        if (Util.isValidateEmail(bcc))
        {
            mailBCC.add(bcc);
        }

    }

    /**
     * 设置邮件内容格式
     * @param mimeType
     */
    public void setContentType(String mimeType)
    {
        contentType = mimeType;
    }

    public void setMsgContent(String content)
    {
        this.content = content;
    }

    /**
     * 设置邮件发送服务器
     * @param host
     */
    public void setSMTPHost(String host)
    {
        protocol = "smtp";
        mailHost = host;
        propKey.put("mail.smtp.host", host);
    }

    public void setSMTPAuth(boolean auth)
    {
        propKey.put("mail.smtp.auth", auth ? "true" : "false");
    }

    /**
     * 设置邮件接收服务器
     * @param host
     */
    public void setPOP3Host(String host)
    {
        protocol = "pop3";
        mailHost = host;
        propKey.put("mail.pop3.host", host);
    }

    public void setIMAPHost(String host)
    {
        protocol = "imap";
        mailHost = host;
        propKey.put("mail.imap.host", host);
    }

    /**
     * 设置邮件主题
     * @param sub
     */
    public void setSubject(String sub)
    {
        subject = sub;
    }

    /**
     * 设置登录用户
     * @param name
     */
    public void setUserName(String name)
    {
        userName = name;
    }

    /**
     * 设置登录口令
     * @param pwd
     */
    public void setPassword(String pwd)
    {
        password = pwd;
    }

    /**
     * Gmail邮件服务器POP3默认配置
     */
    public void gmailPOP3Def()
    {
        protocol = "pop3";
        mailHost = "pop.gmail.com";
        mailPort = 995;

        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        propKey.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        propKey.setProperty("mail.pop3.socketFactory.fallback", "false");
        propKey.setProperty("mail.pop3.port", "995");
        propKey.setProperty("mail.pop3.socketFactory.port", "995");
    }

    /**
     * Gmail邮件服务器SMTP默认配置
     */
    public void gmailSMTPDef()
    {
    }

    /**
     * Gmail邮件服务器IMAP默认配置
     */
    public void gmailIMAPDef()
    {
        protocol = "pop3";
        mailHost = "imap.gmail.com";
        mailPort = 993;

        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        propKey.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        propKey.setProperty("mail.imap.socketFactory.fallback", "false");
        propKey.setProperty("mail.imap.port", "993");
        propKey.setProperty("mail.imap.socketFactory.port", "993");
    }
}
