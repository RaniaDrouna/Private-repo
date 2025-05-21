package Model;

import java.time.LocalDate;

public class Utilisateur {
	
	public enum Role {
	    Admin, Client;
	}

	
	    private String NIN; //numero d'identite
	    private String nomUtilisateur;
	    private String motPasse;
	    private LocalDate dateDeNaissance;
	    private Role role;

	    public Utilisateur(String NIN, String nomUtilisateur, String motPasse, LocalDate dateDeNaissance, Role role) {
	        this.NIN = NIN ;
	        this.nomUtilisateur = nomUtilisateur;
	        this.motPasse = motPasse;
	        this.dateDeNaissance = dateDeNaissance;
	        this.role = role;
	    }

	    // Getters et Setters
	    
	    public String getNIN() { return NIN; }
	    public void setNIN(String NIN) { this.NIN = NIN; }

	    public String getNomUtilisateur() { return nomUtilisateur; }
	    public void setNomUtilisateur(String nomUtilisateur) { this.nomUtilisateur = nomUtilisateur; }

	    public String getMotPasse() { return motPasse; }
	    public void setMotPasse(String motPasse) { this.motPasse = motPasse; }
	    
	    public LocalDate getDateDeNaissance() { return dateDeNaissance;}
	    public void setDateDeNaissance(LocalDate dateDeNaissance) { this.dateDeNaissance = dateDeNaissance; }
	    
	    public Role getRole() { return role; }
	    public void setRole(Role role) { this.role = role; }

	    @Override
	    public String toString() {
	        return "Utilisateur{" +
	                "Numero d'identite=" + NIN +
	                "Date de naissance="+ dateDeNaissance +
	                ", nomUtilisateur='" + nomUtilisateur + '\'' +
	                ", role='" + role + '\'' +
	                '}';
	    }
}
