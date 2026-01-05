package com.smea.prototipo_smea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class asignarPermisosController implements Initializable, ControladorInyectable {

    private MainController mainController;

    // ===================== CONTENEDORES =====================
    @FXML
    private AnchorPane paneFondo;

    // ===================== BUSQUEDA =====================
    @FXML
    private ChoiceBox<String> chiceBoxCliente;

    @FXML
    private TextField textfieldCedulCliente;

    @FXML
    private TextField textFieldNombreCliente;

    // ===================== DATOS USUARIO =====================
    @FXML
    private TextField textFieldUsuario;

    @FXML
    private TextField textFieldRol;

    @FXML
    private TextField textfieldTelefono; // Estado

    // ===================== PERMISOS =====================
    @FXML
    private CheckBox checkProveedores;

    @FXML
    private CheckBox checkInventario;

    @FXML
    private CheckBox checkMantenimiento;

    @FXML
    private CheckBox checkClientes;

    @FXML
    private CheckBox checkReportes;

    @FXML
    private CheckBox checkAdministracion;

    @FXML
    private CheckBox checkAuditoria;

    // ===================== BOTONES =====================
    @FXML
    private Button buttonGuardarCambios;

    @FXML
    private Button buttonRegresar;

    @FXML
    private Button buttonBuscarNombre;

    @FXML
    private Button buttonBuscarCedula;

    // ===================== USUARIO ACTUAL =====================
    private boolean usuarioCargado = false;

    @Override
    public void setMainController(MainController mainController) {
         this.mainController = mainController;
    }

    // ===================== INITIALIZE =====================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        chiceBoxCliente.getItems().addAll(
                "Cédula",
                "Nombre"
        );
        chiceBoxCliente.setValue("Cédula");
        configurarBusqueda();
        limpiarFormulario();
    }

    // ===================== BUSCAR USUARIO =====================
    @FXML
    private void buscarUsuario(ActionEvent event) {

        if (chiceBoxCliente.getValue().equals("Cédula")) {

            if (textfieldCedulCliente.getText().isEmpty()) {
                mostrarAlerta("Advertencia", "Debe ingresar una cédula", Alert.AlertType.WARNING);
                return;
            }

        } else {

            if (textFieldNombreCliente.getText().isEmpty()) {
                mostrarAlerta("Advertencia", "Debe ingresar un nombre", Alert.AlertType.WARNING);
                return;
            }
        }

        // 🔹 SIMULACIÓN DE USUARIO ENCONTRADO
        textFieldUsuario.setText("jperez");
        textFieldRol.setText("Administrador");
        textfieldTelefono.setText("Activo");

        // Simulación de permisos actuales
        checkProveedores.setSelected(true);
        checkInventario.setSelected(true);
        checkMantenimiento.setSelected(false);
        checkClientes.setSelected(true);
        checkReportes.setSelected(false);
        checkAdministracion.setSelected(true);
        checkAuditoria.setSelected(false);

        usuarioCargado = true;

        mostrarAlerta("Éxito", "Usuario encontrado correctamente", Alert.AlertType.INFORMATION);
    }

    // ===================== GUARDAR PERMISOS =====================
    @FXML
    private void guardarCambios(ActionEvent event) {

        if (!usuarioCargado) {
            mostrarAlerta("Error", "Debe buscar un usuario primero", Alert.AlertType.ERROR);
            return;
        }

        // Recolectar permisos
        boolean proveedores = checkProveedores.isSelected();
        boolean inventario = checkInventario.isSelected();
        boolean mantenimiento = checkMantenimiento.isSelected();
        boolean clientes = checkClientes.isSelected();
        boolean reportes = checkReportes.isSelected();
        boolean administracion = checkAdministracion.isSelected();
        boolean auditoria = checkAuditoria.isSelected();

        // 🔹 AQUÍ IRÍA LA LÓGICA DE BD
        /*
        permisoDAO.actualizarPermisos(usuarioId, proveedores, inventario, ...);
        */

        mostrarAlerta("Éxito", "Permisos asignados correctamente", Alert.AlertType.INFORMATION);

        limpiarFormulario();
    }

    // ================= NAVEGACIÓN =================
    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("moduloAdministracionSistema.fxml");
    }

    // ===================== MÉTODOS AUXILIARES =====================
    private void limpiarFormulario() {

        textfieldCedulCliente.clear();
        textFieldNombreCliente.clear();
        textFieldUsuario.clear();
        textFieldRol.clear();
        textfieldTelefono.clear();

        checkProveedores.setSelected(false);
        checkInventario.setSelected(false);
        checkMantenimiento.setSelected(false);
        checkClientes.setSelected(false);
        checkReportes.setSelected(false);
        checkAdministracion.setSelected(false);
        checkAuditoria.setSelected(false);

        usuarioCargado = false;
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void configurarBusqueda() {

        boolean buscarPorCedula = chiceBoxCliente.getValue().equals("Cédula");

        textfieldCedulCliente.setDisable(!buscarPorCedula);
        buttonBuscarCedula.setDisable(!buscarPorCedula);

        textFieldNombreCliente.setDisable(buscarPorCedula);
        buttonBuscarNombre.setDisable(buscarPorCedula);

        // Limpiar el campo que no se usa
        if (buscarPorCedula) {
            textFieldNombreCliente.clear();
        } else {
            textfieldCedulCliente.clear();
        }
    }

}
