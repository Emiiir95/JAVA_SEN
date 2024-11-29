package fr.esiee.javafxtest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// la classe Application représente l'application a démarrer
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // la fenêtre principal est personnifié par l'objet de type Stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // scene représente le cadre qui structure la fenêtre et accueille le panel principal
        // panel principal = rootPanel
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("JavaFXTEST");
        stage.setScene(scene);
        stage.show(); // permet de rendre visible la fenêtre
    }

    public static void main(String[] args) {
        launch();
    }
}