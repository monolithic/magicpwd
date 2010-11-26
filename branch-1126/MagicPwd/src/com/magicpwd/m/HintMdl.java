package com.magicpwd.m;

import com.magicpwd._comn.Keys;
import java.util.ArrayList;
import java.util.List;

import com.magicpwd.d.DBA3000;
import com.magicpwd.v.TrayPtn;
import java.sql.Timestamp;

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

    private static List<Keys> hintList;
    private int counter;
    private UserMdl userMdl;

    public HintMdl(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }

    public void initData()
    {
        hintList = new ArrayList<Keys>();
        counter = userMdl.getHintInt();
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
        if (TrayPtn.isDbLocked())
        {
            return;
        }

        hintList.clear();
        DBA3000.findHintList(userMdl, start, end, hintList);
    }

    public List<Keys> getUnread()
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

    public Keys getHintData()
    {
        return null;
    }
}
