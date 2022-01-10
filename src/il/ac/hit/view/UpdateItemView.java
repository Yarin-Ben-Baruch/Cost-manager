package il.ac.hit.view;
import il.ac.hit.viewmodel.IViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class that represents the information update button in the app.
 * Inside the department there is data about what happens after the click of a button and what the display is.
 */
public class UpdateItemView {
    // Update Item Button, TextField, Label and Submit Action.
    private JFrame updateFrame;
    private JButton updateItemToDBButton;
    private JLabel updateItemColNameLabel, updateItemDataToSetLabel;
    private JComboBox<String> updateItemColNameComboBox;
    private JLabel updateItemCostNumberLabel;
    private JTextField updateItemDataToSetTextField, updateItemCostNumberTextField;
    private final IViewModel viewModel;
    private final String userName;

    /**
     * The ctor call the init and start methods to start and initialized the update view.
     * @param vm An object that holds the link to the viewModel class
     */
    public UpdateItemView(IViewModel vm,String userName) {
        this.viewModel = vm;
        this.userName = userName;
        updateItemInit();
        updateItemStart();
    }

    // Init method.
    private void updateItemInit() {
        String[] columnsToUpdate;
        columnsToUpdate = new String[]{"name", "description", "category", "sum"};

        // Create new fields.
        updateItemColNameLabel = new JLabel("Col name to change:");
        updateItemColNameComboBox = new JComboBox<>(columnsToUpdate);
        updateItemDataToSetLabel = new JLabel("Data to set:");
        updateItemDataToSetTextField = new JTextField();
        updateItemToDBButton = new JButton("Update item to DB");
        updateItemCostNumberLabel = new JLabel("Cost number:");
        updateItemCostNumberTextField = new JTextField();
        updateFrame = new JFrame();
    }

    // Start method.
    private void updateItemStart() {

        setColorToLabel();

        // this will center the frame
        updateFrame.setLocationRelativeTo(null);

        // Add image background.
        updateFrame.setContentPane(new JLabel(new ImageIcon("src/images/General background.jpg")));

        // Add components to the frame.
        updateFrame.add(updateItemColNameLabel);
        updateFrame.add(updateItemColNameComboBox);
        updateFrame.add(updateItemDataToSetLabel);
        updateFrame.add(updateItemDataToSetTextField);
        updateFrame.add(updateItemCostNumberLabel);
        updateFrame.add(updateItemCostNumberTextField);
        updateFrame.add(updateItemToDBButton);

        updateFrame.setLayout(new BorderLayout());
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

                // Close the frame.
                updateFrame.dispose();
            }
        });
    }

    // Set size and bounds.
    private void setComponentsSizeAndLocation() {
        updateFrame.setSize(400,300);
        updateFrame.setResizable(false);
        updateItemColNameLabel.setBounds(50, 30, 150, 30);
        updateItemDataToSetLabel.setBounds(updateItemColNameLabel.getX(), updateItemColNameLabel.getY()+50,
                100, 30);
        updateItemCostNumberLabel.setBounds(updateItemColNameLabel.getX(), updateItemDataToSetLabel.getY()+50,
                100, 30);
        updateItemColNameComboBox.setBounds(updateItemColNameLabel.getWidth() + updateItemColNameLabel.getX(),
                updateItemColNameLabel.getY(), 150, 30);
        updateItemDataToSetTextField.setBounds(updateItemColNameComboBox.getX(), updateItemDataToSetLabel.getY(),
                150, 30);
        updateItemCostNumberTextField.setBounds(updateItemColNameComboBox.getX(), updateItemCostNumberLabel.getY(),
                150, 30);
        updateItemToDBButton.setSize(150,30);
        updateItemToDBButton.setLocation((updateFrame.getWidth()- updateItemToDBButton.getWidth())/2 ,
                updateItemCostNumberTextField.getY() + updateItemCostNumberTextField.getHeight()+30);

    }

    // Set colors.
    private void setColorToLabel(){
        updateItemColNameLabel.setForeground(Color.WHITE);
        updateItemDataToSetLabel.setForeground(Color.WHITE);
        updateItemCostNumberLabel.setForeground(Color.WHITE);
    }
}
