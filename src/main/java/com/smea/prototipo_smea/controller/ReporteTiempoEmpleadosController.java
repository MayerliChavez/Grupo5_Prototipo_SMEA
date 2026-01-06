package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.RegistroTiempo1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReporteTiempoEmpleadosController implements Initializable, ControladorInyectable {

    private MainController mainController;

    // ================== FXML ==================

    @FXML private DatePicker datePickerFechaInicio;
    @FXML private DatePicker datePickerFechaFinal;

    @FXML private TextField textFielEmpleado;
    @FXML private TextField textFieldHoras;

    @FXML private TableView<RegistroTiempo1> tableViewTablaBuscarParametro;
    @FXML private TableColumn<RegistroTiempo1, String> tableColumnEmpleado;
    @FXML private TableColumn<RegistroTiempo1, String> tableColumnOrden;
    @FXML private TableColumn<RegistroTiempo1, LocalDate> tableColumnFecha;
    @FXML private TableColumn<RegistroTiempo1, String> tableColumnActividad;

    @FXML private Button buttonGenerar;
    @FXML private Button buttonLimpiar;
    @FXML private Button buttonRegresar;
    @FXML private Button buttonExportar;

    // ================== DATOS ==================

    private ObservableList<RegistroTiempo1> listaRegistros = FXCollections.observableArrayList();

    // ================== INITIALIZE ==================

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarDatosPrueba();
    }

    private void configurarTabla() {
        tableColumnEmpleado.setCellValueFactory(data -> data.getValue().empleadoProperty());
        tableColumnOrden.setCellValueFactory(data -> data.getValue().ordenProperty());
        tableColumnFecha.setCellValueFactory(data -> data.getValue().fechaProperty());
        tableColumnActividad.setCellValueFactory(data -> data.getValue().actividadProperty());

        tableViewTablaBuscarParametro.setItems(listaRegistros);
    }

    // ================== EVENTOS ==================

    @FXML
    private void buscarItem(ActionEvent event) {

        LocalDate inicio = datePickerFechaInicio.getValue();
        LocalDate fin = datePickerFechaFinal.getValue();
        String empleado = textFielEmpleado.getText();

        double totalHoras = 0;

        ObservableList<RegistroTiempo1> filtrados = FXCollections.observableArrayList();

        for (RegistroTiempo1 r : listaRegistros) {

            boolean cumple = true;

            if (inicio != null && r.getFecha().isBefore(inicio)) {
                cumple = false;
            }

            if (fin != null && r.getFecha().isAfter(fin)) {
                cumple = false;
            }

            if (empleado != null && !empleado.isEmpty()
                    && !r.getEmpleado().toLowerCase().contains(empleado.toLowerCase())) {
                cumple = false;
            }

            if (cumple) {
                filtrados.add(r);
                totalHoras += r.getHoras();
            }
        }

        tableViewTablaBuscarParametro.setItems(filtrados);
        textFieldHoras.setText(String.valueOf(totalHoras));
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("reportesRRHH.fxml");
    }

    @FXML
    private void guardarOrden(ActionEvent event) {
        System.out.println("Exportar reporte");
    }

    @FXML
    private void clickLimpiar(ActionEvent event) {

        datePickerFechaInicio.setValue(null);
        datePickerFechaFinal.setValue(null);
        textFielEmpleado.clear();
        textFieldHoras.clear();

        tableViewTablaBuscarParametro.setItems(listaRegistros);
    }

    // ================== DATOS DE PRUEBA ==================

    private void cargarDatosPrueba() {
        listaRegistros.add(new RegistroTiempo1(
                "Juan Pérez", "OT-001", LocalDate.now().minusDays(2),
                "Mantenimiento preventivo", 8));

        listaRegistros.add(new RegistroTiempo1(
                "María Gómez", "OT-002", LocalDate.now().minusDays(1),
                "Revisión de equipo", 6));

        listaRegistros.add(new RegistroTiempo1(
                "Carlos López", "OT-003", LocalDate.now(),
                "Cambio de repuestos", 7));
    }
}
