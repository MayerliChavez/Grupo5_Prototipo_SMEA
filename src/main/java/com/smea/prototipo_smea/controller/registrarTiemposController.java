package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.OrdenPago;
import com.smea.prototipo_smea.clasesNormales.RegistroTiempo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class registrarTiemposController implements Initializable, ControladorInyectable {

    private MainController mainController;

    @FXML private ChoiceBox<OrdenPago> choiceOrdenes;
    @FXML private ChoiceBox<String> choiceTecnico;
    @FXML private DatePicker datePickerFecha;
    @FXML private TextField txtHoras;
    @FXML private TextArea txtDescripcion;

    private ObservableList<RegistroTiempo> listaTiempos = FXCollections.observableArrayList();
    private ObservableList<OrdenPago> listaOrdenes = FXCollections.observableArrayList(); // se puede pasar desde mainController

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializar ChoiceBox de técnicos
        choiceTecnico.setItems(FXCollections.observableArrayList("Técnico 1", "Técnico 2", "Técnico 3"));

        // Cargar órdenes existentes (ejemplo de prueba)
        listaOrdenes.addAll(
                new OrdenPago("OT-001", "Proveedor A", LocalDate.of(2026,1,5), 250.0, "Transferencia", "Pago pendiente", "Cliente A", "Av. Quito 123", "Mantenimiento", "Alta", "Técnico 1", "Pendiente"),
                new OrdenPago("OT-002", "Proveedor B", LocalDate.of(2026,1,6), 400.0, "Efectivo", "Pago completado", "Cliente B", "Calle Loja 45", "Instalación", "Media", "Técnico 2", "Completado")
        );

        choiceOrdenes.setItems(listaOrdenes);
    }

    @FXML
    private void guardarTiempo() {
        OrdenPago ordenSeleccionada = choiceOrdenes.getValue();
        String tecnico = choiceTecnico.getValue();
        LocalDate fecha = datePickerFecha.getValue();
        String descripcion = txtDescripcion.getText();
        double horas;

        try {
            horas = Double.parseDouble(txtHoras.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un número válido para las horas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (ordenSeleccionada == null || tecnico == null || fecha == null) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        RegistroTiempo registro = new RegistroTiempo(
                ordenSeleccionada.getCodigo(),
                tecnico,
                fecha,
                horas,
                descripcion
        );

        listaTiempos.add(registro);
        JOptionPane.showMessageDialog(null, "Tiempo registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiarFormulario();
    }

    @FXML
    private void limpiarFormulario() {
        choiceOrdenes.setValue(null);
        choiceTecnico.setValue(null);
        datePickerFecha.setValue(null);
        txtHoras.clear();
        txtDescripcion.clear();
    }

    public ObservableList<RegistroTiempo> getListaTiempos() {
        return listaTiempos;
    }
}
