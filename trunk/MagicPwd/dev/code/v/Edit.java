/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code.v;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Amon
 */
public class Edit extends JPanel {

    private javax.swing.JButton bt_CopyData;
    private javax.swing.JButton bt_SaveData;
    private javax.swing.JButton bt_DropData;

    public void init() {
        bt_CopyData = new javax.swing.JButton();
        bt_SaveData = new javax.swing.JButton();
        bt_DropData = new javax.swing.JButton();

        bt_CopyData.setText("C");

        bt_SaveData.setText("S");

        bt_DropData.setText("D");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        javax.swing.GroupLayout.ParallelGroup hgp = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hgp.addGap(0, 0, Short.MAX_VALUE);
        hgp.addComponent(bt_DropData, javax.swing.GroupLayout.Alignment.TRAILING);
        hgp.addComponent(bt_SaveData, javax.swing.GroupLayout.Alignment.TRAILING);
        hgp.addComponent(bt_CopyData, javax.swing.GroupLayout.Alignment.TRAILING);
        layout.setHorizontalGroup(hgp);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addContainerGap(0, Short.MAX_VALUE);
        vsg.addComponent(bt_CopyData);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(bt_SaveData);
        vsg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        vsg.addComponent(bt_DropData);
        javax.swing.GroupLayout.ParallelGroup vpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        vpg.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vsg);
        layout.setVerticalGroup(vpg);
    }


    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
        }

        Edit m = new Edit();
        m.init();

        javax.swing.JFrame jf = new javax.swing.JFrame();
        jf.getContentPane().add(m);
        jf.pack();
        jf.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
