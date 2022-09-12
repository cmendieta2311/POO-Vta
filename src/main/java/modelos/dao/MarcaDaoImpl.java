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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Marca t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Marca eliminar(Marca t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
