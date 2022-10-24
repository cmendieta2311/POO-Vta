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
import modelos.Iva;
import modelos.dao.IvaDaoImpl;
import modelos.tabla.IvaTablaModel;
import vistas.GUIMarca;

/**
 *
 * @author Cris_
 */
public class IvaController implements ActionListener {

    private GUIMarca gui;
    private IvaDaoImpl abm;
    IvaTablaModel modelo = new IvaTablaModel();
    private char operacion;

    public IvaController(GUIMarca guimarca, IvaDaoImpl abm) {
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
//        //Recuperar lista de marcas
//        List<Iva> lista = this.abm.listar();
//        //asignar lista de marca a modelo de tabla
//        modelo.setIvaList(lista);
//        //vincular modelo con tabla(GUI)
//        llenarTabla(gui.tabla);
        listar();

        gui.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() > 1) {
                    JTable table = (JTable) evt.getSource();
                    int row = table.rowAtPoint(evt.getPoint());
                    IvaTablaModel model = (IvaTablaModel) table.getModel();
                    System.out.println(model.getEntityByRow(row));
                    setIvaForm(model.getEntityByRow(row));
                    //JOptionPane.showMessageDialog(table.getParent(), model.getEntityByRow(row));
                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gui.btnNuevo) {
            operacion = 'N';
            System.out.println("Soy boton nuevo");
            habilitarCampos(true);
            habilitarBotones(true);
            limpiar();
        }

        if (e.getSource() == gui.btnEditar) {
            operacion = 'E';
            System.out.println("Soy boton Editar");
            habilitarCampos(true);
            habilitarBotones(true);
        }

        if (e.getSource() == gui.btnEliminar) {
            int select = gui.tabla.getSelectedRow();
            if (select > 0) {
                int ok = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar el registro?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                System.out.print("Boton de confirmacion" + ok);
                if (ok == 0) {
                    abm.eliminar(modelo.getEntityByRow(select));
                    listar();
                }

            } else {
                JOptionPane.showMessageDialog(gui, "Debe seleccionar una fila");
            }
        }

        if (e.getSource() == gui.btnBuscar) {
            System.out.println("Boton buscar");
            listar();
        }

        if (e.getSource() == gui.btnCancelar) {
            habilitarCampos(false);
            habilitarBotones(false);
            limpiar();
        }

        if (e.getSource() == gui.btnGuardar) {
            switch (operacion) {
                case 'N':
                    abm.insertar(getIvaForm());
                    break;
                case 'E':
                    abm.modificar(getIvaForm());
                    break;
            }
            habilitarCampos(false);
            habilitarBotones(false);
            limpiar();
            listar();

        }
    }

    public void mostrarVentana() {
        gui.setVisible(true);
    }

    public void listar() {
        String valorBuscar = "";
        if (gui.txt_buscar.getText().isEmpty()) {
            valorBuscar = "";
        } else {
            valorBuscar = gui.txt_buscar.getText().trim();
        }
        List<Iva> lista = this.abm.listar(valorBuscar);
        //asignar lista de marca a modelo de tabla
        modelo.setIvaList(lista);
        //vincular modelo con tabla(GUI)
        llenarTabla(gui.tabla);
    }

    public void llenarTabla(JTable tabla) {

        gui.tabla.setModel(modelo);
        gui.tabla.updateUI();

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

    private Iva getIvaForm() {
        Iva marca = new Iva();
        marca.setId(Integer.valueOf(gui.txt_id.getText()));
        marca.setDescripcion(gui.txt_descripcion.getText());
        return marca;
    }

    //Asignar valores a la vista a partir del Modelo Iva
    private void setIvaForm(Iva m) {
        gui.txt_id.setText(m.getId().toString());
        gui.txt_descripcion.setText(m.getDescripcion());

    }

}
