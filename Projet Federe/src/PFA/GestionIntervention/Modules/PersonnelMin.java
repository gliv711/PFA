package PFA.GestionIntervention.Modules;

import javafx.scene.control.CheckBox;

public class PersonnelMin {
    private int id;
    private String nom,prenom,poste;
    private CheckBox check;
    
    public CheckBox getCheck() {
        return check;
    }
    
    public void setCheck(CheckBox check) {
        this.check = check;
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
    
    public String getPoste() {
        return poste;
    }
    
    public void setPoste(String poste) {
        this.poste = poste;
    }
    
    public PersonnelMin(String nom, String prenom, String poste) {
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.check = new CheckBox();
    }
    
    public PersonnelMin(int id, String nom, String prenom, String poste) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.check = new CheckBox();
    }
}
