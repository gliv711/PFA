package PFA.GestionPersonnel.Services;

import PFA.GestionPersonnel.Modules.Personnel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class PersonnelServices {
    public static void Ajouter(Personnel p){
        String SQLquery = String.format("insert into Personnel values(" +
                "Personnel_seq.nextval," +
                "'%s'," +
                "'%s'," +
                "%d," +
                "to_date('%s','yyyy-mm-dd')," +
                "'%s'," +
                "%f)",p.getNom(),p.getPrenom(),p.getCIN(),p.getDateNaissance(),p.getPoste(),p.getSalaire());
        Statement statement;
        try {
            Connection connection = getOracleConnection();
            statement = connection.createStatement();
            statement.executeUpdate(SQLquery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Suprrimer(int id) {
        String query = "delete from personnel where IDPERSONNEL = " + id ;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void Modifier(Personnel p) {
        String query = String.format("update perosnnel set " +
                "nomPersonnel = '%s', " +
                "prenomPersonnel = '%s'," +
                "cinPersonnel = %d," +
                "datenaissancepersonnel = to_date('%s','yyyy-mm-dd')," +
                "postepersonnel = '%s'," +
                "salaire = %f" +
                " where idPersonnel = %d",p.getNom(),p.getPrenom(),p.getCIN(),p.getDateNaissance(),p.getPoste(),p.getSalaire(),p.getId());
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public static boolean CINexist(int cin){
        String query = "select count(*) from personnel where CINPERSONNEL = " + cin ;
        boolean exist = false;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            res.next();
            exist = res.getInt(1) == 1;
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return exist;
    }
    
    public static List<Personnel> Recherche(String term){
        List<Personnel> liste = new ArrayList<>();
        String query = "select * from Personnel" +
                " WHERE lower(NOMPERSONNEL) LIKE lower('%" + term + "%')" +
                " OR lower(PRENOMPERSONNEL) LIKE lower('%" + term + "%')" ;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                liste.add(new Personnel(res.getInt("idPersonnel")
                        ,res.getString("nomPersonnel")
                        ,res.getString("prenomPersonnel")
                        ,res.getInt("cinPersonnel")
                        ,res.getFloat("salaire")
                        ,res.getString("postePersonnel")
                        ,res.getDate("datenaissancepersonnel").toLocalDate()));}
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    public static List<Personnel> ParsePersonnelListe(){
        List<Personnel> liste = new ArrayList<>();
        String query = "select * from Personnel" ;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                liste.add(new Personnel(res.getInt("idPersonnel")
                        ,res.getString("nomPersonnel")
                        ,res.getString("prenomPersonnel")
                        ,res.getInt("cinPersonnel")
                        ,res.getFloat("salaire")
                        ,res.getString("postePersonnel")
                        ,res.getDate("datenaissancepersonnel").toLocalDate()));}
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    public static Personnel ParsePersonnel(int id){
        Personnel personnel = null;
        String query = "select * from Personnel where IDPERSONNEL = " + id ;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                personnel = new Personnel(res.getInt("idPersonnel")
                        ,res.getString("nomPersonnel")
                        ,res.getString("prenomPersonnel")
                        ,res.getInt("cinPersonnel")
                        ,res.getFloat("salaire")
                        ,res.getString("postePersonnel")
                        ,res.getDate("datenaissancepersonnel").toLocalDate());
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personnel;
    }
    
}
