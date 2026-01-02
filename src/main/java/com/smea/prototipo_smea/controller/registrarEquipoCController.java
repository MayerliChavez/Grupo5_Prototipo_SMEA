package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class registrarEquipoCController
        implements Initializable, ControladorInyectable {

    // ===== CONTROLADOR PRINCIPAL =====
    private MainController mainController;

    // ===== COMPONENTES FXML =====
    @FXML private TextField textFieldNombreCliente;      // NO editable
    @FXML private TextField textFieldEquipo;       // Tipo de equipo
    @FXML private TextField textFieldMarca;      // Marca
    @FXML private TextField textFieldModelo;          // Modelo
    @FXML private TextField textFieldNumeroSerie;
    @FXML private TextArea textAreaDescripcion;

    @FXML private Button buttonGuardar;
    @FXML private Button buttonRegistrarOtro;
    @FXML private Button buttonRegresar;

    // ===== DATOS =====
    private String nombreCliente;

    // ===== INYECCIÓN MAIN CONTROLLER =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ===== INIT =====
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // El proveedor NO debe modificarse
        textFieldNombreCliente.setEditable(false);
        textFieldNombreCliente.setFocusTraversable(false);
        configurarTabulacion();

        textFieldEquipo.setOnAction(e -> textFieldNumeroSerie.requestFocus());
        textFieldNumeroSerie.setOnAction(e -> textFieldMarca.requestFocus());
        textFieldMarca.setOnAction(e -> textFieldModelo.requestFocus());
        textFieldModelo.setOnAction(e -> textAreaDescripcion.requestFocus());
        textAreaDescripcion.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                buttonGuardar.requestFocus();
            }
        });
    }

    private void configurarTabulacion() {
        configurarTab(textFieldEquipo, textFieldNumeroSerie);
        configurarTab(textFieldNumeroSerie, textFieldMarca);
        configurarTab(textFieldMarca, textFieldModelo);
        configurarTab(textFieldModelo, textAreaDescripcion);
        configurarTab(textAreaDescripcion, buttonGuardar);
    }

    private void configurarTab(Control actual, Control siguiente) {
        actual.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.TAB) {
                siguiente.requestFocus();
                event.consume();
            }
        });
    }

    // ===== RECIBIR PROVEEDOR DESDE OTRA PANTALLA =====
    public void setCliente(String nombreProveedor) {
        this.nombreCliente = nombreProveedor;
        textFieldNombreCliente.setText(nombreProveedor);
    }

    // ===== GUARDAR EQUIPO =====
    @FXML
    private void registrarProducto(ActionEvent event) {
        if (!validarCampos()) {
            return;
        }

        System.out.println("Equipo registrado:");
        System.out.println("Proveedor: " + nombreCliente);
        System.out.println("Tipo: " + textFieldMarca.getText());
        System.out.println("Marca: " + textFieldMarca.getText());
        System.out.println("Modelo: " + textFieldModelo.getText());
        System.out.println("Serie: " + textFieldNumeroSerie.getText());
        System.out.println("Descripción: " + textAreaDescripcion.getText());

        mostrarAlerta(
                "Registro exitoso",
                "El equipo fue registrado correctamente.",
                Alert.AlertType.INFORMATION
        );
    }

    @FXML
    private void registrarEquipoOtravez(ActionEvent event) {
        limpiarCampos();
    }

    // ===== REGRESAR =====
    @FXML
    private void regresarModuloProveedor(ActionEvent event) {
        mainController.saver("moduloCliente.fxml");
    }

    // ===== VALIDACIONES =====
    private boolean validarCampos() {

        if (textFieldEquipo.getText().isEmpty()
                || textFieldMarca.getText().isEmpty()
                || textFieldModelo.getText().isEmpty()
                || textFieldNumeroSerie.getText().isEmpty()) {

            mostrarAlerta(
                    "Campos obligatorios",
                    "Complete todos los campos requeridos.",
                    Alert.AlertType.WARNING
            );
            return false;
        }
        return true;
    }

    // ===== LIMPIAR (SIN BORRAR PROVEEDOR) =====
    private void limpiarCampos() {
        textFieldEquipo.clear();
        textFieldMarca.clear();
        textFieldModelo.clear();
        textFieldNumeroSerie.clear();
        textAreaDescripcion.clear();
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
