/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.dao;

import java.util.List;

/**
 *
 * @author Cris_
 */
public interface DAO<T> {
    void insertar(T t);
    void modificar(T t);
   void eliminar(T t);
    List<T> listar();
}
