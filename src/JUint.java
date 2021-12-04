import java.util.Collection;

public class JUint {

    public static void main(String[] args) {

        try {

            DBModel test = new DBModel();
            Collection<Item> item;

            item = test.getItems();

            for(Item s : item)
            {
                System.out.println(s);
            }

        } catch (CostMangerException e) {
            e.printStackTrace();
        }

        //java.sql.Date date = new java.sql.Date(0000-00-00);
        //date = java.sql.Date.valueOf("2013-09-04");
        //System.out.println(new java.sql.Date(System.currentTimeMillis()));

//            Item currentItemToAdd = new Item(
//                    "DaniGlafend",
//                    "aksfhksdhfjksd","USD",
//                    "House","200M", java.sql.Date.valueOf("2017-09-04"));
//            test.addItem(currentItemToAdd);
//            test.getItems();

//            item = test.getDetailedReport(java.sql.Date.valueOf("2016-09-04"), new java.sql.Date(System.currentTimeMillis()));

    }
}
