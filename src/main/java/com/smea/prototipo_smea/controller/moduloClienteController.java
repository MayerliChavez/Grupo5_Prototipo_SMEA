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
    @FXML private Button buttonHistorialServicios;
    @FXML private Button buttonFiltrarHistorialServicios;
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

    private void cargarIconos() {
        // ICONO DEL BOTÓN DE CONSULTAR CLIENTE
        Image imageConsultarCliente = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoConsultarCliente.png")
        );

        ImageView imageViewConsultarCliente = new ImageView(imageConsultarCliente);
        imageViewConsultarCliente.setFitWidth(68);
        imageViewConsultarCliente.setFitHeight(68);
        imageViewConsultarCliente.setPreserveRatio(true);

        buttonConsultarCliente.setGraphic(imageViewConsultarCliente);
        buttonConsultarCliente.setContentDisplay(ContentDisplay.TOP);
        buttonConsultarCliente.setGraphicTextGap(25);

        // ICONO DEL BOTÓN DE REGISTRAR CLIENTE
        Image imageRegistrarCliente = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoRegistrarCliente.png")
        );

        ImageView imageViewRegistrarCliente = new ImageView(imageRegistrarCliente);
        imageViewRegistrarCliente.setFitWidth(68);
        imageViewRegistrarCliente.setFitHeight(68);
        imageViewRegistrarCliente.setPreserveRatio(true);

        buttonRegistrarCliente.setGraphic(imageViewRegistrarCliente);
        buttonRegistrarCliente.setContentDisplay(ContentDisplay.TOP);
        buttonRegistrarCliente.setGraphicTextGap(25);

        // ICONO DEL BOTÓN DE  REGISTRAR EQUIPO DEL CLIENTE
        Image imageRegistrarEquipoCliente = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoRegistrarEquipoCliente.png")
        );

        ImageView imageViewRegistrarEquipoCliente = new ImageView(imageRegistrarEquipoCliente);
        imageViewRegistrarEquipoCliente.setFitWidth(68);
        imageViewRegistrarEquipoCliente.setFitHeight(68);
        imageViewRegistrarEquipoCliente.setPreserveRatio(true);

        buttonRegistrarEquipoCliente.setGraphic(imageViewRegistrarEquipoCliente);
        buttonRegistrarEquipoCliente.setContentDisplay(ContentDisplay.TOP);
        buttonRegistrarEquipoCliente.setGraphicTextGap(25);

        // ICONO DEL BOTÓN DE  CONSULTAR EQUIPO DEL CLIENTE
        Image imageConsultarEquipoCliente = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoConsultarEquipoCliente.png")
        );

        ImageView imageViewConsultarEquipoCliente = new ImageView(imageConsultarEquipoCliente);
        imageViewConsultarEquipoCliente.setFitWidth(68);
        imageViewConsultarEquipoCliente.setFitHeight(68);
        imageViewConsultarEquipoCliente.setPreserveRatio(true);

        buttonConsultarEquiposCliente.setGraphic(imageViewConsultarEquipoCliente);
        buttonConsultarEquiposCliente.setContentDisplay(ContentDisplay.TOP);
        buttonConsultarEquiposCliente.setGraphicTextGap(25);

        // ICONO DEL BOTÓN DE  ACTUALIZAR CLIENTE
        Image imageActualizarDatosCliente = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoActualizarDatosCliente.png")
        );

        ImageView imageViewActualizarDatosCliente = new ImageView(imageActualizarDatosCliente);
        imageViewActualizarDatosCliente.setFitWidth(68);
        imageViewActualizarDatosCliente.setFitHeight(68);
        imageViewActualizarDatosCliente.setPreserveRatio(true);

        buttonActualizarDatosCliente.setGraphic(imageViewActualizarDatosCliente);
        buttonActualizarDatosCliente.setContentDisplay(ContentDisplay.TOP);
        buttonActualizarDatosCliente.setGraphicTextGap(25);

        // ICONO DEL BOTÓN DE PROGRAMAR CITA TECNICA
        Image imageProgramarCitaTecnica = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoProgramarCitaTecnica.png")
        );

        ImageView imageViewProgramarCitaTecnica = new ImageView(imageProgramarCitaTecnica);
        imageViewProgramarCitaTecnica.setFitWidth(68);
        imageViewProgramarCitaTecnica.setFitHeight(68);
        imageViewProgramarCitaTecnica.setPreserveRatio(true);

        buttonProgramarCitasTecnicas.setGraphic(imageViewProgramarCitaTecnica);
        buttonProgramarCitasTecnicas.setContentDisplay(ContentDisplay.TOP);
        buttonProgramarCitasTecnicas.setGraphicTextGap(25);

        //ICONO BOTON DE CONSULTAR CITA TECNICA
        Image imageConsultarCita = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoConsultarCitaProgramada.png")
        );

        ImageView imageViewConsultarCita = new ImageView(imageConsultarCita);
        imageViewConsultarCita.setFitWidth(68);
        imageViewConsultarCita.setFitHeight(68);
        imageViewConsultarCita.setPreserveRatio(true);

        buttonConsultarCitaProgramadas.setGraphic(imageViewConsultarCita);
        buttonConsultarCitaProgramadas.setContentDisplay(ContentDisplay.TOP);
        buttonConsultarCitaProgramadas.setGraphicTextGap(25);

        //ICONO BOTON DE FILTRAR CITA
        Image imageFiltrarCita = new Image(
                getClass().getResourceAsStream("/Imagenes/iconoFiltro.png")
        );

        ImageView imageViewFiltrarCita = new ImageView(imageFiltrarCita);
        imageViewFiltrarCita.setFitWidth(68);
        imageViewFiltrarCita.setFitHeight(68);
        imageViewFiltrarCita.setPreserveRatio(true);

        buttonFiltrarHistorialServicios.setGraphic(imageViewFiltrarCita);
        buttonFiltrarHistorialServicios.setContentDisplay(ContentDisplay.TOP);
        buttonFiltrarHistorialServicios.setGraphicTextGap(25);

        //ICONO BOTON DE HISTORIAL CITA
        Image imageHistorialCita = new Image(
                getClass().getResourceAsStream("/Imagenes/iconohistorial.png")
        );

        ImageView imageViewHistorialCita = new ImageView(imageHistorialCita);
        imageViewHistorialCita.setFitWidth(68);
        imageViewHistorialCita.setFitHeight(68);
        imageViewHistorialCita.setPreserveRatio(true);

        buttonHistorialServicios.setGraphic(imageViewHistorialCita);
        buttonHistorialServicios.setContentDisplay(ContentDisplay.TOP);
        buttonHistorialServicios.setGraphicTextGap(25);
    }

    // ================== PERMISOS ==================
    private void configurarPermisos() {

        String rol = mainController.getRolUsuario();
        if ("Técnico Coordinador".equalsIgnoreCase(rol)) {
            buttonConsultarCliente.setDisable(false);
            buttonProgramarCitasTecnicas.setDisable(false);
            buttonConsultarCitaProgramadas.setDisable(false);
            buttonHistorialServicios.setDisable(false);

            bloquearBoton(buttonRegistrarCliente);
            bloquearBoton(buttonRegistrarEquipoCliente);
            bloquearBoton(buttonActualizarDatosCliente);
            bloquearBoton(buttonFiltrarHistorialServicios);
            bloquearBoton(buttonConsultarEquiposCliente);
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
    private void clickHistorialServicios(ActionEvent event) {
        System.out.println("Historial de Servicios");
        mainController.saver("historialC.fxml");
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
            case "Gerente General y Técnico" ->
                    mainController.saver("menuGerencia.fxml");
        }
    }
}
