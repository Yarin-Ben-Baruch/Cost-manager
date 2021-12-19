package il.ac.hit.View;

import il.ac.hit.ViewModel.IViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateItemView {
    private String[] m_ColumnsToUpdate;
    // Update Item Button, TextField, Label and Submit Action.
    private JFrame m_UpdateFrame;
    private JButton m_UpdateItemToDBButton;
    private JLabel m_UpdateItemColNameLabel, m_UpdateItemDataToSetLabel;
    private JComboBox m_UpdateItemColNameComboBox;
    private JLabel m_UpdateItemCostNumberLabel;
    private JTextField m_UpdateItemDataToSetTextField, m_UpdateItemCostNumberTextField;
    private IViewModel m_ViewModel;
    private String m_Username;

    /**
     * The ctor call the init and start methods to start and initialized the update view.
     * @param m_ViewModel
     */
    public UpdateItemView(IViewModel m_ViewModel,String i_Username) {
        this.m_ViewModel = m_ViewModel;
        m_Username = i_Username;
        updateItemInit();
        updateItemStart();
    }

    private void updateItemInit() {
        m_ColumnsToUpdate = new String[]{"name", "description", "category", "sum"};

        m_UpdateItemColNameLabel = new JLabel("Col name to change:");
        m_UpdateItemColNameComboBox = new JComboBox(m_ColumnsToUpdate);
        m_UpdateItemDataToSetLabel = new JLabel("Data to set:");
        m_UpdateItemDataToSetTextField = new JTextField();
        m_UpdateItemToDBButton = new JButton("Update item to DB");
        m_UpdateItemCostNumberLabel = new JLabel("Cost number:");
        m_UpdateItemCostNumberTextField = new JTextField();
        m_UpdateFrame = new JFrame();
    }

    private void updateItemStart() {
        m_UpdateFrame.setLayout(null);
        m_UpdateFrame.add(m_UpdateItemColNameLabel);
        m_UpdateFrame.add(m_UpdateItemColNameComboBox);
        m_UpdateFrame.add(m_UpdateItemDataToSetLabel);
        m_UpdateFrame.add(m_UpdateItemDataToSetTextField);
        m_UpdateFrame.add(m_UpdateItemCostNumberLabel);
        m_UpdateFrame.add(m_UpdateItemCostNumberTextField);
        m_UpdateFrame.add(m_UpdateItemCostNumberLabel);
        m_UpdateFrame.add(m_UpdateItemCostNumberTextField);
        m_UpdateFrame.add(m_UpdateItemToDBButton);

        m_UpdateFrame.setTitle("Update Cost");
        setComponentsSizeAndLocation();
        m_UpdateFrame.setVisible(true);

        m_UpdateItemToDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_ViewModel.updateItem((String)m_UpdateItemColNameComboBox.getSelectedItem(),
                        m_UpdateItemDataToSetTextField.getText(),
                        m_UpdateItemCostNumberTextField.getText(),
                        m_Username);

                m_UpdateFrame.dispose();
            }
        });
    }

    private void setComponentsSizeAndLocation() {
        m_UpdateFrame.setSize(400,300);

        m_UpdateItemColNameLabel.setBounds(50, 30, 150, 30);
        m_UpdateItemDataToSetLabel.setBounds(m_UpdateItemColNameLabel.getX(),m_UpdateItemColNameLabel.getY()+50, 100, 30);
        m_UpdateItemCostNumberLabel.setBounds(m_UpdateItemColNameLabel.getX(),m_UpdateItemDataToSetLabel.getY()+50, 100, 30);
        m_UpdateItemColNameComboBox.setBounds(m_UpdateItemColNameLabel.getWidth() + m_UpdateItemColNameLabel.getX(), m_UpdateItemColNameLabel.getY(), 150, 30);
        m_UpdateItemDataToSetTextField.setBounds(m_UpdateItemColNameComboBox.getX(), m_UpdateItemDataToSetLabel.getY(), 150, 30);
        m_UpdateItemCostNumberTextField.setBounds(m_UpdateItemColNameComboBox.getX(), m_UpdateItemCostNumberLabel.getY(), 150, 30);
        m_UpdateItemToDBButton.setSize(150,30);
        m_UpdateItemToDBButton.setLocation((m_UpdateFrame.getWidth()-m_UpdateItemToDBButton.getWidth())/2 , m_UpdateItemCostNumberTextField.getY() + m_UpdateItemCostNumberTextField.getHeight()+30);

    }

}
