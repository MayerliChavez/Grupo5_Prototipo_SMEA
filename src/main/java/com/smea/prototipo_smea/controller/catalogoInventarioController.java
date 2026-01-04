package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class catalogoInventarioController
        implements Initializable, ControladorInyectable {

    // ================== CONTROLADOR PRINCIPAL ==================
    private MainController mainController;

    // ================== BOTONES ==================
    @FXML private Button buttonRegistrarCatalogo;
    @FXML private Button buttonActualizarCatalogo;
    @FXML private Button buttonAsignarCategoria;
    @FXML private Button buttonConsultarCatalogo;
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

    // ================== ICONOS ==================
    private void cargarIconos() {

        configurarIcono(
                buttonRegistrarCatalogo,
                "/Imagenes/iconoRegistrarCatalogo.png"
        );
        configurarIcono(
                buttonActualizarCatalogo,
                "/Imagenes/iconoActualizarCatalogo.png"
        );
        configurarIcono(
                buttonAsignarCategoria,
                "/Imagenes/iconoCategoria.png"
        );
        configurarIcono(
                buttonConsultarCatalogo,
                "/Imagenes/iconoConsultar.png"
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
    private void clickRegistrarCatalogo(ActionEvent event) {
        System.out.println("Registrar catálogo");
        mainController.saver("registrarCatalogo.fxml");
    }

    @FXML
    private void clickActualizarCatalogo(ActionEvent event) {
        System.out.println("Actualizar catálogo");
        mainController.saver("actualizarCatalogo.fxml");
    }

    @FXML
    private void clickAsignarCategoria(ActionEvent event) {
        System.out.println("Asignar categoría");
        mainController.saver("asignarCategoria.fxml");
    }

    @FXML
    private void clickConsultarCatalogo(ActionEvent event) {
        System.out.println("Consultar producto");
        mainController.saver("consultarCatalogo.fxml");
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("moduloInventario.fxml");
    }
}
