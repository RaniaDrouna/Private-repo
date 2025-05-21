package Control;

import DAO.CinemaDAO;
import Model.Cinema;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CinemaControl {

    private CinemaDAO cinemaDAO;

    public CinemaControl(Connection connection) {
        this.cinemaDAO = new CinemaDAO(connection);
    }

    // 1 Ajouter un cinema a la BD
    public boolean ajouterCinema(String nom, String adresse, String numeroTelephone, int nombreSalle) {
        if (nom == null || nom.trim().isEmpty()) {
            System.out.println(" Erreur : Le nom du cinéma ne peut pas être vide.");
            return false;
        }

        try {
            List<Cinema> cinemasExistants = cinemaDAO.rechercherCinemasParNom(nom);
            if (!cinemasExistants.isEmpty()) {
                System.out.println(" Erreur : Un cinéma avec ce nom existe déjà.");
                return false;
            }

            Cinema cinema = new Cinema(0, nom, adresse, numeroTelephone, nombreSalle);
            boolean success = cinemaDAO.ajouterCinema(cinema);

            if (success) {
                System.out.println(" Cinéma ajouté avec succès.");
            } else {
                System.out.println(" Échec de l'ajout du cinéma.");
            }
            return success;

        } catch (SQLException e) {
            System.out.println(" Erreur SQL lors de l'ajout du cinéma : " + e.getMessage());
            return false;
        }
    }

    // 2 Vérifier si un cinéma existe en base de données à partir de son nom
    public boolean verifierExistenceCinema(String nom) throws SQLException {
        if (nom == null || nom.trim().isEmpty()) {
            System.out.println(" Erreur : Le nom du cinéma ne peut pas être vide.");
            return false;
        }

        List<Cinema> cinemas = cinemaDAO.rechercherCinemasParNom(nom);
        boolean existe = cinemas != null && !cinemas.isEmpty();

        if (existe) {
            System.out.println(" Le cinéma \"" + nom + "\" existe déjà.");
        } else {
            System.out.println(" Aucun cinéma trouvé avec ce nom.");
        }

        return existe;
    }

    // 3 changer le nom du cinema existant
    public boolean renommerCinema(int idCinema, String nouveauNom) throws SQLException {
        if (idCinema <= 0) {
            System.out.println(" Erreur : L'ID du cinéma est invalide.");
            return false;
        }

        if (nouveauNom == null || nouveauNom.trim().isEmpty()) {
            System.out.println(" Erreur : Le nouveau nom du cinéma ne peut pas être vide.");
            return false;
        }

        // Vérifier si un autre cinéma porte déjà ce nom
        if (verifierExistenceCinema(nouveauNom)) {
            System.out.println(" Erreur : Un cinéma avec ce nom existe déjà.");
            return false;
        }

        // Récupérer l'ancien cinéma pour conserver ses autres informations
        Cinema ancienCinema = cinemaDAO.getCinemaById(idCinema);
        if (ancienCinema == null) {
            System.out.println(" Erreur : Aucun cinéma trouvé avec cet ID.");
            return false;
        }

        // Créer un nouvel objet Cinema avec le nouveau nom et les anciennes valeurs
        Cinema cinemaModifie = new Cinema(
            idCinema,
            nouveauNom, 
            ancienCinema.getAdresse(),
            ancienCinema.getNumeroTelephone(),
            ancienCinema.getNombreSalle()
        );

        // Mettre à jour le cinéma
        boolean success = cinemaDAO.mettreAJourCinema(cinemaModifie);

        if (success) {
            System.out.println(" Cinéma renommé avec succès en \"" + nouveauNom + "\".");
        } else {
            System.out.println(" Échec du renommage du cinéma.");
        }

        return success;
    }

    // 4 Supprimer un cinéma et ses salles associées
    public boolean supprimerCinemaEtSalles(int idCinema) throws SQLException {
        if (idCinema <= 0) {
            System.out.println(" Erreur : ID du cinéma invalide.");
            return false;
        }

        // Vérifier si le cinéma existe avant de le supprimer
        Cinema cinema = cinemaDAO.getCinemaById(idCinema);
        if (cinema == null) {
            System.out.println(" Erreur : Aucun cinéma trouvé avec cet ID.");
            return false;
        }

        // Supprimer le cinéma et ses salles
        boolean success = cinemaDAO.supprimerCinemaEtSalles(idCinema);

        if (success) {
            System.out.println(" Cinéma supprimé avec succès, ainsi que toutes ses salles associées.");
        } else {
            System.out.println(" Échec de la suppression du cinéma.");
        }

        return success;
    }

    // 5 Afficher tt les information d'un cinema
    public void afficherDetailsCinema(int idCinema) throws SQLException {
        if (idCinema <= 0) {
            System.out.println("❌ Erreur : ID du cinéma invalide.");
            return;
        }

        Cinema cinema = cinemaDAO.getCinemaById(idCinema);
        
        if (cinema == null) {
            System.out.println("❌ Aucun cinéma trouvé avec cet ID.");
            return;
        }

        // Affichage des détails du cinéma
        System.out.println("🎬 Détails du Cinéma :");
        System.out.println("ID : " + cinema.getIdCinema());
        System.out.println("Nom : " + cinema.getNomCinema());
        System.out.println("Adresse : " + cinema.getAdresse());
        System.out.println("Numéro de téléphone : " + cinema.getNumeroTelephone());
        System.out.println("Nombre de salles : " + cinema.getNombreSalle());
    }

 // 6 Récupérer et afficher la liste de tous les cinémas
    public List<Cinema> listerTousCinemas() throws SQLException {
        List<Cinema> cinemas = cinemaDAO.getTousLesCinemas();

        if (cinemas.isEmpty()) {
            System.out.println("Aucun cinéma trouvé.");
            return cinemas;
        }

        System.out.println("🎬 Liste des cinémas :");
        for (Cinema cinema : cinemas) {
            System.out.println("- ID : " + cinema.getIdCinema() + 
                               ", Nom : " + cinema.getNomCinema() + 
                               ", Adresse : " + cinema.getAdresse() + 
                               ", Téléphone : " + cinema.getNumeroTelephone() + 
                               ", Salles : " + cinema.getNombreSalle());
        }
		return cinemas;
    }

 // 7  Mettre à jour un cinéma
    public boolean mettreAJourCinema(Cinema cinema) {
        return cinemaDAO.mettreAJourCinema(cinema);
    }

    //  8 Supprimer un cinéma sans supprimer ses salles associées
    public boolean supprimerCinema(int idCinema) {
        return cinemaDAO.supprimerCinema(idCinema);
    }
    //Rechercher un cinema par Id
    public Cinema getCinemaById(int idCinema) {
        return cinemaDAO.getCinemaById(idCinema); // Appel à la DAO
    }

}



	


