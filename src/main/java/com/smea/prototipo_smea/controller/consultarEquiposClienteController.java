package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.EquipoCliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class consultarEquiposClienteController
        implements Initializable, ControladorInyectable {

    // ===== MAIN =====
    private MainController mainController;

    // ===== BUSQUEDA =====
    @FXML private ChoiceBox<String> chiceBoxCliente;
    @FXML private TextField textfieldCedulCliente;
    @FXML private TextField textFieldNombreCliente;
    @FXML private Button buttonBuscarCedula;
    @FXML private Button buttonBuscarNombre;

    // ===== TABLA =====
    @FXML private TableView<EquipoCliente> tableViewClientes;
    @FXML private TableColumn<EquipoCliente, String> tableColumnNombre;
    @FXML private TableColumn<EquipoCliente, String> tableColumnTipoEquipo;
    @FXML private TableColumn<EquipoCliente, String> tableColumnNumeroSerie;
    @FXML private TableColumn<EquipoCliente, String> tableColumnEstado;

    // ===== BOTONES =====
    @FXML private Button buttonEstado;
    @FXML private Button buttonVerDetalle;
    @FXML private Button buttonRegresar;

    // ===== DATOS =====
    private ObservableList<EquipoCliente> listaEquipos;

    // ===== INYECCIÓN =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;

        if (!"Administrador".equals(mainController.getRolUsuario())) {
            buttonEstado.setDisable(true);
            buttonEstado.setVisible(false);
        }
    }

    // ===== INIT =====
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        chiceBoxCliente.setItems(
                FXCollections.observableArrayList("Cédula", "Nombre")
        );
        chiceBoxCliente.getSelectionModel().selectFirst();

        configurarBusqueda();

        tableColumnNombre.setCellValueFactory(d -> d.getValue().clienteProperty());
        tableColumnTipoEquipo.setCellValueFactory(d -> d.getValue().tipoEquipoProperty());
        tableColumnNumeroSerie.setCellValueFactory(d -> d.getValue().numeroSerieProperty());
        tableColumnEstado.setCellValueFactory(d -> d.getValue().estadoProperty());

        textfieldCedulCliente.setOnAction(e-> buttonBuscarCedula.requestFocus());
        textFieldNombreCliente.setOnAction(e-> buttonBuscarNombre.requestFocus());

        cargarDatosSimulados();
    }

    // ===== DATOS =====
    private void cargarDatosSimulados() {
        listaEquipos = FXCollections.observableArrayList(
                new EquipoCliente("Juan López", "Laptop", "Dell",
                        "Inspiron 15", "DL-12345", "OPERATIVO", "Sin novedades"),

                new EquipoCliente("Juan López", "Impresora", "HP",
                        "LaserJet 1020", "HP-5566", "EN_REPARACION", "Cambio de rodillo"),

                new EquipoCliente("María Chávez", "PC Escritorio", "Lenovo",
                        "ThinkCentre", "LN-8899", "DADO_DE_BAJA", "Equipo obsoleto")
        );

        tableViewClientes.setItems(listaEquipos);
    }

    // ===== BUSCAR =====
    @FXML
    private void buscarCliente(ActionEvent event) {

        if (chiceBoxCliente.getValue() == null) {
            mostrarAlerta(
                    "Criterio no seleccionado",
                    "Debe seleccionar un criterio de búsqueda (Cédula o Nombre)",
                    Alert.AlertType.WARNING
            );
            return;
        }

        ObservableList<EquipoCliente> filtrado = FXCollections.observableArrayList();

        if ("Cédula".equals(chiceBoxCliente.getValue())) {

            String cedula = textfieldCedulCliente.getText().trim();

            if (cedula.isEmpty()) {
                mostrarAlerta(
                        "Campo vacío",
                        "Ingrese una cédula para realizar la búsqueda",
                        Alert.AlertType.WARNING
                );
                return;
            }

            for (EquipoCliente e : listaEquipos) {
                if (e.getCliente().toLowerCase().contains(cedula.toLowerCase())) {
                    filtrado.add(e);
                }
            }

        }

        else {

            String nombre = textFieldNombreCliente.getText().trim();

            if (nombre.isEmpty()) {
                mostrarAlerta(
                        "Campo vacío",
                        "Ingrese un nombre para realizar la búsqueda",
                        Alert.AlertType.WARNING
                );
                return;
            }

            for (EquipoCliente e : listaEquipos) {
                if (e.getCliente().toLowerCase().contains(nombre.toLowerCase())) {
                    filtrado.add(e);
                }
            }
        }

        if (filtrado.isEmpty()) {
            mostrarAlerta(
                    "Sin resultados",
                    "No se encontraron registros con los datos ingresados",
                    Alert.AlertType.INFORMATION
            );

            tableViewClientes.setItems(listaEquipos);
        } else {
            tableViewClientes.setItems(filtrado);
        }
    }


    // ===== DETALLE =====
    @FXML
    private void verDetalleEquipo(ActionEvent event) {

        EquipoCliente e = tableViewClientes.getSelectionModel().getSelectedItem();

        if (e == null) {
            mostrarAlerta("Seleccione un equipo",
                    "Debe seleccionar un equipo",
                    Alert.AlertType.WARNING);
            return;
        }

        String detalle =
                "Cliente: " + e.getCliente() + "\n" +
                        "Tipo de equipo: " + e.getTipoEquipo() + "\n" +
                        "Marca: " + e.getMarca() + "\n" +
                        "Modelo: " + e.getModelo() + "\n" +
                        "Número de serie: " + e.getNumeroSerie() + "\n" +
                        "Resultados: " + e.getResultados() + "\n" +
                        "Estado: " + e.getEstado();

        mostrarAlerta("Detalle del equipo", detalle, Alert.AlertType.INFORMATION);
    }

    // ===== CAMBIAR ESTADO =====
    @FXML
    private void cambiarEstadoEquipo(ActionEvent event) {

        EquipoCliente e = tableViewClientes.getSelectionModel().getSelectedItem();

        if (e == null) {
            mostrarAlerta("Seleccione un equipo",
                    "Debe seleccionar un equipo",
                    Alert.AlertType.WARNING);
            return;
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(
                e.getEstado(),
                "OPERATIVO", "EN_REPARACION", "DADO_DE_BAJA"
        );

        dialog.setTitle("Cambiar estado");
        dialog.setHeaderText("Seleccione el nuevo estado");

        dialog.showAndWait().ifPresent(estado -> {
            e.setEstado(estado);
            tableViewClientes.refresh();
        });
    }

    // ===== REGRESAR =====
    @FXML
    private void regresarModuloCliente(ActionEvent event) {
        mainController.saver("moduloCliente.fxml");
    }

    // ===== UTIL =====
    private void configurarBusqueda() {
        textfieldCedulCliente.setDisable(false);
        textFieldNombreCliente.setDisable(true);
        buttonBuscarCedula.setDisable(false);
        buttonBuscarNombre.setDisable(true);

        chiceBoxCliente.getSelectionModel().selectedItemProperty()
                .addListener((obs, o, n) -> {
                    boolean cedula = "Cédula".equals(n);
                    textfieldCedulCliente.setDisable(!cedula);
                    buttonBuscarCedula.setDisable(!cedula);
                    textFieldNombreCliente.setDisable(cedula);
                    buttonBuscarNombre.setDisable(cedula);
                });
    }

    private void mostrarAlerta(String t, String m, Alert.AlertType tipo) {
        Alert a = new Alert(tipo);
        a.setTitle(t);
        a.setHeaderText(null);
        a.setContentText(m);
        a.showAndWait();
    }
}
