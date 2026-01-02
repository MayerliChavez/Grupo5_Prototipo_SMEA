package com.smea.prototipo_smea.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.smea.prototipo_smea.clasesNormales.HistorialServicio;

import java.net.URL;
import java.util.ResourceBundle;

public class historialServiciosController
        implements Initializable, ControladorInyectable {

    private MainController mainController;

    @FXML private TableView<HistorialServicio> tableViewClientes;

    @FXML private TableColumn<HistorialServicio, String> tableColumnNombre;       // Fecha
    @FXML private TableColumn<HistorialServicio, String> tableColumnTipoEquipo;   // Equipo
    @FXML private TableColumn<HistorialServicio, String> tableColumnNumeroSerie;  // Tipo servicio
    @FXML private TableColumn<HistorialServicio, String> tableColumnTecnico;       // Técnico
    @FXML private TableColumn<HistorialServicio, String> tableColumnEstadoServicio;
    @FXML private TableColumn<HistorialServicio, Double> tableColumnCosto;

    @FXML private Button buttonRegresar;
    @FXML private Button buttonVerDetalle;

    private ObservableList<HistorialServicio> listaHistorial;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableColumnNombre.setCellValueFactory(d -> d.getValue().fechaProperty());
        tableColumnTipoEquipo.setCellValueFactory(d -> d.getValue().tipoEquipoProperty());
        tableColumnNumeroSerie.setCellValueFactory(d -> d.getValue().tipoServicioProperty());
        tableColumnTecnico.setCellValueFactory(d -> d.getValue().tecnicoProperty());
        tableColumnEstadoServicio.setCellValueFactory(d -> d.getValue().estadoProperty());
        tableColumnCosto.setCellValueFactory(d -> d.getValue().costoProperty().asObject());

        cargarDatosSimulados();
    }

    private void cargarDatosSimulados() {
        listaHistorial = FXCollections.observableArrayList(
                new HistorialServicio(
                        "Juan Lopez",
                        "Elevador hidráulico",
                        "EHH-345",
                        "EN CURSO",
                        "1728678158",
                        "2025-01-10",
                        "PREVENTIIVO",
                        "Ana Flores",
                        120.0
                ),
                new HistorialServicio(
                        "Maria Chavez",
                        "Compresor industrial",
                        "CI-8970",
                        "FINALIIZADO",
                        "0987654321",
                        "2025-01-18",
                        "CORRECTIVO",
                        "María López",
                        250.50
                ),
                new HistorialServicio(
                        "Homero Simpson",
                        "Gata eléctrica",
                        "ED-1234",
                        "FINALIZADO",
                        "1234567890",
                        "2025-02-02",
                        "EMERGENCIA",
                        "Luis Méndez",
                        180.75
                )
        );

        tableViewClientes.setItems(listaHistorial);
    }

    @FXML
    private void verDetalleProveedor(ActionEvent event) {
        mostrarAlerta("Reporte generado",
                "El reporte PDF fue generado correctamente.",
                Alert.AlertType.INFORMATION);
    }

    @FXML
    private void regresarModuloCliente(ActionEvent event) {
        mainController.saver("historialC.fxml");
    }

    private void mostrarAlerta(String t, String m, Alert.AlertType tipo) {
        Alert a = new Alert(tipo);
        a.setTitle(t);
        a.setHeaderText(null);
        a.setContentText(m);
        a.showAndWait();
    }
}
