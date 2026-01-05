package com.smea.prototipo_smea.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class accesoAuditoriaController implements Initializable, ControladorInyectable {

    private MainController mainController;

    @FXML
    private AnchorPane paneFondo;

    @FXML
    private Button buttonControlarAcceso;

    @FXML
    private Button buttonControlarSeciones;

    @FXML
    private Button buttonRegresar;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarIconos();
    }

    private void cargarIconos() {

        configurarIcono(
                buttonControlarAcceso,
                "/Imagenes/iconoControlAcceso.png"
        );
        configurarIcono(
                buttonControlarSeciones,
                "/Imagenes/iconoControlSeccion.png"
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
    // NAVEGACIÓN
    // ===============================

    @FXML
    private void clickControlarAcceso(ActionEvent event) {
        mainController.saver("controlarAcceso.fxml");
    }

    @FXML
    private void clickControlarSesiones(ActionEvent event) {
        mainController.saver("controlarSesiones.fxml");
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("moduloAuditoria.fxml");
    }

    // ===============================
    // ALERTAS
    // ===============================

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
