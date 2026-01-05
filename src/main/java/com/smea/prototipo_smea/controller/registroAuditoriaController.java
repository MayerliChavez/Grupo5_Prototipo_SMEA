package com.smea.prototipo_smea.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class registroAuditoriaController implements Initializable, ControladorInyectable {

    private MainController mainController;

    // ================= BOTONES =================
    @FXML
    private Button buttonAccionesUsuarios;

    @FXML
    private Button buttonMovimientoInventario;

    @FXML
    private Button buttonMovimientoMantenimiento;

    @FXML
    private Button buttonRegresar;

    // ================= INYECCIÓN =================
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ================= INITIALIZE =================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarIconos();
    }

    // ================= ICONOS =================
    private void cargarIconos() {
        configurarIcono(buttonAccionesUsuarios, "/Imagenes/iconoAccionesUsuarios.png");
        configurarIcono(buttonMovimientoInventario, "/Imagenes/iconoMovimeintoInventario.png");
        configurarIcono(buttonMovimientoMantenimiento, "/Imagenes/iconoMovimeintoMantenimiento.png");
    }

    private void configurarIcono(Button boton, String ruta) {
        Image image = new Image(getClass().getResourceAsStream(ruta));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(95);
        imageView.setFitHeight(95);
        imageView.setPreserveRatio(true);

        boton.setGraphic(imageView);
        boton.setContentDisplay(ContentDisplay.TOP);
        boton.setGraphicTextGap(35);
    }

    // ================= NAVEGACIÓN =================
    @FXML
    private void clickAccionesUsuarios() {
        mainController.saver("accionesUsuarios.fxml");
    }

    @FXML
    private void clickMovimientoInventario() {
        mainController.saver("movimientoInventario.fxml");
    }

    @FXML
    private void clickMovimientoMantenimiento() {
        mainController.saver("movimientoMantenimiento.fxml");
    }

    @FXML
    private void clickRegresar() {
        mainController.saver("moduloAuditoria.fxml");
    }
}
