package PFA.MaterielFiras.GUIsMateriel;

import PFA.MaterielFiras.ModuleMateriel.Vehicule;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
public class DetailsVehiculeController {
    @FXML
    Label lbid,lbmodel,lbnom,lbquantité,lbmatricule,lbprix,lbdate;
    @FXML
    Button retour;

    public void retour3() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }

    public void initData(Vehicule p) {
        lbnom.setText(p.getNom());
        lbmodel.setText(p.getModel());
       // String date = p.getDateDachat().toString();
        lbid.setText(String.valueOf(p.getId()));
        lbmatricule.setText(String.valueOf(p.getMatricule()));

    }

}
