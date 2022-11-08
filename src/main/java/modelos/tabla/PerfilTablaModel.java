/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.tabla;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelos.Perfil;

/**
 *
 * @author Cris_
 */
public class PerfilTablaModel extends AbstractTableModel {

    List<Perfil> lista; // difinimos la lista 
    private String[] columnNames = new String[]{"ID", "Descripcion"}; // definimos el nombre de las columnas

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

    public List<Perfil> getPerfilList() {
        return lista;
    }

    public void setPerfilList(List<Perfil> marcaList) {
        this.lista = marcaList;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public Perfil getEntityByRow(int index){
        return lista.get(index);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Perfil item = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return item.getId();
            case 1:
                return item.getDescripcion();

        }
        return "";
    }

}
