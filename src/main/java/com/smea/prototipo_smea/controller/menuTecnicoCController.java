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

public class menuTecnicoCController implements Initializable, ControladorInyectable  {

    // ====== REFERENCIA AL CONTROLADOR CENTRAL ======
    private MainController mainController;

    // ====== COMPONENTES FXML ======
    @FXML
    private TextField labelNombre;
    @FXML private Button buttonModuloClientes;
    @FXML private Button buttonActualizarDatos;
    @FXML private Button buttonRegresarMenuPrincipal;


    // ====== INYECCIÓN DEL CONTROLADOR CENTRAL ======
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configuramos el nombre por defecto (puedes cambiarlo según la lógica de tu login)
        labelNombre.setText("Nombre del Tecnico");
        labelNombre.setEditable(false);
        cargarIconos();
    }

    private void cargarIconos() {

        // ICONO DEL BOTÓN CLIENTE
        Image imageCliente = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoCliente.png")
        );

        ImageView imageViewCliente = new ImageView(imageCliente);
        imageViewCliente.setFitWidth(60);
        imageViewCliente.setFitHeight(60);
        imageViewCliente.setPreserveRatio(true);

        buttonModuloClientes.setGraphic(imageViewCliente);
        buttonModuloClientes.setContentDisplay(ContentDisplay.TOP);
        buttonModuloClientes.setGraphicTextGap(15);

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
    }

    // ====== EVENTOS DE BOTONES ======

    @FXML
    private void clickModuloClientes(ActionEvent event) {
        System.out.println("Click en Módulo de Clientes (Técnico Coordinador)");
        mainController.saver("moduloCliente.fxml");
    }

    @FXML
    private void clickModuloMantenimiento(ActionEvent event) {
        System.out.println("Click en Módulo de Mantenimiento (Técnico Coordinador)");
        mainController.saver("moduloMantenimiento.fxml");
    }

    @FXML
    private void clickActualizarDatos(ActionEvent event) {
        System.out.println("Click en Actualizar Información Personal");
        mainController.saver("actualizarInformacion.fxml");
    }

    @FXML
    private void clickActualizarCredenciales(ActionEvent event) {
        System.out.println("Click en Actualizar Credenciales");
        mainController.saver("actualizarCredenciales.fxml");
    }

    @FXML
    private void clikcRegresarMenuPrincipal(ActionEvent event) {
        System.out.println("Regresar al Menú Principal (Técnico Coordinador)");
        mainController.saver("menuSMEA.fxml");
    }
}

