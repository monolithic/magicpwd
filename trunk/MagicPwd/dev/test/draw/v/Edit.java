/*
 * Data.java
 *
 * Created on 2009-11-17, 9:39:19
 */

package test.draw.v;

/**
 *
 * @author Amon
 */
public class Edit extends javax.swing.JPanel {

    /** Creates new form Data */
    public Edit() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt_DropData = new javax.swing.JButton();
        bt_SaveData = new javax.swing.JButton();
        bt_CopyData = new javax.swing.JButton();

        bt_DropData.setText("D");

        bt_SaveData.setText("S");

        bt_CopyData.setText("C");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_DropData, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bt_SaveData, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bt_CopyData, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(bt_CopyData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_SaveData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_DropData))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_CopyData;
    private javax.swing.JButton bt_DropData;
    private javax.swing.JButton bt_SaveData;
    // End of variables declaration//GEN-END:variables

}