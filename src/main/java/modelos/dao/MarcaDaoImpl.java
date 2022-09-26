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
import modelos.Marca;

/**
 *
 * @author Cris_
 */
public class MarcaDaoImpl implements DAO<Marca> {
    
    private Connection conec;
    private PreparedStatement sentencia;
    
    public MarcaDaoImpl() {
        Conexion conectar = new Conexion();
        conec = conectar.ConectarBD();
    }
    
    @Override
    public void insertar(Marca t) {
        try {
            System.out.println(t);
            String cSQL = "insert into marca (id,descripcion)values(?,?)";
            sentencia = conec.prepareStatement(cSQL);
            sentencia.setInt(1, t.getId());
            sentencia.setString(2, t.getDescripcion());
            int registro = sentencia.executeUpdate();
            System.out.print("Insertar " + registro);
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void modificar(Marca t) {
        try {
            String cSQL="update marca set descripcion=? where id=?";
            sentencia = conec.prepareStatement(cSQL);
            sentencia.setString(1, t.getDescripcion());
            sentencia.setInt(2, t.getId());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void eliminar(Marca t) {
        try {
            String cSQL="delete from marca where id=?";
            sentencia= conec.prepareStatement(cSQL);
            sentencia.setInt(1, t.getId());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Marca> listar() {
        ArrayList<Marca> listaMarca = new ArrayList<>();
        try {
            sentencia = this.conec.prepareStatement("SELECT * FROM public.marca ORDER BY id ASC");
            ResultSet rs = sentencia.executeQuery();
            
            while (rs.next()) {
                Marca m = new Marca();
                m.setId(rs.getInt("id"));
                m.setDescripcion(rs.getString("descripcion"));
                listaMarca.add(m);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaMarca;
    }
    
}
