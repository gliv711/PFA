package PFA.GestionTache.GUIs;


import PFA.GestionProjet.GUIs.ajouterProjet.ajouterPersonnel;
import PFA.GestionProjet.Module.Projet;
import PFA.GestionTache.Module.Tache;
import PFA.GestionTache.Services.tacheServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListeTacheController{
    @FXML
    private AnchorPane pane;
    
    @FXML
    private Button detailsButton;
    
    @FXML
    private Button modifierButton;
    
    @FXML
    private Button supprimerButton;
    
    @FXML
    private TableView<Tache> compteTableView;
    
    @FXML
    private TableColumn<Tache, String> nomColumn;
    
    @FXML
    private TableColumn<Tache, String> nomColumn1;
    
    @FXML
    private TextField rechercheTextField;
    
    @FXML
    private Button rechercheButton;
    
    @FXML
    private Button refreshButton;
    
    @FXML
    private TableColumn<Tache, CheckBox> selectColumn;
    
    @FXML
    private TableColumn<Tache , LocalDate> dateColumn;
    
    public void initData() {
        refresh(tacheServices.parsetacheListe(projet.getDateBedut(),projet.getDateFin()));
        
        compteTableView.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY) && !compteTableView.getSelectionModel().isEmpty()){
                detailsButton.setDisable(false);
                modifierButton.setDisable(false);
                supprimerButton.setDisable(false);
            }
        });
        rechercheTextField.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                compteTableView.getSelectionModel().clearSelection();
                detailsButton.setDisable(true);
                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
            }
        });
        pane.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                compteTableView.getSelectionModel().clearSelection();
                detailsButton.setDisable(true);
                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
            }
        });
        rechercheButton.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                compteTableView.getSelectionModel().clearSelection();
                detailsButton.setDisable(true);
                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
            }
        });
        refreshButton.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                compteTableView.getSelectionModel().clearSelection();
                detailsButton.setDisable(true);
                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
            }
        });
        
    }
    
    public void refresh(ArrayList<Tache> liste){
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomColumn1.setCellValueFactory(new PropertyValueFactory<>("nomPersonnel"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("check"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        for (Tache tache : liste){
            tache.setCheck(new CheckBox());
        }
        compteTableView.getItems().setAll(liste);
    }
    
    public void ajouterButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ajoutertache.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        ajouterTacheController controller = loader.getController();
        controller.initListe();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        refresh(tacheServices.parsetacheListe(projet.getDateBedut(),projet.getDateFin()));
    }
    
    public void detailsButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/detailstache.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        detailsTacheController controller = loader.getController();
        controller.initData(compteTableView.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        refresh(tacheServices.parsetacheListe(projet.getDateBedut(),projet.getDateFin()));
    }
    
    public void modifierButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/modifiertache.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        ModifierTacheController controller = loader.getController();
        controller.initListe(compteTableView.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        refresh(tacheServices.parsetacheListe(projet.getDateBedut(),projet.getDateFin()));
    }
    
    public void supprimerButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/suppression.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        supprimerTacheController controller = loader.getController();
        controller.setID(compteTableView.getSelectionModel().getSelectedItem().getId());
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        refresh(tacheServices.parsetacheListe(projet.getDateBedut(),projet.getDateFin()));
    }
    
    public void recherche() {
        refresh(tacheServices.recherche(rechercheTextField.getText()));
    }
    
    public void actualiser() {
        refresh(tacheServices.parsetacheListe(projet.getDateBedut(),projet.getDateFin()));
    }
    
    public void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../login_mainMenu/fxml/mainMenu.fxml"));
        Parent root = loader.load();
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Projet projet;
    
    public Projet getProjet() {
        return projet;
    }
    
    public void setProjet(Projet projet) {
        this.projet = projet;
    }
    
    public void switchToPersonnel(ActionEvent event) throws IOException {
        ArrayList<Tache> toAdd = new ArrayList<>();
        for (Tache tache : compteTableView.getItems()){
            if (tache.getCheck().isSelected()){
                toAdd.add(tache);
            }
        }
        projet.setTaches(toAdd);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../GestionProjet/GUIs/fxml/ajouterpersonnel.fxml"));
        Parent root = loader.load();
        ajouterPersonnel controller = loader.getController();
        controller.setProjet(projet);
        controller.initData();
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
