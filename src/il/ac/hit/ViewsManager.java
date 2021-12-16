package il.ac.hit;

import javax.swing.*;
import java.util.Collection;

public class ViewsManager implements IView{

    //RegisterPageGUI m_RegisterPage;
    LoginPageGUI m_LoginPage;
    ApplicationPageGUI m_ApplicationPage;
    IViewModel m_ViewModel;

    @Override
    public void init() {
        m_ApplicationPage = new ApplicationPageGUI(m_ViewModel);
        m_LoginPage = new LoginPageGUI(m_ViewModel);
        //m_RegisterPage = new RegisterPageGUI(m_ViewModel);

        m_LoginPage.init();
    }

    @Override
    public void start() {
        m_LoginPage.start();
    }

    @Override
    public void showItems(Collection<Item> i_Items) {
        m_ApplicationPage.showItems(i_Items);
    }

    @Override
    public void ShowCategories(Collection<Category> i_Categories) {

    }

    @Override
    public void setUsers(Collection<User> i_Users) {

    }

    @Override
    public void setIViewModel(IViewModel i_Vm) {
        m_ViewModel = i_Vm;
    }

    @Override
    public void showMessage(Message i_Message) {

    }


    public void showInvalidInput(){

        //JOptionPane.showMessageDialog(m_MainFrame, "Invalid Username or Password");
    }

    public void openApplicationFromLogin() {
        //JOptionPane.showMessageDialog(m_MainFrame, "Login Successful");
        m_LoginPage.Close();
        m_ApplicationPage.initApplication();
        m_ApplicationPage.startApplication();

    }

    public void registerSucceeded() {
        m_LoginPage.OpenAfterRegister();
    }

}
