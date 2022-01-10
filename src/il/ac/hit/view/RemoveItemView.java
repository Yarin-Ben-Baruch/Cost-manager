package il.ac.hit.view;
import il.ac.hit.viewmodel.IViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class that represents delete Information button in the app.
 * Inside the department there is data about what happens after the click of a button and what the display is.
 */
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
     * @param vm An object that holds the link to the viewModel class
     * @param userName Username of the person who is connected to the app
     */
    public RemoveItemView(IViewModel vm, String userName) {
        viewModel = vm;
        this.userName = userName;
        removeItemInit();
        removeItemStart();
    }

    // Create new fields.
    private void removeItemInit() {
        removeItemFrame = new JFrame();
        removeItemFromDBButton = new JButton("Remove Item from the list");
        removeItemCostNumberLabel = new JLabel("Cost number:");
        removeItemCostNumberTextField = new JTextField();
    }

    // Start frame.
    private void removeItemStart() {

        // Add a color.
        removeItemCostNumberLabel.setForeground(Color.WHITE);

        // this will center the frame
        removeItemFrame.setLocationRelativeTo(null);

        // Add image background.
        removeItemFrame.setContentPane(new JLabel(new ImageIcon("src/images/General background.jpg")));

        // add components to the frame.
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

    // Set size and locations.
    private void setComponentsSizeAndLocations() {
        removeItemFrame.setSize(400,200);
        removeItemFrame.setResizable(false);
        removeItemCostNumberLabel.setBounds(50, 30, 100, 30);
        removeItemCostNumberTextField.setBounds(removeItemCostNumberLabel.getWidth() +
                removeItemCostNumberLabel.getX(), removeItemCostNumberLabel.getY(), 150, 30);
        removeItemFromDBButton.setSize(200,30);
        removeItemFromDBButton.setLocation((removeItemFrame.getWidth()- removeItemFromDBButton.getWidth())/2 ,
                removeItemCostNumberTextField.getY() + removeItemCostNumberTextField.getHeight()+30);

    }
}
