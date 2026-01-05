package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.MovimientoInventario;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class movimientoInventarioController
        implements Initializable, ControladorInyectable {

    private MainController mainController;

    @FXML private TableView<MovimientoInventario> tablaMovimientos;

    @FXML private TableColumn<MovimientoInventario,String> colUsuario;
    @FXML private TableColumn<MovimientoInventario,String> colRol;
    @FXML private TableColumn<MovimientoInventario,String> colProducto;
    @FXML private TableColumn<MovimientoInventario,String> colTipo;
    @FXML private TableColumn<MovimientoInventario,Integer> colCantidad;
    @FXML private TableColumn<MovimientoInventario,String> colAccion;
    @FXML private TableColumn<MovimientoInventario,String> colFecha;
    @FXML private TableColumn<MovimientoInventario,String> colDetalle;

    @FXML private TextField txtProducto;
    @FXML private TextField txtUsuario;
    @FXML private ChoiceBox<String> choiceTipo;
    @FXML private DatePicker dateFecha;

    private ObservableList<MovimientoInventario> listaMovimientos;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        configurarChoiceBox();
        cargarDatos();
    }

    private void configurarTabla() {
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colAccion.setCellValueFactory(new PropertyValueFactory<>("accion"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colDetalle.setCellValueFactory(new PropertyValueFactory<>("detalle"));
    }

    private void configurarChoiceBox() {
        choiceTipo.setItems(FXCollections.observableArrayList(
                "ENTRADA", "SALIDA", "AJUSTE", "DEVOLUCIÓN"
        ));
    }
    private void cargarDatos() {
        listaMovimientos = FXCollections.observableArrayList(
                new MovimientoInventario(
                        "admin", "Administrador", "Taladro",
                        "ENTRADA", 10, "INSERT",
                        LocalDate.of(2026, 1, 5), "Ingreso inicial"
                ),
                new MovimientoInventario(
                        "bodeguero", "Operador", "Guantes",
                        "SALIDA", 5, "UPDATE",
                        LocalDate.of(2026, 1, 5), "Entrega a mantenimiento"
                )
        );
    }

    @FXML
    private void buscarMovimientos() {
        if (txtProducto.getText().isBlank()
                && txtUsuario.getText().isBlank()
                && choiceTipo.getValue() == null
                && dateFecha.getValue() == null) {

            alerta("Filtros vacíos",
                    "Debe ingresar al menos un filtro",
                    Alert.AlertType.WARNING);
            return;
        }

        alerta("Búsqueda",
                "Búsqueda realizada correctamente",
                Alert.AlertType.INFORMATION);
    }

    @FXML
    private void limpiarFiltros() {
        txtProducto.clear();
        txtUsuario.clear();
        choiceTipo.setValue(null);
        dateFecha.setValue(null);
        tablaMovimientos.setItems(listaMovimientos);
    }

    @FXML
    private void exportar() {
        alerta("Exportar",
                "Exportación pendiente",
                Alert.AlertType.INFORMATION);
    }

    @FXML
    private void regresar() {
        mainController.saver("registroAuditoria.fxml");
    }

    private void alerta(String t, String m, Alert.AlertType tipo) {
        Alert a = new Alert(tipo);
        a.setTitle(t);
        a.setHeaderText(null);
        a.setContentText(m);
        a.showAndWait();
    }
}
