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
import java.text.SimpleDateFormat;
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
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

/**
 * @author Amon
 * 
 */
public class MailInf
{

    private String contentType;
    private Address[] address;
    private Address[] to;
    private Address[] cc;
    private Address[] bcc;
    private String subject;
    private Date sendDate;
    private boolean needReply;
    private boolean unReaded;
    /**
     * 附件下载后的存放目录
     */
    private String saveAttachPath = "";
    /**
     * 存放邮件内容
     */
    private StringBuffer bodytext = new StringBuffer();
    private static Pattern typeSet;
    private static Pattern charSet;

    public MailInf()
    {
    }

    public boolean loadMsg(Message message)
    {
        try
        {
            typeSet = Pattern.compile("text\\s*/\\s*\\w*?[^;\\s];");
            charSet = Pattern.compile("charset\\s*=\\s*\"?[\\w\\d-]+\"?[^;\\s];");
            String temp = message.getContentType();
            Matcher m = typeSet.matcher(temp);
            String t1 = "text/html;";
            if (m.find())
            {
                t1 = m.group();
            }
            m = charSet.matcher(temp);
            String t2 = "charset=UTF-8;";
            if (m.find())
            {
                t2 = m.group();
            }
            contentType = t1 + ' ' + t2;

            address = message.getFrom();
            to = message.getRecipients(Message.RecipientType.TO);
            cc = message.getRecipients(Message.RecipientType.CC);
            bcc = message.getRecipients(Message.RecipientType.BCC);
            subject = message.getSubject();
            sendDate = message.getSentDate();
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
    public String getFrom() throws Exception
    {
        if (address != null && address.length > 0)
        {
            Address addr = address[0];
            if (addr instanceof InternetAddress)
            {
                InternetAddress temp = (InternetAddress) addr;
                return temp.getPersonal() + "<" + temp.getAddress() + ">";
            }
        }
        return "";
    }

    /**
     * 获得邮件的收件人，抄送，和密送的地址和姓名，根据所传递的参数的不同 "to"----收件人 "cc"---抄送人地址 "bcc"---密送人地址
     */
    public String getMailAddress(String type) throws Exception
    {
        String mailaddr = "";
        String addtype = type.toUpperCase();
        InternetAddress[] address = null;
        if (addtype.equals("TO") || addtype.equals("CC") || addtype.equals("BCC"))
        {
            if (addtype.equals("TO"))
            {
                address = (InternetAddress[]) to;
            }
            else if (addtype.equals("CC"))
            {
                address = (InternetAddress[]) cc;
            }
            else
            {
                address = (InternetAddress[]) bcc;
            }
            if (address != null)
            {
                for (int i = 0; i < address.length; i++)
                {
                    String email = address[i].getAddress();
                    if (email == null)
                    {
                        email = "";
                    }
                    else
                    {
                        email = MimeUtility.decodeText(email);
                    }
                    String personal = address[i].getPersonal();
                    if (personal == null)
                    {
                        personal = "";
                    }
                    else
                    {
                        personal = MimeUtility.decodeText(personal);
                    }
                    String compositeto = personal + "<" + email + ">";
                    mailaddr += "," + compositeto;
                }
                mailaddr = mailaddr.substring(1);
            }
        }
        else
        {
            throw new Exception("Error emailaddr type!");
        }
        return mailaddr;
    }

    /**
     * 获得邮件主题
     */
    public String getSubject() throws MessagingException
    {
        try
        {
            return MimeUtility.decodeText(subject);
        }
        catch (Exception exce)
        {
            Logs.exception(exce);
            return null;
        }
    }

    /**
     * 获得邮件发送日期
     */
    public String getSentDate() throws Exception
    {
        return SimpleDateFormat.getDateTimeInstance().format(sendDate);
    }

    /**
     * 获得邮件正文内容
     */
    public String getBodyText()
    {
        return bodytext.toString();
    }

    /**
     * 解析邮件，把得到的邮件内容保存到一个StringBuffer对象中，解析邮件 主要是根据MimeType类型的不同执行不同的操作，一步一步的解析
     */
    public void getMailContent(Part part) throws Exception
    {
        String contenttype = part.getContentType();
        int nameindex = contenttype.indexOf("name");
        boolean conname = false;
        if (nameindex != -1)
        {
            conname = true;
        }
        if (part.isMimeType("text/plain") && !conname)
        {
            bodytext.append((String) part.getContent());
        }
        else if (part.isMimeType("text/html") && !conname)
        {
            bodytext.append((String) part.getContent());
        }
        else if (part.isMimeType("multipart/*"))
        {
            Multipart multipart = (Multipart) part.getContent();
            int counts = multipart.getCount();
            for (int i = 0; i < counts; i++)
            {
                getMailContent(multipart.getBodyPart(i));
            }
        }
        else if (part.isMimeType("message/rfc822"))
        {
            getMailContent((Part) part.getContent());
        }
        else
        {
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
    public String getMessageId() throws MessagingException
    {
        return "";//mimeMessage.getMessageID();
    }

    /**
     * 【判断此邮件是否已读，如果未读返回返回 false,反之返回true】
     */
    public boolean isNew() throws MessagingException
    {
        return unReaded;
    }

    /**
     * 判断此邮件是否包含附件
     */
    public boolean isContainAttach(Part part) throws Exception
    {
        boolean attachflag = false;
        String contentType = part.getContentType();
        if (part.isMimeType("multipart/*"))
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
                else if (mpart.isMimeType("multipart/*"))
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
        else if (part.isMimeType("message/rfc822"))
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
        if (part.isMimeType("multipart/*"))
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
                else if (mpart.isMimeType("multipart/*"))
                {
                    saveAttachMent(mpart);
                }
                else
                {
                    fileName = mpart.getFileName();
                    if ((fileName != null) && (fileName.toLowerCase().indexOf("GB2312") != -1))
                    {
                        fileName = MimeUtility.decodeText(fileName);
                        saveFile(fileName, mpart.getInputStream());
                    }
                }
            }
        }
        else if (part.isMimeType("message/rfc822"))
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
            exception.printStackTrace();
            throw new Exception("文件保存失败!");
        }
        finally
        {
            bos.close();
            bis.close();
        }
    }
}
