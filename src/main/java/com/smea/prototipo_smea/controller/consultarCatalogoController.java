package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.ElementoCatalogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.JOptionPane;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class consultarCatalogoController implements Initializable, ControladorInyectable {

    // ================== CONTROLADOR PRINCIPAL ==================
    private MainController mainController;

    // ================== TABLA ==================
    @FXML private TableView<ElementoCatalogo> tableViewCatalogo;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnCodigo;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnNombre;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnTipoElemento;
    @FXML private TableColumn<ElementoCatalogo, Integer> tableColumnCantidad;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnEstado;

    // ================== CAMPOS ==================
    @FXML private TextField textFieldNombreCliente;
    @FXML private ChoiceBox<String> choiseBoxTipoElemento;
    @FXML private ChoiceBox<String> choiceBoxBusqueda;

    // ================== BOTONES ==================
    @FXML private Button buttonBuscarTipo;
    @FXML private Button buttonBuscarNombre;

    // ================== DATOS ==================
    private final ObservableList<ElementoCatalogo> listaCatalogo =
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
        cargarChoiceBox();
        configurarBusqueda();

        textFieldNombreCliente.setOnAction(e -> buttonBuscarNombre.requestFocus());
        choiseBoxTipoElemento.setOnAction(e -> buttonBuscarTipo.requestFocus());

        ConfigurarTab();
    }

    public void ConfigurarTab(){
        configurarTab(textFieldNombreCliente, buttonBuscarNombre );
        configurarTab(choiseBoxTipoElemento, buttonBuscarTipo);
    }

    private void configurarTab(Control actual, Control siguiente) {
        actual.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.TAB) {
                siguiente.requestFocus();
                event.consume();
            }
        });
    }
    // ================== CONFIGURAR TABLA ==================
    private void configurarTabla() {
        tableColumnCodigo.setCellValueFactory(d -> d.getValue().codigoProperty());
        tableColumnNombre.setCellValueFactory(d -> d.getValue().nombreProperty());
        tableColumnTipoElemento.setCellValueFactory(d -> d.getValue().tipoElementoProperty());
        tableColumnCantidad.setCellValueFactory(d -> d.getValue().cantidadProperty().asObject());
        tableColumnEstado.setCellValueFactory(d -> d.getValue().estadoProperty());

        tableViewCatalogo.setItems(listaCatalogo);
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

    // ================== CHOICEBOX ==================
    private void cargarChoiceBox() {

        choiseBoxTipoElemento.setItems(
                FXCollections.observableArrayList("Repuesto", "Herramienta")
        );

        choiceBoxBusqueda.setItems(
                FXCollections.observableArrayList("Nombre", "Tipo")
        );

        choiceBoxBusqueda.getSelectionModel().selectFirst();
    }

    // ================== CONFIGURAR BUSQUEDA ==================
    private void configurarBusqueda() {

        // Estado inicial
        textFieldNombreCliente.setDisable(false);
        choiseBoxTipoElemento.setDisable(true);
        buttonBuscarNombre.setDisable(false);
        buttonBuscarTipo.setDisable(true);

        choiceBoxBusqueda.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {

                    if ("Nombre".equals(newVal)) {
                        textFieldNombreCliente.setDisable(false);
                        buttonBuscarNombre.setDisable(false);

                        choiseBoxTipoElemento.setDisable(true);
                        buttonBuscarTipo.setDisable(true);

                    } else {
                        choiseBoxTipoElemento.setDisable(false);
                        buttonBuscarTipo.setDisable(false);

                        textFieldNombreCliente.setDisable(true);
                        buttonBuscarNombre.setDisable(true);
                    }
                });
    }

    // ================== BUSCAR ==================
    @FXML
    private void buscarProducto(ActionEvent event) {

        ObservableList<ElementoCatalogo> filtrado =
                FXCollections.observableArrayList();

        String modo = choiceBoxBusqueda.getValue();
        String nombre = textFieldNombreCliente.getText();
        String tipo = choiseBoxTipoElemento.getValue();

        if ("Nombre".equals(modo)) {

            if (nombre == null || nombre.trim().isEmpty()) {
                mostrarAlerta("Aviso",
                        "Ingrese un nombre para buscar",
                        Alert.AlertType.WARNING);
                return;
            }

            for (ElementoCatalogo e : listaCatalogo) {
                if (e.getNombre().toLowerCase()
                        .contains(nombre.toLowerCase())) {
                    filtrado.add(e);
                }
            }

        } else {

            if (tipo == null) {
                mostrarAlerta("Aviso",
                        "Seleccione un tipo de elemento",
                        Alert.AlertType.WARNING);
                return;
            }

            for (ElementoCatalogo e : listaCatalogo) {
                if (e.getTipoElemento().equalsIgnoreCase(tipo)) {
                    filtrado.add(e);
                }
            }
        }

        if (filtrado.isEmpty()) {
            mostrarAlerta("Sin resultados",
                    "No se encontraron elementos",
                    Alert.AlertType.INFORMATION);
            tableViewCatalogo.setItems(listaCatalogo);
            return;
        }

        tableViewCatalogo.setItems(filtrado);
    }

    // ================== VER DETALLE ==================
    @FXML
    private void verDetalleProducto(ActionEvent event) {

        ElementoCatalogo e =
                tableViewCatalogo.getSelectionModel().getSelectedItem();

        if (e == null) {
            mostrarAlerta("Aviso",
                    "Seleccione un elemento",
                    Alert.AlertType.WARNING);
            return;
        }

        JOptionPane.showMessageDialog(
                null,
                "Código: " + e.getCodigo() +
                        "\nNombre: " + e.getNombre() +
                        "\nTipo: " + e.getTipoElemento() +
                        "\nCantidad: " + e.getCantidad() +
                        "\nDescripcion: " + e.getDescripcion() +
                        "\nFecha de modificacion: " + e.getFechaModificacion() +
                        "\nEstado: " + e.getEstado(),
                "Detalle del Elemento",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ================== CAMBIAR ESTADO ==================
    @FXML
    private void cambiarEstadoElemento(ActionEvent event) {

        ElementoCatalogo e =
                tableViewCatalogo.getSelectionModel().getSelectedItem();

        if (e == null) {
            mostrarAlerta("Aviso",
                    "Seleccione un elemento",
                    Alert.AlertType.WARNING);
            return;
        }

        List<String> estados =
                List.of("DISPONIBLE", "CASI AGOTADO", "AGOTADO", "DADO DE BAJA");

        ChoiceDialog<String> dialog =
                new ChoiceDialog<>(e.getEstado(), estados);

        dialog.setTitle("Cambiar estado");
        dialog.setHeaderText(null);
        dialog.setContentText("Seleccione el nuevo estado:");

        dialog.showAndWait().ifPresent(nuevoEstado -> {
            e.setEstado(nuevoEstado);
            tableViewCatalogo.refresh();
        });
    }

    // ================== REGRESAR ==================
    @FXML
    private void regresarModuloInventario(ActionEvent event) {
        mainController.saver("catalogoInventario.fxml");
    }

    // ================== ALERTA ==================
    private void mostrarAlerta(String t, String m, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(t);
        alert.setHeaderText(null);
        alert.setContentText(m);
        alert.showAndWait();
    }
}
