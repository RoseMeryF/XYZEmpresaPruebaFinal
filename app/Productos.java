import android.os.Bundle;
import android.widget.EditText;

import com.example.xyzempresa.R;

public class Productos {
    private String nombre;
    private double precio;
    private String detalle;
    private String imagen;

    public Productos(String nombre, double precio, String detalle, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.detalle = detalle;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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
}
