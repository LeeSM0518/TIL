package seminar_project.management_service;

import seminar_project.parts.CPU;
import seminar_project.parts.GraphicCard;
import seminar_project.parts.RAM;
import seminar_project.parts.Parts;

import java.util.*;

import static seminar_project.management_service.UIService.inquiryPartsUI;

public class PartsManagementService implements StockManagement {

    final static  int SUCCESS = 1;
    final static int ERROR = -1;

    private List<Parts> parts = new LinkedList<>();
    private static Parts removePart = null;

    public void inquiryPart(final String partClass) {
        parts.stream()
                .forEach(part -> {
                    if (partClass.equals("CPU") &&
                            part.getClass() == CPU.class) {
                        inquiryPartsUI(part);
                    } else if (partClass.equals("RAM") &&
                            part.getClass() == RAM.class) {
                        inquiryPartsUI(part);
                    } else if (partClass.equals("GraphicCard") &&
                            part.getClass() == GraphicCard.class) {
                        inquiryPartsUI(part);
                    }
                });
    }

    @Override
    public void inquiry() {
        System.out.println("CPU 재고");
        inquiryPart("CPU");
        System.out.println("RAM 재고");
        inquiryPart("RAM");
        System.out.println("Graphic Card 재고");
        inquiryPart("GraphicCard");
    }

    public int purchase (final int part, final String partName, final String partPerformance, final int partPrice) {
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

    public void sale (final int partSelect, final String partName) {

        switch (partSelect) {
            case CPU_NUM:
                searchPart(partName, CPU.class);
                break;
            case RAM_NUM:
                searchPart(partName, RAM.class);
                break;
            case GRAPHIC_CARD_NUM:
                searchPart(partName, GraphicCard.class);
                break;
        }
    }

    public void searchPart (final String partName, final Class partClass) {
        parts.stream()
                .forEach(part -> {
                    if (partName.equals(part.getProductName()) &&
                            part.getClass() == partClass) {
                        removePart = part;
                    }
                });

        if (removePart == null) {
            System.out.println("없는 부품 입니다.");
        } else {
            System.out.println("");
        }
    }


}
