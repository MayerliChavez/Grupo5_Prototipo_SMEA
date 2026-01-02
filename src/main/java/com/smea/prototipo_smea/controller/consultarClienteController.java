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

public class consultarClienteController
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

    @FXML private Button buttonVerDetalle;
    @FXML private Button buttonCambiarEstado;
    @FXML private Button buttonRegresar;
    @FXML private Button buttonBuscarCedula;
    @FXML private Button buttonBuscarNombre;

    // ===== DATOS =====
    private ObservableList<Cliente> listaClientes;

    // ===== INYECCIÓN =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        if (!"Administrador".equals(mainController.getRolUsuario())) {
            buttonCambiarEstado.setDisable(true);
            buttonCambiarEstado.setVisible(false);
        }
    }

    // ===== INITIALIZE =====
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        chiceBoxCliente.setItems(
                FXCollections.observableArrayList("Cédula", "Nombre")
        );
        chiceBoxCliente.getSelectionModel().selectFirst();
        tableColumnNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        tableColumnCedula.setCellValueFactory(data -> data.getValue().cedulaRucProperty());
        tableColumnTelefono.setCellValueFactory(data -> data.getValue().telefonoProperty());
        tableColumnEstado.setCellValueFactory(data -> data.getValue().estadoProperty());

        textFieldNombreCliente.setOnAction(e -> buttonBuscarNombre.requestFocus());
        textfieldCedulCliente.setOnAction(e -> buttonBuscarCedula.requestFocus());

        cargarDatosSimulados();
        configurarBusqueda();
    }

    // ===== DATOS SIMULADOS =====
    private void cargarDatosSimulados() {
        listaClientes = FXCollections.observableArrayList(
                new Cliente("Juan", "Lopez", "0102030405",
                        "Calderón", "0987654321",
                        "juan@correo.com", "ACTIVO"),

                new Cliente("María", "Chávez", "1728678150",
                        "Carcelén", "0912345678",
                        "maria@correo.com", "ACTIVO"),

                new Cliente("Carlos", "Pérez", "1234567890",
                        "Marianas de Jesús", "0998765432",
                        "carlos@correo.com", "INACTIVO")
        );

        tableViewClientes.setItems(listaClientes);
    }

    // ===== HABILITAR / DESHABILITAR =====
    private void configurarBusqueda() {
        textfieldCedulCliente.setDisable(true);
        textFieldNombreCliente.setDisable(true);
        buttonBuscarCedula.setDisable(true);
        buttonBuscarNombre.setDisable(true);

        chiceBoxCliente.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> {

                    if ("Cédula".equals(newValue)) {
                        textfieldCedulCliente.setDisable(false);
                        textFieldNombreCliente.setDisable(true);
                        textFieldNombreCliente.clear();
                        buttonBuscarNombre.setDisable(true);
                        buttonBuscarCedula.setDisable(false);
                    } else {
                        textfieldCedulCliente.setDisable(true);
                        textFieldNombreCliente.setDisable(false);
                        textfieldCedulCliente.clear();
                        buttonBuscarNombre.setDisable(false);
                        buttonBuscarCedula.setDisable(true);
                    }
                });
    }

    // ===== BUSCAR =====
    @FXML
    private void buscarCliente(ActionEvent event) {

        if (chiceBoxCliente.getValue() == null) {
            mostrarAlerta(
                    "Seleccione un criterio",
                    "Debe seleccionar si desea buscar por Cédula o por Nombre",
                    Alert.AlertType.WARNING
            );
            return;
        }

        ObservableList<Cliente> filtrado = FXCollections.observableArrayList();

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

            for (Cliente c : listaClientes) {
                if (c.getCedulaRuc().contains(cedula)) {
                    filtrado.add(c);
                }
            }

        } else if ("Nombre".equals(chiceBoxCliente.getValue())) {

            String nombre = textFieldNombreCliente.getText().trim().toLowerCase();

            if (nombre.isEmpty()) {
                mostrarAlerta(
                        "Campo vacío",
                        "Ingrese un nombre para realizar la búsqueda",
                        Alert.AlertType.WARNING
                );
                return;
            }

            for (Cliente c : listaClientes) {
                if (c.getNombre().toLowerCase().contains(nombre)) {
                    filtrado.add(c);
                }
            }
        }

        if (filtrado.isEmpty()) {
            mostrarAlerta(
                    "Sin resultados",
                    "No se encontraron clientes con los datos ingresados",
                    Alert.AlertType.INFORMATION
            );

            tableViewClientes.setItems(listaClientes);
            return;
        }

        tableViewClientes.setItems(filtrado);
    }


    // ===== VER DETALLE =====
    @FXML
    private void verDetalleCliente(ActionEvent event) {

        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();

        if (cliente == null) {
            mostrarAlerta(
                    "Selección requerida",
                    "Seleccione un cliente para ver los detalles",
                    Alert.AlertType.WARNING
            );
            return;
        }

        String detalle =
                "Nombre: " + cliente.getNombre() + " " + cliente.getApellido() + "\n" +
                        "Cédula: " + cliente.getCedulaRuc() + "\n" +
                        "Dirección: " + cliente.getDireccion() + "\n" +
                        "Teléfono: " + cliente.getTelefono() + "\n" +
                        "Correo: " + cliente.getEmail() + "\n" +
                        "Estado: " + cliente.getEstado();

        mostrarAlerta("Detalle del Cliente", detalle, Alert.AlertType.INFORMATION);
    }

    // ===== CAMBIAR ESTADO (SOLO ADMIN) =====
    @FXML
    private void cambiarEstadoCliente(ActionEvent event) {

        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();

        if (cliente == null) {
            mostrarAlerta("Seleccione un cliente",
                    "Debe seleccionar un cliente",
                    Alert.AlertType.WARNING);
            return;
        }

        String nuevoEstado = cliente.getEstado().equals("ACTIVO")
                ? "INACTIVO"
                : "ACTIVO";

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar cambio");
        confirm.setHeaderText(null);
        confirm.setContentText("¿Cambiar estado a " + nuevoEstado + "?");

        if (confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            cliente.setEstado(nuevoEstado);
            tableViewClientes.refresh();
        }
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
