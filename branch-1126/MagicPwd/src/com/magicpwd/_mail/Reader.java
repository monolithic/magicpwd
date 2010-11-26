/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._mail;

import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Char;
import com.magicpwd._util.Logs;
import com.magicpwd.m.UserMdl;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Amon
 */
public class Reader extends Mailer
{

    private String messageId;// 消息索引
    private boolean needReply;//是否需要回复
    private String attachmentPath;//附件下载目录

    public Reader(UserMdl userMdl, Connect connect)
    {
        super(userMdl, connect);
        attachmentPath = ConsEnv.DIR_MAIL;
    }

    public boolean read(Message message) throws Exception
    {
        if (message == null)
        {
            return false;
        }

        String[] headers = message.getHeader("charset");
        if (headers != null && headers.length > 0)
        {
            setCharset(headers[0]);
        }

        String contentType;
        headers = message.getHeader("content-type");
        if (headers != null && headers.length > 0)
        {
            contentType = headers[0];
            java.util.regex.Matcher m = java.util.regex.Pattern.compile("charset\\s*=[\\s'\"]?[\\w-]+[\\s'\"]?").matcher(contentType);
            if (m.find())
            {
                setCharset(m.group().replaceAll("charset\\s*=[\\s'\"]?", "").replaceAll("[\\s'\"]?", ""));
            }
        }
        setFrom(decodeAddress(message.getFrom()));
        try
        {
            setTo(decodeAddress(message.getRecipients(Message.RecipientType.TO)));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        try
        {
            setCc(decodeAddress(message.getRecipients(Message.RecipientType.CC)));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        try
        {
            setBcc(decodeAddress(message.getRecipients(Message.RecipientType.BCC)));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
        }
        headers = message.getHeader("subject");
        if (headers != null && headers.length > 0)
        {
            setSubject(decodeMessage(headers[0]));
        }
        setSentDate(message.getSentDate());
        needReply = message.getHeader("Disposition-Notification-To") != null;
        Object obj = message.getContent();
        if (obj instanceof MimeMultipart)
        {
            Multipart part = (MimeMultipart) obj;
            for (int i = 0; i < part.getCount(); i++)
            {
                decodePart(part.getBodyPart(i));
            }
        }
        else if (obj instanceof String)
        {
            appendContent(decodeContext((String) obj));
        }

        if (message instanceof MimeMessage)
        {
            messageId = ((MimeMessage) message).getMessageID();
            if (!com.magicpwd._util.Char.isValidate(messageId))
            {
                messageId = Long.toHexString(com.magicpwd._util.Hash.ber(getFrom() + getSubject() + getSentDate()));
            }
            getConnect().appendMailInfo(messageId, getConnect().isMailExists(messageId));
        }

        return true;
    }

    /**
     * 解析邮件，把得到的邮件内容保存到一个StringBuffer对象中，解析邮件 主要是根据MimeType类型的不同执行不同的操作，一步一步的解析
     */
    private void decodePart(Part part) throws Exception
    {
        // 附件
        String disposition = part.getDisposition();
        if (disposition != null && (disposition.equalsIgnoreCase(Part.ATTACHMENT) || disposition.equalsIgnoreCase(Part.INLINE)))
        {
            saveAttach(decodeMessage(part.getFileName()), part.getInputStream());//保存附件
            return;
        }

        String sType = getContentType(part, "UTF-8");
        boolean conname = sType.indexOf("name") != -1;
        if (part.isMimeType(MailEnv.TEXT_PLAIN) && !conname)
        {
            setContentType(MailEnv.TEXT_PLAIN + ';' + MailEnv.CHARSET + '=' + sType);
            appendContent((String) part.getContent());
            return;
        }
        if (part.isMimeType(MailEnv.TEXT_HTML) && !conname)
        {
            setContentType(MailEnv.TEXT_HTML + ';' + MailEnv.CHARSET + '=' + sType);
            appendContent((String) part.getContent());
            return;
        }
        if (part.isMimeType(MailEnv.MULTIPART))
        {
            Multipart multipart = (Multipart) part.getContent();
            for (int i = 0, j = multipart.getCount(); i < j; i++)
            {
                decodePart(multipart.getBodyPart(i));
            }
            return;
        }
        if (part.isMimeType(MailEnv.MESSAGE))
        {
            decodePart((Part) part.getContent());
            return;
        }
        Object obj = part.getContent();
        if (obj instanceof String)
        {
            appendContent((String) obj);
        }
    }

    /**
     * 【真正的保存附件到指定目录里】
     */
    private void saveAttach(String fileName, java.io.InputStream inStream) throws Exception
    {
        if (!Char.isValidate(fileName))
        {
            return;
        }
        java.io.File file = new java.io.File(getAttachmentPath(), fileName);

        java.io.BufferedOutputStream bos = null;
        java.io.BufferedInputStream bis = null;
        try
        {
            bos = new java.io.BufferedOutputStream(new java.io.FileOutputStream(file));
            bis = new java.io.BufferedInputStream(inStream);
            byte[] b = new byte[2048];
            int c = bis.read(b);
            while (c >= 0)
            {
                bos.write(b, 0, c);
                c = bis.read(b);
            }
            bos.flush();

            addAttachment(fileName, file.getAbsolutePath());
        }
        finally
        {
            if (bis != null)
            {
                bis.close();
            }
            if (bos != null)
            {
                bos.close();
            }
        }
    }

    private static String getContentType(Part part, String type) throws Exception
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
                        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("=\\?\\w+\\?", java.util.regex.Pattern.CASE_INSENSITIVE).matcher(sType);
                        if (matcher.find())
                        {
                            sType = matcher.group();
                            sType = sType.substring(2, sType.length() - 1);
                        }
                    }
                }
            }
        }
        return sType != null ? sType : type;
    }

    public String getMessageId()
    {
        return messageId;
    }

    /**
     * 【获得附件存放路径】
     */
    public String getAttachmentPath()
    {
        return attachmentPath != null ? attachmentPath : System.getProperty("java.io.tmpdir");
    }

    /**
     * 【设置附件存放路径】
     */
    public void setAttachmentPath(String attachmentPath)
    {
        this.attachmentPath = attachmentPath;
    }

    /**
     * 判断此邮件是否需要回执，如果需要回执返回"true",否则返回"false"
     */
    public boolean needReply()
    {
        return needReply;
    }

    public boolean isReaded()
    {
        return getConnect().isMailReaded(messageId);
    }
}
