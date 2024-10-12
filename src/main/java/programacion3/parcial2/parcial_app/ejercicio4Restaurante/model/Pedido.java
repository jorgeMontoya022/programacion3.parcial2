package programacion3.parcial2.parcial_app.ejercicio4Restaurante.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDate fecha;
    private double total;
    private List<Producto>listaProductos = new ArrayList<>();
    private Cliente clienteAsociado;

    public Pedido(LocalDate fecha, List<Producto> listaProductos, Cliente clienteAsociado) {
        this.fecha = fecha;
        this.listaProductos = listaProductos;
        this.clienteAsociado = clienteAsociado;
        calcularTotal();
    }

    private void calcularTotal() {
        total = 0;
        for(Producto producto: listaProductos){
            total+=producto.getPrecio();
        }
    }

    public Pedido(){

    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
        calcularTotal();
    }
    public Cliente getClienteAsociado() {
        return clienteAsociado;
    }

    public void setClienteAsociado(Cliente clienteAsociado) {
        this.clienteAsociado = clienteAsociado;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "fecha=" + fecha +
                ", total=" + total +
                ", listaProductos=" + listaProductos +
                ", clienteAsociado=" + clienteAsociado +
                '}';
    }
}
