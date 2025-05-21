package Model;

public class Cinema {

	    private int idCinema;
	    private String nomCinema;
	    private String adresse;
	    private String numeroTelephone;
	    private int nombreSalle;

	    public Cinema(int idCinema, String nomCinema, String adresse, String numeroTelephone, int nombreSalle) {
	        this.idCinema = idCinema;
	        this.nomCinema = nomCinema;
	        this.adresse = adresse;
	        this.numeroTelephone = numeroTelephone;
	        this.nombreSalle = nombreSalle;
	    }
	    
	    //getter, setter et to string
	    
		public int getIdCinema() { return idCinema; }
		public void setIdCinema(int idCinema) {	this.idCinema = idCinema; }

		public String getNomCinema() { return nomCinema; }
		public void setNomCinema(String nomCinema) { this.nomCinema = nomCinema; }

		public String getAdresse() { return adresse;	}
		public void setAdresse(String adresse) { this.adresse = adresse;	}

		public String getNumeroTelephone() {return numeroTelephone;	}
        public void setNumeroTelephone(String numeroTelephone) { this.numeroTelephone = numeroTelephone; }
        

		public int getNombreSalle() { return nombreSalle; }
		public void setNombreSalle(int nombreSalle) {	this.nombreSalle = nombreSalle; }

	    @Override
	    public String toString() {
	        return "Cinema{" +
	                "idCinema=" + idCinema +
	                ", nomCinema='" + nomCinema + 
	                ", nombreSalle='" + nombreSalle + '\'' +
	                '}';
	    }

	
}
