package programacion3.parcial2.parcial_app.ejercicio2.model;

import java.io.Serializable;

public class Programa implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String nombre;
    private String modalidad;
    private String adscrito;

    public Programa(String codigo, String nombre, String modadlidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.modalidad = modadlidad;
        this.adscrito = "Universidad del Quindío";
    }

    public Programa(){

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getAdscrito() {
        return adscrito;
    }

    public void setAdscrito(String adscrito) {
        this.adscrito = adscrito;
    }

    @Override
    public String toString() {
        return "Programa{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", modadlidad='" + modalidad + '\'' +
                ", adscrito='" + adscrito + '\'' +
                '}';
    }
}
