package il.ac.hit.viewmodel;

import il.ac.hit.model.Category;
import il.ac.hit.model.IModel;
import il.ac.hit.model.Item;
import il.ac.hit.model.User;
import il.ac.hit.view.IView;

import java.sql.Date;

public interface IViewModel {

    void setView(IView view);
    void setModel(IModel model);

    /**
     * A method that adds an item to DB.
     * @param item
     */
    void addItem(Item item);

    /**
     * A method that adds a new user to DB.
     * @param user
     */
    void addNewUser(User user);

    /**
     * A method that adds a new category to DB if there is no such category.
     * @param category
     */
    void addNewCategoryIfExists(Category category);

    /**
     * A method that deletes an item from DB.
     * @param costNumber
     * @param userName
     */
    void removeItem(String costNumber, String userName);

    /**
     * A method that updates a specific item from DB.
     * @param nameColToUpdate
     * @param dataToSet
     * @param costNumber
     * @param userName
     */
    void updateItem(String nameColToUpdate, String dataToSet, String costNumber, String userName);

    /**
     * A method that returns a detailed statement of expenses in a date range.
     * @param startDate
     * @param endDate
     * @param username
     */
    void getDetailedReport(Date startDate, Date endDate, String username);

    /**
     * A method that returns all existing categories, to all users.
     */
    void getAllCategories();

    /**
     * A method that returns all existing items, with the user connected.
     * @param username
     */
    void getItems(String username);

    /**
     * A method that checks whether such a user exists in the system.
     * @param user
     */
    void isUserExists(User user);
}
