package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class menuAdministradorController implements Initializable, ControladorInyectable {

    // ====== CONTROLADOR CENTRAL ======
    private MainController mainController;

    // ====== COMPONENTES FXML ======
    @FXML private TextField labelNombre;

    @FXML private Button buttonmoduloreporte;
    @FXML private Button buttonModuloAdministracionSistema;
    @FXML private Button buttonRegresarMenuPrincipal;
    @FXML private Button buttonActualizarDatos;
    @FXML private Button buttonActualizarCredenciales;
    @FXML private Button buttonModuloProveedor;
    @FXML private Button buttonModuloInventario;
    @FXML private Button buttonModuloCliente;

    // ====== INITIALIZE ======
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelNombre.setText("Nombre del administrador");
        labelNombre.setEditable(false);
        cargarIconos();
    }

    private void cargarIconos() {
        // ICONO DEL BOTÓN REPORTES
        Image imageReporte = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoReportes.png")
        );

        ImageView imageViewReporte = new ImageView(imageReporte);
        imageViewReporte.setFitWidth(50);
        imageViewReporte.setFitHeight(50);
        imageViewReporte.setPreserveRatio(true);

        buttonmoduloreporte.setGraphic(imageViewReporte);
        buttonmoduloreporte.setContentDisplay(ContentDisplay.TOP);
        buttonmoduloreporte.setGraphicTextGap(15);

        // ICONO DEL BOTÓN INVENTARIO
        Image imageInventario = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoInventario.png")
        );

        ImageView imageViewInventario = new ImageView(imageInventario);
        imageViewInventario.setFitWidth(50);
        imageViewInventario.setFitHeight(50);
        imageViewInventario.setPreserveRatio(true);

        buttonModuloInventario.setGraphic(imageViewInventario);
        buttonModuloInventario.setContentDisplay(ContentDisplay.TOP);
        buttonModuloInventario.setGraphicTextGap(15);

        // ICONO DEL BOTÓN CLIENTE
        Image imageCliente = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoCliente.png")
        );

        ImageView imageViewCliente = new ImageView(imageCliente);
        imageViewCliente.setFitWidth(60);
        imageViewCliente.setFitHeight(60);
        imageViewCliente.setPreserveRatio(true);

        buttonModuloCliente.setGraphic(imageViewCliente);
        buttonModuloCliente.setContentDisplay(ContentDisplay.TOP);
        buttonModuloCliente.setGraphicTextGap(15);

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

        // ICONO DEL BOTÓN ADMINISTRADOR DEL SISTEMA
        Image imageAdministradorSistema = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoAdministradorSistema.png")
        );

        ImageView imageViewAdministradorSistema = new ImageView(imageAdministradorSistema);
        imageViewAdministradorSistema.setFitWidth(64);
        imageViewAdministradorSistema.setFitHeight(64);
        imageViewAdministradorSistema.setPreserveRatio(true);

        buttonModuloAdministracionSistema.setGraphic(imageViewAdministradorSistema);
        buttonModuloAdministracionSistema.setContentDisplay(ContentDisplay.TOP);
        buttonModuloAdministracionSistema.setGraphicTextGap(12);

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

        buttonActualizarCredenciales.setGraphic(imageViewActualizarCredenciales);
        buttonActualizarCredenciales.setContentDisplay(ContentDisplay.TOP);
        buttonActualizarCredenciales.setGraphicTextGap(15);
    }

    // ====== INYECCIÓN ======
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ====== NAVEGACIÓN ======

    @FXML
    private void clikcRegresarMenuPrincipal(ActionEvent event) {
        mainController.saver("menuSMEA.fxml");
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
    private void clickIngresarModuloReportes(ActionEvent event) {
        mainController.saver("moduloReportes.fxml");
    }

    @FXML
    private void clickIngresarModuloAdministracionSistema(ActionEvent event) {
        mainController.saver("moduloAdministracionSistema.fxml");
    }

    @FXML
    private void clickActualizarInformacionPersonal(ActionEvent event) {
        mainController.saver("actualizarInformacion.fxml");
    }

    @FXML
    private void clcikActualizarCredenciales(ActionEvent event) {

  /*      String contraseniaActual = mainController.getPasswordUsuario();

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Verificación de seguridad");
        dialog.setHeaderText("Ingrese su contraseña actual");

        ButtonType confirmarButton = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmarButton, ButtonType.CANCEL);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Contraseña");

        dialog.getDialogPane().setContent(passwordField);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmarButton) {
                return passwordField.getText();
            }
            return null;
        });

        contraseniaActual = "12345678";

        String finalContraseniaActual = contraseniaActual;
        dialog.showAndWait().ifPresent(ingreso -> {

            if (ingreso.equals(finalContraseniaActual)) {
                mainController.saver("actualizarCredenciales.fxml");
            } else {
                mostrarAlerta(
                        "Contraseña incorrecta",
                        "La contraseña ingresada no es válida",
                        Alert.AlertType.ERROR
                );
            }
        });*/
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }



}
