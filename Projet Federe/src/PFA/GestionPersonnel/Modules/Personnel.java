package PFA.GestionPersonnel.Modules;

import javafx.scene.control.CheckBox;

import java.time.LocalDate;

public class Personnel {
    private int id;
    private String nom;
    private String prenom;
    private int CIN;
    private float salaire;
    private String poste;
    private LocalDate dateNaissance;
    private CheckBox check;
    
    public Personnel(String nom, String prenom, int CIN, float salaire, String poste, LocalDate dateNaissaince) {
        this.nom = nom;
        this.prenom = prenom;
        this.CIN = CIN;
        this.salaire = salaire;
        this.poste = poste;
        this.dateNaissance = dateNaissaince;
        this.check = new CheckBox();
    }
    
    public Personnel(int id, String nom, String prenom, int CIN, float salaire, String poste, LocalDate dateNaissaince) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.CIN = CIN;
        this.salaire = salaire;
        this.poste = poste;
        this.dateNaissance = dateNaissaince;
        this.check = new CheckBox();
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
    
    public int getCIN() {
        return CIN;
    }
    
    public void setCIN(int CIN) {
        this.CIN = CIN;
    }
    
    public float getSalaire() {
        return salaire;
    }
    
    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }
    
    public String getPoste() {
        return poste;
    }
    
    public void setPoste(String poste) {
        this.poste = poste;
    }
    
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }
    
    public void setDateNaissance(LocalDate dateNaissaince) {
        this.dateNaissance = dateNaissaince;
    }
    
    @Override
    public String toString() {
        return "Personnel{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", CIN=" + CIN +
                ", salaire=" + salaire +
                ", poste='" + poste + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                '}';
    }
    
    public Personnel(int id, String nom, String prenom, String poste) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
    }
    
    public CheckBox getCheck() {
        return check;
    }
    
    public void setCheck(CheckBox check) {
        this.check = check;
    }
}



