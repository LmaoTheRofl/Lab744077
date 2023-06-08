package org.example.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.organization.Organization;
import org.example.storage.Collection;
import org.example.storage.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class Save implements Command{

    /**
     *  сохранить коллекцию в файл
     * @return saved
     */
    @Override
    public String execute() {
        try{ Connection connection = Database.getInstance().getConnection();
        String truncateQuery = "TRUNCATE TABLE organizationcatalog";
        PreparedStatement truncateStatement = connection.prepareStatement(truncateQuery);
        truncateStatement.executeUpdate(); // очищаем таблицу

        String insertQuery = "INSERT INTO " +   "organizationcatalog " +"(name, coordinates_x, coordinates_y, creationdate, annualturnover, orgtype, officialaddress_street, officialaddress_town_x, officialaddress_town_y, officialaddress_town_z, creator) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertQuery);

        for (Organization organization : Collection.getInstance().getAll()) {
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
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

        return "saved";
    }

    @Override
    public String getCommandName() {
        return "save";
    }

}
