/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.mail;

import com.magicpwd._mail.Connect;
import com.magicpwd._mail.MailDlg;
import com.magicpwd._util.Logs;
import javax.swing.UIManager;

/**
 *
 * @author Administrator
 */
public class Test
{

    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
        MailDlg md = new MailDlg();
        md.initView();
        md.initLang();
        md.initData();
        md.setVisible(true);
        Connect connect = new Connect("pop3", "Amon.WK@163.com", "iv7I7UuG");
        connect.setUsername("Amon.WK");
        connect.setHost("pop.163.com");
        connect.setPort(-1);
        connect.setAuth(true);
        try
        {
            md.append(connect, "");
        }
        catch (Exception ex)
        {
            Logs.exception(ex);
        }
    }
}
