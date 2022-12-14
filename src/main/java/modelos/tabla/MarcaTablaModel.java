/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.tabla;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelos.Marca;

/**
 *
 * @author Cris_
 */
public class MarcaTablaModel extends AbstractTableModel {

    List<Marca> lista; // difinimos la lista 
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

    public List<Marca> getMarcaList() {
        return lista;
    }

    public void setMarcaList(List<Marca> marcaList) {
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
    
    public Marca getEntityByRow(int index){
        return lista.get(index);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Marca marca = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return marca.getId();
            case 1:
                return marca.getDescripcion();

        }
        return "";
    }

}
