package org.example.storage;
import org.example.organization.*;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class RecordsDao {


    public static int createOrganization(Organization organization) {
        int generatedId = -1;
        Connection connection = Database.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO " +   "organizationcatalog " +"(name, coordinates_x, coordinates_y, creationdate, annualturnover, orgtype, officialaddress_street, officialaddress_town_x, officialaddress_town_y, officialaddress_town_z, creator) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")){
             statement.setString(1, organization.getName());
             statement.setFloat(2, organization.getCoordinates().getX());
             statement.setLong(3, organization.getCoordinates().getY());
             Date creationDate = organization.getCreationDate();
            Timestamp timestamp = new Timestamp(creationDate.getTime());
             statement.setTimestamp(4, timestamp);
             statement.setLong(5, organization.getAnnualTurnover());
             statement.setObject(6, organization.getType().name(), Types.OTHER);
             statement.setString(7, organization.getOfficialAddress().getStreet());
             statement.setDouble(8, organization.getOfficialAddress().getTown().getX());
             statement.setDouble(9, organization.getOfficialAddress().getTown().getY());
             statement.setInt(10, organization.getOfficialAddress().getTown().getZ());
             statement.setString(11, organization.getCreator());
             statement.executeUpdate();
            Statement selectStatement = connection.createStatement();
            try (ResultSet rs = selectStatement.executeQuery("SELECT MAX(id) FROM organizationcatalog")) {
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
}
    public static String register() {
        Scanner scanner = new Scanner(System.in);
       try{ Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO users" +
                        "(login, password)" +
                        "VALUES (?, ?)");

        Hasher hasher = new Hasher("SHA-512");
           System.out.println("Введите логин");
           User.login  = getName(scanner).trim();
           System.out.println("Введите пароль");
           User.password= getName(scanner).trim();
           if(!checkPassword(User.login, hasher.encode(User.password))) {
               if(!checkLogin(User.login)) {
                   statement.setString(1, User.login);
                   statement.setString(2, hasher.encode(User.password));
                   int rowsAffected = statement.executeUpdate();
                   if (rowsAffected == 0) {
                       throw new SQLException("Registring failed, no rows affected.");
                   }
               }
               else{System.out.println("Логин уже занят");
                   register();}
           }
           else {
              return "PRIVET";
           }
        }
       catch (SQLException e) {e.printStackTrace();}
        return "Welcome!";
    }

    public static boolean checkLogin(String login){
        try{
            Connection connection = Database.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ?");
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return false;
    }
    public static boolean checkPassword(String login, String pwd){
        try{
            Connection connection = Database.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?");
            statement.setString(1, login);
            statement.setString(2, pwd);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return false;
    }
    private static String getName(Scanner scanner) {
        while (true) {
            String s = scanner.nextLine();
            if (s != null && !s.isEmpty()) {
                String[] x = s.split(" ");
                if(x.length>=1) {
                    return s; }
            } System.out.println("Incorrect input!");

        }
    }

}