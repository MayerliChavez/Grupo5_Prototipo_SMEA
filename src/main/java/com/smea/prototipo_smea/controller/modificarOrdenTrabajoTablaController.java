package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.OrdenPago;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class modificarOrdenTrabajoTablaController implements Initializable, ControladorInyectable {

    private MainController mainController;

    // ===== FXML =====
    @FXML private TableView<OrdenPago> tableViewTablaContenido;
    @FXML private TableColumn<OrdenPago, String> tableColumnCodigo;
    @FXML private TableColumn<OrdenPago, String> tableColumnCliente;
    @FXML private TableColumn<OrdenPago, String> tableColumnDireccion;
    @FXML private TableColumn<OrdenPago, String> tableColumnTipoTrabajo;
    @FXML private TableColumn<OrdenPago, String> tableColumnPrioridad;
    @FXML private TableColumn<OrdenPago, String> tableColumnTecnicoResponsable;
    @FXML private TableColumn<OrdenPago, String> tableColumnEstado;

    @FXML private ChoiceBox<String> choiseBoxEstado;
    @FXML private DatePicker datePickerRangoFecha;

    // Botones
    @FXML private Button buttonlimpiar;
    @FXML private Button buttonVerDetalle;

    // ===== LISTAS =====
    private ObservableList<OrdenPago> listaOrdenes;
    private ObservableList<OrdenPago> listaFiltrada;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Inicializar ChoiceBox de estado
        choiseBoxEstado.setItems(FXCollections.observableArrayList(
                "Todos", "Pendiente", "En Proceso", "Completado"
        ));
        choiseBoxEstado.setValue("Todos");

        // Inicializar listas
        listaOrdenes = FXCollections.observableArrayList();
        listaFiltrada = FXCollections.observableArrayList();
        tableViewTablaContenido.setItems(listaFiltrada);

        // Configurar columnas
        tableColumnCodigo.setCellValueFactory(cell -> cell.getValue().codigoProperty());
        tableColumnCliente.setCellValueFactory(cell -> cell.getValue().clienteProperty());
        tableColumnDireccion.setCellValueFactory(cell -> cell.getValue().direccionProperty());
        tableColumnTipoTrabajo.setCellValueFactory(cell -> cell.getValue().tipoTrabajoProperty());
        tableColumnPrioridad.setCellValueFactory(cell -> cell.getValue().prioridadProperty());
        tableColumnTecnicoResponsable.setCellValueFactory(cell -> cell.getValue().tecnicoProperty());
        tableColumnEstado.setCellValueFactory(cell -> cell.getValue().estadoProperty());

        // Cargar datos de prueba
        cargarDatosPrueba();

        // Mostrar todos al inicio
        listaFiltrada.setAll(listaOrdenes);
    }

    // ===== BOTÓN BUSCAR POR ESTADO =====
    @FXML
    private void buscarPorCodigo(ActionEvent event) {
        filtrar(choiseBoxEstado.getValue(), datePickerRangoFecha.getValue());
    }

    // ===== BOTÓN BUSCAR POR FECHA =====
    @FXML
    private void buscarPorFecha(ActionEvent event) {
        filtrar(choiseBoxEstado.getValue(), datePickerRangoFecha.getValue());
    }

    // ===== FILTRAR =====
    private void filtrar(String estado, LocalDate fecha) {
        List<OrdenPago> filtrados = listaOrdenes.stream()
                .filter(o -> estado.equals("Todos") || o.getEstado().equals(estado))
                .filter(o -> fecha == null || o.getFechaEmision().equals(fecha))
                .collect(Collectors.toList());

        if (filtrados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron resultados para la búsqueda.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }

        listaFiltrada.setAll(filtrados);
    }

    // ===== BOTÓN LIMPIAR =====
    @FXML
    private void limpiarTabla(ActionEvent event) {
        datePickerRangoFecha.setValue(null);
        choiseBoxEstado.setValue("Todos");
        listaFiltrada.setAll(listaOrdenes);
    }

    // ===== BOTÓN MODIFICAR / VER DETALLE =====
    @FXML
    private void modificar (ActionEvent event) {
        OrdenPago seleccionado = tableViewTablaContenido.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                // Cargar la ventana de modificación
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/modificarOrdenTrabajo.fxml"));
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Modificar Orden de Trabajo");
                stage.setScene(new Scene(loader.load()));

                // Pasar la orden seleccionada al controlador de modificación
                modificarOrdenTrabajoController controlador = loader.getController();
                controlador.setOrden(seleccionado);

                stage.showAndWait();

                // Después de cerrar la ventana, refrescar la tabla
                tableViewTablaContenido.refresh();

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "No se pudo abrir la ventana de modificación.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una orden para modificar.", "Información", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ===== BOTÓN REGRESAR =====
    @FXML
    private void regresarModuloProveedor(ActionEvent event) {
        mainController.saver("planificacionMantenimiento.fxml");
    }

    // ===== DATOS DE PRUEBA =====
    private void cargarDatosPrueba() {
        listaOrdenes.add(new OrdenPago(
                "OT-001", "Proveedor A", LocalDate.of(2026,1,5), 250.0,
                "Transferencia", "Pago pendiente", "Cliente A", "Av. Quito 123",
                "Mantenimiento", "Alta", "Técnico 1", "Pendiente"
        ));
        listaOrdenes.add(new OrdenPago(
                "OT-002", "Proveedor B", LocalDate.of(2026,1,6), 400.0,
                "Efectivo", "Pago completado", "Cliente B", "Calle Loja 45",
                "Instalación", "Media", "Técnico 2", "Completado"
        ));
        listaOrdenes.add(new OrdenPago(
                "OT-003", "Proveedor C", LocalDate.of(2026,1,7), 150.0,
                "Cheque", "Requiere aprobación", "Cliente C", "Av. Amazonas 78",
                "Reparación", "Baja", "Técnico 3", "En Proceso"
        ));
        listaOrdenes.add(new OrdenPago(
                "OT-004", "Proveedor D", LocalDate.of(2026,1,8), 300.0,
                "Transferencia", "Pago parcial", "Cliente D", "Calle 9 de Octubre 200",
                "Mantenimiento", "Alta", "Técnico 1", "Pendiente"
        ));
    }
}
