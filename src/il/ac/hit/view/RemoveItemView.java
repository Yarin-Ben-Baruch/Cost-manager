package il.ac.hit.view;

import il.ac.hit.viewmodel.IViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveItemView {

    // Remove Item  Button, TextField, Label and Submit Action.
    private JFrame removeItemFrame;
    private JButton removeItemFromDBButton;
    private JLabel removeItemCostNumberLabel;
    private JTextField removeItemCostNumberTextField;
    private final IViewModel viewModel;
    private final String userName;

    /**
     * The ctor call the init and start methods.
     * @param vm
     * @param userName
     */
    public RemoveItemView(IViewModel vm, String userName) {
        viewModel = vm;
        this.userName = userName;
        removeItemInit();
        removeItemStart();
    }

    private void removeItemInit() {
        removeItemFrame = new JFrame();
        removeItemFromDBButton = new JButton("Remove Item from the list");
        removeItemCostNumberLabel = new JLabel("Cost number:");
        removeItemCostNumberTextField = new JTextField();
    }

    private void removeItemStart() {
        removeItemFrame.add(removeItemCostNumberLabel);
        removeItemFrame.add(removeItemCostNumberTextField);
        removeItemFrame.add(removeItemFromDBButton);

        removeItemFrame.setLayout(new BorderLayout());
        removeItemFrame.setTitle("Remove Cost");
        setComponentsSizeAndLocations();
        removeItemFrame.setVisible(true);

        removeItemFromDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.removeItem(removeItemCostNumberTextField.getText(), userName);
                removeItemFrame.dispose();
            }
        });
    }

    private void setComponentsSizeAndLocations() {
        removeItemFrame.setSize(400,200);
        removeItemFrame.setResizable(false);
        removeItemCostNumberLabel.setBounds(50, 30, 100, 30);
        removeItemCostNumberTextField.setBounds(removeItemCostNumberLabel.getWidth() + removeItemCostNumberLabel.getX(), removeItemCostNumberLabel.getY(), 150, 30);
        removeItemFromDBButton.setSize(200,30);
        removeItemFromDBButton.setLocation((removeItemFrame.getWidth()- removeItemFromDBButton.getWidth())/2 , removeItemCostNumberTextField.getY() + removeItemCostNumberTextField.getHeight()+30);

    }

}
