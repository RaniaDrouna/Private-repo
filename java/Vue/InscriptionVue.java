package Vue;

import Control.UtilisateurControl;
import Model.Utilisateur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;

public class InscriptionVue {

    private Stage stage;
    private UtilisateurControl utilisateurControl;

    public InscriptionVue(UtilisateurControl utilisateurControl) {
        this.utilisateurControl = utilisateurControl;
        this.stage = new Stage();
        initializeUI();
    }

    private void initializeUI() {
        stage.setTitle("Inscription");

        // Champ NIN
        TextField ninField = new TextField();
        ninField.setPromptText("Numéro de Carte d'Identité (NIN)");
        ninField.setStyle("-fx-padding: 10px; -fx-background-color: #E7D7C1; -fx-border-radius: 5px; -fx-border-color: #735751;");

        // Champ Nom d'utilisateur
        TextField usernameField = new TextField();
        usernameField.setPromptText("Nom d'utilisateur");
        usernameField.setStyle("-fx-padding: 10px; -fx-background-color: #E7D7C1; -fx-border-radius: 5px; -fx-border-color: #735751;");

        // Champ Date de naissance
        DatePicker datePicker = new DatePicker();
        datePicker.setStyle("-fx-padding: 10px; -fx-background-color: #E7D7C1; -fx-border-radius: 5px; -fx-border-color: #735751;");
        
        // Champ Mot de passe
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Mot de passe");
        passwordField.setStyle("-fx-padding: 10px; -fx-background-color: #E7D7C1; -fx-border-radius: 5px; -fx-border-color: #735751;");

        // Bouton Soumettre l'inscription
        Button submitButton = new Button("S'inscrire");
        submitButton.setStyle("-fx-background-color: #8C1C13; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-font-size: 14px; -fx-border-radius: 5px;");

        // Logique du bouton Soumettre
        submitButton.setOnAction(e -> {
            String nin = ninField.getText().trim();
            String username = usernameField.getText().trim();
            LocalDate birthdate = datePicker.getValue();
            String password = passwordField.getText().trim();

            // Vérifier que tous les champs sont remplis
            if (nin.isEmpty() || username.isEmpty() || birthdate == null || password.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Champs vides", "Veuillez remplir tous les champs.");
                return;
            }

            // Créer un nouvel utilisateur
            Utilisateur utilisateur = new Utilisateur(nin, username, password, birthdate, Utilisateur.Role.Client);

            // Ajouter l'utilisateur à la base de données
            boolean success = utilisateurControl.ajouterUtilisateur(utilisateur);

            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Inscription réussie", "Votre inscription a été réalisée avec succès.");
                stage.close(); // Fermer la fenêtre et revenir à la fenêtre de connexion
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur", "L'inscription a échoué, veuillez réessayer.");
            }
        });

        // Disposition de l'interface avec GridPane
        GridPane grid = new GridPane();
        grid.setVgap(15);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);
        grid.add(ninField, 0, 0);
        grid.add(usernameField, 0, 1);
        grid.add(datePicker, 0, 2);
        grid.add(passwordField, 0, 3);
        grid.add(submitButton, 0, 4);

        // Créer la scène et l'ajouter à la fenêtre
        Scene scene = new Scene(grid, 350, 350, Color.web("#A78A7F"));
        stage.setScene(scene);
    }

    public void afficher() {
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
