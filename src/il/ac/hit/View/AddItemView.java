package il.ac.hit.View;

import il.ac.hit.Model.Category;
import il.ac.hit.ViewModel.IViewModel;
import il.ac.hit.Model.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemView {

    private String[] days;
    private String[] months;

    // Add Item Button, TextField, Label and Submit Action.
    private JFrame m_AddItemFrame;
    private JTextField m_AddItemNameTextField, m_AddItemDescribingTextField, m_AddItemCurrencyTextField;
    private JTextField m_AddItemCategoryTextField, m_AddItemSumTextField;
    private JComboBox m_AddItemDayComboBox, m_AddItemMonthComboBox;
    private JButton m_AddItemToDBButton;
    private JLabel m_AddItemNameLabel, m_AddItemDescribingLabel, m_AddItemCurrencyLabel;
    private JLabel m_AddItemCategoryLabel, m_AddItemSumLabel,m_AddItemDayLabel, m_AddItemMonthLabel;
    IViewModel m_ViewModel;

    public AddItemView(IViewModel m_ViewModel) {
        this.m_ViewModel = m_ViewModel;
        addItemInit();
        addItemStart();
    }

    private void addItemInit() {
        // Creating the detailed report Action.
        days = new String[31];
        for (int i =0 ; i<31 ; i++) {
            days[i] = (i+1) +"";
        }
        months = new String[12];
        for (int i =0 ; i<12 ; i++) {
            months[i] = (i+1) +"";
        }

        // Creating the AddItem Action.
        m_AddItemFrame = new JFrame();
        m_AddItemNameLabel = new JLabel("Name:");
        m_AddItemNameTextField = new JTextField(10);
        m_AddItemDescribingLabel = new JLabel("Descrption:");
        m_AddItemDescribingTextField = new JTextField(30);
        m_AddItemCurrencyLabel = new JLabel("Currency:");
        m_AddItemCurrencyTextField = new JTextField(8);
        m_AddItemCategoryLabel = new JLabel("Category:");
        m_AddItemCategoryTextField = new JTextField(10);
        m_AddItemSumLabel = new JLabel("Sum");
        m_AddItemSumTextField = new JTextField(8);
        m_AddItemDayLabel = new JLabel("Day:");
        m_AddItemDayComboBox = new JComboBox(days);
        m_AddItemMonthLabel = new JLabel("Month");
        m_AddItemMonthComboBox = new JComboBox(months);
        m_AddItemToDBButton = new JButton("Add cost to the list");
    }


    private void addItemStart() {
        // Creating the Add Item Panel.
        m_AddItemFrame.setLayout(new GridLayout(5,2));
        m_AddItemFrame.add(m_AddItemNameLabel);
        m_AddItemFrame.add(m_AddItemNameTextField);
        m_AddItemFrame.add(m_AddItemCurrencyLabel);
        m_AddItemFrame.add(m_AddItemCurrencyTextField);
        m_AddItemFrame.add(m_AddItemCategoryLabel);
        m_AddItemFrame.add(m_AddItemCategoryTextField);
        m_AddItemFrame.add(m_AddItemSumLabel);
        m_AddItemFrame.add(m_AddItemSumTextField);
        m_AddItemFrame.add(m_AddItemDayLabel);
        m_AddItemFrame.add(m_AddItemDayComboBox);
        m_AddItemFrame.add(m_AddItemMonthLabel);
        m_AddItemFrame.add(m_AddItemMonthComboBox);
        m_AddItemFrame.add(m_AddItemDescribingLabel);
        m_AddItemFrame.add(m_AddItemDescribingTextField);
        m_AddItemFrame.add(m_AddItemToDBButton);

        m_AddItemFrame.setSize(1000,700);
        m_AddItemFrame.setVisible(true);

        //can't add from here Category
        m_AddItemToDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //למחוק את ה -8
                Item item = new Item(9,m_AddItemNameTextField.getText(),
                        m_AddItemDescribingTextField.getText(),
                        m_AddItemCurrencyTextField.getText(),
                        new Category(m_AddItemCategoryTextField.getText()),
                        m_AddItemSumTextField.getText(),
                        java.sql.Date.valueOf("2021-"+m_AddItemMonthComboBox.getSelectedItem()+"-"+m_AddItemDayComboBox.getSelectedItem()),
                        "matan");

                m_ViewModel.addItem(item);
                m_AddItemFrame.dispose();
            }
        });
    }





}
