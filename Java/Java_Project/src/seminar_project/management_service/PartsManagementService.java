package seminar_project.management_service;

import seminar_project.parts.CPU;
import seminar_project.parts.GraphicCard;
import seminar_project.parts.Part;
import seminar_project.parts.RAM;

import java.util.*;

import static seminar_project.management_service.UIService.inquiryPart;

class PartsManagementService {

    // TODO 부품을 선택할 때 선택한 번호와 매칭시키기 위해 상수 선언
    private final int CPU_NUM = 1;
    private final int RAM_NUM = 2;
    private final int GRAPHIC_CARD_NUM = 3;

    // TODO 부품들을 저장할 연결리스트 선언
    private List<Part> parts = new LinkedList<>();

    // TODO 스트림을 이용해서 부품을 삭제할 때 필요한 변수 선언
    private Part removePart = null;

    // TODO inquiry() 에서 부품을 String 으로 받고 그 부품을 Stream 을 사용해서 출력
    public void inquiryParts(final String partClass) {

        parts.forEach(part -> {
            if (partClass.equals("CPU") && part.getClass() == CPU.class) {
                inquiryPart(part);
            } else if (partClass.equals("RAM") && part.getClass() == RAM.class) {
                inquiryPart(part);
            } else if (partClass.equals("GraphicCard") && part.getClass() == GraphicCard.class) {
                inquiryPart(part);
            }
        });

    }

    // TODO 부품들이 존재하는지 확인하는 메소드
    public boolean checkPartsList() {

        int cpuCount = (int) parts.stream().filter(part -> part.getClass() == CPU.class).count();
        int ramCount = (int) parts.stream().filter(part -> part.getClass() == RAM.class).count();
        int graphicCount = (int) parts.stream().filter(part -> part.getClass() == GraphicCard.class).count();

        if (cpuCount > 0 && ramCount > 0 && graphicCount > 0) return true;
        else return false;
    }

    void purchase(final int part, final String partName, final String partPerformance, final int partPrice) {

        switch (part) {
            case CPU_NUM:
                parts.add(new CPU(partName, partPerformance, partPrice));
                break;

            case RAM_NUM:
                parts.add(new RAM(partName, partPerformance, partPrice));
                break;

            case GRAPHIC_CARD_NUM:
                parts.add(new GraphicCard(partName, partPerformance, partPrice));
                break;

            default:
                System.out.println();
        }

    }

    void sale(final int partSelect, final String partName) {

        switch (partSelect) {
            case CPU_NUM:
                deletePart(partName, CPU.class);
                break;
            case RAM_NUM:
                deletePart(partName, RAM.class);
                break;
            case GRAPHIC_CARD_NUM:
                deletePart(partName, GraphicCard.class);
                break;
        }

    }

    private void deletePart(final String partName, final Class partClass) {

        parts.forEach(part -> {
            if (partName.equals(part.getProductName()) &&
                    part.getClass() == partClass) {
                removePart = part;
            }
        });

        if (removePart == null) {
            System.out.println("없는 부품 입니다.");
        } else {
            parts.remove(removePart);
            removePart = null;
        }

    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
