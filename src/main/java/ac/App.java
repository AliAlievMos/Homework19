package ac;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class App {

    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DATABASE_URL = "jdbc:sqlite:my.db";
    public static String table;
    public static String action;
    public static String id;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("С какой таблицей будем работать?");
        table = sc.next();


        Connection connection = null;
        Statement statement = null;

        System.out.println("Registering JDBC driver...");

        Class.forName(JDBC_DRIVER);

        System.out.println("Creating database connection...");
        connection = DriverManager.getConnection(DATABASE_URL);

        System.out.println("Executing statement...");
        statement = connection.createStatement();


        menu(statement);






        System.out.println("Closing connection and releasing resources...");
        statement.close();
        connection.close();
        sc.close();
    }

    public static void menu(Statement statement) throws SQLException {
        System.out.println("1. Вывети таблицу\n2. Удалить эллемент");
        Scanner sc = new Scanner(System.in);
        action = sc.next();
        if (Objects.equals(action, "1")){
            showTable(statement);
        }
        if (Objects.equals(action, "2")){
            deleteItem(statement);
        }
    }
    public static void deleteItem(Statement st) throws SQLException {

        String sql;
        Scanner sc = new Scanner(System.in);
        System.out.println("Что удалить?(введите id)");
        id = sc.next();

        sql = "DELETE FROM " + table + " WHERE id=" + "'"+id+"'";

        try{
            Statement statement = st;
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.deleteRow();
        }
        catch (java.sql.SQLException e){
        }
        st.close();

    }

    public static void showTable(Statement st) throws SQLException {

        String sql;
        sql = "SELECT * FROM " + table;

        Statement statement = st;
        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("Retrieving data from database...");
        System.out.println("\n" + table + ":");
        if (Objects.equals(table, "shops")) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int adress = resultSet.getInt("adress");
                int id = resultSet.getInt("id");
                System.out.println("Name: " + name);
                System.out.println("Adress: " + adress);
                System.out.println("ID: " + id);
                System.out.println("\n================\n");
            }
        }
//            name TEXT, color TEXT, model TEXT, speed CHAR
        if (Objects.equals(table, "cars")) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String color = resultSet.getString("color");
                String model = resultSet.getString("model");
                int speed = resultSet.getInt("speed");
                int id = resultSet.getInt("id");
                System.out.println("Name: " + name);
                System.out.println("Сolor: " + color);
                System.out.println("Model: " + model);
                System.out.println("Speed: " + speed);
                System.out.println("ID: " + id);
                System.out.println("\n================\n");
            }
        }
        if (Objects.equals(table, "users")) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int money = resultSet.getInt("money");
                int birthday = resultSet.getInt("birthday");
                int id = resultSet.getInt("id");
                System.out.println("Name: " + name);
                System.out.println("Surname: " + surname);
                System.out.println("Money: " + money);
                System.out.println("Birthday: " + birthday);
                System.out.println("ID: " + id);
                System.out.println("\n================\n");
            }
        }
        resultSet.close();

    }
}