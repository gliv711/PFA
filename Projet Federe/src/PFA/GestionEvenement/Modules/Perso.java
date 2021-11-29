package PFA.GestionEvenement.Modules;

import javafx.scene.control.CheckBox;

public class Perso {
    private int idper;
    private String nomper,prenomper,posteper;
    private CheckBox checkper;

    public Perso(int idper, String nomper, String prenomper, String posteper) {
        this.idper = idper;
        this.nomper = nomper;
        this.prenomper = prenomper;
        this.posteper = posteper;
    }

    public Perso(String nomper, String prenomper, String posteper) {
        this.nomper = nomper;
        this.prenomper = prenomper;
        this.posteper = posteper;
    }

    public int getIdper() {
        return idper;
    }

    public void setIdper(int idper) {
        this.idper = idper;
    }

    public String getNomper() {
        return nomper;
    }

    public void setNomper(String nomper) {
        this.nomper = nomper;
    }

    public String getPrenomper() {
        return prenomper;
    }

    public void setPrenomper(String prenomper) {
        this.prenomper = prenomper;
    }

    public String getPosteper() {
        return posteper;
    }

    public void setPosteper(String posteper) {
        this.posteper = posteper;
    }

    public CheckBox getCheckper() {
        return checkper;
    }

    public void setCheckper(CheckBox checkper) {
        this.checkper = checkper;
    }
}
