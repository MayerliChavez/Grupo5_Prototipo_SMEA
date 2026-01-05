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

public class registrarUsuarioController implements Initializable, ControladorInyectable {

    // ===== CONTROLADOR CENTRAL =====
    private MainController mainController;

    // ===== FXML =====
    @FXML private TextField textFieldUsuario;
    @FXML private TextField textFieldContraseña;

    @FXML private TextField textfieldNombre;
    @FXML private TextField textfielApellido;
    @FXML private TextField textfieldCedula;
    @FXML private TextField textfieldTelefono;
    @FXML private TextField textfieldCorreo;
    @FXML private TextField textfieldDireccion;

    @FXML private ChoiceBox<String> choiseBoxRol;
    @FXML private DatePicker datePickerFechaNacimiento;

    @FXML private Button buttonCrearUsuario;
    @FXML private Button buttonRegresar;

    // ===== INYECCIÓN =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ===== INITIALIZE =====
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Roles del sistema
        choiseBoxRol.setItems(FXCollections.observableArrayList(
                "Gerente General y Técnico",
                "Administrador",
                "Representante de Cobranzas",
                "Representante de Tesorería",
                "Operador",
                "Técnico Coordinador",
                "Bodeguero",
                "Bodeguero Coordinador"
        ));

        datePickerFechaNacimiento.setEditable(false);

        textFieldUsuario.setOnAction(e-> textFieldContraseña.requestFocus());
        textFieldContraseña.setOnAction(e-> textfieldNombre.requestFocus());
        textfieldNombre.setOnAction(e-> textfielApellido.requestFocus());
        textfielApellido.setOnAction(e-> textfieldCedula.requestFocus());
        textfieldCedula.setOnAction(e-> textfieldTelefono.requestFocus());
        textfieldTelefono.setOnAction(e-> textfieldDireccion.requestFocus());
        textfieldDireccion.setOnAction(e-> textfieldCorreo.requestFocus());
        textfieldCorreo.setOnAction(e-> datePickerFechaNacimiento.requestFocus());
        datePickerFechaNacimiento.setOnAction(e-> buttonCrearUsuario.requestFocus());

        configurarTabulacion();
    }

    // ================== TABULACIÓN ==================
    private void configurarTabulacion() {
        configurarTab(textFieldUsuario, textFieldContraseña);
        configurarTab(textFieldContraseña, textfieldNombre);
        configurarTab(textfieldNombre, textfielApellido);
        configurarTab(textfielApellido, textfieldCedula);
        configurarTab(textfieldCedula, textfieldTelefono);
        configurarTab(textfieldTelefono, textfieldDireccion);
        configurarTab(textfieldDireccion, textfieldCorreo);
        configurarTab(textfieldCorreo, datePickerFechaNacimiento);
        configurarTab(datePickerFechaNacimiento, buttonCrearUsuario);
    }

    private void configurarTab(Control actual, Control siguiente) {
        actual.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.TAB) {
                siguiente.requestFocus();
                event.consume();
            }
        });
    }

    // ================== REGISTRAR USUARIO ==================
    @FXML
    private void clickCrearUsuario(ActionEvent event) {

        if (!validarCampos()) {
            return;
        }

        System.out.println("=== NUEVO USUARIO REGISTRADO ===");
        System.out.println("Usuario: " + textFieldUsuario.getText());
        System.out.println("Nombre: " + textfieldNombre.getText());
        System.out.println("Apellido: " + textfielApellido.getText());
        System.out.println("Rol: " + choiseBoxRol.getValue());

        mostrarInfo("Usuario registrado correctamente.");

        limpiarFormulario();
    }

    // ================== VALIDACIONES ==================
    private boolean validarCampos() {

        if (textFieldUsuario.getText().trim().isEmpty()
                || textFieldContraseña.getText().trim().isEmpty()
                || textfieldNombre.getText().trim().isEmpty()
                || textfielApellido.getText().trim().isEmpty()
                || textfieldCedula.getText().trim().isEmpty()
                || textfieldCorreo.getText().trim().isEmpty()
                || choiseBoxRol.getValue() == null
                || datePickerFechaNacimiento.getValue() == null) {

            mostrarAlerta("Debe completar todos los campos obligatorios.");
            return false;
        }

        if (!textfieldCorreo.getText().contains("@")) {
            mostrarAlerta("El correo electrónico no es válido.");
            return false;
        }

        if (textfieldCedula.getText().length() < 10) {
            mostrarAlerta("La cédula ingresada no es válida.");
            return false;
        }

        if (textfieldTelefono.getText().length() < 10) {
            mostrarAlerta("La cédula ingresada no es válida.");
            return false;
        }

        LocalDate fechaNacimiento = datePickerFechaNacimiento.getValue();
        if (fechaNacimiento.isAfter(LocalDate.now())) {
            mostrarAlerta("La fecha de nacimiento no es válida.");
            return false;
        }

        if(textFieldUsuario.getLength() < 4){
            mostrarAlerta("El usuario es inválido, ingrese un usuario con más de 4 caracteres.");
            return false;
        }

        if(textFieldContraseña.getLength() < 8){
            mostrarAlerta("La contraseña es inválida, ingrese una contraseña con más de 8 caracteres.");
            return false;
        }

        return true;
    }

    // ================== LIMPIAR ==================
    private void limpiarFormulario() {
        textFieldUsuario.clear();
        textFieldContraseña.clear();
        textfieldNombre.clear();
        textfielApellido.clear();
        textfieldCedula.clear();
        textfieldTelefono.clear();
        textfieldCorreo.clear();
        textfieldDireccion.clear();
        choiseBoxRol.getSelectionModel().clearSelection();
        datePickerFechaNacimiento.setValue(null);
    }

    // ================== NAVEGACIÓN ==================
    @FXML
    private void clikcRegresarMenuPrincipal(ActionEvent event) {
        mainController.saver("moduloAdministracionSistema.fxml");
    }

    // ================== ALERTAS ==================
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
