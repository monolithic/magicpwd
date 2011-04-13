/*
 *  Copyright (C) 2010 Amon
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
import com.magicpwd._comn.Task;
import com.magicpwd._comn.mpwd.Mgtd;
import com.magicpwd._comn.mpwd.Mkey;
import com.magicpwd._cons.ConsDat;
import com.magicpwd._util.Char;
import com.magicpwd._util.Logs;
import com.magicpwd._util.Time;
import java.util.ArrayList;
import java.util.List;

import com.magicpwd.d.db.DBA4000;
import com.magicpwd.d.db.DBAccess;
import java.sql.Timestamp;
import java.util.Calendar;
import org.javia.arity.Symbols;

/**
 * 过期提醒数据模型
 * TODO:提醒功能需要完善。
 * 1、添加配置界面：检测时间间隔，提前多久提醒，是否重复等
 * 2、当新增或修改数据以后，能即时更新需要提醒数据列表。
 * 3、到达提醒时间后，能够以某种方式进行提醒。
 * @author Amon
 */
public final class HintMdl
{

    private static List<Mgtd> hintList;
    private static List<Mgtd> mgtdList;
    private int counter;
    private UserMdl userMdl;

    public HintMdl(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }

    public void initData()
    {
        hintList = new ArrayList<Mgtd>();
        counter = userMdl.getHintInt();
        Time.getInstance().registerAction(new Task(0, 1, "mexp-hint", ""), new IBackCall<String, Task>()
        {

            @Override
            public boolean callBack(String options, Task object)
            {
                return dd(null, null);
            }
        });
    }

    private boolean dd(Calendar start, Calendar end)
    {
        Calendar cal = Calendar.getInstance();
        long now = cal.getTimeInMillis();

        for (Mgtd mgtd : hintList)
        {
            // 指定时间
            if (mgtd.getP30F0701() == ConsDat.MGTD_FIXED)
            {
                if (start.getTimeInMillis() < mgtd.getP30F070F() && mgtd.getP30F070F() < end.getTimeInMillis())
                {
                    mgtdList.add(mgtd);
                }
                continue;
            }
            // 公式
            if (mgtd.getP30F0701() == ConsDat.MGTD_FORMULA)
            {
                String t = mgtd.getP30F0711();
                if (Char.isValidate(t))
                {
                    try
                    {
                        t = t.replaceAll("(n|nian|year)", "" + cal.get(Calendar.YEAR));
                        t = t.replaceAll("(y|yue|month)", "" + (cal.get(Calendar.MONTH) + 1));
                        t = t.replaceAll("(r|ri|day)", "" + cal.get(Calendar.DAY_OF_MONTH));
                        t = t.replaceAll("(s|shi|hour)", "" + cal.get(Calendar.HOUR_OF_DAY));
                        t = t.replaceAll("(f|fen|minute)", "" + cal.get(Calendar.MINUTE));
                        t = t.replaceAll("(m|miao|second)", "" + cal.get(Calendar.SECOND));
                        t = t.replaceAll("(z|zhou|date)", "" + (cal.get(Calendar.DAY_OF_WEEK) - 1));
                        double d = new Symbols().eval("");
                        if (d < 0.000001 || d > 0.000001)
                        {
                            mgtdList.add(mgtd);
                        }
                    }
                    catch (Exception exp)
                    {
                        Logs.exception(exp);
                    }
                }
                continue;
            }
            // 按秒重复
            if (mgtd.getP30F0701() == ConsDat.MGTD_CYCLE_BY_SECOND)
            {
                // long diff = (now - mgtd.getP30F070F()) / 1000;
                if ((now - mgtd.getP30F070F()) % (mgtd.getP30F0710() * 1000) == 0)
                {
                    mgtdList.add(mgtd);
                }
                continue;
            }
            // 按分重复
            if (mgtd.getP30F0701() == ConsDat.MGTD_CYCLE_BY_MINUTE)
            {
                // long diff = (now - mgtd.getP30F070F()) / 1000 / 60;
                if ((now - mgtd.getP30F070F()) % (mgtd.getP30F0710() * 60000) == 0)
                {
                    mgtdList.add(mgtd);
                }
                continue;
            }
            // 按时重复
            if (mgtd.getP30F0701() == ConsDat.MGTD_CYCLE_BY_HOUR)
            {
                // long diff = (now - mgtd.getP30F070F()) / 1000 / 60 / 60;
                if ((now - mgtd.getP30F070F()) % (mgtd.getP30F0710() * 3600000) == 0)
                {
                    mgtdList.add(mgtd);
                }
                continue;
            }
            // 按天重复
            if (mgtd.getP30F0701() == ConsDat.MGTD_CYCLE_BY_DAY)
            {
                // long diff = (now - mgtd.getP30F070F()) / 1000 / 60 / 60 / 24;
                if ((now - mgtd.getP30F070F()) % (mgtd.getP30F0710() * 86400000) == 0)
                {
                    mgtdList.add(mgtd);
                }
                continue;
            }
            // 按周重复
            if (mgtd.getP30F0701() == ConsDat.MGTD_CYCLE_BY_WEEK)
            {
                // long diff = (now - mgtd.getP30F070F()) / 1000 / 60 / 60 / 24 / 7;
                if ((now - mgtd.getP30F070F()) % (mgtd.getP30F0710() * 604800000) == 0)
                {
                    mgtdList.add(mgtd);
                }
                continue;
            }
            // 按月重复
            if (mgtd.getP30F0701() == ConsDat.MGTD_CYCLE_BY_MONTH)
            {
                Calendar tmp = (Calendar) cal.clone();
                tmp.setTimeInMillis(mgtd.getP30F070F());
                int dif = (cal.get(Calendar.YEAR) - tmp.get(Calendar.YEAR)) * 12 + cal.get(Calendar.MONTH) - tmp.get(Calendar.MONTH);
                if (dif % mgtd.getP30F0710() == 0)
                {
                    mgtdList.add(mgtd);
                }
                continue;
            }
            // 按年重复
            if (mgtd.getP30F0701() == ConsDat.MGTD_CYCLE_BY_YEAR)
            {
                Calendar tmp = (Calendar) cal.clone();
                tmp.setTimeInMillis(mgtd.getP30F070F());
                int dif = cal.get(Calendar.YEAR) - tmp.get(Calendar.YEAR);
                if (dif % mgtd.getP30F0710() == 0)
                {
                    mgtdList.add(mgtd);
                }
                continue;
            }
        }
        return true;
    }

    public void process(Timestamp start, Timestamp end, boolean schedule)
    {
        // 计数器
        if (schedule)
        {
            counter += 1;
            if (counter < userMdl.getHintInt())
            {
                return;
            }
        }

        counter = 0;
        if (DBAccess.locked)
        {
            return;
        }

        hintList.clear();
        DBA4000.findHintList(userMdl, start, end, hintList);
    }

    public List<Mgtd> getUnread()
    {
        return hintList;
    }

    public int getUnreadCount()
    {
        return hintList.size();
    }

    public int getReadedCount()
    {
        return 0;
    }

    public Mkey getHintData()
    {
        return null;
    }
}
