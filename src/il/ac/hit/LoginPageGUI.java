package il.ac.hit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPageGUI{

    private IViewModel m_Vm;
    private RegisterPageGUI m_RegisterFrame;
    //Creating login page frame
    private JFrame m_LoginFrame;
    private Container m_LoginContainer;
    private JLabel m_LoginUserNameLabel, m_LoginPasswordLabel;
    private JTextField m_LoginUserNameTextField;
    private JPasswordField m_LoginPasswordField;
    private JButton m_LoginButton, m_LoginResetButton, m_LoginRegisterButton;
    private JCheckBox m_LoginShowPasswordCheckBox;


    public LoginPageGUI(IViewModel i_Vm) {
        this.m_Vm = i_Vm;
    }


    public void init() {
        // Sub menu of Login !
        m_RegisterFrame = new RegisterPageGUI(m_Vm);

        m_LoginFrame = new JFrame();
        m_LoginContainer = m_LoginFrame.getContentPane();
        m_LoginUserNameLabel = new JLabel("USERNAME");
        m_LoginPasswordLabel = new JLabel("PASSWORD");
        m_LoginUserNameTextField = new JTextField();
        m_LoginPasswordField = new JPasswordField();
        m_LoginButton = new JButton("Login");
        m_LoginResetButton = new JButton("Reset");
        m_LoginRegisterButton = new JButton("Register");
        m_LoginShowPasswordCheckBox = new JCheckBox("Show Password");
    }


    public void start(){

        setLoginLayoutManager();
        setLoginLocationAndSize();
        addLoginComponentsToContainer();
        addLoginActionEvents();

        m_LoginFrame.setTitle("Login Form");
        m_LoginFrame.setVisible(true);
        m_LoginFrame.setBounds(10, 10, 350, 400);
        m_LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_LoginFrame.setResizable(false);
    }

    public void Close() {
        m_LoginFrame.dispose();
    }

    //Login
    private void setLoginLayoutManager() {
        m_LoginContainer.setLayout(null);
    }

    //Login
    private void setLoginLocationAndSize() {

        m_LoginUserNameLabel.setBounds(50, 70, 100, 30);
        m_LoginPasswordLabel.setBounds(50, 120, 100, 30);
        m_LoginUserNameTextField.setBounds(150, 70, 150, 30);
        m_LoginPasswordField.setBounds(150, 120, 150, 30);
        m_LoginShowPasswordCheckBox.setBounds(150, 150, 150, 30);
        m_LoginButton.setBounds(50, 200, 100, 30);
        m_LoginResetButton.setBounds(200, 200, 100, 30);
        m_LoginRegisterButton.setBounds(125, 250, 100, 30);
    }

    //Login
    private void addLoginComponentsToContainer() {
        m_LoginContainer.add(m_LoginUserNameLabel);
        m_LoginContainer.add(m_LoginPasswordLabel);
        m_LoginContainer.add(m_LoginUserNameTextField);
        m_LoginContainer.add(m_LoginPasswordField);
        m_LoginContainer.add(m_LoginShowPasswordCheckBox);
        m_LoginContainer.add(m_LoginButton);
        m_LoginContainer.add(m_LoginResetButton);
        m_LoginContainer.add(m_LoginRegisterButton);
    }

    //Login
    private void addLoginActionEvents() {

        m_LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Coding Part of LOGIN button

                String userText;
                String pwdText;
                userText = m_LoginUserNameTextField.getText();
                pwdText = new String(m_LoginPasswordField.getPassword());
                User user = new User(userText, pwdText);
                m_Vm.isUserExists(user);
            }
        });

        m_LoginResetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Coding Part of RESET button
                m_LoginUserNameTextField.setText("");
                m_LoginPasswordField.setText("");
            }
        });

        m_LoginShowPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Coding Part of showPassword JCheckBox
                if (m_LoginShowPasswordCheckBox.isSelected()) {
                    m_LoginPasswordField.setEchoChar((char) 0);
                } else {
                    m_LoginPasswordField.setEchoChar('*');
                }
            }
        });

        m_LoginRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //close this windows
                m_LoginFrame.dispose();

                // open register window
                m_RegisterFrame.init();
                m_RegisterFrame.start();

            }
        });
    }

}

