package programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Asignacion {
    private static int contadorAsignaciones = 0; // Contador para los códigos
    private String codigoAsignacion;
    private String codigoMateria;
    private String nombreMateria;
    private String codigoDocente;
    private List<String> codigoEstudiantes = new ArrayList<>();

    public Asignacion(String codigoMateria, String nombreMateria, String codigoDocente, List<String> codigoEstudiantes) {
        this.codigoAsignacion = generarCodigoAsignacion();
        this.codigoMateria = codigoMateria;
        this.nombreMateria = nombreMateria;
        this.codigoDocente = codigoDocente;
        this.codigoEstudiantes = codigoEstudiantes;
    }

    public Asignacion() {
        // Constructor vacío
    }

    public static void inicializarContador() {
        String ultimoCodigo = cargarUltimoCodigoDesdeArchivo();
        if (ultimoCodigo != null) {
            contadorAsignaciones = Integer.parseInt(ultimoCodigo);
        } else {
            contadorAsignaciones = 1; // Establece a 1 si no hay un valor máximo existente
        }
    }

    // Método para generar un código de asignación con máximo 4 dígitos
    private String generarCodigoAsignacion() {
        contadorAsignaciones++;
        guardarContador(); // Guardar el nuevo contador en el archivo
        return String.format("%04d", contadorAsignaciones); // Formatea a 4 dígitos
    }

    private static String cargarUltimoCodigoDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/programacion3/parcial2/parcial_app/ejercicio5/contadorCodigoAsignacion.txt"))) {
            return br.readLine(); // Lee la primera línea donde está el último código
        } catch (IOException e) {
            // Manejo de excepciones
            System.err.println("Error al cargar el contador: " + e.getMessage());
            return null; // Retorna null si hay un error
        }
    }

    private void guardarContador() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/programacion3/parcial2/parcial_app/ejercicio5/contadorCodigoAsignacion.txt"))) {
            bw.write(String.valueOf(contadorAsignaciones)); // Guarda el contador actual
        } catch (IOException e) {
            // Manejo de excepciones
            System.err.println("Error al guardar el contador: " + e.getMessage());
        }
    }

    public String getCodigoAsignacion() {
        return codigoAsignacion;
    }

    public void setCodigoAsignacion(String codigoAsignacion) {
        this.codigoAsignacion = codigoAsignacion;
    }

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(String codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getCodigoDocente() {
        return codigoDocente;
    }

    public void setCodigoDocente(String codigoDocente) {
        this.codigoDocente = codigoDocente;
    }

    public List<String> getCodigoEstudiantes() {
        return codigoEstudiantes;
    }

    public void setCodigoEstudiantes(List<String> codigoEstudiantes) {
        this.codigoEstudiantes = codigoEstudiantes;
    }

    @Override
    public String toString() {
        return "Asignacion{" +
                "codigoAsignacion='" + codigoAsignacion + '\'' +
                ", codigoMateria='" + codigoMateria + '\'' +
                ", nombreMateria='" + nombreMateria + '\'' +
                ", codigoDocente='" + codigoDocente + '\'' +
                ", codigoEstudiantes=" + codigoEstudiantes +
                '}';
    }
}
