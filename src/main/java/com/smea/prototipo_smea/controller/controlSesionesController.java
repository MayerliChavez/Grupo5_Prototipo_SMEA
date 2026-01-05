package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.Sesion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class controlSesionesController implements Initializable, ControladorInyectable {

    private MainController mainController;

    // =================== FILTROS ===================
    @FXML private TextField textUsuario;
    @FXML private ChoiceBox<String> choiceEstado;

    // =================== TABLA ===================
    @FXML private TableView<Sesion> tableSesiones;
    @FXML private TableColumn<Sesion, String> colUsuario;
    @FXML private TableColumn<Sesion, String> colRol;
    @FXML private TableColumn<Sesion, String> colInicio;
    @FXML private TableColumn<Sesion, String> colFin;
    @FXML private TableColumn<Sesion, String> colIP;
    @FXML private TableColumn<Sesion, String> colEquipo;
    @FXML private TableColumn<Sesion, String> colEstado;

    // =================== DATA ===================
    private ObservableList<Sesion> listaSesiones = FXCollections.observableArrayList();

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // =================== INIT ===================
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarEstados();
        cargarSesionesIniciales();
    }

    private void configurarTabla() {
        colUsuario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getUsuario()));
        colRol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRol()));
        colInicio.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getInicio()));
        colFin.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFin()));
        colIP.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIp()));
        colEquipo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEquipo()));
        colEstado.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstado()));
    }

    private void cargarEstados() {
        choiceEstado.setItems(FXCollections.observableArrayList(
                "Todos", "Activa", "Cerrada", "Forzada"
        ));
        choiceEstado.setValue("Todos");
    }

    private void cargarSesionesIniciales() {
        listaSesiones.clear();

        listaSesiones.add(new Sesion(
                "jperez", "Administrador", "2026-01-04 08:00",
                "—", "192.168.1.10", "PC-ADMIN", "Activa"
        ));

        listaSesiones.add(new Sesion(
                "mlopez", "Técnico", "2026-01-03 15:30",
                "2026-01-03 17:00", "192.168.1.15", "LAPTOP-01", "Cerrada"
        ));

        tableSesiones.setItems(listaSesiones);
    }

    // =================== BUSCAR ===================
    @FXML
    private void buscarSesiones() {

        String usuario = textUsuario.getText().trim().toLowerCase();
        String estado = choiceEstado.getValue();

        if (usuario.isEmpty() && estado.equals("Todos")) {
            mostrarAlerta("Advertencia",
                    "Ingrese al menos un criterio de búsqueda",
                    Alert.AlertType.WARNING);
            return;
        }

        ObservableList<Sesion> filtradas = FXCollections.observableArrayList();

        for (Sesion s : listaSesiones) {
            boolean coincideUsuario = usuario.isEmpty() ||
                    s.getUsuario().toLowerCase().contains(usuario);

            boolean coincideEstado = estado.equals("Todos") ||
                    s.getEstado().equalsIgnoreCase(estado);

            if (coincideUsuario && coincideEstado) {
                filtradas.add(s);
            }
        }

        if (filtradas.isEmpty()) {
            mostrarAlerta("Sin resultados",
                    "No se encontraron sesiones con los criterios ingresados",
                    Alert.AlertType.INFORMATION);

            tableSesiones.setItems(listaSesiones);
        } else {
            tableSesiones.setItems(filtradas);
        }
    }

    // =================== CANCELAR ===================
    @FXML
    private void clickCancelar() {
                textUsuario.clear();
        choiceEstado.setValue("Todos");
        tableSesiones.setItems(listaSesiones);
    }

    // =================== REGRESAR ===================
    @FXML
    private void clickRegresar() {
        mainController.saver("accesoAuditoria.fxml");
    }

    // =================== ALERTAS ===================
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
