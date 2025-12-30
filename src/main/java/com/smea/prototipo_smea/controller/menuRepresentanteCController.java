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

public class menuRepresentanteCController
        implements Initializable, ControladorInyectable {

    // ===== CONTROLLER CENTRAL =====
    private MainController mainController;

    @FXML private TextField labelNombre;
    @FXML private Button buttonModuloProveedor;
    @FXML private Button buttonActualizarDatos;
    @FXML private Button buttonActubuttonActualizarDatosalizarCredenciales;
    @FXML private Button buttonRegresarMenuPrincipal;

    // ===== INYECCIÓN =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ===== INITIALIZE =====
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Texto del representante
        labelNombre.setText("Nombre del representante de cobranzas");
        labelNombre.setEditable(false);

        // ICONO DEL BOTÓN PROVEEDOR
        Image imageProveedor = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoProveedor.png")
        );

        ImageView imageViewProveedor = new ImageView(imageProveedor);
        imageViewProveedor.setFitWidth(65);
        imageViewProveedor.setFitHeight(65);
        imageViewProveedor.setPreserveRatio(true);

        buttonModuloProveedor.setGraphic(imageViewProveedor);
        buttonModuloProveedor.setContentDisplay(ContentDisplay.TOP);
        buttonModuloProveedor.setGraphicTextGap(15);

        // ICONO DEL BOTÓN INFORMACIÓN PERSONAL
        Image imageInformacionPersonal = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoInformacionPersonal.png")
        );

        ImageView imageViewInformacionPersonal = new ImageView(imageInformacionPersonal);
        imageViewInformacionPersonal.setFitWidth(64);
        imageViewInformacionPersonal.setFitHeight(64);
        imageViewInformacionPersonal.setPreserveRatio(true);

        buttonActualizarDatos.setGraphic(imageViewInformacionPersonal);
        buttonActualizarDatos.setContentDisplay(ContentDisplay.TOP);
        buttonActualizarDatos.setGraphicTextGap(12);

        // ICONO DEL BOTÓN ACTUALIZAR CREDENCIALES
        Image imageActualizarCredenciales = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoActualizarCredenciales.png")
        );

        ImageView imageViewActualizarCredenciales = new ImageView(imageActualizarCredenciales);
        imageViewActualizarCredenciales.setFitWidth(50);
        imageViewActualizarCredenciales.setFitHeight(50);
        imageViewActualizarCredenciales.setPreserveRatio(true);

        buttonActubuttonActualizarDatosalizarCredenciales.setGraphic(imageViewActualizarCredenciales);
        buttonActubuttonActualizarDatosalizarCredenciales.setContentDisplay(ContentDisplay.TOP);
        buttonActubuttonActualizarDatosalizarCredenciales.setGraphicTextGap(12);
    }

    // ===== EVENTOS =====
    @FXML
    private void clickModuloProveedores(ActionEvent event) {
        mainController.saver("moduloProveedor.fxml");
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
