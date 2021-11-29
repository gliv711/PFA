package PFA.GestionFinances.GUIs;

import PFA.GestionFinances.Module.Operation;
import PFA.GestionFinances.Service.OperationServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ajouterController implements Initializable {
    @FXML
    private TextField nomTextField;
    
    @FXML
    private TextField valeurTF;
    
    @FXML
    private Label nomLabel;
    
    @FXML
    private Label mdpLabel;
    
    @FXML
    private Label confirmerLabel;
    
    @FXML
    private Label personnelLabel;
    
    @FXML
    private Label remplirLabel;
    
    @FXML
    private Button ajouter;
    
    @FXML
    private Button annuler;
    
    @FXML
    private ComboBox<String> typeCombobox;
    
    
    @FXML
    private DatePicker datepicker;
    
    public void ajouter() {
        boolean valid = Pattern.matches(nomPattern, nomTextField.getText()) && Pattern.matches(valeurPattern, valeurTF.getText()) && !datepicker.getValue().toString().isEmpty() && !typeCombobox.getSelectionModel().isEmpty();
        if (valid) {
            Operation op = new Operation(nomTextField.getText(), typeCombobox.getSelectionModel().getSelectedItem().toString(), Float.parseFloat(valeurTF.getText()), datepicker.getValue());
            OperationServices.ajouter(op);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Operation Ajoute");
            alert.showAndWait();
            Stage stage = (Stage) ajouter.getScene().getWindow();
            stage.close();
        }
    }
    
    public void annuler(ActionEvent event) {
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
    
    private final String[] types = { "Depense", "Revenue" };
    private final String nomPattern = "[0-9a-zA-Z_]{3,20}";
    private final String valeurPattern = "[0-9]+.[0-9]+";
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                nomLabel.setVisible(!Pattern.matches(nomPattern, nomTextField.getText()));
            }
        });
        valeurTF.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                mdpLabel.setVisible(!Pattern.matches(valeurPattern, valeurTF.getText()));
            }
        });
        typeCombobox.getItems().addAll(types);
    }
}
