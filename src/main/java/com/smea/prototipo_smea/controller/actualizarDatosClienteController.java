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

public class actualizarDatosClienteController
        implements Initializable, ControladorInyectable {

    // ===== MAIN CONTROLLER =====
    private MainController mainController;

    // ===== FXML =====
    @FXML private ChoiceBox<String> chiceBoxCliente;

    @FXML private TextField textfieldCedulCliente;
    @FXML private TextField textFieldNombreCliente;

    @FXML private TextField textfieldDireccion;
    @FXML private TextField textfieldCorreo;
    @FXML private TextField textfieldTelefono;

    @FXML private Button buttonBuscarCedula;
    @FXML private Button buttonBuscarNombre;
    @FXML private Button buttonGuardarCambios;
    @FXML private Button buttonGuardarCambios1; // restaurar
    @FXML private Button buttonRegresar;

    // ===== DATOS =====
    private ObservableList<Cliente> listaClientes;
    private Cliente clienteActual;
    private Cliente respaldoCliente;

    // ===== INYECCIÓN =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ===== INIT =====
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        chiceBoxCliente.setItems(
                FXCollections.observableArrayList("Cédula", "Nombre")
        );
        chiceBoxCliente.getSelectionModel().selectFirst();

        configurarBusqueda();
        cargarDatosSimulados();
        deshabilitarEdicion();
        configurarTabulacion();

        textfieldDireccion.setOnAction(event -> textfieldCorreo.requestFocus());
        textfieldCorreo.setOnAction(event -> textfieldTelefono.requestFocus());
    }

    private void configurarTabulacion() {
        configurarTab(textfieldDireccion, textfieldCorreo);
        configurarTab(textfieldCorreo, textfieldTelefono);
    }

    private void configurarTab(Control actual, Control siguiente) {
        actual.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.TAB) {
                siguiente.requestFocus();
                event.consume();
            }
        });
    }

    // ===== DATOS SIMULADOS =====
    private void cargarDatosSimulados() {
        listaClientes = FXCollections.observableArrayList(
                new Cliente("Juan", "López", "0102030405",
                        "Calderón", "0987654321",
                        "juan@correo.com", "ACTIVO"),

                new Cliente("María", "Chávez", "1728678150",
                        "Carcelén", "0912345678",
                        "maria@correo.com", "ACTIVO")
        );
    }

    // ===== CONFIGURAR BUSQUEDA =====
    private void configurarBusqueda() {

        textfieldCedulCliente.setDisable(false);
        textFieldNombreCliente.setDisable(true);
        buttonBuscarCedula.setDisable(false);
        buttonBuscarNombre.setDisable(true);

        chiceBoxCliente.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {

                    if ("Cédula".equals(newVal)) {
                        textfieldCedulCliente.setDisable(false);
                        textFieldNombreCliente.setDisable(true);
                        textFieldNombreCliente.clear();

                        buttonBuscarCedula.setDisable(false);
                        buttonBuscarNombre.setDisable(true);
                    } else {
                        textfieldCedulCliente.setDisable(true);
                        textFieldNombreCliente.setDisable(false);
                        textfieldCedulCliente.clear();

                        buttonBuscarCedula.setDisable(true);
                        buttonBuscarNombre.setDisable(false);
                    }
                });
    }

    // ===== BUSCAR CLIENTE =====
    @FXML
    private void buscarCliente(ActionEvent event) {

        clienteActual = null;
        if (chiceBoxCliente.getValue() == null) {
            mostrarAlerta(
                    "Seleccione un criterio",
                    "Debe seleccionar un criterio de búsqueda",
                    Alert.AlertType.WARNING
            );
            return;
        }
        if ("Cédula".equals(chiceBoxCliente.getValue())) {

            String cedula = textfieldCedulCliente.getText().trim();

            // Validar campo vacío
            if (cedula.isEmpty()) {
                mostrarAlerta(
                        "Campo vacío",
                        "Debe ingresar una cédula para buscar",
                        Alert.AlertType.WARNING
                );
                return;
            }

            // Validar solo números
            if (!cedula.matches("\\d+")) {
                mostrarAlerta(
                        "Cédula inválida",
                        "La cédula solo debe contener números",
                        Alert.AlertType.WARNING
                );
                return;
            }

            for (Cliente c : listaClientes) {
                if (c.getCedulaRuc().equals(cedula)) {
                    clienteActual = c;
                    break;
                }
            }

        }
        else if ("Nombre".equals(chiceBoxCliente.getValue())) {

            String nombre = textFieldNombreCliente.getText().trim();

            if (nombre.isEmpty()) {
                mostrarAlerta(
                        "Campo vacío",
                        "Debe ingresar un nombre para buscar",
                        Alert.AlertType.WARNING
                );
                return;
            }

            for (Cliente c : listaClientes) {
                if (c.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                    clienteActual = c;
                    break;
                }
            }
        }

        if (clienteActual == null) {
            mostrarAlerta(
                    "Cliente no encontrado",
                    "No se encontró ningún cliente con los datos ingresados",
                    Alert.AlertType.INFORMATION
            );
            return;
        }

        cargarClienteEnFormulario(clienteActual);
    }


    // ===== CARGAR DATOS =====
    private void cargarClienteEnFormulario(Cliente c) {

        textfieldDireccion.setText(c.getDireccion());
        textfieldCorreo.setText(c.getEmail());
        textfieldTelefono.setText(c.getTelefono());

        respaldoCliente = new Cliente(
                c.getNombre(), c.getApellido(), c.getCedulaRuc(),
                c.getDireccion(), c.getTelefono(),
                c.getEmail(), c.getEstado()
        );

        habilitarEdicion();
    }

    // ===== GUARDAR =====
    @FXML
    private void clickguardarCambios(ActionEvent event) {

        if (clienteActual == null) {
            mostrarAlerta("Error",
                    "Debe buscar un cliente primero",
                    Alert.AlertType.WARNING);
            return;
        }

        if (!validarCampos()) {
            return;
        }

        clienteActual.setDireccion(textfieldDireccion.getText());
        clienteActual.setEmail(textfieldCorreo.getText());
        clienteActual.setTelefono(textfieldTelefono.getText());

        mostrarAlerta("Actualizado",
                "Datos del cliente actualizados correctamente",
                Alert.AlertType.INFORMATION);
    }


    private boolean validarCampos() {

        String direccion = textfieldDireccion.getText().trim();
        String correo = textfieldCorreo.getText().trim();
        String telefono = textfieldTelefono.getText().trim();

        if (direccion.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
            mostrarAlerta("Campos obligatorios",
                    "Debe completar todos los campos",
                    Alert.AlertType.WARNING);
            return false;
        }

        if (!correo.contains("@")) {
            mostrarAlerta(
                    "Correo inválido",
                    "Ingrese un correo electrónico válido",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        if (!telefono.matches("\\d{10}") || !validarSoloNumeros(telefono)) {
            mostrarAlerta(
                    "Teléfono inválido",
                    "El teléfono tiene caracteres no permitidos",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        return true;
    }

    private boolean validarSoloNumeros(String cadena) {

        if (!cadena.matches("\\d+")) {
            return false;
        }
        return true;
    }

    // ===== RESTAURAR =====
    @FXML
    private void clickRestaurarValores(ActionEvent event) {
        if (respaldoCliente == null) return;

        textfieldDireccion.setText(respaldoCliente.getDireccion());
        textfieldCorreo.setText(respaldoCliente.getEmail());
        textfieldTelefono.setText(respaldoCliente.getTelefono());
    }


    // ===== REGRESAR =====
    @FXML
    private void clikcRegresarMenu(ActionEvent event) {
        mainController.saver("moduloCliente.fxml");
    }

    // ===== UTIL =====
    private void habilitarEdicion() {
        textfieldDireccion.setDisable(false);
        textfieldCorreo.setDisable(false);
        textfieldTelefono.setDisable(false);
        buttonGuardarCambios.setDisable(false);
        buttonGuardarCambios1.setDisable(false);
    }

    private void deshabilitarEdicion() {
        textfieldDireccion.setDisable(true);
        textfieldCorreo.setDisable(true);
        textfieldTelefono.setDisable(true);
        buttonGuardarCambios.setDisable(true);
        buttonGuardarCambios1.setDisable(true);
    }

    private void mostrarAlerta(String t, String m, Alert.AlertType tipo) {
        Alert a = new Alert(tipo);
        a.setTitle(t);
        a.setHeaderText(null);
        a.setContentText(m);
        a.showAndWait();
    }
}
