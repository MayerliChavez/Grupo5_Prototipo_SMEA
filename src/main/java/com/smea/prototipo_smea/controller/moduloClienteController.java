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

public class moduloClienteController
        implements Initializable, ControladorInyectable {

    // ================== CONTROLADOR PRINCIPAL ==================
    private MainController mainController;

    // ================== BOTONES ==================
    @FXML private Button buttonRegistrarCliente;
    @FXML private Button buttonRegistrarEquipoCliente;
    @FXML private Button buttonConsultarCliente;
    @FXML private Button buttonConsultarEquiposCliente;
    @FXML private Button buttonConsultarCitaProgramadas;
    @FXML private Button buttonActualizarDatosCliente;
    @FXML private Button buttonProgramarCitasTecnicas;
    @FXML private Button buttonModificarCitasTecnicas;
    @FXML private Button buttonHistorialServicios;
    @FXML private Button buttonFiltrarHistorialServicios;
    @FXML private Button buttonRegresar;

    // ================== INYECCIÓN ==================
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ================== INIT ==================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Aquí puedes inicializar datos si luego lo necesitas
        cargarIconos();
    }

    private void cargarIconos() {
        // ICONO DEL BOTÓN DE REGISTRAR CLIENTE
        Image imageRegistrarCliente = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoAc.png")
        );

        ImageView imageViewRegistrarCliente = new ImageView(imageRegistrarCliente);
        imageViewRegistrarCliente.setFitWidth(65);
        imageViewRegistrarCliente.setFitHeight(65);
        imageViewRegistrarCliente.setPreserveRatio(true);

        buttonRegistrarCliente.setGraphic(imageViewRegistrarCliente);
        buttonRegistrarCliente.setContentDisplay(ContentDisplay.TOP);
        buttonRegistrarCliente.setGraphicTextGap(20);

    }

    // ================== PERMISOS ==================
    private void configurarPermisos() {
        deshabilitarTodos();

        String rol = mainController.getRolUsuario();

        if ("Administrador".equalsIgnoreCase(rol)) {
            habilitarTodos();
            return;
        }

        if ("Técnico Coordinador".equalsIgnoreCase(rol)) {
            buttonConsultarEquiposCliente.setDisable(false);
            buttonConsultarCliente.setDisable(false);
            buttonFiltrarHistorialServicios.setDisable(false);
            buttonProgramarCitasTecnicas.setDisable(false);
            buttonModificarCitasTecnicas.setDisable(false);
            buttonConsultarCitaProgramadas.setDisable(false);
            buttonHistorialServicios.setDisable(false);

            Tooltip sinPermiso = new Tooltip("No tiene permisos para esta acción");

            buttonRegistrarCliente.setTooltip(sinPermiso);
            buttonRegistrarEquipoCliente.setTooltip(sinPermiso);
            buttonActualizarDatosCliente.setTooltip(sinPermiso);
        }
    }

    private void deshabilitarTodos() {
        buttonRegistrarCliente.setDisable(true);
        buttonRegistrarEquipoCliente.setDisable(true);
        buttonConsultarCliente.setDisable(true);
        buttonConsultarEquiposCliente.setDisable(true);
        buttonProgramarCitasTecnicas.setDisable(true);
        buttonModificarCitasTecnicas.setDisable(true);
        buttonFiltrarHistorialServicios.setDisable(true);
        buttonHistorialServicios.setDisable(true);
        buttonConsultarCitaProgramadas.setDisable(true);
        buttonActualizarDatosCliente.setDisable(true);
    }

    private void habilitarTodos() {
        buttonRegistrarCliente.setDisable(false);
        buttonRegistrarEquipoCliente.setDisable(false);
        buttonConsultarCliente.setDisable(false);
        buttonConsultarEquiposCliente.setDisable(false);
        buttonProgramarCitasTecnicas.setDisable(false);
        buttonModificarCitasTecnicas.setDisable(false);
        buttonFiltrarHistorialServicios.setDisable(false);
        buttonHistorialServicios.setDisable(false);
        buttonConsultarCitaProgramadas.setDisable(false);
        buttonActualizarDatosCliente.setDisable(false);
    }

    // ================== EVENTOS ==================
    @FXML
    private void clickRegistrarCliente(ActionEvent event) {
        System.out.println("Registrar Cliente");
        mainController.saver("registrarCliente.fxml");
    }

    @FXML
    private void clickRegistrarEquipoCliente(ActionEvent event) {
        System.out.println("Registrar Equipo del Cliente");
        mainController.saver("registrarEquipoCliente.fxml");
    }

    @FXML
    private void clickConsultarCliente(ActionEvent event) {
        System.out.println("Consultar Cliente");
        mainController.saver("consultarCliente.fxml");
    }

    @FXML
    private void clickConsultarEquiposCliente(ActionEvent event) {
        System.out.println("Consultar Equipos del Cliente");
        mainController.saver("consultarEquiposCliente.fxml");
    }

    @FXML
    private void clickConsultarCitaProgramadas(ActionEvent event) {
        System.out.println("Consultar Citas Programadas");
        mainController.saver("consultarCitasProgramadas.fxml");
    }

    @FXML
    private void clickActualizarDatosCliente(ActionEvent event) {
        System.out.println("Actualizar Datos Cliente");
        mainController.saver("actualizarDatosCliente.fxml");
    }

    @FXML
    private void clickProgramarCitasTecnicas(ActionEvent event) {
        System.out.println("Programar Cita Técnica");
        mainController.saver("programarCitaTecnica.fxml");
    }

    @FXML
    private void clicModificarCitasTecnicas(ActionEvent event) {
        System.out.println("Modificar Cita Técnica");
        mainController.saver("modificarCitaTecnica.fxml");
    }

    @FXML
    private void clickHistorialServicios(ActionEvent event) {
        System.out.println("Historial de Servicios");
        mainController.saver("historialServicios.fxml");
    }

    @FXML
    private void clickFiltrarHistorialServicios(ActionEvent event) {
        System.out.println("Filtrar Historial de Servicios");
        mainController.saver("filtrarHistorialServicios.fxml");
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        switch (mainController.getRolUsuario()) {
            case "Administrador" ->
                    mainController.saver("menuAdministrador.fxml");

            case "Técnico Coordinador" ->
                    mainController.saver("menuTecnicoC.fxml");
        }
    }
}
