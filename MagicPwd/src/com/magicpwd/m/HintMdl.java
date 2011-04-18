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
package com.magicpwd.m;

import com.magicpwd.__i.IBackCall;
import com.magicpwd.__i.IHintView;
import com.magicpwd._comn.Task;
import com.magicpwd._comn.mpwd.Hint;
import com.magicpwd._comn.mpwd.Mgtd;
import com.magicpwd._util.Char;
import com.magicpwd._util.Time;
import com.magicpwd.d.db.DBA4000;
import com.magicpwd.d.db.DBAccess;

/**
 * Application: MagicPwd
 * Author     : Aven
 * Encoding   : UTF-8
 * Website    : http://magicpwd.com/
 * Project    : http://magicpwd.googlecode.com/
 * Contact    : Amon@magicpwd.com
 * CopyRight  : Winshine.biz
 * Description:
 */
public final class HintMdl
{

    private UserMdl userMdl;
    private java.text.DateFormat dateTplt;
    private java.util.List<Hint> mgtdList;
    private java.util.List<Hint> hintList;
    private java.util.List<IHintView> viewList;
    private IBackCall<String, java.util.List<Mgtd>> backCall;
    private int counter;

    HintMdl(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }

    public void init()
    {
        dateTplt = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.FULL, java.text.DateFormat.MEDIUM);

        viewList = new java.util.ArrayList<IHintView>();
        mgtdList = new java.util.ArrayList<Hint>();
        hintList = new java.util.ArrayList<Hint>();
        counter = userMdl.getHintInt();

        Time.getInstance().registerAction(new Task(0, 1, "mexp-hint", ""), new IBackCall<String, Task>()
        {

            @Override
            public boolean callBack(String options, Task object)
            {
                object.setCounter(0);
                return showNote(false);
            }
        });
    }

    public void registerHintView(IHintView view)
    {
        viewList.add(view);
    }

    public boolean showNote(boolean forced)
    {
        showTime();

        // 读取数据信息
        if (counter >= userMdl.getHintInt() || forced)
        {
            if (DBAccess.locked)
            {
                return false;
            }
            mgtdList.clear();
            DBA4000.findHintList(userMdl, mgtdList);
            counter = 0;
        }

        // 到期提示判断
        hintList.clear();
        java.util.Calendar cal = java.util.Calendar.getInstance();
        for (Hint hint : mgtdList)
        {
            if (Time.isOnTime(cal, hint))
            {
                hintList.add(hint);
            }
        }

        showHint();
        return true;
    }

    /**
     * 显示日期信息
     */
    public void showTime()
    {
        String text = dateTplt.format(new java.util.Date());
        for (IHintView view : viewList)
        {
            view.showTime(text, text);
        }
    }

    public void showHint()
    {
        int size = hintList.size();
        for (IHintView view : viewList)
        {
            if (size > 0)
            {
                view.showHint(Char.format("您有 {0} 条提醒数据！", Integer.toString(size)), "点击查看详细信息！", java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
            }
            else
            {
                view.showHint("您目前没有需要提醒的数据！", null, java.awt.Cursor.getDefaultCursor());
            }
        }
    }

    public java.util.List<Hint> getHint()
    {
        return hintList;
    }
}
