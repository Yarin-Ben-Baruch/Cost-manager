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
    private final ExecutorService m_Service;

    /**
     * A constructor that creates a pull of threads.
     */
    public CostManagerViewModel() {
        this.m_Service = Executors.newFixedThreadPool(3);
    }

    /**
     * A method that receives an object from the item class and takes care of connecting it to the view.
     * @param item
     */
    @Override
    public void addItem(Item item) {
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    m_Model.addItem(item);
                    Collection<Item> items = m_Model.getItems(item.getUserName());

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
     * @param user
     */
    @Override
    public void addNewUser(User user) {
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    m_Model.addNewUser(user);

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
     * @param category
     */
    @Override
    public void addNewCategoryIfExists(Category category) {
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    m_Model.addNewCategoryIfExists(category);

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
     * @param costNumber
     * @param userName
     */
    @Override
    public void removeItem(String costNumber, String userName) {
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    m_Model.removeItem(costNumber, userName);
                    Collection<Item> items = m_Model.getItems(userName);

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
     * @param nameColToUpdate
     * @param dataToSet
     * @param costNumber
     * @param userName
     */
    @Override
    public void updateItem(String nameColToUpdate, String dataToSet, String costNumber, String userName) {
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    m_Model.updateItem(nameColToUpdate, dataToSet, costNumber, userName);
                    Collection<Item> items = m_Model.getItems(userName);

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
     * @param startDate
     * @param endDate
     */
    @Override
    public void getDetailedReport(Date startDate, Date endDate, String username) {
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Collection<Item> items = m_Model.getDetailedReport(startDate, endDate, username);

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
     * @param username
     */
    @Override
    public void getItems(String username) {

        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Collection<Item> items =  m_Model.getItems(username);

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
     * @param user
     */
    @Override
    public void isUserExists(User user) {
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    m_Model.checkIfUserExists(user);

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
    public void setView(IView view) {
        this.m_View = view;
    }

    @Override
    public void setModel(IModel model) {
        this.m_Model = model;
    }


}
