package PFA.GestionDoleances.Service;

import PFA.GestionDoleances.Module.ModuleDoleance;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class DoleanceService {
    public static void Ajouter(ModuleDoleance p) {
        /*String SQLquery = String.format("insert into doleance values (" +
                "'%s','%s',%d,'%s',%d,'%s','%s','%s',%d )",p.getNom(),p.getPrenom(),p.getCin(),p.getAddresse(),p.getIDdoleance(),
                p.getSujet(),p.getDescreption(),p.getDateDoleance(),p.getNumTel());

         */

       /* String SQLquery = "insert into doleance values ( '" + p.getNom() + "','" + p.getPrenom() + "'," + p.getCin() + ",'" +
        p.getAddresse() + "'," + "," + p.getIDdoleance() + ",'" + p.getSujet() + "','" + p.getDescreption() + "'," +
                "TO_DATE('" + p.getDateDoleance().toString() + "','yyyy-mm-dd')" + "," + p.getNumTel()  + ")";

        */

        String SQLquery = String.format("insert into doleance values( " +
                "'%s','%s',%d,'%s',DOL_SEQ.nextval,'%s','%s',to_date('%s','yyyy-mm-dd'),%d)",
                p.getNom(),p.getPrenom(), p.getCin(),p.getAddresse(),p.getSujet(),p.getDescreption(),
                p.getDateDoleance().toString(),p.getNumTel());


        Statement statement;
        try {
            Connection connection = getOracleConnection();
            statement = connection.createStatement();
            statement.executeUpdate(SQLquery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Modifier(ModuleDoleance p) {
        String SQLquery = String.format("update doleance set " +
                "nom = '%s'," +
                "prenom = '%s'," +
                "cin = %d," +
                "addresse = '%s'," +
                "sujet = '%s'," +
                "descreption = '%s'," +
                "datedoleance = to_date('%s','yyyy-mm-dd')," +
                "numtel = %d " +
                "where iddoleance = %d", p.getNom(), p.getPrenom(), p.getCin(), p.getAddresse(),p.getSujet(),
                p.getDescreption(),p.getDateDoleance().toString(),p.getNumTel(),p.getIDdoleance());
        System.out.println(SQLquery);
        Statement statement;
        try {
            Connection connection = getOracleConnection();

            statement = connection.createStatement();

            statement.executeUpdate(SQLquery);



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void Supprimer(Integer iddoleance) {
        String query = "delete from doleance where iddoleance = " + iddoleance ;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<ModuleDoleance> Recherche(String term) {
        List<ModuleDoleance> liste = new ArrayList<>();
        String query = "select * from doleance where lower(nom) like lower('%" + term + "%') " +
                //"or iddoleance =" + "'" + term + "'" +
                //  "or cin =" + "'" + term + "'" +
                "or lower(sujet) like lower('%" + term + "%')";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                liste.add(new ModuleDoleance(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getInt("cin"),
                        rs.getString("addresse"),
                        rs.getInt("iddoleance"),
                        rs.getString("sujet"),
                        rs.getString("descreption"),
                        rs.getDate("datedoleance").toLocalDate(),
                        rs.getInt("numtel")));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }

    public static List<ModuleDoleance> Check (int iddoleance,boolean idExists) {
        List<ModuleDoleance> liste = new ArrayList<>();
        String query = "select * from doleance";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int idCounter;
            if (rs.next()) {
              idCounter = rs.getInt("iddoleance");
              if(idCounter==iddoleance){
                  System.out.println("id already exist");
                  idExists=true;
              }else{
                  idExists=false;
              }
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }


}
