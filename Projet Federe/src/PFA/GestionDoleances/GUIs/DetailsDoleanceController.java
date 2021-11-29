package PFA.GestionDoleances.GUIs;
import PFA.GestionDoleances.Module.ModuleDoleance;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
public class DetailsDoleanceController {
    @FXML
    TextField id,nom,prenom,cin,sujet,addresse,tel,date,tell;
    @FXML
    TextArea desc;
    @FXML
    Button retour;


    public void retour3() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }


    public void initData(ModuleDoleance p) {
        nom.setText(p.getNom());
        prenom.setText(p.getPrenom());
        // String date = p.getDateDachat().toString();
        date.setText(p.getDateDoleance().toString());
        id.setText(String.valueOf(p.getIDdoleance()));
        cin.setText(String.valueOf(p.getCin()));
        desc.setText(p.getDescreption());
        sujet.setText(p.getSujet());
        addresse.setText(p.getAddresse());
        tel.setText(String.valueOf(p.getNumTel()));
        nom.setEditable(false);
        prenom.setEditable(false);
        date.setEditable(false);
        id.setEditable(false);
        cin.setEditable(false);
        desc.setEditable(false);
        sujet.setEditable(false);
        addresse.setEditable(false);
        tel.setEditable(false);
        tell.setEditable(false);
    }

}
