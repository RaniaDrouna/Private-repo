package Model;

import java.time.LocalDateTime;

public class Reservation {

    public enum Statut {
        EN_ATTENTE, CONFIRME, ANNULEE, PAYE;
}

	    private int idReservation;
	    private Statut statut;
	    private LocalDateTime dateHeure;
	    private String NIN;

	    public Reservation(int idReservation, Statut statut, LocalDateTime dateHeure, String NIN) {
	        this.idReservation = idReservation;
	        this.statut = statut;
	        this.dateHeure = dateHeure;
	        this.NIN = NIN;
	    }

	  //  settre, getter and toString
	    
	    public int getIdReservation() {	return idReservation;	}
		public void setIdReservation(int idReservation) { this.idReservation = idReservation;	}

		public Statut getStatut() { return statut; }
		public void setStatut(Statut statut) { this.statut = statut; }

		public LocalDateTime getDateHeure() { return dateHeure; }
		public void setDateHeure(LocalDateTime dateHeure) { this.dateHeure = dateHeure; }
		
		public String getNIN() { return NIN; }
     	public void setNIN(String NIN) { this.NIN = NIN; }
		
	    @Override
	    public String toString() {
	        return "Reservation{" +
	                "idReservation=" + idReservation +
	                ", statut='" + statut +  
	                "NIN =" + NIN + '/'+
	                '}';
	    }
		
}
