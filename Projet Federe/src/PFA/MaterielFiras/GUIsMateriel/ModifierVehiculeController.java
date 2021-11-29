package PFA.MaterielFiras.GUIsMateriel;

import PFA.MaterielFiras.ModuleMateriel.Vehicule;
import PFA.MaterielFiras.ServiceMateriel.Vehicules;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class ModifierVehiculeController {
    @FXML
    TextField tfid, tfmodel, tfmatricule, prixtextField, tfnom;
    @FXML
    Button btnajouter;
    @FXML
    DatePicker datePicker;
    @FXML
    Button retour;
    @FXML
    Label  lbprix,lbmodel,lbnom,lbmatricule,lbdate,lbloo;

    
    String prixPattern="[0-9]*.[0-9]*";
    String nomPattern = "[a-zA-Z]{3}[a-zA-Z ]*";
    String modelPattern = "[a-zA-Z0-9-_]*[a-zA-Z0-9-_ ]*";
    String matriculePattern = "[0-9]{4}";

    boolean verif = true;

    public void ModifierButton(ActionEvent event) throws IOException {
        if (verif && Pattern.matches(nomPattern, tfnom.getText()) && Pattern.matches(modelPattern, tfmodel.getText()) && Pattern.matches(matriculePattern, tfmatricule.getText()) && Pattern.matches(prixPattern, prixtextField.getText()) && !prixtextField.getText().isEmpty() && !tfmodel.getText().isEmpty() && !tfmatricule.getText().isEmpty() && !tfnom.getText().isEmpty()) {
            Vehicule p = new Vehicule(id,Integer.parseInt(tfmatricule.getText()),tfmodel.getText(),tfnom.getText(),datePicker.getValue(),Float.parseFloat(prixtextField.getText()));
            Vehicules.Modifier(p);
            Stage stage = (Stage) btnajouter.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("modification est fait avec succés");
            alert.show();
        
        
        }

    }
private int id;
    public void initData(Vehicule p) {
        tfnom.setText(p.getNom());
        tfmodel.setText(p.getModel());
        tfmatricule.setText(String.valueOf(p.getMatricule()));
        id = p.getId();
    
        tfnom.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbnom.setVisible(!Pattern.matches(nomPattern, tfnom.getText())|| tfnom.getText().isEmpty());
            }
        });
    
        tfmodel.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbmodel.setVisible(!Pattern.matches(modelPattern, tfmodel.getText()) || tfmodel.getText().isEmpty());
            }
        });
    
    
        tfmatricule.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbmatricule.setVisible(!Pattern.matches(matriculePattern, tfmatricule.getText())|| tfmatricule.getText().isEmpty());
            }
        });
    
        prixtextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbprix.setVisible(!Pattern.matches(prixPattern, prixtextField.getText())|| prixtextField.getText().isEmpty());
            }
        });
    }

    public void retour() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();

    }

}
