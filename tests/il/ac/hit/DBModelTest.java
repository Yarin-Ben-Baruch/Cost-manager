package il.ac.hit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DBModelTest {

    private DBModel test;

    @BeforeEach
    void setUp() {

        try {
            test = new DBModel();

            Collection<Item> item;
            Collection<Item> item2;
            Collection<Item> item3;
            Collection<Item> item4;

            test.addNewCategory("House");
            test.addNewCategory("house");
            test.addNewCategory("car");
            test.addNewCategory("Car");

            item = test.getItems();

            test.updateItem("Yarin","dataToSet", 100);
            test.updateItem("21323","dataToSet2", 80);
            test.updateItem("dsfdasf23","dataToSet", 70);
            test.updateItem("Name","dataToSet", 60);

            test.removeItem(1);
            test.removeItem(-1);
            test.removeItem(139);
            test.removeItem(-50);

            item = test.getDetailedReport(java.sql.Date.valueOf("fdsfds-09-04"), new java.sql.Date(System.currentTimeMillis()));
            item2 = test.getDetailedReport(java.sql.Date.valueOf("gfdgdgsdfg"), new java.sql.Date(System.currentTimeMillis()));
            item3 = test.getDetailedReport(java.sql.Date.valueOf("2016-09-fsdfsd"), new java.sql.Date(System.currentTimeMillis()));
            item4 = test.getDetailedReport(java.sql.Date.valueOf("2016-fsdsfdsf-04"), new java.sql.Date(System.currentTimeMillis()));


            for(Item s : item)
            {
                System.out.println(s);
            }

        } catch (CostMangerException e) {
            e.printStackTrace();
        }

    }

    @AfterEach
    void tearDown() {
        test = null;
    }

    @Test
    void addItem() {
        //???????????
        try {
            test.addItem(new Item(
                    "Matan",
                    "buying new TV","USD",
                    "House","1000", new java.sql.Date(System.currentTimeMillis())));

            Item expected = new Item(
                    "Matan",
                    "buying new TV","USD",
                    "House","1000", new java.sql.Date(System.currentTimeMillis()));


        } catch (CostMangerException e) {
            e.printStackTrace();
        }

    }

    @Test
    void getItems() {

    }

    @Test
    void updateItem() {
    }

    @Test
    void removeItem() {
    }

    @Test
    void getDetailedReport() {
    }

}