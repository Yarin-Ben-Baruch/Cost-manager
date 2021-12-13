package il.ac.hit;

/**
 * A class that maintains the existing categories in the expense tracking software.
 */

public class Category {

    private String categoryName;

    /**
     * A constructor that updates the category in DB.
     * @param category
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
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }
}
