package PFA.GestionDoleances.GUIs;
import PFA.GestionDoleances.Module.ModuleDoleance;
import PFA.GestionDoleances.Service.DoleanceService;
import PFA.dbConnection.dbConnection;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;

import java.util.Objects;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class ListeDolController implements Initializable{
    @FXML
    Button retour,actualiser,recherche,ajouter,modifier,supprimer,details;
    @FXML
    TableView <ModuleDoleance> tvdol;
    @FXML
    TableColumn <ModuleDoleance,Integer> colid;
    @FXML
    TableColumn<ModuleDoleance,String> colsujet;
    @FXML
    TableColumn<ModuleDoleance,String> colnom;
    @FXML
    TableColumn<ModuleDoleance,Integer> colcin;
    @FXML
    AnchorPane anchro;
    @FXML
    TextField tfrecherche;

    public void initialize(URL url, ResourceBundle resource){
        refresh();
        anchro.setOnMouseClicked((MouseEvent event)->{
            if (event.getButton().equals(MouseButton.PRIMARY)){
                modifier.setDisable(true);
                supprimer.setDisable(true);
                details.setDisable(true);
                tvdol.getSelectionModel().clearSelection();
            }
        });

        tfrecherche.setOnMouseClicked((MouseEvent event)->{
            if (event.getButton().equals(MouseButton.PRIMARY)){
                modifier.setDisable(true);
                supprimer.setDisable(true);
                details.setDisable(true);
                tvdol.getSelectionModel().clearSelection();
            }
        });

        tvdol.setOnMouseClicked((MouseEvent event)->{
            if (event.getButton().equals(MouseButton.PRIMARY) && !tvdol.getSelectionModel().isEmpty()){
                modifier.setDisable(false);
                supprimer.setDisable(false);
                details.setDisable(false);
            }
        });
    }

    private List<ModuleDoleance> DoleanceListe() {
        List<ModuleDoleance> data = new ArrayList<>();
        String SQLquery = "SELECT * from doleance ";
        try {
            Connection connection = dbConnection.getOracleConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SQLquery);
            while (rs.next()) {
                data.add(new ModuleDoleance(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getInt("cin"),
                        rs.getString("addresse"),
                        rs.getInt("iddoleance"),
                        rs.getString("sujet"),
                        rs.getString("descreption"),
                        rs.getDate("datedoleance").toLocalDate(),
                        rs.getInt("numtel")));

            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;

    }

    private void refresh(){
        colid.setCellValueFactory(new PropertyValueFactory<>("IDdoleance"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colsujet.setCellValueFactory(new PropertyValueFactory<>("Sujet"));
        colcin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        tvdol.getItems().setAll(DoleanceListe());
    }

    public void switchToAjouter() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUIs/Fxmls/AjouterDoleance.fxml"));
        Parent rt = loader.load();
        Stage stage1 = new Stage();
        stage1.setTitle("Ajouter une Doleance");
        Scene scene1 = new Scene(rt);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene1);
        stage1.setScene(scene1);
        //stage1.show();
        stage1.setResizable(false);
        stage1.showAndWait();
        refresh();


    }

    public void switchToModifier() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUIs/Fxmls/ModiferDoleance.fxml"));
        Parent rt = loader.load();
        ModifierDoleanceController controller = loader.getController();
        controller.initData(tvdol.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        stage.setTitle("Modifier une Doleance");
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUIs/Fxmls/DetailsDoleance.fxml"));
        Parent rt = loader.load();
        DetailsDoleanceController controller = loader.getController();
        controller.initData(tvdol.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        stage.setTitle("Details d'une Doleance");
        Scene scene = new Scene(rt);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
        refresh();
    }

    public void switchToSupprimer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUIs/Fxmls/SupprimerDoleance.fxml"));
        Parent rt = loader.load();
        SupprimerDoleanceController controller = loader.getController();
        controller.iddoleance = tvdol.getSelectionModel().getSelectedItem().getIDdoleance();
        Stage stage1 = new Stage();
        stage1.setTitle("Supprimer une Doleance");
        Scene scene1 = new Scene(rt);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene1);
        stage1.setScene(scene1);
        stage1.setResizable(false);
        stage1.showAndWait();
        refresh();
    }

    public void rechercheButton(ActionEvent event){
        if (event.getSource()==recherche && !tfrecherche.getText().isEmpty()) {
            colid.setCellValueFactory(new PropertyValueFactory<>("IDdoleance"));
            colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colsujet.setCellValueFactory(new PropertyValueFactory<>("Sujet"));
            colcin.setCellValueFactory(new PropertyValueFactory<>("cin"));
            tvdol.getItems().setAll(DoleanceService.Recherche(tfrecherche.getText()));
        }
        if (event.getSource()==recherche && tfrecherche.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("echec de recherche");
            alert.setHeaderText(null);
            alert.setContentText("svp entrer un id pour faite la recherche");
            alert.show();
        }
    }

    public void RefreshButton1(ActionEvent event) throws IOException{
        if(event.getSource()==actualiser) {
            refresh();
        }
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
