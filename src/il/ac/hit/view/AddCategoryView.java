package il.ac.hit.view;

import il.ac.hit.model.Category;
import il.ac.hit.model.CostManagerException;
import il.ac.hit.model.Message;
import il.ac.hit.viewmodel.IViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A button inside the app. The button represents adding a new category to the category table.
 * When you press the button from the app, the object opens.
 */
public class AddCategoryView {

    // Add Category Button, TextField, Label and Submit Action.
    private JFrame addCategoryFrame;
    private JTextField addCategoryTextField;
    private JLabel addCategoryLabel;
    private JButton addCategoryToDBButton;
    private final IViewModel viewModel;
    private final ApplicationPageGUI applicationPageGUI;

    /**
     * Ctor that contain the init and start CategoryView
     * @param m_ViewModel An object that holds the link to the viewModel class
     * @param applicationPageGUI member to the "father" for up alert when create item failed.
     */
    public AddCategoryView(IViewModel m_ViewModel, ApplicationPageGUI applicationPageGUI) {
        this.viewModel = m_ViewModel;
        this.applicationPageGUI = applicationPageGUI;
        addCategoryInit();
        addCategoryStart();
    }

    // Init the frame.
    private void addCategoryInit() {
        // Creating the AddCategory Action.
        addCategoryFrame = new JFrame();
        addCategoryLabel = new JLabel("Category name:");
        addCategoryTextField = new JTextField(20);
        addCategoryToDBButton = new JButton("Add category to the list");
    }

    // Start the frame.
    private void addCategoryStart() {
        // Creating the Add Category Panel.
        addCategoryLabel.setForeground(Color.WHITE);

        // This will center the frame.
        addCategoryFrame.setLocationRelativeTo(null);
        // Add photo background.
        addCategoryFrame.setContentPane(new JLabel(new ImageIcon("src/images/General background.jpg")));
        // Add to frame.
        addCategoryFrame.add(addCategoryLabel,BorderLayout.LINE_START);
        addCategoryFrame.add(addCategoryTextField,BorderLayout.CENTER);
        addCategoryFrame.add(addCategoryToDBButton,BorderLayout.PAGE_END);

        addCategoryFrame.setLayout(new BorderLayout());
        addCategoryFrame.setTitle("Add Category");
        addCategoryFrame.setSize(400,150);
        addCategoryFrame.setResizable(false);

        // Set size and Bounds.
        addCategoryLabel.setBounds(65, 20, 100, 30);
        addCategoryTextField.setBounds(addCategoryLabel.getX()+ addCategoryLabel.getWidth()+10, addCategoryLabel.getY(), 160, 30);
        addCategoryToDBButton.setSize(250, 30);
        addCategoryToDBButton.setLocation((addCategoryFrame.getWidth() - addCategoryToDBButton.getWidth())/2, addCategoryLabel.getY()+60);

        addCategoryFrame.setVisible(true);

        // Action listener.
        addCategoryToDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Check is category exist.
                    viewModel.addNewCategoryIfExists(new Category(addCategoryTextField.getText()));
                } catch (CostManagerException ex) {
                    applicationPageGUI.showErrorMessage(new Message(ex.getMessage()));
                }
                // dispose frame.
                addCategoryFrame.dispose();
            }
        });
    }
}
