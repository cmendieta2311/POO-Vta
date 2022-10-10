/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelos.dao.MarcaDaoImpl;
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
        gui.mMarca.addActionListener(this);
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
            MarcaController vistaMarca = new MarcaController(gui, abm);
            vistaMarca.mostrarVentana();
        }

    }

}
