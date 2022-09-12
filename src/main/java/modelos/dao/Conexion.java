/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cris_
 */
public class Conexion {
    private String URL = "jdbc:postgresql://localhost:5432/poo";
    private String usuario = "postgres";
    private String contraseña= "12345"; 

    public Conexion(String url, String usuario, String contraseña) {
        this.URL = url;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public Conexion() {
    }
    
    

    public Connection ConectarBD(){
        Connection conexion = null;
        try {
          conexion = DriverManager.getConnection(URL, usuario, contraseña);
          System.out.print("Conexion Exitosa");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
    }
}
