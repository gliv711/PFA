package PFA.GestionEvenement.GUIs;

import PFA.GestionEvenement.Modules.Evenement;
import PFA.GestionEvenement.Services.EvenementServ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class ListeEvenementController implements Initializable {
    @FXML
    Button ajouterButton ,detailsButton,supprimerButton,modifierButton,refreshButton,rechercheButton;
    @FXML
    TableView <Evenement> listeEve;
    @FXML
    TableColumn <Evenement,String> nomColumn;
    @FXML
    TableColumn <Evenement, Date> dateDebutColumn;
    @FXML
    TableColumn <Evenement,Date> dateFinColumn;
    @FXML
    TableColumn <Evenement,Date> adresseColumn;
    @FXML
    TextField tfrecherche;
    @FXML
    AnchorPane pane;
    @FXML
    Button rapportbtn;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ArrayList <Evenement> liste = new ArrayList<>(EvenementServ.parsEveListe());
        refreshListe(liste);
        listeEve.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && !listeEve.getSelectionModel().isEmpty()) {
                detailsButton.setDisable(false);
                modifierButton.setDisable(false);
                supprimerButton.setDisable(false);
                rapportbtn.setDisable(false);
            }
        });
        tfrecherche.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                detailsButton.setDisable(true);
                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
                rapportbtn.setDisable(true);
            }
        });
        rechercheButton.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                detailsButton.setDisable(true);
                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
                rapportbtn.setDisable(true);
            }
        });
        pane.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                detailsButton.setDisable(true);
                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
                rapportbtn.setDisable(true);
            }
        });
        refreshButton.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                detailsButton.setDisable(true);
                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
                rapportbtn.setDisable(true);
            }
        });
    }
    public void refreshListe(List<Evenement> parseEvenementListe) {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nomEve"));
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("dateBedutEve"));
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("dateFinEve"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresseEve"));

        listeEve.getItems().setAll (parseEvenementListe);
    }

    public void ajouterButtonEve() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ajouterGeneralEvenement.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.showAndWait();
        refreshListe(EvenementServ.parsEveListe());
    }


    public void retour0(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../login_mainMenu/fxml/mainMenu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    public void refresh1() {
        refreshListe(EvenementServ.parsEveListe());
    }

    public void modifierButton2() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/modifierEvenement.fxml"));
        Parent root = loader.load();
        ModifierEvenementController controller = loader.getController();
        controller.initData(listeEve.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.showAndWait();
        refreshListe(EvenementServ.parsEveListe());
    }

    public void detailsbutton2() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/detailsEvenement.fxml"));
        Parent root = loader.load();
        detailsEvenement controller = loader.getController();
        controller.initData1(listeEve.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void supprimerButton2() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/supprimerEvenement.fxml"));
        Parent root = loader.load();
        supprimerEvenement controller = loader.getController();
        controller.id = listeEve.getSelectionModel().getSelectedItem().getIdEve();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.showAndWait();
        refreshListe(EvenementServ.parsEveListe());
    }
    public void recherche3() {
        refreshListe(EvenementServ.Recherche2(tfrecherche.getText()));
    }
    
    public void generate() {
//        String fileName = "/devel/examples/test.jasper";
//        String outFileName = "/devel/examples/test.pdf";
//        HashMap hm = new HashMap();
//        try
//        {
//            JasperPrint print = JasperFillManager.fillReport(
//                    fileName,
//                    hm,
//                    new JREmptyDataSource());
//            JRExporter exporter =
//                    new net.sf.jasperreports.engine.export.JRPdfExporter();
//            exporter.setParameter(
//                    JRExporterParameter.OUTPUT_FILE_NAME,
//                    outFileName);
//            exporter.setParameter(
//                    JRExporterParameter.JASPER_PRINT,print);
//            exporter.exportReport();
//            System.out.println("Created file: " + outFileName);
//        }
//        catch (JRException e)
//        {
//            e.printStackTrace();
//            System.exit(1);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            System.exit(1);
//        }
    }
}
