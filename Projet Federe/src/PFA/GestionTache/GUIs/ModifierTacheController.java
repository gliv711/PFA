package PFA.GestionTache.GUIs;

import PFA.GestionCompte.Services.compteServices;
import PFA.GestionPersonnel.Modules.Personnel;
import PFA.GestionTache.Module.Tache;
import PFA.GestionTache.Services.tacheServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ModifierTacheController {
    
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
    private DatePicker datepicker;
    
    @FXML
    private Label dateLabel;
    
    public void initListe(Tache tache){
        id = tache.getId();
        personnel = tache.getPersonnel();
        nomTextField.setText(tache.getNom());
        descriptionTextField.setText(tache.getDescription());
    
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        posteColumn.setCellValueFactory(new PropertyValueFactory<>("poste"));
        cinColumn.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    
        personnelTV.getItems().setAll(tacheServices.ParsePersonnelListe());
    }
    int id;
    Personnel personnel;
    public void modifier() {
        if (nomTextField.getText().isEmpty()){
            remplirLabel.setVisible(true);
            return ;
        }else remplirLabel.setVisible(false);
        if(datepicker.getValue().isBefore(LocalDate.now())){
            dateLabel.setVisible(true);
            return;
        }else dateLabel.setVisible(false);
        personnel = personnelTV.getSelectionModel().getSelectedItem();
        tacheServices.Modifier(new Tache(id,nomTextField.getText(),descriptionTextField.getText(),personnel,datepicker.getValue()));
        Stage stage = (Stage) ajouter.getScene().getWindow();
        stage.close();
    }
    
    public void annuler() {
        Stage stage = (Stage) ajouter.getScene().getWindow();
        stage.close();
    }
}
