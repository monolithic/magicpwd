/*
 *  Copyright (C) 2011 Aven
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

/*
 * Interval.java
 *
 * Created on 2011-4-16, 15:22:35
 */

package dev.x.task;

/**
 *
 * @author Aven
 */
public class Interval extends javax.swing.JPanel {

    /** Creates new form Interval */
    public Interval() {
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

        lbFtime = new javax.swing.JLabel();
        spFtime = new javax.swing.JSpinner();
        btFtime = new javax.swing.JButton();
        lbTtime = new javax.swing.JLabel();
        spTtime = new javax.swing.JSpinner();
        btTtime = new javax.swing.JButton();
        lbStime = new javax.swing.JLabel();
        spStime = new javax.swing.JSpinner();
        btStime = new javax.swing.JButton();
        lbIntval = new javax.swing.JLabel();
        cbIntval = new javax.swing.JComboBox();
        lbEnum = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lsEnum = new javax.swing.JList();

        lbFtime.setLabelFor(spFtime);
        lbFtime.setText("起始时间(T)");

        spFtime.setModel(new javax.swing.SpinnerDateModel());

        btFtime.setText(">");

        lbTtime.setLabelFor(spTtime);
        lbTtime.setText("jLabel2");

        spTtime.setModel(new javax.swing.SpinnerDateModel());

        btTtime.setText(">");

        lbStime.setLabelFor(spStime);
        lbStime.setText("jLabel3");

        spStime.setModel(new javax.swing.SpinnerDateModel());

        btStime.setText(">");

        lbIntval.setLabelFor(cbIntval);
        lbIntval.setText("jLabel4");

        cbIntval.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbEnum.setLabelFor(lsEnum);
        lbEnum.setText("jLabel1");

        lsEnum.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lsEnum.setVisibleRowCount(3);
        jScrollPane1.setViewportView(lsEnum);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbEnum)
                    .addComponent(lbFtime)
                    .addComponent(lbTtime)
                    .addComponent(lbStime)
                    .addComponent(lbIntval))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(spFtime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btFtime))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(spTtime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btTtime))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(spStime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btStime))
                    .addComponent(cbIntval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFtime)
                    .addComponent(spFtime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFtime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTtime)
                    .addComponent(spTtime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTtime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbStime)
                    .addComponent(spStime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btStime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbIntval)
                    .addComponent(cbIntval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEnum)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFtime;
    private javax.swing.JButton btStime;
    private javax.swing.JButton btTtime;
    private javax.swing.JComboBox cbIntval;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbEnum;
    private javax.swing.JLabel lbFtime;
    private javax.swing.JLabel lbIntval;
    private javax.swing.JLabel lbStime;
    private javax.swing.JLabel lbTtime;
    private javax.swing.JList lsEnum;
    private javax.swing.JSpinner spFtime;
    private javax.swing.JSpinner spStime;
    private javax.swing.JSpinner spTtime;
    // End of variables declaration//GEN-END:variables

}
