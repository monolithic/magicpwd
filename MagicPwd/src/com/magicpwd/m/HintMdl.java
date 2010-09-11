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
public class HintMdl
{

    private static List<Keys> hintList = new ArrayList<Keys>();
    private int counter = 999999999;//TODO:需要修改
    private UserMdl coreMdl;

    HintMdl(UserMdl coreMdl)
    {
        this.coreMdl = coreMdl;
    }

    public void process(Timestamp start, Timestamp end)
    {
        // 计数器
        counter += 1;
        if (counter < coreMdl.getUserCfg().getHintInt())
        {
            return;
        }
        counter = 0;
        if (TrayPtn.isDbLocked())
        {
            return;
        }

        hintList.clear();
        DBA3000.findHintList(start, end, hintList);
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
