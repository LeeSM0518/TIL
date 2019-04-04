package seminar_project.management_service;

import seminar_project.desktop.Desktop;
import seminar_project.parts.*;
import seminar_project.ui.DesktopsManagementUI;

public class DesktopsManagementService extends StocksManagementService {

    private DesktopsManagementUI desktopsManagementUI = new DesktopsManagementUI();

    private Desktop removeDesktop = null;

    public DesktopsManagementService(StocksManagementService stocksManagementService) {

        this.parts = stocksManagementService.parts;
        this.desktops = stocksManagementService.desktops;

    }

    public void inquiryDesktops() {

        desktopsManagementUI.inquiryDesktopsUI(desktops);

    }

    public void makeDesktop() {

        if (!checkAllPartCount()) {
            System.out.println("부품이 부족합니다.");
            return;
        }

        System.out.print("데스크탑의 제품명을 입력해주세요: ");
        String desktopName = desktopsManagementUI.informationInput();

        CPU cpu = (CPU) selectPart("CPU", CPU.class);
        RAM ram = (RAM) selectPart("RAM", RAM.class);
        GraphicCard graphicCard = (GraphicCard) selectPart("GraphicCard", GraphicCard.class);

        int sumPrice = cpu.getPrice() + ram.getPrice() + graphicCard.getPrice();

        desktops.add(new Desktop(desktopName, cpu, ram, graphicCard, sumPrice));

        deletePart(cpu.getProductName(), CPU.class);
        deletePart(ram.getProductName(), RAM.class);
        deletePart(graphicCard.getProductName(), GraphicCard.class);

    }

    private Part selectPart(String partKind, Class partClass) {

        Part part;

        inquiryParts(partKind);
        System.out.println();

        do {
            part = searchPart(desktopsManagementUI.inputPartNameUI(), partClass);
        } while (part == null);

        return part;

    }

    public void sale() {

        if (desktops.size() == 0) {
            System.out.println("재고가 없습니다.");
            return;
        }

        inquiryDesktops();

        String desktopName = desktopsManagementUI.saleDesktopUI();

        desktops.forEach(desktop -> {
            if (desktop.getDesktopName().equals(desktopName)) {
                removeDesktop = desktop;
            }
        });

        if (removeDesktop == null) {
            System.out.println("없는 데스크탑 입니다.");
        } else {
            desktops.remove(removeDesktop);
            removeDesktop = null;
        }

    }

}