package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class registrarEquipoClienteController
        implements Initializable, ControladorInyectable {

    // ===== CONTROLADOR PRINCIPAL =====
    private MainController mainController;

    // ===== COMPONENTES FXML =====
    @FXML private ChoiceBox<String> chiceBoxCliente;

    @FXML private TextField textfieldCedulCliente;
    @FXML private TextField textFieldNombreCliente;

    @FXML private TableView<Cliente> tableViewClientes;
    @FXML private TableColumn<Cliente, String> tableColumnNombre;
    @FXML private TableColumn<Cliente, String> tableColumnCedula;
    @FXML private TableColumn<Cliente, String> tableColumnTelefono;
    @FXML private TableColumn<Cliente, String> tableColumnEstado;

    // ===== DATOS =====
    private ObservableList<Cliente> listaClientes;

    // ===== INYECCIÓN =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ===== INITIALIZE =====
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // ChoiceBox
        chiceBoxCliente.setItems(
                FXCollections.observableArrayList("Cédula", "Nombre")
        );
        chiceBoxCliente.getSelectionModel().selectFirst();

        // Columnas
        tableColumnNombre.setCellValueFactory(data ->
                data.getValue().nombreProperty());

        tableColumnCedula.setCellValueFactory(data ->
                data.getValue().cedulaRucProperty());

        tableColumnTelefono.setCellValueFactory(data ->
                data.getValue().telefonoProperty());

        tableColumnEstado.setCellValueFactory(data ->
                data.getValue().estadoProperty());

        // Cargar datos
        cargarDatosSimulados();

        // Configurar habilitación de campos
        configurarBusqueda();
    }

    // ===== DATOS SIMULADOS =====
    private void cargarDatosSimulados() {
        listaClientes = FXCollections.observableArrayList(
                new Cliente("Juan", "Lopez", "0987654321", "calderon", "0987090787", "mayerli@epn.edu","ACTIVO"),
                new Cliente("María","Chavez","1728678150", "Calceren", "0912345678", "carla@guapo.com", "ACTIVO"),
                new Cliente("Carlos", "Pérez", "1234567890","Marianas De Jesus",  "1712345678", "luis@pnm.com", "INACTIVO")
        );

        tableViewClientes.setItems(listaClientes);
    }

    // ===== HABILITAR / DESHABILITAR =====
    private void configurarBusqueda() {

        // Estado inicial
        textfieldCedulCliente.setDisable(false);
        textFieldNombreCliente.setDisable(true);
        button

        chiceBoxCliente.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldValue, newValue) -> {

                    if ("Cédula".equals(newValue)) {
                        textfieldCedulCliente.setDisable(false);
                        textFieldNombreCliente.setDisable(true);
                        textFieldNombreCliente.clear();
                    } else {
                        textfieldCedulCliente.setDisable(true);
                        textFieldNombreCliente.setDisable(false);
                        textfieldCedulCliente.clear();
                    }
                }
        );
    }

    // ===== BUSCAR =====
    @FXML
    private void buscarCliente(ActionEvent event) {

        String criterio = chiceBoxCliente.getValue();
        ObservableList<Cliente> filtrado = FXCollections.observableArrayList();

        if ("Cédula".equals(criterio)) {
            String cedula = textfieldCedulCliente.getText().trim();

            for (Cliente c : listaClientes) {
                if (c.getCedulaRuc().contains(cedula)) {
                    filtrado.add(c);
                }
            }
        } else {
            String nombre = textFieldNombreCliente.getText().trim().toLowerCase();

            for (Cliente c : listaClientes) {
                if (c.getNombre().toLowerCase().contains(nombre)) {
                    filtrado.add(c);
                }
            }
        }

        tableViewClientes.setItems(filtrado);
    }

    // ===== REGISTRAR EQUIPO =====
    @FXML
    private void registrarProducto(ActionEvent event) {

        Cliente clienteSeleccionado =
                tableViewClientes.getSelectionModel().getSelectedItem();

        if (clienteSeleccionado == null) {
            mostrarAlerta(
                    "Selección requerida",
                    "Seleccione un cliente para registrar el equipo",
                    Alert.AlertType.WARNING
            );
            return;
        }

        System.out.println("Registrar equipo para: " +
                clienteSeleccionado.getNombre());

        mainController.saver("registrarEquipoC.fxml");
    }

    // ===== REGRESAR =====
    @FXML
    private void regresarModuloProveedor(ActionEvent event) {
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
