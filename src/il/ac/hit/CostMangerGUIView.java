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
    private TextField m_MessageTextField;
    private TextArea m_CostInfoTextArea;
    private JButton m_ShowItemsButton, m_ShowCategoriesButton, m_ShowReportButton, m_ShowUsersButton;
    private JButton m_AddItemButton, m_AddUserButton , m_AddCategory;
    private JButton m_RemoveItemButton, m_UpdateItemButton;
    private IViewModel vm;

    @Override
    public void init() {
        // Create JFrame
        m_MainFrame = new JFrame();
        // Create all the JPanels
        m_ButtonsPanel = new JPanel();
        m_MessagePanel = new JPanel();
        m_CostPanel = new JPanel();
        // Create all the JButtons
        m_ShowItemsButton = new JButton("Show Items");
        m_ShowCategoriesButton = new JButton("Show Categories");
        m_ShowReportButton = new JButton("Show detail report");
        m_ShowUsersButton = new JButton("Show users names"); // I THINK WE DONT NEED THIS!
        m_AddItemButton = new JButton("Add item");
        m_AddUserButton = new JButton("Add user"); // NO NEED !
        m_AddCategory = new JButton("Add category");
        m_RemoveItemButton = new JButton("Remove item");
        m_UpdateItemButton = new JButton("Update item");
        // Create TextField
        m_MessageTextField = new TextField(40);
        // Creating TextArea
        m_CostInfoTextArea = new TextArea();
    }

    @Override
    public void start() {
        // Creating the message panel
        m_MessagePanel.setLayout(new FlowLayout());
        m_MessagePanel.add(m_MessageTextField);
        // Add the message panel to the main frame
        m_MainFrame.add(m_MessagePanel,BorderLayout.NORTH);

        // Creating the Button Panel !
        m_ButtonsPanel.setLayout(new FlowLayout());
        m_ButtonsPanel.add(m_ShowItemsButton);
        m_ButtonsPanel.add(m_ShowCategoriesButton);
        m_ButtonsPanel.add(m_ShowReportButton);
        m_ButtonsPanel.add(m_ShowUsersButton);
        m_ButtonsPanel.add(m_AddItemButton);
        m_ButtonsPanel.add(m_AddUserButton);
        m_ButtonsPanel.add(m_AddCategory);
        m_ButtonsPanel.add(m_RemoveItemButton);
        m_ButtonsPanel.add(m_UpdateItemButton);
        m_MainFrame.add(m_ButtonsPanel,BorderLayout.CENTER);

        // Creating the cost Area
        m_CostPanel.setLayout(new FlowLayout());
        m_CostPanel.add(m_CostInfoTextArea);
        m_MainFrame.add(m_CostPanel,BorderLayout.SOUTH);

        m_MainFrame.setSize(600,700);
        m_MainFrame.setVisible(true);

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

    }

    /**
     * A method that gets collection of items and print the items into the TextArea.
     * @param i_Items
     */
    @Override
    public void showItems(Collection<Item> i_Items) {

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
