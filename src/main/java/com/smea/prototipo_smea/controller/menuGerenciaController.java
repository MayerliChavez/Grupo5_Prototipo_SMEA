package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class menuGerenciaController
        implements Initializable, ControladorInyectable {

    // Inyectado desde MainController
    private MainController mainController;

    @FXML
    private TextField labelNombre;
    @FXML
    private Button buttonmoduloreporte;
    @FXML
    private Button buttonModuloAuditoria;
    @FXML
    private Button buttonActualizarDatos;
    @FXML
    private Button buttonActualizarCredenciales;
    @FXML
    private Button buttonSalir;

    @Override
    public void setMainController(MainController main) {
        this.mainController = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelNombre.setText("Gerente General");
        labelNombre.setEditable(false);
    }

    @FXML
    private void clickabrirModuloReportes(ActionEvent event) {
        mainController.saver("moduloReportes.fxml");
    }

    @FXML
    private void clickabrirModuloAuditoria(ActionEvent event) {
        mainController.saver("moduloAuditoria.fxml");
    }

    @FXML
    private void clickactualizarDatos(ActionEvent event) {
        mainController.saver("actualizarDatos.fxml");
    }

    @FXML
    private void clickactualizarCredenciales(ActionEvent event) {
        mainController.saver("actualizarCredenciales.fxml");
    }

    @FXML
    private void clikcRegresarMenuPrincipal(ActionEvent event) {mainController.saver("menuSMEA.fxml");}
}
