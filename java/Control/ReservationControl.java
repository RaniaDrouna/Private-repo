package Control;

import DAO.ReservationDAO;
import Model.Reservation;
import java.sql.Connection;
import java.util.List;
public class ReservationControl {

    private ReservationDAO reservationDAO;

    public ReservationControl(Connection connection) {
        this.reservationDAO = new ReservationDAO(connection);
    }

    // Ajouter une nouvelle réservation
    public boolean ajouterReservation(Reservation reservation) {
        if (reservation == null || reservation.getIdUtilisateur() <= 0) {
            System.out.println("Erreur : données invalides pour la réservation.");
            return false;
        }
        return reservationDAO.ajouterReservation(reservation);
    }

    // Récupérer une réservation par son ID
    public Reservation getReservationParId(int idReservation) {
        if (idReservation <= 0) {
            System.out.println("Erreur : ID de réservation invalide.");
            return null;
        }
        return reservationDAO.getReservationParId(idReservation);
    }

    // Récupérer toutes les réservations d'un utilisateur
    public List<Reservation> getReservationsParUtilisateur(int idUtilisateur) {
        if (idUtilisateur <= 0) {
            System.out.println("Erreur : ID utilisateur invalide.");
            return null;
        }
        return reservationDAO.getReservationParIdUtilisateur(idUtilisateur);
    }

    // Mettre à jour une réservation
    public boolean mettreAJourReservation(Reservation reservation) {
        if (reservation == null || reservation.getIdReservation() <= 0) {
            System.out.println("Erreur : réservation invalide.");
            return false;
        }
        return reservationDAO.mettreAJourReservation(reservation);
    }

    // Lister toutes les réservations
    public List<Reservation> listerToutesLesReservations() {
        return reservationDAO.listerToutesLesReservation();
    }

    // Lister les réservations d’un utilisateur
    public List<Reservation> listerReservationsUtilisateur(int idUtilisateur) {
        if (idUtilisateur <= 0) {
            System.out.println("Erreur : ID utilisateur invalide.");
            return null;
        }
        return reservationDAO.listerToutesLesReservationDunUtilisateur(idUtilisateur);
    }

    // Supprimer une réservation
    public boolean supprimerReservation(int idReservation) {
        if (idReservation <= 0) {
            System.out.println("Erreur : ID de réservation invalide.");
            return false;
        }
        return reservationDAO.supprimerReservation(idReservation);
    }
}
