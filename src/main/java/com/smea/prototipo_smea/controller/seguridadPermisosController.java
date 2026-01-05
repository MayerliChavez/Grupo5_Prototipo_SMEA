package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.Permiso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class seguridadPermisosController implements Initializable, ControladorInyectable {

    private MainController mainController;

    // ================== FXML ==================

    @FXML private ChoiceBox<String> choiceRol;
    @FXML private ChoiceBox<String> choiceModulo;

    @FXML private TableView<Permiso> tablaPermisos;
    @FXML private TableColumn<Permiso, String> colRol;
    @FXML private TableColumn<Permiso, String> colModulo;
    @FXML private TableColumn<Permiso, String> colAccion;
    @FXML private TableColumn<Permiso, Boolean> colPermitido;

    @FXML private Button buttonVerificar;
    @FXML private Button buttonCambiarEstado;
    @FXML private Button buttonRegresar;

    // ================== DATA ==================

    private ObservableList<Permiso> listaPermisos;

    // ================== INYECCIÓN ==================

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ================== INIT ==================

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarChoiceBox();
        configurarTabla();
        cargarPermisos();
    }

    // ================== CHOICEBOX ==================

    private void configurarChoiceBox() {
        choiceRol.setItems(FXCollections.observableArrayList(
                "Administrador",
                "Bodeguero",
                "Auditor",
                "Invitado"
        ));

        choiceModulo.setItems(FXCollections.observableArrayList(
                "Inventario",
                "Mantenimiento",
                "Acceso",
                "Auditoría",
                "Seguridad"
        ));
    }

    // ================== TABLA ==================

    private void configurarTabla() {
        colRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        colModulo.setCellValueFactory(new PropertyValueFactory<>("modulo"));
        colAccion.setCellValueFactory(new PropertyValueFactory<>("accion"));
        colPermitido.setCellValueFactory(new PropertyValueFactory<>("permitido"));

        colPermitido.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean permitido, boolean empty) {
                super.updateItem(permitido, empty);
                if (empty || permitido == null) {
                    setText(null);
                } else {
                    setText(permitido ? "PERMITIDO" : "DENEGADO");
                }
            }
        });
    }

    // ================== DATOS ==================

    private void cargarPermisos() {
        listaPermisos = FXCollections.observableArrayList(
                new Permiso(1, "Administrador", "Inventario", "INSERT", true),
                new Permiso(2, "Administrador", "Inventario", "DELETE", true),
                new Permiso(3, "Bodeguero", "Inventario", "INSERT", true),
                new Permiso(4, "Bodeguero", "Inventario", "DELETE", false),
                new Permiso(5, "Auditor", "Auditoría", "CONSULTA", true)
        );

        tablaPermisos.setItems(listaPermisos);
    }

    // ================== ACCIONES ==================

    @FXML
    private void verificarPermiso() {
        String rol = choiceRol.getValue();
        String modulo = choiceModulo.getValue();
        Permiso seleccionado = tablaPermisos.getSelectionModel().getSelectedItem();

        if (rol == null || modulo == null) {
            mostrarMensaje("Validación", "Seleccione rol y módulo.", Alert.AlertType.WARNING);
            return;
        }

        if (seleccionado == null) {
            mostrarMensaje("Validación", "Seleccione un permiso.", Alert.AlertType.WARNING);
            return;
        }

        String resultado = seleccionado.isPermitido()
                ? "✔ ACCESO PERMITIDO"
                : "✖ ACCESO DENEGADO";

        mostrarMensaje(
                "Resultado de Verificación",
                "Rol: " + rol +
                        "\nMódulo: " + modulo +
                        "\nAcción: " + seleccionado.getAccion() +
                        "\nResultado: " + resultado,
                Alert.AlertType.INFORMATION
        );
    }

    @FXML
    private void cambiarEstadoPermiso() {
        Permiso seleccionado = tablaPermisos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarMensaje("Acción inválida", "Seleccione un permiso.", Alert.AlertType.WARNING);
            return;
        }

        seleccionado.setPermitido(!seleccionado.isPermitido());
        tablaPermisos.refresh();

        mostrarMensaje(
                "Permiso actualizado",
                "El permiso ahora está: " +
                        (seleccionado.isPermitido() ? "PERMITIDO" : "DENEGADO"),
                Alert.AlertType.INFORMATION
        );
    }

    @FXML
    private void regresar() {
        mainController.saver("seguridadAuditoria.fxml");
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
