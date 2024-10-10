package programacion3.parcial2.parcial_app.ejercicio2.viewController;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import programacion3.parcial2.parcial_app.ejercicio2.controller.ProgramaController;
import programacion3.parcial2.parcial_app.ejercicio2.model.Programa;


public class ProgramasUViewController {
    ProgramaController programaController;
    Programa programaSeleccionado;
    ObservableList<Programa> listaProgramas = FXCollections.observableArrayList();
    FilteredList<Programa> filteredListProgramas;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cbModalidad;

    @FXML
    private TableView<Programa> tablePrograma;

    @FXML
    private TableColumn<Programa, String> tcAdscrito;

    @FXML
    private TableColumn<Programa, String> tcCodigo;

    @FXML
    private TableColumn<Programa, String> tcModalidad;

    @FXML
    private TableColumn<Programa, String> tcNombre;

    @FXML
    private TextField txtCódigo;

    @FXML
    private TextField txtNombre;


    @FXML
    private TextField txtFilter;

    @FXML
    void onAgregar(ActionEvent event) {
        agregarPrograma();

    }


    @FXML
    void onLimpliar(ActionEvent event) {
        limpiarCampos();

    }

    @FXML
    void initialize() {
        programaController = new ProgramaController();
        initView();
        cargarModalidades();
        setupFilter();


    }


    private void initView() {
        initDataBinding();
        getProgramas();
        filteredListProgramas = new FilteredList<>(listaProgramas, p -> true);
        tablePrograma.setItems(filteredListProgramas);
        listenerSelection();
    }


    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        tcAdscrito.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdscrito()));
        tcModalidad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModalidad()));
    }

    // Método para cargar modalidades desde un archivo de propiedades
    private void cargarModalidades() {
        Properties propiedades = new Properties();
        ObservableList<String> modalidades = FXCollections.observableArrayList();

        try (FileInputStream fis = new FileInputStream("src/main/java/programacion3/parcial2/parcial_app/ejercicio2/propiedades/properties.modalidad")) {
            propiedades.load(fis);

            // Cargar las modalidades desde el archivo
            for (int i = 1; ; i++) {
                String modalidad = propiedades.getProperty("modalidad" + i);
                if (modalidad == null) break; // Si no hay más modalidades, salir del bucle
                modalidades.add(modalidad);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Poblar el ComboBox con las modalidades cargadas
        cbModalidad.setItems(modalidades);
    }

    private void getProgramas() {
        listaProgramas.clear();
        listaProgramas.addAll(programaController.getProgramas());
    }

    private void setupFilter() {
        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListProgramas.setPredicate(programa -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Mostrar todos los estudiantes si el campo está vacío
                }
                // Filtrar por código
                return programa.getCodigo().toLowerCase().contains(newValue.toLowerCase());
            });
        });
    }

    private void listenerSelection() {
        tablePrograma.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            programaSeleccionado = newSelection;
            showInformationPrograma(programaSeleccionado);

        });
    }

    private void showInformationPrograma(Programa programaSeleccionado) {
        if (programaSeleccionado != null) {
            txtNombre.setText(programaSeleccionado.getNombre());
            txtCódigo.setText(programaSeleccionado.getCodigo());
            cbModalidad.getSelectionModel().select(programaSeleccionado.getModalidad());
        }
    }

    private void agregarPrograma() {
        if (validarFormulario()) {
            Programa programa = buildDataPrograma();
            if (programaController.agregarPrograma(programa)) {
                listaProgramas.add(programa);
                mostrarMensaje("Notificación Programa", "Programa creado", "El programa ha sido creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCampos();

            } else {
                mostrarMensaje("Error", "Creación fallida", "No se pudo crear el programa.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Error", "Datos inválidos", "Por favor, ingrese datos válidos", Alert.AlertType.WARNING);

        }
    }

    private void mostrarMensaje(String title, String header, String content, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(title);
        aler.setHeaderText(header);
        aler.setContentText(content);
        aler.showAndWait();
    }

    private void limpiarCampos() {
        txtCódigo.setText("");
        txtNombre.setText("");
        cbModalidad.setValue("");
        txtFilter.setText("");
    }

    private Programa buildDataPrograma() {
        String modalidad = cbModalidad.getValue();
        return new Programa(txtCódigo.getText(), txtNombre.getText(), modalidad);
    }

    private boolean validarFormulario() {
        return !txtNombre.getText().isEmpty()
                && !txtCódigo.getText().isEmpty();
    }


}
