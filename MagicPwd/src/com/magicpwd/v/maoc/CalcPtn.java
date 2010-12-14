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
package com.magicpwd.v.maoc;

import com.magicpwd._comn.S1S2;
import com.magicpwd._cons.LangRes;
import com.magicpwd._cons.maoc.MaocEnv;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Char;
import com.magicpwd._util.Lang;
import com.magicpwd._util.Logs;
import com.magicpwd.m.maoc.WComputer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.Document;

/**
 *
 * @author Amon
 */
public class CalcPtn extends javax.swing.JPanel
{

    private WComputer computer;
    /** 计算精度 */
    private int precision = 8;

    public CalcPtn(WComputer computer)
    {
        this.computer = computer;
    }

    /*
     * (non-Javadoc)
     *
     * @see rmp.face.WRmps#wInit()
     */
    public boolean initView()
    {
        ica();
        icb();
        icc();
        icd();

        ita();
        itb();
        itc();
        itd();

        bt_ButtonT5.setEnabled(false);
        bt_ButtonT4.setEnabled(false);
        bt_ButtonT3.setEnabled(false);

        return true;
    }

    /**
     * 数据及四则运算输入区域
     */
    private void ica()
    {
        pl_NPanel = new javax.swing.JPanel();
        pl_NPanel.setLayout(new java.awt.GridLayout(4, 4, 3, 3));

        bt_ButtonN7 = new javax.swing.JButton();
        bt_ButtonN7.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonN7_Handler(evt);
            }
        });
        pl_NPanel.add(bt_ButtonN7);

        bt_ButtonN8 = new javax.swing.JButton();
        bt_ButtonN8.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonN8_Handler(evt);
            }
        });
        pl_NPanel.add(bt_ButtonN8);

        bt_ButtonN9 = new javax.swing.JButton();
        bt_ButtonN9.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonN9_Handler(evt);
            }
        });
        pl_NPanel.add(bt_ButtonN9);

        bt_ButtonS3 = new javax.swing.JButton();
        bt_ButtonS3.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonS3_Handler(evt);
            }
        });
        pl_NPanel.add(bt_ButtonS3);

        bt_ButtonN4 = new javax.swing.JButton();
        bt_ButtonN4.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonN4_Handler(evt);
            }
        });
        pl_NPanel.add(bt_ButtonN4);

        bt_ButtonN5 = new javax.swing.JButton();
        bt_ButtonN5.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonN5_Handler(evt);
            }
        });
        pl_NPanel.add(bt_ButtonN5);

        bt_ButtonN6 = new javax.swing.JButton();
        bt_ButtonN6.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonN6_Handler(evt);
            }
        });
        pl_NPanel.add(bt_ButtonN6);

        bt_ButtonS2 = new javax.swing.JButton();
        bt_ButtonS2.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonS2_Handler(evt);
            }
        });
        pl_NPanel.add(bt_ButtonS2);

        bt_ButtonN1 = new javax.swing.JButton();
        bt_ButtonN1.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonN1_Handler(evt);
            }
        });
        pl_NPanel.add(bt_ButtonN1);

        bt_ButtonN2 = new javax.swing.JButton("2");
        bt_ButtonN2.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonN2_Handler(evt);
            }
        });
        pl_NPanel.add(bt_ButtonN2);

        bt_ButtonN3 = new javax.swing.JButton();
        bt_ButtonN3.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonN3_Handler(evt);
            }
        });
        pl_NPanel.add(bt_ButtonN3);

        bt_ButtonS1 = new javax.swing.JButton();
        bt_ButtonS1.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonS1_Handler(evt);
            }
        });
        pl_NPanel.add(bt_ButtonS1);

        bt_ButtonN0 = new javax.swing.JButton();
        bt_ButtonN0.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonN0_Handler(evt);
            }
        });
        pl_NPanel.add(bt_ButtonN0);

        bt_ButtonS5 = new javax.swing.JButton();
        bt_ButtonS5.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonS5_Handler(evt);
            }
        });
        pl_NPanel.add(bt_ButtonS5);

        bt_ButtonS4 = new javax.swing.JButton();
        bt_ButtonS4.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonS4_Handler(evt);
            }
        });
        pl_NPanel.add(bt_ButtonS4);

        bt_ButtonS0 = new javax.swing.JButton();
        bt_ButtonS0.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonS0_Handler(evt);
            }
        });
        pl_NPanel.add(bt_ButtonS0);
    }

    /**
     * 括号及PI等输入区域
     */
    private void icb()
    {
        pl_PPanel = new javax.swing.JPanel();
        pl_PPanel.setLayout(new java.awt.GridLayout(4, 2, 3, 3));

        bt_ButtonP5 = new javax.swing.JButton();
        bt_ButtonP5.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonP5_Handler(evt);
            }
        });
        pl_PPanel.add(bt_ButtonP5);

        bt_ButtonP4 = new javax.swing.JButton();
        bt_ButtonP4.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonP4_Handler(evt);
            }
        });
        pl_PPanel.add(bt_ButtonP4);

        bt_ButtonP3 = new javax.swing.JButton();
        bt_ButtonP3.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonP3_Handler(evt);
            }
        });
        pl_PPanel.add(bt_ButtonP3);

        bt_ButtonP2 = new javax.swing.JButton();
        bt_ButtonP2.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonP2_Handler(evt);
            }
        });
        pl_PPanel.add(bt_ButtonP2);

        bt_ButtonP1 = new javax.swing.JButton();
        bt_ButtonP1.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonP1_Handler(evt);
            }
        });
        pl_PPanel.add(bt_ButtonP1);

        bt_ButtonP0 = new javax.swing.JButton();
        bt_ButtonP0.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonP0_Handler(evt);
            }
        });
        pl_PPanel.add(bt_ButtonP0);

        bt_ButtonA6 = new javax.swing.JButton();
        bt_ButtonA6.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonA6_Handler(evt);
            }
        });
        pl_PPanel.add(bt_ButtonA6);

        bt_ButtonA5 = new javax.swing.JButton();
        bt_ButtonA5.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonA5_Handler(evt);
            }
        });
        pl_PPanel.add(bt_ButtonA5);
    }

    /**
     * 代数运算输入区域
     */
    private void icc()
    {
        pl_TPanel = new javax.swing.JPanel();
        pl_TPanel.setLayout(new java.awt.GridLayout(4, 3, 3, 3));

        // x^y
        bt_ButtonA3 = new javax.swing.JButton();
        bt_ButtonA3.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonA3_Handler(evt);
            }
        });
        pl_TPanel.add(bt_ButtonA3);

        // sin
        bt_ButtonT2 = new javax.swing.JButton();
        bt_ButtonT2.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonT2_Handler(evt);
            }
        });
        pl_TPanel.add(bt_ButtonT2);

        // csc
        bt_ButtonT5 = new javax.swing.JButton();
        bt_ButtonT5.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonT5_Handler(evt);
            }
        });
        pl_TPanel.add(bt_ButtonT5);

        // √
        bt_ButtonA2 = new javax.swing.JButton();
        bt_ButtonA2.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonA2_Handler(evt);
            }
        });
        pl_TPanel.add(bt_ButtonA2);

        // cos
        bt_ButtonT1 = new javax.swing.JButton();
        bt_ButtonT1.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonT1_Handler(evt);
            }
        });
        pl_TPanel.add(bt_ButtonT1);

        // sec
        bt_ButtonT4 = new javax.swing.JButton("sec");
        bt_ButtonT4.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonT4_Handler(evt);
            }
        });
        pl_TPanel.add(bt_ButtonT4);

        // log
        bt_ButtonA1 = new javax.swing.JButton();
        bt_ButtonA1.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonA1_Handler(evt);
            }
        });
        pl_TPanel.add(bt_ButtonA1);

        // tan
        bt_ButtonT0 = new javax.swing.JButton();
        bt_ButtonT0.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonT0_Handler(evt);
            }
        });
        pl_TPanel.add(bt_ButtonT0);

        // cot
        bt_ButtonT3 = new javax.swing.JButton("cot");
        bt_ButtonT3.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonT3_Handler(evt);
            }
        });
        pl_TPanel.add(bt_ButtonT3);

        // ln
        bt_ButtonA0 = new javax.swing.JButton();
        bt_ButtonA0.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonA0_Handler(evt);
            }
        });
        pl_TPanel.add(bt_ButtonA0);

        // n!
        bt_ButtonA4 = new javax.swing.JButton();
        bt_ButtonA4.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonA4_Handler(evt);
            }
        });
        pl_TPanel.add(bt_ButtonA4);

        // =
        bt_ButtonS6 = new javax.swing.JButton();
        bt_ButtonS6.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonS6_Handler(evt);
            }
        });
        pl_TPanel.add(bt_ButtonS6);
    }

    /**
     *
     */
    private void icd()
    {
        pl_VPanel = new javax.swing.JPanel();
        pl_VPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 6, 0));

        tf_Precision = new javax.swing.JTextField();
        bt_ButtonV1 = new javax.swing.JButton();
        bt_ButtonV0 = new javax.swing.JButton();
        bt_ShowStep = new javax.swing.JButton();

        tf_Precision.setColumns(4);
        tf_Precision.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tf_Precision.setText("8");
        tf_Precision.setToolTipText("结果计算精度");
        tf_Precision.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                tf_Precision_Handler(evt);
            }
        });
        tf_Precision.addFocusListener(new java.awt.event.FocusListener()
        {

            @Override
            public void focusGained(java.awt.event.FocusEvent evt)
            {
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                tf_Precision_Handler(evt);
            }
        });
        pl_VPanel.add(tf_Precision);

        bt_ButtonV1.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonV1_Handler(evt);
            }
        });
        pl_VPanel.add(bt_ButtonV1);

        bt_ButtonV0.setText("<-");
        bt_ButtonV0.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_ButtonV0_Handler(evt);
            }
        });
        pl_VPanel.add(bt_ButtonV0);

        bt_ShowStep.setMnemonic('S');
        bt_ShowStep.setText(">>");
        bt_ShowStep.setToolTipText("显示计算过程");
        bt_ShowStep.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
            }
        });
        pl_VPanel.add(bt_ShowStep);
    }

    /**
     *
     */
    private void ita()
    {
        Bean.setText(bt_ButtonN7, Lang.getLang(LangRes.P30FB50F, "7"));
        Bean.setTips(bt_ButtonN7, Lang.getLang(LangRes.P30FB510, ""));

        Bean.setText(bt_ButtonN8, Lang.getLang(LangRes.P30FB511, "8"));
        Bean.setTips(bt_ButtonN8, Lang.getLang(LangRes.P30FB512, ""));

        Bean.setText(bt_ButtonN9, Lang.getLang(LangRes.P30FB513, "9"));
        Bean.setTips(bt_ButtonN9, Lang.getLang(LangRes.P30FB514, ""));

        Bean.setText(bt_ButtonS3, Lang.getLang(LangRes.P30FB51B, "/"));
        Bean.setTips(bt_ButtonS3, Lang.getLang(LangRes.P30FB51C, ""));

        Bean.setText(bt_ButtonN4, Lang.getLang(LangRes.P30FB509, "4"));
        Bean.setTips(bt_ButtonN4, Lang.getLang(LangRes.P30FB50A, ""));

        Bean.setText(bt_ButtonN5, Lang.getLang(LangRes.P30FB50B, "5"));
        Bean.setTips(bt_ButtonN5, Lang.getLang(LangRes.P30FB50C, ""));

        Bean.setText(bt_ButtonN6, Lang.getLang(LangRes.P30FB50D, "6"));
        Bean.setTips(bt_ButtonN6, Lang.getLang(LangRes.P30FB50E, ""));

        Bean.setText(bt_ButtonS2, Lang.getLang(LangRes.P30FB519, "*"));
        Bean.setTips(bt_ButtonS2, Lang.getLang(LangRes.P30FB51A, ""));

        Bean.setText(bt_ButtonN1, Lang.getLang(LangRes.P30FB503, "1"));
        Bean.setTips(bt_ButtonN1, Lang.getLang(LangRes.P30FB504, ""));

        Bean.setText(bt_ButtonN2, Lang.getLang(LangRes.P30FB505, "2"));
        Bean.setTips(bt_ButtonN2, Lang.getLang(LangRes.P30FB506, ""));

        Bean.setText(bt_ButtonN3, Lang.getLang(LangRes.P30FB507, "3"));
        Bean.setTips(bt_ButtonN3, Lang.getLang(LangRes.P30FB508, ""));

        Bean.setText(bt_ButtonS1, Lang.getLang(LangRes.P30FB517, "-"));
        Bean.setTips(bt_ButtonS1, Lang.getLang(LangRes.P30FB518, ""));

        Bean.setText(bt_ButtonN0, Lang.getLang(LangRes.P30FB501, "0"));
        Bean.setTips(bt_ButtonN0, Lang.getLang(LangRes.P30FB502, ""));

        Bean.setText(bt_ButtonS5, Lang.getLang(LangRes.P30FB51F, "."));
        Bean.setTips(bt_ButtonS5, Lang.getLang(LangRes.P30FB520, ""));

        Bean.setText(bt_ButtonS4, Lang.getLang(LangRes.P30FB51D, "%"));
        Bean.setTips(bt_ButtonS4, Lang.getLang(LangRes.P30FB51E, ""));

        Bean.setText(bt_ButtonS0, Lang.getLang(LangRes.P30FB515, "+"));
        Bean.setTips(bt_ButtonS0, Lang.getLang(LangRes.P30FB516, ""));
    }

    /**
     *
     */
    private void itb()
    {
        Bean.setText(bt_ButtonP5, Lang.getLang(LangRes.P30FB523, "("));
        Bean.setTips(bt_ButtonP5, Lang.getLang(LangRes.P30FB524, ""));

        Bean.setText(bt_ButtonP4, Lang.getLang(LangRes.P30FB525, ")"));
        Bean.setTips(bt_ButtonP4, Lang.getLang(LangRes.P30FB526, ""));

        Bean.setText(bt_ButtonP3, Lang.getLang(LangRes.P30FB527, "["));
        Bean.setTips(bt_ButtonP3, Lang.getLang(LangRes.P30FB528, ""));

        Bean.setText(bt_ButtonP2, Lang.getLang(LangRes.P30FB529, "]"));
        Bean.setTips(bt_ButtonP2, Lang.getLang(LangRes.P30FB52A, ""));

        Bean.setText(bt_ButtonP1, Lang.getLang(LangRes.P30FB52B, "{"));
        Bean.setTips(bt_ButtonP1, Lang.getLang(LangRes.P30FB52C, ""));

        Bean.setText(bt_ButtonP0, Lang.getLang(LangRes.P30FB52D, "}"));
        Bean.setTips(bt_ButtonP0, Lang.getLang(LangRes.P30FB52E, ""));

        Bean.setText(bt_ButtonA6, Lang.getLang(LangRes.P30FB53F, "π"));
        Bean.setTips(bt_ButtonA6, Lang.getLang(LangRes.P30FB540, ""));

        Bean.setText(bt_ButtonA5, Lang.getLang(LangRes.P30FB53D, "e"));
        Bean.setTips(bt_ButtonA5, Lang.getLang(LangRes.P30FB53E, ""));
    }

    /**
     *
     */
    private void itc()
    {
        Bean.setText(bt_ButtonA3, Lang.getLang(LangRes.P30FB539, "x^y"));
        Bean.setTips(bt_ButtonA3, Lang.getLang(LangRes.P30FB53A, ""));

        Bean.setText(bt_ButtonT2, Lang.getLang(LangRes.P30FB545, "sin"));
        Bean.setTips(bt_ButtonT2, Lang.getLang(LangRes.P30FB546, ""));

        Bean.setText(bt_ButtonT5, Lang.getLang(LangRes.P30FB547, "csc"));
        Bean.setTips(bt_ButtonT5, Lang.getLang(LangRes.P30FB548, ""));

        Bean.setText(bt_ButtonA2, Lang.getLang(LangRes.P30FB537, "√"));
        Bean.setTips(bt_ButtonA2, Lang.getLang(LangRes.P30FB538, ""));

        Bean.setText(bt_ButtonT1, Lang.getLang(LangRes.P30FB543, "cos"));
        Bean.setTips(bt_ButtonT1, Lang.getLang(LangRes.P30FB544, ""));

        Bean.setText(bt_ButtonT4, Lang.getLang(LangRes.P30FB549, "sec"));
        Bean.setTips(bt_ButtonT4, Lang.getLang(LangRes.P30FB54A, ""));

        Bean.setText(bt_ButtonA1, Lang.getLang(LangRes.P30FB535, "log"));
        Bean.setTips(bt_ButtonA1, Lang.getLang(LangRes.P30FB536, ""));

        Bean.setText(bt_ButtonT0, Lang.getLang(LangRes.P30FB541, "tan"));
        Bean.setTips(bt_ButtonT0, Lang.getLang(LangRes.P30FB542, ""));

        Bean.setText(bt_ButtonT3, Lang.getLang(LangRes.P30FB54B, "cot"));
        Bean.setTips(bt_ButtonT3, Lang.getLang(LangRes.P30FB54C, ""));

        Bean.setText(bt_ButtonA0, Lang.getLang(LangRes.P30FB533, "ln"));
        Bean.setTips(bt_ButtonA0, Lang.getLang(LangRes.P30FB534, ""));

        Bean.setText(bt_ButtonA4, Lang.getLang(LangRes.P30FB53B, "n!"));
        Bean.setTips(bt_ButtonA4, Lang.getLang(LangRes.P30FB53C, ""));

        Bean.setText(bt_ButtonS6, Lang.getLang(LangRes.P30FB521, "="));
        Bean.setTips(bt_ButtonS6, Lang.getLang(LangRes.P30FB522, ""));
    }

    /**
     *
     */
    private void itd()
    {
        tf_Precision.setText(Lang.getLang(LangRes.P30FB401, ""));
        Bean.setTips(tf_Precision, Lang.getLang(LangRes.P30FB402, "计算结果显示精度，默认为小数点后保留8位"));

        Bean.setText(bt_ButtonV1, Lang.getLang(LangRes.P30FB54D, "CR"));
        Bean.setTips(bt_ButtonV1, Lang.getLang(LangRes.P30FB54E, "清除当前运算结果"));

        Bean.setText(bt_ButtonV0, Lang.getLang(LangRes.P30FB54F, "<-"));
        Bean.setTips(bt_ButtonV0, Lang.getLang(LangRes.P30FB550, "清除一个字符"));

        Bean.setText(bt_ShowStep, Lang.getLang(LangRes.P30FB551, ""));
//        Bean.setTips(bt_ShowStep, Lang.getLang(LangRes.P30FB54D, ""));
    }

    // ////////////////////////////////////////////////////////////////////////
    // 事件处理区域
    // ////////////////////////////////////////////////////////////////////////
    // ----------------------------------------------------
    // 数值输入区域
    // ----------------------------------------------------
    /**
     * @param evt
     */
    private void bt_ButtonN0_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.NUM_0);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonN1_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.NUM_1);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonN2_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.NUM_2);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonN3_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.NUM_3);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonN4_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.NUM_4);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonN5_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.NUM_5);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonN6_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.NUM_6);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonN7_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.NUM_7);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonN8_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.NUM_8);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonN9_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.NUM_9);
        ta_UserForm.requestFocus();
    }

    // ----------------------------------------------------
    // 四则运算区域
    // ----------------------------------------------------
    /**
     * 加
     *
     * @param evt
     */
    private void bt_ButtonS0_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_ADD_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * 减
     *
     * @param evt
     */
    private void bt_ButtonS1_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_SUB_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * 乘
     *
     * @param evt
     */
    private void bt_ButtonS2_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_MUL_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * 除
     *
     * @param evt
     */
    private void bt_ButtonS3_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_DIV_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonS4_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_MOD_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonS5_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.NUM_D);
        ta_UserForm.requestFocus();
    }

    /**
     * 等号按钮事件处理
     *
     * @param evt
     */
    private void bt_ButtonS6_Handler(java.awt.event.ActionEvent evt)
    {
        String exps = ta_UserForm.getText();
        if (!Char.isValidate(exps))
        {
            Lang.showMesg(this, LangRes.P30FBA010, "");
            ta_UserForm.requestFocus();
            return;
        }

        int idx1 = exps.lastIndexOf(MaocEnv.OPR_EQU_EXP);
        if (exps.endsWith(MaocEnv.OPR_EQU_EXP))
        {
            int idx2 = exps.lastIndexOf(MaocEnv.OPR_EQU_EXP, idx1);
            if (idx2 >= 0)
            {
                exps = exps.substring(idx2 + 1, idx1);
            }
        }
        else
        {
            if (idx1 >= 0)
            {
                exps = exps.substring(exps.lastIndexOf(MaocEnv.OPR_EQU_EXP) + 1);
            }
            ta_UserForm.append(MaocEnv.OPR_EQU_EXP);
        }

        try
        {
            List<S1S2> stepList = new ArrayList<S1S2>();
            ta_UserForm.append(computer.calculate(exps, precision, stepList));
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, null, exp.getLocalizedMessage());
        }
    }

    // ----------------------------------------------------
    // 括号事件区域
    // ----------------------------------------------------
    /**
     * @param evt
     */
    private void bt_ButtonP0_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.append(MaocEnv.OPR_LRB_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonP1_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_LLB_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonP2_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_MRB_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonP3_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_MLB_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonP4_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_SRB_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonP5_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_SLB_EXP);
        ta_UserForm.requestFocus();
    }

    // ----------------------------------------------------
    // 代数运算区域
    // ----------------------------------------------------
    /**
     * @param evt
     */
    private void bt_ButtonA0_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_LNE_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonA1_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_LOG_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonA2_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_ROT_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonA3_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_POW_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonA4_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_FAC_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonA5_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.NUM_E);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonA6_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.NUM_P);
        ta_UserForm.requestFocus();
    }

    // ----------------------------------------------------
    // 三角函数区域
    // ----------------------------------------------------
    /**
     * @param evt
     */
    private void bt_ButtonT0_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_TAN_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonT1_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_COS_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonT2_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_SIN_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonT3_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_COT_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonT4_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_SEC_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonT5_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.replaceSelection(MaocEnv.OPR_CSC_EXP);
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void bt_ButtonV0_Handler(java.awt.event.ActionEvent evt)
    {
        try
        {
            Document doc = ta_UserForm.getDocument();
            Caret caret = ta_UserForm.getCaret();
            int dot = caret.getDot();
            int mark = caret.getMark();
            if (dot != mark)
            {
                doc.remove(Math.min(dot, mark), Math.abs(dot - mark));
            }
            else if (dot > 0)
            {
                int delChars = 1;

                if (dot > 1)
                {
                    String dotChars = doc.getText(dot - 2, 2);
                    char c0 = dotChars.charAt(0);
                    char c1 = dotChars.charAt(1);

                    if (c0 >= '\uD800' && c0 <= '\uDBFF' && c1 >= '\uDC00' && c1 <= '\uDFFF')
                    {
                        delChars = 2;
                    }
                }

                doc.remove(dot - delChars, delChars);
            }
            ta_UserForm.requestFocus();
        }
        catch (BadLocationException bl)
        {
        }
    }

    /**
     * @param evt
     */
    private void bt_ButtonV1_Handler(java.awt.event.ActionEvent evt)
    {
        ta_UserForm.setText("");
        ta_UserForm.requestFocus();
    }

    /**
     * @param evt
     */
    private void tf_Precision_Handler(java.awt.AWTEvent evt)
    {
        String t = tf_Precision.getText();
        if (t.trim().length() < 1)
        {
            t = "8";
        }
        try
        {
            precision = Integer.parseInt(t);
        }
        catch (Exception exp)
        {
            Logs.exception(exp);
            Lang.showMesg(this, LangRes.P30FBA02, "");
            tf_Precision.requestFocus();
        }
        if (precision < 0)
        {
            precision = 8;
        }
    }
    // ////////////////////////////////////////////////////////////////////////
    // 界面变更区域
    // ////////////////////////////////////////////////////////////////////////
    // ----------------------------------------------------
    // 数值输入区域
    // ----------------------------------------------------
    /** 0 */
    private javax.swing.JButton bt_ButtonN0;
    /** 1 */
    private javax.swing.JButton bt_ButtonN1;
    /** 2 */
    private javax.swing.JButton bt_ButtonN2;
    /** 3 */
    private javax.swing.JButton bt_ButtonN3;
    /** 4 */
    private javax.swing.JButton bt_ButtonN4;
    /** 5 */
    private javax.swing.JButton bt_ButtonN5;
    /** 6 */
    private javax.swing.JButton bt_ButtonN6;
    /** 7 */
    private javax.swing.JButton bt_ButtonN7;
    /** 8 */
    private javax.swing.JButton bt_ButtonN8;
    /** 9 */
    private javax.swing.JButton bt_ButtonN9;
    // ----------------------------------------------------
    // 四则运算区域
    // ----------------------------------------------------
    /** + */
    private javax.swing.JButton bt_ButtonS0;
    /** - */
    private javax.swing.JButton bt_ButtonS1;
    /** x */
    private javax.swing.JButton bt_ButtonS2;
    /** / */
    private javax.swing.JButton bt_ButtonS3;
    /** % */
    private javax.swing.JButton bt_ButtonS4;
    /** . */
    private javax.swing.JButton bt_ButtonS5;
    /** = */
    private javax.swing.JButton bt_ButtonS6;
    // ----------------------------------------------------
    // 括号按钮区域
    // ----------------------------------------------------
    /** } */
    private javax.swing.JButton bt_ButtonP0;
    /** { */
    private javax.swing.JButton bt_ButtonP1;
    /** ] */
    private javax.swing.JButton bt_ButtonP2;
    /** [ */
    private javax.swing.JButton bt_ButtonP3;
    /** ) */
    private javax.swing.JButton bt_ButtonP4;
    /** ( */
    private javax.swing.JButton bt_ButtonP5;
    // ----------------------------------------------------
    // 代数运算区域
    // ----------------------------------------------------
    /** ln */
    private javax.swing.JButton bt_ButtonA0;
    /** log */
    private javax.swing.JButton bt_ButtonA1;
    /** √ */
    private javax.swing.JButton bt_ButtonA2;
    /** x^y */
    private javax.swing.JButton bt_ButtonA3;
    /** n! */
    private javax.swing.JButton bt_ButtonA4;
    /** e */
    private javax.swing.JButton bt_ButtonA5;
    /** π */
    private javax.swing.JButton bt_ButtonA6;
    // ----------------------------------------------------
    // 三角函数区域
    // ----------------------------------------------------
    /** tan */
    private javax.swing.JButton bt_ButtonT0;
    /** cos */
    private javax.swing.JButton bt_ButtonT1;
    /** sin */
    private javax.swing.JButton bt_ButtonT2;
    /** cot */
    private javax.swing.JButton bt_ButtonT3;
    /** sec */
    private javax.swing.JButton bt_ButtonT4;
    /** csc */
    private javax.swing.JButton bt_ButtonT5;
    private javax.swing.JPanel pl_NPanel;
    private javax.swing.JPanel pl_PPanel;
    private javax.swing.JPanel pl_TPanel;
    private javax.swing.JPanel pl_VPanel;
    /** 清除一个字符 */
    private javax.swing.JButton bt_ButtonV0;
    /** 清除所有结果 */
    private javax.swing.JButton bt_ButtonV1;
    /** 是否显示计算过程 */
    private javax.swing.JButton bt_ShowStep;
    /** 计算精度 */
    private javax.swing.JTextField tf_Precision;
    /** 用户计算式输入区域 */
    private javax.swing.JTextArea ta_UserForm;
    /** serialVersionUID */
    private static final long serialVersionUID = 5864479988152708567L;
}
