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

public class visualizarHistorialDePagosController
        implements Initializable, ControladorInyectable {

    private MainController mainController;

    // ================== FXML ==================

    @FXML private TableView<OrdenPago> tableViewOrdenPago;
    @FXML private TableColumn<OrdenPago, String> tableColumnCodigo;
    @FXML private TableColumn<OrdenPago, String> tableColumnProveedor;
    @FXML private TableColumn<OrdenPago, LocalDate> tableColumnFecha;
    @FXML private TableColumn<OrdenPago, Double> tableColumnMonto;
    @FXML private TableColumn<OrdenPago, String> tableColumnTipoPago;

    @FXML private TextField textFielNombreProveedor;

    @FXML private Button buttonBuscar;
    @FXML private Button buttonVerDetalle;
    @FXML private Button buttonGenerarPDF;
    @FXML private Button buttonRegresar;

    private ObservableList<OrdenPago> listaOrdenes;

    // ================== INYECCIÓN ==================

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ================== INIT ==================

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tableColumnCodigo.setCellValueFactory(data ->
                data.getValue().codigoProperty());

        tableColumnProveedor.setCellValueFactory(data ->
                data.getValue().proveedorProperty());

        tableColumnFecha.setCellValueFactory(data ->
                data.getValue().fechaProperty());

        tableColumnMonto.setCellValueFactory(data ->
                data.getValue().montoProperty().asObject());

        tableColumnTipoPago.setCellValueFactory(data ->
                data.getValue().tipoPagoProperty());

        textFielNombreProveedor.setOnAction(e->buttonBuscar.requestFocus());

        cargarDatosSimulados();
    }

    // ================== DATOS SIMULADOS ==================

    private void cargarDatosSimulados() {

        listaOrdenes = FXCollections.observableArrayList(
                new OrdenPago(
                "OP-001", "Proveedor A", LocalDate.of(2026, 1, 5), 250.0,
                "Transferencia", "Pago pendiente de revisión", "Cliente A", "Av. Quito 123",
                "Mantenimiento", "Alta", "Técnico 1", "Pendiente"
        ),

                new OrdenPago(
                        "OP-002", "Proveedor B", LocalDate.of(2026, 1, 6), 400.0,
                        "Efectivo", "Pago completado con factura", "Cliente B", "Calle Loja 45",
                        "Instalación", "Media", "Técnico 2", "Completado"
                ),

                new OrdenPago(
                        "OP-004", "Proveedor D", LocalDate.of(2026, 1, 8), 300.0,
                        "Transferencia", "Pago parcial recibido", "Cliente D", "Calle 9 de Octubre 200",
                        "Mantenimiento", "Alta", "Técnico 1", "Pendiente"
                ));
        // ⚠️ Solo aprobadas
        tableViewOrdenPago.setItems(listaOrdenes);
    }

    // ================== BUSCAR ==================

    @FXML
    private void buscarProveedor(ActionEvent event) {
        String texto = textFielNombreProveedor.getText().trim().toLowerCase();

        if (texto.isEmpty()) {
            tableViewOrdenPago.setItems(listaOrdenes);
            mostrarAlerta(
                    "Sin resultados",
                    "Ingrese un nombre para realizar la busqueda",
                    Alert.AlertType.INFORMATION
            );
            return;
        }

        ObservableList<OrdenPago> filtrado = FXCollections.observableArrayList();

        for (OrdenPago op : listaOrdenes) {
            if (op.getProveedor().toLowerCase().contains(texto)) {
                filtrado.add(op);
            }
        }

        if(filtrado.isEmpty()){
            mostrarAlerta(
                    "Sin resultados",
                    "No se encontraron proveedores con los datos ingresados",
                    Alert.AlertType.INFORMATION
            );
            tableViewOrdenPago.setItems(listaOrdenes);
        }else{
            tableViewOrdenPago.setItems(filtrado);
        }
    }

    // ================== VER DETALLE ==================

    @FXML
    private void verDetalleOrden(ActionEvent event) {

        OrdenPago seleccionado =
                tableViewOrdenPago.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(
                    "Selección requerida",
                    "Seleccione una orden de pago",
                    Alert.AlertType.WARNING
            );
            return;
        }

        mainController.saver("verDetalleHistorial.fxml");
    }

    // ================== GENERAR PDF (SIMULADO) ==================

    @FXML
    private void generarPDF(ActionEvent event) {

        OrdenPago seleccionado =
                tableViewOrdenPago.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(
                    "Selección requerida",
                    "Seleccione una orden para generar el PDF",
                    Alert.AlertType.WARNING
            );
            return;
        }

        // Simulación (caso de uso correcto)
        mostrarAlerta(
                "PDF generado",
                "El PDF de la orden " + seleccionado.getCodigo()
                        + " fue generado correctamente.",
                Alert.AlertType.INFORMATION
        );
    }

    // ================== NAVEGACIÓN ==================

    @FXML
    private void regresarMenu(ActionEvent event) {
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
