package PFA.GestionCompte.GUIs;

import PFA.GestionCompte.Modules.Compte;
import PFA.GestionCompte.Services.compteServices;
import PFA.GestionPersonnel.Modules.Personnel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ajouterCompteController implements Initializable {
    public Label userLabel;
    @FXML
    ComboBox<String> roleCombobox;
    @FXML
    private TextField nomTextField;
    
    @FXML
    private TextField MDPTextField;
    
    @FXML
    private TextField confirmerTextField;
    
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
    
    @FXML
    private Button annuler;
    
    private final String[] roleListe = { "Adminstrateur", "Agent Financier", "Agent RH" ,"Agent Guichet" , "Project Manager"};
    private final String nomPattern = "[0-9a-zA-Z_]{5,20}";
    private final String passPattern = "[0-9a-zA-Z_]{5,20}";
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        posteColumn.setCellValueFactory(new PropertyValueFactory<>("poste"));
        cinColumn.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        
        personnelTV.getItems().setAll(compteServices.ParsePersonnelListe());
        
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
        boolean valid = Pattern.matches(passPattern, MDPTextField.getText()) && confirmerTextField.getText().equals(MDPTextField.getText()) && Pattern.matches(nomPattern, nomTextField.getText());
        boolean personnelSelected = personnelTV.getSelectionModel().isEmpty();
        boolean roleSelected = roleCombobox.getSelectionModel().isEmpty();
        personnelLabel.setVisible(personnelSelected);
        roleLabel.setVisible(roleSelected);
        if (nomTextField.getText().isEmpty() || MDPTextField.getText().isEmpty() || confirmerTextField.getText().isEmpty() || roleCombobox.getSelectionModel().isEmpty()) {
            remplirLabel.setVisible(true);
            return ;
        }
        boolean usernameExists = compteServices.usernameExist(nomTextField.getText());
        userLabel.setVisible(usernameExists);
        if (valid && !usernameExists && !roleSelected && !personnelSelected) {
            Compte compte = new Compte(nomTextField.getText(), MDPTextField.getText(), roleCombobox.getSelectionModel().getSelectedItem(), personnelTV.getSelectionModel().getSelectedItem());
            compteServices.Ajouter(compte);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Compte Ajoute");
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
