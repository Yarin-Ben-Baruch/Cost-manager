package il.ac.hit.view;

import il.ac.hit.model.Category;
import il.ac.hit.viewmodel.IViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCategoryView {

    // Add Category Button, TextField, Label and Submit Action.
    private JFrame addCategoryFrame;
    private JTextField addCategoryTextField;
    private JLabel addCategoryLabel;
    private JButton addCategoryToDBButton;
    private final IViewModel viewModel;

    /**
     * Ctor that contain the init and start CategoryView
     * @param m_ViewModel An object that holds the link to the viewModel class
     */
    public AddCategoryView(IViewModel m_ViewModel) {
        this.viewModel = m_ViewModel;
        addCategoryInit();
        addCategoryStart();
    }

    private void addCategoryInit() {
        // Creating the AddCategory Action.
        addCategoryFrame = new JFrame();
        addCategoryLabel = new JLabel("Category name:");
        addCategoryTextField = new JTextField(20);
        addCategoryToDBButton = new JButton("Add category to the list");
    }

    private void addCategoryStart() {
        // Creating the Add Category Panel.
        addCategoryFrame.add(addCategoryLabel,BorderLayout.LINE_START);
        addCategoryFrame.add(addCategoryTextField,BorderLayout.CENTER);
        addCategoryFrame.add(addCategoryToDBButton,BorderLayout.PAGE_END);

        addCategoryFrame.setLayout(new BorderLayout());
        addCategoryFrame.setTitle("Add Category");
        addCategoryFrame.setSize(400,150);
        addCategoryFrame.setResizable(false);

        addCategoryLabel.setBounds(65, 20, 100, 30);
        addCategoryTextField.setBounds(addCategoryLabel.getX()+ addCategoryLabel.getWidth()+10, addCategoryLabel.getY(), 160, 30);
        addCategoryToDBButton.setSize(250, 30);
        addCategoryToDBButton.setLocation((addCategoryFrame.getWidth() - addCategoryToDBButton.getWidth())/2, addCategoryLabel.getY()+60);

        addCategoryFrame.setVisible(true);

        addCategoryToDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.addNewCategoryIfExists(new Category(addCategoryTextField.getText()));
                addCategoryFrame.dispose();
            }
        });
    }
}
