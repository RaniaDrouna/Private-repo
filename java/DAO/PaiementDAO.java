package DAO;

import Model.Paiement;
import Model.Paiement.Methode;
import Model.Paiement.Statut;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaiementDAO {

    private Connection connection;

    public PaiementDAO(Connection connection) {
        this.connection = connection;
    }

    // 1️⃣ Ajouter un paiement
    public boolean ajouterPaiement(Paiement paiement) {
        String sql = "INSERT INTO paiement (Montant_Total, Date_Heure, Statut, Methode, id_Reservation, NIN) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setFloat(1, paiement.getMontantTotal());
            statement.setTimestamp(2, Timestamp.valueOf(paiement.getDateHeure()));
            statement.setString(3, paiement.getStatut().name());
            statement.setString(4, paiement.getMethode().name());
            statement.setInt(5, paiement.getIdReservation());
            statement.setString(6, paiement.getNIN());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        paiement.setIdPaiement(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du paiement : " + e.getMessage());
        }
        return false;
    }

    // 2️⃣ Récupérer un paiement par son ID
    public Paiement getPaiementParId(int id) throws SQLException {
        String sql = "SELECT * FROM paiement WHERE id_Paiement = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Statut (enum) en majuscules et suppression des espaces
                    String statutString = resultSet.getString("Statut").trim().toUpperCase();
                    Statut statut = Statut.valueOf(statutString);

                    // Méthode de paiement (enum)
                    String methodeString = resultSet.getString("Methode").trim().toUpperCase();
                    Methode methode = Methode.valueOf(methodeString);

                    return new Paiement(
                        resultSet.getInt("id_Paiement"),
                        resultSet.getFloat("Montant_Total"),
                        resultSet.getTimestamp("Date_Heure").toLocalDateTime(),
                        statut,
                        methode,
                        resultSet.getInt("id_Reservation"),
                        resultSet.getString("NIN")
                    );
                }
            }
        }
        return null; // Aucun paiement trouvé
    }

    // 3️⃣ Mettre à jour un paiement
    public void mettreAJourPaiement(Paiement paiement) throws SQLException {
        // Vérifier d'abord la validité de la méthode de paiement avant la requête SQL
        if (paiement.getMethode() != Methode.CARTE_CIB && paiement.getMethode() != Methode.CARTE_EDAHABIA) {
            throw new IllegalArgumentException("❌ Méthode de paiement invalide !");
        }

        String sql = "UPDATE paiement SET Montant_Total = ?, Date_Heure = ?, Statut = ?, Methode = ?, id_Reservation = ?, NIN = ? WHERE id_Paiement = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setFloat(1, paiement.getMontantTotal());
            statement.setTimestamp(2, Timestamp.valueOf(paiement.getDateHeure()));
            statement.setString(3, paiement.getStatut().name());
            statement.setString(4, paiement.getMethode().name());
            statement.setInt(5, paiement.getIdReservation());
            statement.setString(6, paiement.getNIN());
            statement.setInt(7, paiement.getIdPaiement());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Paiement mis à jour avec succès !");
            } else {
                System.out.println("❌ Aucune mise à jour effectuée, ID introuvable.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la mise à jour du paiement : " + e.getMessage());
            throw e;
        }
    }

    // 4️⃣ Supprimer un paiement
    public boolean supprimerPaiement(int idPaiement) {
        String sql = "DELETE FROM paiement WHERE id_Paiement = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPaiement);
            return statement.executeUpdate() > 0; // Retourne true si une ligne a été supprimée
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du paiement : " + e.getMessage());
        }
        return false; // En cas d'erreur, on retourne false
    }

    // 5️⃣ Récupérer tous les paiements
    public List<Paiement> getTousLesPaiements() {
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM paiement";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                // Statut (enum) en majuscules et suppression des espaces
                String statutString = resultSet.getString("Statut").trim().toUpperCase();
                Statut statut = Statut.valueOf(statutString);

                // Méthode de paiement (enum)
                String methodeString = resultSet.getString("Methode");
                Methode methode = Methode.valueOf(methodeString);

                paiements.add(new Paiement(
                    resultSet.getInt("id_Paiement"),
                    resultSet.getFloat("Montant_Total"),
                    resultSet.getTimestamp("Date_Heure").toLocalDateTime(),
                    statut,
                    methode,
                    resultSet.getInt("id_Reservation"),
                    resultSet.getString("NIN")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des paiements : " + e.getMessage());
        }
        return paiements;
    }

    // 6️⃣ Récupérer les paiements d'un utilisateur donné
    public List<Paiement> getPaiementsParUtilisateur(String NIN) {
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM paiement WHERE NIN = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, NIN);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Statut (enum) en majuscules et suppression des espaces
                    String statutString = resultSet.getString("Statut").trim().toUpperCase();
                    Statut statut = Statut.valueOf(statutString);

                    // Méthode de paiement (enum)
                    String methodeString = resultSet.getString("Methode");
                    Methode methode = Methode.valueOf(methodeString);

                    paiements.add(new Paiement(
                        resultSet.getInt("id_Paiement"),
                        resultSet.getFloat("Montant_Total"),
                        resultSet.getTimestamp("Date_Heure").toLocalDateTime(),
                        statut,
                        methode,
                        resultSet.getInt("id_Reservation"),
                        resultSet.getString("NIN")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des paiements par utilisateur : " + e.getMessage());
        }
        return paiements;
    }

    // 7️⃣ Vérifier si un paiement a été effectué (statut "payé")
    public boolean estPaiementEffectue(int idPaiement) {
        String sql = "SELECT statut FROM paiement WHERE id_Paiement = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPaiement);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String statutEnBase = resultSet.getString("Statut").toUpperCase();
                    for (Statut s : Statut.values()) {
                        if (s.name().equals(statutEnBase) && s == Statut.PAYE) {
                            return true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification du paiement : " + e.getMessage());
        }
        return false;
    }

    // 8️⃣ Récupérer les paiements par statut ("en attente", "payé", "refusé", etc.)
    public List<Paiement> getPaiementsParStatut(String statut) {
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM paiement WHERE Statut = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, statut);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Statut (enum) en majuscules et suppression des espaces
                    String statutString = resultSet.getString("Statut").trim().toUpperCase();
                    Statut statutPaiement = Statut.valueOf(statutString);

                    // Méthode de paiement (enum)
                    String methodeString = resultSet.getString("Methode");
                    Methode methode = Methode.valueOf(methodeString);

                    paiements.add(new Paiement(
                        resultSet.getInt("id_Paiement"),
                        resultSet.getFloat("Montant_Total"),
                        resultSet.getTimestamp("Date_Heure").toLocalDateTime(),
                        statutPaiement,
                        methode,
                        resultSet.getInt("id_Reservation"),
                        resultSet.getString("NIN")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des paiements par statut : " + e.getMessage());
        }
        return paiements;
    }
}



