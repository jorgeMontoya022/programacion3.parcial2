package programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model;

public class Materia {
    private String codigo;
    private String nombre;
    private int intensidadHoraria;
    private String tipoMateria;//práctica y teórica-Practica

    public Materia(String codigo, String nombre, int intensidadHoraria, String tipoMateria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.intensidadHoraria = intensidadHoraria;
        this.tipoMateria = tipoMateria;
    }

    public Materia() {

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

    public int getIntensidadHoraria() {
        return intensidadHoraria;
    }

    public void setIntensidadHoraria(int intensidadHoraria) {
        this.intensidadHoraria = intensidadHoraria;
    }

    public String getTipoMateria() {
        return tipoMateria;
    }

    public void setTipoMateria(String tipoMateria) {
        this.tipoMateria = tipoMateria;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
