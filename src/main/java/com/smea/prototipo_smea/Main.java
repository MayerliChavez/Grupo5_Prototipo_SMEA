package com.smea.prototipo_smea;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/ec/edu/smea/view/menuSEMEA.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sistema SMEA");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}