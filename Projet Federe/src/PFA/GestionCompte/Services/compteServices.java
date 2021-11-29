package PFA.GestionCompte.Services;

import PFA.GestionCompte.Modules.Compte;
import PFA.GestionPersonnel.Modules.Personnel;
import PFA.GestionPersonnel.Services.PersonnelServices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class compteServices {
    public static ArrayList<Compte> ParseCompteListe() {
        ArrayList<Compte> liste = new ArrayList<>();
        String query = "select * from compte";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                liste.add(new Compte(res.getInt("idcompte"), res.getString("nomutilisateur"), res.getString("password"), res.getString("role"), PersonnelServices.ParsePersonnel(res.getInt("idpersonnel"))));
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    public static void Suprrimer(int id) {
        String query = "delete from COMPTE where IDCOMPTE = " + id;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public static void Modifier(Compte compte) {
        String query = "update COMPTE set " +
                "NOMUTILISATEUR = " + compte.getNomUtilisateur() + "," +
                "PASSWORD = " + compte.getMDP() +
                " where IDCOMPTE = " + compte.getId();
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public static void Ajouter(Compte compte) {
        String SQLquery = "insert into COMPTE values (compte_seq.nextval," + compte.getPersonnel().getId() + ",'" + compte.getNomUtilisateur() + "','" + compte.getMDP() + "','" + compte.getRole() + "')";
        Statement statement;
        try {
            Connection connection = getOracleConnection();
            statement = connection.createStatement();
            statement.executeUpdate(SQLquery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Compte> recherche(String term) {
        ArrayList<Compte> liste = new ArrayList<>();
        String query = "select * from compte where lower(NOMUTILISATEUR) like lower('%" + term + "%')";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                liste.add(new Compte(res.getInt("idcompte"), res.getString("nomutilisateur"), res.getString("password"), res.getString("role"), PersonnelServices.ParsePersonnel(res.getInt("idpersonnel"))));
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    public static boolean usernameExist(String username){
        String query = "select count(*) from compte where NOMUTILISATEUR like '" + username + "'";
        boolean exist = false;
         try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            res.next();
            int count = res.getInt(1);
            exist = count == 1;
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return exist;
    }
    
    public static List<Personnel> ParsePersonnelListe() {
        List<Personnel> liste = new ArrayList<>();
        String query = "select * from Personnel where IDPERSONNEL not in (select IDPERSONNEL from COMPTE)";
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
    
}
