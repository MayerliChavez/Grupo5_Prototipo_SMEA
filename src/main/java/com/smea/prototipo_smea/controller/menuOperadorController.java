package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class menuOperadorController implements ControladorInyectable {

    // ====== REFERENCIA AL CONTROLADOR CENTRAL ======
    private MainController mainController;

    // ====== COMPONENTES FXML ======
    @FXML
    private TextField labelNombre;

    // ====== INYECCIÓN DEL CONTROLADOR CENTRAL ======
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        initialize(null, null);
    }

    // ====== EVENTOS DE BOTONES ======

    @FXML
    private void clickModuloMantenimiento(ActionEvent event) {
        System.out.println("Click en Módulo de Mantenimiento (Operador)");
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
        System.out.println("Regresar al Menú Principal (Operador)");
        mainController.saver("menuSMEA.fxml");
    }

    public void initialize(URL url, ResourceBundle rb) {
        // Configuramos el nombre por defecto (puedes cambiarlo según la lógica de tu login)
        labelNombre.setText("Nombre del operador");
        labelNombre.setEditable(false);
    }
}
