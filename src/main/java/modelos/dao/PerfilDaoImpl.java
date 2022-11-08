/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Perfil;
import modelos.Perfil;

/**
 *
 * @author Cris_
 */
public class PerfilDaoImpl implements DAO<Perfil>{

  
    private Connection conec; // la conexion de la base de datos
    private PreparedStatement sentencia; // preparar sentencia
    
    public PerfilDaoImpl() {
        Conexion conectar = new Conexion();
        conec = conectar.ConectarBD();
    }
    
    @Override
    public void insertar(Perfil t) {
        try {
            System.out.println(t);
            String cSQL = "insert into perfil (descripcion)values(?)";
            sentencia = conec.prepareStatement(cSQL);
            //sentencia.setInt(1, t.getId());
            sentencia.setString(1, t.getDescripcion());
            int registro = sentencia.executeUpdate();
            System.out.print("Insertar " + registro);
        } catch (SQLException ex) {
            Logger.getLogger(PerfilDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void modificar(Perfil t) {
        try {
            String cSQL="update perfil set descripcion=? where id=?";
            sentencia = conec.prepareStatement(cSQL);
            sentencia.setString(1, t.getDescripcion());
            sentencia.setInt(2, t.getId());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PerfilDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void eliminar(Perfil t) {
        try {
            String cSQL="delete from perfil where id=?";
            sentencia= conec.prepareStatement(cSQL);
            sentencia.setInt(1, t.getId());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PerfilDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Perfil> listar(String valor) {
        ArrayList<Perfil> listaPerfil = new ArrayList<>();
        try {
            sentencia = this.conec.prepareStatement("SELECT * FROM perfil where descripcion ilike '%"+valor+"%' ORDER BY id ASC");
            ResultSet rs = sentencia.executeQuery();
            
            while (rs.next()) {
                Perfil m = new Perfil();
                m.setId(rs.getInt("id"));
                m.setDescripcion(rs.getString("descripcion"));
                listaPerfil.add(m);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PerfilDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPerfil;
    }
    
    
}
