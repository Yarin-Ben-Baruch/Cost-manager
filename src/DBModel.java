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

        try (Connection connection = DriverManager.getConnection(dbUrl, user, password)) {
            System.out.println("Connection success !");
            //משתנה שנותן לי לעשות פעולות על db
            Statement myStatement = connection.createStatement();

            setConnection(connection);
            setStatement(myStatement);

            items = new LinkedList<>();
        }
        catch (SQLException e) {
            throw new CostMangerException("Connection field!",e);
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
            int numberRowAdding = statement.executeQuery(
                    "insert into items" +
                            "(id,name,description,currency,category,sum,date) " +
                            "values " +
                            "(??????)");
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
            myResult = statement.executeQuery("select * from Items");
            while (myResult.next())
            {
                System.out.println(myResult.getString("id") + myResult.getString("name")
                        + myResult.getString("description") + myResult.getString("currency")
                        +myResult.getString("category") + myResult.getString("sum" + myResult.getString("date"));
            }

        } catch (SQLException e) {
            throw new CostMangerException("Unable to pull data from DB");
        }

        return items;
    }

    @Override
    public void updateItem(String nameColToUpdate, String dataToSet, int itemId) throws CostMangerException {

        try {
            int numberRowUpdate= statement.executeUpdate(
                    "update items" +
                            "set {nameColToUpdate} = {dataToSet} " +
                            "where id = {itemId}");
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
        } catch (CostMangerException e) {
            e.printStackTrace();
        }
    }

}
