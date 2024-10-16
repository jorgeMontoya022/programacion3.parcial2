module programacion3.parcial2.parcial_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;


    opens programacion3.parcial2.parcial_app to javafx.fxml;
    exports programacion3.parcial2.parcial_app;

    opens programacion3.parcial2.parcial_app.ejercicio1.viewController;
    exports programacion3.parcial2.parcial_app.ejercicio1.viewController;

    opens programacion3.parcial2.parcial_app.ejercicio2.viewController;
    exports programacion3.parcial2.parcial_app.ejercicio2.viewController;

    opens programacion3.parcial2.parcial_app.ejercicio2.model; // Asegúrate de abrir el paquete
    exports programacion3.parcial2.parcial_app.ejercicio2.model; // Exporta también el paquete3

    opens programacion3.parcial2.parcial_app.ejercicio4Restaurante.viewController;
    exports programacion3.parcial2.parcial_app.ejercicio4Restaurante.viewController;

    opens programacion3.parcial2.parcial_app.ejercicio4Restaurante.model; // Asegúrate de abrir el paquete
    exports programacion3.parcial2.parcial_app.ejercicio4Restaurante.model;

    opens programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.view;
    exports programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.view;

    opens programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model;
    exports programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model;


}