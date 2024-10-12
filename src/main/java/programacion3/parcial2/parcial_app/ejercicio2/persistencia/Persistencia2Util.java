package programacion3.parcial2.parcial_app.ejercicio2.persistencia;

import programacion3.parcial2.parcial_app.ejercicio2.model.Programa;

import java.util.ArrayList;
import java.util.List;

public class Persistencia2Util {

    public static final String PROGRAMA_XML_FILE_PATH = "src/main/resources/programacion3/parcial2/parcial_app/ejercicio2/ejercicio2.xml";
    public static final String PRGRAMAS_LOG_PATH = "src/main/resources/programacion3/parcial2/parcial_app/ejercicio2/logEjercicio2";

    public List<Programa> cargarProgramasResource() {
        List<Programa> programas = new ArrayList<>();
        try {
            programas = (List<Programa>) ArchivoUtil.cargarSerializableXMLResource(PROGRAMA_XML_FILE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return programas;
    }


    public static void agregarSerializableXMLResource(Programa programa) {
        try{
            ArchivoUtil.agregarSerializableXMLResource(PROGRAMA_XML_FILE_PATH, programa);
            ArchivoUtil.guardarRegistroLog("Programa agregado", 1, "Botón agregar", PRGRAMAS_LOG_PATH);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
