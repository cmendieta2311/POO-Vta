/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
import modelos.Empresas;
import modelos.dao.EmpresasDaoImpl;
import modelos.tabla.EmpresasTablaModel;
import vistas.GUIEmpresa;

/**
 *
 * @author Cris_
 */
public class EmpresasController implements ActionListener {

    GUIEmpresa gui;
    EmpresasDaoImpl abm;
    EmpresasTablaModel model;

    public EmpresasController(GUIEmpresa gui, EmpresasDaoImpl abm) {
        this.gui = gui;
        this.abm = abm;
        model = new EmpresasTablaModel();
        //escuchar eventos de la vista
        gui.btnBuscar.addActionListener(this);
        gui.btnNuevo.addActionListener(this);
        gui.btnEditar.addActionListener(this);
        gui.btnEliminar.addActionListener(this);
        gui.btnGuardar.addActionListener(this);
        gui.btnCancelar.addActionListener(this);

        listar();
    }

    public void mostrarVentana() {
        gui.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gui.btnNuevo) {
            System.out.println("se presiona boton nuevo");
            habilitarCampos(true);
            habilitarBotones(true);
            limpiar();
        }

        if (e.getSource() == gui.btnCancelar) {
            System.out.println("se presiona boton nuevo");
            habilitarCampos(false);
            habilitarBotones(false);
        }

        if (e.getSource() == gui.btnGuardar) {
            abm.insertar(getEmpresaForm());
            listar();
            limpiar();
        }

    }

    public void listar() {
        String valorBuscar = "";
        if (gui.txt_buscar.getText().isEmpty()) {
            valorBuscar = "";
        } else {
            valorBuscar = gui.txt_buscar.getText().trim();
        }
        List<Empresas> lista = this.abm.listar(valorBuscar);
        //asignar lista de marca a modelo de tabla
        model.setMarcaList(lista);
        //vincular modelo con tabla(GUI)
        llenarTabla(gui.tabla);
    }

    public void llenarTabla(JTable tabla) {
        gui.tabla.setModel(model);
        gui.tabla.updateUI();
    }

    //Habilitar campos en la vista
    private void habilitarCampos(boolean h) {
        gui.txt_id.setEnabled(h);
        gui.txt_nombre.setEnabled(h);
        gui.txt_direccion.setEnabled(h);
        gui.txt_telefono.setEnabled(h);
    }

    private void habilitarBotones(boolean h) {
        gui.btnGuardar.setEnabled(h);
        gui.btnCancelar.setEnabled(h);
    }

    private Empresas getEmpresaForm() {
        Empresas empresa = new Empresas();
        empresa.setId(Integer.valueOf(gui.txt_id.getText()));
        empresa.setNombre(gui.txt_nombre.getText());
        empresa.setDireccion(gui.txt_direccion.getText());
        empresa.setTelefono(gui.txt_telefono.getText());
        return empresa;
    }

    private void limpiar() {
        gui.txt_id.setText("");
        gui.txt_nombre.setText("");
        gui.txt_direccion.setText("");
        gui.txt_telefono.setText("");
    }

}
