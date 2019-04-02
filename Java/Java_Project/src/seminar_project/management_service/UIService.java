package seminar_project.management_service;

import seminar_project.parts.Part;

import java.util.*;

public class UIService {

    private static Scanner scanner = new Scanner(System.in);

    private static final int INQUIRY_PARTS = 1;
    private static final int PURCHASE_PARTS = 2;
    private static final int SALE_PARTS = 3;
    private static final int MAKE_DESKTOP = 4;
    private static final int INQUIRY_DESKTOPS = 5;
    private static final int SALE_DESKTOP = 6;
    private static final int EXIT_PROGRAM = 7;

    private PartsManagementService partsManagementService = new PartsManagementService();
    private DesktopManagementService desktopManagementService = new DesktopManagementService();

    // TODO 메인메뉴에서 선택한 메뉴에 따라 UI를 출력시키거나 서비스를 사용한다.
    public void mainMenu () {

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
                inquiryPartsUI();
                break;

            case PURCHASE_PARTS:
                System.out.println("==============================");
                purchasePartsUI();
                break;

            case SALE_PARTS:
                System.out.println("==============================");
                salePartsUI();
                break;

            case MAKE_DESKTOP:
                partsManagementService.checkPartsList();
                break;

            case INQUIRY_DESKTOPS:
                break;

            case SALE_DESKTOP:
                break;

            case EXIT_PROGRAM:
                break;
        }
    }

    // TODO 부품 조회 메소드
    private void inquiryPartsUI() {
        System.out.println("CPU 재고");
        partsManagementService.inquiryParts("CPU");
        System.out.println("RAM 재고");
        partsManagementService.inquiryParts("RAM");
        System.out.println("Graphic Card 재고");
        partsManagementService.inquiryParts("GraphicCard");
    }

    // TODO 부품서비스 클래스에서 조회할 때 import 해서 UI 부분 사용
    static <T> void inquiryPart(final T t) {

        Part part = (Part) t;
        System.out.println("제품명 : " + part.getProductName());
        System.out.println("성능 : " + part.getPerformance());
        System.out.println("가격 : " + part.getPrice());
        System.out.println();

    }

    // TODO 메인메뉴에서 부품구매 호출시 사용
    private void purchasePartsUI () {

        int select;
        System.out.println("(1) CPU");
        System.out.println("(2) RAM");
        System.out.println("(3) Graphic Card");
        System.out.print("구매할 부품을 번호로 입력해주세요 : ");
        select = partSelect();
        partInformation(select);

    }

    // TODO 부품을 선택할 때 사용하고 int 타입 입력에 대한 예외 처리
    //  (예외 처리 및 중복 코드 수정해야함)
    private int partSelect () {

        int select = -1;

        while (true) {
            try {
                select = scanner.nextInt();
            } catch (InputMismatchException ex) {
                scanner.nextLine();
                System.out.println("잘못된 입력입니다. 1~3 까지의 숫자로 입력해주세요.");
                System.out.print("입력 : ");
                select = scanner.nextInt();
            } finally { // 중복코드 => 재사용 가능 코드
                if (select > 3 || select < 0) {
                    System.out.println("잘못된 입력입니다. 1~3 까지의 숫자로 입력해주세요.");
                    System.out.print("입력 : ");
                } else {
                    break;
                }
            }
        }

        return select;
    }


    // TODO 부품에 대한 정보를 입력받을 때 사용
    //  (예외 처리 수정해야하고 정보 입력 메소드인데 부품 구매도 같이 하기 때문에
    //  수정 해야함)
    private void partInformation (final int part) {

        String name;
        String performance;
        int price;

        scanner.nextLine();

        System.out.println("==============================");
        System.out.print("구매할 부품의 제품명을 입력해주세요 : ");
        name = informationInput();
        System.out.print("구매할 부품의 성능을 입력해주세요 : ");
        performance = informationInput();

        try {
            System.out.print("구매할 재고의 가격을 입력해주세요 : ");
            price = scanner.nextInt();
        } catch (InputMismatchException exception) {
            scanner.nextLine();
            System.out.println("숫자로 입력해주세요.");
            System.out.print("입력 : ");
            price = scanner.nextInt();
        }

        partsManagementService.purchase(part, name, performance, price);

    }

    // TODO 정보에 대한 입력을 확인해주는 메소드
    private String informationInput() {

        String information = scanner.nextLine();

        while(true){
            if (information.equals("")) {
                System.out.println("다시 입력해주세요.");
                System.out.print("입력 : ");
                information = scanner.nextLine();
            } else {
                return information;
            }
        }

    }


    // TODO 부품 판매 메소드
    private void salePartsUI() {

        System.out.println("==============================");
        System.out.println("(1) CPU");
        System.out.println("(2) RAM");
        System.out.println("(3) Graphic Card");
        System.out.print("판매할 부품을 번호로 입력해주세요 : ");
        int partSelect = partSelect();

        scanner.nextLine();

        System.out.print("판매할 부품의 이름을 입력해주세요 : ");
        String partName = informationInput();

        partsManagementService.sale(partSelect, partName);

    }

    // TODO makeDesktopUI()
    //  1. 부품 서비스에서 부품들이 한 개 이상씩 있는지 확인한다.
    //  2. UI 에서 부품을 고른다.
    //  3. 그 부품이 있는지 부품 서비스에서 확인한다.
    //  4. 모든 부품 선택이 끝나면 UI 에서 선택한 부품들을
    //  데스크탑 서비스에서 제작하고 부품 서비스 부품을 삭제시킨다.
    private void makeDesktopUI() {

        if (!partsManagementService.checkPartsList()) {
            System.out.println("부품이 부족합니다.");
            return;
        }



    }

    // TODO selectPart()
    //  1. 부품을 입력한다.
    //  2. 부품 서비스에서 존재하는지 확인한다.
//    private List<Part> selectPart() {
//
//    }

    // TODO inquiryDesktopUI()
    //  1. 데스크탑 서비스에서 Desktop 스트림을 받는다.
    //  2. Desktop 스트림을 forEach 로 출력시킨다.
    private void inquiryDesktopUI() {

    }

    // TODO saleDesktopUI()
    //  1. 데스크탑 서비스에서 제작되어 있는 데스크탑이 1개 이상인지 확인한다.
    //  2. UI 에서 판매할 데스크탑의 제품명을 입력한다.
    //  3. 데스크탑 서비스에서 그 제품명이 존재하는지 확인한다.
    //  4. 데스크탑 서비스에서 판매한 데스크탑을 삭제시킨다.
    private void saleDesktopUI() {

    }

    public static void main(String[] args) {

        UIService UI = new UIService();

        while (true) {
            UI.mainMenu();
        }

    }
}