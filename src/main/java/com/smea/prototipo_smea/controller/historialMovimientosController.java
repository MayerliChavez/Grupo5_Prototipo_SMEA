package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.MovimientoInventario;
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

public class historialMovimientosController implements Initializable, ControladorInyectable {

    private MainController mainController;

    // ================== TABLA ==================
    @FXML private TableView<MovimientoInventario> tableViewRegistro;
    @FXML private TableColumn<MovimientoInventario, LocalDate> tableColumnFecha;
    @FXML private TableColumn<MovimientoInventario, String> tableColumnTipoMovimiento;
    @FXML private TableColumn<MovimientoInventario, String> tableColumnItem;
    @FXML private TableColumn<MovimientoInventario, Integer> tableColumnCantidad;
    @FXML private TableColumn<MovimientoInventario, String> tableColumnResponsable;

    // ================== FILTROS ==================
    @FXML private ChoiceBox<String> chiceBoxTipoMovimiento;
    @FXML private TextField textfieldItem;
    @FXML private DatePicker datePickerFechaDesde;
    @FXML private DatePicker datePickerFechaHasta;
    @FXML private Button buttonFiltrar;

    // ================== DATOS ==================
    private ObservableList<MovimientoInventario> listaMovimientos =
            FXCollections.observableArrayList();

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ================== INIT ==================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarTabla();
        cargarTiposMovimiento();
        cargarDatosPrueba();
        tableViewRegistro.setItems(listaMovimientos);

        chiceBoxTipoMovimiento.setOnAction(e-> textfieldItem.requestFocus());
        textfieldItem.setOnAction(e-> datePickerFechaDesde.requestFocus());
        datePickerFechaHasta.setOnAction(e-> buttonFiltrar.requestFocus());
        configurarTabulacion();
    }

    // ================== TABULACIÓN ==================
    private void configurarTabulacion() {
        configurarTab(chiceBoxTipoMovimiento, textfieldItem);
        configurarTab(textfieldItem, datePickerFechaDesde);
        configurarTab(datePickerFechaDesde, datePickerFechaHasta);
        configurarTab(datePickerFechaHasta, buttonFiltrar);
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
        tableColumnFecha.setCellValueFactory(
                new PropertyValueFactory<>("fecha"));

        tableColumnTipoMovimiento.setCellValueFactory(
                new PropertyValueFactory<>("tipoMovimiento"));

        tableColumnItem.setCellValueFactory(
                new PropertyValueFactory<>("item"));

        tableColumnCantidad.setCellValueFactory(
                new PropertyValueFactory<>("cantidad"));

        tableColumnResponsable.setCellValueFactory(
                new PropertyValueFactory<>("responsable"));
    }

    // ================== CHOICE BOX ==================
    private void cargarTiposMovimiento() {
        chiceBoxTipoMovimiento.getItems().addAll(
                "Todos",
                "Entrada",
                "Salida",
                "Devolución"
        );
        chiceBoxTipoMovimiento.setValue("Todos");
    }

    // ================== DATOS DE PRUEBA ==================
    private void cargarDatosPrueba() {
        listaMovimientos.add(new MovimientoInventario(
                LocalDate.of(2026, 1, 5),
                "Entrada",
                "Taladro",
                10,
                "Juan Pérez"
        ));

        listaMovimientos.add(new MovimientoInventario(
                LocalDate.of(2026, 1, 7),
                "Salida",
                "Taladro",
                3,
                "María López"
        ));

        listaMovimientos.add(new MovimientoInventario(
                LocalDate.of(2026, 1, 10),
                "Entrada",
                "Martillo",
                15,
                "Carlos Ruiz"
        ));
    }

    // ================== FILTRAR ==================
    @FXML
    private void buscarCliente(ActionEvent event) {

        String tipo = chiceBoxTipoMovimiento.getValue();
        String item = textfieldItem.getText().toLowerCase();
        LocalDate desde = datePickerFechaDesde.getValue();
        LocalDate hasta = datePickerFechaHasta.getValue();

        ObservableList<MovimientoInventario> filtrado =
                FXCollections.observableArrayList();

        for (MovimientoInventario m : listaMovimientos) {

            boolean coincideTipo =
                    tipo == null || tipo.equals("Todos")
                            || m.getTipoMovimiento().equalsIgnoreCase(tipo);

            boolean coincideItem =
                    item == null || item.isEmpty()
                            || m.getItem().toLowerCase().contains(item);

            boolean coincideDesde =
                    desde == null || !m.getFecha().isBefore(desde);

            boolean coincideHasta =
                    hasta == null || !m.getFecha().isAfter(hasta);

            if (coincideTipo && coincideItem
                    && coincideDesde && coincideHasta) {
                filtrado.add(m);
            }
        }

        if (filtrado.isEmpty()) {
            mostrarAlerta(
                    "Sin resultados",
                    "No se encontraron movimientos con los filtros ingresados.",
                    Alert.AlertType.INFORMATION
            );
            tableViewRegistro.setItems(listaMovimientos);
            return;
        }

        tableViewRegistro.setItems(filtrado);
    }

    // ================== LIMPIAR ==================
    @FXML
    private void limpiarFiltros(ActionEvent event) {
        chiceBoxTipoMovimiento.setValue("Todos");
        textfieldItem.clear();
        datePickerFechaDesde.setValue(null);
        datePickerFechaHasta.setValue(null);
        tableViewRegistro.setItems(listaMovimientos);
    }

    // ================== REGRESAR ==================
    @FXML
    private void regresarModuloCliente(ActionEvent event) {
        System.out.println("Regresar al módulo anterior");
        mainController.saver("movimientosInventario.fxml");
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
