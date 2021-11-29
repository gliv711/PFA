package PFA.MaterielFiras.ModuleMateriel;

import javafx.scene.control.CheckBox;

import java.time.LocalDate;

public class Vehicule {
    private int id;
    private int matricule;
    private String model;
    private float prix;
    private String nom;
    private LocalDate date;
    private CheckBox check;
    
    public Vehicule(int id, int matricule, String model, String nom) {
        this.id = id;
        this.matricule = matricule;
        this.model = model;
        this.nom = nom;
        this.check = new CheckBox();
    }
    
    public Vehicule(int matricule, String model, String nom) {
        this.matricule = matricule;
        this.model = model;
        this.nom = nom;
        this.check = new CheckBox();
    }
    
    public Vehicule(int matricule, String model, String nom, LocalDate date, float prix) {
        this.matricule = matricule;
        this.model = model;
        this.nom = nom;
        this.check = new CheckBox();
        this.prix = prix;
        this.date = date;
    }
    
    public CheckBox getCheck() {
        return check;
    }
    
    public void setCheck(CheckBox check) {
        this.check = check;
    }
    
    public Vehicule(int id, int matricule, String model, String nom, LocalDate date, float prix) {
        this.matricule = matricule;
        this.model = model;
        this.nom = nom;
        this.check = new CheckBox();
        this.prix = prix;
        this.date = date;
        this.id = id;
    }
    
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getMatricule() {
        return matricule;
    }
    
    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public float getPrix() {
        return prix;
    }
    
    public void setPrix(float prix) {
        this.prix = prix;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public LocalDate getDateDachat() {
        return date;
    }
    
    public void setdatedachat(LocalDate datedachat) {
        this.date = datedachat;
    }
    
    @Override
    public String toString() {
        return "MaterielVehicule{" +
                "id=" + id +
                ", matricule=" + matricule +
                ", model='" + model + '\'' +
                ", prix=" + prix +
                ", nom='" + nom + '\'' +
                ", DateDachat='" + date + '\'' +
                '}';
    }
}
