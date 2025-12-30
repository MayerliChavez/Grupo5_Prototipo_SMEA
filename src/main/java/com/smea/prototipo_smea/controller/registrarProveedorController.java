package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

    @Override
    public void setMainController(MainController main) {
        this.mainController = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabulacion();

        textfieldNombre.setOnAction(e -> textfieldTelefono.requestFocus());
        textfieldTelefono.setOnAction(e -> textfielRazonSocial.requestFocus());
        textfielRazonSocial.setOnAction(e -> textfieldRUC.requestFocus());
        textfieldRUC.setOnAction(e -> textfieldDireccion.requestFocus());
        textfieldDireccion.setOnAction(e -> textfieldCorreo.requestFocus());
        textfieldCorreo.setOnAction(e -> buttonCrearUsuario.requestFocus());
    }

    private void configurarTabulacion() {
        configurarTab(textfieldNombre, textfieldTelefono);
        configurarTab(textfieldTelefono, textfielRazonSocial);
        configurarTab(textfielRazonSocial, textfieldRUC);
        configurarTab(textfieldRUC, textfieldDireccion);
        configurarTab(textfieldDireccion, textfieldCorreo);
        configurarTab(textfieldCorreo, buttonCrearUsuario);
    }

    private void configurarTab(Control actual, Control siguiente) {
        actual.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.TAB) {
                siguiente.requestFocus();
                event.consume();
            }
        });
    }

    @FXML
    private void clickCrearUsuario(ActionEvent event) {

        // 1. Validar campos obligatorios
        if (!validarCampos()) {
            return;
        }

        // 2. Obtener datos
        String nombre = textfieldNombre.getText();
        String razonSocial = textfielRazonSocial.getText();
        String ruc = textfieldRUC.getText();
        String telefono = textfieldTelefono.getText();
        String direccion = textfieldDireccion.getText();
        String correo = textfieldCorreo.getText();

        // 3. Aquí iría la lógica real
        // 👉 Guardar en base de datos
        // 👉 Llamar a un servicio
        // 👉 Usar DAO / Repository

        System.out.println("=== REGISTRO PROVEEDOR ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("Razón Social: " + razonSocial);
        System.out.println("RUC: " + ruc);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Correo: " + correo);

        // 4. Confirmación
        mostrarAlerta(
                "Registro exitoso",
                "El proveedor ha sido registrado correctamente",
                Alert.AlertType.INFORMATION
        );

        regresarMenuProveedor();
    }

    @FXML
    private void clikcRegresarMenuPrincipal(ActionEvent event) {
        System.out.println("Regresar al Menú Principal (Técnico Coordinador)");
        mainController.saver("moduloProveedor.fxml");
    }

    private void regresarMenuProveedor() {
        System.out.println("Regresar al Menú Principal (Técnico Coordinador)");
        mainController.saver("moduloProveedor.fxml");
    }

    private boolean validarCampos() {
        String RUC =  textfieldRUC.getText();
        String telefono =  textfieldTelefono.getText();

        if (textfieldNombre.getText().isEmpty()
                || textfieldRUC.getText().isEmpty()
                || textfielRazonSocial.getText().isEmpty()
                || textfieldTelefono.getText().isEmpty()
                || textfieldDireccion.getText().isEmpty()
                || textfieldCorreo.getText().isEmpty()) {

            mostrarAlerta(
                    "Campos obligatorios",
                    "Todos los campos deben ser llenados",
                    Alert.AlertType.WARNING
            );
            return false;
        }

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


        if(telefono.length() < 10 ){
            mostrarAlerta(
                    "Número de teléfono inválido" ,
                    "Ingrese un número de teléfono válido",
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
