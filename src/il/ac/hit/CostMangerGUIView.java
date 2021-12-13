package il.ac.hit;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class CostMangerGUIView implements IView {

    private JFrame m_MainFrame;
//    private JScrollPane m_ScrollPanel;
//    private JTable m_TablePanel;
    private JPanel m_ButtonsPanel;
    private JPanel m_MessagePanel;
    private TextField m_MessageTextField;

    private JButton m_ShowItemsButton,m_ShowCategoriesButton,m_ShowReport;

    private IViewModel vm;

    @Override
    public void init() {
        // Create JFrame
        m_MainFrame = new JFrame();
        // Create all the JPanels
        m_ButtonsPanel = new JPanel();
        m_MessagePanel = new JPanel();
        // Create all the JLabels
        m_ShowItemsButton = new JButton("Show Items");
        m_ShowCategoriesButton = new JButton("Show Categories");
        m_ShowReport = new JButton("Show detail report");
        // Create TextField
        m_MessageTextField = new TextField(40);
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
        m_ButtonsPanel.add(m_ShowReport);
        m_MainFrame.add(m_ButtonsPanel,BorderLayout.CENTER);



        m_MainFrame.setSize(600,700);
        m_MainFrame.setVisible(true);

    }

    @Override
    public void showItems(Collection<Item> i_Items) {

    }

    @Override
    public void setIViewModel(IViewModel i_Vm) {

    }

    @Override
    public void showMessage(Message i_Message) {

    }

}
