package PFA.GestionDemandes.Module;

import java.time.LocalDate;

public class DemandeModu {
    private String nom;
    private String prenom;
    private int cin;
    private String adresse;
    private int numtel;
    private int id;
    private LocalDate dateDem;
    private String Typedem;
    private String descreption;
    
    public DemandeModu(String nom, String prenom,
                       int cin, String adresse,
                       int numtel, int id,
                       LocalDate dateDem, String Typedem,
                       String descreption) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.adresse = adresse;
        this.numtel = numtel;
        this.id = id;
        this.dateDem = dateDem;
        this.Typedem = Typedem;
        this.descreption = descreption;
    }
    
    public DemandeModu(String nom, String prenom,
                       int cin, String adresse,
                       int numtel,
                       LocalDate dateDem, String Typedem,
                       String descreption) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.adresse = adresse;
        this.numtel = numtel;
        this.dateDem = dateDem;
        this.Typedem = Typedem;
        this.descreption = descreption;
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
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public int getNumtel() {
        return numtel;
    }
    
    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public LocalDate getDateDem() {
        return dateDem;
    }
    
    public void setDateDem(LocalDate dateDem) {
        this.dateDem = dateDem;
    }
    
    public String getTypeDem() {
        return Typedem;
    }
    
    public void setTypeDem(String Typedem) {
        this.Typedem = Typedem;
    }
    
    public String getDescreption() {
        return descreption;
    }
    
    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }
    
    @Override
    public String toString() {
        return "DemandeModu{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", cin=" + cin +
                ", adresse='" + adresse + '\'' +
                ", numtel=" + numtel +
                ", id=" + id +
                ", dateDem=" + dateDem +
                ", TypeDem='" + Typedem + '\'' +
                ", descreption='" + descreption + '\'' +
                '}';
    }
}

