package il.ac.hit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBModelTest {

    private DBModel test;

    @BeforeEach
    void setUp() {

        try {
            test = new DBModel();
        } catch (CostMangerException e) {
            e.printStackTrace();
        }

    }

    @AfterEach
    void tearDown() {
        test = null;

        //assertEquals();
    }

    @Test
    void addItem() {
        try {
            test.addItem(new Item(5, "Dani",
                    "buy new car","nis",
                    new Category("Car"),"1000",
                    java.sql.Date.valueOf("2015-09-09"), "Danoy"));

            Collection<Item> report = test.getDetailedReport(java.sql.Date.valueOf("2015-08-08"), java.sql.Date.valueOf("2015-09-09"));
            LinkedList<Item> items = (LinkedList<Item>) report;

            Item item = new Item(3, "Dani",
                    "buy new car","nis",
                    new Category("Car"),"1000",
                    java.sql.Date.valueOf("2015-09-09"), "Danoy");

            assertEquals(item,items.get(0));

        } catch (CostMangerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getItems() {
        try {
            Collection<Item> allItems = test.getItems();
            Collection<Item> allItems2 = test.getItems();

            assertEquals(allItems,allItems2);

        } catch (CostMangerException e) {
            e.printStackTrace();
        }

    }

    @Test
    void updateItem() {
        try {
            test.updateItem("name", "Yarin", 3, "Danoy");
            Collection<Item> allItems = test.getItems();
            Item searchedItem;

            for (Item item : allItems) {
                if(item.getUserName() == "Danoy" && item.getCostNumber() == 3) {
                    searchedItem = item;
                    assertEquals(item,searchedItem);
                    break;
                }
            }

        } catch (CostMangerException e) {
            e.printStackTrace();
        }

    }

    @Test
    void removeItem() {
        try {
            Collection<Item> allItemsBeforeEdit = test.getItems();

            Item item =  new Item(99, "Dani",
                    "buy new car","nis",
                    new Category("Car"),"1000",
                    java.sql.Date.valueOf("2013-09-04"), "Danoy");

            test.addItem(item);

            test.removeItem(99, "Danoy");

            Collection<Item> allItemsAfterAddAndRemove = test.getItems();

            assertEquals(allItemsBeforeEdit,allItemsAfterAddAndRemove);


        } catch (CostMangerException e) {
            e.printStackTrace();
        }
    }


}