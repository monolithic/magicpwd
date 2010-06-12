/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._docs;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gdata.client.spreadsheet.FeedURLFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.ServiceException;
import com.magicpwd._cons.ConsEnv;
import com.magicpwd._util.Util;

/**
 *
 * @author Administrator
 */
public class Google
{

    private SpreadsheetService service;
    private SpreadsheetEntry spreadsheetEntry;
    private WorksheetEntry worksheetEntry;

    public Google()
    {
    }

    public boolean backup(String user, String pass, String name)
    {
        return true;
    }

    public boolean resume(String user, String pass, String name)
    {
        return true;
    }

    public boolean chooseSpreadsheet(String user, String pass, String name) throws Exception
    {
        if (!Util.isValidate(user) || pass == null || !Util.isValidate(name))
        {
            return false;
        }

        service = new SpreadsheetService("MagicPwd");
        service.setUserCredentials(user, pass);
        SpreadsheetFeed feed = service.getFeed(FeedURLFactory.getDefault().getSpreadsheetsFeedUrl(), SpreadsheetFeed.class);
        for (SpreadsheetEntry entry : feed.getEntries())
        {
            if (name.equalsIgnoreCase(entry.getTitle().getPlainText()))
            {
                spreadsheetEntry = entry;
                worksheetEntry = spreadsheetEntry.getDefaultWorksheet();
                return true;
            }
        }

        com.google.gdata.data.docs.SpreadsheetEntry entry = new com.google.gdata.data.docs.SpreadsheetEntry();
        entry.setTitle(new PlainTextConstruct(name));
        com.google.gdata.client.docs.DocsService docs = new com.google.gdata.client.docs.DocsService("MagicPwd");
        docs.setUserCredentials(user, pass);
        docs.insert(buildUrl(URL_DEFAULT + URL_DOCLIST_FEED), entry);
        worksheetEntry = entry.getDefaultWorksheet();

        return true;
    }

    private void createWorksheet(String title, int rowCount, int colCount) throws Exception
    {
        WorksheetEntry worksheet = new WorksheetEntry();
        worksheet.setTitle(new PlainTextConstruct(title));
        worksheet.setRowCount(rowCount);
        worksheet.setColCount(colCount);
        service.insert(spreadsheetEntry.getWorksheetFeedUrl(), worksheet);
    }

    public void updateWorksheet(String oldTitle, String newTitle, int rowCount, int colCount) throws Exception
    {
        worksheetEntry.setTitle(new PlainTextConstruct(newTitle));
        worksheetEntry.setRowCount(rowCount);
        worksheetEntry.setColCount(colCount);
        worksheetEntry.update();
    }

    public void dd() throws Exception
    {
        CellFeed feed = service.getFeed(worksheetEntry.getCellFeedUrl(), CellFeed.class);
        for (CellEntry entry : feed.getEntries())
        {
            entry.getCell().getValue();
        }
    }

    public void setCell(int row, int col, String formulaOrValue) throws IOException, ServiceException
    {
        service.insert(worksheetEntry.getCellFeedUrl(), new CellEntry(row, col, formulaOrValue));
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
    protected URL buildUrl(String path) throws MalformedURLException, Exception
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
    protected URL buildUrl(String path, String[] parameters) throws MalformedURLException, Exception
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
    protected URL buildUrl(String domain, String path, String[] parameters) throws MalformedURLException, Exception
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
    protected URL buildUrl(String domain, String path, Map<String, String> parameters) throws MalformedURLException, Exception
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
    public static final String DEFAULT_AUTH_PROTOCOL = "https";
    public static final String DEFAULT_AUTH_HOST = "docs.google.com";
    public static final String DEFAULT_PROTOCOL = "http";
    public static final String DEFAULT_HOST = "docs.google.com";
    public static final String SPREADSHEETS_SERVICE_NAME = "wise";
    public static final String SPREADSHEETS_HOST = "spreadsheets.google.com";
    protected final String URL_FEED = "/feeds";
    protected final String URL_DOWNLOAD = "/download";
    protected final String URL_DOCLIST_FEED = "/private/full";
    protected final String URL_DEFAULT = "/default";
    protected final String URL_FOLDERS = "/contents";
    protected final String URL_ACL = "/acl";
    protected final String URL_REVISIONS = "/revisions";
    protected final String URL_CATEGORY_DOCUMENT = "/-/document";
    protected final String URL_CATEGORY_SPREADSHEET = "/-/spreadsheet";
    protected final String URL_CATEGORY_PDF = "/-/pdf";
    protected final String URL_CATEGORY_PRESENTATION = "/-/presentation";
    protected final String URL_CATEGORY_STARRED = "/-/starred";
    protected final String URL_CATEGORY_TRASHED = "/-/trashed";
    protected final String URL_CATEGORY_FOLDER = "/-/folder";
    protected final String URL_CATEGORY_EXPORT = "/Export";
    protected final String PARAMETER_SHOW_FOLDERS = "showfolders=true";

    public static void main(String[] args)
    {
        try
        {
            Google gss = new Google();
            gss.chooseSpreadsheet("Amon.CT@gmail.com", "qTrH2e3oXk", ConsEnv.FILE_SYNC);
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
