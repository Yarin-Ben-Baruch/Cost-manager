//package il.ac.hit;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.Collection;
//
//public class LoginPageGUI{
//
//    //Creating login page frame
//    private JFrame m_LoginFrame;
//    private Container m_Container;
//    private JLabel m_UserNameLabel, m_PasswordLabel;
//    private JTextField m_UserNameTextField;
//    private JPasswordField m_PasswordField;
//    private JButton m_LoginButton, m_ResetButton, m_RegisterButton;
//    private JCheckBox m_ShowPasswordCheckBox;
//
//
//    public void start(){
//
//        setLayoutManager();
//        setLocationAndSize();
//        addComponentsToContainer();
//        addActionEvent();
//
//        m_LoginFrame.setTitle("Login Form");
//        m_LoginFrame.setVisible(true);
//        m_LoginFrame.setBounds(10, 10, 350, 400);
//        m_LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        m_LoginFrame.setResizable(false);
//    }
//
//    public void init(){
//        m_LoginFrame = new JFrame();
//        m_Container = m_LoginFrame.getContentPane();
//        m_UserNameLabel = new JLabel("USERNAME");
//        m_PasswordLabel = new JLabel("PASSWORD");
//        m_UserNameTextField = new JTextField();
//        m_PasswordField = new JPasswordField();
//        m_LoginButton = new JButton("Login");
//        m_ResetButton = new JButton("Reset");
//        m_RegisterButton = new JButton("Register");
//        m_ShowPasswordCheckBox = new JCheckBox("Show Password");
//    }
//
//    public void setLayoutManager() {
//        m_Container.setLayout(null);
//    }
//
//    public void setLocationAndSize() {
//
//        m_UserNameLabel.setBounds(50, 70, 100, 30);
//        m_PasswordLabel.setBounds(50, 120, 100, 30);
//        m_UserNameTextField.setBounds(150, 70, 150, 30);
//        m_PasswordField.setBounds(150, 120, 150, 30);
//        m_ShowPasswordCheckBox.setBounds(150, 150, 150, 30);
//        m_LoginButton.setBounds(50, 200, 100, 30);
//        m_ResetButton.setBounds(200, 200, 100, 30);
//        m_RegisterButton.setBounds(125, 250, 100, 30);
//    }
//
//    public void addComponentsToContainer() {
//        m_Container.add(m_UserNameLabel);
//        m_Container.add(m_PasswordLabel);
//        m_Container.add(m_UserNameTextField);
//        m_Container.add(m_PasswordField);
//        m_Container.add(m_ShowPasswordCheckBox);
//        m_Container.add(m_LoginButton);
//        m_Container.add(m_ResetButton);
//        m_Container.add(m_RegisterButton);
//    }
//
//    public void addActionEvent() {
//
//        m_LoginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //Coding Part of LOGIN button
//
//                String userText;
//                String pwdText;
//                userText = m_UserNameTextField.getText();
//                pwdText = new String(m_PasswordField.getPassword());
//                User user = new User(userText, pwdText);
//
//                if(m_Users.contains(user)) {
//                    JOptionPane.showMessageDialog(m_LoginFrame, "Login Successful");
//                    initApplication();
//                    startApplication();
//                } else {
//                    JOptionPane.showMessageDialog(m_LoginFrame, "Invalid Username or Password");
//                }
//            }
//        });
//
//        m_ResetButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //Coding Part of RESET button
//                m_UserNameTextField.setText("");
//                m_PasswordField.setText("");
//            }
//        });
//
//        m_ShowPasswordCheckBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //Coding Part of showPassword JCheckBox
//                if (m_ShowPasswordCheckBox.isSelected()) {
//                    m_PasswordField.setEchoChar((char) 0);
//                } else {
//                    m_PasswordField.setEchoChar('*');
//                }
//            }
//        });
//
//        m_RegisterButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //Coding Part of register button
//
//                //close this windows
//                m_LoginFrame.dispose();
//                //
//                Register register = new Register();
//                register.init();
//                register.start();
//            }
//        });
//    }
//
//}
//
//
//class Login {
//    public static void main(String[] a) {
//        LoginPageGUI frame = new LoginPageGUI();
//        frame.init();
//        frame.start();
////        frame.setTitle("Login Form");
////        frame.setVisible(true);
////        frame.setBounds(10, 10, 370, 600);
////        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        frame.setResizable(false);
//
//    }
//}