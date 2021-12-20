package il.ac.hit.model;

import java.sql.Date;
import java.util.Objects;

/**
 * A class that holds the existing table data in the database related to the user's expense tracking data.
 */
public class Item {

    private int m_CostNumber;
    private String m_Name;
    private String m_Describing;
    private String m_Currency;
    private Category m_Category;
    private String m_Sum;
    private java.sql.Date m_Date;
    private String m_Username;

    /**
     * A constructor that updates the user expense information.
     * @param i_CostNumber
     * @param i_Name
     * @param i_Describing
     * @param i_Currency
     * @param i_Category
     * @param i_Sum
     * @param i_Date
     * @param i_Username
     */
    public Item(int i_CostNumber, String i_Name, String i_Describing, String i_Currency, Category i_Category, String i_Sum, Date i_Date, String i_Username) {
        setCostNumber(i_CostNumber);
        setName(i_Name);
        setDescribing(i_Describing);
        setCurrency(i_Currency);
        setCategory( i_Category);
        setSum(i_Sum);
        setDate(i_Date);
        setUserName(i_Username);
    }


    public int getCostNumber() {
        return m_CostNumber;
    }

    public String getName() {
        return m_Name;
    }

    public String getDescribing() {
        return m_Describing;
    }

    public String getCurrency() {
        return m_Currency;
    }

    public Category getCategory() {
        return m_Category;
    }

    public String getSum() {
        return m_Sum;
    }

    public Date getDate() {
        return m_Date;
    }

    public String getUserName() {
        return m_Username;
    }

    private void setCostNumber(int i_CostNumber) {
        this.m_CostNumber = i_CostNumber;
    }

    private void setName(String i_Name) {
        this.m_Name = i_Name;
    }

    private void setDescribing(String i_Describing) {
        this.m_Describing = i_Describing;
    }

    private void setCurrency(String i_Currency) {
        this.m_Currency = i_Currency;
    }

    private void setCategory(Category i_Category) {
        this.m_Category = i_Category;
    }

    private void setSum(String i_Sum) {
        this.m_Sum = i_Sum;
    }

    private void setDate(Date i_Date) {
        this.m_Date = i_Date;
    }

    private void setUserName(String i_Username) {
        this.m_Username = i_Username;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_CostNumber, m_Name, m_Describing, m_Currency, m_Category, m_Sum, m_Date, m_Username);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return m_CostNumber == item.m_CostNumber && Objects.equals(m_Name, item.m_Name) && Objects.equals(m_Describing, item.m_Describing) &&
                Objects.equals(m_Currency, item.m_Currency) && Objects.equals(m_Category, item.m_Category) && Objects.equals(m_Sum, item.m_Sum) &&
                Objects.equals(m_Date, item.m_Date) && Objects.equals(m_Username, item.m_Username);
    }

    @Override
    public String toString() {
        return m_CostNumber +
                "." + m_Name +
                "\ndescribing: " + m_Describing +
                "\ncost: " + m_Sum +
                " " + m_Currency +
                " category= " + m_Category.getCategoryName() +
                " date=" + m_Date +
                " userName=" + m_Username;
    }
}
