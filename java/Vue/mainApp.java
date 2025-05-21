package Vue;

import Control.*;
import Database.DatabaseConnexion;
import Model.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class mainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialize database connection
        Connection connection = DatabaseConnexion.getConnection();

        // Initialize controllers
        UtilisateurControl utilisateurControl = new UtilisateurControl(connection);

        // Show login screen
        ConnexionVue connexionVue = new ConnexionVue(utilisateurControl);
        connexionVue.afficher();

        // For testing purposes, you can directly show other screens:
        // showAdminDashboard(primaryStage, connection);
        // showClientDashboard(primaryStage, connection);
        // showMovieListing(primaryStage, connection);
    }

    private void showAdminDashboard(Stage primaryStage, Connection connection) {
        AdminDashboardVue adminDashboard = new AdminDashboardVue(primaryStage);
        adminDashboard.show();
    }

    private void showClientDashboard(Stage primaryStage, Connection connection) {
        ClientDashboardVue clientDashboard = new ClientDashboardVue(primaryStage);
        clientDashboard.show();
    }

    private void showMovieListing(Stage primaryStage, Connection connection) {
        FilmControl filmControl = new FilmControl(connection);
        List<Film> films = filmControl.getTousLesFilms();
        MovieListingVue movieListing = new MovieListingVue(primaryStage, films);
        movieListing.show();
    }
}