package DAO;

import Model.Seance;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeanceDAO {
    private Connection connection;

    public SeanceDAO(Connection connection) {
        this.connection = connection;
    }

    // 1️⃣ Ajouter une séance
    public boolean ajouterSeance(Seance seance) {
        String sql = "INSERT INTO Seance (Date_Heure, Langue, Place_Disponible, NIN, id_Film, id_Salle) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, Timestamp.valueOf(seance.getDateHeure()));
            statement.setString(2, seance.getLangue());
            statement.setInt(3, seance.getPlacesDisponibles());
            statement.setString(4, seance.getNIN());
            statement.setInt(5, seance.getIdFilm());
            statement.setInt(6, seance.getIdSalle());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                seance.setIdSeance(generatedKeys.getInt(1));
            }

            System.out.println("✅ Séance ajoutée avec succès !");
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'ajout de la séance : " + e.getMessage());
            return false;
        }
    }

    // 2️⃣ Récupérer une séance par ID
    public Seance getSeanceById(int idSeance) {
        String sql = "SELECT * FROM Seance WHERE id_Seance = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idSeance);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Seance(
                    resultSet.getInt("id_Seance"),
                    resultSet.getTimestamp("Date_Heure").toLocalDateTime(),
                    resultSet.getString("Langue"),
                    resultSet.getInt("Place_Disponible"),
                    resultSet.getString("NIN"),
                    resultSet.getInt("id_Film"),
                    resultSet.getInt("id_Salle")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération de la séance : " + e.getMessage());
        }
        return null;
    }

    // 3️⃣ Récupérer toutes les séances
    public List<Seance> getAllSeances() {
        List<Seance> seances = new ArrayList<>();
        String sql = "SELECT * FROM Seance";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                seances.add(new Seance(
                    resultSet.getInt("id_Seance"),
                    resultSet.getTimestamp("Date_Heure").toLocalDateTime(),
                    resultSet.getString("Langue"),
                    resultSet.getInt("Place_Disponible"),
                    resultSet.getString("NIN"),
                    resultSet.getInt("id_Film"),
                    resultSet.getInt("id_Salle")
                ));
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des séances : " + e.getMessage());
        }
        return seances;
    }

    // 4️⃣ Mettre à jour une séance
    public boolean updateSeance(Seance seance) {
        String sql = "UPDATE Seance SET Date_Heure = ?, Langue = ?, Place_Disponible = ?, NIN = ?, id_Film = ?, id_Salle = ? WHERE id_Seance = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setTimestamp(1, Timestamp.valueOf(seance.getDateHeure()));
            statement.setString(2, seance.getLangue());
            statement.setInt(3, seance.getPlacesDisponibles());
            statement.setString(4, seance.getNIN());
            statement.setInt(5, seance.getIdFilm());
            statement.setInt(6, seance.getIdSalle());
            statement.setInt(7, seance.getIdSeance());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Séance mise à jour !");
                return true;
            } else {
                System.out.println("❌ Aucune séance mise à jour.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la mise à jour : " + e.getMessage());
        }
        return false;
    }

    // 5️⃣ Supprimer une séance
    public boolean supprimerSeance(int idSeance) {
        String sql = "DELETE FROM Seance WHERE id_Seance = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idSeance);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Séance supprimée !");
                return true;
            } else {
                System.out.println("❌ Aucune séance supprimée.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la suppression de la séance : " + e.getMessage());
        }
        return false;
    }

    // 6️⃣ Récupérer les séances d’un film
    public List<Seance> getSeancesParFilm(int idFilm) {
        List<Seance> seances = new ArrayList<>();
        String sql = "SELECT * FROM Seance WHERE id_Film = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idFilm);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                seances.add(new Seance(
                    resultSet.getInt("id_Seance"),
                    resultSet.getTimestamp("Date_Heure").toLocalDateTime(),
                    resultSet.getString("Langue"),
                    resultSet.getInt("Place_Disponible"),
                    resultSet.getString("NIN"),
                    resultSet.getInt("id_Film"),
                    resultSet.getInt("id_Salle")
                ));
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des séances par film : " + e.getMessage());
        }
        return seances;
    }

    // 7️⃣ Récupérer les séances d’une salle
    public List<Seance> getSeancesParSalle(int idSalle) {
        List<Seance> seances = new ArrayList<>();
        String sql = "SELECT * FROM Seance WHERE id_Salle = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idSalle);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                seances.add(new Seance(
                    resultSet.getInt("id_Seance"),
                    resultSet.getTimestamp("Date_Heure").toLocalDateTime(),
                    resultSet.getString("Langue"),
                    resultSet.getInt("Place_Disponible"),
                    resultSet.getString("NIN"),
                    resultSet.getInt("id_Film"),
                    resultSet.getInt("id_Salle")
                ));
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des séances par salle : " + e.getMessage());
        }
        return seances;
    }

    // 8️⃣ Vérifier disponibilité d'une séance
    public boolean estSeanceDisponible(int idSeance) {
        String sql = "SELECT Place_Disponible FROM Seance WHERE id_Seance = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idSeance);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("Place_Disponible") > 0;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la vérification des places disponibles : " + e.getMessage());
        }
        return false;
    }

    // 9️⃣ Réserver une place
    public boolean reserverPlace(int idSeance) {
        if (!estSeanceDisponible(idSeance)) {
            System.out.println("⚠️ Plus de places disponibles pour cette séance !");
            return false;
        }

        String sql = "UPDATE Seance SET Place_Disponible = Place_Disponible - 1 WHERE id_Seance = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idSeance);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la réservation de la place : " + e.getMessage());
        }
        return false;
    }
}

