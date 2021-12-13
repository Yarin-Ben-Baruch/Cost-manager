package il.ac.hit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Register extends JFrame implements ActionListener {

    // Components of the Form
    private Container m_Container;
    private JLabel m_TitleLabel, m_UserNameLabel;
    private JTextField m_UsernameTextField;
    private JLabel m_PasswordLabel;
    private JTextField m_PasswordTextField;
    private JCheckBox m_TermCheckBox;
    private JButton m_SubmitButton, m_ResetJButton;
    private JTextArea tout;
    private JLabel m_ResetJLabel;
    private JTextArea resadd;


    // constructor, to initialize the components
    // with default values.
    public Register() {
        setTitle("Registration Form");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        m_Container = getContentPane();
        m_Container.setLayout(null);

        // כותרת של ההרשמה
        m_TitleLabel = new JLabel("Registration Form");
        m_TitleLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        m_TitleLabel.setSize(300, 30);
        m_TitleLabel.setLocation(300, 30);
        m_Container.add(m_TitleLabel);

        // החלק של הכנסת השם
        m_UserNameLabel = new JLabel("UserName");
        m_UserNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        m_UserNameLabel.setSize(100, 20);
        m_UserNameLabel.setLocation(100, 100);

        m_UsernameTextField = new JTextField();
        m_UsernameTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        m_UsernameTextField.setSize(190, 20);
        m_UsernameTextField.setLocation(200, 100);

        m_Container.add(m_UserNameLabel);
        m_Container.add(m_UsernameTextField);

        // החלק של הכנסת הסיסמא
        m_PasswordLabel = new JLabel("Password");
        m_PasswordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        m_PasswordLabel.setSize(100, 20);
        m_PasswordLabel.setLocation(100, 150);

        m_PasswordTextField = new JTextField();
        m_PasswordTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        m_PasswordTextField.setSize(190, 20);
        m_PasswordTextField.setLocation(200, 150);

        m_Container.add(m_PasswordLabel);
        m_Container.add(m_PasswordTextField);


        // החלק של אישור התנאים
        m_TermCheckBox = new JCheckBox("Accept Terms And Conditions.");
        m_TermCheckBox.setFont(new Font("Arial", Font.PLAIN, 15));
        m_TermCheckBox.setSize(250, 20);
        m_TermCheckBox.setLocation(150, 250);
        m_Container.add(m_TermCheckBox);

        // כפתור של submit
        m_SubmitButton = new JButton("Submit");
        m_SubmitButton.setFont(new Font("Arial", Font.PLAIN, 15));
        m_SubmitButton.setSize(100, 20);
        m_SubmitButton.setLocation(150, 300);
        m_SubmitButton.addActionListener(this);
        m_Container.add(m_SubmitButton);

        // כפתור של reset
        m_ResetJButton = new JButton("Reset");
        m_ResetJButton.setFont(new Font("Arial", Font.PLAIN, 15));
        m_ResetJButton.setSize(100, 20);
        m_ResetJButton.setLocation(270, 300);
        m_ResetJButton.addActionListener(this);
        m_Container.add(m_ResetJButton);


        m_ResetJLabel = new JLabel("");
        m_ResetJLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        m_ResetJLabel.setSize(500, 25);
        m_ResetJLabel.setLocation(100, 500);
        m_Container.add(m_ResetJLabel);


        // שניהם עובדים על הלוח מצד ימין
        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 15));
        tout.setSize(200, 250);
        tout.setLocation(500, 100);
        tout.setLineWrap(true);
        tout.setEditable(false);
        m_Container.add(tout);

        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(500, 100);
        resadd.setLineWrap(true);
        m_Container.add(resadd);

        setVisible(true);
    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == m_SubmitButton) {
            if (m_TermCheckBox.isSelected()) {
                String data
                        = "Name : "
                        + m_UsernameTextField.getText() + "\n"
                        + "Password : "
                        + m_PasswordTextField.getText() + "\n";

                tout.setText(data);
                tout.setEditable(false);
                m_ResetJLabel.setText("Registration Successfully..");
            }
            else {
                tout.setText("");
                resadd.setText("");
                m_ResetJLabel.setText("Please accept the"
                        + " terms & conditions..");
            }
        }
        else if (e.getSource() == m_ResetJButton) {
            String def = "";
            m_UsernameTextField.setText(def);
            m_PasswordTextField.setText(def);
            m_ResetJLabel.setText(def);
            tout.setText(def);
            m_TermCheckBox.setSelected(false);
            resadd.setText(def);
        }
    }

}

// Driver Code
class Registration {

    public static void main(String[] args) throws Exception {
        Register f = new Register();
    }
}


