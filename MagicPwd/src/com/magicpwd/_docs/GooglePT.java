/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._docs;

import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Administrator
 */
public class GooglePT extends Google
{

    public GooglePT(String applicationName)
    {
        super(applicationName);
    }

    /**
     * Downloads a presentation.
     *
     * @param filepath
     *            path and name of the object to be saved as.
     * @param resourceId
     *            the resource id of the object to be downloaded.
     * @param format
     *            format to download the file to. The following file types are
     *            supported: presentations: "pdf", "ppt", "png", "swf", "txt"
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

        String[] parameters =
        {
            "docID=" + resourceId, "exportFormat=" + format
        };
        URL url = buildUrl(URL_DOWNLOAD + "/presentations" + URL_CATEGORY_EXPORT, parameters);

        downloadFile(url, filepath);
    }
}
