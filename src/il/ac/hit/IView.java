package il.ac.hit;

import java.util.Collection;

public interface IView {

    void showItems(Collection<Item> i_Items);
    void showCategories(Collection<Category> i_Categories);
    void showDetailedReport();
    void setIViewModel(IViewModel i_Vm);
    void showMessage(Message i_Message);
    void init();
    void start();
}
