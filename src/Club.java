public class Club {

    private Long id;
    private String name;
    private City city;

    public Club(Long id, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public Club(){

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
