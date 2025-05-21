package Vue;

import Control.UtilisateurControl;
import Model.Utilisateur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ConnexionVue {

    private Stage stage;
    private UtilisateurControl utilisateurControl;

    public ConnexionVue(UtilisateurControl utilisateurControl) {
        this.utilisateurControl = utilisateurControl;
        this.stage = new Stage();
        initializeUI();
    }

    private void initializeUI() {
        stage.setTitle("Connexion");

        // Création des champs
        TextField usernameField = new TextField();
        usernameField.setPromptText("Nom d'utilisateur");
        usernameField.setStyle("-fx-padding: 10px; -fx-background-color: #E7D7C1; -fx-border-radius: 5px; -fx-border-color: #735751;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Mot de passe");
        passwordField.setStyle("-fx-padding: 10px; -fx-background-color: #E7D7C1; -fx-border-radius: 5px; -fx-border-color: #735751;");

        // Bouton Connexion
        Button loginButton = new Button("Se connecter");
        loginButton.setStyle("-fx-background-color: #8C1C13; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-font-size: 14px; -fx-border-radius: 5px;");

        // Bouton Inscription
        Button inscriptionButton = new Button("S'inscrire");
        inscriptionButton.setStyle("-fx-background-color: #BF4342; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-font-size: 14px; -fx-border-radius: 5px;");

        // Logique du bouton Connexion
        loginButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Champs vides", "Veuillez remplir tous les champs.");
                return;
            }

            Utilisateur utilisateur = utilisateurControl.authentifierUtilisateur(username, password);
            if (utilisateur != null) {
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Bienvenue, " + utilisateur.getNomUtilisateur() + " !");
                stage.close(); // Rediriger vers une autre vue ou écran
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Nom d'utilisateur ou mot de passe incorrect.");
            }
        });

        // Logique du bouton Inscription
        inscriptionButton.setOnAction(e -> {
            // Ouvrir la fenêtre d'inscription et fermer la fenêtre de connexion
            InscriptionVue inscriptionVue = new InscriptionVue(utilisateurControl);
            inscriptionVue.afficher();
            stage.close(); // Fermer la fenêtre de connexion
        });

        // Disposition de l'interface avec GridPane
        GridPane grid = new GridPane();
        grid.setVgap(15);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);
        grid.add(usernameField, 0, 0);
        grid.add(passwordField, 0, 1);
        grid.add(loginButton, 0, 2);
        grid.add(inscriptionButton, 0, 3);

        // Créer la scène et l'ajouter à la fenêtre
        Scene scene = new Scene(grid, 350, 300, Color.web("#A78A7F"));
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
