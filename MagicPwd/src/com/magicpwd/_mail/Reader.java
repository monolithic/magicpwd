/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._mail;

import com.magicpwd._comn.S1S1;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 *
 * @author Amon
 */
public class Reader extends Mailer
{

    protected MimeMessage message;
    private String from;
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private Date sentDate;
    private boolean needReply;
    /**
     * 附件下载后的存放目录
     */
    private String saveAttachPath = "";
    private String contentType = MailEnv.TEXT_HTML + ';' + MailEnv.CHARSET + "=UTF-8";
    /**
     * 存放邮件内容
     */
    private StringBuffer content = new StringBuffer();
    private List<S1S1> attachmentList = new ArrayList<S1S1>();

    public Reader()
    {
    }

    public boolean initData() throws Exception
    {
        setFrom(decodeAddress(message.getFrom()));
        setTo(decodeAddress(message.getRecipients(Message.RecipientType.TO)));
        setCc(decodeAddress(message.getRecipients(Message.RecipientType.CC)));
        setBcc(decodeAddress(message.getRecipients(Message.RecipientType.BCC)));
        setSubject(decodeMessage(message.getSubject()));
        sentDate = message.getSentDate();
        needReply = message.getHeader("Disposition-Notification-To") != null;
        Object obj = message.getContent();
        if (obj instanceof Multipart)
        {
            Multipart mPart = (MimeMultipart) obj;
            for (int i = 0; i < mPart.getCount(); i++)
            {
                extractPart((MimeBodyPart) mPart.getBodyPart(i));
            }
        }
        return true;
    }

    private String getContentType(Part part, String defType) throws Exception
    {
        String sType = part.getContentType();
        if (com.magicpwd._util.Char.isValidate(sType))
        {
            ContentType cType = new ContentType(sType);
            sType = cType.getParameter(MailEnv.CHARSET);
            if (!com.magicpwd._util.Char.isValidate(sType))
            {
                String[] sTemp = part.getHeader("from");
                if (sTemp != null && sTemp.length > 0)
                {
                    sType = sTemp[0];
                    if (com.magicpwd._util.Char.isValidate(sType))
                    {
                        Matcher matcher = Pattern.compile("=\\?\\w+\\?", Pattern.CASE_INSENSITIVE).matcher(sType);
                        if (matcher.find())
                        {
                            sType = matcher.group();
                            sType = sType.substring(2, sType.length() - 1);
                        }
                    }
                }
            }
        }
        return sType != null ? sType : defType;
    }

    /**
     * 解析邮件，把得到的邮件内容保存到一个StringBuffer对象中，解析邮件 主要是根据MimeType类型的不同执行不同的操作，一步一步的解析
     */
    public void getMailContent(Part part) throws Exception
    {
        String sType = getContentType(part, "UTF-8");
        boolean conname = sType.indexOf("name") != -1;
        if (part.isMimeType(MailEnv.TEXT_PLAIN) && !conname)
        {
            contentType = MailEnv.TEXT_PLAIN + ';' + MailEnv.CHARSET + '=' + sType;

            content.append((String) part.getContent());
            return;
        }
        if (part.isMimeType(MailEnv.TEXT_HTML) && !conname)
        {
            contentType = MailEnv.TEXT_HTML + ';' + MailEnv.CHARSET + '=' + sType;
            content.append((String) part.getContent());
            return;
        }
        if (part.isMimeType(MailEnv.MULTIPART))
        {
            Multipart multipart = (Multipart) part.getContent();
            int counts = multipart.getCount();
            for (int i = 0; i < counts; i++)
            {
                getMailContent(multipart.getBodyPart(i));
            }
            return;
        }
        if (part.isMimeType(MailEnv.MESSAGE))
        {
            getMailContent((Part) part.getContent());
            return;
        }
        Object obj = part.getContent();
        if (obj instanceof String)
        {
            content.append((String) obj);
        }
    }

