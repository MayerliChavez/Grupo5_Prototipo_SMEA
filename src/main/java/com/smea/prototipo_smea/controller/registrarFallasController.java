package com.smea.prototipo_smea.controller;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class registrarFallasController implements Initializable, ControladorInyectable {

    private MainController mainController;

    @FXML private TextField txtCodigoEquipo;
    @FXML private DatePicker dateFalla;
    @FXML private TextField txtHora;
    @FXML private TextArea txtDescripcion;
    @FXML private TextField txtResponsable;
    @FXML private ComboBox<String> comboPrioridad;
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;

    @Override
    public void setMainController(MainController mainController) {
            this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // ================= EVENTOS =================
    @FXML
    private void clickGuardar(ActionEvent event) {
        // Validar campos
        if(txtCodigoEquipo.getText().isEmpty() || dateFalla.getValue() == null || txtDescripcion.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, complete los campos obligatorios.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Aquí guardas la falla (puede ser en la base de datos o lista temporal)
        String codigo = txtCodigoEquipo.getText();
        String fecha = dateFalla.getValue().toString();
        String hora = txtHora.getText();
        String descripcion = txtDescripcion.getText();
        String responsable = txtResponsable.getText();
        String prioridad = comboPrioridad.getValue();

        // Solo ejemplo de confirmación
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Falla registrada:\nCódigo: "+codigo+"\nFecha: "+fecha+"\nDescripción: "+descripcion, ButtonType.OK);
        alert.showAndWait();

        // Limpiar campos
        txtCodigoEquipo.clear();
        dateFalla.setValue(null);
        txtHora.clear();
        txtDescripcion.clear();
        txtResponsable.clear();
        comboPrioridad.setValue(null);
    }

    @FXML
    private void clickCancelar(ActionEvent event) {
        // Aquí puedes cerrar la ventana o regresar al menú principal
        txtCodigoEquipo.clear();
        dateFalla.setValue(null);
        txtHora.clear();
        txtDescripcion.clear();
        txtResponsable.clear();
        comboPrioridad.setValue(null);
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        mainController.saver("controlMantenimiento.fxml");
    }
}
