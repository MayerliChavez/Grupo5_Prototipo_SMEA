package com.smea.prototipo_smea.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class generarOrdenTrabajoController implements Initializable, ControladorInyectable {

    private MainController mainController;

    // ===================== CAMPOS CLIENTE =====================
    @FXML private TextField txtCliente;
    @FXML private TextField txtContacto;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtEmail;

    // ===================== DATOS TRABAJO =====================
    @FXML private ChoiceBox<String> choiceTipoTrabajo;
    @FXML private ChoiceBox<String> choicePrioridad;
    @FXML private TextArea txtDescripcion;

    // ===================== ASIGNACIÓN =====================
    @FXML private ChoiceBox<String> choiceTecnico;
    @FXML private DatePicker dateProgramada;
    @FXML private TextField txtObservaciones;

    // ===================== BOTONES =====================
    @FXML private Button btnGuardar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnCancelar;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ===================== INIT =====================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicializarChoiceBox();
        inicializarDatePicker();
        dateProgramada.setValue(LocalDate.now());
    }

    // ===================== INICIALIZACIÓN =====================
    private void inicializarChoiceBox() {

        choiceTipoTrabajo.setItems(FXCollections.observableArrayList(
                "Mantenimiento Preventivo",
                "Mantenimiento Correctivo",
                "Instalación",
                "Inspección"
        ));

        choicePrioridad.setItems(FXCollections.observableArrayList(
                "Baja",
                "Media",
                "Alta",
                "Crítica"
        ));

        choiceTecnico.setItems(FXCollections.observableArrayList(
                "Juan Pérez",
                "María López",
                "Carlos Andrade",
                "Equipo Externo"
        ));
    }

    private void inicializarDatePicker() {
        dateProgramada.setValue(LocalDate.now());
        dateProgramada.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(LocalDate.now()));
            }
        });
    }

    // ===================== ACCIONES =====================

    @FXML
    private void guardarOrden() {

        if (!validarFormulario()) {
            return;
        }

        // 🚧 Aquí irá la lógica de guardado en BD
        // OrdenTrabajoDAO.guardar(...)

        mostrarMensaje(
                "Orden generada",
                "La orden de trabajo se ha generado correctamente.",
                Alert.AlertType.INFORMATION
        );

        limpiarFormulario();
    }

    @FXML
    private void limpiarFormulario() {

        txtCliente.clear();
        txtContacto.clear();
        txtDireccion.clear();
        txtEmail.clear();

        choiceTipoTrabajo.setValue(null);
        choicePrioridad.setValue(null);
        txtDescripcion.clear();

        choiceTecnico.setValue(null);
        dateProgramada.setValue(LocalDate.now());
        txtObservaciones.clear();
    }

    @FXML
    private void cancelar() {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Cancelar");
        confirmacion.setHeaderText("¿Desea cancelar la generación?");
        confirmacion.setContentText("Los datos ingresados se perderán.");

        if (confirmacion.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            limpiarFormulario();
        }
    }

    // ===================== VALIDACIONES =====================
    private boolean validarFormulario() {

        if (txtCliente.getText().isBlank()) {
            mostrarMensaje("Validación", "Debe ingresar el nombre del cliente.", Alert.AlertType.WARNING);
            txtCliente.requestFocus();
            return false;
        }

        if (choiceTipoTrabajo.getValue() == null) {
            mostrarMensaje("Validación", "Debe seleccionar el tipo de trabajo.", Alert.AlertType.WARNING);
            return false;
        }

        if (choicePrioridad.getValue() == null) {
            mostrarMensaje("Validación", "Debe seleccionar la prioridad.", Alert.AlertType.WARNING);
            return false;
        }

        if (txtDescripcion.getText().isBlank()) {
            mostrarMensaje("Validación", "Debe ingresar una descripción del trabajo.", Alert.AlertType.WARNING);
            return false;
        }

        if (choiceTecnico.getValue() == null) {
            mostrarMensaje("Validación", "Debe asignar un técnico responsable.", Alert.AlertType.WARNING);
            return false;
        }

        if (dateProgramada.getValue() == null) {
            mostrarMensaje("Validación", "Debe seleccionar una fecha programada.", Alert.AlertType.WARNING);
            return false;
        }

        if (!txtEmail.getText().contains("@")) {
            mostrarMensaje(
                    "Correo electrónico inválido",
                    "Ingrese un correo electrónico válido",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        if(txtContacto.getLength() < 10 ){
            mostrarMensaje(
                    "Número de teléfono inválido" ,
                    "Ingrese un número de teléfono válido",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        return true;
    }

    @FXML
    private void regresar() {
        mainController.saver("planificacionMantenimiento.fxml");
    }

    // ===================== UTIL =====================
    private void mostrarMensaje(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
