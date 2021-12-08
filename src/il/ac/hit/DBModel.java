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
                            "(?,?,?,?,?,?)");

            preparedStatement.setString(1,item.getCostName());
            preparedStatement.setString(2,item.getName());
            preparedStatement.setString(3,item.getDescription());
            preparedStatement.setString(4,item.getCurrency());
            preparedStatement.setString(5,item.getCategory());
            preparedStatement.setDate(6, item.getSum());
            preparedStatement.setDate(7, item.getDate());
            preparedStatement.setDate(8, item.getUserName());


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
    public void updateItem(String nameColToUpdate, String dataToSet, int itemId) throws CostMangerException {

        try {
            PreparedStatement preparedStatement = null;
            Object date = dataToSet;

            switch (nameColToUpdate)
            {
                case "name" :
                    preparedStatement = connection.prepareStatement("UPDATE Items SET name = ? WHERE id = ?");
                    break;
                case "description" :
                    preparedStatement = connection.prepareStatement("UPDATE Items SET description = ? WHERE id = ?");
                    break;
                case "currency" :
                    preparedStatement = connection.prepareStatement("UPDATE Items SET currency = ? WHERE id = ?");
                    break;
                case "category" :
                    preparedStatement = connection.prepareStatement("UPDATE Items SET category = ? WHERE id = ?");
                    break;
                case "sum" :
                    preparedStatement = connection.prepareStatement("UPDATE Items SET sum = ? WHERE id = ?");
                    break;
                case "date" :
                    preparedStatement = connection.prepareStatement("UPDATE Items SET date = ? WHERE id = ?");
                    date = java.sql.Date.valueOf(dataToSet);
                    break;

            }

            //preparedStatement.setObject(1,nameColToUpdate); // אי אפשר לעדכן עמודה לפי ערך שנותנים רק ערך ספציפי
            preparedStatement.setObject(1,date); // יכולה להיות בעיה כי לא יודעים איזה סוג משתנה זה
            preparedStatement.setInt(2,itemId);

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
