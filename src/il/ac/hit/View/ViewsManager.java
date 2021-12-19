package il.ac.hit.View;

import il.ac.hit.Message;
import il.ac.hit.Model.Category;
import il.ac.hit.Model.Item;
import il.ac.hit.Model.User;
import il.ac.hit.ViewModel.IViewModel;

import java.util.Collection;

public class ViewsManager implements IView {

    LoginPageGUI m_LoginPage;
    ApplicationPageGUI m_ApplicationPage;
    IViewModel m_ViewModel;

    /**
     * This method Initialized the Application and start with the login screen page.
     */
    @Override
    public void init() {
        m_ApplicationPage = new ApplicationPageGUI(m_ViewModel,this);
        m_LoginPage = new LoginPageGUI(m_ViewModel);
        m_LoginPage.init();
    }
    /**
     * This method Start the Application and start with the login screen page.
     */
    @Override
    public void start() {
        m_LoginPage.start();
    }

    /**
     * This method init and start the login page.
     */
    @Override
    public void openLogin() {
        m_LoginPage.init();
        m_LoginPage.start();
    }

    /**
     * This method show the items in the cost table.
     * @param i_Items
     */
    @Override
    public void showItems(Collection<Item> i_Items) {
        m_ApplicationPage.showItems(i_Items);
    }

    /**
     * This method show the categoris in the category table.
     * @param i_Categories
     */
    @Override
    public void ShowCategories(Collection<Category> i_Categories) {
        m_ApplicationPage.ShowCategories(i_Categories);
    }

    /**
     * This method called the application page showMessage method.
     * Inorder to show the message that she get from the ViewModel.
     * @param i_Message
     */
    @Override
    public void showMessage(Message i_Message) {
        m_ApplicationPage.showMessage(i_Message);
    }

    /**
     * This method call the login page method showInvalidInputInLogin.
     * Inorder to show the message of invalid input that she get from the ViewModel.
     */
    public void showInvalidInputInLogin(){
        m_LoginPage.showInvalidInputInLogin();
    }

    /**
     * This method call the login page method showInvalidInputInRegister.
     * Inorder to show the message of invalid input that she get from the ViewModel.
     */
    public void showInvalidInputInRegister() {
        m_LoginPage.showInvalidInputInRegister();
    }

    /**
     * This method open the application page gui.
     * Close that login page gui.
     * Set the username that entered to the application.
     */
    public void openApplicationFromLogin() {
        String username =  m_LoginPage.Close();
        m_ApplicationPage.setUsername(username);
        m_ApplicationPage.initApplication();
        m_ApplicationPage.startApplication();

    }

    /**
     * This method call the login method OpenAfterRegister.
     * The method open the login page again after register.
     */
    public void registerSucceeded() {
        m_LoginPage.OpenAfterRegister();
    }

    @Override
    public void setIViewModel(IViewModel i_Vm) {
        m_ViewModel = i_Vm;
    }

}
