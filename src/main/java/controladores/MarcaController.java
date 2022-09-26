/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelos.Marca;
import modelos.dao.MarcaDaoImpl;
import modelos.tabla.MarcaTablaModel;
import vistas.GUIMarca;

/**
 *
 * @author Cris_
 */
public class MarcaController implements ActionListener {

    private GUIMarca gui;
    private MarcaDaoImpl abm;
    MarcaTablaModel modelo = new MarcaTablaModel();

    public MarcaController(GUIMarca guimarca, MarcaDaoImpl abm) {
        this.gui = guimarca;
        this.abm = abm;
        //guimarca.setVisible(true);
        //Asignar eventos a botones
        guimarca.btnBuscar.addActionListener(this);
        guimarca.btnNuevo.addActionListener(this);
        guimarca.btnEditar.addActionListener(this);
        guimarca.btnEliminar.addActionListener(this);
        guimarca.btnGuardar.addActionListener(this);
        guimarca.btnCancelar.addActionListener(this);
        System.out.println("Dentro del constructor");
        //Recuperar lista de marcas
        List<Marca> lista = this.abm.listar();
        //asignar lista de marca a modelo de tabla
        modelo.setMarcaList(lista);
        //vincular modelo con tabla(GUI)
        llenarTabla(gui.tablaMarca);
        
        gui.tablaMarca.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() > 1) {
                JTable table = (JTable) evt.getSource();
                int row = table.rowAtPoint(evt.getPoint());
                MarcaTablaModel model = (MarcaTablaModel) table.getModel();
                System.out.println(model.getEntityByRow(row));
                setMarcaForm(model.getEntityByRow(row));
                JOptionPane.showMessageDialog(table.getParent(), model.getEntityByRow(row));
            }
        }
    });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gui.btnNuevo) {
            System.out.println("Soy boton nuevo");
            habilitarCampos(true);
            habilitarBotones(true);
        }

        if (e.getSource() == gui.btnEditar) {
            System.out.println("Soy boton Editar");
        }

        if (e.getSource() == gui.btnEliminar) {
            int select = gui.tablaMarca.getSelectedRow();
            if(select>0){
                
                abm.eliminar(modelo.getEntityByRow(select));
            }else{
                JOptionPane.showMessageDialog(gui, "Debe seleccionar una fila");
            }
        }

        if (e.getSource() == gui.btnBuscar) {
            System.out.println("Boton buscar");
            //Recuperar lista de marcas
            List<Marca> lista = this.abm.listar();
            //asignar lista de marca a modelo de tabla
            modelo.setMarcaList(lista);
            //vincular modelo con tabla(GUI)
            llenarTabla(gui.tablaMarca);
        }

        if (e.getSource() == gui.btnCancelar) {
            habilitarCampos(false);
            habilitarBotones(false);
            limpiar();
        }

        if (e.getSource() == gui.btnGuardar) {

            abm.insertar(getMarcaForm());
            habilitarCampos(false);
            habilitarBotones(false);
            limpiar();
        }
    }

    public void mostrarVentana() {
        gui.setVisible(true);
    }

    public void llenarTabla(JTable tabla) {

        gui.tablaMarca.setModel(modelo);
        gui.tablaMarca.updateUI();

    }

    private void habilitarCampos(boolean h) {
        gui.txt_id.setEnabled(h);
        gui.txt_descripcion.setEnabled(h);
    }

    private void habilitarBotones(boolean h) {
        gui.btnGuardar.setEnabled(h);
        gui.btnCancelar.setEnabled(h);
    }

    private void limpiar() {
        gui.txt_id.setText("");
        gui.txt_descripcion.setText("");
    }

    private Marca getMarcaForm() {
        Marca marca = new Marca();
        marca.setId(Integer.valueOf(gui.txt_id.getText()));
        marca.setDescripcion(gui.txt_descripcion.getText());
        return marca;
    }
    
    private void setMarcaForm(Marca m){
        gui.txt_id.setText(m.getId().toString());
        gui.txt_descripcion.setText(m.getDescripcion());
        
    }

}
