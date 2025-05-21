package Control;

import DAO.CommentaireDAO;
import Model.Commentaire;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public class CommentaireControl {

    private final CommentaireDAO commentaireDAO;

    public CommentaireControl(Connection connection) {
        this.commentaireDAO = new CommentaireDAO(connection);
    }

    // Ajouter un commentaire
    public boolean ajouterCommentaire(String contenu, int note, String NIN, int idFilm) throws Exception {
        LocalDateTime dateHeure = LocalDateTime.now();
        Commentaire commentaire = new Commentaire(0, contenu, note, dateHeure, NIN, idFilm);
        commentaireDAO.ajouterCommentaire(commentaire);
        return true;
    }

    // Modifier un commentaire
    public boolean modifierCommentaire(int idCommentaire, String contenu, int note) throws Exception {
        Commentaire commentaireExistant = commentaireDAO.rechercherCommentaireParId(idCommentaire);
        if (commentaireExistant != null) {
            commentaireExistant.setContenu(contenu);
            commentaireExistant.setNote(note);
            commentaireDAO.modifierCommentaire(commentaireExistant);
            return true;
        }
        return false;
    }

    // Supprimer un commentaire
    public boolean supprimerCommentaire(int idCommentaire) throws Exception {
        commentaireDAO.supprimerCommentaire(idCommentaire);
        return true;
    }

    // Récupérer un commentaire par ID
    public Commentaire getCommentaire(int idCommentaire) throws Exception {
        return commentaireDAO.rechercherCommentaireParId(idCommentaire);
    }

    // Récupérer tous les commentaires
    public List<Commentaire> getTousLesCommentaires() throws Exception {
        return commentaireDAO.listerTousLesCommentaires();
    }

    // Récupérer les commentaires d'un film
    public List<Commentaire> getCommentairesParFilm(int idFilm) throws Exception {
        return commentaireDAO.getCommentairesParFilm(idFilm);
    }

    // Récupérer les commentaires d'un utilisateur
    public List<Commentaire> getCommentairesParUtilisateur(String NIN) throws Exception {
        return commentaireDAO.getCommentairesParUtilisateur(NIN);
    }

    // Récupérer les derniers commentaires
    public List<Commentaire> getDerniersCommentaires(int limite) throws Exception {
        return commentaireDAO.getDerniersCommentaires(limite);
    }

    // Récupérer le nombre total de commentaires pour un film
    public int getNombreCommentairesParFilm(int idFilm) throws Exception {
        return commentaireDAO.getNombreCommentairesParFilm(idFilm);
    }
}
