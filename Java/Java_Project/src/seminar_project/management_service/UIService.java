package seminar_project.management_service;

import seminar_project.parts.CPU;
import seminar_project.parts.Parts;

import java.util.*;

public class UIService {

    Scanner scanner = new Scanner(System.in);

    final int SUCCESS = 1;
    final int ERROR = -1;

    private final int INQUIRY_PARTS = 1;
    private final int PURCHASE_PARTS = 2;
    private final int SALE_PARTS = 3;
    private final int MAKE_DESKTOP = 4;
    private final int INQUIRY_DESKTOPS = 5;
    private final int SALE_DESKTOP = 6;
    private final int EXIT_PROGRAM = 7;

    private PartsManagementService partsManagementService = new PartsManagementService();

    private int mainMenuSelect;
    private int partSelect;

    public void mainMenu() {
        System.out.println("==============================");
        System.out.println("1. 부품 조회");
        System.out.println("2. 부품 구매");
        System.out.println("3. 부품 판매");
        System.out.println("4. 본체 제작");
        System.out.println("5. 본체 조회");
        System.out.println("6. 본체 판매");
        System.out.println("7. 종료");
        System.out.print("입력 : ");
        mainMenuSelect = scanner.nextInt();

        switch (mainMenuSelect) {
            case INQUIRY_PARTS:
                partsManagementService.inquiry();
                break;

            case PURCHASE_PARTS:
                purchasePartsUI();
                break;

            case SALE_PARTS:
                break;

            case MAKE_DESKTOP:
                break;
            case INQUIRY_DESKTOPS:
                break;

            case SALE_DESKTOP:
                break;

            case EXIT_PROGRAM:
                break;
        }
    }

    public static <T> void inquiryPartsUI(T t) {
        Parts part = (Parts) t;
        System.out.println("제품명 : " + part.getProductName());
        System.out.println("성능 : " + part.getPerformance());
        System.out.println("가격 : " + part.getPrice());
        System.out.println();
    }

    public void purchasePartsUI() {
        System.out.println("==============================");
        System.out.println("(1) CPU");
        System.out.println("(2) RAM");
        System.out.println("(3) Graphic Card");
        System.out.print("구매할 부품을 번호로 입력해주세요 : ");

        while (true) {
            try {
                partSelect = scanner.nextInt();
            } catch (InputMismatchException ex) {
                scanner.nextLine();
                System.out.println("잘못된 입력입니다. 1~3 까지의 숫자로 입력해주세요.");
                System.out.print("입력 : ");
                partSelect = scanner.nextInt();
            } finally {
                if (partSelect > 3 || partSelect < 0) {
                    System.out.println("잘못된 입력입니다. 1~3 까지의 숫자로 입력해주세요.");
                    System.out.print("입력 : ");
                } else {
                    partInformation(partSelect);
                    break;
                }
            }
        }
    }

    public void partInformation(final int part) {
        String partName;
        String partPerformance;
        int partPrice;

        scanner.nextLine();

        System.out.println("==============================");
        System.out.print("구매할 재고의 제품명을 입력해주세요 : ");
        partName = scanner.nextLine();
        System.out.print("구매할 재고의 성능을 입력해주세요 : ");
        partPerformance = scanner.nextLine();

        try {
            System.out.print("구매할 재고의 가격을 입력해주세요 : ");
            partPrice = scanner.nextInt();
        } catch (InputMismatchException exception) {
            scanner.nextLine();
            System.out.println("숫자로 입력해주세요.");
            System.out.print("입력 : ");
            partPrice = scanner.nextInt();
        }

        partsManagementService.purchase(
                part, partName, partPerformance, partPrice
        );
    }

    public static void main(String[] args) {
        UIService UI = new UIService();

        while (true) {
            UI.mainMenu();
        }
    }
}