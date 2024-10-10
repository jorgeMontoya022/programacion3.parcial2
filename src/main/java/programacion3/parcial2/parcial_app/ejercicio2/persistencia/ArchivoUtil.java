package programacion3.parcial2.parcial_app.ejercicio2.persistencia;

import programacion3.parcial2.parcial_app.ejercicio2.model.Programa;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ArchivoUtil {
    public static Object cargarSerializableXMLResource(String programaXmlFilePath) throws IOException {
        XMLDecoder xmlDecoder;
        Object xmlObject;

        xmlDecoder = new XMLDecoder(new FileInputStream(programaXmlFilePath));
        xmlObject = xmlDecoder.readObject();
        xmlDecoder.close();
        return xmlObject;
    }

    public static void agregarSerializableXMLResource(String programaXmlFilePath, Programa programa) throws IOException {
        // Primero cargar la lista existente
        List<Programa> programas = new ArrayList<>();
        try {
            programas = (List<Programa>) cargarSerializableXMLResource(programaXmlFilePath);
        } catch (Exception e) {
            // Si no se puede cargar la lista (por ejemplo, si el archivo no existe), inicializar una nueva lista
            e.printStackTrace();
        }

        // Agregar el nuevo programa a la lista
        programas.add(programa);

        // Sobrescribir el archivo con la lista actualizada
        XMLEncoder xmlEncoder = null;
        try {
            xmlEncoder = new XMLEncoder(new FileOutputStream(programaXmlFilePath));
            xmlEncoder.writeObject(programas);
        } catch (IOException e) {
            throw e;
        } finally {
            if (xmlEncoder != null) {
                xmlEncoder.close();
            }
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
