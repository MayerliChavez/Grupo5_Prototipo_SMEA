package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.HistorialServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class historialCController
        implements Initializable, ControladorInyectable {

    // ===== MAIN =====
    private MainController mainController;

    // ===== FXML =====
    @FXML private TextField textFielNombreProveedor; // cédula del cliente
    @FXML private TableView<HistorialServicio> tableViewClientes;

    @FXML private TableColumn<HistorialServicio, String> tableColumnNombre;
    @FXML private TableColumn<HistorialServicio, String> tableColumnCedulaCliente;
    @FXML private TableColumn<HistorialServicio, String> tableColumnTipoEquipo;
    @FXML private TableColumn<HistorialServicio, String> tableColumnNumeroSerie;
    @FXML private TableColumn<HistorialServicio, String> tableColumnEstado;

    @FXML private Button buttonBuscar;
    @FXML private Button buttonVerDetalle;
    @FXML private Button buttonRegresar;

    // ===== DATOS =====
    private ObservableList<HistorialServicio> listaHistorial;

    // ===== INYECCIÓN =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ===== INIT =====
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Configurar columnas
        tableColumnNombre.setCellValueFactory(d -> d.getValue().nombreClienteProperty());
        tableColumnCedulaCliente.setCellValueFactory(d-> d.getValue().cedulaClienteProperty());
        tableColumnTipoEquipo.setCellValueFactory(d -> d.getValue().tipoEquipoProperty());
        tableColumnNumeroSerie.setCellValueFactory(d -> d.getValue().numeroSerieProperty());
        tableColumnEstado.setCellValueFactory(d -> d.getValue().estadoProperty());

        textFielNombreProveedor.setOnAction(e->buttonBuscar.requestFocus());

        cargarDatosSimulados();
    }

    // ===== DATOS SIMULADOS =====
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

    // ===== BUSCAR POR CÉDULA =====
    @FXML
    private void buscarProveedor(ActionEvent event) {

        String cedula = textFielNombreProveedor.getText().trim();

        if (cedula.isEmpty()) {
            tableViewClientes.setItems(listaHistorial);
            mostrarAlerta(
                    "Cédula inválida",
                    "Ingrese un número de cédula para realizar la búsqueda",
                    Alert.AlertType.WARNING
            );
            return;
        }

        if (!cedula.matches("\\d+")) {
            mostrarAlerta(
                    "Cédula inválida",
                    "La cédula solo debe contener números",
                    Alert.AlertType.WARNING
            );
            return;
        }

        ObservableList<HistorialServicio> filtrado = FXCollections.observableArrayList();

        for (HistorialServicio h : listaHistorial) {
            if (h.getCedulaCliente().equals(cedula)) {
                filtrado.add(h);
            }
        }

        if (filtrado.isEmpty()) {
            mostrarAlerta(
                    "Sin resultados",
                    "No existe historial para la cédula ingresada",
                    Alert.AlertType.INFORMATION
            );
            tableViewClientes.setItems(listaHistorial);
        } else {
            tableViewClientes.setItems(filtrado);
        }
    }

    // ===== VER HISTORIAL =====
    @FXML
    private void verHistorialProveedor(ActionEvent event) {

        HistorialServicio seleccionado =
                tableViewClientes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(
                    "Seleccione un registro",
                    "Debe seleccionar un registro para ver el historial",
                    Alert.AlertType.WARNING
            );
            return;
        }
        mainController.saver("historialServicios.fxml");
    }

    // ===== REGRESAR =====
    @FXML
    private void regresarModuloProveedor(ActionEvent event) {
        mainController.saver("moduloCliente.fxml");
    }

    // ===== ALERTAS =====
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
