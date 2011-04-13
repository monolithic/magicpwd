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
package com.magicpwd._util;

import com.magicpwd.__i.IBackCall;
import com.magicpwd._comn.Task;
import com.magicpwd._comn.mpwd.Mgtd;
import com.magicpwd._cons.ConsDat;
import org.javia.arity.Symbols;

/**
 *
 * @author Amon
 */
public class Time implements java.awt.event.ActionListener
{

    private static Time taskTime;
    private java.util.HashMap<Task, IBackCall<String, Task>> tasks;
    private javax.swing.Timer timer;
    private long lastTime;

    private Time()
    {
        tasks = new java.util.HashMap<Task, IBackCall<String, Task>>();
        timer = new javax.swing.Timer(500, this);
        timer.setInitialDelay(3000);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        taskActionPerformed(e);
    }

    public static Time getInstance()
    {
        if (taskTime == null)
        {
            taskTime = new Time();
        }
        return taskTime;
    }

    public void registerAction(Task item, IBackCall<String, Task> backCall)
    {
        if (!tasks.containsKey(item))
        {
            tasks.put(item, backCall);
        }

        if (!timer.isRunning())
        {
            timer.start();
        }
    }

    public void removeAction(Task info)
    {
        tasks.remove(info);
        if (tasks.size() < 1)
        {
            if (timer.isRunning())
            {
                timer.stop();
            }
        }
    }

    public IBackCall<String, Task> getAction(String taskName)
    {
        for (Task info : tasks.keySet())
        {
            if (info.getTaskName().equals(taskName))
            {
                return tasks.get(info);
            }
        }
        return null;
    }

    public IBackCall getAction(Task info)
    {
        return tasks.get(info);
    }

    public boolean reActive(String key, int interval)
    {
        if (key == null)
        {
            return false;
        }

        for (Task info : tasks.keySet())
        {
            if (info.getTaskName().equals(key))
            {
                info.setCounter(0);
                info.setInterval(interval);
                return true;
            }
        }

        return false;
    }

    public boolean deActive(String key)
    {
        if (key == null)
        {
            return false;
        }

        for (Task info : tasks.keySet())
        {
            if (info.getTaskName().equals(key))
            {
                info.setCounter(info.getInterval() + 1);
                return true;
            }
        }

        return false;
    }

    public boolean reActive(String key, int initiate, int interval)
    {
        if (key == null)
        {
            return false;
        }

        for (Task info : tasks.keySet())
        {
            if (info.getTaskName().equals(key))
            {
                info.setCounter(0);
                info.setInitiate(initiate);
                info.setInterval(interval);
                return true;
            }
        }

        return false;
    }

    private void taskActionPerformed(java.awt.event.ActionEvent e)
    {
        long t = System.currentTimeMillis();
        if (t - lastTime < 1000)
        {
            return;
        }
        lastTime = t;
        for (final Task info : tasks.keySet())
        {
            if (info.getInitiate() > 0)
            {
                info.addInitiate(-1);
                continue;
            }

            if (info.getCounter() > info.getInterval())
            {
                continue;
            }

            info.addCounter(1);
            if (info.getCounter() == info.getInterval())
            {
                new Thread()
                {

                    @Override
                    public void run()
                    {
                        tasks.get(info).callBack(null, info);
                    }
                }.start();
            }
        }
    }

