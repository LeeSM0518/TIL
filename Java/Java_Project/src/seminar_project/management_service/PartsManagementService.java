package seminar_project.management_service;

import seminar_project.parts.*;
import seminar_project.ui.*;

import java.util.Map;

public class PartsManagementService extends StocksManagementService {

    private PartsManagementUI partsManagementUI = new PartsManagementUI();

    public PartsManagementService() {}

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

    public void sale() {

        Map<String, String> information = partsManagementUI.salePartsUI();
        inquirySelectedPart(new Integer(information.get("select")));

        switch (new Integer(information.get("select"))) {
            case CPU_NUM:
                deletePart(information.get("name"), CPU.class);
                break;
            case RAM_NUM:
                deletePart(information.get("name"), RAM.class);
                break;
            case GRAPHIC_CARD_NUM:
                deletePart(information.get("name"), GraphicCard.class);
                break;
        }

    }

    private void inquirySelectedPart(int part) {

        switch (part) {
            case CPU_NUM:
                inquiryParts("CPU");
                break;
            case RAM_NUM:
                inquiryParts("RAM");
                break;
            case GRAPHIC_CARD_NUM:
                inquiryParts("GraphicCard");
                break;
        }

    }



}
