import java.util.Collection;

public interface IModel {
    public void addItem(Item item) throws CostMangerException;
    public Collection<Item> getDetailedReport(Item[] item,String date) throws CostMangerException;
    public void addNewCategory(Category category) throws CostMangerException;
}
