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

public class menuBodegueroCController
        implements ControladorInyectable, Initializable {

    // ====== CONTROLADOR CENTRAL ======
    private MainController mainController;

    // ====== COMPONENTES FXML ======
    @FXML private TextField labelNombre;
    @FXML private Button buttonModuloInventario;
    @FXML private Button buttonRegresarMenuPrincipal;

    // ====== INYECCIÓN DEL CONTROLADOR CENTRAL ======
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelNombre.setText("Bodeguero Coordinador");
        labelNombre.setEditable(false);
        cargarIconos();
    }

    private void cargarIconos() {
        // ICONO DEL BOTÓN MÓDULO INVENTARIO
        Image imageModuloMInventario = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoInventario.png")
        );

        ImageView imageViewModuloInventario = new ImageView(imageModuloMInventario);
        imageViewModuloInventario.setFitWidth(95);
        imageViewModuloInventario.setFitHeight(95);
        imageViewModuloInventario.setPreserveRatio(true);

        buttonModuloInventario.setGraphic(imageViewModuloInventario);
        buttonModuloInventario.setContentDisplay(ContentDisplay.TOP);
        buttonModuloInventario.setGraphicTextGap(15);
    }

    // ====== EVENTOS DE BOTONES ======

    @FXML
    private void clickModuloInventario(ActionEvent event) {
        System.out.println("Click en Módulo de Inventario (Bodeguero Coordinador)");
        mainController.saver("moduloInventario.fxml");
    }

    @FXML
    private void clikcRegresarMenuPrincipal(ActionEvent event) {
        System.out.println("Regresar al Menú Principal (Bodeguero Coordinador)");
        mainController.saver("menuSMEA.fxml");
    }

}
