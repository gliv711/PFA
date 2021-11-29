package PFA.GestionCompte.GUIs;

import PFA.GestionCompte.Modules.Compte;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class detailsCompteController{
    
    @FXML
    private Button ajouter;
    
    @FXML
    private Label nomULabel;
    
    @FXML
    private Label mdpLabel;
    
    @FXML
    private Label roleLabel;
    
    @FXML
    private Label cintLabel;
    
    @FXML
    private Label prenomLabel;
    
    @FXML
    private Label nomLabel;
    
    @FXML
    private Label posteLabel;
    
    public void initData(Compte compte){
        nomLabel.setText(compte.getPersonnel().getNom());
        prenomLabel.setText(compte.getPersonnel().getPrenom());
        cintLabel.setText(String.valueOf(compte.getPersonnel().getCIN()));
        roleLabel.setText(compte.getRole());
        mdpLabel.setText(compte.getMDP());
        nomULabel.setText(compte.getNomUtilisateur());
        posteLabel.setText(compte.getPersonnel().getPoste());
    }
    
    public void annuler(){
        Stage stage = (Stage) ajouter.getScene().getWindow();
        stage.close();
    }
    
}
