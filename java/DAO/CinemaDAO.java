package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Cinema;

public class CinemaDAO {
    private Connection connection;

    public CinemaDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter un cinéma
    public boolean ajouterCinema(Cinema cinema) {
        String sql = "INSERT INTO Cinema (Nom_Cinema, Adresse, Numero_Telephone, Nombre_Salle) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cinema.getNomCinema());
            stmt.setString(2, cinema.getAdresse());
            stmt.setString(3, cinema.getNumeroTelephone());
            stmt.setInt(4, cinema.getNombreSalle());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Récupérer un cinéma par ID
    public Cinema getCinemaById(int id) {
        String sql = "SELECT * FROM Cinema WHERE id_Cinema = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cinema(
                    rs.getInt("id_Cinema"),
                    rs.getString("Nom_Cinema"),
                    rs.getString("Adresse"),
                    rs.getString("Numero_Telephone"),
                    rs.getInt("Nombre_Salle")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Mettre à jour un cinéma
    public boolean mettreAJourCinema(Cinema cinema) {
        String sql = "UPDATE Cinema SET Nom_Cinema = ?, Adresse = ?, Numero_Telephone = ?, Nombre_Salle = ? WHERE id_Cinema = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cinema.getNomCinema());
            stmt.setString(2, cinema.getAdresse());
            stmt.setString(3, cinema.getNumeroTelephone());
            stmt.setInt(4, cinema.getNombreSalle());
            stmt.setInt(5, cinema.getIdCinema());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Supprimer un cinéma
    public boolean supprimerCinema(int id) {
        String sql = "DELETE FROM Cinema WHERE id_Cinema = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Rechercher des cinémas par nom
    public List<Cinema> rechercherCinemasParNom(String nom) throws SQLException {
        List<Cinema> cinemas = new ArrayList<>();
        String sql = "SELECT * FROM Cinema WHERE Nom_Cinema = ?"; // Suppression du LIKE pour une correspondance exacte

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cinemas.add(new Cinema(
                    rs.getInt("id_Cinema"),
                    rs.getString("Nom_Cinema"),
                    rs.getString("Adresse"),
                    rs.getString("Numero_Telephone"),
                    rs.getInt("Nombre_Salle")
                ));
            }
        }
        return cinemas; // Lancer une exception si une erreur survient
    }
    
 // Rechercher des cinémas par ville
    public List<Cinema> rechercherCinemasParVille(String ville) {
        List<Cinema> cinemas = new ArrayList<>();
        String sql = "SELECT * FROM cinema WHERE Adresse LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + ville + "%"); // Recherche partielle dans l'adresse
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Cinema cinema = new Cinema(
                    resultSet.getInt("id_Cinema"),
                    resultSet.getString("Nom_Cinema"),
                    resultSet.getString("Adresse"),
                    resultSet.getString("Numero_Telephone"),
                    resultSet.getInt("Nombre_Salle")
                );
                cinemas.add(cinema);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return cinemas;
    }
    
    //Récupérer la liste de tous les cinémas 
    public List<Cinema> getTousLesCinemas() {
        List<Cinema> cinemas = new ArrayList<>();
        String sql = "SELECT * FROM cinema";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Cinema cinema = new Cinema(
                    resultSet.getInt("id_Cinema"),
                    resultSet.getString("Nom_Cinema"),
                    resultSet.getString("Adresse"),
                    resultSet.getString("Numero_Telephone"),
                    resultSet.getInt("Nombre_Salle")
                );
                cinemas.add(cinema);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return cinemas;
    }

 // Supprimer un cinéma et toutes ses salles associées
    public boolean supprimerCinemaEtSalles(int idCinema) {
        String sqlSalles = "DELETE FROM Salle WHERE id_Cinema = ?";
        String sqlCinema = "DELETE FROM Cinema WHERE id_Cinema = ?";

        try (PreparedStatement stmtSalles = connection.prepareStatement(sqlSalles);
             PreparedStatement stmtCinema = connection.prepareStatement(sqlCinema)) {

            // Supprimer d'abord les salles associées
            stmtSalles.setInt(1, idCinema);
            stmtSalles.executeUpdate();

            // Ensuite, supprimer le cinéma
            stmtCinema.setInt(1, idCinema);
            int rowsAffected = stmtCinema.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
