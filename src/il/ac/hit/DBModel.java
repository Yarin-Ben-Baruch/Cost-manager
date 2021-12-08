package il.ac.hit;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

public class DBModel implements IModel {

    private String user = "admin";
    private String password = "admin";
    private String dbUrl = "jdbc:mysql://localhost:8889/admin";
    private Connection connection;
    private Statement statement;
    private final Collection<String> categorys;

    private Collection<Item> items; // ?

    public DBModel() throws CostMangerException {

        try {
            Connection connection = DriverManager.getConnection(dbUrl, user, password);
            System.out.println("Connection success !");

            //משתנה שנותן לי לעשות פעולות על db
            Statement myStatement = connection.createStatement();

            setConnection(connection);
            setStatement(myStatement);

            //items = new LinkedList<>();
            categorys = new HashSet<>();
        }
        catch (SQLException e) {
            throw new CostMangerException("Connection failed!",e);
        }

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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into items (costName,name,description,currency,category,sum,date,userName) " +
                            "values " +
                            "(?,?,?,?,?,?,?,?)");

            preparedStatement.setInt(1,item.getCostNumber());
            preparedStatement.setString(2,item.getName());
            preparedStatement.setString(3,item.getDescribing());
            preparedStatement.setString(4,item.getCurrency());
            preparedStatement.setString(5,item.getCategory());
            preparedStatement.setString(6, item.getSum());
            preparedStatement.setDate(7, item.getDate());
            preparedStatement.setString(8, item.getUserName());


            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new CostMangerException("Unable insert into the DB",e);
        }
    }

    @Override
    public Collection<Item> getItems() throws CostMangerException {

        ResultSet myResult = null;
        Collection<Item> currentItems = new LinkedList<>();

        try {
            myResult = statement.executeQuery("SELECT * from Items");

            while (myResult.next())
            {
                currentItems.add(new Item(myResult.getInt("id"), myResult.getString("name"),
                        myResult.getString("description"),myResult.getString("currency"),
                        myResult.getString("category"),myResult.getString("sum"),
                        myResult.getDate("date")));

            }

        } catch (SQLException e) {
            throw new CostMangerException("Unable to pull data from DB");
        }

        return currentItems;
    }

    // ask haim (life) !!!!
    // לא נבדק
    @Override
    public void updateItem(String nameColToUpdate, String dataToSet, int costNumber, String userName) throws CostMangerException {

        try {
            PreparedStatement preparedStatement = null;
            Object date = dataToSet;
            StringBuffer queryToExecute = new StringBuffer();

            queryToExecute.append("UPDATE Items SET " + nameColToUpdate + " = " + dataToSet + " WHERE costNumber = "+ costNumber + " and userName = " +userName);

            preparedStatement = connection.prepareStatement(queryToExecute.toString());
            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new CostMangerException("Unable to update data to DB",e);
        }

    }

    @Override
    public void removeItem(int itemId) throws CostMangerException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM items WHERE id = ?");

            preparedStatement.setInt(1,itemId);
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

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Items WHERE date >= ? AND date <= ?");

            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);
            myResult = preparedStatement.executeQuery();

            while(myResult.next()) {

                    Item currentItemToAdd = new Item(
                            myResult.getString("name"),
                            myResult.getString("description"),myResult.getString("currency"),
                            myResult.getString("category"),myResult.getString("sum"),
                            myResult.getDate("date"));

                    reportItems.add(currentItemToAdd);

            }
        } catch (SQLException e) {
            throw new CostMangerException("Unable to pull data from DB");
        }

        return reportItems;
    }

    @Override
    public void addNewCategory(String category) throws CostMangerException {
        categorys.add(category);
    }

}
