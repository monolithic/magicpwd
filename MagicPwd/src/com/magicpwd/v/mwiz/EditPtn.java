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

    private int currStep;
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

        headBean = new HeadBean();
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
    }

    private void initHeadView()
    {
        cl_Layout.show(pl_EditArea, "head");
    }

    private void prevBodyView()
    {
        cl_Layout.show(pl_EditArea, "body");
    }

    private void nextBodyView()
    {
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
    private javax.swing.JTextArea ta_NoteArea;
}
