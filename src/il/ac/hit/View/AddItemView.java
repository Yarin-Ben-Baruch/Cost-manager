package il.ac.hit.View;

import il.ac.hit.Model.Category;
import il.ac.hit.ViewModel.IViewModel;
import il.ac.hit.Model.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemView {

    private String[] m_Days;
    private String[] m_Months;
    private String[] m_Years;
    private String[] m_Currencies;

    // Add Item Button, TextField, Label and Submit Action.
    private JFrame m_AddItemFrame;
    private JTextField m_AddItemNameTextField, m_AddItemDescribingTextField;
    private JTextField m_AddItemCategoryTextField, m_AddItemSumTextField;
    private JComboBox m_AddItemDayComboBox, m_AddItemMonthComboBox,m_AddItemYearComboBox;
    private JComboBox m_AddItemCurrencyComboBox;
    private JButton m_AddItemToDBButton;
    private JLabel m_AddItemNameLabel, m_AddItemDescribingLabel, m_AddItemCurrencyLabel;
    private JLabel m_AddItemCategoryLabel, m_AddItemSumLabel, m_AddItemDateLabel;
    private IViewModel m_ViewModel;
    private String m_Username;
    private int m_CurrentTableSize;

    /**
     * Ctor that contain the init and start CategoryView
     * @param i_ViewModel
     * @param i_Username
     * @param i_CurrentTableSize
     */
    public AddItemView(IViewModel i_ViewModel, String i_Username, int i_CurrentTableSize) {
        this.m_ViewModel = i_ViewModel;
        this.m_Username = i_Username;
        this.m_CurrentTableSize = i_CurrentTableSize;
        addItemInit();
        addItemStart();
    }

    private void addItemInit() {
        // Creating the detailed report Action.
        m_Days = new String[31];
        for (int i =0 ; i<31 ; i++) {
            m_Days[i] = String.valueOf(i+1);
        }
        m_Months = new String[12];
        for (int i =0 ; i<12 ; i++) {
            m_Months[i] = String.valueOf(i+1);
        }
        m_Years = new String[5];
        for (int i =0 ; i<5 ; i++) {
            m_Years[i] = String.valueOf(i+2021);
        }
        m_Currencies = new String[]{"ILS", "USD", "EUR", "GBP", "TRY"};

        // Creating the AddItem Action.
        m_AddItemFrame = new JFrame();
        m_AddItemNameLabel = new JLabel("Name:");
        m_AddItemNameTextField = new JTextField();
        m_AddItemDescribingLabel = new JLabel("Descrption:");
        m_AddItemDescribingTextField = new JTextField();
        m_AddItemCurrencyLabel = new JLabel("Currency:");
        m_AddItemCurrencyComboBox = new JComboBox(m_Currencies);
        m_AddItemCategoryLabel = new JLabel("Category:");
        m_AddItemCategoryTextField = new JTextField();
        m_AddItemSumLabel = new JLabel("Sum");
        m_AddItemSumTextField = new JTextField();
        m_AddItemDateLabel = new JLabel("Date:");
        m_AddItemDayComboBox = new JComboBox(m_Days);
        m_AddItemMonthComboBox = new JComboBox(m_Months);
        m_AddItemYearComboBox = new JComboBox(m_Years);
        m_AddItemToDBButton = new JButton("Add cost to the list");
    }

    private void addItemStart() {
        // Creating the Add Item Panel.
        m_AddItemFrame.setLayout(null);
        m_AddItemFrame.add(m_AddItemNameLabel);
        m_AddItemFrame.add(m_AddItemNameTextField);
        m_AddItemFrame.add(m_AddItemCurrencyLabel);
        m_AddItemFrame.add(m_AddItemCurrencyComboBox);
        m_AddItemFrame.add(m_AddItemCategoryLabel);
        m_AddItemFrame.add(m_AddItemCategoryTextField);
        m_AddItemFrame.add(m_AddItemSumLabel);
        m_AddItemFrame.add(m_AddItemSumTextField);
        m_AddItemFrame.add(m_AddItemDateLabel);
        m_AddItemFrame.add(m_AddItemDayComboBox);
        m_AddItemFrame.add(m_AddItemMonthComboBox);
        m_AddItemFrame.add(m_AddItemYearComboBox);
        m_AddItemFrame.add(m_AddItemDescribingLabel);
        m_AddItemFrame.add(m_AddItemDescribingTextField);
        m_AddItemFrame.add(m_AddItemToDBButton);

        m_AddItemFrame.setTitle("Add Cost");
        setComponentsLocationAndSize();
        m_AddItemFrame.setVisible(true);

        m_AddItemToDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //m_ViewModel.getAllUsers();

                Item item = new Item(++m_CurrentTableSize,
                        m_AddItemNameTextField.getText(),
                        m_AddItemDescribingTextField.getText(),
                        (String)m_AddItemCurrencyComboBox.getSelectedItem(),
                        new Category(m_AddItemCategoryTextField.getText()),
                        m_AddItemSumTextField.getText(),
                        java.sql.Date.valueOf(m_AddItemYearComboBox.getSelectedItem()+"-" +
                                m_AddItemMonthComboBox.getSelectedItem() + "-" +
                                m_AddItemDayComboBox.getSelectedItem()),
                        m_Username);

                m_ViewModel.addItem(item);
                m_AddItemFrame.dispose();
            }
        });
    }

    private void setComponentsLocationAndSize() {
        m_AddItemFrame.setSize(480,475);

        m_AddItemNameLabel.setBounds(50, 30, 80, 30);
        m_AddItemCurrencyLabel.setBounds(m_AddItemNameLabel.getX(),m_AddItemNameLabel.getY()+50, 80, 30);
        m_AddItemCategoryLabel.setBounds(m_AddItemNameLabel.getX(),m_AddItemCurrencyLabel.getY()+50, 80, 30);
        m_AddItemSumLabel.setBounds(m_AddItemNameLabel.getX(),m_AddItemCategoryLabel.getY() + 50, 80, 30);
        m_AddItemDateLabel.setBounds(m_AddItemNameLabel.getX(),m_AddItemSumLabel.getY()+50, 80, 30);
        m_AddItemDescribingLabel.setBounds(m_AddItemNameLabel.getX(),m_AddItemDateLabel.getY() + 50, 80, 30);

        m_AddItemNameTextField.setBounds(m_AddItemNameLabel.getWidth() + m_AddItemNameLabel.getX(), m_AddItemNameLabel.getY(), 150, 30);
        m_AddItemCurrencyComboBox.setBounds(m_AddItemNameTextField.getX(), m_AddItemCurrencyLabel.getY(), 150, 30);
        m_AddItemCategoryTextField.setBounds(m_AddItemNameTextField.getX(), m_AddItemCategoryLabel.getY(), 150, 30);

        m_AddItemSumTextField.setBounds(m_AddItemNameTextField.getX(), m_AddItemSumLabel.getY(), 100, 30);

        m_AddItemDayComboBox.setBounds(m_AddItemSumTextField.getX()+m_AddItemDayComboBox.getWidth(), m_AddItemDateLabel.getY(), 100, 30);
        m_AddItemMonthComboBox.setBounds(m_AddItemDayComboBox.getX()+m_AddItemDayComboBox.getWidth(), m_AddItemDateLabel.getY(), 100, 30);
        m_AddItemYearComboBox.setBounds(m_AddItemMonthComboBox.getX()+m_AddItemMonthComboBox.getWidth(), m_AddItemDateLabel.getY(), 100, 30);

        m_AddItemDescribingTextField.setBounds(m_AddItemNameTextField.getX(), m_AddItemDescribingLabel.getY(), 150, 30);

        m_AddItemToDBButton.setSize(150,30);
        m_AddItemToDBButton.setLocation((m_AddItemFrame.getWidth()-m_AddItemToDBButton.getWidth())/2 , m_AddItemDescribingTextField.getY() + m_AddItemDescribingTextField.getHeight()+30);

    }
}
