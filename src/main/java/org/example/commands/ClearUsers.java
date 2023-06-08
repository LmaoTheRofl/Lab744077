package org.example.commands;


import org.example.storage.Database;
import java.sql.*;


public class ClearUsers implements Command{

    /**
     * очистить базу пользователей
     * @return saved
     */
    @Override
    public String execute() {
        try{ Connection connection = Database.getInstance().getConnection();
            String truncateQuery = "TRUNCATE TABLE users";
            PreparedStatement truncateStatement = connection.prepareStatement(truncateQuery);
            truncateStatement.executeUpdate(); // очищаем таблицу
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "cleared";
    }

    @Override
    public String getCommandName() {
        return "clear_users";
    }

}

