package PFA.GestionFinances.GUIs;

import PFA.GestionFinances.Service.OperationServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class supprimerController {
    private int id;
    
    public void setId(int id) {
        this.id = id;
    }
    
    @FXML
    Button annuler;
    
    public void confirmer() {
        OperationServices.supprimer(id);
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
    
    public void annuler() {
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
}
