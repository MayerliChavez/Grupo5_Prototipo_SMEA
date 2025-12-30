package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.Proveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class actualizarDatosProveedorController
        implements Initializable, ControladorInyectable {

    private MainController mainController;

    @FXML private TextField textFielNombreProveedor;

    @FXML private TableView<Proveedor> tableViewTablaContenido;
    @FXML private TableColumn<Proveedor, String> tableColumnNombre;
    @FXML private TableColumn<Proveedor, String> tableColumnRazonSocial;
    @FXML private TableColumn<Proveedor, String> tableColumnRUC;
    @FXML private TableColumn<Proveedor, String> tableColumnEstado;

    @FXML private Button buttonBuscar;
    @FXML private Button buttonActualizarDatos;
    @FXML private Button buttonRegresar;

    private ObservableList<Proveedor> listaProveedores;

    // ================== INYECCIÓN ==================

    @Override
    public void setMainController(MainController main) {
        this.mainController = main;
    }

    // ================== INIT ==================

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tableColumnNombre.setCellValueFactory(data ->
                data.getValue().nombreProperty());

        tableColumnRazonSocial.setCellValueFactory(data ->
                data.getValue().razonSocialProperty());

        tableColumnRUC.setCellValueFactory(data ->
                data.getValue().rucProperty());

        tableColumnEstado.setCellValueFactory(data ->
                data.getValue().estadoProperty());

        cargarDatosSimulados();
    }

    // ================== DATOS SIMULADOS ==================

    private void cargarDatosSimulados() {
        listaProveedores = FXCollections.observableArrayList(
                new Proveedor("Proveedor A", "A S.A.", "1234567890001", "ACTIVO", "0987090787", "proveedorA@gmai.com", "Calderon"),
                new Proveedor("Proveedor B", "B S.A.", "0987654321001", "INACTIVO", "0987090757", "proveedorB@gmai.com", "Calderon- Moran"),
                new Proveedor("Proveedor C", "C S.A.", "1122334455001", "ACTIVO", "09870907787", "proveedorC@gmai.com", "Mitad del Mundo")
        );

        tableViewTablaContenido.setItems(listaProveedores);
    }

    // ================== BUSCAR ==================

    @FXML
    private void buscarProveedor(ActionEvent event) {
        String texto = textFielNombreProveedor.getText().trim().toLowerCase();

        if (texto.isEmpty()) {
            tableViewTablaContenido.setItems(listaProveedores);
            return;
        }

        ObservableList<Proveedor> filtrado = FXCollections.observableArrayList();

        for (Proveedor p : listaProveedores) {
            if (p.getNombre().toLowerCase().contains(texto)) {
                filtrado.add(p);
            }
        }

        tableViewTablaContenido.setItems(filtrado);
    }

    // ================== ACTUALIZAR ==================

    @FXML
    private void actualizarDatos(ActionEvent event) {
        Proveedor seleccionado =
                tableViewTablaContenido.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(
                    "Selección requerida",
                    "Seleccione un proveedor para actualizar",
                    Alert.AlertType.WARNING
            );
            return;
        }

        mainController.saver("actualizarDatosP.fxml");
    }

    // ================== NAVEGACIÓN ==================

    @FXML
    private void regresarModuloProveedor(ActionEvent event) {
        mainController.saver("moduloProveedor.fxml");
    }

    // ================== UTIL ==================

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
