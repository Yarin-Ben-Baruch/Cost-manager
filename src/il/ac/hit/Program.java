package il.ac.hit;

import java.util.Collection;


public class Program {
    public static void main(String[] args) {

        try {

            DBModel test = new DBModel();

            test.addItem(new Item(3, "Dani",
                    "buy new car","nis",
                    new Category("Car"),"1000",
                    java.sql.Date.valueOf("2013-09-04"), "Danoy"));
            test.removeItem(3, "Danoy");
            test.updateItem("name", "Yarin", 3, "Danoy");
            Collection<Item> allItems = test.getItems();
            Collection<Item> report = test.getDetailedReport(java.sql.Date.valueOf("2014-09-04"), java.sql.Date.valueOf("2020-09-04"));


            for(Item item : allItems){
                System.out.println(item);
            }



            test.addNewUser(new User("Danoy","1234"));
            test.addNewUser(new User("matan","12345"));
            Collection<User> allUsers = test.getAllUsers();

            for(User user : allUsers){
                System.out.println(user);
            }



            test.addNewCategoryIfExists(new Category("House"));
            test.addNewCategoryIfExists(new Category("Car"));

            Collection<Category> allCategories = test.getAllCategories();

            for(Category category : allCategories){
                System.out.println(category);
            }
            

        } catch (CostMangerException e) {
            e.printStackTrace();
        }

    }
}
