import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static DBManager dbManager = new DBManager();

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while(true){

            System.out.println("PRESS 1 TO ADD PLAYER");
            System.out.println("PRESS 2 TO LIST PLAYERS");
            System.out.println("PRESS 3 TO EDIT PLAYER");
            System.out.println("PRESS 4 TO DELETE PLAYER");
            System.out.println("PRESS 0 TO EXIT");

            String choice = in.next();

            if(choice.equals("1")){

                System.out.println("Insert name: ");
                String name = in.next();
                System.out.println("Insert surname: ");
                String surname = in.next();
                System.out.println("Insert club: ");
                String club = in.next();
                double price = 0;

                try {
                   price = Double.parseDouble(in.next());
                }catch (Exception e){
                }

                Player player = new Player(null, name, surname, club, price);
                if(dbManager.addPlayer(player)){
                    System.out.println("Player added successfully!");
                }else{
                    System.out.println("Couldn't add player");
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
                    player.setClub(club);
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

            }else{
                System.out.println("Wrong command!");
            }

        }

    }
}
