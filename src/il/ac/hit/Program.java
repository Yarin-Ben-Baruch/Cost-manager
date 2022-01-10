package il.ac.hit;
import il.ac.hit.model.DBModel;
import il.ac.hit.model.IModel;
import il.ac.hit.view.IView;
import il.ac.hit.view.ViewsManager;
import il.ac.hit.viewmodel.CostManagerViewModel;
import il.ac.hit.viewmodel.IViewModel;
import javax.swing.*;

public class Program {
    public static void main(String[] args) {

        IModel model;
        // get object from DBModel (Singleton).
        model = DBModel.getObject();
        IViewModel vm = new CostManagerViewModel();
        IView view = new ViewsManager();
        // Make sure you invoke this class during the execution of the AWT Event thread.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.init();
                view.start();
            }
        });
        // MVVM.
        vm.setModel(model);
        vm.setView(view);
        view.setIViewModel(vm);
    }
}
