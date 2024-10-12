package programacion3.parcial2.parcial_app.ejercicio4Restaurante.controller;

import programacion3.parcial2.parcial_app.ejercicio4Restaurante.model.Cliente;
import programacion3.parcial2.parcial_app.ejercicio4Restaurante.model.Pedido;
import programacion3.parcial2.parcial_app.ejercicio4Restaurante.model.Producto;
import programacion3.parcial2.parcial_app.ejercicio4Restaurante.persistencia.Persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestionPedidosController {
    private Persistencia persistencia;

    public GestionPedidosController() {
        this.persistencia = new Persistencia();
    }

    public boolean agregarProducto(Producto producto) {
        try{
            persistencia.agregarProducto(producto);
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean agregarPedido(Pedido pedido) {
        persistencia.agregarSerializableXMLResource(pedido);
        return true;
    }

    public boolean agregarCliente(Cliente cliente) {
        try {
            persistencia.agregarCliente(cliente);
            return true;  // Cliente agregado exitosamente
        } catch (IOException e) {
            e.printStackTrace();  // Imprimir el error para depuración
            return false;  // Error al agregar el cliente
        }
    }

    public List<Pedido> mostrarPedidos() {
        List<Pedido> pedidos = new ArrayList<>();

        try {
            pedidos = persistencia.cargarPedidosResource();
        }catch (IOException e){
            e.printStackTrace();
        }
        return pedidos;
    }
}
