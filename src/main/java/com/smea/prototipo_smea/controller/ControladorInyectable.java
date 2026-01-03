package com.smea.prototipo_smea.controller;

import java.net.URL;
import java.util.ResourceBundle;

public interface ControladorInyectable {
    void setMainController(MainController main);

    void initialize(URL url, ResourceBundle rb);
}

