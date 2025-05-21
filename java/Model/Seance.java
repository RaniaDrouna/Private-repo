package Model;

import java.time.LocalDateTime;

public class Seance {

	private int idSeance;
    private LocalDateTime dateHeure;
    private String langue;
    private int placesDisponibles;
    private String NIN;
    private int idFilm;
    private int idSalle;

    public Seance(int idSeance, LocalDateTime dateHeure, String langue, int placesDisponibles,
                  String NIN, int idFilm, int idSalle) {
        this.idSeance = idSeance;
        this.dateHeure = dateHeure;
        this.langue = langue;
        this.placesDisponibles = placesDisponibles;
        this.NIN = NIN;
        this.idFilm = idFilm;
        this.idSalle = idSalle;
    }
   //getter and setter and toString

	public int getIdSeance() { return idSeance; }
    public void setIdSeance(int idSeance) { this.idSeance = idSeance; }

	public LocalDateTime getDateHeure() { return dateHeure; }
    public void setDateHeure(LocalDateTime dateHeure) {	this.dateHeure = dateHeure; }

    public String getLangue() { return langue; }
	public void setLangue(String langue) { this.langue = langue; }

	public int getPlacesDisponibles() { return placesDisponibles; }
	public void setPlacesDisponibles(int placesDisponibles) { this.placesDisponibles = placesDisponibles; }

	public String getNIN() { return NIN; }
	public void setNIN(String NIN) { this.NIN = NIN; }

	public int getIdFilm() { return idFilm; }
	public void setIdFilm(int idFilm) { this.idFilm = idFilm; }

	public int getIdSalle() {return idSalle; }
    public void setIdSalle(int idSalle) { this.idSalle = idSalle; }
    
    
    @Override
    public String toString() {
        return "Seance{" +
                "idSeance=" + idSeance +
                ", dateHeure=" + dateHeure +
                ", langue='" + langue + '\'' +
                '}';
    }


}
