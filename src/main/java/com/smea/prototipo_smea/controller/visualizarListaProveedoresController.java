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

public class visualizarListaProveedoresController
        implements Initializable, ControladorInyectable{
    private MainController mainController;

    @FXML private TextField textFielNombreProveedor;
    @FXML private TableView<Proveedor> tableViewTablaContenido;
    @FXML private TableColumn<Proveedor, String> tableColumnNombre;
    @FXML private TableColumn<Proveedor, String> tableColumnRazonSocial;
    @FXML private TableColumn<Proveedor, String> tableColumnRUC;
    @FXML private TableColumn<Proveedor, String> tableColumnEstado;

    @FXML private Button buttonBuscar;
    @FXML private Button buttonVerDetalle;
    @FXML private Button buttonRegresar;

    private ObservableList<Proveedor> listaProveedores;

    @Override
    public void setMainController(MainController main) {
        this.mainController = main;
        configurarPermisos();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        configurarTabulacion();

        tableColumnNombre.setCellValueFactory(d -> d.getValue().nombreProperty());
        tableColumnRazonSocial.setCellValueFactory(d -> d.getValue().razonSocialProperty());
        tableColumnRUC.setCellValueFactory(d -> d.getValue().rucProperty());
        tableColumnEstado.setCellValueFactory(d -> d.getValue().estadoProperty());

        textFielNombreProveedor.setOnAction(e->buttonBuscar.requestFocus());

        cargarDatosSimulados();

    }

    private void configurarTabulacion() {
        configurarTab(textFielNombreProveedor, buttonBuscar);
    }

    private void configurarTab(Control actual, Control siguiente) {
        actual.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.TAB) {
                siguiente.requestFocus();
                event.consume();
            }
        });
    }

    // ================== PERMISOS ==================

    private void configurarPermisos() {
        String rol = mainController.getRolUsuario();
    }

    // ================== DATOS ==================

    private void cargarDatosSimulados() {
        listaProveedores = FXCollections.observableArrayList(
                new Proveedor("Proveedor A", "A S.A.", "1234567890001", "ACTIVO", "0987090787", "proveedorA@gmai.com", "Calderon"),
                new Proveedor("Proveedor B", "B S.A.", "0987654321001", "INACTIVO", "0987090757", "proveedorB@gmai.com", "Calderon- Moran"),
                new Proveedor("Proveedor C", "C S.A.", "1122334455001", "ACTIVO", "09870907787", "proveedorC@gmai.com", "Mitad del Mundo")
        );

        tableViewTablaContenido.setItems(listaProveedores);
    }

    // ================== ACCIONES ==================

    @FXML
    private void buscarProveedor(ActionEvent event) {
        String texto = textFielNombreProveedor.getText().trim().toLowerCase();

        if (texto.isEmpty()) {
            tableViewTablaContenido.setItems(listaProveedores);
            mostrarAlerta(
                    "Sin resultados",
                    "Ingrese un nombre para buscar",
                    Alert.AlertType.INFORMATION
            );
            return;
        }

        ObservableList<Proveedor> filtrado = FXCollections.observableArrayList();

        for (Proveedor p : listaProveedores) {
            if (p.getNombre().toLowerCase().contains(texto)) {
                filtrado.add(p);
            }
        }

        if(filtrado.isEmpty()){
            mostrarAlerta(
                    "Sin resultados",
                    "No se encontraron clientes con los datos ingresados",
                    Alert.AlertType.INFORMATION
            );
            tableViewTablaContenido.setItems(listaProveedores);
        }else{
        tableViewTablaContenido.setItems(filtrado);
        }
    }

    @FXML
    private void verDetalleProveedor(ActionEvent event) {
        Proveedor seleccionado = tableViewTablaContenido.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Seleccione un proveedor",
                    "Debe seleccionar un proveedor de la tabla",
                    Alert.AlertType.WARNING);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detalle del proveedor");
        alert.setHeaderText(null);
        alert.setContentText(
                "Nombre: " + seleccionado.getNombre() + "\n" +
                        "Razón Social: " + seleccionado.getRazonSocial() + "\n" +
                        "RUC: " + seleccionado.getRuc() + "\n" +
                        "Telefono: " + seleccionado.getTelefono() + "\n" +
                        "Email: " + seleccionado.getEmail() + "\n" +
                        "Direccion: " + seleccionado.getDireccion() + "\n" +
                        "Estado: " + seleccionado.getEstado()
        );
        alert.showAndWait();
    }

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
