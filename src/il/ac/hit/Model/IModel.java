package il.ac.hit.Model;

import il.ac.hit.Exception.CostMangerException;

import java.sql.Date;
import java.util.Collection;

/**
 *  NEED TO WRITE HERE !
 */
public interface IModel {
    /**
     * Add new cost i_Item to the items sql table.
     * @param i_Item
     * @throws CostMangerException
     */
    void addItem(Item i_Item) throws CostMangerException;

    /**
     * Add new i_User for the users sql table.
     * @param i_User
     * @throws CostMangerException
     */
    void addNewUser(User i_User) throws CostMangerException;

    /**
     * Add new i_Category for the categories sql table.
     * @param i_Category
     * @throws CostMangerException
     */
    void addNewCategoryIfExists(Category i_Category) throws CostMangerException;

    /**
     * Get report return collection with all the costs from start date to the end date.
     * @param i_StartDate
     * @param i_EndDate
     * @return
     * @throws CostMangerException
     */
    Collection<Item> getDetailedReport(Date i_StartDate, Date i_EndDate) throws CostMangerException;

    /**
     * Get items return collection with all the cost items in the items sql table.
     * @return
     * @throws CostMangerException
     */
    Collection<Item> getItems() throws CostMangerException;

    /**
     * Update item change specific info in a cost item.
     * @param i_NameColToUpdate
     * @param i_DataToSet
     * @param i_CostNumber
     * @param i_Username
     * @throws CostMangerException
     */
    void updateItem(String i_NameColToUpdate, String i_DataToSet, String i_CostNumber, String i_Username) throws CostMangerException;

    /**
     * remove item from the items sql table.
     * @param i_CostNumber
     * @param i_Username
     * @throws CostMangerException
     */
    void removeItem(String i_CostNumber, String i_Username) throws CostMangerException;

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
