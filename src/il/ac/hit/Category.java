package il.ac.hit;

public class Category {

    private String categoryName;

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
