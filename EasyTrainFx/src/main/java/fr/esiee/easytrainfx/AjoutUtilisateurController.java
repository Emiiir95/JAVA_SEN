package fr.esiee.easytrainfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AjoutUtilisateurController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onAddUser() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}