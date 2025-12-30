package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class menuRepresentanteTController implements Initializable, ControladorInyectable {

    // Inyectado por MainController
    private MainController mainController;
    @FXML
    private Label textBienvenida;
    @FXML
    private TextField labelNombre;
    @FXML
    private Button buttonModuloReportes;
    @FXML
    private Button buttonActualizarDatos;
    @FXML
    private Button buttonActualizarCredenciales;
    @FXML
    private Button buttonRegresarMenuPrincipal;

    @Override
    public void setMainController(MainController main) {
        this.mainController = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configuramos el nombre por defecto (puedes cambiarlo según la lógica de tu login)
        labelNombre.setText("Representante de Tesorería");
        labelNombre.setEditable(false);
    }

    @FXML
    private void clickAbrirModuloReportes(ActionEvent event) {
        // Asegúrate de tener este fxml creado
        mainController.saver("moduloReportes.fxml");
    }

    @FXML
    private void clickActualizarDatos(ActionEvent event) {
        mainController.saver("actualizarDatos.fxml");
    }

    @FXML
    private void clickActualizarCredenciales(ActionEvent event) {
        mainController.saver("actualizarCredenciales.fxml");
    }

    @FXML
    private void clikcRegresarMenuPrincipal(ActionEvent event) {
        // Regresa al login o menú principal
        mainController.saver("menuSMEA.fxml");
    }
}