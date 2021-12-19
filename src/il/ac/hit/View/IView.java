package il.ac.hit.View;

import il.ac.hit.*;
import il.ac.hit.Model.Category;
import il.ac.hit.Model.Item;
import il.ac.hit.Model.User;
import il.ac.hit.ViewModel.IViewModel;

import java.util.Collection;

public interface IView {

    /**
     * A method that introduces to the system all the existing items in the system.
     * @param i_Items
     */
    void showItems(Collection<Item> i_Items);

    /**
     * A method that introduces to the system all the existing categories in the system.
     * @param i_Categories
     */
    void showCategories(Collection<Category> i_Categories);

    /**
     * A method that presents a message to the system, obtained from the model.
     * @param i_Message
     */
    void showMessage(Message i_Message);

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

    void setIViewModel(IViewModel i_Vm);

}
