package PFA.GestionIntervention.GUIs;


import PFA.GestionIntervention.Modules.Intervention;
import PFA.GestionIntervention.Services.InterventionServices;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ListeInterventionController {
    @FXML
    TableView<Intervention> listeIntervention;
    @FXML
    TableColumn<Intervention, String> nomColumn;
    @FXML
    TableColumn<Intervention, Date> dateDebutColumn;
    @FXML
    TableColumn<Intervention, Date> dateFinColumn;
    @FXML
    TableColumn<Intervention, String> adresseColumn;
    @FXML
    Button modifierButton, supprimerButton, detailsButton, refreshButton, rechercheButton;
    @FXML
    TextField rechercheTextField;
    @FXML
    AnchorPane pane;
    
    public void initListe() {
        ArrayList<Intervention> liste = new ArrayList<>(InterventionServices.parseInterventionListe());
        refreshListe(liste);
        listeIntervention.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && !listeIntervention.getSelectionModel().isEmpty()) {
                detailsButton.setDisable(false);
                modifierButton.setDisable(false);
                supprimerButton.setDisable(false);
            }
        });
        rechercheTextField.setOnMouseClicked((MouseEvent event) -> {
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
    
    public void refreshListe(List<Intervention> parseInterventionListe) {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("dateBedut"));
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        
        listeIntervention.getItems().setAll(parseInterventionListe);
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
    
    
    public void detailsbutton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/detailsIntervention.fxml"));
        Parent root = loader.load();
        detailsController controller = loader.getController();
        controller.initData(listeIntervention.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void modifierButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/modifierIntervention.fxml"));
        Parent root = loader.load();
        ModifierInterventionController controller = loader.getController();
        controller.initData(listeIntervention.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.showAndWait();
        refreshListe(InterventionServices.parseInterventionListe());
    }
    
    public void supprimerButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/suppressionIntervention.fxml"));
        Parent root = loader.load();
        supprimerInterventionController controller = loader.getController();
        controller.id = listeIntervention.getSelectionModel().getSelectedItem().getId();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.showAndWait();
        refreshListe(InterventionServices.parseInterventionListe());
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
        refreshListe(InterventionServices.Recherche(rechercheTextField.getText()));
    }
    
    public void refresh() {
        refreshListe(InterventionServices.parseInterventionListe());
    }
    
    public void generate(ActionEvent event) {
    }
}
