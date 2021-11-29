package PFA.GestionCompte.GUIs;

import PFA.GestionCompte.Modules.Compte;
import PFA.GestionCompte.Services.compteServices;
import PFA.GestionPersonnel.Modules.Personnel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class modifierCompteController {
    
    
    private final String[] roleListe = { "Adminstrateur", "Agent Financier", "Agent RH" ,"Agent Guichet" , "Project Manager"};
    private final String nomPattern = "[0-9a-zA-Z_.]{5,20}";
    private final String passPattern = "[0-9a-zA-Z_]{5,20}";
    @FXML
    ComboBox<String> roleCombobox;
    
    @FXML
    private TextField nomTextField;
    
    @FXML
    private TableView<Personnel> personnelTV;
    
    @FXML
    private TableColumn<Personnel, String> nomColumn;
    
    @FXML
    private TableColumn<Personnel, String> prenomColumn;
    
    @FXML
    private TableColumn<Personnel, String> posteColumn;
    
    @FXML
    private TableColumn<Personnel, Integer> cinColumn;
    
    @FXML
    private TextField MDPTextField;
    
    @FXML
    private TextField confirmerTextField;
    
    @FXML
    private Label nomLabel;
    
    @FXML
    private Label mdpLabel;
    
    @FXML
    private Label confirmerLabel;
    
    @FXML
    private Label personnelLabel;
    
    @FXML
    private Label remplirLabel;
    
    @FXML
    private Label roleLabel;
    
    @FXML
    private Button ajouter;
    
    public void initData(Compte compte){
        nomTextField.setText(compte.getNomUtilisateur());
        MDPTextField.setText(compte.getMDP());
        confirmerTextField.setText(compte.getMDP());
        
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        posteColumn.setCellValueFactory(new PropertyValueFactory<>("poste"));
        cinColumn.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    
        personnelTV.getItems().setAll(compteServices.ParsePersonnelListe());
        personnelTV.getItems().add(compte.getPersonnel());
    
        roleCombobox.getItems().setAll(roleListe);
    
        nomTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                nomLabel.setVisible(!Pattern.matches(nomPattern, nomTextField.getText()));
            }
        });
    
        MDPTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                mdpLabel.setVisible(!Pattern.matches(passPattern, MDPTextField.getText()));
            }
        });
    
        confirmerTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                confirmerLabel.setVisible(!confirmerTextField.getText().equals(MDPTextField.getText()));
            }
        });
    }
    
    public void ajouter() {
        boolean valid = !personnelTV.getSelectionModel().isEmpty() && Pattern.matches(passPattern, MDPTextField.getText()) && confirmerTextField.getText().equals(MDPTextField.getText()) && Pattern.matches(nomPattern, nomTextField.getText());
        personnelLabel.setVisible(personnelTV.getSelectionModel().isEmpty());
        roleLabel.setVisible(roleCombobox.getSelectionModel().isEmpty());
        if (nomTextField.getText().isEmpty() || MDPTextField.getText().isEmpty() || confirmerTextField.getText().isEmpty() || roleCombobox.getSelectionModel().isEmpty()) {
            valid = false;
            remplirLabel.setVisible(true);
        }
        
        if (valid) {
            Compte compte = new Compte(nomTextField.getText(), MDPTextField.getText(), roleCombobox.getSelectionModel().getSelectedItem(), personnelTV.getSelectionModel().getSelectedItem());
            compteServices.Modifier(compte);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Compte Modifier");
            alert.showAndWait();
            Stage stage = (Stage) ajouter.getScene().getWindow();
            stage.close();
        }
    }
    
    public void annuler() {
        Stage stage = (Stage) ajouter.getScene().getWindow();
        stage.close();
    }
    
}
