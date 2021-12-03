import java.sql.*;
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
//        try {
//            int numberRowAdding = statement.executeQuery(
//                    "insert into items" +
//                            "(id,name,description,currency,category,sum,date) " +
//                            "values " +
//                            "(??????)");
//        }
//        catch (SQLException e) {
//            throw new CostMangerException("Unable insert into the DB",e);
//        }
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

    @Override
    public void updateItem(String nameColToUpdate, String dataToSet, int itemId) throws CostMangerException {

        try {
            int numberRowAffected = statement.executeUpdate(
                    "UPDATE items" +
                            "set {nameColToUpdate} = {dataToSet}" +
                            "WHERE id = {itemId}");
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
    public Collection<Item> getDetailedReport(Item[] item, String date) throws CostMangerException {
        return null;
    }

    @Override
    public void addNewCategory(Category category) throws CostMangerException {

    }



    public static void main(String[] args){

        try {
            DBModel test = new DBModel();
            test.getItems();
            test.removeItem(3);
            test.getItems();
        } catch (CostMangerException e) {
            e.printStackTrace();
        }
    }

}
