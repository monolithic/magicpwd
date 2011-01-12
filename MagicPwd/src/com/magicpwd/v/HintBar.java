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

    private IBackCall<String> backCall;
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
        lb_HintLabel = new javax.swing.JLabel();
//        lb_HintLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lb_InfoLabel = new javax.swing.JLabel();
//        lb_InfoLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lb_InfoLabel.addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lb_InfoLabelMouseClicked(evt);
            }
        });

        lb_DateLabel = new javax.swing.JLabel();
//        lb_DateLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lb_DateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addComponent(lb_HintLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(lb_InfoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(lb_DateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg.addContainerGap();
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        hpg.addComponent(lb_DateLabel);
        hpg.addComponent(lb_InfoLabel);
        hpg.addComponent(lb_HintLabel);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addGap(3);
        vsg.addGroup(hpg);
        vsg.addGap(3);
        layout.setVerticalGroup(vsg);
    }

    public void initLang()
    {
        dateTplt = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.FULL, java.text.DateFormat.SHORT);

        lb_HintLabel.setText("请选择口令类别！");
        lb_InfoLabel.setText("数据处理中……");
        lb_DateLabel.setText("");
    }

    public void initData()
    {
        hintMdl = new HintMdl(userMdl);
        hintMdl.initData();

        Task.registerAction(new TaskInfo(0, 1, "mpwd-hint", ""), new IBackCall<TaskInfo>()
        {

            @Override
            public boolean callBack(String options, TaskInfo object)
            {
                return showInfo(true);
            }
        });
    }

    private void lb_InfoLabelMouseClicked(java.awt.event.MouseEvent evt)
    {
        if (hintMdl.getUnreadCount() > 0)
        {
            if (backCall != null)
            {
                backCall.callBack("", null);
            }
        }
    }

    public void showHint(String hint)
    {
        lb_HintLabel.setText(hint);
    }

    public boolean showInfo(boolean schedule)
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

    /**
     * @return the backCall
     */
    public IBackCall<String> getBackCall()
    {
        return backCall;
    }

    /**
     * @param backCall the backCall to set
     */
    public void setBackCall(IBackCall<String> backCall)
    {
        this.backCall = backCall;
    }
    private javax.swing.JLabel lb_HintLabel;
    private javax.swing.JLabel lb_InfoLabel;
    private javax.swing.JLabel lb_DateLabel;
}
