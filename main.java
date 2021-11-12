package com.example.petdating;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;

public class main extends Application {





    @Override
    public void start(Stage stage) throws IOException {

        Parent fmxlloader=FXMLLoader.load(getClass().getResource("/com/example/petdating/login.fxml"));

        Scene scene = new Scene(fmxlloader);
        stage.setTitle("Petdating");

        stage.setScene(scene);
        stage.show();




    }







    public static void main(String[] args) {
        launch();
    }
}