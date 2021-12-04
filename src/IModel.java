import java.sql.Date;
import java.util.Collection;

public interface IModel {
    void addItem(Item item) throws CostMangerException;
    Collection<Item> getDetailedReport(Date startDate, Date endDate) throws CostMangerException;
    void addNewCategory(String category) throws CostMangerException;
    Collection<Item> getItems() throws CostMangerException;
    void updateItem(String nameColToUpdate, String dataToSet, int itemId) throws CostMangerException;
    void removeItem(int itemId) throws CostMangerException;
}
