package fr.esiee.easytrainfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import static javafx.application.Application.launch;

public class EasyTrainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EasyTrainApplication.class.getResource("ajoutArret-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    //@Override
    //public void start(Stage stage) throws IOException {
    //  FXMLLoader fxmlLoader = new FXMLLoader(EasyTrainApplication.class.getResource("ajoutUtilisateur-view.fxml"));
    //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
    //stage.setTitle("Hello!");
    //stage.setScene(scene);
    //  stage.show();
    }

    public static void main(String[] args) {
        launch();

        }
    }

