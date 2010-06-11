/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._docs;

import com.google.gdata.client.GoogleService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.docs.DocumentEntry;

/**
 *
 * @author Administrator
 */
public class GoogleWP extends Google
{

    public GoogleWP(String applicationName)
    {
        super(applicationName);
    }

    public void create(String title) throws Exception
    {
        if (title == null)
        {
            throw new Exception("null title or type");
        }

        DocumentEntry entry = new DocumentEntry();
        entry.setTitle(new PlainTextConstruct(title));
        service.insert(buildUrl(URL_DEFAULT + URL_DOCLIST_FEED), entry);
    }

    public GoogleService getService()
    {
        return service;
    }
}
