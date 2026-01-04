package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.ElementoCatalogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;

import java.net.URL;
import java.util.ResourceBundle;

public class asignarCategoriaController implements Initializable, ControladorInyectable {

    // ================== CONTROLADOR PRINCIPAL ==================
    private MainController mainController;

    // ================== FXML ==================
    @FXML private TextField textFielBuscarItem;
    @FXML private Button buttonBuscarItem;
    @FXML private Button buttonAsignarCategoria;
    @FXML private Button buttonLimpiar;

    @FXML private TableView<ElementoCatalogo> tableViewTablaContenido;

    @FXML private TableColumn<ElementoCatalogo, String> tableColumnCodigo;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnNombre;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnDescripcion;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnCategoriaActual;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnEstado;
    @FXML private TableColumn<ElementoCatalogo, Boolean> tableColumnSeleccionar;


    @FXML private ChoiceBox<String> choiseBoxCategoria;

    // ================== DATOS EN MEMORIA ==================
    private final ObservableList<ElementoCatalogo> listaOriginal =
            FXCollections.observableArrayList();

    private final ObservableList<ElementoCatalogo> listaItems =
            FXCollections.observableArrayList();

    // ================== INYECCIÓN ==================
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ================== INITIALIZE ==================
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableViewTablaContenido.setEditable(true);
        tableColumnSeleccionar.setEditable(true);

        // ---- Columnas
        tableColumnCodigo.setCellValueFactory(c -> c.getValue().codigoProperty());
        tableColumnNombre.setCellValueFactory(c -> c.getValue().nombreProperty());
        tableColumnDescripcion.setCellValueFactory(c -> c.getValue().descripcionProperty());
        tableColumnCategoriaActual.setCellValueFactory(c -> c.getValue().categoriaProperty());
        tableColumnEstado.setCellValueFactory(c -> c.getValue().estadoProperty());
        tableColumnSeleccionar.setCellValueFactory(c ->
                c.getValue().seleccionadoProperty());

        tableColumnSeleccionar.setCellFactory(CheckBoxTableCell.forTableColumn(tableColumnSeleccionar));


        // ---- Selección múltiple
        tableViewTablaContenido.getSelectionModel()
                .setSelectionMode(SelectionMode.MULTIPLE);

        // ---- Categorías
        choiseBoxCategoria.setItems(FXCollections.observableArrayList(
                "Eléctricos",
                "Herramientas",
                "Materiales de Construcción"
        ));

        // ---- Carga inicial
        cargarItemsMemoria();
        listaItems.setAll(listaOriginal);
        tableViewTablaContenido.setItems(listaItems);

        // ---- Enter ejecuta búsqueda
        textFielBuscarItem.setOnAction(e -> buscarItem(null));
    }

    // ================== BUSCAR ==================
    @FXML
    private void buscarItem(ActionEvent event) {

        String texto = textFielBuscarItem.getText();

        if (texto == null || texto.trim().length() < 2) {
            mostrarAlerta("Ingrese al menos 2 caracteres para buscar.");
            restaurarTabla();
            return;
        }

        ObservableList<ElementoCatalogo> filtrados = FXCollections.observableArrayList();

        for (ElementoCatalogo item : listaOriginal) {
            if (item.getCodigo().toLowerCase().contains(texto.toLowerCase()) ||
                    item.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                filtrados.add(item);
            }
        }

        if (filtrados.isEmpty()) {
            mostrarAlerta("No se encontraron ítems.");
            restaurarTabla();
            return;
        }

        tableViewTablaContenido.setItems(filtrados);
    }

    // ================== ASIGNAR CATEGORÍA ==================
    @FXML
    private void asignarCategoria(ActionEvent event) {
        String categoria = choiseBoxCategoria.getValue();

        ObservableList<ElementoCatalogo> seleccionados = FXCollections.observableArrayList();

        for (ElementoCatalogo item : tableViewTablaContenido.getItems()) {
            if (item.isSeleccionado()) {
                seleccionados.add(item);
            }
        }

        if (seleccionados.isEmpty()) {
            mostrarAlerta("Debe seleccionar al menos un ítem.");
            return;
        }

        if (categoria == null) {
            mostrarAlerta("Debe seleccionar una categoría.");
            return;
        }

        for (ElementoCatalogo item : seleccionados) {
            item.setCategoria(categoria);
        }

        tableViewTablaContenido.refresh();
        limpiarSeleccion();
        choiseBoxCategoria.getSelectionModel().clearSelection();
        textFielBuscarItem.clear();
        restaurarTabla();

        mostrarInfo("Categoría asignada correctamente.");
    }

    // ================== LIMPIAR ==================
    @FXML
    private void limpiarMenu(ActionEvent event) {
        textFielBuscarItem.clear();
        choiseBoxCategoria.getSelectionModel().clearSelection();
        restaurarTabla();
        tableViewTablaContenido.getSelectionModel().clearSelection();
        limpiarSeleccion();
    }

    private void limpiarSeleccion() {
        for (ElementoCatalogo item : listaOriginal) {
            item.setSeleccionado(false);
        }
        tableViewTablaContenido.refresh();
    }


    private void restaurarTabla() {
        listaItems.setAll(listaOriginal);
        tableViewTablaContenido.setItems(listaItems);
    }

    // ================== NAVEGACIÓN ==================
    @FXML
    private void regresarMenu(ActionEvent event) {
        mainController.saver("catalogoInventario.fxml");
    }

    // ================== DATOS SIMULADOS ==================
    private void cargarItemsMemoria() {

        listaOriginal.add(new ElementoCatalogo(
                "IR-002", "Llave", "Herramienta", 6,
                "Útil para tornillar tuercas",
                "Bodega A", "2026-01-02",
                "Herramientas", "DISPONIBLE"));

        listaOriginal.add(new ElementoCatalogo(
                "CAT-002", "Elevador", "Herramienta", 10,
                "Útil para elevar vehículos",
                "Bodega B", "2026-01-02",
                "Eléctricos", "AGOTADO"));

        listaOriginal.add(new ElementoCatalogo(
                "CAT-003", "Filtro", "Repuesto", 120,
                "Repuesto para mantenimiento",
                "Bodega A", "2026-01-02",
                "Materiales de Construcción", "CASI AGOTADO"));
    }

    // ================== ALERTAS ==================
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
