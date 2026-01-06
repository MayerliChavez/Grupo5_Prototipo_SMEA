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

public class moduloMantenimientoController implements Initializable, ControladorInyectable {

    // ================== CONTROLADOR PRINCIPAL ==================
    private MainController mainController;

    // ================== FXML ==================
    @FXML private Button buttonPlanificacion;
    @FXML private Button buttonControl;
    @FXML private Button buttonRegresar;

    // ================== INYECCIÓN ==================
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ================== INITIALIZE ==================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarIconos();
    }

    // ================== ICONOS ==================
    private void cargarIconos() {

        configurarIcono(
                buttonPlanificacion,
                "/Imagenes/iconoPlanificacion.png"
        );
        configurarIcono(
                buttonControl,
                "/Imagenes/iconoControl.png"
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


    // ================== ACCIONES ==================
    @FXML
    private void clickPlanificacion(ActionEvent event) {
        // Pantalla de planificación de mantenimientos
        mainController.saver("planificacionMantenimiento.fxml");
    }

    @FXML
    private void clickControl(ActionEvent event) {
        mainController.saver("controlMantenimiento.fxml");
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        switch (mainController.getRolUsuario()) {
            case "Administrador" ->
                    mainController.saver("menuAdministrador.fxml");
            case "Gerente General y Técnico" ->
                    mainController.saver("menuGerencia.fxml");
        }
    }
}
