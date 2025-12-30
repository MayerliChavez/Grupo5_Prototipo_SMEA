package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class registrarProductoQueOfreceProveedorController
        implements Initializable, ControladorInyectable {

    private MainController mainController;

    @FXML private ChoiceBox<String> chiceBoxProveedor;
    @FXML private TextField textFieldProducto;
    @FXML private TextArea textAreaDescripcion;
    @FXML private TextField textFieldCosto;
    @FXML private ChoiceBox<String> choicEBoxCategoria;

    @FXML private Button buttonRegistrarProducto;
    @FXML private Button buttonRegresar;

    @Override
    public void setMainController(MainController main) {
        this.mainController = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Proveedores (ejemplo – luego vendrá de BD)
        chiceBoxProveedor.getItems().addAll(
                "Proveedor A",
                "Proveedor B",
                "Proveedor C"
        );

        // Categorías
        choicEBoxCategoria.getItems().addAll(
                "Repuestos",
                "Lubricantes",
                "Herramientas",
                "Servicios",
                "Accesorios"
        );

        // Orden de TAB / ENTER
        textFieldProducto.setOnAction(e -> textFieldCosto.requestFocus());
        textFieldCosto.setOnAction(e -> textAreaDescripcion.requestFocus());
        textAreaDescripcion.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                choicEBoxCategoria.requestFocus();
            }
        });
    }

    @FXML
    private void registrarProducto(ActionEvent event) {

        if (!validarCampos()) {
            return;
        }

        // Obtener datos
        String proveedor = chiceBoxProveedor.getValue();
        String producto = textFieldProducto.getText();
        String descripcion = textAreaDescripcion.getText();
        String costo = textFieldCosto.getText();
        String categoria = choicEBoxCategoria.getValue();

        // Simulación de guardado
        System.out.println("=== REGISTRO PRODUCTO ===");
        System.out.println("Proveedor: " + proveedor);
        System.out.println("Producto: " + producto);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Costo: " + costo);
        System.out.println("Categoría: " + categoria);

        mostrarAlerta(
                "Registro exitoso",
                "El producto fue registrado correctamente",
                Alert.AlertType.INFORMATION
        );

        regresarModuloProveedor();
    }

    @FXML
    private void regresarModuloProveedor() {
        mainController.saver("moduloProveedor.fxml");
    }

    private boolean validarCampos() {

        if (chiceBoxProveedor.getValue() == null
                || textFieldProducto.getText().isEmpty()
                || textAreaDescripcion.getText().isEmpty()
                || textFieldCosto.getText().isEmpty()
                || choicEBoxCategoria.getValue() == null) {

            mostrarAlerta(
                    "Campos obligatorios",
                    "Debe completar todos los campos",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        try {
            Double.parseDouble(textFieldCosto.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta(
                    "Costo inválido",
                    "Ingrese un valor numérico válido",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
