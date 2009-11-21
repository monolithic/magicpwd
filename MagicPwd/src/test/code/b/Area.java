/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.code.b;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yihaodian
 */
public class Area extends javax.swing.JPanel {
    private javax.swing.JLabel lb_PropName;
    private javax.swing.JLabel lb_PropData;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel pl_ItemEdit;
    private javax.swing.JTextArea ta_PropData;
    private javax.swing.JTextField tf_PropName;

    public void init() {
        jLabel3 = new javax.swing.JLabel();
        jLabel3.setText("jLabel3");

        lb_PropName = new javax.swing.JLabel();
        lb_PropName.setText("jLabel1");

        tf_PropName = new javax.swing.JTextField();
        tf_PropName.setText("jTextField1");

        lb_PropData = new javax.swing.JLabel();
        lb_PropData.setText("jLabel2ddd");

        ta_PropData = new javax.swing.JTextArea();
        ta_PropData.setColumns(20);
        ta_PropData.setLineWrap(true);
        ta_PropData.setRows(3);

        javax.swing.JScrollPane sp1 = new javax.swing.JScrollPane();
        sp1.setViewportView(ta_PropData);

        pl_ItemEdit = new javax.swing.JPanel();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pl_ItemEdit);
        pl_ItemEdit.setLayout(layout);
        pl_ItemEdit.setBackground(java.awt.Color.red);
        javax.swing.GroupLayout.SequentialGroup sg = layout.createSequentialGroup();
        sg.addComponent(jLabel3);
        layout.setHorizontalGroup(sg);
        javax.swing.GroupLayout.SequentialGroup vg = layout.createSequentialGroup();
        vg.addContainerGap(0, Short.MAX_VALUE);
        vg.addComponent(jLabel3);
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vg));

        layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        javax.swing.GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg1.addComponent(lb_PropName);
        hpg1.addComponent(lb_PropData);

        javax.swing.GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        hpg2.addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);

        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addGroup(hpg1);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addGroup(hpg2);
        hsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        hsg.addComponent(pl_ItemEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setHorizontalGroup(hsg);

        javax.swing.GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(lb_PropName);
        vpg1.addComponent(tf_PropName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);

        javax.swing.GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg2.addComponent(lb_PropData);
        vpg2.addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);

        javax.swing.GroupLayout.SequentialGroup vpg3 = layout.createSequentialGroup();
        vpg3.addGroup(vpg1);
        vpg3.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vpg3.addGroup(vpg2);

        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg.addGroup(vpg3);
        vpg.addComponent(pl_ItemEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        layout.setVerticalGroup(vpg);
    }

    public static void main(String[] args) {
        try {
           javax.swing. UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Area.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Area.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Area.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Area.class.getName()).log(Level.SEVERE, null, ex);
        }

        Area m = new Area();
        m.init();

        javax.swing.JFrame jf = new javax.swing.JFrame();
        jf.getContentPane().add(m);
        jf.pack();
        jf.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
