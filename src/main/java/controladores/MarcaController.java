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
        guimarca.btnListar.addActionListener(this);
        System.out.println("Dentro del constructor");
        //Recuperar lista de marcas
        List<Marca> lista = this.abm.listar();
        //asignar lista de marca a modelo de tabla
        modelo.setMarcaList(lista);
        //vincular modelo con tabla(GUI)
        llenarTabla(gui.tablaMarca);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("soy un evento");
        
        System.out.print("modelo de tabla"+modelo);
        
      
//        for (int i = 0; i < lista.size(); i++) {
//            System.out.println("recorrer lista "+lista.get(i).getId() + " - " + lista.get(i).getDescripcion());
//        }
        
    }
    
    public void mostrarVentana() {
        gui.setVisible(true);
    }
    
    public void llenarTabla(JTable tabla) {
        
        gui.tablaMarca.setModel(modelo);
        
    }
    
}
