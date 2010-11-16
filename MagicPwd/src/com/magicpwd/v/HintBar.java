/**
 * 
 */
package com.magicpwd.v;

import com.magicpwd.__i.IBackCall;
import com.magicpwd._comn.TaskInfo;
import com.magicpwd._util.Char;
import com.magicpwd._util.Task;
import com.magicpwd.m.HintMdl;
import com.magicpwd.m.UserMdl;

/**
 * 主窗口状态提示工具条
 * @author Amon
 */
public class HintBar extends javax.swing.JPanel
{

    private IBackCall backCall;
    private UserMdl userMdl;
    private HintMdl hintMdl;
    private java.text.DateFormat dateTplt;

    public HintBar(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }

    public void initView()
    {
        setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, getBackground().darker()));
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
        dateTplt = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.FULL, java.text.DateFormat.SHORT);

        lb_InfoLabel.setText("数据处理中……");
    }

    public void initData()
    {
        hintMdl = new HintMdl(userMdl);
        hintMdl.initData();

        Task.registerAction(new TaskInfo(0, 1, "mpwd-hint", ""), new IBackCall()
        {

            @Override
            public boolean callBack(Object sender, java.util.EventListener event, String... params)
            {
                return showHint(true);
            }
        });
    }

    private void lb_InfoLabelMouseClicked(java.awt.event.MouseEvent evt)
    {
        if (hintMdl.getUnreadCount() > 0)
        {
            if (backCall != null)
            {
                backCall.callBack(this, null);
            }
        }
    }

    public boolean showHint(boolean schedule)
    {
        java.util.Calendar cal = java.util.Calendar.getInstance();

        // 显示日期信息
        String text = dateTplt.format(cal.getTime());
        lb_DateLabel.setText(text);
        lb_DateLabel.setToolTipText(text);

        // 读取数据信息
        java.sql.Timestamp s = new java.sql.Timestamp(cal.getTimeInMillis());
        cal.add(java.util.Calendar.DAY_OF_MONTH, 1);
        java.sql.Timestamp e = new java.sql.Timestamp(cal.getTimeInMillis());
        hintMdl.process(s, e, schedule);

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
        return true;
    }
    private javax.swing.JLabel lb_InfoLabel;
    private javax.swing.JLabel lb_DateLabel;

    /**
     * @return the backCall
     */
    public IBackCall getBackCall()
    {
        return backCall;
    }

    /**
     * @param backCall the backCall to set
     */
    public void setBackCall(IBackCall backCall)
    {
        this.backCall = backCall;
    }
}
