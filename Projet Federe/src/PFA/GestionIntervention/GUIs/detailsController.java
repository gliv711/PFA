package PFA.GestionIntervention.GUIs;

import PFA.GestionIntervention.Modules.Intervention;
import PFA.GestionIntervention.Modules.OutilsUtilise;
import PFA.GestionIntervention.Modules.PersonnelMin;
import PFA.MaterielFiras.ModuleMateriel.Outil;
import PFA.MaterielFiras.ModuleMateriel.Vehicule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class detailsController {
    
    @FXML
    TableView<OutilsUtilise> OutilListe;
    @FXML
    TableColumn<OutilsUtilise, String> nomOutilColumn;
    @FXML
    TableColumn<OutilsUtilise, Integer> quantiteOutilColumn;
    @FXML
    Button retour;
    @FXML
    TableView<PersonnelMin> PersonnelListe;
    @FXML
    TableColumn<PersonnelMin, String> nomPersonnelColumn;
    @FXML
    TableColumn<PersonnelMin, String> prenomPersonnelColumn;
    @FXML
    TableColumn<PersonnelMin, String> postePersonnelColumn;
    
    @FXML
    TableView<Vehicule> VehiculeListe;
    @FXML
    TableColumn<Vehicule, String> nomVehiculeColumn;
    @FXML
    TableColumn<Vehicule, String> modelVehiculeColumn;
    @FXML
    TableColumn<Vehicule, Integer> matriculeVehiculeColumn;
    
    @FXML
    Label nomLabel,dateDebutLabel,dateFinLabel,adresseLabel,budgetLabel;
    
    public void initData(Intervention intervention) {
        //initializing outil liste
        nomOutilColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        quantiteOutilColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        OutilListe.getItems().setAll(intervention.getOutilsUtilises());
        for (OutilsUtilise o :intervention.getOutilsUtilises()){
            System.out.println(o.nom);
            System.out.println(o.quantite);
        }
        //intializing personnel liste
        nomPersonnelColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomPersonnelColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        postePersonnelColumn.setCellValueFactory(new PropertyValueFactory<>("poste"));
        PersonnelListe.getItems().setAll(intervention.getEquipe());
        //initializing vehicule liste
        nomVehiculeColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        modelVehiculeColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        matriculeVehiculeColumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        VehiculeListe.getItems().setAll(intervention.getVehicules());
        //initializing lables
        nomLabel.setText(intervention.getNom());
        adresseLabel.setText(intervention.getAdresse());
        budgetLabel.setText(String.valueOf(intervention.getBudget()));
        dateDebutLabel.setText(String.valueOf(intervention.getDateBedut()));
        dateFinLabel.setText(String.valueOf(intervention.getDateFin()));
    }
    
    public void listeOutilButton(){
        PersonnelListe.setVisible(false);
        OutilListe.setVisible(true);
        VehiculeListe.setVisible(false);
    }
    
    public void listeVehiculeButton(){
        PersonnelListe.setVisible(false);
        OutilListe.setVisible(false);
        VehiculeListe.setVisible(true);
    }
    
    public void listePersonnelButton(){
        VehiculeListe.setVisible(false);
        PersonnelListe.setVisible(true);
        OutilListe.setVisible(false);
    }
    
    public void retour() throws IOException {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }
    
}
