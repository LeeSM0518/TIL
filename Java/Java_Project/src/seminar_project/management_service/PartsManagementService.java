package seminar_project.management_service;

import seminar_project.parts.CPU;
import seminar_project.parts.GraphicCard;
import seminar_project.parts.RAM;
import seminar_project.parts.Parts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PartsManagementService implements StockManagement {

    final int SUCCESS = 1;
    final int ERROR = -1;

    List<Parts> parts = new ArrayList<Parts>();

    @Override
    public void inquiry() {
        Stream<Parts> partsStream = parts.stream();


    }

    public int purchase(int part, String partName, String partPerformance, int partPrice) {
        switch (part) {
            case StockManagement.CPU_NUM:
                parts.add(new CPU(partName, partPerformance, partPrice));
                break;

            case StockManagement.RAM_NUM:
                parts.add(new RAM(partName, partPerformance, partPrice));
                break;

            case StockManagement.GRAPHIC_CARD_NUM:
                parts.add(new GraphicCard(partName, partPerformance, partPrice));
                break;

            default:
                System.out.println();
                return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public <T> void sale(List<T> list) {

    }

}
