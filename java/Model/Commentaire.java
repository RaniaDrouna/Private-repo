 package Model;

import java.time.LocalDateTime;

public class Commentaire {

	    private int idCommentaire;
	    private String contenu;
	    private int note;
	    private LocalDateTime dateHeure;
	    private String NIN;
	    private int idFilm;

	    public Commentaire(int idCommentaire, String contenu, int note, LocalDateTime dateHeure,  String NIN, int idFilm) {
	        this.idCommentaire = idCommentaire;
	        this.contenu = contenu;
	        this.note = note;
	        this.dateHeure = dateHeure;
	        this.NIN = NIN;
	        this.idFilm = idFilm;
	    }
  //getter , setter and toString
	    
		public int getIdCommentaire() { return idCommentaire; }
		public void setIdCommentaire(int idCommentaire) { this.idCommentaire = idCommentaire; }

		public String getContenu() { return contenu;}
		public void setContenu(String contenu) { this.contenu = contenu; }

		public int getNote() { return note; }
        public void setNote(int note) { this.note = note; }
        
		public LocalDateTime getDateHeure() { return dateHeure; }
		public void setDateHeure(LocalDateTime dateHeure) { this.dateHeure = dateHeure; }
		
		public String getNIN() { return NIN; }
		public void setNIN( String NIN) {this.NIN = NIN;}
		
		public int getIdFilm() {return idFilm; }
		public void setIdFilm(int idFilm) { this.idFilm = idFilm; }
		
	    @Override
	    public String toString() {
	        return "Commentaire{" +
	                "idCommentaire=" + idCommentaire +
	                ", contenu='" + contenu + '\'' +
	                ", note=" + note +
	                '}';
	    }

}
