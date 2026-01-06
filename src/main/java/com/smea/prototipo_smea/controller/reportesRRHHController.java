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

public class reportesRRHHController implements Initializable, ControladorInyectable{

    // ===== CONTROLADOR CENTRAL =====
    private MainController mainController;

    // ===== FXML =====
    @FXML private Label labelBienvenida;

    @FXML private Button buttonReporteTiempoEmpleados;
    @FXML private Button buttonRolPagosEmpleados;
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

        configurarIcono(buttonReporteTiempoEmpleados, "/Imagenes/iconoOrdenTrabajo.png");
        configurarIcono(buttonRolPagosEmpleados, "/Imagenes/iconoCalendario.png");
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
    private void clickReporteTiempoEmpleados(ActionEvent event) {
        mainController.saver("ReporteTiempoEmpleados.fxml");
    }

    @FXML
    private void clickRolPagosEmpleados(ActionEvent event) {
        mainController.saver("RolPagosEmpleados.fxml");
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("moduloReportes.fxml");
    }
}