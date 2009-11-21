/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.code.v;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yihaodian
 */
public class Main extends javax.swing.JPanel {

    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private Guid jPanel1;
    private View jPanel2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel infobar;

    public void init() {
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new Guid();
        jPanel1.init();
        jPanel2 = new View();
        jPanel2.init();
        infobar = new javax.swing.JPanel();
        infobar.setBackground(java.awt.Color.red);

        jSplitPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jSplitPane1.setDividerLocation(160);
        jSplitPane1.setOneTouchExpandable(true);

        jSplitPane1.setLeftComponent(jPanel1);

        jSplitPane1.setRightComponent(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        setLayout(layout);

        javax.swing.GroupLayout.SequentialGroup hsg = layout.createSequentialGroup();
        hsg.addContainerGap();
        hsg.addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE);
        hsg.addContainerGap();

        javax.swing.GroupLayout.ParallelGroup hpg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        hpg.addGroup(hsg);
        hpg.addComponent(infobar, javax.swing.GroupLayout.PREFERRED_SIZE, 560, Short.MAX_VALUE);

        layout.setHorizontalGroup(hpg);

        javax.swing.GroupLayout.SequentialGroup vsg = layout.createSequentialGroup();
        vsg.addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE);
        vsg.addComponent(infobar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE);
        layout.setVerticalGroup(vsg);
    }

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        Main m = new Main();
        m.init();

        javax.swing.JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
        javax.swing.JMenu jMenu1 = new javax.swing.JMenu();
        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu1 = new javax.swing.JMenu();
        jMenu1.setText("Edit");
        jMenuBar1.add(jMenu1);

        javax.swing.JFrame jf = new javax.swing.JFrame();
        jf.setJMenuBar(jMenuBar1);
        java.awt.Container t = jf.getContentPane();
        t.setLayout(new java.awt.BorderLayout());
        javax.swing.JToolBar jToolBar1 = new javax.swing.JToolBar();
        jToolBar1.add(new javax.swing.JButton("A"));
        jToolBar1.setRollover(true);
        t.add(jToolBar1, java.awt.BorderLayout.NORTH);
        t.add(m);
        jf.pack();
        jf.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
