package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.ElementoCatalogo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class actualizarCatalogoController
        implements Initializable, ControladorInyectable {

    // ================== CONTROLADOR PRINCIPAL ==================
    private MainController mainController;

    // ================== CAMPOS BUSQUEDA ==================
    @FXML private TextField textFieldNombreCliente;

    // ================== CAMPOS EDICION ==================
    @FXML private TextField textFieldNombre;
    @FXML private TextField textFieldUbicacion;
    @FXML private TextField textFieldCantidad;

    // ================== BOTONES ==================
    @FXML private Button buttonGuardar;
    @FXML private Button buttonRegresar;
    @FXML private Button buttonBuscarNombre;

    // ================== ELEMENTO ACTUAL ==================
    private ElementoCatalogo elementoActual;

    // ================== INYECCIÓN ==================
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ================== INIT ==================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bloquearCampos(true);
        validarSoloNumeros(textFieldCantidad);
        ConfigurarTab();

        textFieldNombreCliente.setOnAction(e-> buttonBuscarNombre.requestFocus());
        textFieldNombre.setOnAction(e-> textFieldUbicacion.requestFocus());
        textFieldUbicacion.setOnAction(e-> textFieldCantidad.requestFocus());
        textFieldCantidad.setOnAction(e-> buttonGuardar.requestFocus());
    }

    public void ConfigurarTab(){
        configurarTab(textFieldNombreCliente,buttonBuscarNombre);
        configurarTab(textFieldNombre,textFieldUbicacion);
        configurarTab(textFieldUbicacion, textFieldCantidad);
        configurarTab(textFieldCantidad,buttonGuardar);
    }

    private void configurarTab(Control actual, Control siguiente) {
        actual.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.TAB) {
                siguiente.requestFocus();
                event.consume();
            }
        });
    }

    // ================== BUSCAR PRODUCTO ==================
    @FXML
    private void buscarProducto(ActionEvent event) {

        String nombreBusqueda = textFieldNombreCliente.getText();

        if (nombreBusqueda == null || nombreBusqueda.trim().isEmpty()) {
            mostrarAlerta(
                    "Aviso",
                    "Ingrese el nombre del producto a buscar",
                    Alert.AlertType.WARNING
            );
            return;
        }

        // 🔹 Simulación de búsqueda (luego reemplazas por BD)
        elementoActual = buscarElementoSimulado(nombreBusqueda);

        if (elementoActual == null) {
            mostrarAlerta(
                    "Sin resultados",
                    "No se encontró un producto con ese nombre",
                    Alert.AlertType.INFORMATION
            );
            bloquearCampos(true);
            return;
        }

        // Cargar datos en campos
        textFieldNombre.setText(elementoActual.getNombre());
        textFieldUbicacion.setText(elementoActual.getUbicacion());
        textFieldCantidad.setText(
                String.valueOf(elementoActual.getCantidad())
        );

        bloquearCampos(false);
    }

    // ================== GUARDAR CAMBIOS ==================
    @FXML
    private void guardarCambios(ActionEvent event) {

        if (elementoActual == null) {
            mostrarAlerta(
                    "Aviso",
                    "Primero debe buscar un producto",
                    Alert.AlertType.WARNING
            );
            return;
        }

        String nombre = textFieldNombre.getText();
        String ubicacion = textFieldUbicacion.getText();
        String cantidadTexto = textFieldCantidad.getText();

        if (nombre.isBlank() || ubicacion.isBlank() || cantidadTexto.isBlank()) {
            mostrarAlerta(
                    "Error",
                    "Todos los campos son obligatorios",
                    Alert.AlertType.ERROR
            );
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadTexto);
            if (cantidad < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            mostrarAlerta(
                    "Error",
                    "La cantidad debe ser un número válido mayor o igual a 0",
                    Alert.AlertType.ERROR
            );
            return;
        }

        // Guardar cambios
        elementoActual.setNombre(nombre);
        elementoActual.setUbicacion(ubicacion);
        elementoActual.setCantidad(cantidad);

        mostrarAlerta(
                "Éxito",
                "Producto actualizado correctamente",
                Alert.AlertType.INFORMATION
        );

        limpiarFormulario();
        bloquearCampos(true);
    }

    // ================== REGRESAR ==================
    @FXML
    private void regresarModuloInventario(ActionEvent event) {
        mainController.saver("catalogoInventario.fxml");
    }

    // ================== MÉTODOS AUXILIARES ==================

    private void bloquearCampos(boolean bloquear) {
        textFieldNombre.setDisable(bloquear);
        textFieldUbicacion.setDisable(bloquear);
        textFieldCantidad.setDisable(bloquear);
        buttonGuardar.setDisable(bloquear);
    }

    private void limpiarFormulario() {
        textFieldNombreCliente.clear();
        textFieldNombre.clear();
        textFieldUbicacion.clear();
        textFieldCantidad.clear();
        elementoActual = null;
    }

    private void validarSoloNumeros(TextField campo) {
        campo.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                campo.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // ================== BÚSQUEDA SIMULADA ==================
    private ElementoCatalogo buscarElementoSimulado(String nombre) {
        if (nombre.equalsIgnoreCase("Llave")) {
            return new ElementoCatalogo(
                    "IR-002",
                    "Llave",
                    "Herramienta",
                    6,
                    "Útil para tornillos",
                    "Bodega A",
                    "2026-01-02",
                    "Herramienta",
                    "DISPONIBLE"
            );
        }
        return null;
    }
}
