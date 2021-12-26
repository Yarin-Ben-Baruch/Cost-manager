package il.ac.hit.viewmodel;

import il.ac.hit.model.*;
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
     * @param item An object that stores all types of data in the expense table.
     */
    @Override
    public void addItem(Item item) {
        // Using the thread pull.
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // Go to the add item in the model.
                    m_Model.addItem(item);
                    // Get the items from the method get items in the model.
                    Collection<Item> items = m_Model.getItems(item.getUserName());
                    // avoid deadlock in the GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // Calling the show message in the view manager.
                            m_View.showSuccessMessage(new Message("Item was added!"));
                            // Calling the show items to show all the costs in the DB.
                            m_View.showItems(items);
                        }
                    });
                } catch(CostManagerException e) {
                    // avoid deadlock in the GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // Calling the show message method in the view manager to show the error.
                            m_View.showErrorMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }
        });
    }

    /**
     * A method that receives an object from the User class and takes care of connecting it to the view
     * @param user The username is online.
     */
    @Override
    public void addNewUser(User user) {
        // Using the thread pull.
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // Calling the add new user method in the model.
                    m_Model.addNewUser(user);
                    // Avoid deadlock in the GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // Calling the registerSucceeded method in the view manager.
                            m_View.registerSucceeded();
                        }
                    });
                }
                catch (CostManagerException e) {
                    // Avoid deadlock in the GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // Calling the show message method in the view manager to show the error.
                            m_View.showInvalidInputInRegister();
                        }
                    });
                }
            }
        });
    }

    /**
     * A method that receives an object from the Category class and takes care of connecting it to the view
     * @param category Category you want to add to the database.
     */
    @Override
    public void addNewCategoryIfExists(Category category) {
        // Using the thread pull.
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // Calling the addNewCategoryIfExists in the model to add the category if
                    // the category is not exists.
                    m_Model.addNewCategoryIfExists(category);
                    // Get the category from the method getAllCategories in the model.
                    Collection<Category> categories = m_Model.getAllCategories();
                    // Using to avoid deadlock in the GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // Call the show message in the view manager.
                            m_View.showSuccessMessage(new Message( "Category was added!"));
                            m_View.showCategories(categories);
                        }
                    });
                }
                catch (CostManagerException e) {
                    // Using to avoid deadlock in the GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // Calling the show message method in the view manager to show the error.
                            m_View.showErrorMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }
        });

    }

    /**
     * A method that receives a record number and username, and
     * takes care of deleting it from the DB and then presenting the updated data to view.
     * @param costNumber In what row is it in the table.
     * @param userName The username is online.
     */
    @Override
    public void removeItem(String costNumber, String userName) {
        // Using the thread pull.
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // Calling the removeItem method in the model to remove the cost.
                    m_Model.removeItem(costNumber, userName);
                    // Getting all the costs after the remove from the DB.
                    Collection<Item> items = m_Model.getItems(userName);
                    // Using to avoid deadlock in the GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showSuccessMessage(new Message( "The item was removed!"));
                            // Calling the show item method in the view manager.
                            m_View.showItems(items);
                        }
                    });
                }
                catch (CostManagerException e) {
                    // Using to avoid deadlock in the GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // Calling the show message method in the view manager to show the error.
                            m_View.showErrorMessage(new Message(e.getMessage()));
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
     * @param nameColToUpdate The name of the column you want to change.
     * @param dataToSet The information you want to update.
     * @param costNumber In what row is it in the table.
     * @param userName The username is online.
     */
    @Override
    public void updateItem(String nameColToUpdate, String dataToSet, String costNumber, String userName) {
        // Using the thread pull.
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // Calling the updateItem method in the model to update the data in the DB.
                    m_Model.updateItem(nameColToUpdate, dataToSet, costNumber, userName);
                    // Get the update item from the DB .
                    Collection<Item> items = m_Model.getItems(userName);
                    // Using to avoid deadlock in the GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showSuccessMessage(new Message( "The item was updated!"));
                            // Calling the show item method in the view to show the costs.
                            m_View.showItems(items);
                        }
                    });
                }
                catch (CostManagerException e) {
                    // Using to avoid deadlock in the GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // Calling the show message method in the view manager to show the error.
                            m_View.showErrorMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }
        });

    }

    /**
     * A method that gets a start-date and end-date and show you by the View the items between those dates.
     * @param startDate Date from which you want to receive data.
     * @param endDate Date by which you want to receive data.
     * @param username The username is online.
     */
    @Override
    public void getDetailedReport(Date startDate, Date endDate, String username) {
        // Using the thread pull.
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // Calling the get detailed report in the model to get the specific information the user wants.
                    Collection<Item> items = m_Model.getDetailedReport(startDate, endDate, username);
                    // Using to avoid deadlock in the GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            m_View.showSuccessMessage(new Message( "The Report is in the cost table!"));
                            // Calling the show item method in the view to show the update items.
                            m_View.showItems(items);
                        }
                    });
                } catch (CostManagerException e) {
                    // Using to avoid deadlock in the GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // Calling the show message method in the view manager to show the error.
                            m_View.showErrorMessage(new Message(e.getMessage()));
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
        // Using the thread pull.
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    // Calling the get all categories in the model to get the
                    // current categories from the DB.
                    Collection<Category> categories = m_Model.getAllCategories();
                    // Using to avoid deadlock in the GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // Calling the show categories method in the view manager.
                            m_View.showCategories(categories);
                        }
                    });
                } catch (CostManagerException e) {
                    // Using to avoid deadlock in the GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // Calling the show message method in the view manager to show the error.
                            m_View.showErrorMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }
        });
    }

    /**
     * A method that takes from the class that implements the model, all
     * The data stored in DB and then passes to the class that implements the view the list.
     * @param username The username is online.
     */
    @Override
    public void getItems(String username) {
        // Using the thread pull.
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // Calling the get items method in the model to get the current costs
                    // from the DB.
                    Collection<Item> items =  m_Model.getItems(username);
                    // Using to avoid deadlock in the GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // Calling show items in the view manager.
                            m_View.showItems(items);
                        }
                    });
                }
                catch (CostManagerException e) {
                    // Using to avoid deadlock in the GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // Calling the show message method in the view manager to show the error.
                            m_View.showErrorMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }
        });
    }

    /**
     * A method that takes from the class that implements the model, all
     * The data stored in DB and then open the application after successful login.
     * @param user The username is online.
     */
    @Override
    public void isUserExists(User user) {
        // Using the thread pull.
        m_Service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // Calling the checkIfUserExists method in the model
                    // to check if the user is exists in the DB.
                    m_Model.checkIfUserExists(user);
                    // Using to avoid deadlock in GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // Calling the open application from login in the view manager.
                            m_View.openApplicationFromLogin();
                        }
                    });
                } catch (CostManagerException e) {
                    // Using to avoid deadlock in the GUI.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // Calling the show message method in the view manager to show the error.
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
