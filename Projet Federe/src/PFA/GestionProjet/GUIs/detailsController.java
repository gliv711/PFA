package PFA.GestionProjet.GUIs;

import PFA.GestionPersonnel.Modules.Personnel;
import PFA.GestionProjet.Module.Projet;
import PFA.GestionTache.Module.Tache;
import PFA.MaterielFiras.ModuleMateriel.Outil;
import PFA.MaterielFiras.ModuleMateriel.Vehicule;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class detailsController {
    @FXML
    private Button retour;
    
    @FXML
    private TableView<Personnel> PersonnelListe;
    
    @FXML
    private TableColumn<Personnel, String> nomPersonnelColumn;
    
    @FXML
    private TableColumn<Personnel, String> prenomPersonnelColumn;
    
    @FXML
    private TableColumn<Personnel, String> postePersonnelColumn;
    
    @FXML
    private TableView<Vehicule> VehiculeListe;
    
    @FXML
    private TableColumn<Vehicule, String> nomVehiculeColumn;
    
    @FXML
    private TableColumn<Vehicule, String> modelVehiculeColumn;
    
    @FXML
    private TableColumn<Vehicule, Integer> matriculeVehiculeColumn;
    
    @FXML
    private TableView<Outil> OutilListe;
    
    @FXML
    private TableColumn<Outil, String> nomOutilColumn;
    
    @FXML
    private TableColumn<Outil, Integer> quantiteOutilColumn;
    
    @FXML
    private Label dateDebutLabel;
    
    @FXML
    private Label dateFinLabel;
    
    @FXML
    private Label budgetLabel;
    
    @FXML
    private Label nomLabel;
    
    @FXML
    private Label adresseLabel;
    
    @FXML
    private TableView<Tache> tacheListe;
    
    @FXML
    private TableColumn<Tache, String> nomPColumn;
    
    @FXML
    private TableColumn<Tache, String> nomTacheColumn;
    
    public void retour() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }
    
    public void listePersonnelButton() {
        PersonnelListe.setVisible(true);
        OutilListe.setVisible(false);
        tacheListe.setVisible(false);
        VehiculeListe.setVisible(false);
    }
    
    public void listeVehiculeButton() {
        PersonnelListe.setVisible(false);
        OutilListe.setVisible(false);
        tacheListe.setVisible(false);
        VehiculeListe.setVisible(true);
    }
    
    public void listeOutilButton() {
        PersonnelListe.setVisible(false);
        OutilListe.setVisible(true);
        tacheListe.setVisible(false);
        VehiculeListe.setVisible(false);
    }
    
    public void listeTacheButton() {
        PersonnelListe.setVisible(false);
        OutilListe.setVisible(false);
        tacheListe.setVisible(true);
        VehiculeListe.setVisible(false);
    }
    
    public void initData(Projet selectedItem) {
        //setting up outil list
        nomOutilColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        quantiteOutilColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        OutilListe.getItems().setAll(selectedItem.getOutils());
        //setting up vehicule list
        nomVehiculeColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        modelVehiculeColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        matriculeVehiculeColumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        VehiculeListe.getItems().setAll(selectedItem.getVehicules());
        //setting up personnel list
        nomPersonnelColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomPersonnelColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        postePersonnelColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PersonnelListe.getItems().setAll(selectedItem.getEquipe());
        //setting up tache list
        for (Tache tache : selectedItem.getTaches()) {
            if (tache.getPersonnel() != null){
                tache.setNomPersonnel(tache.getPersonnel().getNom() + " " + tache.getPersonnel().getPrenom());
            }
        }
        nomTacheColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomPColumn.setCellValueFactory(new PropertyValueFactory<>("nomPersonnel"));
        tacheListe.getItems().setAll(selectedItem.getTaches());
        //setting up labels
        dateDebutLabel.setText(selectedItem.getDateBedut().toString());
        dateFinLabel.setText(selectedItem.getDateFin().toString());
        budgetLabel.setText(String.valueOf(selectedItem.getCout()));
        nomLabel.setText(selectedItem.getNom());
        adresseLabel.setText(selectedItem.getAdresse());
    }
}
