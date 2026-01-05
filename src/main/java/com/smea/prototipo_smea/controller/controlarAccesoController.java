package com.smea.prototipo_smea.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.smea.prototipo_smea.clasesNormales.PermisoAcceso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class controlarAccesoController implements Initializable, ControladorInyectable {

    private MainController mainController;

    @FXML
    private AnchorPane paneFondo;

    @FXML
    private ChoiceBox<String> choiseBoxRol;

    @FXML
    private Button butoonCargar;

    @FXML
    private TableView<PermisoAcceso> tableViewTablaContenido;

    @FXML
    private TableColumn<PermisoAcceso, String> tableColumnModulo;

    @FXML
    private TableColumn<PermisoAcceso, String> tableColumnSubModulo;

    @FXML
    private TableColumn<PermisoAcceso, String> tableColumnPermiso;

    @FXML
    private TableColumn<PermisoAcceso, Boolean> tableColumnAcceso;

    @FXML
    private Button buttonGuardar;

    @FXML
    private Button buttonLimpiar;

    @FXML
    private Button buttonRegresar;

    private ObservableList<PermisoAcceso> listaPermisos = FXCollections.observableArrayList();

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarRoles();
    }

    // ===============================
    // CONFIGURACIÓN INICIAL
    // ===============================

    private void configurarTabla() {
        tableColumnModulo.setCellValueFactory(data -> data.getValue().moduloProperty());
        tableColumnSubModulo.setCellValueFactory(data -> data.getValue().subModuloProperty());
        tableColumnPermiso.setCellValueFactory(data -> data.getValue().permisoProperty());
        tableColumnAcceso.setCellValueFactory(data -> data.getValue().accesoProperty());

        tableColumnAcceso.setCellFactory(CheckBoxTableCell.forTableColumn(tableColumnAcceso));
        tableViewTablaContenido.setEditable(true);
    }

    private void cargarRoles() {
        choiseBoxRol.setItems(FXCollections.observableArrayList(
                "Administrador",
                "Gerente",
                "Técnico",
                "Bodeguero"
        ));
    }

    // ===============================
    // EVENTOS
    // ===============================

    @FXML
    private void cargarPermisosPorRol() {
        if (choiseBoxRol.getValue() == null) {
            mostrarAlerta("Advertencia", "Seleccione un rol antes de cargar.");
            return;
        }

        listaPermisos.clear();

        // 🔹 Datos simulados (luego BD)
        listaPermisos.add(new PermisoAcceso("Inventario", "Catálogo", "Ver", true));
        listaPermisos.add(new PermisoAcceso("Inventario", "Catálogo", "Editar", false));
        listaPermisos.add(new PermisoAcceso("Mantenimiento", "Órdenes", "Registrar", true));
        listaPermisos.add(new PermisoAcceso("Auditoría", "Accesos", "Consultar", false));

        tableViewTablaContenido.setItems(listaPermisos);
    }

    @FXML
    private void guardarPermisos() {
        if (listaPermisos.isEmpty()) {
            mostrarAlerta("Información", "No hay permisos para guardar.");
            return;
        }

        // Aquí luego va el guardado en BD
        mostrarAlerta("Éxito", "Permisos guardados correctamente.");
    }

    @FXML
    private void limpiarFormulario() {
        choiseBoxRol.setValue(null);
        tableViewTablaContenido.getItems().clear();
    }

    @FXML
    private void clikcRegresarMenu() {
        mainController.saver("accesoAuditoria.fxml");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
