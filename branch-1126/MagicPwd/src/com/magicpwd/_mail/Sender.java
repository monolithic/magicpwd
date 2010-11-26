/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._mail;

import com.magicpwd._comn.S1S1;
import com.magicpwd.m.UserMdl;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
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

    private java.util.HashMap<String, String> headers;

    public Sender(UserMdl userMdl, Connect connect)
    {
        super(userMdl, connect);
        headers = new java.util.HashMap<String, String>();
    }

    public boolean send() throws Exception
    {
        if (!com.magicpwd._util.Char.isValidate(getFrom()) || !com.magicpwd._util.Char.isValidate(getTo()))
        {
            return false;
        }

        Session session = getConnect().getSession();
        MimeMessage message = new MimeMessage(session);

        message.setSubject(getSubject());
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
        if (headers != null)
        {
            for (String key : headers.keySet())
            {
                message.setHeader(key, headers.get(key));
            }
        }

        Multipart multipart = new MimeMultipart();
        appendBodypart(multipart, getContent());
        for (S1S1 item : getAttachmentList())
        {
            appendAttachment(multipart, item.getV(), item.getK());
        }
        message.setContent(multipart);
        message.saveChanges();

        Transport transport = session.getTransport("smtp");
        transport.connect(getConnect().getHost(), getConnect().getUsername(), getConnect().getPassword());
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
        return true;
    }

    private boolean appendBodypart(Multipart multipart, String content) throws Exception
    {
        BodyPart bp = new MimeBodyPart();
        bp.setContent(content, getContentType());
        multipart.addBodyPart(bp);
        return true;
    }

    private boolean appendAttachment(Multipart multipart, String path, String name) throws Exception
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

        MimeBodyPart bp = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(file);
        bp.setDataHandler(new DataHandler(fds));
        bp.setFileName(MimeUtility.encodeText(name));
        multipart.addBodyPart(bp);
        return true;
    }

    public void setHeader(String key, String value)
    {
        if (headers == null)
        {
            headers = new java.util.HashMap<String, String>();
        }
        headers.put(key, value);
    }
}
