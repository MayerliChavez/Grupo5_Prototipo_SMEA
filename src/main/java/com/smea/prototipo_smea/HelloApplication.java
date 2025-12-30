package com.smea.prototipo_smea;

import com.smea.prototipo_smea.controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {

        StackPane root = new StackPane();
        MainController mainController = new MainController(root);

        // Cargar vista inicial
        mainController.saver("menuSMEA.fxml");

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("SMEA");

        stage.setMaximized(true);   // 👈 COMO NAVEGADOR
        stage.setResizable(true);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
