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
        model = new DBModel();
        IViewModel vm = new CostManagerViewModel();
        IView view = new ViewsManager();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.init();
                view.start();
            }
        });
        vm.setModel(model);
        vm.setView(view);
        view.setIViewModel(vm);
    }
}
