package programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model;

public abstract class Persona {
    private String nombre;
    private String apellido;
    private String sexo;
    private int edad;
    private String codigo;
    private String correo;
    private String telefono;

    public Persona(String nombre, String apellido, String sexo, int edad, String codigo, String correo, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.edad = edad;
        this.codigo = codigo;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Persona() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "codigo: "+getCodigo()+" "+getNombre() +" "+getApellido();
    }
}
