package PFA.GestionDemandes.GUIs;


import PFA.GestionDemandes.Module.DemandeModu;
import PFA.GestionDemandes.Service.DemandeServ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AjouterDemandeController implements Initializable {
    @FXML
    TextField nom,prenom,cin,adresse,tel,tell;
    @FXML
    Label lbnom,lbprenom,lbcin,lbadresse,lbtel,lbdate,lbtype,remplir;
    @FXML
    TextArea descreption;
    @FXML
    ChoiceBox<String> type;
    @FXML
    Button boutajouter,retour;
    @FXML
    DatePicker date;
    
    private final String[] Type = {"Voirie", "Degradation", "Construction","Permission"};
    String nompattern = "[a-zA-Z]+";
    String prenompattern = "[a-zA-Z]*";
    String cinpattern = "[0-9]{8}";
    String adressepattern = "[a-zA-Z0-9,;. ]+";
    String telpattern = "[0-9]{8}";
    boolean verif = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.getItems().setAll(Type);

        nom.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbnom.setVisible(!Pattern.matches(nompattern, nom.getText()));
            }
        });

        type.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbtype.setVisible(type.getSelectionModel().isEmpty());
            }
        });

        prenom.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbprenom.setVisible(!Pattern.matches(prenompattern, prenom.getText()) );
            }
        });


        cin.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbcin.setVisible(!Pattern.matches(cinpattern, cin.getText())|| cin.getText().isEmpty());
            }
        });

        adresse.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbadresse.setVisible(!Pattern.matches(adressepattern, adresse.getText())|| adresse.getText().isEmpty());
            }
        });

        tel.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbtel.setVisible(!Pattern.matches(telpattern, tel.getText())|| tel.getText().isEmpty());
            }
        });
        tell.setEditable(false);

    }


    public void ajouterDem(ActionEvent event){
        if (nom.getText().isEmpty() || prenom.getText().isEmpty() || cin.getText().isEmpty() || adresse.getText().isEmpty() ||
                type.getSelectionModel().isEmpty() || tel.getText().isEmpty()) {
            remplir.setVisible(true);
            verif = false;
        }

        if (date.getValue().toString().isEmpty()) {
            lbdate.setVisible(true);
            verif = false;
        }

        if (verif && Pattern.matches(nompattern, nom.getText()) &&
                Pattern.matches(prenompattern, prenom.getText()) &&
                Pattern.matches(cinpattern, cin.getText()) &&
                Pattern.matches(adressepattern, adresse.getText()) &&
                Pattern.matches(telpattern, tel.getText()) &&
                !type.getSelectionModel().isEmpty() &&
                !date.getValue().toString().isEmpty() &&
                event.getSource() == boutajouter) {
            // make a new instance of demande to add
            DemandeModu p = new DemandeModu(nom.getText(),prenom.getText(),Integer.parseInt(cin.getText()),
                    adresse.getText(),Integer.parseInt(tel.getText()),date.getValue(),type.getValue(),
                    descreption.getText());
            

            //call service to add it to the database
            DemandeServ.Ajouter(p);
            Stage stage = (Stage) boutajouter.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("succés d'ajout");
            alert.setHeaderText(null);
            alert.setContentText("l'ajout est fait avec succés");
            alert.show();
        }
    }



    public void retour() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }


}

