package PFA.GestionTache.Module;

import PFA.GestionPersonnel.Modules.Personnel;
import javafx.scene.control.CheckBox;

import java.time.LocalDate;

public class Tache {
    private int id;
    private String nom;
    private String description;
    private Personnel personnel;
    private String nomPersonnel = "Non Disponible";
    private LocalDate date;
    private CheckBox check;
    
    public Tache(int id, String nom, String description, Personnel personnel, LocalDate date) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.personnel = personnel;
        if(!(personnel == null)) nomPersonnel = personnel.getNom();
        this.date = date;
    }
    
    public Tache(String nom, String description, Personnel personnel, LocalDate date) {
        this.nom = nom;
        this.description = description;
        this.personnel = personnel;
        if(!(personnel == null)) nomPersonnel = personnel.getNom();
        this.date = date;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public CheckBox getCheck() {
        return check;
    }
    
    public void setCheck(CheckBox check) {
        this.check = check;
    }
    
    public String getNomPersonnel() {
        return nomPersonnel;
    }
    
    public void setNomPersonnel(String nomPersonnel) {
        this.nomPersonnel = nomPersonnel;
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Personnel getPersonnel() {
        return personnel;
    }
    
    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }
    
    
}
