package il.ac.hit;

import java.util.Collection;

public interface IView {

    void showItems(Collection<Item> i_Items);
    void ShowCategories(Collection<Category> i_Categories);
    void setUsers(Collection<User> i_Users);
    void setIViewModel(IViewModel i_Vm);
    void showMessage(Message i_Message);
    void showInvalidInput();
    void init();
    void start();
    void openApplicationFromLogin();
    void registerSucceeded();
}
