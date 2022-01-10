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
    public Item(int costNumber, String name, String description, String currency, Category category, String sum, Date date, String userName) throws CostManagerException {
        setCostNumber(costNumber);
        setName(name);
        setDescription(description);
        setCurrency(currency);
        setCategory( category);
        setSum(sum);
        setDate(date);
        setUsername(userName);
    }

    /**
     * basic getter.
     * @return int(costNumber).
     */
    public int getCostNumber() {
        return costNumber;
    }

    /**
     * basic getter.
     * @return String(name).
     */
    public String getName() {
        return name;
    }

    /**
     * basic getter.
     * @return String(description).
     */
    public String getDescribing() {
        return description;
    }

    /**
     * basic getter.
     * @return String(currency).
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * basic getter.
     * @return Category(category).
     */
    public Category getCategory() {
        return category;
    }

    /**
     * basic getter.
     * @return String(sum).
     */
    public String getSum() {
        return sum;
    }

    /**
     * basic getter.
     * @return Date(date).
     */
    public Date getDate() {
        return date;
    }

    /**
     * basic getter.
     * @return String(username).
     */
    public String getUserName() {
        return username;
    }

    // Basic Setter.
    private void setCostNumber(int i_CostNumber) {
        this.costNumber = i_CostNumber;
    }

    // Setter that checks if the figure is empty, extra and also throws.
    private void setName(String name) throws CostManagerException {
        if(name.isEmpty()){
            throw new CostManagerException("Can't add with empty name");
        }
        else {
            this.name = name;
        }
    }

    // Setter that checks if the figure is empty, extra and also throws.
    private void setDescription(String description) throws CostManagerException {

        if(description.isEmpty()){
            throw new CostManagerException("Can't add with empty description");
        }
        else {
            this.description = description;
        }
    }

    // Basic Setter.
    private void setCurrency(String currency) {
        this.currency = currency;
    }

    // Basic Setter.
    private void setCategory(Category category) {
        this.category = category;
    }

    // Setter that checks if the figure is empty, extra and also throws.
    private void setSum(String sum) throws CostManagerException {
        if(sum.isEmpty()){
            throw new CostManagerException("Can't add with empty sum");
        }
        else {
            this.sum = sum;
        }
    }

    // Basic Setter.
    private void setDate(Date date) {
        this.date = date;
    }

    // Basic Setter.
    private void setUsername(String username) {
        this.username = username;
    }

    /**
     * basic override to hashCode.
     * @return Objects.hash(costNumber, name, description, currency, category, sum, date, username).
     */
    @Override
    public int hashCode() {
        return Objects.hash(costNumber, name, description, currency, category, sum, date, username);
    }

    /**
     * override to equals, base on Item.
     * @param o is Item object.
     * @return equals or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return costNumber == item.costNumber && Objects.equals(name, item.name) && Objects.equals(description, item.description) &&
                Objects.equals(currency, item.currency) && Objects.equals(category, item.category) && Objects.equals(sum, item.sum) &&
                Objects.equals(date, item.date) && Objects.equals(username, item.username);
    }
}
