package il.ac.hit.Model;

import java.sql.Date;
import java.util.Objects;

/**
 * A class that holds the existing table data in the database related to the user's expense tracking data.
 */
public class Item {

    private int costNumber;
    private String name;
    private String describing;
    private String currency;
    private Category category;
    private String sum;
    private java.sql.Date date;
    private String userName;

    /**
     * A constructor that updates the user expense information.
     * @param costNumber
     * @param name
     * @param describing
     * @param currency
     * @param category
     * @param sum
     * @param date
     * @param userName
     */
    public Item(int costNumber, String name, String describing, String currency, Category category, String sum, Date date, String userName) {
        setCostNumber(costNumber);
        setName(name);
        setDescribing(describing);
        setCurrency(currency);
        setCategory( category);
        setSum(sum);
        setDate(date);
        setUserName(userName);
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

    private void setCostNumber(int costNumber) {
        this.costNumber = costNumber;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setDescribing(String describing) {
        this.describing = describing;
    }

    private void setCurrency(String currency) {
        this.currency = currency;
    }

    private void setCategory(Category category) {
        this.category = category;
    }

    private void setSum(String sum) {
        this.sum = sum;
    }

    private void setDate(Date date) {
        this.date = date;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

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
        return costNumber +
                "." + name +
                "\ndescribing: " + describing +
                "\ncost: " + sum +
                " " + currency +
                " category= " + category.getCategoryName() +
                " date=" + date +
                " userName=" + userName;
    }
}
