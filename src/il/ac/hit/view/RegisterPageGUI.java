package il.ac.hit.view;

import il.ac.hit.model.User;
import il.ac.hit.viewmodel.IViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A class representing the sign-up button.
 * Inside the department there is data about what happens after
 * the registration button is clicked and what opens after it closes.
 */
public class RegisterPageGUI {

    private final IViewModel viewModel;

    // Components of the Register Page Gui.
    private JFrame registerFrame;
    private JLabel titleLabel, userNameLabel, passwordLabel, resetJLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JCheckBox termCheckBox;
    private JButton submitButton, resetJButton;
    private JCheckBox registerShowPasswordCheckBox;

    /**
     * This is the Ctor of the Register page gui.
     * @param vm An object that holds the link to the viewModel class
     */
    public RegisterPageGUI(IViewModel vm) {
        this.viewModel = vm;
    }

    /**
     * This method initializing the Register page gui.
     */
    public void init() {

        registerFrame = new JFrame("Registration Form");
        titleLabel = new JLabel("Registration Form");
        userNameLabel = new JLabel("UserName");
        usernameTextField = new JTextField(15);
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField(15);
        termCheckBox = new JCheckBox("Accept Terms And Conditions.");
        submitButton = new JButton("Submit");
        resetJButton = new JButton("Reset");
        resetJLabel = new JLabel("");
        registerShowPasswordCheckBox = new JCheckBox("Show Password");

        registerSetFont();
    }

    /**
     * This method starting the Register page gui.
     */
    public void start(){
        registerFrame.setContentPane(new JLabel(new ImageIcon("src/images/Register background.jpg")));

        registerAddToFrame();
        registerFrame.setTitle("Registration Form");
        registerFrame.setBounds(300, 90, 630, 450);
        registerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        registerFrame.setResizable(false);
        registerFrame.setLayout(new BorderLayout());
        registerFrame.setBackground(Color.getColor("50",Color.lightGray));
        registerSetLocations();
        registerSetSize();
        registerFrame.setVisible(true);

        addRegisterActionEvent();
    }

    /**
     * This method show messageDialog if invalid input is entered.
     */
    public void showInvalidInputInRegister() {
        JOptionPane.showMessageDialog(registerFrame,"Register Failed!");
    }

    /**
     * This method close the register page gui.
     */
    public void close() {
        JOptionPane.showMessageDialog(registerFrame, "Register Success");
        registerFrame.dispose();
    }

    private void registerSetLocations(){
        titleLabel.setLocation(150, 30);
        userNameLabel.setLocation(100, 100);
        usernameTextField.setLocation(200, 100);
        passwordLabel.setLocation(100, 150);
        passwordField.setLocation(200, 150);
        registerShowPasswordCheckBox.setLocation(230,180);
        termCheckBox.setLocation(150, 250);
        submitButton.setLocation(150, 300);
        resetJButton.setLocation(270, 300);
        resetJLabel.setLocation(100, 500);
    }

    private void addRegisterActionEvent() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pwdText = new String(passwordField.getPassword());
                // Makes sure he has approved the terms, and UserName, password not empty.
                if (termCheckBox.isSelected()
                        && usernameTextField.getText().length() != 0
                        && pwdText.length() != 0) {

                    User user = new User(usernameTextField.getText(),pwdText);

                    viewModel.addNewUser(user);
                }
                // If not confirmed
                else {
                    showInvalidInputInRegister();
                }
            }
        });

        resetJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // restarting all the button to none.
                String def = "";
                usernameTextField.setText(def);
                passwordField.setText(def);
                resetJLabel.setText(def);
                termCheckBox.setSelected(false);
            }
        });

        registerShowPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Do the password field visible and invisible.
                if (registerShowPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        });
    }

    private void registerSetSize(){
        titleLabel.setSize(300, 30);
        userNameLabel.setSize(100, 20);
        usernameTextField.setSize(190, 20);
        passwordLabel.setSize(100, 20);
        passwordField.setSize(190, 20);
        registerShowPasswordCheckBox.setSize(150,30);
        termCheckBox.setSize(250, 20);
        submitButton.setSize(100, 20);
        resetJButton.setSize(100, 20);
        resetJLabel.setSize(500, 25);
    }

    private void registerAddToFrame(){
        registerFrame.add(titleLabel);
        registerFrame.add(userNameLabel);
        registerFrame.add(usernameTextField);
        registerFrame.add(passwordLabel);
        registerFrame.add(passwordField);
        registerFrame.add(registerShowPasswordCheckBox);
        registerFrame.add(termCheckBox);
        registerFrame.add(submitButton);
        registerFrame.add(resetJButton);

        // Clearing the information
        registerFrame.add(resetJLabel);

    }

    private void registerSetFont(){
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        userNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
        termCheckBox.setFont(new Font("Arial", Font.PLAIN, 15));
        submitButton.setFont(new Font("Arial", Font.PLAIN, 15));
        resetJButton.setFont(new Font("Arial", Font.PLAIN, 15));
        resetJLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    }

}



