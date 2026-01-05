package com.smea.prototipo_smea.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class seguridadAuditoriaController implements Initializable, ControladorInyectable {

    private MainController mainController;

    // ================== FXML ==================

    @FXML
    private Button buttonControlarAcceso;

    @FXML
    private Button buttonControlarSeciones;

    @FXML
    private Button buttonRegresar;

    // ================== INYECCIÓN ==================

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
                "/Imagenes/iconoVisualizarRoles.png"
        );
        configurarIcono(
                buttonControlarSeciones,
                "/Imagenes/iconoVisualizarPermisos.png"
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

    // ================== INIT ==================

    // ================== ACCIONES ==================

    @FXML
    private void clickControlarAcceso() {
        mainController.saver("seguridadroles.fxml");
    }

    @FXML
    private void clickControlarSesiones() {
        mainController.saver("seguridadPermisos.fxml");
    }

    @FXML
    private void clickRegresar() {
        mainController.saver("moduloAuditoria.fxml");
    }
}
