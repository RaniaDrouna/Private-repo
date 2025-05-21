package Model;

public class Salle {

	    private int idSalle;
	    private String nomSalle;
	    private int capacite;
	    private int idCinema;

	    public Salle(int idSalle, String nomSalle, int capacite, int idCinema) {
	        this.idSalle = idSalle;
	        this.nomSalle = nomSalle;
	        this.capacite = capacite;
	        this.idCinema = idCinema;
	    }
	    
	    //getter, setter and toString
	    
		public int getIdSalle() { return idSalle; }
		public void setIdSalle(int idSalle) { this.idSalle = idSalle; }

		public String getNomSalle() { return nomSalle;	}
        public void setNomSalle(String nomSalle) { this.nomSalle = nomSalle; }
        
		public int getCapacite() { return capacite; }
		public void setCapacite(int capacite) {	this.capacite = capacite; }
		
		public int getIdCinema() {	return idCinema;}
		public void setIdCinema(int idCinema) {	this.idCinema = idCinema; }

	    @Override
	    public String toString() {
	        return "Salle{" +
	                "idSalle=" + idSalle +
	                ", nomSalle=" + nomSalle +
	                ", capacite=" + capacite +
	                '}';
	    }

	
}
