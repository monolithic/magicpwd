/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magicpwd._prop;

import com.magicpwd._comn.S1S1;
import com.magicpwd._face.IPropBean;

/**
 *
 * @author Amon
 */
public class JavaProp extends javax.swing.JPanel implements IPropBean
{

    private java.util.List<S1S1> javaList;

    public JavaProp()
    {
    }

    @Override
    public void initView()
    {
        javax.swing.JScrollPane sp_SkeyList = new javax.swing.JScrollPane();
        tb_JavaList = new javax.swing.JTable();
        sp_SkeyList.setViewportView(tb_JavaList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                sp_SkeyList, javax.swing.GroupLayout.PREFERRED_SIZE, 300, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                sp_SkeyList, javax.swing.GroupLayout.PREFERRED_SIZE, 200, Short.MAX_VALUE));
    }

    @Override
    public void initLang()
    {
    }

    @Override
    public void initData()
    {
        if (javaList == null)
        {
            javaList = new java.util.ArrayList<S1S1>();
            java.util.Properties prop = System.getProperties();
            S1S1 item;
            String[] value =
            {
                "", ""
            };
            for (String key : prop.stringPropertyNames())
            {
                item = new S1S1(key, prop.getProperty(key));
                javaList.add(item);

                if (item.getK().length() > value[0].length())
                {
                    value[0] = item.getV();
                }
                if (item.getV().length() > value[1].length())
                {
                    value[1] = item.getV();
                }
            }
            JavaModel jm = new JavaModel();
            tb_JavaList.setModel(jm);
            tb_JavaList.setRowSorter(new javax.swing.table.TableRowSorter<JavaModel>(jm));
            reSize(jm, value);
        }
    }

    @Override
    public void saveData()
    {
    }

    @Override
    public javax.swing.JPanel getPanel()
    {
        return this;
    }

    private void reSize(JavaModel model, Object[] value)
    {
        javax.swing.table.TableColumn column = null;
        int headerWidth;
        int columnWidth;
        javax.swing.table.TableCellRenderer headerRenderer = tb_JavaList.getTableHeader().getDefaultRenderer();
        javax.swing.table.TableCellRenderer columnRenderer;
        javax.swing.table.TableColumnModel columnModel = tb_JavaList.getColumnModel();
        for (int i = 0, j = columnModel.getColumnCount(); i < j; i++)
        {
            column = columnModel.getColumn(i);

            headerWidth = headerRenderer.getTableCellRendererComponent(tb_JavaList, column.getHeaderValue(), false, false, 0, i).getPreferredSize().width;
            columnRenderer = tb_JavaList.getDefaultRenderer(model.getColumnClass(i));
            columnWidth = columnRenderer.getTableCellRendererComponent(tb_JavaList, value[i], false, false, 0, i).getPreferredSize().width;

            column.setPreferredWidth(Math.max(headerWidth, columnWidth));
        }
    }

    private class JavaModel implements javax.swing.table.TableModel
    {

        @Override
        public void addTableModelListener(javax.swing.event.TableModelListener l)
        {
        }

        @Override
        public Class<?> getColumnClass(int columnIndex)
        {
            return String.class;
        }

        @Override
        public int getColumnCount()
        {
            return 2;
        }

        @Override
        public String getColumnName(int columnIndex)
        {
            return columnIndex == 0 ? "Property" : "Value";
        }

        @Override
        public int getRowCount()
        {
            return javaList.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            S1S1 item = javaList.get(rowIndex);
            return columnIndex == 0 ? item.getK() : item.getV();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex)
        {
            return false;
        }

        @Override
        public void removeTableModelListener(javax.swing.event.TableModelListener l)
        {
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex)
        {
        }
    }
    private javax.swing.JTable tb_JavaList;
}
