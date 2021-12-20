package il.ac.hit.view;

import il.ac.hit.*;
import il.ac.hit.model.Category;
import il.ac.hit.model.Item;
import il.ac.hit.viewmodel.IViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;

public class ApplicationPageGUI {
    private IViewModel viewModel;
    private final ViewsManager viewsManager;

    private JFrame mainFrame;
    private JPanel buttonsPanel;
    private JButton showItemsButton, showCategoriesButton;

    // Show Report Button
    private JButton showReportButton;
    // Update Item Button.
    private JButton updateItemButton;
    // Remove Item  Button.
    private JButton removeItemButton;
    // Add Category Button.
    private JButton addCategory;
    // Add Item Button.
    private JButton addItemButton;

    // Cost table
    private JTable costItemsTable;
    private JScrollPane costScrollPanel;
    private DefaultTableModel costTableModel;

    // Category table
    private JScrollPane categoryScrollPanel;
    private DefaultTableModel categoryTableModel;

    // Creating menu
    private MenuBar menuBar;
    private Menu menu;
    private String m_Username;

    /**
     * Ctor of application page GUI.
     * @param vm
     * @param viewsManager
     */
    public ApplicationPageGUI(IViewModel vm, ViewsManager viewsManager) {
        viewModel = vm;
        this.viewsManager = viewsManager;
    }

    /**
     * This method Initializing the application page.
     */
    public void initApplication() {

        // Create the Main JFrame
        mainFrame = new JFrame();

        // Create JPanels.
        buttonsPanel = new JPanel();

        // Create all the show Buttons.
        showItemsButton = new JButton("Show Items");
        showCategoriesButton = new JButton("Show Categories");

        // Creating the RemoveItem Button.
        removeItemButton = new JButton("Remove item");

        // Creating the AddCategory Button.
        addCategory = new JButton("Add category");

        // Creating the UpdateItem Button
        updateItemButton = new JButton("Update item");

        // Creating the AddItem Button.
        addItemButton = new JButton("Add item");

        // Creating the ShowReport Button.
        showReportButton = new JButton("Show detail report");

        // Creating the Cost table.
        costTableModel = new DefaultTableModel(buildCostColumnsName(),0);
        costItemsTable = new JTable(costTableModel);
        costScrollPanel = new JScrollPane(costItemsTable);

        // Creating Category table.

        categoryTableModel = new DefaultTableModel(buildCategoryColumnsName(),0);
        JTable categoryTable = new JTable(categoryTableModel);
        categoryScrollPanel = new JScrollPane(categoryTable);

        //Creating Menu bar.
        menuBar = new MenuBar();
        menu = new Menu("Options");
    }

    /**
     * This method Starting the application page.
     */
    public void startApplication() {

        creatingButtonsStart();
        buildCostColumnsName();
        buildCategoryColumnsName();

        // Setting the menu bar.
        menu.add("Logout");
        menuBar.add(menu);
        mainFrame.setMenuBar(menuBar);

        // Setting the MainFrame Layout.
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000,700);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

        // Setting the action listeners for the buttons
        buttonActionListeners();

        // Do click to show the costs and categories in the table
        showItemsButton.doClick();
        showCategoriesButton.doClick();
    }

    /**
     * A method that gets collection of items and print the items into the Table.
     * @param items
     */
    public void showItems(Collection<Item> items) {

        LinkedList<Item> itemsList = (LinkedList<Item>) items;
        int sizeOfOldTable = costTableModel.getRowCount();

        for(int i = 0; i < sizeOfOldTable; i++) {
            costTableModel.removeRow(0);
        }

        for (int i = 0; i < items.size(); i++) {
            Vector<String> addRowToTable = new Vector<>();

            addRowToTable.add(String.valueOf(itemsList.get(i).getCostNumber()));
            addRowToTable.add(itemsList.get(i).getName());
            addRowToTable.add(itemsList.get(i).getDescribing());
            addRowToTable.add(itemsList.get(i).getCurrency());
            addRowToTable.add(itemsList.get(i).getCategory().getCategoryName());
            addRowToTable.add(itemsList.get(i).getSum());
            addRowToTable.add(itemsList.get(i).getDate().toString());
            costTableModel.addRow(addRowToTable);
        }
    }

    /**
     * A method that gets collection of categories and print the items into the TextArea.
     *
     * @param categories
     */
    public void showCategories(Collection<Category> categories) {
        LinkedList<Category> categoriesList = (LinkedList<Category>) categories;
        int sizeOfOldTable = categoryTableModel.getRowCount();

        for(int i = 0; i < sizeOfOldTable; i++) {
            categoryTableModel.removeRow(0);
        }

        for (int i = 0; i < categories.size(); i++) {
            Vector<String> addRowToTable = new Vector<>();
            addRowToTable.add(String.valueOf(i + 1));
            addRowToTable.add(String.valueOf(categoriesList.get(i).getCategoryName()));

            categoryTableModel.addRow(addRowToTable);
        }
    }

    /**
     * A method that set the IViewModel that send to her from the main program.
     * @param vm
     */
    public void setIViewModel(IViewModel vm) {
        viewModel = vm;
    }

    /**
     * A method that get message from the ViewModel and put it in the TextView.
     * Let the user know what action was preformed.
     * @param message
     */
    public void showMessage(Message message) {
        JOptionPane.showMessageDialog(mainFrame, message.getText());
    }

    public void setUsername(String userName) {
        this.m_Username = userName;
    }

    private Vector<String> buildCostColumnsName(){

        Vector<String> addColToTable = new Vector<>();

        addColToTable.add("Cost number");
        addColToTable.add("Name");
        addColToTable.add("Description");
        addColToTable.add("Currency");
        addColToTable.add("Category");
        addColToTable.add("Sum");
        addColToTable.add("Date");

        return addColToTable;
    }

    private Vector<String> buildCategoryColumnsName(){
        Vector<String> addColToTable = new Vector<>();

        addColToTable.add("Number");
        addColToTable.add("Category");

        return addColToTable;
    }

    private void creatingButtonsStart() {
        // Creating the Button Panel.
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(showItemsButton);
        buttonsPanel.add(showCategoriesButton);
        buttonsPanel.add(showReportButton);
        buttonsPanel.add(addItemButton);
        buttonsPanel.add(addCategory);
        buttonsPanel.add(removeItemButton);
        buttonsPanel.add(updateItemButton);
        mainFrame.add(buttonsPanel,BorderLayout.NORTH);

        costItemsTable.setBounds(30, 40, 200, 300);
        mainFrame.add(costScrollPanel, BorderLayout.CENTER);

        // להקטין את הטבלה
        mainFrame.add(categoryScrollPanel, BorderLayout.CENTER);
        categoryScrollPanel.setSize( 50, 200);
    }

    private void buttonActionListeners() {
        showItemsButton.addActionListener(e -> viewModel.getItems(m_Username));

        showCategoriesButton.addActionListener(e -> viewModel.getAllCategories());

        showReportButton.addActionListener(e -> new DetailedReportView(viewModel,m_Username));

        updateItemButton.addActionListener(e -> new UpdateItemView(viewModel, m_Username));

        removeItemButton.addActionListener(e -> new RemoveItemView(viewModel, m_Username));

        addCategory.addActionListener(e -> new AddCategoryView(viewModel));

        addItemButton.addActionListener(e -> new AddItemView(viewModel, m_Username, costTableModel.getRowCount()));

        menu.addActionListener(e -> {
            mainFrame.dispose();
            viewsManager.openLogin();
        });
    }
}
