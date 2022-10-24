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
import modelos.Iva;

/**
 *
 * @author Cris_
 */
public class IvaDaoImpl implements DAO<Iva> {
    
    private Connection conec;
    private PreparedStatement sentencia;
    
    public IvaDaoImpl() {
        Conexion conectar = new Conexion();
        conec = conectar.ConectarBD();
    }
    
    @Override
    public void insertar(Iva t) {
        try {
            System.out.println(t);
            String cSQL = "insert into iva (id,descripcion)values(?,?)";
            sentencia = conec.prepareStatement(cSQL);
            sentencia.setInt(1, t.getId());
            sentencia.setString(2, t.getDescripcion());
            int registro = sentencia.executeUpdate();
            System.out.print("Insertar " + registro);
        } catch (SQLException ex) {
            Logger.getLogger(IvaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void modificar(Iva t) {
        try {
            String cSQL="update iva set descripcion=? where id=?";
            sentencia = conec.prepareStatement(cSQL);
            sentencia.setString(1, t.getDescripcion());
            sentencia.setInt(2, t.getId());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IvaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void eliminar(Iva t) {
        try {
            String cSQL="delete from iva where id=?";
            sentencia= conec.prepareStatement(cSQL);
            sentencia.setInt(1, t.getId());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IvaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Iva> listar(String valor) {
        ArrayList<Iva> listaIva = new ArrayList<>();
        try {
            sentencia = this.conec.prepareStatement("SELECT * FROM iva where descripcion ilike '%"+valor+"%' ORDER BY id ASC");
            ResultSet rs = sentencia.executeQuery();
            
            while (rs.next()) {
                Iva m = new Iva();
                m.setId(rs.getInt("id"));
                m.setDescripcion(rs.getString("descripcion"));
                listaIva.add(m);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IvaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaIva;
    }
    
}
