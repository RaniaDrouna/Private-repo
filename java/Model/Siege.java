package Model;

public class Siege {

    public enum Statut { DISPONIBLE, RESERVER, SELECTIONNER } // Définition de l'énumération
    
	    private int idSiege;
	    private String nomSiege;
	    private Statut statut;
		private int idSalle;
	    

	    public Siege(int idSiege, String nomSiege,  Statut statut, int idSalle) {
	        this.idSiege = idSiege;
	        this.nomSiege = nomSiege;
	        this.statut = statut;
	        this.idSalle = idSalle;
	    }
	    
	    //setter, gettre and toString
	    
		public int getIdSiege() { return idSiege; }
		public void setIdSiege(int idSiege) { this.idSiege = idSiege;}

		public String getNomSiege() { return nomSiege; }
		public void setNomSiege(String nomSiege) { this.nomSiege = nomSiege; }

	    public Statut getStatut() { return statut; }
		public void setStatut(Statut statut) { this.statut = statut; }
			
		public int getIdSalle() { return idSalle; }
		public void setIdSalle(int idSalle) { this.idSalle = idSalle;	}
		

	    @Override
	    public String toString() {
	        return "Siege{" +
	                "idSiege=" + idSiege +
	                ", numeroSiege=" + nomSiege +
	                ", statut=" + statut +
	                ", idSalle=" + idSalle +
	                '}';
	    }

}
