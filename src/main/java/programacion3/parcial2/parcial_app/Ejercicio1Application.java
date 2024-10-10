package programacion3.parcial2.parcial_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Ejercicio1Application extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Ejercicio1Application.class.getResource("estudiante-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("GESTIÓN DE ESTUDIANTES");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}