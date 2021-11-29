package PFA.MaterielFiras.GUIsMateriel;

import PFA.MaterielFiras.ServiceMateriel.Vehicules;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.ResourceBundle;
import java.net.URL;

public class SuppressionVehiculeController implements Initializable {

    @FXML
    Button btnannuler,btnconfirmer;
    int matricule;


    public void initialize(URL location, ResourceBundle resources) {
    }

    public void confirmerButtonSupprimer (){

      Vehicules.Suprrimer(matricule);
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
