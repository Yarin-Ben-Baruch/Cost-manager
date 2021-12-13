package il.ac.hit;

import java.sql.Date;

public interface IViewModel {

    void setView(IView view);
    void setModel(IModel model);
    void addItem(Item item);
    void addNewUser(User user);
    void addNewCategoryIfExists(Category category);
    void removeItem(int costNumber, String userName);
    void updateItem(String nameColToUpdate, String dataToSet, int costNumber, String userName);
    void getDetailedReport(Date startDate, Date endDate);
    void getAllUsers();
    void getAllCategories();
    void getItems();
}
