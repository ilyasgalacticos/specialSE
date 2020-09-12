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
                    "INSERT INTO players (id, name, surname, club, transfer_price) " +
                    "VALUES (NULL, ?, ?, ?, ?)");

            statement.setString(1, player.getName());
            statement.setString(2, player.getSurname());
            statement.setString(3, player.getClub());
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
                    "SELECT id, name, surname, club, transfer_price FROM players");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                players.add(new Player(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("club"),
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
                    "SELECT id, name, surname, club, transfer_price FROM players WHERE id = ? LIMIT 1");

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                player = new Player(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("club"),
                        resultSet.getDouble("transfer_price")
                );
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
                    "UPDATE players SET name = ?, surname = ?, club = ?, transfer_price = ? " +
                    "WHERE id = ? ");

            statement.setString(1, player.getName());
            statement.setString(2, player.getSurname());
            statement.setString(3, player.getClub());
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

}
