package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.ElementoCatalogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class buscarElementoCodigoController
        implements Initializable, ControladorInyectable {

    // ================== CONTROLADOR PRINCIPAL ==================
    private MainController mainController;

    // ================== TABLA ==================
    @FXML private TableView<ElementoCatalogo> tableViewCatalogo;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnCodigo;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnNombre;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnTipoElemento;
    @FXML private TableColumn<ElementoCatalogo, Integer> tableColumnCantidad;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnCategoria;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnDescripcion;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnEstado;

    // ================== CAMPOS ==================
    @FXML private TextField textFieldNombreCliente;

    // ================== DATOS ==================
    private ObservableList<ElementoCatalogo> listaCatalogo =
            FXCollections.observableArrayList();

    // ================== INYECCIÓN ==================
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ================== INIT ==================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarTabla();
        cargarDatosPrueba();
    }

    // ================== CONFIGURAR TABLA ==================
    private void configurarTabla() {
        tableColumnCodigo.setCellValueFactory(d -> d.getValue().codigoProperty());
        tableColumnNombre.setCellValueFactory(d -> d.getValue().nombreProperty());
        tableColumnTipoElemento.setCellValueFactory(d -> d.getValue().tipoElementoProperty());
        tableColumnCantidad.setCellValueFactory(d -> d.getValue().cantidadProperty().asObject());
        tableColumnCategoria.setCellValueFactory(d -> d.getValue().categoriaProperty());
        tableColumnDescripcion.setCellValueFactory(d -> d.getValue().descripcionProperty());
        tableColumnEstado.setCellValueFactory(d -> d.getValue().estadoProperty());
    }

    // ================== DATOS DE PRUEBA ==================
    private void cargarDatosPrueba() {
        listaCatalogo.add(new ElementoCatalogo(
                "IR-002", "Llave", "Herramienta", 6,
                "Útil para tornillar tuercas", "Bodega A", "2026-01-02", "Herramienta","DISPONIBLE"));

        listaCatalogo.add(new ElementoCatalogo(
                "CAT-002", "Elevador", "Herramienta", 10,
                "Útil para elevar vehículos", "Bodega B", "2026-01-02", "Eléctricos","AGOTADO"));

        listaCatalogo.add(new ElementoCatalogo(
                "CAT-003", "Filtro", "Repuesto", 120,
                "Repuesto para mantenimiento", "Bodega A", "2026-01-02", "Materiales de Construcción","CASI AGOTADO"));
        tableViewCatalogo.setItems(listaCatalogo);
    }

    // ================== BUSCAR POR CÓDIGO ==================
    @FXML
    private void buscarElemento(ActionEvent event) {

        String codigo = textFieldNombreCliente.getText();

        if (codigo == null || codigo.trim().isEmpty()) {
            mostrarAlerta(
                    "Aviso",
                    "Debe ingresar un código para realizar la búsqueda.",
                    Alert.AlertType.WARNING
            );
            return;
        }

        ObservableList<ElementoCatalogo> filtrado =
                FXCollections.observableArrayList();

        for (ElementoCatalogo e : listaCatalogo) {
            if (e.getCodigo().equalsIgnoreCase(codigo.trim())) {
                filtrado.add(e);
            }
        }

        if (filtrado.isEmpty()) {
            mostrarAlerta(
                    "Sin resultados",
                    "No se encontró ningún elemento con el código ingresado.",
                    Alert.AlertType.INFORMATION
            );
            tableViewCatalogo.setItems(listaCatalogo);
            return;
        }

        tableViewCatalogo.setItems(filtrado);
    }

    // ================== REGRESAR ==================
    @FXML
    private void regresarModuloInventario(ActionEvent event) {
        mainController.saver("inventarioGeneral.fxml");
    }

    // ================== ALERTA GENERAL ==================
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
