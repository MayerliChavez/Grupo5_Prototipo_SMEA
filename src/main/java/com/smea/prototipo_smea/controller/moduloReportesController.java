package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class moduloReportesController implements Initializable, ControladorInyectable {

    private MainController mainController;

    // ====== FXML ======
    @FXML private Button buttonOperativos;
    @FXML private Button buttonRRHH;
    @FXML private Button buttonRegresar;

    // ====== INYECCIÓN ======
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ====== INIT ======
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            cargarIconos();
    }

    // ================== ICONOS ==================
    private void cargarIconos() {

        configurarIcono(
                buttonOperativos,
                "/Imagenes/iconoOperativos.png"
        );
        configurarIcono(
                buttonRRHH,
                "/Imagenes/iconoRRHH.png"
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
        boton.setGraphicTextGap(25);
    }

    // ====== NAVEGACIÓN ======

    @FXML
    private void clickOperativos() {
        mainController.saver("reportesOperativos.fxml");
    }

    @FXML
    private void clickRRHH() {
        mainController.saver("reportesRRHH.fxml");
    }


    @FXML
    private void clickRegresar(ActionEvent event) {
        switch (mainController.getRolUsuario()) {
            case "Administrador" ->
                    mainController.saver("menuAdministrador.fxml");
            case "Representante de Tesorería" ->
                    mainController.saver("menuTecnicoC.fxml");
            case "Gerente General y Técnico" ->
                    mainController.saver("menuGerencia.fxml");
        }
    }
}
