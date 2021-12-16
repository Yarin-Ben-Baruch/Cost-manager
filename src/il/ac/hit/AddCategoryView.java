package il.ac.hit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCategoryView {

    // Add Category Button, TextField, Label and Submit Action.
    private JFrame m_AddCategoryFrame;
    private JTextField m_AddCategoryTextField;
    private JLabel m_AddCategoryLabel;
    private JButton m_AddCategoryToDBButton;
    IViewModel m_ViewModel;

    public AddCategoryView(IViewModel m_ViewModel) {
        this.m_ViewModel = m_ViewModel;
        addCategoryInit();
        addCategoryStart();
    }

    private void addCategoryInit() {
        // Creating the AddCategory Action.
        m_AddCategoryFrame = new JFrame();
        m_AddCategoryLabel = new JLabel("Category name:");
        m_AddCategoryTextField = new JTextField(40);
        m_AddCategoryToDBButton = new JButton("Add category to the list");
    }

    private void addCategoryStart() {
        // Creating the Add Category Panel.
        m_AddCategoryFrame.setLayout(new GridLayout(2,2));
        m_AddCategoryFrame.add(m_AddCategoryLabel);
        m_AddCategoryFrame.add(m_AddCategoryTextField);
        m_AddCategoryFrame.add(m_AddCategoryToDBButton);

        m_AddCategoryFrame.setSize(1000,700);
        m_AddCategoryFrame.setVisible(true);

        m_AddCategoryToDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_ViewModel.addNewCategoryIfExists(new Category(m_AddCategoryTextField.getText()));
            }
        });
    }

}
