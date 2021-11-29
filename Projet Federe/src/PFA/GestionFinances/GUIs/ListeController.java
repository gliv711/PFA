package PFA.GestionFinances.GUIs;

import PFA.GestionFinances.Module.Operation;
import PFA.GestionFinances.Service.OperationServices;
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
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListeController implements Initializable {
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    private Button supprimerButton;
    
    @FXML
    private TableView<Operation> listeEve;
    
    @FXML
    private TableColumn<Operation, String> nomColumn;
    
    @FXML
    private TableColumn<Operation, String> typeColumn;
    
    @FXML
    private TableColumn<Operation, Float> valeurColumn;
    
    @FXML
    private TableColumn<Operation, String> dateColumn;
    
    @FXML
    private TextField tfrecherche;
    
    @FXML
    private Button rechercheButton;
    
    @FXML
    private Button refreshButton;
    
    @FXML
    private RadioButton depenseButton;
    
    @FXML
    private RadioButton revenueButton;
    
    private ArrayList<Operation> liste;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        valeurColumn.setCellValueFactory(new PropertyValueFactory<>("valeur"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        liste = OperationServices.parseOperationList();
        listeEve.getItems().setAll(liste);
        
        listeEve.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && !listeEve.getSelectionModel().isEmpty()) {
                supprimerButton.setDisable(false);
            }
        });
        tfrecherche.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                listeEve.getSelectionModel().clearSelection();
                supprimerButton.setDisable(true);
            }
        });
        pane.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                listeEve.getSelectionModel().clearSelection();
                supprimerButton.setDisable(true);
            }
        });
        rechercheButton.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                listeEve.getSelectionModel().clearSelection();
                supprimerButton.setDisable(true);
            }
        });
        refreshButton.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                listeEve.getSelectionModel().clearSelection();
                supprimerButton.setDisable(true);
            }
        });
        
        depenseButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                revenueButton.setSelected(false);
                listeEve.getItems().setAll(OperationServices.parseOperationListByType("Depense"));
            } else if (oldValue && !revenueButton.isSelected()) {
                refresh();
            }
        });
        
        revenueButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                depenseButton.setSelected(false);
                listeEve.getItems().setAll(OperationServices.parseOperationListByType("Revenue"));
            } else if (oldValue && !depenseButton.isSelected()) {
                refresh();
            }
        });
        
    }
    
    public void retour() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../login_mainMenu/fxml/mainMenu.fxml"));
        Parent root = loader.load();
        Stage primaryStage = (Stage) listeEve.getScene().getWindow();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public void refresh() {
        liste = OperationServices.parseOperationList();
        listeEve.getItems().setAll(liste);
        depenseButton.setSelected(false);
        revenueButton.setSelected(false);
    }
    
    public void recherche() {
        if (!tfrecherche.getText().isEmpty())
            liste = OperationServices.recherche(tfrecherche.getText());
        else
            liste = OperationServices.parseOperationList();
        listeEve.getItems().setAll(liste);
    }
    
    public void supprimerButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/supprimer.fxml"));
        Parent root = loader.load();
        supprimerController controller = loader.getController();
        controller.setId(listeEve.getSelectionModel().getSelectedItem().getId());
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.showAndWait();
        refresh();
    }
    
    public void ajouterButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ajouteroperation.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.showAndWait();
        refresh();
    }
}
