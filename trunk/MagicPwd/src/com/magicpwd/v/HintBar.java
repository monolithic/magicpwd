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
import com.magicpwd._comn.Task;
import com.magicpwd._comn.mpwd.Mgtd;
import com.magicpwd._util.Char;
import com.magicpwd._util.Time;
import com.magicpwd.d.db.DBA4000;
import com.magicpwd.d.db.DBAccess;
import com.magicpwd.m.UserMdl;

/**
 * 主窗口状态提示工具条
 * @author Amon
 */
public class HintBar extends javax.swing.JPanel
{

    private UserMdl userMdl;
    private java.text.DateFormat dateTplt;
    private java.util.List<Mgtd> mgtdList;
    private java.util.List<Mgtd> hintList;
    private IBackCall<String, java.util.List<Mgtd>> backCall;
    private int counter;

    public HintBar(UserMdl userMdl)
    {
        this.userMdl = userMdl;
    }

    public void initView()
    {
        setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, getBackground().darker()));

        lb_HintLabel = new javax.swing.JLabel();
        lb_HintLabel.addMouseListener(new java.awt.event.MouseAdapter()
        {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lb_HintLabelMouseClicked(evt);
            }
        });

//        lb_InfoLabel = new javax.swing.JLabel();
//        lb_InfoLabel.setVisible(false);

        lb_DateLabel = new javax.swing.JLabel();
        lb_DateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addComponent(lb_HintLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, Short.MAX_VALUE);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
//        hsg.addComponent(lb_InfoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE);
//        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(lb_DateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE);
        hsg.addContainerGap();
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        hpg.addComponent(lb_DateLabel);
//        hpg.addComponent(lb_InfoLabel);
        hpg.addComponent(lb_HintLabel);
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addGap(3);
        vsg.addGroup(hpg);
        vsg.addGap(3);
        layout.setVerticalGroup(vsg);
    }

    public void initLang()
    {
//        lb_InfoLabel.setText("请选择口令类别！");
        lb_HintLabel.setText("数据处理中……");
        lb_DateLabel.setText("");
    }

    public void initData()
    {
        dateTplt = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.FULL, java.text.DateFormat.MEDIUM);

        mgtdList = new java.util.ArrayList<Mgtd>();
        hintList = new java.util.ArrayList<Mgtd>();
        counter = userMdl.getHintInt();

        Time.getInstance().registerAction(new Task(0, 1, "mexp-hint", ""), new IBackCall<String, Task>()
        {

            @Override
            public boolean callBack(String options, Task object)
            {
                object.setCounter(0);
                return showNote(false);
            }
        });
    }

    private void lb_HintLabelMouseClicked(java.awt.event.MouseEvent evt)
    {
        if (backCall != null)
        {
            backCall.callBack("hint", hintList);
        }
    }

    public boolean showNote(boolean forced)
    {
        showTime();

        // 读取数据信息
        if (counter >= userMdl.getHintInt() || forced)
        {
            if (DBAccess.locked)
            {
                return false;
            }
            mgtdList.clear();
            DBA4000.findHintList(userMdl, mgtdList);
            counter = 0;
        }

        // 到期提示判断
        hintList.clear();
        java.util.Calendar cal = java.util.Calendar.getInstance();
        for (Mgtd mgtd : mgtdList)
        {
            if (Time.isOnTime(cal, mgtd))
            {
                hintList.add(mgtd);
            }
        }

        showHint();
        return true;
    }

    /**
     * 显示日期信息
     */
    public void showTime()
    {
        String text = dateTplt.format(new java.util.Date());
        lb_DateLabel.setText(text);
        lb_DateLabel.setToolTipText(text);
    }

    /**
     * 显示提示信息
     * @param hint
     */
    public void showInfo(String hint)
    {
        lb_InfoLabel.setText(hint);
    }

    public void showHint()
    {
        int size = hintList.size();
        if (size > 0)
        {
            lb_HintLabel.setText(Char.format("您有 {0} 条提醒数据！", Integer.toString(size)));
            lb_HintLabel.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
            lb_HintLabel.setToolTipText("点击查看详细信息！");
        }
        else
        {
            lb_HintLabel.setText("您目前没有需要提醒的数据！");
            lb_HintLabel.setCursor(java.awt.Cursor.getDefaultCursor());
            lb_HintLabel.setToolTipText(null);
        }
    }

    /**
     * @return the backCall
     */
    public IBackCall<String, java.util.List<Mgtd>> getBackCall()
    {
        return backCall;
    }

    /**
     * @param backCall the backCall to set
     */
    public void setBackCall(IBackCall<String, java.util.List<Mgtd>> backCall)
    {
        this.backCall = backCall;
    }
    private javax.swing.JLabel lb_InfoLabel;
    private javax.swing.JLabel lb_HintLabel;
    private javax.swing.JLabel lb_DateLabel;
}
