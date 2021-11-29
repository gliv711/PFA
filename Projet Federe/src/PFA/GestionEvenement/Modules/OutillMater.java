package PFA.GestionEvenement.Modules;

import PFA.MaterielFiras.ModuleMateriel.Outil;

public class OutillMater {
    public String nom;
    public Outil outils;
    public int quantite;

    public OutillMater(Outil outils, int quantite) {
        this.outils = outils;
        this.quantite = quantite;
        this.nom= outils.getNom();
    }
}
