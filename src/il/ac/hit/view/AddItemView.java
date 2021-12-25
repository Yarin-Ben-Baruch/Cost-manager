package il.ac.hit.view;

import il.ac.hit.model.Category;
import il.ac.hit.viewmodel.IViewModel;
import il.ac.hit.model.Item;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * A button inside the app. The button represents adding a new record to the expense table.
 * When you press the button from the app, the object opens.
 */
public class AddItemView {

    // Add Item Button, TextField, Label and Submit Action.
    private JFrame addItemFrame;
    private JTextField addItemNameTextField, addItemDescribingTextField;
    private JTextField  addItemSumTextField;
    private JComboBox<String> addItemDayComboBox, addItemMonthComboBox;
    private JComboBox<String> addItemYearComboBox, addItemCurrencyComboBox;
    private JComboBox<String> addItemCategoryComboBox;
    private JButton addItemToDBButton;
    private JLabel addItemNameLabel, addItemDescribingLabel, addItemCurrencyLabel;
    private JLabel addItemCategoryLabel, addItemSumLabel, addItemDateLabel;
    private final IViewModel viewModel;
    private final String userName;
    private int currentTableSize;
    private LinkedList<Category> categoriesList;


    /**
     * Ctor that contain the init and start CategoryView.
     * @param viewModel An object that holds the link to the viewModel class.
     * @param userName Username of the person who is connected to the app.
     * @param currentTableSize The current size of the expense table.
     */
    public AddItemView(IViewModel viewModel, String userName, int currentTableSize, LinkedList<Category> categoriesList) {
        this.viewModel = viewModel;
        this.userName = userName;
        this.currentTableSize = currentTableSize;
        this.categoriesList = categoriesList;
        addItemInit();
        addItemStart();
    }

    // init the frame
    private void addItemInit() {
        String[] days;
        String[] months;
        String[] years;
        String[] currencies;
        String[] categories;

        // Creating the detailed report Action.
        days = new String[31];
        for (int i = 0 ; i < 31 ; i++) {
            days[i] = String.valueOf(i+1);
        }

        months = new String[12];
        for (int i = 0 ; i < 12 ; i++) {
            months[i] = String.valueOf(i+1);
        }

        years = new String[5];
        for (int i = 0 ; i < 5 ; i++) {
            years[i] = String.valueOf(i+2021);
        }

        categories = new String[categoriesList.size()];
        for (int i = 0; i< categoriesList.size(); i++){
            categories[i] = categoriesList.get(i).getCategoryName();
        }



        // currencies we support.
        currencies = new String[]{"ILS", "USD", "EUR", "GBP", "TRY"};

        // Creating the AddItem Action.
        addItemFrame = new JFrame();
        addItemNameLabel = new JLabel("Name:");
        addItemNameTextField = new JTextField();
        addItemDescribingLabel = new JLabel("Description:");
        addItemDescribingTextField = new JTextField();
        addItemCurrencyLabel = new JLabel("Currency:");
        addItemCurrencyComboBox = new JComboBox<>(currencies);
        addItemCategoryLabel = new JLabel("Category:");
        addItemCategoryComboBox = new JComboBox<>(categories);
        //addItemCategoryTextField = new JTextField();
        addItemSumLabel = new JLabel("Sum");
        addItemSumTextField = new JTextField();
        addItemDateLabel = new JLabel("Date:");
        addItemDayComboBox = new JComboBox<>(days);
        addItemMonthComboBox = new JComboBox<>(months);
        addItemYearComboBox = new JComboBox<>(years);
        addItemToDBButton = new JButton("Add cost to the list");
    }

