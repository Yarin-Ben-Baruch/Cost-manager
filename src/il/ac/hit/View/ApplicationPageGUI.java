package il.ac.hit.View;

import il.ac.hit.*;
import il.ac.hit.Model.Category;
import il.ac.hit.Model.Item;
import il.ac.hit.ViewModel.IViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;

public class ApplicationPageGUI {
    private IViewModel m_ViewModel;
    private boolean isCostsAppears;
    private boolean isCategoriesAppears;

    private JFrame m_MainFrame;
    private JPanel m_ButtonsPanel;
    private JButton m_ShowItemsButton, m_ShowCategoriesButton;

    // Show Report Button
    private JButton m_ShowReportButton;
    // Update Item Button.
    private JButton m_UpdateItemButton;
    // Remove Item  Button.
    private JButton m_RemoveItemButton;
    // Add Category Button.
    private JButton m_AddCategory;
    // Add Item Button.
    private JButton m_AddItemButton;
    // Cost table
    private JTable m_ItemsTable;
    private JScrollPane scrollPanel;
    DefaultTableModel m_TableModel;

    String m_Username;

    public ApplicationPageGUI(IViewModel i_Vm) {
        m_ViewModel = i_Vm;
        isCostsAppears = false;
        isCategoriesAppears = false;
    }

    public void setUsername(String m_Username) {
        this.m_Username = m_Username;
    }

    public void initApplication() {

        // Create the Main JFrame
        m_MainFrame = new JFrame();

        // Create JPanels.
        m_ButtonsPanel = new JPanel();

        // Create all the show JButtons.
        m_ShowItemsButton = new JButton("Show Items");
        m_ShowCategoriesButton = new JButton("Show Categories");

        // Creating the RemoveItem Button.
        m_RemoveItemButton = new JButton("Remove item");
        // Creating the AddCategory Button.
        m_AddCategory = new JButton("Add category");
        // Creating the UpdateItem Button
        m_UpdateItemButton = new JButton("Update item");
        // Creating the AddItem Button.
        m_AddItemButton = new JButton("Add item");
        // Creating the ShowReport Button.
        m_ShowReportButton = new JButton("Show detail report");


        m_TableModel = new DefaultTableModel(buildCostColumnsName(),0);
        m_ItemsTable = new JTable(m_TableModel);
        scrollPanel = new JScrollPane(m_ItemsTable);

    }

    public void startApplication() {

        creatingButtonsStart();
        buildCostColumnsName();

//        MenuBar menuBar = new MenuBar();
//        Menu menu = new Menu("User");
//        menu.add("Logout");
//        menuBar.add(menu);
//        m_MainFrame.setMenuBar(menuBar);


        // Setting the MainFrame Layout
        m_MainFrame.setLayout(new FlowLayout());
        m_MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_MainFrame.setSize(1000,700);
        m_MainFrame.setVisible(true);


        ButtonActionListeners();
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

    private void creatingButtonsStart() {
        // Creating the Button Panel.
        m_ButtonsPanel.setLayout(new FlowLayout());
        m_ButtonsPanel.add(m_ShowItemsButton);
        m_ButtonsPanel.add(m_ShowCategoriesButton);
        m_ButtonsPanel.add(m_ShowReportButton);
        m_ButtonsPanel.add(m_AddItemButton);
        m_ButtonsPanel.add(m_AddCategory);
        m_ButtonsPanel.add(m_RemoveItemButton);
        m_ButtonsPanel.add(m_UpdateItemButton);
        m_MainFrame.add(m_ButtonsPanel,BorderLayout.NORTH);

        m_ItemsTable.setBounds(30, 40, 200, 300);
        m_MainFrame.add(scrollPanel, BorderLayout.CENTER);

    }

    private void ButtonActionListeners() {
        m_ShowItemsButton.addActionListener(e -> m_ViewModel.getItems());

        m_ShowCategoriesButton.addActionListener(e -> m_ViewModel.getAllCategories());

        m_ShowReportButton.addActionListener(e -> new ReportView(m_ViewModel));

        m_UpdateItemButton.addActionListener(e -> new UpdateItemView(m_ViewModel));

        m_RemoveItemButton.addActionListener(e -> new RemoveItemView(m_ViewModel));

        m_AddCategory.addActionListener(e -> new AddCategoryView(m_ViewModel));

        m_AddItemButton.addActionListener(e -> new AddItemView(m_ViewModel, m_Username));
    }

    /**
     * A method that gets collection of items and print the items into the Table.
     * @param i_Items
     */
    public void showItems(Collection<Item> i_Items) {

//        Vector<String> addRowToTable = new Vector<>();
        LinkedList<Item> items = (LinkedList<Item>) i_Items;
        int sizeOfOldTable = m_TableModel.getRowCount();

        for(int i = 0; i < sizeOfOldTable; i++) {
            m_TableModel.removeRow(0);
        }

        for (int i = 0; i < i_Items.size(); i++) {
            Vector<String> addRowToTable = new Vector<>();

            addRowToTable.add(String.valueOf(items.get(i).getCostNumber()));
            addRowToTable.add(items.get(i).getName());
            addRowToTable.add(items.get(i).getDescribing());
            addRowToTable.add(items.get(i).getCurrency());
            addRowToTable.add(items.get(i).getCategory().getCategoryName());
            addRowToTable.add(items.get(i).getSum());
            addRowToTable.add(items.get(i).getDate().toString());
            m_TableModel.addRow(addRowToTable);


//            m_TableCostData[i][0] = String.valueOf(items.get(i).getCostNumber());
//            m_TableCostData[i][1] = items.get(i).getName();
//            m_TableCostData[i][2] = items.get(i).getDescribing();
//            m_TableCostData[i][3] = items.get(i).getCurrency();
//            m_TableCostData[i][4] = items.get(i).getCategory().getCategoryName();
//            m_TableCostData[i][5] = items.get(i).getSum();
//            m_TableCostData[i][6] = items.get(i).getDate().toString();
        }

    }

    /**
     * A method that gets collection of categories and print the items into the TextArea.
     *
     * @param i_Categories
     */
    public void ShowCategories(Collection<Category> i_Categories) {
        LinkedList<Category> categories = (LinkedList<Category>) i_Categories;

        String[][] data = new String[i_Categories.size()][1];
        String[] columnNames = {"Category"};

        for (int i = 0; i < i_Categories.size(); i++) {
            data[i][0] = String.valueOf(categories.get(i).getCategoryName());
        }

        // Initializing the JTable
        m_ItemsTable = new JTable(data, columnNames);
        m_ItemsTable.setBounds(30, 40, 200, 300);

        scrollPanel = new JScrollPane(m_ItemsTable);

        m_MainFrame.add(scrollPanel, BorderLayout.CENTER);
        m_MainFrame.setVisible(true);


    }

    /**
     * A method that set the IViewModel that send to her from the main program.
     * @param i_Vm
     */
    public void setIViewModel(IViewModel i_Vm) {
        m_ViewModel = i_Vm;
    }

    /**
     * A method that get message from the ViewModel and put it in the TextView.
     * Let the user know what action was preformed.
     * @param i_Message
     */
    public void showMessage(Message i_Message) {
        JOptionPane.showMessageDialog(m_MainFrame, i_Message.getText());
    }
}
