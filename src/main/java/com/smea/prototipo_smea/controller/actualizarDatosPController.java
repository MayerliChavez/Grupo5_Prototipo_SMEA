package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.Proveedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
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
        configurarTabulacion();

        textfieldNombre.setOnAction(e -> textfieldTelefono.requestFocus());
        textfieldTelefono.setOnAction(e -> textfielRazonSocial.requestFocus());
        textfielRazonSocial.setOnAction(e -> textfieldDireccion.requestFocus());
        textfieldDireccion.setOnAction(e -> textfieldCorreo.requestFocus());
        textfieldCorreo.setOnAction(e -> buttonGuardarCambios.requestFocus());
    }

    private void configurarTabulacion() {
        configurarTab(textfieldNombre, textfieldTelefono);
        configurarTab(textfieldTelefono, textfielRazonSocial);
        configurarTab(textfielRazonSocial, textfieldDireccion);
        configurarTab(textfieldDireccion, textfieldCorreo);
        configurarTab(textfieldCorreo, buttonGuardarCambios);
    }

    private void configurarTab(Control actual, Control siguiente) {
        actual.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.TAB) {
                siguiente.requestFocus();
                event.consume();
            }
        });
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
    }

    // ================== GUARDAR CAMBIOS ==================

    @FXML
    private void clickguardarCambios(ActionEvent event) {

        if (textfieldNombre.getText().isBlank()
                || textfielRazonSocial.getText().isBlank()
                || textfieldTelefono.getText().isBlank()
                || textfieldDireccion.getText().isBlank()
                || textfieldCorreo.getText().isBlank()) {

            mostrarAlerta(
                    "Campos incompletos",
                    "Todos los campos habilitados deben estar llenos",
                    Alert.AlertType.WARNING
            );
            return;
        }

        if(!verificarCampos()){
            return;
        }

        // Aquí luego va la lógica de BD
        mostrarAlerta(
                "Datos actualizados",
                "La información del proveedor se actualizó correctamente",
                Alert.AlertType.INFORMATION
        );
    }

    private boolean verificarCampos(){
        String correo = textfieldCorreo.getText();
        String RUC = textfieldRUC.getText();
        String telefono =  textfieldTelefono.getText();

        if (!textfieldCorreo.getText().contains("@")) {
            mostrarAlerta(
                    "Correo electrónico inválido",
                    "Ingrese un correo electrónico válido",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        if (RUC.length() != 13 || !RUC.endsWith("001")) {
            mostrarAlerta(
                    "RUC inválido",
                    "El RUC debe tener 13 dígitos y terminar en 001",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        if (!telefono.matches("\\d{10}") || !validarSoloNumeros(telefono)) {
            mostrarAlerta(
                    "Teléfono inválido",
                    "El teléfono tiene caracteres no permitidos",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        return true;
    }

    private boolean validarSoloNumeros(String cadena) {

        if (!cadena.matches("\\d+")) {
            return false;
        }
        return true;
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
