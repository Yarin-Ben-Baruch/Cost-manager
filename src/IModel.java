import java.util.Collection;

public interface IModel {
    public void addItem(Item item) throws CostMangerException;
    public Collection<Item> getItems() throws CostMangerException;
    public void addNewCategory(Category category) throws CostMangerException;
}
