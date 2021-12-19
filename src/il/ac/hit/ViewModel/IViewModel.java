package il.ac.hit.ViewModel;

import il.ac.hit.Model.Category;
import il.ac.hit.Model.IModel;
import il.ac.hit.Model.Item;
import il.ac.hit.Model.User;
import il.ac.hit.View.IView;

import java.sql.Date;

public interface IViewModel {

    void setView(IView i_View);
    void setModel(IModel i_Model);
    void addItem(Item i_Item);
    void addNewUser(User i_User);
    void addNewCategoryIfExists(Category i_Category);
    void removeItem(String i_CostNumber, String i_UserName);
    void updateItem(String i_NameColToUpdate, String i_DataToSet, String i_CostNumber, String i_UserName);
    void getDetailedReport(Date i_StartDate, Date i_EndDate);
    void getAllUsers();
    void getAllCategories();
    void getItems(String i_Username);
    void isUserExists(User i_User);
}
