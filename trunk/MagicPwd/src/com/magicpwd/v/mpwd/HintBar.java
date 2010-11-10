/**
 * 
 */
package com.magicpwd.v.mpwd;

import com.magicpwd.__i.IBackCall;
import com.magicpwd._comn.TaskInfo;
import com.magicpwd._util.Char;
import com.magicpwd._util.Task;
import java.util.EventListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.magicpwd.m.HintMdl;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;

/**
 * 主窗口状态提示工具条
 * @author Amon
 */
public class HintBar extends JPanel
{

    private DateFormat dateTplt;
    private MainPtn mainPtn;
    private HintMdl hintMdl;

    public HintBar(MainPtn mainPtn, HintMdl hintMdl)
    {
        this.mainPtn = mainPtn;
        this.hintMdl = hintMdl;
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
        Task.registerAction(new TaskInfo(0, 5, "mpwd-hint", ""), new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, EventListener event, String... params)
            {
                showNote();
                return true;
            }
        });
    }

    private void lb_InfoLabelMouseClicked(java.awt.event.MouseEvent evt)
    {
        if (hintMdl.getUnreadCount() > 0)
        {
            java.util.Calendar c = java.util.Calendar.getInstance();
            java.util.Date s = c.getTime();
            c.add(java.util.Calendar.DAY_OF_MONTH, 1);
            java.util.Date t = c.getTime();
            mainPtn.getListMdl().listTask(s, t);
        }
    }

    private void showNote()
    {
        java.util.Calendar cal = java.util.Calendar.getInstance();

        // 显示日期信息
        String text = dateTplt.format(cal.getTime());
        lb_DateLabel.setText(text);
        lb_DateLabel.setToolTipText(text);

        if (hintMdl.getHintData() != null)
        {
            //ie_InfoEvent.hintDataActionPerformed(null);
        }

        // 读取数据信息
        Timestamp s = new Timestamp(cal.getTimeInMillis());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Timestamp e = new Timestamp(cal.getTimeInMillis());
        hintMdl.process(s, e);

        int size = hintMdl.getUnreadCount();
        if (size > 0)
        {
            lb_InfoLabel.setText(Char.format("您有 {0} 条提醒数据！", Integer.toString(size)));
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
