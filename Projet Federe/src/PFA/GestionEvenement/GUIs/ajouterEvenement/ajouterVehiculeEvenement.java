package PFA.GestionEvenement.GUIs.ajouterEvenement;

import PFA.GestionEvenement.Modules.Evenement;
import PFA.GestionIntervention.Services.InterventionServices;
import PFA.MaterielFiras.ModuleMateriel.Vehicule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.util.ArrayList;

public class ajouterVehiculeEvenement{


    public void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouterVehiculeEvenement.fxml"));
        Parent root = loader.load();
        ajouterVehiculeEvenement controller = loader.getController();
        controller.evenement = evenement;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToOutilEvenement(ActionEvent event) throws IOException {
        ArrayList<Vehicule> liste = new ArrayList<>(listeVehicule.getItems());
        ArrayList<Vehicule> toAdd = new ArrayList<>();
        for (Vehicule v : liste) {
            if (v.getCheck().isSelected())
                toAdd.add(v);
        }
        evenement.setVehiculesEve(toAdd);
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouterOutilEvenement.fxml"));
        Parent root = loader.load();
        ajouterOutilEvenement controller = loader.getController();
        controller.setEvenement(evenement);
        controller.initData();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    
    public Evenement getEvenement() {
        return evenement;
    }
    
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
    
    private Evenement evenement;

    @FXML
    private TableView<Vehicule> listeVehicule;

    @FXML
    private TableColumn<Vehicule, String> nomColumn;

    @FXML
    private TableColumn<Vehicule, String> modelColumn;

    @FXML
    private TableColumn<Vehicule, Integer> MatriculeColumn;
    @FXML
    private TableColumn<Vehicule, CheckBox> selectColumn;


    public void initData() {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        MatriculeColumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("check"));
        ArrayList<Vehicule> liste = InterventionServices.parseVehiculeList(evenement.getDateBedutEve());
        for (Vehicule v : liste){
            v.setCheck(new CheckBox());
        }
        listeVehicule.getItems().setAll(liste);


    }
}
