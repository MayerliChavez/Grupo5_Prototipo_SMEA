package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class moduloAuditoriaController implements Initializable, ControladorInyectable {

    private MainController mainController;


    @FXML
    private Button buttonAcceso;

    @FXML
    private Button buttonRegistros;

    @FXML
    private Button buttonSeguridad;

    @FXML
    private Button buttonRegresar;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarIconos();

    }

    private void cargarIconos() {

        configurarIcono(
                buttonAcceso,
                "/Imagenes/iconoAcceso.png"
        );
        configurarIcono(
                buttonRegistros,
                "/Imagenes/iconoRegistros.png"
        );
        configurarIcono(
                buttonSeguridad,
                "/Imagenes/iconoSeguridad.png"
        );
    }

    private void configurarIcono(Button boton, String ruta) {
        Image image = new Image(getClass().getResourceAsStream(ruta));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(120);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(true);

        boton.setGraphic(imageView);
        boton.setContentDisplay(ContentDisplay.TOP);
        boton.setGraphicTextGap(40);
    }

    // ===============================
    // ACCIONES DE LOS BOTONES
    // ===============================

    @FXML
    private void clickAcceso(ActionEvent event) {
        mainController.saver("accesoAuditoria.fxml");
    }

    @FXML
    private void clickRegistro(ActionEvent event) {
        mainController.saver("registroAuditoria.fxml");
    }

    @FXML
    private void clickSeguridad(ActionEvent event) {
        mainController.saver("seguridadAuditoria.fxml");
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("menuGerencia.fxml");
    }
}
