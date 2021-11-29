package PFA.GestionIntervention.Modules;

import PFA.MaterielFiras.ModuleMateriel.Vehicule;

import java.time.LocalDate;
import java.util.ArrayList;

public class Intervention {
    private int id;
    private String nom;
    private LocalDate dateBedut;
    private LocalDate dateFin;
    private float budget;
    private String adresse;
    private ArrayList<PersonnelMin> equipe;
    private ArrayList<Vehicule> vehicules;
    private ArrayList<OutilsUtilise> outilsUtilises;
    
    public Intervention(String nom, LocalDate dateBedut, LocalDate dateFin, float budget, String adresse, ArrayList<PersonnelMin> equipe, ArrayList<Vehicule> vehicules, ArrayList<OutilsUtilise> outilsUtilises) {
        this.nom = nom;
        this.dateBedut = dateBedut;
        this.dateFin = dateFin;
        this.budget = budget;
        this.adresse = adresse;
        this.equipe = equipe;
        this.vehicules = vehicules;
        this.outilsUtilises = outilsUtilises;
    }
    
    public Intervention(int id, String nom, LocalDate dateBedut, LocalDate dateFin, float budget, String adresse, ArrayList<PersonnelMin> equipe, ArrayList<Vehicule> vehicules, ArrayList<OutilsUtilise> outilsUtilises) {
        this.id = id;
        this.nom = nom;
        this.dateBedut = dateBedut;
        this.dateFin = dateFin;
        this.budget = budget;
        this.adresse = adresse;
        this.equipe = equipe;
        this.vehicules = vehicules;
        this.outilsUtilises = outilsUtilises;
    }
    
    public Intervention(int id, String nom, LocalDate dateBedut, LocalDate dateFin, float budget, String adresse) {
        this.id = id;
        this.nom = nom;
        this.dateBedut = dateBedut;
        this.dateFin = dateFin;
        this.budget = budget;
        this.adresse = adresse;
    }
    
    public Intervention(String nom, LocalDate dateBedut, LocalDate dateFin, float budget, String adresse) {
        this.nom = nom;
        this.dateBedut = dateBedut;
        this.dateFin = dateFin;
        this.budget = budget;
        this.adresse = adresse;
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
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
    
    public float getBudget() {
        return budget;
    }
    
    public void setBudget(float budget) {
        this.budget = budget;
    }
    
    public ArrayList<PersonnelMin> getEquipe() {
        return equipe;
    }
    
    public void setEquipe(ArrayList<PersonnelMin> equipe) {
        this.equipe = equipe;
    }
    
    public ArrayList<Vehicule> getVehicules() {
        return vehicules;
    }
    
    public void setVehicules(ArrayList<Vehicule> vehicules) {
        this.vehicules = vehicules;
    }
    
    public ArrayList<OutilsUtilise> getOutilsUtilises() {
        return outilsUtilises;
    }
    
    public void setOutilsUtilises(ArrayList<OutilsUtilise> outilsUtilises) {
        this.outilsUtilises = outilsUtilises;
    }
    
    @Override
    public String toString() {
        return "Intervention{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateBedut=" + dateBedut +
                ", dateFin=" + dateFin +
                ", budget=" + budget +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}


