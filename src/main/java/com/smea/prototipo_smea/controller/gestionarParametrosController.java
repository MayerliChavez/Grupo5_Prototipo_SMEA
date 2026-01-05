package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.Parametro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class gestionarParametrosController implements Initializable, ControladorInyectable {

    // ===== CONTROLADOR CENTRAL =====
    private MainController mainController;

    // ===== COMPONENTES FXML =====
    @FXML private TextField textFielBuscarParametro;
    @FXML private TableView<Parametro> tableViewTablaBuscarParametro;
    @FXML private TableColumn<Parametro, String> tableColumnNombre;
    @FXML private TableColumn<Parametro, String> tableColumnValor;

    @FXML private TextField textFieldNombre;
    @FXML private TextField textFieldTipo;
    @FXML private TextField textFieldValor;
    @FXML private TextArea textAreaDescripcion;

    @FXML private Button buttonGuardarCambios;

    // ===== LISTA DE PARÁMETROS (simula BD) =====
    private ObservableList<Parametro> listaParametros = FXCollections.observableArrayList();

    // ===== INYECCIÓN MAIN =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ===== INICIALIZACIÓN =====
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarTabla();
        cargarParametros();
        configurarSeleccionTabla();
        bloquearCampos();

        textFielBuscarParametro.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.isEmpty()) {
                tableViewTablaBuscarParametro.setItems(listaParametros);
            }
        });
    }

    // ================= TABLA =================
    private void configurarTabla() {
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        tableViewTablaBuscarParametro.setItems(listaParametros);
    }

    private void cargarParametros() {
        listaParametros.addAll(
                new Parametro("IVA", "15", "NUMERICO", "Impuesto al valor agregado"),
                new Parametro("DIAS_FACTURA", "30", "NUMERICO", "Días límite de pago"),
                new Parametro("MANT_AUTO", "true", "BOOLEANO", "Mantenimiento automático")
        );
    }

    // ================= SELECCIÓN =================
    private void configurarSeleccionTabla() {
        tableViewTablaBuscarParametro.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, parametro) -> {
                    if (parametro != null) {
                        cargarFormulario(parametro);
                    }
                });
    }

    private void cargarFormulario(Parametro p) {
        textFieldNombre.setText(p.getNombre());
        textFieldTipo.setText(p.getTipo());
        textFieldValor.setText(p.getValor());
        textAreaDescripcion.setText(p.getDescripcion());
    }

    private void bloquearCampos() {
        textFieldNombre.setEditable(false);
        textFieldTipo.setEditable(false);
    }

    // ================= BUSCAR =================
    @FXML
    private void buscarItem(ActionEvent event) {

        String texto = textFielBuscarParametro.getText();

        if (texto == null || texto.trim().isEmpty()) {
            mostrarAlerta(
                    "Campo vacío",
                    "Debe ingresar el nombre del parámetro a buscar"
            );

            tableViewTablaBuscarParametro.setItems(listaParametros);
            return;
        }

        texto = texto.toLowerCase();

        ObservableList<Parametro> filtrados = FXCollections.observableArrayList();

        for (Parametro p : listaParametros) {
            if (p.getNombre().toLowerCase().contains(texto)) {
                filtrados.add(p);
            }
        }

        if (filtrados.isEmpty()) {
            mostrarAlerta(
                    "Sin resultados",
                    "No se encontró ningún parámetro con ese nombre"
            );


            tableViewTablaBuscarParametro.setItems(listaParametros);
        } else {

            tableViewTablaBuscarParametro.setItems(filtrados);
        }
    }


    // ================= GUARDAR =================
    @FXML
    private void guardarOrden(ActionEvent event) {
        Parametro seleccionado =
                tableViewTablaBuscarParametro.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Aviso","Debe seleccionar un parámetro");
            return;
        }

        if (!validarValor(seleccionado.getTipo(), textFieldValor.getText())) {
            mostrarAlerta("Aviso","Valor inválido para el tipo " + seleccionado.getTipo());
            return;
        }

        seleccionado.setValor(textFieldValor.getText());
        seleccionado.setDescripcion(textAreaDescripcion.getText());

        tableViewTablaBuscarParametro.refresh();

        mostrarInfo("Parámetro actualizado correctamente");
        limpiarFormulario();
    }

    // ================= VALIDACIONES =================
    private boolean validarValor(String tipo, String valor) {
        switch (tipo) {
            case "NUMERICO":
                return valor.matches("\\d+(\\.\\d+)?");
            case "BOOLEANO":
                return valor.equalsIgnoreCase("true") || valor.equalsIgnoreCase("false");
            default:
                return true;
        }
    }

    // ================= UTILIDADES =================
    private void limpiarFormulario() {
        textFieldNombre.clear();
        textFieldTipo.clear();
        textFieldValor.clear();
        textAreaDescripcion.clear();
        tableViewTablaBuscarParametro.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }


    private void mostrarInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.show();
    }

    // ================= NAVEGACIÓN =================
    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("moduloAdministracionSistema.fxml");
    }
}
