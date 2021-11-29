package PFA.GestionPersonnel.GUIs;


import PFA.GestionPersonnel.Modules.Personnel;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class DetailsPersonnelController {

    @FXML
    Label IDD,nomD,prenomD,dateD,posteD,salaireD,CIND;
    @FXML
    Button retour;
    public void retour() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }


    public void initData(Personnel p) {
        nomD.setText(p.getNom());
        prenomD.setText(p.getPrenom());
        IDD.setText(String.valueOf(p.getId()));
        salaireD.setText(String.valueOf(p.getSalaire()));
        posteD.setText(p.getPoste());
        CIND.setText(String.valueOf(p.getCIN()));
        dateD.setText(p.getDateNaissance().toString());
    }

}
