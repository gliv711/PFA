package PFA.MaterielFiras.GUIsMateriel;

import PFA.MaterielFiras.ModuleMateriel.Outil;
import PFA.MaterielFiras.ServiceMateriel.Outils;
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


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class ListeOutilsController implements Initializable {

    @FXML
    Button boutajouter,boutmodifier,boutactualiser,boutsupprimer,boutrecherche,boutretour;
    @FXML
    TableView<Outil> tvoutils;
    @FXML
    TableColumn<Outil,Integer> coulid;
    @FXML
    TableColumn<Outil,String> coulnom;
    @FXML
    TableColumn<Outil,Integer> coulquantité;
    @FXML
    Label lboutilstitle;
    @FXML
    AnchorPane anchro;
    @FXML
    TextField toufrecherche;

    public void initialize(URL url, ResourceBundle resource){
        refresh();
        tvoutils.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        anchro.setOnMouseClicked((MouseEvent event)->{
            if (event.getButton().equals(MouseButton.PRIMARY)){
                boutmodifier.setDisable(true);
                boutsupprimer.setDisable(true);
                tvoutils.getSelectionModel().clearSelection();
            }
        });
    
        toufrecherche.setOnMouseClicked((MouseEvent event)->{
            if (event.getButton().equals(MouseButton.PRIMARY)){
                boutmodifier.setDisable(true);
                boutsupprimer.setDisable(true);
                tvoutils.getSelectionModel().clearSelection();
            }
        });
    
        tvoutils.setOnMouseClicked((MouseEvent event)->{
            if (event.getButton().equals(MouseButton.PRIMARY) && !tvoutils.getSelectionModel().isEmpty()){
                boutmodifier.setDisable(false);
                boutsupprimer.setDisable(false);
            }
        });
    }

    
    public void switchToAjouter() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxmls/ajouterOutils.fxml"));
        Parent rt = loader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(rt);
        stage1.setScene(scene1);
        stage1.showAndWait();
        refresh();
    }

    public void rechercheButton(ActionEvent event){
        if (event.getSource()==boutrecherche && !toufrecherche.getText().isEmpty()) {
            coulid.setCellValueFactory(new PropertyValueFactory<>("id"));
            coulnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            coulquantité.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            tvoutils.getItems().setAll(Outils.Recherche(toufrecherche.getText()));
        }
        if (event.getSource()==boutrecherche && toufrecherche.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("echec de recherche");
            alert.setHeaderText(null);
            alert.setContentText("svp entrer un id , un prix , quantité ou un nom  pour faite la recherche");
            alert.show();
        }
    }


    public void retour(){

    }

    public void switchToChoix1(ActionEvent event) throws IOException {
        Parent root7 = FXMLLoader.load(getClass().getResource("Fxmls/ChoixVehiculeOutils.fxml"));
        Stage stage7 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene7 = new Scene(root7);
        stage7.setScene(scene7);
        stage7.show();
    }


    public void RefreshButton1(ActionEvent event) throws IOException{
        if(event.getSource()==boutactualiser) {
            refresh();
        }
    }
    
    private void refresh(){
        coulid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coulnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coulquantité.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        tvoutils.getItems().setAll(Outils.OutilsListe());
    }
    
    public void switchToModifier() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxmls/ModifierOutils.fxml"));
        Parent rt = loader.load();
        ModifierOutilsController controller = loader.getController();
        controller.initData(tvoutils.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        Scene scene = new Scene(rt);
        stage.setScene(scene);
        stage.showAndWait();
        refresh();
    }

    public void switchToSupprimer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxmls/SupprimerOutils.fxml"));
        Parent rt = loader.load();
        SupprimerOutilsController controller = loader.getController();
        controller.id = tvoutils.getSelectionModel().getSelectedItem().getId();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(rt);
        stage1.setScene(scene1);
        stage1.showAndWait();
        refresh();
    }


}

