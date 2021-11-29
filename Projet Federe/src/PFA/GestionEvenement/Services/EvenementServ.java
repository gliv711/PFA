package PFA.GestionEvenement.Services;

import PFA.GestionEvenement.Modules.Evenement;
import PFA.GestionEvenement.Modules.OutillMater;
import PFA.GestionEvenement.Modules.Perso;
import PFA.GestionPersonnel.Modules.Personnel;
import PFA.MaterielFiras.ModuleMateriel.Outil;
import PFA.MaterielFiras.ModuleMateriel.Vehicule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class EvenementServ {

    public static List <Evenement> parsEveListe(){
        List <Evenement> liste = new ArrayList<>();
        String SQLquery = "SELECT * FROM EVENEMENT";
        String fetchPersonnelEve = "SELECT * from evenement_personnel where idEvenement = ";
        String fetchVehiculeEve = "SELECT * from evenement_vehicule where idEvenement = ";
        String fetchOutilEve = "SELECT * from evenement_outil where idEvenement = ";
        ArrayList <Personnel> listePerso = new ArrayList<>();
        ArrayList <Vehicule> listeVehicule = new ArrayList<>();
        ArrayList <OutillMater> listeOutilMat = new ArrayList<>();
        String idEvenement;

        try{
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(SQLquery);
            while (res.next()){
                idEvenement = res.getString("idevenement");
                Statement statement1 = connection.createStatement();
                Statement statement2 = connection.createStatement();
                Statement statement3 = connection.createStatement();
                ResultSet rsPerso = statement1.executeQuery(fetchPersonnelEve + idEvenement);
                ResultSet rsVehicule = statement2.executeQuery(fetchVehiculeEve + idEvenement);
                ResultSet rsOutilMat = statement3.executeQuery(fetchOutilEve + idEvenement);
                listePerso.clear();
                listeVehicule.clear();
                listeOutilMat.clear();
                while (rsPerso.next()){
                    listePerso.add(new Personnel(rsPerso.getInt("idPersonnel"),rsPerso.getString("nom"),
                            rsPerso.getString("prenom"),rsPerso.getString("poste")));
                }
                rsPerso.close();
                while (rsVehicule.next()){
                    listeVehicule.add(new Vehicule(rsVehicule.getInt("idvehicule"),
                            rsVehicule.getInt("matricule"),rsVehicule.getString("model"),
                            rsVehicule.getString("nom")));
                }
                rsVehicule.close();
                while (rsOutilMat.next()){
                    listeOutilMat.add(new OutillMater(new Outil(rsOutilMat.getInt("idOutil"),
                            rsOutilMat.getInt("quantite"),rsOutilMat.getString("nom"),
                            rsOutilMat.getInt("consumable")),rsOutilMat.getInt("quantite")));
                }
                rsOutilMat.close();
                liste.add(new Evenement(Integer.parseInt(idEvenement),res.getString("nomevenement"),
                        res.getDate("datedebutevenement").toLocalDate(),
                        res.getDate("datefinevenement").toLocalDate(),
                        res.getFloat("budget"), res.getString("adresse"),
                        listePerso, listeVehicule, listeOutilMat));
            }
            res.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return liste;
    }

    public static List<Perso> ParsePersonnelListe() {
        List<Perso> liste = new ArrayList<>();
        String query = "select * from Personnel " +
                "where idpersonnel NOT IN (select idpersonnel from evenement_personnel)";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                liste.add(new Perso(res.getInt("idpersonnel"),
                        res.getString("nomPersonnel"),
                        res.getString("prenomPersonnel"), res.getString("postePersonnel")));
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }

    public static List<Vehicule> parseVehiculeList() {
        List<Vehicule> data = new ArrayList<>();
        String SQLquery = "SELECT * from VEHICULE where IDVEHICULE not in (select IDVEHICULE from EVENEMENT_VEHICULE)";
        try {
            Connection connection = getOracleConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SQLquery);
            while (rs.next()) {
                data.add(new Vehicule(
                        rs.getInt("idVehicule"),
                        rs.getInt("matriculeVehicule"), rs.getString("modelVehicule"), rs.getString("nomVehicule"), rs.getDate("dateachat").toLocalDate(), rs.getFloat("prix")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static List<Outil> parseOutilList() {
        List<Outil> liste = new ArrayList<>();
        String query = "select * from OUTIL";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                liste.add(new Outil(rs.getInt("idoutil"),
                        rs.getInt("quantiteoutil"),
                        rs.getString("nomoutil"),
                        rs.getInt("consumableoutil")));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }

    public static void Ajouter(Evenement i) {
        String query1;
        String query3;
        String query2;
        String query = "insert into EVENEMENT values(EVENEMENT_SEQ.nextval,'" + i.getNomEve() +
                "'," + "TO_DATE('" + i.getDateBedutEve().toString() + "','yyyy-mm-dd')" + "," +
                "TO_DATE('" + i.getDateFinEve().toString() + "','yyyy-mm-dd')" + ","
                + i.getBudgetEve() + ",'" + i.getAdresseEve() + "')";
        Statement statement;
        Statement statement1;
        try {
            Connection connection = getOracleConnection();
            statement1 = connection.createStatement();
            statement = connection.createStatement();
            statement.executeUpdate(query);

            for (OutillMater o : i.getOutilsUtilisEve()) {
                query1 = "insert into EVENEMENT_OUTIL (IDOUTIL, CONSUMABLE, IDEVENEMENT, QUANTITE, NOM) values(" + o.outils.getId() +
                        "," + o.outils.getConsumable() + ",EVENEMENT_SEQ.currval ,"
                        + o.quantite + ",'" + o.outils.getNom() + "')";
                statement1.executeUpdate(query1);
                if (o.outils.getConsumable() == 1){
                    statement1.execute("update outil set QUANTITEOUTIL = QUANTITEOUTIL - " + o.quantite +" where IDOUTIL = " + o.outils.getId());
                }
            }
            for (Personnel p : i.getEquipeEve()) {
                query2 = "insert into EVENEMENT_PERSONNEL values("
                        + p.getId() + ",EVENEMENT_SEQ.currval,'"
                        + p.getNom() + "','" + p.getPrenom() + "','" + p.getPoste() + "')";
                statement.executeUpdate(query2);
            }
            for (Vehicule v : i.getVehiculesEve()) {
                query3 = "insert into EVENEMENT_VEHICULE values("
                        + v.getId() + ",EVENEMENT_SEQ.currval,'" + v.getModel() + "','" + v.getNom()
                        + "','" + v.getMatricule() + "'," + v.getPrix() + ",to_date('" + v.getDateDachat().toString()
                        + "','yyyy-mm-dd'))";
                statement.executeUpdate(query3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Modifier(Evenement i) {
    /* String SQLquery = String.format("update EVENEMENT set " +
                "IDEVENEMENT = %d," +
                "NOMEVENEMENT = '%s'," +
                "DATEDEBUTEVENEMENT = TO_DATE('%s','DD-MM-YYYY')," +
                "DATEFINEVENEMENT = TO_DATE('%s','DD-MM-YYYY')," +
                "BUDGET = %f," +
                "ADRESSE = '%s'" +
                "WHERE IDEVENEMENT = %d", i.getIdEve(),
                i.getNomEve(), i.getDateBedutEve().toString(), i.getDateFinEve().toString(),
                i.getBudgetEve(), i.getAdresseEve(), i.getIdEve());

     */
        String SQLquery = "update EVENEMENT set IDEVENEMENT = " + i.getIdEve() + ",NOMEVENEMENT ='"  + i.getNomEve() +
            "', DATEDEBUTEVENEMENT = TO_DATE('" + i.getDateBedutEve().toString() + "','yyyy-mm-dd')" +
            ", DATEFINEVENEMENT = TO_DATE('" + i.getDateFinEve().toString() + "','yyyy-mm-dd')" +", BUDGET =" +
            i.getBudgetEve() + ", ADRESSE ='" + i.getAdresseEve() + "' WHERE IDEVENEMENT =" + i.getIdEve() + "";
         System.out.println(SQLquery);
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQLquery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void Suprrimer(int id) {
        String query = "delete from EVENEMENT where IDEVENEMENT = " + id;
        String query1 = "delete from EVENEMENT_VEHICULE where IDEVENEMENT = " + id;
        String query2 = "delete from EVENEMENT_PERSONNEL where IDEVENEMENT = " + id;
        String query3 = "delete from EVENEMENT_OUTIL where IDEVENEMENT = " + id;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.execute(query1);
            statement.execute(query2);
            statement.execute(query3);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public static List<Evenement> Recherche2(String term) {
        List<Evenement> liste = new ArrayList<>();
        String query = "SELECT * FROM EVENEMENT WHERE lower(NOMEVENEMENT) like lower('%" + term + "%')";
        String fetchPersonnel = "SELECT * from EVENEMENT_PERSONNEL where IDEVENEMENT = ";
        String fetchVehicule = "SELECT * from EVENEMENT_VEHICULE where IDEVENEMENT = ";
        String fetchOutil = "SELECT * from EVENEMENT_OUTIL where IDEVENEMENT = ";
        ArrayList<Personnel> listePersonnel = new ArrayList<>();
        ArrayList<Vehicule> listeVehicule = new ArrayList<>();
        ArrayList<OutillMater> listeOutil = new ArrayList<>();
        String id;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();

            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                id = res.getString("idevenement");
                Statement statement1 = connection.createStatement();
                Statement statement2 = connection.createStatement();
                Statement statement3 = connection.createStatement();
                ResultSet rsPersonnel = statement1.executeQuery(fetchPersonnel + id);
                ResultSet rsVehicule = statement2.executeQuery(fetchVehicule + id);
                ResultSet rsOutil = statement3.executeQuery(fetchOutil + id);
                listePersonnel.clear();
                listeVehicule.clear();
                listeOutil.clear();
                while (rsPersonnel.next()) {
                    listePersonnel.add(new Personnel(rsPersonnel.getInt("idPersonnel"), rsPersonnel.getString("nom"), rsPersonnel.getString("prenom"), rsPersonnel.getString("poste")));
                }
                rsPersonnel.close();
                while (rsVehicule.next()) {
                    listeVehicule.add(new Vehicule(rsVehicule.getInt("idVehicule"), rsVehicule.getInt("matricule"), rsVehicule.getString("model"), rsVehicule.getString("nom")));
                }
                rsVehicule.close();
                while (rsOutil.next()) {
                    listeOutil.add(new OutillMater(new Outil(rsOutil.getInt("idOutil"), rsOutil.getInt("quantite"), rsOutil.getString("nom"), rsOutil.getInt("consumable")), rsOutil.getInt("quantite")));
                }
                rsOutil.close();
                liste.add(new Evenement(Integer.parseInt(id), res.getString("nomevenement"), res.getDate("DATEDEBUTEVENEMENT").toLocalDate(), res.getDate("DATEFINEVENEMENT").toLocalDate(), res.getFloat("budget"), res.getString("adresse"), listePersonnel, listeVehicule, listeOutil));
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }




}
