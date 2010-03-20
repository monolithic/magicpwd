package com.magicpwd.m;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.magicpwd._comn.S1SD;
import com.magicpwd._face.IBackCall;

public class TimeOut implements Runnable
{

    private IBackCall callBack;
    private static List<S1SD> noteList = new ArrayList<S1SD>();
    private static boolean stopWork;

    public TimeOut()
    {
        readNote();
    }

    public static boolean isStopWork()
    {
        return stopWork;
    }

    public static void setStopWork(boolean stopWork)
    {
        TimeOut.stopWork = stopWork;
    }

    public IBackCall getCallBack()
    {
        return callBack;
    }

    public void setCallBack(IBackCall callBack)
    {
        this.callBack = callBack;
    }

    @Override
    public void run()
    {
        while (!stopWork)
        {
            Calendar cal1 = Calendar.getInstance();
            if (cal1.get(Calendar.MINUTE) < 2)
            {
                readNote();
            }

            // 检测提示时间
            Calendar cal2;
            int j = noteList.size() - 1;
            while (j >= 0)
            {
                cal2 = noteList.get(j).getV3();
                if (cal2 == null || cal2.get(Calendar.MINUTE) < cal1.get(Calendar.MINUTE))
                {
                    noteList.remove(j--);
                    continue;
                }
                if (cal2.get(Calendar.MINUTE) > cal1.get(Calendar.MINUTE))
                {
                    break;
                }

                if (callBack != null)
                {
                    callBack.callBack(noteList.remove(j--), null);
                }
            }

            try
            {
                Thread.sleep(30000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void readNote()
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 59);
        noteList.clear();
        //DBA3000.findTimeNote(new Timestamp(cal.getTimeInMillis()), null, noteList);
    }
}
