package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.OrdenPago;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class consultarDatosOrdenPagoController
        implements Initializable, ControladorInyectable {

    private MainController mainController;

    @FXML private TextField textFielCodigoOrdenPago;
    @FXML private TableView<OrdenPago> tableViewTablaContenido;
    @FXML private TableColumn<OrdenPago, String> tableColumnCodigo;
    @FXML private TableColumn<OrdenPago, String> tableColumnProveedor;
    @FXML private TableColumn<OrdenPago, LocalDate> tableColumnFechaEmision;
    @FXML private TableColumn<OrdenPago, Double> tableColumnMonto;
    @FXML private Button buttonBuscar;
    @FXML private Button buttonRegresar;
    @FXML private Button buttonVerDetalle;
    private ObservableList<OrdenPago> listaOrdenes;

    @Override
    public void setMainController(MainController main) {
        this.mainController = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // 🔸 Enlazar columnas con el modelo
        tableColumnCodigo.setCellValueFactory(data ->
                data.getValue().codigoProperty());

        tableColumnProveedor.setCellValueFactory(data ->
                data.getValue().proveedorProperty());

        tableColumnFechaEmision.setCellValueFactory(data ->
                data.getValue().fechaEmisionProperty());

        tableColumnMonto.setCellValueFactory(data ->
                data.getValue().montoProperty().asObject());

        textFielCodigoOrdenPago.setOnAction(e -> buttonBuscar.requestFocus());
        // 🔸 Cargar datos de prueba
        cargarDatosSimulados();
    }

    private void cargarDatosSimulados() {
        listaOrdenes = FXCollections.observableArrayList(
                new OrdenPago(
                        "OP-001", "Proveedor A", LocalDate.of(2026, 1, 5), 250.0,
                        "Transferencia", "Pago pendiente de revisión", "Cliente A", "Av. Quito 123",
                        "Mantenimiento", "Alta", "Técnico 1", "Pendiente"
                ),
                new OrdenPago(
                        "OP-001", "Proveedor A", LocalDate.of(2026, 1, 5), 250.0,
                        "Transferencia", "Pago pendiente de revisión", "Cliente A", "Av. Quito 123",
                        "Mantenimiento", "Alta", "Técnico 1", "Pendiente"
                ),
                new OrdenPago(
                        "OP-004", "Proveedor D", LocalDate.of(2026, 1, 8), 300.0,
                        "Transferencia", "Pago parcial recibido", "Cliente D", "Calle 9 de Octubre 200",
                        "Mantenimiento", "Alta", "Técnico 1", "Pendiente"
                )
        );

        tableViewTablaContenido.setItems(listaOrdenes);
    }

    @FXML
    private void buscarOrdenPago(ActionEvent event) {
        String codigo = textFielCodigoOrdenPago.getText().trim();

        if (codigo.isEmpty()) {
            tableViewTablaContenido.setItems(listaOrdenes);
            mostrarAlerta(
                    "Sin resultados",
                    "Ingrese un codigo para buscar",
                    Alert.AlertType.INFORMATION
            );
            return;
        }

        ObservableList<OrdenPago> filtrado = FXCollections.observableArrayList();

        for (OrdenPago op : listaOrdenes) {
            if (op.getCodigo().equalsIgnoreCase(codigo)) {
                filtrado.add(op);
            }
        }

        if (filtrado.isEmpty()) {
            mostrarAlerta(
                    "Sin resultados",
                    "No se encontró una orden con ese código",
                    Alert.AlertType.INFORMATION
            );
        }

        if(filtrado.isEmpty()){
            mostrarAlerta(
                    "Sin resultados",
                    "No se encontraron proveedores con los datos ingresados",
                    Alert.AlertType.INFORMATION
            );
            tableViewTablaContenido.setItems(listaOrdenes);
        }else{
            tableViewTablaContenido.setItems(filtrado);
        }
    }

    @FXML
    private void verDetalle(ActionEvent event) {
        OrdenPago seleccion = tableViewTablaContenido.getSelectionModel().getSelectedItem();
        if (seleccion == null) {
            mostrarAlerta(
                    "Selección requerida",
                    "Seleccione una orden de pago",
                    Alert.AlertType.WARNING
            );
            return;
        }

        mainController.saver("verDetallesOrdenPago.fxml");
    }

    @FXML
    private void regresarModuloProveedor(ActionEvent event) {mainController.saver("moduloProveedor.fxml");}

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}