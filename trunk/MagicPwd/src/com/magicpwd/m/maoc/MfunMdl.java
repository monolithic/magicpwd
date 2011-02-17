/*
 *  Copyright (C) 2011 Aven
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.magicpwd.m.maoc;

import com.magicpwd._comn.S1S3;

/**
 *
 * @author Aven
 */
public class MfunMdl implements javax.swing.ListModel, java.io.Serializable
{

    private java.util.List<S1S3> funList;
    private MaocMdl maocMdl;

    public MfunMdl(MaocMdl maocMdl)
    {
        this.maocMdl = maocMdl;
        funList = new java.util.ArrayList<S1S3>();
    }

    @Override
    public int getSize()
    {
        return funList != null ? funList.size() : 0;
    }

    @Override
    public Object getElementAt(int index)
    {
        return funList.get(index);
    }

    @Override
    public void addListDataListener(javax.swing.event.ListDataListener l)
    {
        listenerList.add(javax.swing.event.ListDataListener.class, l);
    }

    @Override
    public void removeListDataListener(javax.swing.event.ListDataListener l)
    {
        listenerList.remove(javax.swing.event.ListDataListener.class, l);
    }

    public S1S3 getItemAt(int index)
    {
        return funList.get(index);
    }

    public void appendItem(S1S3 item)
    {
        int i = funList.size();
        funList.add(item);
        this.fireIntervalAdded(item, i, i);
    }

    private void fireContentsChanged(Object source, int index0, int index1)
    {
        Object[] listeners = listenerList.getListenerList();
        javax.swing.event.ListDataEvent e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2)
        {
            if (listeners[i] == javax.swing.event.ListDataListener.class)
            {
                if (e == null)
                {
                    e = new javax.swing.event.ListDataEvent(source, javax.swing.event.ListDataEvent.CONTENTS_CHANGED, index0, index1);
                }
                ((javax.swing.event.ListDataListener) listeners[i + 1]).contentsChanged(e);
            }
        }
    }

    private void fireIntervalAdded(Object source, int index0, int index1)
    {
        Object[] listeners = listenerList.getListenerList();
        javax.swing.event.ListDataEvent e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2)
        {
            if (listeners[i] == javax.swing.event.ListDataListener.class)
            {
                if (e == null)
                {
                    e = new javax.swing.event.ListDataEvent(source, javax.swing.event.ListDataEvent.INTERVAL_ADDED, index0, index1);
                }
                ((javax.swing.event.ListDataListener) listeners[i + 1]).intervalAdded(e);
            }
        }
    }

    private void fireIntervalRemoved(Object source, int index0, int index1)
    {
        Object[] listeners = listenerList.getListenerList();
        javax.swing.event.ListDataEvent e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2)
        {
            if (listeners[i] == javax.swing.event.ListDataListener.class)
            {
                if (e == null)
                {
                    e = new javax.swing.event.ListDataEvent(source, javax.swing.event.ListDataEvent.INTERVAL_REMOVED, index0, index1);
                }
                ((javax.swing.event.ListDataListener) listeners[i + 1]).intervalRemoved(e);
            }
        }
    }
    private javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();
}
