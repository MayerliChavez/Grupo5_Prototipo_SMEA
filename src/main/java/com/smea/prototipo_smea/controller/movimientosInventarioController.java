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

public class movimientosInventarioController implements Initializable, ControladorInyectable {

    // ===== CONTROLADOR PRINCIPAL =====
    private MainController mainController;

    // ===== FXML =====
    @FXML private Label labelBienvenida;

    @FXML private Button buttonDevolucion;
    @FXML private Button buttonRegistrarEntrada;
    @FXML private Button buttonRegistrarOrdenSalida;
    @FXML private Button buttonHistorialMovimiento;
    @FXML private Button buttonConsultarOrdenSalida;
    @FXML private Button buttonRegresar;

    // ===== INYECCIÓN =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ===== INITIALIZE =====
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarIconos();
    }

    // ================= ICONOS =================
    private void cargarIconos() {
        configurarIcono(
                buttonDevolucion,
                "/Imagenes/iconoDevolucion.png"
        );
        configurarIcono(
                buttonRegistrarEntrada,
                "/Imagenes/iconoRegistrarEntrada.png"
        );
        configurarIcono(
                buttonRegistrarOrdenSalida,
                "/Imagenes/iconoRegistrarOrdenSalida.png"
        );
        configurarIcono(
                buttonHistorialMovimiento,
                "/Imagenes/iconoHistorialMovimientos.png"
        );
        configurarIcono(
                buttonConsultarOrdenSalida,
                "/Imagenes/iconoConsultarOrdenSalida.png"
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

    // ================= EVENTOS =================

    @FXML
    private void clickDevolucion(ActionEvent event) {
        mainController.saver("devolucionInventario.fxml");
    }

    @FXML
    private void clickRegistrarEntrada(ActionEvent event) {
        mainController.saver("registrarEntradaInventario.fxml");
    }

    @FXML
    private void clickRegistrarOrdenSalida(ActionEvent event) {
        mainController.saver("registrarOrdenSalida.fxml");
    }

    @FXML
    private void clickHistorialMovimientos(ActionEvent event) {
        mainController.saver("historialMovimientos.fxml");
    }

    @FXML
    private void clickConsultarOrdenSalida(ActionEvent event) {
        mainController.saver("consultarOrdenSalida.fxml");
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("moduloInventario.fxml");
    }
}
