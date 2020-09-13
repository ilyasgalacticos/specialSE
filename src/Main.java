import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static DBManager dbManager = new DBManager();

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while(true){

            System.out.println("-----------| PLAYERS |-------------");
            System.out.println("PRESS 1 TO ADD PLAYER");
            System.out.println("PRESS 2 TO LIST PLAYERS");
            System.out.println("PRESS 3 TO EDIT PLAYER");
            System.out.println("PRESS 4 TO DELETE PLAYER");
            System.out.println("-----------| CLUBS |-------------");
            System.out.println("PRESS 5 TO ADD CLUB");
            System.out.println("PRESS 6 TO LIST CLUBS");
            System.out.println("PRESS 7 TO EDIT CLUB");
            System.out.println("PRESS 8 TO DELETE CLUB");
            System.out.println("-----------| CITIES |-------------");
            System.out.println("PRESS 9 TO ADD CITY");
            System.out.println("PRESS 10 TO LIST CITIES");
            System.out.println("PRESS 11 TO EDIT CITY");
            System.out.println("PRESS 12 TO DELETE CITY");
            System.out.println("PRESS 0 TO EXIT");

            String choice = in.next();

            if(choice.equals("1")){

                System.out.println("Insert name: ");
                String name = in.next();
                System.out.println("Insert surname: ");
                String surname = in.next();

                System.out.println("Transfer price: ");
                double price = 0;

                try {
                   price = Double.parseDouble(in.next());
                }catch (Exception e){
                }

                System.out.println("Choose club id: ");

                ArrayList<Club> clubs = dbManager.getAllClubs();
                for(Club c : clubs){
                    System.out.println(c.getId() + " " + c.getName() + " " + c.getCity().getName() + " - " + c.getCity().getCountry());
                }

                Long clubId = in.nextLong();

                Club chosenClub = dbManager.getClub(clubId);

                if(chosenClub!=null) {

                    Player player = new Player(null, name, surname, chosenClub, price);
                    if (dbManager.addPlayer(player)) {
                        System.out.println("Player added successfully!");
                    } else {
                        System.out.println("Couldn't add player");
                    }
                }

            }else if(choice.equals("2")){

                ArrayList<Player> players = dbManager.getAllPlayers();
                for(Player p : players){
                    System.out.println(p);
                }

            }else if(choice.equals("0")){

                System.exit(0);

            }else if(choice.equals("3")){

                ArrayList<Player> players = dbManager.getAllPlayers();
                for(Player p : players){
                    System.out.println(p);
                }

                System.out.println("Choose ID of PLAYER TO UPDATE: ");
                Long id = in.nextLong();

                Player player = dbManager.getPlayer(id);

                if(player!=null){

                    System.out.println("Insert new name: ");
                    String name = in.next();
                    System.out.println("Insert new surname: ");
                    String surname = in.next();
                    System.out.println("Insert new club: ");
                    String club = in.next();
                    double price = 0;
                    try {
                        price = Double.parseDouble(in.next());
                    }catch (Exception e){

                    }

                    player.setName(name);
                    player.setSurname(surname);
                    //player.setClub(club);
                    player.setPrice(price);

                    System.out.println("PRESS 1 TO SAVE PLAYER");
                    System.out.println("PRESS 0 TO GO BACK");

                    choice = in.next();

                    while(true) {

                        if (choice.equals("1")) {
                            if(dbManager.updatePlayer(player)){
                                System.out.println("Player saved successfully!");
                                break;
                            }
                        } else if (choice.equals("0")) {
                            break;
                        } else {
                            System.out.println("Wrong command!");
                        }

                    }

                }else{

                    System.out.println("Player not found!");

                }

            }else if(choice.equals("4")){

                ArrayList<Player> players = dbManager.getAllPlayers();
                for(Player p : players){
                    System.out.println(p);
                }

                System.out.println("Choose ID of PLAYER TO DELETE: ");
                Long id = in.nextLong();

                Player player = dbManager.getPlayer(id);

                if(player!=null){

                    if(dbManager.deletePlayer(player.getId())){
                        System.out.println("Player deleted successfully!");
                    }

                }else{

                    System.out.println("Player not found!");

                }

            }else if(choice.equals("9")){

                System.out.println("INSERT NAME: ");
                String name = in.next();
                System.out.println("INSERT COUNTRY: ");
                String country = in.next();

                dbManager.addCity(new City(null, name, country));

            }else if(choice.equals("10")){

                ArrayList<City> cities = dbManager.getAllCities();
                for(City c : cities){
                    System.out.println(c.getId() + " " + c.getName() + " " + c.getCountry());
                }

            }else if(choice.equals("5")){

                System.out.println("INSERT NAME: ");
                String name = in.next();
                System.out.println("CHOOSE CITY ID: ");
                ArrayList<City> cities = dbManager.getAllCities();
                for(City c : cities){
                    System.out.println(c.getId() + " " + c.getName() + " " + c.getCountry());
                }

                Long cityId = in.nextLong();
                City chosenCity = dbManager.getCity(cityId);

                if(chosenCity!=null){
                    dbManager.addClub(new Club(null, name, chosenCity));
                    System.out.println("CLUB ADDED SUCCESSFULLY!");
                }else{
                    System.out.println("Incorrect city!");
                }

            }else if(choice.equals("6")){

                ArrayList<Club> clubs = dbManager.getAllClubs();
                for(Club c : clubs){
                    System.out.println(c.getId() + " " + c.getName() + " " + c.getCity().getName() + " - " + c.getCity().getCountry());
                }

            }else{
                System.out.println("Wrong command!");
            }

        }

    }
}
