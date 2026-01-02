package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.Cliente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class programarCitaTecnicaController
        implements Initializable, ControladorInyectable {

    // ===== MAIN =====
    private MainController mainController;

    // ===== FXML =====
    @FXML private TextField textFielNombreProveedor; // Cédula
    @FXML private TableView<Cliente> tableViewClientes;

    @FXML private TableColumn<Cliente, String> tableColumnNombre;
    @FXML private TableColumn<Cliente, String> tableColumnCedula;
    @FXML private TableColumn<Cliente, String> tableColumnTelefono;
    @FXML private TableColumn<Cliente, String> tableColumnEquiposAsociados;
    @FXML private TableColumn<Cliente, String> tablacColumnEstado;

    @FXML private Button buttonBuscar;
    @FXML private Button buttonAgendarCita;
    @FXML private Button buttonRegresar;

    // ===== DATOS =====
    private ObservableList<Cliente> listaClientes;

    // ===== INYECCIÓN =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ===== INIT =====
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableColumnNombre.setCellValueFactory(d -> d.getValue().nombreProperty());
        tableColumnCedula.setCellValueFactory(d -> d.getValue().cedulaRucProperty());
        tableColumnTelefono.setCellValueFactory(d -> d.getValue().telefonoProperty());
        tablacColumnEstado.setCellValueFactory(d -> d.getValue().estadoProperty());

        // Equipos asociados (dato calculado / simulado)
        tableColumnEquiposAsociados.setCellValueFactory(d ->
                new SimpleStringProperty("2 equipos"));

        textFielNombreProveedor.setOnAction(e-> buttonBuscar.requestFocus());

        cargarDatosSimulados();
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

        tableViewClientes.setItems(listaClientes);
    }

    // ===== BUSCAR POR CÉDULA =====
    @FXML
    private void buscarCliente(ActionEvent event) {

        String cedula = textFielNombreProveedor.getText().trim();

        // 🔹 Si el campo está vacío → mostrar todos
        if (cedula.isEmpty()) {
            tableViewClientes.setItems(listaClientes);
            mostrarAlerta(
                    "Cédula inválida",
                    "Ingrese un número de cédula para realizar la busqueda",
                    Alert.AlertType.WARNING
            );
            return;
        }

        // 🔹 Validar solo números
        if (!cedula.matches("\\d+")) {
            mostrarAlerta(
                    "Cédula inválida",
                    "La cédula solo debe contener números",
                    Alert.AlertType.WARNING
            );
            textFielNombreProveedor.clear();
            tableViewClientes.setItems(listaClientes);
            return;
        }

        ObservableList<Cliente> filtrado = FXCollections.observableArrayList();

        for (Cliente c : listaClientes) {
            if (c.getCedulaRuc().equals(cedula)) {
                filtrado.add(c);
            }
        }

        // 🔹 Si no se encontró nada
        if (filtrado.isEmpty()) {
            mostrarAlerta(
                    "No encontrado",
                    "No existe un cliente con esa cédula.",
                    Alert.AlertType.INFORMATION
            );
            textFielNombreProveedor.clear();
            tableViewClientes.setItems(listaClientes);
            return;
        }

        // 🔹 Mostrar resultado
        tableViewClientes.setItems(filtrado);
    }


    // ===== AGENDAR CITA =====
    @FXML
    private void AgendarCita(ActionEvent event) {

        Cliente clienteSeleccionado =
                tableViewClientes.getSelectionModel().getSelectedItem();

        if (clienteSeleccionado == null) {
            mostrarAlerta(
                    "Seleccione un cliente",
                    "Debe seleccionar un cliente para agendar la cita",
                    Alert.AlertType.WARNING
            );
            return;
        }

        // Guardar cliente seleccionado
        mainController.setDatoTemporal(clienteSeleccionado);

        // Abrir pantalla de agendamiento
        mainController.saver("citaTecnicaAgendar.fxml");
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
