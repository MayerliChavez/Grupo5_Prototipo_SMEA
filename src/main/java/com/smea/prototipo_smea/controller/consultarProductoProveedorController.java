package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.ProductoProveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class consultarProductoProveedorController
        implements Initializable, ControladorInyectable {

    private MainController mainController;

    @FXML private TextField textFielNombreProveedor;

    @FXML private TableView<ProductoProveedor> tableViewTablaContenido;
    @FXML private TableColumn<ProductoProveedor, String> tableColumnProveedor;
    @FXML private TableColumn<ProductoProveedor, String> tableColumnNombreProducto;
    @FXML private TableColumn<ProductoProveedor, Double> tableColumnCosto;
    @FXML private TableColumn<ProductoProveedor, String> tableColumnEstado;

    @FXML private Button buttonBuscar;
    @FXML private Button buttonVerDetalle;
    @FXML private Button buttonEstado;
    @FXML private Button buttonRegresar;

    private ObservableList<ProductoProveedor> listaProductos;

    @Override
    public void setMainController(MainController main) {
        this.mainController = main;
        configurarPermisos();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tableColumnProveedor.setCellValueFactory(data ->
                data.getValue().proveedorProperty());

        tableColumnNombreProducto.setCellValueFactory(data ->
                data.getValue().nombreProductoProperty());

        tableColumnCosto.setCellValueFactory(data ->
                data.getValue().costoProperty().asObject());

        tableColumnEstado.setCellValueFactory(data ->
                data.getValue().estadoProperty());

        cargarDatosSimulados();

        buttonEstado.setVisible(false); // oculto por defecto
    }


    // ================== DATOS SIMULADOS ==================

    private void cargarDatosSimulados() {
        listaProductos = FXCollections.observableArrayList(
                new ProductoProveedor("Proveedor A", "Aceite 20W50", 25.50, "Activo", "Repuesto", "Comprando hace 20 dias en buen estado"),
                new ProductoProveedor("Proveedor A", "Filtro de aire", 12.00, "Inactivo", "Herramienta", "Utilizada para ajustra tornillos"),
                new ProductoProveedor("Proveedor B", "Batería 12V", 95.00, "Suspendido", "Maquina", "Utilizada para limpiar el polvo"),
                new ProductoProveedor("Proveedor C", "Pastillas de freno", 45.90, "Activo","herramienta","Uso general")
        );

        tableViewTablaContenido.setItems(listaProductos);
    }

    // ================== BUSCAR ==================

    @FXML
    private void buscarProveedor(ActionEvent event) {
        String texto = textFielNombreProveedor.getText().trim().toLowerCase();

        if (texto.isEmpty()) {
            tableViewTablaContenido.setItems(listaProductos);
            return;
        }

        ObservableList<ProductoProveedor> filtrado = FXCollections.observableArrayList();

        for (ProductoProveedor p : listaProductos) {
            if (p.getProveedor().toLowerCase().contains(texto)) {
                filtrado.add(p);
            }
        }

        tableViewTablaContenido.setItems(filtrado);
    }

    // ================== VER DETALLE (ALERT) ==================

    @FXML
    private void verDetalleProveedor(ActionEvent event) {
        ProductoProveedor seleccionado =
                tableViewTablaContenido.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(
                    "Selección requerida",
                    "Seleccione un producto",
                    Alert.AlertType.WARNING
            );
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detalle del producto");
        alert.setHeaderText(null);
        alert.setContentText(
                "Proveedor: " + seleccionado.getProveedor() + "\n" +
                        "Producto: " + seleccionado.getNombreProducto() + "\n" +
                        "Costo: $" + seleccionado.getCosto() + "\n" +
                        "Categoria: " + seleccionado.getCategoria() + "\n" +
                        "Descripcion del producto: " + seleccionado.getDescripcionDelProducto() + "\n" +
                        "Estado: " + seleccionado.getEstado()
        );
        alert.showAndWait();
    }

    // ================== CAMBIAR ESTADO ==================

    @FXML
    private void cambiarEstadoProveedor(ActionEvent event) {
        ProductoProveedor seleccionado =
                tableViewTablaContenido.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(
                    "Selección requerida",
                    "Seleccione un producto",
                    Alert.AlertType.WARNING
            );
            return;
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(
                seleccionado.getEstado(),
                "Activo", "Inactivo", "Suspendido"
        );

        dialog.setTitle("Cambiar estado del producto");
        dialog.setHeaderText(null);
        dialog.setContentText("Seleccione el nuevo estado:");

        dialog.showAndWait().ifPresent(nuevoEstado -> {
            seleccionado.setEstado(nuevoEstado);
            tableViewTablaContenido.refresh();
        });
    }

    // ================== PERMISOS ==================

    private void configurarPermisos() {
        buttonEstado.setVisible(false);

        if (mainController == null) return;

        if ("Administrador".equals(mainController.getRolUsuario())) {
            buttonEstado.setVisible(true);
        }
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