    /**
     * 【保存附件】
     */
    public void saveAttachMent(Part part) throws Exception
    {
        String fileName = "";
        if (part.isMimeType(MailEnv.MULTIPART))
        {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++)
            {
                BodyPart mpart = mp.getBodyPart(i);
                String disposition = mpart.getDisposition();
                if (disposition != null && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE))))
                {
                    fileName = decodeMessage(mpart.getFileName());
                    saveFile(fileName, mpart.getInputStream());
                }
                else if (mpart.isMimeType(MailEnv.MULTIPART))
                {
                    saveAttachMent(mpart);
                }
                else
                {
                    fileName = mpart.getFileName();
                    if ((fileName != null) && (fileName.toLowerCase().indexOf("gb2312") != -1))
                    {
                        fileName = MimeUtility.decodeText(fileName);
                        saveFile(fileName, mpart.getInputStream());
                    }
                }
            }
        }
        else if (part.isMimeType(MailEnv.MESSAGE))
        {
            saveAttachMent((Part) part.getContent());
        }
    }

    /**
     * 抽取内容
     *
     * @param part
     */
    public void extractPart(MimeBodyPart part)
    {
        try
        {
            String disposition = part.getDisposition();

            // 附件
            if (disposition != null && (disposition.equalsIgnoreCase(Part.ATTACHMENT) || disposition.equalsIgnoreCase(Part.INLINE)))
            {
                String fileName = decodeMessage(part.getFileName());
                System.out.println(fileName);
                saveFile(fileName, part.getInputStream());//保存附件
            }
            // 正文
            else
            {
                //接收到的纯文本
                if (part.getContent() instanceof String)
                {
                    System.out.println(part.getContent());
                }
                //接收的邮件有附件时
                if (part.getContent() instanceof MimeMultipart)
                {
                    BodyPart bodyPart = ((MimeMultipart) part.getContent()).getBodyPart(0);
                    System.out.println(bodyPart.getContent());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 判断此邮件是否包含附件
     */
    public boolean isContainAttach(Part part) throws Exception
    {
        boolean attachflag = false;
        if (part.isMimeType(MailEnv.MULTIPART))
        {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++)
            {
                BodyPart mpart = mp.getBodyPart(i);
                String disposition = mpart.getDisposition();
                if ((disposition != null) && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE))))
                {
                    attachflag = true;
                }
                else if (mpart.isMimeType(MailEnv.MULTIPART))
                {
                    attachflag = isContainAttach((Part) mpart);
                }
                else
                {
                    String contype = mpart.getContentType();
                    if (contype.toLowerCase().indexOf("application") != -1)
                    {
                        attachflag = true;
                    }
                    if (contype.toLowerCase().indexOf("name") != -1)
                    {
                        attachflag = true;
                    }
                }
            }
        }
        else if (part.isMimeType(MailEnv.MESSAGE))
        {
            attachflag = isContainAttach((Part) part.getContent());
        }
        return attachflag;
    }

    /**
     * 【真正的保存附件到指定目录里】
     */
    private void saveFile(String fileName, InputStream in) throws Exception
    {
        String storedir = getAttachPath();
        File storefile = new File(storedir, fileName);

        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try
        {
            bos = new BufferedOutputStream(new FileOutputStream(storefile));
            bis = new BufferedInputStream(in);
            int l;
            byte[] b = new byte[2048];
            l = bis.read(b);
            while (l >= 0)
            {
                bos.write(b, 0, l);
                l = bis.read(b);
            }
            bos.flush();
        }
        finally
        {
            bis.close();
            bos.close();
        }
    }

    /**
     * 获得邮件正文内容
     */
    public String getContent()
    {
        return content.toString();
    }

    /**
     * 判断此邮件是否需要回执，如果需要回执返回"true",否则返回"false"
     */
    public boolean isNeedReply() throws MessagingException
    {
        return needReply;
    }

    /**
     * 【设置附件存放路径】
     */
    public void setAttachPath(String attachpath)
    {
        this.saveAttachPath = attachpath;
    }

    /**
     * 【获得附件存放路径】
     */
    public String getAttachPath()
    {
        return saveAttachPath;
    }

    /**
     * @return the from
     */
    @Override
    public String getFrom()
    {
        return from;
    }

    /**
     * @param from the from to set
     */
    @Override
    public boolean setFrom(String from)
    {
        return true;
    }

    /**
     * @return the to
     */
    @Override
    public String getTo()
    {
        return to;
    }

    /**
     * @param to the to to set
     */
    @Override
    public boolean setTo(String to)
    {
        return true;
    }

    /**
     * @return the cc
     */
    @Override
    public String getCc()
    {
        return cc;
    }

    /**
     * @param cc the cc to set
     */
    public boolean setCc(String cc)
    {
        return true;
    }

    /**
     * @return the bcc
     */
    @Override
    public String getBcc()
    {
        return bcc;
    }

    /**
     * @param bcc the bcc to set
     */
    public boolean setBcc(String bcc)
    {
        return true;
    }

    /**
     * @return the subject
     */
    @Override
    public String getSubject()
    {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    @Override
    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    @Override
    public boolean addCc(String cc)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addBcc(String bcc)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Date getSentDate()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getMessageId()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
