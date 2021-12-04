package il.ac.hit;

import java.sql.Date;

public class Item {

    private int id;
    private String name;
    private String describing;
    private String currency;
    private String category;
    private String sum;
    private java.sql.Date date;

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

    public Item(int id, String name, String describing, String currency, String category, String sum, java.sql.Date date) {
        this.id = id;
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
        return "il.ac.hit.Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", describing='" + describing + '\'' +
                ", currency='" + currency + '\'' +
                ", category='" + category + '\'' +
                ", sum='" + sum + '\'' +
                ", date=" + date +
                '}';
    }
}
