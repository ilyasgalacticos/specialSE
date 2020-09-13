import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {

    private Connection connection;

    public DBManager(){
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/special_database?useUnicode=true&serverTimezone=UTC", "root", "");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean addPlayer(Player player){

        int rows = 0;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO players (id, name, surname, club_id, transfer_price) " +
                    "VALUES (NULL, ?, ?, ?, ?)");

            statement.setString(1, player.getName());
            statement.setString(2, player.getSurname());
            statement.setLong(3, player.getClub().getId());
            statement.setDouble(4, player.getPrice());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;

    }

    public ArrayList<Player> getAllPlayers(){

        ArrayList<Player> players = new ArrayList<>();
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT p.id, p.name, p.surname, p.transfer_price, p.club_id, c.name AS clubName, ci.id as cityId, ci.name AS cityName, ci.country " +
                    "FROM players p " +
                    "INNER JOIN clubs c ON c.id = p.club_id " +
                    "INNER JOIN cities ci ON c.city_id = ci.id  ");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                players.add(new Player(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        new Club(
                                resultSet.getLong("club_id"),
                                resultSet.getString("clubName"),
                                new City(
                                        resultSet.getLong("cityId"),
                                        resultSet.getString("cityName"),
                                        resultSet.getString("country")
                                )
                        ),
                        resultSet.getDouble("transfer_price")
                ));
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return players;
    }

    public Player getPlayer(Long id){

        Player player = null;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT p.id, p.name, p.surname, p.transfer_price, p.club_id, c.name AS clubName, ci.id as cityId, ci.name AS cityName, ci.country " +
                    "FROM players p " +
                    "INNER JOIN clubs c ON c.id = p.club_id " +
                    "INNER JOIN cities ci ON c.city_id = ci.id " +
                    "WHERE p.id = ? LIMIT 1");

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                player = new Player(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        new Club(
                                resultSet.getLong("club_id"),
                                resultSet.getString("clubName"),
                                new City(
                                        resultSet.getLong("cityId"),
                                        resultSet.getString("cityName"),
                                        resultSet.getString("country")
                                )
                        ),
                        resultSet.getDouble("transfer_price"));
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return player;
    }

    public boolean updatePlayer(Player player){

        int rows = 0;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE players SET name = ?, surname = ?, club_id = ?, transfer_price = ? " +
                    "WHERE id = ? ");

            statement.setString(1, player.getName());
            statement.setString(2, player.getSurname());
            statement.setLong(3, player.getClub().getId());
            statement.setDouble(4, player.getPrice());
            statement.setLong(5, player.getId());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;

    }

    public boolean deletePlayer(Long id){

        int rows = 0;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM players " +
                    "WHERE id = ? ");

            statement.setLong(1, id);

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;

    }

    public boolean addCity(City city){

        int rows = 0;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO cities (id, name, country) " +
                    "VALUES (NULL, ?, ?)");

            statement.setString(1, city.getName());
            statement.setString(2, city.getCountry());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;

    }

    public ArrayList<City> getAllCities(){

        ArrayList<City> cities = new ArrayList<>();
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM cities");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                cities.add(new City(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("country")
                ));
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return cities;
    }

    public City getCity(Long id){

        City city = null;

        try{

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM cities WHERE id = ?"
            );

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                city = new City(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("country")
                );
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return city;
    }

    public boolean addClub(Club club){

        int rows = 0;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO clubs (id, name, city_id) " +
                    "VALUES (NULL, ?, ?)");

            statement.setString(1, club.getName());
            statement.setLong(2, club.getCity().getId());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;

    }

    public ArrayList<Club> getAllClubs(){

        ArrayList<Club> clubs = new ArrayList<>();
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id, c.name, c.city_id, ci.name AS cityName, ci.country " +
                    "FROM clubs c " +
                    "INNER JOIN cities ci ON ci.id = c.city_id ");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                clubs.add(
                        new Club(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                new City(
                                        resultSet.getLong("city_id"),
                                        resultSet.getString("cityName"),
                                        resultSet.getString("country")
                                )
                        )
                );
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return clubs;
    }

    public Club getClub(Long id){

        Club club = null;

        try{

            PreparedStatement statement = connection.prepareStatement(
                            "SELECT c.id, c.name, c.city_id, ci.name AS cityName, ci.country " +
                            "FROM clubs c " +
                            "INNER JOIN cities ci ON ci.id = c.city_id WHERE c.id = ? "
            );

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                club = new Club(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        new City(
                                resultSet.getLong("city_id"),
                                resultSet.getString("cityName"),
                                resultSet.getString("country")
                        )
                );
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return club;
    }

}
