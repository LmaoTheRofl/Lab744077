package org.example.storage;
import org.example.organization.*;

import java.sql.*;
import java.util.*;

public class RecordsDao {



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
               else{System.out.println("Неверный пароль");
                   register();}
           }
           else {
              return "PRIVET";
           }
        }
       catch (SQLException e) {e.printStackTrace();}
        return "Welcome!";
    }
public static String sign_in(String x) {
    Scanner scanner = new Scanner(System.in);
        if(x.equals("Welcome!")) {
            while (true) {
                System.out.println("Введите пароль");
                String c = getName(scanner).trim();
                if(c.equals(User.password)){
                return"PRIVET!";}
            }

        }
        return "";
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