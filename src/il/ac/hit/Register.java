package il.ac.hit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Register extends JFrame implements ActionListener {

    // Components of the Form
    private Container m_Container;
    private JLabel m_TitleLabel, m_UserNameLabel, m_PasswordLabel, m_ResetJLabel;
    private JTextField m_UsernameTextField, m_PasswordTextField;
    private JCheckBox m_TermCheckBox;
    private JButton m_SubmitButton, m_ResetJButton;
    private JTextArea m_TermOutTextArea, m_RestAddTextArea;

    public void init(){
        m_TitleLabel = new JLabel("Registration Form");
        m_TitleLabel.setFont(new Font("Arial", Font.PLAIN, 30));

        m_UserNameLabel = new JLabel("UserName");
        m_UserNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        m_UsernameTextField = new JTextField();
        m_UsernameTextField.setFont(new Font("Arial", Font.PLAIN, 15));

        m_PasswordLabel = new JLabel("Password");
        m_PasswordLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        m_PasswordTextField = new JTextField();
        m_PasswordTextField.setFont(new Font("Arial", Font.PLAIN, 15));

        m_TermCheckBox = new JCheckBox("Accept Terms And Conditions.");
        m_TermCheckBox.setFont(new Font("Arial", Font.PLAIN, 15));

        m_SubmitButton = new JButton("Submit");
        m_SubmitButton.setFont(new Font("Arial", Font.PLAIN, 15));

        m_ResetJButton = new JButton("Reset");
        m_ResetJButton.setFont(new Font("Arial", Font.PLAIN, 15));

        m_ResetJLabel = new JLabel("");
        m_ResetJLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        m_TermOutTextArea = new JTextArea();
        m_TermOutTextArea.setFont(new Font("Arial", Font.PLAIN, 15));

        m_RestAddTextArea = new JTextArea();
        m_RestAddTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
    }

    public void start(){

        setTitle("Registration Form");
        setBounds(300, 90, 800, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        m_Container = getContentPane();
        m_Container.setLayout(null);
        m_Container.setBackground(Color.getColor("50",Color.lightGray));

        // Registration title
        m_TitleLabel.setSize(300, 30);
        m_TitleLabel.setLocation(300, 30);
        m_Container.add(m_TitleLabel);

        // The text on which the username is written
        m_UserNameLabel.setSize(100, 20);
        m_UserNameLabel.setLocation(100, 100);

        // The text that the user enters information into
        m_UsernameTextField.setSize(190, 20);
        m_UsernameTextField.setLocation(200, 100);

        // Add to container
        m_Container.add(m_UserNameLabel);
        m_Container.add(m_UsernameTextField);

        // The text on which the password is written
        m_PasswordLabel.setSize(100, 20);
        m_PasswordLabel.setLocation(100, 150);

        // The text that the user enters information into - password
        m_PasswordTextField.setSize(190, 20);
        m_PasswordTextField.setLocation(200, 150);

        // Add to container
        m_Container.add(m_PasswordLabel);
        m_Container.add(m_PasswordTextField);


        // Approval of the terms
        m_TermCheckBox.setSize(250, 20);
        m_TermCheckBox.setLocation(150, 250);
        m_Container.add(m_TermCheckBox);

        // submit button
        m_SubmitButton.setSize(100, 20);
        m_SubmitButton.setLocation(150, 300);
        m_SubmitButton.addActionListener(this);
        m_Container.add(m_SubmitButton);

        // Reset button
        m_ResetJButton.setSize(100, 20);
        m_ResetJButton.setLocation(270, 300);
        m_ResetJButton.addActionListener(this);
        m_Container.add(m_ResetJButton);

        // Clearing the information
        m_ResetJLabel.setSize(500, 25);
        m_ResetJLabel.setLocation(100, 500);
        m_Container.add(m_ResetJLabel);


        // The right panel
        m_TermOutTextArea.setSize(200, 170);
        m_TermOutTextArea.setLocation(500, 100);
        m_TermOutTextArea.setLineWrap(true);
        m_TermOutTextArea.setEditable(false);
        m_Container.add(m_TermOutTextArea);

        m_RestAddTextArea.setSize(200, 60);
        m_RestAddTextArea.setLocation(500, 100);
        m_RestAddTextArea.setLineWrap(true);
        m_Container.add(m_RestAddTextArea);

        setVisible(true);

    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == m_SubmitButton) {

            // Makes sure he has approved the terms, and UserName, password not empty
            if (m_TermCheckBox.isSelected()
                && m_UsernameTextField.getText().length() != 0
                && m_PasswordTextField.getText().length() != 0) {

                String data
                        = "Name : "
                        + m_UsernameTextField.getText() + "\n"
                        + "Password : "
                        + m_PasswordTextField.getText() + "\n";

                m_TermOutTextArea.setText(data);
                m_TermOutTextArea.setEditable(false);
                m_ResetJLabel.setText("Registration Successfully..");
            }
            // If not confirmed
            else {
                m_TermOutTextArea.setText("");
                m_RestAddTextArea.setText("");
                m_ResetJLabel.setText("Please accept the"
                        + " terms & conditions..");
            }
        }
        // Action at the touch of a button rest
        else if (e.getSource() == m_ResetJButton) {
            String def = "";
            m_UsernameTextField.setText(def);
            m_PasswordTextField.setText(def);
            m_ResetJLabel.setText(def);
            m_TermOutTextArea.setText(def);
            m_TermCheckBox.setSelected(false);
            m_RestAddTextArea.setText(def);
        }
    }
}

// Driver Code
//class Registration {
//
//    public static void main(String[] args) throws Exception {
//        Register f = new Register();
//        f.init();
//        f.start();
//    }
//}


