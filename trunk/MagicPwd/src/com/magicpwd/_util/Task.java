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
import com.magicpwd._comn.TaskInfo;
import java.awt.event.ActionEvent;

/**
 *
 * @author Amon
 */
public class Task
{

    private static java.util.HashMap<TaskInfo, IBackCall<TaskInfo>> tasks;
    private static javax.swing.Timer timer;
    private static long lastTime;

    public static void registerAction(TaskInfo item, IBackCall<TaskInfo> backCall)
    {
        if (tasks == null)
        {
            tasks = new java.util.HashMap<TaskInfo, IBackCall<TaskInfo>>();
            timer = new javax.swing.Timer(500, new java.awt.event.ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    taskActionPerformed(e);
                }
            });
            timer.setInitialDelay(3000);
        }

        if (!tasks.containsKey(item))
        {
            tasks.put(item, backCall);
        }

        if (!timer.isRunning())
        {
            timer.start();
        }
    }

    public static void removeAction(TaskInfo info)
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

    public static IBackCall getAction(String taskName)
    {
        for (TaskInfo info : tasks.keySet())
        {
            if (info.getTaskName().equals(taskName))
            {
                return tasks.get(info);
            }
        }
        return null;
    }

    public static IBackCall getAction(TaskInfo info)
    {
        return tasks.get(info);
    }

    public static boolean reActive(String key, int interval)
    {
        if (key == null)
        {
            return false;
        }

        for (TaskInfo info : tasks.keySet())
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

    public static boolean deActive(String key)
    {
        if (key == null)
        {
            return false;
        }

        for (TaskInfo info : tasks.keySet())
        {
            if (info.getTaskName().equals(key))
            {
                info.setCounter(info.getInterval() + 1);
                return true;
            }
        }

        return false;
    }

    public static boolean reActive(String key, int initiate, int interval)
    {
        if (key == null)
        {
            return false;
        }

        for (TaskInfo info : tasks.keySet())
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

    private static void taskActionPerformed(java.awt.event.ActionEvent e)
    {
        long t = System.currentTimeMillis();
        if (t - lastTime < 1000)
        {
            return;
        }
        lastTime = t;
        for (final TaskInfo info : tasks.keySet())
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
}
