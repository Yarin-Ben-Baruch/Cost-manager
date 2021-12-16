package il.ac.hit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class ApplicationPageGUI {
    private IViewModel m_ViewModel;

    private JFrame m_MainFrame;
    private JPanel m_CostPanel;
    private JPanel m_ButtonsPanel;
    private JPanel m_MessagePanel;
    private JTextField m_MessageTextField;
    private JTextArea m_CostInfoTextArea;
    private JButton m_ShowItemsButton, m_ShowCategoriesButton;

    // Show Report Button
    private JButton m_ShowReportButton;


    // Update Item Button, TextField, Label and Submit Action.
    private JButton m_UpdateItemButton;

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


    public ApplicationPageGUI(IViewModel i_Vm) {
        m_ViewModel = i_Vm;
    }

    public void initApplication() {
        // remove to method !

        // Create JFrame
        m_MainFrame = new JFrame();

        // Create JPanels.
        m_ButtonsPanel = new JPanel();
        m_MessagePanel = new JPanel();
        m_CostPanel = new JPanel();

        // Create all the show JButtons.
        m_ShowItemsButton = new JButton("Show Items");
        m_ShowCategoriesButton = new JButton("Show Categories");

        showReportInit();
        addItemInit();
        addCategoryInit();
        removeItemInit();
        updateItemInit();

        // Create Message TextField.
        m_MessageTextField = new JTextField(40);

        // Creating the List of costs TextArea.
        m_CostInfoTextArea = new JTextArea();
    }

    public void startApplication() {
        // Creating the message panel
        m_MessagePanel.setLayout(new FlowLayout());
        m_MessagePanel.add(m_MessageTextField);
        // Add the message panel to the main frame
        m_MainFrame.add(m_MessagePanel,BorderLayout.NORTH);

        creatingButtonsStart();
        removeItemStart();
        addCategoryStart();
        addItemStart();
        creatingCostAreaStart();

        // Setting the MainFrame Layout
        m_MainFrame.setLayout(new FlowLayout());
        m_MainFrame.setSize(1000,700);
        m_MainFrame.setVisible(true);

        // Listeners ...
        m_ShowItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_ViewModel.getItems();
            }
        });

        m_ShowCategoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_ViewModel.getAllCategories();
            }
        });

        m_ShowReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReportView(m_ViewModel);
                m_ButtonsPanel.setVisible(false);
            }
        });


        m_UpdateItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateView(m_ViewModel);
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
                m_ViewModel.removeItem(Integer.parseInt(m_RemoveItemCostNumberTextField.getText()), m_RemoveItemUsernameTextField.getText());
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
                m_ViewModel.addNewCategoryIfExists(new Category(m_AddCategoryTextField.getText()));
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
                Item item = new Item(8,m_AddItemNameTextField.getText(),
                        m_AddItemDescribingTextField.getText(),
                        m_AddItemCurrencyTextField.getText(),
                        new Category(m_AddCategoryTextField.getText()),
                        m_AddItemSumTextField.getText(),
                        java.sql.Date.valueOf("2021-"+m_AddItemMonthComboBox.getSelectedItem()+"-"+m_AddItemDayComboBox.getSelectedItem()),
                        "matan");
                m_ViewModel.addItem(item);
                m_AddItemPanel.setVisible(false);
                m_ButtonsPanel.setVisible(true);
            }
        });
    }

    private void removeItemInit() {
        // Creating the RemoveItem Action
        m_RemoveItemPanel = new JPanel();
        m_RemoveItemButton = new JButton("Remove item");
        m_RemoveItemFromDBButton = new JButton("Remove Item from the list");
        m_RemoveItemCostNumberLabel = new JLabel("Cost number:");
        m_RemoveItemCostNumberTextField = new JTextField(10);
        m_RemoveItemUsernameLabel = new JLabel("Username:");
        m_RemoveItemUsernameTextField = new JTextField(15);

    }

    private void addCategoryInit() {
        // Creating the AddCategory Action.
        m_AddCategoryPanel = new JPanel();
        m_AddCategoryLabel = new JLabel("Category name:");
        m_AddCategory = new JButton("Add category");
        m_AddCategoryTextField = new JTextField(40);
        m_AddCategoryToDBButton = new JButton("Add category to the list");
    }

    private void updateItemInit() {
        m_UpdateItemButton = new JButton("Update item");
    }

    private void addItemInit() {
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
    }

    private void showReportInit() {
        m_ShowReportButton = new JButton("Show detail report");
    }


    private void removeItemStart() {
        // Creating the Remove Item Panel.
        m_RemoveItemPanel.setLayout(new GridLayout(4,2));
        m_RemoveItemPanel.add(m_RemoveItemCostNumberLabel);
        m_RemoveItemPanel.add(m_RemoveItemCostNumberTextField);
        m_RemoveItemPanel.add(m_RemoveItemUsernameLabel);
        m_RemoveItemPanel.add(m_RemoveItemUsernameTextField);
        m_RemoveItemPanel.add(m_RemoveItemFromDBButton);
        m_RemoveItemPanel.setVisible(false);
        m_MainFrame.add(m_RemoveItemPanel,BorderLayout.CENTER);
    }

    private void addCategoryStart() {
        // Creating the Add Category Panel.
        m_AddCategoryPanel.setLayout(new GridLayout(2,2));
        m_AddCategoryPanel.add(m_AddCategoryLabel);
        m_AddCategoryPanel.add(m_AddCategoryTextField);
        m_AddCategoryPanel.add(m_AddCategoryToDBButton);
        m_AddCategoryPanel.setVisible(false);
        m_MainFrame.add(m_AddCategoryPanel,BorderLayout.CENTER);
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
        m_AddItemPanel.setVisible(false);
        m_MainFrame.add(m_AddItemPanel,BorderLayout.CENTER);
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
        m_MainFrame.add(m_ButtonsPanel,BorderLayout.CENTER);
    }

    private void creatingCostAreaStart() {
        // Creating the cost Area.
        m_CostPanel.setLayout(new FlowLayout());
        m_CostPanel.add(m_CostInfoTextArea);
        m_MainFrame.add(m_CostPanel,BorderLayout.SOUTH);
    }


    /**
     * A method that gets collection of items and print the items into the TextArea.
     * @param i_Items
     */
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
    public void setUsers(Collection<User> i_Users) {
//        m_Users = i_Users;

//        m_CostInfoTextArea.setText("");
//        for (User user : i_Users) {
//            m_CostInfoTextArea.append(user.toString() + "\n");
//        }
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
        m_MessageTextField.setText(i_Message.getText());
    }
}
