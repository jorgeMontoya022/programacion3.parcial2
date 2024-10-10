package programacion3.parcial2.parcial_app.ejercicio2.controller;

import programacion3.parcial2.parcial_app.ejercicio2.model.Programa;
import programacion3.parcial2.parcial_app.ejercicio2.persistencia.Persistencia2Util;

import java.io.IOException;
import java.util.List;

public class ProgramaController {
    private Persistencia2Util persistenciaUtil;

    public ProgramaController() {
        this.persistenciaUtil = new Persistencia2Util();

    }

    public List<Programa> getProgramas() {
        return persistenciaUtil.cargarProgramasResource();
    }


    public boolean agregarPrograma(Programa programa) {
        persistenciaUtil.agregarSerializableXMLResource(programa);
        return true;

    }


}
