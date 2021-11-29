package PFA.GestionDoleances.GUIs;

import PFA.GestionDoleances.Module.ModuleDoleance;
import PFA.GestionDoleances.Service.DoleanceService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AjouterDoleanceController implements Initializable {
    @FXML
    TextField nom, prenom, cin, adresse, sujet,tel,tell;
    @FXML
    Label lbnom, lbprenom, lbcin, lbadresse, lbsujet, lbdate, lbid ,lbtel,remplir;
    @FXML
    Button boutajouter, retour;
    @FXML
    DatePicker date;
    @FXML
    TextArea descreption;


    String nompattern = "[a-zA-Z]*";
    String prenompattern = "[a-zA-Z]*";
    String cinpattern = "[0-9]{8}";
    String adressepattern = "[a-zA-Z0-9,;. ]*";
    //  String idpattern = "[0-9]*";
    String sujetpattern = "[a-zA-Z0-9,; ]*";
    String datepattern = "[0-9]{2}/-[0-9]{2}/-[0-9]*";
   // String descreptionpattern = "";
    String telpattern = "[0-9]{8}";
    boolean verif = true;


    public void ajouterDol(ActionEvent event) throws IOException {

    /*    if (!Pattern.matches(nompattern, nom.getText()) || nom.getText().isEmpty()) {
            lbnom.setVisible(true);

        }else{
            lbnom.setVisible(false);
        }

        if (!Pattern.matches(prenompattern, prenom.getText()) || prenom.getText().isEmpty()) {
            lbprenom.setVisible(true);

        }else{
            lbprenom.setVisible(false);
        }


        if (!Pattern.matches(cinpattern, cin.getText()) || cin.getText().isEmpty()) {
            lbcin.setVisible(true);

        }else{
            lbcin.setVisible(false);
        }

        if (!Pattern.matches(adressepattern, adresse.getText()) || adresse.getText().isEmpty()) {
            lbadresse.setVisible(true);

        }else{
            lbadresse.setVisible(false);
        }


       /* if (!Pattern.matches(idpattern, id.getText()) || id.getText().isEmpty()) {
            lbid.setVisible(true);

        }else{
            lbid.setVisible(false);
        }

        */
/*

        if (!Pattern.matches(sujetpattern, sujet.getText()) || sujet.getText().isEmpty()) {
            lbsujet.setVisible(true);

        }else{
            lbsujet.setVisible(false);
        }

     /*  if (!Pattern.matches(datepattern, date.getValue().toString()) || date.getValue().toString().isEmpty()) {
            lbdate.setVisible(true);

        }else{
            lbdate.setVisible(false);
        }

      */
        /*

          if (!Pattern.matches(telpattern, tel.getText()) || tel.getText().isEmpty()) {
            lbtel.setVisible(true);

        }else{
            lbtel.setVisible(false);
        }

         */
        if (nom.getText().isEmpty() || prenom.getText().isEmpty() || cin.getText().isEmpty() || adresse.getText().isEmpty() ||
                sujet.getText().isEmpty() || tel.getText().isEmpty()
                || date.getValue().toString().isEmpty() ) {
            remplir.setVisible(true);
            verif = false;
        } else {
            remplir.setVisible(false);
        }



        if (verif && Pattern.matches(nompattern, nom.getText())
                && Pattern.matches(prenompattern, prenom.getText()) &&
                Pattern.matches(cinpattern, cin.getText()) &&
                Pattern.matches(adressepattern, adresse.getText()) &&
                Pattern.matches(telpattern, tel.getText()) &&
                Pattern.matches(sujetpattern, sujet.getText()) &&
                !date.getValue().toString().isEmpty() &&
                event.getSource() == boutajouter) {

            ModuleDoleance p = new ModuleDoleance(nom.getText(),prenom.getText(),Integer.parseInt(cin.getText()),
                    adresse.getText(),sujet.getText(),descreption.getText(),date.getValue(),
                    Integer.parseInt(tel.getText()));
            DoleanceService.Ajouter(p);
            Stage stage = (Stage) boutajouter.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("succés d'ajout");
            alert.setHeaderText(null);
            alert.setContentText("l'ajout est fait avec succés");
            alert.show();




        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nom.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbnom.setVisible(!Pattern.matches(nompattern, nom.getText())|| nom.getText().isEmpty());
            }
        });

        prenom.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbprenom.setVisible(!Pattern.matches(prenompattern, prenom.getText()) || prenom.getText().isEmpty());
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

        sujet.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbsujet.setVisible(!Pattern.matches(sujetpattern, sujet.getText())|| sujet.getText().isEmpty());
            }
        });
        tel.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbtel.setVisible(!Pattern.matches(telpattern, tel.getText())|| tel.getText().isEmpty());
            }
        });
        tell.setEditable(false);

     /* date.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbdate.setVisible(!Pattern.matches(datepattern,date.getValue().toString()) || date.getValue().toString().isEmpty());
            }
        });

      */

     //   if (!Pattern.matches(datepattern, date.getValue().toString()) || date.getValue().toString().isEmpty()) {
     //       lbdate.setVisible(true);

      //  }else{
         //   lbdate.setVisible(false);
       // }








      /* id.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbid.setVisible(!Pattern.matches(idpattern, id.getText())|| id.getText().isEmpty());
            }
        });

       */




    }

    public void retour() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }
}
