import javax.xml.crypto.Data;
import java.util.Date;

public class Item {

    private int id;
    private String name;
    private String currency;
    private double sum;
    private String describing;
    private Category category;
    private Date date;

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

    public Category getCategoryObject() {
        return category;
    }

    public Date getDate() {
        return date;
    }
}
