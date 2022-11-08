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
import modelos.Empresa;
import modelos.Perfil;
import modelos.Usuario;

/**
 *
 * @author Cris_
 */
public class UsuarioDaoImpl implements DAO<Usuario> {

    private Connection conec; // la conexion de la base de datos
    private PreparedStatement sentencia; // preparar sentencia

    public UsuarioDaoImpl() {
        Conexion conectar = new Conexion();
        conec = conectar.ConectarBD();
    }

    @Override
    public void insertar(Usuario t) {
        try {
            System.out.println(t);
            String cSQL = "insert into usuario (nombre,apellido,usuario,contraseña,idempresa,idperfil) values(?,?,?,md5(?),?,?)";
            sentencia = conec.prepareStatement(cSQL);
            //sentencia.setInt(1, t.getId());
            sentencia.setString(1, t.getNombre());
            sentencia.setString(2, t.getApellido());
            sentencia.setString(3, t.getUsuario());
            sentencia.setString(4, t.getContraseña());
            sentencia.setInt(5, t.getEmpresa().getId());
            sentencia.setInt(6, t.getPerfil().getId());
            int registro = sentencia.executeUpdate();
            System.out.print("Insertar " + registro);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificar(Usuario t) {
        try {
            String cSQL = "update usuario set nombre=?,apellido=?,idempresa=?,idperfil=? where id=?";
            sentencia = conec.prepareStatement(cSQL);
            sentencia.setString(1, t.getNombre().trim());
            sentencia.setString(2, t.getApellido().trim());
            sentencia.setInt(3, t.getEmpresa().getId());
            sentencia.setInt(4, t.getPerfil().getId());
            sentencia.setInt(5, t.getId());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void eliminar(Usuario t) {
        try {
            String cSQL = "delete from usuario where id=?";
            sentencia = conec.prepareStatement(cSQL);
            sentencia.setInt(1, t.getId());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Usuario> listar(String valor) {
        ArrayList<Usuario> listaUsuario = new ArrayList<>();
        try {
            sentencia = this.conec.prepareStatement("select usuario.*,empresa.nombre as empresa ,perfil.descripcion as perfil\n" +
            "from usuario \n" +
            "inner join empresa on usuario.idempresa=empresa.id\n" +
            "inner join perfil on perfil.id=usuario.idperfil where usuario.nombre || ' '||apellido|| ' '||usuario ilike '%" + valor + "%' ORDER BY id ASC");
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                Usuario m = new Usuario();
                Perfil p = new Perfil();
                Empresa e = new Empresa();
                
                m.setId(rs.getInt("id"));
                m.setNombre(rs.getString("nombre").trim());
                m.setApellido(rs.getString("apellido").trim());
                m.setUsuario(rs.getString("usuario").trim());
                m.setContraseña(rs.getString("contraseña").trim());
                //preparar obejeto de empresa
                e.setId(rs.getInt("idempresa"));
                e.setNombre(rs.getString("empresa").trim());
               //asignar objeto de empresa
                m.setEmpresa(e);
                //preparar objeto para perfil
                p.setId(rs.getInt("idperfil"));
                p.setDescripcion(rs.getString("perfil"));
                m.setPerfil(p);
                listaUsuario.add(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUsuario;
    }

}
