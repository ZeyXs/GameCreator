package fr.zeyx.gamecreator.database;

import fr.zeyx.gamecreator.GameCreator;
import fr.zeyx.gamecreator.utils.Errors;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("ALL")
public class SQLite extends Database {

    private String dbName;

    public SQLite() {
        dbName = "database";
    }

    public String SQLiteCreateTokensTable =
            "CREATE TABLE IF NOT EXISTS " + table + " (" +
            "`loc` VARCHAR(128) NOT NULL," +
            "`type` VARCHAR(32) NOT NULL," +
            "`data` VARCHAR(255) NOT NULL," +
            "PRIMARY KEY (`loc`)" +
            ");";

    public Connection getSQLConnection() {
        File dataFolder = new File(GameCreator.getInstance().getDataFolder(), dbName + ".db");
        if (!dataFolder.exists()) {
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                Errors.severeError("File write error: " + dbName + ".db");
            }
        }
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return connection;
        } catch (SQLException ex) {
            Errors.severeError("SQLite exception on initialize", ex);
        } catch (ClassNotFoundException ex) {
            Errors.severeError("You need the SQLite JBDC library.");
        }
        return null;
    }

    @Override
    public void load() {
        connection = getSQLConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQLiteCreateTokensTable);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initialize();
    }

}