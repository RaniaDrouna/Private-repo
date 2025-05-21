
package Control;
import DAO.SeanceDAO;
import Model.Seance;
import java.sql.Connection;
import java.util.List;
public class SeanceControl {

	private SeanceDAO seanceDAO;

	public SeanceControl(Connection connection) {
		this.seanceDAO = new SeanceDAO(connection);
	}

	// Ajouter une séance
	public void ajouterSeance(Seance seance) {
		if (seanceDAO.ajouterSeance(seance)) {
			System.out.println("Séance ajoutée avec succès.");
		} else {
			System.err.println("Erreur lors de l'ajout de la séance.");
		}
	}

	// Supprimer une séance
	public void supprimerSeance(int idSeance) {
		if (seanceDAO.supprimerSeance(idSeance)) {
			System.out.println("Séance supprimée avec succès.");
		} else {
			System.err.println("Erreur lors de la suppression de la séance.");
		}
	}

	// Modifier une séance
	public void modifierSeance(Seance seance) {
		if (seanceDAO.updateSeance(seance)) {
			System.out.println("Séance modifiée avec succès.");
		} else {
			System.err.println("Erreur lors de la modification de la séance.");
		}
	}

	// Rechercher une séance par ID
	public Seance rechercherSeanceParId(int idSeance) {
		return seanceDAO.getSeanceById(idSeance);
	}

	// Lister toutes les séances
	public List<Seance> listerToutesLesSeances() {
		return seanceDAO.getAllSeances();
	}

	// Lister les séances par film
	public List<Seance> getSeancesParFilm(int idFilm) {
		return seanceDAO.getSeancesParFilm(idFilm);
	}

	// Lister les séances par salle
	public List<Seance> getSeancesParSalle(int idSalle) {
		return seanceDAO.getSeancesParSalle(idSalle);
	}

	// Vérifier si une séance a encore des places disponibles
	public boolean estSeanceDisponible(int idSeance) {
		return seanceDAO.estSeanceDisponible(idSeance);
	}

	// Réserver une place pour une séance
	public boolean reserverPlace(int idSeance) {
		return seanceDAO.reserverPlace(idSeance);
	}
}