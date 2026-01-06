package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.OrdenPago;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class controlMantenimientoController implements Initializable, ControladorInyectable {

    // ===== CONTROLADOR CENTRAL =====
    private MainController mainController;

    // ===== FXML =====
    @FXML private Label labelBienvenida;

    @FXML private Button buttonRegistrarTiempos;
    @FXML private Button buttonRegistrarFallas;
    @FXML private Button buttonRegistrarDiagnostico;
    @FXML private Button buttonRegistrarRepuestosUtilizados;
    @FXML private Button buttonVerHistorial;

    // ===== INYECCIÓN =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        configurarPermisos();
    }

    // ===== INITIALIZE =====
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarIconos();
    }

    // ================= ICONOS =================
        private void cargarIconos() {

        configurarIcono(buttonRegistrarTiempos, "/Imagenes/iconoRegistrarTiempo.png");
        configurarIcono(buttonRegistrarFallas, "/Imagenes/iconoRegistrarFallas.png");
        configurarIcono(buttonRegistrarDiagnostico, "/Imagenes/iconoDiagnostico.png");
        configurarIcono(buttonRegistrarRepuestosUtilizados, "/Imagenes/iconoHerramientasR.png");
        configurarIcono(buttonVerHistorial, "/Imagenes/iconohistorial.png");
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

    private void configurarPermisos() {
        String rol = mainController.getRolUsuario();

        if ("Operador".equalsIgnoreCase(rol)) {
            buttonVerHistorial.setDisable(false);

            bloquearBoton(buttonRegistrarDiagnostico);
            bloquearBoton(buttonRegistrarRepuestosUtilizados);
            bloquearBoton(buttonRegistrarFallas);
            bloquearBoton(buttonRegistrarTiempos);
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

    // ================= EVENTOS =================

    @FXML
    private void clickRegistrarTiempos(ActionEvent event) {mainController.saver("registrarTiempos.fxml");}

    @FXML
    private void clickRegistrarFallas(ActionEvent event) {
        mainController.saver("registrarFallas.fxml");
    }

    @FXML
    private void clickRegistrarDiagnostico(ActionEvent event) {mainController.saver("registrarDiagnostico.fxml");}

    @FXML
    private void clickRegistrarRepuestosUtilizados(ActionEvent event) {mainController.saver("registrarRepuestoUtilizados.fxml");}

    @FXML
    private void clickVerHistorial(ActionEvent event) { mainController.saver("verHistorial.fxml");}

    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("moduloMantenimiento.fxml");
    }
}

