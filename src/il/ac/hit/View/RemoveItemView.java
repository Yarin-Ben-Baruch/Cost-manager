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

    public RemoveItemView(IViewModel i_Vm) {
        m_ViewModel = i_Vm;
        removeItemInit();
        removeItemStart();
    }

    private void removeItemInit() {
        // Creating the RemoveItem Action
        m_RemoveItemFrame = new JFrame();
        m_RemoveItemFromDBButton = new JButton("Remove Item from the list");
        m_RemoveItemCostNumberLabel = new JLabel("Cost number:");
        m_RemoveItemCostNumberTextField = new JTextField(10);
        m_RemoveItemUsernameLabel = new JLabel("Username:");
        m_RemoveItemUsernameTextField = new JTextField(15);
    }

    private void removeItemStart() {
        // Creating the Remove Item Panel.
        m_RemoveItemFrame.setLayout(new GridLayout(4,2));
        m_RemoveItemFrame.add(m_RemoveItemCostNumberLabel);
        m_RemoveItemFrame.add(m_RemoveItemCostNumberTextField);
        m_RemoveItemFrame.add(m_RemoveItemUsernameLabel);
        m_RemoveItemFrame.add(m_RemoveItemUsernameTextField);
        m_RemoveItemFrame.add(m_RemoveItemFromDBButton);


        m_RemoveItemFrame.setSize(1000,700);
        m_RemoveItemFrame.setVisible(true);


        m_RemoveItemFromDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_ViewModel.removeItem(m_RemoveItemCostNumberTextField.getText(), m_RemoveItemUsernameTextField.getText());
                m_RemoveItemFrame.dispose();
            }
        });
    }


}
