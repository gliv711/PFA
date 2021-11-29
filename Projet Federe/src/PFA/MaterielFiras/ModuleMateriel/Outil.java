package PFA.MaterielFiras.ModuleMateriel;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;

public class Outil {
    private int id;
    private int quantite;
    private String nom;
    private CheckBox check;
    private int consumable;
    private Spinner<Integer> spinner;
    
    public Spinner<Integer> getSpinner() {
        return spinner;
    }
    
    public void setSpinner(Spinner<Integer> spinner) {
        this.spinner = spinner;
    }
    
    public Outil(int id, int quantité, String nom, int consumable) {
        this.id = id;
        this.quantite = quantité;
        this.nom = nom;
        this.check = new CheckBox();
        this.consumable = consumable;
    }
    
    public int getConsumable() {
        return consumable;
    }
    
    public void setConsumable(int consumable) {
        this.consumable = consumable;
    }
    
    public Outil(int quantité, String nom, int consumable) {
        this.quantite = quantité;
        this.nom = nom;
        this.consumable = consumable;
        this.check = new CheckBox();
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
    
    public int getQuantite() {
        return quantite;
    }
    
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    @Override
    public String toString() {
        return "MaterielOutils{" +
                "idOutils=" + id +
                ", quantitéOutils=" + quantite +
                ", nomOutils='" + nom + '\'' +
                '}';
    }
}

