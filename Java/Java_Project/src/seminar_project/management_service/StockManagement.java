package seminar_project.management_service;

import java.util.List;

public interface StockManagement {

    final static int CPU_NUM = 1;
    final static int RAM_NUM = 2;
    final static int GRAPHIC_CARD_NUM = 3;

    public void inquiry();

    public <T> void sale(List<T> list);
}
