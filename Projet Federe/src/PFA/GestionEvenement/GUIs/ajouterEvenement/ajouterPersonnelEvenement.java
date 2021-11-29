package PFA.GestionEvenement.GUIs.ajouterEvenement;

import PFA.GestionEvenement.Modules.Evenement;
import PFA.GestionIntervention.Services.InterventionServices;
import PFA.GestionPersonnel.Modules.Personnel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ajouterPersonnelEvenement{
    private Evenement evenement;

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    @FXML
    private TableView<Personnel> PersonnelTV;

    @FXML
    private TableColumn<Personnel, String> nomColumn;

    @FXML
    private TableColumn<Personnel, String> prenomColumn;

    @FXML
    private TableColumn<Personnel, String> posteColumn;

    @FXML
    private TableColumn<Personnel, CheckBox> selectColumn;

    public void retour1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouterGeneralEvenement.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToVehicule(ActionEvent event) throws IOException {
        ArrayList<Personnel> liste = new ArrayList<>(PersonnelTV.getItems());
        ArrayList<Personnel> toAdd = new ArrayList<>();
        for (Personnel p : liste) {
            if (p.getCheck().isSelected()) {
                toAdd.add(p);
            }
        }
        evenement.setEquipeEve(toAdd);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouterVehiculeEvenement.fxml"));
        Parent root = loader.load();
        ajouterVehiculeEvenement controller = loader.getController();
        controller.setEvenement(evenement);
        controller.initData();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initData() {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        posteColumn.setCellValueFactory(new PropertyValueFactory<>("poste"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("check"));
        ArrayList<Personnel> liste = InterventionServices._ParsePersonnelListe(evenement.getDateBedutEve());
        for (Personnel p:liste){
            p.setCheck(new CheckBox());
        }
        PersonnelTV.getItems().setAll(liste);
    }
}
