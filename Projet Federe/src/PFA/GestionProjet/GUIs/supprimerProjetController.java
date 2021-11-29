package PFA.GestionProjet.GUIs;

import PFA.GestionProjet.Services.ProjetServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class supprimerProjetController {
    private int id;
    
    @FXML
    Button annuler;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void confirmerButtonSupprimer() {
        ProjetServices.supprimer(id);
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
    
    
    public void annuler() {
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
}
