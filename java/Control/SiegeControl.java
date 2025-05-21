package Control;
 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.SiegeDAO;
import Database.DatabaseConnexion;
import Model.Siege;

 

public class SiegeControl {
    private SiegeDAO siegeDAO;

    public SiegeControl() {
        this.siegeDAO = new SiegeDAO(DatabaseConnexion.getConnection());   
    }
    
    //1 Verifier si un siege est disponible
    public boolean estDisponible(int idSiege) throws SQLException {
        Siege siege = siegeDAO.getSiegeById(idSiege);
        return siege != null && siege.getStatut() == Siege.Statut.DISPONIBLE;
    }

    //2 Réserver un siège
    public void reserverSiege(int idSiege) throws SQLException {
        if (estDisponible(idSiege)) {
            siegeDAO.modifierStatutSiege(idSiege, Siege.Statut.RESERVER);
            System.out.println(" Siège " + idSiege + " réservé avec succès !");
        } else {
            System.out.println(" Erreur : Le siège " + idSiege + " est déjà réservé ou inexistant.");
        }
    }

    //3 Remettre un siège à l'état disponible après annulation d'une réservation
    public void libererSiege(int idSiege) throws SQLException {
        if (!estDisponible(idSiege)) {  // Vérifie que le siège est bien réservé
            siegeDAO.modifierStatutSiege(idSiege, Siege.Statut.DISPONIBLE);
            System.out.println(" Le siège " + idSiege + " a été libéré avec succès.");
        } else {
            System.out.println(" Le siège " + idSiege + " était déjà disponible.");
        }
    }

    //4 écupérer tous les sièges de la salle depuis la base de données
    public List<Siege> getSiegesBySalle(int idSalle) throws SQLException {
        List<Siege> sieges = siegeDAO.getSiegesBySalle(idSalle);
        if (sieges.isEmpty()) {
            System.out.println(" Aucun siège trouvé pour la salle " + idSalle);
        }
        return sieges;
    }

    //5 Changer le statut d’un siège
    public void changerStatutSiege(int idSiege, Siege.Statut nouveauStatut) {
        try {
            siegeDAO.modifierStatutSiege(idSiege, nouveauStatut); // On appelle la méthode DAO
            System.out.println(" Statut du siège " + idSiege + " mis à jour en : " + nouveauStatut);
        } catch (SQLException e) {
            System.out.println(" Échec de la mise à jour du siège " + idSiege);
            e.printStackTrace();
        }
    }

    //6 Vérifier si un siège est attribué à un ticket
    public boolean estAttribueATicket(int idSiege) {
        try {
            return siegeDAO.estAttribueATicket(idSiege);
        } catch (SQLException e) {
            System.out.println("⚠️ Erreur lors de la vérification du siège " + idSiege);
            e.printStackTrace();
            return false; // Par défaut, on suppose que le siège n'est pas attribué en cas d'erreur
        }
    }

     // 7 Ajouter un siège
    public void ajouterSiege(Siege siege) {
        try {
            siegeDAO.ajouterSiege(siege);
            System.out.println(" Siège ajouté avec succès !");
        } catch (Exception e) {
            System.err.println(" Erreur lors de l'ajout du siège : " + e.getMessage());
        }
    }

    public List<Siege> getTousLesSieges() {
        try {
            return siegeDAO.getTousLesSieges();
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des sièges : " + e.getMessage());
            return new ArrayList<>(); // Retourne une liste vide en cas d'erreur
        }
    }
   //  8 Met à jour un siège existant
    public void updateSiege(Siege siege) {
        try {
            siegeDAO.updateSiege(siege);
            System.out.println(" Siège mis à jour avec succès !");
        } catch (Exception e) {
            System.err.println(" Erreur lors de la mise à jour du siège : " + e.getMessage());
        }
    }
    // 9 supprimer un sige
    public void supprimerSiege(int idSiege) {
        try {
            siegeDAO.supprimerSiege(idSiege);
            System.out.println("Siège supprimé avec succès !");
        } catch (Exception e) {
            System.err.println(" Erreur lors de la suppression du siège : " + e.getMessage());
        }
    }

}