package models;

import java.sql.Date;
import java.sql.Time;

public class Rendezvous {
    private int id;
    private String nom;
    private String prenom;
    private String groupe_sanguin;
    private Date date_rendezvous;
    private Time heure_rendezvous;

    public Rendezvous(int id, String nom, String prenom, String groupe_sanguin, Date date_rendezvous, Time heure_rendezvous) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.groupe_sanguin = groupe_sanguin;
        this.date_rendezvous = date_rendezvous;
        this.heure_rendezvous = heure_rendezvous;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        return groupe_sanguin;
    }

    public void setGroupeSanguin(String groupe_sanguin) {
        this.groupe_sanguin = groupe_sanguin;
    }

    public Date getDateRendezvous() {
        return date_rendezvous;
    }

    public void setDateRendezvous(Date date_rendezvous) {
        this.date_rendezvous = date_rendezvous;
    }

    public Time getHeureRendezvous() {
        return heure_rendezvous;
    }

    public void setHeureRendezvous(Time heure_rendezvous) {
        this.heure_rendezvous = heure_rendezvous;
    }
}
