package il.ac.hit;

import java.util.Collection;

public class Program {
    public static void main(String[] args) {

        try {

            DBModel test = new DBModel();
            Collection<Item> item;

            test.addItem(new Item(
                    "Matan",
                    "buying new TV","USD",
                    "House","1000", new java.sql.Date(System.currentTimeMillis())));

            test.addItem(new Item(
                    "",
                    "buying new House","NIS",
                    "House","100000", java.sql.Date.valueOf("2013-09-04")));


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

            for(Item s : item)
            {
                System.out.println(s);
            }

        } catch (CostMangerException e) {
            e.printStackTrace();
        }

    }
}
