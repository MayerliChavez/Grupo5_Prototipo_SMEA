package com.smea.prototipo_smea.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class devolucionInventarioController implements Initializable, ControladorInyectable {

    // ================== CONTROLADOR PRINCIPAL ==================
    private MainController mainController;

    // ===================== CAMPOS DE BÚSQUEDA =====================
    @FXML private TextField textFieldCodigoItem;
    @FXML private Button buttonBuscar;

    // ===================== DATOS DEL ÍTEM =====================
    @FXML private TextField textFieldNombreItem;
    @FXML private TextField textFieldTipoItem;
    @FXML private TextField textFieldStockActual;

    // ===================== DATOS DEVOLUCIÓN =====================
    @FXML private ChoiceBox<String> choiceBoxTipoDevolucion;
    @FXML private TextField textFieldCantidad;
    @FXML private TextArea textAreaMotivo;
    @FXML private DatePicker datePickerFecha;

    // ===================== BOTONES =====================
    @FXML private Button buttonConfirmar;
    @FXML private Button buttonRegresar;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ===================== INIT =====================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarTiposDevolucion();
        configurarCamposIniciales();

        textFieldCodigoItem.setOnAction(e-> buttonBuscar.requestFocus());
        choiceBoxTipoDevolucion.setOnAction(e-> textFieldCantidad.requestFocus());
        textFieldCantidad.setOnAction(e-> textAreaMotivo.requestFocus());
        textAreaMotivo.setOnKeyPressed(e -> {
                    if (e.getCode().toString().equals("ENTER")) {
                        datePickerFecha.requestFocus();
                    }
                }
        );
        datePickerFecha.setOnAction(e -> buttonConfirmar.requestFocus());
    }

    // ================== TABULACIÓN ==================
    private void configurarTabulacion() {
        configurarTab(textFieldCodigoItem, buttonBuscar);
        configurarTab(choiceBoxTipoDevolucion, textFieldCantidad);
        configurarTab(textFieldCantidad, textAreaMotivo);
        configurarTab(textAreaMotivo, datePickerFecha);
        configurarTab(datePickerFecha, buttonConfirmar);
    }

    private void configurarTab(Control actual, Control siguiente) {
        actual.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.TAB) {
                siguiente.requestFocus();
                event.consume();
            }
        });
    }

    // ===================== CONFIGURACIÓN =====================
    private void cargarTiposDevolucion() {
        choiceBoxTipoDevolucion.setItems(
                FXCollections.observableArrayList(
                        "HERRAMIENTA",
                        "REPUESTO"
                )
        );
    }

    private void configurarCamposIniciales() {
        textFieldNombreItem.setEditable(false);
        textFieldTipoItem.setEditable(false);
        textFieldStockActual.setEditable(false);
        datePickerFecha.setValue(LocalDate.now());
    }

    // ===================== BUSCAR ÍTEM =====================
    @FXML
    private void buscarItem(ActionEvent event) {

        String codigo = textFieldCodigoItem.getText();

        if (codigo == null || codigo.trim().isEmpty()) {
            mostrarAlerta("Aviso",
                    "Ingrese el código del ítem.",
                    Alert.AlertType.WARNING);
            return;
        }

        // 🔹 SIMULACIÓN DE BÚSQUEDA (BD después)
        if (codigo.equalsIgnoreCase("ITM-001")) {
            textFieldNombreItem.setText("Taladro Industrial");
            textFieldTipoItem.setText("HERRAMIENTA");
            textFieldStockActual.setText("10");
        } else if (codigo.equalsIgnoreCase("REP-002")) {
            textFieldNombreItem.setText("Filtro de Aceite");
            textFieldTipoItem.setText("REPUESTO");
            textFieldStockActual.setText("25");
        } else {
            mostrarAlerta("Error",
                    "El ítem no existe.",
                    Alert.AlertType.ERROR);
            limpiarCamposItem();
        }
    }

    // ===================== CONFIRMAR DEVOLUCIÓN =====================
    @FXML
    private void confirmarDevolucion(ActionEvent event) {

        if (!validarCampos()) return;

        int cantidadDevuelta = Integer.parseInt(textFieldCantidad.getText());
        int stockActual = Integer.parseInt(textFieldStockActual.getText());

        int nuevoStock = stockActual + cantidadDevuelta;

        // 🔹 AQUÍ VA LA LÓGICA DE BD
        // update inventario set stock = nuevoStock where codigo = ?

        mostrarAlerta("Éxito",
                "Devolución registrada correctamente.\n" +
                        "Nuevo stock: " + nuevoStock,
                Alert.AlertType.INFORMATION);

        limpiarFormulario();
    }

    // ===================== VALIDACIONES =====================
    private boolean validarCampos() {

        if (textFieldNombreItem.getText().isEmpty()) {
            mostrarAlerta("Aviso",
                    "Debe buscar un ítem válido.",
                    Alert.AlertType.WARNING);
            return false;
        }

        if (choiceBoxTipoDevolucion.getValue() == null) {
            mostrarAlerta("Aviso",
                    "Seleccione el tipo de devolución.",
                    Alert.AlertType.WARNING);
            return false;
        }

        if (textFieldCantidad.getText().isEmpty()) {
            mostrarAlerta("Aviso",
                    "Ingrese la cantidad devuelta.",
                    Alert.AlertType.WARNING);
            return false;
        }

        try {
            int cantidad = Integer.parseInt(textFieldCantidad.getText());
            if (cantidad <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error",
                    "La cantidad debe ser un número mayor a 0.",
                    Alert.AlertType.ERROR);
            return false;
        }

        if (textAreaMotivo.getText().isEmpty()) {
            mostrarAlerta("Aviso",
                    "Ingrese el motivo de la devolución.",
                    Alert.AlertType.WARNING);
            return false;
        }

        if (datePickerFecha.getValue() == null) {
            mostrarAlerta("Aviso",
                    "Seleccione la fecha de devolución.",
                    Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    // ===================== LIMPIAR =====================
    private void limpiarCamposItem() {
        textFieldNombreItem.clear();
        textFieldTipoItem.clear();
        textFieldStockActual.clear();
    }

    private void limpiarFormulario() {
        textFieldCodigoItem.clear();
        limpiarCamposItem();
        textFieldCantidad.clear();
        textAreaMotivo.clear();
        choiceBoxTipoDevolucion.setValue(null);
        datePickerFecha.setValue(LocalDate.now());
    }

    // ===================== REGRESAR =====================
    @FXML
    private void regresar(ActionEvent event) {
        System.out.println("Regresar al módulo anterior");
        mainController.saver("movimientosInventario.fxml");
    }

    // ===================== ALERTA =====================
    private void mostrarAlerta(String titulo, String mensaje,
                               Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
