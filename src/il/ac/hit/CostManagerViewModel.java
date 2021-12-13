package il.ac.hit;

import javax.swing.*;
import java.sql.Date;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A class that implements the interface of the view model.
 */

public class CostManagerViewModel implements IViewModel {

    private IModel m_Model;
    private IView m_View;
    private ExecutorService m_Service;

    /**
     * A constructor that creates a pull of threads.
     */
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

    /**
     * A method that receives an object from the item class and takes care of connecting it to the view.
     * @param i_Item
     */
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
                            m_View.showMessage(new Message("Item was added!"));
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

    /**
     * A method that receives an object from the User class and takes care of connecting it to the view
     * @param i_User
     */
    @Override
    public void addNewUser(User i_User) {
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    m_Model.addNewUser(i_User);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showMessage(new Message("User was added!"));
                        }
                    });
                }
                catch (CostMangerException e) {
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

    /**
     * A method that receives an object from the Category class and takes care of connecting it to the view
     * @param i_Category
     */
    @Override
    public void addNewCategoryIfExists(Category i_Category) {
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    m_Model.addNewCategoryIfExists(i_Category);

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showMessage(new Message( "Category was added!"));
                        }
                    });
                }
                catch (CostMangerException e) {
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

    /**
     * A method that receives a record number and username, and
     * takes care of deleting it from the DB and then presenting the updated data to view.
     * @param i_CostNumber
     * @param i_UserName
     */
    @Override
    public void removeItem(int i_CostNumber, String i_UserName) {
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    m_Model.removeItem(i_CostNumber, i_UserName);
                    Collection<Item> items = m_Model.getItems();

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showItems(items);
                        }
                    });
                }
                catch (CostMangerException e) {
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

    /**
     * A method that gets a column name that you want to change, which value to put in place of the existing value and
     * uses the record number and username of the person who is connected to the application, and
     * takes care of updating it in DB and then displaying the updated data.
     * @param i_NameColToUpdate
     * @param i_DataToSet
     * @param i_CostNumber
     * @param i_UserName
     */
    @Override
    public void updateItem(String i_NameColToUpdate, String i_DataToSet, int i_CostNumber, String i_UserName) {
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    m_Model.updateItem(i_NameColToUpdate, i_DataToSet, i_CostNumber, i_UserName);
                    Collection<Item> items = m_Model.getItems();

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showItems(items);
                        }
                    });
                }
                catch (CostMangerException e) {
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
    public void getDetailedReport(Date i_StartDate, Date i_EndDate) {

    }

    @Override
    public void getAllUsers() {

    }

    @Override
    public void getAllCategories() {

    }

    /**
     * A method that takes from the class that implements the model, all
     * the data stored in DB and then passes to the class that implements the view the list.
     */
    @Override
    public void getItems() {

        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Collection<Item> items =  m_Model.getItems();

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showItems(items);
                        }
                    });
                }
                catch (CostMangerException e) {
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
}
