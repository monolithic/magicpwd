/**
 * 
 */
package com.magicpwd.m;

import com.magicpwd._comn.Tplt;
import com.magicpwd.d.DBA3000;

/**
 * @author shangwen.yao
 * 
 */
public class CboxMdl extends javax.swing.AbstractListModel implements javax.swing.MutableComboBoxModel, java.io.Serializable
{
    private java.util.List<Tplt> itemList;
    private Object selectedObject;

    CboxMdl()
    {
    }

    void initData()
    {
        itemList = DBA3000.selectTpltData("0");
    }

    public void setSelectedItem(Object anObject)
    {
        if ((selectedObject != null && !selectedObject.equals(anObject)) || (selectedObject == null && anObject != null))
        {
            selectedObject = anObject;
            fireContentsChanged(this, -1, -1);
        }
    }

    public Object getSelectedItem()
    {
        return selectedObject;
    }

    public int getSize()
    {
        return itemList.size();
    }

    public Tplt getElementAt(int index)
    {
        if (index >= 0 && index < itemList.size())
        {
            return itemList.get(index);
        }
        else
        {
            return null;
        }
    }

    public void addElement(Object anObject)
    {
        if (anObject instanceof Tplt)
        {
            itemList.add((Tplt) anObject);
            fireIntervalAdded(this, itemList.size() - 1, itemList.size() - 1);
            if (itemList.size() == 1 && selectedObject == null && anObject != null)
            {
                setSelectedItem(anObject);
            }
        }
    }

    public void insertElementAt(Object anObject, int index)
    {
        if (anObject instanceof Tplt)
        {
            itemList.add(index, (Tplt) anObject);
            fireIntervalAdded(this, index, index);
        }
    }

    public void removeElementAt(int index)
    {
        if (getElementAt(index) == selectedObject)
        {
            if (index == 0)
            {
                setSelectedItem(getSize() == 1 ? null : getElementAt(index + 1));
            }
            else
            {
                setSelectedItem(getElementAt(index - 1));
            }
        }

        itemList.remove(index);

        fireIntervalRemoved(this, index, index);
    }

    public void removeElement(Object anObject)
    {
        int index = itemList.indexOf(anObject);
        if (index != -1)
        {
            removeElementAt(index);
        }
    }

    public int getIndexOf(Object anObject)
    {
        return itemList.indexOf(anObject);
    }

    public void removeAllElements()
    {
        if (itemList.size() > 0)
        {
            int firstIndex = 0;
            int lastIndex = itemList.size() - 1;
            itemList.clear();
            fireIntervalRemoved(this, firstIndex, lastIndex);
        }
        selectedObject = null;
    }

    public java.util.List<Tplt> getAllItems()
    {
        return itemList;
    }

    public void moveTo(int s, int t)
    {
    }

    public void wAppend(Tplt item)
    {
        itemList.add(item);
        fireIntervalAdded(this, itemList.size() - 1, itemList.size() - 1);
        if (itemList.size() == 1 && selectedObject == null && item != null)
        {
            setSelectedItem(item);
        }
    }

    public void wUpdate()
    {
        this.fireContentsChanged(this, 0, itemList.size() - 1);
    }

    public void wRemove(int index)
    {
        itemList.remove(index);
        fireIntervalRemoved(this, index, index);
        if (index > itemList.size() - 1)
        {
            index = 0;
        }
        setSelectedItem(itemList.get(index));
    }
}
