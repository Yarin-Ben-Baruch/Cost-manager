package il.ac.hit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.LinkedList;

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
    // Update Item Button.
    private JButton m_UpdateItemButton;
    // Remove Item  Button.
    private JButton m_RemoveItemButton;
    // Add Category Button.
    private JButton m_AddCategory;
    // Add Item Button.
    private JButton m_AddItemButton;
    //table
    private JTable m_ItemsTable;


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

        // Creating the RemoveItem Action
        m_RemoveItemButton = new JButton("Remove item");
        // Creating the AddCategory Action.
        m_AddCategory = new JButton("Add category");
        // Creating the UpdateItem Action
        m_UpdateItemButton = new JButton("Update item");
        // Creating the AddItem Action.
        m_AddItemButton = new JButton("Add item");
        // Creating the ShowReport Action
        m_ShowReportButton = new JButton("Show detail report");



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
                new UpdateItemView(m_ViewModel);
            }
        });

        m_RemoveItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveItemView(m_ViewModel);
            }
        });



        m_AddCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCategoryView(m_ViewModel);
            }
        });



        m_AddItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddItemView(m_ViewModel);
            }
        });


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

        LinkedList<Item> items = (LinkedList<Item>) i_Items;
        String[][] data = new String[i_Items.size()][7];
        String[] columnNames = { "Cost number", "Name", "Description", "Currency", "Category", "Sum", "Date"};

        for(int i = 0 ; i < i_Items.size() ; i++){
            data[i][0] = String.valueOf(items.get(i).getCostNumber());
            data[i][1] = items.get(i).getName();
            data[i][2] = items.get(i).getDescribing();
            data[i][3] = items.get(i).getCurrency();
            data[i][4] = items.get(i).getCategory().getCategoryName();
            data[i][5] = items.get(i).getSum();
            data[i][6] = items.get(i).getDate().toString();
        }

        // Initializing the JTable
        m_ItemsTable = new JTable(data, columnNames);
        m_ItemsTable.setBounds(30, 40, 200, 300);

        JScrollPane sp = new JScrollPane(m_ItemsTable);
        m_MainFrame.add(sp);
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
