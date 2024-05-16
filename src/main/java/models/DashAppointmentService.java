package models;

import java.util.Date;

public class DashAppointmentService {
	private String nom;
    private String prenom;
    private String groupeSanguin;
    private Date dateRendezVous;
    private String heureRendezVous;
    private String status;

    // Constructor
    public DashAppointmentService(String nom, String prenom, String groupeSanguin, Date dateRendezVous, String heureRendezVous, String status) {
        this.nom = nom;
        this.prenom = prenom;
        this.groupeSanguin = groupeSanguin;
        this.dateRendezVous = dateRendezVous;
        this.heureRendezVous = heureRendezVous;
        this.status = status;
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public Date getDateRendezVous() {
        return dateRendezVous;
    }

    public void setDateRendezVous(Date dateRendezVous) {
        this.dateRendezVous = dateRendezVous;
    }

    public String getHeureRendezVous() {
        return heureRendezVous;
    }

    public void setHeureRendezVous(String heureRendezVous) {
        this.heureRendezVous = heureRendezVous;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

