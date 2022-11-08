/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.tabla;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelos.Usuario;

/**
 *
 * @author Cris_
 */
public class UsuarioTablaModel extends AbstractTableModel {

    List<Usuario> lista; // difinimos la lista 
    private String[] columnNames = new String[]{"ID", "Nombre", "Apellido", "Empresa", "Perfil"}; // definimos el nombre de las columnas

    Class[] columClass = new Class[]{Integer.class, String.class, String.class, String.class, String.class};

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

    public List<Usuario> getUsuarioList() {
        return lista;
    }

    public void setUsuarioList(List<Usuario> marcaList) {
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

    public Usuario getEntityByRow(int index) {
        return lista.get(index);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario item = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return item.getId();
            case 1:
                return item.getNombre();
            case 2:
                return item.getApellido();
            case 3:
                return item.getEmpresa().getNombre();
            case 4:
                return item.getPerfil().getDescripcion();

        }
        return "";
    }

}
