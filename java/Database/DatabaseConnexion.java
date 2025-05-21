package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnexion {
    private static final String URL = "jdbc:mysql://localhost:3308/movie ticket"; // Port mis à jour
    private static final String USER = "root"; //  utilisateur MySQL
    private static final String PASSWORD = ""; //  mot de passe 

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie !");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC introuvable !");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données !");
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        // Test de connexion
        getConnection();
    }
}

