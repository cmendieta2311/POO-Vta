/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Cris_
 */
public class Marca {
    
    Integer id;
    String descripcion;

    public Marca(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Marca() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return id+ "-"+this.descripcion; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
