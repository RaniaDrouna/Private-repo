package Model;

import java.sql.Date;

public class Film {

	    private int idFilm;
	    private String titre;
	    private int duree;
	    private String image;
	    private Date dateSortie;
	    private int limiteAge;
	    private String nomRealisateur;
	    private float note;
	    private String description;
	    private String genre;
	    private String NIN;

	    public Film(int idFilm, String titre, int duree, String image, Date dateSortie, int limiteAge,
	                String nomRealisateur, float note, String description, String genre, String NIN) {
	        this.idFilm = idFilm;
	        this.titre = titre;
	        this.duree = duree;
	        this.image = image;
	        this.dateSortie = dateSortie;
	        this.limiteAge = limiteAge;
	        this.nomRealisateur = nomRealisateur;
	        this.note = note;
	        this.description = description;
	        this.genre = genre;
	        this.NIN = NIN;
	    }

	    // Getters, Setters & toString()
 
	    public int getIdFilm() { return idFilm; }
	    public void setIdFilm(int idFilm) { this.idFilm = idFilm; }
	    
	    public String getTitre() { return titre; }
	    public void setTitre(String titre) { this.titre = titre; }
	    
	    public int getDuree() {  return duree; }
        public void setDuree(int duree) { this.duree = duree; }
        
        public String getImage() { return image; }
	    public void setImage(String image) { this.image = image; }

		public Date getDateSortie() { return dateSortie; }
        public void setDateSortie(Date dateSortie) { this.dateSortie = dateSortie; }

		public int getLimiteAge() { return limiteAge; }
        public void setLimiteAge(int limiteAge) { this.limiteAge = limiteAge; }

		public String getNomRealisateur() { return nomRealisateur; }
        public void setNomRealisateur(String nomRealisateur) {this.nomRealisateur = nomRealisateur; }

		public float getNote() { return note; }
        public void setNote(float note) { this.note = note; }

		public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
	    
	    public String getGenre() { return genre; }
	    public void setGenre(String genre) { this.genre = genre; }
	    
	    public String getNIN() { return NIN; }
	    public void setNIN(String NIN) { this.NIN = NIN; }
 

	    @Override
	    public String toString() {
	        return "Film{" +
	                "idFilm=" + idFilm +
	                ", titre='" + titre + '\'' +
	                ", anneeSortie=" + dateSortie +
	                ", note=" + note +
	                '}';
	    }
}
