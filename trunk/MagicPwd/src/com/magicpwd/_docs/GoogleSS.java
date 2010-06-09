/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._docs;

import com.google.gdata.client.spreadsheet.FeedURLFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class GoogleSS extends Google
{

    private SpreadsheetService service;
    private URL worksheetFeedUrl;

    public GoogleSS(String name) throws Exception
    {
        super(name);
    }

    public void createSpreadsheet() throws Exception
    {
        createNew("", "spreadsheet");
    }

    private void createWorksheet(String title, int rowCount, int colCount) throws IOException, ServiceException
    {
        WorksheetEntry worksheet = new WorksheetEntry();
        worksheet.setTitle(new PlainTextConstruct(title));
        worksheet.setRowCount(rowCount);
        worksheet.setColCount(colCount);
        service.insert(worksheetFeedUrl, worksheet);
    }

    public void listWorksheets(String name) throws Exception
    {
        SpreadsheetFeed feed = service.getFeed(FeedURLFactory.getDefault().getSpreadsheetsFeedUrl(), SpreadsheetFeed.class);
        for (SpreadsheetEntry entry : feed.getEntries())
        {
            if (name.equalsIgnoreCase(entry.getTitle().getPlainText()))
            {
                worksheetFeedUrl = entry.getWorksheetFeedUrl();
            }
        }
        if (worksheetFeedUrl == null)
        {
            createSpreadsheet();
            createWorksheet("name", 0, 0);
        }
        WorksheetFeed worksheetFeed = service.getFeed(worksheetFeedUrl,
                WorksheetFeed.class);
        for (WorksheetEntry worksheet : worksheetFeed.getEntries())
        {
            String title = worksheet.getTitle().getPlainText();
            int rowCount = worksheet.getRowCount();
            int colCount = worksheet.getColCount();
            System.out.println("\t" + title + " - rows:" + rowCount + " cols: "
                    + colCount);
        }
    }

    private void deleteWorksheet(String title) throws IOException,
            ServiceException
    {
        WorksheetFeed worksheetFeed = service.getFeed(worksheetFeedUrl,
                WorksheetFeed.class);
        for (WorksheetEntry worksheet : worksheetFeed.getEntries())
        {
            String currTitle = worksheet.getTitle().getPlainText();
            if (currTitle.equals(title))
            {
                worksheet.delete();
                System.out.println("Worksheet deleted.");
                return;
            }
        }

        // If it got this far, the worksheet wasn't found.
        System.out.println("Worksheet not found: " + title);
    }

    private void updateWorksheet(String oldTitle, String newTitle,
            int rowCount, int colCount) throws IOException, ServiceException
    {
        WorksheetFeed worksheetFeed = service.getFeed(worksheetFeedUrl,
                WorksheetFeed.class);
        for (WorksheetEntry worksheet : worksheetFeed.getEntries())
        {
            String currTitle = worksheet.getTitle().getPlainText();
            if (currTitle.equals(oldTitle))
            {
                worksheet.setTitle(new PlainTextConstruct(newTitle));
                worksheet.setRowCount(rowCount);
                worksheet.setColCount(colCount);
                worksheet.update();
                System.out.println("Worksheet updated.");
                return;
            }
        }

        // If it got this far, the worksheet wasn't found.
        System.out.println("Worksheet not found: " + oldTitle);
    }
}
