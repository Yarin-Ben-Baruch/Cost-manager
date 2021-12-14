package il.ac.hit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPageGUI extends JFrame implements ActionListener {

    Container m_Container;
    JLabel m_UserNameLabel, m_PasswordLabel;
    JTextField m_UserNameTextField;
    JPasswordField m_PasswordField;
    JButton m_LoginButton, m_ResetButton;
    JCheckBox m_ShowPasswordCheckBox;

    public void start(){

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        setTitle("Login Form");
        setVisible(true);
        setBounds(10, 10, 370, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void init(){
        m_Container = getContentPane();
        m_UserNameLabel = new JLabel("USERNAME");
        m_PasswordLabel = new JLabel("PASSWORD");
        m_UserNameTextField = new JTextField();
        m_PasswordField = new JPasswordField();
        m_LoginButton = new JButton("LOGIN");
        m_ResetButton = new JButton("RESET");
        m_ShowPasswordCheckBox = new JCheckBox("Show Password");


    }

    public void setLayoutManager() {
        m_Container.setLayout(null);
    }

    public void setLocationAndSize() {

        m_UserNameLabel.setBounds(50, 150, 100, 30);
        m_PasswordLabel.setBounds(50, 220, 100, 30);
        m_UserNameTextField.setBounds(150, 150, 150, 30);
        m_PasswordField.setBounds(150, 220, 150, 30);
        m_ShowPasswordCheckBox.setBounds(150, 250, 150, 30);
        m_LoginButton.setBounds(50, 300, 100, 30);
        m_ResetButton.setBounds(200, 300, 100, 30);

    }

    public void addComponentsToContainer() {
        m_Container.add(m_UserNameLabel);
        m_Container.add(m_PasswordLabel);
        m_Container.add(m_UserNameTextField);
        m_Container.add(m_PasswordField);
        m_Container.add(m_ShowPasswordCheckBox);
        m_Container.add(m_LoginButton);
        m_Container.add(m_ResetButton);
    }

    public void addActionEvent() {
        m_LoginButton.addActionListener(this);
        m_ResetButton.addActionListener(this);
        m_ShowPasswordCheckBox.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding Part of LOGIN button
        if (e.getSource() == m_LoginButton) {
            String userText;
            String pwdText;
            userText = m_UserNameTextField.getText();
            pwdText = new String(this.m_PasswordField.getPassword());
            if (userText.equalsIgnoreCase("dani") && pwdText.equalsIgnoreCase("12345")) {
                JOptionPane.showMessageDialog(this, "Login Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }

        }
        //Coding Part of RESET button
        if (e.getSource() == m_ResetButton) {
            m_UserNameTextField.setText("");
            m_PasswordField.setText("");
        }
        //Coding Part of showPassword JCheckBox
        if (e.getSource() == m_ShowPasswordCheckBox) {
            if (m_ShowPasswordCheckBox.isSelected()) {
                m_PasswordField.setEchoChar((char) 0);
            } else {
                m_PasswordField.setEchoChar('*');
            }
        }
    }

}

class Login {
    public static void main(String[] a) {
        LoginPageGUI frame = new LoginPageGUI();
        frame.init();
        frame.start();
//        frame.setTitle("Login Form");
//        frame.setVisible(true);
//        frame.setBounds(10, 10, 370, 600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);

    }

}