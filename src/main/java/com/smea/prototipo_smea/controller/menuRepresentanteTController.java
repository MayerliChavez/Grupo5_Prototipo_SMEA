package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class menuRepresentanteTController implements Initializable, ControladorInyectable {

    // Inyectado por MainController
    private MainController mainController;
    @FXML
    private Label textBienvenida;
    @FXML
    private TextField labelNombre;
    @FXML
    private Button buttonModuloReportes;
    @FXML
    private Button buttonRegresarMenuPrincipal;

    @Override
    public void setMainController(MainController main) {
        this.mainController = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configuramos el nombre por defecto (puedes cambiarlo según la lógica de tu login)
        labelNombre.setText("Representante de Tesorería");
        labelNombre.setEditable(false);
        cargarIconos();
    }

    // ================= ICONOS ======================

    private void cargarIconos() {
        configurarIcono(
                buttonModuloReportes,
                "/Imagenes/iconoReportes.png"
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

    @FXML
    private void clickAbrirModuloReportes(ActionEvent event) {
        // Asegúrate de tener este fxml creado
        mainController.saver("moduloReportes.fxml");
    }

    @FXML
    private void clikcRegresarMenuPrincipal(ActionEvent event) {
        // Regresa al login o menú principal
        mainController.saver("menuSMEA.fxml");
    }
}