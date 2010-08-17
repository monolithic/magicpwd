/**
 * 
 */
package com.magicpwd._mail;

import com.magicpwd._util.Logs;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

/**
 * @author Amon
 * 
 */
public class Mailer
{

    private String contentType = MailEnv.TEXT_HTML + ';' + MailEnv.CHARSET + "=UTF-8";
    private String from;
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private Date sentDate;
    private boolean needReply;
    private boolean unReaded;
    /**
     * 附件下载后的存放目录
     */
    private String saveAttachPath = "";
    /**
     * 存放邮件内容
     */
    private StringBuffer content = new StringBuffer();

    public Mailer()
    {
    }

    public boolean loadMsg(Message message)
    {
        try
        {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            InternetAddress ia = (InternetAddress)message.getFrom()[0];
            System.out.println(ia.getPersonal());
            System.out.println(ia.getType());
            System.out.println(ia.toString());
            from = getAddress(message.getFrom());
            to = getAddress(message.getRecipients(Message.RecipientType.TO));
            cc = getAddress(message.getRecipients(Message.RecipientType.CC));
            bcc = getAddress(message.getRecipients(Message.RecipientType.BCC));
            subject = decodeText(message.getSubject());
            sentDate = message.getSentDate();
            needReply = message.getHeader("Disposition-Notification-To") != null;
            getMailContent(message);
            for (Flags.Flag flag : message.getFlags().getSystemFlags())
            {
                if (flag == Flags.Flag.SEEN)
                {
                    unReaded = true;
                    break;
                }
            }
            message.setFlag(Flags.Flag.SEEN, true);
            return true;
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
            return false;
        }
    }

    public String getContentType()
    {
        return contentType;
    }

    /**
     * 获得发件人的地址和姓名
     */
    public String getFrom()
    {
        return from;
    }

    /**
     * 获取收件人的地址和姓名
     * @return
     */
    public String getTo()
    {
        return to;
    }

    /**
     * 获取抄送人的地址和姓名
     * @return
     */
    public String getCc()
    {
        return cc;
    }

    /**
     * 获取密抄人的地址和姓名
     * @return
     */
    public String getBcc()
    {
        return bcc;
    }

    private String getAddress(Address[] addresses) throws UnsupportedEncodingException
    {
        if (addresses == null || addresses.length < 1)
        {
            return "";
        }
        StringBuilder buffer = new StringBuilder();
        for (Address addr : addresses)
        {
            if (addr instanceof InternetAddress)
            {
                InternetAddress temp = (InternetAddress) addr;
                String personal = temp.getPersonal();
                String address = temp.getAddress();
                buffer.append(personal != null ? MimeUtility.decodeText(personal) : "");
                buffer.append('<').append(address != null ? MimeUtility.decodeText(address) : "").append(">,");
                continue;
            }
            buffer.append(addr.toString()).append(',');
        }
        return buffer.toString();
    }

    /**
     * 获得邮件主题
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * 获得邮件发送日期
     */
    public Date getSentDate()
    {
        return sentDate;
    }

    /**
     * 获得邮件正文内容
     */
    public String getBodyText()
    {
        return content.toString();
    }

    /**
     * 解析邮件，把得到的邮件内容保存到一个StringBuffer对象中，解析邮件 主要是根据MimeType类型的不同执行不同的操作，一步一步的解析
     */
    public void getMailContent(Part part) throws Exception
    {
        String sType = part.getContentType();
        if (!com.magicpwd._util.Char.isValidate(sType))
        {
            return;
        }
        ContentType cType = new ContentType(sType);
        sType = cType.getParameter(MailEnv.CHARSET);
        if (!com.magicpwd._util.Char.isValidate(sType))
        {
            String[] sTemp = part.getHeader("from");
            if (sTemp != null && sTemp.length > 0)
            {
                sType = sTemp[0];
            }
            if (com.magicpwd._util.Char.isValidate(sType))
            {
                Matcher matcher = Pattern.compile("=\\?\\w+\\?", Pattern.CASE_INSENSITIVE).matcher(sType);
                if (matcher.find())
                {
                    sType = matcher.group();
                    sType = sType.substring(2, sType.length() - 1);
                }
                else
                {
                    sType = "UTF-8";
                }
            }
            else
            {
                sType = "UTF-8";
            }
        }

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
     * 判断此邮件是否需要回执，如果需要回执返回"true",否则返回"false"
     */
    public boolean getReplySign() throws MessagingException
    {
        return needReply;
    }

    /**
     * 获得此邮件的Message-ID
     */
    public String getMessageId()
    {
        return "";//mimeMessage.getMessageID();
    }

    /**
     * 【判断此邮件是否已读，如果未读返回返回 false,反之返回true】
     */
    public boolean isNew()
    {
        return unReaded;
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
                if ((disposition != null) && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE))))
                {
                    fileName = mpart.getFileName();
                    if (fileName.toLowerCase().indexOf("gb2312") != -1)
                    {
                        fileName = MimeUtility.decodeText(fileName);
                    }
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
     * 【真正的保存附件到指定目录里】
     */
    private void saveFile(String fileName, InputStream in) throws Exception
    {
        String osName = System.getProperty("os.name");
        String storedir = getAttachPath();
        String separator = "";
        if (osName == null)
        {
            osName = "";
        }
        if (osName.toLowerCase().indexOf("win") != -1)
        {
            separator = "\\";
            if (storedir == null || storedir.equals(""))
            {
                storedir = "c:\\tmp";
            }
        }
        else
        {
            separator = "/";
            storedir = "/tmp";
        }
        File storefile = new File(storedir + separator + fileName);
        // for(int i=0;storefile.exists();i++){
        // storefile = new File(storedir+separator+fileName+i);
        // }
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try
        {
            bos = new BufferedOutputStream(new FileOutputStream(storefile));
            bis = new BufferedInputStream(in);
            int c;
            while ((c = bis.read()) != -1)
            {
                bos.write(c);
                bos.flush();
            }
        }
        catch (Exception exception)
        {
            Logs.exception(exception);
            throw new Exception("文件保存失败!");
        }
        finally
        {
            bos.close();
            bis.close();
        }
    }

    private static String decodeText(String text) throws UnsupportedEncodingException
    {
        System.out.println(text);
        if (text == null)
        {
            return null;
        }
        if (text.toLowerCase().startsWith("=?gb"))
        {
            return MimeUtility.decodeText(text);
        }
        return text;//new String(text.getBytes("ISO8859_1"));
    }
}
