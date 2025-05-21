package DAO;

import Model.Commentaire;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentaireDAO {
    private Connection connection;

    public CommentaireDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter un commentaire
    public void ajouterCommentaire(Commentaire commentaire) {
        String query = "INSERT INTO Commentaire (Contenu, Note, Date_Heure, NIN, id_Film) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, commentaire.getContenu());
            stmt.setFloat(2, commentaire.getNote());
            stmt.setTimestamp(3, Timestamp.valueOf(commentaire.getDateHeure()));
            stmt.setString(4, commentaire.getNIN());
            stmt.setInt(5, commentaire.getIdFilm());
            stmt.executeUpdate();

            // Récupérer l'ID généré
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    commentaire.setIdCommentaire(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout du commentaire : " + e.getMessage());
        }
    }

    // Récupérer un commentaire par ID
    public Commentaire rechercherCommentaireParId(int idCommentaire) {
        String query = "SELECT * FROM Commentaire WHERE id_Commentaire = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCommentaire);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Commentaire(
                        rs.getInt("id_Commentaire"),
                        rs.getString("Contenu"),
                        rs.getInt("Note"),
                        rs.getTimestamp("Date_Heure").toLocalDateTime(),
                        rs.getString("NIN"),
                        rs.getInt("id_Film")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la recherche du commentaire : " + e.getMessage());
        }
        return null;
    }

    // Lister tous les commentaires
    public List<Commentaire> listerTousLesCommentaires() {
        List<Commentaire> commentaires = new ArrayList<>();
        String query = "SELECT * FROM Commentaire";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Commentaire commentaire = new Commentaire(
                    rs.getInt("id_Commentaire"),
                    rs.getString("Contenu"),
                    rs.getInt("Note"),
                    rs.getTimestamp("Date_Heure").toLocalDateTime(),
                    rs.getString("NIN"),
                    rs.getInt("id_Film")
                );
                commentaires.add(commentaire);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des commentaires : " + e.getMessage());
        }
        return commentaires;
    }

    // Modifier un commentaire
    public void modifierCommentaire(Commentaire commentaire) {
        String query = "UPDATE Commentaire SET Contenu = ?, Note = ?, Date_Heure = ? WHERE id_Commentaire = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, commentaire.getContenu());
            stmt.setFloat(2, commentaire.getNote());
            stmt.setTimestamp(3, Timestamp.valueOf(commentaire.getDateHeure()));
            stmt.setInt(4, commentaire.getIdCommentaire());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la modification du commentaire : " + e.getMessage());
        }
    }

    // Supprimer un commentaire
    public void supprimerCommentaire(int idCommentaire) {
        String query = "DELETE FROM Commentaire WHERE id_Commentaire = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCommentaire);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la suppression du commentaire : " + e.getMessage());
        }
    }

    // Lister les commentaires d'un film spécifique
    public List<Commentaire> getCommentairesParFilm(int idFilm) {
        List<Commentaire> commentaires = new ArrayList<>();
        String query = "SELECT * FROM Commentaire WHERE id_Film = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idFilm);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Commentaire commentaire = new Commentaire(
                        rs.getInt("id_Commentaire"),
                        rs.getString("Contenu"),
                        rs.getInt("Note"),
                        rs.getTimestamp("Date_Heure").toLocalDateTime(),
                        rs.getString("NIN"),
                        rs.getInt("id_Film")
                    );
                    commentaires.add(commentaire);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des commentaires pour le film : " + e.getMessage());
        }
        return commentaires;
    }

    // Retourner le nombre total de commentaires pour un film
    public int getNombreCommentairesParFilm(int idFilm) {
        String query = "SELECT COUNT(*) AS total FROM Commentaire WHERE id_Film = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idFilm);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du calcul du nombre de commentaires pour le film : " + e.getMessage());
        }
        return 0;
    }

    // Récupérer tous les commentaires laissés par un utilisateur spécifique
    public List<Commentaire> getCommentairesParUtilisateur(String NIN) {
        List<Commentaire> commentaires = new ArrayList<>();
        String query = "SELECT * FROM Commentaire WHERE NIN = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, NIN);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Commentaire commentaire = new Commentaire(
                        rs.getInt("id_Commentaire"),
                        rs.getString("Contenu"),
                        rs.getInt("Note"),
                        rs.getTimestamp("Date_Heure").toLocalDateTime(),
                        rs.getString("NIN"),
                        rs.getInt("id_Film")
                    );
                    commentaires.add(commentaire);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des commentaires de l'utilisateur : " + e.getMessage());
        }
        return commentaires;
    }

    // Récupérer les derniers commentaires ajoutés (par exemple, les 5 plus récents)
    public List<Commentaire> getDerniersCommentaires(int limite) {
        List<Commentaire> commentaires = new ArrayList<>();
        String query = "SELECT * FROM Commentaire ORDER BY Date_Heure DESC LIMIT ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, limite);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Commentaire commentaire = new Commentaire(
                        rs.getInt("id_Commentaire"),
                        rs.getString("Contenu"),
                        rs.getInt("Note"),
                        rs.getTimestamp("Date_Heure").toLocalDateTime(),
                        rs.getString("NIN"),
                        rs.getInt("id_Film")
                    );
                    commentaires.add(commentaire);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des derniers commentaires : " + e.getMessage());
        }
        return commentaires;
    }
}

