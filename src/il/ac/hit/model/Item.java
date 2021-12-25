package il.ac.hit.model;

import java.sql.Date;
import java.util.Objects;

/**
 * A class that holds the existing table data in the database related to the user's expense tracking data.
 */
public class Item {

    private int costNumber;
    private String name;
    private String description;
    private String currency;
    private Category category;
    private String sum;
    private java.sql.Date date;
    private String username;

    /**
     * A constructor that updates the user expense information.
     * @param costNumber In what row is it in the table.
     * @param name Name of the person who added the record.
     * @param description Expenditure description.
     * @param currency In what currency the expenditure was made.
     * @param category To which category does the expense belong.
     * @param sum What is the amount of expenditure.
     * @param date On what date was the expenditure.
     * @param userName In which user the spend was made.
     */
    public Item(int costNumber, String name, String description, String currency, Category category, String sum, Date date, String userName) {
        setCostNumber(costNumber);
        setName(name);
        setDescription(description);
        setCurrency(currency);
        setCategory( category);
        setSum(sum);
        setDate(date);
        setUsername(userName);
    }

    public int getCostNumber() {
        return costNumber;
    }

    public String getName() {
        return name;
    }

    public String getDescribing() {
        return description;
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
        return username;
    }

    private void setCostNumber(int i_CostNumber) {
        this.costNumber = i_CostNumber;
    }

    public void setName(String name)  {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        return Objects.hash(costNumber, name, description, currency, category, sum, date, username);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return costNumber == item.costNumber && Objects.equals(name, item.name) && Objects.equals(description, item.description) &&
                Objects.equals(currency, item.currency) && Objects.equals(category, item.category) && Objects.equals(sum, item.sum) &&
                Objects.equals(date, item.date) && Objects.equals(username, item.username);
    }

    @Override
    public String toString() {
        return costNumber +
                "." + name +
                "\ndescribing: " + description +
                "\ncost: " + sum +
                " " + currency +
                " category= " + category.getCategoryName() +
                " date=" + date +
                " userName=" + username;
    }

    public void isItemEmpty() throws CostManagerException {

        if(this.name.isEmpty()){
            throw new CostManagerException("Can't add with empty name");
        }
        else if(this.description.isEmpty()){
            throw new CostManagerException("Can't add with empty description");
        }
        else if(this.sum.isEmpty()){
            throw new CostManagerException("Can't add with empty sum");
        }
    }
}
