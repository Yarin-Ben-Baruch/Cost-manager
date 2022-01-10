package il.ac.hit.viewmodel;

import il.ac.hit.model.*;
import il.ac.hit.view.IView;
import java.sql.Date;


/**
 * An interface that includes all the actions between the model and view.
 */
public interface IViewModel {

    /**
     * basic set
     * @param view save as a member class.
     */
    void setView(IView view);

    /**
     * basic set
     * @param model save as a member class.
     */
    void setModel(IModel model);

    /**
     * A method that adds an item to DB.
     * @param item An object that stores all types of data in the expense table.
     */
    void addItem(Item item);

    /**
     * A method that adds a new user to DB.
     * @param user The username is online.
     */
    void addNewUser(User user);

    /**
     * A method that adds a new category to DB if there is no such category.
     * @param category Category you want to add to the database.
     */
    void addNewCategoryIfExists(Category category);

    /**
     * A method that deletes an item from DB.
     * @param costNumber In what row is it in the table.
     * @param userName The username is online.
     */
    void removeItem(String costNumber, String userName);

    /**
     * A method that updates a specific item from DB.
     * @param nameColToUpdate The name of the column you want to change.
     * @param dataToSet The information you want to update.
     * @param costNumber In what row is it in the table.
     * @param userName The username is online.
     */
    void updateItem(String nameColToUpdate, String dataToSet, String costNumber, String userName);

    /**
     * A method that returns a detailed statement of expenses in a date range.
     * @param startDate Date from which you want to receive data.
     * @param endDate Date by which you want to receive data.
     * @param username The username is online.
     */
    void getDetailedReport(Date startDate, Date endDate, String username);

    /**
     * A method that returns all existing categories, to all users.
     */
    void getAllCategories();

    /**
     * A method that returns all existing items, with the user connected.
     * @param username The username is online.
     */
    void getItems(String username);

    /**
     * A method that checks whether such a user exists in the system.
     * @param user The username is online.
     */
    void isUserExists(User user);

    /**
     * A method that returns all currencies from the localhost.
     */
    void getCurrencies();
}
