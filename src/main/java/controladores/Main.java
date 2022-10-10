package controladores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import modelos.dao.MarcaDaoImpl;
import vistas.GUIMarca;

/**
 *
 * @author Cris_
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MarcaDaoImpl abm = new MarcaDaoImpl();
        GUIMarca gui = new GUIMarca(null, true);
        MarcaController vistaMarca = new MarcaController(gui,abm);
        vistaMarca.mostrarVentana();
    }
    
}
