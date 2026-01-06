package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.OrdenPago;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;

public class modificarOrdenTrabajoController {

    // ===== FXML =====
    @FXML private TextField txtCliente;
    @FXML private TextField txtContacto;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtEmail;

    @FXML private ChoiceBox<String> choiceTipoTrabajo;
    @FXML private ChoiceBox<String> choicePrioridad;
    @FXML private TextArea txtDescripcion;

    @FXML private ChoiceBox<String> choiceTecnico;
    @FXML private DatePicker dateProgramada;
    @FXML private TextField txtObservaciones;

    @FXML private Button btnGuardar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnCancelar;
    @FXML private Button buttonRegresar;

    // ===== Orden seleccionada =====
    private OrdenPago orden;

    // ===== Inicializar =====
    @FXML
    public void initialize() {
        // Configurar opciones de ChoiceBox
        choiceTipoTrabajo.setItems(FXCollections.observableArrayList("Mantenimiento", "Instalación", "Reparación"));
        choicePrioridad.setItems(FXCollections.observableArrayList("Alta", "Media", "Baja"));
        choiceTecnico.setItems(FXCollections.observableArrayList("Técnico 1", "Técnico 2", "Técnico 3"));

        // Opciones iniciales pueden ser agregadas al abrir la ventana
    }

    // ===== SETTER para recibir la orden =====
    public void setOrden(OrdenPago orden) {
        this.orden = orden;
        cargarDatosEnFormulario();
    }

    // ===== Cargar datos de la orden en los campos =====
    private void cargarDatosEnFormulario() {
        if (orden != null) {
            txtCliente.setText(orden.getCliente());
            txtDireccion.setText(orden.getDireccion());
            txtContacto.setText(""); // No se tiene campo en OrdenPago, se deja vacío
            txtEmail.setText("");    // No se tiene campo en OrdenPago, se deja vacío

            choiceTipoTrabajo.setValue(orden.getTipoTrabajo());
            choicePrioridad.setValue(orden.getPrioridad());
            txtDescripcion.setText(""); // No se tiene descripción en OrdenPago, opcional

            choiceTecnico.setValue(orden.getTecnico());
            dateProgramada.setValue(orden.getFechaEmision());
            txtObservaciones.setText(orden.getObservaciones());
        }
    }

    // ===== BOTÓN GUARDAR =====
    @FXML
    private void guardarOrden() {
        if (orden != null) {
            // Actualizar datos de la orden
            orden.setCliente(txtCliente.getText());
            orden.setDireccion(txtDireccion.getText());
            orden.setTipoTrabajo(choiceTipoTrabajo.getValue());
            orden.setPrioridad(choicePrioridad.getValue());
            orden.setTecnico(choiceTecnico.getValue());
            orden.setFechaEmision(dateProgramada.getValue());
            orden.setObservaciones(txtObservaciones.getText());

            JOptionPane.showMessageDialog(null, "La orden se ha modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Cerrar ventana
            cerrarVentana();
        } else {
            JOptionPane.showMessageDialog(null, "No hay orden seleccionada para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== BOTÓN LIMPIAR =====
    @FXML
    private void limpiarFormulario() {
        txtCliente.clear();
        txtDireccion.clear();
        txtContacto.clear();
        txtEmail.clear();
        txtDescripcion.clear();
        txtObservaciones.clear();
        choiceTipoTrabajo.setValue(null);
        choicePrioridad.setValue(null);
        choiceTecnico.setValue(null);
        dateProgramada.setValue(null);
    }

    // ===== BOTÓN CANCELAR / ELIMINAR =====
    @FXML
    private void cancelar() {
        if (orden != null) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar esta orden?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                // Para este ejemplo eliminamos la referencia, pero en la práctica se eliminaría de la lista o base de datos
                orden = null;

                JOptionPane.showMessageDialog(null, "Orden eliminada.", "Información", JOptionPane.INFORMATION_MESSAGE);
                cerrarVentana();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay orden seleccionada para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== BOTÓN REGRESAR =====
    @FXML
    private void regresar() {
        cerrarVentana();
    }

    // ===== MÉTODO AUXILIAR =====
    private void cerrarVentana() {
        Stage stage = (Stage) btnGuardar.getScene().getWindow();
        stage.close();
    }
}
