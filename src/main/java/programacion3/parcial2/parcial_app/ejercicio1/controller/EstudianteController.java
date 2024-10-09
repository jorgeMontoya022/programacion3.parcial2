package programacion3.parcial2.parcial_app.ejercicio1.controller;

import programacion3.parcial2.parcial_app.ejercicio1.model.Estudiante;
import programacion3.parcial2.parcial_app.ejercicio1.persistencia.PersistenciaUtil;

import java.io.IOException;
import java.util.List;

public class EstudianteController {
    private PersistenciaUtil persistenciaUtil;

    public EstudianteController() {
        this.persistenciaUtil = new PersistenciaUtil(); // Inicializa PersistenciaUtil
    }

    public List<Estudiante> getEstudiantes() {
        try {
            return persistenciaUtil.cargarEstudiantes(); // Manejo de IOException
        } catch (IOException e) {
            System.out.println("Error al cargar los estudiantes: " + e.getMessage());
            return List.of(); // Devuelve una lista vacía en caso de error
        }
    }

    public boolean agregarEstudiante(Estudiante estudiante) {
        try {
            persistenciaUtil.guardarEstudiante(estudiante);
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
