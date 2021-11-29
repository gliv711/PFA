package PFA.GestionIntervention.GUIs;

import PFA.GestionIntervention.Services.InterventionServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class supprimerInterventionController {
    
    int id;
    
    
    public void confirmerButtonSupprimer (){
        InterventionServices.Suprrimer(id);
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
    @FXML
    Button annuler;
    public void annuler(){
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
}
