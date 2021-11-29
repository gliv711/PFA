package PFA.GestionPersonnel.GUIs;


import PFA.GestionPersonnel.Modules.Personnel;
import PFA.GestionPersonnel.Services.PersonnelServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SupprimerPersonnelController {
    private Personnel p;
    
    @FXML
    Button annuler;
    public void setP(Personnel p) {
        this.p = p;
    }
    
    public void confirmerButtonSupprimer (){
        PersonnelServices.Suprrimer(p.getId());
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
    
    public void annuler(){
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
}
