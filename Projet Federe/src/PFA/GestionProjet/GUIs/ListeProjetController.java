package PFA.GestionProjet.GUIs;

import PFA.GestionProjet.Module.Projet;
import PFA.GestionProjet.Services.ProjetServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class ListeProjetController {
    
    @FXML
    AnchorPane pane;
    
    @FXML
    private Button ajouterButton;
    
    @FXML
    private Button detailsButton;
    
    @FXML
    private Button modifierButton;
    
    @FXML
    private Button supprimerButton;
    
    @FXML
    private TableView<Projet> listeProjet;
    
    @FXML
    private TableColumn<Projet, String> nomColumn;
    
    @FXML
    private TableColumn<Projet, LocalDate> dateBedutColumn;
    
    @FXML
    private TableColumn<Projet, LocalDate> dateFinColumn;
    
    @FXML
    private TableColumn<Projet, String> adresseColumn;
    
    @FXML
    private TextField rechercheTextFIeld;
    
    @FXML
    private Button rechercheButton;
    
    @FXML
    private Button refreshButton;
    
    public void refreshListe(ArrayList<Projet> projets){
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        dateBedutColumn.setCellValueFactory(new PropertyValueFactory<>("DateBedut"));
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        
        listeProjet.getItems().setAll(projets);
    }
    
    public void initDate(){
        ArrayList<Projet> liste = ProjetServices.parseProjetListe();
        refreshListe(liste);
        listeProjet.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && !listeProjet.getSelectionModel().isEmpty()) {
                detailsButton.setDisable(false);
                modifierButton.setDisable(false);
                supprimerButton.setDisable(false);
            }
        });
        rechercheTextFIeld.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                detailsButton.setDisable(true);
                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
            }
        });
        rechercheButton.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                detailsButton.setDisable(true);
                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
            }
        });
        pane.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                detailsButton.setDisable(true);
                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
            }
        });
        refreshButton.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                detailsButton.setDisable(true);
                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
            }
        });
    }
    
    public void ajouterButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ajouterGenerale.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
        
    }
    
    
    public void detailsButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/details.fxml"));
        Parent root = loader.load();
        detailsController controller = loader.getController();
        controller.initData(listeProjet.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void modifierButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/modifier.fxml"));
        Parent root = loader.load();
        modifierProjetController controller = loader.getController();
        controller.initData(listeProjet.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.showAndWait();
        refreshListe(ProjetServices.parseProjetListe());
    }
    
    public void supprimerButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/supprimer.fxml"));
        Parent root = loader.load();
        supprimerProjetController controller = loader.getController();
        controller.setId(listeProjet.getSelectionModel().getSelectedItem().getId());
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.showAndWait();
        refreshListe(ProjetServices.parseProjetListe());
    }
    
    public void retour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../login_mainMenu/fxml/mainMenu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    
    public void recherche() {
        refreshListe(ProjetServices.Recherche(rechercheTextFIeld.getText()));
    }
    
    public void refresh() {
        refreshListe(ProjetServices.parseProjetListe());
    }
    
    public void generate(ActionEvent event) {
    }
}
