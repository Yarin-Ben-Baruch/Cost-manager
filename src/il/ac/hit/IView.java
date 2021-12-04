package il.ac.hit;

import java.util.Collection;

public interface IView {

    public void showItems(Collection<Item> items);
    //public void showMessage(Message message);
    public void setIViewModel(IViewModel vm);
    public void init();
    public void start();

}
