package il.ac.hit.view;
import il.ac.hit.model.Category;
import il.ac.hit.model.Currency;
import il.ac.hit.model.Item;
import il.ac.hit.model.Message;
import il.ac.hit.viewmodel.IViewModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
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
    private JPanel tablesPanel;
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
    // Show currencies Button.
    private JButton showCurrencies;

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
        mainFrame = new JFrame("Cost Manager");

        // Create JPanels.
        buttonsPanel = new JPanel();
        tablesPanel = new JPanel();
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

        // Creating the showCurrencies Button.
        showCurrencies = new JButton("Show currencies");


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
        mainFrame.setContentPane(new JLabel(new ImageIcon("src/images/Application background.jpg")));
        // Setting the MainFrame Layout.
        mainFrame.setLayout(new BorderLayout());
        // this will center the frame
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Method that build the cost table.
        buildCostColumnsName();
        // Method that build the category table.
        buildCategoryColumnsName();
        // Method that adding the buttons to the frame.
        addingComponentsStart();

        // Setting the menu bar.
        menu.add("Logout");
        menuBar.add(menu);
        mainFrame.setMenuBar(menuBar);

        // Setting the action listeners for the buttons
        buttonActionListeners();

        mainFrame.setSize(1050,700);
        mainFrame.setVisible(true);

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
        for(Item item : itemsList){
            Vector<String> addRowToTable = new Vector<>();
            // Build row in the table cost.
            addRowToTable.add(String.valueOf(item.getCostNumber()));
            addRowToTable.add(item.getName());
            addRowToTable.add(item.getDescribing());
            addRowToTable.add(item.getCurrency());
            addRowToTable.add(item.getCategory().getCategoryName());
            addRowToTable.add(item.getSum());
            addRowToTable.add(item.getDate().toString());
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
        int index = 1;
        for(Category category : categoriesList){
            Vector<String> addRowToTable = new Vector<>();
            // Build row in the category table.
            addRowToTable.add(String.valueOf(index));
            addRowToTable.add(String.valueOf(category.getCategoryName()));

            categoryTableModel.addRow(addRowToTable);
            index++;
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

    /**
     * A method that get list from the ViewModel and put it in the TextView.
     * This method parse the json file and translate it to text.
     * Let the user know what is the currencies change rates.
     * @param currencies list of the currencies.
     */
    public void showCurrencies(List<Currency> currencies) {
        // The text from the json will be here.
        StringBuilder currencyMessage = new StringBuilder();

        // Translate the json to text.
        for(Currency currency : currencies) {
            currencyMessage.append(currency.getSymbol()).append(" : ").append(currency.getRate()).append("\n");
        }

        // Setting the color of the pop window.
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Color.white);

        JOptionPane.showMessageDialog(mainFrame,currencyMessage.toString(),"Currencies", JOptionPane.UNDEFINED_CONDITION);
    }

    public void setUsername(String userName) {
        this.m_Username = userName;
    }

    // Building Vector that contain the names of the columns in table.
    private Vector<String> buildCostColumnsName(){

        Vector<String> addColToTable = new Vector<>();

        // names of column table.
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

    // Add components.
    private void addingComponentsStart() {
        // setting the layout to be GridLayout.
        buttonsPanel.setLayout(new GridLayout(1,8));
        // set the background of the panel transparent.
        buttonsPanel.setOpaque(false);
        // setting the layout to be GridBagLayout.
        tablesPanel.setLayout(new GridBagLayout());
        // set the background of the panel transparent.
        tablesPanel.setOpaque(false);

        // adding the buttons to the buttons panel.
        buttonsPanel.add(showItemsButton);
        buttonsPanel.add(showCategoriesButton);
        buttonsPanel.add(showReportButton);
        buttonsPanel.add(addItemButton);
        buttonsPanel.add(addCategory);
        buttonsPanel.add(removeItemButton);
        buttonsPanel.add(updateItemButton);
        buttonsPanel.add(showCurrencies);


        // setting the table size and padding from the window.
        GridBagConstraints setCompSizeAndPadding = new GridBagConstraints();
        // setting the table to be horizontal.
        setCompSizeAndPadding.fill = GridBagConstraints.HORIZONTAL;
        // setting that the cost table to be 80% of the size.
        setCompSizeAndPadding.weightx = 0.8;
        // setting the height of the table when the window shrinks.
        setCompSizeAndPadding.ipady = 80;
        // setting padding from the top left bottom and right for 10.
        setCompSizeAndPadding.insets = new Insets(10,10,10,10);
        // adding the cost table to the panel with the settings.
        tablesPanel.add(costScrollPanel,setCompSizeAndPadding);
        // changing the category table to be 20% of the size.
        setCompSizeAndPadding.weightx = 0.2;
        // adding the category table to the panel with the settings.
        tablesPanel.add(categoryScrollPanel,setCompSizeAndPadding);
        // adding the tables panel to the frame in the south.
        mainFrame.add(tablesPanel,BorderLayout.SOUTH);
        // adding the table panel to the frame in the north.
        mainFrame.add(buttonsPanel,BorderLayout.NORTH);
    }

    // Action listeners.
    private void buttonActionListeners() {
        // Action lister for the get items.
        showItemsButton.addActionListener(e -> viewModel.getItems(m_Username));

        // Action lister for the get categories.
        showCategoriesButton.addActionListener(e -> viewModel.getAllCategories());

        // Action lister for the get detailed report.
        showReportButton.addActionListener(e -> new DetailedReportView(viewModel,m_Username));

        // Action lister for the update item.
        updateItemButton.addActionListener(e -> new UpdateItemView(viewModel, m_Username));

        // Action lister for the remove item.
        removeItemButton.addActionListener(e -> new RemoveItemView(viewModel, m_Username));

        // Action lister for the add category.
        addCategory.addActionListener(e -> new AddCategoryView(viewModel, this));

        // Action lister for the add item.
        addItemButton.addActionListener(e -> new AddItemView(viewModel, m_Username, costTableModel.getRowCount(),
                categoriesList,this));

        // Action lister for the get current currencies.
        showCurrencies.addActionListener(e -> viewModel.getCurrencies());

        menu.addActionListener(e -> {
            mainFrame.dispose();
            viewsManager.openLogin();
        });
    }

}
