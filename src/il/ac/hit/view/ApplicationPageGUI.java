package il.ac.hit.view;

import il.ac.hit.model.Category;
import il.ac.hit.model.Item;
import il.ac.hit.model.Message;
import il.ac.hit.viewmodel.IViewModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;

/**
 * A class representing the app.
 * The department manages all the sub-buttons that are inside it.
 * Each button in the class represents another object that is opened when you click on it.
 */

public class ApplicationPageGUI {
    private IViewModel viewModel;
    private final ViewsManager viewsManager;
    // frame panel and show buttons.
    private JFrame mainFrame;
    private JPanel buttonsPanel;
    private JButton showItemsButton, showCategoriesButton;

    // Show Report Button.
    private JButton showReportButton;
    // Update Item Button.
    private JButton updateItemButton;
    // Remove Item  Button.
    private JButton removeItemButton;
    // Add Category Button.
    private JButton addCategory;
    // Add Item Button.
    private JButton addItemButton;

    // Cost table.
    private JTable costItemsTable;
    private JScrollPane costScrollPanel;
    private DefaultTableModel costTableModel;

    // Category table.
    private JScrollPane categoryScrollPanel;
    private DefaultTableModel categoryTableModel;
    private LinkedList<Category> categoriesList;

    // Creating menu.
    private MenuBar menuBar;
    private Menu menu;
    private String m_Username;


    /**
     * Ctor of application page GUI.
     * @param vm An object that holds the link to the viewModel class
     * @param viewsManager An object of the person who manages the screens
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
        showItemsButton = new JButton("Show costs");
        showCategoriesButton = new JButton("Show Categories");

        // Creating the RemoveItem Button.
        removeItemButton = new JButton("Remove cost");

        // Creating the AddCategory Button.
        addCategory = new JButton("Add category");

        // Creating the UpdateItem Button
        updateItemButton = new JButton("Update cost");

        // Creating the AddItem Button.
        addItemButton = new JButton("Add cost");

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

        // this will center the frame
        mainFrame.setLocationRelativeTo(null);

        mainFrame.setContentPane(new JLabel(new ImageIcon("src/images/Application background.jpg")));
        // Method that start add the button to the frame.
        creatingButtonsStart();
        // Method that build the cost table.
        buildCostColumnsName();
        // Method that build the category table.
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
     * @param items  A list of items that list all the column types in the expense table
     */
    public void showItems(Collection<Item> items) {

        // converting the list of items to linkedList to get to the 'i' item.
        LinkedList<Item> itemsList = (LinkedList<Item>) items;
        // get the size of the table.
        int sizeOfOldTable = costTableModel.getRowCount();

        // removing the old rows from the table.
        for(int i = 0; i < sizeOfOldTable; i++) {
            costTableModel.removeRow(0);
        }

        // adding the new items to the cost table.
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
     * @param categories A list of items that list all the types of categories there are
     */
    public void showCategories(Collection<Category> categories) {
        // converting the list of categories to linkedList to get to the 'i' category.
        categoriesList = (LinkedList<Category>) categories;
        // get the size of the table.
        int sizeOfOldTable = categoryTableModel.getRowCount();

        // removing the old rows from the table.
        for(int i = 0; i < sizeOfOldTable; i++) {
            categoryTableModel.removeRow(0);
        }

        // adding the new categories to the category table.
        for (int i = 0; i < categories.size(); i++) {
            Vector<String> addRowToTable = new Vector<>();
            addRowToTable.add(String.valueOf(i + 1));
            addRowToTable.add(String.valueOf(categoriesList.get(i).getCategoryName()));

            categoryTableModel.addRow(addRowToTable);
        }
    }

    /**
     * A method that set the IViewModel that send to her from the main program.
     * @param vm An object that holds the link to the viewModel class
     */
    public void setIViewModel(IViewModel vm) {
        viewModel = vm;
    }

    /**
     * A method that get message from the ViewModel and put it in the TextView.
     * Let the user know what action was preformed.
     * @param message A message-type object that conveys a message to the user
     */
    public void showErrorMessage(Message message) {
        JOptionPane.showMessageDialog(mainFrame, message.getText(),"Error",JOptionPane.ERROR_MESSAGE);
    }

    /**
     * A method that get message from the ViewModel and put it in the TextView.
     * Let the user know what action was preformed.
     * @param message A message-type object that conveys a message to the user
     */
    public void showSuccessMessage(Message message) {
        JOptionPane.showMessageDialog(mainFrame, message.getText(),"Success",JOptionPane.INFORMATION_MESSAGE);
    }


    public void setUsername(String userName) {
        this.m_Username = userName;
    }

    // Building Vector that contain the names of the columns in table.
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

    // Building Vector that contain the names of the columns in table.
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

        //costItemsTable.setBounds(30, 40, 200, 300);
        mainFrame.add(costScrollPanel, BorderLayout.CENTER);

        // להקטין את הטבלה
        mainFrame.add(categoryScrollPanel, BorderLayout.CENTER);
        //categoryScrollPanel.setSize( 50, 200);
    }

    private void buttonActionListeners() {
        showItemsButton.addActionListener(e -> viewModel.getItems(m_Username));

        showCategoriesButton.addActionListener(e -> viewModel.getAllCategories());

        showReportButton.addActionListener(e -> new DetailedReportView(viewModel,m_Username));

        updateItemButton.addActionListener(e -> new UpdateItemView(viewModel, m_Username));

        removeItemButton.addActionListener(e -> new RemoveItemView(viewModel, m_Username));

        addCategory.addActionListener(e -> new AddCategoryView(viewModel));

        addItemButton.addActionListener(e -> new AddItemView(viewModel, m_Username, costTableModel.getRowCount(), categoriesList));

        menu.addActionListener(e -> {
            mainFrame.dispose();
            viewsManager.openLogin();
        });
    }
}
