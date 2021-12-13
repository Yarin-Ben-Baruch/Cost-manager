package il.ac.hit;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

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
     * להוסיף לכל קלאס, ומתודות
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

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public void addItem(Item item) throws CostMangerException {

        try {
            addNewCategory(item.getCategory()); // במידה והקטגוריה חדשה מוסיף לרשימת הקטגוריות

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


            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
            throw new CostMangerException("Unable insert into the DB",e);
        }
    }

    @Override
    public Collection<Item> getItems() throws CostMangerException {

        ResultSet myResult = null;
        Collection<Item> currentItems = new LinkedList<>();

        try {
            myResult = statement.executeQuery("SELECT * from items");

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

    @Override
    public void updateItem(String nameColToUpdate, String dataToSet, int costNumber, String userName) throws CostMangerException {

        try {

            // אם מנסה לשנות username לזרוק אקספשין
            if(nameColToUpdate.equals("userName")){
                throw new CostMangerException("Can't change here userName");
            }

            PreparedStatement preparedStatement = null;
            Object date = dataToSet;
            StringBuffer queryToExecute = new StringBuffer();

            // מתאים רק ל date
            if(nameColToUpdate.equals("date")){
                queryToExecute.append("UPDATE Items SET " + nameColToUpdate + " = " + dataToSet + " WHERE costNumber = "
                        + costNumber + " and userName = " + "'" + userName + "'");
            }
            else {
                // מתאים רק ל varchar
                queryToExecute.append("UPDATE Items SET " + nameColToUpdate + " = " + "'" + dataToSet + "'" + " WHERE costNumber = "
                        + costNumber + " and userName = " + "'" + userName + "'");
            }


            preparedStatement = connection.prepareStatement(queryToExecute.toString());
            int howManyUpdates = preparedStatement.executeUpdate();

           if(howManyUpdates > 1){
               throw new CostMangerException("Can't update more than one item !");
           }

        }
        catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
            throw new CostMangerException("Unable to update data to DB",e);
        }

    }

    @Override
    public void removeItem(int costNumber, String userName) throws CostMangerException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM items WHERE costNumber = ? AND userName = ?");

            preparedStatement.setInt(1,costNumber);
            preparedStatement.setString(2,userName);

            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new CostMangerException("Unable to update data to DB",e);
        }
    }

    @Override
    public Collection<Item> getDetailedReport(Date startDate, Date endDate) throws CostMangerException {
        ResultSet myResult = null;
        Collection<Item> reportItems = new LinkedList<>();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from items WHERE date >= ? AND date <= ?");

            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);
            myResult = preparedStatement.executeQuery();

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

    @Override
    public void addNewUser(User user) throws CostMangerException {
        try {

            Collection<User> allUsers = getAllUsers();

            if(allUsers.contains(user)) {
                throw new CostMangerException("This user is already exists");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into users (userName,password) " +
                            "values " +
                            "(?,?)");

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new CostMangerException("Unable insert into the DB",e);
        }
    }

    @Override
    public void addNewCategory(Category category) throws CostMangerException {
        // להחליף את השם ליותר נכון
        try {
            Collection<Category> allCategories = getAllCategories();
//            if(allCategories.contains(category)) {
//                throw new CostMangerException("This category is already exists");
//            }

            if(!allCategories.contains(category)){
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into categories (category) " +
                                "values " +
                                "(?)");

                preparedStatement.setString(1, category.getCategoryName());

                preparedStatement.executeUpdate();
                // לוודא שחזר 1, אם לא לזרוק הערה מתאימה
            }

        }
        catch (SQLException e) {
            throw new CostMangerException("Unable insert into the DB",e);
        }

    }

    @Override
    public Collection<User> getAllUsers() throws CostMangerException {

        ResultSet myResult = null;
        Collection<User> currentItems = new LinkedList<>();

        try {
            myResult = statement.executeQuery("SELECT * from users");

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

    @Override
    public Collection<Category> getAllCategories() throws CostMangerException {

        ResultSet myResult = null;
        Collection<Category> currentItems = new LinkedList<>();

        try {
            myResult = statement.executeQuery("SELECT * from categories");

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
