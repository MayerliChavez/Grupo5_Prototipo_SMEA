package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class actualizarDatosPController implements Initializable, ControladorInyectable {

    private MainController mainController;

    // ====== CAMPOS EDITABLES ======
    @FXML private TextField textfieldNombre;
    @FXML private TextField textfielRazonSocial;
    @FXML private TextField textfieldTelefono;
    @FXML private TextField textfieldDireccion;

    // ====== CAMPOS SOLO LECTURA (NO SE MODIFICAN) ======
    @FXML private TextField textfieldRUC;
    @FXML private TextField textfieldCorreo;

    @FXML private Button buttonGuardarCambios;
    @FXML private Button buttonRegresar;

    @Override
    public void setMainController(MainController main) {
        this.mainController = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarDatosSimulados();
        bloquearCamposNoEditables();
    }

    // ================== DATOS SIMULADOS ==================

    private void cargarDatosSimulados() {
        textfieldNombre.setText("Proveedor ABC");
        textfielRazonSocial.setText("Proveedor ABC S.A.");
        textfieldTelefono.setText("0987654321");
        textfieldDireccion.setText("Av. Principal y Secundaria");

        textfieldRUC.setText("1799999999001");
        textfieldCorreo.setText("proveedor@correo.com");
    }

    private void bloquearCamposNoEditables() {
        textfieldRUC.setEditable(false);
        textfieldCorreo.setEditable(false);
    }

    // ================== GUARDAR CAMBIOS ==================

    @FXML
    private void clickguardarCambios(ActionEvent event) {

        if (textfieldNombre.getText().isBlank()
                || textfielRazonSocial.getText().isBlank()
                || textfieldTelefono.getText().isBlank()
                || textfieldDireccion.getText().isBlank()) {

            mostrarAlerta(
                    "Campos incompletos",
                    "Todos los campos habilitados deben estar llenos",
                    Alert.AlertType.WARNING
            );
            return;
        }

        // Aquí luego va la lógica de BD
        mostrarAlerta(
                "Datos actualizados",
                "La información del proveedor se actualizó correctamente",
                Alert.AlertType.INFORMATION
        );
    }

    // ================== NAVEGACIÓN ==================

    @FXML
    private void clikcRegresarMenu(ActionEvent event) {
        mainController.saver("actualizarDatosProveedor.fxml");
    }

    // ================== UTIL ==================

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
