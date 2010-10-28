/*
 *  Copyright (C) 2010 Administrator
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
package com.magicpwd.v.mwiz;

import com.magicpwd._bean.mwiz.GuidBean;
import com.magicpwd._bean.mwiz.HeadBean;
import com.magicpwd._util.Bean;
import com.magicpwd._util.Lang;
import com.magicpwd.m.mwiz.KeysMdl;

/**
 * Application: MagicPwd
 * Author     : Administrator
 * Encoding   : UTF-8
 * Created    : 2010-10-24 22:46:25
 * Website    : http://magicpwd.com/
 * Project    : http://magicpwd.googlecode.com/
 * Contact    : Amon@magicpwd.com
 * CopyRight  : Winshine.biz
 * Description:
 */
public class EditPtn extends javax.swing.JDialog
{

    private static final int MAX_ROW = 5;
    private int currStep;
    private KeysMdl keysMdl;
    private NormPtn normPtn;
    private GuidBean guidBean;
    private HeadBean headBean;

    public EditPtn(NormPtn normPtn)
    {
        super(normPtn, false);
        this.normPtn = normPtn;
    }

    public void initView()
    {
        initNoteView();
        initBaseView();

        guidBean = new GuidBean(normPtn);
        guidBean.initView();
        pl_EditArea.add("guid", guidBean);

        headBean = new HeadBean(normPtn);
        headBean.initView();
        pl_EditArea.add("head", headBean);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Bean.getLogo(16));
        setResizable(false);
        pack();
        Bean.centerForm(this, normPtn);
    }

    public void initLang()
    {
        guidBean.initLang();
        headBean.initLang();

        bt_Cancel.setText("取消(C)");
        bt_Update.setText("保存(S)");
        bt_NextStep.setText("下一步(N)");
        bt_PrevStep.setText("上一步(P)");
        setTitle("口令编辑");
    }

    public void initData()
    {
        guidBean.initData();
        headBean.initData();

        currStep = -1;
        bt_Update.setVisible(false);
        bt_PrevStep.setVisible(false);
        setVisible(true);
    }

    public final void showData(KeysMdl keysMdl)
    {
        this.keysMdl = keysMdl;
        initGuidView();
    }

    private void initNoteView()
    {
        pl_NoteArea = new javax.swing.JPanel();
        ta_NoteArea = new javax.swing.JTextArea();

        ta_NoteArea.setEditable(false);
        ta_NoteArea.setRows(3);
        javax.swing.JScrollPane jsp = new javax.swing.JScrollPane(ta_NoteArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_NoteArea);
        pl_NoteArea.setLayout(layout);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addContainerGap();
        hsg1.addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE);
        hsg1.addContainerGap();
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(hsg1));

        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addContainerGap();
        vsg1.addComponent(jsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg1.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(vsg1));
    }

    private void initBaseView()
    {
        pl_EditArea = new javax.swing.JPanel();
        bt_Cancel = new javax.swing.JButton();
        bt_Update = new javax.swing.JButton();
        bt_NextStep = new javax.swing.JButton();
        bt_PrevStep = new javax.swing.JButton();

        cl_Layout = new java.awt.CardLayout();
        pl_EditArea.setLayout(cl_Layout);

        bt_Cancel.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_CancelActionPerformed(evt);
            }
        });

        bt_Update.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_UpdateActionPerformed(evt);
            }
        });

        bt_NextStep.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_NextStepActionPerformed(evt);
            }
        });

        bt_PrevStep.addActionListener(new java.awt.event.ActionListener()
        {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                bt_PrevStepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(pl_NoteArea, javax.swing.GroupLayout.DEFAULT_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg1.addComponent(pl_EditArea, javax.swing.GroupLayout.DEFAULT_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE);
        javax.swing.GroupLayout.SequentialGroup hsg1 = layout.createSequentialGroup();
        hsg1.addContainerGap();
        hsg1.addGroup(hpg1);
        hsg1.addContainerGap();
        javax.swing.GroupLayout.SequentialGroup hsg2 = layout.createSequentialGroup();
        hsg2.addContainerGap(1, Short.MAX_VALUE);
        hsg2.addComponent(bt_PrevStep);
        //hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg2.addComponent(bt_NextStep);
        //hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg2.addComponent(bt_Update);
        //hsg2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg2.addComponent(bt_Cancel);
        hsg2.addContainerGap();
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(hpg1).addGroup(hsg2));

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(bt_Cancel);
        vpg1.addComponent(bt_Update);
        vpg1.addComponent(bt_NextStep);
        vpg1.addComponent(bt_PrevStep);
        javax.swing.GroupLayout.SequentialGroup vsg1 = layout.createSequentialGroup();
        vsg1.addContainerGap();
        vsg1.addComponent(pl_NoteArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addComponent(pl_EditArea, javax.swing.GroupLayout.DEFAULT_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg1.addGroup(vpg1);
        vsg1.addContainerGap();
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(vsg1));
    }

    private void initGuidView()
    {
        cl_Layout.show(pl_EditArea, "guid");
        guidBean.showData(keysMdl);
    }

    private void initHeadView()
    {
        cl_Layout.show(pl_EditArea, "head");
        headBean.showData(keysMdl);
    }

    private void prevBodyView()
    {
        cl_Layout.show(pl_EditArea, "body");

        pl_EditBody.removeAll();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hpg0 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);

        hpg0.addComponent(getLabel(0));
        hpg1.addComponent(getPanel(0), javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE);

        hpg0.addComponent(lb_EditLbl1);
        hpg1.addComponent(pl_EditPnl1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE);

        hpg0.addComponent(lb_EditLbl2);
        hpg1.addComponent(pl_EditPnl2, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE);

        hpg0.addComponent(lb_EditLbl3);
        hpg1.addComponent(pl_EditPnl3, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE);

        hpg0.addComponent(lb_EditLbl4);
        hpg1.addComponent(pl_EditPnl4, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE);

        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addGroup(hpg0);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addGroup(hpg1);
        hsg.addContainerGap();
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap();

        javax.swing.GroupLayout.ParallelGroup vpg0 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg0.addComponent(getLabel(0));
        vpg0.addComponent(getPanel(0), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addGroup(vpg0);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lb_EditLbl1);
        vpg1.addComponent(pl_EditPnl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addGroup(vpg1);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(lb_EditLbl2);
        vpg2.addComponent(pl_EditPnl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addGroup(vpg2);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

        javax.swing.GroupLayout.ParallelGroup vpg3 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg3.addComponent(lb_EditLbl3);
        vpg3.addComponent(pl_EditPnl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addGroup(vpg3);

        javax.swing.GroupLayout.ParallelGroup vpg4 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg4.addComponent(lb_EditLbl4);
        vpg4.addComponent(pl_EditPnl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        vsg.addGroup(vpg4);

        vsg.addContainerGap(0, Short.MAX_VALUE);
        layout.setVerticalGroup(vsg);
    }

    private void nextBodyView()
    {
    }

    private javax.swing.JLabel getLabel(int index)
    {
        if (lb_EditList == null)
        {
            lb_EditList = new javax.swing.JLabel[MAX_ROW];
        }
        javax.swing.JLabel label = lb_EditList[index];
        if (label == null)
        {
            label = new javax.swing.JLabel();
            lb_EditList[index] = label;
        }
        return label;
    }

    private javax.swing.JPanel getPanel(int index)
    {
        if (pl_EditList == null)
        {
            pl_EditList = new javax.swing.JPanel[MAX_ROW];
        }
        javax.swing.JPanel panel = pl_EditList[index];
        if (panel == null)
        {
            panel = new javax.swing.JPanel();
            pl_EditList[index] = panel;
        }
        return panel;
    }

    private void bt_PrevStepActionPerformed(java.awt.event.ActionEvent evt)
    {
        currStep -= 1;
        if (currStep < 0)
        {
            initGuidView();
            bt_Update.setVisible(false);
            bt_PrevStep.setVisible(false);
            return;
        }
        if (currStep == 0)
        {
            initHeadView();
            return;
        }
        prevBodyView();
    }

    private void bt_NextStepActionPerformed(java.awt.event.ActionEvent evt)
    {
        currStep += 1;
        bt_PrevStep.setVisible(true);
        if (currStep == 0)
        {
            initHeadView();
            return;
        }
        nextBodyView();
    }

    private void bt_UpdateActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    private void bt_CancelActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (currStep > -1)
        {
            if (javax.swing.JOptionPane.YES_OPTION != Lang.showFirm(this, null, null))
            {
                return;
            }
        }
        setVisible(false);
    }
    private java.awt.CardLayout cl_Layout;
    private javax.swing.JButton bt_Cancel;
    private javax.swing.JButton bt_NextStep;
    private javax.swing.JButton bt_PrevStep;
    private javax.swing.JButton bt_Update;
    private javax.swing.JPanel pl_EditArea;
    private javax.swing.JPanel pl_NoteArea;
    private javax.swing.JPanel pl_EditBody;
    private javax.swing.JTextArea ta_NoteArea;
    // 动态组件
    private javax.swing.JLabel[] lb_EditList;
    private javax.swing.JPanel[] pl_EditList;
    private javax.swing.JLabel lb_EditLbl1;
    private javax.swing.JLabel lb_EditLbl2;
    private javax.swing.JLabel lb_EditLbl3;
    private javax.swing.JLabel lb_EditLbl4;
    private javax.swing.JPanel pl_EditPnl1;
    private javax.swing.JPanel pl_EditPnl2;
    private javax.swing.JPanel pl_EditPnl3;
    private javax.swing.JPanel pl_EditPnl4;
}