    // start the frame
    private void addItemStart() {

        // background image
        addItemFrame.setContentPane(new JLabel(new ImageIcon("src/images/General background.jpg")));

        // Creating the Add Item Panel.
        addItemFrame.add(addItemNameLabel);
        addItemFrame.add(addItemNameTextField);
        addItemFrame.add(addItemCurrencyLabel);
        addItemFrame.add(addItemCurrencyComboBox);
        addItemFrame.add(addItemCategoryLabel);
        addItemFrame.add(addItemCategoryComboBox);
        addItemFrame.add(addItemSumLabel);
        addItemFrame.add(addItemSumTextField);
        addItemFrame.add(addItemDateLabel);
        addItemFrame.add(addItemDayComboBox);
        addItemFrame.add(addItemMonthComboBox);
        addItemFrame.add(addItemYearComboBox);
        addItemFrame.add(addItemDescribingLabel);
        addItemFrame.add(addItemDescribingTextField);
        addItemFrame.add(addItemToDBButton);

        addItemFrame.setLayout(new BorderLayout());
        addItemFrame.setTitle("Add Cost");
        addItemFrame.setSize(480,475);
        addItemFrame.setResizable(false);

        setComponentsLocationAndSize();

        addItemFrame.setVisible(true);


        addItemToDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = new Item(++currentTableSize,
                        addItemNameTextField.getText(),
                        addItemDescribingTextField.getText(),
                        String.valueOf(addItemCurrencyComboBox.getSelectedItem()),
                        new Category(String.valueOf(addItemCategoryComboBox.getSelectedItem())),
                        addItemSumTextField.getText(),
                        java.sql.Date.valueOf(addItemYearComboBox.getSelectedItem()+"-" +
                                addItemMonthComboBox.getSelectedItem() + "-" +
                                addItemDayComboBox.getSelectedItem()),
                        userName);

                viewModel.addItem(item);
                addItemFrame.dispose();
            }
        });
    }

    // setting the size and location of the components in the frame.
    private void setComponentsLocationAndSize() {

        addItemNameLabel.setBounds(50, 30, 80, 30);
        addItemCurrencyLabel.setBounds(addItemNameLabel.getX(), addItemNameLabel.getY()+50, 80, 30);
        addItemCategoryLabel.setBounds(addItemNameLabel.getX(), addItemCurrencyLabel.getY()+50, 80, 30);
        addItemSumLabel.setBounds(addItemNameLabel.getX(), addItemCategoryLabel.getY() + 50, 80, 30);
        addItemDateLabel.setBounds(addItemNameLabel.getX(), addItemSumLabel.getY()+50, 80, 30);
        addItemDescribingLabel.setBounds(addItemNameLabel.getX(), addItemDateLabel.getY() + 50, 80, 30);

        addItemNameTextField.setBounds(addItemNameLabel.getWidth() + addItemNameLabel.getX(), addItemNameLabel.getY(), 150, 30);
        addItemCurrencyComboBox.setBounds(addItemNameTextField.getX(), addItemCurrencyLabel.getY(), 150, 30);
        addItemCategoryComboBox.setBounds(addItemNameTextField.getX(), addItemCategoryLabel.getY(), 150, 30);

        addItemSumTextField.setBounds(addItemNameTextField.getX(), addItemSumLabel.getY(), 100, 30);

        addItemDayComboBox.setBounds(addItemSumTextField.getX()+ addItemDayComboBox.getWidth(), addItemDateLabel.getY(), 100, 30);
        addItemMonthComboBox.setBounds(addItemDayComboBox.getX()+ addItemDayComboBox.getWidth(), addItemDateLabel.getY(), 100, 30);
        addItemYearComboBox.setBounds(addItemMonthComboBox.getX()+ addItemMonthComboBox.getWidth(), addItemDateLabel.getY(), 100, 30);

        addItemDescribingTextField.setBounds(addItemNameTextField.getX(), addItemDescribingLabel.getY(), 150, 30);

        addItemToDBButton.setSize(150,30);
        addItemToDBButton.setLocation((addItemFrame.getWidth()- addItemToDBButton.getWidth())/2 , addItemDescribingTextField.getY() + addItemDescribingTextField.getHeight()+30);

    }
}
