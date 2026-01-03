package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class registrarCatalogoController implements Initializable, ControladorInyectable {

    // ================== CONTROLADOR PRINCIPAL ==================
    private MainController mainController;

    // ================== CAMPOS DEL FORMULARIO ==================
    @FXML private TextField textFieldNombre;
    @FXML private TextField textFieldUbicacion;
    @FXML private TextField textFieldCantidad;
    @FXML private TextArea textAreaDescripcion;
    @FXML private ChoiceBox<String> choiseBoxElemento;
    @FXML private DatePicker datePickerFechaRegistro;

    // ================== BOTONES ==================
    @FXML private Button buttonGuardar;
    @FXML private Button buttonRegistrarOtro;
    @FXML private Button buttonRegresar;

    // ================== INYECCIÓN ==================
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ================== INIT ==================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarChoiceBox();
        configurarDatePicker();
        ConfigurarTab();

        choiseBoxElemento.setOnAction(e-> textFieldNombre.requestFocus());
        textFieldNombre.setOnAction(e-> textFieldUbicacion.requestFocus());
        textFieldUbicacion.setOnAction(e-> textFieldCantidad.requestFocus());
        textFieldCantidad.setOnAction(e-> datePickerFechaRegistro.requestFocus());
        datePickerFechaRegistro.setOnAction(e-> textAreaDescripcion.requestFocus());

    }

    // ================== CONFIGURACIONES ==================
    private void cargarChoiceBox() {
        choiseBoxElemento.getItems().addAll(
                "Herramienta",
                "Repuesto"
        );
    }

    public void ConfigurarTab(){
        configurarTab(choiseBoxElemento, textFieldNombre );
        configurarTab(textFieldNombre, textFieldUbicacion);
        configurarTab(textFieldUbicacion, textFieldCantidad);
        configurarTab(textFieldCantidad, datePickerFechaRegistro);
        configurarTab(datePickerFechaRegistro, textAreaDescripcion);
        configurarTab(textAreaDescripcion, buttonGuardar);
    }

    private void configurarTab(Control actual, Control siguiente) {
        actual.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.TAB) {
                siguiente.requestFocus();
                event.consume();
            }
        });
    }

    private void configurarDatePicker() {
        datePickerFechaRegistro.setValue(LocalDate.now());
    }

    // ================== EVENTOS ==================

    @FXML
    private void registrarElemento(ActionEvent event) {

        if (!validarFormulario()) {
            return;
        }

        String elemento = choiseBoxElemento.getValue();
        String nombre = textFieldNombre.getText().trim();
        String ubicacion = textFieldUbicacion.getText().trim();
        String descripcion = textAreaDescripcion.getText().trim();
        int cantidad = Integer.parseInt(textFieldCantidad.getText().trim());
        LocalDate fecha = datePickerFechaRegistro.getValue();

        // 🔹 Aquí luego conectas con DAO o servicio
        System.out.println("Elemento: " + elemento);
        System.out.println("Nombre: " + nombre);
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Fecha: " + fecha);
        System.out.println("Descripción: " + descripcion);

        mostrarMensaje("Registro exitoso", "Elemento registrado correctamente.");
    }

    @FXML
    private void registrarElementoOtravez(ActionEvent event) {
        limpiarFormulario();
    }

    @FXML
    private void regresarModuloInventario(ActionEvent event) {
        mainController.saver("catalogoInventario.fxml");
    }

    // ================== VALIDACIONES ==================
    private boolean validarFormulario() {

        if (choiseBoxElemento.getValue() == null) {
            mostrarError("Debe seleccionar el tipo de elemento.");
            return false;
        }

        if (textFieldNombre.getText().isBlank()) {
            mostrarError("El nombre no puede estar vacío.");
            return false;
        }

        if (textFieldCantidad.getText().isBlank()) {
            mostrarError("Debe ingresar la cantidad.");
            return false;
        }

        try {
            Integer.parseInt(textFieldCantidad.getText().trim());
        } catch (NumberFormatException e) {
            mostrarError("La cantidad debe ser numérica.");
            return false;
        }

        if (datePickerFechaRegistro.getValue() == null) {
            mostrarError("Debe seleccionar la fecha de registro.");
            return false;
        }

        return true;
    }

    // ================== UTILIDADES ==================
    private void limpiarFormulario() {
        choiseBoxElemento.setValue(null);
        textFieldNombre.clear();
        textFieldUbicacion.clear();
        textFieldCantidad.clear();
        textAreaDescripcion.clear();
        datePickerFechaRegistro.setValue(LocalDate.now());
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }
}
