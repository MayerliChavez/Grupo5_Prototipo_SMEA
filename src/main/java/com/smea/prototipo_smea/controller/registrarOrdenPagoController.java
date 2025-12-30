package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class registrarOrdenPagoController
        implements Initializable, ControladorInyectable {

    private MainController mainController;

    /* ===== CAMPOS ===== */
    @FXML private TextField textFieldProveedor;
    @FXML private TextField textFieldMonto;
    @FXML private TextField textFieldTipoPago;
    @FXML private TextField textFieldTiempoEntrega;

    @FXML private DatePicker datePickerFechaEmision;
    @FXML private DatePicker datePickerFechaVigente;

    @FXML private TextArea textAreaObservaciones;

    @FXML private Button buttonCrearOrdenPago;
    @FXML private Button buttonRegistrarDocumentoRespaldo;
    @FXML private Button buttonRegresar;

    /* ===== INYECCIÓN ===== */
    @Override
    public void setMainController(MainController main) {
        this.mainController = main;
    }

    /* ===== INIT ===== */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Fechas por defecto
        datePickerFechaEmision.setValue(LocalDate.now());
        datePickerFechaVigente.setValue(LocalDate.now().plusDays(30));

        configurarTabulacion();
    }

    private void configurarTabulacion() {
        configurarTab(textFieldProveedor, textFieldMonto);
        configurarTab(textFieldMonto, datePickerFechaEmision);
        configurarTab(datePickerFechaEmision, textFieldTipoPago);
        configurarTab(textFieldTipoPago, textFieldTiempoEntrega);
        configurarTab(textFieldTiempoEntrega, textAreaObservaciones);
    }

    private void configurarTab(Control actual, Control siguiente) {
        actual.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.TAB) {
                siguiente.requestFocus();
                event.consume();
            }
        });
    }

    /* ===== ACCIONES ===== */

    @FXML
    private void clickCreaOrdenPago(ActionEvent event) {

        if (!validarCampos()) return;

        System.out.println("=== ORDEN DE PAGO ===");
        System.out.println("Proveedor: " + textFieldProveedor.getText());
        System.out.println("Monto: " + textFieldMonto.getText());
        System.out.println("Fecha emisión: " + datePickerFechaEmision.getValue());
        System.out.println("Fecha vigencia: " + datePickerFechaVigente.getValue());
        System.out.println("Tipo pago: " + textFieldTipoPago.getText());
        System.out.println("Tiempo entrega: " + textFieldTiempoEntrega.getText());
        System.out.println("Observaciones: " + textAreaObservaciones.getText());

        mostrarAlerta(
                "Orden registrada",
                "La orden de pago se registró correctamente",
                Alert.AlertType.INFORMATION
        );

        regresarMenuProveedor();
    }

    @FXML
    private void clickRegistrarDocumentoRespaldo(ActionEvent event) {
        mainController.saver("registraDocumentoRespaldo.fxml");
    }

    @FXML
    private void regresarMenuProveedor() {
        mainController.saver("moduloProveedor.fxml");
    }

    /* ===== VALIDACIONES ===== */
    private boolean validarCampos() {

        if (textFieldProveedor.getText().isEmpty()
                || textFieldMonto.getText().isEmpty()
                || textFieldTipoPago.getText().isEmpty()
                || datePickerFechaEmision.getValue() == null) {

            mostrarAlerta(
                    "Campos obligatorios",
                    "Complete todos los campos requeridos",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        try {
            double monto = Double.parseDouble(textFieldMonto.getText());
            if (monto <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            mostrarAlerta(
                    "Monto inválido",
                    "Ingrese un monto válido mayor a 0",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
