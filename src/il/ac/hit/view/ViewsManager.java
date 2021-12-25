package il.ac.hit.view;

import il.ac.hit.model.Message;
import il.ac.hit.model.Category;
import il.ac.hit.model.Item;
import il.ac.hit.viewmodel.IViewModel;

import java.util.Collection;

/**
 * An object that implements the view and manages all the windows in the application.
 * The object manages all the methods that need to be implemented from the view class,
 * and receives the relevant information from the objects relevant to that function.
 */

public class ViewsManager implements IView {

    private LoginPageGUI loginPage;
    private ApplicationPageGUI applicationPage;
    private IViewModel viewModel;

    /**
     * This method Initialized the Application and start with the login screen page.
     * Make sure you invoke this method during the execution of the AWT Event thread.
     */
    @Override
    public void init() {
        applicationPage = new ApplicationPageGUI(viewModel,this);
        loginPage = new LoginPageGUI(viewModel);
        loginPage.init();
    }
    /**
     * This method Start the Application and start with the login screen page.
     * Make sure you invoke this method during the execution of the AWT Event thread.
     */
    @Override
    public void start() {
        loginPage.start();
    }

    /**
     * This method init and start the login page.
     * Make sure you invoke this method during the execution of the AWT Event thread.
     */
    @Override
    public void openLogin() {
        loginPage.init();
        loginPage.start();
    }

    /**
     * This method show the items in the cost table.
     * Make sure you invoke this method during the execution of the AWT Event thread.
     * @param items  A list of items that list all the column types in the expense table.
     */
    @Override
    public void showItems(Collection<Item> items) {
        applicationPage.showItems(items);
    }

    /**
     * This method show the categories in the category table.
     * Make sure you invoke this method during the execution of the AWT Event thread.
     * @param categories A list of items that list all the types of categories there are.
     */
    @Override
    public void showCategories(Collection<Category> categories) {
        applicationPage.showCategories(categories);
    }

    /**
     * This method called the application page showMessage method.
     * Inorder to show the message that she get from the ViewModel.
     * Make sure you invoke this method during the execution of the AWT Event thread.
     * @param message A message-type object that conveys a message to the user.
     */
    @Override
    public void showMessage(Message message) {
        applicationPage.showMessage(message);
    }

    /**
     * This method call the login page method showInvalidInputInLogin.
     * Inorder to show the message of invalid input that she get from the ViewModel.
     * Make sure you invoke this method during the execution of the AWT Event thread.
     */
    public void showInvalidInputInLogin(){
        loginPage.showInvalidInputInLogin();
    }

    /**
     * This method call the login page method showInvalidInputInRegister.
     * Inorder to show the message of invalid input that she get from the ViewModel.
     * Make sure you invoke this method during the execution of the AWT Event thread.
     */
    public void showInvalidInputInRegister() {
        loginPage.showInvalidInputInRegister();
    }

    /**
     * This method open the application page gui.
     * Close that login page gui.
     * Set the username that entered to the application.
     * Make sure you invoke this method during the execution of the AWT Event thread.
     */
    public void openApplicationFromLogin() {
        String username =  loginPage.close();
        applicationPage.setUsername(username);
        applicationPage.initApplication();
        applicationPage.startApplication();

    }

    /**
     * This method call the login method OpenAfterRegister.
     * The method open the login page again after register.
     * Make sure you invoke this method during the execution of the AWT Event thread.
     */
    public void registerSucceeded() {
        loginPage.openAfterRegister();
    }

    @Override
    public void setIViewModel(IViewModel vm) {
        viewModel = vm;
    }
}
