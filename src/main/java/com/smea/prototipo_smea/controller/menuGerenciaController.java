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

public class menuGerenciaController implements Initializable, ControladorInyectable {

    // ===================== MAIN CONTROLLER =====================
    private MainController mainController;

    // ===================== DATOS USUARIO =====================
    @FXML
    private TextField labelNombre;

    // ===================== BOTONES =====================
    @FXML
    private Button buttonmoduloreporte1;

    @FXML private Button buttonmoduloreporte;
    @FXML private Button buttonModuloAdministracionSistema;
    @FXML private Button buttonMantenimiento;
    @FXML private Button buttonModuloProveedor;
    @FXML private Button buttonModuloInventario;
    @FXML private Button buttonModuloCliente;
    @FXML private Button buttonModuloAuditoria;

    // ===================== INYECCIÓN =====================
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ===================== INITIALIZE =====================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarDatosUsuario();
        cargarIconos();
    }

    // ===================== CARGAR DATOS =====================
    private void cargarDatosUsuario() {
        // 🔹 Simulación (luego vendrá del login)
        labelNombre.setText("Gerente General");
        labelNombre.setEditable(false);
    }

    // ================= ICONOS ======================

    private void cargarIconos() {

        configurarIcono(
                buttonMantenimiento,
                "/Imagenes/iconoMantenimiento.png"
        );
        configurarIcono(
                buttonModuloAuditoria,
                "/Imagenes/iconoModuloAuditoria.png"
        );
        configurarIcono(
                buttonmoduloreporte,
                "/Imagenes/iconoReportes.png"
        );
        configurarIcono(
                buttonModuloCliente,
                "/Imagenes/iconoCliente.png"
        );
        configurarIcono(
                buttonModuloProveedor,
                "/Imagenes/iconoProveedor.png"
        );
        configurarIcono(
                buttonModuloAdministracionSistema,
                "/Imagenes/iconoAdministradorSistema.png"
        );
        configurarIcono(
                buttonModuloInventario,
                "/Imagenes/iconoInventario.png"
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


    // ===================== NAVEGACIÓN =====================
    @FXML
    private void clickIngresarModuloReportes(ActionEvent event) {
        mainController.saver("moduloReportes.fxml");
    }

    @FXML
    private void clickIngresarModuloAdministracionSistema(ActionEvent event) {
        mainController.saver("moduloAdministracionSistema.fxml");
    }

    @FXML
    private void clcikModuloMantenimiento(ActionEvent event) {
        mainController.saver("moduloMantenimiento.fxml");
    }

    @FXML
    private void clickIngresarModuloProveedores(ActionEvent event) {
        mainController.saver("moduloProveedor.fxml");
    }

    @FXML
    private void clickIngresarModuloInventario(ActionEvent event) {
        mainController.saver("moduloInventario.fxml");
    }

    @FXML
    private void clickIngresarModuloClientes(ActionEvent event) {
        mainController.saver("moduloCliente.fxml");
    }

    @FXML
    private void clickIngresarModuloAuditoria(ActionEvent event) {
        mainController.saver("moduloAuditoria.fxml");
    }


    @FXML
    private void clikcRegresarMenuPrincipal(ActionEvent event) {
        mainController.saver("menuSMEA.fxml");
    }
}
