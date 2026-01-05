package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class moduloAdministracionSistemaController implements Initializable, ControladorInyectable {

    // ====== CONTROLADOR CENTRAL ======
    private MainController mainController;

    @FXML private Button buttonGestionarParametros;
    @FXML private Button buttonRegistrarUsuario;
    @FXML private Button buttonActualizarUsuario;
    @FXML private Button buttonAsignarPermisos;
    @FXML private Button buttonRegresar;

    // ===============================
    // MÉTODOS DE NAVEGACIÓN
    // ===============================

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarIconos();
    }

    // ================= ICONOS =================
    private void cargarIconos() {
        configurarIcono(buttonGestionarParametros, "/Imagenes/iconoGestionarParametros.png");
        configurarIcono(buttonRegistrarUsuario, "/Imagenes/iconoRegistrarUsuario.png");
        configurarIcono(buttonActualizarUsuario, "/Imagenes/iconoActualizarUsuario.png");
        configurarIcono(buttonAsignarPermisos, "/Imagenes/iconoAsignarPermisos.png");
    }

    private void configurarIcono(Button boton, String ruta) {
        Image image = new Image(getClass().getResourceAsStream(ruta));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(68);
        imageView.setFitHeight(68);
        imageView.setPreserveRatio(true);

        boton.setGraphic(imageView);
        boton.setContentDisplay(ContentDisplay.TOP);
        boton.setGraphicTextGap(25);
    }

    // ================= EVENTOS =================

    @FXML
    private void clickGestionarParametros(ActionEvent event) {
        mainController.saver("gestionarParametros.fxml");
    }

    @FXML
    private void clickRegistrarUsuario(ActionEvent event) {
        mainController.saver("registrarUsuario.fxml");
    }

    @FXML
    private void clickActualizarUsuario(ActionEvent event) {
        mainController.saver("actualizarUsuario.fxml");
    }

    @FXML
    private void clickAsignarPermisos(ActionEvent event) {
        mainController.saver("asignarPermisos.fxml");
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("menuSMEA.fxml");
    }
}
