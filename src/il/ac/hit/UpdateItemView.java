package il.ac.hit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateItemView {

    // Update Item Button, TextField, Label and Submit Action.
    private JFrame m_UpdateFrame;
    private JButton m_UpdateItemToDBButton;
    private JLabel m_UpdateItemColNameLabel, m_UpdateItemDataToSetLabel;
    private JTextField m_UpdateItemColNameTextField, m_UpdateItemDataToSetTextField;
    private JLabel m_UpdateItemCostNumberLabel, m_UpdateItemUsernameLabel;
    private JTextField m_UpdateItemUsernameTextField, m_UpdateItemCostNumberTextField;
    private IViewModel m_ViewModel;

    public UpdateItemView(IViewModel m_ViewModel) {
        this.m_ViewModel = m_ViewModel;
    }

    private void updateItemInit() {
        // Creating the UpdateItem Action
        m_UpdateItemColNameLabel = new JLabel("Col name to change:");
        m_UpdateItemColNameTextField = new JTextField(15);
        m_UpdateItemDataToSetLabel = new JLabel("Date to set:");
        m_UpdateItemDataToSetTextField = new JTextField(15);
        m_UpdateItemToDBButton = new JButton("Update item to DB");
        m_UpdateItemCostNumberLabel = new JLabel("Cost number:");
        m_UpdateItemCostNumberTextField = new JTextField(10);
        m_UpdateItemUsernameLabel = new JLabel("Username:");
        m_UpdateItemUsernameTextField = new JTextField(15);
        m_UpdateFrame = new JFrame();
    }

    private void updateItemStart() {
        // Creating the Update Item Panel.
        m_UpdateFrame.setLayout(new GridLayout(5,2));
        m_UpdateFrame.add(m_UpdateItemColNameLabel);
        m_UpdateFrame.add(m_UpdateItemColNameTextField);
        m_UpdateFrame.add(m_UpdateItemDataToSetLabel);
        m_UpdateFrame.add(m_UpdateItemDataToSetTextField);
        m_UpdateFrame.add(m_UpdateItemCostNumberLabel);
        m_UpdateFrame.add(m_UpdateItemCostNumberTextField);
        m_UpdateFrame.add(m_UpdateItemUsernameLabel);
        m_UpdateFrame.add(m_UpdateItemUsernameTextField);
        m_UpdateFrame.add(m_UpdateItemCostNumberLabel);
        m_UpdateFrame.add(m_UpdateItemCostNumberTextField);
        m_UpdateFrame.add(m_UpdateItemUsernameLabel);
        m_UpdateFrame.add(m_UpdateItemUsernameTextField);
        m_UpdateFrame.add(m_UpdateItemToDBButton);

        m_UpdateItemToDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_ViewModel.updateItem(m_UpdateItemColNameTextField.getText(),
                        m_UpdateItemDataToSetTextField.getText(),
                        Integer.parseInt(m_UpdateItemCostNumberTextField.getText()),
                        m_UpdateItemUsernameTextField.getText());
            }
        });
    }

}
