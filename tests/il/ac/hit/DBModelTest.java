package il.ac.hit;
import il.ac.hit.model.CostManagerException;
import il.ac.hit.model.Category;
import il.ac.hit.model.DBModel;
import il.ac.hit.model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

class DBModelTest {

    private DBModel test;

    @BeforeEach
    void setUp() {
        test = DBModel.getObject();
    }

    @AfterEach
    void tearDown() {
        test = null;
    }

    @Test
    void addItem() {
        try {
            test.addItem(new Item(5, "Dani",
                    "buy new car","nis",
                    new Category("Car"),"1000",
                    java.sql.Date.valueOf("2015-09-09"), "Danoy"));

            Collection<Item> report = test.getDetailedReport(java.sql.Date.valueOf("2015-08-08"),
                    java.sql.Date.valueOf("2015-09-09"),"Danoy");
            LinkedList<Item> items = (LinkedList<Item>) report;

            Item item = new Item(3, "Dani",
                    "buy new car","nis",
                    new Category("Car"),"1000",
                    java.sql.Date.valueOf("2015-09-09"), "Danoy");


            assertEquals(item,items.get(0));

        } catch (CostManagerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getItems() {
        try {
            Collection<Item> allItems = test.getItems("Danoy");
            Collection<Item> allItems2 = test.getItems("Danoy");


            assertEquals(allItems,allItems2);

        } catch (CostManagerException e) {
            e.printStackTrace();
        }

    }

    @Test
    void updateItem() {
        try {
            test.updateItem("name", "Yarin", "3", "Danoy");
            Collection<Item> allItems = test.getItems("Danoy");
            Item searchedItem;

            for (Item item : allItems) {
                if(item.getUserName() == "Danoy" && item.getCostNumber() == 3) {
                    searchedItem = item;
                    assertEquals(item,searchedItem);
                    break;
                }
            }

        } catch (CostManagerException e) {
            e.printStackTrace();
        }

    }

    @Test
    void removeItem() {
        try {
            Collection<Item> allItemsBeforeEdit = test.getItems("Danoy");

            Item item =  new Item(99, "Dani",
                    "buy new car","nis",
                    new Category("Car"),"1000",
                    java.sql.Date.valueOf("2013-09-04"), "Danoy");

            test.addItem(item);

            test.removeItem("99", "Danoy");

            Collection<Item> allItemsAfterAddAndRemove = test.getItems("Danoy");


            assertEquals(allItemsBeforeEdit,allItemsAfterAddAndRemove);

        } catch (CostManagerException e) {
            e.printStackTrace();
        }
    }
}