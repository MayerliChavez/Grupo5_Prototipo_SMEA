package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.Cliente;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class agendarCitaTecnicaController
        implements Initializable, ControladorInyectable {

    // ===== MAIN =====
    private MainController mainController;
    private Cliente clienteSeleccionado;

    // ===== FXML CLIENTE =====
    @FXML private TextField textfieldNombre;
    @FXML private TextField textfieldCedulaRUC;
    @FXML private TextField textfieldDireccion;
    @FXML private TextField textfieldTelefono;

    // ===== FXML CITA =====
    @FXML private DatePicker dataPickerFecha;
    @FXML private ChoiceBox<String> choiseBoxHora;
    @FXML private ChoiceBox<String> choiseBoxTipoServicio;
    @FXML private ChoiceBox<String> choiseBoxTecnicoAsignado;

    @FXML private Button buttonAgendarCita;
    @FXML private Button buttonRegresar;

    // ===== INYECCIÓN =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;

        Cliente cliente =
                (Cliente) mainController.getDatoTemporal();

        if (cliente != null) {
            cargarCliente(cliente);
        }
    }

    // ===== INIT =====
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Campos del cliente NO editables
        textfieldNombre.setEditable(false);
        textfieldCedulaRUC.setEditable(false);
        textfieldDireccion.setEditable(false);
        textfieldTelefono.setEditable(false);

        // Horas disponibles
        choiseBoxHora.setItems(FXCollections.observableArrayList(
                "08:00", "09:00", "10:00", "11:00",
                "14:00", "15:00", "16:00"
        ));

        // Tipos de servicio
        choiseBoxTipoServicio.setItems(FXCollections.observableArrayList(
                "Mantenimiento preventivo",
                "Mantenimiento correctivo",
                "Matenimiento de emergencia"
        ));

        // Técnicos
        choiseBoxTecnicoAsignado.setItems(FXCollections.observableArrayList(
                "Carlos Pérez",
                "Luis Andrade",
                "María Gómez"
        ));

        // Fecha mínima = hoy
        dataPickerFecha.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(LocalDate.now()));
            }
        });
    }

    // ===== RECIBIR CLIENTE DESDE PANTALLA ANTERIOR =====
    public void cargarCliente(Cliente cliente) {
        this.clienteSeleccionado = cliente;

        textfieldNombre.setText(cliente.getNombre() + " " + cliente.getApellido());
        textfieldCedulaRUC.setText(cliente.getCedulaRuc());
        textfieldDireccion.setText(cliente.getDireccion());
        textfieldTelefono.setText(cliente.getTelefono());
    }

    // ===== AGENDAR CITA =====
    @FXML
    private void clickCrearCliente(ActionEvent event) {

        if (!validarCampos()) return;

        LocalDate fecha = dataPickerFecha.getValue();
        String hora = choiseBoxHora.getValue();
        String tipoServicio = choiseBoxTipoServicio.getValue();
        String tecnico = choiseBoxTecnicoAsignado.getValue();

        // Aquí luego irá la lógica de BD
        System.out.println("CITA REGISTRADA:");
        System.out.println("Cliente: " + clienteSeleccionado.getNombre());
        System.out.println("Fecha: " + fecha);
        System.out.println("Hora: " + hora);
        System.out.println("Servicio: " + tipoServicio);
        System.out.println("Técnico: " + tecnico);

        mostrarAlerta(
                "Cita agendada",
                "La cita técnica fue registrada correctamente.",
                Alert.AlertType.INFORMATION
        );

        mainController.saver("moduloCliente.fxml");
    }

    // ===== VALIDACIONES =====
    private boolean validarCampos() {

        if (clienteSeleccionado == null) {
            mostrarAlerta("Error",
                    "No hay cliente seleccionado",
                    Alert.AlertType.WARNING);
            return false;
        }

        if (dataPickerFecha.getValue() == null ||
                choiseBoxHora.getValue() == null ||
                choiseBoxTipoServicio.getValue() == null ||
                choiseBoxTecnicoAsignado.getValue() == null) {

            mostrarAlerta(
                    "Campos obligatorios",
                    "Debe completar todos los datos de la cita",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        return true;
    }

    // ===== REGRESAR =====
    @FXML
    private void clikcRegresarMenuPrincipal(ActionEvent event) {
        mainController.saver("programarCitaTecnica.fxml");
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
