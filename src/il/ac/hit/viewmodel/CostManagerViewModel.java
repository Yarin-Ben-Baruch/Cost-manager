package il.ac.hit.viewmodel;

import il.ac.hit.*;
import il.ac.hit.model.CostManagerException;
import il.ac.hit.model.Category;
import il.ac.hit.model.IModel;
import il.ac.hit.model.Item;
import il.ac.hit.model.User;
import il.ac.hit.view.IView;

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
                    Collection<Item> items = m_Model.getItems(i_Item.getUserName());

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showMessage(new Message("Item was added!"));
                            m_View.showItems(items);
                        }
                    });
                } catch(CostManagerException e) {
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
                            m_View.registerSucceeded();
                        }
                    });
                }
                catch (CostManagerException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showInvalidInputInRegister();
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
                catch (CostManagerException e) {
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
    public void removeItem(String i_CostNumber, String i_UserName) {
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    m_Model.removeItem(i_CostNumber, i_UserName);
                    Collection<Item> items = m_Model.getItems(i_UserName);

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showItems(items);
                        }
                    });
                }
                catch (CostManagerException e) {
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
    public void updateItem(String i_NameColToUpdate, String i_DataToSet, String i_CostNumber, String i_UserName) {
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    m_Model.updateItem(i_NameColToUpdate, i_DataToSet, i_CostNumber, i_UserName);
                    Collection<Item> items = m_Model.getItems(i_UserName);

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showItems(items);
                        }
                    });
                }
                catch (CostManagerException e) {
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
     * A method that gets a start-date and end-date and show you by the View the items between those dates.
     * @param i_StartDate
     * @param i_EndDate
     */
    @Override
    public void getDetailedReport(Date i_StartDate, Date i_EndDate, String i_Username) {
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Collection items = m_Model.getDetailedReport(i_StartDate, i_EndDate, i_Username);

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showItems(items);
                        }
                    });
                } catch (CostManagerException e) {
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
     * A method that takes from the class that implements the model, all
     * the data stored in DB and then passes to the class that implements the view the list.
     */
    @Override
    public void getAllCategories() {
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    Collection<Category> categories = m_Model.getAllCategories();

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showCategories(categories);
                        }
                    });
                } catch (CostManagerException e) {
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
    * A method that takes from the class that implements the model, all
    * the data stored in DB and then passes to the class that implements the view the list.
     * @param i_Username
     */
    @Override
    public void getItems(String i_Username) {

        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Collection<Item> items =  m_Model.getItems(i_Username);

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showItems(items);
                        }
                    });
                }
                catch (CostManagerException e) {
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
     * A method that takes from the class that implements the model, all
     * the data stored in DB and then open the application after successful login.
     * @param i_User
     */
    @Override
    public void isUserExists(User i_User) {
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    m_Model.checkIfUserExists(i_User);

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.openApplicationFromLogin();
                        }
                    });
                } catch (CostManagerException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showInvalidInputInLogin();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void setView(IView i_View) {
        this.m_View = i_View;
    }

    @Override
    public void setModel(IModel i_Model) {
        this.m_Model = i_Model;
    }


}
