import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.UUID;

public class Main {


    private final static String dbURL = "jdbc:sqlite:/Users/viktoriakuznichenko/homework.db";
    private static Connection connection = null;

    static {
        try {
            connection = DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, SQLException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        for (int i = 0; i <3; i++) {
            City city = new City(UUID.randomUUID(), addManualy(reader));
            CitiesService citiesService = new CitiesService(connection);

          citiesService.addCity(city);
          citiesService.getCity();
        }

            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {

                System.err.println(e.getMessage());
            }

        }



    private static String addManualy(BufferedReader reader) throws IOException {
        String name = reader.readLine();
        return name;
    }
}







