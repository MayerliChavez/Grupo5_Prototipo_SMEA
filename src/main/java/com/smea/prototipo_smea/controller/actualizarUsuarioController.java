package com.smea.prototipo_smea.controller;

import com.smea.prototipo_smea.clasesNormales.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class actualizarUsuarioController
        implements Initializable, ControladorInyectable {

    // ===== MAIN CONTROLLER =====
    private MainController mainController;

    // ===== FXML =====
    @FXML private ChoiceBox<String> chiceBoxCliente;

    @FXML private TextField textfieldCedulCliente;
    @FXML private TextField textFieldNombreCliente;

    @FXML private TextField textFieldUsuario;
    @FXML private TextField textFieldContrasena;
    @FXML private TextField textfieldCorreo;
    @FXML private TextField textfieldDireccion;
    @FXML private TextField textfieldTelefono;

    @FXML private ChoiceBox<String> choiseBoxRol;

    @FXML private Button buttonBuscarCedula;
    @FXML private Button buttonBuscarNombre;
    @FXML private Button buttonGuardarCambios;
    @FXML private Button buttonGuardarCambios1; // restaurar
    @FXML private Button buttonCambiarEstado;
    @FXML private Button buttonRegresar;

    // ===== DATOS =====
    private ObservableList<Usuario> listaUsuarios;
    private Usuario usuarioActual;
    private Usuario respaldoUsuario;

    // ===== INYECCIÓN =====
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // ===== INIT =====
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        chiceBoxCliente.setItems(
                FXCollections.observableArrayList("Cédula", "Nombre")
        );
        chiceBoxCliente.getSelectionModel().selectFirst();

        choiseBoxRol.setItems(
                FXCollections.observableArrayList(
                        "Gerente General y Técnico",
                        "Administrador",
                        "Representante de Cobranzas",
                        "Representante de Tesorería",
                        "Operador",
                        "Técnico Coordinador",
                        "Bodeguero",
                        "Bodeguero Coordinador"
                )
        );

        cargarDatosSimulados();
        configurarBusqueda();
        deshabilitarEdicion();
    }

    // ===== DATOS SIMULADOS =====
    private void cargarDatosSimulados() {

        listaUsuarios = FXCollections.observableArrayList(

                new Usuario(
                        "Juan",
                        "Pérez",
                        "0102030405",
                        "0987654321",
                        "Av. Amazonas y NN.UU",
                        "juan.perez@correo.com",
                        "Administrador",
                        "ACTIVO",
                        "jperez",
                        "1234"
                ),

                new Usuario(
                        "María",
                        "López",
                        "1728678150",
                        "0912345678",
                        "Carcelén Bajo",
                        "maria.lopez@correo.com",
                        "Bodeguero",
                        "ACTIVO",
                        "mlopez",
                        "abcd"
                ),

                new Usuario(
                        "Carlos",
                        "Mendoza",
                        "0911122233",
                        "0998877665",
                        "La Ecuatoriana",
                        "carlos.mendoza@correo.com",
                        "Técnico",
                        "INACTIVO",
                        "cmendoza",
                        "tecnico01"
                ),

                new Usuario(
                        "Ana",
                        "Torres",
                        "1102233445",
                        "0976543210",
                        "Centro Histórico",
                        "ana.torres@correo.com",
                        "Gerente",
                        "ACTIVO",
                        "atorres",
                        "admin2025"
                )
        );
    }

    // ===== CONFIGURAR BUSQUEDA =====
    private void configurarBusqueda() {

        textfieldCedulCliente.setDisable(false);
        textFieldNombreCliente.setDisable(true);

        buttonBuscarCedula.setDisable(false);
        buttonBuscarNombre.setDisable(true);

        chiceBoxCliente.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {

                    if ("Cédula".equals(newVal)) {
                        textfieldCedulCliente.setDisable(false);
                        textFieldNombreCliente.setDisable(true);
                        textFieldNombreCliente.clear();

                        buttonBuscarCedula.setDisable(false);
                        buttonBuscarNombre.setDisable(true);
                    } else {
                        textfieldCedulCliente.setDisable(true);
                        textFieldNombreCliente.setDisable(false);
                        textfieldCedulCliente.clear();

                        buttonBuscarCedula.setDisable(true);
                        buttonBuscarNombre.setDisable(false);
                    }
                });
    }

    // ===== BUSCAR USUARIO =====
    @FXML
    private void buscarUsuario(ActionEvent event) {

        usuarioActual = null;

        if ("Cédula".equals(chiceBoxCliente.getValue())) {

            String cedula = textfieldCedulCliente.getText().trim();

            if (cedula.isEmpty()) {
                alerta("Campo vacío",
                        "Debe ingresar la cédula",
                        Alert.AlertType.WARNING);
                return;
            }

            if (!cedula.matches("\\d+")) {
                alerta("Cédula inválida",
                        "La cédula solo debe contener números",
                        Alert.AlertType.WARNING);
                return;
            }

            for (Usuario u : listaUsuarios) {
                if (u.getCedula().equals(cedula)) {
                    usuarioActual = u;
                    break;
                }
            }

        } else {

            String nombre = textFieldNombreCliente.getText().trim();

            if (nombre.isEmpty()) {
                alerta("Campo vacío",
                        "Debe ingresar el nombre",
                        Alert.AlertType.WARNING);
                return;
            }

            for (Usuario u : listaUsuarios) {
                if (u.getNombre().toLowerCase()
                        .contains(nombre.toLowerCase())) {
                    usuarioActual = u;
                    break;
                }
            }
        }

        if (usuarioActual == null) {
            alerta("Usuario no encontrado",
                    "No se encontró el usuario",
                    Alert.AlertType.INFORMATION);
            return;
        }

        cargarUsuario(usuarioActual);
    }

    // ===== CARGAR DATOS =====
    private void cargarUsuario(Usuario u) {

        textFieldUsuario.setText(u.getUsuario());
        textFieldContrasena.setText(u.getContrasena());
        textfieldCorreo.setText(u.getCorreo());
        textfieldDireccion.setText(u.getDireccion());
        textfieldTelefono.setText(u.getTelefono());
        choiseBoxRol.setValue(u.getRol());

        respaldoUsuario = new Usuario(
                u.getNombre(), u.getApellido(), u.getCedula(),
                u.getUsuario(), u.getContrasena(),
                u.getCorreo(), u.getDireccion(),
                u.getTelefono(), u.getRol(), u.getEstado()
        );

        habilitarEdicion();
    }

    // ===== GUARDAR =====
    @FXML
    private void guardarCambios(ActionEvent event) {

        if (usuarioActual == null) {
            alerta("Error",
                    "Debe buscar un usuario primero",
                    Alert.AlertType.WARNING);
            return;
        }

        if (!validarCampos()) return;

        usuarioActual.setUsuario(textFieldUsuario.getText());
        usuarioActual.setContrasena(textFieldContrasena.getText());
        usuarioActual.setCorreo(textfieldCorreo.getText());
        usuarioActual.setDireccion(textfieldDireccion.getText());
        usuarioActual.setTelefono(textfieldTelefono.getText());
        usuarioActual.setRol(choiseBoxRol.getValue());

        alerta("Actualizado",
                "Datos del usuario actualizados correctamente",
                Alert.AlertType.INFORMATION);
    }

    // ===== VALIDAR =====
    private boolean validarCampos() {

        if (textFieldUsuario.getText().isEmpty()
                || textFieldContrasena.getText().isEmpty()
                || textfieldCorreo.getText().isEmpty()
                || textfieldTelefono.getText().isEmpty()
                || choiseBoxRol.getValue() == null) {

            alerta("Campos obligatorios",
                    "Debe completar todos los campos",
                    Alert.AlertType.WARNING);
            return false;
        }

        if (!textfieldCorreo.getText().contains("@")) {
            alerta("Correo inválido",
                    "Ingrese un correo válido",
                    Alert.AlertType.WARNING);
            return false;
        }

        if (!textfieldTelefono.getText().matches("\\d{10}")) {
            alerta("Teléfono inválido",
                    "El teléfono debe tener 10 dígitos",
                    Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    // ===== RESTAURAR =====
    @FXML
    private void restaurarValores(ActionEvent event) {

        if (respaldoUsuario == null) return;

        textFieldUsuario.setText(respaldoUsuario.getUsuario());
        textFieldContrasena.setText(respaldoUsuario.getContrasena());
        textfieldCorreo.setText(respaldoUsuario.getCorreo());
        textfieldDireccion.setText(respaldoUsuario.getDireccion());
        textfieldTelefono.setText(respaldoUsuario.getTelefono());
        choiseBoxRol.setValue(respaldoUsuario.getRol());
    }

    // ===== CAMBIAR ESTADO =====
    @FXML
    private void cambiarEstado(ActionEvent event) {
        Usuario u = usuarioActual;

        if (usuarioActual == null) return;

        String nuevoEstado =
                usuarioActual.getEstado().equals("ACTIVO")
                        ? "INACTIVO" : "ACTIVO";

        usuarioActual.setEstado(nuevoEstado);

        alerta("Estado actualizado",
                "El usuario " + u.getNombre() +"ahora está " + nuevoEstado,
                Alert.AlertType.INFORMATION);
    }

    // ===== REGRESAR =====
    @FXML
    private void clikcRegresarMenu(ActionEvent event) {
        mainController.saver("moduloAdministracionSistema.fxml");
    }

    // ===== UTIL =====
    private void habilitarEdicion() {

        textFieldUsuario.setDisable(false);
        textFieldContrasena.setDisable(false);
        textfieldCorreo.setDisable(false);
        textfieldDireccion.setDisable(false);
        textfieldTelefono.setDisable(false);
        choiseBoxRol.setDisable(false);

        buttonGuardarCambios.setDisable(false);
        buttonGuardarCambios1.setDisable(false);
        buttonCambiarEstado.setDisable(false);
    }

    private void deshabilitarEdicion() {

        textFieldUsuario.setDisable(true);
        textFieldContrasena.setDisable(true);
        textfieldCorreo.setDisable(true);
        textfieldDireccion.setDisable(true);
        textfieldTelefono.setDisable(true);
        choiseBoxRol.setDisable(true);

        buttonGuardarCambios.setDisable(true);
        buttonGuardarCambios1.setDisable(true);
        buttonCambiarEstado.setDisable(true);
    }

    private void alerta(String t, String m, Alert.AlertType tipo) {
        Alert a = new Alert(tipo);
        a.setTitle(t);
        a.setHeaderText(null);
        a.setContentText(m);
        a.showAndWait();
    }
}
