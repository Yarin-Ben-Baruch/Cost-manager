package il.ac.hit;

import javax.swing.*;
import java.util.Collection;

public class CostMangerGUIView implements IView {

    private JFrame loginFrame;
    private JTextField m_UsernameTextField, m_PasswordTextField;
    private JLabel m_UsernameLabel, m_PasswordLabel;
    private JButton m_CancelButton,m_LoginButton;
    private JButton removeItemButton;
    private JButton removeAllItemsButton;
    private JTextArea ta;
    private JPanel panelSouth, panelNorth;
    private IViewModel vm;

    @Override
    public void init() {
        loginFrame = new JFrame();
        m_PasswordLabel = new JLabel("Password: ");
        m_UsernameLabel = new JLabel("Username: ");
        m_UsernameTextField = new JTextField(40);
        m_PasswordTextField = new JTextField(40);
        m_CancelButton = new JButton("Cancel");
        m_LoginButton = new JButton("Login");

    }

    @Override
    public void start() {

    }

    @Override
    public void showItems(Collection<Item> i_Items) {

    }

    @Override
    public void showCategories(Collection<Category> i_Categories) {

    }

    @Override
    public void showDetailedReport() {

    }

    @Override
    public void setIViewModel(IViewModel i_Vm) {

    }

}
