package PFA.GestionProjet.GUIs.ajouterProjet;

import PFA.GestionIntervention.Services.InterventionServices;
import PFA.GestionPersonnel.Modules.Personnel;
import PFA.GestionPersonnel.Services.PersonnelServices;
import PFA.GestionProjet.Module.Projet;
import PFA.GestionTache.GUIs.ListeTacheController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ajouterPersonnel {
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
    private Projet projet;
    
    public Projet getProjet() {
        return projet;
    }
    
    public void setProjet(Projet projet) {
        this.projet = projet;
    }
    
    public void retour(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../GestionTache/GUIs/fxml/listetache.fxml"));
        Parent root = loader.load();
        ListeTacheController controller = loader.getController();
        projet.getTaches().clear();
        controller.setProjet(projet);
        controller.initData();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToOutil(ActionEvent actionEvent) throws IOException {
        ArrayList<Personnel> toAdd = new ArrayList<>();
        for (Personnel p : PersonnelTV.getItems()) {
            if (p.getCheck().isSelected()) {
                toAdd.add(p);
            }
        }
        projet.setEquipe(toAdd);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouteroutil.fxml"));
        Parent root = loader.load();
        ajouterOutil controller = loader.getController();
        controller.setProjet(projet);
        controller.initData();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void initData() {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        posteColumn.setCellValueFactory(new PropertyValueFactory<>("poste"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("check"));
        ArrayList<Personnel> liste =  InterventionServices._ParsePersonnelListe(projet.getDateBedut());
        for (Personnel p : liste)
            p.setCheck(new CheckBox());
        PersonnelTV.getItems().setAll(liste);
    }
}
