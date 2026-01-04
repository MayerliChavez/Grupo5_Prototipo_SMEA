package com.smea.prototipo_smea.controller;

import javafx.collections.FXCollections;
import com.smea.prototipo_smea.clasesNormales.ItemInventario;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.ResourceBundle;

public class registrarOrdenSalidaController
        implements Initializable, ControladorInyectable {

    // ====== REFERENCIA AL CONTROLADOR CENTRAL ======
    private MainController mainController;

    // =========================
    // DATOS DE LA ORDEN
    // =========================
    @FXML private DatePicker datePickerFechaEmision;
    @FXML private ChoiceBox<String> choiseBoxMotivo;
    @FXML private TextField textFieldDestino;
    @FXML private TextField textFieldResponsable;
    @FXML private TextArea textAreaObservaciones;
    @FXML private Button buttonBuscar;

    // =========================
    // BUSCAR ÍTEM
    // =========================
    @FXML private TextField textFielBuscarItem;
    @FXML private TableView<ItemInventario> tableViewTablaBuscarItem;
    @FXML private TableColumn<ItemInventario, String> tableColumnCodigo;
    @FXML private TableColumn<ItemInventario, String> tableColumnNombre;
    @FXML private TableColumn<ItemInventario, Integer> tableColumnStock;

    // =========================
    // ÍTEMS SELECCIONADOS
    // =========================
    @FXML private TableView<ItemInventario> tableViewTablaItemSeleccionado;
    @FXML private TableColumn<ItemInventario, String> tableColumnCodigoIS;
    @FXML private TableColumn<ItemInventario, String> tableColumnNombreIS;
    @FXML private TableColumn<ItemInventario, Integer> tableColumnStockIS;
    @FXML private TableColumn<ItemInventario, Integer> tableColumnCantidad;

    // =========================
    // LISTAS
    // =========================
    private ObservableList<ItemInventario> listaInventario =
            FXCollections.observableArrayList();

    private ObservableList<ItemInventario> listaSeleccionados =
            FXCollections.observableArrayList();

    // =========================
    // INITIALIZE
    // =========================

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fecha por defecto
        datePickerFechaEmision.setValue(LocalDate.now());

        choiseBoxMotivo.setItems(
                FXCollections.observableArrayList("Mantenimiento", "Préstamo", "Consumo")
        );

        // Columnas tabla búsqueda
        tableColumnCodigo.setCellValueFactory(
                new PropertyValueFactory<>("codigo")
        );
        tableColumnNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );
        tableColumnStock.setCellValueFactory(
                new PropertyValueFactory<>("stock")
        );

        // Columnas tabla seleccionados
        tableColumnCodigoIS.setCellValueFactory(
                new PropertyValueFactory<>("codigo")
        );
        tableColumnNombreIS.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );
        tableColumnStockIS.setCellValueFactory(
                new PropertyValueFactory<>("stock")
        );
        tableColumnCantidad.setCellValueFactory(
                new PropertyValueFactory<>("cantidad")
        );

        // Datos de prueba
        cargarDatosInventario();

        tableViewTablaBuscarItem.setItems(listaInventario);
        tableViewTablaItemSeleccionado.setItems(listaSeleccionados);

        configurarTabulacion();

        datePickerFechaEmision.setOnAction(e-> choiseBoxMotivo.requestFocus());
        choiseBoxMotivo.setOnAction(e-> textFieldResponsable.requestFocus());
        textFieldResponsable.setOnAction(e-> textFieldDestino.requestFocus());
        textFieldDestino.setOnAction(e-> textAreaObservaciones.requestFocus());
        textAreaObservaciones.setOnKeyPressed(e -> {
                    if (e.getCode().toString().equals("ENTER")) {
                        textFielBuscarItem.requestFocus();
                    }
                }
        );
        textFielBuscarItem.setOnAction(e-> buttonBuscar.requestFocus());

        tableViewTablaBuscarItem.setItems(listaInventario);
        textFielBuscarItem.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.trim().isEmpty()) {
                tableViewTablaBuscarItem.setItems(listaInventario);
            }
        });

    }

    // ============= TAB ============
    private void configurarTabulacion() {
        configurarTab(datePickerFechaEmision, choiseBoxMotivo);
        configurarTab(choiseBoxMotivo, textFieldResponsable);
        configurarTab(textFieldResponsable, textFieldDestino);
        configurarTab(textFieldDestino, textAreaObservaciones);
        configurarTab(textAreaObservaciones, textFielBuscarItem);
        configurarTab(textFielBuscarItem, buttonBuscar);
    }

    private void configurarTab(Control actual, Control siguiente) {
        actual.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.TAB) {
                siguiente.requestFocus();
                event.consume();
            }
        });
    }

    // =========================
    // DATOS DE PRUEBA
    // =========================
    private void cargarDatosInventario() {
        listaInventario.add(new ItemInventario("IT-001", "Martillo", 10));
        listaInventario.add(new ItemInventario("IT-002", "Taladro", 5));
        listaInventario.add(new ItemInventario("IT-003", "Llave inglesa", 15));
    }

    // =========================
    // BUSCAR ÍTEM
    // =========================
    @FXML
    private void buscarItem(ActionEvent event) {

        String texto = textFielBuscarItem.getText();

        if (texto == null || texto.trim().isEmpty()) {
            tableViewTablaBuscarItem.setItems(listaInventario);
            return;
        }

        texto = normalizarTexto(texto);

        ObservableList<ItemInventario> filtrado =
                FXCollections.observableArrayList();

        for (ItemInventario item : listaInventario) {

            String nombre = normalizarTexto(item.getNombre());
            String codigo = normalizarTexto(item.getCodigo());

            if (nombre.contains(texto) || codigo.contains(texto)) {
                filtrado.add(item);
            }
        }

        if (filtrado.isEmpty()) {
            mostrarAlerta(
                    "Sin resultados",
                    "No se encontraron ítems que coincidan con la búsqueda.",
                    Alert.AlertType.INFORMATION
            );
        }

        // Ordenar por nombre
        filtrado.sort(Comparator.comparing(ItemInventario::getNombre));

        tableViewTablaBuscarItem.setItems(filtrado);
    }


    // =========================
    // AGREGAR ÍTEM
    // =========================
    @FXML
    private void agregarItem(ActionEvent event) {

        ItemInventario seleccionado =
                tableViewTablaBuscarItem.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Aviso",
                    "Seleccione un ítem",
                    Alert.AlertType.WARNING);
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cantidad");
        dialog.setHeaderText("Ingrese la cantidad a retirar");

        dialog.showAndWait().ifPresent(valor -> {
            try {
                int cantidad = Integer.parseInt(valor);

                if (cantidad <= 0 || cantidad > seleccionado.getStock()) {
                    mostrarAlerta("Cantidad inválida",
                            "la cantidad debe ser mayor a 0",
                            Alert.AlertType.ERROR);
                    return;
                }

                ItemInventario nuevo = new ItemInventario(
                        seleccionado.getCodigo(),
                        seleccionado.getNombre(),
                        seleccionado.getStock()
                );
                nuevo.setCantidad(cantidad);

                listaSeleccionados.add(nuevo);

            } catch (NumberFormatException e) {
                mostrarAlerta("Aviso",
                        "Ingrese solo números",
                        Alert.AlertType.ERROR);
            }
        });
    }

    // =========================
    // QUITAR ÍTEM
    // =========================
    @FXML
    private void quitarItem(ActionEvent event) {

        ItemInventario seleccionado =
                tableViewTablaItemSeleccionado.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            listaSeleccionados.remove(seleccionado);
        }
    }

    // =========================
    // GUARDAR ORDEN
    // =========================
    @FXML
    private void guardarOrden(ActionEvent event) {

        if (listaSeleccionados.isEmpty()) {
            mostrarAlerta("Error en la selección",
                    "Debe agregar al menos un ítem",
                    Alert.AlertType.WARNING);
            return;
        }

        if(!validarCampos()){
            return;
        }

        // Aquí iría:
        // - Guardar orden
        // - Guardar detalle
        // - Actualizar stock

        mostrarAlerta("Aviso",
                "Orden registrada correctamente",
                Alert.AlertType.INFORMATION);
        limpiarFormulario();
    }

    @FXML
    private void cancelarOrden(ActionEvent event) {
        limpiarFormulario();
    }

    // =========================
    // UTILIDADES
    // =========================
    private void limpiarFormulario() {
        textFieldDestino.clear();
        textAreaObservaciones.clear();
        listaSeleccionados.clear();
    }

    private boolean validarCampos() {

        //Responsable
        if (textFieldResponsable.getText() == null) {
            mostrarAlerta("Validación",
                    "Debe seleccionar un responsable",
                    Alert.AlertType.WARNING);
            return false;
        }

        //Motivo
        if (choiseBoxMotivo.getValue() == null) {
            mostrarAlerta("Validación",
                    "Debe seleccionar un motivo de salida",
                    Alert.AlertType.WARNING);
            return false;
        }

        //Fecha
        if (datePickerFechaEmision.getValue() == null) {
            mostrarAlerta("Validación",
                    "Debe seleccionar la fecha de emisión",
                    Alert.AlertType.WARNING);
            return false;
        }

        // Destino
        if (textFieldDestino.getText() == null ||
                textFieldDestino.getText().trim().isEmpty()) {

            mostrarAlerta("Validación",
                    "El destino no puede estar vacío",
                    Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("movimientosInventario.fxml");
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private String normalizarTexto(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase()
                .trim();
    }

}
