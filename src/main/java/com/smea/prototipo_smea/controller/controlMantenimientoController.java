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
    }

    // ===== INITIALIZE =====
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarIconos();
    }

    // ================= ICONOS =================
        private void cargarIconos() {

        configurarIcono(buttonRegistrarTiempos, "/Imagenes/iconoOrdenTrabajo.png");
        configurarIcono(buttonRegistrarFallas, "/Imagenes/iconoCalendario.png");
        configurarIcono(buttonRegistrarDiagnostico, "/Imagenes/iconoConsultarOrdenTrabajo.png");
        configurarIcono(buttonRegistrarRepuestosUtilizados, "/Imagenes/iconoModificarOrdenTrabajo.png");
        configurarIcono(buttonVerHistorial, "/Imagenes/iconoModificarOrdenTrabajo.png");
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

