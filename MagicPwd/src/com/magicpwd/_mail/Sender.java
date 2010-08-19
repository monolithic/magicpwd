/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._mail;

import com.magicpwd._comn.S1S1;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 *
 * @author Amon
 */
public class Sender extends Mailer
{

    private Multipart multipart = new MimeMultipart();

    public Sender()
    {
    }

    public boolean send() throws Exception
    {
        if (!com.magicpwd._util.Char.isValidate(getFrom()) || !com.magicpwd._util.Char.isValidate(getTo()))
        {
            return false;
        }

        Message message = new MimeMessage(getConnect().getSession());

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


        appendBodypart(getContent());
        for (S1S1 item : getAttachmentList())
        {
            appendAttachment(item.getV(), item.getK());
        }
        message.setContent(multipart);
        message.saveChanges();

        //Transport transport = getConnect().getSession().getTransport();
        //transport.connect("", "", "");

        //transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        Transport.send(message);
        //transport.close();
        return true;
    }

    public boolean appendBodypart(String content) throws Exception
    {
        BodyPart bp = new MimeBodyPart();
        bp.setContent(content, getContentType());
        multipart.addBodyPart(bp);
        return true;
    }

    private boolean appendAttachment(String path, String name) throws Exception
    {
        if (!com.magicpwd._util.Char.isValidate(path))
        {
            return false;
        }

        java.io.File file = new java.io.File(path);
        if (file == null || !file.isFile() || !file.canRead())
        {
            return false;
        }

        if (!com.magicpwd._util.Char.isValidate(name))
        {
            name = file.getName();
        }

        BodyPart bp = new MimeBodyPart();
        FileDataSource fileds = new FileDataSource(file);
        bp.setDataHandler(new DataHandler(fileds));
        bp.setFileName(MimeUtility.encodeText(name));
        multipart.addBodyPart(bp);
        return true;
    }
}
