package Control;

import DAO.SalleDAO;
import Model.Salle;
import java.sql.Connection;
import java.util.List;

public class SalleControl {

    private SalleDAO salleDAO;

    public SalleControl(Connection connection) {
        this.salleDAO = new SalleDAO(connection);
    }
    
    // Ajoute une nouvelle salle dans la base de données.
    public void ajouterSalle(Salle salle) {
        try {
            salleDAO.ajouterSalle(salle);
            System.out.println("Salle ajoutée avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout de la salle : " + e.getMessage());
        }
    }

     // Recherche une salle dans la base de données en fonction de son ID.
    public Salle rechercherSalleParId(int idSalle) {
        try {
            return salleDAO.rechercherSalleParId(idSalle);
        } catch (Exception e) {
            System.err.println("Erreur lors de la recherche de la salle : " + e.getMessage());
            return null;
        }
    }
 
     //Recherche toutes les salles appartenant à un cinéma spécifique.     
    public List<Salle> rechercherSallesParCinema(int idCinema) {
        try {
            return salleDAO.getSallesByCinema(idCinema);
        } catch (Exception e) {
            System.err.println("Erreur lors de la recherche des salles du cinéma : " + e.getMessage());
            return null;
        }
    }

     // Met à jour les informations d'une salle dans la base de données. 
    public void modifierSalle(Salle salle) {
        try {
            salleDAO.modifierSalle(salle);
            System.out.println("Salle modifiée avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de la modification de la salle : " + e.getMessage());
        }
    }
    
    
    //  Récupère la liste de toutes les salles enregistrées dans la base de données.
    public List<Salle> listerToutesLesSalles() {
        try {
            return salleDAO.listerToutesLesSalles();
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des salles : " + e.getMessage());
            return null;
        }
    }
 
    //Vérifie si une salle existe dans la base de données en fonction de son ID.     
    public boolean verifierExistenceSalle(int idSalle) {
        try {
            return salleDAO.salleExiste(idSalle);
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification de l'existence de la salle : " + e.getMessage());
            return false;
        }
    }


  // Recherche une salle dans la base de données en fonction de son nom.
    public Salle rechercherSalleParNom(String nomSalle) {
        try {
            return salleDAO.rechercherSalleParNom(nomSalle);
        } catch (Exception e) {
            System.err.println("Erreur lors de la recherche de la salle par nom : " + e.getMessage());
            return null;
        }
    }

    //Supprime une salle de la base de données en fonction de son ID
    public void supprimerSalle(int idSalle) {
        try {
            salleDAO.supprimerSalle(idSalle);
            System.out.println("Salle supprimée avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression de la salle : " + e.getMessage());
        }
    }
   
     // Récupère toutes les salles associées à un cinéma spécifique.
    public List<Salle> recupererToutesLesSallesDUnCinema(int idCinema) {
        try {
            return salleDAO.getSallesByCinema(idCinema);
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des salles du cinéma : " + e.getMessage());
            return null;
        }
    }


}
 


 

