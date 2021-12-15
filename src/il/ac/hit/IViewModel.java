package il.ac.hit;

import java.sql.Date;

public interface IViewModel {

    void setView(IView i_View);
    void setModel(IModel i_Model);
    void addItem(Item i_Item);
    void addNewUser(User i_User);
    void addNewCategoryIfExists(Category i_Category);
    void removeItem(int i_CostNumber, String i_UserName);
    void updateItem(String i_NameColToUpdate, String i_DataToSet, int i_CostNumber, String i_UserName);
    void getDetailedReport(Date i_StartDate, Date i_EndDate);
    void getAllUsers();
    void getAllCategories();
    void getItems();
    void isUserExists(User i_User);
}
