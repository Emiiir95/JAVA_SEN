package fr.esiee.javafxtest;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

// chaque cobtroller est relié à un fichier fxml
public class HelloController {
    @FXML
    private Label l_welcomeText;
    @FXML
    private TextField tf_inputName;

    public void onHelloButtonClick() {
        //1- récupérer le inputName saisie par l'utilisateur dans tf_inputName
        String inputName = tf_inputName.getText();
        // Sie le nom est saisi :
        if (!inputName.isEmpty()) {
            //2- afficher dans le label l_welcomeText le message de bienvenu avec le inputName
            l_welcomeText.setText("Bienvenue, " + inputName + "!");
            l_welcomeText.setTextFill(Paint.valueOf("black"));
        }
        //sinon :
        else {
            //3- changer la couleur du text du label l_welcomeText en rouge et afficher un text "Erreur.. saisi"
            l_welcomeText.setText("Erreur : veuillez saisir un nom.");
            l_welcomeText.setTextFill(Paint.valueOf("red"));
        }
    }
}

