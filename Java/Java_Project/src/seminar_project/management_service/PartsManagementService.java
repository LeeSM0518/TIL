package seminar_project.management_service;

import seminar_project.parts.CPU;
import seminar_project.parts.GraphicCard;
import seminar_project.parts.RAM;
import seminar_project.parts.Parts;

import java.util.*;
import java.util.stream.Stream;

import static seminar_project.management_service.UIService.inquiryPartsUI;

public class PartsManagementService implements StockManagement {

    final int SUCCESS = 1;
    final int ERROR = -1;

    private List<Parts> parts = new LinkedList<>();

    public void inquiryPart(String partClass) {
        parts.stream()
                .forEach(part -> {
                    if (partClass.equals("CPU") &&
                            part.getClass() == CPU.class) {
                        inquiryPartsUI(part);
                    } else if (partClass.equals("RAM") &&
                            part.getClass() == RAM.class) {
                        inquiryPartsUI(part);
                    } else {
                        inquiryPartsUI(part);
                    }
                });
    }

    @Override
    public void inquiry() {
        System.out.println("==============================");
        System.out.println("CPU 재고");
        inquiryPart("CPU");
        System.out.println("RAM 재고");
        inquiryPart("RAM");
        System.out.println("Graphic Card 재고");
        inquiryPart("GraphicCard");
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
