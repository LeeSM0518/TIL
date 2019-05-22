package seminar_project.management_service;

import seminar_project.parts.*;
import seminar_project.ui.*;

import java.util.Map;

public class PartsManagementService extends StocksManagementService {

    private PartsManagementUI partsManagementUI = new PartsManagementUI();

    public PartsManagementService(StocksManagementService stocksManagementService) {
        this.parts = stocksManagementService.parts;
        this.desktops = stocksManagementService.desktops;
    }

    public void purchase() {

        partsManagementUI.purchasePartsUI();

        Map<String ,String> partInformation = partsManagementUI.partInformation();

        switch (new Integer(partInformation.get("select"))) {
            case CPU_NUM:
                CPU cpu = new CPU();
                addPart(cpu, partInformation);
                break;

            case RAM_NUM:
                Part ram = new RAM();
                addPart(ram, partInformation);
                break;

            case GRAPHIC_CARD_NUM:
                Part graphicCard = new GraphicCard();
                addPart(graphicCard, partInformation);
                break;

            default:
                System.out.println();
        }

    }

    public void inquiryAllParts() {

        inquiryParts("CPU");
        inquiryParts("RAM");
        inquiryParts("GraphicCard");

    }

    public void sale() {

        CPU cpu  = new CPU();
        RAM ram = new RAM();
        GraphicCard graphicCard = new GraphicCard();

        inquiryAllParts();
        Map<String, String> information = partsManagementUI.salePartsUI();


        switch (new Integer(information.get("select"))) {
            case CPU_NUM:
                deletePart(information.get("name"), cpu);
                break;
            case RAM_NUM:
                deletePart(information.get("name"), ram);
                break;
            case GRAPHIC_CARD_NUM:
                deletePart(information.get("name"), graphicCard);
                break;
        }

    }

}
