package il.ac.hit.view;
import il.ac.hit.model.Category;
import il.ac.hit.model.Currency;
import il.ac.hit.model.Item;
import il.ac.hit.model.Message;
import il.ac.hit.viewmodel.IViewModel;

import java.util.Collection;
import java.util.List;

/**
 * An interface that includes all the actions that can be taken in an expense management app.
 */
public interface IView {

    /**
     * A method that introduces to the system all the existing items in the system.
     * @param items A list of items that list all the column types in the expense table
     */
    void showItems(Collection<Item> items);

    /**
     * A method that introduces to the system all the existing categories in the system.
     * @param categories A list of items that list all the types of categories there are
     */
    void showCategories(Collection<Category> categories);

    /**
     * A method that presents a message to the system when error occurred, obtained from the model.
     * @param message A message-type object that conveys a message to the user
     */
    void showErrorMessage(Message message);

    /**
     * A method that presents a message to the system when the action succeed, obtained from the model.
     * @param message A message-type object that conveys a message to the user
     */
    void showSuccessMessage(Message message);

    /**
     * A method that initializes each class variable.
     */
    void init();

    /**
     * A method that runs each class variable.
     */
    void start();

    /**
     * Method launches the app from the login window.
     */
    void openApplicationFromLogin();

    /**
     * A method that updates if the registration was successful.
     */
    void registerSucceeded();

    /**
     * A method that displays the login window if something went wrong.
     */
    void showInvalidInputInLogin();

    /**
     * A method that displays a message in the registration window, if something is wrong.
     */
    void showInvalidInputInRegister();

    /**
     * A method that displays a message in the application window that show the currencies.
     */
    void showCurrencies(List<Currency> currencies);

    /**
     * A method that opens the login window
     */
    void openLogin();

    /**
     * a basic setter.
     * @param vm save the view model as a member.
     */
    void setIViewModel(IViewModel vm);
}
