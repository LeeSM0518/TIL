package seminar_project.ui;

import seminar_project.parts.*;

import java.util.*;

public class PartsManagementUI {

    private static Scanner scanner = new Scanner(System.in);

    // TODO 부품서비스 클래스에서 조회할 때 import 해서 UI 부분 사용
    public <T> void inquiryPart(final T t) {

        Part part = (Part) t;
        System.out.println("제품명 : " + part.getProductName());
        System.out.println("성능 : " + part.getPerformance());
        System.out.println("가격 : " + part.getPrice());
        System.out.println();

    }

    // TODO 메인메뉴에서 부품구매 호출시 사용
    public void purchasePartsUI() {

        System.out.println("(1) CPU");
        System.out.println("(2) RAM");
        System.out.println("(3) Graphic Card");
        System.out.print("구매할 부품을 번호로 입력해주세요 : ");

    }

    // TODO 부품을 선택할 때 사용하고 int 타입 입력에 대한 예외 처리
    //  (예외 처리 및 중복 코드 수정해야함)
    private Integer selectPartNumber() {

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
    public Map<String, String> partInformation() {

        Map<String, String> information = new HashMap<>();

        int part = selectPartNumber();
        int price;

        scanner.nextLine();

        information.put("select", Integer.toString(part));
        System.out.println("==============================");
        System.out.print("구매할 부품의 제품명을 입력해주세요 : ");
        information.put("name", informationInput());
        System.out.print("구매할 부품의 성능을 입력해주세요 : ");
        information.put("performance", informationInput());

        try {
            System.out.print("구매할 재고의 가격을 입력해주세요 : ");
            price = scanner.nextInt();
        } catch (InputMismatchException exception) {
            scanner.nextLine();
            System.out.println("숫자로 입력해주세요.");
            System.out.print("입력 : ");
            price = scanner.nextInt();
        }

        information.put("price", Integer.toString(price));

        return information;

    }

    // TODO 정보에 대한 입력을 확인해주는 메소드
    public String informationInput() {

        String information = scanner.nextLine();

        while (true) {
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
    public Map<String, String> salePartsUI() {

        Map<String, String> information = new HashMap<>();

        System.out.println("==============================");
        System.out.println("(1) CPU");
        System.out.println("(2) RAM");
        System.out.println("(3) Graphic Card");
        System.out.print("판매할 부품을 번호로 입력해주세요 : ");
        information.put("select", Integer.toString(selectPartNumber()));

        scanner.nextLine();

        System.out.print("판매할 부품의 이름을 입력해주세요 : ");
        information.put("name", informationInput());

        return information;

    }

    // TODO selectPart()
    //  1. 부품을 입력한다.
    //  2. 부품 서비스에서 존재하는지 확인한다.
    public String inputPartNameUI() {

        scanner.nextLine();

        System.out.println("부품의 제품명을 입력해주세요.");
        System.out.print("입력 : ");
        String partName = informationInput();

        return partName;
    }

}