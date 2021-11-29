package PFA.GestionEvenement.GUIs;

import PFA.GestionEvenement.Services.EvenementServ;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class supprimerEvenement {
    int id;


    public void confirmerButtonSupprimer (){
        EvenementServ.Suprrimer(id);
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
    @FXML
    Button annuler;
    public void annuler2(){
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
}
