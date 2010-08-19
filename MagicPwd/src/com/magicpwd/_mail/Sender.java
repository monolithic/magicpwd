/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._mail;

import com.magicpwd._util.Logs;
import java.util.Date;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 *
 * @author Amon
 */
public class Sender extends Mailer
{

    private MimeMessage message; // MIME邮件对象
    private InternetAddress from;
    private InternetAddress to;
    private List<InternetAddress> cc;
    private List<InternetAddress> bcc;
    private Multipart multipart;

    public Sender()
    {
    }

    public boolean appendBodypart(String content)
    {
        try
        {
            BodyPart bp = new MimeBodyPart();
            bp.setContent(content, "text/plain;charset=UTF-8");
            multipart.addBodyPart(bp);
            return true;
        }
        catch (Exception e)
        {
            System.err.println("设置邮件正文时发生错误！" + e);
            return false;
        }
    }

    private boolean appendAttachment(String filePath) throws Exception
    {
        return appendAttachment(new java.io.File(filePath));
    }

    private boolean appendAttachment(java.io.File file) throws Exception
    {
        if (file == null || !file.isFile() || !file.canRead())
        {
            return false;
        }
        BodyPart bp = new MimeBodyPart();
        FileDataSource fileds = new FileDataSource(file);
        bp.setDataHandler(new DataHandler(fileds));
        bp.setFileName(MimeUtility.encodeText(fileds.getName()));
        multipart.addBodyPart(bp);
        return true;
    }

    public boolean send()
    {
        try
        {
            message.setContent(multipart);
            message.saveChanges();
            System.out.println("正在发送邮件....");
            Session mailSession = null;//Session.getInstance("", "");
            Transport transport = mailSession.getTransport("smtp");
            transport.connect("", "", "");

            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            Transport.send(message);
            System.out.println("发送邮件成功！");
            transport.close();
            return true;
        }
        catch (Exception e)
        {
            System.err.println("邮件发送失败！" + e);
            return false;
        }
    }

    @Override
    public String getFrom()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean setFrom(String from)
    {
        if (!com.magicpwd._util.Char.isValidateEmail(from))
        {
            return false;
        }
        try
        {
            this.from = new InternetAddress(from);
            return true;
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
            return false;
        }
    }

    @Override
    public String getTo()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean setTo(String to)
    {
        if (!com.magicpwd._util.Char.isValidateEmail(to))
        {
            return false;
        }

        try
        {
            this.to = new InternetAddress(to);
            return true;
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
            return false;
        }
    }

    @Override
    public String getCc()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addCc(String cc)
    {
        if (!com.magicpwd._util.Char.isValidateEmail(cc))
        {
            return false;
        }

        try
        {
            this.cc.add(new InternetAddress(cc));
            return true;
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            return false;
        }
    }

    @Override
    public String getBcc()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addBcc(String bcc)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getSubject()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSubject(String subject)
    {
        try
        {
            message.setSubject(subject);
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
        }
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
