package il.ac.hit;

public class Category {

    private String categoryName;

    public Category(String category) {
        this.categoryName = category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }
}