    public static boolean isOnTime(Mgtd mgtd)
    {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        long now = cal.getTimeInMillis();

        if (mgtd.getP30F0701() == ConsDat.MGTD_FIXED)
        {
            long dif = (now - mgtd.getP30F070F()) / 1000;
            return dif < 2;
        }
        // 公式
        if (mgtd.getP30F0701() == ConsDat.MGTD_FORMULA)
        {
            String tmp = mgtd.getP30F0711();
            if (Char.isValidate(tmp))
            {
                try
                {
                    tmp = tmp.replaceAll("(n|nian|year)", "" + cal.get(java.util.Calendar.YEAR));
                    tmp = tmp.replaceAll("(y|yue|month)", "" + (cal.get(java.util.Calendar.MONTH) + 1));
                    tmp = tmp.replaceAll("(r|ri|day)", "" + cal.get(java.util.Calendar.DAY_OF_MONTH));
                    tmp = tmp.replaceAll("(s|shi|hour)", "" + cal.get(java.util.Calendar.HOUR_OF_DAY));
                    tmp = tmp.replaceAll("(f|fen|minute)", "" + cal.get(java.util.Calendar.MINUTE));
                    tmp = tmp.replaceAll("(m|miao|second)", "" + cal.get(java.util.Calendar.SECOND));
                    tmp = tmp.replaceAll("(z|zhou|date)", "" + (cal.get(java.util.Calendar.DAY_OF_WEEK) - 1));
                    double d = new Symbols().eval(tmp);
                    return (d < 0.000001 && d > 0.000001);
                }
                catch (Exception exp)
                {
                    Logs.exception(exp);
                }
            }
            return false;
        }
        // 按秒重复
        if (mgtd.getP30F0701() == ConsDat.MGTD_CYCLE_BY_SECOND)
        {
            // long diff = (now - mgtd.getP30F070F()) / 1000;
            return (now - mgtd.getP30F070F()) % (mgtd.getP30F0710() * 1000) == 0;
        }
        // 按分重复
        if (mgtd.getP30F0701() == ConsDat.MGTD_CYCLE_BY_MINUTE)
        {
            // long diff = (now - mgtd.getP30F070F()) / 1000 / 60;
            return (now - mgtd.getP30F070F()) % (mgtd.getP30F0710() * 60000) == 0;
        }
        // 按时重复
        if (mgtd.getP30F0701() == ConsDat.MGTD_CYCLE_BY_HOUR)
        {
            // long diff = (now - mgtd.getP30F070F()) / 1000 / 60 / 60;
            return (now - mgtd.getP30F070F()) % (mgtd.getP30F0710() * 3600000) == 0;
        }
        // 按天重复
        if (mgtd.getP30F0701() == ConsDat.MGTD_CYCLE_BY_DAY)
        {
            // long diff = (now - mgtd.getP30F070F()) / 1000 / 60 / 60 / 24;
            return (now - mgtd.getP30F070F()) % (mgtd.getP30F0710() * 86400000) == 0;
        }
        // 按周重复
        if (mgtd.getP30F0701() == ConsDat.MGTD_CYCLE_BY_WEEK)
        {
            // long diff = (now - mgtd.getP30F070F()) / 1000 / 60 / 60 / 24 / 7;
            return (now - mgtd.getP30F070F()) % (mgtd.getP30F0710() * 604800000) == 0;
        }
        // 按月重复
        if (mgtd.getP30F0701() == ConsDat.MGTD_CYCLE_BY_MONTH)
        {
            java.util.Calendar tmp = (java.util.Calendar) cal.clone();
            tmp.setTimeInMillis(mgtd.getP30F070F());
            int dif = (cal.get(java.util.Calendar.YEAR) - tmp.get(java.util.Calendar.YEAR)) * 12 + cal.get(java.util.Calendar.MONTH) - tmp.get(java.util.Calendar.MONTH);
            return dif % mgtd.getP30F0710() == 0;
        }
        // 按年重复
        if (mgtd.getP30F0701() == ConsDat.MGTD_CYCLE_BY_YEAR)
        {
            java.util.Calendar tmp = (java.util.Calendar) cal.clone();
            tmp.setTimeInMillis(mgtd.getP30F070F());
            int dif = cal.get(java.util.Calendar.YEAR) - tmp.get(java.util.Calendar.YEAR);
            return dif % mgtd.getP30F0710() == 0;
        }
        return false;
    }
}
