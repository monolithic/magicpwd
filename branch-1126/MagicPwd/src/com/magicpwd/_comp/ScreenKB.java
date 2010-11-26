/**
 * 
 */
package com.magicpwd._comp;

import com.magicpwd._comn.C1C1;

/**
 * @author Amon
 */
public final class ScreenKB extends javax.swing.JPanel
{

    private int currPage;
    private int currCase;

    public ScreenKB()
    {
    }

    public void initView()
    {
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        jPanel1.setLayout(new java.awt.GridLayout(3, 9, 1, 2));

        lb_CharButn = new BtnLabel[26];
        BtnLabel button;
        for (int i = 0, j = lb_CharButn.length; i < j; i += 1)
        {
            button = new BtnLabel();
            jPanel1.add(button);
            lb_CharButn[i] = button;
        }
        // 换页键
        lb_NextPage = new BtnLabel();
        lb_NextPage.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                nextK(currPage == 0 ? getKeyB() : getKeyA(), lb_CharButn);
                currPage = 1 - currPage;
            }
        });
        jPanel1.add(lb_NextPage);

        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        jPanel2.setLayout(new java.awt.GridLayout(3, 1));

        // 退格键
        lb_BackButn = new BtnLabel();
        jPanel2.add(lb_BackButn);

        // 大写键
        lb_CapsButn = new BtnLabel();
        lb_CapsButn.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                nextV(currCase == 0 ? getKeyA() : getKeyB(), lb_CharButn);
                currCase = 1 - currCase;
            }
        });
        jPanel2.add(lb_CapsButn);

        // 上档键
        lb_ShiftBtn = new BtnLabel();
        jPanel2.add(lb_ShiftBtn);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hsg.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        vpg.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setVerticalGroup(vpg);
    }

    public void initLang()
    {
        lb_BackButn.setText(" Back ");
        lb_CapsButn.setText(" Caps ");
        lb_NextPage.setText("#");
        lb_ShiftBtn.setText(" Shift ");

        nextK(getKeyA(), lb_CharButn);
    }

    private void nextK(C1C1[] cvs, BtnLabel[] lbs)
    {
        for (int i = 0, j = cvs.length; i < j; i += 1)
        {
            lbs[i].setText("" + cvs[i].getK());
        }
    }

    private void nextV(C1C1[] cvs, BtnLabel[] lbs)
    {
        for (int i = 0, j = cvs.length; i < j; i += 1)
        {
            lbs[i].setText("" + cvs[i].getV());
        }
    }

    private C1C1[] getKeyA()
    {
        int i = 0;
        C1C1[] cv1 = new C1C1[26];
        cv1[i++] = new C1C1('a', 'A');
        cv1[i++] = new C1C1('b', 'B');
        cv1[i++] = new C1C1('c', 'C');
        cv1[i++] = new C1C1('d', 'D');
        cv1[i++] = new C1C1('e', 'E');
        cv1[i++] = new C1C1('f', 'F');
        cv1[i++] = new C1C1('g', 'G');
        cv1[i++] = new C1C1('h', 'H');
        cv1[i++] = new C1C1('i', 'I');
        cv1[i++] = new C1C1('j', 'J');
        cv1[i++] = new C1C1('k', 'K');
        cv1[i++] = new C1C1('l', 'L');
        cv1[i++] = new C1C1('m', 'M');
        cv1[i++] = new C1C1('n', 'N');
        cv1[i++] = new C1C1('o', 'O');
        cv1[i++] = new C1C1('p', 'P');
        cv1[i++] = new C1C1('q', 'Q');
        cv1[i++] = new C1C1('r', 'R');
        cv1[i++] = new C1C1('s', 'S');
        cv1[i++] = new C1C1('t', 'T');
        cv1[i++] = new C1C1('u', 'U');
        cv1[i++] = new C1C1('v', 'V');
        cv1[i++] = new C1C1('w', 'W');
        cv1[i++] = new C1C1('x', 'X');
        cv1[i++] = new C1C1('y', 'Y');
        cv1[i++] = new C1C1('z', 'Z');
        return cv1;
    }

    private C1C1[] getKeyB()
    {
        int i = 0;
        C1C1[] cv1 = new C1C1[26];
        cv1[i++] = new C1C1('`', '~');
        cv1[i++] = new C1C1('1', '!');
        cv1[i++] = new C1C1('2', '@');
        cv1[i++] = new C1C1('3', '#');
        cv1[i++] = new C1C1('4', '$');
        cv1[i++] = new C1C1('5', '%');
        cv1[i++] = new C1C1('6', '^');
        cv1[i++] = new C1C1('7', '&');
        cv1[i++] = new C1C1('8', '*');
        cv1[i++] = new C1C1('9', '(');
        cv1[i++] = new C1C1('0', ')');
        cv1[i++] = new C1C1('-', '_');
        cv1[i++] = new C1C1('=', '+');
        cv1[i++] = new C1C1('\\', '|');
        cv1[i++] = new C1C1('[', '{');
        cv1[i++] = new C1C1(']', '}');
        cv1[i++] = new C1C1(';', ':');
        cv1[i++] = new C1C1('\'', '"');
        cv1[i++] = new C1C1(',', '<');
        cv1[i++] = new C1C1('.', '>');
        cv1[i++] = new C1C1('/', '?');
        cv1[i++] = new C1C1(' ', ' ');
        cv1[i++] = new C1C1('；', '：');
        cv1[i++] = new C1C1('，', '？');
        cv1[i++] = new C1C1('。', '！');
        cv1[i++] = new C1C1('、', '￥');
        return cv1;
    }
    private BtnLabel[] lb_CharButn;
    private BtnLabel lb_BackButn;
    private BtnLabel lb_CapsButn;
    private BtnLabel lb_ShiftBtn;
    private BtnLabel lb_NextPage;
}
