/**
 *
 * @author Akhil
 */
public class Item {

    private long id;
    private String name;
    private String desciption;
    private double price;

    public Item() {
    }

    public Item(long id, String name, String desciption, double price) {
        this.id = id;
        this.name = name;
        this.desciption = desciption;
        this.price = price;
    }

    public String getDesciption() {
        return desciption;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
