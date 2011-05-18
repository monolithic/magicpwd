package test;

import com.magicpwd.v.tray.TrayWnd;
import java.sql.DriverManager;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aven
 */
public class Test
{

    public static void main(String[] args)
    {
        TrayWnd tw = new TrayWnd(null, null);
        tw.initView();
        tw.initLang();
        tw.initData();
        tw.setVisible(true);
    }

    private static void testDB()
    {
        try
        {
            StringBuilder buf = new StringBuilder();
            buf.append("jdbc:hsqldb:file:").append("D:\\").append("amon");
            java.sql.Connection conn = DriverManager.getConnection(buf.toString());
            java.sql.Statement stat = conn.createStatement();
            stat.execute("shutdown");
            stat.close();
            conn.close();
            System.out.println("OK");
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }
}
