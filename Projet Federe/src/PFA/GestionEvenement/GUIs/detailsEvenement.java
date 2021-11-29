package PFA.GestionEvenement.GUIs;

import PFA.GestionEvenement.Modules.Evenement;
import PFA.GestionEvenement.Modules.OutillMater;
import PFA.GestionEvenement.Modules.Perso;
import PFA.GestionPersonnel.Modules.Personnel;
import PFA.MaterielFiras.ModuleMateriel.Vehicule;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class detailsEvenement {

    @FXML
    TableView<OutillMater> OutilListe;
    @FXML
    TableColumn<OutillMater, String> nomOutilColumn;
    @FXML
    TableColumn<OutillMater, Integer> quantiteOutilColumn;
    @FXML
    Button retour;
    @FXML
    TableView<Personnel> PersonnelListe;
    @FXML
    TableColumn<Personnel, String> nomPersonnelColumn;
    @FXML
    TableColumn<Personnel, String> prenomPersonnelColumn;
    @FXML
    TableColumn<Personnel, String> postePersonnelColumn;

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

    public void initData1(Evenement evenement) {
        //intializing personnel liste
        nomPersonnelColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomPersonnelColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        postePersonnelColumn.setCellValueFactory(new PropertyValueFactory<>("poste"));
        PersonnelListe.getItems().setAll(evenement.getEquipeEve());
        //initializing vehicule liste
        nomVehiculeColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        modelVehiculeColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        matriculeVehiculeColumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        VehiculeListe.getItems().setAll(evenement.getVehiculesEve());
        //initializing lables
        nomLabel.setText(evenement.getNomEve());
        adresseLabel.setText(evenement.getAdresseEve());
        budgetLabel.setText(String.valueOf(evenement.getBudgetEve()));
        dateDebutLabel.setText(String.valueOf(evenement.getDateBedutEve()));
        dateFinLabel.setText(String.valueOf(evenement.getDateFinEve()));
        //initializing outil liste
        nomOutilColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        quantiteOutilColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        OutilListe.getItems().setAll(evenement.getOutilsUtilisEve());
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

    public void retour9() throws IOException {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }
}
