package seminar_project;

import seminar_project.management_service.*;

import java.util.Scanner;

public class Client {

    private static Scanner scanner = new Scanner(System.in);

    private StocksManagementService stocksManagementService = new StocksManagementService();
    private PartsManagementService partsManagementService = new PartsManagementService(stocksManagementService);
    private DesktopsManagementService desktopsManagementService = new DesktopsManagementService(stocksManagementService);

    private static final int INQUIRY_PARTS = 1;
    private static final int PURCHASE_PARTS = 2;
    private static final int SALE_PARTS = 3;
    private static final int MAKE_DESKTOP = 4;
    private static final int INQUIRY_DESKTOPS = 5;
    private static final int SALE_DESKTOP = 6;
    private static final int EXIT_PROGRAM = 7;

    private boolean mainMenu() {

        System.out.println("==============================");
        System.out.println("1. 부품 조회");
        System.out.println("2. 부품 구매");
        System.out.println("3. 부품 판매");
        System.out.println("4. 본체 제작");
        System.out.println("5. 본체 조회");
        System.out.println("6. 본체 판매");
        System.out.println("7. 종료");
        System.out.print("입력 : ");
        int mainMenuSelect = scanner.nextInt();

        switch (mainMenuSelect) {
            case INQUIRY_PARTS:
                System.out.println("==============================");
                partsManagementService.inquiryAllParts();
                break;

            case PURCHASE_PARTS:
                System.out.println("==============================");
                partsManagementService.purchase();
                break;

            case SALE_PARTS:
                System.out.println("==============================");
                partsManagementService.sale();
                break;

            case MAKE_DESKTOP:
                System.out.println("==============================");
                desktopsManagementService.makeDesktop();
                break;

            case INQUIRY_DESKTOPS:
                System.out.println("==============================");
                desktopsManagementService.inquiryDesktops();
                break;

            case SALE_DESKTOP:
                System.out.println("==============================");
                desktopsManagementService.sale();
                break;

            case EXIT_PROGRAM:
                return true;

            default:
                System.out.println("다시 입력해주세요.");
        }

        return false;
    }

    public static void main(String[] args) {

        Client client = new Client();

        boolean exit;

        do {
            exit = client.mainMenu();
        } while (!exit);

    }

}
