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


public class ajouterPersonnelController implements Initializable {
    String namePattern = "[a-zA-Z]{3}[a-zA-Z ]*";
    String salairePattern = "[0-9]{1,9}.[0-9]+";
    String cinPattern = "[0-9]{8}";
    private final String[] Poste = {"Agent(e) administratif(tive)", "Ouvrier(e)", "Technicien(ne) Principal(e)"};
    
   
    @FXML
    ChoiceBox<String> PostePicker;
    @FXML
    Label nomErrorLabel, prenomErrorLabel, posteErrorLabel, dateErrorLabel, cinErrorLabel, salaireErrorLabel, Remplir;
    @FXML
    TextField nom, prenom, CIN, salaire;
    @FXML
    DatePicker date;
    @FXML
    Button retour;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // fill poste picker with their values
        
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
    
    @FXML
    Label cinLabel;
    
    public void AjouterButton() {
        if (PostePicker.getSelectionModel().isEmpty() || salaire.getText().isEmpty() || CIN.getText().isEmpty() || prenom.getText().isEmpty() || nom.getText().isEmpty() || date.getValue().toString().isEmpty()) {
            Remplir.setVisible(true);
            return ;
        } else Remplir.setVisible(false);
        boolean valid = date.getValue().isBefore(LocalDate.now().minus(18,ChronoUnit.YEARS));
        dateErrorLabel.setVisible(!valid);
        
        boolean cinExist = PersonnelServices.CINexist(Integer.parseInt(CIN.getText()));
        cinLabel.setVisible(cinExist);
        if (valid && !cinExist && Pattern.matches(cinPattern, CIN.getText()) && Pattern.matches(salairePattern, salaire.getText()) && Pattern.matches(namePattern, prenom.getText()) && Pattern.matches(namePattern, nom.getText()) && !PostePicker.getSelectionModel().isEmpty()) {
            Personnel p = new Personnel(nom.getText(), prenom.getText(), Integer.parseInt(CIN.getText()), Float.parseFloat(salaire.getText()), PostePicker.getValue(), date.getValue());
            PersonnelServices.Ajouter(p);
            Stage stage = (Stage) retour.getScene().getWindow();
            stage.close();
        }
    }
    
    public void retour() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }
    
    
}
