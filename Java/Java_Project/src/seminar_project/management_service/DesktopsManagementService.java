package seminar_project.management_service;

import seminar_project.desktop.Desktop;
import seminar_project.parts.*;
import seminar_project.ui.DesktopsManagementUI;
import seminar_project.ui.PartsManagementUI;

import java.util.*;

public class DesktopsManagementService extends StocksManagementService {

    private Scanner scanner = new Scanner(System.in);
    private PartsManagementUI partsManagementUI = new PartsManagementUI();
    private DesktopsManagementUI desktopsManagementUI = new DesktopsManagementUI();

    public DesktopsManagementService(StocksManagementService stocksManagementService) {
        this.parts = stocksManagementService.parts;
        this.desktops = stocksManagementService.desktops;
    }

    public void inquiryDesktops() {

        System.out.println(desktops.size());

    }

    public void makeDesktop() {

        CPU cpu;
        RAM ram;
        GraphicCard graphicCard;

        if (!checkAllPartCount()) {
            System.out.println("부품이 부족합니다.");
            return;
        }

        inquiryParts("CPU");
        System.out.println();

        while (true) {
            cpu = (CPU) searchPart(partsManagementUI.inputPartNameUI(), CPU.class);
            if (cpu != null) break;
        }

        inquiryParts("RAM");
        System.out.println();

        while (true) {
            ram = (RAM) searchPart(partsManagementUI.inputPartNameUI(), RAM.class);
            if (ram != null) break;
        }

        inquiryParts("GraphicCard");
        System.out.println();

        while (true) {
            graphicCard = (GraphicCard) searchPart(partsManagementUI.inputPartNameUI(), GraphicCard.class);
            if (graphicCard != null) break;
        }

        int sumPrice = cpu.getPrice() + ram.getPrice() + graphicCard.getPrice();

        desktops.add(new Desktop(cpu, ram, graphicCard, sumPrice));

        deletePart(cpu.getProductName(), CPU.class);
        deletePart(ram.getProductName(), RAM.class);
        deletePart(graphicCard.getProductName(), GraphicCard.class);

    }



    public <T> void sale(List<T> list) {

    }

}
