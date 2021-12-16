package il.ac.hit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterPageGUI {

    private IViewModel m_Vm;
    // Components of the Form
    private JFrame m_RegisterFrame;
    private Container m_RegisterContainer;
    private JLabel m_TitleLabel, m_UserNameLabel, m_PasswordLabel, m_ResetJLabel;
    private JTextField m_UsernameTextField;
    private JPasswordField m_PasswordField;
    private JCheckBox m_TermCheckBox;
    private JButton m_SubmitButton, m_ResetJButton;
    private JCheckBox m_RegisterShowPasswordCheckBox;

    public RegisterPageGUI(IViewModel i_Vm) {
        this.m_Vm = i_Vm;
    }


    public void init() {

        m_RegisterFrame = new JFrame("Registration Form");
        m_TitleLabel = new JLabel("Registration Form");
        m_UserNameLabel = new JLabel("UserName");
        m_UsernameTextField = new JTextField();
        m_PasswordLabel = new JLabel("Password");
        m_PasswordField = new JPasswordField();
        m_TermCheckBox = new JCheckBox("Accept Terms And Conditions.");
        m_SubmitButton = new JButton("Submit");
        m_ResetJButton = new JButton("Reset");
        m_ResetJLabel = new JLabel("");
        m_RegisterShowPasswordCheckBox = new JCheckBox("Show Password");

        RegisterSetFont();
    }

    public void start(){

        m_RegisterFrame.setTitle("Registration Form");
        m_RegisterFrame.setBounds(300, 90, 500, 450);
        m_RegisterFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        m_RegisterFrame.setResizable(false);
        m_RegisterContainer = m_RegisterFrame.getContentPane();
        m_RegisterContainer.setLayout(null);
        m_RegisterContainer.setBackground(Color.getColor("50",Color.lightGray));

        RegisterSetLocations();
        RegisterSetSize();
        RegisterAddToContainer();
        AddRegisterActionEvent();

        m_RegisterFrame.setVisible(true);

    }

    private void RegisterSetLocations(){
        m_TitleLabel.setLocation(150, 30);
        m_UserNameLabel.setLocation(100, 100);
        m_UsernameTextField.setLocation(200, 100);
        m_PasswordLabel.setLocation(100, 150);
        m_PasswordField.setLocation(200, 150);
        m_RegisterShowPasswordCheckBox.setLocation(230,180);
        m_TermCheckBox.setLocation(150, 250);
        m_SubmitButton.setLocation(150, 300);
        m_ResetJButton.setLocation(270, 300);
        m_ResetJLabel.setLocation(100, 500);
    }

    private void RegisterSetSize(){
        m_TitleLabel.setSize(300, 30);
        m_UserNameLabel.setSize(100, 20);
        m_UsernameTextField.setSize(190, 20);
        m_PasswordLabel.setSize(100, 20);
        m_PasswordField.setSize(190, 20);
        m_RegisterShowPasswordCheckBox.setSize(150,30);
        m_TermCheckBox.setSize(250, 20);
        m_SubmitButton.setSize(100, 20);
        m_ResetJButton.setSize(100, 20);
        m_ResetJLabel.setSize(500, 25);
    }

    private void RegisterAddToContainer(){
        m_RegisterContainer.add(m_TitleLabel);
        m_RegisterContainer.add(m_UserNameLabel);
        m_RegisterContainer.add(m_UsernameTextField);
        m_RegisterContainer.add(m_PasswordLabel);
        m_RegisterContainer.add(m_PasswordField);
        m_RegisterContainer.add(m_RegisterShowPasswordCheckBox);
        m_RegisterContainer.add(m_TermCheckBox);
        m_RegisterContainer.add(m_SubmitButton);
        m_RegisterContainer.add(m_ResetJButton);

        // Clearing the information
        m_RegisterContainer.add(m_ResetJLabel);

    }

    private void RegisterSetFont(){
        m_TitleLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        m_UserNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        m_UsernameTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        m_PasswordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        m_PasswordField.setFont(new Font("Arial", Font.PLAIN, 15));
        m_TermCheckBox.setFont(new Font("Arial", Font.PLAIN, 15));
        m_SubmitButton.setFont(new Font("Arial", Font.PLAIN, 15));
        m_ResetJButton.setFont(new Font("Arial", Font.PLAIN, 15));
        m_ResetJLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    }

    public void ShowInvalidInputInRegister() {
        JOptionPane.showMessageDialog(m_RegisterFrame,"Register Failed!");
    }

    public void Close() {
        JOptionPane.showMessageDialog(m_RegisterFrame, "Register Success");
        m_RegisterFrame.dispose();
    }

    private void AddRegisterActionEvent(){
        m_SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pwdText = new String(m_PasswordField.getPassword());
                // Makes sure he has approved the terms, and UserName, password not empty
                // Fix the deprecated method.
                if (m_TermCheckBox.isSelected()
                        && m_UsernameTextField.getText().length() != 0
                        && pwdText.length() != 0) {

                    User user = new User(m_UsernameTextField.getText(),pwdText);

                    m_Vm.addNewUser(user);
                }
                // If not confirmed
                else {
                    ShowInvalidInputInRegister();
                }
            }
        });

        m_ResetJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String def = "";
                m_UsernameTextField.setText(def);
                m_PasswordField.setText(def);
                m_ResetJLabel.setText(def);
                m_TermCheckBox.setSelected(false);
            }
        });

        m_RegisterShowPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Coding Part of showPassword JCheckBox
                if (m_RegisterShowPasswordCheckBox.isSelected()) {
                    m_PasswordField.setEchoChar((char) 0);
                } else {
                    m_PasswordField.setEchoChar('*');
                }
            }
        });
    }

}



