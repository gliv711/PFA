package PFA.GestionTache.GUIs;

import PFA.GestionTache.Module.Tache;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class detailsTacheController{
    
    @FXML
    private Label nomLabel,nomTacheLabel;
    
    
    @FXML
    private Label cintLabel;
    
    @FXML
    private Label prenomLabel;
    
    @FXML
    private Label posteLabel;
    
    @FXML
    private TextArea descriptionLabel;
    
    public void retour() {
        Stage stage  = (Stage) posteLabel.getScene().getWindow();
        stage.close();
    }
    
    public void initData(Tache tache){
        descriptionLabel.setText(tache.getDescription());
        if(!(tache.getPersonnel() == null)){
            prenomLabel.setText(tache.getPersonnel().getNom());
            nomLabel.setText(tache.getPersonnel().getPrenom());
            cintLabel.setText(String.valueOf(tache.getPersonnel().getCIN()));
            posteLabel.setText(tache.getPersonnel().getPoste());
        }else {
            prenomLabel.setText("Non Disponible");
            nomLabel.setText("Non Disponible");
            cintLabel.setText("Non Disponible");
            posteLabel.setText("Non Disponible");
        }
        nomTacheLabel.setText(tache.getNom());
    }
    
}
