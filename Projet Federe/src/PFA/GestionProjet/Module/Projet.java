package PFA.GestionProjet.Module;

import PFA.GestionPersonnel.Modules.Personnel;
import PFA.GestionTache.Module.Tache;
import PFA.MaterielFiras.ModuleMateriel.Outil;
import PFA.MaterielFiras.ModuleMateriel.Vehicule;

import java.time.LocalDate;
import java.util.ArrayList;

public class Projet {
    private int id;
    private String nom;
    private String description;
    private ArrayList<Tache> taches;
    private String adresse;
    private float cout;
    private LocalDate dateBedut;
    private LocalDate dateFin;
    private ArrayList<Personnel> equipe;
    private ArrayList<Vehicule> vehicules;
    private ArrayList<Outil> outils;
    
    public Projet(String nom, String description, ArrayList<Tache> taches, String adresse, float cout, LocalDate dateBedut, LocalDate dateFin, ArrayList<Personnel> equipe, ArrayList<Vehicule> vehicules, ArrayList<Outil> outils) {
        this.nom = nom;
        this.description = description;
        this.taches = taches;
        this.adresse = adresse;
        this.cout = cout;
        this.dateBedut = dateBedut;
        this.dateFin = dateFin;
        this.equipe = equipe;
        this.vehicules = vehicules;
        this.outils = outils;
    }
    
    public Projet(int id, String nom, String description, String adresse, float cout, LocalDate dateBedut, LocalDate dateFin) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.cout = cout;
        this.dateBedut = dateBedut;
        this.dateFin = dateFin;
    }
    
    public Projet(int id, String nom, String description, ArrayList<Tache> taches, String adresse, float cout, LocalDate dateBedut, LocalDate dateFin, ArrayList<Personnel> equipe, ArrayList<Vehicule> vehicules, ArrayList<Outil> outils) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.taches = taches;
        this.adresse = adresse;
        this.cout = cout;
        this.dateBedut = dateBedut;
        this.dateFin = dateFin;
        this.equipe = equipe;
        this.vehicules = vehicules;
        this.outils = outils;
    }
    
    public ArrayList<Outil> getOutils() {
        return outils;
    }
    
    public void setOutils(ArrayList<Outil> outils) {
        this.outils = outils;
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
    
    public ArrayList<Tache> getTaches() {
        return taches;
    }
    
    public void setTaches(ArrayList<Tache> taches) {
        this.taches = taches;
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public float getCout() {
        return cout;
    }
    
    public void setCout(float cout) {
        this.cout = cout;
    }
    
    public LocalDate getDateBedut() {
        return dateBedut;
    }
    
    public void setDateBedut(LocalDate dateBedut) {
        this.dateBedut = dateBedut;
    }
    
    public LocalDate getDateFin() {
        return dateFin;
    }
    
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
    
    public ArrayList<Personnel> getEquipe() {
        return equipe;
    }
    
    public void setEquipe(ArrayList<Personnel> equipe) {
        this.equipe = equipe;
    }
    
    public ArrayList<Vehicule> getVehicules() {
        return vehicules;
    }
    
    public void setVehicules(ArrayList<Vehicule> vehicules) {
        this.vehicules = vehicules;
    }
}
