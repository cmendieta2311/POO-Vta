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

    List<Marca> marcaList;
    private String[] columnNames = new String[]{"ID","Descripcion"};
    
    Class[] columClass = new Class[]{Integer.class,String.class};

    public String getColumnNames(int column) {
        return columnNames[column];
    }
    
    public Class<?> getColumnClass(int column) {
        return columClass[column];
    }
//
//    public void setColumnNames(String[] columnNames) {
//        this.columnNames = columnNames;
//    }

   
    

    public List<Marca> getMarcaList() {
        return marcaList;
    }

    public void setMarcaList(List<Marca> marcaList) {
        this.marcaList = marcaList;
    }

    @Override
    public int getRowCount() {
        return marcaList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Marca marca = marcaList.get(rowIndex);
        
      switch(columnIndex){
          case 0:
              return marca.getId();
          case 1:
              return marca.getDescripcion();
                      
      }
       return "";
    }

}
