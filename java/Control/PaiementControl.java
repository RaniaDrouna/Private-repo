package Control;

import java.sql.Connection;
import java.util.List;
import java.sql.SQLException;

import DAO.PaiementDAO;
import Database.DatabaseConnexion;
import Model.Paiement;
import Model.Paiement.Statut;

public class PaiementControl {

	private PaiementDAO paiementDAO;

	// Constructor with connection parameter
	public PaiementControl(Connection connection) {
		this.paiementDAO = new PaiementDAO(connection);
	}

	// Default constructor that gets connection from DatabaseConnexion
	public PaiementControl() {
		Connection connection = DatabaseConnexion.getConnection();
		this.paiementDAO = new PaiementDAO(connection);
	}

	// Constructor that accepts a PaiementDAO directly
	public PaiementControl(PaiementDAO paiementDAO) {
		this.paiementDAO = paiementDAO;
	}

	// Ajouter un paiement
	public boolean ajouterPaiement(Paiement paiement) {
		return paiementDAO.ajouterPaiement(paiement);
	}

	// Obtenir un paiement par son ID
	public Paiement getPaiementParId(int idPaiement) throws SQLException {
		return paiementDAO.getPaiementParId(idPaiement);
	}

	// Mettre à jour un paiement
	public void mettreAJourPaiement(Paiement paiement) throws SQLException {
		paiementDAO.mettreAJourPaiement(paiement);
	}

	// Supprimer un paiement
	public boolean supprimerPaiement(int idPaiement) {
		return paiementDAO.supprimerPaiement(idPaiement);
	}

	// Obtenir tous les paiements
	public List<Paiement> getTousLesPaiements() {
		return paiementDAO.getTousLesPaiements();
	}

	// Obtenir tous les paiements d'un utilisateur
	public List<Paiement> getPaiementsParUtilisateur(int idUtilisateur) {
		return paiementDAO.getPaiementsParUtilisateur(idUtilisateur);
	}

	// Vérifier si un paiement a été effectué
	public boolean estPaiementEffectue(int idPaiement) {
		return paiementDAO.estPaiementEffectue(idPaiement);
	}

	// Récupérer les paiements par statut
	public List<Paiement> getPaiementsParStatut(String statut) {
		return paiementDAO.getPaiementsParStatut(statut);
	}

	// Récupérer les paiements en attente
	public List<Paiement> getPaiementsEnAttente() {
		return paiementDAO.getPaiementsParStatut(Statut.EN_ATTENTE.name());
	}

	// Valider un paiement (changer le statut à PAYÉ)
	public boolean validerPaiement(int idPaiement) {
		try {
			Paiement paiement = paiementDAO.getPaiementParId(idPaiement);
			if (paiement != null) {
				paiement.setStatut(Statut.PAYE);
				paiementDAO.mettreAJourPaiement(paiement);
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.err.println("Erreur lors de la validation du paiement: " + e.getMessage());
			return false;
		}
	}
}