package seminar_project.management_service;

import seminar_project.desktop.Desktop;
import seminar_project.parts.*;
import seminar_project.ui.StocksManagementUI;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StocksManagementService {

    private StocksManagementUI stocksManagementUI = new StocksManagementUI();

    final int CPU_NUM = 1;
    final int RAM_NUM = 2;
    final int GRAPHIC_CARD_NUM = 3;

    List<Part> parts = new LinkedList<>();
    List<Desktop> desktops = new LinkedList<>();

    private Part searchedPart = null;

    public void inquiryParts(final String partClass) {

        switch (partClass) {

            case "CPU" :
                System.out.println("CPU 재고");
                break;

            case "RAM" :
                System.out.println("RAM 재고");
                break;

            case "GraphicCard" :
                System.out.println("Graphic Card 재고");
                break;

        }

        parts.forEach(part -> {
            if (partClass.equals("CPU") && part.getClass() == CPU.class) {
                stocksManagementUI.inquiryPart(part);
            } else if (partClass.equals("RAM") && part.getClass() == RAM.class) {
                stocksManagementUI.inquiryPart(part);
            } else if (partClass.equals("GraphicCard") && part.getClass() == GraphicCard.class) {
                stocksManagementUI.inquiryPart(part);
            }
        });

    }

    Part searchPart(final String partName, final Class partClass) {

        parts.forEach(part -> {
            if (partName.equals(part.getProductName()) &&
                    part.getClass() == partClass) {
                searchedPart = part;
            }
        });

        if (searchedPart == null) {
            System.out.println("없는 부품입니다.");
        }

        return searchedPart;

    }

    <T> void deletePart(final String name, final T t) {

        searchPart(name, (Class) t);

        if (searchedPart != null) {
            parts.remove(searchedPart);
            searchedPart = null;
        }

    }

    <T> void addPart(final T t, final Map<String ,String> partInformation) {

        Part part = (Part) t;
        part.createPart(partInformation, part);
        parts.add(part);

    }

    // TODO 부품들의 개수를 확인하는 메소드
    boolean checkAllPartCount() {

        int cpuCount = partCount(CPU.class);
        int ramCount = partCount(RAM.class);
        int graphicCount = partCount(GraphicCard.class);

        return cpuCount > 0 && ramCount > 0 && graphicCount > 0;

    }

    private <T> int partCount(final T t) {

        return (int) parts.stream().filter(part -> part.getClass() == t).count();

    }

}
