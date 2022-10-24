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
public class Articulo {
    Integer id;
    Marca marca;
    String descripcion;
    Integer precio_costo;
    Integer precio_venta;
    Iva iva;

    public Articulo(Integer id, Marca marca, String descripcion, Integer precio_costo, Integer precio_venta, Iva iva) {
        this.id = id;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
        this.iva = iva;
    }

    public Articulo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(Integer precio_costo) {
        this.precio_costo = precio_costo;
    }

    public Integer getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Integer precio_venta) {
        this.precio_venta = precio_venta;
    }

    public Iva getIva() {
        return iva;
    }

    public void setIva(Iva iva) {
        this.iva = iva;
    }
    
    
    
}
