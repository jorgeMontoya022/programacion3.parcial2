package programacion3.parcial2.parcial_app.ejercicio1.builder;

import programacion3.parcial2.parcial_app.ejercicio1.model.Estudiante;

public class EstudianteBuilder implements IBuilder<Estudiante>{

    private static final long serialVersionUID = 1L;
    private String codigo;
    private String nombre;
    private int nota1;
    private int nota2;
    private int nota3;

    public EstudianteBuilder codigo(String codigo){
        this.codigo = codigo;
        return this;
    }

    public EstudianteBuilder nombre(String nombre){
        this.nombre = nombre;
        return this;
    }

    public EstudianteBuilder nota1(int nota1){
        this.nota1 = nota1;
        return this;
    }

    public EstudianteBuilder nota2(int nota2){
        this.nota2 = nota2;
        return this;
    }

    public EstudianteBuilder nota3(int nota3){
        this.nota3 = nota3;
        return this;
    }
    @Override
    public Estudiante build() {
        return new Estudiante(codigo, nombre, nota1, nota2, nota3);
    }
}
