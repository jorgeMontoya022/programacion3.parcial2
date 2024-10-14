package programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.controller;

import programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model.Asignacion;
import programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model.Docente;
import programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model.Estudiante;
import programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model.Materia;
import programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.persitence.Persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AsignacionMateriasContoller {
    private Persistencia persistencia;

    public AsignacionMateriasContoller(){
        this.persistencia = new Persistencia();
    }

    // Método para cargar las materias
    public List<Materia> getMaterias() {
        try{
            return persistencia.cargarMaterias();
        } catch (IOException e) {
            System.out.println("Error al cargar las materias: " + e.getMessage());
            return List.of(); // Devolver lista vacía en caso de error
        }
    }

    // Método para cargar los estudiantes
    public List<Estudiante> getEstudiantes() {
        try{
            return persistencia.cargarEstudiantes();
        } catch (IOException e) {
            System.out.println("Error al cargar los estudiantes: " + e.getMessage());
            return List.of(); // Devolver lista vacía en caso de error
        }
    }

    // Método para cargar los docentes
    public List<Docente> getDocentes() {
        try{
            return persistencia.cargarDocentes();
        } catch (IOException e) {
            System.out.println("Error al cargar los docentes: " + e.getMessage());
            return List.of(); // Devolver lista vacía en caso de error
        }
    }

    public boolean asignarMateria(Asignacion asignacion) {
        try {
            persistencia.asignarMateria(asignacion);
            return true;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Asignacion> mostrarMateriasAsignadas() {
        List<Asignacion> materiasAsignadas = new ArrayList<>();

        try {
            materiasAsignadas = persistencia.cargarMateriasAsignadas();
        } catch (IOException e){
            e.printStackTrace();
        }
        return materiasAsignadas;
    }
}
