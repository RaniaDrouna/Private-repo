package DAO;

import Model.Siege;
import Model.Siege.Statut; // Correct import of Statut enum

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SiegeDAO {
    private Connection connection;

    public SiegeDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter un siège
    public void ajouterSiege(Siege siege) throws SQLException {
        String sql = "INSERT INTO siege (Nom_Siege, Statut, id_Salle) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, siege.getNomSiege());
            statement.setString(2, siege.getStatut().name()); // Convert Statut to String
            statement.setInt(3, siege.getIdSalle());
            statement.executeUpdate();
            
            // Récupérer l'ID généré
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    siege.setIdSiege(generatedKeys.getInt(1)); // Mise à jour de l'ID
                }
            }
        }
    }

    // Récupérer tous les sièges
    public List<Siege> getTousLesSieges() throws SQLException {
        List<Siege> sieges = new ArrayList<>();
        String sql = "SELECT * FROM siege";
        
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                sieges.add(new Siege(
                    resultSet.getInt("id_Siege"),
                    resultSet.getString("Nom_Siege"),
                    Statut.valueOf(resultSet.getString("Statut").trim().toUpperCase()), // Convert from String to Statut
                    resultSet.getInt("id_Salle")
                ));
            }
        }
        return sieges;
    }

    // Récupérer un siège par son ID
    public Siege getSiegeById(int idSiege) throws SQLException {
        String sql = "SELECT * FROM siege WHERE id_Siege = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idSiege);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Siege(
                        resultSet.getInt("id_Siege"),
                        resultSet.getString("Nom_Siege"),
                        Statut.valueOf(resultSet.getString("Statut").trim().toUpperCase()), // Convert from String to Statut
                        resultSet.getInt("id_Salle")
                    );
                }
            }
        }
        return null;
    }

    // Modifier un siège (nom, salle, statut)
    public void updateSiege(Siege siege) throws SQLException {
        String sql = "UPDATE siege SET Nom_Siege = ?, id_Salle = ?, Statut = ? WHERE id_Siege = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, siege.getNomSiege());         // Nom_Siege
            statement.setInt(2, siege.getIdSalle());             // id_Salle
            statement.setString(3, siege.getStatut().name());    // Statut (converted to String)
            statement.setInt(4, siege.getIdSiege());             // id_Siege
            statement.executeUpdate();
        }
    }

    // Modifier uniquement le statut d’un siège
    public void modifierStatutSiege(int idSiege, Statut statut) throws SQLException {
        String sql = "UPDATE siege SET Statut = ? WHERE id_Siege = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, statut.name()); // Statut (converted to String)
            statement.setInt(2, idSiege);
            statement.executeUpdate();
        }
    }

    // Récupérer tous les sièges d’une salle donnée
    public List<Siege> getSiegesBySalle(int idSalle) {
        List<Siege> sieges = new ArrayList<>();
        try {
            String sql = "SELECT * FROM siege WHERE id_Salle = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idSalle);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                sieges.add(new Siege(
                    rs.getInt("id_Siege"),
                    rs.getString("Nom_Siege"),
                    Statut.valueOf(rs.getString("Statut").toUpperCase()), // Convert from String to Statut
                    rs.getInt("id_Salle")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sieges;
    }

    // Vérifier si un siège est attribué à un ticket
    public boolean estAttribueATicket(int idSiege) throws SQLException {
        String sql = "SELECT COUNT(*) FROM ticket WHERE id_Siege = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, idSiege);
        
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0; // Si COUNT(*) > 0, alors le siège est attribué
        }
        return false;
    }

    // Supprimer un siège
    public void supprimerSiege(int idSiege) throws SQLException {
        String sql = "DELETE FROM siege WHERE id_Siege = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idSiege);
            statement.executeUpdate();
        }
    }

    // Récupérer un siège par son nom et sa salle
    public Siege getSiegeByNomEtSalle(String nomSiege, int idSalle) {
        Siege siege = null;
        String sql = "SELECT * FROM siege WHERE Nom_Siege = ? AND id_Salle = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomSiege);
            stmt.setInt(2, idSalle);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String statutStr = rs.getString("Statut");
                    Statut statut = null;
                    if (statutStr != null) {
                        try {
                            statut = Statut.valueOf(statutStr.toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.err.println("Statut inconnu : " + statutStr);
                        }
                    }

                    siege = new Siege(
                        rs.getInt("id_Siege"),
                        rs.getString("Nom_Siege"),
                        statut,
                        rs.getInt("id_Salle")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return siege;
    }
}
