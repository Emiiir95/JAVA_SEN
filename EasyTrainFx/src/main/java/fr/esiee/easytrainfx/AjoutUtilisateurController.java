package fr.esiee.easytrainfx;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AjoutUtilisateurController {

    @FXML
    private TextField tf_inputName;
    @FXML
    private ComboBox<String> cb_typeArret;
    @FXML
    private Label lbl_message;

    // Méthode d'initialisation qui est appelée après l'injection des éléments FXML
    @FXML
    private void initialize() {
        // Ajout des valeurs par défaut dans le ComboBox
        cb_typeArret.getItems().addAll("Terminus", "Intermediaire");
    }

    // Méthode pour insérer un arrêt dans la base de données
    @FXML
    protected void onAddArret() {
        String nom = tf_inputName.getText();
        String typeArret = cb_typeArret.getValue();

        // Vérifier si les champs sont remplis
        if (nom.isEmpty() || typeArret == null) {
            lbl_message.setText("Tous les champs doivent être remplis!");
            return;
        }

        // Connexion à la base de données
        String urlLocal = "jdbc:mariadb://localhost:3306/emirsen_easytrain";
        String userLocal = "root";
        String pwdLocal = "root";
        Connection connectionLocal = null;

        try {
            connectionLocal = DriverManager.getConnection(urlLocal, userLocal, pwdLocal);
            System.out.println("Connexion OK à la base de données locale");

            // Préparer la requête d'insertion
            String sql = "INSERT INTO Arret (nom, type_arret) VALUES (?, ?)";
            PreparedStatement preparedStatement = connectionLocal.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, typeArret);

            // Exécuter la requête
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                lbl_message.setText("Arrêt ajouté avec succès !");
            } else {
                lbl_message.setText("Échec de l'ajout de l'arrêt.");
            }

        } catch (SQLException e) {
            lbl_message.setText("Erreur de connexion : " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (connectionLocal != null) {
                    connectionLocal.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
