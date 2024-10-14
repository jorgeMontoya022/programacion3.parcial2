package programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.persitence;

import programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model.Asignacion;
import programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model.Docente;
import programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model.Estudiante;
import programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model.Materia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    public static final String ESTUDIANTES_FILE_PATH = "src/main/resources/programacion3/parcial2/parcial_app/ejercicio5/estudiantes.txt";
    public static final String DOCENTES_FILE_PATH = "src/main/resources/programacion3/parcial2/parcial_app/ejercicio5/docentes.txt";
    public static final String ASIGNACION_FILE_PATH = "src/main/resources/programacion3/parcial2/parcial_app/ejercicio5/asignacion.txt";
    public static final String MATERIAS_FILE_PATH = "src/main/resources/programacion3/parcial2/parcial_app/ejercicio5/materias.txt";
    public static final String LOG_FILE_PATH = "src/main/resources/programacion3/parcial2/parcial_app/ejercicio5/logAsignacionMaterias";

    public List<Materia> cargarMaterias() throws IOException {
        String rutaArchivo = MATERIAS_FILE_PATH;
        List<String> contenido = ArchivoUtil.leerArchivoMaterias(rutaArchivo);
        List<Materia> listaMaterias = new ArrayList<>();

        for (String linea : contenido) {
            String[] datos = linea.split("@@");

            if (datos.length != 4) {
                System.out.println("Formato incorrecto en la línea: " + linea);
                continue;
            }
            try {
                String codigo = datos[0];
                String nombre = datos[1];
                int intensidadHoraria = Integer.parseInt(datos[2]);
                String tipoMateria = datos[3];

                Materia materia = new Materia(codigo, nombre, intensidadHoraria, tipoMateria);
                listaMaterias.add(materia);

            }catch (NumberFormatException e){
                System.out.println("Error al convertir la intensidad horaria en la línea: " + linea);
            }
        }
        return listaMaterias;
    }

    public List<Estudiante> cargarEstudiantes() throws IOException{
        String rutaArchivo = ESTUDIANTES_FILE_PATH;
        List<String> contenido = ArchivoUtil.leerArchivoEstudiantes(rutaArchivo);
        List<Estudiante> listaEstudiantes = new ArrayList<>();

        for(String linea: contenido){
            String[] datos = linea.split(";");

            if (datos.length !=7){
                System.out.println("Formato incorrecto en la línea: " + linea);
                continue;
            }
            try {
                String nombre = datos[0];
                String apellido = datos[1];
                String sexo = datos[2];
                int edad = Integer.parseInt(datos[3]);
                String codigo = datos[4];
                String correo = datos[5];
                String telefono = datos[6];

                Estudiante estudiante = new Estudiante(nombre, apellido, sexo, edad, codigo, correo, telefono);
                listaEstudiantes.add(estudiante);
            }catch (NumberFormatException e){
                System.out.println("Error al convertir la edad  en la línea: " + linea);

            }
        }
        return listaEstudiantes;
    }

    public List<Docente> cargarDocentes() throws IOException {
        String rutaArchivo = DOCENTES_FILE_PATH;
        List<String> contenido = ArchivoUtil.leerArchivo(rutaArchivo); // Usamos el método general para leer el archivo
        List<Docente> listaDocentes = new ArrayList<>();

        for (String linea : contenido) {
            String[] datos = linea.split(";");

            if (datos.length != 9) {
                System.out.println("Formato incorrecto en la línea: " + linea);
                continue;
            }
            try {
                String nombre = datos[0];
                String apellido = datos[1];
                String sexo = datos[2];
                int edad = Integer.parseInt(datos[3]);
                String codigo = datos[4];
                String correo = datos[5];
                String telefono = datos[6];
                String programaAdscrito = datos[7];
                String profesion = datos[8];

                Docente docente = new Docente(nombre, apellido, sexo, edad, codigo, correo, telefono, programaAdscrito, profesion);
                listaDocentes.add(docente);
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir la edad en la línea: " + linea);
            }
        }
        return listaDocentes;
    }

    public void asignarMateria(Asignacion asignacion) throws IOException {
        StringBuilder textoAsignacionMateria = new StringBuilder();

        textoAsignacionMateria.append(asignacion.getCodigoAsignacion()).append("@@");
        textoAsignacionMateria.append(asignacion.getCodigoMateria()).append("@@");
        textoAsignacionMateria.append(asignacion.getNombreMateria()).append("@@");
        textoAsignacionMateria.append(asignacion.getCodigoDocente()).append("@@");
        String codigosEstudiantes = String.join("@@", asignacion.getCodigoEstudiantes());
        textoAsignacionMateria.append(codigosEstudiantes).append("\n");



        ArchivoUtil.guardarArchivoAsignacionMaterias(ASIGNACION_FILE_PATH, textoAsignacionMateria.toString(), true);
        ArchivoUtil.guardarRegistroLog("Materia Asignada", 1, "Botón asignarMaterias", LOG_FILE_PATH);

    }

    public List<Asignacion> cargarMateriasAsignadas() throws IOException {
        String rutaArchivo = ASIGNACION_FILE_PATH;
        List<String> contenido = ArchivoUtil.leerArchivo(rutaArchivo);
        List<Asignacion> listaAsignaciones = new ArrayList<>();

        for (String linea : contenido) {
            String[] datos = linea.split("@@");

            // Verifica que la línea tenga el número correcto de datos
            if (datos.length < 5) {
                System.out.println("Formato incorrecto en la línea: " + linea);
                continue;
            }

            try {
                String codigoAsignacion = datos[0]; // Cargar el código de asignación desde el archivo
                String codigoMateria = datos[1];
                String nombreMateria = datos[2];
                String codigoDocente = datos[3];
                List<String> codigoEstudiantes = new ArrayList<>();

                // Cargar códigos de estudiantes desde la posición 4 en adelante
                for (int i = 4; i < datos.length; i++) {
                    codigoEstudiantes.add(datos[i]);
                }

                // Crea la asignación y añádela a la lista
                Asignacion asignacion = new Asignacion(codigoMateria, nombreMateria, codigoDocente, codigoEstudiantes);
                asignacion.setCodigoAsignacion(codigoAsignacion); // Setear el código de asignación leído
                listaAsignaciones.add(asignacion);
            } catch (Exception e) {
                System.out.println("Error al procesar la línea: " + linea + " - " + e.getMessage());
            }
        }

        return listaAsignaciones;
    }

}
