package com.smea.prototipo_smea.controller;

import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class movimientoMantenimientoController
        implements Initializable, ControladorInyectable {

    private MainController mainController;

    @FXML private TableView<MovimientoMantenimiento> tablaMovimientos;

    @FXML private TableColumn<MovimientoMantenimiento,String> colUsuario;
    @FXML private TableColumn<MovimientoMantenimiento,String> colRol;
    @FXML private TableColumn<MovimientoMantenimiento,String> colEquipo;
    @FXML private TableColumn<MovimientoMantenimiento,String> colTipo;
    @FXML private TableColumn<MovimientoMantenimiento,String> colAccion;
    @FXML private TableColumn<MovimientoMantenimiento,String> colEstado;
    @FXML private TableColumn<MovimientoMantenimiento,String> colFecha;
    @FXML private TableColumn<MovimientoMantenimiento,String> colObservacion;

    @FXML private TextField txtEquipo;
    @FXML private TextField txtUsuario;
    @FXML private ChoiceBox<String> choiceTipo;
    @FXML private DatePicker dateFecha;

    private ObservableList<MovimientoMantenimiento> listaMovimientos;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        configurarChoiceBox();
        cargarDatos();

        txtEquipo.setOnAction(e-> choiceTipo.requestFocus());
        choiceTipo.setOnAction(e-> txtUsuario.requestFocus());
        txtUsuario.setOnAction(e-> dateFecha.requestFocus());
    }

    private void configurarTabla() {
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        colEquipo.setCellValueFactory(new PropertyValueFactory<>("equipo"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colAccion.setCellValueFactory(new PropertyValueFactory<>("accion"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colObservacion.setCellValueFactory(new PropertyValueFactory<>("observacion"));
    }

    private void configurarChoiceBox() {
        choiceTipo.setItems(FXCollections.observableArrayList(
                "PREVENTIVO", "CORRECTIVO"
        ));
    }

    private void cargarDatos() {
        listaMovimientos = FXCollections.observableArrayList(
                new MovimientoMantenimiento(
                        "admin","Administrador","Compresor",
                        "PREVENTIVO","CREAR","PROGRAMADO",
                        "2026-01-06 08:30",
                        "Orden de mantenimiento creada"
                ),
                new MovimientoMantenimiento(
                        "tecnico1","Técnico","Compresor",
                        "PREVENTIVO","FINALIZAR","COMPLETADO",
                        "2026-01-06 11:45",
                        "Mantenimiento realizado con éxito"
                )
        );

        tablaMovimientos.setItems(listaMovimientos);
    }

    @FXML
    private void buscarMovimientos() {
        if (txtEquipo.getText().isBlank()
                && txtUsuario.getText().isBlank()
                && choiceTipo.getValue() == null
                && dateFecha.getValue() == null) {

            alerta("Filtros vacíos",
                    "Debe ingresar al menos un filtro",
                    Alert.AlertType.WARNING);
            return;
        }

        alerta("Búsqueda",
                "Movimientos filtrados correctamente",
                Alert.AlertType.INFORMATION);
    }

    @FXML
    private void limpiarFiltros() {
        txtEquipo.clear();
        txtUsuario.clear();
        choiceTipo.setValue(null);
        dateFecha.setValue(null);
        tablaMovimientos.setItems(listaMovimientos);
    }

    @FXML
    private void exportar() {
        alerta("Exportar",
                "Funcionalidad de exportación pendiente",
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
