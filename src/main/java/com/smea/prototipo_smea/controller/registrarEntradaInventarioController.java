package com.smea.prototipo_smea.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class registrarEntradaInventarioController implements Initializable, ControladorInyectable {
    // ================== CONTROLADOR PRINCIPAL ==================
    private MainController mainController;

    // ================= CAMPOS =================
    @FXML private TextField textFieldCodigoItem;
    @FXML private TextField textFieldNombreItem;
    @FXML private TextField textFieldCategoria;
    @FXML private TextField textFieldStockActual;
    @FXML private TextField textFieldCantidadActual;

    @FXML private ChoiceBox<String> chiceBoxTipoItem;
    @FXML private ChoiceBox<String> chiceBoxMotivo;

    @FXML private DatePicker datePickerFechaEntrada;
    @FXML private Button buttonBuscar;
    @FXML private Button buttonRegresar;
    @FXML private Button buttonRegistrarEntrada;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ================= INIT =================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicializarChoiceBox();
        bloquearCamposEntrada();
        datePickerFechaEntrada.setValue(LocalDate.now());

        textFieldCodigoItem.setOnAction(e-> buttonBuscar.requestFocus());
        textFieldCantidadActual.setOnAction(e-> chiceBoxTipoItem.requestFocus());
        chiceBoxTipoItem.setOnAction(e-> chiceBoxMotivo.requestFocus());
        chiceBoxMotivo.setOnAction(e-> datePickerFechaEntrada.requestFocus());
        datePickerFechaEntrada.setOnAction(e-> buttonRegistrarEntrada.requestFocus());
    }

    // ================== TABULACIÓN ==================
    private void configurarTabulacion() {
        configurarTab(textFieldCodigoItem, buttonBuscar);
        configurarTab(textFieldCantidadActual, chiceBoxTipoItem);
        configurarTab(chiceBoxTipoItem, chiceBoxMotivo);
        configurarTab(chiceBoxMotivo, datePickerFechaEntrada);
        configurarTab(datePickerFechaEntrada, buttonRegistrarEntrada);
    }

    private void configurarTab(Control actual, Control siguiente) {
        actual.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.TAB) {
                siguiente.requestFocus();
                event.consume();
            }
        });
    }

    // ================= INICIALIZAR =================
    private void inicializarChoiceBox() {

        chiceBoxTipoItem.setItems(
                FXCollections.observableArrayList(
                        "Herramienta", "Repuesto"
                )
        );

        chiceBoxMotivo.setItems(
                FXCollections.observableArrayList(
                        "Compra",
                        "Reposición",
                        "Devolución",
                        "Ajuste de inventario",
                        "Donación"
                )
        );
    }

    // ================= BUSCAR ITEM =================
    @FXML
    private void buscarItem(ActionEvent event) {

        String codigo = textFieldCodigoItem.getText();

        if (codigo == null || codigo.trim().isEmpty()) {
            mostrarAlerta("Aviso",
                    "Ingrese el código del ítem.",
                    Alert.AlertType.WARNING);
            return;
        }

        // 🔹 SIMULACIÓN DE BÚSQUEDA (reemplazar con BD)
        if (codigo.equalsIgnoreCase("ITM-001")) {

            textFieldNombreItem.setText("Taladro Industrial");
            textFieldCategoria.setText("Herramientas");
            textFieldStockActual.setText("15");

            habilitarCamposEntrada();

        } else {
            mostrarAlerta("Aviso",
                    "El ítem no existe en el sistema.",
                    Alert.AlertType.WARNING);
            limpiarCampos();
            bloquearCamposEntrada();
        }
    }

    // ================= REGISTRAR ENTRADA =================
    @FXML
    private void registrarEntrada(ActionEvent event) {

        if (!validarCampos()) {
            return;
        }

        int stockActual = Integer.parseInt(textFieldStockActual.getText());
        int cantidadEntrada = Integer.parseInt(textFieldCantidadActual.getText());

        int nuevoStock = stockActual + cantidadEntrada;

        // 🔹 AQUÍ IRÍA LA BD (UPDATE inventario SET stock = nuevoStock)

        mostrarAlerta("Éxito",
                "Entrada registrada correctamente.\nStock actualizado: " + nuevoStock,
                Alert.AlertType.INFORMATION);

        limpiarCampos();
        bloquearCamposEntrada();
    }

    // ================= VALIDACIONES =================
    private boolean validarCampos() {

        if (chiceBoxTipoItem.getValue() == null) {
            mostrarAlerta("Aviso",
                    "Seleccione el tipo de ítem.",
                    Alert.AlertType.WARNING);
            return false;
        }

        if (chiceBoxMotivo.getValue() == null) {
            mostrarAlerta("Aviso",
                    "Seleccione el motivo de la entrada.",
                    Alert.AlertType.WARNING);
            return false;
        }

        if (datePickerFechaEntrada.getValue() == null) {
            mostrarAlerta("Aviso",
                    "Seleccione la fecha de entrada.",
                    Alert.AlertType.WARNING);
            return false;
        }

        String cantidadTexto = textFieldCantidadActual.getText();

        if (cantidadTexto == null || cantidadTexto.trim().isEmpty()) {
            mostrarAlerta("Aviso",
                    "Ingrese la cantidad.",
                    Alert.AlertType.WARNING);
            return false;
        }

        try {
            int cantidad = Integer.parseInt(cantidadTexto);
            if (cantidad <= 0) {
                mostrarAlerta("Aviso",
                        "La cantidad debe ser mayor a cero.",
                        Alert.AlertType.WARNING);
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Aviso",
                    "La cantidad debe ser numérica.",
                    Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    // ================= UTILIDADES =================
    private void bloquearCamposEntrada() {

        textFieldNombreItem.setEditable(false);
        textFieldCategoria.setEditable(false);
        textFieldStockActual.setEditable(false);

        textFieldCantidadActual.setDisable(true);
        chiceBoxTipoItem.setDisable(true);
        chiceBoxMotivo.setDisable(true);
        datePickerFechaEntrada.setDisable(true);
    }

    private void habilitarCamposEntrada() {

        textFieldCantidadActual.setDisable(false);
        chiceBoxTipoItem.setDisable(false);
        chiceBoxMotivo.setDisable(false);
        datePickerFechaEntrada.setDisable(false);
    }

    private void limpiarCampos() {

        textFieldNombreItem.clear();
        textFieldCategoria.clear();
        textFieldStockActual.clear();
        textFieldCantidadActual.clear();

        chiceBoxTipoItem.setValue(null);
        chiceBoxMotivo.setValue(null);
        datePickerFechaEntrada.setValue(LocalDate.now());
    }

    // ================= REGRESAR =================
    @FXML
    private void regresarModuloProveedor(ActionEvent event) {
        System.out.println("Regresar al módulo anterior");
        mainController.saver("movimientosInventario.fxml");
    }

    // ================= ALERTAS =================
    private void mostrarAlerta(String titulo,
                               String mensaje,
                               Alert.AlertType tipo) {

        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
