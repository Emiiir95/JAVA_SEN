package fr.esiee.javafxtest;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

// chaque cobtroller est relié à un fichier fxml
public class HelloController {
    @FXML
    private Label l_welcomeText;
    @FXML
    protected void onHelloButtonClick() {
        //1- récupérer le inputName saisie par l'utilisateur dans tf_inputName
        // Sie le nom est saisi :
        //2- afficher dans le label l_welcomeText le message de bienvenu avec le inputName
        //sinon :
        //3- changer la couleur du text du label l_welcomeText en rouge et afficher un text "Erreur.. saisi"
        l_welcomeText.setText("Welcome to JavaFX Application!");
        l_welcomeText.setTextFill(Paint.valueOf("red"));
    }
}