package PFA.GestionProjet.GUIs.ajouterProjet;

import PFA.GestionIntervention.Services.InterventionServices;
import PFA.GestionProjet.Module.Projet;
import PFA.MaterielFiras.ModuleMateriel.Outil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.util.ArrayList;

public class ajouterOutil {
    
    @FXML
    private TableView<Outil> outilTV;
    
    @FXML
    private TableColumn<Outil, ?> nomColumn;
    
    @FXML
    private TableColumn<Outil, String> conumableColumn;
    
    @FXML
    private TableColumn<Outil, Integer> quantiteColumn;
    
    @FXML
    private TableColumn<Outil, Spinner<Integer>> spinnerColumn;
    
    
    private Projet projet;
    
    public Projet getProjet() {
        return projet;
    }
    
    public void setProjet(Projet projet) {
        this.projet = projet;
    }
    
    public void retour(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouterpersonnel.fxml"));
        Parent root = loader.load();
        ajouterPersonnel controller = loader.getController();
        projet.getEquipe().clear();
        controller.setProjet(projet);
        controller.initData();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToVehicule(ActionEvent actionEvent) throws IOException {
        ArrayList<Outil> toAdd = new ArrayList<>();
        for (Outil outil:outilTV.getItems()){
            if (outil.getSpinner().getValue() != 0){
                outil.setQuantite(outil.getSpinner().getValue());
                toAdd.add(outil);
            }
        }
        projet.setOutils(toAdd);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajoutervehicule.fxml"));
        Parent root = loader.load();
        ajouterVehicule controller = loader.getController();
        controller.setProjet(projet);
        controller.initData();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void initData(){
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        conumableColumn.setCellValueFactory(new PropertyValueFactory<>("consumable"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        spinnerColumn.setCellValueFactory(new PropertyValueFactory<>("spinner"));
        ArrayList<Outil> liste = InterventionServices.parseOutilList(projet.getDateBedut());
        for (Outil outil:liste){
            outil.setSpinner(new Spinner<>());
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, outil.getQuantite(), 0);
            outil.getSpinner().setValueFactory(valueFactory);
        }
        outilTV.getItems().setAll(liste);
    }
}
