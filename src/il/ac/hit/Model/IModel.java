package il.ac.hit.Model;

import il.ac.hit.Exception.CostMangerException;

import java.sql.Date;
import java.util.Collection;

/**
 *  NEED TO WRITE HERE !
 */
public interface IModel {
    /**
     * Add new cost item to the items sql table.
     * @param item
     * @throws CostMangerException
     */
    void addItem(Item item) throws CostMangerException;

    /**
     * Add new user for the users sql table.
     * @param user
     * @throws CostMangerException
     */
    void addNewUser(User user) throws CostMangerException;

    /**
     * Add new category for the categories sql table.
     * @param category
     * @throws CostMangerException
     */
    void addNewCategoryIfExists(Category category) throws CostMangerException;

    /**
     * Get report return collection with all the costs from start date to the end date.
     * @param startDate
     * @param endDate
     * @return
     * @throws CostMangerException
     */
    Collection<Item> getDetailedReport(Date startDate, Date endDate) throws CostMangerException;

    /**
     * Get items return collection with all the cost items in the items sql table.
     * @return
     * @throws CostMangerException
     */
    Collection<Item> getItems() throws CostMangerException;

    /**
     * Update item change specific info in a cost item.
     * @param nameColToUpdate
     * @param dataToSet
     * @param costNumber
     * @param userName
     * @throws CostMangerException
     */
    void updateItem(String nameColToUpdate, String dataToSet, String costNumber, String userName) throws CostMangerException;

    /**
     * remove item from the items sql table.
     * @param costNumber
     * @param userName
     * @throws CostMangerException
     */
    void removeItem(String costNumber, String userName) throws CostMangerException;

    /**
     * Get users return collection with all the users in the users sql table.
     * @return
     * @throws CostMangerException
     */
    Collection<User> getAllUsers() throws CostMangerException;

    /**
     * Get category return collection with all the categories in the categories sql table.
     * @return
     * @throws CostMangerException
     */
    Collection<Category> getAllCategories() throws CostMangerException;

    void checkIfUserExists(User i_User) throws CostMangerException;
}
