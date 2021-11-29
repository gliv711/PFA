package PFA.GestionDemandes.GUIs;

import PFA.GestionDemandes.Service.DemandeServ;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SupprimerDemandeController {
    @FXML
    Button confirmer,annuler;

    public void annuler(){
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
    int id;
    public void confirmer (){
        DemandeServ.Supprimer(id);
        Stage stage = (Stage) confirmer.getScene().getWindow();
        stage.close();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("succés de suppression");
        alert.setHeaderText(null);
        alert.setContentText("suppression faite avec succés");
        alert.show();
    }
}
