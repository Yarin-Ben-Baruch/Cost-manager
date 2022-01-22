package il.ac.hit.model;

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * DBModel class contact with the sql DB.
 * This class implements the IModel.
 */
public class DBModel implements IModel {

    /*
    This class implements the DAO design pattern.
    This class implements the Singleton design pattern.
     */
    private final String user = "admin";
    private final String password = "admin";
    private final String dbUrl = "jdbc:mysql://localhost:8889/admin";
    private static DBModel dbModel = null;

    // Singleton Principle.
    private DBModel(){}

    /**
     * A method that returns the object that represents the class
     * @return DBModel object.
     */
    public static DBModel getObject() {
        // Creation only for first use in the class.
        if(dbModel == null){
            dbModel = new DBModel();
        }
        return dbModel;
    }

    /**
     * Add item method adding cost item for the items sql table.
     * Add item method also adding category if not exists to the categories sql table.
     * @param item An object that stores all types of data in the expense table.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    @Override
    public void addItem(Item item) throws CostManagerException {

        String sqlStatement =
                "insert into items (costNumber,name,description,currency,category,sum,date,userName) values (?,?,?,?,?,?,?,?)";

        try ( Connection connection = DriverManager.getConnection(dbUrl, user, password);
         PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            // if the category is not exists add the category to the categories sql table.
            addCategoryInAddItem(item.getCategory());


            // Replaces the question marks.
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

        String sqlStatement = "SELECT * from items WHERE userName =  ? ";
                Collection<Item> currentItems = new LinkedList<>();

        try ( Connection connection = DriverManager.getConnection(dbUrl, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            // Replaces the question marks
            preparedStatement.setString(1,userName);

            try(ResultSet myResult = preparedStatement.executeQuery()){

                // Put all the values form the query in LinkedList.
                while (myResult.next()) {
                    // build new item inside to list.
                    currentItems.add(new Item(myResult.getInt("costNumber"), myResult.getString("name"),
                            myResult.getString("description"), myResult.getString("currency"),
                            new Category(myResult.getString("category")), myResult.getString("sum"),
                            myResult.getDate("date"), myResult.getString("userName")));
                }
            }
        } catch (SQLException e) {
            throw new CostManagerException("Unable to pull data from DB",e);
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

            StringBuilder queryToExecute = new StringBuilder();
            queryToExecute.append("UPDATE Items SET ").append(nameColToUpdate)
                    .append(" = ").append("'").append(dataToSet).append("'")
                    .append(" WHERE costNumber = ").append(currentCostNumber)
                    .append(" and userName = ").append("'").append(userName)
                    .append("'");

            try(PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute.toString())) {
                // Check that the update execute properly.
                int howManyUpdates = preparedStatement.executeUpdate();

                // If the update not execute properly throw CostMangerException.
                if (howManyUpdates != 1) {
                    throw new CostManagerException("Can't update the item !");
                }
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

        String sqlStatement = "DELETE FROM items WHERE costNumber = ? AND userName = ?";

        try (Connection connection = DriverManager.getConnection(dbUrl, user, password);
              PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            // parsing the string costNumber to int for the table.
            int currentCostNumber = Integer.parseInt(costNumber);

            // Replaces the question marks.
            preparedStatement.setInt(1,currentCostNumber);
            preparedStatement.setString(2, userName);

            // Check that the remove item was executed properly.
            int howManyUpdates = preparedStatement.executeUpdate();

            // If the remove item not execute properly throw CostMangerException.
            if(howManyUpdates != 1){
                throw new CostManagerException("Can't remove the Item!");
            }
            updateCostNumberAfterRemove(userName);
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
        // report collection to the items the user asked for.
        String sqlStatement = "SELECT * from items WHERE date >= ? AND date <= ? AND userName = ?";
        Collection<Item> reportItems = new LinkedList<>();

        try ( Connection connection = DriverManager.getConnection(dbUrl, user, password);
              PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            // Replaces the question marks.
            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);
            preparedStatement.setString(3, userName);

            try(ResultSet myResult = preparedStatement.executeQuery()) {
                // Put all the values form the query in LinkedList.
                while (myResult.next()) {
                    //build new Item for the list.
                    Item currentItemToAdd = new Item(myResult.getInt("costNumber"), myResult.getString("name"),
                            myResult.getString("description"), myResult.getString("currency"),
                            new Category(myResult.getString("category")), myResult.getString("sum"),
                            myResult.getDate("date"), myResult.getString("userName"));

                    reportItems.add(currentItemToAdd);

                }
            }
        } catch (SQLException e) {
            throw new CostManagerException("Unable to pull data from DB",e);
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

        String sqlStatement = "insert into users (userName,password) values (?,?)";

        try ( Connection connection = DriverManager.getConnection(dbUrl, this.user, password);
              PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            Collection<User> allUsers = getAllUsers();

            // Check if the user is exists in the users sql table. If the user exists throws CostMangerException.
            if(allUsers.contains(user)) {
                throw new CostManagerException("This user is already exists");
            }

            // Replaces the question marks.
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

        String sqlStatement = "insert into categories (category) values (?)";

        try ( Connection connection = DriverManager.getConnection(dbUrl, user, password);
              PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            // if category empty

            Collection<Category> allCategories = getAllCategories();

            // If the category is not in the list of the categories add the category.
            if(!allCategories.contains(category)){

                // Replaces the question marks.
                preparedStatement.setString(1, category.getCategoryName());

                // Check that the add category was executed properly.
                int howManyAdded = preparedStatement.executeUpdate();

                // If the add category was not execute properly throw CostMangerException.
                if(howManyAdded != 1){
                    throw new CostManagerException("Unable insert the category");
                }
            }
            // if category contains.
            else{
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

        Collection<User> currentItems = new LinkedList<>();
        String sqlStatement = "SELECT * from users";

        try ( Connection connection = DriverManager.getConnection(dbUrl, user, password);
              PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
              ResultSet myResult = preparedStatement.executeQuery()) {

            // Put all the values form the query in LinkedList.
            while (myResult.next())
            {
                // Build a new user.
                currentItems.add(new User(myResult.getString("userName"),
                        myResult.getString("password")));
            }
        } catch (SQLException e) {
            throw new CostManagerException("Unable to pull data from DB",e);
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

        Collection<Category> currentItems = new LinkedList<>();
        String sqlStatement = "SELECT * from categories";

        try ( Connection connection = DriverManager.getConnection(dbUrl, user, password);
              PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
              ResultSet myResult = preparedStatement.executeQuery()) {

            // Put all the values form the query in LinkedList.
            while (myResult.next())
            {
                // build a new Category.
                currentItems.add(new Category(myResult.getString("category")));
            }

        } catch (SQLException e) {
            throw new CostManagerException("Unable to pull data from DB",e);
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

        Collection<User> currentItems = new LinkedList<>();
        String sqlStatement = "SELECT * from users";

        try ( Connection connection = DriverManager.getConnection(dbUrl, this.user, password);
              PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
              ResultSet myResult = preparedStatement.executeQuery()) {

            // Put all the values form the query in LinkedList.
            while (myResult.next())
            {
                currentItems.add(new User(myResult.getString("userName"),
                        myResult.getString("password")));
            }

            if(!currentItems.contains(user))
                throw new CostManagerException("User not Exists!");

        } catch (SQLException e) {
            throw new CostManagerException("Unable to pull data from DB",e);
        }

    }

    /**
     * getCurrencies return List of all the currencies in the cost manager application form the localhost:8080.
     * @return Return list of all currencies in the localhost:8080.
     * @throws CostManagerException A class wraps up the problems of the program.
     */
    @Override
    public List<Currency> getCurrencies() throws CostManagerException {

        try {
            List<Currency> result = new LinkedList<>();
            // Building http client.
            HttpClient client = HttpClient.newBuilder().build();
            // Building http request.
            HttpRequest request =
                    HttpRequest.
                            newBuilder().
                            uri(URI.create("http://localhost:8080")).
                            GET().
                            build();

            // getting the response from the http request.
            HttpResponse<String> response = client.send(
                    request, HttpResponse.BodyHandlers.ofString());
            // getting the text from the response body.
            String text = response.body(); // [{"symbol":"usd","rate":3.1},{"symbol":"cad","rate":3.21},{"symbol":"euro","rate":3.6}]
            // creating the json arr.
            JSONArray arrayList = new JSONArray(text);
            int numOfObjects = arrayList.length();

            // parsing the json to currencies array.
            for(int i = 0; i < numOfObjects; i++) {
                JSONObject currency = arrayList.getJSONObject(i);
                result.add(new Currency(currency.getString("symbol"), currency.getDouble("rate")));
            }

            return result;
        }
        catch(java.io.IOException | java.lang.InterruptedException e) {
            throw new CostManagerException("problem with getting currencies exchange rates",e);
        }
    }

    // This method check if the category is inside the categories list
    // if the category is inside the method do nothing.
    // and if the category is not inside she put the category inside the list.
    private void addCategoryInAddItem(Category category) throws CostManagerException {

        String sqlStatement = "insert into categories (category) values (?)";

        try ( Connection connection = DriverManager.getConnection(dbUrl, user, password);
              PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            Collection<Category> allCategories = getAllCategories();

            // If the category is not in the list of the categories add the category.
            if(!allCategories.contains(category) && !category.getCategoryName().isEmpty()){

                // Replaces the question marks.
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

    //A method that aims to update the row number after deletion.
    private void updateCostNumberAfterRemove(String userName) throws CostManagerException {

        try {
            LinkedList<Item> allItems;
            allItems = (LinkedList<Item>)getItems(userName);
            String costNumber;

            // Goes through all the expenses of a specific user, and updates their number.
            for(int i = 0 ; i < allItems.size(); i++){
                costNumber = String.valueOf(allItems.get(i).getCostNumber());
                updateItem("costNumber", String.valueOf(i+1), costNumber, userName);
            }

        }
        catch (CostManagerException e) {
            throw new CostManagerException("Problem with removing process",e);
        }
    }
}
