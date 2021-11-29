package PFA.GestionIntervention.Modules;

import PFA.MaterielFiras.ModuleMateriel.Outil;

public class OutilsUtilise {
    public OutilsUtilise(Outil outils, int quantite) {
        this.outils = outils;
        this.quantite = quantite;
        this.nom = outils.getNom();
    }
    public String nom;
    public Outil outils;
    public int quantite;
}
