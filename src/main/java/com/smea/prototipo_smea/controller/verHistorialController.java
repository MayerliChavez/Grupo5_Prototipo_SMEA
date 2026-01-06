package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.HistorialMantenimiento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class verHistorialController implements Initializable, ControladorInyectable {

    private MainController mainController;

    @FXML private TableView<HistorialMantenimiento> tablaHistorial;
    @FXML private TableColumn<HistorialMantenimiento, String> colCodigo;
    @FXML private TableColumn<HistorialMantenimiento, String> colFecha;
    @FXML private TableColumn<HistorialMantenimiento, String> colTipo;
    @FXML private TableColumn<HistorialMantenimiento, String> colDescripcion;
    @FXML private TableColumn<HistorialMantenimiento, String> colResponsable;
    @FXML private TableColumn<HistorialMantenimiento, Integer> colCantidad;
    @FXML private TableColumn<HistorialMantenimiento, String> colEstadoPrioridad;

    private ObservableList<HistorialMantenimiento> listaHistorial = FXCollections.observableArrayList();

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Vincular columnas con propiedades del modelo
        colCodigo.setCellValueFactory(data -> data.getValue().codigoProperty());
        colFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        colTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        colDescripcion.setCellValueFactory(data -> data.getValue().descripcionProperty());
        colResponsable.setCellValueFactory(data -> data.getValue().responsableProperty());
        colCantidad.setCellValueFactory(data -> data.getValue().cantidadProperty().asObject());
        colEstadoPrioridad.setCellValueFactory(data -> data.getValue().estadoPrioridadProperty());

        // Cargar datos iniciales (puede ser de BD)
        cargarDatosEjemplo();
    }

    private void cargarDatosEjemplo() {
        listaHistorial.add(new HistorialMantenimiento("ORD001","2026-01-05","Falla","Motor no arranca","Juan","0","Alta"));
        listaHistorial.add(new HistorialMantenimiento("ORD002","2026-01-04","Repuesto","Filtro de aceite","Carlos","2","-"));
        listaHistorial.add(new HistorialMantenimiento("ORD003","2026-01-03","Diagnóstico","Revisión sistema eléctrico","Ana","0","Finalizado"));

        tablaHistorial.setItems(listaHistorial);
    }

    @FXML
    private void clickActualizar(ActionEvent event) {
        // Aquí se puede recargar la tabla desde la base de datos
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Tabla actualizada", ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("controlMantenimiento.fxml");
    }
}
