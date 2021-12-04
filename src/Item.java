import javax.xml.crypto.Data;
import java.sql.Date;

public class Item {

    private int id;
    private String name;
    private String describing;
    private String currency;
    private String category;
    private String sum;
    private java.sql.Date date;
    //java.sql.Date date = new java.sql.Date(0000-00-00);
    //date = java.sql.Date.valueOf("2013-09-04");
    //System.out.println(new java.sql.Date(System.currentTimeMillis()));


    public Item() {
    }

    public Item(String name, String describing, String currency, String category, String sum, java.sql.Date date) {
        this.name = name;
        this.describing = describing;
        this.currency = currency;
        this.category = category;
        this.sum = sum;
        this.date = date;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescribing() {
        return describing;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCategory() {
        return category;
    }

    public String getSum() {
        return sum;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", describing='" + describing + '\'' +
                ", currency='" + currency + '\'' +
                ", category='" + category + '\'' +
                ", sum='" + sum + '\'' +
                ", date=" + date +
                '}';
    }

}
