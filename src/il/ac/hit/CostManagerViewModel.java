package il.ac.hit;

import javax.swing.*;
import java.sql.Date;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CostManagerViewModel implements IViewModel {

    private IModel m_Model;
    private IView m_View;
    private ExecutorService m_Service;

    public CostManagerViewModel() {
        this.m_Service = Executors.newFixedThreadPool(3);
    }

    @Override
    public void setView(IView i_View) {
        this.m_View = i_View;
    }

    @Override
    public void setModel(IModel i_Model) {
        this.m_Model = i_Model;
    }

    @Override
    public void addItem(Item i_Item) {
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    m_Model.addItem(i_Item);
                    Collection<Item> items = m_Model.getItems();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showMessage(new Message("item was added"));
                            m_View.showItems(items);
                        }
                    });
                } catch(CostMangerException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void addNewUser(User i_User) {

    }

    @Override
    public void addNewCategoryIfExists(Category i_Category) {

    }

    @Override
    public void removeItem(int i_CostNumber, String i_UserName) {

    }

    @Override
    public void updateItem(String i_NameColToUpdate, String i_DataToSet, int i_CostNumber, String i_UserName) {

    }

    @Override
    public void getDetailedReport(Date i_StartDate, Date i_EndDate) {

    }

    @Override
    public void getAllUsers() {

    }

    @Override
    public void getAllCategories() {

    }

    @Override
    public void getItems() {

    }
}
