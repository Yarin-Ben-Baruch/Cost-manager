package il.ac.hit.view;

import il.ac.hit.viewmodel.IViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateItemView {
    private String[] m_ColumnsToUpdate;
    // Update Item Button, TextField, Label and Submit Action.
    private JFrame updateFrame;
    private JButton updateItemToDBButton;
    private JLabel updateItemColNameLabel, updateItemDataToSetLabel;
    private JComboBox updateItemColNameComboBox;
    private JLabel updateItemCostNumberLabel;
    private JTextField updateItemDataToSetTextField, updateItemCostNumberTextField;
    private final IViewModel viewModel;
    private final String userName;

    /**
     * The ctor call the init and start methods to start and initialized the update view.
     * @param vm
     */
    public UpdateItemView(IViewModel vm,String userName) {
        this.viewModel = vm;
        this.userName = userName;
        updateItemInit();
        updateItemStart();
    }

    private void updateItemInit() {
        m_ColumnsToUpdate = new String[]{"name", "description", "category", "sum"};

        updateItemColNameLabel = new JLabel("Col name to change:");
        updateItemColNameComboBox = new JComboBox(m_ColumnsToUpdate);
        updateItemDataToSetLabel = new JLabel("Data to set:");
        updateItemDataToSetTextField = new JTextField();
        updateItemToDBButton = new JButton("Update item to DB");
        updateItemCostNumberLabel = new JLabel("Cost number:");
        updateItemCostNumberTextField = new JTextField();
        updateFrame = new JFrame();
    }

    private void updateItemStart() {
        updateFrame.setLayout(new BorderLayout());
        updateFrame.add(updateItemColNameLabel);
        updateFrame.add(updateItemColNameComboBox);
        updateFrame.add(updateItemDataToSetLabel);
        updateFrame.add(updateItemDataToSetTextField);
        updateFrame.add(updateItemCostNumberLabel);
        updateFrame.add(updateItemCostNumberTextField);
        updateFrame.add(updateItemCostNumberLabel);
        updateFrame.add(updateItemCostNumberTextField);
        updateFrame.add(updateItemToDBButton);

        updateFrame.setTitle("Update Cost");
        setComponentsSizeAndLocation();
        updateFrame.setVisible(true);

        updateItemToDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.updateItem((String) updateItemColNameComboBox.getSelectedItem(),
                        updateItemDataToSetTextField.getText(),
                        updateItemCostNumberTextField.getText(),
                        userName);

                updateFrame.dispose();
            }
        });
    }

    private void setComponentsSizeAndLocation() {
        updateFrame.setSize(400,300);
        updateFrame.setResizable(false);
        updateItemColNameLabel.setBounds(50, 30, 150, 30);
        updateItemDataToSetLabel.setBounds(updateItemColNameLabel.getX(), updateItemColNameLabel.getY()+50, 100, 30);
        updateItemCostNumberLabel.setBounds(updateItemColNameLabel.getX(), updateItemDataToSetLabel.getY()+50, 100, 30);
        updateItemColNameComboBox.setBounds(updateItemColNameLabel.getWidth() + updateItemColNameLabel.getX(), updateItemColNameLabel.getY(), 150, 30);
        updateItemDataToSetTextField.setBounds(updateItemColNameComboBox.getX(), updateItemDataToSetLabel.getY(), 150, 30);
        updateItemCostNumberTextField.setBounds(updateItemColNameComboBox.getX(), updateItemCostNumberLabel.getY(), 150, 30);
        updateItemToDBButton.setSize(150,30);
        updateItemToDBButton.setLocation((updateFrame.getWidth()- updateItemToDBButton.getWidth())/2 , updateItemCostNumberTextField.getY() + updateItemCostNumberTextField.getHeight()+30);

    }

}
