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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class verDetalleHistorialController
        implements Initializable, ControladorInyectable {

    private MainController mainController;

    // ================== CAMPOS ==================

    @FXML private TextField textfieldNombreProveedor;
    @FXML private TextField textMondo;
    @FXML private TextField textTipoPago;
    @FXML private DatePicker dateFecha;
    @FXML private TextArea textObservaciones;

    @FXML private TableView<DocumentoRespaldo> tableViewDocumentos;
    @FXML private TableColumn<DocumentoRespaldo, String> colNombreArchivo;
    @FXML private TableColumn<DocumentoRespaldo, String> colTipoArchivo;

    @FXML private Button buttonRegresar;

    private final ObservableList<DocumentoRespaldo> listaDocumentos =
            FXCollections.observableArrayList();

    // ================== INYECCIÓN ==================

    @Override
    public void setMainController(MainController main) {
        this.mainController = main;
    }

    // ================== INIT ==================

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

        // Doble clic para abrir documento
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

    // ================== DATOS SIMULADOS ==================

    private void cargarDatosOrden() {
        textfieldNombreProveedor.setText("Proveedor A");
        textMondo.setText("450.00");
        textTipoPago.setText("Transferencia");
        dateFecha.setValue(LocalDate.now());
        textObservaciones.setText(
                "Orden de pago aprobada correctamente."
        );

        // Solo lectura
        textfieldNombreProveedor.setEditable(false);
        textMondo.setEditable(false);
        textTipoPago.setEditable(false);
        textObservaciones.setEditable(false);
        dateFecha.setEditable(false);
    }

    private void cargarDocumentos() {
        listaDocumentos.addAll(
                new DocumentoRespaldo(
                        "Factura_001.pdf",
                        "Factura",
                        "C:/documentos/Factura_001.pdf"
                ),
                new DocumentoRespaldo(
                        "Contrato.pdf",
                        "Contrato",
                        "C:/documentos/Contrato.pdf"
                )
        );
    }

    // ================== ABRIR DOCUMENTO ==================

    private void abrirDocumento(DocumentoRespaldo doc) {
        try {
            File archivo = new File(doc.getRuta());
            if (archivo.exists()) {
                Desktop.getDesktop().open(archivo);
            } else {
                mostrarAlerta(
                        "Archivo no encontrado",
                        "No se encontró el archivo.",
                        Alert.AlertType.ERROR
                );
            }
        } catch (Exception e) {
            mostrarAlerta(
                    "Error",
                    "No se pudo abrir el archivo.",
                    Alert.AlertType.ERROR
            );
        }
    }

    // ================== REGRESAR ==================

    @FXML
    private void regresarConsultarOrdenPago(ActionEvent event) {
        mainController.saver("visualizarHistorialDePagos.fxml");
    }

    // ================== UTIL ==================

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
