package il.ac.hit.View;

import il.ac.hit.ViewModel.IViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveItemView {

    // Remove Item  Button, TextField, Label and Submit Action.
    private JFrame m_RemoveItemFrame;
    private JButton m_RemoveItemFromDBButton;
    private JLabel m_RemoveItemCostNumberLabel, m_RemoveItemUsernameLabel;
    private JTextField m_RemoveItemUsernameTextField, m_RemoveItemCostNumberTextField;
    private IViewModel m_ViewModel;

    /**
     * The ctor call the init and start methods.
     * @param i_Vm
     */
    public RemoveItemView(IViewModel i_Vm) {
        m_ViewModel = i_Vm;
        removeItemInit();
        removeItemStart();
    }

    private void removeItemInit() {
        m_RemoveItemFrame = new JFrame();
        m_RemoveItemFromDBButton = new JButton("Remove Item from the list");
        m_RemoveItemCostNumberLabel = new JLabel("Cost number:");
        m_RemoveItemCostNumberTextField = new JTextField(10);
        m_RemoveItemUsernameLabel = new JLabel("Username:");
        m_RemoveItemUsernameTextField = new JTextField(15);
    }

    private void removeItemStart() {
        m_RemoveItemFrame.add(m_RemoveItemCostNumberLabel);
        m_RemoveItemFrame.add(m_RemoveItemCostNumberTextField);
        m_RemoveItemFrame.add(m_RemoveItemUsernameLabel);
        m_RemoveItemFrame.add(m_RemoveItemUsernameTextField);
        m_RemoveItemFrame.add(m_RemoveItemFromDBButton);

        m_RemoveItemFrame.setLayout(null);
        m_RemoveItemFrame.setTitle("Remove Cost");
        setComponentsSizeAndLocations();
        m_RemoveItemFrame.setVisible(true);

        m_RemoveItemFromDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_ViewModel.removeItem(m_RemoveItemCostNumberTextField.getText(), m_RemoveItemUsernameTextField.getText());
                m_RemoveItemFrame.dispose();
            }
        });
    }

    private void setComponentsSizeAndLocations() {
        m_RemoveItemFrame.setSize(400,250);

        m_RemoveItemCostNumberLabel.setBounds(50, 30, 100, 30);
        m_RemoveItemUsernameLabel.setBounds(m_RemoveItemCostNumberLabel.getX(),m_RemoveItemCostNumberLabel.getY()+50, 100, 30);
        m_RemoveItemCostNumberTextField.setBounds(m_RemoveItemCostNumberLabel.getWidth() + m_RemoveItemCostNumberLabel.getX(), m_RemoveItemCostNumberLabel.getY(), 150, 30);
        m_RemoveItemUsernameTextField.setBounds(m_RemoveItemUsernameLabel.getWidth() + m_RemoveItemUsernameLabel.getX(), m_RemoveItemUsernameLabel.getY(), 150, 30);
        m_RemoveItemFromDBButton.setSize(200,30);
        m_RemoveItemFromDBButton.setLocation((m_RemoveItemFrame.getWidth()-m_RemoveItemFromDBButton.getWidth())/2 , m_RemoveItemUsernameTextField.getY() + m_RemoveItemUsernameTextField.getHeight()+30);

    }


}
