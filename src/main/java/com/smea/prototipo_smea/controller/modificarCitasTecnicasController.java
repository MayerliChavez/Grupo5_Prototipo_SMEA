package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.CitaTecnica;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class modificarCitasTecnicasController
        implements Initializable, ControladorInyectable {

    // ===== MAIN =====
    private MainController mainController;
    private CitaTecnica citaActual;

    // ===== FXML =====
    @FXML private TextField textfieldNombre;
    @FXML private TextField textfieldRUC;
    @FXML private DatePicker datePickerFecha;
    @FXML private TextField textfielHora;
    @FXML private ChoiceBox<String> choiseBoxTipoServicio;
    @FXML private TextField textfieldTecnicoEncargado;

    @FXML private Button buttonGuardarCambios;
    @FXML private Button buttonRegresar;

    // ===== INIT =====
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choiseBoxTipoServicio.setItems(FXCollections.observableArrayList(
                "PREVENTIVO", "CORRECTIVO", "EMERGENCIA"
        ));
    }

    // ===== INYECCIÓN =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;

        Object dato = mainController.getDatoTemporal();
        if (dato instanceof CitaTecnica) {
            citaActual = (CitaTecnica) dato;
            cargarDatosCita();
        } else {
            mostrarAlerta(
                    "Error",
                    "No se pudo cargar la cita seleccionada",
                    Alert.AlertType.ERROR
            );
        }
    }

    // ===== CARGAR DATOS =====
    private void cargarDatosCita() {

        textfieldNombre.setText(citaActual.getCliente());
        textfieldNombre.setDisable(true);

        textfieldRUC.setText("N/A");
        textfieldRUC.setDisable(true);

        datePickerFecha.setValue(LocalDate.parse(citaActual.getFecha()));
        textfielHora.setText(citaActual.getHora());
        choiseBoxTipoServicio.setValue(citaActual.getTipoMantenimiento());
        textfieldTecnicoEncargado.setText(citaActual.getTecnicoAsignado());
    }

    // ===== GUARDAR =====
    @FXML
    private void clickguardarCambios(ActionEvent event) {

        if (datePickerFecha.getValue() == null ||
                textfielHora.getText().isEmpty() ||
                choiseBoxTipoServicio.getValue() == null ||
                textfieldTecnicoEncargado.getText().isEmpty()) {

            mostrarAlerta(
                    "Campos incompletos",
                    "Debe completar todos los campos",
                    Alert.AlertType.WARNING
            );
            return;
        }

        citaActual.setFecha(datePickerFecha.getValue().toString());
        citaActual.setHora(textfielHora.getText());
        citaActual.setTipoMantenimiento(choiseBoxTipoServicio.getValue());
        citaActual.setTecnicoAsignado(textfieldTecnicoEncargado.getText());

        mostrarAlerta(
                "Cita actualizada",
                "La cita técnica fue modificada correctamente",
                Alert.AlertType.INFORMATION
        );

        mainController.saver("consultarCitasProgramadas.fxml");
    }

    // ===== REGRESAR =====
    @FXML
    private void clikcRegresarMenu(ActionEvent event) {
        mainController.saver("consultarCitasProgramadas.fxml");
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
