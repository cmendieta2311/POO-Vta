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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelos.Empresa;
import modelos.Marca;
import modelos.Perfil;
import modelos.Usuario;
import modelos.dao.EmpresaDaoImpl;
import modelos.dao.PerfilDaoImpl;
import modelos.dao.UsuarioDaoImpl;
import modelos.tabla.UsuarioTablaModel;
import vistas.GUIUsuario;

/**
 *
 * @author Cris_
 */
public class UsuarioController implements ActionListener {

    private GUIUsuario gui;
    private UsuarioDaoImpl abm;
    UsuarioTablaModel modelo = new UsuarioTablaModel();
    private char operacion;
    private EmpresaDaoImpl abmEmpresa = new EmpresaDaoImpl();
    private PerfilDaoImpl abmPerfil = new PerfilDaoImpl();

    public UsuarioController(GUIUsuario guiitem, UsuarioDaoImpl abm) {
        this.gui = guiitem;
        this.abm = abm;
        //guiitem.setVisible(true);
        //Asignar eventos a botones
        guiitem.btnBuscar.addActionListener(this);
        guiitem.btnNuevo.addActionListener(this);
        guiitem.btnEditar.addActionListener(this);
        guiitem.btnEliminar.addActionListener(this);
        guiitem.btnGuardar.addActionListener(this);
        guiitem.btnCancelar.addActionListener(this);
        System.out.println("Dentro del constructor");
//        //Recuperar lista de items
//        List<Usuario> lista = this.abm.listar();
//        //asignar lista de item a modelo de tabla
//        modelo.setUsuarioList(lista);
//        //vincular modelo con tabla(GUI)
//        llenarTabla(gui.tablaUsuario);
        listar();
        llenarComboEmpresa(gui.jcbo_Empresa);
        llenarComboPerfil(gui.jcboPerfil);

        gui.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() > 1) {
                    JTable table = (JTable) evt.getSource();
                    int row = table.rowAtPoint(evt.getPoint());
                    UsuarioTablaModel model = (UsuarioTablaModel) table.getModel();
                    System.out.println(model.getEntityByRow(row));
                    setUsuarioForm(model.getEntityByRow(row));
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
            gui.txt_usuario.setEnabled(false);
            gui.txt_contraseña.setEnabled(false);
        }

        if (e.getSource() == gui.btnEliminar) {
            int select = gui.tabla.getSelectedRow();
            if (select >= 0) {
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
            boolean v_control = validarDatos();
            if (v_control == true) {
                JOptionPane.showMessageDialog(gui, "Favor completar los datos");
                return;
            }
            switch (operacion) {
                case 'N':
                    abm.insertar(getUsuarioForm());
                    break;
                case 'E':
                    abm.modificar(getUsuarioForm());
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
        List<Usuario> lista = this.abm.listar(valorBuscar);
        //asignar lista de item a modelo de tabla
        modelo.setUsuarioList(lista);
        //vincular modelo con tabla(GUI)
        llenarTabla(gui.tabla);
    }

    public void llenarTabla(JTable tabla) {

        gui.tabla.setModel(modelo);
        gui.tabla.updateUI();

    }

    private void habilitarCampos(boolean h) {
        //gui.txt_id.setEnabled(h);
        gui.txt_nombre.setEnabled(h);
        gui.txt_apellido.setEnabled(h);
        gui.txt_usuario.setEnabled(h);
        gui.txt_contraseña.setEnabled(h);
        gui.jcbo_Empresa.setEnabled(h);
        gui.jcboPerfil.setEnabled(h);
    }

    private void habilitarBotones(boolean h) {
        gui.btnGuardar.setEnabled(h);
        gui.btnCancelar.setEnabled(h);
    }

    private void limpiar() {
        gui.txt_id.setText("");
        gui.txt_nombre.setText("");
        gui.txt_apellido.setText("");
        gui.txt_usuario.setText("");
        gui.txt_contraseña.setText("");
    }

    private Usuario getUsuarioForm() {
        Usuario item = new Usuario();
        Empresa e = new Empresa();
        Perfil p = new Perfil();

        item.setId(Integer.valueOf(gui.txt_id.getText()));
        item.setNombre(gui.txt_nombre.getText().trim());
        item.setApellido(gui.txt_apellido.getText().trim());
        item.setUsuario(gui.txt_usuario.getText().trim());
        item.setContraseña(gui.txt_contraseña.getText().trim());
        item.setEmpresa((Empresa) gui.jcbo_Empresa.getSelectedItem());
        item.setPerfil((Perfil) gui.jcboPerfil.getSelectedItem());
        return item;
    }

    //Asignar valores a la vista a partir del Modelo Usuario
    private void setUsuarioForm(Usuario m) {
        gui.txt_id.setText(m.getId().toString());
        gui.txt_nombre.setText(m.getNombre().trim());
        gui.txt_apellido.setText(m.getApellido().trim());
        gui.txt_usuario.setText(m.getUsuario().trim());
        gui.txt_contraseña.setText(m.getContraseña().trim());
        gui.jcbo_Empresa.setSelectedItem(m.getEmpresa());
        gui.jcboPerfil.setSelectedItem(m.getPerfil());

    }

    private boolean validarDatos() {
        boolean vacio = false;
        if (gui.txt_id.getText().isEmpty()) {
            gui.txt_id.setText("0");
        }
        if (gui.txt_nombre.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.txt_apellido.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.txt_usuario.getText().isEmpty()) {
            vacio = true;
        }
        return vacio;
    }

    private void llenarComboEmpresa(JComboBox cbo) {
        DefaultComboBoxModel<Empresa> model = new DefaultComboBoxModel();
        List<Empresa> lista = abmEmpresa.listar("");
        for (int i = 0; i < lista.size(); i++) {
            Empresa empresa = lista.get(i);
            model.addElement(empresa);
        }
        cbo.setModel(model);
    }

    private void llenarComboPerfil(JComboBox cbo) {
        DefaultComboBoxModel<Perfil> model = new DefaultComboBoxModel();
        List<Perfil> lista = abmPerfil.listar("");
        for (int i = 0; i < lista.size(); i++) {
            Perfil empresa = lista.get(i);
            model.addElement(empresa);
        }
        cbo.setModel(model);
    }

}
