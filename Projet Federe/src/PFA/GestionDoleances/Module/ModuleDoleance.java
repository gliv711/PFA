package PFA.GestionDoleances.Module;

import java.time.LocalDate;

public class ModuleDoleance {
    private String nom;
    private String prenom;
    private int cin;
    private String addresse;
    private int IDdoleance;
    private String Sujet;
    private String descreption;
    private LocalDate dateDoleance;
    private int numTel;

    public ModuleDoleance(String nom, String prenom, int cin,
                          String addresse, int IDdoleance,
                          String sujet, String descreption, LocalDate dateDoleance, int numTel) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.addresse = addresse;
        this.IDdoleance = IDdoleance;
        this.Sujet = sujet;
        this.descreption = descreption;
        this.dateDoleance = dateDoleance;
        this.numTel = numTel;
    }
    public ModuleDoleance(String nom, String prenom, int cin,
                          String addresse,
                          String sujet, String descreption, LocalDate dateDoleance, int numTel) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.addresse = addresse;
        this.Sujet = sujet;
        this.descreption = descreption;
        this.dateDoleance = dateDoleance;
        this.numTel = numTel;
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

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public int getIDdoleance() {
        return IDdoleance;
    }

    public void setIDdoleance(int IDdoleance) {
        this.IDdoleance = IDdoleance;
    }

    public String getSujet() {
        return Sujet;
    }

    public void setSujet(String sujet) {
        this.Sujet = sujet;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public LocalDate getDateDoleance() {
        return dateDoleance;
    }

    public void setDateDoleance(LocalDate dateDoleance) {
        this.dateDoleance = dateDoleance;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    @Override
    public String toString() {
        return "ModuleDoleance{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", cin=" + cin +
                ", addresse='" + addresse + '\'' +
                ", IDdoleance=" + IDdoleance +
                ", Sujet='" + Sujet + '\'' +
                ", Descreption='" + descreption + '\'' +
                ", DateDoleance=" + dateDoleance +
                ", NumTel=" + numTel +
                '}';
    }
}