package PFA.GestionCompte.GUIs;

import PFA.GestionCompte.Modules.Compte;
import PFA.GestionCompte.Services.compteServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListeCompteController implements Initializable {
    
    @FXML
    private Button ajouterButton;
    
    @FXML
    private Button detailsButton;
    
    @FXML
    private Button modifierButton;
    
    @FXML
    private Button supprimerButton;
    
    @FXML
    private TableView<Compte> compteTableView;
    
    @FXML
    private TableColumn<Compte, String> nomColumn;
    
    @FXML
    private TableColumn<Compte, String> roleColumn;
    
    @FXML
    private TextField rechercheTextField;
    
    @FXML
    private Button rechercheButton;
    
    @FXML
    private Button refreshButton;
    
    @FXML
    private Button retourButton;
    
    @FXML
    private AnchorPane pane;
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshListe(compteServices.ParseCompteListe());
    
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
    
    public void refreshListe(ArrayList<Compte> liste){
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nomUtilisateur"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        compteTableView.getItems().setAll(liste);
    }
    
    public void actualiser() {
        refreshListe(compteServices.ParseCompteListe());
    }
    
    public void recherche() {
        refreshListe(compteServices.recherche(rechercheTextField.getText()));
    }
    
    public void retour() throws IOException {
        Stage stage = (Stage) refreshButton.getScene().getWindow();
        stage.close();
    }
    
    public void ajouterButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ajoutercompte.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        refreshListe(compteServices.ParseCompteListe());
    }
    
    public void detailsButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/detailscompte.fxml"));
        Parent root = loader.load();
        detailsCompteController controller = loader.getController();
        controller.initData(compteTableView.getSelectionModel().getSelectedItem());
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void supprimerButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/suppression.fxml"));
        Parent root = loader.load();
        supprimerCompteController controller = loader.getController();
        controller.id = compteTableView.getSelectionModel().getSelectedItem().getId();
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        refreshListe(compteServices.ParseCompteListe());
    }
    
    public void modifierButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/modifiercompte.fxml"));
        Parent root = loader.load();
        modifierCompteController controller = loader.getController();
        controller.initData(compteTableView.getSelectionModel().getSelectedItem());
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        refreshListe(compteServices.ParseCompteListe());
    }
}
