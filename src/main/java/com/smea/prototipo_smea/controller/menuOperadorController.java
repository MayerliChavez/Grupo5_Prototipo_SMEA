package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class menuOperadorController implements Initializable, ControladorInyectable {

    // ====== CONTROLADOR CENTRAL ======
    private MainController mainController;

    // ====== FXML ======
    @FXML private TextField labelNombre;
    @FXML private Button buttonModuloMantenimiento;
    @FXML private Button buttonActualizarDatos;
    @FXML private Button buttonActualizarCredenciales;

    // ====== INYECCIÓN ======
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ====== INITIALIZE ======
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelNombre.setText("Bodeguero Coordinador");
        labelNombre.setEditable(false);
        cargarIconos();
    }

    // ================= ICONOS =================
    private void cargarIconos() {
        configurarIcono(buttonModuloMantenimiento, "/Imagenes/iconoMantenimiento.png");
        configurarIcono(buttonActualizarDatos, "/Imagenes/iconoInformacionPersonal.png");
        configurarIcono(buttonActualizarCredenciales, "/Imagenes/iconoActualizarCredenciales.png");
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

    // ================= EVENTOS =================

    @FXML
    private void clickModuloMantenimiento(ActionEvent event) {
        mainController.saver("moduloMantenimiento.fxml");
    }

    @FXML
    private void clickActualizarDatos(ActionEvent event) {
        mainController.saver("actualizarInformacion.fxml");
    }

    @FXML
    private void clickActualizarCredenciales(ActionEvent event) {
        mainController.saver("actualizarCredenciales.fxml");
    }

    @FXML
    private void clikcRegresarMenuPrincipal(ActionEvent event) {
        mainController.saver("menuSMEA.fxml");
    }
}
