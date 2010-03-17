/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd.u;

import com.magicpwd._cons.ConsEnv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Amon
 */
public class DBU3000
{

    public void initView()
    {
    }

    public void initLang()
    {
    }

    public void initData()
    {
        try
        {
            Class.forName("org.hsqldb.jdbcDriver");

            Connection connSrc1 = DriverManager.getConnection("jdbc:hsqldb:file:" + ConsEnv.DIR_DAT + "/amon");
            Statement statSrc1 = connSrc1.createStatement();
            Statement statSrc2 = connSrc1.createStatement();

            Connection connDst = DriverManager.getConnection("jdbc:hsqldb:file:" + ConsEnv.DIR_DAT + "/amon");
            Statement statDst = connDst.createStatement();
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }

    private void copyPwds(Statement statSrc1, Statement statSrc2, Statement statDst) throws Exception
    {
        ResultSet result = statSrc1.executeQuery("");
        while (result.next())
        {
        }
    }

    public static void main(String[] args)
    {
    }
}
