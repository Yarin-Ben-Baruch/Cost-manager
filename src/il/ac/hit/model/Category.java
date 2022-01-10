package il.ac.hit.model;

import java.util.Objects;

/**
 * A class that maintains the existing categories in the expense tracking software.
 */
public class Category {

    private String categoryName;
    /**
     * A constructor that updates the i_Category in DB.
     * @param category the category name.
     */
    public Category(String category) throws CostManagerException {
        setCategoryName(category);
    }

    /**
     * basic getter.
     * @return string ( categoryName ).
     */
    public String getCategoryName() {
        return categoryName;
    }

    private void setCategoryName(String categoryName) throws CostManagerException {
        if(categoryName.isEmpty())
        {
            throw new CostManagerException("Can't add empty category" );
        }
        else {
            this.categoryName = categoryName.toLowerCase();
        }
    }

    /**
     * override to equals, base on categoryName.
     * @param o is Category object.
     * @return equals or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryName, category.categoryName);
    }

    /**
     * basic override to hashCode.
     * @return Objects.hash(categoryName).
     */
    @Override
    public int hashCode() {
        return Objects.hash(categoryName);
    }
}
