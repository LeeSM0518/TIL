package seminar_project.management_service;

import seminar_project.desktop.Desktop;
import seminar_project.parts.*;
import seminar_project.ui.PartsManagementUI;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StocksManagementService {

    private PartsManagementUI partsManagementUi = new PartsManagementUI();

    // TODO 부품을 선택할 때 선택한 번호와 매칭시키기 위해 상수 선언
    final int CPU_NUM = 1;
    final int RAM_NUM = 2;
    final int GRAPHIC_CARD_NUM = 3;

    // TODO 부품들을 저장할 연결리스트 선언
    List<Part> parts = new LinkedList<>();
    List<Desktop> desktops = new LinkedList<>();

    // TODO 스트림을 이용해서 부품을 삭제할 때 필요한 변수 선언
    private Part searchedPart = null;

    // TODO inquiry() 에서 부품을 String 으로 받고 그 부품을 Stream 을 사용해서 출력
    public void inquiryParts(final String partClass) {

        parts.forEach(part -> {
            if (partClass.equals("CPU") && part.getClass() == CPU.class) {
                partsManagementUi.inquiryPart(part);
            } else if (partClass.equals("RAM") && part.getClass() == RAM.class) {
                partsManagementUi.inquiryPart(part);
            } else if (partClass.equals("GraphicCard") && part.getClass() == GraphicCard.class) {
                partsManagementUi.inquiryPart(part);
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

    void deleteSearchedPart() {

        if (searchedPart != null) {
            parts.remove(searchedPart);
            searchedPart = null;
        }

    }

    <T> void deletePart(String name, T t) {

        searchPart(name, (Class) t);
        deleteSearchedPart();

    }

    <T> void addPart(T t, Map<String ,String> partInformation) {

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
