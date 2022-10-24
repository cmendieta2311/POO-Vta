/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.tabla;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelos.Empresas;

/**
 *
 * @author Cris_
 */
public class EmpresasTablaModel extends AbstractTableModel {

    List<Empresas> lista; // difinimos la lista 
    private String[] columnNames = new String[]{"ID", "Nombre", "Direccion", "Telefono"}; // definimos el nombre de las columnas

    Class[] columClass = new Class[]{Integer.class, String.class, String.class, String.class}; // los tipos de datos 

    public String getColumnName(int i) {
        return columnNames[i];
    }

    public Class<?> getColumnClass(int column) {
        return columClass[column];
    }

    public List<Empresas> getMarcaList() {
        return lista;
    }

    public void setMarcaList(List<Empresas> List) {
        this.lista = List;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public Empresas getEntityByRow(int index) {
        return lista.get(index);
    }

    //definir valor a mostrar
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Empresas item = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return item.getId();
            case 1:
                return item.getNombre();
            case 2:
                return item.getDireccion();
            case 3:
                return item.getTelefono();

        }
        return "";
    }

}
