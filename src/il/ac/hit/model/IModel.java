package il.ac.hit.model;

import java.sql.Date;
import java.util.Collection;

public interface IModel {
    /**
     * Add new cost i_Item to the items sql table.
     * @param i_Item
     * @throws CostManagerException
     */
    void addItem(Item i_Item) throws CostManagerException;

    /**
     * Add new i_User for the users sql table.
     * @param i_User
     * @throws CostManagerException
     */
    void addNewUser(User i_User) throws CostManagerException;

    /**
     * Add new i_Category for the categories sql table.
     * @param i_Category
     * @throws CostManagerException
     */
    void addNewCategoryIfExists(Category i_Category) throws CostManagerException;

    /**
     * Get report return collection with all the costs from start date to the end date.
     * @param i_StartDate
     * @param i_EndDate
     * @return
     * @throws CostManagerException
     */
    Collection<Item> getDetailedReport(Date i_StartDate, Date i_EndDate, String i_Username) throws CostManagerException;

    /**
     * Get items return collection with all the cost items in the items sql table.
     * @param i_Username
     * @return
     * @throws CostManagerException
     */
    Collection<Item> getItems(String i_Username) throws CostManagerException;

    /**
     * Update item change specific info in a cost item.
     * @param i_NameColToUpdate
     * @param i_DataToSet
     * @param i_CostNumber
     * @param i_Username
     * @throws CostManagerException
     */
    void updateItem(String i_NameColToUpdate, String i_DataToSet, String i_CostNumber, String i_Username) throws CostManagerException;

    /**
     * remove item from the items sql table.
     * @param i_CostNumber
     * @param i_Username
     * @throws CostManagerException
     */
    void removeItem(String i_CostNumber, String i_Username) throws CostManagerException;

    /**
     * Get users return collection with all the users in the users sql table.
     * @return
     * @throws CostManagerException
     */
    Collection<User> getAllUsers() throws CostManagerException;

    /**
     * Get category return collection with all the categories in the categories sql table.
     * @return
     * @throws CostManagerException
     */
    Collection<Category> getAllCategories() throws CostManagerException;

    void checkIfUserExists(User i_User) throws CostManagerException;
}
