package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.AccionUsuario;
import com.smea.prototipo_smea.controller.ControladorInyectable;
import com.smea.prototipo_smea.controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class accionesUsuariosController implements Initializable, ControladorInyectable {

    private MainController mainController;

    // ================== FXML ==================

    @FXML private TableView<AccionUsuario> tablaAcciones;

    @FXML private TableColumn<AccionUsuario, String> colUsuario;
    @FXML private TableColumn<AccionUsuario, String> colRol;
    @FXML private TableColumn<AccionUsuario, String> colModulo;
    @FXML private TableColumn<AccionUsuario, String> colAccion;
    @FXML private TableColumn<AccionUsuario, String> colFecha;

    @FXML private TextField textFieldUsuario;
    @FXML private ChoiceBox<String> choiseBoxModulo;
    @FXML private ChoiceBox<String> choiseBoxAccion;
    @FXML private DatePicker datePickerFecha;

    @FXML private Button buttonBuscar;
    @FXML private Button buttonLimpiar;
    @FXML private Button buttonVerDetalles;
    @FXML private Button buttonExportar;
    @FXML private Button buttonRegresar;

    // ================== DATA ==================

    private ObservableList<AccionUsuario> listaAcciones;

    // ================== INYECCIÓN ==================

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ================== INIT ==================

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabla();
        inicializarChoiceBox();
        cargarDatosIniciales();
    }

    // ================== CONFIGURACIÓN TABLA ==================

    private void inicializarTabla() {
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        colModulo.setCellValueFactory(new PropertyValueFactory<>("modulo"));
        colAccion.setCellValueFactory(new PropertyValueFactory<>("accion"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    }

    // ================== CHOICEBOX ==================

    private void inicializarChoiceBox() {
        choiseBoxModulo.setItems(FXCollections.observableArrayList(
                "Inventario",
                "Mantenimiento",
                "Acceso",
                "Auditoría"
        ));

        choiseBoxAccion.setItems(FXCollections.observableArrayList(
                "LOGIN",
                "LOGOUT",
                "INSERT",
                "UPDATE",
                "DELETE",
                "CONSULTA"
        ));
    }

    // ================== DATOS INICIALES ==================

    private void cargarDatosIniciales() {
        listaAcciones = FXCollections.observableArrayList(
                new AccionUsuario("admin", "Administrador", "Acceso", "LOGIN", "2026-01-04 10:32"),
                new AccionUsuario("bodeguero", "Operador", "Inventario", "UPDATE", "2026-01-04 11:10"),
                new AccionUsuario("auditor", "Auditor", "Auditoría", "CONSULTA", "2026-01-04 11:40")
        );

        tablaAcciones.setItems(listaAcciones);
    }

    // ================== ACCIONES ==================

    @FXML
    private void buscarAcciones() {
        String usuario = textFieldUsuario.getText();
        String modulo = choiseBoxModulo.getValue();
        String accion = choiseBoxAccion.getValue();
        LocalDate fecha = datePickerFecha.getValue();

        if ((usuario == null || usuario.isBlank()) &&
                modulo == null &&
                accion == null &&
                fecha == null) {

            mostrarMensaje(
                    "Filtros vacíos",
                    "Debe ingresar al menos un criterio de búsqueda.",
                    Alert.AlertType.WARNING
            );
            return;
        }

        ObservableList<AccionUsuario> filtrados = FXCollections.observableArrayList();

        for (AccionUsuario a : listaAcciones) {
            boolean coincide = true;

            if (usuario != null && !usuario.isBlank())
                coincide &= a.getUsuario().toLowerCase().contains(usuario.toLowerCase());

            if (modulo != null)
                coincide &= a.getModulo().equals(modulo);

            if (accion != null)
                coincide &= a.getAccion().equals(accion);

            if (fecha != null)
                coincide &= a.getFecha().contains(fecha.toString());

            if (coincide)
                filtrados.add(a);
        }

        if (filtrados.isEmpty()) {
            mostrarMensaje(
                    "Sin resultados",
                    "No se encontraron acciones con los filtros seleccionados.",
                    Alert.AlertType.INFORMATION
            );
            tablaAcciones.setItems(listaAcciones);
        } else {
            tablaAcciones.setItems(filtrados);
        }
    }

    @FXML
    private void verDetalle() {
        AccionUsuario seleccion = tablaAcciones.getSelectionModel().getSelectedItem();

        if (seleccion == null) {
            mostrarMensaje(
                    "Sin selección",
                    "Seleccione una acción de la tabla.",
                    Alert.AlertType.WARNING
            );
            return;
        }

        String detalle =
                "Usuario: " + seleccion.getUsuario() + "\n" +
                        "Rol: " + seleccion.getRol() + "\n" +
                        "Módulo: " + seleccion.getModulo() + "\n" +
                        "Acción: " + seleccion.getAccion() + "\n" +
                        "Fecha: " + seleccion.getFecha();

        mostrarMensaje("Detalle de Acción", detalle, Alert.AlertType.INFORMATION);
    }

    @FXML
    private void limpiarFiltros() {
        textFieldUsuario.clear();
        choiseBoxModulo.setValue(null);
        choiseBoxAccion.setValue(null);
        datePickerFecha.setValue(null);
        tablaAcciones.setItems(listaAcciones);
    }

    @FXML
    private void exportar() {
        mostrarMensaje(
                "Exportar",
                "Funcionalidad de exportación pendiente.",
                Alert.AlertType.INFORMATION
        );
    }

    @FXML
    private void regresar() {
        mainController.saver("registroAuditoria.fxml");
    }

    // ================== UTIL ==================

    private void mostrarMensaje(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
