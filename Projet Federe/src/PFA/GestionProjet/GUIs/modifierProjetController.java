package PFA.GestionProjet.GUIs;

import PFA.GestionProjet.Module.Projet;
import PFA.GestionProjet.Services.ProjetServices;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class modifierProjetController {
    private final String nomPattern = "[A-Za-z]{3}[A-Za-z ]*";
    private final String adressePattern = "[A-Za-z 0-9]*";
    private final String budgetPattern = "[0-9]+.[0-9]+";
    @FXML
    private DatePicker dateDebutPicker;
    
    @FXML
    private DatePicker dateFinPicker;
    
    @FXML
    private TextField nomTextField;
    
    @FXML
    private TextField budgetTextField;
    
    
    @FXML
    private TextField adresseTextField;
    
    @FXML
    private Button retour;
    
    @FXML
    private Label DateDebutErrorLabel;
    
    @FXML
    private Label DateFinErrorLabel;
    
    @FXML
    private Label BudgetErrorLabel;
    
    @FXML
    private Label AdresseErrorLabel;
    
    @FXML
    private Label NomErrorLabel;
    
    @FXML
    private Label remplir;
    
    @FXML private TextArea descriptionTF;
    int id;
    public void initData(Projet selectedItem) {
        id = selectedItem.getId();
        nomTextField.setText(selectedItem.getNom());
        adresseTextField.setText(selectedItem.getAdresse());
        budgetTextField.setText(String.valueOf(selectedItem.getCout()));
        dateDebutPicker.setAccessibleText(selectedItem.getDateBedut().toString());
        dateFinPicker.setAccessibleText(selectedItem.getDateFin().toString());
        descriptionTF.setText(selectedItem.getDescription());
        
        nomTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                NomErrorLabel.setVisible(!Pattern.matches(nomPattern, nomTextField.getText()));
            }
        });
        
        adresseTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                AdresseErrorLabel.setVisible(!Pattern.matches(adressePattern, adresseTextField.getText()));
            }
        });
        
        budgetTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                BudgetErrorLabel.setVisible(!Pattern.matches(budgetPattern, budgetTextField.getText()));
            }
        });
        
    }
    
    
    public void modifier() throws IOException {
        boolean valid = true;
        if (nomTextField.getText().isEmpty() || adresseTextField.getText().isEmpty() || budgetTextField.getText().isEmpty() || dateDebutPicker.getValue().toString().isEmpty() || dateFinPicker.getValue().toString().isEmpty()) {
            remplir.setVisible(true);
            valid = false;
        }
        
        if (dateDebutPicker.getValue().toString().isEmpty() || dateDebutPicker.getValue().toString().isEmpty() || !dateDebutPicker.getValue().isBefore(dateFinPicker.getValue())) {
            DateDebutErrorLabel.setVisible(true);
            DateFinErrorLabel.setVisible(true);
            valid = false;
        }
        if (valid && Pattern.matches(budgetPattern, budgetTextField.getText()) && Pattern.matches(adressePattern, adresseTextField.getText()) && Pattern.matches(nomPattern, nomTextField.getText())) {
            Projet projet = new Projet(id,nomTextField.getText(),descriptionTF.getText(),adresseTextField.getText(),Float.parseFloat(budgetTextField.getText()),dateDebutPicker.getValue(),dateFinPicker.getValue());
            ProjetServices.modifier(projet);
            System.out.println("fiff");
        }
    }
    
    public void retour() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }
}
