/**
 * 
 */
package com.magicpwd.v;

import com.magicpwd._comn.Keys;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.magicpwd._comn.S1SD;
import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Util;
import com.magicpwd.c.InfoEvt;
import com.magicpwd.d.DBA3000;
import com.magicpwd.m.UserMdl;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.swing.Timer;

/**
 * @author amon
 */
public class InfoBar extends JPanel
{
    //private InfoEvt ie_InfoEvent;
    private int lastDays;
    private SimpleDateFormat dateTplt;
    private String[] weekName;
    private List<Keys> lp_PastNote;
    private Timer tm_TimeNote;

    public InfoBar()
    {
        lp_PastNote = new ArrayList<Keys>();
    }

    public void initView()
    {
        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, getBackground().darker()));
        lb_DateLabel = new javax.swing.JLabel();
        lb_InfoLabel = new javax.swing.JLabel();
        lb_InfoLabel.addMouseListener(new java.awt.event.MouseAdapter()
        {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lb_InfoLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap().addComponent(lb_InfoLabel,
                                                                              javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE).addPreferredGap(
                javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lb_DateLabel,
                                                                                 javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addGap(3).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(
                lb_DateLabel).addComponent(lb_InfoLabel)).addGap(3)));
    }

    public void initLang()
    {
        showNote();
    }

    public void initData()
    {
        if (tm_TimeNote == null)
        {
            tm_TimeNote = new Timer(600, new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    showNote();
                }
            });
            tm_TimeNote.setInitialDelay(5000);
        }
        if (!tm_TimeNote.isRunning())
        {
            tm_TimeNote.start();
        }
    }

    public void setActionEvent(InfoEvt event)
    {
        //ie_InfoEvent = event;
    }

    private void lb_InfoLabelMouseClicked(java.awt.event.MouseEvent evt)
    {
        int size = lp_PastNote.size();
        if (size > 0)
        {
            UserMdl.getListMdl().findName(lp_PastNote.remove(--size).getP30F0107());
            showNote();
        }
    }

    private void showNote()
    {
        java.util.Calendar cal = java.util.Calendar.getInstance();

        // 显示日期信息
        String text;
        int days = cal.get(Calendar.DAY_OF_MONTH);
        if (days == lastDays)
        {
            text = new StringBuffer(dateTplt.format(cal.getTime())).append(' ').append(weekName.length < days ? "未知" : weekName[days - 1]).toString();
            lb_DateLabel.setText(text);
            lb_DateLabel.setToolTipText(text);
            return;
        }

        // 初始化提醒数据
        lastDays = days;
        if (dateTplt == null)
        {
            dateTplt = new SimpleDateFormat(Lang.getLang(LangRes.P30F110E, "yyyy年MM月dd日 HH:mm:ss").trim());
            weekName = Lang.getLang(LangRes.P30F1110, "星期日,星期一,星期二,星期三,星期四,星期五,星期六").split(",");
        }

        days = cal.get(java.util.Calendar.DAY_OF_WEEK);

        // 显示日期信息
        text = weekName.length < days ? Lang.getLang(LangRes.P30F1111, "未知") : weekName[days - 1];
        text = new StringBuffer(dateTplt.format(cal.getTime())).append(' ').append(text).toString();
        lb_DateLabel.setText(text);
        lb_DateLabel.setToolTipText(text);

        // 读取数据信息
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Timestamp s = new Timestamp(cal.getTimeInMillis());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Timestamp e = new Timestamp(cal.getTimeInMillis());
        DBA3000.findTimeNote(s, e, lp_PastNote);

        int size = lp_PastNote.size();
        if (size > 0)
        {
            lb_InfoLabel.setText(Util.format("您有 {0} 条提醒数据！", "" + size));
            lb_InfoLabel.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
            lb_InfoLabel.setToolTipText("点击查看详细信息！");
        }
        else
        {
            lb_InfoLabel.setText("您目前没有需要提醒的数据！");
            lb_InfoLabel.setCursor(java.awt.Cursor.getDefaultCursor());
            lb_InfoLabel.setToolTipText(null);
        }
    }
    private javax.swing.JLabel lb_InfoLabel;
    private javax.swing.JLabel lb_DateLabel;
}
