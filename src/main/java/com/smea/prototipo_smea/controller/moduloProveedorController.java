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

public class moduloProveedorController
        implements Initializable, ControladorInyectable {

    private MainController mainController;

    // ================== BOTONES ==================
    @FXML private Button buttonRegistrarOrdenPago;
    @FXML private Button buttonVisualizarProveedores;
    @FXML private Button buttonConsultarProductoProveedor;
    @FXML private Button buttonConsultarDatosProveedor;
    @FXML private Button buttonConsultarOrdenPago;
    @FXML private Button buttonVisualizarHistorialPagos;
    @FXML private Button buttonRegistrarProducto;
    @FXML private Button buttonActualizarDatosProveedor;
    @FXML private Button buttonRegistrarProveedor;
    @FXML private Button buttonRegresar;

    // ================== INIT ==================
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarIconos();
    }

    private void cargarIconos() {
        // ICONO DEL BOTÓN REGISTRAR ORDEN DE PAGO
        Image imageOrdenPago = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoRegistrarOrdenPago.png")
        );

        ImageView imageViewOrdenPago = new ImageView(imageOrdenPago);
        imageViewOrdenPago.setFitWidth(65);
        imageViewOrdenPago.setFitHeight(65);
        imageViewOrdenPago.setPreserveRatio(true);

        buttonRegistrarOrdenPago.setGraphic(imageViewOrdenPago);
        buttonRegistrarOrdenPago.setContentDisplay(ContentDisplay.TOP);
        buttonRegistrarOrdenPago.setGraphicTextGap(20);

        // ICONO DEL BOTÓN LISTA DE PROVEEDORES
        Image imageListaProveedores = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoListaProveedores.png")
        );

        ImageView imageViewListaProveedores = new ImageView(imageListaProveedores);
        imageViewListaProveedores.setFitWidth(65);
        imageViewListaProveedores.setFitHeight(65);
        imageViewListaProveedores.setPreserveRatio(true);

        buttonVisualizarProveedores.setGraphic(imageViewListaProveedores);
        buttonVisualizarProveedores.setContentDisplay(ContentDisplay.TOP);
        buttonVisualizarProveedores.setGraphicTextGap(20);

        // ICONO DEL BOTÓN DE CONSULTAR PRODUCTO
        Image imageConsultarProducto = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoConsultarProducto.png")
        );

        ImageView imageViewConsultarProducto = new ImageView(imageConsultarProducto);
        imageViewConsultarProducto.setFitWidth(65);
        imageViewConsultarProducto.setFitHeight(65);
        imageViewConsultarProducto.setPreserveRatio(true);

        buttonConsultarProductoProveedor.setGraphic(imageViewConsultarProducto);
        buttonConsultarProductoProveedor.setContentDisplay(ContentDisplay.TOP);
        buttonConsultarProductoProveedor.setGraphicTextGap(18);

        // ICONO DEL BOTÓN DE CONSULTAR PROVEEDOR
        Image imageConsultarProveedor = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoConsultarProveedor.png")
        );

        ImageView imageViewConsultarProveedor = new ImageView(imageConsultarProveedor);
        imageViewConsultarProveedor.setFitWidth(65);
        imageViewConsultarProveedor.setFitHeight(65);
        imageViewConsultarProveedor.setPreserveRatio(true);

        buttonConsultarDatosProveedor.setGraphic(imageViewConsultarProveedor);
        buttonConsultarDatosProveedor.setContentDisplay(ContentDisplay.TOP);
        buttonConsultarDatosProveedor.setGraphicTextGap(20);

        // ICONO DEL BOTÓN DE CONSULTAR ORDEN DE PAGO
        Image imageConsultarOrdenPago = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoConsultarOrdenPago.png")
        );

        ImageView imageViewConsultarOrdenPago = new ImageView(imageConsultarOrdenPago);
        imageViewConsultarOrdenPago.setFitWidth(65);
        imageViewConsultarOrdenPago.setFitHeight(65);
        imageViewConsultarOrdenPago.setPreserveRatio(true);

        buttonConsultarOrdenPago.setGraphic(imageViewConsultarOrdenPago);
        buttonConsultarOrdenPago.setContentDisplay(ContentDisplay.TOP);
        buttonConsultarOrdenPago.setGraphicTextGap(20);

        // ICONO DEL BOTÓN DE HISTORIAL DE PAGOS
        Image imageHistorialPagos = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoHistorialPagos.png")
        );

        ImageView imageViewHistorialPago = new ImageView(imageHistorialPagos);
        imageViewHistorialPago.setFitWidth(65);
        imageViewHistorialPago.setFitHeight(65);
        imageViewHistorialPago.setPreserveRatio(true);

        buttonVisualizarHistorialPagos.setGraphic(imageViewHistorialPago);
        buttonVisualizarHistorialPagos.setContentDisplay(ContentDisplay.TOP);
        buttonVisualizarHistorialPagos.setGraphicTextGap(20);

        // ICONO DEL BOTÓN DE REGISTRAR PRODUCTO
        Image imageRegistrarProducto = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoRegistrarProducto.png")
        );

        ImageView imageViewRegistraProducto = new ImageView(imageRegistrarProducto);
        imageViewRegistraProducto.setFitWidth(65);
        imageViewRegistraProducto.setFitHeight(65);
        imageViewRegistraProducto.setPreserveRatio(true);

        buttonRegistrarProducto.setGraphic(imageViewRegistraProducto);
        buttonRegistrarProducto.setContentDisplay(ContentDisplay.TOP);
        buttonRegistrarProducto.setGraphicTextGap(20);

        // ICONO DEL BOTÓN DE REGISTRAR PROVEEDOR
        Image imageActualizarProveedor = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoActualizarCredenciales.png")
        );

        ImageView imageViewActualizarProveedor = new ImageView(imageActualizarProveedor);
        imageViewActualizarProveedor.setFitWidth(65);
        imageViewActualizarProveedor.setFitHeight(65);
        imageViewActualizarProveedor.setPreserveRatio(true);

        buttonActualizarDatosProveedor.setGraphic(imageViewActualizarProveedor);
        buttonActualizarDatosProveedor.setContentDisplay(ContentDisplay.TOP);
        buttonActualizarDatosProveedor.setGraphicTextGap(20);

        // ICONO DEL BOTÓN DE REGISTRAR PROVEEDOR
        Image imageRegistrarProveedor = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoActualizarProveedor.png")
        );

        ImageView imageViewRegistrarProveedor = new ImageView(imageRegistrarProveedor);
        imageViewRegistrarProveedor.setFitWidth(65);
        imageViewRegistrarProveedor.setFitHeight(65);
        imageViewRegistrarProveedor.setPreserveRatio(true);

        buttonRegistrarProveedor.setGraphic(imageViewRegistrarProveedor);
        buttonRegistrarProveedor.setContentDisplay(ContentDisplay.TOP);
        buttonRegistrarProveedor.setGraphicTextGap(20);
    }


    // ================== INYECCIÓN ==================
    @Override
    public void setMainController(MainController main) {
        this.mainController = main;
        configurarPermisos();
    }

    // ================== PERMISOS ==================
    private void configurarPermisos() {
        String rol = mainController.getRolUsuario();

        if ("Representante de Cobranzas".equalsIgnoreCase(rol)) {

            buttonRegistrarOrdenPago.setDisable(false);
            buttonConsultarDatosProveedor.setDisable(false);
            buttonConsultarProductoProveedor.setDisable(false);
            buttonConsultarOrdenPago.setDisable(false);
            buttonVisualizarHistorialPagos.setDisable(false);
            buttonVisualizarProveedores.setDisable(false);

            bloquearBoton(buttonRegistrarProveedor);
            bloquearBoton(buttonRegistrarProducto);
            bloquearBoton(buttonConsultarOrdenPago);
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

    // ================== ACCIONES ==================
    @FXML
    private void clickRegistrarProveedor(ActionEvent event) {
        mainController.saver("registrarProveedor.fxml");
    }

    @FXML
    private void clickRegistrarOrdenPago(ActionEvent event) {
        mainController.saver("registrarOrdenPago.fxml");
    }

    @FXML
    private void clickRegistrarProducto(ActionEvent event) {
        mainController.saver("registrarProductoQueOfreceProveedor.fxml");
    }

    @FXML
    private void clickActualizarDatosProveedor(ActionEvent event) {
        mainController.saver("actualizarDatosProveedor.fxml");
    }

    @FXML
    private void clickConsultarDatosProveedor(ActionEvent event) {
        mainController.saver("consultarDatosProveedor.fxml");
    }

    @FXML
    private void clickConsultarProductoProveedor(ActionEvent event) {
        mainController.saver("consultarProductoProveedor.fxml");
    }

    @FXML
    private void clickConsultarOrdenPago(ActionEvent event) {
        mainController.saver("consultarDatosOrdenPago.fxml");
    }

    @FXML
    private void clickVisualizarHistorialPagos(ActionEvent event) {
        mainController.saver("visualizarHistorialDePagos.fxml");
    }

    @FXML
    private void clickVisualizarProveedores(ActionEvent event) {
        mainController.saver("visualizarListaProveedores.fxml");
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        switch (mainController.getRolUsuario()) {
            case "Administrador" ->
                    mainController.saver("menuAdministrador.fxml");

            case "Representante de Cobranzas" ->
                    mainController.saver("menuRepresentanteC.fxml");
            case "Gerente General y Técnico" ->
                    mainController.saver("menuGerencia.fxml");
        }
    }
}
