package programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.controller.AsignacionMateriasContoller;
import programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model.Asignacion;
import programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model.Docente;
import programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model.Estudiante;
import programacion3.parcial2.parcial_app.ejercicio5AsignacionMaterias.model.Materia;

public class AsignacionMateriasViewController {
     AsignacionMateriasContoller asignacionMateriasContoller;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Estudiante> ListViewEstudiantes;

    @FXML
    private ListView<Docente> listViewDocentes;

    @FXML
    private ListView<Materia> listViewMaterias;

    @FXML
    private TextArea txtAsignaciones;

    @FXML
    void onAsignarMaterias(ActionEvent event) {
        asignarMaterias();

    }



    @FXML
    void onLimpiarTextArea(ActionEvent event) {
        txtAsignaciones.setText("");

    }

    @FXML
    void onMostarMateriasAsignadas(ActionEvent event) {
        mostarMaterias();

    }



    @FXML
    void initialize() {
        Asignacion.inicializarContador();
        asignacionMateriasContoller = new AsignacionMateriasContoller();
        configurarSeleccionMultiple();
        getEstudiantes();
        getDocentes();
        getMaterias();


    }

    private void configurarSeleccionMultiple() {
        ListViewEstudiantes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void getMaterias() {
        List<Materia> listaMaterias = asignacionMateriasContoller.getMaterias();
        listViewMaterias.getItems().clear(); // Limpiar antes de agregar
        if (listaMaterias != null && !listaMaterias.isEmpty()) {
            listViewMaterias.getItems().addAll(listaMaterias);
        }
    }

    private void getDocentes() {
        List<Docente> listaDocentes = asignacionMateriasContoller.getDocentes();
        listViewDocentes.getItems().clear(); // Limpiar antes de agregar
        if (listaDocentes != null && !listaDocentes.isEmpty()) {
            listViewDocentes.getItems().addAll(listaDocentes);
        }
    }

    private void getEstudiantes() {
        List<Estudiante> listaEstudiantes = asignacionMateriasContoller.getEstudiantes();
        ListViewEstudiantes.getItems().clear(); // Limpiar antes de agregar
        if (listaEstudiantes != null && !listaEstudiantes.isEmpty()) {
            ListViewEstudiantes.getItems().addAll(listaEstudiantes);
        }
    }

    private void asignarMaterias() {
        if (validarFormulario()) {
            Asignacion asignacion = buildDataAsignacionMaterias();
            boolean suceso = asignacionMateriasContoller.asignarMateria(asignacion);
            if(suceso) {
                mostrarMensaje("Materia asignada con éxito", "Materia asignada", "la materia se ha asignado exitosamente", Alert.AlertType.INFORMATION);
            }else{
                mostrarMensaje("Error", "Materia no asignada", "la materia no ha sido asignada exitosamente", Alert.AlertType.INFORMATION);
            }
        }else{
            mostrarMensaje("Error", "Datos invalidos", "Por favor complete todos los campos requeridos con datos válidos.", Alert.AlertType.ERROR);
        }
    }

    private void mostarMaterias() {
        List<Asignacion> materiasAsignadas = asignacionMateriasContoller.mostrarMateriasAsignadas();
        StringBuilder sb = new StringBuilder();

        // Verifica si la lista de asignaciones está vacía
        if (materiasAsignadas.isEmpty()) {
            sb.append("No hay materias asignadas.");
        } else {
            // Recorre cada asignación y formatea la salida
            for (Asignacion asignacion : materiasAsignadas) {
                sb.append("Código Asignación: ").append(asignacion.getCodigoAsignacion()).append("\n");
                sb.append("Código materia").append(asignacion.getCodigoMateria()).append("\n");
                sb.append("Materia: ").append(asignacion.getNombreMateria()).append("\n");
                sb.append("Código Docente: ").append(asignacion.getCodigoDocente()).append("\n");
                sb.append("Estudiantes:\n");
                for (String codigoEstudiante : asignacion.getCodigoEstudiantes()) {
                    sb.append(" - ").append(codigoEstudiante).append("\n");
                }
                sb.append("\n"); // Añade un espacio entre cada asignación
            }
        }

        // Asigna el texto formateado al TextArea
        txtAsignaciones.setText(sb.toString());
    }


    private Asignacion buildDataAsignacionMaterias() {
        // Obtener el docente seleccionado
        Docente docenteSeleccionado = listViewDocentes.getSelectionModel().getSelectedItem();
        String codigoDocente = docenteSeleccionado != null ? docenteSeleccionado.getCodigo() : null;

        // Obtener la materia seleccionada
        Materia materiaSeleccionada = listViewMaterias.getSelectionModel().getSelectedItem();
        String codigoMateria = materiaSeleccionada != null ? materiaSeleccionada.getCodigo() : null;
        String nombreMateria = materiaSeleccionada != null ? materiaSeleccionada.getNombre() : null;

        // Obtener los estudiantes seleccionados
        List<Estudiante> estudiantesSeleccionados = ListViewEstudiantes.getSelectionModel().getSelectedItems();
        List<String> codigosEstudiantes = new ArrayList<>();
        for (Estudiante estudiante : estudiantesSeleccionados) {
            codigosEstudiantes.add(estudiante.getCodigo()); // Asegúrate de que getCodigo() devuelva el código correcto
        }

        // Crear y devolver el objeto Asignacion
        return new Asignacion(codigoMateria, nombreMateria, codigoDocente, codigosEstudiantes);
    }

    private boolean validarFormulario() {
        return !ListViewEstudiantes.getSelectionModel().getSelectedItems().isEmpty()
                && !listViewMaterias.getSelectionModel().getSelectedItems().isEmpty()
                && !listViewDocentes.getSelectionModel().getSelectedItems().isEmpty();
    }


    private void mostrarMensaje(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
