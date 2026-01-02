package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.ResourceBundle;

public class registrarProveedorController
        implements Initializable, ControladorInyectable {

    private MainController mainController;

    @FXML private TextField textfieldNombre;
    @FXML private TextField textfielRazonSocial;
    @FXML private TextField textfieldRUC;
    @FXML private TextField textfieldTelefono;
    @FXML private TextField textfieldDireccion;
    @FXML private TextField textfieldCorreo;
    @FXML private Button buttonCrearUsuario;
    @FXML private Button buttonRegresar;

    // ================== INYECCIÓN ==================
    @Override
    public void setMainController(MainController main) {
        this.mainController = main;
    }

    // ================== INIT ==================
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        configurarTabulacion();

        textfieldNombre.setOnAction(e -> textfieldRUC.requestFocus());
        textfieldRUC.setOnAction(e -> textfielRazonSocial.requestFocus());
        textfielRazonSocial.setOnAction(e -> textfieldTelefono.requestFocus());
        textfieldTelefono.setOnAction(e -> textfieldDireccion.requestFocus());
        textfieldDireccion.setOnAction(e -> textfieldCorreo.requestFocus());
        textfieldCorreo.setOnAction(e -> buttonCrearUsuario.requestFocus());
    }

    // ================== TABULACIÓN ==================
    private void configurarTabulacion() {
        configurarTab(textfieldNombre, textfieldRUC);
        configurarTab(textfieldRUC, textfielRazonSocial);
        configurarTab(textfielRazonSocial, textfieldTelefono);
        configurarTab(textfieldTelefono, textfieldDireccion);
        configurarTab(textfieldDireccion, textfieldCorreo);
        configurarTab(textfieldCorreo, buttonCrearUsuario);
    }

    private void configurarTab(Control actual, Control siguiente) {
        actual.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.TAB) {
                siguiente.requestFocus();
                event.consume();
            }
        });
    }

    // ================== CREAR PROVEEDOR ==================
    @FXML
    private void clickCrearUsuario(ActionEvent event) {

        if (!validarCampos()) {
            return;
        }

        String nombre = textfieldNombre.getText();
        String razonSocial = textfielRazonSocial.getText();
        String ruc = textfieldRUC.getText();
        String telefono = textfieldTelefono.getText();
        String direccion = textfieldDireccion.getText();
        String correo = textfieldCorreo.getText();

        // Simulación de guardado
        System.out.println("=== REGISTRO PROVEEDOR ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("Razón Social: " + razonSocial);
        System.out.println("RUC: " + ruc);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Dirección: " + direccion);
        System.out.println("Correo: " + correo);

        mostrarAlerta(
                "Registro exitoso",
                "El proveedor ha sido registrado correctamente",
                Alert.AlertType.INFORMATION
        );

        regresarMenuProveedor();
    }

    // ================== REGRESAR ==================
    @FXML
    private void clikcRegresarMenuPrincipal(ActionEvent event) {
        regresarMenuProveedor();
    }

    private void regresarMenuProveedor() {
        mainController.saver("moduloProveedor.fxml");
    }

    // ================== VALIDACIONES ==================
    private boolean validarCampos() {

        String ruc = textfieldRUC.getText().trim();
        String telefono = textfieldTelefono.getText().trim();
        String correo = textfieldCorreo.getText().trim();

        // Campos vacíos
        if (textfieldNombre.getText().isEmpty()
                || textfielRazonSocial.getText().isEmpty()
                || ruc.isEmpty()
                || telefono.isEmpty()
                || textfieldDireccion.getText().isEmpty()
                || correo.isEmpty()) {

            mostrarAlerta(
                    "Campos obligatorios",
                    "Todos los campos deben ser llenados",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        // Correo
        if (!correo.contains("@")) {
            mostrarAlerta(
                    "Correo inválido",
                    "Ingrese un correo electrónico válido",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        // RUC: solo números, 13 dígitos y termina en 001
        if (!ruc.matches("\\d{13}") || !ruc.endsWith("001")) {
            mostrarAlerta(
                    "RUC inválido",
                    "El RUC debe tener 13 números y terminar en 001",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        // Teléfono: solo números y mínimo 10 dígitos
        if (!telefono.matches("\\d{10,}")) {
            mostrarAlerta(
                    "Teléfono inválido",
                    "El teléfono solo debe contener números y mínimo 10 dígitos",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        return true;
    }

    // ================== ALERTAS ==================
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
