package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.ElementoCatalogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class buscarElementoCategoriaController implements Initializable,
ControladorInyectable{
    // ================== CONTROLADOR PRINCIPAL ==================
    private MainController mainController;

    // =============================
    // 🔹 FXML
    // =============================
    @FXML private TableView<ElementoCatalogo> tableViewCatalogo;

    @FXML private TableColumn<ElementoCatalogo, String> tableColumnCodigo;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnNombre;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnTipoElemento;
    @FXML private TableColumn<ElementoCatalogo, Integer> tableColumnCantidad;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnCategoria;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnDescripcion;
    @FXML private TableColumn<ElementoCatalogo, String> tableColumnEstado;

    @FXML private ChoiceBox<String> choiceBoxCategoria;
    @FXML private Button buttonBuscarCategoria;
    @FXML private Button buttonLimpiar;


    // =============================
    // Datos en memoria
    // =============================
    private final ObservableList<ElementoCatalogo> listaElementos = FXCollections.observableArrayList();
    private final ObservableList<ElementoCatalogo> listaOriginal = FXCollections.observableArrayList();

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // =============================
    // Inicialización
    // =============================
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Columnas
        tableColumnCodigo.setCellValueFactory(c -> c.getValue().codigoProperty());
        tableColumnNombre.setCellValueFactory(c -> c.getValue().nombreProperty());
        tableColumnTipoElemento.setCellValueFactory(c -> c.getValue().tipoElementoProperty());
        tableColumnCantidad.setCellValueFactory(c -> c.getValue().cantidadProperty().asObject());
        tableColumnCategoria.setCellValueFactory(c -> c.getValue().categoriaProperty());
        tableColumnDescripcion.setCellValueFactory(c -> c.getValue().descripcionProperty());
        tableColumnEstado.setCellValueFactory(c -> c.getValue().estadoProperty());

        // Categorías disponibles
        choiceBoxCategoria.setItems(FXCollections.observableArrayList(
                "Eléctricos",
                "Herramienta",
                "Materiales de Construcción"
        ));

        // Carga simulada
        cargarElementosMemoria();

        tableViewCatalogo.setItems(listaElementos);
    }

    // =============================
    //  Buscar por categoría
    // =============================
    @FXML
    private void buscarElemento(ActionEvent  event) {

        String categoriaSeleccionada = choiceBoxCategoria.getValue();

        //  Validación
        if (categoriaSeleccionada == null) {
            mostrarAlerta("Debe seleccionar una categoría.");
            tableViewCatalogo.setItems(listaElementos); // NO vacía
            return;
        }

        ObservableList<ElementoCatalogo> filtrados = FXCollections.observableArrayList();

        for (ElementoCatalogo e : listaOriginal) {
            if (e.getCategoria().equalsIgnoreCase(categoriaSeleccionada)) {
                filtrados.add(e);
            }
        }

        //  No hay resultados
        if (filtrados.isEmpty()) {
            mostrarAlerta("No se encontraron elementos para la categoría seleccionada.");
            tableViewCatalogo.setItems(listaElementos);
            return;
        }

        tableViewCatalogo.setItems(filtrados);
    }

    @FXML
    private void limpiar(ActionEvent event) {
        choiceBoxCategoria.getSelectionModel().clearSelection();
        tableViewCatalogo.setItems(listaOriginal);
        tableViewCatalogo.getSelectionModel().clearSelection();
    }

    // =============================
    //  Carga en memoria
    // =============================
    private void cargarElementosMemoria() {

        listaOriginal.add(new ElementoCatalogo(
                        "IR-002", "Llave", "Herramienta", 6,
                        "Útil para tornillar tuercas", "Bodega A", "2026-01-02", "Herramienta","DISPONIBLE"));

        listaOriginal.add(new ElementoCatalogo(
                "CAT-002", "Elevador", "Herramienta", 10,
                "Útil para elevar vehículos", "Bodega B", "2026-01-02", "Eléctricos","AGOTADO"));

        listaOriginal.add(new ElementoCatalogo(
                "CAT-003", "Filtro", "Repuesto", 120,
                "Repuesto para mantenimiento", "Bodega A", "2026-01-02", "Materiales de Construcción","CASI AGOTADO")
        );

        listaElementos.setAll(listaOriginal);
    }

    @FXML
    private void regresarMenu(ActionEvent event) {
        mainController.saver("inventarioGeneral.fxml");
    }

    // =============================
    //  Alertas
    // =============================
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
