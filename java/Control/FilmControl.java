package Control;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import DAO.FilmDAO;
import Model.Film;
public class FilmControl {
	
	 private FilmDAO filmDAO;

	    public FilmControl(Connection connection) {
	        this.filmDAO = new FilmDAO(connection);
	    }

	 // Ajouter un film
	    public boolean ajouterFilm(String titre, int duree, String image, java.util.Date dateSortie, int limiteAge,
	                               String nomRealisateur, float note, String description, String genre, String NIN) {
	        Date sqlDateSortie = new Date(dateSortie.getTime()); // Conversion java.util.Date -> java.sql.Date
	        Film film = new Film(0, titre, duree, image, sqlDateSortie, limiteAge, nomRealisateur, note, description, genre, NIN );
	        return filmDAO.ajouterFilm(film);
	    }


	    // Supprimer un film par ID
	    public boolean supprimerFilm(int idFilm) {
	        return filmDAO.supprimerFilm(idFilm);
	    }

	    // Modifier un film
	    public boolean modifierFilm(int idFilm, String titre, int duree, String image, java.util.Date dateSortie, 
	                                int limiteAge, String nomRealisateur, float note, String description, String genre) {
	        Date sqlDateSortie = new Date(dateSortie.getTime()); // Conversion java.util.Date -> java.sql.Date
	        Film film = new Film(idFilm, titre, duree, image, sqlDateSortie, limiteAge, nomRealisateur, note, description, genre, "9999999");
	        return filmDAO.mettreAJourFilm(film);
	    }

	    // Obtenir un film par son ID
	    public Film getFilm(int idFilm) {
	        return filmDAO.getFilmById(idFilm);
	    }

	    // Rechercher des films par titre
	    public List<Film> rechercherFilmsParTitre(String titre) {
	        return filmDAO.rechercherFilmsParTitre(titre);
	    }

	    // Rechercher des films par genre
	    public List<Film> rechercherFilmsParGenre(String genre) {
	        return filmDAO.rechercherFilmsParGenre(genre);
	    }

	    // Obtenir tous les films
	    public List<Film> getTousLesFilms() {
	        return filmDAO.getAllFilms();
	    }

	    // Obtenir la note moyenne des commentaires d'un film
	    public double getMoyenneNoteParFilm(int idFilm) {
	        return filmDAO.getMoyenneNoteParFilm(idFilm);
	    }
}