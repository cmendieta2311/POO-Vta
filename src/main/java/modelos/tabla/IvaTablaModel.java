/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.tabla;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelos.Iva;

/**
 *
 * @author Cris_
 */
public class IvaTablaModel extends AbstractTableModel {

    List<Iva> ivaList;
    private String[] columnNames = new String[]{"ID", "Descripcion"};

    Class[] columClass = new Class[]{Integer.class, String.class};
    
    public String getColumnName(int i) {
        return columnNames[i];
    }

    public Class<?> getColumnClass(int column) {
        return columClass[column];
    }
//
//    public void setColumnNames(String[] columnNames) {
//        this.columnNames = columnNames;
//    }

    public List<Iva> getIvaList() {
        return ivaList;
    }

    public void setIvaList(List<Iva> ivaList) {
        this.ivaList = ivaList;
    }

    @Override
    public int getRowCount() {
        return ivaList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public Iva getEntityByRow(int index){
        return ivaList.get(index);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Iva iva = ivaList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return iva.getId();
            case 1:
                return iva.getDescripcion();

        }
        return "";
    }

}
