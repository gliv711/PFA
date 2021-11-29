package PFA.MaterielFiras.GUIsMateriel;

import PFA.MaterielFiras.ServiceMateriel.Outils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;


public class SupprimerOutilsController{
    @FXML
    Button btnconfirmer,btnannuler;
    @FXML
    ChoiceBox <Integer> choiceid;
    int id;
    

    public void confirmerButtonSupprimer (){

        Outils.Suprrimer(id);
        Stage stage = (Stage) btnconfirmer.getScene().getWindow();
        stage.close();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("succés de suppression");
        alert.setHeaderText(null);
        alert.setContentText("suppression faite avec succés");
        alert.show();
    }

    public void annuler(){
        Stage stage = (Stage) btnannuler.getScene().getWindow();
        stage.close();
    }

}
