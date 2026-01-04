module com.smea.prototipo_smea {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    //requires com.smea.prototipo_smea;
    //requires com.smea.prototipo_smea;

    opens com.smea.prototipo_smea.controller to javafx.fxml;
    exports com.smea.prototipo_smea;
    opens com.smea.prototipo_smea.clasesNormales to javafx.fxml;
    exports com.smea.prototipo_smea.clasesNormales;
}
