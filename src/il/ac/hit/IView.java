package il.ac.hit;

import java.util.Collection;

public interface IView {

    void showItems(Collection<Item> items);
    void showCategories(Collection<Category> categories);
    void showDetailedReport();
    void setIViewModel(IViewModel vm);
    void init();
    void start();
}
