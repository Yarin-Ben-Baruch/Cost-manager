package il.ac.hit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemView {

    private String[] days;
    private String[] months;

    // Add Item Button, TextField, Label and Submit Action.
    private JFrame m_AddItemPanel;
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
        m_AddItemPanel = new JFrame();
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
        m_AddItemPanel.setLayout(new GridLayout(5,2));
        m_AddItemPanel.add(m_AddItemNameLabel);
        m_AddItemPanel.add(m_AddItemNameTextField);
        m_AddItemPanel.add(m_AddItemCurrencyLabel);
        m_AddItemPanel.add(m_AddItemCurrencyTextField);
        m_AddItemPanel.add(m_AddItemCategoryLabel);
        m_AddItemPanel.add(m_AddItemCategoryTextField);
        m_AddItemPanel.add(m_AddItemSumLabel);
        m_AddItemPanel.add(m_AddItemSumTextField);
        m_AddItemPanel.add(m_AddItemDayLabel);
        m_AddItemPanel.add(m_AddItemDayComboBox);
        m_AddItemPanel.add(m_AddItemMonthLabel);
        m_AddItemPanel.add(m_AddItemMonthComboBox);
        m_AddItemPanel.add(m_AddItemDescribingLabel);
        m_AddItemPanel.add(m_AddItemDescribingTextField);
        m_AddItemPanel.add(m_AddItemToDBButton);


        m_AddItemToDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = new Item(8,m_AddItemNameTextField.getText(),
                        m_AddItemDescribingTextField.getText(),
                        m_AddItemCurrencyTextField.getText(),
                        new Category(m_AddItemCategoryTextField.getText()),
                        m_AddItemSumTextField.getText(),
                        java.sql.Date.valueOf("2021-"+m_AddItemMonthComboBox.getSelectedItem()+"-"+m_AddItemDayComboBox.getSelectedItem()),
                        "matan");

                m_ViewModel.addItem(item);

            }
        });
    }





}
