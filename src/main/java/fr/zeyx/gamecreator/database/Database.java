package fr.zeyx.gamecreator.database;

import fr.zeyx.gamecreator.utils.Errors;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("ALL")
public abstract class Database {

    Connection connection;
    public String table = "function_block";
    public int tokens = 0;

    public abstract Connection getSQLConnection();

    public abstract void load();

    public void initialize(){
        connection = getSQLConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + table + " WHERE loc = ?");
            ResultSet resultSet = preparedStatement.executeQuery();
            close(preparedStatement, resultSet);
        } catch (SQLException exception) {
            Errors.severeError("Unable to retrieve connection.", exception);
        }
    }

    public void setValues(Location location, String type, String data) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = getSQLConnection();
            preparedStatement = conn.prepareStatement("REPLACE INTO " + table + " (loc,type,data) VALUES(?,?,?)");
            preparedStatement.setString(1, location.toString());
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, data);
            preparedStatement.executeUpdate();
            return;
        } catch (SQLException exception) {
            Errors.severeError("Unable to retreive MYSQL connection: ", exception);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                Errors.severeError("Failed to close MySQL connection: ", ex);
            }
        }
    }

    public String getType(Location location) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = getSQLConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM " + table + " WHERE loc = '" + location + "';");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                if (resultSet.getString("loc").equalsIgnoreCase(location.toString())) {
                    return resultSet.getString("type");
                }
            }
        } catch (SQLException exception) {
            Errors.severeError("Unable to retreive MYSQL connection: ", exception);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                Errors.severeError("Failed to close MySQL connection: ", ex);
            }
        }
        return "";
    }

    public void close(PreparedStatement preparedStatement, ResultSet resultSet){
        try {
            if (preparedStatement != null) preparedStatement.close();
            if (resultSet != null) resultSet.close();
        } catch (SQLException exception) {
            Errors.severeError("Failed to close MySQL connection: ", exception);
        }
    }

}
