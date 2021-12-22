package il.ac.hit.model;

import java.util.Objects;

/**
 * A class that maintains the existing categories in the expense tracking software.
 */
public class Category {

    private String categoryName;

    /**
     * A constructor that updates the i_Category in DB.
     * @param category Category reboot the constructor
     */
    public Category(String category) {
        setCategoryName(category);
    }

    public String getCategoryName() {
        return categoryName;
    }

    private void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryName, category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }
}