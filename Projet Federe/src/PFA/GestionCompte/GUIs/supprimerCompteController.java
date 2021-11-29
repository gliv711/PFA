package PFA.GestionCompte.GUIs;

import PFA.GestionCompte.Services.compteServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class supprimerCompteController {
    int id;
    @FXML
    Button annuler;
    
    public void supprimer (){
        compteServices.Suprrimer(id);
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
    
    public void annuler(ActionEvent actionEvent) {
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
}
