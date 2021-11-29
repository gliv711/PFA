package PFA.GestionProjet.GUIs.ajouterProjet;

import PFA.GestionIntervention.Services.InterventionServices;
import PFA.GestionProjet.Module.Projet;
import PFA.GestionProjet.Services.ProjetServices;
import PFA.GestionTache.GUIs.ListeTacheController;
import PFA.MaterielFiras.ModuleMateriel.Vehicule;
import PFA.MaterielFiras.ServiceMateriel.Vehicules;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ajouterVehicule {
    private Projet projet;
    @FXML
    private TableView<Vehicule> listeVehicule;
    
    @FXML
    private TableColumn<Vehicule, String> nomColumn;
    
    @FXML
    private TableColumn<Vehicule, String> modelColumn;
    
    @FXML
    private TableColumn<Vehicule, Integer> MatriculeColumn;
    
    @FXML
    private TableColumn<?, ?> selectColumn;
    
    public Projet getProjet() {
        return projet;
    }
    
    public void setProjet(Projet projet) {
        this.projet = projet;
    }
    
    public void retour(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouteroutil.fxml"));
        Parent root = loader.load();
        ajouterOutil controller = loader.getController();
        projet.getOutils().clear();
        controller.setProjet(projet);
        controller.initData();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void ajouter(ActionEvent actionEvent) {
        ArrayList<Vehicule> toAdd = new ArrayList<>();
        for (Vehicule vehicule : listeVehicule.getItems()){
            if (vehicule.getCheck().isSelected()){
                toAdd.add(vehicule);
            }
        }
        projet.setVehicules(toAdd);
        ProjetServices.ajouter(projet);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("projet ajoute");
        alert.showAndWait();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void initData() {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        MatriculeColumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("check"));
        ArrayList<Vehicule> liste = InterventionServices.parseVehiculeList(projet.getDateBedut());
        for (Vehicule vehicule : liste) {
            vehicule.setCheck(new CheckBox());
        }
        listeVehicule.getItems().setAll(liste);
    }
}
