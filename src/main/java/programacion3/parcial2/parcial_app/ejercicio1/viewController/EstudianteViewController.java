package programacion3.parcial2.parcial_app.ejercicio1.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import programacion3.parcial2.parcial_app.ejercicio1.builder.EstudianteBuilder;
import programacion3.parcial2.parcial_app.ejercicio1.controller.EstudianteController;
import programacion3.parcial2.parcial_app.ejercicio1.model.Estudiante;

public class EstudianteViewController {
    ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();
    FilteredList<Estudiante> filteredListEstudiante;
    Estudiante seleccionado;

    private EstudianteController estudianteController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Estudiante> tableEstudiante;

    @FXML
    private TableColumn<Estudiante, String> tcCódigo;

    @FXML
    private TableColumn<Estudiante, String> tcNombre;

    @FXML
    private TableColumn<Estudiante, String> tcNota1;

    @FXML
    private TableColumn<Estudiante, String> tcNota2;

    @FXML
    private TableColumn<Estudiante, String> tcNota3;

    @FXML
    private TextField txtCódigo;

    @FXML
    private TextField txtFilter;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNota1;

    @FXML
    private TextField txtNota2;

    @FXML
    private TextField txtNota3;

    @FXML
    void onAgregar(ActionEvent event) {
        agregarEstudiante();
    }

    @FXML
    void onLimpiarCampos(ActionEvent event) {
        limpiarCampos();

    }

    @FXML
    void initialize() {
        estudianteController = new EstudianteController();
        initView();
        setupFilter();
    }

    private void initView() {
        initDataBinding();
        getEstudiantes();
        filteredListEstudiante = new FilteredList<>(listaEstudiantes, p -> true); // Inicializa el FilteredList aquí
        tableEstudiante.setItems(filteredListEstudiante); // Usa el FilteredList en lugar de la lista original
        listenerSelection();
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcCódigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        tcNota1.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNota1())));
        tcNota2.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNota2())));
        tcNota3.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNota3())));
    }

    private void getEstudiantes() {
        listaEstudiantes.clear(); // Limpia la lista antes de cargar nuevos estudiantes
        listaEstudiantes.addAll(estudianteController.getEstudiantes()); // Carga los estudiantes
    }

    private void setupFilter() {
        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListEstudiante.setPredicate(estudiante -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Mostrar todos los estudiantes si el campo está vacío
                }
                // Filtrar por código
                return estudiante.getCodigo().toLowerCase().contains(newValue.toLowerCase());
            });
        });
    }

    private void listenerSelection() {
        tableEstudiante.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            seleccionado = newSelection; // Actualiza el estudiante seleccionado
            showInformationEstudiante(seleccionado);
        });
    }

    private void showInformationEstudiante(Estudiante estudianteSeleccionado) {
        if (estudianteSeleccionado != null) {
            txtNombre.setText(estudianteSeleccionado.getNombre());
            txtCódigo.setText(estudianteSeleccionado.getCodigo());
            txtNota1.setText(String.valueOf(estudianteSeleccionado.getNota1()));
            txtNota2.setText(String.valueOf(estudianteSeleccionado.getNota2()));
            txtNota3.setText(String.valueOf(estudianteSeleccionado.getNota3()));
        }
    }

    private void agregarEstudiante() {
        if (validarFormulario()) {
            Estudiante estudiante = buildDataEstudiante();
            if (estudianteController.agregarEstudiante(estudiante)) {
                listaEstudiantes.add(estudiante);
                mostrarMensaje("Notificación Estudiante", "Estudiante creado", "El estudiante ha sido creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCampos();
            } else {
                mostrarMensaje("Error", "Creación fallida", "No se pudo crear el estudiante.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Error", "Datos inválidos", "Por favor, ingrese datos válidos", Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtCódigo.setText("");
        txtNota1.setText("");
        txtNota2.setText("");
        txtNota3.setText("");
    }

    private Estudiante buildDataEstudiante() {
        return new EstudianteBuilder()
                .nombre(txtNombre.getText())
                .codigo(txtCódigo.getText())
                .nota1(Double.parseDouble(txtNota1.getText()))
                .nota2(Double.parseDouble(txtNota2.getText()))
                .nota3(Double.parseDouble(txtNota3.getText()))
                .build();
    }


    private boolean validarFormulario() {
        return !txtNombre.getText().isEmpty()
                && !txtCódigo.getText().isEmpty()
                && !txtNota1.getText().isEmpty()
                && !txtNota2.getText().isEmpty()
                && !txtNota3.getText().isEmpty();
    }

    private void mostrarMensaje(String title, String header, String content, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(title);
        aler.setHeaderText(header);
        aler.setContentText(content);
        aler.showAndWait();
    }
}
