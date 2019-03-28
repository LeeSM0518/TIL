package seminar_project.management_service;

import java.util.List;

public interface StockManagement {

    public <T> void inquiry(List<T> list);

    public <T> void purchase(List<T> list);

    public <T> void sale(List<T> list);
}
