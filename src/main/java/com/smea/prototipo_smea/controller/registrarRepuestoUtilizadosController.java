package com.smea.prototipo_smea.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class registrarRepuestoUtilizadosController implements Initializable, ControladorInyectable {

    private MainController mainController;

    @FXML private TextField txtCodigoOrden;
    @FXML private DatePicker dateUso;
    @FXML private TextField txtNombreRepuesto;
    @FXML private TextField txtCantidad;
    @FXML private TextField txtResponsable;
    @FXML private TextArea txtObservaciones;
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;

    @Override
    public void setMainController(MainController mainController) {
            this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // ================= EVENTOS =================
    @FXML
    private void clickGuardar(ActionEvent event) {
        // Validar campos obligatorios
        if(txtCodigoOrden.getText().isEmpty() || dateUso.getValue() == null || txtNombreRepuesto.getText().isEmpty() || txtCantidad.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Complete los campos obligatorios.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Datos del repuesto
        String codigoOrden = txtCodigoOrden.getText();
        String fecha = dateUso.getValue().toString();
        String nombreRepuesto = txtNombreRepuesto.getText();
        String cantidad = txtCantidad.getText();
        String responsable = txtResponsable.getText();
        String observaciones = txtObservaciones.getText();

        // Confirmación de registro
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Repuesto registrado:\nOrden: "+codigoOrden+"\nRepuesto: "+nombreRepuesto+"\nCantidad: "+cantidad, ButtonType.OK);
        alert.showAndWait();

        // Limpiar campos
        txtCodigoOrden.clear();
        dateUso.setValue(null);
        txtNombreRepuesto.clear();
        txtCantidad.clear();
        txtResponsable.clear();
        txtObservaciones.clear();
    }

    @FXML
    private void clickCancelar(ActionEvent event) {
        txtCodigoOrden.clear();
        dateUso.setValue(null);
        txtNombreRepuesto.clear();
        txtCantidad.clear();
        txtResponsable.clear();
        txtObservaciones.clear();
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("controlMantenimiento.fxml");
    }
}
