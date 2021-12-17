package il.ac.hit.View;

import il.ac.hit.*;
import il.ac.hit.Model.Category;
import il.ac.hit.Model.Item;
import il.ac.hit.Model.User;
import il.ac.hit.ViewModel.IViewModel;

import java.util.Collection;

public interface IView {

    void showItems(Collection<Item> i_Items);
    void ShowCategories(Collection<Category> i_Categories);
    void setUsers(Collection<User> i_Users);
    void setIViewModel(IViewModel i_Vm);
    void showMessage(Message i_Message);
    void init();
    void start();
    void openApplicationFromLogin();
    void registerSucceeded();
    void showInvalidInputInLogin();
    void showInvalidInputInRegister();
}
