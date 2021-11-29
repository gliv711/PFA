package PFA.GestionFinances.Module;

import java.time.LocalDate;

public class Operation {
    private int id;
    private String nom;
    private String type;
    private float valeur;
    private LocalDate date;
    
    public Operation(String nom, String type, float valeur, LocalDate date) {
        this.nom = nom;
        this.type = type;
        this.valeur = valeur;
        this.date = date;
    }
    
    public Operation(int id, String nom, String type, float valeur, LocalDate date) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.valeur = valeur;
        this.date = date;
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
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public float getValeur() {
        return valeur;
    }
    
    public void setValeur(float valeur) {
        this.valeur = valeur;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
