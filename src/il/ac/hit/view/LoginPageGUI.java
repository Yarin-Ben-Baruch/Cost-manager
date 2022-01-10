package il.ac.hit.view;
import il.ac.hit.model.User;
import il.ac.hit.viewmodel.IViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class that represents the login button for the app.
 * Inside the department there is data about what happens after clicking the login button,
 * and in addition. Option to click the register button.
 */
public class LoginPageGUI{

    private final IViewModel viewModel;
    private RegisterPageGUI registerFrame;

    //Creating login page frame
    private JFrame loginFrame;
    private JLabel loginUserNameLabel, loginPasswordLabel;
    private JTextField loginUserNameTextField;
    private JPasswordField loginPasswordField;
    private JButton loginButton, loginResetButton, loginRegisterButton;
    private JCheckBox loginShowPasswordCheckBox;

    /**
     *  Ctor of the login page gui
     * @param vm An object that holds the link to the viewModel class
     */
    public LoginPageGUI(IViewModel vm) {
        this.viewModel = vm;
    }

    /**
     * This method Initializing the Login page gui.
     */
    public void init() {
        // Sub menu of Login.
        registerFrame = new RegisterPageGUI(viewModel);

        // create new fields.
        loginFrame = new JFrame();
        loginUserNameLabel = new JLabel("USERNAME");
        loginPasswordLabel = new JLabel("PASSWORD");
        loginUserNameTextField = new JTextField(15);
        loginPasswordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        loginResetButton = new JButton("Reset");
        loginRegisterButton = new JButton("Register");
        loginShowPasswordCheckBox = new JCheckBox("Show Password");
    }

    /**
     * This method starting the Login page gui.
     */
    public void start() {

        // this will center the frame
        loginFrame.setLocationRelativeTo(null);

        // Add image background.
        loginFrame.setContentPane(new JLabel(new ImageIcon("src/images/Login background.jpg")));

        addLoginComponentsToFrame();

        loginFrame.setLayout(new BorderLayout());
        loginFrame.setTitle("Login Form");
        loginFrame.setSize(350,350);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);
        setLoginLocationAndSize();
        loginFrame.setVisible(true);

        addLoginActionEvents();
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
     * @return Returns the login username.
     */
    public String close() {
        loginFrame.dispose();
        return loginUserNameTextField.getText();
    }

    /**
     * This method open messageDialog that show invalid input in login.
     */
    public void showInvalidInputInLogin() {
        JOptionPane.showMessageDialog(loginFrame, "Invalid Username or Password","Error",
                JOptionPane.ERROR_MESSAGE);
    }
    /**
     * This method open messageDialog that show invalid input in Register.
     */
    public void showInvalidInputInRegister() {
        registerFrame.showInvalidInputInRegister();
    }

    // Set size and locations of the Components.
    private void setLoginLocationAndSize() {
        // set bounds.
        loginUserNameLabel.setBounds(50, 70, 100, 30);
        loginPasswordLabel.setBounds(50, 120, 100, 30);
        loginUserNameTextField.setBounds(150, 70, 150, 30);
        loginPasswordField.setBounds(150, 120, 150, 30);
        loginShowPasswordCheckBox.setBounds(150, 150, 150, 30);
        loginButton.setBounds(50, 200, 100, 30);
        loginResetButton.setBounds(200, 200, 100, 30);
        loginRegisterButton.setBounds(125, loginResetButton.getY()+50, 100, 30);
    }

    // Adding all the components to the frame.
    private void addLoginComponentsToFrame() {
        setColorToLabel();
        // add to the frame.
        loginFrame.add(loginUserNameLabel);
        loginFrame.add(loginUserNameTextField);
        loginFrame.add(loginPasswordLabel);
        loginFrame.add(loginPasswordField);
        loginFrame.add(loginShowPasswordCheckBox);
        loginFrame.add(loginButton);
        loginFrame.add(loginResetButton);
        loginFrame.add(loginRegisterButton);
    }

    //set colors.
    private void setColorToLabel(){
        loginUserNameLabel.setForeground(Color.WHITE);
        loginPasswordLabel.setForeground(Color.WHITE);
        loginShowPasswordCheckBox.setForeground(Color.WHITE);
    }

    // Actions listeners.
    private void addLoginActionEvents() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userText;
                String pwdText;
                // take txt from loginUserNameTextField.
                userText = loginUserNameTextField.getText();
                pwdText = new String(loginPasswordField.getPassword());
                User user = new User(userText, pwdText);
                viewModel.isUserExists(user);
            }
        });

        loginResetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // remove the text.
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
                    // replace withe *.
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

