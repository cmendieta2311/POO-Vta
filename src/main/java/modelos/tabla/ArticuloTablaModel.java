/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.tabla;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelos.Articulo;

/**
 *
 * @author Cris_
 */
public class ArticuloTablaModel extends AbstractTableModel {

    List<Articulo> lista; // difinimos la lista 
    private String[] columnNames = new String[]{"ID", "Descripcion", "Marca", "P. Costo", "P. Venta"}; // definimos el nombre de las columnas

    Class[] columClass = new Class[]{Integer.class, String.class, String.class, Integer.class, Integer.class};

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

    public List<Articulo> getArticuloList() {
        return lista;
    }

    public void setArticuloList(List<Articulo> articuloList) {
        this.lista = articuloList;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public Articulo getEntityByRow(int index) {
        return lista.get(index);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Articulo articulo = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return articulo.getId();
            case 1:
                return articulo.getDescripcion();
            case 2:
                return articulo.getMarca().getDescripcion();
            case 3:
                return articulo.getPrecio_costo();
            case 4:
                return articulo.getPrecio_venta();

        }
        return "";
    }

}
