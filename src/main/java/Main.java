import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.sql.*;
import java.util.UUID;

/**
 * Создать в ней таблицу "cities" с колонками id - TEXT
 * (использовать класс java.util.UUID для заполнения этой колонки),
 * name - TEXT. Первчиный ключ - id. Имена городов не должны повторяться,
 * добавить ограничение колонке.
 * Написать неизменяемый класс City с полями id и name.
 * Написать класс CitiesService с методами addCity(City) и getCities(),
 * который возращает список городов.
 * В Main классе добавить несколько городов
 * (можно сделать ввод названия города с клавиатуры),
 * вычитать их и напечатать в консоль.
 */
public class Main {

    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        City city = new City(UUID.randomUUID(), "hhh");
        for(int i = 0; i<3;i++){
            addManualy(reader, city);

        }

        CitiesService citiesService = new CitiesService();
        citiesService.getCities();



        Connection connection = null;

        try {

            String dbURL = "jdbc:sqlite:/Users/viktoriakuznichenko/homework.db";


            connection = DriverManager.getConnection(dbURL);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.


            statement.executeUpdate("create table if not exists cities(id text primary key, name text unique)");
            statement.executeUpdate("insert into cities values('" + city.id + "', '" + city.name + "');");

            ResultSet rs = statement.executeQuery("select * from cities");
            while (rs.next()) {
                // read the result set
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }

    public static void addManualy(BufferedReader reader, City city) throws IOException {
         CitiesService citiesService = new CitiesService();

        city.name = reader.readLine();
        citiesService.addCity(city);
        }
    }





