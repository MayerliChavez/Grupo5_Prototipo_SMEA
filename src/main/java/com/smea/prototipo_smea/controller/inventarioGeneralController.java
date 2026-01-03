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

public class inventarioGeneralController
        implements Initializable, ControladorInyectable {

    // ================== CONTROLADOR PRINCIPAL ==================
    private MainController mainController;

    // ================== BOTONES ==================
    @FXML private Button buttonBuscarCodigo;
    @FXML private Button buttonBuscarCategoria;
    @FXML private Button buttonRegresar;

    // ================== INYECCIÓN ==================
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ================== INIT ==================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarIconos();
    }

    private void cargarIconos() {

        configurarIcono(
                buttonBuscarCategoria,
                "/Imagenes/iconoBuscarCategoria.png"
        );
        configurarIcono(
                buttonBuscarCodigo,
                "/Imagenes/iconoBuscarCodigo.png"
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

    // ================== EVENTOS ==================

    @FXML
    private void clickBuscarCodigo(ActionEvent event) {
        // Vista para buscar elemento por código
        mainController.saver("buscarElementoCodigo.fxml");
    }

    @FXML
    private void clickBuscarCategoria(ActionEvent event) {
        // Vista para buscar elemento por categoría
        mainController.saver("buscarElementoCategoria.fxml");
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        // Regresar al submódulo anterior
        mainController.saver("moduloInventario.fxml");
    }
}
