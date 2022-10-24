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
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelos.Articulo;
import modelos.Iva;
import modelos.Marca;
import modelos.dao.ArticuloDaoImpl;
import modelos.dao.IvaDaoImpl;
import modelos.dao.MarcaDaoImpl;
import modelos.tabla.ArticuloTablaModel;
import vistas.GUIArticulo;

/**
 *
 * @author Cris_
 */
public class ArticuloController implements ActionListener {
    
    private GUIArticulo gui;
    private ArticuloDaoImpl abm;
    private MarcaDaoImpl abmMarca = new MarcaDaoImpl();
    private IvaDaoImpl abmIva = new IvaDaoImpl();
    
    ArticuloTablaModel modelo = new ArticuloTablaModel();
    private char operacion;
    
    public ArticuloController(GUIArticulo guiarticulo, ArticuloDaoImpl abm) {
        this.gui = guiarticulo;
        this.abm = abm;
        //guiarticulo.setVisible(true);
        //Asignar eventos a botones
        guiarticulo.btnBuscar.addActionListener(this);
        guiarticulo.btnNuevo.addActionListener(this);
        guiarticulo.btnEditar.addActionListener(this);
        guiarticulo.btnEliminar.addActionListener(this);
        guiarticulo.btnGuardar.addActionListener(this);
        guiarticulo.btnCancelar.addActionListener(this);
        System.out.println("Dentro del constructor");
//        //Recuperar lista de articulos
//        List<Articulo> lista = this.abm.listar();
//        //asignar lista de articulo a modelo de tabla
//        modelo.setArticuloList(lista);
//        //vincular modelo con tabla(GUI)
//        llenarTabla(gui.tablaArticulo);
        listar();
        //llenar los combos
        llenarComboMarca(gui.jcboMarca);
        llenarComboIva(gui.jCboIva);
        
        gui.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() > 1) {
                    JTable table = (JTable) evt.getSource();
                    int row = table.rowAtPoint(evt.getPoint());
                    ArticuloTablaModel model = (ArticuloTablaModel) table.getModel();
                    System.out.println(model.getEntityByRow(row));
                    setArticuloForm(model.getEntityByRow(row));
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
                    abm.insertar(getArticuloForm());
                    break;
                case 'E':
                    abm.modificar(getArticuloForm());
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
        List<Articulo> lista = this.abm.listar(valorBuscar);
        //asignar lista de articulo a modelo de tabla
        modelo.setArticuloList(lista);
        //vincular modelo con tabla(GUI)
        llenarTabla(gui.tabla);
    }
    
    public void llenarTabla(JTable tabla) {
        
        gui.tabla.setModel(modelo);
        gui.tabla.updateUI();
        
    }
    
    private void habilitarCampos(boolean h) {
        //gui.txt_id.setEnabled(h);
        gui.txt_descripcion.setEnabled(h);
        gui.txt_costo.setEnabled(h);
        gui.txt_venta.setEnabled(h);
        gui.jcboMarca.setEnabled(h);
        gui.jCboIva.setEnabled(h);
        
    }
    
    private void habilitarBotones(boolean h) {
        gui.btnGuardar.setEnabled(h);
        gui.btnCancelar.setEnabled(h);
        
    }
    
    private void limpiar() {
        gui.txt_id.setText("");
        gui.txt_descripcion.setText("");
        gui.txt_costo.setText("");
        gui.txt_venta.setText("");
    }
    
    private Articulo getArticuloForm() {
        Articulo articulo = new Articulo();
        articulo.setId(Integer.valueOf(gui.txt_id.getText()));
        articulo.setDescripcion(gui.txt_descripcion.getText());
        articulo.setMarca((Marca) gui.jcboMarca.getSelectedItem());
        articulo.setIva((Iva) gui.jCboIva.getSelectedItem());
        articulo.setPrecio_costo(Integer.valueOf(gui.txt_costo.getText()));
        articulo.setPrecio_venta(Integer.valueOf(gui.txt_venta.getText()));
        return articulo;
    }

    //Asignar valores a la vista a partir del Modelo Articulo
    private void setArticuloForm(Articulo m) {
        gui.txt_id.setText(m.getId().toString());
        gui.txt_descripcion.setText(m.getDescripcion());
        gui.jcboMarca.setSelectedItem(m.getMarca());
        gui.jCboIva.setSelectedItem(m.getIva());
        gui.txt_costo.setText(m.getPrecio_costo().toString());
        gui.txt_venta.setText(m.getPrecio_venta().toString());
        
    }
    
    private void llenarComboMarca(JComboBox cbo) {
        DefaultComboBoxModel<Marca> model = new DefaultComboBoxModel();
        List<Marca> lista = abmMarca.listar("");
        for (int i = 0; i < lista.size(); i++) {
            Marca marca = lista.get(i);
            model.addElement(marca);
        }
        cbo.setModel(model);
        
    }
    
    private void llenarComboIva(JComboBox cbo) {
        DefaultComboBoxModel<Iva> model = new DefaultComboBoxModel();
        List<Iva> lista = abmIva.listar("");
        for (int i = 0; i < lista.size(); i++) {
            Iva iva = lista.get(i);
            model.addElement(iva);
        }
        cbo.setModel(model);
        
    }
    
    private boolean validarDatos() {
        boolean vacio = false;
        if (gui.txt_id.getText().isEmpty()) {
            gui.txt_id.setText("0");
        }
        if (gui.txt_descripcion.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.txt_costo.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.txt_venta.getText().isEmpty()) {
            vacio = true;
        }
        return vacio;
        
    }
    
}
