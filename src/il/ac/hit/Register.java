package il.ac.hit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Register extends JFrame implements ActionListener {

    // Components of the Form
    private Container m_Container;
    private JLabel m_Title,m_UserName;
    private JTextField m_TextUsername;
    private JLabel m_Password;
    private JTextField m_TextPassword;
    private JCheckBox m_TermCB;
    private JButton sub;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;
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
        m_Title = new JLabel("Registration Form");
        m_Title.setFont(new Font("Arial", Font.PLAIN, 30));
        m_Title.setSize(300, 30);
        m_Title.setLocation(300, 30);
        m_Container.add(m_Title);

        // החלק של הכנסת השם
        m_UserName = new JLabel("UserName");
        m_UserName.setFont(new Font("Arial", Font.PLAIN, 20));
        m_UserName.setSize(100, 20);
        m_UserName.setLocation(100, 100);

        m_TextUsername = new JTextField();
        m_TextUsername.setFont(new Font("Arial", Font.PLAIN, 15));
        m_TextUsername.setSize(190, 20);
        m_TextUsername.setLocation(200, 100);

        m_Container.add(m_UserName);
        m_Container.add(m_TextUsername);

        // החלק של הכנסת הסיסמא
        m_Password = new JLabel("Password");
        m_Password.setFont(new Font("Arial", Font.PLAIN, 20));
        m_Password.setSize(100, 20);
        m_Password.setLocation(100, 150);

        m_TextPassword = new JTextField();
        m_TextPassword.setFont(new Font("Arial", Font.PLAIN, 15));
        m_TextPassword.setSize(190, 20);
        m_TextPassword.setLocation(200, 150);

        m_Container.add(m_Password);
        m_Container.add(m_TextPassword);


        // החלק של אישור התנאים
        m_TermCB = new JCheckBox("Accept Terms And Conditions.");
        m_TermCB.setFont(new Font("Arial", Font.PLAIN, 15));
        m_TermCB.setSize(250, 20);
        m_TermCB.setLocation(150, 250);
        m_Container.add(m_TermCB);

        // כפתור של submit
        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 300);
        sub.addActionListener(this);
        m_Container.add(sub);

        // כפתור של reset
        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 300);
        reset.addActionListener(this);
        m_Container.add(reset);


        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        m_Container.add(res);


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
        if (e.getSource() == sub) {
            if (m_TermCB.isSelected()) {
                String data
                        = "Name : "
                        + m_TextUsername.getText() + "\n"
                        + "Password : "
                        + m_TextPassword.getText() + "\n";

                tout.setText(data);
                tout.setEditable(false);
                res.setText("Registration Successfully..");
            }
            else {
                tout.setText("");
                resadd.setText("");
                res.setText("Please accept the"
                        + " terms & conditions..");
            }
        }
        else if (e.getSource() == reset) {
            String def = "";
            m_TextUsername.setText(def);
            m_TextPassword.setText(def);
            res.setText(def);
            tout.setText(def);
            m_TermCB.setSelected(false);
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


