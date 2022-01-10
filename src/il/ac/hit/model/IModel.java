package il.ac.hit.model;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

/**
 * An interface that includes all the actions that can be taken in an expense management app.
 */
public interface IModel {
    /**
     * Add new cost i_Item to the items sql table.
     * @param item An object that stores all types of data in the expense table.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    void addItem(Item item) throws CostManagerException;

    /**
     * Add new i_User for the users sql table.
     * @param user The username is online.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    void addNewUser(User user) throws CostManagerException;

    /**
     * Add new i_Category for the categories sql table.
     * @param category Category you want to add to the database.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    void addNewCategoryIfExists(Category category) throws CostManagerException;

    /**
     * Get report return collection with all the costs from start date to the end date.
     * @param startDate Date from which you want to receive data.
     * @param endDate Date by which you want to receive data.
     * @return Return list of all items in the database.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    Collection<Item> getDetailedReport(Date startDate, Date endDate, String userName) throws CostManagerException;

    /**
     * Get items return collection with all the cost items in the items sql table.
     * @param userName The username is online.
     * @return Returns Item collection.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    Collection<Item> getItems(String userName) throws CostManagerException;

    /**
     * Update item change specific info in a cost item.
     * @param nameColToUpdate The name of the column you want to change.
     * @param dataToSet The information you want to update.
     * @param costNumber In what row is it in the table.
     * @param userName The username is online.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    void updateItem(String nameColToUpdate, String dataToSet, String costNumber, String userName) throws CostManagerException;

    /**
     * remove item from the items sql table.
     * @param costNumber In what row is it in the table.
     * @param userName The username is online.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    void removeItem(String costNumber, String userName) throws CostManagerException;

    /**
     * Get users return collection with all the users in the users sql table.
     * @return Returns a User collection.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    Collection<User> getAllUsers() throws CostManagerException;

    /**
     * Get category return collection with all the categories in the categories sql table.
     * @return Returns a category collection.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    Collection<Category> getAllCategories() throws CostManagerException;

    /**
     *  This method is checking if the user that she get is inside the DB.
     * @param user The username is online.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    void checkIfUserExists(User user) throws CostManagerException;

    /**
     * getCurrencies return List of all the currencies in the cost manager application form the localhost:8080.
     * @return Return list of all currencies in the localhost:8080.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    List<Currency> getCurrencies() throws CostManagerException;
}
