package programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model;

public class Docente extends Persona {
    private String programaAdscrito;
    private String profesion;

    public Docente(String nombre, String apellido, String sexo, int edad, String codigo, String correo, String telefono, String programaAdscrito, String profesion) {
        super(nombre, apellido, sexo, edad, codigo, correo, telefono);
        this.programaAdscrito = programaAdscrito;
        this.profesion = profesion;
    }

    public String getProgramaAdscrito() {
        return programaAdscrito;
    }

    public void setProgramaAdscrito(String programaAdscrito) {
        this.programaAdscrito = programaAdscrito;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    @Override
    public String toString() {
        return "codigo: "+getCodigo()+", "+getNombre()+" "+getApellido()+", "+getProgramaAdscrito()+", "+getProfesion();
    }
}
