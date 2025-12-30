package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.DocumentoRespaldo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class verDetallesOrdenPagoController
        implements Initializable, ControladorInyectable {

    private MainController mainController;

    @FXML private TextField textfieldNombreProveedor;
    @FXML private TextField textMondo;
    @FXML private TextField textTipoPago;
    @FXML private DatePicker dateFecha;
    @FXML private TextArea textObservaciones;

    @FXML private TableView<DocumentoRespaldo> tableViewDocumentos;
    @FXML private TableColumn<DocumentoRespaldo, String> colNombreArchivo;
    @FXML private TableColumn<DocumentoRespaldo, String> colTipoArchivo;

    @FXML private Button buttonAceptarOrdenPago;
    @FXML private Button buttonAnularOrden;
    @FXML private Button buttonRegresar;

    private final ObservableList<DocumentoRespaldo> listaDocumentos =
            FXCollections.observableArrayList();

    // =====================================================
    // INYECCIÓN DEL MAIN CONTROLLER (CLAVE)
    // =====================================================
    @Override
    public void setMainController(MainController main) {
        this.mainController = main;
        configurarPermisos();
    }

    // =====================================================
    // INITIALIZE
    // =====================================================
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colNombreArchivo.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getNombre()
                )
        );

        colTipoArchivo.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getTipo()
                )
        );

        tableViewDocumentos.setItems(listaDocumentos);

        tableViewDocumentos.setRowFactory(tv -> {
            TableRow<DocumentoRespaldo> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()
                        && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    abrirDocumento(row.getItem());
                }
            });
            return row;
        });

        cargarDatosOrden();
        cargarDocumentos();
    }

    // =====================================================
    // PERMISOS POR ROL
    // =====================================================
    private void configurarPermisos() {

        // Ocultar por defecto
        buttonAceptarOrdenPago.setVisible(false);
        buttonAnularOrden.setVisible(false);

        if (mainController == null) return;

        String rol = mainController.getRolUsuario();

        if ("Administrador".equalsIgnoreCase(rol)) {
            buttonAceptarOrdenPago.setVisible(true);
            buttonAnularOrden.setVisible(true);
        }
    }

    // =====================================================
    // DATOS SIMULADOS
    // =====================================================
    private void cargarDatosOrden() {
        textfieldNombreProveedor.setText("Proveedor XYZ");
        textMondo.setText("450.00");
        textTipoPago.setText("Transferencia");
        dateFecha.setValue(java.time.LocalDate.now());
        textObservaciones.setText(
                "Pago correspondiente a servicios de mantenimiento."
        );
    }

    private void cargarDocumentos() {
        listaDocumentos.addAll(
                new DocumentoRespaldo("Factura_001.pdf", "Factura", "C:/documentos/Factura_001.pdf"),
                new DocumentoRespaldo("Contrato.pdf", "Contrato", "C:/documentos/Contrato.pdf")
        );
    }

    // =====================================================
    // ACCIONES
    // =====================================================
    private void abrirDocumento(DocumentoRespaldo doc) {
        try {
            File archivo = new File(doc.getRuta());
            if (archivo.exists()) {
                Desktop.getDesktop().open(archivo);
            } else {
                mostrarAlerta("Archivo no encontrado",
                        "No se encontró el archivo.",
                        Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            mostrarAlerta("Error",
                    "No se pudo abrir el archivo.",
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void aceptarOrden(ActionEvent event) {
        mostrarAlerta("Orden aprobada",
                "La orden fue aprobada correctamente.",
                Alert.AlertType.INFORMATION);
    }

    @FXML
    private void anularOrden(ActionEvent event) {
        mostrarAlerta("Orden anulada",
                "La orden fue anulada.",
                Alert.AlertType.WARNING);
    }

    @FXML
    private void regresarConsultarOrdenPago(ActionEvent event) {
        mainController.saver("consultarDatosOrdenPago.fxml");
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
