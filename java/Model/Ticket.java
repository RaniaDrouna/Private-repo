package Model;

import java.math.BigDecimal;

public class Ticket {

	    private int idTicket;
	    private BigDecimal prixSiege;
        private Statut statut;
	    private String NIN;
	    private int idSeance;
	    private int idReservation;
	    private int idSiege;
	    
	    public enum Statut {
	        RESERVE, PAYE, ANNULE, LIBRE
	    }


	    public Ticket(int idTicket, BigDecimal prixSiege, Statut statut, String NIN, int idSeance, int idReservation, int idSiege) {
	        this.idTicket = idTicket;
	        this.prixSiege = prixSiege;
	        this.statut = statut;
	        this.NIN = NIN;
	        this.idSeance = idSeance;
	        this.idReservation = idReservation;
	        this.idSiege = idSiege;
	    }
	    
	 //   setter, getter and toString
	    
		public int getIdTicket() { return idTicket; }
		public void setIdTicket(int idTicket) { this.idTicket = idTicket; }

		public BigDecimal getPrixSiege() { return prixSiege; }
        public void setPrixSiege(BigDecimal prixSiege) { this.prixSiege = prixSiege; }

		public Statut getStatut() { return statut; }
		public void setStatut(Statut statut) { this.statut = statut;}

		public String getNIN() { return NIN; }
		public void setNIN(String NIN) { this.NIN = NIN; }

		public int getIdSeance() { return idSeance; }
		public void setIdSeance(int idSeance) {	this.idSeance = idSeance; }

		public int getIdReservation() { return idReservation; }
		public void setIdReservation(int idReservation) { this.idReservation = idReservation; }

		public int getIdSiege() { return idSiege; }
		public void setIdSiege(int idSiege) { this.idSiege = idSiege; }

	    @Override
	    public String toString() {
	        return "Ticket{" +
	                "idTicket=" + idTicket +
	                ", prix=" + prixSiege +
	                ", statut='" + statut + '\'' +
	                '}';
	    }

	
}
