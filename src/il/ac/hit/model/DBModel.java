package il.ac.hit.model;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

/**
 * DBModel class contact with the sql DB.
 * This class implements the IModel.
 */

public class DBModel implements IModel {

    private final String user = "admin";
    private final String password = "admin";
    private final String dbUrl = "jdbc:mysql://localhost:8889/admin";

    /**
     *  Ctor that set the connection and the statement.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    public DBModel() throws CostManagerException {
        //Already creating an object from the class, we want to know if there is a connection problem,
        // so the connection remains
        try (Connection connection = DriverManager.getConnection(dbUrl, user, password)) {
            System.out.println("Connection success !");
        }
        catch (SQLException e) {
            throw new CostManagerException("Connection failed!",e);
        }
    }

    /**
     * Add item method adding cost item for the items sql table.
     * Add item method also adding category if not exists to the categories sql table.
     * @param item An object that stores all types of data in the expense table.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    @Override
    public void addItem(Item item) throws CostManagerException {

        try ( Connection connection = DriverManager.getConnection(dbUrl, user, password)) {
            // if the category is not exists add the category to the categories sql table.
            addCategoryInAddItem(item.getCategory());

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into items (costNumber,name,description,currency,category,sum,date,userName) " +
                            "values " +
                            "(?,?,?,?,?,?,?,?)");

            preparedStatement.setInt(1, item.getCostNumber());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3, item.getDescribing());
            preparedStatement.setString(4, item.getCurrency());
            preparedStatement.setString(5, item.getCategory().getCategoryName());
            preparedStatement.setString(6, item.getSum());
            preparedStatement.setDate(7, item.getDate());
            preparedStatement.setString(8, item.getUserName());

            // checking if adding user was performed.
            int checkAddItem = preparedStatement.executeUpdate();

            // if the add user not performed throws CostMangerException.
            if(checkAddItem != 1)
                throw new CostManagerException("Adding was not executed!");

        }
        catch (SQLException e) {
            throw new CostManagerException("Unable insert into the DB",e);
        }
    }

    /**
     * Get all the items in the cost items sql table.
     * @return Return list of all items in the database.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    @Override
    public Collection<Item> getItems(String userName) throws CostManagerException {

        ResultSet myResult;
        Collection<Item> currentItems = new LinkedList<>();

        try ( Connection connection = DriverManager.getConnection(dbUrl, user, password)) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from items WHERE userName = " +" ? ");
            preparedStatement.setString(1,userName);
            myResult = preparedStatement.executeQuery();

            // Put all the values form the query in LinkedList.
            while (myResult.next())
            {
                currentItems.add(new Item(myResult.getInt("costNumber"), myResult.getString("name"),
                        myResult.getString("description"),myResult.getString("currency"),
                        new Category(myResult.getString("category")),myResult.getString("sum"),
                        myResult.getDate("date"), myResult.getString("userName")));
            }
        } catch (SQLException e) {
            throw new CostManagerException("Unable to pull data from DB");
        }

        return currentItems;
    }

    /**
     * Updating specific data that the user selected, in cost item sql table.
     * @param nameColToUpdate The name of the column you want to change.
     * @param dataToSet The information you want to update.
     * @param costNumber In what row is it in the table.
     * @param userName The username is online.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    @Override
    public void updateItem(String nameColToUpdate, String dataToSet, String costNumber, String userName) throws CostManagerException {

        try ( Connection connection = DriverManager.getConnection(dbUrl, user, password)) {
            int currentCostNumber = Integer.parseInt(costNumber);

            // If the user try to change username throw CostMangerException.
            if(nameColToUpdate.equals("userName")){
                throw new CostManagerException("Can't change here userName");
            }

            PreparedStatement preparedStatement;
            StringBuilder queryToExecute = new StringBuilder();

            // Work for dates only.
            if(nameColToUpdate.equals("date")){
                queryToExecute.append("UPDATE Items SET ").append(nameColToUpdate).append(" = ").append(dataToSet).append(" WHERE costNumber = ").append(currentCostNumber).append(" and userName = ").append("'").append(userName).append("'");
            }
            else {
                // Work with VARCHAR.
                queryToExecute.append("UPDATE Items SET ").append(nameColToUpdate).append(" = ").append("'").append(dataToSet).append("'").append(" WHERE costNumber = ").append(currentCostNumber).append(" and userName = ").append("'").append(userName).append("'");
            }

            preparedStatement = connection.prepareStatement(queryToExecute.toString());
            // Check that the update execute properly.
            int howManyUpdates = preparedStatement.executeUpdate();

            // If the update not execute properly throw CostMangerException.
           if(howManyUpdates != 1){
               throw new CostManagerException("Can't update the item !");
           }
        }
        catch (SQLException | NumberFormatException e) {
            throw new CostManagerException("Unable to update data to DB",e);
        }
    }

    /**
     * Remove item from the cost items sql table.
     * @param costNumber In what row is it in the table.
     * @param userName The username is online.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    @Override
    public void removeItem(String costNumber, String userName) throws CostManagerException {
        try ( Connection connection = DriverManager.getConnection(dbUrl, user, password)) {
            int currentCostNumber = Integer.parseInt(costNumber);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM items WHERE costNumber = ? AND userName = ?");

            preparedStatement.setInt(1,currentCostNumber);
            preparedStatement.setString(2, userName);

            // Check that the remove item was executed properly.
            int howManyUpdates = preparedStatement.executeUpdate();

            // If the remove item not execute properly throw CostMangerException.
            if(howManyUpdates != 1){
                throw new CostManagerException("Can't remove the Item!");
            }

        }
        catch (SQLException | NumberFormatException e) {
            throw new CostManagerException("Unable to remove data from DB",e);
        }
    }


    /**
     * Get Report return collection of all the items in the items sql table that start with the startDate and end with the endDate.
     * @param startDate Date from which you want to receive data.
     * @param endDate Date by which you want to receive data.
     * @return Return list of all items in the database.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    @Override
    public Collection<Item> getDetailedReport(Date startDate, Date endDate, String userName) throws CostManagerException {

        ResultSet myResult;
        Collection<Item> reportItems = new LinkedList<>();

        try ( Connection connection = DriverManager.getConnection(dbUrl, user, password)) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from items WHERE date >= ? AND date <= ? AND userName = ?");

            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);
            preparedStatement.setString(3, userName);

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
            throw new CostManagerException("Unable to pull data from DB");
        }

        return reportItems;
    }

    /**
     * Adding new user to the users sql table.
     * @param user The username is online.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    @Override
    public void addNewUser(User user) throws CostManagerException {
        try ( Connection connection = DriverManager.getConnection(dbUrl, this.user, password)) {

            Collection<User> allUsers = getAllUsers();

            // Check if the user is exists in the users sql table. If the user exists throws CostMangerException.
            if(allUsers.contains(user)) {
                throw new CostManagerException("This user is already exists");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into users (userName,password) " +
                            "values " +
                            "(?,?)");

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());

            // Check that the add new user was executed properly.
            int howManyAdded = preparedStatement.executeUpdate();

            // If the add user not execute properly throw CostMangerException.
            if(howManyAdded != 1) {
                throw new CostManagerException("Can't add same user twice!");
            }
        }
        catch (SQLException e) {
            throw new CostManagerException("Unable insert into the DB",e);
        }
    }

    /**
     * Adding new category to the categories sql table if the category is not exists.
     * @param category Category you want to add to the database.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    @Override
    public void addNewCategoryIfExists(Category category) throws CostManagerException {
        try ( Connection connection = DriverManager.getConnection(dbUrl, user, password)) {

            Collection<Category> allCategories = getAllCategories();

            // If the category is not in the list of the categories add the category.
            if(!allCategories.contains(category) && !category.getCategoryName().isEmpty()){
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into categories (category) " +
                                "values " +
                                "(?)");

                preparedStatement.setString(1, category.getCategoryName());

                // Check that the add category was executed properly.
                int howManyAdded = preparedStatement.executeUpdate();

                // If the add category was not execute properly throw CostMangerException.
                if(howManyAdded != 1){
                    throw new CostManagerException("Unable insert the category");
                }
            }
            else{
                if(category.getCategoryName().isEmpty())
                {
                    throw new CostManagerException("Can't add empty category" );
                }
                throw new CostManagerException("Can't add same category twice!");
            }

        }
        catch (SQLException e) {
            throw new CostManagerException("Unable insert into the DB",e);
        }
    }

    /**
     * Get all the users in the users sql table.
     * @return Returns a list of all registered users in the system.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    @Override
    public Collection<User> getAllUsers() throws CostManagerException {

        ResultSet myResult;
        Collection<User> currentItems = new LinkedList<>();

        try ( Connection connection = DriverManager.getConnection(dbUrl, user, password)) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from users");

            myResult = preparedStatement.executeQuery();

            // Put all the values form the query in LinkedList.
            while (myResult.next())
            {
                currentItems.add(new User(myResult.getString("userName"),
                        myResult.getString("password")));
            }
        } catch (SQLException e) {
            throw new CostManagerException("Unable to pull data from DB");
        }

        return currentItems;
    }

    /**
     * Get all the categories in the categories sql table.
     * @return Returns a list of all categories for all users.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    @Override
    public Collection<Category> getAllCategories() throws CostManagerException {

        ResultSet myResult ;
        Collection<Category> currentItems = new LinkedList<>();

        try ( Connection connection = DriverManager.getConnection(dbUrl, user, password)) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from categories");
            myResult = preparedStatement.executeQuery();

            // Put all the values form the query in LinkedList.
            while (myResult.next())
            {
                currentItems.add(new Category(myResult.getString("category")));
            }

        } catch (SQLException e) {
            throw new CostManagerException("Unable to pull data from DB");
        }

        return currentItems;
    }

    /**
     *  This method is checking if the user that she get is inside the DB.
     * @param user The username is online.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    @Override
    public void checkIfUserExists(User user) throws CostManagerException {

        ResultSet myResult;
        Collection<User> currentItems = new LinkedList<>();

        try ( Connection connection = DriverManager.getConnection(dbUrl, this.user, password)) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from users");

            myResult = preparedStatement.executeQuery();

            // Put all the values form the query in LinkedList.
            while (myResult.next())
            {
                currentItems.add(new User(myResult.getString("userName"),
                        myResult.getString("password")));
            }

            if(!currentItems.contains(user))
                throw new CostManagerException("User not Exists!");

        } catch (SQLException e) {
            throw new CostManagerException("Unable to pull data from DB");
        }

    }

    // This method check if the category is inside the categories list
    // if the category is inside the method do nothing.
    // and if the category is not inside she put the category inside the list.
    private void addCategoryInAddItem(Category category) throws CostManagerException {
        try ( Connection connection = DriverManager.getConnection(dbUrl, user, password)) {

            Collection<Category> allCategories = getAllCategories();

            // If the category is not in the list of the categories add the category.
            if(!allCategories.contains(category) && !category.getCategoryName().isEmpty()){
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into categories (category) " +
                                "values " +
                                "(?)");

                preparedStatement.setString(1, category.getCategoryName());

                // Check that the add category was executed properly.
                int howManyAdded = preparedStatement.executeUpdate();

                // If the add category was not execute properly throw CostMangerException.
                if(howManyAdded != 1){
                    throw new CostManagerException("Unable insert the category");
                }
            }
        }
        catch (SQLException e) {
            throw new CostManagerException("Unable insert into the DB",e);
        }
    }
}