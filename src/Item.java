import javax.xml.crypto.Data;

public class Item {

    private int id;
    private String name;
    private String currency;
    private double sum;
    private String describing;
    private Category category;
    private Data data;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public double getSum() {
        return sum;
    }

    public String getDescribing() {
        return describing;
    }

    public Category getCategory() {
        return category;
    }

    public Data getData() {
        return data;
    }
}
