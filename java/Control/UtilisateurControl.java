package Control;
import java.sql.Connection;
import java.util.List;

import DAO.UtilisateurDAO;
import Model.Utilisateur;
import Model.Utilisateur.Role;

public class UtilisateurControl {
	private UtilisateurDAO utilisateurDAO;

    public UtilisateurControl(Connection connection) {
        this.utilisateurDAO = new UtilisateurDAO(connection);
    }

    public boolean ajouterUtilisateur(Utilisateur utilisateur) {
        return utilisateurDAO.ajouterUtilisateur(utilisateur);
    }

    public boolean mettreAJourUtilisateur(Utilisateur utilisateur) {
        return utilisateurDAO.mettreAJourUtilisateur(utilisateur);
    }

    public boolean changerMotDePasse(String NIN, String nouveauMotDePasse) {
        return utilisateurDAO.changerMotDePasse(NIN, nouveauMotDePasse);
    }

    public Utilisateur getUtilisateurParId(String NIN) {
        return utilisateurDAO.getUtilisateurParId( NIN);
    }

    public List<Utilisateur> getTousLesUtilisateurs() {
        return utilisateurDAO.getTousLesUtilisateurs();
    }

    public boolean utilisateurExiste(String nomUtilisateur) {
        return utilisateurDAO.utilisateurExiste(nomUtilisateur);
    }

    public Utilisateur authentifierUtilisateur(String nomUtilisateur, String motPasse) {
        return utilisateurDAO.authentifierUtilisateur(nomUtilisateur, motPasse);
    }

    public List<Utilisateur> getUtilisateursParRole(Role role) {
        return utilisateurDAO.getUtilisateursParRole(role);
    }

    public int compterUtilisateurs() {
        return utilisateurDAO.compterUtilisateurs();
    }

    public boolean supprimerUtilisateur(String NIN) {
        return utilisateurDAO.supprimerUtilisateur(NIN);
    }

}
