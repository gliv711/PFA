package PFA.GestionEvenement.GUIs.ajouterEvenement;

import PFA.GestionEvenement.Modules.Evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ajouterGeneralEvenement implements Initializable {

    private final String nomPattern = "[A-Za-z]{3}[A-Za-z ]*";
    private final String budgetPattern = "[0-9]+.[0-9]+";
    private final String numPattern = "[0-9]*";

    @FXML
    TextField nomTextField, budgetTextField, numAdresseTextField, adresseTextField;
    @FXML
    DatePicker dateDebutPicker, dateFinPicker;
    @FXML
    private Label DateDebutErrorLabel;

    @FXML
    private Label DateFinErrorLabel;

    @FXML
    private Label BudgetErrorLabel;

    @FXML
    private Label AdresseErrorLabel;

    @FXML
    private Label NomErrorLabel, remplir;

    @FXML
    Button retour;

    public void switchToEvePersonnel(ActionEvent event) throws IOException {
        boolean valid = true;
        if (nomTextField.getText().isEmpty() ||
                numAdresseTextField.getText().isEmpty() ||
                adresseTextField.getText().isEmpty() ||
                budgetTextField.getText().isEmpty() ||
                dateDebutPicker.getValue().toString().isEmpty() ||
                dateFinPicker.getValue().toString().isEmpty()) {
            remplir.setVisible(true);
            valid = false;
        }

        if (!dateDebutPicker.getValue().toString().isEmpty() &&
                !dateDebutPicker.getValue().toString().isEmpty() &&
                !dateDebutPicker.getValue().isBefore(dateFinPicker.getValue())) {
            DateDebutErrorLabel.setVisible(true);
            DateFinErrorLabel.setVisible(true);
            valid = false;
        }

        if (valid && Pattern.matches(budgetPattern, budgetTextField.getText()) &&
                Pattern.matches(numPattern, numAdresseTextField.getText()) &&
                Pattern.matches(nomPattern, adresseTextField.getText()) &&
                Pattern.matches(nomPattern, nomTextField.getText())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouterPersonnelEvenement.fxml"));
            Parent root = loader.load();
            ajouterPersonnelEvenement controller = loader.getController();
            controller.setEvenement(new Evenement(nomTextField.getText(),
                    dateDebutPicker.getValue(), dateFinPicker.getValue(),
                    Float.parseFloat(budgetTextField.getText()), numAdresseTextField.getText() +
                    " " + adresseTextField.getText()));
            controller.initData();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            JMetro jMetro = new JMetro(Style.LIGHT);
            jMetro.setScene(scene);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void retour() throws IOException {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nomTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                NomErrorLabel.setVisible(!Pattern.matches(nomPattern, nomTextField.getText()));
            }
        });

        adresseTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                AdresseErrorLabel.setVisible(!Pattern.matches(nomPattern, adresseTextField.getText()));
            }
        });

        numAdresseTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                AdresseErrorLabel.setVisible(!Pattern.matches(numPattern, numAdresseTextField.getText()));
            }
        });


        budgetTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                BudgetErrorLabel.setVisible(!Pattern.matches(budgetPattern, budgetTextField.getText()));
            }
        });


    }
}