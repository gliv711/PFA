package PFA.GestionDemandes.GUIs;
import PFA.GestionDemandes.Module.DemandeModu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DetailsDemandeController {
    @FXML
    TextField nom,prenom,cin,adresse,tel,date,type,id,tell;
    @FXML
    TextArea descreption;
    @FXML
    Button retour;

    public void initData(DemandeModu p) {
        nom.setText(p.getNom());
        prenom.setText(p.getPrenom());
        date.setText(p.getDateDem().toString());
        id.setText(String.valueOf(p.getId()));
        cin.setText(String.valueOf(p.getCin()));
        descreption.setText(p.getDescreption());
        type.setText(p.getTypeDem());
        adresse.setText(p.getAdresse());
        tel.setText(String.valueOf(p.getNumtel()));
        nom.setEditable(false);
        prenom.setEditable(false);
        date.setEditable(false);
        id.setEditable(false);
        cin.setEditable(false);
        descreption.setEditable(false);
        type.setEditable(false);
        adresse.setEditable(false);
        tel.setEditable(false);
        tell.setEditable(false);
    }

    public void retour3() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }
}
