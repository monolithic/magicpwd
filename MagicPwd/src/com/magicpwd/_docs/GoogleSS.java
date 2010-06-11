/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._docs;

import com.google.gdata.client.GoogleAuthTokenFactory.UserToken;
import com.google.gdata.client.GoogleService;
import com.google.gdata.client.spreadsheet.FeedURLFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class GoogleSS extends Google
{

    private SpreadsheetService service;
    private SpreadsheetEntry spreadsheetEntry;
    private WorksheetEntry worksheetEntry;

    public GoogleSS(String name) throws Exception
    {
        super(name);
        service = new SpreadsheetService(getApplicationName());
    }

    @Override
    public void signin(String user, String pass) throws Exception
    {
        if (user == null || pass == null)
        {
            throw new Exception("null login credentials");
        }

        super.signin(user, pass);
        service.setUserCredentials(user, pass);
    }

    @Override
    public void signinWithAuthSubToken(String token) throws Exception
    {
        if (token == null)
        {
            throw new Exception("null login credentials");
        }

        super.signinWithAuthSubToken(token);
        service.setAuthSubToken(token);
    }

    private void createWorksheet(String title, int rowCount, int colCount) throws IOException, ServiceException
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
                System.out.println("Worksheet deleted.");
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
        for (SpreadsheetEntry entry : feed.getEntries())
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
     * Downloads a spreadsheet file.
     *
     * @param filepath
     *            path and name of the object to be saved as.
     * @param resourceId
     *            the resource id of the object to be downloaded.
     * @param format
     *            format to download the file to. The following file types are
     *            supported: spreadsheets: "ods", "pdf", "xls", "csv", "html",
     *            "tsv"
     *
     * @throws IOException
     * @throws MalformedURLException
     * @throws ServiceException
     * @throws Exception
     */
    public void download(String resourceId, String filepath, String format) throws Exception
    {
        if (resourceId == null || filepath == null || format == null)
        {
            throw new Exception("null passed in for required parameters");
        }

        UserToken docsToken = (UserToken) service.getAuthTokenFactory().getAuthToken();
        UserToken spreadsheetsToken = (UserToken) service.getAuthTokenFactory().getAuthToken();
        service.setUserToken(spreadsheetsToken.getValue());

        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("key", resourceId.substring(resourceId.lastIndexOf(':') + 1));
        parameters.put("exportFormat", format);

        // If exporting to .csv or .tsv, add the gid parameter to specify which
        // sheet to export
        if (format.equals(DOWNLOAD_SPREADSHEET_FORMATS.get("csv")) || format.equals(DOWNLOAD_SPREADSHEET_FORMATS.get("tsv")))
        {
            parameters.put("gid", "0"); // download only the first sheet
        }

        URL url = buildUrl(SPREADSHEETS_HOST, URL_DOWNLOAD + "/spreadsheets" + URL_CATEGORY_EXPORT, parameters);

        downloadFile(url, filepath);

        // Restore docs token for our DocList client
        service.setUserToken(docsToken.getValue());
    }

    public GoogleService getService()
    {
        return service;
    }
}
