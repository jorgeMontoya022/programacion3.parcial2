package programacion3.parcial2.parcial_app.ejercicio4Restaurante.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import programacion3.parcial2.parcial_app.ejercicio4Restaurante.controller.GestionPedidosController;
import programacion3.parcial2.parcial_app.ejercicio4Restaurante.model.Cliente;
import programacion3.parcial2.parcial_app.ejercicio4Restaurante.model.Pedido;
import programacion3.parcial2.parcial_app.ejercicio4Restaurante.model.Producto;

public class GestionPedidosViewController {
    GestionPedidosController gestionPedidosController;
    List<Producto> listaProductos = new ArrayList<>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker dpFechaPedido;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextArea txtAreaMostrarPedidos;

    @FXML
    private TextField txtCodigoCliente;

    @FXML
    private TextField txtCodigoProducto;

    @FXML
    private TextField txtDocumento;

    @FXML
    private TextField txtNombreCliente;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtPrecioProducto;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtTipoDocumento;

    @FXML
    void onAgregarProducto(ActionEvent event) {
        agregarProducto();

    }


    @FXML
    void onAgregarPedido(ActionEvent event) {
        agregarPedido();

    }

    @FXML
    void onLimpiarCampos(ActionEvent event) {
        limpiarCamposPedido();

    }

    @FXML
    void onMostrarPedidos(ActionEvent event) {
        mostrarPedidos();

    }


    @FXML
    void initialize() {
        gestionPedidosController = new GestionPedidosController();


    }


    private void agregarProducto() {
        if (validarFormulario()) {
            Producto producto = new Producto(txtCodigoProducto.getText(), txtNombreProducto.getText(), Double.parseDouble(txtPrecioProducto.getText()));
            if (gestionPedidosController.agregarProducto(producto)) {
                listaProductos.add(producto);
                mostrarMensaje("Notificación de producto", "Producto agregado", "El producto ha sido añadido a la lista", Alert.AlertType.INFORMATION);
                limpiarCamposProductos();
            } else {
                mostrarMensaje("Notificación del producto", "Producto no agregado", "El producto no ha sido agregado con éxito", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Notificación de producto", "Producto no agregado", "Por favor complete los campos correctamente", Alert.AlertType.WARNING);
        }
    }

    private void agregarPedido() {
        if (validarFormularioPedido()) {
            Cliente cliente = buildDataCliente();  // Obtener el cliente
            if (cliente != null) {  // Si el cliente es válido
                if (!gestionPedidosController.agregarCliente(cliente)) {
                    mostrarMensaje("Error", "Error al guardar cliente", "No se pudo guardar el cliente en el archivo.", Alert.AlertType.ERROR);
                    return;  // No continuar si no se pudo guardar el cliente
                }

                Pedido pedido = new Pedido(dpFechaPedido.getValue(), listaProductos, cliente);  // Crear el pedido
                if (gestionPedidosController.agregarPedido(pedido)) {
                    mostrarMensaje("Pedido", "Pedido agregado", "El pedido ha sido agregado exitosamente.", Alert.AlertType.INFORMATION);
                    limpiarCamposPedido();  // Limpiar los campos del formulario
                    listaProductos.clear();  // Limpiar la lista de productos después de agregar el pedido
                } else {
                    mostrarMensaje("Error", "Pedido no agregado", "No se pudo agregar el pedido.", Alert.AlertType.ERROR);
                }
            }
        } else {
            mostrarMensaje("Error", "Datos inválidos", "Por favor complete los campos correctamente.", Alert.AlertType.WARNING);
        }
    }

    private void mostrarPedidos() {
        List<Pedido> pedidos = gestionPedidosController.mostrarPedidos();
        StringBuilder resultado = new StringBuilder();

        if (pedidos.isEmpty()) {
            mostrarMensaje("Notificación de pedidos", "No hay pedidos", "No hay pedidos disponibles", Alert.AlertType.WARNING);
        } else {
            for (Pedido pedido : pedidos) {
                // Añadir información del pedido
                resultado.append("Fecha: ").append(pedido.getFecha()).append("\n");
                resultado.append("Total: ").append(pedido.getTotal()).append("\n");

                // Verificar si el cliente asociado no es null
                Cliente clienteAsociado = pedido.getClienteAsociado();
                if (clienteAsociado != null) {
                    resultado.append("Cliente:\n")
                            .append("  Código: ").append(clienteAsociado.getCodigo()).append("\n")
                            .append("  Documento: ").append(clienteAsociado.getDocumento()).append("\n")
                            .append("  Nombre: ").append(clienteAsociado.getNombre()).append("\n")
                            .append("  Apellido: ").append(clienteAsociado.getApellido()).append("\n")
                            .append("  Tipo de Documento: ").append(clienteAsociado.getTipoDocumento()).append("\n")
                            .append("  Teléfono: ").append(clienteAsociado.getTelefono()).append("\n");
                } else {
                    resultado.append("Cliente: No disponible\n");
                }

                // Añadir productos
                resultado.append("Productos:\n");
                for (Producto producto : pedido.getListaProductos()) {
                    resultado.append("  Código: ").append(producto.getCodigo()).append("\n")
                            .append("  Nombre: ").append(producto.getNombre()).append("\n")
                            .append("  Precio: ").append(producto.getPrecio()).append("\n");
                }

                resultado.append("\n");
            }
        }

        // Mostrar el resultado en un TextArea o similar
        txtAreaMostrarPedidos.setText(resultado.toString());
    }



    private void limpiarCamposPedido() {
        txtPrecioProducto.setText("");
        txtNombreProducto.setText("");
        txtCodigoProducto.setText("");
        txtCodigoCliente.setText("");
        txtNombreCliente.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtDocumento.setText("");
        txtTipoDocumento.setText("");
        txtAreaMostrarPedidos.setText("");
        dpFechaPedido.setValue(null);
    }



    private Cliente buildDataCliente() {
        Cliente cliente = new Cliente(txtCodigoCliente.getText(), txtDocumento.getText(), txtNombreCliente.getText(), txtApellido.getText(), txtTipoDocumento.getText(), txtTelefono.getText());
        return cliente;
    }


    private boolean validarFormularioPedido() {
        return dpFechaPedido.getValue() != null
                && !listaProductos.isEmpty()
                && !txtCodigoCliente.getText().isEmpty()
                && !txtNombreCliente.getText().isEmpty()
                && !txtDocumento.getText().isEmpty()
                && !txtTelefono.getText().isEmpty();
    }
    private void limpiarCamposProductos() {
        txtPrecioProducto.setText("");
        txtNombreProducto.setText("");
        txtCodigoProducto.setText("");
    }


    private boolean validarFormulario() {
        return !txtNombreProducto.getText().isEmpty()
                && !txtCodigoProducto.getText().isEmpty()
                && validarNumero(txtPrecioProducto.getText());
    }

    private boolean validarNumero(String texto) {
        try {
            Double.parseDouble(texto);
            return true;
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Precio inválido", "Por favor ingrese un precio válido", Alert.AlertType.ERROR);
            return false;


        }
    }

    private void mostrarMensaje(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
