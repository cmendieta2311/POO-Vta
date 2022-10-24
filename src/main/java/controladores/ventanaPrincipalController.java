/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelos.dao.ArticuloDaoImpl;
import modelos.dao.EmpresasDaoImpl;
import modelos.dao.IvaDaoImpl;
import modelos.dao.MarcaDaoImpl;
import vistas.GUIArticulo;
import vistas.GUIEmpresa;
//import vistas.GUIIva;
import vistas.GUIMarca;
import vistas.VentanaPrincipal;

/**
 *
 * @author Cris_
 */
public class ventanaPrincipalController implements ActionListener {

    private VentanaPrincipal gui;

    public ventanaPrincipalController(VentanaPrincipal vista) {
        gui = vista;
        //definiomos los eventos que vamos escuchar 
        gui.mMarca.addActionListener(this);
        gui.mIva.addActionListener(this);
        gui.menuEmpresa.addActionListener(this);
        gui.mProducto.addActionListener(this);
    }

    public void mostrarVentana() {
        gui.setVisible(true);
        gui.setLocationRelativeTo(gui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gui.mMarca) {
            MarcaDaoImpl abm = new MarcaDaoImpl();
            GUIMarca gui = new GUIMarca(null, true);
            gui.titulo.setText("Datos de marca");
            MarcaController vistaMarca = new MarcaController(gui, abm);
            vistaMarca.mostrarVentana();
        }
        
        if (e.getSource() == gui.mIva) {
            IvaDaoImpl abm = new IvaDaoImpl();
//            GUIIva gui = new GUIIva(null, true);
            GUIMarca gui = new GUIMarca(null, true);
            gui.titulo.setText("Datos de iva");
            IvaController vista = new IvaController(gui, abm);
            vista.mostrarVentana();
        }
        
        if(e.getSource()==gui.menuEmpresa){
            EmpresasDaoImpl abm= new EmpresasDaoImpl();
            GUIEmpresa gui = new GUIEmpresa(null, true);
            EmpresasController controller= new EmpresasController(gui, abm);
            controller.mostrarVentana();
            
        }
        
         if(e.getSource()==gui.mProducto){
            ArticuloDaoImpl abm= new ArticuloDaoImpl();
            GUIArticulo gui = new GUIArticulo(null, true);
            ArticuloController controller= new ArticuloController(gui, abm);
            controller.mostrarVentana();
            
        }
    }

}
