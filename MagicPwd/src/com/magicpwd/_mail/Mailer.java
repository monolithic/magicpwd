/**
 * 
 */
package com.magicpwd._mail;

import com.magicpwd._comn.S1S1;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Jzip;
import com.magicpwd._util.Lang;
import java.io.UnsupportedEncodingException;

import javax.mail.Address;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

/**
 * @author Amon
 * 
 */
public class Mailer
{

    private Connect connect;
    private String from;//发送者
    private String to;//接收者
    private String cc;//明抄
    private String bcc;//暗抄
    private String subject;//标题
    private java.util.Date sentDate;//日期
    private StringBuffer content = new StringBuffer();//内容
    private String contentType = MailEnv.TEXT_HTML + ';' + MailEnv.CHARSET + "=UTF-8";
    private java.util.ArrayList<S1S1> attachmentList = new java.util.ArrayList<S1S1>();//附件

    public Mailer(Connect connect)
    {
        this.connect = connect;
    }

    public Message find(Folder folder, String from, String subject, String sentDate, String header) throws Exception
    {
        Message[] messages = folder.getMessages();
        if (messages != null)
        {
            for (Message message : messages)
            {
                if (subject == null || !subject.equals(decodeMessage(message.getSubject())))
                {
                    continue;
                }
                if (sentDate == null || !sentDate.equals(Long.toHexString(message.getSentDate().getTime())))
                {
                    continue;
                }
                if (header == null || message.getHeader("magicpwd-sign") == null || header.equals(message.getHeader("magicpwd-sign")[0]))
                {
                    continue;
                }
                return message;
            }
        }
        return null;
    }

    public boolean resume() throws Exception
    {
        // 删除已有备份邮件
        Store store = connect.getStore();
        Folder folder = store.getDefaultFolder();
        folder.open(Folder.READ_ONLY);
        Message message = find(folder, null, Lang.getLang(LangRes.P30F7A48, "魔方密码备份文件！"), null, null);
        if (message != null)
        {
            message.setFlag(Flag.DELETED, true);
        }
        folder.close(true);

        Reader reader = new Reader(connect);
        //reader.setAttachmentPath(ConsEnv.DIR_BAK);
        if (reader.read(message))
        {
            for (S1S1 item : reader.getAttachmentList())
            {
                if (ConsEnv.FILE_SYNC.equals(item.getK()))
                {
                    Jzip.unZip(item.getV(), ConsEnv.DIR_DAT);
                    //Lang.showMesg(TrayPtn.getCurrForm(), LangRes.P30F7A3F, "数据恢复成功，您需要重新启动本程序！");
                    return true;
                }
            }
        }
        return false;
    }

    protected String decodeAddress(Address[] addresses) throws UnsupportedEncodingException
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

    protected static String decodeMessage(String text) throws Exception
    {
        if (text == null)
        {
            return null;
        }
        if (text.startsWith("=?") && text.endsWith("?="))
        {
            //return MimeUtility.decodeWord(text);
            return MimeUtility.decodeText(text);
        }

        return text;//new String(text.getBytes("ISO8859_1"));
    }

    /**
     * @return the connect
     */
    public Connect getConnect()
    {
        return connect;
    }

    /**
     * @param connect the connect to set
     */
    public void setConnect(Connect connect)
    {
        this.connect = connect;
    }

    /**
     * 获得发件人的地址和姓名
     */
    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    /**
     * 获取收件人的地址和姓名
     * @return
     */
    public String getTo()
    {
        return to;
    }

    public void setTo(String to)
    {
        this.to = to;
    }

    /**
     * 获取抄送人的地址和姓名
     * @return
     */
    public String getCc()
    {
        return cc;
    }

    public void setCc(String cc)
    {
        this.cc = cc;
    }

    /**
     * 获取密抄人的地址和姓名
     * @return
     */
    public String getBcc()
    {
        return bcc;
    }

    public void setBcc(String bcc)
    {
        this.bcc = bcc;
    }

    /**
     * 获得邮件发送日期
     */
    public java.util.Date getSentDate()
    {
        return sentDate;
    }

    public void setSentDate(java.util.Date sentDate)
    {
        this.sentDate = sentDate;
    }

    /**
     * 获得邮件主题
     */
    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    /**
     * @return the content
     */
    public String getContent()
    {
        return content.toString();
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content)
    {
        this.content.delete(0, this.content.length()).append(content);
    }

    public void appendContent(String content)
    {
        this.content.append(content);
    }

    /**
     * @return the attachmentList
     */
    public java.util.ArrayList<S1S1> getAttachmentList()
    {
        return attachmentList;
    }

    public void addAttachment(String name, String file)
    {
        attachmentList.add(new S1S1(name, file));
    }

    public boolean hasAttachment()
    {
        return attachmentList != null && attachmentList.size() > 0;
    }

    public String getContentType()
    {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }
}
