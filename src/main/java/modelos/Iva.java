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
public class Iva {

    Integer id;
    String descripcion;

    public Iva(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Iva() {
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
        return descripcion; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        Integer cod1 = getId();
        Integer cod2 = ((Iva) obj).getId();
        if (cod1.equals(cod2)) {
            return true;
        }
        return false; //To change body of generated methods, choose Tools | Templates.
    }

}
