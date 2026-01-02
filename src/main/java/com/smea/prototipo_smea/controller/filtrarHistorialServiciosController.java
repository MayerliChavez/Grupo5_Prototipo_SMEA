package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.ServicioHistorial;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class filtrarHistorialServiciosController
        implements Initializable, ControladorInyectable {

    // ===== MAIN =====
    private MainController mainController;

    // ===== FXML =====
    @FXML private TextField textfieldCedulCliente;
    @FXML private TextField textfieldCodigoEquipo;
    @FXML private DatePicker datePickerFecha;

    @FXML private ChoiceBox<String> chiceBoxTipoServicio;
    @FXML private ChoiceBox<String> chiceBoxEstadoCita;

    @FXML private TableView<ServicioHistorial> tableViewClientes;

    @FXML private TableColumn<ServicioHistorial, String> tableColumnCodigo;
    @FXML private TableColumn<ServicioHistorial, String> tableColumnCliente;
    @FXML private TableColumn<ServicioHistorial, String> tableColumnCedulaCliente;
    @FXML private TableColumn<ServicioHistorial, String> tableColumnEquipo;
    @FXML private TableColumn<ServicioHistorial, String> buttonTipoServicio;
    @FXML private TableColumn<ServicioHistorial, String> tableColumnFecha;
    @FXML private TableColumn<ServicioHistorial, String> tableColumnTecnico;
    @FXML private TableColumn<ServicioHistorial, String> tableColumnEstado;

    @FXML private Button buttonFiltrar;
    @FXML private Button buttonRegresar;

    // ===== DATOS =====
    private ObservableList<ServicioHistorial> listaServicios;

    // ===== INYECCIÓN =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ===== INIT =====
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // ChoiceBox
        chiceBoxTipoServicio.setItems(FXCollections.observableArrayList(
                "PREVENTIVO", "CORRECTIVO", "EMERGENCIA"
        ));

        chiceBoxEstadoCita.setItems(FXCollections.observableArrayList(
                "PROGRAMADA", "FINALIZADA", "CANCELADA"
        ));

        // Columnas
        tableColumnCodigo.setCellValueFactory(d -> d.getValue().codigoServicioProperty());
        tableColumnCliente.setCellValueFactory(d -> d.getValue().clienteProperty());
        tableColumnCedulaCliente.setCellValueFactory(d -> d.getValue().cedulaClienteProperty());
        tableColumnEquipo.setCellValueFactory(d -> d.getValue().equipoProperty());
        buttonTipoServicio.setCellValueFactory(d -> d.getValue().tipoServicioProperty());
        tableColumnFecha.setCellValueFactory(d -> d.getValue().fechaProperty());
        tableColumnTecnico.setCellValueFactory(d -> d.getValue().tecnicoProperty());
        tableColumnEstado.setCellValueFactory(d -> d.getValue().estadoProperty());

        cargarDatosSimulados();
    }

    // ===== DATOS SIMULADOS =====
    private void cargarDatosSimulados() {

        listaServicios = FXCollections.observableArrayList(
                new ServicioHistorial(
                        "SER-001", "Juan López", "0102030405",
                        "EQ-100", "PREVENTIVO", "2025-01-10",
                        "Luis Méndez", "FINALIZADA"
                ),
                new ServicioHistorial(
                        "SER-002", "María Chávez", "1728678150",
                        "EQ-200", "CORRECTIVO", "2025-01-12",
                        "Carlos Pérez", "PROGRAMADA"
                ),
                new ServicioHistorial(
                        "SER-003", "Juan López", "0102030405",
                        "EQ-300", "EMERGENCIA", "2025-01-15",
                        "Luis Méndez", "CANCELADA"
                )
        );

        tableViewClientes.setItems(listaServicios);
    }

    // ===== FILTRAR =====
    @FXML
    private void buscarCliente(ActionEvent event) {

        ObservableList<ServicioHistorial> filtrado = FXCollections.observableArrayList();

        String cedula = textfieldCedulCliente.getText().trim();
        String codigoEquipo = textfieldCodigoEquipo.getText().trim();
        LocalDate fecha = datePickerFecha.getValue();
        String tipoServicio = chiceBoxTipoServicio.getValue();
        String estado = chiceBoxEstadoCita.getValue();

        if (cedula.isEmpty()
                && codigoEquipo.isEmpty()
                && fecha == null
                && tipoServicio == null
                && estado == null) {

            mostrarAlerta(
                    "Búsqueda vacía",
                    "Debe ingresar al menos un criterio de búsqueda",
                    Alert.AlertType.WARNING
            );
            return;
        }

        for (ServicioHistorial s : listaServicios) {

            boolean coincide = true;

            if (!cedula.isEmpty() && !s.getCedulaCliente().contains(cedula))
                coincide = false;

            if (!codigoEquipo.isEmpty() && !s.getEquipo().contains(codigoEquipo))
                coincide = false;

            if (fecha != null && !s.getFecha().equals(fecha.toString()))
                coincide = false;

            if (tipoServicio != null && !s.getTipoServicio().equals(tipoServicio))
                coincide = false;

            if (estado != null && !s.getEstado().equals(estado))
                coincide = false;

            if (coincide)
                filtrado.add(s);
        }

        if (filtrado.isEmpty()) {
            mostrarAlerta(
                    "Sin resultados",
                    "No se encontraron servicios con los filtros aplicados",
                    Alert.AlertType.INFORMATION
            );
            tableViewClientes.setItems(listaServicios); // 🔁 vuelve a mostrar todo
        } else {
            tableViewClientes.setItems(filtrado);
        }
    }


    // ===== REGRESAR =====
    @FXML
    private void regresarModuloCliente(ActionEvent event) {
        mainController.saver("moduloCliente.fxml");
    }

    // ===== ALERTAS =====
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
