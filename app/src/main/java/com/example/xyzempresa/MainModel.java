package com.example.xyzempresa;

public class MainModel extends Producto {
    private String nombre, detalle, imagen;
    private double precio;
    public MainModel() {}

    public MainModel(String nombre, String detalle, String imagen, double precio) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.imagen = imagen;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImgURL() {
        return imagen;
    }
    public void setImgURL(String imagen) {
        this.imagen = imagen;
    }
}


