/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._docs;

import com.google.gdata.client.spreadsheet.CellQuery;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gdata.client.spreadsheet.FeedURLFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.spreadsheet.Cell;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Code;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import java.io.File;
import java.net.MalformedURLException;

/**
 *
 * @author Administrator
 */
public class Google
{

    private SpreadsheetService service;

    public Google()
    {
    }

    public boolean backup(String user, String pass, String name, File file) throws Exception
    {
        if (!Util.isValidate(user) || pass == null || !Util.isValidate(name))
        {
            return false;
        }

        service = new SpreadsheetService("MagicPwd");
        service.setUserCredentials(user, pass);
        SpreadsheetEntry spreadsheet = listSpreadsheetFeed(name);
        java.net.URL cellFeedUrl = null;
        if (spreadsheet == null)
        {
            com.google.gdata.data.docs.SpreadsheetEntry entry = new com.google.gdata.data.docs.SpreadsheetEntry();
            entry.setTitle(new PlainTextConstruct(name));
            com.google.gdata.client.docs.DocsService docs = new com.google.gdata.client.docs.DocsService("MagicPwd");
            docs.setUserCredentials(user, pass);
            docs.insert(buildUrl(URL_DEFAULT + URL_DOCLIST_FEED), entry);
            spreadsheet = listSpreadsheetFeed(name);
            if (spreadsheet == null)
            {
                return false;
            }

            java.util.List<WorksheetEntry> list = spreadsheet.getWorksheets();
            WorksheetEntry worksheet;
            if (list.size() > 0)
            {
                worksheet = list.get(0);
                worksheet.setColCount(2);
                worksheet.setRowCount(100000);
                worksheet.setTitle(new PlainTextConstruct(name));
                worksheet.update();
            }
            else
            {
                worksheet = new WorksheetEntry();
                worksheet.setTitle(new PlainTextConstruct(name));
                worksheet.setRowCount(65536);
                worksheet.setColCount(2);
                service.insert(entry.getWorksheetFeedUrl(), worksheet);
            }
            int s = worksheet.getRowCount();
            cellFeedUrl = worksheet.getCellFeedUrl();
        }

        java.io.BufferedInputStream bi = null;

        try
        {
            bi = new java.io.BufferedInputStream(new java.io.FileInputStream(file));

            byte[] buf = new byte[19200];
            int len = bi.read(buf);
            int row = 2;

            while (len >= 0)
            {
                service.insert(cellFeedUrl, new CellEntry(row++, 2, new String(Code.encode(buf, 0, len))));
                len = bi.read(buf);
            }

            service.insert(cellFeedUrl, new CellEntry(1, 2, file.getName()));
        }
        finally
        {
            if (bi != null)
            {
                try
                {
                    bi.close();
                }
                catch (Exception exp)
                {
                    Logs.exception(exp);
                    return false;
                }
            }
        }

        return true;
    }

    public boolean resume(String user, String pass, String name, File file) throws Exception
    {
        if (!Util.isValidate(user) || pass == null || !Util.isValidate(name))
        {
            return false;
        }

        service = new SpreadsheetService("MagicPwd");
        service.setUserCredentials(user, pass);
        SpreadsheetEntry spreadsheet = listSpreadsheetFeed(name);
        if (spreadsheet == null)
        {
            return false;
        }

        WorksheetEntry worksheet = null;
        for (WorksheetEntry entry : spreadsheet.getWorksheets())
        {
            if (name.equalsIgnoreCase(entry.getTitle().getPlainText()))
            {
                worksheet = entry;
                break;
            }
        }
        if (worksheet == null)
        {
            return false;
        }

        CellQuery query = new CellQuery(worksheet.getCellFeedUrl());
        query.setMinimumRow(1);
        query.setMaximumRow(1);
        query.setMinimumCol(1);
        query.setMaximumCol(2);
        CellFeed feed = service.query(query, CellFeed.class);
        int maxRow = 0;
        String fileName = null;
        for (CellEntry entry : feed.getEntries())
        {
            Cell cell = entry.getCell();
            if (cell.getCol() == 1)
            {
                maxRow = Integer.parseInt(cell.getValue(), 16);
            }
            else if (cell.getCol() == 2)
            {
                fileName = cell.getValue();
            }
        }

        java.io.BufferedOutputStream bo = null;
        try
        {
            bo = new java.io.BufferedOutputStream(new java.io.FileOutputStream(file));

            query.setMinimumRow(2);
            query.setMaximumRow(maxRow);
            query.setMinimumCol(2);
            query.setMaximumCol(2);
            feed = service.query(query, CellFeed.class);
            for (CellEntry entry : feed.getEntries())
            {
                bo.write(Code.decode(entry.getCell().getValue().toCharArray()));
            }
        }
        finally
        {
            if (bo != null)
            {
                try
                {
                    bo.flush();
                }
                catch (Exception exp)
                {
                    Logs.exception(exp);
                }
                try
                {
                    bo.close();
                }
                catch (Exception exp)
                {
                    Logs.exception(exp);
                }
            }
        }
        return true;
    }

