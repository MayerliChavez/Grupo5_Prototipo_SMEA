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

public class movimientoInventarioController
        implements Initializable, ControladorInyectable {

    // ================== CONTROLADOR PRINCIPAL ==================
    private MainController mainController;

    // ================== BOTONES ==================
    @FXML private Button buttonDevolucion;
    @FXML private Button buttonRegistrarEntrada;
    @FXML private Button buttonRegistrarOrdenSalida;
    @FXML private Button buttonHistorialMovimiento;
    @FXML private Button buttonConsultarOrdenSalida;
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

    // ================= PERMISOS ===================
    private void configurarPermisos() {
        String rol = mainController.getRolUsuario();

        if("Bodeguero Coordinador".equalsIgnoreCase(rol)){
            buttonDevolucion.setDisable(false);
            buttonRegistrarEntrada.setDisable(false);
            buttonHistorialMovimiento.setDisable(false);
            buttonConsultarOrdenSalida.setDisable(false);

            bloquearBoton(buttonRegistrarOrdenSalida);
        }

        if ("Bodeguero".equalsIgnoreCase(rol)) {
            buttonHistorialMovimiento.setDisable(false);
            buttonRegistrarOrdenSalida.setDisable(false);
            buttonConsultarOrdenSalida.setDisable(false);

            bloquearBoton(buttonDevolucion);
            bloquearBoton(buttonRegistrarEntrada);

        }

        if("Administrador".equalsIgnoreCase(rol)){
            buttonHistorialMovimiento.setDisable(false);
            buttonConsultarOrdenSalida.setDisable(false);

            bloquearBoton(buttonRegistrarOrdenSalida);
            bloquearBoton(buttonDevolucion);
            bloquearBoton(buttonRegistrarEntrada);
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

    // ================= ICONOS ======================

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
                "/Imagenes/iconoHistorialMovimiento.png"
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
        boton.setGraphicTextGap(25);
    }

    // ================== EVENTOS ==================

    @FXML
    private void clickDevolucion(ActionEvent event) {
        System.out.println("Abrir módulo Devolución");
        mainController.saver("devolucionInventario.fxml");
    }

    @FXML
    private void clickRegistrarEntrada(ActionEvent event) {
        System.out.println("Abrir Registrar Entrada");
        mainController.saver("registrarEntradaInventario.fxml");
    }

    @FXML
    private void clickRegistrarOrdenSalida(ActionEvent event) {
        System.out.println("Abrir Registrar Orden de Salida");
        mainController.saver("registrarOrdenSalida.fxml");
    }

    @FXML
    private void clickHistorialMovimientos(ActionEvent event) {
        System.out.println("Abrir Historial de Movimientos");
        mainController.saver("historialMovimientos.fxml");
    }

    @FXML
    private void clickConsultarOrdenSalida(ActionEvent event) {
        System.out.println("Abrir Consulta de Órdenes de Salida");
        mainController.saver("consultarOrdenSalida.fxml");
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("moduloInventario.fxml");
    }
}
