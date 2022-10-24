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
import modelos.Articulo;
import modelos.Articulo;
import modelos.Iva;
import modelos.Marca;

/**
 *
 * @author Cris_
 */
public class ArticuloDaoImpl implements DAO<Articulo> {

    private Connection conec; // la conexion de la base de datos
    private PreparedStatement sentencia; // preparar sentencia

    public ArticuloDaoImpl() {
        Conexion conectar = new Conexion();
        conec = conectar.ConectarBD();
    }

    @Override
    public void insertar(Articulo t) {
        try {
            System.out.println(t);
            String cSQL = "insert into articulo (descripcion,idmarca,precio_costo,precio_venta,idiva) \n"
                    + "values(?,?,?,?,?)";
            sentencia = conec.prepareStatement(cSQL);
            //sentencia.setInt(1, t.getId());
            sentencia.setString(1, t.getDescripcion());
            sentencia.setInt(2, t.getMarca().getId());
            sentencia.setInt(3, t.getPrecio_costo());
            sentencia.setInt(4, t.getPrecio_venta());
            sentencia.setInt(5, t.getIva().getId());
            int registro = sentencia.executeUpdate();
            System.out.print("Insertar " + registro);
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificar(Articulo t) {
        try {
            String cSQL = "update articulo set idmarca=?,descripcion=?, precio_costo=?,precio_venta=?,idiva=?\n"
                    + "where id=?";
            sentencia = conec.prepareStatement(cSQL);
            sentencia.setInt(1, t.getMarca().getId());
            sentencia.setString(2, t.getDescripcion().trim());
            sentencia.setInt(3, t.getPrecio_costo());
            sentencia.setInt(4, t.getPrecio_venta());
            sentencia.setInt(5, t.getIva().getId());
            sentencia.setInt(6, t.getId());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void eliminar(Articulo t) {
        try {
            String cSQL = "delete from articulo where id=?";
            sentencia = conec.prepareStatement(cSQL);
            sentencia.setInt(1, t.getId());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Articulo> listar(String valor) {
        ArrayList<Articulo> listaArticulo = new ArrayList<>();
        try {
            sentencia = this.conec.prepareStatement("SELECT a.*,m.descripcion as marca,i.descripcion as iva\n"
                    + "FROM articulo a\n"
                    + "inner join marca m on (m.id=a.idmarca)\n"
                    + "inner join iva i on (i.id=a.idiva)\n"
                    + "where a.descripcion ilike '%"+valor+"%' ORDER BY a.id ASC");
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                Articulo articulo = new Articulo();
                Marca marca = new Marca();
                Iva iva = new Iva();
                
                articulo.setId(rs.getInt("id"));
                articulo.setDescripcion(rs.getString("descripcion"));
                //asignamos valor al objeto marca y vinculamos con articulo
                marca.setId(rs.getInt("idmarca"));
                marca.setDescripcion(rs.getString("marca"));
                articulo.setMarca(marca);
                //asignamos valor al objeto iva y vinculamos con articulo
                iva.setId(rs.getInt("idiva"));
                iva.setDescripcion(rs.getString("iva"));
                articulo.setIva(iva);
                
                articulo.setPrecio_costo(rs.getInt("precio_costo"));
                articulo.setPrecio_venta(rs.getInt("precio_venta"));
              
                listaArticulo.add(articulo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaArticulo;
    }
}
