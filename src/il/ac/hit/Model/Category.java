package il.ac.hit.Model;

import java.util.Objects;

/**
 * A class that maintains the existing categories in the expense tracking software.
 */

public class Category {

    private String m_CategoryName;

    /**
     * A constructor that updates the i_Category in DB.
     * @param i_Category
     */
    public Category(String i_Category) {
        setCategoryName(i_Category);
    }

    public String getCategoryName() {
        return m_CategoryName;
    }

    private void setCategoryName(String i_CategoryName) {
        this.m_CategoryName = i_CategoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(m_CategoryName, category.m_CategoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_CategoryName);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + m_CategoryName + '\'' +
                '}';
    }
}
