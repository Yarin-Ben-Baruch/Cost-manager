package il.ac.hit;

import java.sql.Date;
import java.util.Collection;

public interface IModel {
    void addItem(Item item) throws CostMangerException;
    void addNewUser(User user) throws CostMangerException;
    void addNewCategory(Category category) throws CostMangerException;
    Collection<Item> getDetailedReport(Date startDate, Date endDate) throws CostMangerException;
    Collection<Item> getItems() throws CostMangerException;
    void updateItem(String nameColToUpdate, String dataToSet, int costNumber, String userName) throws CostMangerException;
    void removeItem(int costNumber, String userName) throws CostMangerException;


}
