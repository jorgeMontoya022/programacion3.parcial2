module programacion3.parcial2.parcial_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens programacion3.parcial2.parcial_app to javafx.fxml;
    exports programacion3.parcial2.parcial_app;

    opens programacion3.parcial2.parcial_app.ejercicio1.viewController;
    exports programacion3.parcial2.parcial_app.ejercicio1.viewController;
}