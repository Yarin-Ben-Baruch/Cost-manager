package il.ac.hit.view;

import il.ac.hit.*;
import il.ac.hit.model.Category;
import il.ac.hit.model.Item;
import il.ac.hit.viewmodel.IViewModel;

import java.util.Collection;

public interface IView {

    /**
     * A method that introduces to the system all the existing items in the system.
     * @param items
     */
    void showItems(Collection<Item> items);

    /**
     * A method that introduces to the system all the existing categories in the system.
     * @param categories
     */
    void showCategories(Collection<Category> categories);

    /**
     * A method that presents a message to the system, obtained from the model.
     * @param message
     */
    void showMessage(Message message);

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
     * A method that opens the login window
     */
    void openLogin();

    void setIViewModel(IViewModel vm);

}
