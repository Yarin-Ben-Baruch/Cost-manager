package il.ac.hit.Model;

import il.ac.hit.Exception.CostMangerException;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

/**
 * DBModel class contact with the sql DB.
 * This class implements the IModel.
 */
public class DBModel implements IModel {

    private String m_User = "admin";
    private String m_Password = "admin";
    private String m_DbUrl = "jdbc:mysql://localhost:8889/admin";


    /*
    הערות מהסגנון הזה, מיועדות להסביר על אלגוריתם מורכב בקוד
     */

    // הערות שכותבים תוך כדי הקוד להסביר משהו עד לנקודה מסוימת

    /**
     *  C'tor that set the connection and the statement.
     * @throws CostMangerException
     */
    public DBModel() throws CostMangerException {

        try ( Connection connection = DriverManager.getConnection(m_DbUrl, m_User, m_Password)) {
            System.out.println("Connection success !");
        }
        catch (SQLException e) {
            throw new CostMangerException("Connection failed!",e);
        }

    }


    /**
     * Add item method adding cost item for the items sql table.
     * Add item method also adding category if not exists to the categories sql table.
     * @param i_Item
     * @throws CostMangerException
     */
    @Override
    public void addItem(Item i_Item) throws CostMangerException {

        try ( Connection connection = DriverManager.getConnection(m_DbUrl, m_User, m_Password)) {
            // if the category is not exists add the category to the categories sql table.
            addCategoryInAddItem(i_Item.getCategory());

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into items (costNumber,name,description,currency,category,sum,date,userName) " +
                            "values " +
                            "(?,?,?,?,?,?,?,?)");

            preparedStatement.setInt(1, i_Item.getCostNumber());
            preparedStatement.setString(2, i_Item.getName());
            preparedStatement.setString(3, i_Item.getDescribing());
            preparedStatement.setString(4, i_Item.getCurrency());
            preparedStatement.setString(5, i_Item.getCategory().getCategoryName());
            preparedStatement.setString(6, i_Item.getSum());
            preparedStatement.setDate(7, i_Item.getDate());
            preparedStatement.setString(8, i_Item.getUserName());

            // checking if adding user was performed.
            int checkAddItem = preparedStatement.executeUpdate();

            // if the add user not performed throws CostMangerException.
            if(checkAddItem != 1)
                throw new CostMangerException("Adding was not executed!");

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
    public Collection<Item> getItems(String i_Username) throws CostMangerException {

        ResultSet myResult = null;
        Collection<Item> currentItems = new LinkedList<>();

        try ( Connection connection = DriverManager.getConnection(m_DbUrl, m_User, m_Password)) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from items WHERE userName = " +" ? ");
            preparedStatement.setString(1,i_Username);
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
            throw new CostMangerException("Unable to pull data from DB");
        }

        return currentItems;
    }

    /**
     * Updating specific data that the user selected, in cost item sql table.
     * @param i_NameColToUpdate
     * @param i_DataToSet
     * @param i_CostNumber
     * @param i_Username
     * @throws CostMangerException
     */
    @Override
    public void updateItem(String i_NameColToUpdate, String i_DataToSet, String i_CostNumber, String i_Username) throws CostMangerException {

        try ( Connection connection = DriverManager.getConnection(m_DbUrl, m_User, m_Password)) {
            int costNumber = Integer.parseInt(i_CostNumber);

            // If the user try to change username throw CostMangerException.
            if(i_NameColToUpdate.equals("userName")){
                throw new CostMangerException("Can't change here userName");
            }

            PreparedStatement preparedStatement = null;
            Object date = i_DataToSet;
            StringBuffer queryToExecute = new StringBuffer();

            // Work for dates only.
            if(i_NameColToUpdate.equals("date")){
                queryToExecute.append("UPDATE Items SET " + i_NameColToUpdate + " = " + i_DataToSet + " WHERE costNumber = "
                        + costNumber + " and userName = " + "'" + i_Username + "'");
            }
            else {
                // Work with VARCHAR.
                queryToExecute.append("UPDATE Items SET " + i_NameColToUpdate + " = " + "'" + i_DataToSet + "'" + " WHERE costNumber = "
                        + costNumber + " and userName = " + "'" + i_Username + "'");
            }

            preparedStatement = connection.prepareStatement(queryToExecute.toString());
            // Check that the update execute properly.
            int howManyUpdates = preparedStatement.executeUpdate();

            // If the update not execute properly throw CostMangerException.
           if(howManyUpdates != 1){
               throw new CostMangerException("Can't update the item !");
           }
        }
        catch (SQLException | NumberFormatException e) {
            System.out.println(e.fillInStackTrace());
            throw new CostMangerException("Unable to update data to DB",e);
        }
    }

