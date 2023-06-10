package org.example.storage;

import org.example.organization.*;
import java.sql.*;
import java.util.*;
import java.util.Date;


public class Database {

    private static final String DB_URL = "jdbc:postgresql://db:5432/studs";
    private static final String USER = "s367217";
    private static final String PASSWORD = "CEHo95y0T65VQNYQ";

    private static Database database;
    private final Connection connection;

    private Database() {
        this.connection = connectToDatabase();
    }

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    private Connection connectToDatabase() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            return connect;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Problems with creating connection!");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * метод для инициализации базы данных
     */
    public void init() {
        try {
    Statement stat = getConnection().createStatement();
    stat.executeUpdate("CREATE TYPE organizationtype AS ENUM ('COMMERCIAL', 'PUBLIC', 'TRUST');");
    stat.executeUpdate("CREATE TABLE IF NOT EXISTS organizationcatalog(\n" +
            "id serial primary key, \n" +
            "name text not NULL,\n" +
            "coordinates_x float,\n" +
            "coordinates_y INTEGER CHECK (coordinates_y < 274),\n" +
            "creationDate TIMESTAMP WITH time zone not NULL,\n" +
            "annualTurnover INTEGER CHECK (annualTurnover >= 0), \n" +
            "orgType OrganizationType not NULL, \n" +
            "officialAddress_street text not NULL CHECK (char_length(officialAddress_street)<83),\n" +
            "officialAddress_town_x float,\n" +
            "officialAddress_town_y float,\n" +
            "officialAddress_town_z INTEGER,\n" +
            "creator text not NULL)");
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS users(login text primary key, password text)");
        }
        catch(SQLException e) {
        }
}

    /**
     * метод для загрузки коллекции
     * @return ArrayDeque<Organization>
     * @throws SQLException
     */
    public ArrayDeque<Organization> getAllOrganizations() throws SQLException {
        Connection conn = connectToDatabase();
        ArrayDeque<Organization> organizations = new  ArrayDeque<Organization>();
        final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM organizationcatalog";
        PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_ORGANIZATIONS); {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                float coordinates_x = resultSet.getFloat("coordinates_x");
                long coordinates_y = resultSet.getLong("coordinates_y");
                Date creationDate = resultSet.getTimestamp("creationdate");
                long annualTurnover = resultSet.getLong("annualturnover");
                OrganizationType organizationType = null;
                if (resultSet.getString("orgtype") != null) {
                    organizationType = OrganizationType.valueOf(resultSet.getString("orgtype"));
                }
                String officialaddress_street = resultSet.getString("officialaddress_street");
                double officialaddress_town_x = resultSet.getDouble("officialaddress_town_x");
                double officialaddress_town_y = resultSet.getDouble("officialaddress_town_y");
                int officialaddress_town_z = resultSet.getInt("officialaddress_town_z");
                String creator = resultSet.getString("creator");
                Coordinates coordinates = new Coordinates(coordinates_x, coordinates_y);
                Address officialAddress = new Address(officialaddress_street, new Location(officialaddress_town_x, officialaddress_town_y, officialaddress_town_z));
                Organization organization = new Organization(id, name, coordinates,  creationDate,  annualTurnover, organizationType,  officialAddress, creator);
                organization.setSaved();
                organizations.add(organization);
            }
        }

        return organizations;
    }

}


