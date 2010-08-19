/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._mail;

import com.magicpwd._cons.ConsEnv;
import java.util.Date;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
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
    private Multipart multipart;

    public Sender()
    {
    }

    public boolean send() throws Exception
    {
        if (!com.magicpwd._util.Char.isValidate(getFrom()) || !com.magicpwd._util.Char.isValidate(getTo()))
        {
            return false;
        }

        message.setFrom(new InternetAddress(getFrom()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(getTo()));
        if (com.magicpwd._util.Char.isValidate(getCc()))
        {
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(getCc()));
        }
        if (com.magicpwd._util.Char.isValidate(getBcc()))
        {
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(getBcc()));
        }
        if (getSentDate() != null)
        {
            message.setSentDate(getSentDate());
        }
        message.setContent(multipart);
        message.saveChanges();

        Transport transport = getConnect().getSession().getTransport();
        //transport.connect("", "", "");

        //transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        Transport.send(message);
        //System.out.println("发送邮件成功！");
        transport.close();
        return true;
    }

    public boolean appendBodypart(String content) throws Exception
    {
        BodyPart bp = new MimeBodyPart();
        bp.setContent(content, "text/plain;charset=" + ConsEnv.FILE_ENCODING);
        multipart.addBodyPart(bp);
        return true;
    }

    public boolean appendAttachment(String filePath) throws Exception
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
