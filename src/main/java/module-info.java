module programacion3.parcial2.parcial_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens programacion3.parcial2.parcial_app to javafx.fxml;
    exports programacion3.parcial2.parcial_app;
}