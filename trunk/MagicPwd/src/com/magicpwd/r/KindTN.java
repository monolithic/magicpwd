/**
 * 
 */
package com.magicpwd.r;

import com.magicpwd._comn.Kind;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import com.magicpwd.d.DBA3000;

/**
 * @author Amon
 * 
 */
public class KindTN extends DefaultMutableTreeNode
{
    /**
     * @param kvItem
     */
    public KindTN(Kind kvItem)
    {
        super(kvItem);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.tree.DefaultMutableTreeNode#isLeaf()
     */
    @Override
    public boolean isLeaf()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.tree.DefaultMutableTreeNode#getChildCount()
     */
    @Override
    public int getChildCount()
    {
        if (!hasLoaded)
        {
            loadChildren();
        }
        return super.getChildCount();
    }

    /**
     * 获取下级目录数据列表
     */
    private void loadChildren()
    {
        Kind kvItem = (Kind) getUserObject();
        if (kvItem != null)
        {
            List<Kind> list = DBA3000.selectKindData(kvItem.getC2010103());
            if (list != null)
            {
                for (int i = 0, j = list.size(); i < j; i += 1)
                {
                    insert(new KindTN(list.get(i)), i);
                }
            }
        }
        hasLoaded = true;
    }

    /**
     * @return
     */
    public boolean isLoaded()
    {
        return hasLoaded;
    }
    private boolean hasLoaded;
}
