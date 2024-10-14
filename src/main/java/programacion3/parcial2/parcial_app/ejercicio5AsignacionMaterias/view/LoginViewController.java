package programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.view;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginViewController {

    private String usuario;
    private String contrasenia;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private TextField txtUsuario;

    @FXML
    void onIngresar(ActionEvent event) {
        iniciarSesion();

    }



    @FXML
    void initialize() {
        cargarPropiedades();

    }

    private void cargarPropiedades() {

        Properties propiedades = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/programacion3/parcial2/parcial_app/ejercicio5/login.properties")) {
            propiedades.load(input);
            usuario = propiedades.getProperty("usuario");
            contrasenia = propiedades.getProperty("contrasenia");
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la configuración.", Alert.AlertType.ERROR);
        }
    }
    private void iniciarSesion() {
        String usuarioIngresado = txtUsuario.getText();
        String contraseniaIngresada = txtContrasenia.getText();

        // Validar que los campos no sean nulos o vacíos
        if (usuarioIngresado.isEmpty() || contraseniaIngresada.isEmpty()) {
            mostrarAlerta("Error de inicio de sesión", "Por favor, complete todos los campos.", Alert.AlertType.ERROR);
            return; // Salir del método si los campos no están completos
        }

        if (usuarioIngresado.equals(usuario) && contraseniaIngresada.equals(contrasenia)) {
            mostrarAlerta("Inicio de sesión exitoso", "¡Bienvenido!", Alert.AlertType.INFORMATION);

            // Cargar la interfaz de gestión de pedidos
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/programacion3/parcial2/parcial_app/asignacionMaterias-view.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) txtUsuario.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                stage.setTitle("Gestión de pedidos");
            } catch (IOException e) {
                e.printStackTrace();
                mostrarAlerta("Error", "No se pudo cargar la vista de gestión de pedidos.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Error de inicio de sesión", "Usuario o contraseña incorrectos.", Alert.AlertType.ERROR);
        }
    }



    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

}
