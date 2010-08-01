/**
 * 
 */
package com.magicpwd.v;

import com.magicpwd._comn.Keys;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.magicpwd._util.Util;
import com.magicpwd.c.InfoEvt;
import com.magicpwd.m.UserMdl;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;
import javax.swing.Timer;

/**
 * @author Amon
 */
public class HintBar extends JPanel
{

    private InfoEvt ie_InfoEvent;
    private DateFormat dateTplt;
    private Timer tm_TimeNote;

    public HintBar()
    {
    }

    public void initView()
    {
        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, getBackground().darker()));
        lb_DateLabel = new javax.swing.JLabel();
        lb_DateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
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
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addComponent(lb_InfoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(lb_DateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg.addContainerGap();
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        hpg.addComponent(lb_DateLabel);
        hpg.addComponent(lb_InfoLabel);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addGap(3);
        vsg.addGroup(hpg);
        vsg.addGap(3);
        layout.setVerticalGroup(vsg);
    }

    public void initLang()
    {
        dateTplt = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT);

        lb_InfoLabel.setText("数据处理中……");
    }

    public void initData()
    {
        if (tm_TimeNote == null)
        {
            tm_TimeNote = new Timer(600, new ActionListener()
            {

                @Override
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

    public void setInfoEvent(InfoEvt event)
    {
        ie_InfoEvent = event;
    }

    private void lb_InfoLabelMouseClicked(java.awt.event.MouseEvent evt)
    {
        if (UserMdl.getHintMdl().getUnreadCount() > 0)
        {
            UserMdl.getListMdl().clear();
            for (Keys keys : UserMdl.getHintMdl().getUnread())
            {
                UserMdl.getListMdl().wAppend(keys);
            }
            showNote();
        }
    }

    private void showNote()
    {
        java.util.Calendar cal = java.util.Calendar.getInstance();

        // 显示日期信息
        String text = dateTplt.format(cal.getTime());
        lb_DateLabel.setText(text);
        lb_DateLabel.setToolTipText(text);

        if (UserMdl.getHintMdl().getHintData() != null)
        {
            ie_InfoEvent.hintDataActionPerformed(null);
        }

        // 读取数据信息
        Timestamp s = new Timestamp(cal.getTimeInMillis());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Timestamp e = new Timestamp(cal.getTimeInMillis());
        UserMdl.getHintMdl().process(s, e);

        int size = UserMdl.getHintMdl().getUnreadCount();
        if (size > 0)
        {
            lb_InfoLabel.setText(Util.format("您有 {0} 条提醒数据！", Integer.toString(size)));
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
