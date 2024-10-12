package programacion3.parcial2.parcial_app.ejercicio4Restaurante.viewController;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private TextField txtIdentifiacion;

    // Variables para almacenar las credenciales
    private String usuario;
    private String contrasena;

    @FXML
    void onIniciar(ActionEvent event) {
        String usuarioIngresado = txtIdentifiacion.getText();
        String contrasenaIngresada = txtContrasenia.getText();

        // Verificar las credenciales
        if (usuarioIngresado.equals(usuario) && contrasenaIngresada.equals(contrasena)) {
            mostrarAlerta("Inicio de sesión exitoso", "¡Bienvenido!");

            // Cargar la interfaz de gestión de pedidos
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/programacion3/parcial2/parcial_app/ejercicio4/gestionPedidos-view.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) txtIdentifiacion.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                stage.setTitle("Gestión de pedidos");
            } catch (IOException e) {
                e.printStackTrace();
                mostrarAlerta("Error", "No se pudo cargar la vista de gestión de pedidos.");
            }
        } else {
            mostrarAlerta("Error de inicio de sesión", "Usuario o contraseña incorrectos.");
        }
    }

    @FXML
    void initialize() {
        cargarPropiedades(); // Cargar propiedades al inicializar
    }

    private void cargarPropiedades() {
        Properties propiedades = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/programacion3/parcial2/parcial_app/ejercicio4/config.properties")) {
            propiedades.load(input);
            usuario = propiedades.getProperty("usuario");
            contrasena = propiedades.getProperty("contrasenia");
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la configuración.");
        }
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
