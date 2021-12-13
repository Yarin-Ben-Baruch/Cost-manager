package il.ac.hit;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

/**
 * DBModel class contact with the sql DB.
 */
public class DBModel implements IModel {

    private String user = "admin";
    private String password = "admin";
    private String dbUrl = "jdbc:mysql://localhost:8889/admin";


    private Connection connection; // pull connection
    private Statement statement;
    // קונקשין להחליף בהיברנט ממש את הפול קונקשין

    /*
    הערות מהסגנון הזה, מיועדות להסביר על אלגוריתם מורכב בקוד
     */

    // הערות שכותבים תוך כדי הקוד להסביר משהו עד לנקודה מסוימת

    /**
     *  C'tor that set the connection and the statement.
     * @throws CostMangerException
     */
    public DBModel() throws CostMangerException {

        try {

            Connection connection = DriverManager.getConnection(dbUrl, user, password);
            System.out.println("Connection success !");

            //משתנה שנותן לי לעשות פעולות על db
            Statement myStatement = connection.createStatement();

            setConnection(connection);
            setStatement(myStatement);
        }
        catch (SQLException e) {
            throw new CostMangerException("Connection failed!",e);
        }

        // לשחרר את הקונקשין
        // בלוק פינלי
    }

    // NEED TO REMOVE ! WAIT FOR YARIN CONFIRMATION
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    /**
     * Add item method adding cost item for the items sql table.
     * Add item method also adding category if not exists to the categories sql table.
     * @param item
     * @throws CostMangerException
     */
    @Override
    public void addItem(Item item) throws CostMangerException {

        try {
            // if the category is not exists add the category to the categories sql table.
            addNewCategoryIfExists(item.getCategory());

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into items (costNumber,name,description,currency,category,sum,date,userName) " +
                            "values " +
                            "(?,?,?,?,?,?,?,?)");

            preparedStatement.setInt(1,item.getCostNumber());
            preparedStatement.setString(2,item.getName());
            preparedStatement.setString(3,item.getDescribing());
            preparedStatement.setString(4,item.getCurrency());
            preparedStatement.setString(5,item.getCategory().getCategoryName());
            preparedStatement.setString(6, item.getSum());
            preparedStatement.setDate(7, item.getDate());
            preparedStatement.setString(8, item.getUserName());

            // checking if the update was performed.
            int checkUpdateItems = preparedStatement.executeUpdate();

            // if the update was not performed throws CostMangerException.
            if(checkUpdateItems != 1)
                throw new CostMangerException("Update was not executed!");

        }
        catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
            throw new CostMangerException("Unable insert into the DB",e);
        }
    }

    /**
     * Get all the items in the cost items sql table.
     * @return
     * @throws CostMangerException
     */
    @Override
    public Collection<Item> getItems() throws CostMangerException {

        ResultSet myResult = null;
        Collection<Item> currentItems = new LinkedList<>();

        try {
            // CHECK WITH YARIN IF NEED TO BE IN preparedStatement !!!!!!!
            myResult = statement.executeQuery("SELECT * from items");

            // Put all the values form the query in LinkedList.
            while (myResult.next())
            {
                currentItems.add(new Item(myResult.getInt("costNumber"), myResult.getString("name"),
                        myResult.getString("description"),myResult.getString("currency"),
                        new Category(myResult.getString("category")),myResult.getString("sum"),
                        myResult.getDate("date"), myResult.getString("userName")));

            }

        } catch (SQLException e) {
            throw new CostMangerException("Unable to pull data from DB");
        }

        return currentItems;
    }

    /**
     * Updating specific data that the user selected, in cost item sql table.
     * @param nameColToUpdate
     * @param dataToSet
     * @param costNumber
     * @param userName
     * @throws CostMangerException
     */
    @Override
    public void updateItem(String nameColToUpdate, String dataToSet, int costNumber, String userName) throws CostMangerException {

        try {

            // If the user try to change username throw CostMangerException.
            if(nameColToUpdate.equals("userName")){
                throw new CostMangerException("Can't change here userName");
            }

            PreparedStatement preparedStatement = null;
            Object date = dataToSet;
            StringBuffer queryToExecute = new StringBuffer();

            // Work for dates only.
            if(nameColToUpdate.equals("date")){
                queryToExecute.append("UPDATE Items SET " + nameColToUpdate + " = " + dataToSet + " WHERE costNumber = "
                        + costNumber + " and userName = " + "'" + userName + "'");
            }
            else {
                // Work with VARCHAR.
                queryToExecute.append("UPDATE Items SET " + nameColToUpdate + " = " + "'" + dataToSet + "'" + " WHERE costNumber = "
                        + costNumber + " and userName = " + "'" + userName + "'");
            }

            preparedStatement = connection.prepareStatement(queryToExecute.toString());
            // Check that the update execute properly.
            int howManyUpdates = preparedStatement.executeUpdate();

            // If the update not execute properly throw CostMangerException.
           if(howManyUpdates != 1){
               throw new CostMangerException("Can't update the item !");
           }

        }
        catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
            throw new CostMangerException("Unable to update data to DB",e);
        }

    }

    /**
     * Remove item from the cost items sql table
     * @param costNumber
     * @param userName
     * @throws CostMangerException
     */
    @Override
    public void removeItem(int costNumber, String userName) throws CostMangerException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM items WHERE costNumber = ? AND userName = ?");

            preparedStatement.setInt(1,costNumber);
            preparedStatement.setString(2,userName);
            // Check that the update execute properly.
            int howManyUpdates = preparedStatement.executeUpdate();

            // If the update not execute properly throw CostMangerException.
            if(howManyUpdates != 1){
                throw new CostMangerException("Can't update more than one item !");
            }
        }
        catch (SQLException e) {
            throw new CostMangerException("Unable to update data to DB",e);
        }
    }

    /**
     * Get Report return collection of all the items in the items sql table that start with the startDate and end with the endDate.
     * @param startDate
     * @param endDate
     * @return
     * @throws CostMangerException
     */
    @Override
    public Collection<Item> getDetailedReport(Date startDate, Date endDate) throws CostMangerException {

        ResultSet myResult = null;
        Collection<Item> reportItems = new LinkedList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from items WHERE date >= ? AND date <= ?");

            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);
            myResult = preparedStatement.executeQuery();

            // Put all the values form the query in LinkedList.
            while(myResult.next()) {

                    Item currentItemToAdd = new Item(myResult.getInt("costNumber"), myResult.getString("name"),
                            myResult.getString("description"),myResult.getString("currency"),
                            new Category(myResult.getString("category")),myResult.getString("sum"),
                            myResult.getDate("date"), myResult.getString("userName"));

                    reportItems.add(currentItemToAdd);

            }
        } catch (SQLException e) {
            throw new CostMangerException("Unable to pull data from DB");
        }

        return reportItems;
    }

    /**
     * Adding new user to the users sql table.
     * @param user
     * @throws CostMangerException
     */
    @Override
    public void addNewUser(User user) throws CostMangerException {
        try {
            Collection<User> allUsers = getAllUsers();

            /*
                Check if the user is exists in the users sql table.
                If the user exists throws CostMangerException.
            */
            if(allUsers.contains(user)) {
                throw new CostMangerException("This user is already exists");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into users (userName,password) " +
                            "values " +
                            "(?,?)");

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());

            // Check that the update execute properly.
            int howManyUpdates = preparedStatement.executeUpdate();

            // If the update not execute properly throw CostMangerException.
            if(howManyUpdates != 1){
                throw new CostMangerException("Can't add same user twice!");
            }
        }
        catch (SQLException e) {
            throw new CostMangerException("Unable insert into the DB",e);
        }
    }

    /**
     * Adding new category to the categories sql table if the category is not exists.
     * @param category
     * @throws CostMangerException
     */
    @Override
    public void addNewCategoryIfExists(Category category) throws CostMangerException {
        try {
            Collection<Category> allCategories = getAllCategories();

            // If the category is not in the list of the categories add the category.
            if(!allCategories.contains(category)){
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into categories (category) " +
                                "values " +
                                "(?)");

                preparedStatement.setString(1, category.getCategoryName());

                // Check that the update execute properly.
                int howManyUpdates = preparedStatement.executeUpdate();

                // If the update not execute properly throw CostMangerException.
                if(howManyUpdates != 1){
                    throw new CostMangerException("Can't add same user twice!");
                }
            }
        }
        catch (SQLException e) {
            throw new CostMangerException("Unable insert into the DB",e);
        }
    }

    /**
     * Get all the users in the users sql table.
     * @return
     * @throws CostMangerException
     */
    @Override
    public Collection<User> getAllUsers() throws CostMangerException {

        ResultSet myResult = null;
        Collection<User> currentItems = new LinkedList<>();

        try {
            // CHECK WITH YARIN ABOUT preparedStatement
            myResult = statement.executeQuery("SELECT * from users");

            // Put all the values form the query in LinkedList.
            while (myResult.next())
            {
                currentItems.add(new User(myResult.getString("userName"),
                        myResult.getString("password")));
            }
        } catch (SQLException e) {
            throw new CostMangerException("Unable to pull data from DB");
        }

        return currentItems;
    }

    /**
     * Get all the categories in the categories sql table.
     * @return
     * @throws CostMangerException
     */
    @Override
    public Collection<Category> getAllCategories() throws CostMangerException {

        ResultSet myResult = null;
        Collection<Category> currentItems = new LinkedList<>();

        try {
            // CHECK WITH YARIN ABOUT preparedStatement
            myResult = statement.executeQuery("SELECT * from categories");

            // Put all the values form the query in LinkedList.
            while (myResult.next())
            {
                currentItems.add(new Category(myResult.getString("category")));
            }

        } catch (SQLException e) {
            throw new CostMangerException("Unable to pull data from DB");
        }

        return currentItems;
    }
}
