package il.ac.hit.view;

import il.ac.hit.model.User;
import il.ac.hit.viewmodel.IViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPageGUI{

    private final IViewModel viewModel;
    private RegisterPageGUI registerFrame;

    //Creating login page frame
    private JFrame loginFrame;
    private Container loginContainer;
    private JLabel loginUserNameLabel, loginPasswordLabel;
    private JTextField loginUserNameTextField;
    private JPasswordField loginPasswordField;
    private JButton loginButton, loginResetButton, loginRegisterButton;
    private JCheckBox loginShowPasswordCheckBox;

    /**
     *  Ctor of the login page gui
     * @param vm
     */
    public LoginPageGUI(IViewModel vm) {
        this.viewModel = vm;
    }

    /**
     * This method Initializing the Login page gui
     */
    public void init() {
        // Sub menu of Login.
        registerFrame = new RegisterPageGUI(viewModel);

        loginFrame = new JFrame();
        loginContainer = loginFrame.getContentPane();
        loginUserNameLabel = new JLabel("USERNAME");
        loginPasswordLabel = new JLabel("PASSWORD");
        loginUserNameTextField = new JTextField();
        loginPasswordField = new JPasswordField();
        loginButton = new JButton("Login");
        loginResetButton = new JButton("Reset");
        loginRegisterButton = new JButton("Register");
        loginShowPasswordCheckBox = new JCheckBox("Show Password");
    }

    /**
     * This method starting the Login page gui.
     */
    public void start(){

        setLoginLayoutManager();
        setLoginLocationAndSize();
        addLoginComponentsToContainer();
        addLoginActionEvents();

        loginFrame.setTitle("Login Form");
        loginFrame.setVisible(true);
        loginFrame.setBounds(10, 10, 350, 400);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);
    }

    /**
     * This method open the login page again after register to the application.
     */
    public void openAfterRegister() {
        registerFrame.close();
        loginFrame.setVisible(true);

    }

    /**
     * This method close the login page and return the username field.
     * @return
     */
    public String close() {
        loginFrame.dispose();

        return loginUserNameTextField.getText();
    }

    /**
     * This method open messageDialog that show invalid input in login.
     */
    public void showInvalidInputInLogin() {
        JOptionPane.showMessageDialog(loginFrame, "Invalid Username or Password");
    }
    /**
     * This method open messageDialog that show invalid input in Register.
     */
    public void showInvalidInputInRegister() {
        registerFrame.showInvalidInputInRegister();
    }

    // Set the layout null to set the size and the location by myself.
    private void setLoginLayoutManager() {
        loginContainer.setLayout(null);
    }

    // Set size and locations of the Components.
    private void setLoginLocationAndSize() {

        loginUserNameLabel.setBounds(50, 70, 100, 30);
        loginPasswordLabel.setBounds(50, 120, 100, 30);
        loginUserNameTextField.setBounds(150, 70, 150, 30);
        loginPasswordField.setBounds(150, 120, 150, 30);
        loginShowPasswordCheckBox.setBounds(150, 150, 150, 30);
        loginButton.setBounds(50, 200, 100, 30);
        loginResetButton.setBounds(200, 200, 100, 30);
        loginRegisterButton.setBounds(125, 250, 100, 30);
    }

    private void addLoginComponentsToContainer() {
        loginContainer.add(loginUserNameLabel);
        loginContainer.add(loginPasswordLabel);
        loginContainer.add(loginUserNameTextField);
        loginContainer.add(loginPasswordField);
        loginContainer.add(loginShowPasswordCheckBox);
        loginContainer.add(loginButton);
        loginContainer.add(loginResetButton);
        loginContainer.add(loginRegisterButton);
    }

    private void addLoginActionEvents() {

        loginButton.addActionListener((e -> {
            String userText;
            String pwdText;
            userText = loginUserNameTextField.getText();
            pwdText = new String(loginPasswordField.getPassword());
            User user = new User(userText, pwdText);
            viewModel.isUserExists(user);
        }));

//        m_LoginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String userText;
//                String pwdText;
//                userText = m_LoginUserNameTextField.getText();
//                pwdText = new String(m_LoginPasswordField.getPassword());
//                User user = new User(userText, pwdText);
//                m_Vm.isUserExists(user);
//            }
//        });

        loginResetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUserNameTextField.setText("");
                loginPasswordField.setText("");
            }
        });

        loginShowPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Do visible and invisible password in the password field.
                if (loginShowPasswordCheckBox.isSelected()) {
                    loginPasswordField.setEchoChar((char) 0);
                } else {
                    loginPasswordField.setEchoChar('*');
                }
            }
        });

        loginRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //close this windows
                loginFrame.setVisible(false);

                // open register window
                registerFrame.init();
                registerFrame.start();

            }
        });
    }
}

