/*
 *  Copyright (C) 2011 Amon
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
package com.magicpwd._comp.date;

import com.magicpwd.__i.IBackCall;
import com.magicpwd._util.Date;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;

class DatePanel extends javax.swing.JPanel implements java.awt.event.ActionListener, javax.swing.MenuElement
{

    protected static final ImageIcon bgIcon = new ImageIcon("img" + java.io.File.separator + "calendar.png");
    private int curY;
    private int curM;
    private int curD;
    private int curW;
    private int selY;
    private int selM;
    private int selD;
    private int selW;
    private int SESSION_DATE = 0;
    private int SESSION_DAY = 0;
    private java.util.Calendar currentDate;
    private javax.swing.SpinnerNumberModel sm_DateY;
    private javax.swing.DefaultComboBoxModel cm_DateM;
    private IBackCall<java.util.Calendar> backCall;
    private boolean popup = true;

    DatePanel()
    {
    }

    public void initView()
    {
        initUserView();
        initDateView();
        initTimeView();

        this.setLayout(new java.awt.BorderLayout());
        this.add(pl_User, java.awt.BorderLayout.NORTH);
        this.add(pl_Date, java.awt.BorderLayout.CENTER);
        this.add(pl_Time, java.awt.BorderLayout.SOUTH);
    }

    private void initUserView()
    {
        pl_User = new javax.swing.JPanel();

        bt_PrevY = new javax.swing.JButton();
        bt_PrevY.setBorder(null);

        Icon prevIcon = new ImageIcon("img" + java.io.File.separator + "prev1.png");
        Icon prevIcon1 = new ImageIcon("img" + java.io.File.separator + "prev.png");
        bt_PrevM = new javax.swing.JButton(prevIcon);
        bt_PrevM.addActionListener(this);
        bt_PrevM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_PrevM.setRolloverEnabled(true);
        bt_PrevM.setPreferredSize(new java.awt.Dimension(prevIcon.getIconWidth(), prevIcon.getIconHeight()));
        bt_PrevM.setBorder(null);
        bt_PrevM.setRolloverIcon(prevIcon1);
        bt_PrevM.setPressedIcon(prevIcon1);

        Icon nextIcon = new ImageIcon("img" + java.io.File.separator + "next1.png");
        Icon nextIcon1 = new ImageIcon("img" + java.io.File.separator + "next.png");
        bt_NextM = new javax.swing.JButton(nextIcon);
        bt_NextM.addActionListener(this);
        bt_NextM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_NextM.setRolloverEnabled(true);
        bt_NextM.setPreferredSize(new java.awt.Dimension(nextIcon.getIconWidth(), nextIcon.getIconHeight()));
        bt_NextM.setBorder(null);
        bt_NextM.setRolloverIcon(nextIcon1);
        bt_NextM.setPressedIcon(nextIcon1);

        bt_NextY = new javax.swing.JButton();
        bt_NextY.setBorder(null);

        lb_Date = new javax.swing.JLabel();
        sp_DateY = new javax.swing.JSpinner();

        cb_DateM = new javax.swing.JComboBox();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_User);
        pl_User.setLayout(layout);

        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
//        hsg.addContainerGap();
        hsg.addComponent(bt_PrevY);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(bt_PrevM);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        if (popup)
        {
            hsg.addComponent(lb_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        }
        else
        {
            hsg.addComponent(sp_DateY, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE);
            hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
            hsg.addComponent(cb_DateM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        }
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(bt_NextM);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(bt_NextY);
//        hsg.addContainerGap();
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(hsg));

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER);
        vpg.addComponent(bt_PrevY);
        vpg.addComponent(bt_PrevM);
        vpg.addComponent(bt_NextY);
        vpg.addComponent(bt_NextM);
        if (popup)
        {
            vpg.addComponent(lb_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        }
        else
        {
            vpg.addComponent(cb_DateM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
            vpg.addComponent(sp_DateY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        }
        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
//        vsg.addContainerGap();
        vsg.addGroup(vpg);
//        vsg.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(vsg));
    }

    private void initDateView()
    {
        pl_Date = new javax.swing.JPanel();
        pl_Date.setLayout(new java.awt.GridLayout(7, 7));

        heads = new HeadLabel[7];
        HeadLabel hl;
        for (int i = 0, j = heads.length; i < j; i += 1)
        {
            hl = new HeadLabel("");
            pl_Date.add(hl);
            heads[i] = hl;
        }

        java.awt.event.MouseListener listener = new java.awt.event.MouseListener()
        {

            @Override
            public void mouseClicked(MouseEvent e)
            {
                TextLabel tl = (TextLabel) e.getSource();
                readDate(tl.getText());
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
//                STATE = 1;
                TextLabel tl = (TextLabel) e.getSource();
                tl.setForeground(java.awt.Color.WHITE);
                tl.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
//                STATE = 0;
                TextLabel tl = (TextLabel) e.getSource();
                tl.setForeground(java.awt.Color.decode("#0597DB"));
                tl.repaint();
            }
        };

        texts = new TextLabel[42];
        TextLabel tl;
        for (int i = 0, j = texts.length; i < j; i += 1)
        {
            tl = new TextLabel(TextLabel.CENTER, 0);
            tl.addMouseListener(listener);
            pl_Date.add(tl);
            texts[i] = tl;
        }
    }

    private void initTimeView()
    {
        pl_Time = new javax.swing.JPanel();
    }

    public void initDate()
    {
    }

    public void showDate()
    {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        curY = cal.get(java.util.Calendar.YEAR);
        curM = cal.get(java.util.Calendar.MONTH) + 1;
        curD = cal.get(java.util.Calendar.DAY_OF_MONTH);
        curW = cal.get(java.util.Calendar.DAY_OF_WEEK);

        if (currentDate != null)
        {
            selY = currentDate.get(java.util.Calendar.YEAR);
            selM = currentDate.get(java.util.Calendar.MONTH) + 1;
            selD = currentDate.get(java.util.Calendar.DAY_OF_MONTH);
            selW = currentDate.get(java.util.Calendar.DAY_OF_WEEK);
        }
        else
        {
            currentDate = cal;
            selY = curY;
            selM = curM;
            selD = curD;
            selW = curW;
        }

        if (popup)
        {
        }
        else
        {
            sm_DateY = new javax.swing.SpinnerNumberModel();
            sm_DateY.setMinimum(1);
            sm_DateY.setValue(selY);
            sp_DateY.setModel(sm_DateY);

            cm_DateM = new javax.swing.DefaultComboBoxModel();
            cm_DateM.addElement("1");
            cm_DateM.addElement("2");
            cm_DateM.addElement("3");
            cm_DateM.addElement("4");
            cm_DateM.addElement("5");
            cm_DateM.addElement("6");
            cm_DateM.addElement("7");
            cm_DateM.addElement("8");
            cm_DateM.addElement("9");
            cm_DateM.addElement("10");
            cm_DateM.addElement("11");
            cm_DateM.addElement("12");
            cb_DateM.setModel(cm_DateM);
            cb_DateM.setSelectedIndex(selM - 1);
        }

        for (int i = 0, j = heads.length; i < j; i++)
        {
            heads[i].setText(i + 1 + "");
        }
        int maxDate = Date.getDaysOfMonth(selY, selM);
        int firstDay = Date.getFirstDayOfMonth(curW, curD);
        int d = 1;
        for (int i = 0, j = texts.length; i < j; i++)
        {
            if (firstDay <= i + 1 && d <= maxDate)
            {
                texts[i].setText(Integer.toString(d));
                texts[i].setEnabled(true);
                d++;
            }
            else
            {
                texts[i].setText("");
                texts[i].setEnabled(false);
            }
        }
    }

    public void initLang()
    {
        bt_PrevY.setText("<<");

        bt_PrevM.setText("<");

        bt_NextY.setText(">>");

        bt_NextM.setText(">");
    }

    @Override
    protected void paintComponent(java.awt.Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(bgIcon.getImage(), 0, 0, null);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        String cmd = e.getActionCommand();
        if ("prevY".equals(cmd))
        {
            int firstDay;
            if (SESSION_DATE == 0 || SESSION_DAY == 0)
            {
                firstDay = Date.getFirstDayOfMonth(selD, selW);
            }
            else
            {
                firstDay = Date.getFirstDayOfMonth(SESSION_DATE, SESSION_DAY);
            }
            selM--;
            if (selM == 0)
            {
                selM = 12;
                selY--;
            }
            sp_DateY.setValue(selY);
            SESSION_DATE = Date.getDaysOfMonth(selY, selM);
            SESSION_DAY = firstDay - 1 == 0 ? 7 : firstDay - 1;
//            loadMainPanel(true, SESSION_DATE, SESSION_DAY);
            return;
        }

        if ("prevM".equals(cmd))
        {
            int lastDay;
            if (SESSION_DATE == 0 || SESSION_DAY == 0)
            {
                lastDay = Date.getLastDayOfMonth(selD, selW, selY, selM);
            }
            else
            {
                lastDay = Date.getLastDayOfMonth(SESSION_DATE, SESSION_DAY, selY, selM);
            }
            selM++;
            if (selM > 12)
            {
                selM = 1;
                selY++;
            }
            sp_DateY.setValue(selY);
            SESSION_DATE = 1;
            SESSION_DAY = lastDay + 1 > 7 ? 1 : lastDay + 1;
//            loadMainPanel(true, SESSION_DATE, SESSION_DAY);
            return;
        }

        if ("nextM".equals(cmd))
        {
            return;
        }

        if ("nextY".equals(cmd))
        {
            return;
        }
    }

    public void setBackCall(IBackCall<java.util.Calendar> backCall)
    {
        this.backCall = backCall;
    }

    private void readDate(String day)
    {
        if (!java.util.regex.Pattern.matches("^\\d+$", day))
        {
            return;
        }

        selD = Integer.parseInt(day);
        if (selD < 1 || selD > 31)
        {
            return;
        }

        currentDate.set(java.util.Calendar.YEAR, selY);
        currentDate.set(java.util.Calendar.MONTH, selM - 1);
        currentDate.set(java.util.Calendar.DAY_OF_MONTH, selD);

        if (backCall != null)
        {
            backCall.callBack("", currentDate);
        }
    }

    @Override
    public void processMouseEvent(java.awt.event.MouseEvent event, javax.swing.MenuElement[] path, javax.swing.MenuSelectionManager manager)
    {
    }

    @Override
    public void processKeyEvent(java.awt.event.KeyEvent event, javax.swing.MenuElement[] path, javax.swing.MenuSelectionManager manager)
    {
    }

    @Override
    public void menuSelectionChanged(boolean isIncluded)
    {
    }

    @Override
    public javax.swing.MenuElement[] getSubElements()
    {
        return null;
    }

    @Override
    public java.awt.Component getComponent()
    {
        return this;
    }
    private javax.swing.JButton bt_PrevY;
    private javax.swing.JButton bt_NextY;
    private javax.swing.JButton bt_PrevM;
    private javax.swing.JButton bt_NextM;
    private javax.swing.JSpinner sp_DateY;
    private javax.swing.JComboBox cb_DateM;
    private javax.swing.JPanel pl_User;
    private javax.swing.JPanel pl_Date;
    private javax.swing.JPanel pl_Time;
    private javax.swing.JLabel lb_Date;
    private HeadLabel[] heads;
    private TextLabel[] texts;
}
