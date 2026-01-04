package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.OrdenSalida;
import com.smea.prototipo_smea.clasesNormales.DetalleOrdenSalida;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class consultarOrdenSalidaController implements Initializable, ControladorInyectable {

    // ===== MAIN CONTROLLER =====
    private MainController mainController;

    // ================== TABLA ORDENES ==================
    @FXML private TableView<OrdenSalida> tableViewTablaContenido;
    @FXML private TableColumn<OrdenSalida, String> tableColumnCodigo;
    @FXML private TableColumn<OrdenSalida, String> tableColumnResponsable;
    @FXML private TableColumn<OrdenSalida, LocalDate> tableColumnFechaEmision;
    @FXML private TableColumn<OrdenSalida, String> tableColumnMotivo;
    @FXML private TableColumn<OrdenSalida, String> tableColumnEstado;

    // ================== CAMPOS BUSQUEDA ==================
    @FXML private TextField textFielCodigoOrdenPago;
    @FXML private DatePicker datePickerRangoFecha;
    @FXML private Button buttonBuscarFecha;
    @FXML private Button buttonBuscar;

    // ================== DATOS ==================
    private ObservableList<OrdenSalida> listaOrdenes =
            FXCollections.observableArrayList();

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ================== INIT ==================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datePickerRangoFecha.setValue(LocalDate.now());
        configurarTabla();
        cargarDatosPrueba();
        tableViewTablaContenido.setItems(listaOrdenes);
        textFielCodigoOrdenPago.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.trim().isEmpty()) {
                tableViewTablaContenido.setItems(listaOrdenes);
            }
        });

        datePickerRangoFecha.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) {
                tableViewTablaContenido.setItems(listaOrdenes);
            }
        });

        textFielCodigoOrdenPago.setOnAction(e-> buttonBuscar.requestFocus());
        datePickerRangoFecha.setOnAction(e-> buttonBuscarFecha.requestFocus());
        configurarTabulacion();
    }

    // ================== TABULACIÓN ==================
    private void configurarTabulacion() {
        configurarTab(textFielCodigoOrdenPago, buttonBuscar);
        configurarTab(datePickerRangoFecha, buttonBuscarFecha);
    }

    private void configurarTab(Control actual, Control siguiente) {
        actual.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.TAB) {
                siguiente.requestFocus();
                event.consume();
            }
        });
    }

    // ================== CONFIG TABLA ==================
    private void configurarTabla() {

        tableColumnCodigo.setCellValueFactory(
                new PropertyValueFactory<>("codigo"));

        tableColumnResponsable.setCellValueFactory(
                new PropertyValueFactory<>("responsable"));

        tableColumnFechaEmision.setCellValueFactory(
                new PropertyValueFactory<>("fechaEmision"));

        tableColumnMotivo.setCellValueFactory(
                new PropertyValueFactory<>("motivo"));

        tableColumnEstado.setCellValueFactory(
                new PropertyValueFactory<>("estado"));
    }

    // ================== DATOS DE PRUEBA ==================
    private void cargarDatosPrueba() {

        listaOrdenes.add(new OrdenSalida(
                "OS-001",
                "Juan Pérez",
                LocalDate.of(2026, 1, 5),
                "Mantenimiento",
                "Taller Central",
                "Orden para mantenimiento preventivo",
                "REGISTRADA"
        ));

        listaOrdenes.add(new OrdenSalida(
                "OS-002",
                "María López",
                LocalDate.of(2026, 1, 10),
                "Reparación",
                "Sucursal Norte",
                "Reparación de equipos",
                "DESPACHADA"
        ));

        listaOrdenes.add(new OrdenSalida(
                "OS-003",
                "Carlos Ruiz",
                LocalDate.of(2026, 1, 4),
                "Instalación",
                "Proyecto Externo",
                "Instalación de equipos",
                "ANULADA"
        ));
    }

    // ================== BUSCAR POR CÓDIGO ==================
    @FXML
    private void buscarPorCodigo(ActionEvent event) {

        String codigo = textFielCodigoOrdenPago.getText();

        if (codigo == null || codigo.trim().isEmpty()) {
            mostrarAlerta(
                    "Aviso",
                    "Ingrese un código para buscar.",
                    Alert.AlertType.WARNING
            );
            tableViewTablaContenido.setItems(listaOrdenes);
            return;
        }

        ObservableList<OrdenSalida> filtrado =
                FXCollections.observableArrayList();

        String codigoLower = codigo.toLowerCase();

        for (OrdenSalida orden : listaOrdenes) {
            if (orden.getCodigo().toLowerCase().contains(codigoLower)) {
                filtrado.add(orden);
            }
        }

        // 🔹 CUANDO NO SE ENCUENTRA NINGÚN RESULTADO
        if (filtrado.isEmpty()) {
            mostrarAlerta(
                    "Sin resultados",
                    "No existe ninguna orden de salida con el código ingresado.",
                    Alert.AlertType.INFORMATION
            );
            tableViewTablaContenido.setItems(listaOrdenes);
            return;
        }

        tableViewTablaContenido.setItems(filtrado);
    }


    // ================== BUSCAR POR FECHA ==================
    @FXML
    private void buscarPorFecha(ActionEvent event) {

        LocalDate fecha = datePickerRangoFecha.getValue();

        if (fecha == null) {
            mostrarAlerta(
                    "Aviso",
                    "Seleccione una fecha para buscar.",
                    Alert.AlertType.WARNING
            );
            tableViewTablaContenido.setItems(listaOrdenes);
            return;
        }

        ObservableList<OrdenSalida> filtrado =
                FXCollections.observableArrayList();

        for (OrdenSalida orden : listaOrdenes) {
            if (orden.getFechaEmision().equals(fecha)) {
                filtrado.add(orden);
            }
        }

        // 🔹 VALIDACIÓN CUANDO NO HAY RESULTADOS
        if (filtrado.isEmpty()) {
            mostrarAlerta(
                    "Sin resultados",
                    "No se encontraron órdenes de salida para la fecha seleccionada.",
                    Alert.AlertType.INFORMATION
            );
            tableViewTablaContenido.setItems(listaOrdenes);
            return;
        }

        tableViewTablaContenido.setItems(filtrado);
    }


    // ================== VER DETALLE ==================
    @FXML
    private void verDetalle(ActionEvent event) {

        OrdenSalida seleccionada =
                tableViewTablaContenido.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            mostrarAlerta(
                    "Aviso",
                    "Seleccione una orden para ver el detalle.",
                    Alert.AlertType.WARNING
            );
            return;
        }

        // ===== Simulación de ítems (luego BD) =====
        ObservableList<DetalleOrdenSalida> items =
                FXCollections.observableArrayList(
                        new DetalleOrdenSalida("IT-01", "Llave", 3),
                        new DetalleOrdenSalida("IT-02", "Filtro", 5),
                        new DetalleOrdenSalida("IT-03", "Aceite", 2)
                );

        StringBuilder detalle = new StringBuilder();
        detalle.append("Código: ").append(seleccionada.getCodigo()).append("\n")
                .append("Responsable: ").append(seleccionada.getResponsable()).append("\n")
                .append("Fecha: ").append(seleccionada.getFechaEmision()).append("\n")
                .append("Motivo: ").append(seleccionada.getMotivo()).append("\n")
                .append("Destino: ").append(seleccionada.getDestino()).append("\n")
                .append("Estado: ").append(seleccionada.getEstado()).append("\n\n")
                .append("Ítems:\n");

        for (DetalleOrdenSalida item : items) {
            detalle.append("- ")
                    .append(item.getNombreItem())
                    .append(" | Cantidad: ")
                    .append(item.getCantidad())
                    .append("\n");
        }

        mostrarAlerta(
                "Detalle de Orden de Salida",
                detalle.toString(),
                Alert.AlertType.INFORMATION
        );
    }

    // ================== REGRESAR ==================
    @FXML
    private void regresarModuloProveedor(ActionEvent event) {
        System.out.println("Regresar al módulo anterior");
        mainController.saver("movimientoInventario.fxml");
    }

    // ================== ALERTA ==================
    private void mostrarAlerta(String titulo, String mensaje,
                               Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
