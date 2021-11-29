package PFA.login_mainMenu;


import PFA.GestionIntervention.GUIs.ListeInterventionController;
import PFA.GestionPersonnel.GUIs.ListePersonnelController;
import PFA.GestionProjet.GUIs.ListeProjetController;
import PFA.ModifierInfo.GUIs.ModiController;
import PFA.ModifierInfo.Module.information;
import PFA.ModifierInfo.Services.InfoServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private Stage stage;
    private Scene scene;
    
    
    @FXML
    private Button materiel;
    
    @FXML
    private Button personnel;
    
    @FXML
    private Button intervention;
    
    @FXML
    private Button doleance;
    
    @FXML
    private Button finance;
    
    @FXML
    private Button evenement;
    
    @FXML
    private Button demande;
    
    @FXML
    private Button compte;
    
    @FXML
    private Button projet;
    
    @FXML
    private Button modifier;
    
    @FXML
    private Button rapportact;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logout.setImage(new Image("PFA/resources/logout.png"));
        logo.setImage(new Image("PFA/resources/logo.png"));
        information info = InfoServices.fetchInfo();
        adresse.setText(info.getAdresse());
        adresseemail.setText(info.getEmail());
        telephone.setText(info.getTelephone());
        nom.setText(info.getNom());
        if (login.getRole().equals("Agent RH")){
            rapportact.setDisable(true);
            modifier.setDisable(true);
            compte.setDisable(true);
        }else if (login.getRole().equals("Agent Guichet")){
            compte.setDisable(true);
            modifier.setDisable(true);
            rapportact.setDisable(true);
            projet.setDisable(true);
            evenement.setDisable(true);
            intervention.setDisable(true);
            personnel.setDisable(true);
            finance.setDisable(true);
        }
    }
    
    public void switchToIntervention(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionIntervention/GUIs/fxml/listeIntervention.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ListeInterventionController controller = loader.getController();
        controller.initListe();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToPersonnel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionPersonnel/GUIs/fxml/listepersonnel.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("PFA/resources/style.css")).toExternalForm());
        ListePersonnelController controller = loader.getController();
        controller.initListe();
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToMateriel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../MaterielFiras/GUIsMateriel/Fxmls/ChoixVehiculeOutils.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToDoleance(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionDoleances/GUIs/Fxmls/ListeDoleance.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToCompte() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionCompte/GUIs/fxml/listecompte.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        JMetro jMetro = new JMetro(Style.LIGHT);
        Scene scene = new Scene(root);
        jMetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }
    
    public void switchToDemande(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionDemandes/GUIs/fxml/ListeDemande.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToEvenement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionEvenement/GUIs/fxml/ListeEvenement.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToProjet(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionProjet/GUIs/fxml/listeprojet.fxml"));
        Parent root = loader.load();
        ListeProjetController controller = loader.getController();
        controller.initDate();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToOperation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionFinances/GUIs/fxml/listeoperation.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void logout(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    ImageView logout;
    @FXML
    private ImageView logo;
    @FXML
    Label adresse;
    @FXML
    Label adresseemail;
    @FXML
    Label telephone;
    @FXML
    Label nom;
    

    
    
    public void refreshInfo(){
        information info = InfoServices.fetchInfo();
        adresse.setText(info.getAdresse());
        adresseemail.setText(info.getEmail());
        telephone.setText(info.getTelephone());
        nom.setText(info.getNom());
    }
    
    public void modifierInformation() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ModifierInfo/GUIs/fxml/modifier.fxml"));
        Parent root = loader.load();
        ModiController controller = loader.getController();
        controller.setInfo(new information(adresse.getText(),adresseemail.getText(),telephone.getText(),nom.getText()));
        controller.init();
        stage = new Stage();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.showAndWait();
        refreshInfo();
    }
    
    public void switchToRapportAct() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../RapportActivite/fxml/rapport.fxml"));
        Parent root = loader.load();
        stage = new Stage();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
}
