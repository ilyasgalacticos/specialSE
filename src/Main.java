import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static DBManager dbManager = new DBManager();

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while(true){

            System.out.println("PRESS 1 TO ADD PLAYER");
            System.out.println("PRESS 2 TO LIST PLAYERS");
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
            }else{
                System.out.println("Wrong command!");
            }

        }

    }
}
