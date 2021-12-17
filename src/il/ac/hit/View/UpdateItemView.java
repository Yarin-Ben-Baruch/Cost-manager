package il.ac.hit.View;

import il.ac.hit.ViewModel.IViewModel;

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
        updateItemInit();
        updateItemStart();
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
        m_UpdateFrame.setLayout(null);
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

        m_UpdateFrame.setTitle("Update Cost");
        m_UpdateFrame.setSize(400,350);

        m_UpdateItemColNameLabel.setBounds(50, 30, 150, 30);
        m_UpdateItemDataToSetLabel.setBounds(m_UpdateItemColNameLabel.getX(),m_UpdateItemColNameLabel.getY()+50, 100, 30);
        m_UpdateItemCostNumberLabel.setBounds(m_UpdateItemColNameLabel.getX(),m_UpdateItemDataToSetLabel.getY()+50, 100, 30);
        m_UpdateItemUsernameLabel.setBounds(m_UpdateItemColNameLabel.getX(),m_UpdateItemCostNumberLabel.getY() + 50, 100, 30);

        m_UpdateItemColNameTextField.setBounds(m_UpdateItemColNameLabel.getWidth() + m_UpdateItemColNameLabel.getX(), m_UpdateItemColNameLabel.getY(), 150, 30);
        m_UpdateItemDataToSetTextField.setBounds(m_UpdateItemColNameTextField.getX(), m_UpdateItemDataToSetLabel.getY(), 150, 30);
        m_UpdateItemCostNumberTextField.setBounds(m_UpdateItemColNameTextField.getX(), m_UpdateItemCostNumberLabel.getY(), 150, 30);
        m_UpdateItemUsernameTextField.setBounds(m_UpdateItemColNameTextField.getX(), m_UpdateItemUsernameLabel.getY(), 150, 30);
        m_UpdateItemToDBButton.setSize(150,30);
        m_UpdateItemToDBButton.setLocation((m_UpdateFrame.getWidth()-m_UpdateItemToDBButton.getWidth())/2 , m_UpdateItemUsernameTextField.getY() + m_UpdateItemUsernameTextField.getHeight()+30);


        m_UpdateFrame.setVisible(true);

        m_UpdateItemToDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_ViewModel.updateItem(m_UpdateItemColNameTextField.getText(),
                        m_UpdateItemDataToSetTextField.getText(),
                        m_UpdateItemCostNumberTextField.getText(),
                        m_UpdateItemUsernameTextField.getText());

                m_UpdateFrame.dispose();
            }
        });
    }

}
