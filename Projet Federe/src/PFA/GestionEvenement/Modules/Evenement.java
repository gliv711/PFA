package PFA.GestionEvenement.Modules;

import PFA.GestionPersonnel.Modules.Personnel;
import PFA.MaterielFiras.ModuleMateriel.Vehicule;
import java.time.LocalDate;
import java.util.ArrayList;

public class Evenement {
    private int idEve;
    private String nomEve;
    private LocalDate dateBedutEve;
    private LocalDate dateFinEve;
    private String adresseEve;
    private Float BudgetEve;
    private ArrayList<Personnel> equipeEve;
    private ArrayList<Vehicule> vehiculesEve;
    private ArrayList<OutillMater> outilsUtilisEve;

    public Evenement(int idEve, String nomEve,
                     LocalDate dateBedutEve, LocalDate dateFinEve, Float BudgetEve,
                     String adresseEve, ArrayList<Personnel> equipeEve,
                     ArrayList<Vehicule> vehiculesEve, ArrayList<OutillMater> outilsUtilisEve) {
        this.idEve = idEve;
        this.nomEve = nomEve;
        this.dateBedutEve = dateBedutEve;
        this.dateFinEve = dateFinEve;
        this.adresseEve = adresseEve;
        this.equipeEve = equipeEve;
        this.vehiculesEve = vehiculesEve;
        this.outilsUtilisEve = outilsUtilisEve;
        this.BudgetEve = BudgetEve;
    }

    public Evenement(String nomEve,
                     LocalDate dateBedutEve,
                     LocalDate dateFinEve,Float BudgetEve,
                     String adresseEve, ArrayList<Personnel> equipeEve,
                     ArrayList<Vehicule> vehiculesEve, ArrayList<OutillMater> outilsUtilisEve) {
        this.nomEve = nomEve;
        this.dateBedutEve = dateBedutEve;
        this.dateFinEve = dateFinEve;
        this.adresseEve = adresseEve;
        this.equipeEve = equipeEve;
        this.vehiculesEve = vehiculesEve;
        this.outilsUtilisEve = outilsUtilisEve;
        this.BudgetEve=BudgetEve;
    }

    public Evenement(int idEve,String nomEve,
                     LocalDate dateBedutEve,
                     LocalDate dateFinEve,Float BudgetEve,
                     String adresseEve){
        this.idEve = idEve;
        this.nomEve = nomEve;
        this.dateBedutEve = dateBedutEve;
        this.dateFinEve = dateFinEve;
        this.adresseEve = adresseEve;
        this.BudgetEve=BudgetEve;
    }
    public Evenement(String nomEve,
                     LocalDate dateBedutEve,
                     LocalDate dateFinEve,Float BudgetEve,
                     String adresseEve){
        this.nomEve = nomEve;
        this.dateBedutEve = dateBedutEve;
        this.dateFinEve = dateFinEve;
        this.adresseEve = adresseEve;
        this.BudgetEve=BudgetEve;
    }


    public int getIdEve() {
        return idEve;
    }

    public void setIdEve(int idEve) {
        this.idEve = idEve;
    }

    public String getNomEve() {
        return nomEve;
    }

    public void setNomEve(String nomEve) {
        this.nomEve = nomEve;
    }

    public LocalDate getDateBedutEve() {
        return dateBedutEve;
    }

    public void setDateBedutEve(LocalDate dateBedutEve) {
        this.dateBedutEve = dateBedutEve;
    }

    public LocalDate getDateFinEve() {
        return dateFinEve;
    }

    public void setDateFinEve(LocalDate dateFinEve) {
        this.dateFinEve = dateFinEve;
    }

    public String getAdresseEve() {
        return adresseEve;
    }

    public void setAdresseEve(String adresseEve) {
        this.adresseEve = adresseEve;
    }

    public ArrayList<Personnel> getEquipeEve() {
        return equipeEve;
    }

    public void setEquipeEve(ArrayList<Personnel> equipeEve) {
        this.equipeEve = equipeEve;
    }

    public ArrayList<Vehicule> getVehiculesEve() {
        return vehiculesEve;
    }

    public void setVehiculesEve(ArrayList<Vehicule> vehiculesEve) {
        this.vehiculesEve = vehiculesEve;
    }

    public ArrayList<OutillMater> getOutilsUtilisEve() {
        return outilsUtilisEve;
    }

    public void setOutilsUtilisEve(ArrayList<OutillMater> outilsUtilisEve) {
        this.outilsUtilisEve = outilsUtilisEve;
    }

    public void setBudgetEve (Float BudgetEve) {this.BudgetEve = BudgetEve ; }

    public  Float getBudgetEve () { return BudgetEve ;}
    @Override
    public String toString() {
        return "Evenement{" +
                "idEve=" + idEve +
                ", titreEve='" + nomEve + '\'' +
                ", dateBedutEve=" + dateBedutEve +
                ", dateFinEve=" + dateFinEve +
                ", adresseEve='" + adresseEve + '\'' +
                ", equipeEve=" + equipeEve +
                ", vehiculesEve=" + vehiculesEve +
                ", outilsUtilisEve=" + outilsUtilisEve +
                ", BudgetEve = " + BudgetEve +
                '}';
    }
}
