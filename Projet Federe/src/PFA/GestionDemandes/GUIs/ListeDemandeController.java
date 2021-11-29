package PFA.GestionDemandes.GUIs;
import PFA.GestionDemandes.Module.DemandeModu;
import PFA.GestionDemandes.Service.DemandeServ;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class ListeDemandeController implements Initializable{
    @FXML
    AnchorPane anchro;
    @FXML
    TableView<DemandeModu> tvdem;
    @FXML
    TableColumn<DemandeModu,Integer> colid;
    @FXML
    TableColumn<DemandeModu,Integer> colcin;
    @FXML
    TableColumn<DemandeModu,String> colprenom;
    @FXML
    TableColumn<DemandeModu,String> colnom;
    @FXML
    Button ajouter,details,retour,recherche,actualiser,modifier,supprimer;
    @FXML
    TextField tfrecherche;

    public void initialize(URL url, ResourceBundle resource){
        refresh();
        anchro.setOnMouseClicked((MouseEvent event)->{
            if (event.getButton().equals(MouseButton.PRIMARY)){
                modifier.setDisable(true);
                supprimer.setDisable(true);
                details.setDisable(true);
                tvdem.getSelectionModel().clearSelection();
            }
        });

        tfrecherche.setOnMouseClicked((MouseEvent event)->{
            if (event.getButton().equals(MouseButton.PRIMARY)){
                modifier.setDisable(true);
                supprimer.setDisable(true);
                details.setDisable(true);
                tvdem.getSelectionModel().clearSelection();
            }
        });

        tvdem.setOnMouseClicked((MouseEvent event)->{
            if (event.getButton().equals(MouseButton.PRIMARY) && !tvdem.getSelectionModel().isEmpty()){
                modifier.setDisable(false);
                supprimer.setDisable(false);
                details.setDisable(false);
            }
        });
    }

    private List<DemandeModu> DemandeListe() {
        List<DemandeModu> data = new ArrayList<>();
        String SQLquery = "SELECT * from dem ";
        try {
            Connection connection = getOracleConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SQLquery);
            while (rs.next()) {
                data.add(new DemandeModu(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getInt("cin"),
                        rs.getString("adresse"),
                        rs.getInt("numtel"),
                        rs.getInt("id"),
                        rs.getDate("datedem").toLocalDate(),
                        rs.getString("typedem"),
                        rs.getString("descreption")));

            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;

    }

    private void refresh(){
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colcin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        tvdem.getItems().setAll(DemandeListe());
    }


    public void switchToAjouter() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/AjouterDemande.fxml"));
        Parent rt = loader.load();
        Stage stage1 = new Stage();
        stage1.setTitle("Ajouter une Demande");
        Scene scene1 = new Scene(rt);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene1);
        stage1.setScene(scene1);
        stage1.setResizable(false);
        stage1.showAndWait();
        refresh();


    }

    public void switchToModifier() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ModifierDemande.fxml"));
        Parent rt = loader.load();
        ModifierDemandeController controller = loader.getController();
        controller.initData(tvdem.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        stage.setTitle("Modifier une Demande");
        Scene scene = new Scene(rt);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
        refresh();
    }

    public void retour(){

    }

    public void switchToDetails() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/DetailsDemande.fxml"));
        Parent rt = loader.load();
        DetailsDemandeController controller = loader.getController();
        controller.initData(tvdem.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        stage.setTitle("Details d'une Demande");
        Scene scene = new Scene(rt);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
        refresh();
    }
    public void rechercheButton(ActionEvent event){
        if (event.getSource()==recherche && !tfrecherche.getText().isEmpty()) {
            colid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            colcin.setCellValueFactory(new PropertyValueFactory<>("cin"));
            tvdem.getItems().setAll(DemandeServ.Recherche(tfrecherche.getText()));
        }
        if (event.getSource()==recherche && tfrecherche.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Echec de recherche");
            alert.setHeaderText(null);
            alert.setContentText("Svp entrer un nom ou un prenom pour faite la recherche");
            alert.show();
        }
    }

    public void RefreshButton1(ActionEvent event) throws IOException{
        if(event.getSource()==actualiser) {
            refresh();
        }
    }

    public void switchToSupprimer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/SupprimerDemande.fxml"));
        Parent rt = loader.load();
        SupprimerDemandeController controller = loader.getController();
        controller.id = tvdem.getSelectionModel().getSelectedItem().getId();
        Stage stage1 = new Stage();
        stage1.setTitle("Supprimer une Demande");
        Scene scene1 = new Scene(rt);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene1);
        stage1.setScene(scene1);
        stage1.setResizable(false);
        stage1.showAndWait();
        refresh();
    }

    public void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("../../login_mainMenu/fxml/mainMenu.fxml")));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }

}
