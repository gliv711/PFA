package PFA.MaterielFiras.GUIsMateriel;

import PFA.MaterielFiras.ModuleMateriel.Outil;
import PFA.MaterielFiras.ServiceMateriel.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class ModifierOutilsController {
    @FXML
    TextField tfprix,tfquantité,tfnom;
    @FXML
    Label lbid,lbprix,lbnom,lbquantité;
    @FXML
    Button btnmodifier,retour;
    @FXML
    CheckBox consumable;

    String prixPattern="[0-9]*.[0-9]*";
    String nomPattern = "[a-zA-Z]{3}[a-zA-Z ]*";
    String quantitePattern = "[0-9]*";
    boolean verif = true;

    public void ModifierButton(ActionEvent event) throws IOException {
    
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
                event.getSource() == btnmodifier) {
        
            Outil p = new Outil(Integer.parseInt(lbid.getText()) ,Integer.parseInt(tfquantité.getText()),tfnom.getText(),check());
            Outils.Modifier(p);
        
            Stage stage = (Stage) btnmodifier.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("succés de modifier");
            alert.setHeaderText(null);
            alert.setContentText("modification est fait avec succés");
            alert.show();
        }
    }
    
    private int check() {
        if (consumable.isSelected()) return 1;
        else return 0;
        
        
    }
    
    public void initData(Outil p) {
            tfnom.setText(p.getNom());
            tfquantité.setText(String.valueOf(p.getQuantite()));
            lbid.setText(String.valueOf(p.getId()));
            if(p.getConsumable()==1)
                consumable.setSelected(true);
            
        }

    public void retour4() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();

    }


}
