/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._docs;

import com.google.gdata.client.spreadsheet.FeedURLFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.docs.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.ServiceException;

/**
 *
 * @author Administrator
 */
public class Google
{

    private SpreadsheetService service;
    private SpreadsheetEntry spreadsheetEntry;
    private WorksheetEntry worksheetEntry;

    public Google(String name)
    {
        service = new SpreadsheetService(name);
    }

    public void signin(String user, String pass) throws Exception
    {
        if (user == null || pass == null)
        {
            throw new Exception("null login credentials");
        }
        service.setUserCredentials(user, pass);
    }

    private void createSpreadsheet(String title) throws Exception
    {
        spreadsheetEntry = new SpreadsheetEntry();
        spreadsheetEntry.setTitle(new PlainTextConstruct(title));
        service.insert(buildUrl(URL_DEFAULT + URL_DOCLIST_FEED), spreadsheetEntry);
        worksheetEntry = spreadsheetEntry.getDefaultWorksheet();
    }

    private void createWorksheet(String title, int rowCount, int colCount) throws Exception
    {
        WorksheetEntry worksheet = new WorksheetEntry();
        worksheet.setTitle(new PlainTextConstruct(title));
        worksheet.setRowCount(rowCount);
        worksheet.setColCount(colCount);
        service.insert(spreadsheetEntry.getWorksheetFeedUrl(), worksheet);
    }

    public void deleteWorksheet(String title) throws Exception
    {
        for (WorksheetEntry worksheet : spreadsheetEntry.getWorksheets())
        {
            String currTitle = worksheet.getTitle().getPlainText();
            if (currTitle.equals(title))
            {
                worksheet.delete();
                return;
            }
        }
    }

    public void updateWorksheet(String oldTitle, String newTitle, int rowCount, int colCount) throws Exception
    {
        worksheetEntry.setTitle(new PlainTextConstruct(newTitle));
        worksheetEntry.setRowCount(rowCount);
        worksheetEntry.setColCount(colCount);
        worksheetEntry.update();
        System.out.println("Worksheet updated.");
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
        CellEntry newEntry = new CellEntry(row, col, formulaOrValue);
        service.insert(worksheetEntry.getCellFeedUrl(), newEntry);
    }

    public boolean listWorksheets(String name) throws Exception
    {
        for (WorksheetEntry entry : spreadsheetEntry.getWorksheets())
        {
            if (name.equalsIgnoreCase(entry.getTitle().getPlainText()))
            {
                worksheetEntry = entry;
                return true;
            }
        }
        return false;
    }

    public boolean listSpreadsheet(String name) throws Exception
    {
        if (name == null)
        {
            return false;
        }
        SpreadsheetFeed feed = service.getFeed(FeedURLFactory.getDefault().getSpreadsheetsFeedUrl(), SpreadsheetFeed.class);
        for (com.google.gdata.data.docs.SpreadsheetEntry entry : feed.getEntries())
        {
            if (name.equalsIgnoreCase(entry.getTitle().getPlainText()))
            {
                spreadsheetEntry = entry;
                return true;
            }
        }
        return false;
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
            Google gss = new Google("amonsoft");
            gss.signin("Amon.RG@gmail.com", "aaa");
            gss.createSpreadsheet("wokao");
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
