package il.ac.hit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class CostMangerGUIView implements IView {

    private JFrame m_MainFrame;
    private JPanel m_CostPanel;
    private JPanel m_ButtonsPanel;
    private JPanel m_MessagePanel;
    private JTextField m_MessageTextField;
    private JTextArea m_CostInfoTextArea;
    private JButton m_ShowItemsButton, m_ShowCategoriesButton;

    // Show Report Button, TextField, Label and Submit Action.
    private JPanel m_ShowReportPanel;
    private JButton m_ShowReportButton,m_ShowReportActionButton;
    private JLabel m_ShowReportStartDateDayLabel, m_ShowReportStartDateMonthLabel;
    private JLabel m_ShowReportEndDateDayLabel, m_ShowReportEndDateMonthLabel;
    private JComboBox m_ShowReportStartDateDayTextField, m_ShowReportStartDateMonthTextField;
    private JComboBox m_ShowReportEndDateDayTextField, m_ShowReportEndDateMonthTextField;

    // Update Item Button, TextField, Label and Submit Action.
    private JPanel m_UpdateItemPanel;
    private JButton m_UpdateItemButton , m_UpdateItemToDBButton;
    private JLabel m_UpdateItemColNameLabel, m_UpdateItemDataToSetLabel;
    private JTextField m_UpdateItemColNameTextField, m_UpdateItemDataToSetTextField;
    private JLabel m_UpdateItemCostNumberLabel, m_UpdateItemUsernameLabel;
    private JTextField m_UpdateItemUsernameTextField, m_UpdateItemCostNumberTextField;

    // Remove Item  Button, TextField, Label and Submit Action.
    private JPanel m_RemoveItemPanel;
    private JButton m_RemoveItemButton,m_RemoveItemFromDBButton;
    private JLabel m_RemoveItemCostNumberLabel, m_RemoveItemUsernameLabel;
    private JTextField m_RemoveItemUsernameTextField, m_RemoveItemCostNumberTextField;

    // Add Category Button, TextField, Label and Submit Action.
    private JPanel m_AddCategoryPanel;
    private JButton m_AddCategory;
    private JTextField m_AddCategoryTextField;
    private JLabel m_AddCategoryLabel;
    private JButton m_AddCategoryToDBButton;


    // Add Item Button, TextField, Label and Submit Action.
    private JPanel m_AddItemPanel;
    private JButton m_AddItemButton;
    private JTextField m_AddItemNameTextField, m_AddItemDescribingTextField, m_AddItemCurrencyTextField;
    private JTextField m_AddItemCategoryTextField, m_AddItemSumTextField;
    private JComboBox m_AddItemDayComboBox, m_AddItemMonthComboBox;
    private JButton m_AddItemToDBButton;
    private JLabel m_AddItemNameLabel, m_AddItemDescribingLabel, m_AddItemCurrencyLabel;
    private JLabel m_AddItemCategoryLabel, m_AddItemSumLabel,m_AddItemDayLabel, m_AddItemMonthLabel;


    private IViewModel vm;

    @Override
    public void init() {
        String[] days = new String[31];
        for (int i =0 ; i<31 ; i++) {
            days[i] = (i+1) +"";
        }
        String[] months =new String[12];
        for (int i =0 ; i<12 ; i++) {
            months[i] = (i+1) +"";
        }

        // Create JFrame
        m_MainFrame = new JFrame();

        // Create all the JPanels.
        m_ButtonsPanel = new JPanel();
        m_MessagePanel = new JPanel();
        m_CostPanel = new JPanel();

        // Create all the show JButtons.
        m_ShowItemsButton = new JButton("Show Items");
        m_ShowCategoriesButton = new JButton("Show Categories");

        // Creating the detailed report Action.
        m_ShowReportPanel = new JPanel();
        m_ShowReportButton = new JButton("Show detail report");
        m_ShowReportStartDateDayLabel = new JLabel("Start day:");
        m_ShowReportStartDateDayTextField = new JComboBox(days);
        m_ShowReportStartDateMonthLabel = new JLabel("Start month:");
        m_ShowReportStartDateMonthTextField = new JComboBox(months);
        m_ShowReportEndDateDayLabel = new JLabel("End day:");
        m_ShowReportEndDateDayTextField = new JComboBox(days);
        m_ShowReportEndDateMonthLabel = new JLabel("End Month");
        m_ShowReportEndDateMonthTextField = new JComboBox(months);
        m_ShowReportActionButton = new JButton("Show the report");

        // Creating the AddItem Action.
        m_AddItemPanel = new JPanel();
        m_AddItemButton = new JButton("Add item");
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

        // Creating the AddCategory Action.
        m_AddCategoryPanel = new JPanel();
        m_AddCategoryLabel = new JLabel("Category name:");
        m_AddCategory = new JButton("Add category");
        m_AddCategoryTextField = new JTextField(40);
        m_AddCategoryToDBButton = new JButton("Add category to the list");

        // Creating the RemoveItem Action
        m_RemoveItemPanel = new JPanel();
        m_RemoveItemButton = new JButton("Remove item");
        m_RemoveItemFromDBButton = new JButton("Remove Item from the list");
        m_RemoveItemCostNumberLabel = new JLabel("Cost number:");
        m_RemoveItemCostNumberTextField = new JTextField(10);
        m_RemoveItemUsernameLabel = new JLabel("Username:");
        m_RemoveItemUsernameTextField = new JTextField(15);

        // Creating the UpdateItem Action
        m_UpdateItemPanel = new JPanel();
        m_UpdateItemButton = new JButton("Update item");
        m_UpdateItemColNameLabel = new JLabel("Col name to change:");
        m_UpdateItemColNameTextField = new JTextField(15);
        m_UpdateItemDataToSetLabel = new JLabel("Date to set:");
        m_UpdateItemDataToSetTextField = new JTextField(15);
        m_UpdateItemToDBButton = new JButton("Update item to DB");
        m_UpdateItemCostNumberLabel = new JLabel("Cost number:");
        m_UpdateItemCostNumberTextField = new JTextField(10);
        m_UpdateItemUsernameLabel = new JLabel("Username:");
        m_UpdateItemUsernameTextField = new JTextField(15);

        // Create Message TextField.
        m_MessageTextField = new JTextField(40);

        // Creating the List of costs TextArea.
        m_CostInfoTextArea = new JTextArea();
    }

    @Override
    public void start() {

        // Creating the message panel
        m_MessagePanel.setLayout(new FlowLayout());
        m_MessagePanel.add(m_MessageTextField);
        // Add the message panel to the main frame
        m_MainFrame.add(m_MessagePanel,BorderLayout.NORTH);

        // Creating the Button Panel.
        m_ButtonsPanel.setLayout(new FlowLayout());
        m_ButtonsPanel.add(m_ShowItemsButton);
        m_ButtonsPanel.add(m_ShowCategoriesButton);
        m_ButtonsPanel.add(m_ShowReportButton);
        m_ButtonsPanel.add(m_AddItemButton);
        m_ButtonsPanel.add(m_AddCategory);
        m_ButtonsPanel.add(m_RemoveItemButton);
        m_ButtonsPanel.add(m_UpdateItemButton);
        m_MainFrame.add(m_ButtonsPanel,BorderLayout.CENTER);

        // Creating the detailed report Panel.
        m_ShowReportPanel.setLayout(new GridLayout(5,2));
        m_ShowReportPanel.add(m_ShowReportStartDateDayLabel);
        m_ShowReportPanel.add(m_ShowReportStartDateDayTextField);
        m_ShowReportPanel.add(m_ShowReportStartDateMonthLabel);
        m_ShowReportPanel.add(m_ShowReportStartDateMonthTextField);
        m_ShowReportPanel.add(m_ShowReportEndDateDayLabel);
        m_ShowReportPanel.add(m_ShowReportEndDateDayTextField);
        m_ShowReportPanel.add(m_ShowReportEndDateMonthLabel);
        m_ShowReportPanel.add(m_ShowReportEndDateMonthTextField);
        m_ShowReportPanel.add(m_ShowReportActionButton);
        m_ShowReportPanel.setVisible(false);
        m_MainFrame.add(m_ShowReportPanel,BorderLayout.CENTER);

        // Creating the Update Item Panel.
        m_UpdateItemPanel.setLayout(new GridLayout(5,2));
        m_UpdateItemPanel.add(m_UpdateItemColNameLabel);
        m_UpdateItemPanel.add(m_UpdateItemColNameTextField);
        m_UpdateItemPanel.add(m_UpdateItemDataToSetLabel);
        m_UpdateItemPanel.add(m_UpdateItemDataToSetTextField);
        m_UpdateItemPanel.add(m_UpdateItemCostNumberLabel);
        m_UpdateItemPanel.add(m_UpdateItemCostNumberTextField);
        m_UpdateItemPanel.add(m_UpdateItemUsernameLabel);
        m_UpdateItemPanel.add(m_UpdateItemUsernameTextField);
        m_UpdateItemPanel.add(m_UpdateItemCostNumberLabel);
        m_UpdateItemPanel.add(m_UpdateItemCostNumberTextField);
        m_UpdateItemPanel.add(m_UpdateItemUsernameLabel);
        m_UpdateItemPanel.add(m_UpdateItemUsernameTextField);
        m_UpdateItemPanel.add(m_UpdateItemToDBButton);
        m_UpdateItemPanel.setVisible(false);
        m_MainFrame.add(m_UpdateItemPanel,BorderLayout.CENTER);

        // Creating the Remove Item Panel.
        m_RemoveItemPanel.setLayout(new GridLayout(4,2));
        m_RemoveItemPanel.add(m_RemoveItemCostNumberLabel);
        m_RemoveItemPanel.add(m_RemoveItemCostNumberTextField);
        m_RemoveItemPanel.add(m_RemoveItemUsernameLabel);
        m_RemoveItemPanel.add(m_RemoveItemUsernameTextField);
        m_RemoveItemPanel.add(m_RemoveItemFromDBButton);
        m_RemoveItemPanel.setVisible(false);
        m_MainFrame.add(m_RemoveItemPanel,BorderLayout.CENTER);

        // Creating the Add Category Panel.
        m_AddCategoryPanel.setLayout(new GridLayout(2,2));
        m_AddCategoryPanel.add(m_AddCategoryLabel);
        m_AddCategoryPanel.add(m_AddCategoryTextField);
        m_AddCategoryPanel.add(m_AddCategoryToDBButton);
        m_AddCategoryPanel.setVisible(false);
        m_MainFrame.add(m_AddCategoryPanel,BorderLayout.CENTER);

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
        m_AddItemPanel.setVisible(false);
        m_MainFrame.add(m_AddItemPanel,BorderLayout.CENTER);

        // Creating the cost Area.
        m_CostPanel.setLayout(new FlowLayout());
        m_CostPanel.add(m_CostInfoTextArea);
        m_MainFrame.add(m_CostPanel,BorderLayout.SOUTH);

        // Setting the MainFrame Layout
        m_MainFrame.setLayout(new FlowLayout());
        m_MainFrame.setSize(1000,700);
        m_MainFrame.setVisible(true);

        // Listeners ...
        m_ShowItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.getItems();
            }
        });

        m_ShowCategoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.getAllCategories();
            }
        });

        m_ShowReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_ShowReportPanel.setVisible(true);
                m_ButtonsPanel.setVisible(false);
            }
        });

        m_ShowReportActionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.getDetailedReport(
                        java.sql.Date.valueOf("2021-"+m_ShowReportStartDateMonthTextField.getSelectedItem() +"-"+m_ShowReportStartDateDayTextField.getSelectedItem()),
                        java.sql.Date.valueOf("2021-"+m_ShowReportEndDateMonthTextField.getSelectedItem()+"-"+ m_ShowReportEndDateDayTextField.getSelectedItem()));

                m_ShowReportPanel.setVisible(false);
                m_ButtonsPanel.setVisible(true);

            }
        });

        m_UpdateItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_UpdateItemPanel.setVisible(true);
                m_ButtonsPanel.setVisible(false);
            }
        });

        m_UpdateItemToDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.updateItem(m_UpdateItemColNameTextField.getText(),
                        m_UpdateItemDataToSetTextField.getText(),
                        Integer.parseInt(m_UpdateItemCostNumberTextField.getText()),
                        m_UpdateItemUsernameTextField.getText());
                m_UpdateItemPanel.setVisible(false);
                m_ButtonsPanel.setVisible(true);
            }
        });

        m_RemoveItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_RemoveItemPanel.setVisible(true);
                m_ButtonsPanel.setVisible(false);
            }
        });

        m_RemoveItemFromDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.removeItem(Integer.parseInt(m_UpdateItemCostNumberTextField.getText()), m_UpdateItemUsernameTextField.getText());
                m_RemoveItemPanel.setVisible(false);
                m_ButtonsPanel.setVisible(true);
            }
        });

        m_AddCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_AddCategoryPanel.setVisible(true);
                m_ButtonsPanel.setVisible(false);
            }
        });

        m_AddCategoryToDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.addNewCategoryIfExists(new Category(m_AddCategoryTextField.getText()));
                m_AddCategoryPanel.setVisible(false);
                m_ButtonsPanel.setVisible(true);
            }
        });

        m_AddItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_AddItemPanel.setVisible(true);
                m_ButtonsPanel.setVisible(false);
            }
        });

        m_AddItemToDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = new Item(6,m_AddItemNameTextField.getText(),
                        m_AddItemDescribingTextField.getText(),
                        m_AddItemCurrencyTextField.getText(),
                        new Category(m_AddCategoryTextField.getText()),
                        m_AddItemSumTextField.getText(),
                        java.sql.Date.valueOf("2021-"+m_AddItemMonthComboBox.getSelectedItem()+"-"+m_AddItemDayComboBox.getSelectedItem()),
                        "matan");
                vm.addItem(item);
                m_AddItemPanel.setVisible(false);
                m_ButtonsPanel.setVisible(true);
            }
        });
    }

    /**
     * A method that gets collection of items and print the items into the TextArea.
     * @param i_Items
     */
    @Override
    public void showItems(Collection<Item> i_Items) {
        m_CostInfoTextArea.setText("");
        for (Item item : i_Items) {
            m_CostInfoTextArea.append(item.toString() + "\n");
        }
    }

    /**
     * A method that gets collection of categories and print the items into the TextArea.
     * @param i_Categories
     */
    @Override
    public void ShowCategories(Collection<Category> i_Categories) {
        m_CostInfoTextArea.setText("");
        for (Category category : i_Categories) {
            m_CostInfoTextArea.append(category.toString() + "\n");
        }
    }

    /**
     * A method that gets collection of users and print the items into the TextArea.
     * @param i_Users
     */
    @Override
    public void showUsers(Collection<User> i_Users) {
        m_CostInfoTextArea.setText("");
        for (User user : i_Users) {
            m_CostInfoTextArea.append(user.toString() + "\n");
        }
    }

    /**
     * A method that set the IViewModel that send to her from the main program.
     * @param i_Vm
     */
    @Override
    public void setIViewModel(IViewModel i_Vm) {
        vm = i_Vm;
    }

    /**
     * A method that get message from the ViewModel and put it in the TextView.
     * Let the user know what action was preformed.
     * @param i_Message
     */
    @Override
    public void showMessage(Message i_Message) {
        m_MessageTextField.setText(i_Message.getText());
    }

}
