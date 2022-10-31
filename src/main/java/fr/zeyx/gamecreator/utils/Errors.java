package fr.zeyx.gamecreator.utils;

import fr.zeyx.gamecreator.GameCreator;

import java.util.logging.Level;

public class Errors {

    public static void execute(Exception exception){
        GameCreator.getInstance().getLogger().log(Level.SEVERE, "Couldn't execute MySQL statement: ", exception);
    }

    public static void close(Exception exception){
        GameCreator.getInstance().getLogger().log(Level.SEVERE, "Failed to close MySQL connection: ", exception);
    }

    public static String sqlConnectionExecute(){
        return "Couldn't execute MySQL statement: ";
    }

    public static String sqlConnectionClose(){
        return "Failed to close MySQL connection: ";
    }

    public static String noSQLConnection(){
        return "Unable to retreive MYSQL connection: ";
    }

    public static String noTableFound(){
        return "Database Error: No Table Found";
    }

    public static void severeError(String errorMessage, Exception exception) {
        GameCreator.getInstance().getLogger().log(Level.SEVERE, errorMessage, exception);
    }

    public static void severeError(String errorMessage) {
        GameCreator.getInstance().getLogger().log(Level.SEVERE, errorMessage);
    }
}
