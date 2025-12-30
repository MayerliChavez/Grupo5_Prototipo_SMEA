package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class registrarEquipoCController
        implements Initializable, ControladorInyectable {

    // ====== CONTROLADOR PRINCIPAL ======
    private MainController mainController;

    // ====== COMPONENTES FXML ======
    @FXML private TextField textFieldProveedor;
    @FXML private TextField textFieldProducto;
    @FXML private TextField textFieldProducto1; // Marca
    @FXML private TextField textFieldCosto;     // Modelo
    @FXML private TextField textFieldNumeroSerie;
    @FXML private TextArea textAreaDescripcion;

    // ====== INYECCIÓN MAIN CONTROLLER ======
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ====== INIT ======
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Aquí puedes cargar datos iniciales si lo necesitas
        // Ejemplo:
        // textFieldProveedor.setText("Cliente seleccionado");
    }

    // ====== GUARDAR EQUIPO ======
    @FXML
    private void registrarProducto(ActionEvent event) {

        if (!validarCampos()) {
            return;
        }

        String proveedor = textFieldProveedor.getText();
        String tipoEquipo = textFieldProducto.getText();
        String marca = textFieldProducto1.getText();
        String modelo = textFieldCosto.getText();
        String numeroSerie = textFieldNumeroSerie.getText();
        String descripcion = textAreaDescripcion.getText();

        System.out.println("Equipo registrado:");
        System.out.println("Proveedor: " + proveedor);
        System.out.println("Tipo: " + tipoEquipo);
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Serie: " + numeroSerie);
        System.out.println("Descripción: " + descripcion);

        mostrarAlerta(
                "Registro exitoso",
                "El equipo del cliente se registró correctamente.",
                Alert.AlertType.INFORMATION
        );

        limpiarCampos();
    }

    // ====== REGRESAR ======
    @FXML
    private void regresarModuloProveedor(ActionEvent event) {
        mainController.saver("moduloCliente.fxml");
    }

    // ====== VALIDACIONES ======
    private boolean validarCampos() {
        if (textFieldProveedor.getText().isEmpty()
                || textFieldProducto.getText().isEmpty()
                || textFieldProducto1.getText().isEmpty()
                || textFieldCosto.getText().isEmpty()
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

    // ====== LIMPIAR ======
    private void limpiarCampos() {
        textFieldProducto.clear();
        textFieldProducto1.clear();
        textFieldCosto.clear();
        textFieldNumeroSerie.clear();
        textAreaDescripcion.clear();
    }

    // ====== ALERTAS ======
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
