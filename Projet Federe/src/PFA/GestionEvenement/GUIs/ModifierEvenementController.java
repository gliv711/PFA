package PFA.GestionEvenement.GUIs;

import PFA.GestionEvenement.Modules.Evenement;
import PFA.GestionEvenement.Services.EvenementServ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class ModifierEvenementController {
    private final String nomPattern = "[A-Za-z]{3}[A-Za-z ]*";
    private final String adressePattern = "[A-Za-z 0-9]*";
    private final String budgetPattern = "[0-9]+.[0-9]+";

    @FXML
    private DatePicker dateDebutPicker;

    @FXML
    private DatePicker dateFinPicker;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField budgetTextField,id;

    @FXML
    private Button modifier;

    @FXML
    private TextField adresseTextField;

    @FXML
    private Button retour;

    @FXML
    private Label DateDebutErrorLabel;

    @FXML
    private Label DateFinErrorLabel;

    @FXML
    private Label BudgetErrorLabel;

    @FXML
    private Label AdresseErrorLabel;

    @FXML
    private Label NomErrorLabel;

    @FXML
    private Label remplir;

    public void initData(Evenement selectedItem) {
        nomTextField.setText(selectedItem.getNomEve());
        adresseTextField.setText(selectedItem.getAdresseEve());
        budgetTextField.setText(String.valueOf(selectedItem.getBudgetEve()));
        dateDebutPicker.setValue(selectedItem.getDateBedutEve());
        dateFinPicker.setValue(selectedItem.getDateFinEve());
        id.setText(String.valueOf(selectedItem.getIdEve()));
        id.setEditable(false);

        nomTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                NomErrorLabel.setVisible(!Pattern.matches(nomPattern, nomTextField.getText()) ||
                        nomTextField.getText().isEmpty());
            }
        });

        adresseTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                AdresseErrorLabel.setVisible(!Pattern.matches(adressePattern, adresseTextField.getText()) ||
                        adresseTextField.getText().isEmpty());
            }
        });

        budgetTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                BudgetErrorLabel.setVisible(!Pattern.matches(budgetPattern, budgetTextField.getText()) ||
                        budgetTextField.getText().isEmpty());
            }
        });


    }

    Evenement evenement;
    public void modifierEve(ActionEvent event) throws IOException {
        boolean valid = true;
        if (nomTextField.getText().isEmpty() ||
                adresseTextField.getText().isEmpty() ||
                budgetTextField.getText().isEmpty() ||
                dateDebutPicker.getValue().toString().isEmpty() || dateFinPicker.getValue().toString().isEmpty()) {
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
                Pattern.matches(adressePattern, adresseTextField.getText()) &&
                Pattern.matches(nomPattern, nomTextField.getText()) &&
                 event.getSource() == modifier) {
            Evenement i = new Evenement(Integer.parseInt(id.getText()),nomTextField.getText(),
                    dateDebutPicker.getValue(),dateFinPicker.getValue(),
                    Float.parseFloat(budgetTextField.getText()),adresseTextField.getText());
            EvenementServ.Modifier(i);

            Stage stage = (Stage) modifier.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("succés de modification");
            alert.setHeaderText(null);
            alert.setContentText("Modification faite avec succés");
            alert.show();
        }
    }

    public void retour8() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }

}
