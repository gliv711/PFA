package PFA.GestionTache.Services;

import PFA.GestionPersonnel.Modules.Personnel;
import PFA.GestionTache.Module.Tache;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class tacheServices {
    public static void Suprrimer(int id) {
        String query = "delete from tache where idtache = " + id;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public static List<Personnel> ParsePersonnelListe() {
        List<Personnel> liste = new ArrayList<>();
        String query = "select * from Personnel";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                liste.add(new Personnel(res.getInt("idPersonnel")
                        , res.getString("nomPersonnel")
                        , res.getString("prenomPersonnel")
                        , res.getInt("cinPersonnel")
                        , res.getFloat("salaire")
                        , res.getString("postePersonnel")
                        , res.getDate("datenaissancepersonnel").toLocalDate()));
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    public static ArrayList<Tache> parsetacheListe(LocalDate debut, LocalDate fin) {
        ArrayList<Tache> liste = new ArrayList<>();
        String query = "select * from tache where DATETACHE between to_date('"+debut.toString()+"','yyyy-mm-dd') and to_date('"+fin.toString()+"','yyyy-mm-dd')";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                int id = res.getInt("idpersonnel");
                ResultSet resPersonnel = statement1.executeQuery("select * from PERSONNEL where IDPERSONNEL = " + id);
                Personnel personnel = null;
                while (resPersonnel.next()) {
                    personnel = new Personnel(resPersonnel.getInt("idpersonnel")
                            , resPersonnel.getString("nomPersonnel")
                            , resPersonnel.getString("prenomPersonnel")
                            , resPersonnel.getInt("cinPersonnel")
                            , resPersonnel.getFloat("salaire")
                            , resPersonnel.getString("postePersonnel")
                            , resPersonnel.getDate("datenaissancepersonnel").toLocalDate());
                }
                resPersonnel.close();
                liste.add(new Tache(res.getInt("idtache"), res.getString("nomtache"), res.getString("description"), personnel, res.getDate("datetache").toLocalDate()));
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    public static void ajouter(Tache tache) {
        String query;
        if (tache.getPersonnel() == null) {
            query = "insert into tache (IDTACHE, NOMTACHE, DESCRIPTION, DATETACHE) values (tache_seq.nextval,'" + tache.getNom() + "','" + tache.getDescription() + "',to_date('" + tache.getDate().toString() + "','yyyy-mm-dd'))";
        } else
            query = "insert into tache (IDTACHE, NOMTACHE, IDPERSONNEL, DESCRIPTION, DATETACHE) values (tache_seq.nextval,'" + tache.getNom() + "'," + tache.getPersonnel().getId() + ",'" + tache.getDescription() + "',to_date('" + tache.getDate().toString() + "','yyyy-mm-dd'))";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public static void Modifier(Tache tache) {
        String query;
        if (tache.getPersonnel() == null) {
            query = "update TACHE set NOMTACHE = '" + tache.getNom() + "'," +
                    "DESCRIPTION = '" + tache.getDescription() +
                    "' where IDTACHE =" + tache.getId();
        } else
            query = "update TACHE set NOMTACHE = '" + tache.getNom() + "'," +
                    "IDPERSONNEL = " + tache.getPersonnel().getId() + "," +
                    "DESCRIPTION = '" + tache.getDescription() +
                    "' where IDTACHE =" + tache.getId();
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public static ArrayList<Tache> recherche(String text) {
        ArrayList<Tache> liste = new ArrayList<>();
        String query = "select * from tache where lower(NOMTACHE) like lower('" + text + "')";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                int id = res.getInt("idtache");
                ResultSet resPersonnel = statement1.executeQuery("select * from PERSONNEL where IDPERSONNEL = " + id);
                Personnel personnel = null;
                while (resPersonnel.next()) {
                    personnel = new Personnel(id
                            , resPersonnel.getString("nomPersonnel")
                            , resPersonnel.getString("prenomPersonnel")
                            , resPersonnel.getInt("cinPersonnel")
                            , resPersonnel.getFloat("salaire")
                            , resPersonnel.getString("postePersonnel")
                            , resPersonnel.getDate("datenaissancepersonnel").toLocalDate());
                }
                resPersonnel.close();
                liste.add(new Tache(id, res.getString("nomtache"), res.getString("description"), personnel, res.getDate("dateachat").toLocalDate()));
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
        
    }
}
