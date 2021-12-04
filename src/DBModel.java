import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PrimitiveIterator;

public class DBModel implements IModel {

    private String user = "admin";
    private String password = "admin";
    private String dbUrl = "jdbc:mysql://localhost:8889/admin";
    private Connection connection;
    private Statement statement;
    private Collection<Item> items;

    public DBModel() throws CostMangerException {

        try {
            Connection connection = DriverManager.getConnection(dbUrl, user, password);
            System.out.println("Connection success !");
            //משתנה שנותן לי לעשות פעולות על db
            Statement myStatement = connection.createStatement();

            setConnection(connection);
            setStatement(myStatement);

            items = new LinkedList<>();
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
                    "insert into items (name,description,currency,category,sum,date) " +
                            "values " +
                            "(?,?,?,?,?,?)");

            preparedStatement.setString(1,item.getName());
            preparedStatement.setString(2,item.getDescribing());
            preparedStatement.setString(3,item.getCurrency());
            preparedStatement.setString(4,item.getCategory());
            preparedStatement.setString(5,item.getSum());
            preparedStatement.setDate(6, item.getDate());

            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new CostMangerException("Unable insert into the DB",e);
        }
    }

    @Override
    public Collection<Item> getItems() throws CostMangerException {

        // שאילתא שמחזירה את כל הטבלה
        ResultSet myResult = null;
        try {
            myResult = statement.executeQuery("SELECT * from Items");

            while (myResult.next())
            {
                System.out.println(myResult.getString("id"));
                System.out.println(myResult.getString("name"));
                System.out.println(myResult.getString("description"));
                System.out.println(myResult.getString("currency"));
                System.out.println(myResult.getString("category"));
                System.out.println(myResult.getString("sum"));
                System.out.println(myResult.getString("date"));
            }

        } catch (SQLException e) {
            throw new CostMangerException("Unable to pull data from DB");
        }

        return items;
    }

    // update only names !!!!
    @Override
    public void updateItem(String nameColToUpdate, String dataToSet, int itemId) throws CostMangerException {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Items SET name = ? WHERE id = ?");  // מעדכן רק שמות עושה בעיה איפה שהשם

            //preparedStatement.setObject(1,nameColToUpdate); // אי אפשר לעדכן עמודה לפי ערך שנותנים רק ערך ספציפי
            preparedStatement.setObject(1,dataToSet); // יכולה להיות בעיה כי לא יודעים איזה סוג משתנה זה
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
    public void addNewCategory(Category category) throws CostMangerException {

    }


    public static void main(String[] args){

        try {

            DBModel test = new DBModel();
            Collection<Item> item;

            test.getItems();
            Item currentItemToAdd = new Item(
                    "DaniGlafend",
                    "aksfhksdhfjksd","USD",
                    "House","200M", java.sql.Date.valueOf("2017-09-04"));

            test.addItem(currentItemToAdd);
            test.getItems();

            item = test.getDetailedReport(java.sql.Date.valueOf("2016-09-04"), new java.sql.Date(System.currentTimeMillis()));

            for(Item s : item)
            {
                System.out.println(s);
            }

        } catch (CostMangerException e) {
            e.printStackTrace();
        }
    }

}