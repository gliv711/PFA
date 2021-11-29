package PFA.GestionIntervention.GUIs.ajouterIntervention;

import PFA.GestionIntervention.Modules.Intervention;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ajouterGenerale implements Initializable {
    
    private final String nomPattern = "[A-Za-z0-9]{3}[0-9A-Za-z ]*";
    private final String budgetPattern = "[0-9]+.?[0-9]+";
    private final String numPattern = "[0-9]*";
    @FXML
    TextField nomTextField, budgetTextField, coutTextField, numAdresseTextField, adresseTextField;
    @FXML
    DatePicker dateDebutPicker, dateFinPicker;
    @FXML
    private Label DateDebutErrorLabel;
    
    @FXML
    private Label DateFinErrorLabel;
    
    @FXML
    private Label CoutErrorLabel;
    
    @FXML
    private Label AdresseErrorLabel;
    
    @FXML
    private Label NomErrorLabel, remplir;
    
    @FXML
    Button retour;
    
    public void switchToPersonnel(ActionEvent event) throws IOException {
        boolean valid = true;
        if (nomTextField.getText().isEmpty() || numAdresseTextField.getText().isEmpty() || coutTextField.getText().isEmpty() || adresseTextField.getText().isEmpty() || dateDebutPicker.getValue().toString().isEmpty() || dateFinPicker.getValue().toString().isEmpty()) {
            remplir.setVisible(true);
            valid = false;
        }
        
        if (!dateDebutPicker.getValue().toString().isEmpty() && !dateDebutPicker.getValue().toString().isEmpty() && !dateDebutPicker.getValue().isBefore(dateFinPicker.getValue())) {
            DateDebutErrorLabel.setVisible(true);
            DateFinErrorLabel.setVisible(true);
            valid = false;
        }
        if (valid && Pattern.matches(budgetPattern, coutTextField.getText()) && Pattern.matches(budgetPattern, coutTextField.getText()) && Pattern.matches(numPattern, numAdresseTextField.getText()) && Pattern.matches(nomPattern, adresseTextField.getText()) && Pattern.matches(nomPattern, nomTextField.getText())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouterPersonnelIntervention.fxml"));
            Parent root = loader.load();
            ajouterPersonnelIntervention controller = loader.getController();
            controller.setIntervention(new Intervention(nomTextField.getText(), dateDebutPicker.getValue(), dateFinPicker.getValue(), Float.parseFloat(coutTextField.getText()), numAdresseTextField.getText() + " " + adresseTextField.getText()));
            controller.initData();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            JMetro jMetro = new JMetro(Style.LIGHT);
            jMetro.setScene(scene);
            stage.setScene(scene);
            stage.show();
        }
        
    }
    
    public void retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        nomTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                NomErrorLabel.setVisible(!Pattern.matches(nomPattern, nomTextField.getText()));
            }
        });
        
        adresseTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                AdresseErrorLabel.setVisible(!Pattern.matches(nomPattern, adresseTextField.getText()));
            }
        });
        
        numAdresseTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                AdresseErrorLabel.setVisible(!Pattern.matches(numPattern, numAdresseTextField.getText()));
            }
        });
        
        coutTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                CoutErrorLabel.setVisible(!Pattern.matches(budgetPattern, coutTextField.getText()));
            }
        });
        
        
    }
}
