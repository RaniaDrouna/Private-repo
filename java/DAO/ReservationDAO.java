package DAO;

import Model.Reservation;
import Model.Reservation.Statut;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    private Connection connection;

    // Constructeur pour initialiser la connexion à la base de données
    public ReservationDAO(Connection connection) {
        this.connection = connection;
    }

    // 1️⃣ Ajouter une réservation
    public boolean ajouterReservation(Reservation reservation) {
        String sql = "INSERT INTO reservation (Statut, Date_Heure, NIN) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, reservation.getStatut().name()); // Enregistrer l'énumération en tant que String
            stmt.setTimestamp(2, Timestamp.valueOf(reservation.getDateHeure())); // Convertir LocalDateTime en Timestamp
            stmt.setString(3, reservation.getNIN()); // NIN

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                // Récupérer l'ID généré automatiquement
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        reservation.setIdReservation(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2️⃣ Récupérer une réservation par ID
    public Reservation getReservationParId(int idReservation) {
        String sql = "SELECT * FROM reservation WHERE id_Reservation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idReservation);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Reservation(
                        rs.getInt("id_Reservation"),
                        Statut.valueOf(rs.getString("Statut")), // Conversion du String vers l'énumération Statut
                        rs.getTimestamp("Date_Heure").toLocalDateTime(), // Conversion du Timestamp vers LocalDateTime
                        rs.getString("NIN")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 3️⃣ Récupérer toutes les réservations d’un utilisateur
    public List<Reservation> getReservationParNIN(String NIN) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation WHERE NIN = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, NIN);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    reservations.add(new Reservation(
                        rs.getInt("id_Reservation"),
                        Statut.valueOf(rs.getString("Statut")),
                        rs.getTimestamp("Date_Heure").toLocalDateTime(),
                        rs.getString("NIN")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // 4️⃣ Mettre à jour une réservation
    public boolean mettreAJourReservation(Reservation reservation) {
        String sql = "UPDATE reservation SET Statut = ?, Date_Heure = ?, NIN = ? WHERE id_Reservation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, reservation.getStatut().name());
            stmt.setTimestamp(2, Timestamp.valueOf(reservation.getDateHeure()));
            stmt.setString(3, reservation.getNIN());
            stmt.setInt(4, reservation.getIdReservation());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5️⃣ Lister toutes les réservations
    public List<Reservation> listerToutesLesReservation() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                reservations.add(new Reservation(
                    rs.getInt("id_Reservation"),
                    Statut.valueOf(rs.getString("Statut")),
                    rs.getTimestamp("Date_Heure").toLocalDateTime(),
                    rs.getString("NIN")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // 6️⃣ Lister toutes les réservations d'un utilisateur
    public List<Reservation> listerToutesLesReservationDunUtilisateur(String NIN) {
        return getReservationParNIN(NIN);
    }

    // 7️⃣ Lister toutes les réservations par statut
    public List<Reservation> listerReservationParStatut(Statut statut) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation WHERE Statut = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, statut.name());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    reservations.add(new Reservation(
                        rs.getInt("id_Reservation"),
                        Statut.valueOf(rs.getString("Statut")),
                        rs.getTimestamp("Date_Heure").toLocalDateTime(),
                        rs.getString("NIN")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // 8️⃣ Vérifier si une réservation existe pour un utilisateur
    public boolean verifierSiReservationExistePourUtilisateur(String NIN) {
        String sql = "SELECT COUNT(*) FROM reservation WHERE NIN = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, NIN);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 9️⃣ Supprimer une réservation en utilisant l'id d'une réservation
    public boolean supprimerReservation(int idReservation) {
        String sql = "DELETE FROM reservation WHERE id_Reservation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idReservation);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}


