package com.smea.prototipo_smea.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class visualizarCalendarioController {

    @FXML
    private DatePicker dateFiltro;

    @FXML
    private ChoiceBox<String> choiceTecnico;

    @FXML
    private TableView<Cronograma> tablaCronograma;

    @FXML
    private TableColumn<Cronograma, String> colFecha;
    @FXML
    private TableColumn<Cronograma, String> colHora;
    @FXML
    private TableColumn<Cronograma, String> colCliente;
    @FXML
    private TableColumn<Cronograma, String> colTecnico;
    @FXML
    private TableColumn<Cronograma, String> colEstado;

    private ObservableList<Cronograma> listaCronograma;
    private ObservableList<Cronograma> listaFiltrada;

    @FXML
    public void initialize() {
        // Inicializar ChoiceBox
        choiceTecnico.setItems(FXCollections.observableArrayList(
                "Todos", "Técnico 1", "Técnico 2", "Técnico 3"
        ));
        choiceTecnico.setValue("Todos");

        // Inicializar listas
        listaCronograma = FXCollections.observableArrayList();
        listaFiltrada = FXCollections.observableArrayList();
        tablaCronograma.setItems(listaFiltrada);

        // Configurar columnas
        colFecha.setCellValueFactory(cell -> cell.getValue().fechaProperty());
        colHora.setCellValueFactory(cell -> cell.getValue().horaProperty());
        colCliente.setCellValueFactory(cell -> cell.getValue().clienteProperty());
        colTecnico.setCellValueFactory(cell -> cell.getValue().tecnicoProperty());
        colEstado.setCellValueFactory(cell -> cell.getValue().estadoProperty());

        // Cargar datos de prueba
        cargarDatosPrueba();

        // Mostrar todos inicialmente
        actualizarTabla();
    }

    // BOTÓN BUSCAR
    @FXML
    private void buscar(ActionEvent event) {
        actualizarTabla();
    }

    // FILTRAR DATOS SEGÚN FECHA Y TÉCNICO
    private void actualizarTabla() {
        LocalDate fecha = dateFiltro.getValue();
        String tecnico = choiceTecnico.getValue();

        List<Cronograma> filtrados = listaCronograma.stream()
                .filter(c -> (fecha == null || c.getFecha().equals(fecha.toString())))
                .filter(c -> (tecnico.equals("Todos") || c.getTecnico().equals(tecnico)))
                .collect(Collectors.toList());

        listaFiltrada.setAll(filtrados);
    }

    // BOTÓN VER DETALLE
    @FXML
    private void verDetalle(ActionEvent event) {
        Cronograma seleccionado = tablaCronograma.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            mostrarAlerta(
                    "Detalle de la cita:\n" +
                            "Cliente: " + seleccionado.getCliente() + "\n" +
                            "Fecha: " + seleccionado.getFecha() + "\n" +
                            "Hora: " + seleccionado.getHora() + "\n" +
                            "Técnico: " + seleccionado.getTecnico() + "\n" +
                            "Estado: " + seleccionado.getEstado()
            );
        } else {
            mostrarAlerta("Seleccione un registro para ver detalle.");
        }
    }

    // BOTÓN CAMBIAR ESTADO
    @FXML
    private void cambiarEstado(ActionEvent event) {
        Cronograma seleccionado = tablaCronograma.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            // Cambiar estado de forma cíclica: Pendiente -> En Proceso -> Completado -> Pendiente
            switch (seleccionado.getEstado()) {
                case "Pendiente" -> seleccionado.setEstado("En Proceso");
                case "En Proceso" -> seleccionado.setEstado("Completado");
                case "Completado" -> seleccionado.setEstado("Pendiente");
            }
            tablaCronograma.refresh();  // Actualizar tabla
        } else {
            mostrarAlerta("Seleccione un registro para cambiar estado.");
        }
    }

    // BOTÓN HOY
    @FXML
    private void irHoy(ActionEvent event) {
        dateFiltro.setValue(LocalDate.now());
        actualizarTabla();
    }

    // BOTÓN LIMPIAR
    @FXML
    private void limpiar(ActionEvent event) {
        dateFiltro.setValue(null);
        choiceTecnico.setValue("Todos");
        tablaCronograma.getSelectionModel().clearSelection();
        actualizarTabla();
    }

    // ALERTA SIMPLE
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // DATOS DE PRUEBA
    private void cargarDatosPrueba() {
        listaCronograma.add(new Cronograma("2026-01-05", "09:00", "Cliente A", "Técnico 1", "Pendiente"));
        listaCronograma.add(new Cronograma("2026-01-05", "11:00", "Cliente B", "Técnico 2", "Completado"));
        listaCronograma.add(new Cronograma("2026-01-06", "14:00", "Cliente C", "Técnico 3", "En Proceso"));
        listaCronograma.add(new Cronograma("2026-01-06", "15:00", "Cliente D", "Técnico 1", "Pendiente"));
        listaCronograma.add(new Cronograma("2026-01-07", "10:00", "Cliente E", "Técnico 2", "Pendiente"));
    }

    // CLASE INTERNA PARA LA TABLA
    public static class Cronograma {
        private final javafx.beans.property.SimpleStringProperty fecha;
        private final javafx.beans.property.SimpleStringProperty hora;
        private final javafx.beans.property.SimpleStringProperty cliente;
        private final javafx.beans.property.SimpleStringProperty tecnico;
        private final javafx.beans.property.SimpleStringProperty estado;

        public Cronograma(String fecha, String hora, String cliente, String tecnico, String estado) {
            this.fecha = new javafx.beans.property.SimpleStringProperty(fecha);
            this.hora = new javafx.beans.property.SimpleStringProperty(hora);
            this.cliente = new javafx.beans.property.SimpleStringProperty(cliente);
            this.tecnico = new javafx.beans.property.SimpleStringProperty(tecnico);
            this.estado = new javafx.beans.property.SimpleStringProperty(estado);
        }

        public String getFecha() { return fecha.get(); }
        public javafx.beans.property.StringProperty fechaProperty() { return fecha; }

        public String getHora() { return hora.get(); }
        public javafx.beans.property.StringProperty horaProperty() { return hora; }

        public String getCliente() { return cliente.get(); }
        public javafx.beans.property.StringProperty clienteProperty() { return cliente; }

        public String getTecnico() { return tecnico.get(); }
        public javafx.beans.property.StringProperty tecnicoProperty() { return tecnico; }

        public String getEstado() { return estado.get(); }
        public void setEstado(String estado) { this.estado.set(estado); }
        public javafx.beans.property.StringProperty estadoProperty() { return estado; }
    }
}
