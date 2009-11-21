/**
 * 
 */
package com.magicpwd._user;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import com.magicpwd._comp.BtnLabel;
import com.magicpwd._comn.C1C1;

/**
 * @author shangwen.yao
 * 
 */
public final class ScreenKB extends JPanel
{
    private static final long serialVersionUID = 5394771781997937430L;

    public ScreenKB()
    {
    }

    public void initView()
    {
        setLayout(new java.awt.GridBagLayout());

        int SIZE = 26;
        lb_CharButn = new BtnLabel[SIZE];

        int idx = 0;
        BtnLabel label;
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        gbc.ipady = 2;
        gbc.insets = new java.awt.Insets(1, 1, 1, 1);

        gbc.gridy = 0;
        while (idx < 9)
        {
            gbc.gridx = idx;

            label = new BtnLabel();
            label.setActionCommand("");
            add(label, gbc);
            lb_CharButn[idx++] = label;
        }

        gbc.gridx = 9;
        lb_BackButn = new BtnLabel();
        add(lb_BackButn, gbc);

        gbc.gridy = 1;
        while (idx < 18)
        {
            gbc.gridx = idx - 9;

            label = new BtnLabel();
            label.setActionCommand("");
            add(label, gbc);
            lb_CharButn[idx++] = label;
        }

        gbc.gridx = 9;
        lb_CapsButn = new BtnLabel();
        lb_CapsButn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                nextV(getKeyA(), lb_CharButn);
            }
        });
        add(lb_CapsButn, gbc);

        gbc.gridy = 2;
        while (idx < 26)
        {
            gbc.gridx = idx - 18;

            label = new BtnLabel();
            label.setActionCommand("");
            add(label, gbc);
            lb_CharButn[idx++] = label;
        }

        gbc.gridx = 8;
        lb_NextPage = new BtnLabel();
        lb_NextPage.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                nextK(getKeyB(), lb_CharButn);
            }
        });
        add(lb_NextPage, gbc);

        gbc.gridx = 9;
        lb_ShiftBtn = new BtnLabel();
        add(lb_ShiftBtn, gbc);
    }

    public void initLang()
    {
        lb_BackButn.setText("Back");
        lb_CapsButn.setText("Caps");
        lb_NextPage.setText("#");
        lb_ShiftBtn.setText("Shift");

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
