package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import Model.Utilisateur;
import Model.Utilisateur.Role;

public class UtilisateurDAO {
	
	 private Connection connection;

	    public UtilisateurDAO(Connection connection) {
	        this.connection = connection;
	    }

	    // ajouter un utilisateur dans la base
	    public boolean ajouterUtilisateur(Utilisateur utilisateur) {
	        String sql = "INSERT INTO utilisateur (NIN, Nom_Utilisateur, Mot_Passe, Date_Naissance, Role) VALUES (?, ?, ?, ?, ?)";
	        
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        	stmt.setString(1, utilisateur.getNIN()); // Définit le numero d'identite
	            stmt.setString(2, utilisateur.getNomUtilisateur()); // Définit le nom d'utilisateur
	            stmt.setString(3, utilisateur.getMotPasse()); // Définit le mot de passe
	            stmt.setDate(4, java.sql.Date.valueOf(utilisateur.getDateDeNaissance()));
	            stmt.setString(5, utilisateur.getRole().name()); // Définit le rôle (Admin ou Client)

	            int rowsInserted = stmt.executeUpdate(); // Exécute la requête
	            return rowsInserted > 0; // Retourne vrai si au moins une ligne a été insérée
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    // mettre à jour les informations d'un utilisateur (nom, mot de passe)
	    public boolean mettreAJourUtilisateur(Utilisateur utilisateur) {
	        String sql = "UPDATE utilisateur SET Nom_Utilisateur = ?, Mot_Passe = ? WHERE  NIN = ?";
	        
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, utilisateur.getNomUtilisateur()); // Met à jour le nom
	            stmt.setString(2, utilisateur.getMotPasse()); // Met à jour le mot de passe
	            stmt.setString(3, utilisateur.getNIN()); // Condition WHERE

	            int rowsUpdated = stmt.executeUpdate(); // Exécute la requête
	            return rowsUpdated > 0; // Retourne true si au moins une ligne a été modifiée
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    //Permet de changer le mot de passe d’un utilisateur
	    public boolean changerMotDePasse(String NIN, String nouveauMotDePasse) {
	        String sql = "UPDATE utilisateur SET Mot_Passe = ? WHERE  NIN = ?";
	        
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, nouveauMotDePasse);
	            stmt.setString(2, NIN);

	            int rowsUpdated = stmt.executeUpdate();
	            return rowsUpdated > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    //récupèrer un utilisateur en fonction de son id_Utilisateur.
	    public Utilisateur getUtilisateurParId(String NIN) {
	        String sql = "SELECT * FROM utilisateur WHERE  NIN = ?";
	        
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, NIN); // On remplace le ? par l'id de l'utilisateur
	            ResultSet rs = stmt.executeQuery(); // Exécute la requête

	            if (rs.next()) { // Si un utilisateur est trouvé
	                return new Utilisateur(
	                    rs.getString("NIN"),
	                    rs.getString("Nom_Utilisateur"),
	                    rs.getString("Mot_Passe"),
	                    rs.getDate("Date_Naissance").toLocalDate(),
	                    Role.valueOf(rs.getString("Role"))
	                );
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null; // Retourne null si aucun utilisateur n'est trouvé
	    }
 
	    //récupèrer tous les utilisateurs dans une liste.
	    public List<Utilisateur> getTousLesUtilisateurs() {
	        List<Utilisateur> utilisateurs = new ArrayList<>();
	        String sql = "SELECT * FROM utilisateur";

	        try (Statement stmt = connection.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            while (rs.next()) {
	                utilisateurs.add(new Utilisateur(
	                    rs.getString("NIN"),
	                    rs.getString("Nom_Utilisateur"),
	                    rs.getString("Mot_Passe"),
	                    rs.getDate("Date_Naissance").toLocalDate(),
	                    Role.valueOf(rs.getString("Role"))
	                ));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return utilisateurs;
	    }
	    
	    //Verifier si un nom d'utilisateur existe déjà dans la base
	    public boolean utilisateurExiste(String nomUtilisateur) {
	        String sql = "SELECT COUNT(*) FROM utilisateur WHERE Nom_Utilisateur = ?";
	        
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, nomUtilisateur);
	            ResultSet rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	                return rs.getInt(1) > 0; // Si le COUNT > 0, l'utilisateur existe
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false; // L'utilisateur n'existe pas
	    }
	    
	    //Vérifie si les identifiants (nom d’utilisateur + mot de passe) sont corrects
	    public Utilisateur authentifierUtilisateur(String nomUtilisateur, String motPasse) {
	        String sql = "SELECT * FROM utilisateur WHERE Nom_Utilisateur = ? AND Mot_Passe = ?";
	        
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, nomUtilisateur);
	            stmt.setString(2, motPasse);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                return new Utilisateur(
	                    rs.getString("NIN"),
	                    rs.getString("Nom_Utilisateur"),
	                    rs.getString("Mot_Passe"),
	                    rs.getDate("Date_Naissance").toLocalDate(),
	                    Role.valueOf(rs.getString("Role"))
	                );
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null; // Retourne null si l'authentification échoue
	    }

	    //Retourne tous les utilisateurs ayant un rôle spécifique (Admin ou Client)
	    public List<Utilisateur> getUtilisateursParRole(Role role) {
	        List<Utilisateur> utilisateurs = new ArrayList<>();
	        String sql = "SELECT * FROM utilisateur WHERE Role = ?";

	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        	stmt.setString(1, role.name());
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                utilisateurs.add(new Utilisateur(
	                    rs.getString("NIN"),
	                    rs.getString("Nom_Utilisateur"),
	                    rs.getString("Mot_Passe"),
	                    rs.getDate("Date_Naissance").toLocalDate(),
	                    Role.valueOf(rs.getString("Role"))
	                ));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return utilisateurs;
	    }

	    //Retourne le nombre total d’utilisateurs enregistrés
	    public int compterUtilisateurs() {
	        String sql = "SELECT COUNT(*) FROM utilisateur";
	        
	        try (Statement stmt = connection.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            if (rs.next()) {
	                return rs.getInt(1); // Retourne le nombre total d'utilisateurs
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return 0; // Retourne 0 si erreur
	    }

	    //supprimer un utilisateur en fonction de son ID.
	    public boolean supprimerUtilisateur(String NIN) {
	        String sql = "DELETE FROM utilisateur WHERE  NIN = ?";
	        
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, NIN); // Remplace le ? par l'ID de l'utilisateur
	            int rowsDeleted = stmt.executeUpdate(); // Exécute la requête
	            return rowsDeleted > 0; // Si au moins une ligne est supprimée, retourne vrai
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

}
