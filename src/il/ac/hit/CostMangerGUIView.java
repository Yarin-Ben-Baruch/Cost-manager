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
    private JButton m_ShowItemsButton,m_ShowCategoriesButton, m_ShowReportButton;

    private IViewModel vm;

    @Override
    public void init() {
        // Create JFrame
        m_MainFrame = new JFrame();
        // Create all the JPanels
        m_ButtonsPanel = new JPanel();
        m_MessagePanel = new JPanel();
        m_CostPanel = new JPanel();
        // Create all the JLabels
        m_ShowItemsButton = new JButton("Show Items");
        m_ShowCategoriesButton = new JButton("Show Categories");
        m_ShowReportButton = new JButton("Show detail report");
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

    @Override
    public void showItems(Collection<Item> i_Items) {

        for (Item item : i_Items) {
            m_CostInfoTextArea.append(item.toString() + "\n");
        }
    }

    @Override
    public void ShowCategories(Collection<Category> i_Categories) {
        for (Category category : i_Categories) {
            m_CostInfoTextArea.append(category.toString() + "\n");
        }
    }

    @Override
    public void setIViewModel(IViewModel i_Vm) {
        vm = i_Vm;
    }

    @Override
    public void showMessage(Message i_Message) {

    }

}
