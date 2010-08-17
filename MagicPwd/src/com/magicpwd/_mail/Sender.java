/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._mail;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;

/**
 *
 * @author Amon
 */
public class Sender extends Mailer
{

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
        //mp.addBodyPart(bp);
        return true;
    }
}
