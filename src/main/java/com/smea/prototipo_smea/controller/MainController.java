package com.smea.prototipo_smea.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    private StackPane root;
    private String rolUsuario;
    private String PasswordUsuario;

    public MainController(StackPane root) {
        this.root = root;
    }

    public void setRolUsuario(String rol) {
        this.rolUsuario = rol;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }
    private Object datoTemporal;

    public void setDatoTemporal(Object dato) {
        this.datoTemporal = dato;
    }

    public Object getDatoTemporal() {
        return datoTemporal;
    }

    public String getPasswordUsuario() {
        return PasswordUsuario;
    }
    public void setPasswordUsuario(String PasswordUsuario) {
        this.PasswordUsuario = PasswordUsuario;
    }

    public void saver(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/smea/prototipo_smea/" + fxml)
            );

            Node vista = loader.load();

            // Inyectar MainController al controlador cargado
            Object controller = loader.getController();
            if (controller instanceof ControladorInyectable) {
                ((ControladorInyectable) controller)
                        .setMainController(this);
            }

            root.getChildren().clear();
            root.getChildren().add(vista);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("No se pudo cargar: " + fxml);
        }
    }
}
