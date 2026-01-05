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

public class moduloInventarioController
        implements Initializable, ControladorInyectable {

    // ================== CONTROLADOR PRINCIPAL ==================
    private MainController mainController;

    // ================== BOTONES ==================
    @FXML private Button buttonCatalogo;
    @FXML private Button buttonMovimientos;
    @FXML private Button buttonInventario;
    @FXML private Button buttonRegresar;

    // ================== INYECCIÓN ==================
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        configurarPermisos();
    }

    // ================== INIT ==================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarIconos();

    }

    private void cargarIconos() {
        // ICONO DEL BOTÓN DE CATALOGO
        Image imageCatalogo = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoCatalogo.png")
        );

        ImageView imageViewCatalogo = new ImageView(imageCatalogo);
        imageViewCatalogo.setFitWidth(150);
        imageViewCatalogo.setFitHeight(150);
        imageViewCatalogo.setPreserveRatio(true);

        buttonCatalogo.setGraphic(imageViewCatalogo);
        buttonCatalogo.setContentDisplay(ContentDisplay.TOP);
        buttonCatalogo.setGraphicTextGap(60);

        // ICONO DEL BOTÓN DE CATALOGO
        Image imageMovimiento = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoMovimiento.png")
        );

        ImageView imageViewMovimiento = new ImageView(imageMovimiento);
        imageViewMovimiento.setFitWidth(150);
        imageViewMovimiento.setFitHeight(150);
        imageViewMovimiento.setPreserveRatio(true);

        buttonMovimientos.setGraphic(imageViewMovimiento);
        buttonMovimientos.setContentDisplay(ContentDisplay.TOP);
        buttonMovimientos.setGraphicTextGap(60);

        // ICONO DEL BOTÓN DE INVENTARIO
        Image imageInventario = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoInventario.png")
        );

        ImageView imageViewInventario = new ImageView(imageInventario);
        imageViewInventario.setFitWidth(150);
        imageViewInventario.setFitHeight(150);
        imageViewInventario.setPreserveRatio(true);

        buttonInventario.setGraphic(imageViewInventario);
        buttonInventario.setContentDisplay(ContentDisplay.TOP);
        buttonInventario.setGraphicTextGap(60);
    }

    private void configurarPermisos() {
        String rol = mainController.getRolUsuario();
        if("Administrador".equalsIgnoreCase(rol)){
            buttonMovimientos.setDisable(false);
            buttonInventario.setDisable(false);

            bloquearBoton(buttonCatalogo);
        }

        if ("Bodeguero".equalsIgnoreCase(rol)) {
           buttonMovimientos.setDisable(false);
           buttonInventario.setDisable(false);

           bloquearBoton(buttonCatalogo);
        }
    }

    private void bloquearBoton(Button boton) {

        Tooltip tooltip = new Tooltip("Acceso restringido");
        tooltip.setShowDelay(javafx.util.Duration.millis(300));
        tooltip.setHideDelay(javafx.util.Duration.millis(0));

        boton.setTooltip(tooltip);
        boton.setOpacity(0.6); // efecto visual de bloqueado

        // Bloquear acción
        boton.addEventFilter(ActionEvent.ACTION, event -> event.consume());
    }

    // ================== EVENTOS ==================

    @FXML
    private void clickCatalogo(ActionEvent event) {
        System.out.println("Abrir Catálogo");
        mainController.saver("catalogoInventario.fxml");
    }

    @FXML
    private void clickMovimientos(ActionEvent event) {
        System.out.println("Abrir Movimientos de Inventario");
        mainController.saver("movimientosInventario.fxml");
    }

    @FXML
    private void clickInventario(ActionEvent event) {
        System.out.println("Abrir Inventario General");
        mainController.saver("inventarioGeneral.fxml");
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        switch (mainController.getRolUsuario()) {
            case "Administrador" ->
                    mainController.saver("menuAdministrador.fxml");

            case "Bodeguero Coordinador" ->
                    mainController.saver("menuBodegueroC.fxml");
            case "Bodeguero" ->
                    mainController.saver("menuBodeguero.fxml");
            case "Gerente General y Técnico" ->
                    mainController.saver("menuGerencia.fxml");
        }
    }
}
