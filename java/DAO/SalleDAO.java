package DAO;

import Model.Salle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalleDAO {
    private Connection connection;

    public SalleDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter une salle
    public void ajouterSalle(Salle salle) throws SQLException {
        String query = "INSERT INTO salle (Nom_Salle, Capacite, id_Cinema) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, salle.getNomSalle());
            stmt.setInt(2, salle.getCapacite());
            stmt.setInt(3, salle.getIdCinema());
            stmt.executeUpdate();
        }
    }

    // Supprimer une salle
    public void supprimerSalle(int idSalle) throws SQLException {
        String query = "DELETE FROM salle WHERE id_Salle = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idSalle);
            stmt.executeUpdate();
        }
    }

    // Modifier une salle
    public void modifierSalle(Salle salle) throws SQLException {
        String query = "UPDATE salle SET Nom_Salle = ?, Capacite = ?, id_Cinema = ? WHERE id_Salle = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, salle.getNomSalle());
            stmt.setInt(2, salle.getCapacite());
            stmt.setInt(3, salle.getIdCinema());
            stmt.setInt(4, salle.getIdSalle());
            stmt.executeUpdate();
        }
    }

    // Rechercher une salle par son ID
    public Salle rechercherSalleParId(int idSalle) throws SQLException {
        String query = "SELECT * FROM salle WHERE id_Salle = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idSalle);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Salle(
                    rs.getInt("id_Salle"),
                    rs.getString("Nom_Salle"),
                    rs.getInt("Capacite"),
                    rs.getInt("id_Cinema")
                );
            }
        }
        return null;
    }

    // Afficher toutes les salles
    public List<Salle> listerToutesLesSalles() throws SQLException {
        List<Salle> salles = new ArrayList<>();
        String query = "SELECT * FROM salle";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                salles.add(new Salle(
                    rs.getInt("id_Salle"),
                    rs.getString("Nom_Salle"),
                    rs.getInt("Capacite"),
                    rs.getInt("id_Cinema")
                ));
            }
        }
        return salles;
    }
    
    //récupérer toutes les salles d'un cinéma
    public List<Salle> getSallesByCinema(int idCinema) {
        List<Salle> salles = new ArrayList<>();
        String sql = "SELECT * FROM salle WHERE id_Cinema = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCinema);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Salle salle = new Salle(
                    rs.getInt("id_Salle"),
                    rs.getString("Nom_Salle"),  // Attention ici : "Nom_Salle" est un varchar, mais ta classe `Salle` attend un `int` (numéro de salle).
                    rs.getInt("Capacite"),
                    rs.getInt("id_Cinema")
                );
                salles.add(salle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salles;
    }

     // Recherche une salle dans la base de données en fonction de son nom.      
    public Salle rechercherSalleParNom(String nomSalle) {
        Salle salle = null;
        String sql = "SELECT * FROM salle WHERE Nom_Salle = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomSalle);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                salle = new Salle(
                    rs.getInt("id_Salle"),
                    rs.getString("Nom_Salle"),
                    rs.getInt("Capacite"),
                    rs.getInt("id_Cinema")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche de la salle par nom : " + e.getMessage());
        }

        return salle;
    }

    public boolean salleExiste(int idSalle) {
        String sql = "SELECT COUNT(*) FROM salle WHERE id_Salle = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idSalle);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de l'existence de la salle : " + e.getMessage());
        }
        return false;
    }

}
