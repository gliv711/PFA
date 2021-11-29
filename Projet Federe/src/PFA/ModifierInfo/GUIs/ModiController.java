package PFA.ModifierInfo.GUIs;

import PFA.ModifierInfo.Module.information;
import PFA.ModifierInfo.Services.InfoServices;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class ModiController {
    
    @FXML
    private TextField nomTF;
    @FXML
    private TextField telephoneTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField adresseTF;
    
    
    private information info;
    
    public void setInfo(information info) {
        this.info = info;
    }
    
    
    public void init() {
        nomTF.setText(info.getNom());
        telephoneTF.setText(info.getTelephone());
        emailTF.setText(info.getEmail());
        adresseTF.setText(info.getAdresse());
        
        
        
    }
    
    @FXML
    private Label error;
    
    public void modifier() {
        String nomPattern = "[0-9A-Za-z '-_:!,]";
        String numPattern = "[2-9][0-9]{7}";
        String emailPattern = "[0-9A-Za-z.-_+%]+@[a-zA-Z0-9.-]+.[a-z]{2,4}$";
        String adressePattern = "[0-9A-Za-z .',:%]";
        if (Pattern.matches(nomPattern,nomTF.getText()) && Pattern.matches(numPattern,telephoneTF.getText()) && Pattern.matches(emailPattern,emailTF.getText()) && Pattern.matches(adressePattern,adresseTF.getText())){
            error.setVisible(true);
            return;
        }else error.setVisible(false);
        information information = new information(adresseTF.getText(),emailTF.getText(),telephoneTF.getText(),nomTF.getText());
        InfoServices.update(information);
        Stage stage = (Stage) error.getScene().getWindow();
        stage.close();
    }
}
