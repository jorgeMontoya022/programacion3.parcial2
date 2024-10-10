package programacion3.parcial2.parcial_app.ejercicio1.persistencia;

import programacion3.parcial2.parcial_app.ejercicio1.model.Estudiante;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaUtil {
    public static final String ESTUDIANTES_FILE_PATH = "src/main/resources/programacion3/parcial2/parcial_app/ejercicio1.txt";
    public static final String ESTUDIANTES_LOG_PATH = "src/main/resources/programacion3/parcial2/parcial_app/logEjercicio1";


    public List<Estudiante> cargarEstudiantes() throws IOException {
        String rutaArchivo = ESTUDIANTES_FILE_PATH;
        List<String> contenido = ArchivoUtil.leerArchivo(rutaArchivo);
        List<Estudiante> listaEstudiantes = new ArrayList<>();

        for (String linea : contenido) {
            String[] datos = linea.split("@");

            // Validar que la línea tenga los 5 campos: código, nombre, nota1, nota2, nota3
            if (datos.length < 5) {
                System.out.println("Formato incorrecto en la línea: " + linea);
                continue; // Saltar esta línea si no tiene suficientes datos
            }

            try {
                // Parsear los datos y crear el estudiante
                String codigo = datos[0];
                String nombre = datos[1];
                double nota1 = Double.parseDouble(datos[2]);
                double nota2 =  Double.parseDouble(datos[3]);
                double nota3 =  Double.parseDouble(datos[4]);

                Estudiante estudiante = new Estudiante(codigo, nombre, nota1, nota2, nota3);
                listaEstudiantes.add(estudiante);
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir las notas en la línea: " + linea);
            }
        }

        return listaEstudiantes;
    }

    public void guardarEstudiante(Estudiante estudiante) throws IOException {
        StringBuilder textoEstudiante = new StringBuilder();

        // Construir la cadena de texto del estudiante
        textoEstudiante.append(estudiante.getCodigo()).append("@");
        textoEstudiante.append(estudiante.getNombre()).append("@");
        textoEstudiante.append(estudiante.getNota1()).append("@");
        textoEstudiante.append(estudiante.getNota2()).append("@");
        textoEstudiante.append(estudiante.getNota3()).append("\n");

        // Guardar el estudiante en el archivo
        ArchivoUtil.guardarArchivo(ESTUDIANTES_FILE_PATH, textoEstudiante.toString(), true);
        ArchivoUtil.guardarRegistroLog("Estudiante agregado", 1, "Botón agregarEstudiante", ESTUDIANTES_LOG_PATH);
    }

}
