package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class registrarDocumentoRespaldoController
        implements Initializable, ControladorInyectable {

    private MainController mainController;
    private File archivoSeleccionado;

    @FXML private TextField textfieldProveedor;
    @FXML private TextField textFieldRUC;
    @FXML private TextField textFieldRazonSocial;
    @FXML private ChoiceBox<String> chioceBoxTipoDocumento;
    @FXML private TextField textfieldDocumento;
    @FXML private TextField textFieldNumeroDocumento;
    @FXML private DatePicker datePickerFechaEmision;
    @FXML private DatePicker datePickerFechaVencimiento;
    @FXML private Label labelArchivo;
    @FXML private Button buttonSeleccionarArchivo;
    @FXML private Button buttonGuardarDocumento;
    @FXML private Button buttonRegistrarOtroDocumento;
    @FXML private Button buttonRegresar;

    @Override
    public void setMainController(MainController main) {
        this.mainController = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Tipos de documentos
        chioceBoxTipoDocumento.getItems().addAll(
                "Contrato",
                "Factura",
                "Certificado",
                "Otro"
        );

        // Valor por defecto
        chioceBoxTipoDocumento.setValue("Contrato");

        // Controlar campo "Documento" solo cuando es OTRO
        chioceBoxTipoDocumento.setOnAction(e -> {
            if ("Otro".equals(chioceBoxTipoDocumento.getValue())) {
                textfieldDocumento.setDisable(false);
            } else {
                textfieldDocumento.clear();
                textfieldDocumento.setDisable(true);
            }
        });

        textfieldDocumento.setDisable(true);
        labelArchivo.setText("Ningún archivo seleccionado");
    }

    //Funcion para cargar el archivo
    @FXML
    private void seleccionarArchivo(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar documento");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Documentos PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("Documentos Word", "*.docx"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );

        archivoSeleccionado = fileChooser.showOpenDialog(null);

        if (archivoSeleccionado != null) {
            labelArchivo.setText(archivoSeleccionado.getName());
        } else {
            labelArchivo.setText("Ningún archivo seleccionado");
        }
    }

    @FXML
    private void guardarDocumento(ActionEvent event) {

        if (!validarCampos()) {
            return;
        }

        System.out.println("=== DOCUMENTO DE RESPALDO ===");
        System.out.println("Proveedor: " + textfieldProveedor.getText());
        System.out.println("RUC: " + textFieldRUC.getText());
        System.out.println("Razón Social: " + textFieldRazonSocial.getText());
        System.out.println("Tipo Documento: " + chioceBoxTipoDocumento.getValue());
        System.out.println("Documento: " + textfieldDocumento.getText());
        System.out.println("Número Documento: " + textFieldNumeroDocumento.getText());
        System.out.println("Fecha Emisión: " + datePickerFechaEmision.getValue());
        System.out.println("Fecha Vencimiento: " + datePickerFechaVencimiento.getValue());
        System.out.println("Archivo: " + archivoSeleccionado.getAbsolutePath());

        mostrarAlerta(
                "Registro exitoso",
                "El documento de respaldo fue registrado correctamente",
                Alert.AlertType.INFORMATION
        );
    }

    @FXML
    private void registrarOtroDocumento(ActionEvent event) {
        limpiarFormulario();
    }

    @FXML
    private void regresarMenu(ActionEvent event) {
        mainController.saver("registrarOrdenPago.fxml");
    }

    private boolean validarCampos() {

        if (textfieldProveedor.getText().isEmpty()
                || textFieldRUC.getText().isEmpty()
                || textFieldRazonSocial.getText().isEmpty()
                || textFieldNumeroDocumento.getText().isEmpty()
                || datePickerFechaEmision.getValue() == null) {

            mostrarAlerta(
                    "Campos obligatorios",
                    "Debe completar todos los campos obligatorios",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        if ("Otro".equals(chioceBoxTipoDocumento.getValue())
                && textfieldDocumento.getText().isEmpty()) {

            mostrarAlerta(
                    "Tipo de documento",
                    "Debe especificar el tipo de documento",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        if (archivoSeleccionado == null) {
            mostrarAlerta(
                    "Archivo requerido",
                    "Debe seleccionar un archivo de respaldo",
                    Alert.AlertType.WARNING
            );
            return false;
        }

        return true;
    }

    private void limpiarFormulario() {
        textFieldNumeroDocumento.clear();
        datePickerFechaEmision.setValue(null);
        datePickerFechaVencimiento.setValue(null);
        chioceBoxTipoDocumento.setValue("Contrato");
        textfieldDocumento.clear();
        textfieldDocumento.setDisable(true);
        archivoSeleccionado = null;
        labelArchivo.setText("Ningún archivo seleccionado");
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
