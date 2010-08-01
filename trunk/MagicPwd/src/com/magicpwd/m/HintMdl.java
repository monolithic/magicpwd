package com.magicpwd.m;

import com.magicpwd._comn.Keys;
import java.util.ArrayList;
import java.util.List;

import com.magicpwd.d.DBA3000;
import com.magicpwd.v.TrayPtn;
import java.sql.Timestamp;

/**
 * 过期提醒数据模型
 * @author yaoshangwen
 */
public class HintMdl
{

    private static List<Keys> hintList = new ArrayList<Keys>();
    private int counter;
    private long ahead;

    public HintMdl()
    {
    }

    public void process(Timestamp start, Timestamp end)
    {
        // 计数器
        if (counter < UserMdl.getUserCfg().getBackNum())
        {
            counter += 1;
            return;
        }
        if (TrayPtn.isDbLocked())
        {
            return;
        }

        DBA3000.findHintList(start, end, hintList);
    }

    public int getUnreadCount()
    {
        return hintList.size();
    }

    public int getReadedCount()
    {
        return 0;
    }

    public Keys getHintData()
    {
        return null;
    }
}
