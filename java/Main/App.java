package Main;

import Control.UtilisateurControl;
import Vue.ConnexionVue;
import Database.DatabaseConnexion;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Connection connection = DatabaseConnexion.getConnection();
        if (connection == null) {
            System.err.println("Erreur : impossible de se connecter à la base de données.");
            return;
        }

        UtilisateurControl utilisateurControl = new UtilisateurControl(connection);
        ConnexionVue connexionVue = new ConnexionVue(utilisateurControl);
        connexionVue.afficher();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
