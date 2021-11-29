package PFA.GestionPersonnel.GUIs;

import PFA.GestionPersonnel.Modules.Personnel;
import PFA.GestionPersonnel.Services.PersonnelServices;
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
import java.util.List;
import java.util.Objects;

public class ListePersonnelController {
    @FXML
    private TableView<Personnel> ListePersonnelTable;
    @FXML
    private TableColumn<Personnel, String> nomColumn;
    @FXML
    private TableColumn<Personnel, String> prenomColumn;
    @FXML
    private TableColumn<Personnel, Integer> CINColumn;
    @FXML
    private TableColumn<Personnel, String> posteColumn;
    @FXML
    private Button detailsButton, modifierButton, supprimerButton;
    @FXML
    private TextField rechercheTextField;
    @FXML
    private AnchorPane ListePersonnel;
    
    public void refreshList(List<Personnel> e){
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        CINColumn.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        posteColumn.setCellValueFactory(new PropertyValueFactory<>("poste"));
    
        ListePersonnelTable.getItems().setAll(e);
    }

    public void initListe() {
        refreshList(PersonnelServices.ParsePersonnelListe());

        ListePersonnelTable.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY) && !ListePersonnelTable.getSelectionModel().isEmpty()){
                detailsButton.setDisable(false);
                modifierButton.setDisable(false);
                supprimerButton.setDisable(false);
            }
        });
        rechercheTextField.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                ListePersonnelTable.getSelectionModel().clearSelection();
                detailsButton.setDisable(true);
                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
            }
        });
        ListePersonnel.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                ListePersonnelTable.getSelectionModel().clearSelection();
                detailsButton.setDisable(true);
                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
            }
        });

    }


    public void detailsButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/detailspersonnel.fxml"));
        Parent rt = loader.load();
        DetailsPersonnelController cntrl = loader.getController();
        cntrl.initData(ListePersonnelTable.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        Scene scene = new Scene(rt);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }


  

    public void rechercheButton(){
        refreshList(PersonnelServices.Recherche(rechercheTextField.getText()));
    }

    public void switchToAjouter() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ajouterpersonnel.fxml"));
        Parent rt = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(rt);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.showAndWait();
        refreshList(PersonnelServices.ParsePersonnelListe());
    }


    public void switchToModifier() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/modifierpersonnel.fxml"));
        Parent rt = loader.load();
        ModifierPersonnelController controller = loader.getController();
        controller.initData(ListePersonnelTable.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        Scene scene = new Scene(rt);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.showAndWait();
        refreshList(PersonnelServices.ParsePersonnelListe());
    }


    public void SuprrimerButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/supression.fxml"));
        Parent root = loader.load();
        SupprimerPersonnelController controller = loader.getController();
        controller.setP(ListePersonnelTable.getSelectionModel().getSelectedItem());
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        refreshList(PersonnelServices.ParsePersonnelListe());
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
    
    public void refresh(ActionEvent event) {
        refreshList(PersonnelServices.ParsePersonnelListe());
    }
}