    /**
     * Remove item from the cost items sql table
     * @param i_CostNumber
     * @param i_Username
     * @throws CostMangerException
     */
    @Override
    public void removeItem(String i_CostNumber, String i_Username) throws CostMangerException {
        try ( Connection connection = DriverManager.getConnection(m_DbUrl, m_User, m_Password)) {
            int costNumber = Integer.parseInt(i_CostNumber);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM items WHERE costNumber = ? AND userName = ?");

            preparedStatement.setInt(1,costNumber);
            preparedStatement.setString(2, i_Username);

            // Check that the remove item was executed properly.
            int howManyUpdates = preparedStatement.executeUpdate();

            // If the remove item not execute properly throw CostMangerException.
            if(howManyUpdates != 1){
                throw new CostMangerException("Can't remove the Item!");
            }
        }
        catch (SQLException | NumberFormatException e) {
            throw new CostMangerException("Unable to update data to DB",e);
        }
    }

    /**
     * Get Report return collection of all the items in the items sql table that start with the startDate and end with the endDate.
     * @param i_StartDate
     * @param i_EndDate
     * @return
     * @throws CostMangerException
     */
    @Override
    public Collection<Item> getDetailedReport(Date i_StartDate, Date i_EndDate) throws CostMangerException {

        ResultSet myResult = null;
        Collection<Item> reportItems = new LinkedList<>();

        try ( Connection connection = DriverManager.getConnection(m_DbUrl, m_User, m_Password)) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from items WHERE date >= ? AND date <= ?");

            preparedStatement.setDate(1, i_StartDate);
            preparedStatement.setDate(2, i_EndDate);
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
     * @param i_User
     * @throws CostMangerException
     */
    @Override
    public void addNewUser(User i_User) throws CostMangerException {
        try ( Connection connection = DriverManager.getConnection(m_DbUrl, this.m_User, m_Password)) {

            Collection<User> allUsers = getAllUsers();

            // Check if the user is exists in the users sql table. If the user exists throws CostMangerException.
            if(allUsers.contains(i_User)) {
                throw new CostMangerException("This user is already exists");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into users (userName,password) " +
                            "values " +
                            "(?,?)");

            preparedStatement.setString(1, i_User.getUserName());
            preparedStatement.setString(2, i_User.getPassword());

            // Check that the add new user was executed properly.
            int howManyAdded = preparedStatement.executeUpdate();

            // If the add user not execute properly throw CostMangerException.
            if(howManyAdded != 1) {
                throw new CostMangerException("Can't add same user twice!");
            }
        }
        catch (SQLException e) {
            throw new CostMangerException("Unable insert into the DB",e);
        }
    }

    /**
     * Adding new category to the categories sql table if the category is not exists.
     * @param i_Category
     * @throws CostMangerException
     */
    @Override
    public void addNewCategoryIfExists(Category i_Category) throws CostMangerException {
        try ( Connection connection = DriverManager.getConnection(m_DbUrl, m_User, m_Password)) {

            Collection<Category> allCategories = getAllCategories();

            // If the category is not in the list of the categories add the category.
            if(!allCategories.contains(i_Category) && !i_Category.getCategoryName().isEmpty()){
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into categories (category) " +
                                "values " +
                                "(?)");

                preparedStatement.setString(1, i_Category.getCategoryName());

                // Check that the add category was executed properly.
                int howManyAdded = preparedStatement.executeUpdate();

                // If the add category was not execute properly throw CostMangerException.
                if(howManyAdded != 1){
                    throw new CostMangerException("Unable insert the category");
                }
            }
            else{
                if(i_Category.getCategoryName().isEmpty())
                {
                    throw new CostMangerException("Can't add empty category" );
                }
                throw new CostMangerException("Can't add same category twice!");
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

        try ( Connection connection = DriverManager.getConnection(m_DbUrl, m_User, m_Password)) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from users");

            myResult = preparedStatement.executeQuery();

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

        try ( Connection connection = DriverManager.getConnection(m_DbUrl, m_User, m_Password)) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from categories");
            myResult = preparedStatement.executeQuery();

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

    @Override
    public void checkIfUserExists(User i_User) throws CostMangerException {

        ResultSet myResult = null;
        Collection<User> currentItems = new LinkedList<>();

        try ( Connection connection = DriverManager.getConnection(m_DbUrl, m_User, m_Password)) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from users");

            myResult = preparedStatement.executeQuery();

            // Put all the values form the query in LinkedList.
            while (myResult.next())
            {
                currentItems.add(new User(myResult.getString("userName"),
                        myResult.getString("password")));
            }

            if(!currentItems.contains(i_User))
                throw new CostMangerException("User not Exists!");

        } catch (SQLException e) {
            throw new CostMangerException("Unable to pull data from DB");
        }

    }


    private void addCategoryInAddItem(Category i_Category) throws CostMangerException{
        try ( Connection connection = DriverManager.getConnection(m_DbUrl, m_User, m_Password)) {

            Collection<Category> allCategories = getAllCategories();

            // If the category is not in the list of the categories add the category.
            if(!allCategories.contains(i_Category) && !i_Category.getCategoryName().isEmpty()){
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into categories (category) " +
                                "values " +
                                "(?)");

                preparedStatement.setString(1, i_Category.getCategoryName());

                // Check that the add category was executed properly.
                int howManyAdded = preparedStatement.executeUpdate();

                // If the add category was not execute properly throw CostMangerException.
                if(howManyAdded != 1){
                    throw new CostMangerException("Unable insert the category");
                }
            }
        }
        catch (SQLException e) {
            throw new CostMangerException("Unable insert into the DB",e);
        }
    }
}
