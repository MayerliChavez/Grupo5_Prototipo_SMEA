package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class planificacionMantenimientoController implements Initializable, ControladorInyectable {

    // ===== CONTROLADOR CENTRAL =====
    private MainController mainController;

    // ===== FXML =====
    @FXML private Label labelBienvenida;

    @FXML private Button buttonRegistrarOrdenTrabajo;
    @FXML private Button buttonVisualizarCalendario;
    @FXML private Button buttonConsultarOrdenTrabajo;
    @FXML private Button buttonConsultarRepuesto;
    @FXML private Button buttonRegresar;

    // ===== INYECCIÓN =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ===== INITIALIZE =====
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarIconos();
    }

    // ================= ICONOS =================
    private void cargarIconos() {

        configurarIcono(buttonRegistrarOrdenTrabajo, "/Imagenes/iconoOrdenTrabajo.png");
        configurarIcono(buttonVisualizarCalendario, "/Imagenes/iconoCalendario.png");
        configurarIcono(buttonConsultarOrdenTrabajo, "/Imagenes/iconoConsultarOrdenTrabajo.png");
        configurarIcono(buttonConsultarRepuesto, "/Imagenes/iconoBuscarRepuesto.png");
    }

    private void configurarIcono(Button boton, String ruta) {
        Image image = new Image(getClass().getResourceAsStream(ruta));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(90);
        imageView.setFitHeight(90);
        imageView.setPreserveRatio(true);

        boton.setGraphic(imageView);
        boton.setContentDisplay(ContentDisplay.TOP);
        boton.setGraphicTextGap(15);
    }

    // ================= EVENTOS =================

    @FXML
    private void clickRegistrarCatalogo(ActionEvent event) {
        mainController.saver("generarOrdenTrabajo.fxml");
    }

    @FXML
    private void clickActualizarCatalogo(ActionEvent event) {
        mainController.saver("visualizarCalendario.fxml");
    }

    @FXML
    private void clickAsignarCategoria(ActionEvent event) {
        mainController.saver("consultarOrdenTrabajo.fxml");
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("moduloMantenimiento.fxml");
    }
}
