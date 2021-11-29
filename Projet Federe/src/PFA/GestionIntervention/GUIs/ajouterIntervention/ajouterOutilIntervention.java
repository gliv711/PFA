package PFA.GestionIntervention.GUIs.ajouterIntervention;

import PFA.GestionIntervention.Modules.Intervention;
import PFA.GestionIntervention.Modules.OutilsUtilise;
import PFA.GestionIntervention.Services.InterventionServices;
import PFA.MaterielFiras.ModuleMateriel.Outil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ajouterOutilIntervention {
    
    @FXML
    private TableView<Outil> outilTV;
    
    @FXML Button ajouter;
    
    @FXML
    private TableColumn<Outil, String> nomColumn;
    
    @FXML
    private TableColumn<Outil, String> conumableColumn;
    
    @FXML
    private TableColumn<Outil, Integer> quantiteColumn;
    
    @FXML
    private TableColumn<Outil, Spinner<Integer>> spinnerColumn;
    
    public Intervention getIntervention() {
        return intervention;
    }
    
    public void setIntervention(Intervention intervention) {
        this.intervention = intervention;
    }
    
    private Intervention intervention;
    
    
    public void initData(){
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        conumableColumn.setCellValueFactory(new PropertyValueFactory<>("consumable"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        spinnerColumn.setCellValueFactory(new PropertyValueFactory<>("spinner"));
        ArrayList<Outil> liste = new ArrayList<>(InterventionServices.parseOutilList(intervention.getDateBedut()));
        for (Outil o: liste){
            o.setSpinner(new Spinner<>());
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, o.getQuantite(), 0);
            o.getSpinner().setValueFactory(valueFactory);
        }
        outilTV.getItems().setAll(liste);
    
    }
    
    public void retour() {
    
    }
    
    public void ajouter() {
        ArrayList<OutilsUtilise> toAdd = new ArrayList<>();
        ArrayList<Outil> liste = new ArrayList<>(outilTV.getItems());
        for (Outil o : liste){
            if (o.getSpinner().getValue() > 0){
                toAdd.add(new OutilsUtilise(o,o.getSpinner().getValue()));
            }
        }
        if(!toAdd.isEmpty()){
            intervention.setOutilsUtilises(toAdd);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Aucun Outil Selectione");
            alert.showAndWait();
            return ;
        }
        InterventionServices.Ajouter(intervention);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("Intervention ajoute");
        alert.showAndWait();
        Stage stage = (Stage) ajouter.getScene().getWindow();
        stage.close();
        
    }
}

