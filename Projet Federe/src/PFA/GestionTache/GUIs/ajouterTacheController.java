package PFA.GestionTache.GUIs;

import PFA.GestionPersonnel.Modules.Personnel;
import PFA.GestionTache.Module.Tache;
import PFA.GestionTache.Services.tacheServices;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class ajouterTacheController {
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
    private Label nomLabel;
    
    @FXML
    private Label remplirLabel;
    
    @FXML
    private Button ajouter;
    
    @FXML
    private TextArea descriptionTextField;
    
    @FXML
    private Label dateLabel;
    
    @FXML
    private DatePicker datepicker;
    
    public void ajouter() {
        if (nomTextField.getText().isEmpty()) {
            remplirLabel.setVisible(true);
            return;
        } else remplirLabel.setVisible(false);
        
        if (datepicker.getValue().isBefore(LocalDate.now())) {
            dateLabel.setVisible(true);
            return;
        } else dateLabel.setVisible(false);
        Tache tache = new Tache(nomTextField.getText(), descriptionTextField.getText(), personnelTV.getSelectionModel().getSelectedItem(), datepicker.getValue());
        tacheServices.ajouter(tache);
        Stage stage = (Stage) ajouter.getScene().getWindow();
        stage.close();
    }
    
    public void annuler() {
        Stage stage = (Stage) ajouter.getScene().getWindow();
        stage.close();
    }
    
    public void initListe() {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        posteColumn.setCellValueFactory(new PropertyValueFactory<>("poste"));
        cinColumn.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        
        personnelTV.getItems().setAll(tacheServices.ParsePersonnelListe());
        
        
        nomTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                String nomPattern = "[a-zA-Z0-9 ._]";
                nomLabel.setVisible(!Pattern.matches(nomPattern, nomTextField.getText()));
            }
        });
    }
}
