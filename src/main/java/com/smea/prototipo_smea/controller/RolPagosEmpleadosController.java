package com.smea.prototipo_smea.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class RolPagosEmpleadosController implements Initializable, ControladorInyectable {

    private MainController mainController;

    @FXML private ComboBox<String> comboEmpleado;
    @FXML private ComboBox<String> comboMes;

    @FXML private TextField txtHoras;
    @FXML private TextField txtPagoHora;
    @FXML private TextField txtHorasExtra;
    @FXML private TextField txtValorHoraExtra;
    @FXML private TextField txtBonos;
    @FXML private TextField txtDescuentos;

    @FXML private TextField txtSueldoBruto;
    @FXML private TextField txtSueldoNeto;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboEmpleado.getItems().addAll("Juan Pérez", "María Gómez", "Carlos López");
        comboMes.getItems().addAll(
                "Enero", "Febrero", "Marzo", "Abril",
                "Mayo", "Junio", "Julio", "Agosto",
                "Septiembre", "Octubre", "Noviembre", "Diciembre"
        );
    }

    @FXML
    private void calcularRol() {

        double horas = parse(txtHoras);
        double pagoHora = parse(txtPagoHora);
        double horasExtra = parse(txtHorasExtra);
        double valorHoraExtra = parse(txtValorHoraExtra);
        double bonos = parse(txtBonos);
        double descuentos = parse(txtDescuentos);

        double sueldoBruto =
                (horas * pagoHora) +
                        (horasExtra * valorHoraExtra) +
                        bonos;

        double sueldoNeto = sueldoBruto - descuentos;

        txtSueldoBruto.setText(String.format("%.2f", sueldoBruto));
        txtSueldoNeto.setText(String.format("%.2f", sueldoNeto));
    }

    @FXML
    private void limpiar() {
        txtHoras.clear();
        txtPagoHora.clear();
        txtHorasExtra.clear();
        txtValorHoraExtra.clear();
        txtBonos.clear();
        txtDescuentos.clear();
        txtSueldoBruto.clear();
        txtSueldoNeto.clear();
    }

    @FXML
    private void exportar() {
        System.out.println("Exportar rol de pago");
    }

    @FXML
    private void regresar() {
       mainController.saver("reportesRRHH.fxml");
    }

    private double parse(TextField t) {
        try {
            return Double.parseDouble(t.getText());
        } catch (Exception e) {
            return 0;
        }
    }
}
