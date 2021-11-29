package PFA.GestionCompte.Modules;

import PFA.GestionPersonnel.Modules.Personnel;

public class Compte {
    private int id;
    private String nomUtilisateur;
    private String MDP;
    private String role;
    private Personnel personnel;
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public Compte(String nomUtilisateur, String MDP, String role, Personnel personnel) {
        this.nomUtilisateur = nomUtilisateur;
        this.MDP = MDP;
        this.role = role;
        this.personnel = personnel;
    }
    
    public Compte(int id, String nomUtilisateur, String MDP, String role, Personnel personnel) {
        this.id = id;
        this.nomUtilisateur = nomUtilisateur;
        this.MDP = MDP;
        this.role = role;
        
        this.personnel = personnel;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNomUtilisateur() {
        return nomUtilisateur;
    }
    
    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }
    
    public String getMDP() {
        return MDP;
    }
    
    public void setMDP(String MDP) {
        this.MDP = MDP;
    }
    
    public Personnel getPersonnel() {
        return personnel;
    }
    
    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }
}
