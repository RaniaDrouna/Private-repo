package DAO;

import Model.Ticket;
import Model.Ticket.Statut;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    private Connection connection;

    public TicketDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter un ticket
    public boolean ajouterTicket(Ticket ticket) {
        String sql = "INSERT INTO ticket (Prix_Siege, Statut, NIN, id_Seance, id_Reservation, id_Siege) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setBigDecimal(1, ticket.getPrixSiege());
            stmt.setString(2, ticket.getStatut().name());
            stmt.setString(3, ticket.getNIN());
            stmt.setInt(4, ticket.getIdSeance());
            stmt.setInt(5, ticket.getIdReservation());
            stmt.setInt(6, ticket.getIdSiege());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    ticket.setIdTicket(rs.getInt(1)); // Récupérer l'ID généré
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Récupérer un ticket par ID
    public Ticket getTicketById(int idTicket) {
        String sql = "SELECT * FROM ticket WHERE id_Ticket = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idTicket);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Ticket(
                        rs.getInt("id_Ticket"),
                        rs.getBigDecimal("Prix_Siege"),
                        Statut.valueOf(rs.getString("Statut").toUpperCase()),
                        rs.getString("NIN"),
                        rs.getInt("id_Seance"),
                        rs.getInt("id_Reservation"),
                        rs.getInt("id_Siege")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Récupérer un ticket par ID d'un utilisateur
    public List<Ticket> getTicketsByIdUser(String NIN) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ticket WHERE  NIN = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, NIN);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {  // Parcourir tous les résultats
                    Ticket ticket = new Ticket(
                            rs.getInt("id_Ticket"),
                            rs.getBigDecimal("Prix_Siege"),
                            Statut.valueOf(rs.getString("Statut").toUpperCase()),
                            rs.getString("NIN"),
                            rs.getInt("id_Seance"),
                            rs.getInt("id_Reservation"),
                            rs.getInt("id_Siege")
                    );
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des tickets : " + e.getMessage());
        }

        return tickets; // Retourne une liste vide si aucun ticket n'est trouvé
    }


 // Récupérer les tickets d'une séance donnée
    public List<Ticket> getTicketsByIdShowTime(int idSeance) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ticket WHERE id_Seance = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idSeance);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) { // Parcourir tous les résultats
                    Ticket ticket = new Ticket(
                            rs.getInt("id_Ticket"),
                            rs.getBigDecimal("Prix_Siege"),
                            Statut.valueOf(rs.getString("Statut").toUpperCase()),
                            rs.getString("NIN"),
                            rs.getInt("id_Seance"),
                            rs.getInt("id_Reservation"),
                            rs.getInt("id_Siege")
                    );
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des tickets : " + e.getMessage());
        }

        return tickets; // Retourne une liste (vide si aucun ticket trouvé)
    }


    // Mettre à jour un ticket
    public boolean mettreAJourTicket(Ticket ticket) {
        String sql = "UPDATE ticket SET Prix_Siege = ?, Statut = ?,  NIN = ?, id_Seance = ?, id_Reservation = ?, id_Siege = ? WHERE id_Ticket = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBigDecimal(1, ticket.getPrixSiege());
            stmt.setString(2, ticket.getStatut().name());
            stmt.setString(3, ticket.getNIN());
            stmt.setInt(4, ticket.getIdSeance());
            stmt.setInt(5, ticket.getIdReservation());
            stmt.setInt(6, ticket.getIdSiege());
            stmt.setInt(7, ticket.getIdTicket());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Supprimer un ticket
    public boolean supprimerTicket(int idTicket) {
        String sql = "DELETE FROM ticket WHERE id_Ticket = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idTicket);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Récupérer tous les tickets
    public List<Ticket> getTousLesTickets() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ticket";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tickets.add(new Ticket(
                        rs.getInt("id_Ticket"),
                        rs.getBigDecimal("Prix_Siege"),
                        Statut.valueOf(rs.getString("Statut").toUpperCase()),
                        rs.getString("NIN"),
                        rs.getInt("id_Seance"),
                        rs.getInt("id_Reservation"),
                        rs.getInt("id_Siege")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    // Vérifier si un siège est réservé pour une séance donnée
    public boolean estSiegeReserve(int idSeance, int idSiege) {
        String sql = "SELECT COUNT(*) FROM ticket WHERE id_Seance = ? AND id_Siege = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idSeance);
            stmt.setInt(2, idSiege);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //récupérer le dernier ID inséré
    public int getLastInsertedId() {
        int lastId = -1;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()")) {
            if (rs.next()) {
                lastId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;
    }

}
