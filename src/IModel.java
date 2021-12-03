import java.util.Collection;

public interface IModel {
    void addItem(Item item) throws CostMangerException;
    Collection<Item> getDetailedReport(Item[] item,String date) throws CostMangerException;
    void addNewCategory(Category category) throws CostMangerException;
    Collection<Item> getItems() throws CostMangerException;
    void updateItem(String nameColToUpdate, String dataToSet, int itemId) throws CostMangerException;
}
