package il.ac.hit;

import java.sql.Date;
import java.util.Objects;

public class Item {


    private int costNumber;
    private String name;
    private String describing;
    private String currency;
    private Category category;
    private String sum;
    private java.sql.Date date;
    private String userName;

    public Item() {

    }

    // add  set
    public Item(int costNumber, String name, String describing, String currency, Category category, String sum, Date date, String userName) {
        this.costNumber = costNumber;
        this.name = name;
        this.describing = describing;
        this.currency = currency;
        this.category = category;
        this.sum = sum;
        this.date = date;
        this.userName = userName;
    }

    public int getCostNumber() {
        return costNumber;
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

    public Category getCategory() {
        return category;
    }

    public String getSum() {
        return sum;
    }

    public Date getDate() {
        return date;
    }

    public String getUserName() {
        return userName;
    }

    //עודכן תוך כדי שיעור
    @Override
    public int hashCode() {
        return Objects.hash(costNumber, name, describing, currency, category, sum, date, userName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return costNumber == item.costNumber && Objects.equals(name, item.name) && Objects.equals(describing, item.describing) &&
                Objects.equals(currency, item.currency) && Objects.equals(category, item.category) && Objects.equals(sum, item.sum) &&
                Objects.equals(date, item.date) && Objects.equals(userName, item.userName);
    }

    @Override
    public String toString() {
        return "Item{" +
                "costNumber=" + costNumber +
                ", name='" + name + '\'' +
                ", describing='" + describing + '\'' +
                ", currency='" + currency + '\'' +
                ", category=" + category.getCategoryName() +
                ", sum='" + sum + '\'' +
                ", date=" + date +
                ", userName='" + userName + '\'' +
                '}';
    }
}
