package PFA.GestionEvenement.GUIs.ajouterEvenement;

import PFA.GestionEvenement.Modules.Evenement;
import PFA.GestionEvenement.Modules.OutillMater;
import PFA.GestionEvenement.Services.EvenementServ;
import PFA.GestionIntervention.Services.InterventionServices;
import PFA.MaterielFiras.ModuleMateriel.Outil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ajouterOutilEvenement{
    public Evenement getEvenement() {
        return evenement;
    }
    
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
    
    private Evenement evenement;
    @FXML
    private TableView<Outil> outilTV;

    @FXML
    Button ajouter;

    @FXML
    private TableColumn<Outil, String> nomColumn;

    @FXML
    private TableColumn<Outil, String> conumableColumn;

    @FXML
    private TableColumn<Outil, Integer> quantiteColumn;

    @FXML
    private TableColumn<Outil, Spinner<Integer>> spinnerColumn;


    
    public void initData() {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        conumableColumn.setCellValueFactory(new PropertyValueFactory<>("consumable"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        spinnerColumn.setCellValueFactory(new PropertyValueFactory<>("spinner"));
        ArrayList<Outil> liste = InterventionServices.parseOutilList(evenement.getDateBedutEve());
        for (Outil o : liste) {
            o.setSpinner(new Spinner<Integer>());
            SpinnerValueFactory<Integer> valueFactory = //
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, o.getQuantite(), 0);
            o.getSpinner().setValueFactory(valueFactory);
        }
        outilTV.getItems().setAll(liste);
    }

    public void retour1(ActionEvent event) {

    }

    public void ajouterO(ActionEvent event) {
        ArrayList<OutillMater> toAdd = new ArrayList<>();
        ArrayList<Outil> liste = new ArrayList<>(outilTV.getItems());
        for (Outil o : liste){
            if (o.getSpinner().getValue() > 0){
                toAdd.add(new OutillMater(o,o.getSpinner().getValue()));
            }
        }
        if(!toAdd.isEmpty()){
            evenement.setOutilsUtilisEve(toAdd);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Aucun Outil Selectione");
            alert.showAndWait();
            return ;
        }
        EvenementServ.Ajouter(evenement);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("Evenement ajoute");
        alert.showAndWait();
        Stage stage = (Stage) ajouter.getScene().getWindow();
        stage.close();

    }
}
