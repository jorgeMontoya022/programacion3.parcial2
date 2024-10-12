package programacion3.parcial2.parcial_app.ejercicio4Restaurante.persistencia;

import programacion3.parcial2.parcial_app.ejercicio4Restaurante.model.Cliente;
import programacion3.parcial2.parcial_app.ejercicio4Restaurante.model.Pedido;
import programacion3.parcial2.parcial_app.ejercicio4Restaurante.model.Producto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    public static final String PRODUCTOS_FILE_PATH = "src/main/resources/programacion3/parcial2/parcial_app/ejercicio4/productos.txt";
    public static final String RESTAURANTE_FILE_LOG_PATH = "src/main/resources/programacion3/parcial2/parcial_app/ejercicio4/logProductos";
    public static final String PEDIDOS_XML_FILE_PATH = "src/main/resources/programacion3/parcial2/parcial_app/ejercicio4/pedidosActualizados.xml";
    public static final String CLIENTES_FILE_PATH = "src/main/resources/programacion3/parcial2/parcial_app/ejercicio4/clientes.txt";


    public void agregarProducto(Producto producto) throws IOException {
        StringBuilder textoProducto = new StringBuilder();
        textoProducto.append(producto.getCodigo()).append("#");
        textoProducto.append(producto.getNombre()).append("#");
        textoProducto.append(producto.getPrecio()).append("\n");

        ArchivoUtil.guardarArchivoProducto(PRODUCTOS_FILE_PATH, textoProducto.toString(), true);
        ArchivoUtil.guardarRegistroLog("Producto agregado", 1, "Botón agregarProducto", RESTAURANTE_FILE_LOG_PATH);
    }

    public void agregarSerializableXMLResource(Pedido pedido) {
        try {
            ArchivoUtil.agregarSerializableXMLResource(PEDIDOS_XML_FILE_PATH,pedido);
            ArchivoUtil.guardarRegistroLog("Pedido agregado", 1, "botón agregarPedido", RESTAURANTE_FILE_LOG_PATH);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void agregarCliente(Cliente cliente) throws IOException {
        StringBuilder textoCliente = new StringBuilder();
        textoCliente.append(cliente.getCodigo()).append("#");
        textoCliente.append(cliente.getNombre()).append("#");
        textoCliente.append(cliente.getApellido()).append("#");
        textoCliente.append(cliente.getDocumento()).append("#");
        textoCliente.append(cliente.getTipoDocumento()).append("#");
        textoCliente.append(cliente.getTelefono()).append("\n");

        ArchivoUtil.guardarArchivoCliente(CLIENTES_FILE_PATH, textoCliente.toString(), true);
        ArchivoUtil.guardarRegistroLog("Cliente agregado", 1, "Botón agregarCliente", RESTAURANTE_FILE_LOG_PATH);
    }

    public List<Pedido> cargarPedidosResource() throws IOException {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            pedidos = (List<Pedido>) ArchivoUtil.cargarSerializablePedidosXMLResource(PEDIDOS_XML_FILE_PATH);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return pedidos;

    }
}
