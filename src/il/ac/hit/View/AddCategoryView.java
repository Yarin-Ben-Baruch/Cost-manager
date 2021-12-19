package il.ac.hit.View;

import il.ac.hit.Model.Category;
import il.ac.hit.ViewModel.IViewModel;

import javax.swing.*;
import javax.swing.border.Border;
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

    /**
     * Ctor that contain the init and start CategoryView
     * @param m_ViewModel
     */
    public AddCategoryView(IViewModel m_ViewModel) {
        this.m_ViewModel = m_ViewModel;
        addCategoryInit();
        addCategoryStart();
    }

    private void addCategoryInit() {
        // Creating the AddCategory Action.
        m_AddCategoryFrame = new JFrame();
        m_AddCategoryLabel = new JLabel("Category name:");
        m_AddCategoryTextField = new JTextField(20);
        m_AddCategoryToDBButton = new JButton("Add category to the list");
    }

    private void addCategoryStart() {
        // Creating the Add Category Panel.
        m_AddCategoryFrame.add(m_AddCategoryLabel,BorderLayout.LINE_START);
        m_AddCategoryFrame.add(m_AddCategoryTextField,BorderLayout.CENTER);
        m_AddCategoryFrame.add(m_AddCategoryToDBButton,BorderLayout.PAGE_END);

        m_AddCategoryFrame.setLayout(null);
        m_AddCategoryFrame.setTitle("Add Category");
        m_AddCategoryFrame.setSize(400,150);
        m_AddCategoryLabel.setBounds(65, 20, 100, 30);
        m_AddCategoryTextField.setBounds(m_AddCategoryLabel.getX()+m_AddCategoryLabel.getWidth()+10, m_AddCategoryLabel.getY(), 160, 30);
        m_AddCategoryToDBButton.setSize(250, 30);
        m_AddCategoryToDBButton.setLocation((m_AddCategoryFrame.getWidth() - m_AddCategoryToDBButton.getWidth())/2, m_AddCategoryLabel.getY()+60);

        m_AddCategoryFrame.setVisible(true);

        m_AddCategoryToDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_ViewModel.addNewCategoryIfExists(new Category(m_AddCategoryTextField.getText()));
                m_AddCategoryFrame.dispose();
            }
        });
    }

}
