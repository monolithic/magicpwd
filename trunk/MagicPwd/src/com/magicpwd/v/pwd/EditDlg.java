/**
 * 
 */
package com.magicpwd.v.pwd;

import com.magicpwd._cons.LangRes;
import com.magicpwd._util.Lang;

/**
 * 属性编辑独立窗口
 * @author Amon
 */
public class EditDlg extends javax.swing.JDialog
{
    private MainPtn mainPtn;

    public EditDlg(MainPtn mainPtn)
    {
        super(mainPtn);
        this.mainPtn = mainPtn;
    }

    public void initView()
    {
        pl_PropEdit = new javax.swing.JPanel();
        pl_PropEdit.setLayout(new java.awt.BorderLayout());

//        Util.addHideAction(pl_PropEdit.getActionMap(), pl_PropEdit.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW), menuEvt);
//        Util.addViewAction(pl_PropEdit.getActionMap(), pl_PropEdit.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW), menuEvt);
//        Util.addFileAction(pl_PropEdit.getActionMap(), pl_PropEdit.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW), menuEvt);
//        Util.addDataAction(pl_PropEdit.getActionMap(), pl_PropEdit.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW), menuEvt);

        addWindowListener(new java.awt.event.WindowAdapter()
        {

            @Override
            public void windowClosing(java.awt.event.WindowEvent e)
            {
                windowClosingEvent(e);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addComponent(pl_PropEdit,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addComponent(pl_PropEdit,
                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)));

        setResizable(false);
    }

    public void initLang()
    {
        setTitle(Lang.getLang(LangRes.P30FA208, "友情提示"));
    }

    public void initData()
    {
    }

    public void setPropView(javax.swing.JPanel prop)
    {
        pl_PropEdit.add(prop);

        pack();
        java.awt.Dimension a = getSize();
        java.awt.Dimension b = mainPtn.getSize();
        java.awt.Point p = mainPtn.getLocation();
        setLocation(p.x + b.width, p.y + b.height - a.height);
    }

    private void windowClosingEvent(java.awt.event.WindowEvent evt)
    {
//        menuEvt.viewSideActionPerformed(null);
    }
    private javax.swing.JPanel pl_PropEdit;
}
