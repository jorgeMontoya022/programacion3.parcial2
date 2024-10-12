package programacion3.parcial2.parcial_app.ejercicio4Restaurante.persistencia;

import programacion3.parcial2.parcial_app.ejercicio4Restaurante.model.Cliente;
import programacion3.parcial2.parcial_app.ejercicio4Restaurante.model.Pedido;
import programacion3.parcial2.parcial_app.ejercicio4Restaurante.model.Producto;

import java.beans.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.beans.XMLEncoder;

public class ArchivoUtil {
    public static void guardarArchivoProducto(String archivoProductos, String contenido, boolean append) throws IOException {
        try (FileWriter fw = new FileWriter(archivoProductos, append);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(contenido);
        }
    }

    public static Object cargarSerializablePedidosXMLResource(String pedidosXmlFilePath) throws IOException {
        File file = new File(pedidosXmlFilePath);

        // Verificar si el archivo existe y no está vacío
        if (!file.exists() || file.length() == 0) {
            System.out.println("El archivo XML no existe o está vacío. Se retornará una lista vacía.");
            return new ArrayList<Pedido>();
        }

        try (XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(file))) {
            Object obj = xmlDecoder.readObject();
            if (obj instanceof List<?>) {
                return obj;
            } else {
                System.out.println("El contenido del archivo XML no es una lista válida. Se retornará una lista vacía.");
                return new ArrayList<Pedido>();
            }
        } catch (Exception e) {
            System.out.println("Error al deserializar el archivo XML: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<Pedido>();
        }
    }


    public static void guardarRegistroLog(String mensajeLog, int nivel, String accion, String rutaArchivo) {
        Logger LOGGER = Logger.getLogger(accion);
        FileHandler fileHandler = null;

        try {
            fileHandler = new FileHandler(rutaArchivo, true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);

            switch (nivel) {
                case 1:
                    LOGGER.log(Level.INFO, accion + "," + mensajeLog);
                    break;

                case 2:
                    LOGGER.log(Level.WARNING, accion + "," + mensajeLog);
                    break;

                case 3:
                    LOGGER.log(Level.SEVERE, accion + "," + mensajeLog);
                    break;

                default:
                    break;
            }

        } catch (SecurityException e) {

            LOGGER.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            LOGGER.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
        } finally {

            fileHandler.close();
        }

    }

    // Asegúrate de que tus clases Cliente y Producto tengan un constructor sin argumentos
    public static void agregarSerializableXMLResource(String pedidosXmlFilePath, Pedido pedido) throws IOException {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            pedidos = (List<Pedido>) cargarSerializablePedidosXMLResource(pedidosXmlFilePath);
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
        }

        // Agregar el nuevo pedido a la lista
        pedidos.add(pedido);

        // Sobrescribir el archivo con la lista actualizada
        try (XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(pedidosXmlFilePath)))) {
            // Registrar un PersistenceDelegate para LocalDate
            xmlEncoder.setPersistenceDelegate(LocalDate.class, new DefaultPersistenceDelegate() {
                @Override
                protected Expression instantiate(Object obj, Encoder enc) {
                    LocalDate date = (LocalDate) obj;
                    return new Expression(obj, LocalDate.class, "of", new Object[]{
                            date.getYear(),
                            date.getMonthValue(),
                            date.getDayOfMonth()
                    });
                }
            });

            xmlEncoder.writeObject(pedidos);
        } catch (IOException e) {
            throw e; // Lanza la excepción hacia arriba para manejo adicional
        }
    }
    public static void guardarArchivoCliente(String archivoPedido, String contenido, boolean append) throws IOException {
        try (FileWriter fw = new FileWriter(archivoPedido, append);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(contenido);
        }
    }


}
