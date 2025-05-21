package Model;

import java.time.LocalDateTime;

public class Paiement {
	
 	
    public enum Statut {
  	        EN_ATTENTE, PAYE, ECHOUE;
  	    } 	

    public enum Methode {
    	CARTE_CIB, CARTE_EDAHABIA;
	    } 
	    private int idPaiement;
	    private float montantTotal;
	    private LocalDateTime dateHeure;
		private Statut statut;
	    private Methode methode;
	    private int idReservation;
	    private String NIN;
	     
	     

	    public Paiement(int idPaiement, float montantTotal, LocalDateTime dateHeure,
	    		Statut statut, Methode methode , int idReservation, String NIN) {
	        this.idPaiement = idPaiement;
	        this.montantTotal = montantTotal;
	        this.dateHeure = dateHeure;
	        this.statut = statut;
	        this.methode = methode;
	        this.idReservation = idReservation;
	        this.NIN = NIN;
	        
	    }
  // setter et getter et toString
	    
	    public int getIdPaiement() {	return idPaiement;	}
		public void setIdPaiement(int idPaiement) {	this.idPaiement = idPaiement;	}

		public float getMontantTotal() {	return montantTotal;}
		public void setMontantTotal(float montantTotal) {	this.montantTotal = montantTotal;	}

		public LocalDateTime getDateHeure() {  return dateHeure;}
		public void setDateHeure(LocalDateTime dateHeure) { this.dateHeure = dateHeure; }

		public Statut getStatut() { return statut; }
		public void setStatut(Statut statut) { this.statut = statut; }
		
		public Methode getMethode() {return methode; }
		public void setMethode(Methode methode) {this.methode = methode;	}   

		public int getIdReservation() { return idReservation; }
		public void setIdReservation(int idReservation) { this.idReservation = idReservation; }

		public String getNIN() { return NIN; }
		public void setNIN(String NIN) { this.NIN = NIN; }

		

	    @Override
	    public String toString() {
	        return "Paiement{" +
	                "idPaiement=" + idPaiement +
	                ", montantTotal=" + montantTotal +
	                ", methode='" + methode + '\'' +
	                '}';
	    }
  
}
