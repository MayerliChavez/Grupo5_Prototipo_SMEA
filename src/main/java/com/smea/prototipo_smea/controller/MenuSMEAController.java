package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuSMEAController
        implements Initializable, ControladorInyectable {

    // Inyectado por MainController
    private MainController mainController;
    @FXML
    private ChoiceBox<String> tipousuario;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField contrasena;
    @FXML
    private Button buttonIngresar;
    @FXML
    private Button buttonSalir;

    private final String[] caracteresNoPermitidos = {
            " ", ":", "?", "¿", "!", "¡"
    };

    @Override
    public void setMainController(MainController main) {
        this.mainController = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabulacion();

        tipousuario.getItems().addAll(
                "Gerente General y Técnico",
                "Administrador",
                "Representante de Cobranzas",
                "Representante de Tesorería",
                "Operador",
                "Técnico Coordinador",
                "Bodeguero",
                "Bodeguero Coordinador"
        );

        usuario.setOnAction(e -> contrasena.requestFocus());
        contrasena.setOnAction(e -> buttonIngresar.requestFocus());
    }

    private void configurarTabulacion() {
        configurarTab(usuario, contrasena);
        configurarTab(contrasena, buttonIngresar);
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
    private void ingresar(ActionEvent event) {

        String user = usuario.getText();
        String pass = contrasena.getText();
        String rol  = tipousuario.getValue();

        if (user == null || user.length() < 4) {
            mostrarAlerta("Usuario inválido",
                    "El usuario debe tener al menos 4 caracteres");
            return;
        }

        if (pass == null || pass.length() < 8) {
            mostrarAlerta("Contraseña inválida",
                    "La contraseña debe tener al menos 8 caracteres");
            return;
        }

        if (rol == null) {
            mostrarAlerta("Rol no seleccionado",
                    "Debe seleccionar un tipo de usuario");
            return;
        }

        for (String c : caracteresNoPermitidos) {
            if (user.contains(c) || pass.contains(c)) {
                mostrarAlerta("Caracteres no permitidos",
                        "No se permiten espacios ni símbolos especiales");
                return;
            }
        }

        switch (rol) {
            case "Administrador" ->{
                    mainController.setRolUsuario(rol);
                    mainController.saver("menuAdministrador.fxml");
            }

            case "Gerente General y Técnico" ->{
                    mainController.setRolUsuario(rol);
                    mainController.saver("menuGerencia.fxml");
            }

            case "Operador" ->{
                    mainController.setRolUsuario(rol);
                    mainController.saver("menuOperador.fxml");
            }

            case "Representante de Cobranzas" ->{
                    mainController.setRolUsuario(rol);
                    mainController.saver("menuRepresentanteC.fxml");
            }

            case "Representante de Tesorería" ->{
                    mainController.setRolUsuario(rol);
                    mainController.saver("menuRepresentanteT.fxml");
            }

            case "Técnico Coordinador" ->{
                    mainController.setRolUsuario(rol);
                    mainController.saver("menuTecnicoC.fxml");
            }

            case "Bodeguero" ->{
                    mainController.setRolUsuario(rol);
                    mainController.saver("menuBodeguero.fxml");
            }

            case "Bodeguero Coordinador" ->{
                    mainController.setRolUsuario(rol);
                    mainController.saver("menuBodegueroC.fxml");
            }

            default ->
                    mostrarAlerta(
                            "Rol sin menú",
                            "Este rol aún no tiene un menú asignado"
                    );
        }

        System.out.println("Botón ingresar presionado");
        System.out.println("Usuario: " + usuario.getText());
        System.out.println("Tipo: " + tipousuario.getValue());
    }

    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) buttonSalir.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
