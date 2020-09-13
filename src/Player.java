public class Player {

    private Long id;
    private String name;
    private String surname;
    private Club club;
    private double price;

    public Player(Long id, String name, String surname, Club club, double price) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.club = club;
        this.price = price;
    }

    public Player() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.id + " " + this.name + " " + this.surname + " [" + this.club.getName() + " " + this.club.getCity().getName() + "-" + this.club.getCity().getCountry() +"] " + (int)this.price;
    }
}
