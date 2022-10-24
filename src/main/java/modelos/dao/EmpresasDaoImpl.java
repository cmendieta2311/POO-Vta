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
import modelos.Empresas;

/**
 *
 * @author Cris_
 */
public class EmpresasDaoImpl implements DAO<Empresas>{
     private Connection conec; // la conexion de la base de datos
    private PreparedStatement sentencia; // preparar sentencia

    public EmpresasDaoImpl() {
         Conexion conectar = new Conexion();
        conec = conectar.ConectarBD();
    }
    
    

    @Override
    public void insertar(Empresas t) {
         try {
            System.out.println(t);
            String cSQL = "insert into empresa values(?,?,?,?)";
            sentencia = conec.prepareStatement(cSQL);
            sentencia.setInt(1, t.getId());
            sentencia.setString(2, t.getNombre().trim());
            sentencia.setString(3, t.getDireccion().trim());
            sentencia.setString(4, t.getTelefono().trim());
            int registro = sentencia.executeUpdate();
            System.out.print("Insertar " + registro);
        } catch (SQLException ex) {
            Logger.getLogger(EmpresasDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificar(Empresas t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Empresas t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Empresas> listar(String valor) {
         ArrayList<Empresas> miLista = new ArrayList<>();
        try {
            sentencia = this.conec.prepareStatement("SELECT * FROM empresa where nombre ilike '%"+valor+"%' ORDER BY id ASC");
            ResultSet rs = sentencia.executeQuery();
            
            while (rs.next()) {
                Empresas m = new Empresas();
                m.setId(rs.getInt("id"));
                m.setNombre(rs.getString("nombre"));
                m.setDireccion(rs.getString("direccion"));
                m.setTelefono(rs.getString("telefono"));
                miLista.add(m);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return miLista;
    }
    
}
