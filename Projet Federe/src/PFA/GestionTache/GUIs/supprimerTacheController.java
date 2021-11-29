package PFA.GestionTache.GUIs;

import PFA.GestionTache.Services.tacheServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class supprimerTacheController {
    @FXML
    Button annuler;
    private int id;
    public void setID (int id) {this.id = id;}
    public void supprimer() {
        tacheServices.Suprrimer(id);
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
    
    public void annuler(ActionEvent actionEvent) {
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
}
