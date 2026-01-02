package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class registrarClienteController
        implements Initializable, ControladorInyectable {

    // ====== CONTROLADOR PRINCIPAL ======
    private MainController mainController;

    // ====== CAMPOS DEL FORMULARIO ======
    @FXML private TextField textfieldNombre;
    @FXML private TextField textfieldApellido;
    @FXML private TextField textfieldCedulaRUC;
    @FXML private TextField textfieldTelefono;
    @FXML private TextField textfieldDireccion;
    @FXML private TextField textfieldCorreo;

    // ====== BOTONES ======
    @FXML private Button buttonRegresar;
    @FXML private Button buttonCrearCliente;

    // ====== INYECCIÓN DEL MAIN CONTROLLER ======
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ====== INIT ======
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarTabulacion();
        textfieldNombre.setOnAction(e -> textfieldCedulaRUC.requestFocus());
        textfieldCedulaRUC.setOnAction(e -> textfieldApellido.requestFocus());
        textfieldApellido.setOnAction(e -> textfieldTelefono.requestFocus());
        textfieldTelefono.setOnAction(e -> textfieldDireccion.requestFocus());
        textfieldDireccion.setOnAction(e -> textfieldCorreo.requestFocus());
        textfieldCorreo.setOnAction(e -> buttonCrearCliente.requestFocus());
    }

    private void configurarTabulacion() {
        configurarTab(textfieldNombre, textfieldCedulaRUC);
        configurarTab(textfieldCedulaRUC, textfieldApellido);
        configurarTab(textfieldApellido, textfieldTelefono);
        configurarTab(textfieldTelefono, textfieldDireccion);
        configurarTab(textfieldDireccion, textfieldCorreo);
        configurarTab(textfieldCorreo, buttonCrearCliente);
    }

    private void configurarTab(Control actual, Control siguiente) {
        actual.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.TAB) {
                siguiente.requestFocus();
                event.consume();
            }
        });
    }

    // =================================================
    // EVENTOS
    // =================================================

    @FXML
    private boolean clickCrearCliente(ActionEvent event) {

        String nombre = textfieldNombre.getText().trim();
        String apellido = textfieldApellido.getText().trim();
        String cedulaRuc = textfieldCedulaRUC.getText().trim();
        String telefono = textfieldTelefono.getText().trim();
        String direccion = textfieldDireccion.getText().trim();
        String correo = textfieldCorreo.getText().trim();

        // ===== VALIDACIÓN BÁSICA =====
        if (nombre.isEmpty() || apellido.isEmpty() || cedulaRuc.isEmpty()
           || telefono.isEmpty() || direccion.isEmpty() || correo.isEmpty()) {
            mostrarAlerta(
                    "Campos obligatorios",
                    "Rellene los campos que son obligatorios",
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

        if (cedulaRuc.length() != 13  && cedulaRuc.length() != 10 && !cedulaRuc.endsWith("001")) {
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

        // ===== SIMULACIÓN DE REGISTRO =====
        System.out.println("=== CLIENTE REGISTRADO ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Cédula/RUC: " + cedulaRuc);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Dirección: " + direccion);
        System.out.println("Correo: " + correo);

        mostrarAlerta(
                "Registro exitoso",
                "El cliente fue registrado correctamente",
                Alert.AlertType.INFORMATION
        );

        return true;
    }

    /**
     * Regresar al menú anterior
     */
    @FXML
    private void clikcRegresarMenuPrincipal(ActionEvent event) {
        mainController.saver("moduloCliente.fxml");
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
