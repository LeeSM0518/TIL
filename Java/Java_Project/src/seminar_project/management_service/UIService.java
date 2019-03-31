package seminar_project.management_service;

import seminar_project.parts.Parts;

import java.util.*;

public class UIService {
    static final int SUCCESS = 1;
    static final int ERROR = -1;

    private static Scanner scanner = new Scanner(System.in); // 정적으로 사용할 때는 목적이 확실해야함, 함수를 공유해서 사용하기 때문애 원치 않는 결과 생성


    private static final int INQUIRY_PARTS = 1;
    private static final int PURCHASE_PARTS = 2;
    private static final int SALE_PARTS = 3;
    private static final int MAKE_DESKTOP = 4;
    private static final int INQUIRY_DESKTOPS = 5;
    private static final int SALE_DESKTOP = 6;
    private static final int EXIT_PROGRAM = 7;

    private PartsManagementService partsManagementService = new PartsManagementService();


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
                partsManagementService.inquiry();
                break;

            case PURCHASE_PARTS:
                System.out.println("==============================");
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

    static <T> void inquiryPartsUI (final T t) {

        Parts part = (Parts) t;
        System.out.println("제품명 : " + part.getProductName());
        System.out.println("성능 : " + part.getPerformance());
        System.out.println("가격 : " + part.getPrice());
        System.out.println();

    }

    private void purchasePartsUI () {

        int select;
        System.out.println("(1) CPU");
        System.out.println("(2) RAM");
        System.out.println("(3) Graphic Card");
        System.out.print("구매할 부품을 번호로 입력해주세요 : ");
        select = partSelect();
        partInformation(select);

    }

    private int partSelect () {

        int select = -1;

        while (true) {
            try {
                select = scanner.nextInt();
            } catch (InputMismatchException ex) { // 에러처리 잘못됨
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

    public void partInformation (final int part) {
        String name;
        String performance;
        int price;

        scanner.nextLine();

        System.out.println("==============================");
        System.out.print("구매할 재고의 제품명을 입력해주세요 : ");
        name = informationInput();
        System.out.print("구매할 재고의 성능을 입력해주세요 : ");
        performance = informationInput();

        try {
            System.out.print("구매할 재고의 가격을 입력해주세요 : ");
            price = scanner.nextInt();
        } catch (InputMismatchException exception) {
            scanner.nextLine();
            System.out.println("숫자로 입력해주세요.");
            System.out.print("입력 : ");
            price = scanner.nextInt();
            // 나라면 이렇게 안짬.
        }

        partsManagementService.purchase(part, name, performance, price);
    }

    public String informationInput() {
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

    public void salePartsUI() {
        System.out.println("==============================");
        System.out.println("(1) CPU");
        System.out.println("(2) RAM");
        System.out.println("(3) Graphic Card");
        System.out.print("판매할 부품을 번호로 입력해주세요 : ");
        int partSelect = partSelect();
        System.out.print("판매할 부품의 이름을 입력해주세요 : ");
        String partName = informationInput();

        partsManagementService.sale(partSelect, partName);
    }

    public static void main(String[] args) {
        UIService UI = new UIService();

        while (true) {
            UI.mainMenu();
        }
    }
}