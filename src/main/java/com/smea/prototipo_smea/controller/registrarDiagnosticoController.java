package com.smea.prototipo_smea.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class registrarDiagnosticoController implements Initializable, ControladorInyectable {

    private MainController mainController;
    @FXML private TextField txtCodigoOrden;
    @FXML private DatePicker dateDiagnostico;
    @FXML private TextArea txtDiagnostico;
    @FXML private TextField txtResponsable;
    @FXML private ComboBox<String> comboEstado;
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
        // Validar campos
        if(txtCodigoOrden.getText().isEmpty() || dateDiagnostico.getValue() == null || txtDiagnostico.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Complete los campos obligatorios.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Datos del diagnóstico
        String codigo = txtCodigoOrden.getText();
        String fecha = dateDiagnostico.getValue().toString();
        String diagnostico = txtDiagnostico.getText();
        String responsable = txtResponsable.getText();
        String estado = comboEstado.getValue();

        // Ejemplo de confirmación
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Diagnóstico registrado:\nCódigo: "+codigo+"\nFecha: "+fecha+"\nDiagnóstico: "+diagnostico, ButtonType.OK);
        alert.showAndWait();

        // Limpiar campos
        txtCodigoOrden.clear();
        dateDiagnostico.setValue(null);
        txtDiagnostico.clear();
        txtResponsable.clear();
        comboEstado.setValue(null);
    }

    @FXML
    private void clickCancelar(ActionEvent event) {
        txtCodigoOrden.clear();
        dateDiagnostico.setValue(null);
        txtDiagnostico.clear();
        txtResponsable.clear();
        comboEstado.setValue(null);
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("controlMantenimiento.fxml");
    }
}
