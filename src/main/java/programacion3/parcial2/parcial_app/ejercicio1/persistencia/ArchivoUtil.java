package programacion3.parcial2.parcial_app.ejercicio1.persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ArchivoUtil {
    public static List<String> leerArchivo(String rutaArchivo) throws IOException {
        List<String> contenido = new ArrayList<>();

        // Uso de try-with-resources para asegurar el cierre automático de recursos
        try (BufferedReader bfr = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = bfr.readLine()) != null) {
                contenido.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            throw e; // Vuelve a lanzar la excepción si es necesario
        }

        return contenido;
    }

    public static void guardarArchivo(String rutaArchivo, String contenido, boolean append) throws IOException {
        try (FileWriter fw = new FileWriter(rutaArchivo, append);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(contenido);
        }
    }

    public static void guardarRegistroLog(String mensajeLog, int nivel, String accion, String rutaArchivo)
    {
        Logger LOGGER = Logger.getLogger(accion);
        FileHandler fileHandler =  null;

        try {
            fileHandler = new FileHandler(rutaArchivo,true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);

            switch (nivel) {
                case 1:
                    LOGGER.log(Level.INFO,accion+","+mensajeLog) ;
                    break;

                case 2:
                    LOGGER.log(Level.WARNING,accion+","+mensajeLog) ;
                    break;

                case 3:
                    LOGGER.log(Level.SEVERE,accion+","+mensajeLog) ;
                    break;

                default:
                    break;
            }

        } catch (SecurityException e) {

            LOGGER.log(Level.SEVERE,e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            LOGGER.log(Level.SEVERE,e.getMessage());
            e.printStackTrace();
        }
        finally {

            fileHandler.close();
        }

    }

}
