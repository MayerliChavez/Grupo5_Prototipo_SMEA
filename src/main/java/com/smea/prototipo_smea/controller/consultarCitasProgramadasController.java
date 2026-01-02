package com.smea.prototipo_smea.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.smea.prototipo_smea.clasesNormales.CitaTecnica;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class consultarCitasProgramadasController
        implements Initializable, ControladorInyectable {

    // ===== MAIN =====
    private MainController mainController;

    // ===== FXML =====
    @FXML private ChoiceBox<String> chiceBoxCliente;

    @FXML private TextField textfieldCedulCliente;
    @FXML private TextField textfieldCodigoEquipo;
    @FXML private TextField textfieldFechaCita;
    @FXML private TextField textfieldTecnicoCargo;

    @FXML private TableView<CitaTecnica> tableViewClientes;
    @FXML private TableColumn<CitaTecnica, String> tableColumnCodigo;
    @FXML private TableColumn<CitaTecnica, String> tableColumnCliente;
    @FXML private TableColumn<CitaTecnica, String> tableColumnFecha;
    @FXML private TableColumn<CitaTecnica, String> tableColumnHora;
    @FXML private TableColumn<CitaTecnica, String> buttonEstado;

    @FXML private Button buttonRegresar;
    @FXML private Button buttonVerDetalle;
    @FXML private Button buttonModificarCita;
    @FXML private Button buttonBuscarCedula;
    @FXML private Button buttonBuscarFecha;
    @FXML private Button buttonBuscarTecnico;
    @FXML private Button buttonBuscarCodigo;

    // ===== DATOS =====
    private ObservableList<CitaTecnica> listaCitas;

    // ===== INYECCIÓN =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ===== INIT =====
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        chiceBoxCliente.setItems(FXCollections.observableArrayList(
                "Cédula Cliente", "Código Equipo", "Fecha", "Técnico"
        ));
        chiceBoxCliente.getSelectionModel().selectFirst();

        // Columnas
        tableColumnCodigo.setCellValueFactory(d -> d.getValue().codigoProperty());
        tableColumnCliente.setCellValueFactory(d -> d.getValue().clienteProperty());
        tableColumnFecha.setCellValueFactory(d -> d.getValue().fechaProperty());
        tableColumnHora.setCellValueFactory(d -> d.getValue().horaProperty());
        buttonEstado.setCellValueFactory(d -> d.getValue().estadoProperty());

        textfieldCedulCliente.setOnAction(e-> buttonBuscarCedula.requestFocus());
        textfieldCodigoEquipo.setOnAction(e-> buttonBuscarCodigo.requestFocus());
        textfieldFechaCita.setOnAction(e-> buttonBuscarFecha.requestFocus());
        textfieldTecnicoCargo.setOnAction(e-> buttonBuscarTecnico.requestFocus());

        cargarDatosSimulados();

        activarBuscadorSeleccionado(
                chiceBoxCliente.getSelectionModel().getSelectedItem()
        );

        chiceBoxCliente.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {
                    activarBuscadorSeleccionado(newVal);
                    tableViewClientes.setItems(listaCitas); // reset tabla
                });
    }

    // ===== DATOS SIMULADOS =====
    private void cargarDatosSimulados() {

        listaCitas = FXCollections.observableArrayList(
                new CitaTecnica("CIT-001", "Juan López", "2025-01-10", "09:00", "PROGRAMADA", "CORRECTIVO", "Luis Mendez"),
                new CitaTecnica("CIT-002", "María Chávez", "2025-01-12", "14:30", "PROGRAMADA", "EMERGENCIA", "Maria Lopez"),
                new CitaTecnica("CIT-003", "Carlos Pérez", "2025-01-15", "11:00", "CANCELADA", "PREVENTIVO", "Luis Mendez")
        );

        tableViewClientes.setItems(listaCitas);
    }

    // ===== BUSCAR =====
    @FXML
    private void buscarCliente(ActionEvent event) {

        String cedula = textfieldCedulCliente.getText().trim();
        String codigoEquipo = textfieldCodigoEquipo.getText().trim();
        String fecha = textfieldFechaCita.getText().trim();
        String tecnico = textfieldTecnicoCargo.getText().trim();

        if (cedula.isEmpty() && codigoEquipo.isEmpty()
                && fecha.isEmpty() && tecnico.isEmpty()) {

            mostrarAlerta(
                    "Criterio vacío",
                    "Debe ingresar al menos un criterio de búsqueda",
                    Alert.AlertType.WARNING
            );
            tableViewClientes.setItems(listaCitas); // no limpiar tabla
            return;
        }

        ObservableList<CitaTecnica> filtrado = FXCollections.observableArrayList();

        for (CitaTecnica c : listaCitas) {

            boolean coincide = true;

            if (!cedula.isEmpty() && !c.getCliente().contains(cedula)) {
                coincide = false;
            }

            if (!codigoEquipo.isEmpty() && !c.getCodigo().contains(codigoEquipo)) {
                coincide = false;
            }

            if (!fecha.isEmpty() && !c.getFecha().equals(fecha)) {
                coincide = false;
            }

            if (!tecnico.isEmpty()
                    && !c.getTecnicoAsignado().toLowerCase()
                    .contains(tecnico.toLowerCase())) {
                coincide = false;
            }

            if (coincide) {
                filtrado.add(c);
            }
        }

        if (filtrado.isEmpty()) {
            mostrarAlerta(
                    "Sin resultados",
                    "No se encontraron citas con los criterios ingresados",
                    Alert.AlertType.INFORMATION
            );
            tableViewClientes.setItems(listaCitas); // restaurar tabla
        } else {
            tableViewClientes.setItems(filtrado);
        }
    }


    // ===== VER DETALLE =====
    @FXML
    private void verDetalleCliente(ActionEvent event) {

        CitaTecnica cita =
                tableViewClientes.getSelectionModel().getSelectedItem();

        if (cita == null) {
            mostrarAlerta(
                    "Seleccione una cita",
                    "Debe seleccionar una cita para ver el detalle",
                    Alert.AlertType.WARNING
            );
            return;
        }

        String detalle =
                "Código: " + cita.getCodigo() + "\n" +
                        "Cliente: " + cita.getCliente() + "\n" +
                        "Fecha: " + cita.getFecha() + "\n" +
                        "Hora: " + cita.getHora() + "\n" +
                        "Estado: " + cita.getEstado() + "\n" +
                        "Tipo de servicio: " + cita.getTipoMantenimiento() + "\n" +
                        "Tecnico Asignado: " + cita.getTecnicoAsignado();

        mostrarAlerta(
                "Detalle de la Cita",
                detalle,
                Alert.AlertType.INFORMATION
        );
    }

    // ===== MODIFICAR =====
    @FXML
    private void modificarCita(ActionEvent event) {

        CitaTecnica cita =
                tableViewClientes.getSelectionModel().getSelectedItem();

        if (cita == null) {
            mostrarAlerta(
                    "Seleccione una cita",
                    "Debe seleccionar una cita para modificar",
                    Alert.AlertType.WARNING
            );
            return;
        }

        mainController.setDatoTemporal(cita);
        mainController.saver("modificarCitasTecnicas.fxml");
    }

    private void activarBuscadorSeleccionado(String opcion) {

        ocultarTodosLosBuscadores();

        switch (opcion) {
            case "Cédula Cliente" -> {
                textfieldCedulCliente.setDisable(false);
                buttonBuscarCedula.setDisable(false);
            }
            case "Código Equipo" -> {
                textfieldCodigoEquipo.setDisable(false);
                buttonBuscarCodigo.setDisable(false);
            }
            case "Fecha" -> {
                textfieldFechaCita.setDisable(false);
                buttonBuscarFecha.setDisable(false);
            }
            case "Técnico" -> {
                textfieldTecnicoCargo.setDisable(false);
                buttonBuscarTecnico.setDisable(false);
            }
        }
    }


    private void ocultarTodosLosBuscadores() {

        textfieldCedulCliente.setDisable(true);
        textfieldCodigoEquipo.setDisable(true);
        textfieldFechaCita.setDisable(true);
        textfieldTecnicoCargo.setDisable(true);

        buttonBuscarCedula.setDisable(true);
        buttonBuscarCodigo.setDisable(true);
        buttonBuscarFecha.setDisable(true);
        buttonBuscarTecnico.setDisable(true);

        // Limpiar campos
        textfieldCedulCliente.clear();
        textfieldCodigoEquipo.clear();
        textfieldFechaCita.clear();
        textfieldTecnicoCargo.clear();
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
