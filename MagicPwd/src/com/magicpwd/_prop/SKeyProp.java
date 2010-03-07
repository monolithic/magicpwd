/**
 * 
 */
package com.magicpwd._prop;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.magicpwd._comn.S1S2;
import com.magicpwd._cons.LangRes;
import com.magicpwd._face.IPropBean;
import com.magicpwd._util.Lang;

/**
 * @author Amon
 * 
 */
public class SKeyProp extends JPanel implements IPropBean
{

    private List<S1S2> skeyList;

    public SKeyProp()
    {
    }

    @Override
    public void initView()
    {
        javax.swing.JScrollPane sp_SkeyList = new javax.swing.JScrollPane();
        tb_SkeyList = new javax.swing.JTable();
        sp_SkeyList.setViewportView(tb_SkeyList);

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
        skeyList = new ArrayList<S1S2>();
        int size = Integer.parseInt(Lang.getLang(LangRes.P30F2B00, "0"), 16);
        int indx = 3;
        S1S2 item;
        while (indx < size)
        {
            item = new S1S2();
            item.setV(Lang.getLang("P30F" + Integer.toHexString(11008 + (indx++)).toUpperCase(), ""));
            item.setV2(Lang.getLang("P30F" + Integer.toHexString(11008 + (indx++)).toUpperCase(), ""));
            skeyList.add(item);
        }
        SkeyModel sm = new SkeyModel();
        tb_SkeyList.setModel(sm);
    }

    @Override
    public JPanel getPanel()
    {
        return this;
    }

    private class SkeyModel implements TableModel
    {

        @Override
        public void addTableModelListener(TableModelListener l)
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
            return columnIndex == 0 ? Lang.getLang(LangRes.P30F2B01, "Key") : Lang.getLang(LangRes.P30F2B02, "Desp");
        }

        @Override
        public int getRowCount()
        {
            return skeyList.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            S1S2 item = skeyList.get(rowIndex);
            return columnIndex == 0 ? item.getV() : item.getV2();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex)
        {
            return false;
        }

        @Override
        public void removeTableModelListener(TableModelListener l)
        {
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex)
        {
        }
    }
    private javax.swing.JTable tb_SkeyList;
}
