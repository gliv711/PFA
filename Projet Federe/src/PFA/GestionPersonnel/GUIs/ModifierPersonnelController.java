package PFA.GestionPersonnel.GUIs;

import PFA.GestionPersonnel.Modules.Personnel;
import PFA.GestionPersonnel.Services.PersonnelServices;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.regex.Pattern;



public class ModifierPersonnelController implements Initializable {
    
    @FXML
    ChoiceBox<String> PostePicker;
    private final String[] Poste = { "Agent(e) administratif (tive)", "Ouvrier(e)", "Technicien(ne) Principal(e)" };
    String namePattern = "[a-zA-Z]{3}[a-zA-Z ]*";
    String salairePattern = "[0-9]{1,9}.[0-9]+";
    String cinPattern = "[0-9]{8}";
    
    @FXML
    Label nomErrorLabel, prenomErrorLabel, posteErrorLabel, dateErrorLabel, cinErrorLabel, salaireErrorLabel, Remplir;
    
    public void initialize(URL location, ResourceBundle resources) {
        
        PostePicker.getItems().setAll(Poste);
        
        //Check if every text field is correct when it loses focus
        
        nom.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                nomErrorLabel.setVisible(!Pattern.matches(namePattern, nom.getText()));
            }
        });
        
        
        PostePicker.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                posteErrorLabel.setVisible(PostePicker.getSelectionModel().isEmpty());
            }
        });
        
        
        prenom.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                prenomErrorLabel.setVisible(!Pattern.matches(namePattern, prenom.getText()));
            }
        });
        
        
        salaire.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                nomErrorLabel.setVisible(!Pattern.matches(salairePattern, salaire.getText()));
            }
        });
        
        
        CIN.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                cinErrorLabel.setVisible(!Pattern.matches(cinPattern, CIN.getText()));
            }
        });
        
        
    }
    
    
    public void ModifierButton() {
        if (PostePicker.getSelectionModel().isEmpty() || salaire.getText().isEmpty() || CIN.getText().isEmpty() || prenom.getText().isEmpty() || nom.getText().isEmpty() || date.getValue().toString().isEmpty()) {
            Remplir.setVisible(true);
            return ;
        } else Remplir.setVisible(false);
        boolean valid = date.getValue().isBefore(LocalDate.now().minus(18,ChronoUnit.YEARS));
        dateErrorLabel.setVisible(!valid);
        
    
        if (valid && Pattern.matches(cinPattern, CIN.getText()) && Pattern.matches(salairePattern, salaire.getText()) && Pattern.matches(namePattern, prenom.getText()) && Pattern.matches(namePattern, nom.getText()) && !PostePicker.getSelectionModel().isEmpty()) {
            Personnel p = new Personnel(id, nom.getText(), prenom.getText(), Integer.parseInt(CIN.getText()), Float.parseFloat(salaire.getText()), PostePicker.getValue(), date.getValue());
            PersonnelServices.Modifier(p);
            Stage stage = (Stage) retour.getScene().getWindow();
            stage.close();
        }
    }
    
    private int id;
    @FXML
    TextField nom, prenom, CIN, salaire;
    @FXML
    DatePicker date;
    
    public void initData(Personnel p) {
        id = p.getId();
        nom.setText(p.getNom());
        prenom.setText(p.getPrenom());
        salaire.setText(String.valueOf(p.getSalaire()));
        CIN.setText(String.valueOf(p.getCIN()));
        date.setValue(p.getDateNaissance());
        PostePicker.setValue(p.getPoste());
    }
    
    @FXML
    Button retour;
    
    public void retour() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }
}
