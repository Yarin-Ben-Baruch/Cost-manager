package il.ac.hit.model;

import java.sql.Date;
import java.util.Collection;

public interface IModel {
    /**
     * Add new cost i_Item to the items sql table.
     * @param item
     * @throws CostManagerException A class wraps up the problems of the program
     */
    void addItem(Item item) throws CostManagerException;

    /**
     * Add new i_User for the users sql table.
     * @param user
     * @throws CostManagerException A class wraps up the problems of the program
     */
    void addNewUser(User user) throws CostManagerException;

    /**
     * Add new i_Category for the categories sql table.
     * @param category
     * @throws CostManagerException A class wraps up the problems of the program
     */
    void addNewCategoryIfExists(Category category) throws CostManagerException;

    /**
     * Get report return collection with all the costs from start date to the end date.
     * @param startDate
     * @param endDate
     * @return Returns Item collection
     * @throws CostManagerException A class wraps up the problems of the program
     */
    Collection<Item> getDetailedReport(Date startDate, Date endDate, String userName) throws CostManagerException;

    /**
     * Get items return collection with all the cost items in the items sql table.
     * @param userName
     * @return Returns Item collection
     * @throws CostManagerException A class wraps up the problems of the program
     */
    Collection<Item> getItems(String userName) throws CostManagerException;

    /**
     * Update item change specific info in a cost item.
     * @param nameColToUpdate
     * @param dataToSet
     * @param costNumber
     * @param userName
     * @throws CostManagerException A class wraps up the problems of the program
     */
    void updateItem(String nameColToUpdate, String dataToSet, String costNumber, String userName) throws CostManagerException;

    /**
     * remove item from the items sql table.
     * @param costNumber
     * @param userName
     * @throws CostManagerException A class wraps up the problems of the program
     */
    void removeItem(String costNumber, String userName) throws CostManagerException;

    /**
     * Get users return collection with all the users in the users sql table.
     * @return Returns a User collection
     * @throws CostManagerException A class wraps up the problems of the program
     */
    Collection<User> getAllUsers() throws CostManagerException;

    /**
     * Get category return collection with all the categories in the categories sql table.
     * @return Returns a category collection
     * @throws CostManagerException A class wraps up the problems of the program
     */
    Collection<Category> getAllCategories() throws CostManagerException;

    void checkIfUserExists(User i_User) throws CostManagerException;
}
