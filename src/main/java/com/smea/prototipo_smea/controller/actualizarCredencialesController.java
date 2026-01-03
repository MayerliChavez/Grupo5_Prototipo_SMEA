package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class actualizarCredencialesController
        implements Initializable, ControladorInyectable {

    private MainController mainController;

    @FXML private ChoiceBox<String> chiceBoxCambios;

    @FXML private TextField textFieldUsuario;
    @FXML private TextField textFieldConfirmarUsuario;

    @FXML private PasswordField passwordFieldContrasena;
    @FXML private PasswordField passwordFieldConfirmarContrasena;

    @FXML private Label labelUsuario;
    @FXML private Label labelConfirmarUsuario;
    @FXML private Label labelContrasena;
    @FXML private Label labelConfirmarContrasena;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        chiceBoxCambios.getItems().addAll(
                "Usuario",
                "Contraseña",
                "Usuario y contraseña"
        );

        chiceBoxCambios.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> configurarVista(newVal));

        ocultarTodo();
    }

    private void ocultarTodo() {
        textFieldUsuario.setDisable(true);
        textFieldConfirmarUsuario.setDisable(true);
        passwordFieldContrasena.setDisable(true);
        passwordFieldConfirmarContrasena.setDisable(true);

        labelUsuario.setDisable(true);
        labelConfirmarUsuario.setDisable(true);
        labelContrasena.setDisable(true);
        labelConfirmarContrasena.setDisable(true);
    }

    private void configurarVista(String opcion) {
        ocultarTodo();

        if (opcion == null) return;

        switch (opcion) {
            case "Usuario" -> {
                mostrarUsuario();
            }
            case "Contraseña" -> {
                mostrarContrasena();
            }
            case "Usuario y contraseña" -> {
                mostrarUsuario();
                mostrarContrasena();
            }
        }
    }

    private void mostrarUsuario() {
        textFieldUsuario.setDisable(false);
        textFieldConfirmarUsuario.setDisable(false);
        labelUsuario.setDisable(false);
        labelConfirmarUsuario.setDisable(false);
    }

    private void mostrarContrasena() {
        passwordFieldContrasena.setDisable(false);
        passwordFieldConfirmarContrasena.setDisable(false);
        labelContrasena.setDisable(false);
        labelConfirmarContrasena.setDisable(false);
    }

    @FXML
    private void guardarCambios(ActionEvent event) {

        if (!validarDatos()) return;
        mostrarAlerta(
                "Actualización exitosa",
                "Las credenciales fueron actualizadas correctamente",
                Alert.AlertType.INFORMATION
        );
    }

    private boolean validarDatos() {

        if (textFieldUsuario.getText().isBlank()
                || !textFieldUsuario.getText()
                .equals(textFieldConfirmarUsuario.getText())) {
            mostrarAlerta(
                    "Error",
                    "Los usuarios no coinciden o están vacíos",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        if (!passwordFieldContrasena.getText().isBlank()
                || !passwordFieldContrasena.getText()
                .equals(passwordFieldConfirmarContrasena.getText())) {

            mostrarAlerta(
                    "Error",
                    "Las contraseñas no coinciden o están vacías",
                    Alert.AlertType.WARNING
            );
            return false;
        }
        return true;
    }

    @FXML
    private void regresarMenuVarias(ActionEvent event) {
        mainController.saver("menuAdministrador.fxml");
    }

    private void mostrarAlerta(String t, String m, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(t);
        alert.setHeaderText(null);
        alert.setContentText(m);
        alert.showAndWait();
    }
}