    private SpreadsheetEntry listSpreadsheetFeed(String name) throws Exception
    {
        SpreadsheetFeed feed = service.getFeed(FeedURLFactory.getDefault().getSpreadsheetsFeedUrl(), SpreadsheetFeed.class);
        for (SpreadsheetEntry entry : feed.getEntries())
        {
            if (name.equalsIgnoreCase(entry.getTitle().getPlainText()))
            {
                return entry;
            }
        }
        return null;
    }

    /**
     * Builds a URL from a patch.
     *
     * @param path
     *            the path to add to the protocol/host
     *
     * @throws MalformedURLException
     * @throws Exception
     */
    protected URL buildUrl(String path) throws Exception
    {
        if (path == null)
        {
            throw new Exception("null path");
        }

        return buildUrl(path, null);
    }

    /**
     * Builds a URL with parameters.
     *
     * @param path
     *            the path to add to the protocol/host
     * @param parameters
     *            parameters to be added to the URL.
     *
     * @throws MalformedURLException
     * @throws Exception
     */
    protected URL buildUrl(String path, String[] parameters) throws Exception
    {
        if (path == null)
        {
            throw new Exception("null path");
        }

        return buildUrl(DEFAULT_HOST, path, parameters);
    }

    /**
     * Builds a URL with parameters.
     *
     * @param domain
     *            the domain of the server
     * @param path
     *            the path to add to the protocol/host
     * @param parameters
     *            parameters to be added to the URL.
     *
     * @throws MalformedURLException
     * @throws Exception
     */
    protected URL buildUrl(String domain, String path, String[] parameters) throws Exception
    {
        if (path == null)
        {
            throw new Exception("null path");
        }

        StringBuffer url = new StringBuffer();
        url.append(DEFAULT_PROTOCOL + "://" + domain + URL_FEED + path);

        if (parameters != null && parameters.length > 0)
        {
            url.append("?");
            for (int i = 0; i < parameters.length; i++)
            {
                url.append(parameters[i]);
                if (i != (parameters.length - 1))
                {
                    url.append("&");
                }
            }
        }

        System.out.println(url.toString());
        return new URL(url.toString());
    }

    /**
     * Builds a URL with parameters.
     *
     * @param domain
     *            the domain of the server
     * @param path
     *            the path to add to the protocol/host
     * @param parameters
     *            parameters to be added to the URL as key value pairs.
     *
     * @throws MalformedURLException
     * @throws Exception
     */
    protected URL buildUrl(String domain, String path, Map<String, String> parameters) throws Exception
    {
        if (path == null)
        {
            throw new Exception("null path");
        }

        StringBuffer url = new StringBuffer();
        url.append(DEFAULT_PROTOCOL + "://" + domain + URL_FEED + path);

        if (parameters != null && parameters.size() > 0)
        {
            Set<Map.Entry<String, String>> params = parameters.entrySet();
            Iterator<Map.Entry<String, String>> itr = params.iterator();

            url.append("?");
            while (itr.hasNext())
            {
                Map.Entry<String, String> entry = itr.next();
                url.append(entry.getKey() + "=" + entry.getValue());
                if (itr.hasNext())
                {
                    url.append("&");
                }
            }
        }

        return new URL(url.toString());
    }
    private static final String DEFAULT_AUTH_PROTOCOL = "https";
    private static final String DEFAULT_AUTH_HOST = "docs.google.com";
    private static final String DEFAULT_PROTOCOL = "http";
    private static final String DEFAULT_HOST = "docs.google.com";
    private static final String SPREADSHEETS_SERVICE_NAME = "wise";
    private static final String SPREADSHEETS_HOST = "spreadsheets.google.com";
    private final String URL_FEED = "/feeds";
    private final String URL_DOWNLOAD = "/download";
    private final String URL_DOCLIST_FEED = "/private/full";
    private final String URL_DEFAULT = "/default";
    private final String URL_FOLDERS = "/contents";
    private final String URL_ACL = "/acl";
    private final String URL_REVISIONS = "/revisions";
    private final String URL_CATEGORY_DOCUMENT = "/-/document";

    public static void main(String[] args)
    {
        try
        {
            Google gss = new Google();
            gss.backup("Amon.CT@gmail.com", "qTrH2e3oXk", ConsEnv.FILE_SYNC, null);
            // if (!gss.listSpreadsheet("magicpwd")) {
            // gss.create("magicpwd", "spreadsheet");
            // gss.listSpreadsheet("magicpwd");
            // }
            // if (!gss.listWorksheets("magicpwd")) {
            // gss.createWorksheet("magicpwd", 65535, 2);
            // gss.listWorksheets("magicpwd");
            // }
            //
            // java.io.BufferedReader bis = new java.io.BufferedReader(new
            // FileReader("F:\\Rmps\\MagicPwd\\bak\\magicpwd.amb"));
            // String line = bis.readLine();
            // int i = 1;
            // while (line != null) {
            // System.out.println(line);
            // gss.setCell(i++, 1, line);
            // line = bis.readLine();
            // }
            // bis.close();
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }
}
