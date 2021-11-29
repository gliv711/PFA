package PFA.GestionProjet.GUIs.ajouterProjet;

import PFA.GestionProjet.Module.Projet;
import PFA.GestionTache.GUIs.ListeTacheController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ajouterGenerale implements Initializable {
    
    @FXML
    private DatePicker dateDebutPicker;
    
    @FXML
    private DatePicker dateFinPicker;
    
    @FXML
    private TextField nomTextField;
    
    @FXML
    private TextField coutTextField;
    
    @FXML
    private TextField numAdresseTextField;
    
    @FXML
    private TextField adresseTextField;
    
    @FXML
    private Label DateDebutErrorLabel;
    
    @FXML
    private Label DateFinErrorLabel;
    
    @FXML
    private Label CoutErrorLabel;
    
    @FXML
    private Label AdresseErrorLabel;
    
    @FXML
    private Label NomErrorLabel;
    
    @FXML
    private Label remplir;
    @FXML
    private TextArea descriptionTF;
    public void retour(ActionEvent actionEvent)  {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void switchToTache(ActionEvent actionEvent) throws IOException {
        boolean valid = true;
        if (nomTextField.getText().isEmpty() || numAdresseTextField.getText().isEmpty() || coutTextField.getText().isEmpty() || adresseTextField.getText().isEmpty() || coutTextField.getText().isEmpty() || dateDebutPicker.getValue().toString().isEmpty() || dateFinPicker.getValue().toString().isEmpty()) {
            remplir.setVisible(true);
            valid = false;
        }
    
        if (!dateDebutPicker.getValue().toString().isEmpty() && !dateDebutPicker.getValue().toString().isEmpty() && !dateDebutPicker.getValue().isBefore(dateFinPicker.getValue())) {
            DateDebutErrorLabel.setVisible(true);
            DateFinErrorLabel.setVisible(true);
            valid = false;
        }
        String budgetPattern = "[0-9]+.[0-9]+";
        String numPattern = "[0-9]+";
        String nomPattern = "[A-Za-z0-9]{3}[A-Za-z '0-9]*";
        if (valid && Pattern.matches(budgetPattern, coutTextField.getText()) && Pattern.matches(budgetPattern, coutTextField.getText()) && Pattern.matches(numPattern, numAdresseTextField.getText()) && Pattern.matches(nomPattern, adresseTextField.getText()) && Pattern.matches(nomPattern, nomTextField.getText())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../GestionTache/GUIs/fxml/listetache.fxml"));
            Parent root = loader.load();
            ListeTacheController controller = loader.getController();
            controller.setProjet(new Projet(nomTextField.getText(),descriptionTF.getText(), new ArrayList<>(),numAdresseTextField.getText() + " " + adresseTextField.getText(),Float.parseFloat(coutTextField.getText()),dateDebutPicker.getValue(),dateFinPicker.getValue(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            JMetro jMetro = new JMetro(Style.LIGHT);
            jMetro.setScene(scene);
            stage.setScene(scene);
            stage.show();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
        String nomPattern = "[A-Za-z'#]{3}[A-Za-z '0-9]*";
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
        
        String numPattern = "[0-9]{1,5}";
        numAdresseTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                AdresseErrorLabel.setVisible(!Pattern.matches(numPattern, numAdresseTextField.getText()));
            }
        });
    
        String budgetPattern = "[0-9]+.[0-9]+";
        coutTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                CoutErrorLabel.setVisible(!Pattern.matches(budgetPattern, coutTextField.getText()));
            }
        });
    }
}
