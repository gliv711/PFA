package PFA.GestionIntervention.GUIs;

import PFA.GestionIntervention.Modules.Intervention;
import PFA.GestionIntervention.Services.InterventionServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class ModifierInterventionController {
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
    int id;
    public void initData(Intervention selectedItem) {
        id = selectedItem.getId();
        nomTextField.setText(selectedItem.getNom());
        adresseTextField.setText(selectedItem.getAdresse());
        budgetTextField.setText(String.valueOf(selectedItem.getBudget()));
        dateDebutPicker.setValue(selectedItem.getDateBedut());
        dateFinPicker.setValue(selectedItem.getDateFin());
    
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
    
        if (!dateDebutPicker.getValue().toString().isEmpty() && !dateDebutPicker.getValue().toString().isEmpty() && !dateDebutPicker.getValue().isBefore(dateFinPicker.getValue())) {
            DateDebutErrorLabel.setVisible(true);
            DateFinErrorLabel.setVisible(true);
            valid = false;
        }
        if (valid && Pattern.matches(budgetPattern, budgetTextField.getText()) && Pattern.matches(nomPattern, adresseTextField.getText()) && Pattern.matches(nomPattern, nomTextField.getText())) {
            Intervention i = new Intervention(id,nomTextField.getText(),dateDebutPicker.getValue(),dateFinPicker.getValue(),Float.parseFloat(budgetTextField.getText()),adresseTextField.getText());
            InterventionServices.Modifier(i);
        }
    }
    
    public void retour() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }
}
