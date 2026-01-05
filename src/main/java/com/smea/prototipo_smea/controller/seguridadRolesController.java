package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.Rol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class seguridadRolesController implements Initializable, ControladorInyectable {

    private MainController mainController;

    // ================== FXML ==================

    @FXML private TextField txtUsuario;
    @FXML private ChoiceBox<String> choiceModulo;

    @FXML private TableView<Rol> tablaRoles;
    @FXML private TableColumn<Rol, String> colNombre;
    @FXML private TableColumn<Rol, Boolean> colEstado;
    @FXML private TableColumn<Rol, String> colDescripcion;

    @FXML private Button buttonVerificar;
    @FXML private Button buttonActivarDesactivar;

    // ================== DATA ==================

    private ObservableList<Rol> listaRoles;

    // ================== INYECCIÓN ==================

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ================== INIT ==================

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarTabla();
        configurarChoiceBox();
        cargarRoles();
    }

    // ================== TABLA ==================

    private void configurarTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("activo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        colEstado.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean activo, boolean empty) {
                super.updateItem(activo, empty);
                if (empty || activo == null) {
                    setText(null);
                } else {
                    setText(activo ? "ACTIVO" : "INACTIVO");
                }
            }
        });
    }

    // ================== CHOICEBOX ==================

    private void configurarChoiceBox() {
        choiceModulo.setItems(FXCollections.observableArrayList(
                "Inventario",
                "Mantenimiento",
                "Acceso",
                "Auditoría",
                "Seguridad"
        ));
    }

    // ================== DATOS ==================

    private void cargarRoles() {
        listaRoles = FXCollections.observableArrayList(
                new Rol(1, "Administrador", "Control total del sistema", true),
                new Rol(2, "Bodeguero", "Gestión de inventario", true),
                new Rol(3, "Auditor", "Consulta y auditoría", true),
                new Rol(4, "Invitado", "Acceso limitado", false)
        );

        tablaRoles.setItems(listaRoles);
    }

    // ================== ACCIONES ==================

    @FXML
    private void verificarRol() {
        String usuario = txtUsuario.getText();
        String modulo = choiceModulo.getValue();
        Rol rolSeleccionado = tablaRoles.getSelectionModel().getSelectedItem();

        if (usuario == null || usuario.isBlank()) {
            mostrarMensaje("Validación", "Debe ingresar el usuario.", Alert.AlertType.WARNING);
            return;
        }

        if (modulo == null) {
            mostrarMensaje("Validación", "Debe seleccionar un módulo.", Alert.AlertType.WARNING);
            return;
        }

        if (rolSeleccionado == null) {
            mostrarMensaje("Validación", "Seleccione un rol de la tabla.", Alert.AlertType.WARNING);
            return;
        }

        String estado = rolSeleccionado.isActivo() ? "ACTIVO" : "INACTIVO";

        mostrarMensaje(
                "Resultado de Verificación",
                "Usuario: " + usuario +
                        "\nRol: " + rolSeleccionado.getNombre() +
                        "\nMódulo: " + modulo +
                        "\nEstado del Rol: " + estado +
                        "\n\n✔ Acceso evaluado correctamente.",
                Alert.AlertType.INFORMATION
        );
    }

    @FXML
    private void cambiarEstado() {
        Rol seleccionado = tablaRoles.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarMensaje("Acción inválida", "Seleccione un rol.", Alert.AlertType.WARNING);
            return;
        }

        seleccionado.setActivo(!seleccionado.isActivo());
        tablaRoles.refresh();

        mostrarMensaje(
                "Estado actualizado",
                "El rol ahora está: " + (seleccionado.isActivo() ? "ACTIVO" : "INACTIVO"),
                Alert.AlertType.INFORMATION
        );
    }

    @FXML
    private void verPermisos() {
        Rol seleccionado = tablaRoles.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarMensaje("Permisos", "Seleccione un rol para ver sus permisos.", Alert.AlertType.WARNING);
            return;
        }

        // Luego aquí navegas a permisos por rol
        mostrarMensaje(
                "Permisos",
                "Mostrando permisos del rol: " + seleccionado.getNombre(),
                Alert.AlertType.INFORMATION
        );

        // mainController.saver("visualizarPermisos.fxml");
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
