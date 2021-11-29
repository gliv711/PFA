package PFA.MaterielFiras.GUIsMateriel;

import PFA.MaterielFiras.ModuleMateriel.Outil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import PFA.MaterielFiras.ServiceMateriel.Outils;

import java.io.IOException;
import java.util.regex.Pattern;

public class ajouterOutilsController {
    @FXML
    TextField tfquantité, tfnom;
    @FXML
    Button btnajouter, retour;
    @FXML
    Label lbquantité, lbnom;
    @FXML
    CheckBox consumableCheck;
    
    String nomPattern = "[a-zA-Z]{3}[a-zA-Z ]*";
    String quantitePattern = "[0-9]+";
    boolean verif = true;
    
    public void ajouterOutils(ActionEvent event) throws IOException {
        
        tfnom.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbnom.setVisible(!Pattern.matches(nomPattern, tfnom.getText()) || tfnom.getText().isEmpty());
            }
        });
        
        
        tfquantité.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbquantité.setVisible(!Pattern.matches(quantitePattern, tfquantité.getText()) || tfquantité.getText().isEmpty());
            }
        });
        
        
        if (verif && Pattern.matches(nomPattern, tfnom.getText())
                && Pattern.matches(quantitePattern, tfquantité.getText()) &&
                event.getSource() == btnajouter) {
                
            Outil p = new Outil(Integer.parseInt(tfquantité.getText()),tfnom.getText(),check());
            Outils.Ajouter(p);
            
            Stage stage = (Stage) btnajouter.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("succés d'ajout");
            alert.setHeaderText(null);
            alert.setContentText("l'ajout est fait avec succés");
            alert.show();
        }
        
    }
    
    private int check(){
        if(consumableCheck.isSelected())
            return 1;
        else return 0;
    }
    
    public void retour3() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }
    
}

