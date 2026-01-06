package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.ServicioReporte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class reportesOperativosController implements ControladorInyectable, Initializable {

    private MainController mainController;

    // ===== FXML =====
    @FXML private DatePicker dateInicio;
    @FXML private DatePicker dateFin;
    @FXML private ComboBox<String> comboTipoServicio;
    @FXML private ComboBox<String> comboEstado;

    @FXML private TableView<ServicioReporte> tablaServicios;
    @FXML private TableColumn<ServicioReporte, String> colCodigo;
    @FXML private TableColumn<ServicioReporte, String> colFecha;
    @FXML private TableColumn<ServicioReporte, String> colTipo;
    @FXML private TableColumn<ServicioReporte, String> colDescripcion;
    @FXML private TableColumn<ServicioReporte, String> colResponsable;
    @FXML private TableColumn<ServicioReporte, String> colEstado;

    // ===== LISTAS =====
    private ObservableList<ServicioReporte> listaServicios;
    private FilteredList<ServicioReporte> listaFiltrada;

    @Override
    public void setMainController(MainController mainController) {
            this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Configurar columnas
        colCodigo.setCellValueFactory(data -> data.getValue().codigoProperty());
        colFecha.setCellValueFactory(data -> data.getValue().fechaProperty().asString());
        colTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        colDescripcion.setCellValueFactory(data -> data.getValue().descripcionProperty());
        colResponsable.setCellValueFactory(data -> data.getValue().responsableProperty());
        colEstado.setCellValueFactory(data -> data.getValue().estadoProperty());

        // Cargar combos
        comboTipoServicio.setItems(FXCollections.observableArrayList(
                "Preventivo", "Correctivo", "Otro"
        ));

        comboEstado.setItems(FXCollections.observableArrayList(
                "Pendiente", "En Proceso", "Finalizado"
        ));

        // Datos de ejemplo (luego vienen de BD)
        listaServicios = FXCollections.observableArrayList(
                new ServicioReporte("ORD001", LocalDate.of(2026,1,5), "Preventivo",
                        "Revisión general motor", "Juan", "Finalizado"),
                new ServicioReporte("ORD002", LocalDate.of(2026,1,4), "Correctivo",
                        "Cambio de filtro", "Carlos", "En Proceso"),
                new ServicioReporte("ORD003", LocalDate.of(2026,1,3), "Preventivo",
                        "Limpieza sistema", "Ana", "Pendiente")
        );

        // Lista filtrable
        listaFiltrada = new FilteredList<>(listaServicios, p -> true);
        tablaServicios.setItems(listaFiltrada);
    }

    // ===== LOGICA DE FILTRADO =====
    private void aplicarFiltros() {

        listaFiltrada.setPredicate(servicio -> {

            // Filtro por fecha inicio
            if (dateInicio.getValue() != null) {
                if (servicio.getFecha().isBefore(dateInicio.getValue())) {
                    return false;
                }
            }

            // Filtro por fecha fin
            if (dateFin.getValue() != null) {
                if (servicio.getFecha().isAfter(dateFin.getValue())) {
                    return false;
                }
            }

            // Filtro por tipo de servicio
            if (comboTipoServicio.getValue() != null) {
                if (!servicio.getTipo().equals(comboTipoServicio.getValue())) {
                    return false;
                }
            }

            // Filtro por estado
            if (comboEstado.getValue() != null) {
                if (!servicio.getEstado().equals(comboEstado.getValue())) {
                    return false;
                }
            }

            return true; // pasa todos los filtros
        });
    }

    // ===== EVENTOS =====
    @FXML
    private void clickGenerar(ActionEvent event) {
        aplicarFiltros();
    }

    @FXML
    private void clickLimpiar(ActionEvent event) {
        dateInicio.setValue(null);
        dateFin.setValue(null);
        comboTipoServicio.setValue(null);
        comboEstado.setValue(null);

        listaFiltrada.setPredicate(p -> true);
    }

    @FXML
    private void clickExportar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Exportación simulada.\n(Se implementa luego PDF/Excel)",
                ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("moduloReportes.fxml");
    }
}
