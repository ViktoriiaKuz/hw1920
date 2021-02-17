
import java.sql.*;


public class CitiesService {


   private Connection connection;

    CitiesService(Connection connection){
        this.connection = connection;
    }


    public void addCity(City city) throws SQLException {


            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate("create table if not exists cities(id text primary key, name text unique)");

            statement.executeUpdate("insert into cities values('" + city.id + "', '" + city.name + "');");

    }

        public void getCity () throws SQLException {

                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
                ResultSet rs = statement.executeQuery("select * from cities");
                while (rs.next()) {
                    // read the result set
                    System.out.println("name = " + rs.getString("name"));
                    System.out.println("id = " + rs.getInt("id"));
                }

        }
    }

