package PFA.ModifierInfo.Services;

import PFA.ModifierInfo.Module.information;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class InfoServices {
    public static information fetchInfo() {
        String query = "select * from INFORMATION_MUNICIPALITE where id = 1";
        information info = null;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            res.next();
            info = new information(res.getString("adresse"), res.getString("email"), res.getString("telephone"), res.getString("nom"));
            res.close();
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return info;
    }
    
    
    public static void update(information info) {
        String query = "update INFORMATION_MUNICIPALITE set nom = '" + info.getNom() + "',ADRESSE = '" + info.getAdresse() + "',TELEPHONE = '" + info.getTelephone() + "',EMAIL = '" + info.getEmail() + "' where id = 1";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
}
