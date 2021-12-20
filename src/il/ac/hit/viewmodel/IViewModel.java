package il.ac.hit.viewmodel;

import il.ac.hit.model.Category;
import il.ac.hit.model.IModel;
import il.ac.hit.model.Item;
import il.ac.hit.model.User;
import il.ac.hit.view.IView;

import java.sql.Date;

public interface IViewModel {

    void setView(IView i_View);
    void setModel(IModel i_Model);

    /**
     * A method that adds an item to DB.
     * @param i_Item
     */
    void addItem(Item i_Item);

    /**
     * A method that adds a new user to DB.
     * @param i_User
     */
    void addNewUser(User i_User);

    /**
     * A method that adds a new category to DB if there is no such category.
     * @param i_Category
     */
    void addNewCategoryIfExists(Category i_Category);

    /**
     * A method that deletes an item from DB.
     * @param i_CostNumber
     * @param i_UserName
     */
    void removeItem(String i_CostNumber, String i_UserName);

    /**
     * A method that updates a specific item from DB.
     * @param i_NameColToUpdate
     * @param i_DataToSet
     * @param i_CostNumber
     * @param i_UserName
     */
    void updateItem(String i_NameColToUpdate, String i_DataToSet, String i_CostNumber, String i_UserName);

    /**
     * A method that returns a detailed statement of expenses in a date range.
     * @param i_StartDate
     * @param i_EndDate
     * @param i_Username
     */
    void getDetailedReport(Date i_StartDate, Date i_EndDate, String i_Username);

    /**
     * A method that returns all existing categories, to all users.
     */
    void getAllCategories();

    /**
     * A method that returns all existing items, with the user connected.
     * @param i_Username
     */
    void getItems(String i_Username);

    /**
     * A method that checks whether such a user exists in the system.
     * @param i_User
     */
    void isUserExists(User i_User);
}
