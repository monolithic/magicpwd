/**
 * 
 */
package com.magicpwd._mail;

import com.magicpwd._util.Logs;
import com.magicpwd._util.Util;
import javax.mail.Folder;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author Amon
 * 
 */
public class NodeMdl extends DefaultMutableTreeNode
{

    private Connect connect;
    private String display;
    private String keyWord;
    private boolean haveMsg;

    public NodeMdl(Connect connect, Folder folder)
    {
        if (folder != null)
        {
            try
            {
                this.connect = connect;
                keyWord = folder.getFullName();
                if (!Util.isValidate(keyWord))
                {
                    display = connect.getMail();
                }
                else
                {
                    display = Util.format("{0}({1}/{2})", folder.getName(), Integer.toString(folder.getUnreadMessageCount()), Integer.toString(folder.getMessageCount()));
                }
                haveMsg = (folder.getType() & Folder.HOLDS_MESSAGES) != 0;
            }
            catch (Exception exp)
            {
                Logs.exception(exp);
            }
        }
    }

    @Override
    public boolean isLeaf()
    {
        return false;
    }

    @Override
    public String toString()
    {
        return Util.isValidate(display) ? display : connect.getMail();
    }

    /**
     * @return the connect
     */
    public Connect getConnect()
    {
        return connect;
    }

    /**
     * @return the keyWord
     */
    public String getKeyWord()
    {
        return keyWord;
    }

    /**
     * @return the haveMsg
     */
    public boolean isHaveMsg()
    {
        return haveMsg;
    }
}
